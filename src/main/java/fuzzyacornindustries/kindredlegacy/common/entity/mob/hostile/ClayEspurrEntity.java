package fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile;

import fuzzyacornindustries.kindredlegacy.client.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.client.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.common.core.ModEntities;
import fuzzyacornindustries.kindredlegacy.common.core.ModSounds;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.ai.GeneralRangedAttackGoal;
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
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ClayEspurrEntity extends HostilePokemon implements IRangedAttackMob, IAnimatedEntity
{
	private IdleAnimationClock armsIdleAnimationClock;
	private IdleAnimationClock earsIdleAnimationClock;
	private IdleAnimationClock tailIdleAnimationClock[] = new IdleAnimationClock[2];

	public static final int actionIDNone = 0;

	static String mobName = "clay_espurr";

	public ClayEspurrEntity(EntityType<? extends ClayEspurrEntity> type, World world)
	{
		super(type, world);

		if(this.world.isRemote)
		{
			setClockDefaults();
		}

		animationID = actionIDNone;
		animationTick = 0;

		this.setPathPriority(PathNodeType.WATER, -1.0F);
	}

	public ClayEspurrEntity(World world) 
	{
		this(ModEntities.CLAY_ESPURR.get(), world);
	}

	@Override
	protected void registerGoals() 
	{
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(4, new GeneralRangedAttackGoal(this, 1.0D, 35, 60, 10.0F));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}

	public static String getMobName()
	{
		return mobName;
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();

		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30D);
	}

	@Override
	public int getTotalArmorValue()
	{
		return 2;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSounds.CLAY_ESPURR_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return ModSounds.CLAY_ESPURR_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSounds.CLAY_ESPURR_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) 
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
	}

	@Override
	public void livingTick() 
	{
		if (this.world.isRemote) 
		{
			for(int i = 0; i < 2; ++i) 
			{
				this.world.addParticle(ParticleTypes.PORTAL, this.getPosXRandom(0.5D), this.getPosYRandom() - 0.25D, this.getPosZRandom(0.5D), (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
			}
		}

		super.livingTick();
	}

	@Override
	public boolean isPotionApplicable(EffectInstance potioneffectIn) 
	{
		if (potioneffectIn.getPotion() == Effects.POISON) 
		{
			net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent event = new net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent(this, potioneffectIn);
			net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
			return event.getResult() == net.minecraftforge.eventbus.api.Event.Result.ALLOW;
		}

		return super.isPotionApplicable(potioneffectIn);
	}

	@Override
	public void attackEntityWithRangedAttack(LivingEntity targetEntity, float par2)
	{
		byte durationSeconds = 5;

		if (this.world.getDifficulty() == Difficulty.NORMAL)
		{
			durationSeconds = 12;
		}
		else if (this.world.getDifficulty() == Difficulty.HARD)
		{
			durationSeconds = 20;
		}

		if (this.rand.nextInt(6) == 0)
		{
			targetEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, durationSeconds * 20, 0));
			}

		if (this.rand.nextInt(12) == 0)
		{
			targetEntity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, durationSeconds * 20, 0));
			}

		targetEntity.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, durationSeconds * 20, 0));
	}

	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	@OnlyIn(Dist.CLIENT)
	public IdleAnimationClock getIdleAnimationClockEars()
	{
		return earsIdleAnimationClock;
	}

	@OnlyIn(Dist.CLIENT)
	public IdleAnimationClock getIdleAnimationClockArms()
	{
		return armsIdleAnimationClock;
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
		armsIdleAnimationClock.incrementClocks();
		earsIdleAnimationClock.incrementClocks();

		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			tailIdleAnimationClock[i].incrementClocks();
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void setClockDefaults()
	{
		setArmsClockDefaults();
		setEarsClockDefaults();
		setTailClockDefaults();
	}

	@OnlyIn(Dist.CLIENT)
	public void setArmsClockDefaults()
	{
		int startingClockZ = this.rand.nextInt(100);

		armsIdleAnimationClock = new IdleAnimationClock(0, 0, 1);

		armsIdleAnimationClock.setPhaseDurationZ(0, 30);

		while(startingClockZ > armsIdleAnimationClock.getTotalDurationLengthZ())
		{
			startingClockZ -= armsIdleAnimationClock.getTotalDurationLengthZ();
		}

		armsIdleAnimationClock.setClockZ(startingClockZ);
	}

	@OnlyIn(Dist.CLIENT)
	public void setEarsClockDefaults()
	{
		int startingClockZ = this.rand.nextInt(100);

		earsIdleAnimationClock = new IdleAnimationClock(0, 0, 1);

		earsIdleAnimationClock.setPhaseDurationZ(0, 50);

		while(startingClockZ > earsIdleAnimationClock.getTotalDurationLengthZ())
		{
			startingClockZ -= earsIdleAnimationClock.getTotalDurationLengthZ();
		}

		earsIdleAnimationClock.setClockZ(startingClockZ);
	}

	@OnlyIn(Dist.CLIENT)
	public void setTailClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			tailIdleAnimationClock[i] = new IdleAnimationClock(1, 0, 1);

			tailIdleAnimationClock[i].setPhaseDurationX(0, 30);
			tailIdleAnimationClock[i].setPhaseDurationZ(0, 50);

			float pointClusterDensityX = 0.55F;
			float pointClusterDensityZ = 0.50F;

			int startingClockX = (int)(((float)(tailIdleAnimationClock.length - i) / (float)tailIdleAnimationClock.length) * 
					(float)tailIdleAnimationClock[i].getTotalDurationLengthX() * pointClusterDensityX) + randomInt;
			int startingClockZ = (int)(((float)(tailIdleAnimationClock.length - i) / (float)tailIdleAnimationClock.length) * 
					(float)tailIdleAnimationClock[i].getTotalDurationLengthZ() * pointClusterDensityZ) + randomInt;

			while(startingClockX > tailIdleAnimationClock[i].getTotalDurationLengthX())
			{
				startingClockX -= tailIdleAnimationClock[i].getTotalDurationLengthX();
			}

			while(startingClockZ > tailIdleAnimationClock[i].getTotalDurationLengthZ())
			{
				startingClockZ -= tailIdleAnimationClock[i].getTotalDurationLengthZ();
			}

			tailIdleAnimationClock[i].setClockX(startingClockX);
			tailIdleAnimationClock[i].setClockZ(startingClockZ);
		}
	}
}