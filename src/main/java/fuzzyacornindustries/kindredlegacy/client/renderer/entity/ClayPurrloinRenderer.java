package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.model.mob.ClayPurrloinModel;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.ClayPurrloinGlowsRenderLayer;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.ClayPurrloinEntity;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class ClayPurrloinRenderer extends MobRenderer<ClayPurrloinEntity, ClayPurrloinModel<ClayPurrloinEntity>>
{
	private static final ResourceLocation MAIN_TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/clay_purrloin.png");

	public ClayPurrloinRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new ClayPurrloinModel(), 0.2F);
		this.addLayer(new ClayPurrloinGlowsRenderLayer(this));
	}

	@Override
	public ResourceLocation getEntityTexture(ClayPurrloinEntity entity) 
	{
			return MAIN_TEXTURE;
	}

	public static class RenderFactory implements IRenderFactory<ClayPurrloinEntity>
	{
		@Override
		public EntityRenderer<? super ClayPurrloinEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new ClayPurrloinRenderer(manager);
		}
	}
}