package fuzzyacornindustries.kindredlegacy.common.entity.mob.ai;

import java.util.EnumSet;

import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.MathHelper;

public class GeneralRangedAttackGoal extends Goal 
{
	private final MobEntity attacker;
	private final IRangedAttackMob rangedAttackEntityHost;
	private LivingEntity target;
	private int rangedAttackTime;
	private final double entityMoveSpeed;
	private int seeTime;
	private final int attackIntervalMin;
	private final int maxRangedAttackTime;
	private final float attackRadius;
	private final float maxAttackDistance;

	public GeneralRangedAttackGoal(IRangedAttackMob attacker, double movespeed, int maxAttackTime, float maxAttackDistanceIn) 
	{
		this(attacker, movespeed, maxAttackTime, maxAttackTime, maxAttackDistanceIn);
	}

	public GeneralRangedAttackGoal(IRangedAttackMob attacker, double movespeed, int attackIntervalMin, int maxAttackTime, float maxAttackDistanceIn) 
	{
		this.rangedAttackTime = -1;
		
		if (!(attacker instanceof LivingEntity)) 
		{
			throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
		} 
		else 
		{
			this.rangedAttackEntityHost = attacker;
			this.attacker = (MobEntity)attacker;
			this.entityMoveSpeed = movespeed;
			this.attackIntervalMin = attackIntervalMin;
			this.maxRangedAttackTime = maxAttackTime;
			this.attackRadius = maxAttackDistanceIn;
			this.maxAttackDistance = maxAttackDistanceIn * maxAttackDistanceIn;
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
    @Override
	public boolean shouldExecute() 
	{
		LivingEntity livingentity = this.attacker.getAttackTarget();
		if (livingentity == null || !livingentity.isAlive()) 
		{
			return false;
		} 
		else 
		{
			this.target = livingentity;
			return true;
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
    @Override
	public boolean shouldContinueExecuting() 
	{
		return this.shouldExecute() || !this.attacker.getNavigator().noPath();
	}

	/**
	 * Reset the task's internal state. Called when this task is interrupted by another one
	 */
    @Override
	public void resetTask() 
	{
		this.target = null;
		this.seeTime = 0;
		this.rangedAttackTime = -1;
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
    @Override
	public void tick() 
	{
		double d0 = this.attacker.getDistanceSq(target);//.getDistanceSq(this.target.getPosX(), this.target.getPosYHeight(0.5F), this.target.getPosZ());
		boolean flag = this.attacker.getEntitySenses().canSee(this.target);
		if (flag) 
		{
			++this.seeTime;
		}
		else
		{
			this.seeTime = 0;
		}

		if (d0 * 1.2F <= (double)this.maxAttackDistance && this.seeTime != 0  || !this.target.isAlive()) 
		{
			this.attacker.getNavigator().clearPath();
		} 
		else 
		{
			this.attacker.getNavigator().tryMoveToEntityLiving(this.target, this.entityMoveSpeed);
		}

		this.attacker.getLookController().setLookPositionWithEntity(this.target, 30.0F, 30.0F);
		
		//System.out.println( "rangedAttackTime " + Integer.toString( rangedAttackTime ) );
		
		if (--this.rangedAttackTime == 0) 
		{
			if (d0 >= (double)this.maxAttackDistance || !flag) 
			{
				return;
			}

			float f = MathHelper.sqrt(d0) / this.attackRadius;
			float lvt_5_1_ = MathHelper.clamp(f, 0.1F, 1.0F);
			this.rangedAttackEntityHost.attackEntityWithRangedAttack(this.target, lvt_5_1_);
			this.rangedAttackTime = MathHelper.floor(f * (float)(this.maxRangedAttackTime - this.attackIntervalMin) + (float)this.attackIntervalMin);
		}
		else if (this.rangedAttackTime < 0) 
		{
			float f2 = MathHelper.sqrt(d0) / this.attackRadius;
			this.rangedAttackTime = MathHelper.floor(f2 * (float)(this.maxRangedAttackTime - this.attackIntervalMin) + (float)this.attackIntervalMin);
		}

	}
}