package fuzzyacornindustries.kindredlegacy.entity.mob.ai;

import fuzzyacornindustries.kindredlegacy.animation.AIAnimation;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityVastayaNinetales;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryAhriNinetalesAttackID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class AIVastayaNinetalesJumpFireball extends AIAnimation 
{
	private EntityVastayaNinetales entityVastayaNinetales;
	private EntityLivingBase attackTarget;

	/*
	 * For some reason, the tick read in the model class lags behind the AI tick.
	 * Only known fix for now is to make the attack duration longer than end animation.
	 */
	public int attackDuration = 30;

	public AIVastayaNinetalesJumpFireball(EntityVastayaNinetales entityVastayaNinetales) 
	{
		super(entityVastayaNinetales, 3);
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
	public void updateTask() 
	{	
		if(attackTarget != null)
		{
			entityVastayaNinetales.getLookHelper().setLookPositionWithEntity(attackTarget, 30F, 30F);

			if (entityVastayaNinetales.getAnimationTick() == 6 && entityVastayaNinetales.onGround)
            {
				entityVastayaNinetales.motionY = 0.45D;
            }
			
			if(entityVastayaNinetales.getAnimationTick() == 14)
			{
				if (!entityVastayaNinetales.world.isRemote)
				{
					float f;
					
					double d0 = this.entityVastayaNinetales.getDistanceSq(this.attackTarget.posX, this.attackTarget.getEntityBoundingBox().minY, this.attackTarget.posZ);
					
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