package fuzzyacornindustries.kindredlegacy.entity.mob.tamable;

import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.world.World;

public class OkamiPokemon extends TamablePokemon implements IRangedAttackMob
{
	public IdleAnimationClock bodyIdleAnimationClock;

	public OkamiPokemon(World par1World)
	{
		super(par1World);
	}
	
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase entityLivingBasePar, float p_82196_2_) 
	{
		
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {}

	public IdleAnimationClock getIdleAnimationClockBody()
	{
		return bodyIdleAnimationClock;
	}
}