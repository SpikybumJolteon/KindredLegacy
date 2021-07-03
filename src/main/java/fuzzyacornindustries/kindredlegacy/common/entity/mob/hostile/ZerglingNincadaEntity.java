package fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile;

import fuzzyacornindustries.kindredlegacy.client.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.client.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.common.core.ModEntities;
import fuzzyacornindustries.kindredlegacy.common.core.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ZerglingNincadaEntity extends HostilePokemon implements IAnimatedEntity
{
	private IdleAnimationClock neckBobbleIdleAnimationClock;
	private IdleAnimationClock wingsIdleAnimationClock;
	private IdleAnimationClock backArmsIdleAnimationClock;

	public static String mobName = "zergling_nincada";

	public int numberOfAlliesToSpawn;

	public ZerglingNincadaEntity(EntityType<? extends ZerglingNincadaEntity> type, World world)
	{
		super(type, world);

		/*
		if(KindredLegacyEntities.mobsHostileToVanillaMobs)
			this.targetTasks.addTask(2, new EntityAINearestAttackableZombieExcludingPigman(this, true));
		 */
		if(this.world.isRemote)
		{
			setClockDefaults();
		}

		animationID = actionIDNone;
		animationTick = 0;

		numberOfAlliesToSpawn = 3;
	}

	public ZerglingNincadaEntity(World world) 
	{
		this(ModEntities.ZERGLING_NINCADA.get(), world);
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
	}

	public static String getMobName()
	{
		return mobName;
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();

		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(22.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28D);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	}

	/**
	 * Get this Entity's EnumCreatureAttribute
	 */
	@Override
	public CreatureAttribute getCreatureAttribute() 
	{
		return CreatureAttribute.ARTHROPOD;
	}
	
	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSounds.ZERGLING_NINCADA_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return ModSounds.ZERGLING_NINCADA_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSounds.ZERGLING_NINCADA_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn)
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (!super.attackEntityFrom(par1DamageSource, par2))
		{
			return false;
		}
		else
		{
			if(par1DamageSource.getTrueSource() != null)
			{
				if(par1DamageSource.getTrueSource() instanceof ZombieEntity)
				{
					if (!this.world.isRemote && numberOfAlliesToSpawn >= 0)
					{
						int randomInt = this.rand.nextInt(20);

						HostilePokemon entityAlly = null;

						if (randomInt >= 0 && randomInt <= 8)
						{
							entityAlly = (HostilePokemon) ModEntities.ZERGLING_NINCADA.get().create(this.world);
						}
						else if (randomInt >= 9 && randomInt <= 11)
						{
							entityAlly = (HostilePokemon) ModEntities.RAPTOR_ZERGLING_NINCADA.get().create(this.world);
						}

						for (int l = 0; l < 1; ++l)
						{
							int i = MathHelper.floor(this.getPosX());
							int j = MathHelper.floor(this.getPosY());
							int k = MathHelper.floor(this.getPosZ());

							int i1 = i + MathHelper.nextInt(this.rand, 2, 4) * MathHelper.nextInt(this.rand, -1, 1);
							int j1 = j + MathHelper.nextInt(this.rand, 2, 4) * MathHelper.nextInt(this.rand, -1, 1);
							int k1 = k + MathHelper.nextInt(this.rand, 2, 4) * MathHelper.nextInt(this.rand, -1, 1);

							BlockPos blockpos = new BlockPos(i1, j1 - 1, k1);

							if(entityAlly != null && this.world.getBlockState(blockpos).isTopSolid(this.world, blockpos, entityAlly) && this.world.getLight(new BlockPos(i1, j1, k1)) > 1)
							{
								entityAlly.setLocationAndAngles(i1 + 0.5D, j1, k1 + 0.5D, this.rotationYaw, this.rotationPitch);

								this.world.addEntity(entityAlly);

								if(entityAlly != null)
									numberOfAlliesToSpawn--;
							}
						}
					}
				}
			}
		}

		return true;
	}

	@Override
	public boolean cancelNetherSpawns()
	{
		return false;
	}
	/*
	@Override
	public boolean cancelOtherDimensionSpawns()
	{
		if(KindredLegacyEntities.dimensionSpawnsDisablerSpaceMobs != null)
		{
			for (int dimensionNumber : KindredLegacyEntities.dimensionSpawnsDisablerSpaceMobs)
			{
				if(this.world.provider.getDimension() == dimensionNumber)
					return true;
			}
		}

		return false;
	}*/

	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	@OnlyIn(Dist.CLIENT)
	public IdleAnimationClock getIdleAnimationClockNeckBobble()
	{
		return neckBobbleIdleAnimationClock;
	}

	@OnlyIn(Dist.CLIENT)
	public IdleAnimationClock getIdleAnimationClockBackArms()
	{
		return backArmsIdleAnimationClock;
	}

	@OnlyIn(Dist.CLIENT)
	public IdleAnimationClock getIdleAnimationClockWings()
	{
		return wingsIdleAnimationClock;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void incrementPartClocks()
	{
		neckBobbleIdleAnimationClock.incrementClocks();
		backArmsIdleAnimationClock.incrementClocks();
		wingsIdleAnimationClock.incrementClocks();
	}

	@OnlyIn(Dist.CLIENT)
	public void setClockDefaults()
	{
		setNeckBobbleClockDefaults();
		setBackArmsClockDefaults();
		setWingsClockDefaults();
	}

	@OnlyIn(Dist.CLIENT)
	public void setNeckBobbleClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		neckBobbleIdleAnimationClock = new IdleAnimationClock(1, 0, 0);

		neckBobbleIdleAnimationClock.setPhaseDurationX(0, 40);

		int startingClockX = randomInt;

		while(startingClockX > neckBobbleIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= neckBobbleIdleAnimationClock.getTotalDurationLengthX();
		}

		neckBobbleIdleAnimationClock.setClockX(startingClockX);
	}

	@OnlyIn(Dist.CLIENT)
	public void setBackArmsClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		backArmsIdleAnimationClock = new IdleAnimationClock(2, 0, 0);

		backArmsIdleAnimationClock.setPhaseDurationX(0, 20);
		backArmsIdleAnimationClock.setPhaseDurationX(1, 20);

		int startingClockX = randomInt;

		while(startingClockX > backArmsIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= backArmsIdleAnimationClock.getTotalDurationLengthX();
		}

		backArmsIdleAnimationClock.setClockX(startingClockX);
	}

	@OnlyIn(Dist.CLIENT)
	public void setWingsClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		wingsIdleAnimationClock = new IdleAnimationClock(0, 1, 0);

		wingsIdleAnimationClock.setPhaseDurationY(0, 60);

		int startingClockY = randomInt;

		while(startingClockY > wingsIdleAnimationClock.getTotalDurationLengthY())
		{
			startingClockY -= wingsIdleAnimationClock.getTotalDurationLengthY();
		}

		wingsIdleAnimationClock.setClockY(startingClockY);
	}
}