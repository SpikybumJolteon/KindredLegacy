package fuzzyacornindustries.kindredlegacy.common.core;

import java.util.function.Function;
import java.util.function.Supplier;

import fuzzyacornindustries.kindredlegacy.common.block.OranianBerryBlock;
import fuzzyacornindustries.kindredlegacy.common.block.VerdantPurifierBlock;
import fuzzyacornindustries.kindredlegacy.common.block.XelNagaPylonBlock;
import fuzzyacornindustries.kindredlegacy.lib.Names;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks 
{
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Names.MOD_ID);
	public static final DeferredRegister<Item> ITEMS = ModItems.ITEMS;

	public static final RegistryObject<OranianBerryBlock> ORANIAN_BERRIES = registerNoItem("oranian_berry_plant",
			() -> new OranianBerryBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.CROP)));

	public static final RegistryObject<Block> PACKED_AURUM_DUST = register("packed_aurum_dust",
			() -> new Block(Block.Properties.create(Material.SAND).sound(SoundType.SAND).hardnessAndResistance(0.5F)));

	public static final RegistryObject<XelNagaPylonBlock> XELNAGA_PYLON = register("xelnaga_pylon", XelNagaPylonBlock::new);
	public static final RegistryObject<VerdantPurifierBlock> VERDANT_PURIFIER = register("verdant_purifier", VerdantPurifierBlock::new);
	/* -----------------------*/

	private static <T extends Block> RegistryObject<T> register(String name, Supplier<? extends T> sup) 
	{
		return register(name, sup, ModBlocks::itemDefault);
	}

	private static <T extends Block> RegistryObject<T> register(String name, Supplier<? extends T> sup, Function<RegistryObject<T>, Supplier<? extends Item>> itemCreator) 
	{
		RegistryObject<T> ret = registerNoItem(name, sup);
		ITEMS.register(name, itemCreator.apply(ret));
		return ret;
	}

	private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<? extends T> sup) 
	{
		return BLOCKS.register(name, sup);
	}

	private static Supplier<BlockItem> itemDefault(final RegistryObject<? extends Block> block) 
	{
		return item(block, ModItems.ItemGroups.KINDRED_LEGACY_CREATIVE_TAB);
	}

	private static Supplier<BlockItem> item(final RegistryObject<? extends Block> block, final ItemGroup itemGroup) 
	{
		return () -> new BlockItem(block.get(), new Item.Properties().group(itemGroup));
	}
}