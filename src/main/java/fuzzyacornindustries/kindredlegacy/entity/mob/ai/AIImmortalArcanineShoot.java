package fuzzyacornindustries.kindredlegacy.entity.mob.ai;

import fuzzyacornindustries.kindredlegacy.animation.AIAnimation;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityImmortalArcanine;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryImmortalArcanineAttackID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class AIImmortalArcanineShoot extends AIAnimation 
{
	private EntityImmortalArcanine entityImmortalArcanine;
	private EntityLivingBase attackTarget;

	/*
	 * For some reason, the tick read in the model class lags behind the AI tick.
	 * Only known fix for now is to make the attack duration longer than end animation.
	 */
	public int attackDuration = 40; // Base length 22

	public AIImmortalArcanineShoot(EntityImmortalArcanine entityImmortalArcanine) 
	{
		super(entityImmortalArcanine, 3);
		this.entityImmortalArcanine = entityImmortalArcanine;
		attackTarget = null;
	}

    @Override
	public int getAnimationID() 
	{
		return LibraryImmortalArcanineAttackID.SHOOT;
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
		attackTarget = entityImmortalArcanine.getAttackTarget();
	}

    @Override
	public void updateTask() 
	{	
		if(attackTarget != null)
		{
			entityImmortalArcanine.getLookHelper().setLookPositionWithEntity(attackTarget, 30F, 30F);
			
			if(entityImmortalArcanine.getAnimationTick() == 3)
			{
				if (!entityImmortalArcanine.world.isRemote)
				{
					float f;
					
					double d0 = this.entityImmortalArcanine.getDistanceSq(this.attackTarget.posX, this.attackTarget.getEntityBoundingBox().minY, this.attackTarget.posZ);
					
					f = MathHelper.sqrt(d0) / entityImmortalArcanine.attackRange;
					float f1 = f;

					if (f < 0.1F)
					{
						f1 = 0.1F;
					}

					if (f1 > 1.0F)
					{
						f1 = 1.0F;
					}
					
					entityImmortalArcanine.attackWithCannons(entityImmortalArcanine, this.attackTarget, f1);
				}
			}
			else if(entityImmortalArcanine.getAnimationTick() == 4)
			{
				entityImmortalArcanine.world.setEntityState(entityImmortalArcanine, (byte)17);
			}
		}
	}
}