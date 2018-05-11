package fuzzyacornindustries.kindredlegacy.entity.mob.hostile;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.ability.EntitySubstituteDoll;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyLootTables;
import fuzzyacornindustries.kindredlegacy.reference.LibraryBiomeID;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySnowSorceressBraixen extends HostilePokemon implements IRangedAttackMob, IAnimatedEntity
{
	private IdleAnimationClock earsIdleAnimationClock[] = new IdleAnimationClock[2];
	private IdleAnimationClock bodySwayIdleAnimationClock[] = new IdleAnimationClock[5];
	private IdleAnimationClock armLftIdleAnimationClock[] = new IdleAnimationClock[2];
	private IdleAnimationClock tailIdleAnimationClock[] = new IdleAnimationClock[4];

	public static final int actionIDNone = 0;
	public static final int actionIDSpell = 1;

	static String mobName = "snow_sorceress_braixen";

	public EntitySnowSorceressBraixen(World par1World)
	{
		super(par1World);
		this.setSize(0.5F, 0.95F);
		this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIAttackRanged(this, 1.0D, 35, 60, 15.0F));
		this.tasks.addTask(4, new EntityAIWander(this, 0.75D));
		this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySubstituteDoll.class, true));

		this.isImmuneToFire = true;
		this.experienceValue = 10;

		if(this.world.isRemote)
		{
			setClockDefaults();
		}
	}

	public static String getMobName()
	{
		return mobName;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(30.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(35.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28D);
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
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
    }

	@Override
	public void onLivingUpdate()
	{
		if (this.world.isRemote)
		{
			for (int i = 0; i < 2; ++i)
			{
				this.world.spawnParticle(EnumParticleTypes.PORTAL, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D, new int[0]);
			}
		}

		super.onLivingUpdate();
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
	}

	/**
	 * Attack the specified entity using a ranged attack.
	 */
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase targetEntity, float par2)
	{
		byte durationSeconds = 4;

		if (this.world.getDifficulty() == EnumDifficulty.NORMAL)
		{
			durationSeconds = 10;
		}
		else if (this.world.getDifficulty() == EnumDifficulty.HARD)
		{
			durationSeconds = 20;
		}

		if (this.rand.nextInt(4) == 0)
		{
            ((EntityLivingBase)targetEntity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, durationSeconds * 20, 0));
		}

		if (this.rand.nextInt(8) == 0)
		{
            ((EntityLivingBase)targetEntity).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, durationSeconds * 20, 0));
		}

		if (this.rand.nextInt(8) == 0)
		{
			((EntityLivingBase)targetEntity).setFire(statusEffectDurationModifier(3));

			playIgniteSound(targetEntity);
		}

		if (this.rand.nextInt(12) == 0)
		{
            ((EntityLivingBase)targetEntity).addPotionEffect(new PotionEffect(MobEffects.POISON, durationSeconds * 20, 0));

			playBioSound(targetEntity);
		}

        ((EntityLivingBase)targetEntity).addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, durationSeconds * 20, 0));
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {}

	public int statusEffectDurationModifier(int durationModifier)
	{
		if(this.world.getDifficulty() == EnumDifficulty.NORMAL)
		{
			return 2 + durationModifier;
		}
		if(this.world.getDifficulty() == EnumDifficulty.HARD)
		{
			return 4 + durationModifier;
		}
		else
		{
			return 1 + durationModifier / 2;
		}
	}

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return KindredLegacyLootTables.SNOW_SORCERESS_BRAIXEN_LOOT_TABLE;
    }

	@Override
	public boolean getCanSpawnHere()
	{
		BlockPos blockpos = new BlockPos(MathHelper.floor(this.posX), 0, MathHelper.floor(this.posZ));
		Chunk chunk = this.world.getChunkFromBlockCoords(blockpos);

		if (this.world.getDifficulty() != EnumDifficulty.PEACEFUL)
		{
			Biome biome = this.world.getBiome(blockpos);

			if (biome == Biome.getBiome(LibraryBiomeID.EXTREME_HILLS) || biome == Biome.getBiome(LibraryBiomeID.EXTREME_HILLS_EDGE) || biome == Biome.getBiome(LibraryBiomeID.EXTREME_HILLS_PLUS))
			{
				if (MathHelper.floor(this.posY) <= 92)
				{
					return false;
				}
			}

			return super.getCanSpawnHere();
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
	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockEars(int partNumber)
	{
		return earsIdleAnimationClock[partNumber];
	}

	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockBodySway(int partNumber)
	{
		return bodySwayIdleAnimationClock[partNumber];
	}

	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockArmLft(int partNumber)
	{
		return armLftIdleAnimationClock[partNumber];
	}

	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockTail(int partNumber)
	{
		return tailIdleAnimationClock[partNumber];
	}

	@Override
	@SideOnly(Side.CLIENT)
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

	@SideOnly(Side.CLIENT)
	public void setClockDefaults()
	{
		setEarClockDefaults();
		setBodySwayClockDefaults();
		setArmLftClockDefaults();
		setTailClockDefaults();
	}

	@SideOnly(Side.CLIENT)
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

	@SideOnly(Side.CLIENT)
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

	@SideOnly(Side.CLIENT)
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

	@SideOnly(Side.CLIENT)
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