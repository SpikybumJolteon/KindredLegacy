package fuzzyacornindustries.kindredlegacy.common.entity.mob;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public interface IMobMotionTracker 
{
	@OnlyIn(Dist.CLIENT)
	public float getAngularVelocity();

	@OnlyIn(Dist.CLIENT)
	public float getHeightVelocity();

	@OnlyIn(Dist.CLIENT)
	public float getTotalAngularChange();
}