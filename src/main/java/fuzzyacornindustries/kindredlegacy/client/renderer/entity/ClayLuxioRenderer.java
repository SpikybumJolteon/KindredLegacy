package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.model.mob.ClayLuxioModel;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.ClayLuxioRenderLayer;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.ClayLuxioEntity;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class ClayLuxioRenderer extends MobRenderer<ClayLuxioEntity, ClayLuxioModel<ClayLuxioEntity>>
{
	private static final ResourceLocation MAIN_TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/clay_luxio.png");

	public ClayLuxioRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new ClayLuxioModel(), 0.2F);
		this.addLayer(new ClayLuxioRenderLayer(this));
	}

	@Override
	public ResourceLocation getEntityTexture(ClayLuxioEntity entity) 
	{
			return MAIN_TEXTURE;
	}

	public static class RenderFactory implements IRenderFactory<ClayLuxioEntity>
	{
		@Override
		public EntityRenderer<? super ClayLuxioEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new ClayLuxioRenderer(manager);
		}
	}
}