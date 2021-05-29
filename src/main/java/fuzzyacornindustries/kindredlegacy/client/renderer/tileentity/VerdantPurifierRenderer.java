package fuzzyacornindustries.kindredlegacy.client.renderer.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fuzzyacornindustries.kindredlegacy.client.renderer.tileentity.model.VerdantPurifierModel;
import fuzzyacornindustries.kindredlegacy.common.tileentity.VerdantPurifierTileEntity;
import fuzzyacornindustries.kindredlegacy.lib.Names;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VerdantPurifierRenderer extends TileEntityRenderer<VerdantPurifierTileEntity> 
{
	public static final ResourceLocation TEXTURE_MAIN = new ResourceLocation(Names.MOD_ID + ":" +"textures/block/verdant_purifier.png");
	public static final ResourceLocation TEXTURE_GLOWS = new ResourceLocation(Names.MOD_ID + ":" +"textures/block/verdant_purifier_glows.png");
	private final VerdantPurifierModel modelMain = new VerdantPurifierModel();
	private final VerdantPurifierModel modelGlows = new VerdantPurifierModel();

	public VerdantPurifierRenderer(TileEntityRendererDispatcher tileEntityRendererDispatcher) 
	{
		super(tileEntityRendererDispatcher);
	}

	@Override
	public void render(VerdantPurifierTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) 
	{
		matrixStackIn.push();
		matrixStackIn.translate(0.5F, 1.5F, 0.5F);
		matrixStackIn.rotate(new Quaternion(180.0F, 0.0F, 0.0F, true));

		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityCutout(TEXTURE_MAIN));
		this.modelMain.renderModel(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F, tileEntityIn);
		matrixStackIn.pop();

		if(tileEntityIn.isPowered())
		{
			matrixStackIn.push();
			matrixStackIn.translate(0.5F, 1.5F, 0.5F);
			matrixStackIn.rotate(new Quaternion(180.0F, 0.0F, 0.0F, true));

			IVertexBuilder ivertexbuilder2 = bufferIn.getBuffer(RenderType.getEyes(TEXTURE_GLOWS));
			this.modelGlows.renderModel(matrixStackIn, ivertexbuilder2, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F, tileEntityIn);
			matrixStackIn.pop();
		}
	}
}