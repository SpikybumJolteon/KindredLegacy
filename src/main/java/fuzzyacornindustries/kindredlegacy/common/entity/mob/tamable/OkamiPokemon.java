package fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable;

import fuzzyacornindustries.kindredlegacy.client.animation.IdleAnimationClock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class OkamiPokemon extends TamablePokemon implements IRangedAttackMob
{
	public IdleAnimationClock bodyIdleAnimationClock;

	public OkamiPokemon(EntityType<? extends OkamiPokemon> type, World world)
	{
		super(type, world);
	}
	
	@Override
	public void attackEntityWithRangedAttack(LivingEntity entityLivingBasePar, float distanceFactor) 
	{
		
	}

	public IdleAnimationClock getIdleAnimationClockBody()
	{
		return bodyIdleAnimationClock;
	}
}