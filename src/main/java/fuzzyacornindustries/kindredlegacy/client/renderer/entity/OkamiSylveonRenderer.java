package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.model.mob.OkamiSylveonModel;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.OkamiSylveonJadeGlaiveRenderLayer;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.OkamiSylveonEntity;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class OkamiSylveonRenderer extends MobRenderer<OkamiSylveonEntity, OkamiSylveonModel<OkamiSylveonEntity>>
{
	private static final ResourceLocation MAIN_TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_sylveon.png");
	private static final ResourceLocation HURT_TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_sylveon_hurt.png");
	private static final ResourceLocation HAPPY_TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_sylveon_happy.png");

	public OkamiSylveonRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new OkamiSylveonModel(), 0.2F);
		this.addLayer(new OkamiSylveonJadeGlaiveRenderLayer(this));
	}

	@Override
	public ResourceLocation getEntityTexture(OkamiSylveonEntity entity) 
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

	public static class RenderFactory implements IRenderFactory<OkamiSylveonEntity>
	{
		@Override
		public EntityRenderer<? super OkamiSylveonEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new OkamiSylveonRenderer(manager);
		}
	}
	/*
	@Override
	public void renderName(OkamiSylveonEntity tamableEntity, double xCoord, double yCoord, double zCoord)
	{
		String name = tamableEntity.getName();

		if (!name.equals("") && !name.equals("Okami Sylveon"))
		{
			renderLivingLabel(tamableEntity, name, xCoord, yCoord, zCoord, 64);
		}

		super.renderName(tamableEntity, xCoord, yCoord, zCoord);
	}*/
}