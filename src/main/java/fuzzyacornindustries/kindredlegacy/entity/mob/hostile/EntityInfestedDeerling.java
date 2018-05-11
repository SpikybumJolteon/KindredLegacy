package fuzzyacornindustries.kindredlegacy.entity.mob.hostile;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.ability.EntitySubstituteDoll;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyLootTables;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
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
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityInfestedDeerling extends HostilePokemon implements IAnimatedEntity
{
	private IdleAnimationClock neckBobbleIdleAnimationClock;
	private IdleAnimationClock tailIdleAnimationClock;

	public static final int actionIDNone = 0;
	
	static String mobName = "infested_deerling";

	public EntityInfestedDeerling(World par1World)
	{
		super(par1World);
		this.setSize(0.3F, 0.9F);
		this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
		this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySubstituteDoll.class, true));

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
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.1D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(25.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30D);
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.world.isDaytime() && !this.world.isRemote)
		{
            float f = this.getBrightness();
         
            BlockPos blockpos = new BlockPos(this.posX, (double)Math.round(this.posY), this.posZ);

            if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.canSeeSky(blockpos))
            {
				this.setFire(8);
			}
		}

		for (int i = 0; i < 2; ++i)
		{
			this.world.spawnParticle(EnumParticleTypes.TOWN_AURA, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
		}

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
		if (super.attackEntityAsMob(targetEntity))
		{
			if (targetEntity instanceof EntityLivingBase)
			{
				byte durationSeconds = 5;

				if (this.world.getDifficulty() == EnumDifficulty.NORMAL)
				{
					durationSeconds = 10;
				}
				else if (this.world.getDifficulty() == EnumDifficulty.HARD)
				{
					durationSeconds = 18;
				}

				if (this.rand.nextInt(2) == 0)
				{
		            ((EntityLivingBase)targetEntity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, durationSeconds * 20, 0));
				}

				if (this.rand.nextInt(4) == 0)
				{
		            ((EntityLivingBase)targetEntity).addPotionEffect(new PotionEffect(MobEffects.POISON, durationSeconds * 20, 0));
				}

				if (this.rand.nextInt(10) == 0)
				{
		            ((EntityLivingBase)targetEntity).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, durationSeconds * 20, 0));
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
        return KindredLegacySoundEvents.INFESTED_DEERLING_AMBIENT;
    }

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return KindredLegacySoundEvents.INFESTED_DEERLING_HURT;
    }

	@Override
    protected SoundEvent getDeathSound()
    {
        return KindredLegacySoundEvents.INFESTED_DEERLING_DEATH;
    }

	@Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
    }

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.UNDEAD;
	}

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return KindredLegacyLootTables.INFESTED_DEERLING_LOOT_TABLE;
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
	public IdleAnimationClock getIdleAnimationClockTail()
	{
		return tailIdleAnimationClock;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void incrementPartClocks()
	{
		neckBobbleIdleAnimationClock.incrementClocks();
		tailIdleAnimationClock.incrementClocks();
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

		neckBobbleIdleAnimationClock.setPhaseDurationX(0, 75);

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