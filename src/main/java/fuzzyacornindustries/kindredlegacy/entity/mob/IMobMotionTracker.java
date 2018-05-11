package fuzzyacornindustries.kindredlegacy.entity.mob;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IMobMotionTracker 
{
	@SideOnly(Side.CLIENT)
	public float getAngularVelocity();

	@SideOnly(Side.CLIENT)
	public float getHeightVelocity();
}