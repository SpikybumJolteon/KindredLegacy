package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.client.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.common.core.ModEntities;
import fuzzyacornindustries.kindredlegacy.common.core.ModSounds;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.HostilePokemon;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.IMiniBoss;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class VoorstMightyenaEntity extends HostilePokemon implements IAnimatedEntity, IMiniBoss
{
	private IdleAnimationClock neckBobbleIdleAnimationClock;
	private IdleAnimationClock jawMainIdleAnimationClock;
	private IdleAnimationClock jawLowerIdleAnimationClock;
	private IdleAnimationClock tailIdleAnimationClock[] = new IdleAnimationClock[6];

	static String mobName = "voorst_mightyena";

	public VoorstMightyenaEntity(EntityType<? extends VoorstMightyenaEntity> type, World world)
	{
		super(type, world);

		this.experienceValue = 30;

		if(this.world.isRemote)
		{
			setClockDefaults();
		}
	}

	public VoorstMightyenaEntity(World world) 
	{
		this(ModEntities.VOORST_MIGHTYENA.get(), world);
	}

	@Override
	protected void registerGoals() 
	{
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, ZombieEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, SkeletonEntity.class, true));
	}

	public static String getMobName()
	{
		return mobName;
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();

		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(25.0D);
		this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30D);
	}

	@Override
	protected int decreaseAirSupply(int currentAirSupply)
	{
		return currentAirSupply;
	}

	@Override
	public boolean onLivingFall(float distance, float damageMultiplier)
	{
		return false;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSounds.VOORST_MIGHTYENA_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return ModSounds.VOORST_MIGHTYENA_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSounds.VOORST_MIGHTYENA_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) 
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
	}

	@Override
	public boolean isPotionApplicable(EffectInstance potioneffectIn) 
	{
		if (potioneffectIn.getPotion() == Effects.POISON || potioneffectIn.getPotion() == Effects.SLOWNESS) 
		{
			net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent event = new net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent(this, potioneffectIn);
			net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
			return event.getResult() == net.minecraftforge.eventbus.api.Event.Result.ALLOW;
		}

		return super.isPotionApplicable(potioneffectIn);
	}

	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	@OnlyIn(Dist.CLIENT)
	public IdleAnimationClock getIdleAnimationClockNeckBobble()
	{
		return neckBobbleIdleAnimationClock;
	}

	@OnlyIn(Dist.CLIENT)
	public IdleAnimationClock getIdleAnimationClockJawMain()
	{
		return jawMainIdleAnimationClock;
	}

	@OnlyIn(Dist.CLIENT)
	public void setIdleAnimationClockJawMain(IdleAnimationClock idleAnimationClock)
	{
		jawMainIdleAnimationClock = idleAnimationClock;
	}

	@OnlyIn(Dist.CLIENT)
	public IdleAnimationClock getIdleAnimationClockJawLower()
	{
		return jawLowerIdleAnimationClock;
	}

	@OnlyIn(Dist.CLIENT)
	public IdleAnimationClock getIdleAnimationClockTail(int partNumber)
	{
		return tailIdleAnimationClock[partNumber];
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void incrementPartClocks()
	{
		neckBobbleIdleAnimationClock.incrementClocks();
		jawMainIdleAnimationClock.incrementClocks();
		jawLowerIdleAnimationClock.incrementClocks();

		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			tailIdleAnimationClock[i].incrementClocks();
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void setClockDefaults()
	{
		setNeckBobbleClockDefaults();
		setJawMainClockDefaults();
		setJawLowerClockDefaults();
		setTailClockDefaults();
	}

	@OnlyIn(Dist.CLIENT)
	public void setNeckBobbleClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		neckBobbleIdleAnimationClock = new IdleAnimationClock(1, 0, 0);

		neckBobbleIdleAnimationClock.setPhaseDurationX(0, 75);

		int startingClockX = randomInt;

		while(startingClockX > neckBobbleIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= neckBobbleIdleAnimationClock.getTotalDurationLengthX();
		}

		neckBobbleIdleAnimationClock.setClockX(startingClockX);
	}

	@OnlyIn(Dist.CLIENT)
	public void setJawMainClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		jawMainIdleAnimationClock = new IdleAnimationClock(3, 0, 0);

		jawMainIdleAnimationClock.setPhaseDurationX(0, 30);
		jawMainIdleAnimationClock.setPhaseDurationX(1, 15);
		jawMainIdleAnimationClock.setPhaseDurationX(2, 30);

		int startingClockX = randomInt;

		while(startingClockX > jawMainIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= jawMainIdleAnimationClock.getTotalDurationLengthX();
		}

		jawMainIdleAnimationClock.setClockX(startingClockX);
	}

	@OnlyIn(Dist.CLIENT)
	public void setJawLowerClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		jawLowerIdleAnimationClock = new IdleAnimationClock(2, 0, 0);

		jawLowerIdleAnimationClock.setPhaseDurationX(0, 20);
		jawLowerIdleAnimationClock.setPhaseDurationX(1, 20);

		int startingClockX = randomInt;

		while(startingClockX > jawLowerIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= jawLowerIdleAnimationClock.getTotalDurationLengthX();
		}

		jawLowerIdleAnimationClock.setClockX(startingClockX);
	}

	@OnlyIn(Dist.CLIENT)
	public void setTailClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			tailIdleAnimationClock[i] = new IdleAnimationClock(1, 1, 0);

			tailIdleAnimationClock[i].setPhaseDurationX(0, 75);

			tailIdleAnimationClock[i].setPhaseDurationY(0, 50);

			float frequencyX = 0.65F;
			float frequencyY = 0.55F;

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