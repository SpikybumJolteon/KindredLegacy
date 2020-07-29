package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.model.mob.FeywoodAbsolModel;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.FeywoodAbsolEntity;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class FeywoodAbsolRenderer extends MobRenderer<FeywoodAbsolEntity, FeywoodAbsolModel<FeywoodAbsolEntity>>
{
	private static final ResourceLocation MAIN_TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/feywood_absol.png");
	private static final ResourceLocation HURT_TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/feywood_absol_hurt.png");
	private static final ResourceLocation HAPPY_TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/feywood_absol_happy.png");

	public FeywoodAbsolRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new FeywoodAbsolModel(), 0.3F);
	}

	@Override
	public ResourceLocation getEntityTexture(FeywoodAbsolEntity entity) 
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

	public static class RenderFactory implements IRenderFactory<FeywoodAbsolEntity>
	{
		@Override
		public EntityRenderer<? super FeywoodAbsolEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new FeywoodAbsolRenderer(manager);
		}
	}
	/*
	@Override
	public void renderName(FeywoodAbsolEntity tamableEntity, double xCoord, double yCoord, double zCoord)
	{
		String name = tamableEntity.getName();

		if (!name.equals("") && !name.equals("Okami Sylveon"))
		{
			renderLivingLabel(tamableEntity, name, xCoord, yCoord, zCoord, 64);
		}

		super.renderName(tamableEntity, xCoord, yCoord, zCoord);
	}*/
}