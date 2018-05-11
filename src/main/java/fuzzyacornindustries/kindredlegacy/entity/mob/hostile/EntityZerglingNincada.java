package fuzzyacornindustries.kindredlegacy.entity.mob.hostile;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.KindredLegacyEntities;
import fuzzyacornindustries.kindredlegacy.entity.ability.EntitySubstituteDoll;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.EntityAINearestAttackableZombieExcludingPigman;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyLootTables;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional.Method;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityZerglingNincada extends HostilePokemon implements IAnimatedEntity
{
	private IdleAnimationClock neckBobbleIdleAnimationClock;
	private IdleAnimationClock wingsIdleAnimationClock;
	private IdleAnimationClock backArmsIdleAnimationClock;

	static String mobName = "zergling_nincada";

	public int numberOfAlliesToSpawn;

	public EntityZerglingNincada(World par1World)
	{
		super(par1World);

		this.setSize(0.4F, 0.9F);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, true));
		this.tasks.addTask(5, new EntityAIWander(this, 0.6D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));

		if(KindredLegacyEntities.mobsHostileToVanillaMobs)
			this.targetTasks.addTask(2, new EntityAINearestAttackableZombieExcludingPigman(this, true));

		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySubstituteDoll.class, true));

		if(this.world.isRemote)
		{
			setClockDefaults();
		}

		animationID = actionIDNone;
		animationTick = 0;

		numberOfAlliesToSpawn = 3;
	}

	public static String getMobName()
	{
		return mobName;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(22.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	}

	/**
	 * Get this Entity's EnumCreatureAttribute
	 */
	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return KindredLegacySoundEvents.ZERGLING_NINCADA_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return KindredLegacySoundEvents.ZERGLING_NINCADA_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return KindredLegacySoundEvents.ZERGLING_NINCADA_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
	}

	@Nullable
	protected ResourceLocation getLootTable()
	{
		return KindredLegacyLootTables.ZERGLING_NINCADA_LOOT_TABLE;
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
				if(par1DamageSource.getTrueSource() instanceof EntityZombie)
				{
					if (!this.world.isRemote && numberOfAlliesToSpawn >= 0)
					{
						for (int l = 0; l < 1; ++l)
						{
							int i = MathHelper.floor(this.posX);
							int j = MathHelper.floor(this.posY);
							int k = MathHelper.floor(this.posZ);

							int i1 = i + MathHelper.getInt(this.rand, 2, 4) * MathHelper.getInt(this.rand, -1, 1);
							int j1 = j + MathHelper.getInt(this.rand, 2, 4) * MathHelper.getInt(this.rand, -1, 1);
							int k1 = k + MathHelper.getInt(this.rand, 2, 4) * MathHelper.getInt(this.rand, -1, 1);

							if(this.world.isSideSolid(new BlockPos(i1, j1 - 1, k1), EnumFacing.UP) && this.world.getLight(new BlockPos(i1, j1, k1)) > 1)
							{
								int randomInt = this.rand.nextInt(20);

								if (randomInt >= 0 && randomInt <= 8)
								{
									EntityZerglingNincada entityAlly = new EntityZerglingNincada(this.world);

									entityAlly.setLocationAndAngles(i1 + 0.5D, j1, k1 + 0.5D, this.rotationYaw, this.rotationPitch);

									this.world.spawnEntity(entityAlly);

									if(entityAlly != null)
										numberOfAlliesToSpawn--;
								}
								else if (randomInt >= 9 && randomInt <= 11)
								{
									EntityRaptorZerglingNincada entityAlly = new EntityRaptorZerglingNincada(this.world);

									entityAlly.setLocationAndAngles(i1 + 0.5D, j1, k1 + 0.5D, this.rotationYaw, this.rotationPitch);

									this.world.spawnEntity(entityAlly);

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
	}

	@Override
	public boolean cancelNetherSpawns()
	{
		return false;
	}

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
	public IdleAnimationClock getIdleAnimationClockBackArms()
	{
		return backArmsIdleAnimationClock;
	}

	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockWings()
	{
		return wingsIdleAnimationClock;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void incrementPartClocks()
	{
		neckBobbleIdleAnimationClock.incrementClocks();
		backArmsIdleAnimationClock.incrementClocks();
		wingsIdleAnimationClock.incrementClocks();
	}

	@SideOnly(Side.CLIENT)
	public void setClockDefaults()
	{
		setNeckBobbleClockDefaults();
		setBackArmsClockDefaults();
		setWingsClockDefaults();
	}

	@SideOnly(Side.CLIENT)
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

	@SideOnly(Side.CLIENT)
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

	@SideOnly(Side.CLIENT)
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