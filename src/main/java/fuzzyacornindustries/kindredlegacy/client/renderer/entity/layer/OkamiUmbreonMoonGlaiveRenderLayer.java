package fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.OkamiUmbreonModel;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.tool.OkamiMoonGlaiveModel;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.OkamiUmbreonEntity;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OkamiUmbreonMoonGlaiveRenderLayer<T extends OkamiUmbreonEntity, M extends OkamiUmbreonModel<T>> extends LayerRenderer<T, M>
{
	private final OkamiMoonGlaiveModel<T> model = new OkamiMoonGlaiveModel<T>();
	private final OkamiMoonGlaiveModel<T> modelGlow = new OkamiMoonGlaiveModel<T>();
	private static final ResourceLocation MOON_GLAIVE_TEXTURE = UtilityFunctions.location("textures/mobs/tamables/okami_weapon/moon_glaive.png");
	private static final ResourceLocation MOON_GLAIVE_GLOW_TEXTURE = UtilityFunctions.location("textures/mobs/tamables/okami_weapon/moon_glaive_glows.png");

	public OkamiUmbreonMoonGlaiveRenderLayer(IEntityRenderer<T, M> entityRenderer) 
	{
		super(entityRenderer);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entity, float distanceMoved, float horzVelocity, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		{
			this.getEntityModel().copyModelAttributesTo(this.model);

			this.model.setLivingAnimations(entity, distanceMoved, horzVelocity, partialTicks);
			this.model.setRotationAngles(entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch);

			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityCutoutNoCull(MOON_GLAIVE_TEXTURE));
			this.model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
			
			this.getEntityModel().copyModelAttributesTo(this.modelGlow);

			this.modelGlow.setLivingAnimations(entity, distanceMoved, horzVelocity, partialTicks);
			this.modelGlow.setRotationAngles(entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch);

			ivertexbuilder = bufferIn.getBuffer(RenderType.getEyes(MOON_GLAIVE_GLOW_TEXTURE));
			this.modelGlow.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}