package fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.OkamiSylveonModel;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.tool.OkamiJadeGlaiveModel;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.OkamiSylveonEntity;
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
public class OkamiSylveonJadeGlaiveRenderLayer<T extends OkamiSylveonEntity, M extends OkamiSylveonModel<T>> extends LayerRenderer<T, M>
{
	private final OkamiJadeGlaiveModel<T> model = new OkamiJadeGlaiveModel<T>();
	private static final ResourceLocation JADE_GLAIVE_TEXTURE = UtilityFunctions.location("textures/mobs/tamables/okami_weapon/jade_glaive.png");

	public OkamiSylveonJadeGlaiveRenderLayer(IEntityRenderer<T, M> entityRenderer) 
	{
		super(entityRenderer);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entity, float distanceMoved, float horzVelocity, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		{
			this.getEntityModel().copyModelAttributesTo(this.model);

			this.model.setLivingAnimations(entity, distanceMoved, horzVelocity, partialTicks);
			this.model.setRotationAngles(entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch);

			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityCutoutNoCull(JADE_GLAIVE_TEXTURE));
			this.model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}