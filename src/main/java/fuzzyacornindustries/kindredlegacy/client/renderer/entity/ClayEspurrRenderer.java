package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.model.mob.ClayEspurrModel;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.ClayEspurrGlowsRenderLayer;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.ClayEspurrEntity;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class ClayEspurrRenderer extends MobRenderer<ClayEspurrEntity, ClayEspurrModel<ClayEspurrEntity>>
{
	private static final ResourceLocation MAIN_TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/clay_espurr.png");

	public ClayEspurrRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new ClayEspurrModel(), 0.2F);
		this.addLayer(new ClayEspurrGlowsRenderLayer(this));
	}

	@Override
	public ResourceLocation getEntityTexture(ClayEspurrEntity entity) 
	{
			return MAIN_TEXTURE;
	}

	public static class RenderFactory implements IRenderFactory<ClayEspurrEntity>
	{
		@Override
		public EntityRenderer<? super ClayEspurrEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new ClayEspurrRenderer(manager);
		}
	}
}