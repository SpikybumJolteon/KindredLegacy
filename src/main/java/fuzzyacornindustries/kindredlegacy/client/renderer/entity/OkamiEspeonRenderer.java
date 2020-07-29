package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.model.mob.OkamiEspeonModel;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.OkamiEspeonSunGlaiveRenderLayer;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.OkamiEspeonEntity;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class OkamiEspeonRenderer extends MobRenderer<OkamiEspeonEntity, OkamiEspeonModel<OkamiEspeonEntity>>
{
	private static final ResourceLocation MAIN_TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_espeon.png");
	private static final ResourceLocation HURT_TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_espeon_hurt.png");
	private static final ResourceLocation HAPPY_TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_espeon_happy.png");

	public OkamiEspeonRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new OkamiEspeonModel(), 0.2F);
		this.addLayer(new OkamiEspeonSunGlaiveRenderLayer(this));
	}

	@Override
	public ResourceLocation getEntityTexture(OkamiEspeonEntity entity) 
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

	public static class RenderFactory implements IRenderFactory<OkamiEspeonEntity>
	{
		@Override
		public EntityRenderer<? super OkamiEspeonEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new OkamiEspeonRenderer(manager);
		}
	}
	/*
	@Override
	public void renderName(OkamiEspeonEntity tamableEntity, double xCoord, double yCoord, double zCoord)
	{
		String name = tamableEntity.getName();

		if (!name.equals("") && !name.equals("Okami Espeon"))
		{
			renderLivingLabel(tamableEntity, name, xCoord, yCoord, zCoord, 64);
		}

		super.renderName(tamableEntity, xCoord, yCoord, zCoord);
	}*/
}