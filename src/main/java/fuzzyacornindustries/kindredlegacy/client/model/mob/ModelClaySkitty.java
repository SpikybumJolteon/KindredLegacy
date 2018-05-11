package fuzzyacornindustries.kindredlegacy.client.model.mob;

import fuzzyacornindustries.kindredlegacy.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.animation.JointAnimation;
import fuzzyacornindustries.kindredlegacy.animation.PartAnimate;
import fuzzyacornindustries.kindredlegacy.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.entity.mob.IMobMotionTracker;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityClaySkitty;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelClaySkitty extends ModelBase 
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

	public ModelRenderer headJoint;
	public ModelRenderer head;
	PartInfo headJointInfo;
	PartInfo headInfo;

	public ModelRenderer earRtJoint;
	public ModelRenderer earRt;
	public ModelRenderer earLftJoint;
	public ModelRenderer earLft;
	PartInfo earRtJointInfo;
	PartInfo earRtInfo;
	PartInfo earLftJointInfo;
	PartInfo earLftInfo;

	public ModelRenderer legFntRt;
	public ModelRenderer legFntLft;
	public ModelRenderer legBckRt;
	public ModelRenderer legBckLft;
	PartInfo legFntRtInfo;
	PartInfo legFntLftInfo;
	PartInfo legBckRtInfo;
	PartInfo legBckLftInfo;

	public ModelRenderer tail[][] = new ModelRenderer[2][2];
	PartInfo tailInfo[][] = new PartInfo[tail.length][tail[0].length];

	public ModelClaySkitty() 
	{
		this.textureWidth = 64;
		this.textureHeight = 32;

		int jointNumber = 0;
		int partNumber = 1;

		this.body = new ModelRenderer(this, 50, 25);
		this.body.setRotationPoint(0.0F, 21.5F, 0.0F);
		this.body.addBox(-1.5F, -1.5F, -2.0F, 3, 3, 4, 0.0F);
		bodyInfo = new PartInfo(body);
		this.neckJoint = new ModelRenderer(this, 0, 0);
		this.neckJoint.setRotationPoint(0.0F, -1.0F, -1.4F);
		this.neckJoint.addBox(0.0F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		neckJointInfo = new PartInfo(neckJoint);
		this.neck = new ModelRenderer(this, 0, 0);
		this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.neck.addBox(0.0F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		neckInfo = new PartInfo(neck);

		this.headJoint = new ModelRenderer(this, 0, 0);
		this.headJoint.setRotationPoint(0.0F, -0.7F, 0.0F);
		this.headJoint.addBox(-2.5F, -4.5F, -3.0F, 0, 0, 0, 0.0F);
		headJointInfo = new PartInfo(headJoint);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, -0.7F, 0.0F);
		this.head.addBox(-2.5F, -4.5F, -3.0F, 5, 5, 4, 0.0F);
		headInfo = new PartInfo(head);

		this.earRtJoint = new ModelRenderer(this, 0, 16);
		this.earRtJoint.setRotationPoint(-1.5F, -4.0F, -0.5F);
		this.earRtJoint.addBox(-1.5F, -1.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earRtJoint, 0.0F, -0.0F, -0.5235987755982988F);
		earRtJointInfo = new PartInfo(earRtJoint);
		this.earRt = new ModelRenderer(this, 0, 16);
		this.earRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earRt.addBox(-1.5F, -1.5F, -0.5F, 3, 2, 1, 0.0F);
		this.earRt.setTextureOffset(1, 13).addBox(-1.0F, -2.5F, -0.5F, 2, 1, 1, 0.0F); // earRtMid
		this.earRt.setTextureOffset(2, 10).addBox(-0.5F, -3.1F, -0.49F, 1, 1, 1, 0.0F); // earRtEnd
		earRtInfo = new PartInfo(earRt);

		this.earLftJoint = new ModelRenderer(this, 9, 16);
		this.earLftJoint.setRotationPoint(1.5F, -4.0F, -0.5F);
		this.earLftJoint.addBox(-1.5F, -1.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earLftJoint, 0.0F, 0.0F, 0.5235987755982988F);
		earLftJointInfo = new PartInfo(earLftJoint);
		this.earLft = new ModelRenderer(this, 9, 16);
		this.earLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earLft.addBox(-1.5F, -1.5F, -0.5F, 3, 2, 1, 0.0F);
		this.earLft.setTextureOffset(10, 13).addBox(-1.0F, -2.5F, -0.5F, 2, 1, 1, 0.0F); // earLftMid
		this.earLft.setTextureOffset(11, 10).addBox(-0.5F, -3.1F, -0.49F, 1, 1, 1, 0.0F); // earLftEnd
		earLftInfo = new PartInfo(earLft);

		this.legFntRt = new ModelRenderer(this, 40, 25);
		this.legFntRt.setRotationPoint(-0.9F, 1.0F, -1.1F);
		this.legFntRt.addBox(-0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F);
		legFntRtInfo = new PartInfo(legFntRt);
		this.legFntLft = new ModelRenderer(this, 45, 25);
		this.legFntLft.setRotationPoint(0.9F, 1.0F, -1.1F);
		this.legFntLft.addBox(-0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F);
		legFntLftInfo = new PartInfo(legFntLft);
		this.legBckRt = new ModelRenderer(this, 40, 29);
		this.legBckRt.setRotationPoint(-0.9F, 1.0F, 1.1F);
		this.legBckRt.addBox(-0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F);
		legBckRtInfo = new PartInfo(legBckRt);
		this.legBckLft = new ModelRenderer(this, 45, 29);
		this.legBckLft.setRotationPoint(0.9F, 1.0F, 1.1F);
		this.legBckLft.addBox(-0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F);
		legBckLftInfo = new PartInfo(legBckLft);

		int tailNumber = 0;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 52, 20); // tailBaseJoint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, -0.5F, 1.5F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][jointNumber], 0.5235987755982988F, -0.0F, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 52, 20); // tailBase
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 52, 11); // tailEndJoint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.8F);
		this.tail[tailNumber][jointNumber].addBox(-1.0F, -5.5F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][jointNumber], -0.5235987755982988F, 0.0F, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 52, 11); // tailEnd
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-1.0F, -5.5F, -1.0F, 2, 6, 2, 0.0F);
		this.tail[tailNumber][partNumber].setTextureOffset(60, 10).addBox(0.8F, -6.0F, -0.5F, 1, 1, 1, 0.0F); // tailTipLft
		this.tail[tailNumber][partNumber].setTextureOffset(54, 8).addBox(-0.5F, -6.3F, -0.5F, 1, 1, 1, 0.0F); // tailTipMid
		this.tail[tailNumber][partNumber].setTextureOffset(48, 10).addBox(-1.8F, -6.0F, -0.5F, 1, 1, 1, 0.0F); // tailTipRt
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);

		this.body.addChild(this.legBckLft);
		this.body.addChild(this.legBckRt);
		this.body.addChild(this.legFntLft);
		this.body.addChild(this.legFntRt);
		this.body.addChild(this.neckJoint);
		this.body.addChild(this.tail[0][0]); // tailBaseJoint

		this.neckJoint.addChild(this.neck);

		this.neck.addChild(this.headJoint);
		this.headJoint.addChild(this.head);
		this.head.addChild(this.earLftJoint);
		this.head.addChild(this.earRtJoint);

		this.earLftJoint.addChild(this.earLft);

		this.earRtJoint.addChild(this.earRt);

		this.tail[0][0].addChild(this.tail[0][1]); // tailBaseJoint * tailBase
		this.tail[0][1].addChild(this.tail[1][0]); // tailBase      * tailEndJoint
		this.tail[1][0].addChild(this.tail[1][1]); // tailEndJoint  * tailEnd

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

		animateBody((EntityClaySkitty)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity);
		animateHead((EntityClaySkitty)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity);
		animateLegs((EntityClaySkitty)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity);
		animateTail((EntityClaySkitty)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity, angularVelocity);

		deployAnimations();
	}

	public void resetPartInfos()
	{
		bodyInfo.resetNewAngles();
		bodyInfo.resetNewPnt();

		legFntLftInfo.resetNewAngles();
		legFntRtInfo.resetNewAngles();
		legBckLftInfo.resetNewAngles();
		legBckRtInfo.resetNewAngles();
		
		neckJointInfo.resetNewAngles();
		neckInfo.resetNewAngles();
		headJointInfo.resetNewAngles();
		headInfo.resetNewAngles();

		earRtInfo.resetNewAngles();
		earLftInfo.resetNewAngles();

		for(int i = 0; i < tail.length; i++)
		{
			tailInfo[i][0].resetNewAngles();
			tailInfo[i][1].resetNewAngles();
		}
	}
	
	public void animateBody(EntityClaySkitty entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity)
	{
		float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
		float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);

		float bodyWalkAngle = (float)Math.toRadians(28);
		float bodyRunAngle = (float)Math.toRadians(44);

		float maximumChangeY = -3.0F;
		float runCyclePointChange = (MathHelper.cos(2F * runCycleInterval * PI) * maximumChangeY + maximumChangeY) * horzVelocity;

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

		newVerticalVelocity = 1.5F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;
		
		bodyInfo.setNewRotateX(bodyInfo.getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity)) 
				+ bodyJumpAngle * newVerticalVelocity);
	}
	
	public void animateHead(EntityClaySkitty entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, neckJointInfo);

		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockNeckBobble();

		float idleAmplitudeX = 0.25F;

		// + (float)Math.toRadians(45)
		float movementFrequency = 2.0F;
		float movementAmplitude = 0.2F;
		float angleChangeX = PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, movementFrequency, movementAmplitude);

		angleChangeX += -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeX + idleAmplitudeX;

		neckInfo.setNewRotateX(neckInfo.getNewRotateX() + angleChangeX);
		neckInfo.setNewRotateY(neckInfo.getNewRotateY());

		JointAnimation.reverseJointRotatesChange(neckInfo, headJointInfo);

		PartAnimate.headAnimateInfoOnly(headInfo, yawHeadOffsetDifference, pitchRotation);

		/* ******* Ears ******** */
		float moveFrequencyZ = movementFrequency;
		float moveAmplitudeZ = 0.15F;

		float firstAngleChangeZ = PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, moveFrequencyZ, moveAmplitudeZ);

		float idleAmplitudeZ = 0.20F;

		// + (float)Math.toRadians(45)
		float angleChangeZ = firstAngleChangeZ;

		angleChangeZ += MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI + PI) * idleAmplitudeZ;

		earRtInfo.setNewRotateZ(earRtInfo.getNewRotateZ() - angleChangeZ);
		earLftInfo.setNewRotateZ(earLftInfo.getNewRotateZ() + angleChangeZ);
	}

	public void animateLegs(EntityClaySkitty entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity)
	{
		float amplitude = 0.6F;

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

		JointAnimation.reverseJointRotatesChange(bodyInfo, legFntRtInfo);
		JointAnimation.reverseJointRotatesChange(bodyInfo, legFntLftInfo);
		JointAnimation.reverseJointRotatesChange(bodyInfo, legBckRtInfo);
		JointAnimation.reverseJointRotatesChange(bodyInfo, legBckLftInfo);

		legFntRtInfo.setNewRotateX(legFntRtInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legFntRtModifier, newHorizontalVelocity, this.RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)) + fallingLegAngle * fallingModifier);
		legFntLftInfo.setNewRotateX(legFntLftInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, newHorizontalVelocity, this.RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)) + fallingLegAngle * fallingModifier);
		legBckRtInfo.setNewRotateX(legBckRtInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legBckRtModifier, newHorizontalVelocity, this.RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)));
		legBckLftInfo.setNewRotateX(legBckLftInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legBckLftModifier, newHorizontalVelocity, this.RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)));
	}

	public void animateTail(EntityClaySkitty entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity, float angularVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, tailInfo[0][0]);

		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockTail();

		float yawChangeAngle = -(float)Math.toRadians(15);
		
		float idleFrequencyX = 1F;
		float idleFrequencyY = 1F;

		float idleAmplitudeX = 0.3F;
		float idleAmplitudeY = 0.3F;

		float moveFrequency = 1.6F;
		float moveAmplitude = 0.6F;

		// + (float)Math.toRadians(45)
		float angleChangeX = 0;
		float angleChangeY = 0;

		angleChangeX += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2 * idleFrequencyX) * idleAmplitudeX
				+ PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, moveFrequency, moveAmplitude);
		angleChangeY += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2 * idleFrequencyY) * idleAmplitudeY;

		// Movement Animations
		angleChangeY += angularVelocity * yawChangeAngle;
		
		tailInfo[0][1].setNewRotateX(tailInfo[0][1].getNewRotateX() + angleChangeX);
		tailInfo[0][1].setNewRotateY(tailInfo[0][1].getNewRotateY() + angleChangeY);

		JointAnimation.reverseJointRotatesChange(tailInfo[0][1], tailInfo[1][0]);
	}

	public void deployAnimations()
	{
		this.animationDeployer.rotate(body, bodyInfo.getNewRotates());
		this.animationDeployer.move(body, bodyInfo.getNewPnt());

		this.animationDeployer.rotate(legFntLft, legFntLftInfo.getNewRotates());
		this.animationDeployer.rotate(legFntRt, legFntRtInfo.getNewRotates());
		this.animationDeployer.rotate(legBckLft, legBckLftInfo.getNewRotates());
		this.animationDeployer.rotate(legBckRt, legBckRtInfo.getNewRotates());
		
		this.animationDeployer.rotate(neckJoint, neckJointInfo.getNewRotates());
		this.animationDeployer.rotate(neck, neckInfo.getNewRotates());
		this.animationDeployer.rotate(headJoint, headJointInfo.getNewRotates());
		this.animationDeployer.rotate(head, headInfo.getNewRotates());

		this.animationDeployer.rotate(earLft, earLftInfo.getNewRotates());
		this.animationDeployer.rotate(earLftJoint, earLftJointInfo.getNewRotates());
		this.animationDeployer.rotate(earRt, earRtInfo.getNewRotates());
		this.animationDeployer.rotate(earRtJoint, earRtJointInfo.getNewRotates());

		for(int i = 0; i < tail.length; i++)
		{
			this.animationDeployer.rotate(tail[i][0], tailInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(tail[i][1], tailInfo[i][1].getNewRotates());
		}

		this.animationDeployer.applyChanges();
	}
}