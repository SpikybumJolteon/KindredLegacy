package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.ZerglingNincadaModel;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.ZerglingNincadaEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class ZerglingNincadaRenderer<T extends ZerglingNincadaEntity> extends MobRenderer<T, ZerglingNincadaModel<T>>
{
	public static final IRenderFactory<ZerglingNincadaEntity> RENDER_FACTORY = manager -> new ZerglingNincadaRenderer<>(manager);
	private static final ResourceLocation MAIN_TEXTURE = UtilityFunctions.location("textures/mobs/zergling_nincada.png");
	
	public ZerglingNincadaRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new ZerglingNincadaModel<>(), 0.2F);
	}

	@Override
	public ResourceLocation getEntityTexture(T entity) 
	{
		return MAIN_TEXTURE;
	}
}