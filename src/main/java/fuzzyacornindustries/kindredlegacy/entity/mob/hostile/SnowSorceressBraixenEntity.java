package fuzzyacornindustries.kindredlegacy.entity.mob.hostile;

import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.GeneralRangedAttackGoal;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SnowSorceressBraixenEntity extends HostilePokemon implements IRangedAttackMob, IAnimatedEntity
{
	private IdleAnimationClock earsIdleAnimationClock[] = new IdleAnimationClock[2];
	private IdleAnimationClock bodySwayIdleAnimationClock[] = new IdleAnimationClock[5];
	private IdleAnimationClock armLftIdleAnimationClock[] = new IdleAnimationClock[2];
	private IdleAnimationClock tailIdleAnimationClock[] = new IdleAnimationClock[4];

	public static final int actionIDNone = 0;
	public static final int actionIDSpell = 1;

	static String mobName = "snow_sorceress_braixen";

	public SnowSorceressBraixenEntity(EntityType<? extends SnowSorceressBraixenEntity> type, World world)
	{
		super(type, world);

		this.experienceValue = 10;

		if(this.world.isRemote)
		{
			setClockDefaults();
		}

		animationID = actionIDNone;
		animationTick = 0;
	}

	public static String getMobName()
	{
		return mobName;
	}

	@Override
	protected void registerGoals() 
	{
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(3, new GeneralRangedAttackGoal(this, 1.0D, 20, 15.0F));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(5, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();

		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(30.0D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(35.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28D);
	}

	@Override
    protected SoundEvent getAmbientSound()
    {
        return KindredLegacySoundEvents.SNOW_SORCERESS_BRAIXEN_AMBIENT;
    }

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return KindredLegacySoundEvents.SNOW_SORCERESS_BRAIXEN_HURT;
    }

	@Override
    protected SoundEvent getDeathSound()
    {
        return KindredLegacySoundEvents.SNOW_SORCERESS_BRAIXEN_DEATH;
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
			for (int i = 0; i < 2; ++i)
			{
				this.world.addParticle(ParticleTypes.PORTAL, this.getPosX() + (this.rand.nextDouble() - 0.5D) * (double)this.getWidth(), this.getPosY() + this.rand.nextDouble() * (double)this.getHeight() - 0.25D, this.getPosZ() + (this.rand.nextDouble() - 0.5D) * (double)this.getWidth(), (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
			}
		}

		super.livingTick();
	}

	/**
	 * Attack the specified entity using a ranged attack.
	 */
	@Override
	public void attackEntityWithRangedAttack(LivingEntity targetEntity, float par2)
	{
		byte durationSeconds = 4;

		if (this.world.getDifficulty() == Difficulty.NORMAL)
		{
			durationSeconds = 10;
		}
		else if (this.world.getDifficulty() == Difficulty.HARD)
		{
			durationSeconds = 20;
		}

		if (this.rand.nextInt(4) == 0)
		{
			((LivingEntity)targetEntity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, durationSeconds * 20, 0));
		}

		if (this.rand.nextInt(8) == 0)
		{
			((LivingEntity)targetEntity).addPotionEffect(new EffectInstance(Effects.BLINDNESS, durationSeconds * 20, 0));
		}

		if (this.rand.nextInt(8) == 0)
		{
			((LivingEntity)targetEntity).setFire(statusEffectDurationModifier(3));

			playIgniteSound(targetEntity);
		}

		if (this.rand.nextInt(12) == 0)
		{
			((LivingEntity)targetEntity).addPotionEffect(new EffectInstance(Effects.POISON, durationSeconds * 20, 0));

			playBioSound(targetEntity);
		}

		((LivingEntity)targetEntity).addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, durationSeconds * 20, 0));
	}

	public int statusEffectDurationModifier(int durationModifier)
	{
		if(this.world.getDifficulty() == Difficulty.NORMAL)
		{
			return 2 + durationModifier;
		}
		if(this.world.getDifficulty() == Difficulty.HARD)
		{
			return 4 + durationModifier;
		}
		else
		{
			return 1 + durationModifier / 2;
		}
	}

	@Override
	public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) 
	{
		if (this.world.getDifficulty() != Difficulty.PEACEFUL)
		{
			Biome biome = this.world.getBiome(getPosition());

			if (biome == Biomes.MOUNTAINS || biome == Biomes.MOUNTAIN_EDGE)
			{
				if (MathHelper.floor(this.getPosY()) <= 92)
				{
					return false;
				}
			}

			return super.canSpawn(worldIn, spawnReasonIn);
		}

		return false;
	}

	@Override
	public boolean cancelNetherSpawns()
	{
		return false;
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
	public IdleAnimationClock getIdleAnimationClockBodySway(int partNumber)
	{
		return bodySwayIdleAnimationClock[partNumber];
	}

	@OnlyIn(Dist.CLIENT)
	public IdleAnimationClock getIdleAnimationClockArmLft(int partNumber)
	{
		return armLftIdleAnimationClock[partNumber];
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

		for(int i = 0; i < bodySwayIdleAnimationClock.length; i++)
		{
			bodySwayIdleAnimationClock[i].incrementClocks();
		}

		for(int i = 0; i < armLftIdleAnimationClock.length; i++)
		{
			armLftIdleAnimationClock[i].incrementClocks();
		}

		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			tailIdleAnimationClock[i].incrementClocks();
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void setClockDefaults()
	{
		setEarClockDefaults();
		setBodySwayClockDefaults();
		setArmLftClockDefaults();
		setTailClockDefaults();
	}

	@OnlyIn(Dist.CLIENT)
	private void setBodySwayClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		for(int i = 0; i < bodySwayIdleAnimationClock.length; i++)
		{
			bodySwayIdleAnimationClock[i] = new IdleAnimationClock(1, 0, 1);

			int duration = 75;

			bodySwayIdleAnimationClock[i].setPhaseDurationX(0, duration);
			bodySwayIdleAnimationClock[i].setPhaseDurationZ(0, duration);

			float frequencyX = 0.95F;
			float frequencyZ = 0.35F;

			int startingClockX = (int)(((float)(bodySwayIdleAnimationClock.length - i) / (float)bodySwayIdleAnimationClock.length) * 
					(float)bodySwayIdleAnimationClock[i].getTotalDurationLengthX() * frequencyX) + randomInt;
			int startingClockZ = (int)(((float)(bodySwayIdleAnimationClock.length - i) / (float)bodySwayIdleAnimationClock.length) * 
					(float)bodySwayIdleAnimationClock[i].getTotalDurationLengthZ() * frequencyZ) + randomInt;

			while(startingClockX > bodySwayIdleAnimationClock[i].getTotalDurationLengthX())
			{
				startingClockX -= bodySwayIdleAnimationClock[i].getTotalDurationLengthX();
			}

			while(startingClockZ > bodySwayIdleAnimationClock[i].getTotalDurationLengthZ())
			{
				startingClockZ -= bodySwayIdleAnimationClock[i].getTotalDurationLengthZ();
			}

			bodySwayIdleAnimationClock[i].setClockX(startingClockX);
			bodySwayIdleAnimationClock[i].setClockZ(startingClockZ);
		}
	}

	@OnlyIn(Dist.CLIENT)
	private void setArmLftClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		for(int i = 0; i < armLftIdleAnimationClock.length; i++)
		{
			armLftIdleAnimationClock[i] = new IdleAnimationClock(1, 1, 0);

			armLftIdleAnimationClock[i].setPhaseDurationX(0, 50);
			armLftIdleAnimationClock[i].setPhaseDurationY(0, 130);

			float frequencyX = 0.2F;
			float frequencyY = 0.4F;

			int startingClockX = (int)(((float)(armLftIdleAnimationClock.length - i) / (float)armLftIdleAnimationClock.length) * 
					(float)armLftIdleAnimationClock[i].getTotalDurationLengthX() * frequencyX) + randomInt;
			int startingClockY = (int)(((float)(armLftIdleAnimationClock.length - i) / (float)armLftIdleAnimationClock.length) * 
					(float)armLftIdleAnimationClock[i].getTotalDurationLengthY() * frequencyY) + randomInt;

			while(startingClockX > armLftIdleAnimationClock[i].getTotalDurationLengthX())
			{
				startingClockX -= armLftIdleAnimationClock[i].getTotalDurationLengthX();
			}

			while(startingClockY > armLftIdleAnimationClock[i].getTotalDurationLengthY())
			{
				startingClockY -= armLftIdleAnimationClock[i].getTotalDurationLengthY();
			}

			armLftIdleAnimationClock[i].setClockX(startingClockX);
			armLftIdleAnimationClock[i].setClockY(startingClockY);
		}

	}

	@OnlyIn(Dist.CLIENT)
	private void setEarClockDefaults() 
	{
		int randomInt = this.rand.nextInt(100);

		for(int i = 0; i < earsIdleAnimationClock.length; i++)
		{
			earsIdleAnimationClock[i] = new IdleAnimationClock(0, 0, 1);

			earsIdleAnimationClock[i].setPhaseDurationZ(0, 75);

			float frequencyZ = 0.25F;

			int startingClockZ = (int)(((float)(earsIdleAnimationClock.length - i) / (float)earsIdleAnimationClock.length) * 
					(float)earsIdleAnimationClock[i].getTotalDurationLengthZ() * frequencyZ) + randomInt;

			while(startingClockZ > earsIdleAnimationClock[i].getTotalDurationLengthZ())
			{
				startingClockZ -= earsIdleAnimationClock[i].getTotalDurationLengthZ();
			}

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

			tailIdleAnimationClock[i].setPhaseDurationX(0, 60);
			tailIdleAnimationClock[i].setPhaseDurationY(0, 50);

			float frequencyX = 0.65F;
			float frequencyY = 0.65F;

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