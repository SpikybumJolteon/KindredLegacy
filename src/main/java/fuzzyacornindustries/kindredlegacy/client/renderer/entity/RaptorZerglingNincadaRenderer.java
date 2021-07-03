package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.ZerglingNincadaModel;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.RaptorZerglingNincadaEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class RaptorZerglingNincadaRenderer<T extends RaptorZerglingNincadaEntity> extends MobRenderer<T, ZerglingNincadaModel<T>>
{
	public static final IRenderFactory<RaptorZerglingNincadaEntity> RENDER_FACTORY = manager -> new RaptorZerglingNincadaRenderer<>(manager);
	private static final ResourceLocation MAIN_TEXTURE = UtilityFunctions.location("textures/mobs/raptor_zergling_nincada.png");
	
	public RaptorZerglingNincadaRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new ZerglingNincadaModel<>(), 0.2F);
	}

	@Override
	public ResourceLocation getEntityTexture(T entity) 
	{
		return MAIN_TEXTURE;
	}
}