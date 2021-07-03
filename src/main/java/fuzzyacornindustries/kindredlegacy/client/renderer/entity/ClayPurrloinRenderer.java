package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.ClayPurrloinGlowsRenderLayer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.ClayPurrloinModel;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.ClayPurrloinEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class ClayPurrloinRenderer<T extends ClayPurrloinEntity, M extends ClayPurrloinModel<T>> extends MobRenderer<T, ClayPurrloinModel<T>>
{
	public static final IRenderFactory<ClayPurrloinEntity> RENDER_FACTORY = manager -> new ClayPurrloinRenderer<>(manager);
	private static final ResourceLocation MAIN_TEXTURE = UtilityFunctions.location("textures/mobs/clay_purrloin.png");

	public ClayPurrloinRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new ClayPurrloinModel<>(), 0.2F);
		this.addLayer(new ClayPurrloinGlowsRenderLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(ClayPurrloinEntity entity) 
	{
			return MAIN_TEXTURE;
	}
}