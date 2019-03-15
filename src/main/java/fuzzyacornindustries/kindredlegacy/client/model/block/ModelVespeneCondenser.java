package fuzzyacornindustries.kindredlegacy.client.model.block;

import fuzzyacornindustries.kindredlegacy.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.animation.Vector3f;
import fuzzyacornindustries.kindredlegacy.tileentity.TileEntityVespeneCondenser;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelVespeneCondenser extends ModelBase 
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public ModelRenderer base;
	PartInfo baseInfo;

	public ModelRenderer core;
	PartInfo coreInfo;

	public ModelRenderer panelRotate;
	PartInfo panelRotateInfo;

	public ModelRenderer panel1Rotate;
	PartInfo panel1RotateInfo;

	public ModelRenderer panel1Rotative[] = new ModelRenderer[4];
	PartInfo panel1RotativeInfo[] = new PartInfo[panel1Rotative.length];

	public ModelRenderer panel1Side;

	public ModelRenderer panel2Rotate;
	PartInfo panel2RotateInfo;

	public ModelRenderer panel2Rotative[] = new ModelRenderer[4];
	PartInfo panel2RotativeInfo[] = new PartInfo[panel2Rotative.length];

	public ModelRenderer panel2Side;

	public ModelRenderer panel3Rotate;
	PartInfo panel3RotateInfo;

	public ModelRenderer panel3Rotative[] = new ModelRenderer[4];
	PartInfo panel3RotativeInfo[] = new PartInfo[panel3Rotative.length];

	public ModelRenderer panel3Side;

	public ModelVespeneCondenser() 
	{
		this.textureWidth = 64;
		this.textureHeight = 32;

		this.base = new ModelRenderer(this, 0, 0);
		this.base.setRotationPoint(0.0F, 21.0F, 0.0F);
		this.base.addBox(-2.0F, -1.5F, -2.0F, 4, 3, 4, 0.0F);
		baseInfo = new PartInfo(base);

		this.core = new ModelRenderer(this, 0, 8);
		this.core.setRotationPoint(0.0F, -6.0F, 0.0F);
		this.core.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
		coreInfo = new PartInfo(core);

		this.panelRotate = new ModelRenderer(this, 0, 0);
		this.panelRotate.setRotationPoint(0.0F, -6.0F, 0.0F);
		this.panelRotate.addBox(-2.0F, -1.5F, -2.0F, 0, 0, 0, 0.0F);
		panelRotateInfo = new PartInfo(panelRotate);

		this.panel1Rotate = new ModelRenderer(this, 0, 0);
		this.panel1Rotate.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.panel1Rotate.addBox(-2.0F, -1.5F, -2.0F, 0, 0, 0, 0.0F);
		panel1RotateInfo = new PartInfo(panel1Rotate);

		int panel1RotativeNumber = 0;
		this.panel1Rotative[panel1RotativeNumber] = new ModelRenderer(this, 0, 19); // panel1Rotative1
		this.panel1Rotative[panel1RotativeNumber].setRotationPoint(2.5F, -4.0F, -3.5F);
		this.panel1Rotative[panel1RotativeNumber].addBox(-0.5F, -0.5F, -2.5F, 1, 1, 5, 0.0F);
		panel1RotativeInfo[panel1RotativeNumber] = new PartInfo(panel1Rotative[panel1RotativeNumber]);
		panel1RotativeNumber++;
		this.panel1Rotative[panel1RotativeNumber] = new ModelRenderer(this, 0, 25); // panel1Rotative2
		this.panel1Rotative[panel1RotativeNumber].setRotationPoint(2.5F, -2.0F, -3.5F);
		this.panel1Rotative[panel1RotativeNumber].addBox(-0.5F, -0.5F, -2.5F, 1, 1, 5, 0.0F);
		panel1RotativeInfo[panel1RotativeNumber] = new PartInfo(panel1Rotative[panel1RotativeNumber]);
		panel1RotativeNumber++;
		this.panel1Rotative[panel1RotativeNumber] = new ModelRenderer(this, 10, 26); // panel1Rotative3
		this.panel1Rotative[panel1RotativeNumber].setRotationPoint(2.5F, 0.0F, -3.5F);
		this.panel1Rotative[panel1RotativeNumber].addBox(-0.5F, -0.5F, -2.5F, 1, 1, 5, 0.0F);
		panel1RotativeInfo[panel1RotativeNumber] = new PartInfo(panel1Rotative[panel1RotativeNumber]);
		panel1RotativeNumber++;
		this.panel1Rotative[panel1RotativeNumber] = new ModelRenderer(this, 17, 25); // panel1Rotative4
		this.panel1Rotative[panel1RotativeNumber].setRotationPoint(2.5F, 2.0F, -3.5F);
		this.panel1Rotative[panel1RotativeNumber].addBox(-0.5F, -0.5F, -2.5F, 1, 1, 5, 0.0F);
		panel1RotativeInfo[panel1RotativeNumber] = new PartInfo(panel1Rotative[panel1RotativeNumber]);

		this.panel1Side = new ModelRenderer(this, 60, 11); // panel1Side
		this.panel1Side.setRotationPoint(0.0F, -1.0F, 0.0F);
		this.panel1Side.addBox(2.0F, -4.5F, -1.0F, 1, 9, 1, 0.0F);
		this.panel1Side.setTextureOffset(60, 21).addBox(2.0F, -4.5F, -7.0F, 1, 9, 1, 0.0F); // panel1SideOuter
		this.panel1Side.setTextureOffset(24, 26).addBox(2.0F, -4.5F, -6.0F, 1, 1, 5, 0.0F); // panel1Base1
		this.panel1Side.setTextureOffset(31, 25).addBox(2.0F, -2.5F, -6.0F, 1, 1, 5, 0.0F); // panel1Base2
		this.panel1Side.setTextureOffset(38, 26).addBox(2.0F, -0.5F, -6.0F, 1, 1, 5, 0.0F); // panel1Base3
		this.panel1Side.setTextureOffset(45, 25).addBox(2.0F, 1.5F, -6.0F, 1, 1, 5, 0.0F); // panel1Base4
		this.panel1Side.setTextureOffset(52, 26).addBox(2.0F, 3.5F, -6.0F, 1, 1, 5, 0.0F); // panel1Base5

		this.panel2Rotate = new ModelRenderer(this, 0, 0);
		this.panel2Rotate.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.panel2Rotate.addBox(-2.0F, -1.5F, -2.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(panel2Rotate, 0.0F, 2.0943951023931953F, 0.0F);
		panel2RotateInfo = new PartInfo(panel2Rotate);

		int panel2RotativeNumber = 0;
		this.panel2Rotative[panel2RotativeNumber] = new ModelRenderer(this, 0, 19); // panel2Rotative1
		this.panel2Rotative[panel2RotativeNumber].setRotationPoint(2.5F, -4.0F, -3.5F);
		this.panel2Rotative[panel2RotativeNumber].addBox(-0.5F, -0.5F, -2.5F, 1, 1, 5, 0.0F);
		panel2RotativeInfo[panel2RotativeNumber] = new PartInfo(panel2Rotative[panel2RotativeNumber]);
		panel2RotativeNumber++;
		this.panel2Rotative[panel2RotativeNumber] = new ModelRenderer(this, 0, 25); // panel2Rotative2
		this.panel2Rotative[panel2RotativeNumber].setRotationPoint(2.5F, -2.0F, -3.5F);
		this.panel2Rotative[panel2RotativeNumber].addBox(-0.5F, -0.5F, -2.5F, 1, 1, 5, 0.0F);
		panel2RotativeInfo[panel2RotativeNumber] = new PartInfo(panel2Rotative[panel2RotativeNumber]);
		panel2RotativeNumber++;
		this.panel2Rotative[panel2RotativeNumber] = new ModelRenderer(this, 10, 26); // panel2Rotative3
		this.panel2Rotative[panel2RotativeNumber].setRotationPoint(2.5F, 0.0F, -3.5F);
		this.panel2Rotative[panel2RotativeNumber].addBox(-0.5F, -0.5F, -2.5F, 1, 1, 5, 0.0F);
		panel2RotativeInfo[panel2RotativeNumber] = new PartInfo(panel2Rotative[panel2RotativeNumber]);
		panel2RotativeNumber++;
		this.panel2Rotative[panel2RotativeNumber] = new ModelRenderer(this, 17, 25); // panel2Rotative4
		this.panel2Rotative[panel2RotativeNumber].setRotationPoint(2.5F, 2.0F, -3.5F);
		this.panel2Rotative[panel2RotativeNumber].addBox(-0.5F, -0.5F, -2.5F, 1, 1, 5, 0.0F);
		panel2RotativeInfo[panel2RotativeNumber] = new PartInfo(panel2Rotative[panel2RotativeNumber]);

		this.panel2Side = new ModelRenderer(this, 60, 11); // panel2SideInner
		this.panel2Side.setRotationPoint(0.0F, -1.0F, 0.0F);
		this.panel2Side.addBox(2.0F, -4.5F, -1.0F, 1, 9, 1, 0.0F);
		this.panel2Side.setTextureOffset(60, 21).addBox(2.0F, -4.5F, -7.0F, 1, 9, 1, 0.0F); // panel2SideOuter
		this.panel2Side.setTextureOffset(24, 26).addBox(2.0F, -4.5F, -6.0F, 1, 1, 5, 0.0F); // panel2Base1
		this.panel2Side.setTextureOffset(31, 25).addBox(2.0F, -2.5F, -6.0F, 1, 1, 5, 0.0F); // panel2Base2
		this.panel2Side.setTextureOffset(38, 26).addBox(2.0F, -0.5F, -6.0F, 1, 1, 5, 0.0F); // panel2Base3
		this.panel2Side.setTextureOffset(45, 25).addBox(2.0F, 1.5F, -6.0F, 1, 1, 5, 0.0F); // panel2Base4
		this.panel2Side.setTextureOffset(52, 26).addBox(2.0F, 3.5F, -6.0F, 1, 1, 5, 0.0F); // panel2Base5

		this.panel3Rotate = new ModelRenderer(this, 0, 0);
		this.panel3Rotate.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.panel3Rotate.addBox(-2.0F, -1.5F, -2.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(panel3Rotate, 0.0F, 4.1887902047863905F, 0.0F);
		panel3RotateInfo = new PartInfo(panel3Rotate);

		int panel3RotativeNumber = 0;
		this.panel3Rotative[panel3RotativeNumber] = new ModelRenderer(this, 0, 19); // panel3Rotative1
		this.panel3Rotative[panel3RotativeNumber].setRotationPoint(2.5F, -4.0F, -3.5F);
		this.panel3Rotative[panel3RotativeNumber].addBox(-0.5F, -0.5F, -2.5F, 1, 1, 5, 0.0F);
		panel3RotativeInfo[panel3RotativeNumber] = new PartInfo(panel3Rotative[panel3RotativeNumber]);
		panel3RotativeNumber++;
		this.panel3Rotative[panel3RotativeNumber] = new ModelRenderer(this, 0, 25); // panel3Rotative2
		this.panel3Rotative[panel3RotativeNumber].setRotationPoint(2.5F, -2.0F, -3.5F);
		this.panel3Rotative[panel3RotativeNumber].addBox(-0.5F, -0.5F, -2.5F, 1, 1, 5, 0.0F);
		panel3RotativeInfo[panel3RotativeNumber] = new PartInfo(panel3Rotative[panel3RotativeNumber]);
		panel3RotativeNumber++;
		this.panel3Rotative[panel3RotativeNumber] = new ModelRenderer(this, 10, 26); // panel3Rotative3
		this.panel3Rotative[panel3RotativeNumber].setRotationPoint(2.5F, 0.0F, -3.5F);
		this.panel3Rotative[panel3RotativeNumber].addBox(-0.5F, -0.5F, -2.5F, 1, 1, 5, 0.0F);
		panel3RotativeInfo[panel3RotativeNumber] = new PartInfo(panel3Rotative[panel3RotativeNumber]);
		panel3RotativeNumber++;
		this.panel3Rotative[panel3RotativeNumber] = new ModelRenderer(this, 17, 25); // panel3Rotative4
		this.panel3Rotative[panel3RotativeNumber].setRotationPoint(2.5F, 2.0F, -3.5F);
		this.panel3Rotative[panel3RotativeNumber].addBox(-0.5F, -0.5F, -2.5F, 1, 1, 5, 0.0F);
		panel3RotativeInfo[panel3RotativeNumber] = new PartInfo(panel3Rotative[panel3RotativeNumber]);

		this.panel3Side = new ModelRenderer(this, 60, 11); // panel3SideInner
		this.panel3Side.setRotationPoint(0.0F, -1.0F, 0.0F);
		this.panel3Side.addBox(2.0F, -4.5F, -1.0F, 1, 9, 1, 0.0F);
		this.panel3Side.setTextureOffset(60, 21).addBox(2.0F, -4.5F, -7.0F, 1, 9, 1, 0.0F); // panel3SideOuter
		this.panel3Side.setTextureOffset(24, 26).addBox(2.0F, -4.5F, -6.0F, 1, 1, 5, 0.0F); // panel3Base1
		this.panel3Side.setTextureOffset(31, 25).addBox(2.0F, -2.5F, -6.0F, 1, 1, 5, 0.0F); // panel3Base2
		this.panel3Side.setTextureOffset(38, 26).addBox(2.0F, -0.5F, -6.0F, 1, 1, 5, 0.0F); // panel3Base3
		this.panel3Side.setTextureOffset(45, 25).addBox(2.0F, 1.5F, -6.0F, 1, 1, 5, 0.0F); // panel3Base4
		this.panel3Side.setTextureOffset(52, 26).addBox(2.0F, 3.5F, -6.0F, 1, 1, 5, 0.0F); // panel3Base5

		this.base.addChild(this.core);
		this.base.addChild(this.panelRotate);

		this.panelRotate.addChild(this.panel1Rotate);
		this.panelRotate.addChild(this.panel2Rotate);
		this.panelRotate.addChild(this.panel3Rotate);

		this.panel1Rotate.addChild(this.panel1Side);

		this.panel2Rotate.addChild(this.panel2Side);

		this.panel3Rotate.addChild(this.panel3Side);

		for(int i = 0; i < panel1Rotative.length; i++)
		{
			this.panel1Rotate.addChild(this.panel1Rotative[i]);
			this.panel2Rotate.addChild(this.panel2Rotative[i]);
			this.panel3Rotate.addChild(this.panel3Rotative[i]);
		}

		animationDeployer = new AnimationDeployer(this);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) 
	{
		this.base.render(f5);
	}

	public void renderModel(float f, TileEntityVespeneCondenser tileEntity)
	{
		animate(tileEntity);

		this.base.render(f);
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) 
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void resetPartInfos()
	{
		baseInfo.resetNewPnt();
		coreInfo.resetNewPnt();

		for(int i = 0; i < panel1RotativeInfo.length; i++)
		{
			panel1RotativeInfo[i].resetNewAngles();
			panel2RotativeInfo[i].resetNewAngles();
			panel3RotativeInfo[i].resetNewAngles();
		}

		panel1RotateInfo.resetNewPnt();
		panel2RotateInfo.resetNewPnt();
		panel3RotateInfo.resetNewPnt();

		panelRotateInfo.resetNewAngles();
	}

	public void animate(TileEntityVespeneCondenser tileEntity)
	{	
		/* Static references to array lengths in Entity class from Model class
		 * makes the glow/normal models not animate in sync. */
		this.animationDeployer.update((IAnimatedEntity)tileEntity);
		resetPartInfos();

		animateVerticalShift((TileEntityVespeneCondenser)tileEntity);
		animatePanelRotation((TileEntityVespeneCondenser)tileEntity);

		deployAnimations();
	}

	public void animateVerticalShift(TileEntityVespeneCondenser tileEntity)
	{
		IdleAnimationClock currentIdleAnimationClock = tileEntity.getIdleAnimationClockVerticalShift(0);

		float amplitudeShiftY = -1F;

		baseInfo.setNewPnt(new Vector3f(baseInfo.getNewPointX(),
				(baseInfo.getNewPointY() + MathHelper.cos(2F * PI * currentIdleAnimationClock.getPhaseDurationCoveredY(0)) * amplitudeShiftY),
				baseInfo.getNewPointZ()));

		currentIdleAnimationClock = tileEntity.getIdleAnimationClockVerticalShift(1);

		panel1RotateInfo.setNewPnt(new Vector3f(panel1RotateInfo.getNewPointX(),
				(panel1RotateInfo.getNewPointY() + MathHelper.cos(2F * PI * currentIdleAnimationClock.getPhaseDurationCoveredY(0)) * amplitudeShiftY),
				panel1RotateInfo.getNewPointZ()));

		currentIdleAnimationClock = tileEntity.getIdleAnimationClockVerticalShift(2);

		panel2RotateInfo.setNewPnt(new Vector3f(panel2RotateInfo.getNewPointX(),
				(panel2RotateInfo.getNewPointY() + MathHelper.cos(2F * PI * currentIdleAnimationClock.getPhaseDurationCoveredY(0)) * amplitudeShiftY),
				panel2RotateInfo.getNewPointZ()));

		currentIdleAnimationClock = tileEntity.getIdleAnimationClockVerticalShift(3);

		panel3RotateInfo.setNewPnt(new Vector3f(panel3RotateInfo.getNewPointX(),
				(panel3RotateInfo.getNewPointY() + MathHelper.cos(2F * PI * currentIdleAnimationClock.getPhaseDurationCoveredY(0)) * amplitudeShiftY),
				panel3RotateInfo.getNewPointZ()));

		currentIdleAnimationClock = tileEntity.getIdleAnimationClockCoreShift();

		amplitudeShiftY = 5F;

		coreInfo.setNewPnt(new Vector3f(coreInfo.getNewPnt().getX(),
				(coreInfo.getNewPnt().getY() + MathHelper.cos(2F * PI * currentIdleAnimationClock.getPhaseDurationCoveredY(0)) * amplitudeShiftY + 1F),
				coreInfo.getNewPnt().getZ()));

		coreInfo.assignNewRotationPointToPart(core);
	}

	public void animatePanelRotation(TileEntityVespeneCondenser tileEntity)
	{
		IdleAnimationClock currentIdleAnimationClock = tileEntity.getIdleAnimationClockRotation(0);

		panelRotateInfo.setNewRotateY((panelRotateInfo.getNewRotateY()) - (2F * PI * currentIdleAnimationClock.getPhaseDurationCoveredY(0)));

		for(int i = 0; i < panel1Rotative.length; i++)
		{
				float defaultAngleMultiple = (float)Math.toRadians(5);

				currentIdleAnimationClock = tileEntity.getIdleAnimationClockRotation(i);

				panel1RotativeInfo[i].setNewRotateY((panel1RotativeInfo[i].getNewRotateY()) + 
						(2F * PI * currentIdleAnimationClock.getPhaseDurationCoveredY(0)) + (defaultAngleMultiple * i));

				panel2RotativeInfo[i].setNewRotateY((panel2RotativeInfo[i].getNewRotateY()) + 
						(2F * PI * currentIdleAnimationClock.getPhaseDurationCoveredY(0)) + (defaultAngleMultiple * i));

				panel3RotativeInfo[i].setNewRotateY((panel3RotativeInfo[i].getNewRotateY()) + 
						(2F * PI * currentIdleAnimationClock.getPhaseDurationCoveredY(0)) + (defaultAngleMultiple * i));
		}
	}

	public void deployAnimations()
	{
		this.animationDeployer.move(base, baseInfo.getNewPnt());
		this.animationDeployer.move(core, coreInfo.getNewPnt());

		this.animationDeployer.rotate(panelRotate, panelRotateInfo.getNewRotates());

		this.animationDeployer.move(panel1Rotate, panel1RotateInfo.getNewPnt());
		this.animationDeployer.move(panel2Rotate, panel2RotateInfo.getNewPnt());
		this.animationDeployer.move(panel3Rotate, panel3RotateInfo.getNewPnt());
		
		for(int i = 0; i < panel1Rotative.length; i++)
		{
			this.animationDeployer.rotate(panel1Rotative[i], panel1RotativeInfo[i].getNewRotates());
			this.animationDeployer.rotate(panel2Rotative[i], panel2RotativeInfo[i].getNewRotates());
			this.animationDeployer.rotate(panel3Rotative[i], panel3RotativeInfo[i].getNewRotates());
		}

		this.animationDeployer.applyChanges();
	}
}