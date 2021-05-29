package fuzzyacornindustries.kindredlegacy.client.animation;

import java.util.EnumSet;

import fuzzyacornindustries.kindredlegacy.common.network.ActionAnimationMessage;
import fuzzyacornindustries.kindredlegacy.common.network.NetworkHandler;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraftforge.fml.network.PacketDistributor;

public abstract class AIAnimation<T extends IAnimatedEntity> extends Goal 
{	
	private IAnimatedEntity animatedEntity;
	private MobEntity mobEntity;
	
	public AIAnimation(MobEntity mobEntity, IAnimatedEntity entity, int entityID, EnumSet<Goal.Flag> mutexFlag) 
	{
		animatedEntity = entity;
		this.mobEntity = mobEntity;
		setMutexFlags(mutexFlag);
	}
	
	public abstract int getAnimationID();
	
	public IAnimatedEntity getEntity() 
	{
		return animatedEntity;
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
		if(!isAutomatic()) NetworkHandler.sendToTarget(new ActionAnimationMessage((byte)getAnimationID(), mobEntity.getEntityId()), PacketDistributor.TRACKING_ENTITY.with(() -> mobEntity));
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
		NetworkHandler.sendToTarget(new ActionAnimationMessage((byte)0, mobEntity.getEntityId()), PacketDistributor.TRACKING_ENTITY.with(() -> mobEntity));		
		animatedEntity.setAnimationID(0);
	}
}