package fuzzyacornindustries.kindredlegacy.entity.mob.hostile;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.client.particle.ParticleConfusion;
import fuzzyacornindustries.kindredlegacy.entity.ability.EntitySubstituteDoll;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.AIGeneralRangedAttack;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.AIMuBunearyChannelConfuse;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.AIMuBunearySpinAttack;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyLootTables;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityMuBuneary extends HostilePokemon implements IAnimatedEntity, IRangedAttackMob
{
	protected static final DataParameter<Byte> CAN_CAST_BOOLEAN = EntityDataManager.<Byte>createKey(EntityMuBuneary.class, DataSerializers.BYTE);

	private IdleAnimationClock earsIdleAnimationClock[] = new IdleAnimationClock[15];
	private IdleAnimationClock bodyIdleAnimationClock;
	private IdleAnimationClock tailIdleAnimationClock[] = new IdleAnimationClock[3];
	private IdleAnimationClock armsIdleAnimationClock[] = new IdleAnimationClock[2];

	public static final int actionIDCastConfuse = 1;
	public static final int actionIDSpinAttack = 2;

	protected static final int CAN_CAST = 17;

	public float meleeAttackRange = 1.3F;
	public float rangedAttackRange = 10.0F;

	static String mobName = "mu_buneary";

	public EntityMuBuneary(World par1World)
	{
		super(par1World);

		this.setSize(0.4F, 1.4F);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(3, new AIMuBunearyChannelConfuse(this, rangedAttackRange));
		this.tasks.addTask(4, new AIGeneralRangedAttack(this, 1.0D, 20, rangedAttackRange));
		this.tasks.addTask(5, new EntityAIWander(this, 0.6D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySubstituteDoll.class, true));

		if(this.world.isRemote)
		{
			setClockDefaults();
		}
	}

	public static String getMobName()
	{
		return mobName;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.33D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();

		this.dataManager.register(CAN_CAST_BOOLEAN, Byte.valueOf(Byte.valueOf((byte)0)));
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if(this.getAnimationID() == this.actionIDCastConfuse && this.world.isRemote)
		{
			if(this.world.rand.nextInt(10) == 0)
			{
				spawnConfusionParticle();
			}
		}

		if(animationID != 0) animationTick++;
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase targetEntity, float par2)
	{
		if(animationID == 0)
		{
			if(this.canCastConfuse())
			{
				KindredLegacyMain.sendAnimationPacket(this, this.actionIDCastConfuse);
			}
			else
			{
				KindredLegacyMain.sendAnimationPacket(this, this.actionIDSpinAttack);
			}
		}
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {}

	@SideOnly(Side.CLIENT)
	public void spawnConfusionParticle()
	{
		double motionX = (this.world.rand.nextDouble() - 0.5D) * 0.1D;
		double motionY = this.world.rand.nextDouble() * 0.1D;
		double motionZ = (this.world.rand.nextDouble() - 0.5D) * 0.1D;

		ParticleConfusion confusionParticle = new ParticleConfusion(this.world, this.posX + (this.world.rand.nextFloat() - 0.5D) * this.width * 1D, 
				this.posY + this.world.rand.nextFloat() * this.height - 0.5F, this.posZ + (this.world.rand.nextFloat() - 0.5D) * this.width * 1D, motionX, motionY, motionZ);

		Minecraft.getMinecraft().effectRenderer.addEffect(confusionParticle);
	}

	public void addMeleeAITasks()
	{
		this.tasks.addTask(1, new AIMuBunearySpinAttack(this));
		this.tasks.addTask(2, new AIGeneralRangedAttack(this, 1.0D, 20, 20, meleeAttackRange));
	}

	public void toggleConfuseCastability() 
	{
		if (!world.isRemote) 
		{
			addMeleeAITasks();

			this.dataManager.set(CAN_CAST_BOOLEAN, (byte)1);
		}
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
		if (this.isEntityInvulnerable(damageSource))
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
		return KindredLegacySoundEvents.MU_BUNEARY_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return KindredLegacySoundEvents.MU_BUNEARY_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return KindredLegacySoundEvents.MU_BUNEARY_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);

		if (par1NBTTagCompound.hasKey("ConfusionCastability", 99))
		{
			byte b0 = par1NBTTagCompound.getByte("ConfusionCastability");
			this.dataManager.set(CAN_CAST_BOOLEAN, b0);
		}

		if(!canCastConfuse())
		{
			addMeleeAITasks();
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setByte("ConfusionCastability", this.dataManager.get(CAN_CAST_BOOLEAN).byteValue());
	}

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return KindredLegacyLootTables.MU_BUNEARY_LOOT_TABLE;
    }

	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockEars(int partNumber)
	{
		return earsIdleAnimationClock[partNumber];
	}

	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockBody()
	{
		return bodyIdleAnimationClock;
	}

	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockArms(int partNumber)
	{
		return armsIdleAnimationClock[partNumber];
	}

	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockTail(int partNumber)
	{
		return tailIdleAnimationClock[partNumber];
	}

	@SideOnly(Side.CLIENT)
	@Override
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

	@SideOnly(Side.CLIENT)
	public void setClockDefaults()
	{
		setEarsClockDefaults();
		setBodyClockDefaults();
		setArmsClockDefaults();
		setTailClockDefaults();
	}

	@SideOnly(Side.CLIENT)
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

	@SideOnly(Side.CLIENT)
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

	@SideOnly(Side.CLIENT)
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

	@SideOnly(Side.CLIENT)
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