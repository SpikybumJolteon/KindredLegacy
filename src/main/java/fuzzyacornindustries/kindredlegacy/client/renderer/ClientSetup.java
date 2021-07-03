package fuzzyacornindustries.kindredlegacy.client.renderer;

import fuzzyacornindustries.kindredlegacy.client.gui.VerdantPurifierGui;
import fuzzyacornindustries.kindredlegacy.client.particle.ConfuseParticle;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.ArmoredLuxrayRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.ArmoredShinxRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.BandersnatchFennekinRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.ClayCommanderDelcattyRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.ClayEspurrRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.ClayLuxioRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.ClayPurrloinRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.ClayShinxRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.ClaySkittyRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.CracklingNincadaRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.FeywoodAbsolRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.FirecrackerLittenRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.InfestedDeerlingRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.MuBunearyRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.OkamiEspeonRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.OkamiSylveonRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.OkamiUmbreonRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.RaptorZerglingNincadaRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.VastayaNinetalesRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.VoorstMightyenaRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.ZerglingNincadaRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.projectile.FireworkMissileRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.projectile.VastayaFireballRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.tileentity.VerdantPurifierRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.tileentity.XelNagaPylonRenderer;
import fuzzyacornindustries.kindredlegacy.common.core.ModBlocks;
import fuzzyacornindustries.kindredlegacy.common.core.ModContainers;
import fuzzyacornindustries.kindredlegacy.common.core.ModEntities;
import fuzzyacornindustries.kindredlegacy.common.core.ModParticleTypes;
import fuzzyacornindustries.kindredlegacy.common.core.ModTileEntities;
import fuzzyacornindustries.kindredlegacy.lib.Names;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Names.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup 
{
	public static void init() 
	{
		setBlockRenderLayers();

        registerEntityRenderers();
        registerTileEntityRenders();
        registerScreenFactories();
    }

    private static void setBlockRenderLayers() 
    {
    	RenderTypeLookup.setRenderLayer(ModBlocks.ORANIAN_BERRIES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.VERDANT_PURIFIER.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.XELNAGA_PYLON.get(), RenderType.getCutout());
    }
    
	public static void registerEntityRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.ARMORED_LUXRAY.get(), ArmoredLuxrayRenderer.RENDER_FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.ARMORED_SHINX.get(), ArmoredShinxRenderer.RENDER_FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.BANDERSNATCH_FENNEKIN.get(), BandersnatchFennekinRenderer.RENDER_FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.CLAY_COMMANDER_DELCATTY.get(), ClayCommanderDelcattyRenderer.RENDER_FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.CLAY_ESPURR.get(), ClayEspurrRenderer.RENDER_FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.CLAY_LUXIO.get(), ClayLuxioRenderer.RENDER_FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.CLAY_PURRLOIN.get(), ClayPurrloinRenderer.RENDER_FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.CLAY_SHINX.get(), ClayShinxRenderer.RENDER_FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.CLAY_SKITTY.get(), ClaySkittyRenderer.RENDER_FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.CRACKLING_NINCADA.get(), CracklingNincadaRenderer.RENDER_FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.FEYWOOD_ABSOL.get(), FeywoodAbsolRenderer.RENDER_FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.FIRECRACKER_LITTEN.get(), FirecrackerLittenRenderer.RENDER_FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.INFESTED_DEERLING.get(), InfestedDeerlingRenderer.RENDER_FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.MU_BUNEARY.get(), MuBunearyRenderer.RENDER_FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.OKAMI_ESPEON.get(), OkamiEspeonRenderer.RENDER_FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.OKAMI_SYLVEON.get(), OkamiSylveonRenderer.RENDER_FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.OKAMI_UMBREON.get(), OkamiUmbreonRenderer.RENDER_FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.RAPTOR_ZERGLING_NINCADA.get(), RaptorZerglingNincadaRenderer.RENDER_FACTORY);
//		RenderingRegistry.registerEntityRenderingHandler(ModEntities.SNOW_SORCERESS_BRAIXEN.get(), new SnowSorceressBraixenRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.VASTAYA_NINETALES.get(), VastayaNinetalesRenderer.RENDER_FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.VOORST_MIGHTYENA.get(), VoorstMightyenaRenderer.RENDER_FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.ZERGLING_NINCADA.get(), ZerglingNincadaRenderer.RENDER_FACTORY);

		RenderingRegistry.registerEntityRenderingHandler(ModEntities.FIREWORK_MISSILE.get(), FireworkMissileRenderer.RENDER_FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.VASTAYA_FIREBALL.get(), VastayaFireballRenderer.RENDER_FACTORY);
	}

	public static void registerTileEntityRenders()
	{
		ClientRegistry.bindTileEntityRenderer(ModTileEntities.XELNAGA_PYLON.get(), XelNagaPylonRenderer::new);
		ClientRegistry.bindTileEntityRenderer(ModTileEntities.VERDANT_PURIFIER.get(), VerdantPurifierRenderer::new);
	}
	
	private static void registerScreenFactories() 
	{
        ScreenManager.registerFactory(ModContainers.VERDANT_PURIFIER.get(), VerdantPurifierGui::new);
	}
	
	@SubscribeEvent
    public static void registerParticleFactories(ParticleFactoryRegisterEvent event) 
	{
        Minecraft.getInstance().particles.registerFactory(ModParticleTypes.CONFUSE_PARTICLE.get(), ConfuseParticle.Factory::new);
    }
}