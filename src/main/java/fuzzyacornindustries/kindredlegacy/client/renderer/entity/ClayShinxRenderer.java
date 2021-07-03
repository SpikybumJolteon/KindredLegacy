package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.ClayShinxGlowsRenderLayer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.ClayShinxModel;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.ClayShinxEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class ClayShinxRenderer<T extends ClayShinxEntity, M extends ClayShinxModel<T>> extends MobRenderer<T, ClayShinxModel<T>>
{
	public static final IRenderFactory<ClayShinxEntity> RENDER_FACTORY = manager -> new ClayShinxRenderer<>(manager);
	private static final ResourceLocation MAIN_TEXTURE = UtilityFunctions.location("textures/mobs/clay_shinx.png");

	public ClayShinxRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new ClayShinxModel<>(), 0.2F);
		this.addLayer(new ClayShinxGlowsRenderLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(ClayShinxEntity entity) 
	{
			return MAIN_TEXTURE;
	}
}