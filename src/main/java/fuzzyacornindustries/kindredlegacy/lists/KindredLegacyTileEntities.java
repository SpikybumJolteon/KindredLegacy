package fuzzyacornindustries.kindredlegacy.lists;

import fuzzyacornindustries.kindredlegacy.tileentity.XelNagaPylonTileEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.tileentity.TileEntityType;

public class KindredLegacyTileEntities 
{
	public static TileEntityType<XelNagaPylonTileEntity> XELNAGA_PYLON = (TileEntityType<XelNagaPylonTileEntity>) TileEntityType.Builder.create(XelNagaPylonTileEntity::new, KindredLegacyBlocks.XELNAGA_PYLON).build(null).setRegistryName(UtilityFunctions.location("xelnaga_pylon"));
	
}