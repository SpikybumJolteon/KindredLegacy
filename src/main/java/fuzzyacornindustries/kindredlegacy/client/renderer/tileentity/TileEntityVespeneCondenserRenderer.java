package fuzzyacornindustries.kindredlegacy.client.renderer.tileentity;

import org.lwjgl.opengl.GL11;

import fuzzyacornindustries.kindredlegacy.block.BlockVespeneCondenser;
import fuzzyacornindustries.kindredlegacy.client.model.block.ModelVespeneCondenser;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import fuzzyacornindustries.kindredlegacy.tileentity.TileEntityVespeneCondenser;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityVespeneCondenserRenderer extends TileEntitySpecialRenderer<TileEntityVespeneCondenser>
{
	private static final ResourceLocation TEXTURE_MAIN = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/blocks/vespene_condenser.png");
	private static final ResourceLocation TEXTURE_GLOWS = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/blocks/vespene_condenser_glows.png");
	private final ModelVespeneCondenser modelMain = new ModelVespeneCondenser();
	private final ModelVespeneCondenser modelGlows = new ModelVespeneCondenser();

	@Override
	public void render(TileEntityVespeneCondenser tileEntity, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		GlStateManager.pushMatrix();
		GlStateManager.translate((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
		GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);

		this.bindTexture(TEXTURE_MAIN);

		GlStateManager.enableCull();
		this.modelMain.renderModel(0.0625F, (TileEntityVespeneCondenser)tileEntity);
		GlStateManager.popMatrix();

		if(BlockVespeneCondenser.isPylonNearby(tileEntity.getWorld(), tileEntity.getPos()))
		{
			GlStateManager.pushMatrix();
			GlStateManager.translate((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
			GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);

			this.bindTexture(TEXTURE_GLOWS);

			float f1 = 1.0F;
			GlStateManager.enableBlend();
			GlStateManager.disableAlpha();
			GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
			GlStateManager.disableLighting();

			char c0 = 61680;
			int j = c0 % 65536;
			int k = c0 / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
			GlStateManager.enableLighting();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);

			GlStateManager.enableCull();
			this.modelGlows.renderModel(0.0625F, (TileEntityVespeneCondenser)tileEntity);

			GlStateManager.disableBlend();
			GlStateManager.enableAlpha();

			GlStateManager.popMatrix();
		}
	}
}