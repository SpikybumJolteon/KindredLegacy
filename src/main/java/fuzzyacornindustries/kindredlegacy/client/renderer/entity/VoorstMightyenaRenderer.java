package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.VoorstMightyenaModel;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class VoorstMightyenaRenderer<T extends VoorstMightyenaEntity> extends MobRenderer<T, VoorstMightyenaModel<T>>
{
	public static final IRenderFactory<VoorstMightyenaEntity> RENDER_FACTORY = manager -> new VoorstMightyenaRenderer<>(manager);
	private static final ResourceLocation MAIN_TEXTURE = UtilityFunctions.location("textures/mobs/voorst_mightyena.png");
	
	public VoorstMightyenaRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new VoorstMightyenaModel<>(), 0.5F);
	}

	@Override
	public ResourceLocation getEntityTexture(T entity) 
	{
		return MAIN_TEXTURE;
	}
}