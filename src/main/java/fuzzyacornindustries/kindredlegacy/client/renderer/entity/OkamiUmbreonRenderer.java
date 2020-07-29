package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.model.mob.OkamiUmbreonModel;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.OkamiUmbreonMoonGlaiveRenderLayer;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.OkamiUmbreonEntity;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class OkamiUmbreonRenderer extends MobRenderer<OkamiUmbreonEntity, OkamiUmbreonModel<OkamiUmbreonEntity>>
{
	private static final ResourceLocation MAIN_TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_umbreon.png");
	private static final ResourceLocation HURT_TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_umbreon_hurt.png");
	private static final ResourceLocation HAPPY_TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_umbreon_happy.png");

	public OkamiUmbreonRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new OkamiUmbreonModel(), 0.2F);
		this.addLayer(new OkamiUmbreonMoonGlaiveRenderLayer(this));
	}

	@Override
	public ResourceLocation getEntityTexture(OkamiUmbreonEntity entity) 
	{
		switch (entity.getMainTextureType())
		{
		case 0:
			return MAIN_TEXTURE;
		case 1:
			return HURT_TEXTURE;
		}

		return HAPPY_TEXTURE;
	}

	public static class RenderFactory implements IRenderFactory<OkamiUmbreonEntity>
	{
		@Override
		public EntityRenderer<? super OkamiUmbreonEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new OkamiUmbreonRenderer(manager);
		}
	}
}