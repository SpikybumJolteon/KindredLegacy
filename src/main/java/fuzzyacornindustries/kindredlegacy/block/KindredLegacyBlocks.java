package fuzzyacornindustries.kindredlegacy.block;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.base.Preconditions;

import fuzzyacornindustries.kindredlegacy.handler.TileEntityHandler;
import fuzzyacornindustries.kindredlegacy.item.itemblock.ItemBlockAlchemenisizer;
import fuzzyacornindustries.kindredlegacy.item.itemblock.ItemBlockVerdantPurifier;
import fuzzyacornindustries.kindredlegacy.item.itemblock.ItemBlockVespeneCondenser;
import fuzzyacornindustries.kindredlegacy.item.itemblock.ItemBlockXelNagaPylon;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class KindredLegacyBlocks 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();

	// MATERIALS TAB
	public static final Block PACKED_AURUM_DUST = new BlockBaseAdvanced("packed_aurum_dust", Material.SAND, SoundType.SAND).setHardness(0.5F);

	// SPECIAL BLOCKS
	public static final Block GUARDIAN_FIELD = new BlockGuardianField();

	// FARM BLOCKS
	public static final Block ORANIAN_BERRY_PLANT = new BlockOranianBerry("oranian_berry_plant");
	public static final Block PECHITA_BERRY_PLANT = new BlockPechitaBerry("pechita_berry_plant");

	// MYSTIC TECH BLOCKS
	public static final Block ALCHEMENISIZER = new BlockAlchemenisizer();
	public static final Block XELNAGA_PYLON = new BlockXelNagaPylon();
	public static final Block VERDANT_PURIFIER = new BlockVerdantPurifier();
	public static final Block VESPENE_CONDENSER = new BlockVespeneCondenser();

	public static final List<ItemBlock> ITEM_BLOCKS = new ArrayList<ItemBlock>();

	@Mod.EventBusSubscriber(modid = ModInfo.MOD_ID)
	public static class BlockRegistrationHandler 
	{
		public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<>();

		@SubscribeEvent
		public static void onBlockRegister(RegistryEvent.Register<Block> event)
		{	
			event.getRegistry().registerAll(BLOCKS.toArray(new Block[0]));

			TileEntityHandler.registerTileEntities();
		}

		/**
		 * Register this mod's {@link ItemBlock}s.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void registerItemBlocks(final RegistryEvent.Register<Item> event) 
		{
			final ItemBlock[] items = 
				{
						new ItemBlockAlchemenisizer(ALCHEMENISIZER),
						new ItemBlockVespeneCondenser(VESPENE_CONDENSER),
						new ItemBlockXelNagaPylon(XELNAGA_PYLON),
						new ItemBlockVerdantPurifier(VERDANT_PURIFIER),
				};

			final IForgeRegistry<Item> registry = event.getRegistry();

			for (final ItemBlock item : items) 
			{
				final Block block = item.getBlock();
				final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(), "Block %s has null registry name", block);
				registry.register(item.setRegistryName(registryName));
				ITEM_BLOCKS.add(item);
			}
		}
	}
}