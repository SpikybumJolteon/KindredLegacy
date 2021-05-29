package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.ArmoredShinxModel;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.ArmoredShinxEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class ArmoredShinxRenderer<T extends ArmoredShinxEntity> extends MobRenderer<T, ArmoredShinxModel<T>>
{
	public static final IRenderFactory<ArmoredShinxEntity> RENDER_FACTORY = manager -> new ArmoredShinxRenderer<>(manager);
	private static final ResourceLocation MAIN_TEXTURE = UtilityFunctions.location("textures/mobs/armored_shinx.png");
	
	public ArmoredShinxRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new ArmoredShinxModel<T>(), 0.2F);
	}

	@Override
	public ResourceLocation getEntityTexture(T entity) 
	{
		return MAIN_TEXTURE;
	}
}