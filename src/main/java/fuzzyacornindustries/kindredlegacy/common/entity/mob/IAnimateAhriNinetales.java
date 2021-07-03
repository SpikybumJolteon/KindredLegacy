package fuzzyacornindustries.kindredlegacy.common.entity.mob;

import fuzzyacornindustries.kindredlegacy.client.animation.IdleAnimationClock;

public interface IAnimateAhriNinetales 
{
	public IdleAnimationClock getIdleAnimationClockBody();
	public IdleAnimationClock getIdleAnimationClockArmRt();
	public IdleAnimationClock getIdleAnimationClockTails(int partNumber);
	public IdleAnimationClock getIdleAnimationClockOrb();
}