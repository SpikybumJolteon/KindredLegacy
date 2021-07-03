package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.OkamiUmbreonMoonGlaiveRenderLayer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.OkamiUmbreonModel;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.OkamiUmbreonEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class OkamiUmbreonRenderer<T extends OkamiUmbreonEntity, M extends OkamiUmbreonModel<T>> extends MobRenderer<T, OkamiUmbreonModel<T>>
{
	public static final IRenderFactory<OkamiUmbreonEntity> RENDER_FACTORY = manager -> new OkamiUmbreonRenderer<>(manager);
	
	private static final ResourceLocation MAIN_TEXTURE = UtilityFunctions.location("textures/mobs/tamables/okami_umbreon.png");
	private static final ResourceLocation HURT_TEXTURE = UtilityFunctions.location("textures/mobs/tamables/okami_umbreon_hurt.png");
	private static final ResourceLocation HAPPY_TEXTURE = UtilityFunctions.location("textures/mobs/tamables/okami_umbreon_happy.png");

	public OkamiUmbreonRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new OkamiUmbreonModel<>(), 0.2F);
		this.addLayer(new OkamiUmbreonMoonGlaiveRenderLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(OkamiUmbreonEntity entity) 
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