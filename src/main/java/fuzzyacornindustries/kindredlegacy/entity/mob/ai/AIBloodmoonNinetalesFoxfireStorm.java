package fuzzyacornindustries.kindredlegacy.entity.mob.ai;

import fuzzyacornindustries.kindredlegacy.animation.AIAnimation;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityBloodmoonNinetales;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryAhriNinetalesAttackID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class AIBloodmoonNinetalesFoxfireStorm extends AIAnimation 
{
	private EntityBloodmoonNinetales entityBloodmoonNinetales;
	private EntityLivingBase attackTarget;

	public int attackDuration = 30;

	public AIBloodmoonNinetalesFoxfireStorm(EntityBloodmoonNinetales entityBloodmoonNinetales) 
	{
		super(entityBloodmoonNinetales, 3);
		this.entityBloodmoonNinetales = entityBloodmoonNinetales;
		attackTarget = null;
	}

	public int getAnimationID() 
	{
		return LibraryAhriNinetalesAttackID.FOXFIRE_STORM;
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
				if(entityBloodmoonNinetales.world.rand.nextInt(3) != 0)
				{
					entityBloodmoonNinetales.playGaspSound();
				}
				else
				{
					entityBloodmoonNinetales.playLaughSound();
				}
			}
			else if(entityBloodmoonNinetales.getAnimationTick() % 3 == 0 && 
					entityBloodmoonNinetales.getAnimationTick() >= 5 && entityBloodmoonNinetales.getAnimationTick() <= 25)
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
					
					entityBloodmoonNinetales.attackWithOffsetFireball(entityBloodmoonNinetales, attackTarget, f1);
					
					//System.out.println( "Test Animation Tick Code in Foxfirestorm class" );
					//System.out.println( Float.toString( entityBloodmoonNinetales.getAnimationTick() ) );
				}
			}
		}
	}
}