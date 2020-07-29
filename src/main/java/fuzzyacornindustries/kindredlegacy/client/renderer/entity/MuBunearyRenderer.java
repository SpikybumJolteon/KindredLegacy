package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.model.mob.MuBunearyModel;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.MuBunearyGlowsRenderLayer;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.MuBunearyEntity;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class MuBunearyRenderer extends MobRenderer<MuBunearyEntity, MuBunearyModel<MuBunearyEntity>>
{
	private static final ResourceLocation MAIN_TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/mu_buneary.png");

	public MuBunearyRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new MuBunearyModel(), 0.2F);
		this.addLayer(new MuBunearyGlowsRenderLayer(this));
	}

	@Override
	public ResourceLocation getEntityTexture(MuBunearyEntity entity) 
	{
			return MAIN_TEXTURE;
	}

	public static class RenderFactory implements IRenderFactory<MuBunearyEntity>
	{
		@Override
		public EntityRenderer<? super MuBunearyEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new MuBunearyRenderer(manager);
		}
	}
}