package fuzzyacornindustries.kindredlegacy.common.entity.mob.ai;

import java.util.EnumSet;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;

public class LeapAttackGoal extends Goal 
{
	private final MobEntity leaper;
	private LivingEntity leapTarget;
	private final float leapMotionY;
	float leapMotionHorzModifier;
	double maximumDistanceToTargeet;
	double minimalDistanceToTargeet;
	int jumpChanceRNGInt;

	SoundEvent soundFX;

	public LeapAttackGoal(MobEntity leapingEntity, float leapMotionYIn, float leapMotionHorzModifier, double minimalDistanceToTargeet, double maximumDistanceToTargeet, int jumpChanceRNGInt, SoundEvent soundFX) 
	{
		this.leaper = leapingEntity;
		this.leapMotionY = leapMotionYIn;
		this.leapMotionHorzModifier = leapMotionHorzModifier;
		this.minimalDistanceToTargeet = minimalDistanceToTargeet;
		this.maximumDistanceToTargeet = maximumDistanceToTargeet;
		this.jumpChanceRNGInt = jumpChanceRNGInt;
		this.setMutexFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));

		this.soundFX = soundFX;
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
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
	public boolean shouldContinueExecuting() 
	{
		return !this.leaper.onGround;
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting()
	{
		if(!this.leaper.isPotionActive(Effects.SLOWNESS))
		{
			Vec3d vec3d = this.leaper.getMotion();
			Vec3d vec3d1 = new Vec3d(this.leapTarget.getPosX() - this.leaper.getPosX(), 0.0D, this.leapTarget.getPosZ() - this.leaper.getPosZ());

			if (vec3d1.lengthSquared() > 1.0E-7D) 
			{
				vec3d1 = vec3d1.normalize().scale(0.4D).add(vec3d.scale(leapMotionHorzModifier));

				if(soundFX != null)
				{
					leaper.world.playSound((PlayerEntity)null, leaper.getPosX(), leaper.getPosY(), leaper.getPosZ(), soundFX, SoundCategory.HOSTILE, 1.0F, 1.2F / (leaper.world.rand.nextFloat() * 0.4F + 0.8F));
				}
			}

			this.leaper.setMotion(vec3d1.x, (double)this.leapMotionY, vec3d1.z);
		}
	}
}