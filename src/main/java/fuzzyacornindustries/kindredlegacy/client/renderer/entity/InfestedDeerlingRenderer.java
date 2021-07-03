package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.InfestedDeerlingGlowsRenderLayer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.InfestedDeerlingModel;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.InfestedDeerlingEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class InfestedDeerlingRenderer<T extends InfestedDeerlingEntity, M extends InfestedDeerlingModel<T>> extends MobRenderer<T, InfestedDeerlingModel<T>>
{
	public static final IRenderFactory<InfestedDeerlingEntity> RENDER_FACTORY = manager -> new InfestedDeerlingRenderer<>(manager);
	private static final ResourceLocation MAIN_TEXTURE = UtilityFunctions.location("textures/mobs/infested_deerling.png");

	public InfestedDeerlingRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new InfestedDeerlingModel<>(), 0.2F);
		this.addLayer(new InfestedDeerlingGlowsRenderLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(InfestedDeerlingEntity entity) 
	{
			return MAIN_TEXTURE;
	}
}