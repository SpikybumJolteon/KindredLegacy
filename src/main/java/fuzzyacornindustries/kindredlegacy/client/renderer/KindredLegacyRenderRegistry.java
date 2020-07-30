package fuzzyacornindustries.kindredlegacy.client.renderer;

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
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.SnowSorceressBraixenRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.VoorstMightyenaRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.ZerglingNincadaRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.projectile.FireworkMissileRenderer;
import fuzzyacornindustries.kindredlegacy.client.renderer.tileentity.XelNagaPylonRenderer;
import fuzzyacornindustries.kindredlegacy.lists.KindredLegacyEntities;
import fuzzyacornindustries.kindredlegacy.lists.KindredLegacyTileEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class KindredLegacyRenderRegistry 
{
	public static void registryEntityRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.ARMORED_LUXRAY, new ArmoredLuxrayRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.ARMORED_SHINX, new ArmoredShinxRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.BANDERSNATCH_FENNEKIN, new BandersnatchFennekinRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.CLAY_COMMANDER_DELCATTY, new ClayCommanderDelcattyRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.CLAY_ESPURR, new ClayEspurrRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.CLAY_LUXIO, new ClayLuxioRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.CLAY_PURRLOIN, new ClayPurrloinRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.CLAY_SHINX, new ClayShinxRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.CLAY_SKITTY, new ClaySkittyRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.CRACKLING_NINCADA, new CracklingNincadaRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.FEYWOOD_ABSOL, new FeywoodAbsolRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.FIRECRACKER_LITTEN, new FirecrackerLittenRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.INFESTED_DEERLING, new InfestedDeerlingRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.MU_BUNEARY, new MuBunearyRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.OKAMI_ESPEON, new OkamiEspeonRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.OKAMI_SYLVEON, new OkamiSylveonRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.OKAMI_UMBREON, new OkamiUmbreonRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.RAPTOR_ZERGLING_NINCADA, new RaptorZerglingNincadaRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.SNOW_SORCERESS_BRAIXEN, new SnowSorceressBraixenRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.VOORST_MIGHTYENA, new VoorstMightyenaRenderer.RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.ZERGLING_NINCADA, new ZerglingNincadaRenderer.RenderFactory());

		//RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.FIREWORK_MISSILE, new FireworkMissileRenderer.RenderFactory());
		//RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.FIREWORK_MISSILE, renderManager -> new FireworkMissileRenderer(renderManager));
		RenderingRegistry.registerEntityRenderingHandler(KindredLegacyEntities.FIREWORK_MISSILE, new FireworkMissileRenderer.RenderFactory());
	}

	public static void registryTileEntityRenders()
	{
		ClientRegistry.bindTileEntityRenderer(KindredLegacyTileEntities.XELNAGA_PYLON, XelNagaPylonRenderer::new);
	}
}