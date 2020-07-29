package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.model.mob.ClayCommanderDelcattyModel;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer.ClayCommanderDelcattyRenderLayer;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.ClayCommanderDelcattyEntity;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class ClayCommanderDelcattyRenderer extends MobRenderer<ClayCommanderDelcattyEntity, ClayCommanderDelcattyModel<ClayCommanderDelcattyEntity>>
{
	private static final ResourceLocation MAIN_TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/clay_commander_delcatty.png");

	public ClayCommanderDelcattyRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new ClayCommanderDelcattyModel(), 0.5F);
		this.addLayer(new ClayCommanderDelcattyRenderLayer(this));
	}

	@Override
	public ResourceLocation getEntityTexture(ClayCommanderDelcattyEntity entity) 
	{
			return MAIN_TEXTURE;
	}

	public static class RenderFactory implements IRenderFactory<ClayCommanderDelcattyEntity>
	{
		@Override
		public EntityRenderer<? super ClayCommanderDelcattyEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new ClayCommanderDelcattyRenderer(manager);
		}
	}
}