package fuzzyacornindustries.kindredlegacy.client.renderer.tileentity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import fuzzyacornindustries.kindredlegacy.client.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.client.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.client.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.client.animation.Vector3f;
import fuzzyacornindustries.kindredlegacy.common.tileentity.VerdantPurifierTileEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class VerdantPurifierModel extends Model 
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public ModelRenderer leafJoint;

	public ModelRenderer leaf[] = new ModelRenderer[6];
	PartInfo leafInfo[] = new PartInfo[leaf.length];

	public ModelRenderer chamber;
	PartInfo chamberInfo;

	public ModelRenderer petalJoint;
	PartInfo petalJointInfo;

	public ModelRenderer petalSubJoint[] = new ModelRenderer[6];
	public ModelRenderer petalTop[] = new ModelRenderer[6];
	public ModelRenderer petalBot[] = new ModelRenderer[6];
	PartInfo petalSubJointInfo[] = new PartInfo[6];

	public VerdantPurifierModel() 
	{
		super(RenderType::getEntitySolid);

		this.textureWidth = 64;
		this.textureHeight = 64;

		this.leafJoint = new ModelRenderer(this, 42, 0);
		this.leafJoint.setRotationPoint(0.0F, 23.5F, 0.0F);
		this.leafJoint.addBox(-1.5F, -0.5F, -8.0F, 0, 0, 0, 0.0F);

		int leafNumber = 0;
		this.leaf[leafNumber] = new ModelRenderer(this, 42, 0); // leaf1
		this.leaf[leafNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leaf[leafNumber].addBox(-1.5F, -0.5F, -8.0F, 3, 1, 8, 0.0F);
		this.setRotateAngle(leaf[leafNumber], -0.2617993877991494F, 3.141592653589793F, 0.0F);
		leafNumber++;
		this.leaf[leafNumber] = new ModelRenderer(this, 42, 10); // leaf2
		this.leaf[leafNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leaf[leafNumber].addBox(-1.5F, -0.5F, -8.0F, 3, 1, 8, 0.0F);
		this.setRotateAngle(leaf[leafNumber], -0.2617993877991494F, 2.0943951023931953F, 0.0F);
		leafNumber++;
		this.leaf[leafNumber] = new ModelRenderer(this, 42, 20); // leaf3
		this.leaf[leafNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leaf[leafNumber].addBox(-1.5F, -0.5F, -8.0F, 3, 1, 8, 0.0F);
		this.setRotateAngle(leaf[leafNumber], -0.2617993877991494F, 1.0471975511965976F, 0.0F);
		leafNumber++;
		this.leaf[leafNumber] = new ModelRenderer(this, 42, 30); // leaf4
		this.leaf[leafNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leaf[leafNumber].addBox(-1.5F, -0.5F, -8.0F, 3, 1, 8, 0.0F);
		this.setRotateAngle(leaf[leafNumber], -0.2617993877991494F, 0.0F, 0.0F);
		leafNumber++;
		this.leaf[leafNumber] = new ModelRenderer(this, 42, 40); // leaf5
		this.leaf[leafNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leaf[leafNumber].addBox(-1.5F, -0.5F, -8.0F, 3, 1, 8, 0.0F);
		this.setRotateAngle(leaf[leafNumber], -0.2617993877991494F, -1.0471975511965976F, 0.0F);
		leafNumber++;
		this.leaf[leafNumber] = new ModelRenderer(this, 42, 50); // leaf6
		this.leaf[leafNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.leaf[leafNumber].addBox(-1.5F, -0.5F, -8.0F, 3, 1, 8, 0.0F);
		this.setRotateAngle(leaf[leafNumber], -0.2617993877991494F, -2.0943951023931953F, 0.0F);

		this.chamber = new ModelRenderer(this, 0, 16);
		this.chamber.setRotationPoint(0.0F, -5.0F, 0.0F);
		this.chamber.addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3, 0.0F);
		chamberInfo = new PartInfo(chamber);

		this.petalJoint = new ModelRenderer(this, 15, 0);
		this.petalJoint.setRotationPoint(0.0F, -1.5F, 0.0F);
		this.petalJoint.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
		petalJointInfo = new PartInfo(petalJoint);

		int petalNumber = 0;
		this.petalSubJoint[petalNumber] = new ModelRenderer(this, 15, 0); // petal1Joint
		this.petalSubJoint[petalNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.petalSubJoint[petalNumber].addBox(-0.6F, -8.7F, -5.8F, 0, 0, 0, 0.0F);
		this.setRotateAngle(petalSubJoint[petalNumber], 0.0F, 3.141592653589793F, 0.0F);
		petalSubJointInfo[petalNumber] = new PartInfo(petalSubJoint[petalNumber]);
		this.petalTop[petalNumber] = new ModelRenderer(this, 1, 0); // petal1Top
		this.petalTop[petalNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.petalTop[petalNumber].addBox(-0.5F, -8.7F, -5.8F, 1, 2, 1, 0.0F);
		this.setRotateAngle(petalTop[petalNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.petalTop[petalNumber].setTextureOffset(0, 3).addBox(-1.01F, -6.7F, -5.8F, 2, 5, 1, 0.0F); // petal1MidTop
		this.petalBot[petalNumber] = new ModelRenderer(this, 1, 13); // petal1Bot
		this.petalBot[petalNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.petalBot[petalNumber].addBox(-0.5F, -3.0F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(petalBot[petalNumber], 0.8726646259971648F, 0.0F, 0.0F);
		this.petalBot[petalNumber].setTextureOffset(0, 9).addBox(-1.0F, -6.0F, -0.5F, 2, 3, 1, 0.0F); // petal1Mid
		petalNumber++;
		this.petalSubJoint[petalNumber] = new ModelRenderer(this, 15, 0); // petal2Joint
		this.petalSubJoint[petalNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.petalSubJoint[petalNumber].addBox(-0.6F, -8.7F, -5.8F, 0, 0, 0, 0.0F);
		this.setRotateAngle(petalSubJoint[petalNumber], 0.0F, 2.0943951023931953F, 0.0F);
		petalSubJointInfo[petalNumber] = new PartInfo(petalSubJoint[petalNumber]);
		this.petalTop[petalNumber] = new ModelRenderer(this, 8, 0); // petal2Top
		this.petalTop[petalNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.petalTop[petalNumber].addBox(-0.5F, -8.7F, -5.8F, 1, 2, 1, 0.0F);
		this.setRotateAngle(petalTop[petalNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.petalTop[petalNumber].setTextureOffset(7, 3).addBox(-1.01F, -6.7F, -5.8F, 2, 5, 1, 0.0F); // petal2MidTop
		this.petalBot[petalNumber] = new ModelRenderer(this, 8, 13); // petal2Bot
		this.petalBot[petalNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.petalBot[petalNumber].addBox(-0.5F, -3.0F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(petalBot[petalNumber], 0.8726646259971648F, 0.0F, 0.0F);
		this.petalBot[petalNumber].setTextureOffset(7, 9).addBox(-1.0F, -6.0F, -0.5F, 2, 3, 1, 0.0F); // petal2Mid
		petalNumber++;
		this.petalSubJoint[petalNumber] = new ModelRenderer(this, 15, 0); // petal3Joint
		this.petalSubJoint[petalNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.petalSubJoint[petalNumber].addBox(-0.6F, -8.7F, -5.8F, 0, 0, 0, 0.0F);
		this.setRotateAngle(petalSubJoint[petalNumber], 0.0F, 1.0471975511965976F, 0.0F);
		petalSubJointInfo[petalNumber] = new PartInfo(petalSubJoint[petalNumber]);
		this.petalTop[petalNumber] = new ModelRenderer(this, 15, 0); // petal3Top
		this.petalTop[petalNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.petalTop[petalNumber].addBox(-0.5F, -8.7F, -5.8F, 1, 2, 1, 0.0F);
		this.setRotateAngle(petalTop[petalNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.petalTop[petalNumber].setTextureOffset(14, 3).addBox(-1.01F, -6.7F, -5.8F, 2, 5, 1, 0.0F); // petal3MidTop
		this.petalBot[petalNumber] = new ModelRenderer(this, 15, 13); // petal3Bot
		this.petalBot[petalNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.petalBot[petalNumber].addBox(-0.5F, -3.0F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(petalBot[petalNumber], 0.8726646259971648F, 0.0F, 0.0F);
		this.petalBot[petalNumber].setTextureOffset(14, 9).addBox(-1.0F, -6.0F, -0.5F, 2, 3, 1, 0.0F); // petal3Mid
		petalNumber++;
		this.petalSubJoint[petalNumber] = new ModelRenderer(this, 15, 0); // petal4Joint
		this.petalSubJoint[petalNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.petalSubJoint[petalNumber].addBox(-0.6F, -8.7F, -5.8F, 0, 0, 0, 0.0F);
		petalSubJointInfo[petalNumber] = new PartInfo(petalSubJoint[petalNumber]);
		this.petalTop[petalNumber] = new ModelRenderer(this, 22, 0); // petal4Top
		this.petalTop[petalNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.petalTop[petalNumber].addBox(-0.5F, -8.7F, -5.8F, 1, 2, 1, 0.0F);
		this.setRotateAngle(petalTop[petalNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.petalTop[petalNumber].setTextureOffset(21, 3).addBox(-1.01F, -6.7F, -5.8F, 2, 5, 1, 0.0F); // petal4MidTop
		this.petalBot[petalNumber] = new ModelRenderer(this, 22, 13); // petal4Bot
		this.petalBot[petalNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.petalBot[petalNumber].addBox(-0.5F, -3.0F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(petalBot[petalNumber], 0.8726646259971648F, 0.0F, 0.0F);
		this.petalBot[petalNumber].setTextureOffset(21, 9).addBox(-1.0F, -6.0F, -0.5F, 2, 3, 1, 0.0F); // petal4Mid
		petalNumber++;
		this.petalSubJoint[petalNumber] = new ModelRenderer(this, 15, 0); // petal5Joint
		this.petalSubJoint[petalNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.petalSubJoint[petalNumber].addBox(-0.6F, -8.7F, -5.8F, 0, 0, 0, 0.0F);
		this.setRotateAngle(petalSubJoint[petalNumber], 0.0F, -1.0471975511965976F, 0.0F);
		petalSubJointInfo[petalNumber] = new PartInfo(petalSubJoint[petalNumber]);
		this.petalTop[petalNumber] = new ModelRenderer(this, 29, 0); // petal5Top
		this.petalTop[petalNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.petalTop[petalNumber].addBox(-0.5F, -8.7F, -5.8F, 1, 2, 1, 0.0F);
		this.setRotateAngle(petalTop[petalNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.petalTop[petalNumber].setTextureOffset(28, 3).addBox(-1.01F, -6.7F, -5.8F, 2, 5, 1, 0.0F); // petal5MidTop
		this.petalBot[petalNumber] = new ModelRenderer(this, 29, 13); // petal5Bot
		this.petalBot[petalNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.petalBot[petalNumber].addBox(-0.5F, -3.0F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(petalBot[petalNumber], 0.8726646259971648F, 0.0F, 0.0F);
		this.petalBot[petalNumber].setTextureOffset(28, 9).addBox(-1.0F, -6.0F, -0.5F, 2, 3, 1, 0.0F); // petal5Mid
		petalNumber++;
		this.petalSubJoint[petalNumber] = new ModelRenderer(this, 15, 0); // petal6Joint
		this.petalSubJoint[petalNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.petalSubJoint[petalNumber].addBox(-0.6F, -8.7F, -5.8F, 0, 0, 0, 0.0F);
		this.setRotateAngle(petalSubJoint[petalNumber], 0.0F, -2.0943951023931953F, 0.0F);
		petalSubJointInfo[petalNumber] = new PartInfo(petalSubJoint[petalNumber]);
		this.petalTop[petalNumber] = new ModelRenderer(this, 36, 0); // petal6Top
		this.petalTop[petalNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.petalTop[petalNumber].addBox(-0.5F, -8.7F, -5.8F, 1, 2, 1, 0.0F);
		this.setRotateAngle(petalTop[petalNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.petalTop[petalNumber].setTextureOffset(35, 3).addBox(-1.01F, -6.7F, -5.8F, 2, 5, 1, 0.0F); // petal6MidTop
		this.petalBot[petalNumber] = new ModelRenderer(this, 36, 13); // petal6Bot
		this.petalBot[petalNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.petalBot[petalNumber].addBox(-0.5F, -3.0F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(petalBot[petalNumber], 0.8726646259971648F, 0.0F, 0.0F);
		this.petalBot[petalNumber].setTextureOffset(35, 9).addBox(-1.0F, -6.0F, -0.5F, 2, 3, 1, 0.0F); // petal6Mid

		this.leafJoint.addChild(this.petalJoint);

		for(int i = 0; i < leaf.length; i++)
		{
			this.leafJoint.addChild(this.leaf[i]);
		}

		this.petalJoint.addChild(this.chamber);

		for(int i = 0; i < petalSubJoint.length; i++)
		{
			this.petalJoint.addChild(this.petalSubJoint[i]);
		}

		for(int i = 0; i < petalTop.length; i++)
		{
			this.petalSubJoint[i].addChild(this.petalTop[i]);
			this.petalSubJoint[i].addChild(this.petalBot[i]);
		}

		animationDeployer = new AnimationDeployer(this);
	}


	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) 
	{
		this.leafJoint.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);

	}

	public void renderModel(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha, VerdantPurifierTileEntity tileEntity)
	{
		animate(tileEntity);

		this.leafJoint.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) 
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void animate(VerdantPurifierTileEntity tileEntity)
	{	
		this.animationDeployer.update((IAnimatedEntity)tileEntity);
		resetPartInfos();

		animateVerticalShift((VerdantPurifierTileEntity)tileEntity);
		animateChamber((VerdantPurifierTileEntity)tileEntity);
		animatePetals((VerdantPurifierTileEntity)tileEntity);

		deployAnimations();
	}

	public void resetPartInfos()
	{
		chamberInfo.resetNewAngles();
		chamberInfo.resetNewPnt();

		petalJointInfo.resetNewAngles();
		petalJointInfo.resetNewPnt();

		for(int i = 0; i < petalSubJointInfo.length; i++)
		{
			petalSubJointInfo[i].resetNewAngles();
			petalSubJointInfo[i].resetNewPnt();
		}
	}

	public void animateVerticalShift(VerdantPurifierTileEntity tileEntity)
	{
		IdleAnimationClock currentIdleAnimationClock = tileEntity.getIdleAnimationClockVerticalShift(0);

		float amplitudeShiftY = -2.0F;

		petalJointInfo.setNewPnt(new Vector3f(petalJointInfo.getNewPnt().getX(),
				(petalJointInfo.getNewPnt().getY() + MathHelper.cos(2F * PI * currentIdleAnimationClock.getPhaseDurationCoveredY(0)) * amplitudeShiftY - 2.0F),
				petalJointInfo.getNewPnt().getZ()));

		float amplitudeShiftX = -1.0F;

		currentIdleAnimationClock = tileEntity.getIdleAnimationClockVerticalShift(1);

		chamberInfo.setNewPnt(new Vector3f(chamberInfo.getNewPnt().getX() + MathHelper.cos(2F * PI * currentIdleAnimationClock.getPhaseDurationCoveredX(0) + PI/2) * amplitudeShiftX,
				(chamberInfo.getNewPnt().getY() + MathHelper.cos(2F * PI * currentIdleAnimationClock.getPhaseDurationCoveredY(0)) * amplitudeShiftY * 0.8F),
				chamberInfo.getNewPnt().getZ()));
	}

	public void animateChamber(VerdantPurifierTileEntity tileEntity)
	{
		IdleAnimationClock currentIdleAnimationClock = tileEntity.getIdleAnimationClockChamber();

		float angleChangeX = currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2;
		float angleChangeY = currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2;
		float angleChangeZ = currentIdleAnimationClock.getPhaseDurationCoveredZ(0) * PI * 2;

		float newAngleX = chamberInfo.getNewRotates().getX() + angleChangeX;
		float newAngleY = chamberInfo.getNewRotates().getY() + angleChangeY;
		float newAngleZ = chamberInfo.getNewRotates().getZ() + angleChangeZ;

		chamberInfo.setNewRotatesWithVector(new Vector3f(newAngleX, newAngleY, newAngleZ));
	}

	public void animatePetals(VerdantPurifierTileEntity tileEntity)
	{
		IdleAnimationClock currentIdleAnimationClock = tileEntity.getIdleAnimationClockPetals();

		float maxAngleChangeX = (float)Math.toRadians(15);

		// + (float)Math.toRadians(45)
		float angleChangeX = 0;

		if(currentIdleAnimationClock.getPhaseDurationCoveredX(0) < 1F)
		{
			angleChangeX += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI + PI) * maxAngleChangeX + maxAngleChangeX;
		}
		else if(currentIdleAnimationClock.getPhaseDurationCoveredX(1) < 1F)
		{
			angleChangeX += 2 * maxAngleChangeX;
		}
		else if(currentIdleAnimationClock.getPhaseDurationCoveredX(2) < 1F)
		{
			angleChangeX += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(2) * PI) * maxAngleChangeX + maxAngleChangeX;
		}
		else
		{
			angleChangeX += 0;
		}

		for(int i = 0; i < petalSubJoint.length; i++)
		{
			petalSubJointInfo[i].setNewRotateX(petalSubJointInfo[i].getNewRotateX() + angleChangeX);
			petalSubJointInfo[i].setNewRotateY(petalSubJointInfo[i].getNewRotateY() + (2F * PI * currentIdleAnimationClock.getPhaseDurationCoveredY(0)));
		}
	}

	public void deployAnimations()
	{
		this.animationDeployer.rotate(chamber, chamberInfo.getNewRotates());
		this.animationDeployer.move(chamber, chamberInfo.getNewPnt());

		this.animationDeployer.move(petalJoint, petalJointInfo.getNewPnt());
		this.animationDeployer.rotate(petalJoint, petalJointInfo.getNewRotates());

		for(int i = 0; i < petalSubJoint.length; i++)
		{
			this.animationDeployer.rotate(petalSubJoint[i], petalSubJointInfo[i].getNewRotates());
		}

		this.animationDeployer.applyChanges();
	}
}