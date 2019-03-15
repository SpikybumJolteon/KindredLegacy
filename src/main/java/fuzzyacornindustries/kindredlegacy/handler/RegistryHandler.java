package fuzzyacornindustries.kindredlegacy.handler;

import java.util.HashSet;
import java.util.Set;

import com.google.common.base.Preconditions;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.block.KindredLegacyBlocks;
import fuzzyacornindustries.kindredlegacy.capabilities.ModCapabilities;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.KindredLegacyEntities;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import fuzzyacornindustries.kindredlegacy.item.itemblock.ItemBlockXelNagaPylon;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import fuzzyacornindustries.kindredlegacy.tileentity.TileEntityXelNagaPylon;
import fuzzyacornindustries.kindredlegacy.utility.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber
public class RegistryHandler 
{
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

	public static void preInitRegistries()
	{
		ModCapabilities.registerCapabilities();

		KindredLegacyEntities.registerEntityMob();
		KindredLegacyEntities.registerEntityAbilities();
		KindredLegacyEntities.registerEntityProjectiles();
	}

	public static void initRegistries()
	{
		KindredLegacySoundEvents.registerSounds();

		NetworkRegistry.INSTANCE.registerGuiHandler(KindredLegacyMain.instance, new GuiHandler());;
	}

	public static void postInitRegistries() {}
}