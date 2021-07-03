package fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable;

import fuzzyacornindustries.kindredlegacy.client.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.animation.ModMathFunctions;
import fuzzyacornindustries.kindredlegacy.client.animation.Vector3f;
import fuzzyacornindustries.kindredlegacy.common.core.ModEntities;
import fuzzyacornindustries.kindredlegacy.common.core.ModItems;
import fuzzyacornindustries.kindredlegacy.common.core.ModSounds;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.IAnimateAhriNinetales;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.ai.GeneralRangedAttackGoal;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.ai.VastayaNinetalesFireballGoal;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.ai.VastayaNinetalesJumpFireballGoal;
import fuzzyacornindustries.kindredlegacy.common.entity.projectile.VastayaFireballEntity;
import fuzzyacornindustries.kindredlegacy.common.network.ActionAnimationMessage;
import fuzzyacornindustries.kindredlegacy.common.network.NetworkHandler;
import fuzzyacornindustries.kindredlegacy.lib.action.LibraryAhriNinetalesAttackID;
import fuzzyacornindustries.kindredlegacy.lib.action.LibraryOkamiPokemonAttackID;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.OwnerHurtByTargetGoal;
import net.minecraft.entity.ai.goal.OwnerHurtTargetGoal;
import net.minecraft.entity.ai.goal.SitGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.PacketDistributor;

public class VastayaNinetalesEntity extends TamablePokemon implements IRangedAttackMob, IAnimateAhriNinetales
{
	private IdleAnimationClock bodyIdleAnimationClock;
	private IdleAnimationClock armRtIdleAnimationClock;
	private IdleAnimationClock tailsIdleAnimationClock[] = new IdleAnimationClock[8];
	private IdleAnimationClock orbIdleAnimationClock;

	static String mobName = "vastaya_ninetales";

	public static float defaultBaseAttackPower = 4F;
	public static float defaultBaseMaxHealth = 30F;
	public static float defaultBaseSpeed = 0.35F;

	public static int defaultRegenLevel = 1;

	public static float defaultMaximumAttackBoost = 10F;
	public static float defaultmaximumHealthBoost = 80F;
	public static float defaultmaximumSpeedBoost = 0.40F;

	public static int defaultArmor = 2;

	public final float attackRange = 14.0F;
	
	public VastayaNinetalesEntity(EntityType<? extends VastayaNinetalesEntity> type, World world)
	{
		super(type, world);

		if(this.world.isRemote)
		{
			setClockDefaults();
		}
		
		default_name = "Vastaya Ninetales";
		default_summon_item_name = "Vastaya Ninetales Summon";
		summonItem = new ItemStack(ModItems.VASTAYA_NINETALES_SUMMON.get());
	}

	public VastayaNinetalesEntity(World world) 
	{
		this(ModEntities.VASTAYA_NINETALES.get(), world);
	}

	public static String getMobName()
	{
		return mobName;
	}

	@Override
	public void setDefaultStats()
	{
		setDefaultBaseAttackPower(defaultBaseAttackPower);
		setDefaultBaseMaxHealth(defaultBaseMaxHealth);
		setDefaultBaseSpeed(defaultBaseSpeed);

		setDefaultRegenLevel(defaultRegenLevel);

		setDefaultMaximumAttackBoost(defaultMaximumAttackBoost);
		setDefaultMaximumHealthBoost(defaultMaximumHealthBoost);
		setDefaultMaximumSpeedBoost(defaultMaximumSpeedBoost);

		setDefaultArmor(defaultArmor);
	}

