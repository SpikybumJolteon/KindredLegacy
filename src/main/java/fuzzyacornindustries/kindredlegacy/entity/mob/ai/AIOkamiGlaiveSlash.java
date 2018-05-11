package fuzzyacornindustries.kindredlegacy.entity.mob.ai;

import fuzzyacornindustries.kindredlegacy.animation.AIAnimation;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.OkamiPokemon;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryOkamiPokemonAttackID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public class AIOkamiGlaiveSlash extends AIAnimation 
{
	private OkamiPokemon entityOkamiPokemon;
	private EntityLivingBase attackTarget;

	//public float attackPower = 0.0F;
	public float range;

	/*
	 * For some reason, the tick read in the model class lags behind the AI tick.
	 * Only known fix for now is to make the attack duration longer than end animation.
	 */
	public int attackDuration = 9;

	public AIOkamiGlaiveSlash(OkamiPokemon entityOkamiPokemon, float range)
	{
		super(entityOkamiPokemon, 3);
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
	public void updateTask() 
	{	
		if(attackTarget != null)
		{
			entityOkamiPokemon.getLookHelper().setLookPositionWithEntity(attackTarget, 30F, 30F);

			if(entityOkamiPokemon.getAnimationTick() == 2 && attackTarget != null && entityOkamiPokemon.getDistanceSq(this.attackTarget.posX, this.attackTarget.getEntityBoundingBox().minY, this.attackTarget.posZ) <= range + 0.5D)
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

				//System.out.println( "Test Entity Detected By Kill Order" );
				//System.out.println( Integer.toString( entityOkamiPokemon.getAttackTarget().getEntityId() ) );
			}
		}
	}
}