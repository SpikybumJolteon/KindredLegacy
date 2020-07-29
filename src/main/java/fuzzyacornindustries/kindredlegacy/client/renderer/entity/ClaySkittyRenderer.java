package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.model.mob.ClaySkittyModel;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.ClaySkittyGlowsRenderLayer;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.ClaySkittyEntity;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class ClaySkittyRenderer extends MobRenderer<ClaySkittyEntity, ClaySkittyModel<ClaySkittyEntity>>
{
	private static final ResourceLocation MAIN_TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/clay_skitty.png");

	public ClaySkittyRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new ClaySkittyModel(), 0.2F);
		this.addLayer(new ClaySkittyGlowsRenderLayer(this));
	}

	@Override
	public ResourceLocation getEntityTexture(ClaySkittyEntity entity) 
	{
			return MAIN_TEXTURE;
	}

	public static class RenderFactory implements IRenderFactory<ClaySkittyEntity>
	{
		@Override
		public EntityRenderer<? super ClaySkittyEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new ClaySkittyRenderer(manager);
		}
	}
}