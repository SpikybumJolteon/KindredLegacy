package fuzzyacornindustries.kindredlegacy.client.renderer.entity.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fuzzyacornindustries.kindredlegacy.client.model.mob.OkamiSylveonModel;
import fuzzyacornindustries.kindredlegacy.client.model.mob.tool.OkamiJadeGlaiveModel;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.OkamiSylveonEntity;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OkamiSylveonJadeGlaiveRenderLayer extends LayerRenderer<OkamiSylveonEntity, OkamiSylveonModel<OkamiSylveonEntity>>
{
	private final OkamiJadeGlaiveModel<OkamiSylveonEntity> model = new OkamiJadeGlaiveModel<OkamiSylveonEntity>();
	//private static final RenderType JADE_GLAIVE_TEXTURE = RenderType.func_228652_i_(new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_weapon/jade_glaive.png"));
	private static final ResourceLocation JADE_GLAIVE_TEXTURE = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/mobs/tamables/okami_weapon/jade_glaive.png");

	public OkamiSylveonJadeGlaiveRenderLayer(IEntityRenderer<OkamiSylveonEntity, OkamiSylveonModel<OkamiSylveonEntity>> entityRenderer) 
	{
		super(entityRenderer);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, OkamiSylveonEntity entity, float distanceMoved, float horzVelocity, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		{
			this.getEntityModel().copyModelAttributesTo(this.model);

			this.model.setLivingAnimations(entity, distanceMoved, horzVelocity, partialTicks);
			this.model.setRotationAngles(entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch);

			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityCutoutNoCull(JADE_GLAIVE_TEXTURE));
			this.model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}