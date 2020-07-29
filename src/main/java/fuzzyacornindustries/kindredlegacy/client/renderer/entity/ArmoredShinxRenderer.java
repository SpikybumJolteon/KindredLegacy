package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.model.mob.ArmoredShinxModel;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.ArmoredShinxEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class ArmoredShinxRenderer<T extends ArmoredShinxEntity> extends MobRenderer<T, ArmoredShinxModel<T>>
{
	private static final ResourceLocation MAIN_TEXTURE = UtilityFunctions.location("textures/mobs/armored_shinx.png");
	
	public ArmoredShinxRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new ArmoredShinxModel(), 0.2F);
	}

	@Override
	public ResourceLocation getEntityTexture(T entity) 
	{
		return MAIN_TEXTURE;
	}
	
	public static class RenderFactory implements IRenderFactory<ArmoredShinxEntity>
	{
		@Override
		public EntityRenderer<? super ArmoredShinxEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new ArmoredShinxRenderer(manager);
		}
	}
}