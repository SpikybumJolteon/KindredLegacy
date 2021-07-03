package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.FeywoodAbsolModel;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.FeywoodAbsolEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class FeywoodAbsolRenderer<T extends FeywoodAbsolEntity> extends MobRenderer<T, FeywoodAbsolModel<T>>
{
	public static final IRenderFactory<FeywoodAbsolEntity> RENDER_FACTORY = manager -> new FeywoodAbsolRenderer<>(manager);
	
	private static final ResourceLocation MAIN_TEXTURE = UtilityFunctions.location("textures/mobs/tamables/feywood_absol.png");
	private static final ResourceLocation HURT_TEXTURE = UtilityFunctions.location("textures/mobs/tamables/feywood_absol_hurt.png");
	private static final ResourceLocation HAPPY_TEXTURE = UtilityFunctions.location("textures/mobs/tamables/feywood_absol_happy.png");

	public FeywoodAbsolRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new FeywoodAbsolModel<>(), 0.3F);
	}
	
	@Override
	public ResourceLocation getEntityTexture(FeywoodAbsolEntity entity) 
	{
		switch (entity.getMainTextureType())
		{
		case 0:
			return MAIN_TEXTURE;
		case 1:
			return HURT_TEXTURE;
		}

		return HAPPY_TEXTURE;
	}
}