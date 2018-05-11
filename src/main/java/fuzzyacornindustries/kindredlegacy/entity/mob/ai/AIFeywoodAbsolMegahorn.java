package fuzzyacornindustries.kindredlegacy.entity.mob.ai;

import fuzzyacornindustries.kindredlegacy.animation.AIAnimation;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityFeywoodAbsol;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryFeywoodAbsolAttackID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public class AIFeywoodAbsolMegahorn extends AIAnimation 
{
	private EntityFeywoodAbsol entityAbsol;
	private EntityLivingBase attackTarget;

	public float range = 2.8F;
	
	/*
	 * For some reason, the tick read in the model class lags behind the AI tick.
	 * Only known fix for now is to make the attack duration longer than end animation.
	 */
	public int attackDuration = 12;

	public AIFeywoodAbsolMegahorn(EntityFeywoodAbsol entityAbsol)
	{
		super(entityAbsol, 3);
		this.entityAbsol = entityAbsol;
		attackTarget = null;
	}

    @Override
	public int getAnimationID() 
	{
		return LibraryFeywoodAbsolAttackID.MEGAHORN;
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
		attackTarget = entityAbsol.getAttackTarget();
	}

    @Override
	public void updateTask() 
	{	
		if(attackTarget != null)
		{
			entityAbsol.getLookHelper().setLookPositionWithEntity(attackTarget, 60F, 60F);
			entityAbsol.faceEntity(attackTarget, 60F, 60F);

			if(entityAbsol.getAnimationTick() == 5 && attackTarget != null && entityAbsol.getDistanceSq(this.attackTarget.posX, this.attackTarget.getEntityBoundingBox().minY, this.attackTarget.posZ) <= (range * range))
			{
				if(entityAbsol.isTamed())
				{
					if(attackTarget.getHealth() <= entityAbsol.getAttackPower())
					{
						attackTarget.attackEntityFrom(DamageSource.causeMobDamage(entityAbsol.getOwner()), entityAbsol.getAttackDamage());
					}
					else
					{
						attackTarget.attackEntityFrom(DamageSource.causeMobDamage(entityAbsol), entityAbsol.getAttackDamage());
					}
				}
				else
				{
					attackTarget.attackEntityFrom(DamageSource.causeMobDamage(entityAbsol), entityAbsol.getAttackDamage());
				}
				//System.out.println( "Test Entity Detected By Kill Order" );
				//System.out.println( Integer.toString( entityAbsol.getAttackTarget().getEntityId() ) );
			}
		}
	}
}