package fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile;

import fuzzyacornindustries.kindredlegacy.client.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.client.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.common.core.ModEntities;
import fuzzyacornindustries.kindredlegacy.common.core.ModSounds;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.ai.GeneralRangedAttackGoal;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.ai.MuBunearyChannelConfuseGoal;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.ai.MuBunearySpinAttackGoal;
import fuzzyacornindustries.kindredlegacy.common.network.ActionAnimationMessage;
import fuzzyacornindustries.kindredlegacy.common.network.NetworkHandler;
import fuzzyacornindustries.kindredlegacy.common.network.SpawnParticlePacket;
import fuzzyacornindustries.kindredlegacy.common.particle.ConfuseParticleData;
import fuzzyacornindustries.kindredlegacy.lib.action.LibraryOkamiPokemonAttackID;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.PacketDistributor;

public class MuBunearyEntity extends HostilePokemon implements IAnimatedEntity, IRangedAttackMob
{
	protected static final DataParameter<Byte> CAN_CAST_BOOLEAN = EntityDataManager.<Byte>createKey(MuBunearyEntity.class, DataSerializers.BYTE);

	private MuBunearySpinAttackGoal spinAttackGoal;
	private GeneralRangedAttackGoal meleeRangeAttackGoal;

	private IdleAnimationClock earsIdleAnimationClock[] = new IdleAnimationClock[15];
	private IdleAnimationClock bodyIdleAnimationClock;
	private IdleAnimationClock tailIdleAnimationClock[] = new IdleAnimationClock[3];
	private IdleAnimationClock armsIdleAnimationClock[] = new IdleAnimationClock[2];

	public static final int actionIDCastConfuse = 1;
	public static final int actionIDSpinAttack = 2;

	public final float meleeAttackRange = 1.3F;
	public final float rangedAttackRange = 10.0F;

	static String mobName = "mu_buneary";

	public MuBunearyEntity(EntityType<? extends MuBunearyEntity> type, World world)
	{
		super(type, world);

		if(this.world.isRemote)
		{
			setClockDefaults();
		}

		animationID = actionIDNone;
		animationTick = 0;
	}

	public MuBunearyEntity(World world) 
	{
		this(ModEntities.MU_BUNEARY.get(), world);
	}

	public static String getMobName()
	{
		return mobName;
	}

