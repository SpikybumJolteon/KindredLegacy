package fuzzyacornindustries.kindredlegacy.common.entity.mob.ai;

import java.util.EnumSet;

import fuzzyacornindustries.kindredlegacy.client.animation.AIAnimation;
import fuzzyacornindustries.kindredlegacy.client.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.MuBunearyEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.DamageSource;

public class MuBunearySpinAttackGoal extends AIAnimation<IAnimatedEntity> 
{
	private MuBunearyEntity entityMuBuneary;
	private LivingEntity attackTarget;

	/*
	 * For some reason, the tick read in the model class lags behind the AI tick.
	 * Only known fix for now is to make the attack duration longer than end animation.
	 */
	public int attackDuration = 18;

	public MuBunearySpinAttackGoal(MuBunearyEntity muBuneary) 
	{
		super(muBuneary, muBuneary, muBuneary.getEntityId(), EnumSet.of(Goal.Flag.MOVE));
		entityMuBuneary = muBuneary;
		attackTarget = null;
	}

    @Override
	public int getAnimationID() 
	{
		return MuBunearyEntity.actionIDSpinAttack;
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
		attackTarget = entityMuBuneary.getAttackTarget();
	}

    @Override
	public void tick() 
	{	
		if(attackTarget != null)
		{
			entityMuBuneary.getLookController().setLookPositionWithEntity(attackTarget, 30F, 30F);

			if(entityMuBuneary.getAnimationTick() == 7 && attackTarget != null && entityMuBuneary.getDistance(attackTarget) <= entityMuBuneary.meleeAttackRange * entityMuBuneary.meleeAttackRange)
			{
				attackTarget.attackEntityFrom(DamageSource.causeMobDamage(entityMuBuneary), 3.0F);
			}
			else if(entityMuBuneary.getAnimationTick() == this.attackDuration - 1 && !attackTarget.isAlive())
			{	
				entityMuBuneary.setAttackTarget(null);
			}
		}
	}
}