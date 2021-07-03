package fuzzyacornindustries.kindredlegacy.client.renderer.entity;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.BandersnatchFennekinModel;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.BandersnatchFennekinEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class BandersnatchFennekinRenderer<T extends BandersnatchFennekinEntity> extends MobRenderer<T, BandersnatchFennekinModel<T>>
{
	public static final IRenderFactory<BandersnatchFennekinEntity> RENDER_FACTORY = manager -> new BandersnatchFennekinRenderer<>(manager);
	private static final ResourceLocation SNOWY_TEXTURE = UtilityFunctions.location("textures/mobs/bandersnatch_fennekin.png");
	private static final ResourceLocation TEMPORATE_TEXTURE = UtilityFunctions.location("textures/mobs/bandersnatch_fennekin_temperate.png");

	public BandersnatchFennekinRenderer(EntityRendererManager rendererManager) 
	{
		super(rendererManager, new BandersnatchFennekinModel<>(), 0.2F);
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
}