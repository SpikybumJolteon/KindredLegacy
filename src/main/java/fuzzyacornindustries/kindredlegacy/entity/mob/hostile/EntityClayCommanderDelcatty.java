package fuzzyacornindustries.kindredlegacy.entity.mob.hostile;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.ability.EntitySubstituteDoll;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyLootTables;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityClayCommanderDelcatty extends HostilePokemon implements IAnimatedEntity, IMiniBoss
{
	private IdleAnimationClock neckBobbleIdleAnimationClock;
	private IdleAnimationClock tailIdleAnimationClock[] = new IdleAnimationClock[8];

	public static final int actionIDNone = 0;

	private int animationID;
	private int animationTick;

	static String mobName = "clay_commander_delcatty";

	int numberOfMinionsToSpawn;

	public EntityClayCommanderDelcatty(World par1World)
	{
		super(par1World);

		this.setSize(0.7F, 1.4F);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
		this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 0.8D, 1.0000001E-5F));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySubstituteDoll.class, true));

		this.isImmuneToFire = true;
		this.experienceValue = 25;

		numberOfMinionsToSpawn = 20;

		if(this.world.isRemote)
		{
			setClockDefaults();
		}

		this.setPathPriority(PathNodeType.WATER, -1.0F);
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
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(25.0D);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}

	@Override
	public int getTotalArmorValue()
	{
		return 16;
	}

	@Override
	public void fall(float distance, float damageMultiplier) {}

	@Override
	protected int decreaseAirSupply(int currentAirSupply)
	{
		return currentAirSupply;
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
	}

	@Override
	public boolean attackEntityAsMob(Entity targetEntity)
	{
		boolean flag = super.attackEntityAsMob(targetEntity);

		return flag;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return KindredLegacySoundEvents.CLAY_COMMANDER_DELCATTY_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return KindredLegacySoundEvents.CLAY_COMMANDER_DELCATTY_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return KindredLegacySoundEvents.CLAY_COMMANDER_DELCATTY_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
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
				int i = MathHelper.floor(this.posX);
				int j = MathHelper.floor(this.posY);
				int k = MathHelper.floor(this.posZ);

				int i1 = i + MathHelper.getInt(this.rand, 2, 4) * MathHelper.getInt(this.rand, -1, 1);
				int j1 = j + MathHelper.getInt(this.rand, 2, 4) * MathHelper.getInt(this.rand, -1, 1);
				int k1 = k + MathHelper.getInt(this.rand, 2, 4) * MathHelper.getInt(this.rand, -1, 1);

				if (!this.world.isRemote && numberOfMinionsToSpawn != 0)
				{
					if(this.world.isSideSolid(new BlockPos(i1, j1 - 1, k1), EnumFacing.UP) && this.world.getLight(new BlockPos(i1, j1, k1)) > 1)
					{
						int randomInt = this.rand.nextInt(22);

						if (randomInt >= 0 && randomInt <= 6)
						{
							EntityClaySkitty entityMinion = new EntityClaySkitty(this.world);

							entityMinion.setLocationAndAngles(i1 + 0.5D, j1, k1 + 0.5D, this.rotationYaw, this.rotationPitch);

							this.world.spawnEntity(entityMinion);

							if(entityMinion != null)
								numberOfMinionsToSpawn--;
						}
						else if (randomInt >= 7 && randomInt <= 9)
						{
							EntityClayPurrloin entityMinion = new EntityClayPurrloin(this.world);

							entityMinion.setLocationAndAngles(i1 + 0.5D, j1, k1 + 0.5D, this.rotationYaw, this.rotationPitch);

							this.world.spawnEntity(entityMinion);

							if(entityMinion != null)
								numberOfMinionsToSpawn--;
						}
						else if (randomInt == 10)
						{
							EntityClayEspurr entityMinion = new EntityClayEspurr(this.world);

							entityMinion.setLocationAndAngles(i1 + 0.5D, j1, k1 + 0.5D, this.rotationYaw, this.rotationPitch);

							this.world.spawnEntity(entityMinion);

							if(entityMinion != null)
								numberOfMinionsToSpawn--;
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
			}

			return true;
		}
	}

	@Override
	public boolean isPotionApplicable(PotionEffect potioneffectIn)
	{
		return potioneffectIn.getPotion() == MobEffects.POISON ? false : super.isPotionApplicable(potioneffectIn);
	}

	@Nullable
	protected ResourceLocation getLootTable()
	{
		return KindredLegacyLootTables.CLAY_COMMANDER_DELCATTY_LOOT_TABLE;
	}

	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockNeckBobble()
	{
		return neckBobbleIdleAnimationClock;
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
		neckBobbleIdleAnimationClock.incrementClocks();

		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			tailIdleAnimationClock[i].incrementClocks();
		}
	}

	@SideOnly(Side.CLIENT)
	public void setClockDefaults()
	{
		setNeckBobbleClockDefaults();
		setTailClockDefaults();
	}

	@SideOnly(Side.CLIENT)
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

	@SideOnly(Side.CLIENT)
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