package fuzzyacornindustries.kindredlegacy.client.renderer.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fuzzyacornindustries.kindredlegacy.client.renderer.tileentity.model.XelNagaPylonModel;
import fuzzyacornindustries.kindredlegacy.common.tileentity.XelNagaPylonTileEntity;
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
public class XelNagaPylonRenderer extends TileEntityRenderer<XelNagaPylonTileEntity> 
{
	public static final ResourceLocation TEXTURE_MAIN = new ResourceLocation(Names.MOD_ID + ":" +"textures/block/xelnaga_pylon.png");
	public static final ResourceLocation TEXTURE_GLOWS = new ResourceLocation(Names.MOD_ID + ":" +"textures/block/xelnaga_pylon_glows.png");
	private final XelNagaPylonModel modelMain = new XelNagaPylonModel();
	private final XelNagaPylonModel modelGlows = new XelNagaPylonModel();

	public XelNagaPylonRenderer(TileEntityRendererDispatcher tileEntityRendererDispatcher) 
	{
		super(tileEntityRendererDispatcher);
	}

	@Override
	public void render(XelNagaPylonTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) 
	{
		matrixStackIn.push();
		matrixStackIn.translate(0.5F, 1.5F, 0.5F);
		matrixStackIn.rotate(new Quaternion(180.0F, 0.0F, 0.0F, true));

		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityCutout(TEXTURE_MAIN));
		this.modelMain.renderModel(matrixStackIn, ivertexbuilder, combinedLightIn, combinedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F, tileEntityIn);
		matrixStackIn.pop();

		if(!tileEntityIn.getWorld().isBlockPowered(tileEntityIn.getPos()))
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