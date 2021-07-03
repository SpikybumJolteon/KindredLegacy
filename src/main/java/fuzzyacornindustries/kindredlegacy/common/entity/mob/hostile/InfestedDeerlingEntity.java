package fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile;

import fuzzyacornindustries.kindredlegacy.client.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.client.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.common.core.ModEntities;
import fuzzyacornindustries.kindredlegacy.common.core.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
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

public class InfestedDeerlingEntity extends HostilePokemon implements IAnimatedEntity
{
	private IdleAnimationClock neckBobbleIdleAnimationClock;
	private IdleAnimationClock tailIdleAnimationClock;

	public static final int actionIDNone = 0;

	static String mobName = "infested_deerling";

	public InfestedDeerlingEntity(EntityType<? extends InfestedDeerlingEntity> type, World world)
	{
		super(type, world);

		if(this.world.isRemote)
		{
			setClockDefaults();
		}

		animationID = actionIDNone;
		animationTick = 0;
	}

	public InfestedDeerlingEntity(World world) 
	{
		this(ModEntities.INFESTED_DEERLING.get(), world);
	}

	public static String getMobName()
	{
		return mobName;
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
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();

		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.1D);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(25.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30D);
	}

	@Override
	public void livingTick()
	{
		if (this.world.isDaytime() && !this.world.isRemote)
		{
			float f = this.getBrightness();

			BlockPos blockpos = new BlockPos(this.getPosX(), (double)Math.round(this.getPosY()), this.getPosZ());

			if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.canSeeSky(blockpos))
			{
				this.setFire(8);
			}
		}

		for (int i = 0; i < 2; ++i)
		{
			this.world.addParticle(ParticleTypes.MYCELIUM, this.getPosX() + (this.rand.nextDouble() - 0.5D) * (double)this.getWidth(), this.getPosY() + this.rand.nextDouble() * (double)this.getHeight() - 0.25D, this.getPosZ() + (this.rand.nextDouble() - 0.5D) * (double)this.getWidth(), (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
		}

		super.livingTick();
	}

	@Override
	public boolean attackEntityAsMob(Entity targetEntity)
	{
		if (super.attackEntityAsMob(targetEntity))
		{
			if (targetEntity instanceof LivingEntity)
			{
				byte durationSeconds = 5;

				if (this.world.getDifficulty() == Difficulty.NORMAL)
				{
					durationSeconds = 10;
				}
				else if (this.world.getDifficulty() == Difficulty.HARD)
				{
					durationSeconds = 18;
				}

				if (this.rand.nextInt(2) == 0)
				{
					((LivingEntity)targetEntity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, durationSeconds * 20, 0));
				}

				if (this.rand.nextInt(4) == 0)
				{
					((LivingEntity)targetEntity).addPotionEffect(new EffectInstance(Effects.POISON, durationSeconds * 20, 0));
				}

				if (this.rand.nextInt(10) == 0)
				{
					((LivingEntity)targetEntity).addPotionEffect(new EffectInstance(Effects.BLINDNESS, durationSeconds * 20, 0));
				}
			}

			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSounds.INFESTED_DEERLING_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return ModSounds.INFESTED_DEERLING_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSounds.INFESTED_DEERLING_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) 
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
	}

	@Override
	public CreatureAttribute getCreatureAttribute() 
	{
		return CreatureAttribute.UNDEAD;
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
	public IdleAnimationClock getIdleAnimationClockTail()
	{
		return tailIdleAnimationClock;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void incrementPartClocks()
	{
		neckBobbleIdleAnimationClock.incrementClocks();
		tailIdleAnimationClock.incrementClocks();
	}

	@OnlyIn(Dist.CLIENT)
	public void setClockDefaults()
	{
		setNeckBobbleClockDefaults();
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
	public void setTailClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		tailIdleAnimationClock = new IdleAnimationClock(1, 1, 0);

		tailIdleAnimationClock.setPhaseDurationX(0, 80);
		tailIdleAnimationClock.setPhaseDurationY(0, 60);

		int startingClockX = randomInt;

		while(startingClockX > tailIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= tailIdleAnimationClock.getTotalDurationLengthX();
		}

		int startingClockY = randomInt;

		while(startingClockY > tailIdleAnimationClock.getTotalDurationLengthY())
		{
			startingClockY -= tailIdleAnimationClock.getTotalDurationLengthY();
		}

		tailIdleAnimationClock.setClockX(startingClockX);
		tailIdleAnimationClock.setClockY(startingClockY);
	}
}