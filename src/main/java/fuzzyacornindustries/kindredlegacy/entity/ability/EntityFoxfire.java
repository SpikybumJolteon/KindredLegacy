package fuzzyacornindustries.kindredlegacy.entity.ability;

import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.AILeapAttack;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.HostilePokemon;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.TamablePokemon;
import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.Optional.Method;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Optional.Interface(iface="micdoodle8.mods.galacticraft.api.entity.IEntityBreathable", modid="galacticraftcore", striprefs=true)
public class EntityFoxfire extends HostilePokemon implements IAnimatedEntity, IAnimateFoxfire, IEntityBreathable
{
	private IdleAnimationClock spinIdleAnimationClock;

	public static final int actionIDNone = 0;

	private int animationID;
	private int animationTick;

	public float attackPower = 2F;

	static String mobName = "foxfire";

	public TamablePokemon attackingPoketamable;

	public EntityFoxfire(World par1World)
	{
		super(par1World);

		this.setSize(0.5F, 0.9F);

		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 0.8D, 1.0000001E-5F));
		this.tasks.addTask(3, new AILeapAttack(this, 0.6F, 2.0F, 6D, 20D, 5, KindredLegacySoundEvents.FIREBALL_SWOOSH));
		this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, true));
		this.tasks.addTask(5, new EntityAIWander(this, 0.6D));

		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySubstituteDoll.class, true));

		if(this.world.isRemote)
		{
			setClockDefaults();
		}

		animationID = actionIDNone;
		animationTick = 0;

		this.experienceValue = 0;
	}

	public static String getMobName()
	{
		return mobName;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(0.0001D);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10.0D);
	}

	@Override
	public void fall(float distance, float damageMultiplier) {}

	@Method(modid="galacticraftcore")
	@Override
	public boolean canBreath()
	{
		return true;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if(this.ticksExisted < 10)
		{
			this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX, this.posY, this.posZ, 1.0D, 0.0D, 0.0D, new int[0]);
		}

		if(this.ticksExisted > 200)
		{
			this.setDead();
		}

		if (this.world.isRemote)
		{
			for (int i = 0; i < 2; ++i)
			{
				this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D, new int[0]);
			}
		}	
	}

	@Override
	public boolean attackEntityAsMob(Entity targetEntity)
	{
		boolean flag = false;

		if(((EntityLivingBase) targetEntity).getHealth() <= this.attackPower && attackingPoketamable.getOwner() != null)
		{
			flag = targetEntity.attackEntityFrom(DamageSource.causeMobDamage(attackingPoketamable.getOwner()), this.attackPower);
		}
		else
		{
			flag = targetEntity.attackEntityFrom(DamageSource.causeMobDamage(this), this.attackPower);
		}
		
		this.setDead();

		return flag;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return SoundEvents.BLOCK_FIRE_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return SoundEvents.BLOCK_FIRE_EXTINGUISH;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundEvents.BLOCK_FIRE_EXTINGUISH;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender()
	{
		return 15728880;
	}

	/**
	 * Gets how bright this entity is.
	 */
	@Override
	public float getBrightness()
	{
		return 1.0F;
	}

	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	@SideOnly(Side.CLIENT)
	@Override
	public IdleAnimationClock getIdleAnimationClockSpin() 
	{
		return spinIdleAnimationClock;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void incrementPartClocks()
	{
		spinIdleAnimationClock.incrementClocks();
	}

	@SideOnly(Side.CLIENT)
	public void setClockDefaults()
	{
		setSpinClockDefaults();
	}

	@SideOnly(Side.CLIENT)
	public void setSpinClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		spinIdleAnimationClock = new IdleAnimationClock(0, 1, 0);

		spinIdleAnimationClock.setPhaseDurationY(0, 60);

		int startingClockY = randomInt;

		while(startingClockY > spinIdleAnimationClock.getTotalDurationLengthY())
		{
			startingClockY -= spinIdleAnimationClock.getTotalDurationLengthY();
		}

		spinIdleAnimationClock.setClockY(startingClockY);
	}
}