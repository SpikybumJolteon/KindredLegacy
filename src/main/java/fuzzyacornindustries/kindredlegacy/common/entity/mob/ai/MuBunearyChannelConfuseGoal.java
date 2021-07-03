package fuzzyacornindustries.kindredlegacy.common.entity.mob.ai;

import java.util.EnumSet;

import fuzzyacornindustries.kindredlegacy.client.animation.AIAnimation;
import fuzzyacornindustries.kindredlegacy.client.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.MuBunearyEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.Difficulty;

/* 
 * Logic taken from Wizzrobe mob in Zelda Sword Skills
 * by coolAlias.
 */
public class MuBunearyChannelConfuseGoal extends AIAnimation<IAnimatedEntity> 
{
	private MuBunearyEntity entityMuBuneary;

	/** Timer for casting time */
	private int castingTimer;

	/** Time between casting attempts */
	private final int attackDuration = 120;

	/** Distance squared at which a target is considered 'close enough' to attack */
	private final double minDistanceSq;

	/** Current attack target */
	private LivingEntity attackTarget;

	/** Number of ticks target has been out of sight */
	private int unseenTimer;

	/** Maximum time target can remain out of sight before caster aborts spell */
	private static final int MAX_TIME_UNSEEN = 10;

	public MuBunearyChannelConfuseGoal(MuBunearyEntity muBuneary, float range) 
	{
		super(muBuneary, muBuneary, muBuneary.getEntityId(), EnumSet.of(Goal.Flag.MOVE));
		entityMuBuneary = muBuneary;
		minDistanceSq = range * range;
		attackTarget = null;
	}

	@Override
	public int getAnimationID() 
	{
		return MuBunearyEntity.actionIDCastConfuse;
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

		return entityMuBuneary.getDistanceSq(attackTarget.getPosX(), attackTarget.getBoundingBox().minY, attackTarget.getPosZ()) < minDistanceSq;
	}

	@Override
	public boolean shouldExecute() 
	{
		LivingEntity target = entityMuBuneary.getAttackTarget();

		if (target == null) 
		{
			return false;
		}
		else if (!entityMuBuneary.canCastConfuse())
		{
			return false;
		}

		attackTarget = target;
		double d = entityMuBuneary.getDistanceSq(attackTarget.getPosX(), attackTarget.getBoundingBox().minY, attackTarget.getPosZ());
		boolean flag = entityMuBuneary.getEntitySenses().canSee(attackTarget);

		if (!flag) 
		{
			interruptCasting(); // calls resetTask
			return false;
		}

		if (castingTimer == 0) 
		{
			entityMuBuneary.getLookController().setLookPositionWithEntity(attackTarget, 30.0F, 30.0F);

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
	public void tick() 
	{
		if (castingTimer > 0) 
		{
			--castingTimer;
			entityMuBuneary.getLookController().setLookPositionWithEntity(attackTarget, 30.0F, 30.0F);
		}

		if (castingTimer <= 0) 
		{
			byte durationSeconds = 8;

			if (entityMuBuneary.world.getDifficulty() == Difficulty.NORMAL)
			{
				durationSeconds = 16;
			}
			else if (entityMuBuneary.world.getDifficulty() == Difficulty.HARD)
			{
				durationSeconds = 26;
			}

			((LivingEntity)attackTarget).addPotionEffect(new EffectInstance(Effects.NAUSEA, durationSeconds * 20, 0));

			entityMuBuneary.toggleConfuseCastability();

			entityMuBuneary.setMotion(entityMuBuneary.getMotion().x, entityMuBuneary.getMotion().y + 0.35D, entityMuBuneary.getMotion().z);
		}
	}
}