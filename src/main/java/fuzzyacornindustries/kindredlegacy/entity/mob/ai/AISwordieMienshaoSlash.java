package fuzzyacornindustries.kindredlegacy.entity.mob.ai;

import fuzzyacornindustries.kindredlegacy.animation.AIAnimation;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntitySwordieMienshao;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public class AISwordieMienshaoSlash extends AIAnimation 
{
	private EntitySwordieMienshao entitySwordieMienshao;
	private EntityLivingBase attackTarget;

	/*
	 * For some reason, the tick read in the model class lags behind the AI tick.
	 * Only known fix for now is to make the attack duration longer than end animation.
	 */
	public int attackDuration = 25;

	public AISwordieMienshaoSlash(EntitySwordieMienshao entitySwordieMienshao) 
	{
		super(entitySwordieMienshao, 3);
		this.entitySwordieMienshao = entitySwordieMienshao;
		attackTarget = null;
	}

    @Override
	public int getAnimationID() 
	{
		return EntitySwordieMienshao.actionIDSlash;
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
		attackTarget = entitySwordieMienshao.getAttackTarget();
	}

    @Override
	public void updateTask() 
	{	
		if(attackTarget != null)
		{
			entitySwordieMienshao.getLookHelper().setLookPositionWithEntity(attackTarget, 30F, 30F);

			if(entitySwordieMienshao.getAnimationTick() == 8 && attackTarget != null && entitySwordieMienshao.getDistanceSq(attackTarget) <= 10F)
			{
				attackTarget.attackEntityFrom(DamageSource.causeMobDamage(entitySwordieMienshao), 6.0F);
			}
			else if(entitySwordieMienshao.getAnimationTick() == this.attackDuration - 1 || attackTarget.isDead == true)
			{	
				entitySwordieMienshao.setAttackTarget(null);
			}
		}
	}
}