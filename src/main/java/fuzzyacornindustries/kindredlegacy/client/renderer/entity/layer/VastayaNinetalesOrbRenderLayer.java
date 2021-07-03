package fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.AhriNinetalesModel;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.AhriNinetalesOrbModel;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.VastayaNinetalesEntity;
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
public class VastayaNinetalesOrbRenderLayer<T extends VastayaNinetalesEntity, M extends AhriNinetalesModel<T>> extends LayerRenderer<T, M>
{
	private final AhriNinetalesOrbModel<T> model = new AhriNinetalesOrbModel<T>();
	private final AhriNinetalesOrbModel<T> modelGlow = new AhriNinetalesOrbModel<T>();
	private static final ResourceLocation ORB_TEXTURE = UtilityFunctions.location("textures/mobs/tamables/vastaya_ninetales_orb.png");
	private static final ResourceLocation ORB_GLOW_TEXTURE = UtilityFunctions.location("textures/mobs/tamables/vastaya_ninetales_orb.png");

	public VastayaNinetalesOrbRenderLayer(IEntityRenderer<T, M> entityRenderer) 
	{
		super(entityRenderer);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entity, float distanceMoved, float horzVelocity, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) 
	{
		if(!entity.isSitting())
		{
			this.getEntityModel().copyModelAttributesTo(this.model);

			this.model.setLivingAnimations(entity, distanceMoved, horzVelocity, partialTicks);
			this.model.setRotationAngles(entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch);

			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityCutoutNoCull(ORB_TEXTURE));
			this.model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

			this.getEntityModel().copyModelAttributesTo(this.modelGlow);

			this.modelGlow.setLivingAnimations(entity, distanceMoved, horzVelocity, partialTicks);
			this.modelGlow.setRotationAngles(entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch);

			ivertexbuilder = bufferIn.getBuffer(RenderType.getEyes(ORB_GLOW_TEXTURE));
			this.modelGlow.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}