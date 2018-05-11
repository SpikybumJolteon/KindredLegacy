package fuzzyacornindustries.kindredlegacy.client.model.mob;

import fuzzyacornindustries.kindredlegacy.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.animation.JointAnimation;
import fuzzyacornindustries.kindredlegacy.animation.PartAnimate;
import fuzzyacornindustries.kindredlegacy.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.entity.mob.IMobMotionTracker;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityBandersnatchFennekin;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBandersnatchFennekin extends ModelBase 
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public final float RUN_FREQUENCY = 1.2F;
	public final float WALK_FREQUENCY = 0.3F;
	public final float SNEAK_VELOCITY = 0.35F;

	public ModelRenderer body;
	public ModelRenderer bodyTuffLft;
	public ModelRenderer bodyTuffRt;
	PartInfo bodyInfo;
	PartInfo bodyTuffLftInfo;
	PartInfo bodyTuffRtInfo;

	public ModelRenderer legFntLft;
	public ModelRenderer legFntRt;
	public ModelRenderer legBckLft;
	public ModelRenderer legBckRt;
	PartInfo legFntRtInfo;
	PartInfo legFntLftInfo;
	PartInfo legBckLftInfo;
	PartInfo legBckRtInfo;

	public ModelRenderer neckJoint;
	public ModelRenderer neck;
	PartInfo neckJointInfo;
	PartInfo neckInfo;

	public ModelRenderer headJoint;
	public ModelRenderer head;
	PartInfo headJointInfo;
	PartInfo headInfo;

	public ModelRenderer muzzle;

	public ModelRenderer cheekLftTuff;
	public ModelRenderer cheekRtTuff;
	PartInfo cheekLftTuffInfo;
	PartInfo cheekRtTuffInfo;

	public ModelRenderer earLftJoint;
	public ModelRenderer earLft;
	public ModelRenderer earRtJoint;
	public ModelRenderer earRt;
	PartInfo earLftJointInfo;
	PartInfo earLftInfo;
	PartInfo earRtJointInfo;
	PartInfo earRtInfo;

	public ModelRenderer earLftTuffJoint;
	public ModelRenderer earLftTuff;
	public ModelRenderer earRtTuffJoint;
	public ModelRenderer earRtTuff;
	PartInfo earLftTuffJointInfo;
	PartInfo earLftTuffInfo;
	PartInfo earRtTuffJointInfo;
	PartInfo earRtTuffInfo;

	public ModelRenderer tail[][] = new ModelRenderer[4][2];
	PartInfo tailInfo[][] = new PartInfo[tail.length][tail[0].length];

	public ModelBandersnatchFennekin() 
	{
		this.textureWidth = 64;
		this.textureHeight = 32;

		int jointNumber = 0;
		int partNumber = 1;

		this.body = new ModelRenderer(this, 51, 26);
		this.body.setRotationPoint(0.0F, 21.0F, 0.0F);
		this.body.addBox(-1.0F, -1.0F, -2.0F, 2, 2, 4, 0.0F);
		bodyInfo = new PartInfo(body);
		this.bodyTuffLft = new ModelRenderer(this, 37, 29);
		this.bodyTuffLft.setRotationPoint(0.6F, -0.5F, 1.47F);
		this.bodyTuffLft.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(bodyTuffLft, 0.7853981633974483F, 0.4363323129985824F, 0.0F);
		bodyTuffLftInfo = new PartInfo(bodyTuffLft);
		this.bodyTuffRt = new ModelRenderer(this, 44, 29);
		this.bodyTuffRt.setRotationPoint(-0.6F, -0.5F, 1.47F);
		this.bodyTuffRt.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(bodyTuffRt, 0.7853981633974483F, -0.4363323129985824F, 0.0F);
		bodyTuffRtInfo = new PartInfo(bodyTuffRt);

		this.legFntLft = new ModelRenderer(this, 52, 17);
		this.legFntLft.setRotationPoint(0.49F, 0.5F, -0.9F);
		this.legFntLft.addBox(-0.5F, -0.5F, -0.5F, 1, 3, 1, 0.0F);
		legFntLftInfo = new PartInfo(legFntLft);
		this.legFntRt = new ModelRenderer(this, 58, 17);
		this.legFntRt.setRotationPoint(-0.49F, 0.5F, -1.0F);
		this.legFntRt.addBox(-0.5F, -0.5F, -0.5F, 1, 3, 1, 0.0F);
		legFntRtInfo = new PartInfo(legFntRt);
		this.legBckLft = new ModelRenderer(this, 50, 22);
		this.legBckLft.setRotationPoint(0.4F, 0.5F, 1.3F);
		this.legBckLft.addBox(-0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(legBckLft, -0.17453292519943295F, -0.0F, 0.0F);
		this.legBckLft.setTextureOffset(50, 26).addBox(-0.49F, 0.6F, 0.0F, 1, 2, 1, 0.0F); // legBckLftHind
		legBckLftInfo = new PartInfo(legBckLft);
		this.legBckRt = new ModelRenderer(this, 60, 22);
		this.legBckRt.setRotationPoint(-0.4F, 0.5F, 1.2F);
		this.legBckRt.addBox(-0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(legBckRt, -0.17453292519943295F, -0.0F, 0.0F);
		this.legBckRt.setTextureOffset(60, 26).addBox(-0.51F, 0.6F, 0.0F, 1, 2, 1, 0.0F); // legBckRtHind
		legBckRtInfo = new PartInfo(legBckRt);

		this.neckJoint = new ModelRenderer(this, 17, 19);
		this.neckJoint.setRotationPoint(0.0F, -0.5F, -1.5F);
		this.neckJoint.addBox(-0.5F, -2.0F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(neckJoint, 0.7853981633974483F, -0.0F, 0.0F);
		neckJointInfo = new PartInfo(neckJoint);
		this.neck = new ModelRenderer(this, 17, 19);
		this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.neck.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		neckInfo = new PartInfo(neck);

		this.headJoint = new ModelRenderer(this, 0, 0);
		this.headJoint.setRotationPoint(0.0F, -2.0F, 0.0F);
		this.headJoint.addBox(-1.5F, -2.3F, -2.3F, 0, 0, 0, 0.0F);
		this.setRotateAngle(headJoint, -0.7853981633974483F, 0.0F, 0.0F);
		headJointInfo = new PartInfo(headJoint);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-1.5F, -2.3F, -2.3F, 3, 3, 3, 0.0F);
		headInfo = new PartInfo(head);

		this.muzzle = new ModelRenderer(this, 10, 0);
		this.muzzle.setRotationPoint(0.0F, 0.0F, -2.0F);
		this.muzzle.addBox(-0.5F, -0.5F, -0.8F, 1, 1, 1, 0.0F);
		this.setRotateAngle(muzzle, 0.3490658503988659F, -0.0F, 0.0F);

		this.cheekLftTuff = new ModelRenderer(this, 0, 27);
		this.cheekLftTuff.setRotationPoint(1.1F, 0.0F, -0.5F);
		this.cheekLftTuff.addBox(0.0F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		this.setRotateAngle(cheekLftTuff, 0.0F, -0.0F, 0.5235987755982988F);
		cheekLftTuffInfo = new PartInfo(cheekLftTuff);
		this.cheekRtTuff = new ModelRenderer(this, 7, 27);
		this.cheekRtTuff.setRotationPoint(-1.1F, 0.0F, -0.5F);
		this.cheekRtTuff.addBox(-2.0F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		this.setRotateAngle(cheekRtTuff, 0.0F, -0.0F, -0.5235987755982988F);
		cheekRtTuffInfo = new PartInfo(cheekRtTuff);

		this.earLftJoint = new ModelRenderer(this, 0, 14);
		this.earLftJoint.setRotationPoint(0.5F, -1.0F, 0.0F);
		this.earLftJoint.addBox(0.2F, -1.5F, -0.67F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earLftJoint, 0.0F, 0.0F, -0.5235987755982988F);
		earLftJointInfo = new PartInfo(earLftJoint);
		this.earLft = new ModelRenderer(this, 0, 14);
		this.earLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earLft.addBox(0.2F, -1.5F, -0.5F, 2, 3, 1, 0.0F);
		this.earLft.setTextureOffset(0, 10).addBox(2.2F, -1.2F, -0.5F, 2, 2, 1, 0.0F); // earLftMid
		this.earLft.setTextureOffset(1, 7).addBox(4.2F, -1.0F, -0.5F, 1, 1, 1, 0.0F); // earLftTip
		earLftInfo = new PartInfo(earLft);
		this.earRtJoint = new ModelRenderer(this, 7, 14);
		this.earRtJoint.setRotationPoint(-0.5F, -1.0F, 0.0F);
		this.earRtJoint.addBox(-2.2F, -1.5F, -0.67F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earRtJoint, 0.0F, -0.0F, 0.5235987755982988F);
		earRtJointInfo = new PartInfo(earRtJoint);
		this.earRt = new ModelRenderer(this, 7, 14);
		this.earRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earRt.addBox(-2.2F, -1.5F, -0.5F, 2, 3, 1, 0.0F);
		this.earRt.setTextureOffset(7, 10).addBox(-4.1F, -1.2F, -0.5F, 2, 2, 1, 0.0F); // earRtMid
		this.earRt.setTextureOffset(8, 7).addBox(-5.1F, -1.0F, -0.5F, 1, 1, 1, 0.0F); // earRtTip
		earRtInfo = new PartInfo(earRt);

		this.earLftTuffJoint = new ModelRenderer(this, 5, 19);
		this.earLftTuffJoint.setRotationPoint(0.7F, -1.3F, -1.0F);
		this.earLftTuffJoint.addBox(0.0F, -1.0F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earLftTuffJoint, 0.0F, 0.0F, -0.2617993877991494F);
		earLftTuffJointInfo = new PartInfo(earLftTuffJoint);
		this.earLftTuff = new ModelRenderer(this, 5, 19);
		this.earLftTuff.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earLftTuff.addBox(0.0F, -1.0F, -0.5F, 3, 2, 1, 0.0F);
		this.earLftTuff.setTextureOffset(0, 19).addBox(3.0F, -0.6F, -0.5F, 1, 1, 1, 0.0F); // earLftTuffTip
		earLftTuffInfo = new PartInfo(earLftTuff);
		this.earRtTuffJoint = new ModelRenderer(this, 0, 23);
		this.earRtTuffJoint.setRotationPoint(-0.7F, -1.3F, -1.0F);
		this.earRtTuffJoint.addBox(-3.0F, -1.0F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earRtTuffJoint, 0.0F, 0.0F, 0.2617993877991494F);
		earRtTuffJointInfo = new PartInfo(earRtTuffJoint);
		this.earRtTuff = new ModelRenderer(this, 0, 23);
		this.earRtTuff.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earRtTuff.addBox(-3.0F, -1.0F, -0.5F, 3, 2, 1, 0.0F);
		this.earRtTuff.setTextureOffset(9, 23).addBox(-4.0F, -0.6F, -0.5F, 1, 1, 1, 0.0F); // earRtTuffTip
		earRtTuffInfo = new PartInfo(earRtTuff);

		int tailNumber = 0;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 16, 15); // tail1Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, -0.4F, 2.0F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 16, 15); // tail1
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 14, 9); // tail2Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.0F);
		this.tail[tailNumber][jointNumber].addBox(-1.5F, -1.5F, -0.5F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 14, 9); // tail2
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-1.5F, -1.5F, -0.5F, 3, 3, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 15, 4); // tail3Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.3F);
		this.tail[tailNumber][jointNumber].addBox(-1.0F, -1.0F, -0.5F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 15, 4); // tail3
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-1.0F, -1.0F, -0.5F, 2, 2, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 16, 0); // tail4Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.0F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, 0.0F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 16, 0); // tail4
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);

		this.body.addChild(this.bodyTuffLft);
		this.body.addChild(this.bodyTuffRt);
		this.body.addChild(this.legBckLft);
		this.body.addChild(this.legBckRt);
		this.body.addChild(this.legFntLft);
		this.body.addChild(this.legFntRt);
		this.body.addChild(this.neckJoint);
		this.body.addChild(this.tail[0][0]);

		this.neckJoint.addChild(this.neck);
		this.neck.addChild(this.headJoint);

		this.headJoint.addChild(this.head);
		this.head.addChild(this.cheekLftTuff);
		this.head.addChild(this.cheekRtTuff);

		this.head.addChild(this.earLftJoint);
		this.head.addChild(this.earLftTuffJoint);
		this.head.addChild(this.earRtJoint);
		this.head.addChild(this.earRtTuffJoint);
		this.head.addChild(this.muzzle);

		this.earLftJoint.addChild(this.earLft);
		this.earLftTuffJoint.addChild(this.earLftTuff);
		this.earRtJoint.addChild(this.earRt);
		this.earRtTuffJoint.addChild(this.earRtTuff);

		this.tail[0][0].addChild(this.tail[0][1]);

		for(int i = 1; i < tail.length; i++)
		{
			this.tail[i - 1][1].addChild(this.tail[i][0]);
			this.tail[i][0].addChild(this.tail[i][1]);
		}

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
		animationDeployer.update((IAnimatedEntity)entity);
		resetPartInfos();

		float angularVelocity = ((IMobMotionTracker)entity).getAngularVelocity();
		float verticalVelocity = (float)((IMobMotionTracker)entity).getHeightVelocity();

		animateBody((EntityBandersnatchFennekin)animationDeployer.getEntity(), distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity);
		animateHead((EntityBandersnatchFennekin)animationDeployer.getEntity(), distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity);
		animateLegs((EntityBandersnatchFennekin)animationDeployer.getEntity(), distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity);
		animateTail((EntityBandersnatchFennekin)animationDeployer.getEntity(), distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity, angularVelocity);

		deployAnimations();
	}

	public void resetPartInfos()
	{
		bodyInfo.resetNewAngles();
		
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
		earLftTuffInfo.resetNewAngles();
		earRtTuffInfo.resetNewAngles();
		cheekLftTuffInfo.resetNewAngles();
		cheekRtTuffInfo.resetNewAngles();

		for(int i = 0; i < tail.length; i++)
		{
			tailInfo[i][0].resetNewAngles();
			tailInfo[i][1].resetNewAngles();
		}
	}

	public void animateBody(EntityBandersnatchFennekin entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity)
	{
		float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
		float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);

		float bodyWalkAngle = (float)Math.toRadians(14);
		float bodyRunAngle = (float)Math.toRadians(22);

		float walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI + PI/2) * bodyWalkAngle * (1 - horzVelocity)
				+ bodyWalkAngle * (1 - horzVelocity);
		float runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI + PI/2) * bodyRunAngle * horzVelocity
				+ bodyRunAngle * horzVelocity;

		float bodyJumpAngle = (float)Math.toRadians(-65);
		float verticalVelocityChangeAngle = (float)Math.toRadians(10);

		float newVerticalVelocity = 1.5F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;

		bodyInfo.setNewRotateX(bodyInfo.getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity)) 
				+ bodyJumpAngle * newVerticalVelocity);
	}

	public void animateHead(EntityBandersnatchFennekin entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, neckJointInfo);

		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockNeckBobble();

		float idleAmplitudeX = 0.1F;

		float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
		float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);

		float walkAngle = (float)Math.toRadians(4);
		float runAngle = (float)Math.toRadians(8);

		float walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI) * walkAngle * (1 - horzVelocity)
				+ walkAngle * (1 - horzVelocity);
		float runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI) * runAngle * horzVelocity
				+ runAngle * horzVelocity;

		// + (float)Math.toRadians(45)
		float movementAmplitudeX = (float)Math.toRadians(60);

		float idleAngleChangeX = -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeX + idleAmplitudeX;

		neckInfo.setNewRotateX(neckInfo.getNewRotateX() + idleAngleChangeX + movementAmplitudeX * (walkCycleAngleChange + runCycleAngleChange) * horzVelocity);

		JointAnimation.reverseJointRotatesChange(neckInfo, headJointInfo);

		PartAnimate.headAnimateInfoOnly(headInfo, yawHeadOffsetDifference, pitchRotation);

		/* ******* Ears ******** */
		float moveAmplitudeZ = (float)Math.toRadians(65);

		float moveAngleZ = moveAmplitudeZ * (walkCycleAngleChange + runCycleAngleChange) * horzVelocity;

		float idleAmplitudeZ = 0.15F;

		float newVerticalVelocity = 1.5F * verticalVelocity;
		
		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;
		
		float fallingEarAngle = (float)Math.toRadians(40);
		
		// + (float)Math.toRadians(45)
		float angleChangeZ = MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI + PI) * idleAmplitudeZ;

		earRtInfo.setNewRotateZ(earRtInfo.getNewRotateZ() - (angleChangeZ + moveAngleZ + fallingEarAngle * newVerticalVelocity));
		earLftInfo.setNewRotateZ(earLftInfo.getNewRotateZ() + (angleChangeZ + moveAngleZ + fallingEarAngle * newVerticalVelocity));

		/* ******* Ear Hairs ******** */
		idleAmplitudeZ = 0.2F;

		// + (float)Math.toRadians(45)
		angleChangeZ = moveAngleZ * 1.3F;

		angleChangeZ += MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI + (PI/2)) * idleAmplitudeZ;

		earRtTuffInfo.setNewRotateZ(earRtTuffInfo.getNewRotateZ() - (angleChangeZ + fallingEarAngle * newVerticalVelocity));
		earLftTuffInfo.setNewRotateZ(earLftTuffInfo.getNewRotateZ() + (angleChangeZ + fallingEarAngle * newVerticalVelocity));

		/* ******* Cheek Hairs ******** */
		angleChangeZ = moveAngleZ * 1.5F;

		cheekLftTuffInfo.setNewRotateZ(cheekLftTuffInfo.getNewRotateZ() - (angleChangeZ + fallingEarAngle * newVerticalVelocity));
		cheekRtTuffInfo.setNewRotateZ(cheekRtTuffInfo.getNewRotateZ() + (angleChangeZ + fallingEarAngle * newVerticalVelocity));
	}

	public void animateLegs(EntityBandersnatchFennekin entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity)
	{
		float amplitude = 0.8F;

		// Run constants
		float legFntRtModifierRun = 2F * PI * (3F/16F);
		float legBckRtModifierRun = 2F * PI * (11F/16F);
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
		/**/
		JointAnimation.reverseJointRotatesChange(bodyInfo, legFntRtInfo);
		JointAnimation.reverseJointRotatesChange(bodyInfo, legFntLftInfo);
		JointAnimation.reverseJointRotatesChange(bodyInfo, legBckRtInfo);
		JointAnimation.reverseJointRotatesChange(bodyInfo, legBckLftInfo);

		//( 1 - (Math.pow(Math.E, -0.4F * (float)(i + 1))));

		legFntRtInfo.setNewRotateX(legFntRtInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legFntRtModifier, newHorizontalVelocity, this.RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)) + fallingLegAngle * fallingModifier);
		legFntLftInfo.setNewRotateX(legFntLftInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, newHorizontalVelocity, this.RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)) + fallingLegAngle * fallingModifier);
		legBckRtInfo.setNewRotateX(legBckRtInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legBckRtModifier, newHorizontalVelocity, this.RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)));
		legBckLftInfo.setNewRotateX(legBckLftInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legBckLftModifier, newHorizontalVelocity, this.RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)));

		this.bodyTuffLft.rotateAngleX = bodyTuffLftInfo.getNewRotates().getX() + PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, RUN_FREQUENCY, amplitude * 0.7F);
		this.bodyTuffRt.rotateAngleX = bodyTuffRtInfo.getNewRotates().getX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, RUN_FREQUENCY, amplitude * 0.7F);
	}

	public void animateTail(EntityBandersnatchFennekin entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity, float angularVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, tailInfo[0][0]);

		float yawChangeAngle = -(float)Math.toRadians(7);
		float verticalVelocityChangeAngle = (float)Math.toRadians(-30);

		float bodyJumpAngle = (float)Math.toRadians(-55);

		float newVerticalVelocity = 1.5F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;
		
		float newHorizontalVelocity = (2F * horzVelocity > 1F ? 1F : horzVelocity * 2F);

		tailInfo[0][1].setNewRotateX(tailInfo[0][1].getNewRotateX() + bodyJumpAngle * newVerticalVelocity);
		
		for(int i = 0; i < tail.length; i++)
		{
			IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockTail(i);

			float moveAmplitude = (float)Math.toRadians(-40) * (1F/(((float)i * 0.25F) + 1F));

			float idleAmplitudeX = 1.0F;
			float idleAmplitudeY = 0.5F;

			// + (float)Math.toRadians(45)
			float angleChangeX = 0;
			float angleChangeY = 0;

			angleChangeX += ((MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2) * idleAmplitudeX  * (1F - newHorizontalVelocity))
					+ PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, this.RUN_FREQUENCY, moveAmplitude))
					* (1F - (Math.pow(Math.E, -0.4F * (float)(i + 1)))) * (1F - Math.abs(newVerticalVelocity))
					+ verticalVelocity * verticalVelocityChangeAngle;
			angleChangeY += ((MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2) * idleAmplitudeY)
					* (1F - (Math.pow(Math.E, -0.4F * (float)(i + 1)))) * (1F - Math.abs(angularVelocity))) * (1F - (Math.abs(newVerticalVelocity) * 0.5F))
					+ angularVelocity * yawChangeAngle;

			tailInfo[i][1].setNewRotateX(tailInfo[i][1].getNewRotateX() + angleChangeX);
			tailInfo[i][1].setNewRotateY(tailInfo[i][1].getNewRotateY() + angleChangeY);
		}
	}

	public void deployAnimations()
	{
		this.animationDeployer.rotate(body, bodyInfo.getNewRotates());

		animationDeployer.rotate(neckJoint, neckJointInfo.getNewRotates());
		animationDeployer.rotate(neck, neckInfo.getNewRotates());
		animationDeployer.rotate(headJoint, headJointInfo.getNewRotates());
		animationDeployer.rotate(head, headInfo.getNewRotates());
		animationDeployer.rotate(earLft, earLftInfo.getNewRotates());
		animationDeployer.rotate(earRt, earRtInfo.getNewRotates());
		animationDeployer.rotate(earLftTuff, earLftTuffInfo.getNewRotates());
		animationDeployer.rotate(earRtTuff, earRtTuffInfo.getNewRotates());
		animationDeployer.rotate(cheekLftTuff, cheekLftTuffInfo.getNewRotates());
		animationDeployer.rotate(cheekRtTuff, cheekRtTuffInfo.getNewRotates());

		this.animationDeployer.rotate(legFntRt, legFntRtInfo.getNewRotates());
		this.animationDeployer.rotate(legFntLft, legFntLftInfo.getNewRotates());
		this.animationDeployer.rotate(legBckRt, legBckRtInfo.getNewRotates());
		this.animationDeployer.rotate(legBckLft, legBckLftInfo.getNewRotates());

		for(int i = 0; i < tail.length; i++)
		{
			animationDeployer.rotate(tail[i][0], tailInfo[i][0].getNewRotates());
			animationDeployer.rotate(tail[i][1], tailInfo[i][1].getNewRotates());
		}

		animationDeployer.applyChanges();
	}
}