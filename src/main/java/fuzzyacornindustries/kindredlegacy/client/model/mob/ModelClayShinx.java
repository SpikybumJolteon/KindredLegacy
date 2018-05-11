package fuzzyacornindustries.kindredlegacy.client.model.mob;

import fuzzyacornindustries.kindredlegacy.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.animation.JointAnimation;
import fuzzyacornindustries.kindredlegacy.animation.PartAnimate;
import fuzzyacornindustries.kindredlegacy.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.entity.mob.IMobMotionTracker;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityClayShinx;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelClayShinx extends ModelBase 
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public final float RUN_FREQUENCY = 1.2F;
	public final float WALK_FREQUENCY = 0.3F;
	public final float SNEAK_VELOCITY = 0.35F;

	public ModelRenderer body;
	public ModelRenderer neckJoint;
	public ModelRenderer neck;
	PartInfo bodyInfo;
	PartInfo neckJointInfo;
	PartInfo neckInfo;

	public ModelRenderer legFntRt;
	public ModelRenderer legFntLft;
	public ModelRenderer legBckLft;
	public ModelRenderer legBckRt;
	PartInfo legFntRtInfo;
	PartInfo legFntLftInfo;
	PartInfo legBckLftInfo;
	PartInfo legBckRtInfo;

	public ModelRenderer headJoint;
	public ModelRenderer head;
	PartInfo headJointInfo;
	PartInfo headInfo;

	public ModelRenderer nose;
	public ModelRenderer cheekHairLft;
	public ModelRenderer cheekHairRt;
	public ModelRenderer foreheadHair;

	public ModelRenderer earRtJoint;
	public ModelRenderer earRt;
	public ModelRenderer earLftJoint;
	public ModelRenderer earLft;
	PartInfo earRtJointInfo;
	PartInfo earRtInfo;
	PartInfo earLftJointInfo;
	PartInfo earLftInfo;

	public ModelRenderer tail[][] = new ModelRenderer[8][2];
	PartInfo tailInfo[][] = new PartInfo[tail.length][tail[0].length];

	public ModelClayShinx() 
	{
		this.textureWidth = 64;
		this.textureHeight = 32;

		int jointNumber = 0;
		int partNumber = 1;

		this.body = new ModelRenderer(this, 48, 24);
		this.body.setRotationPoint(0.0F, 20.0F, 0.0F);
		this.body.addBox(-1.5F, -1.5F, -2.5F, 3, 3, 5, 0.0F);
		bodyInfo = new PartInfo(body);
		this.neckJoint = new ModelRenderer(this, 17, 0);
		this.neckJoint.setRotationPoint(0.0F, -0.8F, -2.0F);
		this.neckJoint.addBox(-0.5F, -0.5F, -3.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(neckJoint, -0.7853981633974483F, 0.0F, 0.0F);
		neckJointInfo = new PartInfo(neckJoint);
		this.neck = new ModelRenderer(this, 17, 0);
		this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.neck.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
		neckInfo = new PartInfo(neck);

		this.legFntRt = new ModelRenderer(this, 48, 17);
		this.legFntRt.setRotationPoint(-0.75F, 1.0F, -1.6F);
		this.legFntRt.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
		legFntRtInfo = new PartInfo(legFntRt);
		this.legFntLft = new ModelRenderer(this, 60, 17);
		this.legFntLft.setRotationPoint(0.75F, 1.0F, -1.6F);
		this.legFntLft.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
		legFntLftInfo = new PartInfo(legFntLft);
		this.legBckLft = new ModelRenderer(this, 60, 22);
		this.legBckLft.setRotationPoint(0.75F, 1.0F, 1.4F);
		this.legBckLft.addBox(-0.5F, -0.2F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(legBckLft, -0.17453292519943295F, 0.0F, 0.0F);
		this.legBckLft.setTextureOffset(60, 25).addBox(-0.55F, 0.9F, 0.2F, 1, 2, 1, 0.0F); // legBckLftHind
		legBckLftInfo = new PartInfo(legBckLft);
		this.legBckRt = new ModelRenderer(this, 48, 22);
		this.legBckRt.setRotationPoint(-0.75F, 1.0F, 1.4F);
		this.legBckRt.addBox(-0.5F, -0.2F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(legBckRt, -0.17453292519943295F, 0.0F, 0.0F);
		this.legBckRt.setTextureOffset(48, 25).addBox(-0.45F, 0.9F, 0.2F, 1, 2, 1, 0.0F); // legBckRtHind
		legBckRtInfo = new PartInfo(legBckRt);

		this.headJoint = new ModelRenderer(this, 0, 0);
		this.headJoint.setRotationPoint(0.0F, 0.0F, -2.5F);
		this.headJoint.addBox(-2.0F, -3.0F, -3.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(headJoint, 0.7853981633974483F, 0.0F, 0.0F);
		headJointInfo = new PartInfo(headJoint);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-2.0F, -3.0F, -3.0F, 4, 4, 4, 0.0F);
		headInfo = new PartInfo(head);

		this.nose = new ModelRenderer(this, 14, 0);
		this.nose.setRotationPoint(0.0F, -0.5F, -2.7F);
		this.nose.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 1, 0.0F);
		this.setRotateAngle(nose, 0.17453292519943295F, 0.0F, 0.0F);
		this.cheekHairLft = new ModelRenderer(this, 8, 24);
		this.cheekHairLft.setRotationPoint(1.5F, 0.0F, -0.5F);
		this.cheekHairLft.addBox(0.0F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		this.setRotateAngle(cheekHairLft, 0.0F, -0.6981317007977318F, 0.6981317007977318F);
		this.cheekHairRt = new ModelRenderer(this, 0, 24);
		this.cheekHairRt.setRotationPoint(-1.5F, 0.0F, -0.5F);
		this.cheekHairRt.addBox(-1.5F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		this.setRotateAngle(cheekHairRt, 0.0F, 0.6981317007977318F, -0.6981317007977318F);
		this.foreheadHair = new ModelRenderer(this, 5, 20);
		this.foreheadHair.setRotationPoint(0.0F, -3.0F, -1.5F);
		this.foreheadHair.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(foreheadHair, -0.6108652381980153F, 0.0F, 0.0F);

		this.earRtJoint = new ModelRenderer(this, 1, 16);
		this.earRtJoint.setRotationPoint(-1.5F, -2.0F, 0.0F);
		this.earRtJoint.addBox(-2.0F, -1.0F, -0.5F, 0, 0, 0, 0.0F);
		earRtJointInfo = new PartInfo(earRtJoint);
		this.earRt = new ModelRenderer(this, 1, 16);
		this.earRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earRt.addBox(-2.0F, -1.0F, -0.5F, 2, 2, 1, 0.0F);
		this.earRt.setTextureOffset(0, 12).addBox(-2.5F, -3.0F, -0.5F, 3, 2, 1, 0.0F); // earRtMid
		this.earRt.setTextureOffset(1, 9).addBox(-2.0F, -4.0F, -0.5F, 2, 1, 1, 0.0F); // earRtTip
		earRtInfo = new PartInfo(earRt);

		this.earLftJoint = new ModelRenderer(this, 10, 16);
		this.earLftJoint.setRotationPoint(1.5F, -2.0F, 0.0F);
		this.earLftJoint.addBox(0.0F, -1.0F, -0.5F, 0, 0, 0, 0.0F);
		earLftJointInfo = new PartInfo(earLftJoint);
		this.earLft = new ModelRenderer(this, 10, 16);
		this.earLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earLft.addBox(0.0F, -1.0F, -0.5F, 2, 2, 1, 0.0F);
		this.earLft.setTextureOffset(9, 12).addBox(-0.5F, -3.0F, -0.5F, 3, 2, 1, 0.0F); // earLftMid
		this.earLft.setTextureOffset(10, 9).addBox(0.0F, -4.0F, -0.5F, 2, 1, 1, 0.0F); // earLftTip
		earLftInfo = new PartInfo(earLft);

		int tailNumber = 0;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 39, 29); // tail1Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, -0.9F, 2.0F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, 0.0F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 39, 29); // tail1
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 40, 26); // tail2Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.8F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 40, 26); // tail2
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 1, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 40, 23); // tail3Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.8F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 40, 23); // tail3
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 1, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 40, 20); // tail4Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.8F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 40, 20); // tail4
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 1, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 40, 17); // tail5Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.8F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 40, 17); // tail5
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 1, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 40, 14); // tail6Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.8F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 40, 14); // tail6
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 1, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 40, 11); // tail7Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.8F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 40, 11); // tail7
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 1, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 40, 8); // tail8Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.8F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 40, 8); // tail8
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 1, 0.0F);
		this.tail[tailNumber][partNumber].setTextureOffset(45, 6).addBox(0.2F, -1.2F, 0.2F, 1, 1, 1, 0.0F); // tail8TopLft
		this.tail[tailNumber][partNumber].setTextureOffset(35, 6).addBox(-1.2F, -1.2F, 0.2F, 1, 1, 1, 0.0F); // tail8TopRt
		this.tail[tailNumber][partNumber].setTextureOffset(35, 9).addBox(-1.2F, 0.2F, 0.2F, 1, 1, 1, 0.0F); // tail8BotRt
		this.tail[tailNumber][partNumber].setTextureOffset(45, 9).addBox(0.2F, 0.2F, 0.2F, 1, 1, 1, 0.0F); // tail8BotLft
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);

		this.body.addChild(this.legFntLft);
		this.body.addChild(this.legFntRt);
		this.body.addChild(this.legBckLft);
		this.body.addChild(this.legBckRt);
		this.body.addChild(this.neckJoint);
		this.body.addChild(this.tail[0][0]);

		this.neckJoint.addChild(this.neck);
		this.neck.addChild(this.headJoint);
		this.headJoint.addChild(this.head);

		this.head.addChild(this.cheekHairLft);
		this.head.addChild(this.cheekHairRt);
		this.head.addChild(this.earLftJoint);
		this.head.addChild(this.earRtJoint);
		this.head.addChild(this.foreheadHair);
		this.head.addChild(this.nose);

		this.earLftJoint.addChild(this.earLft);
		this.earRtJoint.addChild(this.earRt);

		this.tail[0][0].addChild(this.tail[0][1]); // tail1Joint * tail1
		this.tail[0][1].addChild(this.tail[1][0]); // tail1 * tail2Joint
		this.tail[1][0].addChild(this.tail[1][1]); // tail2Joint * tail2
		this.tail[1][1].addChild(this.tail[2][0]); // tail2 * tail3Joint
		this.tail[2][0].addChild(this.tail[2][1]); // tail3Joint * tail3
		this.tail[2][1].addChild(this.tail[3][0]); // tail3 * tail4Joint
		this.tail[3][0].addChild(this.tail[3][1]); // tail4Joint * tail4
		this.tail[3][1].addChild(this.tail[4][0]); // tail4 * tail5Joint
		this.tail[4][0].addChild(this.tail[4][1]); // tail5Joint * tail5
		this.tail[4][1].addChild(this.tail[5][0]); // tail5 * tail6Joint
		this.tail[5][0].addChild(this.tail[5][1]); // tail6Joint * tail6
		this.tail[5][1].addChild(this.tail[6][0]); // tail6 * tail7Joint
		this.tail[6][0].addChild(this.tail[6][1]); // tail7Joint * tail7
		this.tail[6][1].addChild(this.tail[7][0]); // tail7 * tail8Joint
		this.tail[7][0].addChild(this.tail[7][1]); // tail8Joint * tail8

		animationDeployer = new AnimationDeployer(this);
	}

	@Override
	public void render(Entity entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize) 
	{ 
		animate(entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);

		this.body.render(modelSize);
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) 
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void animate(Entity entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize)
	{	
		this.animationDeployer.update((IAnimatedEntity)entity);
		resetPartInfos();

		float angularVelocity = ((IMobMotionTracker)entity).getAngularVelocity();
		float verticalVelocity = (float)((IMobMotionTracker)entity).getHeightVelocity();

		animateBody((EntityClayShinx)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity);
		animateHead((EntityClayShinx)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity);
		animateLegs((EntityClayShinx)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity);
		animateTail((EntityClayShinx)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity, angularVelocity);

		deployAnimations();
	}

	public void resetPartInfos()
	{
		bodyInfo.resetNewAngles();
		bodyInfo.resetNewPnt();
		
		neckJointInfo.resetNewAngles();
		neckInfo.resetNewAngles();
		headJointInfo.resetNewAngles();
		headInfo.resetNewAngles();

		legFntRtInfo.resetNewAngles();
		legFntLftInfo.resetNewAngles();
		legBckRtInfo.resetNewAngles();
		legBckLftInfo.resetNewAngles();

		earRtInfo.resetNewAngles();
		earLftInfo.resetNewAngles();

		for(int i = 0; i < tail.length; i++)
		{
			tailInfo[i][0].resetNewAngles();
			tailInfo[i][1].resetNewAngles();
		}
	}

	public void animateBody(EntityClayShinx entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity)
	{
		float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
		float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);

		float bodyWalkAngle = (float)Math.toRadians(28);
		float bodyRunAngle = (float)Math.toRadians(44);

		float maximumChangeY = -2.0F;
		float runCyclePointChange = (MathHelper.cos(2F * runCycleInterval * PI + PI) * maximumChangeY + maximumChangeY) * horzVelocity;

		float newVerticalVelocity = 2.0F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;

		bodyInfo.setNewPointY(bodyInfo.getNewPointY() + runCyclePointChange * horzVelocity * (1F - Math.abs(newVerticalVelocity)));

		float walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI + PI/2) * bodyWalkAngle * (1 - horzVelocity);
		float runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI + PI/2) * bodyRunAngle * horzVelocity;

		float bodyJumpAngle = (float)Math.toRadians(-65);
		float verticalVelocityChangeAngle = (float)Math.toRadians(10);

		bodyInfo.setNewRotateX(bodyInfo.getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity)) 
				+ bodyJumpAngle * newVerticalVelocity);
	}

	public void animateHead(EntityClayShinx entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity)
	{
		float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
		float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);

		float newVerticalVelocity = 2.0F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;

		JointAnimation.reverseJointRotatesChange(bodyInfo, neckJointInfo);

		float headWalkAngle = (float)Math.toRadians(15);
		float headRunAngle = (float)Math.toRadians(25);

		float walkCycleRateChange = MathHelper.cos(walkCycleInterval * 2 * PI + PI/2) * (1 - horzVelocity);
		float runCycleRateChange = MathHelper.cos(runCycleInterval * 2 * PI + PI/2) * horzVelocity;

		float walkCycleAngleChange = walkCycleRateChange * headWalkAngle;
		float runCycleAngleChange = runCycleRateChange * headRunAngle;

		// + (float)Math.toRadians(45)
		float angleChangeX = (walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity));

		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockNeckBobble();

		float idleHeadAmplitudeX = (float)Math.toRadians(15);

		angleChangeX += -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleHeadAmplitudeX + idleHeadAmplitudeX;

		neckInfo.setNewRotateX(neckInfo.getNewRotateX() + angleChangeX);
		neckInfo.setNewRotateY(neckInfo.getNewRotateY());

		JointAnimation.reverseJointRotatesChange(neckInfo, headJointInfo);

		PartAnimate.headAnimateInfoOnly(headInfo, yawHeadOffsetDifference, pitchRotation);

		/* ******* Ears ******** */
		float earAngleChangeZ = 0;
		
		/* ** Ears Movement ** */
		float earWalkAngle = (float)Math.toRadians(10);
		float earRunAngle = (float)Math.toRadians(25);

		walkCycleAngleChange = walkCycleRateChange * earWalkAngle;
		runCycleAngleChange = runCycleRateChange * earRunAngle;

		earAngleChangeZ = (walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity));

		/* ** Ears Idle ** */
		float idleAmplitudeZ = (float)Math.toRadians(10);
		earAngleChangeZ += MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI + PI) * idleAmplitudeZ;

		/* ** Ears Falling/Jumping ** */
		float fallingEarAngle = (float)Math.toRadians(30);
		earAngleChangeZ += fallingEarAngle * newVerticalVelocity;
		
		earRtInfo.setNewRotateZ(earRtInfo.getNewRotateZ() - earAngleChangeZ);
		earLftInfo.setNewRotateZ(earLftInfo.getNewRotateZ() + earAngleChangeZ);
	}

	public void animateLegs(EntityClayShinx entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity)
	{
		float amplitude = 0.7F;

		// Run constants
		float legFntRtModifierRun = 2F * PI * (1F/4F);
		float legBckRtModifierRun = 2F * PI * (7F/16F);
		float legBckLftModifierRun = 2F * PI * (8F/16F);

		// Walk constants
		float legFntRtModifierWalk = 2F * PI * (9F/16F);
		float legBckRtModifierWalk = 2F * PI * (14F/16F);
		float legBckLftModifierWalk = 2F * PI * (6F/16F);

		float legFntRtModifierDifference = legFntRtModifierRun - legFntRtModifierWalk;
		float legBckRtModifierDifference = legBckRtModifierRun - legBckRtModifierWalk;
		float legBckLftModifierDifference = legBckLftModifierRun - legBckLftModifierWalk;

		float legFntRtModifier;
		float legBckRtModifier;
		float legBckLftModifier;

		float sprint = 1.0F;
		float sneak = SNEAK_VELOCITY;

		if(horzVelocity > sneak)
		{
			float modifier = (horzVelocity - sneak)/(sprint - sneak);
			legFntRtModifier = legFntRtModifierWalk + legFntRtModifierDifference * modifier;
			legBckRtModifier = legBckRtModifierWalk + legBckRtModifierDifference * modifier;
			legBckLftModifier = legBckLftModifierWalk + legBckLftModifierDifference * modifier;
		}
		else
		{
			legFntRtModifier = legFntRtModifierWalk;
			legBckRtModifier = legBckRtModifierWalk;
			legBckLftModifier = legBckLftModifierWalk;	
		}

		float newVerticalVelocity = 1.5F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;

		float fallingModifier = 0;

		if(newVerticalVelocity < 0)
			fallingModifier = newVerticalVelocity;

		float fallingLegAngle = (float)Math.toRadians(25);

		float newHorizontalVelocity = (2F * horzVelocity > 1F ? 1F : horzVelocity * 2F);

		JointAnimation.reverseJointRotatesChange(bodyInfo, legFntRtInfo);
		JointAnimation.reverseJointRotatesChange(bodyInfo, legFntLftInfo);
		JointAnimation.reverseJointRotatesChange(bodyInfo, legBckRtInfo);
		JointAnimation.reverseJointRotatesChange(bodyInfo, legBckLftInfo);

		legFntRtInfo.setNewRotateX(legFntRtInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legFntRtModifier + PI, newHorizontalVelocity, this.RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)) + fallingLegAngle * fallingModifier);
		legFntLftInfo.setNewRotateX(legFntLftInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + PI, newHorizontalVelocity, this.RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)) + fallingLegAngle * fallingModifier);
		legBckRtInfo.setNewRotateX(legBckRtInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legBckRtModifier + PI, newHorizontalVelocity, this.RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)));
		legBckLftInfo.setNewRotateX(legBckLftInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legBckLftModifier + PI, newHorizontalVelocity, this.RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)));
}

	public void animateTail(EntityClayShinx entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity, float angularVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, tailInfo[0][0]);

		float yawChangeAngle = -(float)Math.toRadians(18);
		float verticalVelocityChangeAngle = (float)Math.toRadians(-9);

		float bodyJumpAngle = (float)Math.toRadians(-55);

		float newVerticalVelocity = 1.5F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;

		float absoluteMoveVelocity = horzVelocity + Math.abs(newVerticalVelocity);

		if(absoluteMoveVelocity > 1F)
			absoluteMoveVelocity = 1F;

		tailInfo[0][1].setNewRotateX(tailInfo[0][1].getNewRotateX() + bodyJumpAngle * newVerticalVelocity);

		for(int i = 0; i < tail.length; i++)
		{
			IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockTail(i);

			float idleAmplitudeX = 0.18F;
			float idleAmplitudeY = 0.22F;

			float moveAmplitudeX = (float)Math.toRadians(9);

			// + (float)Math.toRadians(45)
			float angleChangeX = 0;
			float angleChangeY = 0;

			// Idle Animations
			angleChangeX += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2) * idleAmplitudeX 
					* (1F - absoluteMoveVelocity) * (1F - Math.abs(angularVelocity) * 0.5F);
			angleChangeY += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2) * idleAmplitudeY
					* (1F - absoluteMoveVelocity) * (1F - Math.abs(angularVelocity) * 0.5F);

			// Movement Animations
			angleChangeX += PartAnimate.negCosRotateAnimationAdjusted(distanceMoved - (2F * PI * ((float)i / (float)(tail.length - 1))), absoluteMoveVelocity, RUN_FREQUENCY, moveAmplitudeX) 
					+ verticalVelocityChangeAngle * newVerticalVelocity;
			angleChangeY += angularVelocity * yawChangeAngle;

			// Part Number Dampener
			angleChangeX *= (1F - (Math.pow(Math.E, -0.95F * (float)(i + 1))));
			angleChangeY *= (1F - (Math.pow(Math.E, -0.95F * (float)(i + 1))));

			tailInfo[i][1].setNewRotateX(tailInfo[i][1].getNewRotateX() + angleChangeX);
			tailInfo[i][1].setNewRotateY(tailInfo[i][1].getNewRotateY() + angleChangeY);
		}
	}

	public void deployAnimations()
	{
		this.animationDeployer.rotate(body, bodyInfo.getNewRotates());
		this.animationDeployer.move(body, bodyInfo.getNewPnt());

		this.animationDeployer.rotate(neckJoint, neckJointInfo.getNewRotates());
		this.animationDeployer.rotate(neck, neckInfo.getNewRotates());
		this.animationDeployer.rotate(headJoint, headJointInfo.getNewRotates());
		this.animationDeployer.rotate(head, headInfo.getNewRotates());

		this.animationDeployer.rotate(earLft, earLftInfo.getNewRotates());
		this.animationDeployer.rotate(earRt, earRtInfo.getNewRotates());

		this.animationDeployer.rotate(legFntRt, legFntRtInfo.getNewRotates());
		this.animationDeployer.rotate(legFntLft, legFntLftInfo.getNewRotates());
		this.animationDeployer.rotate(legBckRt, legBckRtInfo.getNewRotates());
		this.animationDeployer.rotate(legBckLft, legBckLftInfo.getNewRotates());

		for(int i = 0; i < tail.length; i++)
		{
			this.animationDeployer.rotate(tail[i][0], tailInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(tail[i][1], tailInfo[i][1].getNewRotates());
		}

		this.animationDeployer.applyChanges();
	}
}