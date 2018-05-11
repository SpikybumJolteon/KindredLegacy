package fuzzyacornindustries.kindredlegacy.entity.mob.ai;

import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.mob.IGravityTracker;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;

public class AILeapAttack extends EntityAIBase
{
	/** The entity that is leaping. */
	EntityLiving leaper;
	/** The entity that the leaper is leaping towards. */
	EntityLivingBase leapTarget;
	/** The entity's motionY after leaping. */
	float leapMotionY;
	float leapMotionHorzModifier;
	double maximumDistanceToTargeet;
	double minimalDistanceToTargeet;
	int jumpChanceRNGInt;

	SoundEvent soundFX;

	public AILeapAttack(EntityLiving leapingEntity, float leapMotionYIn, float leapMotionHorzModifier, double minimalDistanceToTargeet, double maximumDistanceToTargeet, int jumpChanceRNGInt, SoundEvent soundFX)
	{
		this.leaper = leapingEntity;
		this.leapMotionY = leapMotionYIn;
		this.leapMotionHorzModifier = leapMotionHorzModifier;
		this.minimalDistanceToTargeet = minimalDistanceToTargeet;
		this.maximumDistanceToTargeet = maximumDistanceToTargeet;
		this.jumpChanceRNGInt = jumpChanceRNGInt;
		this.setMutexBits(5);

		this.soundFX = soundFX;
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
    @Override
	public boolean shouldExecute()
	{
		this.leapTarget = this.leaper.getAttackTarget();

		if (this.leapTarget == null)
		{
			return false;
		}
		else
		{
			double d0 = this.leaper.getDistanceSq(this.leapTarget);
			return d0 >= minimalDistanceToTargeet && d0 <= maximumDistanceToTargeet ? (!this.leaper.onGround ? false : this.leaper.getRNG().nextInt(jumpChanceRNGInt) == 0) : false;
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
    @Override
	public boolean shouldContinueExecuting()
	{
		return !this.leaper.onGround;
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
    @Override
	public void startExecuting()
	{
		double d0 = this.leapTarget.posX - this.leaper.posX;
		double d1 = this.leapTarget.posZ - this.leaper.posZ;
		float f = MathHelper.sqrt(d0 * d0 + d1 * d1);

		if ((double)f >= 1.0E-4D)
		{
			this.leaper.motionX += (d0 / (double)f * 0.5D * 0.800000011920929D + this.leaper.motionX * 0.20000000298023224D) * leapMotionHorzModifier;
			this.leaper.motionZ += (d1 / (double)f * 0.5D * 0.800000011920929D + this.leaper.motionZ * 0.20000000298023224D) * leapMotionHorzModifier;

			if(soundFX != null)
			{
				leaper.world.playSound((EntityPlayer)null, leaper.posX, leaper.posY, leaper.posZ, soundFX, SoundCategory.HOSTILE, 1.0F, 1.2F / (leaper.world.rand.nextFloat() * 0.4F + 0.8F));
			}
		}

		if(this.leaper instanceof IGravityTracker)
		{
			this.leaper.motionY = (double)this.leapMotionY * ((IGravityTracker)this.leaper).getGravityFactor();
		}
		else
		{
			this.leaper.motionY = (double)this.leapMotionY;
		}
	}
}