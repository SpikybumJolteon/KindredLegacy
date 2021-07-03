package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.OkamiEspeonSunGlaiveRenderLayer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.OkamiEspeonModel;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.OkamiEspeonEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class OkamiEspeonRenderer<T extends OkamiEspeonEntity, M extends OkamiEspeonModel<T>> extends MobRenderer<T, OkamiEspeonModel<T>>
{
	public static final IRenderFactory<OkamiEspeonEntity> RENDER_FACTORY = manager -> new OkamiEspeonRenderer<>(manager);
	
	private static final ResourceLocation MAIN_TEXTURE = UtilityFunctions.location("textures/mobs/tamables/okami_espeon.png");
	private static final ResourceLocation HURT_TEXTURE = UtilityFunctions.location("textures/mobs/tamables/okami_espeon_hurt.png");
	private static final ResourceLocation HAPPY_TEXTURE = UtilityFunctions.location("textures/mobs/tamables/okami_espeon_happy.png");

	public OkamiEspeonRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new OkamiEspeonModel<>(), 0.2F);
		this.addLayer(new OkamiEspeonSunGlaiveRenderLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(OkamiEspeonEntity entity) 
	{
		switch (entity.getMainTextureType())
		{
		case 0:
			return MAIN_TEXTURE;
		case 1:
			return HURT_TEXTURE;
		}

		return HAPPY_TEXTURE;
	}
}