package fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile;

import fuzzyacornindustries.kindredlegacy.client.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.client.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.common.core.ModEntities;
import fuzzyacornindustries.kindredlegacy.common.core.ModSounds;
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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ClayCommanderDelcattyEntity extends HostilePokemon implements IAnimatedEntity, IMiniBoss
{
	private IdleAnimationClock neckBobbleIdleAnimationClock;
	private IdleAnimationClock tailIdleAnimationClock[] = new IdleAnimationClock[8];

	static String mobName = "clay_commander_delcatty";

	int numberOfMinionsToSpawn;

	public ClayCommanderDelcattyEntity(EntityType<? extends ClayCommanderDelcattyEntity> type, World world)
	{
		super(type, world);
		
		this.experienceValue = 25;

		numberOfMinionsToSpawn = 20;

		if(this.world.isRemote)
		{
			setClockDefaults();
		}

		animationID = actionIDNone;
		animationTick = 0;

		this.setPathPriority(PathNodeType.WATER, -1.0F);
	}

	public ClayCommanderDelcattyEntity(World world) 
	{
		this(ModEntities.CLAY_COMMANDER_DELCATTY.get(), world);
	}

	@Override
	protected void registerGoals() 
	{
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(4, new LookRandomlyGoal(this));

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

		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(25.0D);
		this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}

	@Override
	public int getTotalArmorValue()
	{
		return 16;
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
		return ModSounds.CLAY_COMMANDER_DELCATTY_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return ModSounds.CLAY_COMMANDER_DELCATTY_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSounds.CLAY_COMMANDER_DELCATTY_DEATH.get();
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
			for (int l = 0; l < 3; ++l)
			{
				int i = MathHelper.floor(this.getPosX());
				int j = MathHelper.floor(this.getPosY());
				int k = MathHelper.floor(this.getPosZ());

				int i1 = i + MathHelper.nextInt(this.rand, 2, 4) * MathHelper.nextInt(this.rand, -1, 1);
				int j1 = j + MathHelper.nextInt(this.rand, 2, 4) * MathHelper.nextInt(this.rand, -1, 1);
				int k1 = k + MathHelper.nextInt(this.rand, 2, 4) * MathHelper.nextInt(this.rand, -1, 1);

				if (!this.world.isRemote && numberOfMinionsToSpawn != 0)
				{
						int randomInt = this.rand.nextInt(22);

						if (randomInt >= 0 && randomInt <= 6)
						{
							ClaySkittyEntity entityMinion = new ClaySkittyEntity(ModEntities.CLAY_SKITTY.get(), this.world);

							spawnMinion(entityMinion, i1, j1, k1);
						}
						else if (randomInt >= 7 && randomInt <= 9)
						{
							ClayPurrloinEntity entityMinion = new ClayPurrloinEntity(ModEntities.CLAY_PURRLOIN.get(), this.world);

							spawnMinion(entityMinion, i1, j1, k1);
						}
						else if (randomInt == 10)
						{
							ClayEspurrEntity entityMinion = new ClayEspurrEntity(ModEntities.CLAY_ESPURR.get(), this.world);

							spawnMinion(entityMinion, i1, j1, k1);
						}/*
						else if (randomInt == 11)
						{
							EntityClayMeowth entityMinion = new EntityClayMeowth(this.world);

							entityMinion.setLocationAndAngles(i1 + 0.5D, j1, k1 + 0.5D, this.rotationYaw, this.rotationPitch);

							this.world.spawnEntity(entityMinion);

							numberOfMinionsToSpawn--;
						}*/
				}
			}

			return true;
		}
	}
	
	private void spawnMinion(HostilePokemon entityMinion, int xCoord, int yCoord, int zCoord)
	{
		if(this.world.isTopSolid(new BlockPos(xCoord, yCoord - 1, zCoord), entityMinion) && this.world.getLight(new BlockPos(xCoord, yCoord, zCoord)) > 1)
		{

			entityMinion.setLocationAndAngles(xCoord + 0.5D, yCoord, zCoord + 0.5D, this.rotationYaw, this.rotationPitch);

			this.world.addEntity(entityMinion);

			if(this.getAttackTarget() != null)
				entityMinion.setAttackTarget(this.getAttackTarget());

			if(entityMinion != null)
				numberOfMinionsToSpawn--;
		}
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

	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	@OnlyIn(Dist.CLIENT)
	public IdleAnimationClock getIdleAnimationClockNeckBobble()
	{
		return neckBobbleIdleAnimationClock;
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

		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			tailIdleAnimationClock[i].incrementClocks();
		}
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

		neckBobbleIdleAnimationClock.setPhaseDurationX(0, 80);

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

		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			tailIdleAnimationClock[i] = new IdleAnimationClock(1, 1, 0);

			tailIdleAnimationClock[i].setPhaseDurationX(0, 60);
			tailIdleAnimationClock[i].setPhaseDurationY(0, 80);

			float pointClusterDensityX = 0.55F;
			float pointClusterDensityY = 0.45F;

			int startingClockX = (int)(((float)(tailIdleAnimationClock.length - i) / (float)tailIdleAnimationClock.length) * (float)tailIdleAnimationClock[i].getTotalDurationLengthX() * pointClusterDensityX) + randomInt;
			int startingClockY = (int)(((float)(tailIdleAnimationClock.length - i) / (float)tailIdleAnimationClock.length) * (float)tailIdleAnimationClock[i].getTotalDurationLengthY() * pointClusterDensityY) + randomInt;

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