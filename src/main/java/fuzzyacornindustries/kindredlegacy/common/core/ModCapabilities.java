package fuzzyacornindustries.kindredlegacy.common.core;

import fuzzyacornindustries.kindredlegacy.common.capability.InventoryRestoreCapability;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModCapabilities 
{
	@SubscribeEvent
	public static void registerCapabilities(final FMLCommonSetupEvent event) 
	{
		InventoryRestoreCapability.register();
	}
}