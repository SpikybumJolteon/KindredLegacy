package fuzzyacornindustries.kindredlegacy.utility;

import fuzzyacornindustries.kindredlegacy.lib.Names;
import net.minecraft.util.ResourceLocation;

public class UtilityFunctions 
{
	public static ResourceLocation location(String name)
	{
		return new ResourceLocation(Names.MOD_ID, name);
	}
}