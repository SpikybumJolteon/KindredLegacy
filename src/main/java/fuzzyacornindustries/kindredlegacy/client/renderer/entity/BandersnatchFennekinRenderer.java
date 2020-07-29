package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.model.mob.BandersnatchFennekinModel;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.BandersnatchFennekinEntity;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class BandersnatchFennekinRenderer extends MobRenderer<BandersnatchFennekinEntity, BandersnatchFennekinModel<BandersnatchFennekinEntity>>
{
	private static final ResourceLocation SNOWY_TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/bandersnatch_fennekin.png");
	private static final ResourceLocation TEMPORATE_TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/bandersnatch_fennekin_temperate.png");

	public BandersnatchFennekinRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new BandersnatchFennekinModel(), 0.2F);
	}

	@Override
	public ResourceLocation getEntityTexture(BandersnatchFennekinEntity entity)
	{
		switch (entity.getFennekinType())
		{
		case BandersnatchFennekinEntity.snowCoatID:
			return SNOWY_TEXTURE;
		}
		
		return TEMPORATE_TEXTURE;
	}

	public static class RenderFactory implements IRenderFactory<BandersnatchFennekinEntity>
	{
		@Override
		public EntityRenderer<? super BandersnatchFennekinEntity> createRenderFor(EntityRendererManager manager) 
		{
			return new BandersnatchFennekinRenderer(manager);
		}
	}
}