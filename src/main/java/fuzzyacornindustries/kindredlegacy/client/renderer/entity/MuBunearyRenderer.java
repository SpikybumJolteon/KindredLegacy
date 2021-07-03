package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.MuBunearyGlowsRenderLayer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.MuBunearyModel;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.MuBunearyEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class MuBunearyRenderer<T extends MuBunearyEntity, M extends MuBunearyModel<T>> extends MobRenderer<T, MuBunearyModel<T>>
{
	public static final IRenderFactory<MuBunearyEntity> RENDER_FACTORY = manager -> new MuBunearyRenderer<>(manager);
	private static final ResourceLocation MAIN_TEXTURE = UtilityFunctions.location("textures/mobs/mu_buneary.png");

	public MuBunearyRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new MuBunearyModel<>(), 0.2F);
		this.addLayer(new MuBunearyGlowsRenderLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(MuBunearyEntity entity) 
	{
			return MAIN_TEXTURE;
	}
}