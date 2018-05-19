package fuzzyacornindustries.kindredlegacy.entity.mob.ai;

import fuzzyacornindustries.kindredlegacy.animation.AIAnimation;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.HostilePokemon;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryUniversalAttackID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;

public class AIGeneralIgnite extends AIAnimation 
{
	private HostilePokemon entityAttacker;
	private EntityLivingBase attackTarget;

	public int attackDuration = 17;

	public AIGeneralIgnite(HostilePokemon entityAttacker) 
	{
		super(entityAttacker, 3);
		this.entityAttacker = entityAttacker;
		attackTarget = null;
	}

	public int getAnimationID() 
	{
		return LibraryUniversalAttackID.IGNITE;
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
		attackTarget = entityAttacker.getAttackTarget();
	}

	public void updateTask() 
	{	
		if(attackTarget != null)
		{
			entityAttacker.getLookHelper().setLookPositionWithEntity(attackTarget, 30F, 30F);

			if (!entityAttacker.world.isRemote)
			{
				if(entityAttacker.getAnimationTick() == 6)
				{
					if(attackTarget.getActivePotionEffect(MobEffects.FIRE_RESISTANCE) != null)
					{
						attackTarget.removePotionEffect(MobEffects.FIRE_RESISTANCE);
					}

					if(attackTarget.getActivePotionEffect(MobEffects.REGENERATION) != null)
					{
						attackTarget.removePotionEffect(MobEffects.REGENERATION);
					}

					if(attackTarget.getActivePotionEffect(MobEffects.RESISTANCE) != null)
					{
						attackTarget.removePotionEffect(MobEffects.RESISTANCE);
					}

					if(attackTarget.getActivePotionEffect(MobEffects.ABSORPTION) != null)
					{
						attackTarget.removePotionEffect(MobEffects.ABSORPTION);
					}
				}

				if(entityAttacker.getAnimationTick() == 8)
				{
					attackTarget.setFire(30);

					entityAttacker.playIgniteSound(attackTarget);
				}
			}
		}
	}
}