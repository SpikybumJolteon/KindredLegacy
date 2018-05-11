package fuzzyacornindustries.kindredlegacy.entity.mob.hostile;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.ability.EntitySubstituteDoll;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyLootTables;
import net.minecraft.block.Block;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityClaySkitty extends HostilePokemon implements IAnimatedEntity
{
	private IdleAnimationClock neckBobbleIdleAnimationClock;
	private IdleAnimationClock tailIdleAnimationClock;

	static String mobName = "clay_skitty";

	public EntityClaySkitty(World par1World)
	{
		super(par1World);
		
		this.setSize(0.3F, 0.9F);
		this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.8D, 1.0000001E-5F));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
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
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.3D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(16.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
	}

	@Override
	public int getTotalArmorValue()
	{
		return 2;
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
    protected SoundEvent getAmbientSound()
    {
        return KindredLegacySoundEvents.CLAY_SKITTY_AMBIENT;
    }

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return KindredLegacySoundEvents.CLAY_SKITTY_HURT;
    }

	@Override
    protected SoundEvent getDeathSound()
    {
        return KindredLegacySoundEvents.CLAY_SKITTY_DEATH;
    }

	@Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
    }

	@Override
    public boolean isPotionApplicable(PotionEffect potioneffectIn)
    {
        return potioneffectIn.getPotion() == MobEffects.POISON ? false : super.isPotionApplicable(potioneffectIn);
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return KindredLegacyLootTables.CLAY_SKITTY_LOOT_TABLE;
    }
    
	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockNeckBobble()
	{
		return tailIdleAnimationClock;
	}

	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockTail()
	{
		return tailIdleAnimationClock;
	}

	@SideOnly(Side.CLIENT)
	@Override
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

		neckBobbleIdleAnimationClock.setPhaseDurationX(0, 70);

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

		tailIdleAnimationClock.setPhaseDurationX(0, 60);
		tailIdleAnimationClock.setPhaseDurationY(0, 60);

		int startingClockX = randomInt;
		int startingClockY = randomInt + 15;

		while(startingClockX > tailIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= tailIdleAnimationClock.getTotalDurationLengthX();
		}

		while(startingClockY > tailIdleAnimationClock.getTotalDurationLengthY())
		{
			startingClockY -= tailIdleAnimationClock.getTotalDurationLengthY();
		}

		tailIdleAnimationClock.setClockX(startingClockX);
		tailIdleAnimationClock.setClockY(startingClockY);
	}
}