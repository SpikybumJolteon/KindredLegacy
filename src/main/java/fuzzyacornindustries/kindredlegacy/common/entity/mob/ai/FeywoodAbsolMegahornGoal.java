package fuzzyacornindustries.kindredlegacy.common.entity.mob.ai;

import java.util.EnumSet;

import fuzzyacornindustries.kindredlegacy.client.animation.AIAnimation;
import fuzzyacornindustries.kindredlegacy.client.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.FeywoodAbsolEntity;
import fuzzyacornindustries.kindredlegacy.lib.action.LibraryFeywoodAbsolAttackID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.DamageSource;

public class FeywoodAbsolMegahornGoal extends AIAnimation<IAnimatedEntity> 
{
	private FeywoodAbsolEntity entityAbsol;
	private LivingEntity attackTarget;

	public float range;
	
	/*
	 * For some reason, the tick read in the model class lags behind the AI tick.
	 * Only known fix for now is to make the attack duration longer than end animation.
	 */
	public int attackDuration = 12;

	public FeywoodAbsolMegahornGoal(FeywoodAbsolEntity entityAbsol, float range)
	{
		super(entityAbsol, entityAbsol, entityAbsol.getEntityId(), EnumSet.of(Goal.Flag.MOVE));
		this.entityAbsol = entityAbsol;
		attackTarget = null;

		this.range = range * range;
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
	public void tick() 
	{	
    	if(attackTarget != null)
		{
			entityAbsol.getLookController().setLookPositionWithEntity(attackTarget, 60F, 60F);

			if(entityAbsol.getAnimationTick() == 5 && entityAbsol.getDistanceSq(this.attackTarget.getPosX(), this.attackTarget.getPosYHeight(0.5), this.attackTarget.getPosZ()) <= range + 0.5D)
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