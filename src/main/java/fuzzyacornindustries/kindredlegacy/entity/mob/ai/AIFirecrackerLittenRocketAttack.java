package fuzzyacornindustries.kindredlegacy.entity.mob.ai;

import fuzzyacornindustries.kindredlegacy.animation.AIAnimation;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityFirecrackerLitten;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryFirecrackerLittenAttackID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class AIFirecrackerLittenRocketAttack extends AIAnimation 
{
	private EntityFirecrackerLitten entityFirecrackerLitten;
	private EntityLivingBase attackTarget;

	/*
	 * For some reason, the tick read in the model class lags behind the AI tick.
	 * Only known fix for now is to make the attack duration longer than end animation.
	 */
	public int attackDuration = 15;

	public AIFirecrackerLittenRocketAttack(EntityFirecrackerLitten entityFirecrackerLitten) 
	{
		super(entityFirecrackerLitten, 3);
		this.entityFirecrackerLitten = entityFirecrackerLitten;
		attackTarget = null;
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
	public void updateTask() 
	{	
		if(attackTarget != null)
		{
			entityFirecrackerLitten.getLookHelper().setLookPositionWithEntity(attackTarget, 30F, 30F);
			
			if(entityFirecrackerLitten.getAnimationTick() == 5)
			{
				if (!entityFirecrackerLitten.world.isRemote)
				{
					float f;
					
					double d0 = this.entityFirecrackerLitten.getDistanceSq(this.attackTarget.posX, this.attackTarget.getEntityBoundingBox().minY, this.attackTarget.posZ);
					
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