	protected void registerGoals() 
	{
		this.sitGoal = new SitGoal(this);
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(2, new VastayaNinetalesJumpFireballGoal(this));
		this.goalSelector.addGoal(2, new VastayaNinetalesFireballGoal(this));

		this.goalSelector.addGoal(3, this.sitGoal);
		this.goalSelector.addGoal(4, new GeneralRangedAttackGoal(this, 1.0D, 20, attackRange));
		this.goalSelector.addGoal(5, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.55D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(7, new LookAtGoal(this, TamablePokemon.class, 8.0F));
		this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
		//this.tasks.addTask(8, new AIGeneralBerryBeg(this, 8.0F));

		this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
		this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
		//this.targetSelector.addGoal(5, new EntityAINearestAttackableTarget(this, EntitySubstituteDoll.class, true));
	}

	@Override
	protected void registerData() 
	{
		super.registerData();

		this.dataManager.set(MAX_HEALTH, getDefaultBaseMaxHealth());
		this.dataManager.set(ATTACK_POWER, getDefaultBaseAttackPower());
		this.dataManager.set(SPEED, getDefaultBaseSpeed());

		this.dataManager.set(REGEN_LEVEL, (byte)getDefaultRegenLevel());

		this.setFireImmunityEssence(1);
		this.setBlockSuffocationAvoidanceEssence(1);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		if(this.isTamed() && this.getHealth() < (this.dataManager.get(MAX_HEALTH)).floatValue() / 4F)
		{
			return ModSounds.VASTAYA_NINETALES_WHINE.get();
		}
		else if(this.getSoundType() == 1)
		{
			return null;
		}
		else
		{
			return ModSounds.VASTAYA_NINETALES_AMBIENT.get();
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return ModSounds.VASTAYA_NINETALES_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSounds.VASTAYA_NINETALES_DEATH.get();
	}
	
	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeAdditional(CompoundNBT nbt)
	{
		super.writeAdditional(nbt);

		nbt.putByte("VastayaNinetalesTextureType", (byte)this.getMainTextureType());
		nbt.putByte("VastayaNinetalesSoundType", (byte)this.getSoundType());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void read(CompoundNBT nbt)
	{
		super.read(nbt);

		if (nbt.contains("VastayaNinetalesTextureType", 99))
		{
			byte b0 = nbt.getByte("VastayaNinetalesTextureType");
			this.setMainTextureType(b0);
		}

		if (nbt.contains("VastayaNinetalesSoundType", 99))
		{
			byte b0 = nbt.getByte("VastayaNinetalesSoundType");
			this.setSoundType(b0);
		}
	}

	@Override
	public void attackEntityWithRangedAttack(LivingEntity targetEntity, float distanceFactor)
	{
		if(this.getDistanceSq(targetEntity.getPosX(), targetEntity.getPosYHeight(0.5), targetEntity.getPosZ()) <= this.attackRange * this.attackRange)
		{
			if(this.getAnimationID() == LibraryOkamiPokemonAttackID.NO_ACTION)
			{
				int attackWeight = this.rand.nextInt(5);

				if(attackWeight < 2)
				{
					NetworkHandler.sendToTarget(new ActionAnimationMessage((byte)LibraryAhriNinetalesAttackID.JUMP_FIREBALL, this.getEntityId()), PacketDistributor.TRACKING_ENTITY.with(() -> this));
					this.setAnimationID(LibraryAhriNinetalesAttackID.JUMP_FIREBALL);
				}
				else
				{
					NetworkHandler.sendToTarget(new ActionAnimationMessage((byte)LibraryAhriNinetalesAttackID.FIREBALL, this.getEntityId()), PacketDistributor.TRACKING_ENTITY.with(() -> this));
					this.setAnimationID(LibraryAhriNinetalesAttackID.FIREBALL);
				}
			}
		}
	}

	public void attackWithFireball(VastayaNinetalesEntity attackingMob, LivingEntity targetEntity, float distanceFactor)
	{
		Vector3f spawnFireballPoint = new Vector3f(ModMathFunctions.findShooterOffsetPoint(attackingMob.getPosX(), attackingMob.getPosZ(), 
				targetEntity.getPosX(), targetEntity.getPosZ(), 1F));

		double d0 = targetEntity.getPosX() - spawnFireballPoint.getX();//this.posX;
		double d1 = targetEntity.getBoundingBox().minY + (double)(targetEntity.getHeight() / 2.0F) - (attackingMob.getPosY() + (double)(attackingMob.getHeight() / 2.0F));
		double d2 = targetEntity.getPosZ() - spawnFireballPoint.getZ();//this.posZ;

		//float f1 = MathHelper.sqrt(par2) * 0.1F;
		this.playSound(ModSounds.FIREBALL_SWOOSH.get(), 0.5F, 0.4F / (this.rand.nextFloat() * 0.4F + 0.8F));

		VastayaFireballEntity entitysmallfireball = new VastayaFireballEntity(attackingMob.world, attackingMob, 
				spawnFireballPoint.getX(), attackingMob.getPosY(), spawnFireballPoint.getZ(),
				d0 + targetEntity.getMotion().getX() * 0.5F, d1 + targetEntity.getMotion().getY() * 0.5F, d2 + targetEntity.getMotion().getZ() * 0.5F, 
				1.5F, this.getAttackPower());
		entitysmallfireball.setPosition(attackingMob.getPosX(), attackingMob.getPosY() + (double)(attackingMob.getHeight() / 2.0F) + 0.25F, attackingMob.getPosZ());

		this.world.addEntity(entitysmallfireball);
	}

	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	@Override
	@OnlyIn(Dist.CLIENT)
	public IdleAnimationClock getIdleAnimationClockBody()
	{
		return bodyIdleAnimationClock;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public IdleAnimationClock getIdleAnimationClockArmRt()
	{
		return armRtIdleAnimationClock;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public IdleAnimationClock getIdleAnimationClockTails(int partNumber)
	{
		return tailsIdleAnimationClock[partNumber];
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public IdleAnimationClock getIdleAnimationClockOrb()
	{
		return orbIdleAnimationClock;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void incrementPartClocks()
	{
		bodyIdleAnimationClock.incrementClocks();

		for(int i = 0; i < tailsIdleAnimationClock.length; i++)
		{
			tailsIdleAnimationClock[i].incrementClocks();
		}

		armRtIdleAnimationClock.incrementClocks();
		orbIdleAnimationClock.incrementClocks();
	}

	@OnlyIn(Dist.CLIENT)
	public void setClockDefaults()
	{
		setBodyClockDefaults();
		setArmRtClockDefaults();
		setTailsClockDefaults();
		setOrbClockDefaults();
	}

	@OnlyIn(Dist.CLIENT)
	public void setBodyClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		bodyIdleAnimationClock = new IdleAnimationClock(1, 0, 0);

		bodyIdleAnimationClock.setPhaseDurationX(0, 50);

		int startingClockX = randomInt;

		while(startingClockX > bodyIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= bodyIdleAnimationClock.getTotalDurationLengthX();
		}

		bodyIdleAnimationClock.setClockX(startingClockX);
	}

	@OnlyIn(Dist.CLIENT)
	public void setArmRtClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		armRtIdleAnimationClock = new IdleAnimationClock(1, 0, 0);

		armRtIdleAnimationClock.setPhaseDurationX(0, 60);

		int startingClockX = randomInt;

		while(startingClockX > armRtIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= armRtIdleAnimationClock.getTotalDurationLengthX();
		}

		armRtIdleAnimationClock.setClockX(startingClockX);
	}

	@OnlyIn(Dist.CLIENT)
	public void setTailsClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		for(int i = 0; i < tailsIdleAnimationClock.length; i++)
		{
			tailsIdleAnimationClock[i] = new IdleAnimationClock(1, 1, 0);

			tailsIdleAnimationClock[i].setPhaseDurationX(0, 45);
			tailsIdleAnimationClock[i].setPhaseDurationY(0, 80);

			float pointClusterDensityX = 0.95F;
			float pointClusterDensityY = 0.85F;

			int startingClockX = (int)(((float)(tailsIdleAnimationClock.length - i) / (float)tailsIdleAnimationClock.length) * (float)tailsIdleAnimationClock[i].getTotalDurationLengthX() * pointClusterDensityX) + randomInt;
			int startingClockY = (int)(((float)(tailsIdleAnimationClock.length - i) / (float)tailsIdleAnimationClock.length) * (float)tailsIdleAnimationClock[i].getTotalDurationLengthY() * pointClusterDensityY) + randomInt;

			while(startingClockX > tailsIdleAnimationClock[i].getTotalDurationLengthX())
			{
				startingClockX -= tailsIdleAnimationClock[i].getTotalDurationLengthX();
			}

			while(startingClockY > tailsIdleAnimationClock[i].getTotalDurationLengthY())
			{
				startingClockY -= tailsIdleAnimationClock[i].getTotalDurationLengthY();
			}

			tailsIdleAnimationClock[i].setClockX(startingClockX);
			tailsIdleAnimationClock[i].setClockY(startingClockY);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void setOrbClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		orbIdleAnimationClock = new IdleAnimationClock(1, 0, 0);

		orbIdleAnimationClock.setPhaseDurationX(0, 40);

		int startingClockX = randomInt;

		while(startingClockX > orbIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= orbIdleAnimationClock.getTotalDurationLengthX();
		}

		orbIdleAnimationClock.setClockX(startingClockX);
	}
}