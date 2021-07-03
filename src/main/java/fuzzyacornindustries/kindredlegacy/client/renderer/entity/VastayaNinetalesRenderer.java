package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.VastayaNinetalesOrbRenderLayer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.AhriNinetalesModel;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.VastayaNinetalesEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class VastayaNinetalesRenderer<T extends VastayaNinetalesEntity, M extends AhriNinetalesModel<T>> extends MobRenderer<T, AhriNinetalesModel<T>>
{
	public static final IRenderFactory<VastayaNinetalesEntity> RENDER_FACTORY = manager -> new VastayaNinetalesRenderer<>(manager);
	
	private static final ResourceLocation MAIN_TEXTURE = UtilityFunctions.location("textures/mobs/tamables/vastaya_ninetales.png");
	private static final ResourceLocation HURT_TEXTURE = UtilityFunctions.location("textures/mobs/tamables/vastaya_ninetales_hurt.png");
	private static final ResourceLocation HAPPY_TEXTURE = UtilityFunctions.location("textures/mobs/tamables/vastaya_ninetales_happy.png");

	public VastayaNinetalesRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new AhriNinetalesModel<>(), 0.2F);
		this.addLayer(new VastayaNinetalesOrbRenderLayer<>(this));
	}
	
	@Override
	public ResourceLocation getEntityTexture(VastayaNinetalesEntity entity) 
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