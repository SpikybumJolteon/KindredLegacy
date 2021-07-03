package fuzzyacornindustries.kindredlegacy;

import fuzzyacornindustries.kindredlegacy.client.renderer.ClientSetup;
import fuzzyacornindustries.kindredlegacy.common.capability.InventoryRestoreCapability;
import fuzzyacornindustries.kindredlegacy.common.core.ModBlocks;
import fuzzyacornindustries.kindredlegacy.common.core.ModCapabilities;
import fuzzyacornindustries.kindredlegacy.common.core.ModContainers;
import fuzzyacornindustries.kindredlegacy.common.core.ModEntities;
import fuzzyacornindustries.kindredlegacy.common.core.ModItems;
import fuzzyacornindustries.kindredlegacy.common.core.ModParticleTypes;
import fuzzyacornindustries.kindredlegacy.common.core.ModRecipes;
import fuzzyacornindustries.kindredlegacy.common.core.ModSounds;
import fuzzyacornindustries.kindredlegacy.common.core.ModTileEntities;
import fuzzyacornindustries.kindredlegacy.common.handler.ConfigHandler;
import fuzzyacornindustries.kindredlegacy.common.handler.ModEntityEvents;
import fuzzyacornindustries.kindredlegacy.common.network.ClientProxy;
import fuzzyacornindustries.kindredlegacy.common.network.IProxy;
import fuzzyacornindustries.kindredlegacy.common.network.NetworkHandler;
import fuzzyacornindustries.kindredlegacy.common.network.ServerProxy;
import fuzzyacornindustries.kindredlegacy.lib.Names;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Names.MOD_ID)
public class KindredLegacy
{
	public static final IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

	public static final String[] fTimer;

	static
	{
		// C:\Users\-username-\.gradle\caches\minecraft\de\oceanlabs\mcp\mcp_stable\20
		fTimer = new String[] {"field_71428_T", "timer"};
	}

	public KindredLegacy() 
	{	
		IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            modBus.addListener(ClientHandler::clientSetup);
        });
		
		modBus.addListener(this::commonSetup);
		
		ModBlocks.BLOCKS.register(modBus);
		ModItems.ITEMS.register(modBus);
//	    ModFluids.FLUIDS.register(modBus);
	    ModSounds.SOUNDS.register(modBus);
	    ModTileEntities.TILE_ENTITIES.register(modBus);
	    ModEntities.ENTITIES.register(modBus);
	    ModContainers.CONTAINERS.register(modBus);
	    ModParticleTypes.PARTICLES.register(modBus);
	    ModRecipes.RECIPES.register(modBus);
//	    ModDecorators.DECORATORS.register(modBus);
//	    ModVillagers.POI.register(modBus);
//	    ModVillagers.PROFESSIONS.register(modBus);
		
		//instance = this;

		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ConfigHandler.SERVER_CONFIG);
		//ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigHandler.CLIENT_CONFIG);

		// Register the setup method for modloading
		//modBus.addListener(this::setup);
		// Register the enqueueIMC method for modloading
		//modBus.addListener(this::enqueueIMC);
		// Register the processIMC method for modloading
		//modBus.addListener(this::processIMC);
		// Register the doClientStuff method for modloading
		//modBus.addListener(this::doClientSetup);

		ConfigHandler.loadConfig(ConfigHandler.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve("kindredlegacy-server.toml").toString());
		//ConfigHandler.loadConfig(ConfigHandler.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("kindredlegacy-client.toml").toString());

		// Register ourselves for server and other game events we are interested in
		//MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new ModEntityEvents());
		MinecraftForge.EVENT_BUS.register(new ModCapabilities());
		MinecraftForge.EVENT_BUS.register(new InventoryRestoreCapability());
	}

	private void commonSetup(FMLCommonSetupEvent event)
	{
		NetworkHandler.init();
		//ConfigHandler.preInit(event);

		//isBiomesOPlentyEnabled = Loader.isModLoaded("biomesoplenty");
		//isGalacticraftEnabled = Loader.isModLoaded("galacticraftcore");
		//isTwilightForestEnabled = Loader.isModLoaded("twilightforest");

		//networkPoketamableName = new NetworkHelper(ModInfo.CHANNEL2, PoketamableNamePacket.class);
		//networkExplorationKit = new NetworkHelper(ModInfo.CHANNEL_POKEMON_EXPLORATION_KIT, PokemonExplorationKitPacket.class);

		//RegistryHandler.preInitRegistries();

		//proxy.preInit();

		ModEntities.registerEntityWorldSpawns();
		
		proxy.initTimer();
	}

	static class ClientHandler 
	{
        static void clientSetup(FMLClientSetupEvent event) 
        {
        	ClientSetup.init();
        }
    }
}