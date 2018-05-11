package fuzzyacornindustries.kindredlegacy.entity.mob.hostile;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.ability.EntitySubstituteDoll;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyLootTables;
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
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityClayEspurr extends HostilePokemon implements IRangedAttackMob, IAnimatedEntity
{
	private IdleAnimationClock armsIdleAnimationClock;
	private IdleAnimationClock earsIdleAnimationClock;
	private IdleAnimationClock tailIdleAnimationClock[] = new IdleAnimationClock[2];

	public static final int actionIDNone = 0;

	private int animationID;
	private int animationTick;

	static String mobName = "clay_espurr";

	public EntityClayEspurr(World par1World)
	{
		super(par1World);
		this.setSize(0.3F, 0.9F);
		this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackRanged(this, 1.0D, 35, 60, 10.0F));
        this.tasks.addTask(3, new EntityAIWanderAvoidWater(this, 0.8D, 1.0000001E-5F));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(5, new EntityAILookIdle(this));
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
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
	}

	@Override
	public int getTotalArmorValue()
	{
		return 2;
	}

	@Override
    protected SoundEvent getAmbientSound()
    {
        return KindredLegacySoundEvents.CLAY_ESPURR_AMBIENT;
    }

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return KindredLegacySoundEvents.CLAY_ESPURR_HURT;
    }

	@Override
    protected SoundEvent getDeathSound()
    {
        return KindredLegacySoundEvents.CLAY_ESPURR_DEATH;
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

	@Override
    public boolean isPotionApplicable(PotionEffect potioneffectIn)
    {
        return potioneffectIn.getPotion() == MobEffects.POISON ? false : super.isPotionApplicable(potioneffectIn);
    }

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase targetEntity, float par2)
	{
		byte durationSeconds = 5;

		if (this.world.getDifficulty() == EnumDifficulty.NORMAL)
		{
			durationSeconds = 12;
		}
		else if (this.world.getDifficulty() == EnumDifficulty.HARD)
		{
			durationSeconds = 20;
		}

		if (this.rand.nextInt(6) == 0)
		{
            ((EntityLivingBase)targetEntity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, durationSeconds * 20, 0));
		}

		if (this.rand.nextInt(12) == 0)
		{
            ((EntityLivingBase)targetEntity).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, durationSeconds * 20, 0));
		}

        ((EntityLivingBase)targetEntity).addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, durationSeconds * 20, 0));
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {}

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return KindredLegacyLootTables.CLAY_ESPURR_LOOT_TABLE;
    }

	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockEars()
	{
		return earsIdleAnimationClock;
	}

	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockArms()
	{
		return armsIdleAnimationClock;
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
		armsIdleAnimationClock.incrementClocks();
		earsIdleAnimationClock.incrementClocks();
		
		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			tailIdleAnimationClock[i].incrementClocks();
		}
	}

	@SideOnly(Side.CLIENT)
	public void setClockDefaults()
	{
		setArmsClockDefaults();
		setEarsClockDefaults();
		setTailClockDefaults();
	}

	@SideOnly(Side.CLIENT)
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

	@SideOnly(Side.CLIENT)
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

	@SideOnly(Side.CLIENT)
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