	@Override
	protected void registerGoals() 
	{
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(3, new MuBunearyChannelConfuseGoal(this, rangedAttackRange));
		this.goalSelector.addGoal(4, new GeneralRangedAttackGoal(this, 1.0D, 20, rangedAttackRange));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();

		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.33D);
	}

	@Override
	protected void registerData() 
	{
		super.registerData();

		this.dataManager.register(CAN_CAST_BOOLEAN, Byte.valueOf((byte)0));
	}
	@Override
	public void tick()
	{
		super.tick();

		if(this.getAnimationID() == MuBunearyEntity.actionIDCastConfuse)// && this.world.isRemote)
		{
			if(this.world.rand.nextInt(3) == 0)
			{
				spawnConfusionParticle();
			}
		}

		if(!this.canCastConfuse())
			addMeleeAITasks();

		if(this.getAnimationID() != 0) animationTick++;
	}

	@Override
	public void attackEntityWithRangedAttack(LivingEntity targetEntity, float par2)
	{
		if(this.getAnimationID() == LibraryOkamiPokemonAttackID.NO_ACTION)
		{
			if(this.canCastConfuse())// && this.getDistanceSq(targetEntity.getPosX(), targetEntity.getPosYHeight(0.5), targetEntity.getPosZ()) <= this.rangedAttackRange * this.rangedAttackRange)
			{
				NetworkHandler.sendToTarget(new ActionAnimationMessage((byte)MuBunearyEntity.actionIDCastConfuse, this.getEntityId()), PacketDistributor.TRACKING_ENTITY.with(() -> this));
				this.setAnimationID(MuBunearyEntity.actionIDCastConfuse);
			}
			else// if(this.getDistanceSq(targetEntity.getPosX(), targetEntity.getPosYHeight(0.5), targetEntity.getPosZ()) <= this.meleeAttackRange * meleeAttackRange)
			{
				NetworkHandler.sendToTarget(new ActionAnimationMessage((byte)MuBunearyEntity.actionIDSpinAttack, this.getEntityId()), PacketDistributor.TRACKING_ENTITY.with(() -> this));
				this.setAnimationID(MuBunearyEntity.actionIDSpinAttack);
			}

			//System.out.println( "Entity ID: " + Integer.toString( this.getEntityId() ) );
		}
	}

	public void spawnConfusionParticle()
	{
		double multiplier = 1.5D;
		double motionX = (this.world.rand.nextDouble() - 0.5D) * multiplier;
		double motionY = this.world.rand.nextDouble() * 1D;
		double motionZ = (this.world.rand.nextDouble() - 0.5D) * multiplier;
		double xPos = this.getPosX() + (this.world.rand.nextFloat() - 0.5D) * this.getWidth();
		double yPos = this.getPosY() + this.world.rand.nextFloat() * this.getHeight() - 0.5F;
		double zPos = this.getPosZ() + (this.world.rand.nextFloat() - 0.5D) * this.getWidth();

		NetworkHandler.sendToAllAround(new SpawnParticlePacket(ConfuseParticleData.NORMAL, xPos, yPos, zPos, motionX, motionY, motionZ), world);
		//this.getEntityWorld().addParticle(ModParticleTypes.CONFUSE_PARTICLE, xPos, yPos, zPos, motionX, motionY, motionZ);
	}

	public void addMeleeAITasks()
	{
		if(this.spinAttackGoal == null)
		{
			this.spinAttackGoal = new MuBunearySpinAttackGoal(this);
			this.goalSelector.addGoal(1, this.spinAttackGoal);
		}

		if(this.meleeRangeAttackGoal == null)
		{
			this.meleeRangeAttackGoal = new GeneralRangedAttackGoal(this, 1.0D, 20, meleeAttackRange);
			this.goalSelector.addGoal(2, this.meleeRangeAttackGoal);
		}
	}

	public void toggleConfuseCastability() 
	{
		this.dataManager.set(CAN_CAST_BOOLEAN, Byte.valueOf((byte)1));
	}

	public boolean canCastConfuse()
	{
		if (this.getHealth() < this.getMaxHealth())
		{
			return false;
		}

		return this.dataManager.get(CAN_CAST_BOOLEAN).byteValue() == (byte)0;
	}

	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float damageAmount)
	{
		if (this.isInvulnerable())
		{
			return false;
		}
		else
		{
			this.toggleConfuseCastability();

			return super.attackEntityFrom(damageSource, damageAmount);
		}
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSounds.MU_BUNEARY_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return ModSounds.MU_BUNEARY_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSounds.MU_BUNEARY_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) 
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
	}

	@Override
	public void read(CompoundNBT nbt)
	{
		super.read(nbt);

		if (nbt.contains("ConfusionCastability", 99))
		{
			byte b0 = nbt.getByte("ConfusionCastability");
			this.dataManager.set(CAN_CAST_BOOLEAN, b0);
		}

		if(!canCastConfuse())
		{
			addMeleeAITasks();
		}
	}

	@Override
	public void writeAdditional(CompoundNBT nbt)
	{
		super.writeAdditional(nbt);

		nbt.putByte("ConfusionCastability", this.dataManager.get(CAN_CAST_BOOLEAN).byteValue());
	}

	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	@OnlyIn(Dist.CLIENT)
	public IdleAnimationClock getIdleAnimationClockEars(int partNumber)
	{
		return earsIdleAnimationClock[partNumber];
	}

	@OnlyIn(Dist.CLIENT)
	public IdleAnimationClock getIdleAnimationClockBody()
	{
		return bodyIdleAnimationClock;
	}

	@OnlyIn(Dist.CLIENT)
	public IdleAnimationClock getIdleAnimationClockArms(int partNumber)
	{
		return armsIdleAnimationClock[partNumber];
	}

	@OnlyIn(Dist.CLIENT)
	public IdleAnimationClock getIdleAnimationClockTail(int partNumber)
	{
		return tailIdleAnimationClock[partNumber];
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void incrementPartClocks()
	{
		for(int i = 0; i < earsIdleAnimationClock.length; i++)
		{
			earsIdleAnimationClock[i].incrementClocks();
		}

		bodyIdleAnimationClock.incrementClocks();

		for(int i = 0; i < armsIdleAnimationClock.length; i++)
		{
			armsIdleAnimationClock[i].incrementClocks();
		}

		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			tailIdleAnimationClock[i].incrementClocks();
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void setClockDefaults()
	{
		setEarsClockDefaults();
		setBodyClockDefaults();
		setArmsClockDefaults();
		setTailClockDefaults();
	}

	@OnlyIn(Dist.CLIENT)
	public void setBodyClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		bodyIdleAnimationClock = new IdleAnimationClock(1, 0, 0);

		bodyIdleAnimationClock.setPhaseDurationX(0, 80);

		int startingClockX = randomInt;

		while(startingClockX > bodyIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= bodyIdleAnimationClock.getTotalDurationLengthX();
		}

		bodyIdleAnimationClock.setClockX(startingClockX);
	}

	@OnlyIn(Dist.CLIENT)
	public void setArmsClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		for(int i = 0; i < armsIdleAnimationClock.length; i++)
		{
			armsIdleAnimationClock[i] = new IdleAnimationClock(1, 1, 0);

			armsIdleAnimationClock[i].setPhaseDurationX(0, 50);
			armsIdleAnimationClock[i].setPhaseDurationY(0, 80);

			float frequencyX = 0.25F;
			float frequencyY = 0.20F;

			int startingClockX = (int)(((float)(armsIdleAnimationClock.length - i) / (float)armsIdleAnimationClock.length) * (float)armsIdleAnimationClock[i].getTotalDurationLengthX() * frequencyX) + randomInt;
			int startingClockY = (int)(((float)(armsIdleAnimationClock.length - i) / (float)armsIdleAnimationClock.length) * (float)armsIdleAnimationClock[i].getTotalDurationLengthY() * frequencyY) + randomInt;

			while(startingClockX > armsIdleAnimationClock[i].getTotalDurationLengthX())
			{
				startingClockX -= armsIdleAnimationClock[i].getTotalDurationLengthX();
			}

			while(startingClockY > armsIdleAnimationClock[i].getTotalDurationLengthY())
			{
				startingClockY -= armsIdleAnimationClock[i].getTotalDurationLengthY();
			}

			armsIdleAnimationClock[i].setClockX(startingClockX);
			armsIdleAnimationClock[i].setClockY(startingClockY);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void setEarsClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		for(int i = 0; i < earsIdleAnimationClock.length; i++)
		{
			earsIdleAnimationClock[i] = new IdleAnimationClock(1, 0, 1);

			earsIdleAnimationClock[i].setPhaseDurationX(0, 80);
			earsIdleAnimationClock[i].setPhaseDurationZ(0, 90);

			float frequencyX = 0.65F;
			float frequencyZ = 0.60F;

			int startingClockX = (int)(((float)(earsIdleAnimationClock.length - i) / (float)earsIdleAnimationClock.length) * (float)earsIdleAnimationClock[i].getTotalDurationLengthX() * frequencyX) + randomInt;
			int startingClockZ = (int)(((float)(earsIdleAnimationClock.length - i) / (float)earsIdleAnimationClock.length) * (float)earsIdleAnimationClock[i].getTotalDurationLengthZ() * frequencyZ) + randomInt;

			while(startingClockX > earsIdleAnimationClock[i].getTotalDurationLengthX())
			{
				startingClockX -= earsIdleAnimationClock[i].getTotalDurationLengthX();
			}

			while(startingClockZ > earsIdleAnimationClock[i].getTotalDurationLengthZ())
			{
				startingClockZ -= earsIdleAnimationClock[i].getTotalDurationLengthZ();
			}

			earsIdleAnimationClock[i].setClockX(startingClockX);
			earsIdleAnimationClock[i].setClockZ(startingClockZ);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void setTailClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			tailIdleAnimationClock[i] = new IdleAnimationClock(1, 1, 0);

			tailIdleAnimationClock[i].setPhaseDurationX(0, 20);
			tailIdleAnimationClock[i].setPhaseDurationY(0, 80);

			float frequencyX = 0.35F;
			float frequencyY = 0.30F;

			int startingClockX = (int)(((float)(tailIdleAnimationClock.length - i) / (float)tailIdleAnimationClock.length) * (float)tailIdleAnimationClock[i].getTotalDurationLengthX() * frequencyX) + randomInt;
			int startingClockY = (int)(((float)(tailIdleAnimationClock.length - i) / (float)tailIdleAnimationClock.length) * (float)tailIdleAnimationClock[i].getTotalDurationLengthY() * frequencyY) + randomInt;

			while(startingClockX > tailIdleAnimationClock[i].getTotalDurationLengthX())
			{
				startingClockX -= tailIdleAnimationClock[i].getTotalDurationLengthX();
			}

			while(startingClockY > tailIdleAnimationClock[i].getTotalDurationLengthY())
			{
				startingClockY -= tailIdleAnimationClock[i].getTotalDurationLengthY();
			}

			tailIdleAnimationClock[i].setClockX(startingClockX);
			tailIdleAnimationClock[i].setClockY(startingClockY);
		}
	}
}