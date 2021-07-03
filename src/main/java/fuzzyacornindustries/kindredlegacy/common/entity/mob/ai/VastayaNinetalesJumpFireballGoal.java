package fuzzyacornindustries.kindredlegacy.common.entity.mob.ai;

import java.util.EnumSet;

import fuzzyacornindustries.kindredlegacy.client.animation.AIAnimation;
import fuzzyacornindustries.kindredlegacy.client.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.VastayaNinetalesEntity;
import fuzzyacornindustries.kindredlegacy.lib.action.LibraryAhriNinetalesAttackID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.MathHelper;

public class VastayaNinetalesJumpFireballGoal extends AIAnimation<IAnimatedEntity> 
{
	private VastayaNinetalesEntity entityVastayaNinetales;
	private LivingEntity attackTarget;

	/*
	 * For some reason, the tick read in the model class lags behind the AI tick.
	 * Only known fix for now is to make the attack duration longer than end animation.
	 */
	public int attackDuration = 30;

	public VastayaNinetalesJumpFireballGoal(VastayaNinetalesEntity entityVastayaNinetales) 
	{
		super(entityVastayaNinetales, entityVastayaNinetales, entityVastayaNinetales.getEntityId(), EnumSet.of(Goal.Flag.MOVE));
		this.entityVastayaNinetales = entityVastayaNinetales;
		attackTarget = null;
	}

    @Override
	public int getAnimationID() 
	{
		return LibraryAhriNinetalesAttackID.JUMP_FIREBALL;
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
		attackTarget = entityVastayaNinetales.getAttackTarget();
	}

    @Override
	public void tick()
	{	
		if(attackTarget != null)
		{
			entityVastayaNinetales.getLookController().setLookPositionWithEntity(attackTarget, 30F, 30F);

			if (entityVastayaNinetales.getAnimationTick() == 6 && entityVastayaNinetales.onGround)
            {
				entityVastayaNinetales.setMotion(entityVastayaNinetales.getMotion().getX(), 0.45D, entityVastayaNinetales.getMotion().getZ());
            }
			
			if(entityVastayaNinetales.getAnimationTick() == 14)
			{
				if (!entityVastayaNinetales.world.isRemote)
				{
					float f;
					
					double d0 = this.entityVastayaNinetales.getDistanceSq(this.attackTarget.getPosX(), this.attackTarget.getBoundingBox().minY, this.attackTarget.getPosZ());
					
					f = MathHelper.sqrt(d0) / entityVastayaNinetales.attackRange;
					float f1 = f;

					if (f < 0.1F)
					{
						f1 = 0.1F;
					}

					if (f1 > 1.0F)
					{
						f1 = 1.0F;
					}
					
					entityVastayaNinetales.attackWithFireball(entityVastayaNinetales, attackTarget, f1);
				}
			}
		}
	}
}