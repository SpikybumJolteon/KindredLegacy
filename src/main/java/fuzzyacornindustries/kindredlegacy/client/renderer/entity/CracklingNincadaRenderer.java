package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.model.mob.ZerglingNincadaModel;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.CracklingNincadaGlowsRenderLayer;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.CracklingNincadaEntity;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class CracklingNincadaRenderer extends MobRenderer<CracklingNincadaEntity, ZerglingNincadaModel<CracklingNincadaEntity>>
{
	private static final ResourceLocation MAIN_TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/crackling_nincada.png");

	public CracklingNincadaRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new ZerglingNincadaModel(), 0.2F);
		this.addLayer(new CracklingNincadaGlowsRenderLayer(this));
	}

	@Override
	public ResourceLocation getEntityTexture(CracklingNincadaEntity entity) 
	{
			return MAIN_TEXTURE;
	}

	public static class RenderFactory implements IRenderFactory<CracklingNincadaEntity>
	{
		@Override
		public EntityRenderer<? super CracklingNincadaEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new CracklingNincadaRenderer(manager);
		}
	}
}