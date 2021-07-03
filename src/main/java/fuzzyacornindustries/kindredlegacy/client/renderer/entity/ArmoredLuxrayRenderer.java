package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.ArmoredLuxrayModel;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.ArmoredLuxrayEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class ArmoredLuxrayRenderer<T extends ArmoredLuxrayEntity> extends MobRenderer<T, ArmoredLuxrayModel<T>>
{
	public static final IRenderFactory<ArmoredLuxrayEntity> RENDER_FACTORY = manager -> new ArmoredLuxrayRenderer<>(manager);
	private static final ResourceLocation MAIN_TEXTURE = UtilityFunctions.location("textures/mobs/armored_luxray.png");
	
	public ArmoredLuxrayRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new ArmoredLuxrayModel<>(), 0.5F);
	}

	@Override
	public ResourceLocation getEntityTexture(T entity) 
	{
		return MAIN_TEXTURE;
	}
}