package fuzzyacornindustries.kindredlegacy.common.core;

import fuzzyacornindustries.kindredlegacy.common.tileentity.VerdantPurifierTileEntity;
import fuzzyacornindustries.kindredlegacy.common.tileentity.XelNagaPylonTileEntity;
import fuzzyacornindustries.kindredlegacy.lib.Names;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities 
{
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Names.MOD_ID);
	
	public static final RegistryObject<TileEntityType<XelNagaPylonTileEntity>> XELNAGA_PYLON
    = TILE_ENTITIES.register("xelnaga_pylon", () -> TileEntityType.Builder.create(XelNagaPylonTileEntity::new, ModBlocks.XELNAGA_PYLON.get()).build(null));
	public static final RegistryObject<TileEntityType<VerdantPurifierTileEntity>> VERDANT_PURIFIER
    = TILE_ENTITIES.register("verdant_purifier", () -> TileEntityType.Builder.create(VerdantPurifierTileEntity::new, ModBlocks.VERDANT_PURIFIER.get()).build(null));
}