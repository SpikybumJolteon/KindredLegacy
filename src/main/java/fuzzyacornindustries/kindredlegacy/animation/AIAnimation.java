package fuzzyacornindustries.kindredlegacy.animation;

import java.util.EnumSet;

import fuzzyacornindustries.kindredlegacy.KindredLegacy;
import fuzzyacornindustries.kindredlegacy.network.ActionAnimationMessage;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraftforge.fml.network.PacketDistributor;

public abstract class AIAnimation extends Goal 
{	
	private IAnimatedEntity animatedEntity;
	private MobEntity mobEntity;
	//private int entityID;
	
	public AIAnimation(MobEntity mobEntity, IAnimatedEntity entity, int entityID, EnumSet<Goal.Flag> mutexFlag) 
	{
		animatedEntity = entity;
		this.mobEntity = mobEntity;
		//this.entityID = entityID;
		setMutexFlags(mutexFlag);
	}
	
	public abstract int getAnimationID();
	
	public <T extends LivingEntity> T getEntity() 
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
		//if(!isAutomatic()) KindredLegacyMain.sendAnimationPacket(mobEntity, animatedEntity, getAnimationID());
		if(!isAutomatic()) KindredLegacy.network.send(PacketDistributor.TRACKING_ENTITY.with(() -> mobEntity), new ActionAnimationMessage((byte)getAnimationID(), mobEntity.getEntityId()));
		//animatedEntity.setAnimationTick(getAnimationID());
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
		KindredLegacy.network.send(PacketDistributor.TRACKING_ENTITY.with(() -> mobEntity), new ActionAnimationMessage((byte)0, mobEntity.getEntityId()));
		animatedEntity.setAnimationID(0);
		//KindredLegacyMain.sendAnimationPacket(mobEntity, animatedEntity, 0);
	}
}