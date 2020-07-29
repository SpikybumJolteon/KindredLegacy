package fuzzyacornindustries.kindredlegacy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fuzzyacornindustries.kindredlegacy.capabilities.InventoryRestoreCapability;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.client.renderer.KindredLegacyRenderRegistry;
import fuzzyacornindustries.kindredlegacy.handler.ConfigHandler;
import fuzzyacornindustries.kindredlegacy.handler.KindredLegacyEntityEvents;
import fuzzyacornindustries.kindredlegacy.lists.KindredLegacyBlocks;
import fuzzyacornindustries.kindredlegacy.lists.KindredLegacyEntities;
import fuzzyacornindustries.kindredlegacy.lists.KindredLegacyItems;
import fuzzyacornindustries.kindredlegacy.network.ClientProxy;
import fuzzyacornindustries.kindredlegacy.network.IProxy;
import fuzzyacornindustries.kindredlegacy.network.KindredLegacyNetwork;
import fuzzyacornindustries.kindredlegacy.network.ServerProxy;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.network.simple.SimpleChannel;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ModInfo.MOD_ID)
public class KindredLegacy
{
	public static KindredLegacy instance;
	public static final String modid = ModInfo.MOD_ID;

	public static final SimpleChannel network = KindredLegacyNetwork.getNetworkChannel();
	public static final IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

	// Directly reference a log4j logger.
	public static final Logger LOGGER = LogManager.getLogger();

	public static final String[] fTimer;

	static
	{
		// C:\Users\-username-\.gradle\caches\minecraft\de\oceanlabs\mcp\mcp_stable\20
		fTimer = new String[] {"field_71428_T", "timer"};
	}

	public KindredLegacy() 
	{		
		instance = this;

		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ConfigHandler.SERVER_CONFIG);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigHandler.CLIENT_CONFIG);

		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		// Register the enqueueIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		// Register the processIMC method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
		// Register the doClientStuff method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientSetup);

		ConfigHandler.loadConfig(ConfigHandler.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve("kindredlegacy-server.toml").toString());
		ConfigHandler.loadConfig(ConfigHandler.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("kindredlegacy-client.toml").toString());

		// Register ourselves for server and other game events we are interested in
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new KindredLegacyEntityEvents());
		MinecraftForge.EVENT_BUS.register(new InventoryRestoreCapability());
	}

	private void setup(final FMLCommonSetupEvent event)
	{
		//ConfigHandler.preInit(event);

		//isBiomesOPlentyEnabled = Loader.isModLoaded("biomesoplenty");
		//isGalacticraftEnabled = Loader.isModLoaded("galacticraftcore");
		//isTwilightForestEnabled = Loader.isModLoaded("twilightforest");

		//networkPoketamableName = new NetworkHelper(ModInfo.CHANNEL2, PoketamableNamePacket.class);
		//networkExplorationKit = new NetworkHelper(ModInfo.CHANNEL_POKEMON_EXPLORATION_KIT, PokemonExplorationKitPacket.class);

		//RegistryHandler.preInitRegistries();

		//proxy.preInit();

		proxy.initTimer();
	}

	private void doClientSetup(final FMLClientSetupEvent event) 
	{
		KindredLegacyRenderRegistry.registryEntityRenders();

		RenderTypeLookup.setRenderLayer(KindredLegacyBlocks.ORANIAN_BERRIES, RenderType.getCutout());

		LOGGER.info("ClientSetup method registered");
	}

	private void enqueueIMC(final InterModEnqueueEvent event)
	{
		// some example code to dispatch IMC to another mod
		//InterModComms.sendTo("kindredlegacy", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
	}

	private void processIMC(final InterModProcessEvent event)
	{
		// some example code to receive and process InterModComms from other mods
		//LOGGER.info("Got IMC {}", event.getIMCStream().
		//        map(m->m.getMessageSupplier().get()).
		//        collect(Collectors.toList()));
	}
	// You can use SubscribeEvent and let the Event Bus discover methods to call
	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) 
	{
		// do something when the server starts
		//LOGGER.info("HELLO from server starting");
	}

	// You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
	// Event bus for receiving Registry Events)
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents 
	{
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event)
		{
			event.getRegistry().registerAll(KindredLegacyItems.ITEMS.toArray(new Item[0]));

			KindredLegacyEntities.registerEntitySpawnEggs(event);

			LOGGER.info("Items registered");
		}

		@SubscribeEvent
		public static void registryBlocks(final RegistryEvent.Register<Block> event) 
		{
			event.getRegistry().registerAll(KindredLegacyBlocks.BLOCKS.toArray(new Block[0]));

			LOGGER.info("Blocks registered");
		}

		@SubscribeEvent
		public static void registryEntities(final RegistryEvent.Register<EntityType<?>> event) 
		{
			event.getRegistry().registerAll(
					KindredLegacyEntities.ARMORED_LUXRAY,
					KindredLegacyEntities.ARMORED_SHINX,
					KindredLegacyEntities.BANDERSNATCH_FENNEKIN,
					KindredLegacyEntities.CLAY_COMMANDER_DELCATTY,
					KindredLegacyEntities.CLAY_ESPURR,
					KindredLegacyEntities.CLAY_LUXIO,
					KindredLegacyEntities.CLAY_PURRLOIN,
					KindredLegacyEntities.CLAY_SHINX,
					KindredLegacyEntities.CLAY_SKITTY,
					KindredLegacyEntities.CRACKLING_NINCADA,
					KindredLegacyEntities.FEYWOOD_ABSOL,
					KindredLegacyEntities.FIRECRACKER_LITTEN,
					KindredLegacyEntities.INFESTED_DEERLING,
					KindredLegacyEntities.MU_BUNEARY,
					KindredLegacyEntities.OKAMI_ESPEON,
					KindredLegacyEntities.OKAMI_SYLVEON,
					KindredLegacyEntities.OKAMI_UMBREON,
					KindredLegacyEntities.RAPTOR_ZERGLING_NINCADA,
					KindredLegacyEntities.SNOW_SORCERESS_BRAIXEN,
					KindredLegacyEntities.VOORST_MIGHTYENA,
					KindredLegacyEntities.ZERGLING_NINCADA,
					KindredLegacyEntities.FIREWORK_MISSILE
					);

			KindredLegacyEntities.registerEntityWorldSpawns();
			
			LOGGER.info("Entities registered");
		}

		@SubscribeEvent
		public static void registerSounds(final RegistryEvent.Register<SoundEvent> event) 
		{
			KindredLegacySoundEvents.registerSounds();
		}
		
		@SubscribeEvent
		public static void registerCapabilities(final FMLCommonSetupEvent event) 
		{
			InventoryRestoreCapability.register();
		}

		//		@SubscribeEvent
		//	    public void registerRecipeSerialziers(RegistryEvent.Register<IRecipeSerializer<?>> event)
		//	    {
		//			CraftingHelper.register(new ResourceLocation(KindredLegacy.modid, "potion"), PotionIngredient.Serializer.INSTANCE);
		//	    }
	}

//	public static void registerCapabilities()
//	{
//		CapabilityManager.INSTANCE.register(IInventoryBackup.class, new IInventoryBackupStorage(), new CapabilityFactory());
//	}
//
//	private static class CapabilityFactory implements Callable<IInventoryBackup> 
//	{
//		@Override
//		public IInventoryBackup call() throws Exception 
//		{
//			return new DefaultInventoryBackup();
//		}
//	}
}