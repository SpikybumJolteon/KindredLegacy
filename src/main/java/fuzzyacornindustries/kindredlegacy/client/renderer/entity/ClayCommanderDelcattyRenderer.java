package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.ClayCommanderDelcattyGlowsRenderLayer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.ClayCommanderDelcattyModel;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.ClayCommanderDelcattyEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class ClayCommanderDelcattyRenderer<T extends ClayCommanderDelcattyEntity, M extends ClayCommanderDelcattyModel<T>> extends MobRenderer<T, ClayCommanderDelcattyModel<T>>
{
	public static final IRenderFactory<ClayCommanderDelcattyEntity> RENDER_FACTORY = manager -> new ClayCommanderDelcattyRenderer<>(manager);
	private static final ResourceLocation MAIN_TEXTURE = UtilityFunctions.location("textures/mobs/clay_commander_delcatty.png");

	public ClayCommanderDelcattyRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new ClayCommanderDelcattyModel<>(), 0.5F);
		this.addLayer(new ClayCommanderDelcattyGlowsRenderLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(ClayCommanderDelcattyEntity entity) 
	{
			return MAIN_TEXTURE;
	}
}