package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.model.mob.ZerglingNincadaModel;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.RaptorZerglingNincadaEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RaptorZerglingNincadaRenderer<T extends RaptorZerglingNincadaEntity> extends MobRenderer<T, ZerglingNincadaModel<T>>
{
	private static final ResourceLocation MAIN_TEXTURE = UtilityFunctions.location("textures/mobs/raptor_zergling_nincada.png");
	
	public RaptorZerglingNincadaRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new ZerglingNincadaModel(), 0.2F);
	}

	@Override
	public ResourceLocation getEntityTexture(T entity) 
	{
		return MAIN_TEXTURE;
	}
	
	public static class RenderFactory implements IRenderFactory<RaptorZerglingNincadaEntity>
	{
		@Override
		public EntityRenderer<? super RaptorZerglingNincadaEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new RaptorZerglingNincadaRenderer(manager);
		}
	}
}