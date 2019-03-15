package fuzzyacornindustries.kindredlegacy.handler;

import fuzzyacornindustries.kindredlegacy.block.KindredLegacyBlocks;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import fuzzyacornindustries.kindredlegacy.tileentity.TileEntityVespeneCondenser;
import fuzzyacornindustries.kindredlegacy.tileentity.TileEntityXelNagaPylon;
import fuzzyacornindustries.kindredlegacy.utility.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class KindredLegacyItemEvents 
{	
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