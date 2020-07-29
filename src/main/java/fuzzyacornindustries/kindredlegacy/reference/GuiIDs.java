package fuzzyacornindustries.kindredlegacy.reference;

import net.minecraft.util.ResourceLocation;

public class GuiIDs 
{
	/**
	 * IDs for {@link Screen} classes opened with {@link OpenClientScreenMessage}.
	 */
	public static class Client 
	{
		public static final ResourceLocation POKETAMABLE_NAME = id("poketamable_name");
	}

	private static ResourceLocation id(final String id) 
	{
		return new ResourceLocation(ModInfo.MOD_ID, id);
	}
}