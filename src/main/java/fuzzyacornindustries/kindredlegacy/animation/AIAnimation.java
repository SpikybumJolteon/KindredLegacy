package fuzzyacornindustries.kindredlegacy.animation;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;

public abstract class AIAnimation extends EntityAIBase 
{	
	private IAnimatedEntity animatedEntity;
	
	public AIAnimation(IAnimatedEntity entity, int mutexBits) 
	{
		animatedEntity = entity;
		setMutexBits(mutexBits);
	}
	
	public abstract int getAnimationID();
	
	public <T extends EntityLiving> T getEntity() 
	{
		return (T)animatedEntity;
	}
	
	public abstract boolean isAutomatic();
	
	public abstract int getDuration();
	
	public boolean shouldAnimate() 
	{
		return false;
	}

	@Override
	public boolean shouldExecute() 
	{
		if(isAutomatic()) return animatedEntity.getAnimationID() == getAnimationID();
		return shouldAnimate();
	}

	@Override
	public void startExecuting() 
	{
		if(!isAutomatic()) KindredLegacyMain.sendAnimationPacket(animatedEntity, getAnimationID());
		animatedEntity.setAnimationTick(0);
	}

	@Override
	public boolean shouldContinueExecuting() 
	{
		return animatedEntity.getAnimationTick() < getDuration();
	}

	@Override
	public void resetTask() 
	{
		KindredLegacyMain.sendAnimationPacket(animatedEntity, 0);
	}
}