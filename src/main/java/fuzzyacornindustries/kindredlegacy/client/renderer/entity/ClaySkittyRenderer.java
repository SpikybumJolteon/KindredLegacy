package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.ClaySkittyGlowsRenderLayer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.ClaySkittyModel;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.ClaySkittyEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class ClaySkittyRenderer<T extends ClaySkittyEntity, M extends ClaySkittyModel<T>> extends MobRenderer<T, ClaySkittyModel<T>>
{
	public static final IRenderFactory<ClaySkittyEntity> RENDER_FACTORY = manager -> new ClaySkittyRenderer<>(manager);
	private static final ResourceLocation MAIN_TEXTURE = UtilityFunctions.location("textures/mobs/clay_skitty.png");

	public ClaySkittyRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new ClaySkittyModel<>(), 0.2F);
		this.addLayer(new ClaySkittyGlowsRenderLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(ClaySkittyEntity entity) 
	{
			return MAIN_TEXTURE;
	}
}