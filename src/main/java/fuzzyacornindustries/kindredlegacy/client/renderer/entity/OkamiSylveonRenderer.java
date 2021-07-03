package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.OkamiSylveonJadeGlaiveRenderLayer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.OkamiSylveonModel;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.OkamiSylveonEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class OkamiSylveonRenderer<T extends OkamiSylveonEntity, M extends OkamiSylveonModel<T>> extends MobRenderer<T, OkamiSylveonModel<T>>
{
	public static final IRenderFactory<OkamiSylveonEntity> RENDER_FACTORY = manager -> new OkamiSylveonRenderer<>(manager);
	
	private static final ResourceLocation MAIN_TEXTURE = UtilityFunctions.location("textures/mobs/tamables/okami_sylveon.png");
	private static final ResourceLocation HURT_TEXTURE = UtilityFunctions.location("textures/mobs/tamables/okami_sylveon_hurt.png");
	private static final ResourceLocation HAPPY_TEXTURE = UtilityFunctions.location("textures/mobs/tamables/okami_sylveon_happy.png");

	public OkamiSylveonRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new OkamiSylveonModel<>(), 0.2F);
		this.addLayer(new OkamiSylveonJadeGlaiveRenderLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(OkamiSylveonEntity entity) 
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