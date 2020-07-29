package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.model.mob.SnowSorceressBraixenModel;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.SnowSorceressBraixenRenderLayer;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.SnowSorceressBraixenEntity;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class SnowSorceressBraixenRenderer extends MobRenderer<SnowSorceressBraixenEntity, SnowSorceressBraixenModel<SnowSorceressBraixenEntity>>
{
	private static final ResourceLocation MAIN_TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/snow_sorceress_braixen.png");

	public SnowSorceressBraixenRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new SnowSorceressBraixenModel(), 0.3F);
		this.addLayer(new SnowSorceressBraixenRenderLayer(this));
	}

	@Override
	public ResourceLocation getEntityTexture(SnowSorceressBraixenEntity entity) 
	{
			return MAIN_TEXTURE;
	}

	public static class RenderFactory implements IRenderFactory<SnowSorceressBraixenEntity>
	{
		@Override
		public EntityRenderer<? super SnowSorceressBraixenEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new SnowSorceressBraixenRenderer(manager);
		}
	}
}