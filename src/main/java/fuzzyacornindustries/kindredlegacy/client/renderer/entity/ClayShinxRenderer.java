package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.model.mob.ClayShinxModel;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.ClayShinxRenderLayer;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.ClayShinxEntity;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class ClayShinxRenderer extends MobRenderer<ClayShinxEntity, ClayShinxModel<ClayShinxEntity>>
{
	private static final ResourceLocation MAIN_TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/clay_shinx.png");

	public ClayShinxRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new ClayShinxModel(), 0.2F);
		this.addLayer(new ClayShinxRenderLayer(this));
	}

	@Override
	public ResourceLocation getEntityTexture(ClayShinxEntity entity) 
	{
			return MAIN_TEXTURE;
	}

	public static class RenderFactory implements IRenderFactory<ClayShinxEntity>
	{
		@Override
		public EntityRenderer<? super ClayShinxEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new ClayShinxRenderer(manager);
		}
	}
}