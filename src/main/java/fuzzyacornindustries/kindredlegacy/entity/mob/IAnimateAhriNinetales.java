package fuzzyacornindustries.kindredlegacy.entity.mob;

import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;

public interface IAnimateAhriNinetales 
{
	public IdleAnimationClock getIdleAnimationClockBody();
	public IdleAnimationClock getIdleAnimationClockArmRt();
	public IdleAnimationClock getIdleAnimationClockTails(int partNumber);
	public IdleAnimationClock getIdleAnimationClockOrb();
}