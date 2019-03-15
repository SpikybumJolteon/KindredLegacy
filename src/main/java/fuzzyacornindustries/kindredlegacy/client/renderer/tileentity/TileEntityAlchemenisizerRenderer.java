package fuzzyacornindustries.kindredlegacy.client.renderer.tileentity;

import org.lwjgl.opengl.GL11;

import fuzzyacornindustries.kindredlegacy.block.BlockAlchemenisizer;
import fuzzyacornindustries.kindredlegacy.client.model.block.ModelAlchemenisizer;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import fuzzyacornindustries.kindredlegacy.tileentity.TileEntityAlchemenisizer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityAlchemenisizerRenderer extends TileEntitySpecialRenderer<TileEntityAlchemenisizer>
{
	private static final ResourceLocation TEXTURE_MAIN = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/blocks/alchemenisizer.png");
	private static final ResourceLocation TEXTURE_GLOWS = new ResourceLocation(ModInfo.MOD_ID + ":" +"textures/blocks/alchemenisizer_glows.png");
	private final ModelAlchemenisizer modelMain = new ModelAlchemenisizer();
	private final ModelAlchemenisizer modelGlows = new ModelAlchemenisizer();

	@Override
	public void render(TileEntityAlchemenisizer tileEntity, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		GlStateManager.pushMatrix();
		GlStateManager.translate((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
		GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);

		this.bindTexture(TEXTURE_MAIN);

		GlStateManager.enableCull();
		this.modelMain.renderModel(0.0625F, (TileEntityAlchemenisizer)tileEntity);
		GlStateManager.popMatrix();

		if(BlockAlchemenisizer.isPylonNearby(tileEntity.getWorld(), tileEntity.getPos()))
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
			//GL11.glEnable(GL11.GL_BLEND);
			//GL11.glDisable(GL11.GL_ALPHA_TEST);
			//GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
			//GL11.glDisable(GL11.GL_LIGHTING);
			char c0 = 61680;
			int j = c0 % 65536;
			int k = c0 / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
			GlStateManager.enableLighting();
			//GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);

			GlStateManager.enableCull();
			this.modelGlows.renderModel(0.0625F, (TileEntityAlchemenisizer)tileEntity);

			GlStateManager.disableBlend();
			GlStateManager.enableAlpha();
			//GL11.glEnable(GL11.GL_ALPHA_TEST);
			//GL11.glDisable(GL11.GL_BLEND);
			GlStateManager.popMatrix();
		}
		/*
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
		GL11.glRotatef(180, 0F, 0F, 1F);

		this.bindTexture(TEXTURE_MAIN);

		GL11.glPushMatrix();
		this.modelMain.renderModel(0.0625F, (TileEntityAlchemenisizer)tileEntity);
		GL11.glPopMatrix();
		GL11.glPopMatrix();

		/* ************ */
		/*
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
		GL11.glRotatef(180, 0F, 0F, 1F);

		this.bindTexture(TEXTURE_GLOWS);

		float f1 = 1.0F;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
		GL11.glDisable(GL11.GL_LIGHTING);
		char c0 = 61680;
		int j = c0 % 65536;
		int k = c0 / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j / 1.0F, (float)k / 1.0F);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);

		GL11.glPushMatrix();
		this.modelGlows.renderModel(0.0625F, (TileEntityAlchemenisizer)tileEntity);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
		GL11.glPopMatrix();*/
	}
}