package fuzzyacornindustries.kindredlegacy.common.entity.mob.ai;

import java.util.EnumSet;

import fuzzyacornindustries.kindredlegacy.client.animation.AIAnimation;
import fuzzyacornindustries.kindredlegacy.client.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.OkamiPokemon;
import fuzzyacornindustries.kindredlegacy.lib.action.LibraryOkamiPokemonAttackID;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.DamageSource;

public class OkamiGlaiveSlashGoal extends AIAnimation<IAnimatedEntity> 
{
	private OkamiPokemon entityOkamiPokemon;
	private LivingEntity attackTarget;

	private final float range;

	/*
	 * For some reason, the tick read in the model class lags behind the AI tick.
	 * Only known fix for now is to make the attack duration longer than end animation.
	 */
	public int attackDuration = 9;

	public OkamiGlaiveSlashGoal(OkamiPokemon entityOkamiPokemon, float range)
	{
		super(entityOkamiPokemon, entityOkamiPokemon, entityOkamiPokemon.getEntityId(), EnumSet.of(Goal.Flag.MOVE));
		this.entityOkamiPokemon = entityOkamiPokemon;
		attackTarget = null;
		this.range = range * range;
	}

    @Override
	public int getAnimationID() 
	{
		return LibraryOkamiPokemonAttackID.GLAIVE_SLASH;
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
		attackTarget = entityOkamiPokemon.getAttackTarget();
	}

    @Override
	public void tick() 
    {
		if(attackTarget != null)
		{
			entityOkamiPokemon.getLookController().setLookPositionWithEntity(attackTarget, 30F, 30F);

			if(entityOkamiPokemon.getAnimationTick() == 2 && entityOkamiPokemon.getDistanceSq(this.attackTarget.getPosX(), this.attackTarget.getPosYHeight(0.5), this.attackTarget.getPosZ()) <= range + 0.5D)
			{
				if(entityOkamiPokemon.isTamed())
				{
					if(attackTarget.getHealth() <= entityOkamiPokemon.getAttackPower())
					{
						attackTarget.attackEntityFrom(DamageSource.causeMobDamage(entityOkamiPokemon.getOwner()), entityOkamiPokemon.getAttackDamage());
					}
					else
					{
						attackTarget.attackEntityFrom(DamageSource.causeMobDamage(entityOkamiPokemon), entityOkamiPokemon.getAttackDamage());
					}
				}
				else
				{
					attackTarget.attackEntityFrom(DamageSource.causeMobDamage(entityOkamiPokemon), entityOkamiPokemon.getAttackDamage());
				}
			}
		}
	}
}