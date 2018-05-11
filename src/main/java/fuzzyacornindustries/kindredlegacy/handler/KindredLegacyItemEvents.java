package fuzzyacornindustries.kindredlegacy.handler;

import fuzzyacornindustries.kindredlegacy.block.KindredLegacyBlocks;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import fuzzyacornindustries.kindredlegacy.utility.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class KindredLegacyItemEvents 
{
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{	
		event.getRegistry().registerAll(KindredLegacyItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{	
		event.getRegistry().registerAll(KindredLegacyBlocks.BLOCKS.toArray(new Block[0]));
	}

	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for(Item item : KindredLegacyItems.ITEMS)
		{
			if(item instanceof IHasModel)
			{
				((IHasModel)item).registerModels();
			}
		}

		for(Block block : KindredLegacyBlocks.BLOCKS)
		{
			if(block instanceof IHasModel)
			{
				((IHasModel)block).registerModels();
			}
		}
	}
	
	@SubscribeEvent
	public void fuelTime(FurnaceFuelBurnTimeEvent event) 
	{
		int timeToSmeltAnItem = 200;
		
		if(event.getItemStack().getItem() == KindredLegacyItems.INFERNO_FUEL_ROD)
		{
			event.setBurnTime(timeToSmeltAnItem * 16); // 2x Coal
		}
	}
}