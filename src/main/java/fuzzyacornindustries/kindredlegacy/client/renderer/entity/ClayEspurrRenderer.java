package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.ClayEspurrGlowsRenderLayer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.ClayEspurrModel;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.ClayEspurrEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class ClayEspurrRenderer<T extends ClayEspurrEntity, M extends ClayEspurrModel<T>> extends MobRenderer<T, ClayEspurrModel<T>>
{
	public static final IRenderFactory<ClayEspurrEntity> RENDER_FACTORY = manager -> new ClayEspurrRenderer<>(manager);
	private static final ResourceLocation MAIN_TEXTURE = UtilityFunctions.location("textures/mobs/clay_espurr.png");

	public ClayEspurrRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new ClayEspurrModel<>(), 0.2F);
		this.addLayer(new ClayEspurrGlowsRenderLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(ClayEspurrEntity entity) 
	{
			return MAIN_TEXTURE;
	}
}