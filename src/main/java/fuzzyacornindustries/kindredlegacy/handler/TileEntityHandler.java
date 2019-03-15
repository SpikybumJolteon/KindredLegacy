package fuzzyacornindustries.kindredlegacy.handler;

import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import fuzzyacornindustries.kindredlegacy.tileentity.TileEntityAlchemenisizer;
import fuzzyacornindustries.kindredlegacy.tileentity.TileEntityVerdantPurifier;
import fuzzyacornindustries.kindredlegacy.tileentity.TileEntityVespeneCondenser;
import fuzzyacornindustries.kindredlegacy.tileentity.TileEntityXelNagaPylon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler 
{
	public static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityAlchemenisizer.class, new ResourceLocation(ModInfo.MOD_ID, "alchemenisizer"));
		GameRegistry.registerTileEntity(TileEntityXelNagaPylon.class, new ResourceLocation(ModInfo.MOD_ID, "xelnaga_pylon"));
		GameRegistry.registerTileEntity(TileEntityVespeneCondenser.class, new ResourceLocation(ModInfo.MOD_ID, "vespene_condenser"));
		GameRegistry.registerTileEntity(TileEntityVerdantPurifier.class, new ResourceLocation(ModInfo.MOD_ID, "verdant_purifier"));
	}
}