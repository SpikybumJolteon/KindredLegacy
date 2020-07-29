package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.model.mob.VoorstMightyenaModel;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.VoorstMightyenaEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class VoorstMightyenaRenderer<T extends VoorstMightyenaEntity> extends MobRenderer<T, VoorstMightyenaModel<T>>
{
	private static final ResourceLocation MAIN_TEXTURE = UtilityFunctions.location("textures/mobs/voorst_mightyena.png");
	
	public VoorstMightyenaRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new VoorstMightyenaModel(), 0.5F);
	}

	@Override
	public ResourceLocation getEntityTexture(T entity) 
	{
		return MAIN_TEXTURE;
	}
	
	public static class RenderFactory implements IRenderFactory<VoorstMightyenaEntity>
	{
		@Override
		public EntityRenderer<? super VoorstMightyenaEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new VoorstMightyenaRenderer(manager);
		}
	}
}