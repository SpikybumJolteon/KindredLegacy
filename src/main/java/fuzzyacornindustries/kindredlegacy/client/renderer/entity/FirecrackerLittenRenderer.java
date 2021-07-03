package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.FirecrackerLittenModel;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.FirecrackerLittenEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class FirecrackerLittenRenderer<T extends FirecrackerLittenEntity> extends MobRenderer<T, FirecrackerLittenModel<T>>
{
	public static final IRenderFactory<FirecrackerLittenEntity> RENDER_FACTORY = manager -> new FirecrackerLittenRenderer<>(manager);
	
	private static final ResourceLocation MAIN_TEXTURE = UtilityFunctions.location("textures/mobs/tamables/firecracker_litten.png");
	private static final ResourceLocation HURT_TEXTURE = UtilityFunctions.location("textures/mobs/tamables/firecracker_litten_hurt.png");
	private static final ResourceLocation HAPPY_TEXTURE = UtilityFunctions.location("textures/mobs/tamables/firecracker_litten_happy.png");

	public FirecrackerLittenRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new FirecrackerLittenModel<>(), 0.3F);
	}

	@Override
	public ResourceLocation getEntityTexture(FirecrackerLittenEntity entity) 
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