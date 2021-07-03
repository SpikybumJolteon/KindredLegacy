package fuzzyacornindustries.kindredlegacy.client.renderer.projectile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fuzzyacornindustries.kindredlegacy.common.entity.projectile.FireworkMissileEntity;
import fuzzyacornindustries.kindredlegacy.lib.Names;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix3f;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class FireworkMissileRenderer extends EntityRenderer<FireworkMissileEntity> 
{
	public static final IRenderFactory<FireworkMissileEntity> RENDER_FACTORY = manager -> new FireworkMissileRenderer(manager);
	public static final ResourceLocation MAIN_TEXTURE = new ResourceLocation(Names.MOD_ID, "textures/projectiles/firework_missile.png");

   public FireworkMissileRenderer(EntityRendererManager renderManagerIn) 
   {
      super(renderManagerIn);
   }

   /**
    * Returns the location of an entity's texture.
    */
   public ResourceLocation getEntityTexture(FireworkMissileEntity entity) 
   {
      return MAIN_TEXTURE;
   }

   @Override
   public void render(FireworkMissileEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) 
   {
	      matrixStackIn.push();
	      matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) - 90.0F));
	      matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch)));

	      matrixStackIn.rotate(Vector3f.XP.rotationDegrees(45.0F));
	      matrixStackIn.scale(0.05625F, 0.05625F, 0.05625F);
	      matrixStackIn.translate(-4.0D, 0.0D, 0.0D);
	      IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityCutout(MAIN_TEXTURE));
	      MatrixStack.Entry matrixstack$entry = matrixStackIn.getLast();
	      Matrix4f matrix4f = matrixstack$entry.getMatrix();
	      Matrix3f matrix3f = matrixstack$entry.getNormal();
	      this.func_229039_a_(matrix4f, matrix3f, ivertexbuilder, -7, -2, -2, 0.0F, 0.15625F, -1, 0, 0, packedLightIn);
	      this.func_229039_a_(matrix4f, matrix3f, ivertexbuilder, -7, -2, 2, 0.15625F, 0.15625F, -1, 0, 0, packedLightIn);
	      this.func_229039_a_(matrix4f, matrix3f, ivertexbuilder, -7, 2, 2, 0.15625F, 0.3125F, -1, 0, 0, packedLightIn);
	      this.func_229039_a_(matrix4f, matrix3f, ivertexbuilder, -7, 2, -2, 0.0F, 0.3125F, -1, 0, 0, packedLightIn);
	      this.func_229039_a_(matrix4f, matrix3f, ivertexbuilder, -7, 2, -2, 0.0F, 0.15625F, 1, 0, 0, packedLightIn);
	      this.func_229039_a_(matrix4f, matrix3f, ivertexbuilder, -7, 2, 2, 0.15625F, 0.15625F, 1, 0, 0, packedLightIn);
	      this.func_229039_a_(matrix4f, matrix3f, ivertexbuilder, -7, -2, 2, 0.15625F, 0.3125F, 1, 0, 0, packedLightIn);
	      this.func_229039_a_(matrix4f, matrix3f, ivertexbuilder, -7, -2, -2, 0.0F, 0.3125F, 1, 0, 0, packedLightIn);

	      for(int j = 0; j < 4; ++j) 
	      {
	         matrixStackIn.rotate(Vector3f.XP.rotationDegrees(90.0F));
	         this.func_229039_a_(matrix4f, matrix3f, ivertexbuilder, -8, -2, 0, 0.0F, 0.0F, 0, 1, 0, packedLightIn);
	         this.func_229039_a_(matrix4f, matrix3f, ivertexbuilder, 8, -2, 0, 0.5F, 0.0F, 0, 1, 0, packedLightIn);
	         this.func_229039_a_(matrix4f, matrix3f, ivertexbuilder, 8, 2, 0, 0.5F, 0.15625F, 0, 1, 0, packedLightIn);
	         this.func_229039_a_(matrix4f, matrix3f, ivertexbuilder, -8, 2, 0, 0.0F, 0.15625F, 0, 1, 0, packedLightIn);
	      }

	      matrixStackIn.pop();
	      super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	   }

	   public void func_229039_a_(Matrix4f p_229039_1_, Matrix3f p_229039_2_, IVertexBuilder p_229039_3_, int p_229039_4_, int p_229039_5_, int p_229039_6_, float p_229039_7_, float p_229039_8_, int p_229039_9_, int p_229039_10_, int p_229039_11_, int p_229039_12_) 
	   {
	      p_229039_3_.pos(p_229039_1_, (float)p_229039_4_, (float)p_229039_5_, (float)p_229039_6_).color(255, 255, 255, 255).tex(p_229039_7_, p_229039_8_).overlay(OverlayTexture.NO_OVERLAY).lightmap(p_229039_12_).normal(p_229039_2_, (float)p_229039_9_, (float)p_229039_11_, (float)p_229039_10_).endVertex();
	   }
}