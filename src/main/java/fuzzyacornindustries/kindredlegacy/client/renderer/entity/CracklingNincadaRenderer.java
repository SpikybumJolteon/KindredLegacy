package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.CracklingNincadaGlowsRenderLayer;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.ZerglingNincadaModel;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.CracklingNincadaEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class CracklingNincadaRenderer<T extends CracklingNincadaEntity, M extends ZerglingNincadaModel<T>> extends MobRenderer<T, ZerglingNincadaModel<T>>
{
	public static final IRenderFactory<CracklingNincadaEntity> RENDER_FACTORY = manager -> new CracklingNincadaRenderer<>(manager);
	private static final ResourceLocation MAIN_TEXTURE = UtilityFunctions.location("textures/mobs/crackling_nincada.png");

	public CracklingNincadaRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new ZerglingNincadaModel<>(), 0.2F);
		this.addLayer(new CracklingNincadaGlowsRenderLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(CracklingNincadaEntity entity) 
	{
			return MAIN_TEXTURE;
	}
}