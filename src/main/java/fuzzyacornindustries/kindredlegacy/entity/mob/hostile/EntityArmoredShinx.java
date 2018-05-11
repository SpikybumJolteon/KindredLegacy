package fuzzyacornindustries.kindredlegacy.entity.mob.hostile;

import java.util.Iterator;
import java.util.Set;

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
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityArmoredShinx extends HostilePokemon implements IAnimatedEntity
{
	private IdleAnimationClock neckBobbleIdleAnimationClock;
	private IdleAnimationClock tailIdleAnimationClock[] = new IdleAnimationClock[9];

	public static final int actionIDNone = 0;

	private int animationID;
	private int animationTick;

	static String mobName = "armored_shinx";

	public EntityArmoredShinx(World par1World)
	{
		super(par1World);
		this.setSize(0.4F, 0.9F);
		this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 0.8D, 1.0000001E-5F));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySubstituteDoll.class, true));

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
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(17.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(14.0D);
	}

	@Override
	public int getTotalArmorValue()
	{
		return 4;
	}

	@Override
    public void fall(float distance, float damageMultiplier) {}

	@Override
	public void onStruckByLightning(EntityLightningBolt par1EntityLightningBolt) {}

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
        return KindredLegacySoundEvents.ARMORED_SHINX_AMBIENT;
    }

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return KindredLegacySoundEvents.ARMORED_SHINX_HURT;
    }

	@Override
    protected SoundEvent getDeathSound()
    {
        return KindredLegacySoundEvents.ARMORED_SHINX_DEATH;
    }

	@Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return KindredLegacyLootTables.ARMORED_SHINX_LOOT_TABLE;
    }
    
	@Override
	public boolean getCanSpawnHere()
	{
		BlockPos blockpos = new BlockPos(MathHelper.floor(this.posX), 0, MathHelper.floor(this.posZ));
		Chunk chunk = this.world.getChunkFromBlockCoords(blockpos);

		if (this.world.getDifficulty() != EnumDifficulty.PEACEFUL)
		{
			Biome biomegenbase = this.world.getBiome(blockpos);

			Set<Type> biomeTypes = BiomeDictionary.getTypes(biomegenbase);

			Iterator<Type> biomeTypeIterator = biomeTypes.iterator();

			while(biomeTypeIterator.hasNext())
			{
				if(biomeTypeIterator.next().equals(Type.NETHER))
				{
					return false;
				}
			}

			return super.getCanSpawnHere();
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
	public IdleAnimationClock getIdleAnimationClockTail(int partNumber)
	{
		return tailIdleAnimationClock[partNumber];
	}

	@SideOnly(Side.CLIENT)
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

		neckBobbleIdleAnimationClock.setPhaseDurationX(0, 40);

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

			tailIdleAnimationClock[i].setPhaseDurationX(0, 30);
			tailIdleAnimationClock[i].setPhaseDurationY(0, 50);

			float frequencyX = 0.55F;
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