package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.model.mob.InfestedDeerlingModel;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.InfestedDeerlingGlowsRenderLayer;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.InfestedDeerlingEntity;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class InfestedDeerlingRenderer extends MobRenderer<InfestedDeerlingEntity, InfestedDeerlingModel<InfestedDeerlingEntity>>
{
	private static final ResourceLocation MAIN_TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/infested_deerling.png");

	public InfestedDeerlingRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new InfestedDeerlingModel(), 0.2F);
		this.addLayer(new InfestedDeerlingGlowsRenderLayer(this));
	}

	@Override
	public ResourceLocation getEntityTexture(InfestedDeerlingEntity entity) 
	{
			return MAIN_TEXTURE;
	}

	public static class RenderFactory implements IRenderFactory<InfestedDeerlingEntity>
	{
		@Override
		public EntityRenderer<? super InfestedDeerlingEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new InfestedDeerlingRenderer(manager);
		}
	}
}