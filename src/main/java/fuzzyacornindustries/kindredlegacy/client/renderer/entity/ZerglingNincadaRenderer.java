package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.model.mob.ZerglingNincadaModel;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.ZerglingNincadaEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class ZerglingNincadaRenderer<T extends ZerglingNincadaEntity> extends MobRenderer<T, ZerglingNincadaModel<T>>
{
	private static final ResourceLocation MAIN_TEXTURE = UtilityFunctions.location("textures/mobs/zergling_nincada.png");
	
	public ZerglingNincadaRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new ZerglingNincadaModel(), 0.2F);
	}

	@Override
	public ResourceLocation getEntityTexture(T entity) 
	{
		return MAIN_TEXTURE;
	}
	
	public static class RenderFactory implements IRenderFactory<ZerglingNincadaEntity>
	{
		@Override
		public EntityRenderer<? super ZerglingNincadaEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new ZerglingNincadaRenderer(manager);
		}
	}
}