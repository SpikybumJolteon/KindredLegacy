package fuzzyacornindustries.kindredlegacy.entity.mob.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;

public class EntityAINearestAttackableZombieExcludingPigman<T extends EntityLivingBase> extends EntityAITarget
{
	private final int targetChance;
	/** Instance of EntityAINearestAttackableTargetSorter. */
	protected final EntityAINearestAttackableZombieExcludingPigman.Sorter theNearestAttackableTargetSorter;
	protected final Predicate <? super T > targetEntitySelector;
	protected T targetEntity;

	public EntityAINearestAttackableZombieExcludingPigman(EntityCreature creature, boolean checkSight)
	{
		this(creature, checkSight, false);
	}

	public EntityAINearestAttackableZombieExcludingPigman(EntityCreature creature, boolean checkSight, boolean onlyNearby)
	{
		this(creature, 10, checkSight, onlyNearby, (Predicate <? super T >)null);
	}

	public EntityAINearestAttackableZombieExcludingPigman(EntityCreature creature, int chance, boolean checkSight, boolean onlyNearby, @Nullable final Predicate <? super T > targetSelector)
	{
		super(creature, checkSight, onlyNearby);
		this.targetChance = chance;
		this.theNearestAttackableTargetSorter = new EntityAINearestAttackableZombieExcludingPigman.Sorter(creature);
		this.setMutexBits(1);
		this.targetEntitySelector = new Predicate<T>()
		{
			public boolean apply(@Nullable T p_apply_1_)
			{
				return p_apply_1_ == null ? false : (targetSelector != null && !targetSelector.apply(p_apply_1_) ? false : (!EntitySelectors.NOT_SPECTATING.apply(p_apply_1_) ? false : EntityAINearestAttackableZombieExcludingPigman.this.isSuitableTarget(p_apply_1_, false)));
			}
		};
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
    @Override
	public boolean shouldExecute()
	{
		if (this.targetChance > 0 && this.taskOwner.getRNG().nextInt(this.targetChance) != 0)
		{
			return false;
		}
		else
		{   
			List<T> listOfZombieMobs = this.taskOwner.world.<T>getEntitiesWithinAABB((Class<T>) EntityZombie.class, this.getTargetableArea(this.getTargetDistance()), this.targetEntitySelector);

			if (listOfZombieMobs.isEmpty())
			{
				return false;
			}
			else
			{
				List<T> zombieTargets = new ArrayList<T>();
				
				for (T target : listOfZombieMobs) 
				{
					if (!(target instanceof EntityPigZombie)) 
					{
						zombieTargets.add(target);
					}
				}

				if (!zombieTargets.isEmpty())
				{
					Collections.sort(zombieTargets, this.theNearestAttackableTargetSorter);
					this.targetEntity = zombieTargets.get(0);
					return true;
				}
				
				return false;
			}
		}
	}

	protected AxisAlignedBB getTargetableArea(double targetDistance)
	{
		return this.taskOwner.getEntityBoundingBox().expand(targetDistance, 4.0D, targetDistance);
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
    @Override
	public void startExecuting()
	{
		this.taskOwner.setAttackTarget(this.targetEntity);
		super.startExecuting();
	}

	public static class Sorter implements Comparator<Entity>
	{
		private final Entity theEntity;

		public Sorter(Entity theEntityIn)
		{
			this.theEntity = theEntityIn;
		}

		public int compare(Entity p_compare_1_, Entity p_compare_2_)
		{
			double d0 = this.theEntity.getDistanceSq(p_compare_1_);
			double d1 = this.theEntity.getDistanceSq(p_compare_2_);
			return d0 < d1 ? -1 : (d0 > d1 ? 1 : 0);
		}
	}
}