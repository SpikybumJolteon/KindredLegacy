package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.ClayLuxioGlowsRenderLayer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.ClayLuxioModel;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.ClayLuxioEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class ClayLuxioRenderer<T extends ClayLuxioEntity, M extends ClayLuxioModel<T>> extends MobRenderer<T, ClayLuxioModel<T>>
{
	public static final IRenderFactory<ClayLuxioEntity> RENDER_FACTORY = manager -> new ClayLuxioRenderer<>(manager);
	private static final ResourceLocation MAIN_TEXTURE = UtilityFunctions.location("textures/mobs/clay_luxio.png");

	public ClayLuxioRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new ClayLuxioModel<>(), 0.2F);
		this.addLayer(new ClayLuxioGlowsRenderLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(ClayLuxioEntity entity) 
	{
			return MAIN_TEXTURE;
	}
}