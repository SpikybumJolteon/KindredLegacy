package fuzzyacornindustries.kindredlegacy.utility;

import fuzzyacornindustries.kindredlegacy.KindredLegacy;
import net.minecraft.util.ResourceLocation;

public class UtilityFunctions 
{
	public static ResourceLocation location(String name)
	{
		return new ResourceLocation(KindredLegacy.modid, name);
	}
}