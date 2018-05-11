package fuzzyacornindustries.kindredlegacy.entity.mob.ai;

import fuzzyacornindustries.kindredlegacy.animation.AIAnimation;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityMuBuneary;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;

/* 
 * Logic taken from Wizzrobe mob in Zelda Sword Skills
 * by coolAlias.
 */
public class AIMuBunearyChannelConfuse extends AIAnimation
{
	private EntityMuBuneary entityMuBuneary;

	/** Timer for casting time */
	private int castingTimer;

	/** Time between casting attempts */
	private final int attackDuration = 120;

	/** Distance squared at which a target is considered 'close enough' to attack */
	private final double minDistanceSq;

	/** Current attack target */
	private EntityLivingBase attackTarget;

	/** Number of ticks target has been out of sight */
	private int unseenTimer;

	/** Maximum time target can remain out of sight before caster aborts spell */
	private static final int MAX_TIME_UNSEEN = 10;

	public AIMuBunearyChannelConfuse(EntityMuBuneary muBuneary, float range) 
	{
		super(muBuneary, 3);
		entityMuBuneary = muBuneary;
		minDistanceSq = range * range;
		attackTarget = null;
	}

    @Override
	public int getAnimationID() 
	{
		return EntityMuBuneary.actionIDCastConfuse;
	}

    @Override
	public boolean isAutomatic() 
	{
		return false;
	}

    @Override
	public int getDuration() 
	{
		return attackDuration;
	}

	/**
	 * Forcefully interrupts the spell-casting attempt, such as when taking damage
	 */
	public void interruptCasting() 
	{
		resetTask();
	}

	@Override
	public boolean shouldContinueExecuting() 
	{
		if (attackTarget == null) 
		{
			return false;
		} 
		else if (castingTimer < 1) 
		{
			return false;
		} 
		else if (!entityMuBuneary.getEntitySenses().canSee(attackTarget) && ++unseenTimer > MAX_TIME_UNSEEN) 
		{
			unseenTimer = 0;
			return false;
		}

		return entityMuBuneary.getDistanceSq(attackTarget.posX, attackTarget.getEntityBoundingBox().minY, attackTarget.posZ) < minDistanceSq;
	}

	@Override
	public boolean shouldExecute() 
	{
		EntityLivingBase target = entityMuBuneary.getAttackTarget();

		if (target == null) 
		{
			return false;
		}
		else if (!entityMuBuneary.canCastConfuse())
		{
			return false;
		}

		attackTarget = target;
		double d = entityMuBuneary.getDistanceSq(attackTarget.posX, attackTarget.getEntityBoundingBox().minY, attackTarget.posZ);
		boolean flag = entityMuBuneary.getEntitySenses().canSee(attackTarget);

		if (!flag) 
		{
			interruptCasting(); // calls resetTask
			return false;
		}

		if (castingTimer == 0) 
		{
			entityMuBuneary.getLookHelper().setLookPositionWithEntity(attackTarget, 30.0F, 30.0F);

			if (d > minDistanceSq || !flag) 
			{
				return false;
			}

			castingTimer = attackDuration;

			return castingTimer > 0;
		}

		return false;
	}

	@Override
	public void startExecuting() 
	{
		super.startExecuting();
		entityMuBuneary.getNavigator().clearPath();
	}

	@Override
	public void resetTask() 
	{
		attackTarget = null;
		castingTimer = 0;
		unseenTimer = 0;
		
		super.resetTask();
	}

	@Override
	public void updateTask() 
	{
		if (castingTimer > 0) 
		{
			--castingTimer;
			entityMuBuneary.getLookHelper().setLookPositionWithEntity(attackTarget, 30.0F, 30.0F);
		}

		if (castingTimer <= 0) 
		{
			byte durationSeconds = 8;

			if (entityMuBuneary.world.getDifficulty() == EnumDifficulty.NORMAL)
			{
				durationSeconds = 16;
			}
			else if (entityMuBuneary.world.getDifficulty() == EnumDifficulty.HARD)
			{
				durationSeconds = 26;
			}

			((EntityLivingBase)attackTarget).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, durationSeconds * 20, 0));
			entityMuBuneary.toggleConfuseCastability();

			entityMuBuneary.motionY = 0.35D;
		}
	}
}