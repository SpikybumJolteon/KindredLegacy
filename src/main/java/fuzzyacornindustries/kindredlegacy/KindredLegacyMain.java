package fuzzyacornindustries.kindredlegacy;

import fuzzyacornindustries.kindredlegacy.handler.*;
import fuzzyacornindustries.kindredlegacy.network.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.KindredLegacyEntities;
import fuzzyacornindustries.kindredlegacy.recipe.CraftingManager;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, version = ModInfo.MOD_VERSION)//, dependencies = "required-after:galacticraftcore;")
public class KindredLegacyMain 
{
	@SidedProxy(clientSide = ModInfo.CLIENT_PROXY, serverSide = ModInfo.COMMON_PROXY)
	public static CommonProxy proxy;

	public static SimpleNetworkWrapper network;

	@Instance(value = ModInfo.MOD_ID)
	public static KindredLegacyMain instance;

	/** Whether mods are loaded */
	public static boolean isBiomesOPlentyEnabled;
	public static boolean isGalacticraftEnabled;

	public static final int packetIDActionAnimation = 0;
	public static final int packetIDBowOfKindredModeSwitch = 1;
	public static final int packetIDBowOfKindredAutoFire = 2;

	public static final String[] fTimer;

	static 
	{
		// C:\Users\-username-\.gradle\caches\minecraft\de\oceanlabs\mcp\mcp_stable\20
		fTimer = new String[] {"field_71428_T", "timer"};
	}
	/*
	@Instance(ModInfo.MOD_ID)
	public static KindredLegacyMain modInstance;
	 */
	public NetworkHelper networkHelper;

	public static final Logger logger = LogManager.getLogger(ModInfo.MOD_ID);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ConfigHandler.preInit(event);

		isBiomesOPlentyEnabled = Loader.isModLoaded("biomesoplenty");
		isGalacticraftEnabled = Loader.isModLoaded("galacticraftcore");

		KindredLegacySoundEvents.registerSounds();

		networkHelper = new NetworkHelper(ModInfo.CHANNEL2, PoketamableNamePacket.class);

		KindredLegacyEntities.registerEntityMob();
		KindredLegacyEntities.registerEntityAbilities();
		KindredLegacyEntities.registerEntityProjectiles();

		proxy.preInit();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.registerRenderers();

		network = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.CHANNEL);
		network.registerMessage(PacketAnimation.Handler.class, PacketAnimation.class, packetIDActionAnimation, Side.CLIENT);
		network.registerMessage(BowOfKindredModeSwitch.Handler.class, BowOfKindredModeSwitch.class, packetIDBowOfKindredModeSwitch, Side.SERVER);
		network.registerMessage(BowOfKindredAutoFire.Handler.class, BowOfKindredAutoFire.class, packetIDBowOfKindredAutoFire, Side.SERVER);

		MinecraftForge.EVENT_BUS.register(new KindredLegacyEntityEvents());
		MinecraftForge.EVENT_BUS.register(new KindredLegacyItemEvents());
		MinecraftForge.EVENT_BUS.register(new BowOfKindredEvents());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.initTimer();

		CraftingManager.registerRecipes();

		KindredLegacyEntities.addSpawnLocations();
		
		if (isGalacticraftEnabled) 
		{
			KindredLegacyEntities.setGalacticraftSpawns();
			
			MinecraftForge.EVENT_BUS.register(new GalacticraftEvents());
		}
	}

	public static boolean isClient() 
	{
		return FMLCommonHandler.instance().getSide().isClient();
	}

	public static boolean isEffectiveClient() 
	{
		return FMLCommonHandler.instance().getEffectiveSide().isClient();
	}

	public static void sendAnimationPacket(IAnimatedEntity entity, int animationID) 
	{
		if(isEffectiveClient()) 
			return;

		entity.setAnimationID(animationID);
		network.sendToAll(new PacketAnimation((byte)animationID, ((Entity)entity).getEntityId()));
	}
}