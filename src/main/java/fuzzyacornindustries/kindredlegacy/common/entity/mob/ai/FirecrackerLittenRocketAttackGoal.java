package fuzzyacornindustries.kindredlegacy.common.entity.mob.ai;

import java.util.EnumSet;

import fuzzyacornindustries.kindredlegacy.client.animation.AIAnimation;
import fuzzyacornindustries.kindredlegacy.client.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.FirecrackerLittenEntity;
import fuzzyacornindustries.kindredlegacy.lib.action.LibraryFirecrackerLittenAttackID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.MathHelper;

public class FirecrackerLittenRocketAttackGoal extends AIAnimation<IAnimatedEntity> 
{
	private FirecrackerLittenEntity entityFirecrackerLitten;
	private LivingEntity attackTarget;

	public float range;

	/*
	 * For some reason, the tick read in the model class lags behind the AI tick.
	 * Only known fix for now is to make the attack duration longer than end animation.
	 */
	public int attackDuration = 15;

	public FirecrackerLittenRocketAttackGoal(FirecrackerLittenEntity entityFirecrackerLitten, float range) 
	{
		super(entityFirecrackerLitten, entityFirecrackerLitten, entityFirecrackerLitten.getEntityId(), EnumSet.of(Goal.Flag.MOVE));
		this.entityFirecrackerLitten = entityFirecrackerLitten;
		attackTarget = null;

		this.range = range * range;
	}

    @Override
	public int getAnimationID() 
	{
		return LibraryFirecrackerLittenAttackID.ROCKET_ATTACK;
	}

    @Override
	public boolean isAutomatic() 
	{
		return true;
	}

    @Override
	public int getDuration() 
	{
		return attackDuration;
	}

    @Override
	public void startExecuting() 
	{
		super.startExecuting();
		attackTarget = entityFirecrackerLitten.getAttackTarget();
	}

    @Override
	public void tick()
	{	
		if(attackTarget != null)
		{
			entityFirecrackerLitten.getLookController().setLookPositionWithEntity(attackTarget, 30F, 30F);
			
			if(entityFirecrackerLitten.getAnimationTick() == 5)
			{
				if (!entityFirecrackerLitten.world.isRemote)
				{
					float f;
					
					double d0 = this.entityFirecrackerLitten.getDistanceSq(this.attackTarget.getPosX(), this.attackTarget.getBoundingBox().minY, this.attackTarget.getPosZ());
					
					f = MathHelper.sqrt(d0) / entityFirecrackerLitten.attackRange;
					float f1 = f;

					if (f < 0.1F)
					{
						f1 = 0.1F;
					}

					if (f1 > 1.0F)
					{
						f1 = 1.0F;
					}
					
					entityFirecrackerLitten.attackWithRocket(entityFirecrackerLitten, this.attackTarget, f1);
				}
			}
		}
	}
}