package fuzzyacornindustries.kindredlegacy.client.renderer.tileentity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fuzzyacornindustries.kindredlegacy.client.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.client.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.client.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.common.tileentity.XelNagaPylonTileEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class XelNagaPylonModel extends Model 
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public ModelRenderer core;
	PartInfo coreInfo;

	public ModelRenderer panelTop[][] = new ModelRenderer[6][2];
	PartInfo panelTopInfo[][] = new PartInfo[panelTop.length][panelTop[0].length];

	public ModelRenderer panelBot[][] = new ModelRenderer[6][2];
	PartInfo panelBotInfo[][] = new PartInfo[panelBot.length][panelBot[0].length];

	public XelNagaPylonModel() 
	{
		super(RenderType::getEntitySolid);

		this.textureWidth = 64;
		this.textureHeight = 32;

		int jointNumber = 0;
		int partNumber = 1;

		this.core = new ModelRenderer(this, 0, 0); // core1
		this.core.setRotationPoint(0.0F, 15.5F, 0.0F);
		this.core.addBox(-1.0F, -3.5F, -1.0F, 2, 1, 2, 0.0F);
		this.core.setTextureOffset(6, 1).addBox(-1.5F, -3.0F, -1.5F, 3, 1, 3, 0.0F); // core2
		this.core.setTextureOffset(15, 2).addBox(-2.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F); // core3
		this.core.setTextureOffset(28, 8).addBox(-1.5F, 1.5F, -1.5F, 3, 2, 3, 0.0F); // core4
		this.core.setTextureOffset(38, 6).addBox(-1.0F, 3.0F, -1.0F, 2, 2, 2, 0.0F); // core5
		this.core.setTextureOffset(45, 4).addBox(-0.5F, 5.0F, -0.5F, 1, 2, 1, 0.0F); // core6
		coreInfo = new PartInfo(core);

		int panelTopNumber = 0;
		this.panelTop[panelTopNumber][jointNumber] = new ModelRenderer(this, 2, 14); // panelTop1Joint
		this.panelTop[panelTopNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.panelTop[panelTopNumber][jointNumber].addBox(-0.5F, -3.0F, -6.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(panelTop[panelTopNumber][jointNumber], -0.3490658503988659F, 3.141592653589793F, 0.0F);
		panelTopInfo[panelTopNumber][jointNumber] = new PartInfo(panelTop[panelTopNumber][jointNumber]);
		this.panelTop[panelTopNumber][partNumber] = new ModelRenderer(this, 2, 14); // panelTop1Top
		this.panelTop[panelTopNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.panelTop[panelTopNumber][partNumber].addBox(-0.5F, -3.0F, -6.0F, 1, 2, 1, 0.0F);
		this.panelTop[panelTopNumber][partNumber].setTextureOffset(1, 17).addBox(-1.0F, -1.0F, -6.0F, 2, 2, 1, 0.0F); // panelTop1MidTop
		this.panelTop[panelTopNumber][partNumber].setTextureOffset(0, 20).addBox(-1.5F, 1.0F, -6.0F, 3, 2, 1, 0.0F); // panelTop1Mid
		this.panelTop[panelTopNumber][partNumber].setTextureOffset(1, 23).addBox(-1.0F, 3.0F, -6.0F, 2, 1, 1, 0.0F); // panelTop1Bot
		panelTopInfo[panelTopNumber][partNumber] = new PartInfo(panelTop[panelTopNumber][partNumber]);
		panelTopNumber++;
		this.panelTop[panelTopNumber][jointNumber] = new ModelRenderer(this, 20, 14); // panelTop2Joint
		this.panelTop[panelTopNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.panelTop[panelTopNumber][jointNumber].addBox(-0.5F, -3.0F, -6.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(panelTop[panelTopNumber][jointNumber], -0.3490658503988659F, 2.0943951023931953F, 0.0F);
		panelTopInfo[panelTopNumber][jointNumber] = new PartInfo(panelTop[panelTopNumber][jointNumber]);
		this.panelTop[panelTopNumber][partNumber] = new ModelRenderer(this, 11, 14); // panelTop2Top
		this.panelTop[panelTopNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.panelTop[panelTopNumber][partNumber].addBox(-0.5F, -3.0F, -6.0F, 1, 2, 1, 0.0F);
		this.panelTop[panelTopNumber][partNumber].setTextureOffset(10, 17).addBox(-1.0F, -1.0F, -6.0F, 2, 2, 1, 0.0F); // panelTop2MidTop
		this.panelTop[panelTopNumber][partNumber].setTextureOffset(9, 20).addBox(-1.5F, 1.0F, -6.0F, 3, 2, 1, 0.0F); // panelTop2Mid
		this.panelTop[panelTopNumber][partNumber].setTextureOffset(10, 23).addBox(-1.0F, 3.0F, -6.0F, 2, 1, 1, 0.0F); // panelTop2Bot
		panelTopInfo[panelTopNumber][partNumber] = new PartInfo(panelTop[panelTopNumber][partNumber]);
		panelTopNumber++;
		this.panelTop[panelTopNumber][jointNumber] = new ModelRenderer(this, 20, 14); // panelTop3Joint
		this.panelTop[panelTopNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.panelTop[panelTopNumber][jointNumber].addBox(-0.5F, -3.0F, -6.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(panelTop[panelTopNumber][jointNumber], -0.3490658503988659F, 1.0471975511965976F, 0.0F);
		panelTopInfo[panelTopNumber][jointNumber] = new PartInfo(panelTop[panelTopNumber][jointNumber]);
		this.panelTop[panelTopNumber][partNumber] = new ModelRenderer(this, 20, 14); // panelTop3Top
		this.panelTop[panelTopNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.panelTop[panelTopNumber][partNumber].addBox(-0.5F, -3.0F, -6.0F, 1, 2, 1, 0.0F);
		this.panelTop[panelTopNumber][partNumber].setTextureOffset(19, 17).addBox(-1.0F, -1.0F, -6.0F, 2, 2, 1, 0.0F); // panelTop3MidTop
		this.panelTop[panelTopNumber][partNumber].setTextureOffset(18, 20).addBox(-1.5F, 1.0F, -6.0F, 3, 2, 1, 0.0F); // panelTop3Mid
		this.panelTop[panelTopNumber][partNumber].setTextureOffset(19, 23).addBox(-1.0F, 3.0F, -6.0F, 2, 1, 1, 0.0F); // panelTop3Bot
		panelTopInfo[panelTopNumber][partNumber] = new PartInfo(panelTop[panelTopNumber][partNumber]);
		panelTopNumber++;
		this.panelTop[panelTopNumber][jointNumber] = new ModelRenderer(this, 29, 14); // panelTop4Joint
		this.panelTop[panelTopNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.panelTop[panelTopNumber][jointNumber].addBox(-0.5F, -3.0F, -6.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(panelTop[panelTopNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		panelTopInfo[panelTopNumber][jointNumber] = new PartInfo(panelTop[panelTopNumber][jointNumber]);
		this.panelTop[panelTopNumber][partNumber] = new ModelRenderer(this, 29, 14); // panelTop4Top
		this.panelTop[panelTopNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.panelTop[panelTopNumber][partNumber].addBox(-0.5F, -3.0F, -6.0F, 1, 2, 1, 0.0F);
		this.panelTop[panelTopNumber][partNumber].setTextureOffset(28, 17).addBox(-1.0F, -1.0F, -6.0F, 2, 2, 1, 0.0F); // panelTop4MidTop
		this.panelTop[panelTopNumber][partNumber].setTextureOffset(27, 20).addBox(-1.5F, 1.0F, -6.0F, 3, 2, 1, 0.0F); // panelTop4Mid
		this.panelTop[panelTopNumber][partNumber].setTextureOffset(28, 23).addBox(-1.0F, 3.0F, -6.0F, 2, 1, 1, 0.0F); // panelTop4Bot
		panelTopInfo[panelTopNumber][partNumber] = new PartInfo(panelTop[panelTopNumber][partNumber]);
		panelTopNumber++;
		this.panelTop[panelTopNumber][jointNumber] = new ModelRenderer(this, 38, 14); // panelTop5Joint
		this.panelTop[panelTopNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.panelTop[panelTopNumber][jointNumber].addBox(-0.5F, -3.0F, -6.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(panelTop[panelTopNumber][jointNumber], -0.3490658503988659F, -1.0471975511965976F, 0.0F);
		panelTopInfo[panelTopNumber][jointNumber] = new PartInfo(panelTop[panelTopNumber][jointNumber]);
		this.panelTop[panelTopNumber][partNumber] = new ModelRenderer(this, 38, 14); // panelTop5Top
		this.panelTop[panelTopNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.panelTop[panelTopNumber][partNumber].addBox(-0.5F, -3.0F, -6.0F, 1, 2, 1, 0.0F);
		this.panelTop[panelTopNumber][partNumber].setTextureOffset(37, 17).addBox(-1.0F, -1.0F, -6.0F, 2, 2, 1, 0.0F); // panelTop5MidTop
		this.panelTop[panelTopNumber][partNumber].setTextureOffset(36, 20).addBox(-1.5F, 1.0F, -6.0F, 3, 2, 1, 0.0F); // panelTop5Mid
		this.panelTop[panelTopNumber][partNumber].setTextureOffset(37, 23).addBox(-1.0F, 3.0F, -6.0F, 2, 1, 1, 0.0F); // panelTop5Bot
		panelTopInfo[panelTopNumber][partNumber] = new PartInfo(panelTop[panelTopNumber][partNumber]);
		panelTopNumber++;
		this.panelTop[panelTopNumber][jointNumber] = new ModelRenderer(this, 38, 14); // panelTop6Joint
		this.panelTop[panelTopNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.panelTop[panelTopNumber][jointNumber].addBox(-0.5F, -3.0F, -6.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(panelTop[panelTopNumber][jointNumber], -0.3490658503988659F, -2.0943951023931953F, 0.0F);
		panelTopInfo[panelTopNumber][jointNumber] = new PartInfo(panelTop[panelTopNumber][jointNumber]);
		this.panelTop[panelTopNumber][partNumber] = new ModelRenderer(this, 47, 14); // panelTop6Top
		this.panelTop[panelTopNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.panelTop[panelTopNumber][partNumber].addBox(-0.5F, -3.0F, -6.0F, 1, 2, 1, 0.0F);
		this.panelTop[panelTopNumber][partNumber].setTextureOffset(46, 17).addBox(-1.0F, -1.0F, -6.0F, 2, 2, 1, 0.0F); // panelTop6MidTop
		this.panelTop[panelTopNumber][partNumber].setTextureOffset(45, 20).addBox(-1.5F, 1.0F, -6.0F, 3, 2, 1, 0.0F); // panelTop6Mid
		this.panelTop[panelTopNumber][partNumber].setTextureOffset(46, 23).addBox(-1.0F, 3.0F, -6.0F, 2, 1, 1, 0.0F); // panelTop6Bot
		panelTopInfo[panelTopNumber][partNumber] = new PartInfo(panelTop[panelTopNumber][partNumber]);

		int panelBotNumber = 0;
		this.panelBot[panelBotNumber][jointNumber] = new ModelRenderer(this, 19, 26); // panelBot1Joint
		this.panelBot[panelBotNumber][jointNumber].setRotationPoint(0.0F, 1.5F, 0.0F);
		this.panelBot[panelBotNumber][jointNumber].addBox(-1.0F, -3.0F, -6.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(panelBot[panelBotNumber][jointNumber], 0.7853981633974483F, 3.141592653589793F, 0.0F);
		panelBotInfo[panelBotNumber][jointNumber] = new PartInfo(panelBot[panelBotNumber][jointNumber]);
		this.panelBot[panelBotNumber][partNumber] = new ModelRenderer(this, 1, 26); // panelBot1Top
		this.panelBot[panelBotNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.panelBot[panelBotNumber][partNumber].addBox(-1.0F, -3.0F, -6.0F, 2, 1, 1, 0.0F);
		this.panelBot[panelBotNumber][partNumber].setTextureOffset(0, 28).addBox(-1.5F, -2.0F, -6.0F, 3, 1, 1, 0.0F); // panelBot1Mid
		this.panelBot[panelBotNumber][partNumber].setTextureOffset(1, 30).addBox(-1.0F, -1.0F, -6.0F, 2, 1, 1, 0.0F); // panelBot1Bot
		panelBotInfo[panelBotNumber][partNumber] = new PartInfo(panelBot[panelBotNumber][partNumber]);
		panelBotNumber++;
		this.panelBot[panelBotNumber][jointNumber] = new ModelRenderer(this, 19, 26); // panelBot2Joint
		this.panelBot[panelBotNumber][jointNumber].setRotationPoint(0.0F, 1.5F, 0.0F);
		this.panelBot[panelBotNumber][jointNumber].addBox(-1.0F, -3.0F, -6.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(panelBot[panelBotNumber][jointNumber], 0.7853981633974483F, 2.0943951023931953F, 0.0F);
		panelBotInfo[panelBotNumber][jointNumber] = new PartInfo(panelBot[panelBotNumber][jointNumber]);
		this.panelBot[panelBotNumber][partNumber] = new ModelRenderer(this, 10, 26); // panelBot2Top
		this.panelBot[panelBotNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.panelBot[panelBotNumber][partNumber].addBox(-1.0F, -3.0F, -6.0F, 2, 1, 1, 0.0F);
		this.panelBot[panelBotNumber][partNumber].setTextureOffset(9, 28).addBox(-1.5F, -2.0F, -6.0F, 3, 1, 1, 0.0F); // panelBot2Mid
		this.panelBot[panelBotNumber][partNumber].setTextureOffset(10, 30).addBox(-1.0F, -1.0F, -6.0F, 2, 1, 1, 0.0F); // panelBot2Bot
		panelBotInfo[panelBotNumber][partNumber] = new PartInfo(panelBot[panelBotNumber][partNumber]);
		panelBotNumber++;
		this.panelBot[panelBotNumber][jointNumber] = new ModelRenderer(this, 19, 26); // panelBot3Joint
		this.panelBot[panelBotNumber][jointNumber].setRotationPoint(0.0F, 1.5F, 0.0F);
		this.panelBot[panelBotNumber][jointNumber].addBox(-1.0F, -3.0F, -6.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(panelBot[panelBotNumber][jointNumber], 0.7853981633974483F, 1.0471975511965976F, 0.0F);
		panelBotInfo[panelBotNumber][jointNumber] = new PartInfo(panelBot[panelBotNumber][jointNumber]);
		this.panelBot[panelBotNumber][partNumber] = new ModelRenderer(this, 19, 26); // panelBot3Top
		this.panelBot[panelBotNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.panelBot[panelBotNumber][partNumber].addBox(-1.0F, -3.0F, -6.0F, 2, 1, 1, 0.0F);
		this.panelBot[panelBotNumber][partNumber].setTextureOffset(18, 28).addBox(-1.5F, -2.0F, -6.0F, 3, 1, 1, 0.0F); // panelBot3Mid
		this.panelBot[panelBotNumber][partNumber].setTextureOffset(19, 30).addBox(-1.0F, -1.0F, -6.0F, 2, 1, 1, 0.0F); // panelBot3Bot
		panelBotInfo[panelBotNumber][partNumber] = new PartInfo(panelBot[panelBotNumber][partNumber]);
		panelBotNumber++;
		this.panelBot[panelBotNumber][jointNumber] = new ModelRenderer(this, 28, 26); // panelBot4Joint
		this.panelBot[panelBotNumber][jointNumber].setRotationPoint(0.0F, 1.5F, 0.0F);
		this.panelBot[panelBotNumber][jointNumber].addBox(-1.0F, -3.0F, -6.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(panelBot[panelBotNumber][jointNumber], 0.7853981633974483F, 0.0F, 0.0F);
		panelBotInfo[panelBotNumber][jointNumber] = new PartInfo(panelBot[panelBotNumber][jointNumber]);
		this.panelBot[panelBotNumber][partNumber] = new ModelRenderer(this, 28, 26); // panelBot4Top
		this.panelBot[panelBotNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.panelBot[panelBotNumber][partNumber].addBox(-1.0F, -3.0F, -6.0F, 2, 1, 1, 0.0F);
		this.panelBot[panelBotNumber][partNumber].setTextureOffset(27, 28).addBox(-1.5F, -2.0F, -6.0F, 3, 1, 1, 0.0F); // panelBot4Mid
		this.panelBot[panelBotNumber][partNumber].setTextureOffset(28, 30).addBox(-1.0F, -1.0F, -6.0F, 2, 1, 1, 0.0F); // panelBot4Bot
		panelBotInfo[panelBotNumber][partNumber] = new PartInfo(panelBot[panelBotNumber][partNumber]);
		panelBotNumber++;
		this.panelBot[panelBotNumber][jointNumber] = new ModelRenderer(this, 37, 26); // panelBot5Joint
		this.panelBot[panelBotNumber][jointNumber].setRotationPoint(0.0F, 1.5F, 0.0F);
		this.panelBot[panelBotNumber][jointNumber].addBox(-1.0F, -3.0F, -6.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(panelBot[panelBotNumber][jointNumber], 0.7853981633974483F, -1.0471975511965976F, 0.0F);
		panelBotInfo[panelBotNumber][jointNumber] = new PartInfo(panelBot[panelBotNumber][jointNumber]);
		this.panelBot[panelBotNumber][partNumber] = new ModelRenderer(this, 37, 26); // panelBot5Top
		this.panelBot[panelBotNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.panelBot[panelBotNumber][partNumber].addBox(-1.0F, -3.0F, -6.0F, 2, 1, 1, 0.0F);
		this.panelBot[panelBotNumber][partNumber].setTextureOffset(36, 28).addBox(-1.5F, -2.0F, -6.0F, 3, 1, 1, 0.0F); // panelBot5Mid
		this.panelBot[panelBotNumber][partNumber].setTextureOffset(37, 30).addBox(-1.0F, -1.0F, -6.0F, 2, 1, 1, 0.0F); // panelBot5Bot
		panelBotInfo[panelBotNumber][partNumber] = new PartInfo(panelBot[panelBotNumber][partNumber]);
		panelBotNumber++;
		this.panelBot[panelBotNumber][jointNumber] = new ModelRenderer(this, 37, 26); // panelBot6Joint
		this.panelBot[panelBotNumber][jointNumber].setRotationPoint(0.0F, 1.5F, 0.0F);
		this.panelBot[panelBotNumber][jointNumber].addBox(-1.0F, -3.0F, -6.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(panelBot[panelBotNumber][jointNumber], 0.7853981633974483F, -2.0943951023931953F, 0.0F);
		panelBotInfo[panelBotNumber][jointNumber] = new PartInfo(panelBot[panelBotNumber][jointNumber]);
		this.panelBot[panelBotNumber][partNumber] = new ModelRenderer(this, 46, 26); // panelBot6Top
		this.panelBot[panelBotNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.panelBot[panelBotNumber][partNumber].addBox(-1.0F, -3.0F, -6.0F, 2, 1, 1, 0.0F);
		this.panelBot[panelBotNumber][partNumber].setTextureOffset(45, 28).addBox(-1.5F, -2.0F, -6.0F, 3, 1, 1, 0.0F); // panelBot6Mid
		this.panelBot[panelBotNumber][partNumber].setTextureOffset(46, 30).addBox(-1.0F, -1.0F, -6.0F, 2, 1, 1, 0.0F); // panelBot6Bot
		panelBotInfo[panelBotNumber][partNumber] = new PartInfo(panelBot[panelBotNumber][partNumber]);

		for(int i = 0; i < panelTop.length; i++)
		{
			this.core.addChild(panelTop[i][0]);
			this.panelTop[i][0].addChild(this.panelTop[i][1]);
		}

		for(int i = 0; i < panelBot.length; i++)
		{
			this.core.addChild(panelBot[i][0]);
			this.panelBot[i][0].addChild(this.panelBot[i][1]);
		}

		animationDeployer = new AnimationDeployer(this);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) 
	{
		this.core.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);

	}

	public void renderModel(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha, XelNagaPylonTileEntity tileEntity)
	{
		animate(tileEntity);

		this.core.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) 
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void animate(XelNagaPylonTileEntity tileEntity)
	{	
		this.animationDeployer.update((IAnimatedEntity)tileEntity);
		resetPartInfos();

		animateVerticalShift((XelNagaPylonTileEntity)tileEntity);
		animateRotations((XelNagaPylonTileEntity)tileEntity);

		deployAnimations();
	}

	public void resetPartInfos()
	{
		coreInfo.resetNewPnt();

		for(int i = 0; i < panelTopInfo.length; i++)
		{
			panelTopInfo[i][0].resetNewAngles();
			panelTopInfo[i][1].resetNewAngles();
			panelTopInfo[i][0].resetNewPnt();
		}

		for(int i = 0; i < panelBotInfo.length; i++)
		{
			panelBotInfo[i][0].resetNewAngles();
			panelBotInfo[i][1].resetNewAngles();
			panelBotInfo[i][0].resetNewPnt();
		}
	}

	public void animateVerticalShift(XelNagaPylonTileEntity tileEntity)
	{
		IdleAnimationClock currentIdleAnimationClock = tileEntity.getIdleAnimationClockVerticalShift(0);

		float amplitudeShiftY = -1F;

		coreInfo.setNewPointY(coreInfo.getNewPointY() + 
				MathHelper.cos(2F * PI * currentIdleAnimationClock.getPhaseDurationCoveredY(0)) * amplitudeShiftY);

		currentIdleAnimationClock = tileEntity.getIdleAnimationClockVerticalShift(1);

		for(int i = 0; i < panelTopInfo.length; i++)
		{
			panelTopInfo[i][0].setNewPointY(panelTopInfo[i][0].getNewPointY() + 
					MathHelper.cos(2F * PI * currentIdleAnimationClock.getPhaseDurationCoveredY(0)) * amplitudeShiftY);
		}

		currentIdleAnimationClock = tileEntity.getIdleAnimationClockVerticalShift(2);

		for(int i = 0; i < panelBotInfo.length; i++)
		{
			panelBotInfo[i][0].setNewPointY(panelBotInfo[i][0].getNewPointY() + 
					MathHelper.cos(2F * PI * currentIdleAnimationClock.getPhaseDurationCoveredY(0)) * amplitudeShiftY);
		}
	}

	public void animateRotations(XelNagaPylonTileEntity tileEntity)
	{
		IdleAnimationClock currentIdleAnimationClock = tileEntity.getIdleAnimationClockRotation();

		for(int i = 0; i < panelTop.length; i++)
		{
			panelTopInfo[i][0].setNewRotateY(panelTopInfo[i][0].getNewRotateY() + (2F * PI * currentIdleAnimationClock.getPhaseDurationCoveredY(0)));
		}

		for(int i = 0; i < panelBotInfo.length; i++)
		{
			panelBotInfo[i][0].setNewRotateY((panelBotInfo[i][0].getNewRotateY()) - (2F * PI * currentIdleAnimationClock.getPhaseDurationCoveredY(0)));
		}
	}

	public void deployAnimations()
	{
		this.animationDeployer.move(core, coreInfo.getNewPnt());

		for(int i = 0; i < panelTop.length; i++)
		{
			this.animationDeployer.rotate(panelTop[i][0], panelTopInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(panelTop[i][1], panelTopInfo[i][1].getNewRotates());
			this.animationDeployer.move(panelTop[i][0], panelTopInfo[i][0].getNewPnt());
		}

		for(int i = 0; i < panelBot.length; i++)
		{
			this.animationDeployer.rotate(panelBot[i][0], panelBotInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(panelBot[i][1], panelBotInfo[i][1].getNewRotates());
			this.animationDeployer.move(panelBot[i][0], panelBotInfo[i][0].getNewPnt());
		}

		this.animationDeployer.applyChanges();
	}
}