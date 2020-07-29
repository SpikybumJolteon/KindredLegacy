package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.model.mob.ArmoredLuxrayModel;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.ArmoredLuxrayEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class ArmoredLuxrayRenderer<T extends ArmoredLuxrayEntity> extends MobRenderer<T, ArmoredLuxrayModel<T>>
{
	private static final ResourceLocation MAIN_TEXTURE = UtilityFunctions.location("textures/mobs/armored_luxray.png");
	
	public ArmoredLuxrayRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new ArmoredLuxrayModel(), 0.5F);
	}

	@Override
	public ResourceLocation getEntityTexture(T entity) 
	{
		return MAIN_TEXTURE;
	}
	
	public static class RenderFactory implements IRenderFactory<ArmoredLuxrayEntity>
	{
		@Override
		public EntityRenderer<? super ArmoredLuxrayEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new ArmoredLuxrayRenderer(manager);
		}
	}
}