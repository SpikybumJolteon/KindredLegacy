package fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.MuBunearyModel;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.MuBunearyEntity;
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
public class MuBunearyGlowsRenderLayer<T extends MuBunearyEntity, M extends MuBunearyModel<T>> extends LayerRenderer<T, M>
{
	private final MuBunearyModel<T> modelGlow = new MuBunearyModel<T>();
	private static final ResourceLocation GLOW_TEXTURE = UtilityFunctions.location("textures/mobs/mu_buneary_glows.png");

	public MuBunearyGlowsRenderLayer(IEntityRenderer<T, M> entityRenderer) 
	{
		super(entityRenderer);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entity, float distanceMoved, float horzVelocity, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		{
			this.getEntityModel().copyModelAttributesTo(this.modelGlow);

			this.modelGlow.setLivingAnimations(entity, distanceMoved, horzVelocity, partialTicks);
			this.modelGlow.setRotationAngles(entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch);

			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEyes(GLOW_TEXTURE));
			this.modelGlow.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}