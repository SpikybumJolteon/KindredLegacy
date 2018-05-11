package fuzzyacornindustries.kindredlegacy.entity.mob.ai;

import fuzzyacornindustries.kindredlegacy.animation.AIAnimation;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityMuBuneary;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public class AIMuBunearySpinAttack extends AIAnimation 
{
	private EntityMuBuneary entityMuBuneary;
	private EntityLivingBase attackTarget;

	/*
	 * For some reason, the tick read in the model class lags behind the AI tick.
	 * Only known fix for now is to make the attack duration longer than end animation.
	 */
	public int attackDuration = 18;

	public AIMuBunearySpinAttack(EntityMuBuneary muBuneary) 
	{
		super(muBuneary, 3);
		entityMuBuneary = muBuneary;
		attackTarget = null;
	}

	public int getAnimationID() 
	{
		return EntityMuBuneary.actionIDSpinAttack;
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
		attackTarget = entityMuBuneary.getAttackTarget();
	}

	public void updateTask() 
	{	
		if(attackTarget != null)
		{
			entityMuBuneary.getLookHelper().setLookPositionWithEntity(attackTarget, 30F, 30F);

			if(entityMuBuneary.getAnimationTick() == 7 && attackTarget != null && entityMuBuneary.getDistanceSq(this.attackTarget.posX, this.attackTarget.getEntityBoundingBox().minY, this.attackTarget.posZ) <= entityMuBuneary.meleeAttackRange * entityMuBuneary.meleeAttackRange)
			{
				attackTarget.attackEntityFrom(DamageSource.causeMobDamage(entityMuBuneary), 3.0F);
			}
			else if(entityMuBuneary.getAnimationTick() == this.attackDuration - 1 && attackTarget.isDead == true)
			{	
				entityMuBuneary.setAttackTarget(null);
			}

			/*
			System.out.println( "Test Animation Tick Code in AI class" );
			System.out.println( Float.toString( entityMuBuneary.getAnimationTick() ) );
			 */
		}
	}
}