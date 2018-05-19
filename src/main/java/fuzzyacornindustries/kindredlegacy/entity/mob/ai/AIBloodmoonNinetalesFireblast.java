package fuzzyacornindustries.kindredlegacy.entity.mob.ai;

import fuzzyacornindustries.kindredlegacy.animation.AIAnimation;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityBloodmoonNinetales;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryAhriNinetalesAttackID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class AIBloodmoonNinetalesFireblast extends AIAnimation 
{
	private EntityBloodmoonNinetales entityBloodmoonNinetales;
	private EntityLivingBase attackTarget;

	public int attackDuration = 56;

	public AIBloodmoonNinetalesFireblast(EntityBloodmoonNinetales entityBloodmoonNinetales) 
	{
		super(entityBloodmoonNinetales, 3);
		this.entityBloodmoonNinetales = entityBloodmoonNinetales;
		attackTarget = null;
	}

	public int getAnimationID() 
	{
		return LibraryAhriNinetalesAttackID.FIREBLAST;
	}

	public boolean isAutomatic() 
	{
		return true;
	}

	public int getDuration() 
	{
		return attackDuration;
	}

	public void startExecuting() 
	{
		super.startExecuting();
		attackTarget = entityBloodmoonNinetales.getAttackTarget();
	}

	public void updateTask() 
	{	
		if(attackTarget != null)
		{
			entityBloodmoonNinetales.getLookHelper().setLookPositionWithEntity(attackTarget, 30F, 30F);

			if(entityBloodmoonNinetales.getAnimationTick() == 1)
			{
				if(entityBloodmoonNinetales.world.rand.nextInt(3) == 1)
				{
					entityBloodmoonNinetales.playLaughSound();
				}
			}
			else if (entityBloodmoonNinetales.getAnimationTick() == 34 && entityBloodmoonNinetales.onGround)
            {
				entityBloodmoonNinetales.motionY = 0.60D;

				entityBloodmoonNinetales.playGaspSound();
            }
			else if(entityBloodmoonNinetales.getAnimationTick() == 40)
			{
				entityBloodmoonNinetales.playAttackSound();
			}
			else if(entityBloodmoonNinetales.getAnimationTick() == 44)
			{
				if (!entityBloodmoonNinetales.world.isRemote)
				{
					float f;
					
					double d0 = this.entityBloodmoonNinetales.getDistanceSq(this.attackTarget.posX, this.attackTarget.getEntityBoundingBox().minY, this.attackTarget.posZ);
					
					f = MathHelper.sqrt(d0) / entityBloodmoonNinetales.maxFireballRange;
					float f1 = f;

					if (f < 0.1F)
					{
						f1 = 0.1F;
					}

					if (f1 > 1.0F)
					{
						f1 = 1.0F;
					}

					entityBloodmoonNinetales.attackWithFireblast(entityBloodmoonNinetales, attackTarget, f1);
				}
			}
		}
	}
}