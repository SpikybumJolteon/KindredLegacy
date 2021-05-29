package fuzzyacornindustries.kindredlegacy.client.renderer;

import fuzzyacornindustries.kindredlegacy.client.gui.VerdantPurifierGui;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.ArmoredShinxRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.FeywoodAbsolRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.tileentity.VerdantPurifierRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.tileentity.XelNagaPylonRenderer;
import fuzzyacornindustries.kindredlegacy.common.core.ModBlocks;
import fuzzyacornindustries.kindredlegacy.common.core.ModContainers;
import fuzzyacornindustries.kindredlegacy.common.core.ModEntities;
import fuzzyacornindustries.kindredlegacy.common.core.ModTileEntities;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class ModRenderRegistry 
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
//		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.ARMORED_LUXRAY.get(), new ArmoredLuxrayRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.ARMORED_SHINX.get(), ArmoredShinxRenderer.RENDER_FACTORY);
//		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.BANDERSNATCH_FENNEKIN.get(), new BandersnatchFennekinRenderer.RenderFactory());
//		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.CLAY_COMMANDER_DELCATTY.get(), new ClayCommanderDelcattyRenderer.RenderFactory());
//		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.CLAY_ESPURR.get(), new ClayEspurrRenderer.RenderFactory());
//		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.CLAY_LUXIO.get(), new ClayLuxioRenderer.RenderFactory());
//		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.CLAY_PURRLOIN.get(), new ClayPurrloinRenderer.RenderFactory());
//		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.CLAY_SHINX.get(), new ClayShinxRenderer.RenderFactory());
//		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.CLAY_SKITTY.get(), new ClaySkittyRenderer.RenderFactory());
//		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.CRACKLING_NINCADA.get(), new CracklingNincadaRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.FEYWOOD_ABSOL.get(), FeywoodAbsolRenderer.RENDER_FACTORY);
//		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.FIRECRACKER_LITTEN.get(), new FirecrackerLittenRenderer.RenderFactory());
//		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.INFESTED_DEERLING.get(), new InfestedDeerlingRenderer.RenderFactory());
//		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.MU_BUNEARY.get(), new MuBunearyRenderer.RenderFactory());
//		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.OKAMI_ESPEON.get(), new OkamiEspeonRenderer.RenderFactory());
//		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.OKAMI_SYLVEON.get(), new OkamiSylveonRenderer.RenderFactory());
//		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.OKAMI_UMBREON.get(), new OkamiUmbreonRenderer.RenderFactory());
//		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.RAPTOR_ZERGLING_NINCADA.get(), new RaptorZerglingNincadaRenderer.RenderFactory());
//		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.SNOW_SORCERESS_BRAIXEN.get(), new SnowSorceressBraixenRenderer.RenderFactory());
//		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.VOORST_MIGHTYENA.get(), new VoorstMightyenaRenderer.RenderFactory());
//		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.ZERGLING_NINCADA.get(), new ZerglingNincadaRenderer.RenderFactory());

		//RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.FIREWORK_MISSILE, new FireworkMissileRenderer.RenderFactory());
		//RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.FIREWORK_MISSILE, renderManager -> new FireworkMissileRenderer(renderManager));
		//RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.FIREWORK_MISSILE.get(), new FireworkMissileRenderer.RenderFactory());
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
}