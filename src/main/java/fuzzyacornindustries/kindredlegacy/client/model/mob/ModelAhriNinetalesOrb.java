package fuzzyacornindustries.kindredlegacy.client.model.mob;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.animation.JointAnimation;
import fuzzyacornindustries.kindredlegacy.animation.PartAnimate;
import fuzzyacornindustries.kindredlegacy.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.animation.Vector3f;
import fuzzyacornindustries.kindredlegacy.entity.mob.IAnimateAhriNinetales;
import fuzzyacornindustries.kindredlegacy.entity.mob.IMobMotionTracker;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityVastayaNinetales;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryAhriNinetalesAttackID;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryUniversalAttackID;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelAhriNinetalesOrb extends ModelBase 
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public final float RUN_FREQUENCY = 0.6F;
	public final float WALK_FREQUENCY = 1.2F;
	public final float SNEAK_VELOCITY = 0.45F;

	public ModelRenderer bodyJoint;
	public ModelRenderer body;
	public PartInfo bodyJointInfo;
	public PartInfo bodyInfo;

	public ModelRenderer shoulderRt;
	public ModelRenderer armRtJoint;
	public ModelRenderer armRt;
	public ModelRenderer forearmRtJoint;
	public ModelRenderer forearmRt;
	public PartInfo shoulderRtInfo;
	public PartInfo armRtJointInfo;
	public PartInfo armRtInfo;
	public PartInfo forearmRtJointInfo;
	public PartInfo forearmRtInfo;

	public ModelRenderer energyBallJoint;
	public ModelRenderer energyBall[] = new ModelRenderer[2];
	public PartInfo energyBallJointInfo;
	public PartInfo energyBallInfo[] = new PartInfo[energyBall.length];

	public ModelAhriNinetalesOrb() 
	{
		this.textureWidth = 128;
		this.textureHeight = 64;

		this.bodyJoint = new ModelRenderer(this, 0, 0);
		this.bodyJoint.setRotationPoint(0.0F, 13.5F, 1.0F);
		this.bodyJoint.addBox(-3.0F, -12.0F, -3.0F, 0, 0, 0, 0.0F);
		this.bodyJointInfo = new PartInfo(this.bodyJoint);
		this.body = new ModelRenderer(this, 0, 45);
		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.addBox(-3.0F, -12.0F, -3.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(body, 0.17453292519943295F, 0.0F, 0.0F);
		this.bodyInfo = new PartInfo(this.body);

		this.shoulderRt = new ModelRenderer(this, 3, 18);
		this.shoulderRt.setRotationPoint(-2.4F, -10.4F, 0.0F);
		this.shoulderRt.addBox(-3.0F, -1.5F, -1.5F, 0, 0, 0, 0.0F);
		this.shoulderRtInfo = new PartInfo(this.shoulderRt);
		this.armRtJoint = new ModelRenderer(this, 0, 0);
		this.armRtJoint.setRotationPoint(-1.5F, 0.1F, 0.0F);
		this.armRtJoint.addBox(-1.5F, -1.5F, -7.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(armRtJoint, 0.4363323129985824F + (float)Math.toRadians(-10), 0.22689280275926282F, 0.0F);
		this.armRtJointInfo = new PartInfo(this.armRtJoint);
		this.armRt = new ModelRenderer(this, 0, 24);
		this.armRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.armRt.addBox(-1.5F, -1.5F, -7.0F, 0, 0, 0, 0.0F);
		this.armRtInfo = new PartInfo(this.armRt);
		this.forearmRtJoint = new ModelRenderer(this, 0, 0);
		this.forearmRtJoint.setRotationPoint(0.0F, 0.0F, -6.5F);
		this.forearmRtJoint.addBox(-1.5F, -1.5F, -6.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(forearmRtJoint, -0.8726646259971648F, 0.0F, 0.0F);
		this.forearmRtJointInfo = new PartInfo(this.forearmRtJoint);
		this.forearmRt = new ModelRenderer(this, 0, 34);
		this.forearmRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.forearmRt.addBox(-1.5F, -1.5F, -6.5F, 0, 0, 0, 0.0F);
		this.forearmRtInfo = new PartInfo(this.forearmRt);

		this.energyBallJoint = new ModelRenderer(this, 0, 52);
		this.energyBallJoint.setRotationPoint(0.0F, 0.0F, -11.0F);
		this.energyBallJoint.addBox(-3.0F, -1.5F, -3.0F, 0, 0, 0, 0.0F);
		this.energyBallJointInfo = new PartInfo(this.energyBallJoint);

		int energyBallNumber = 0;
		this.energyBall[energyBallNumber] = new ModelRenderer(this, 53, 37); // energyBall1
		this.energyBall[energyBallNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.energyBall[energyBallNumber].addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F);
		this.energyBallInfo[energyBallNumber] = new PartInfo(this.energyBall[energyBallNumber]);
		energyBallNumber++;
		this.energyBall[energyBallNumber] = new ModelRenderer(this, 53, 37); // energyBall2
		this.energyBall[energyBallNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.energyBall[energyBallNumber].addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F);
		this.energyBallInfo[energyBallNumber] = new PartInfo(this.energyBall[energyBallNumber]);

		this.bodyJoint.addChild(this.body);
		this.body.addChild(this.shoulderRt);

		this.shoulderRt.addChild(this.armRtJoint);
		this.armRtJoint.addChild(this.armRt);
		this.armRt.addChild(this.forearmRtJoint);
		this.forearmRtJoint.addChild(this.forearmRt);

		this.forearmRt.addChild(this.energyBallJoint);

		for(int i = 0; i < energyBall.length; i++)
		{
			this.energyBallJoint.addChild(this.energyBall[i]);
		}

		animationDeployer = new AnimationDeployer(this);
	}

	@Override
	public void render(Entity entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize) 
	{
		if(!(entity instanceof EntityVastayaNinetales && ((EntityVastayaNinetales)entity).isSitting()))
		{
			animate(entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);

			this.bodyJoint.render(modelSize);
		}
	}

	public void render(Entity entity)
	{
		this.body.render(0.0625F);
		/*
		System.out.println( "Test Animation Tick Code Jade Glaive render class" );
		System.out.println( Float.toString( ((EntityOkamiSylveon)entity).getAnimationTick() ) );*/
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) 
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void animate(Entity entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize)
	{	
		/* Static references to array lengths in Entity class from Model class
		 * makes the glow/normal models not animate in sync. */
		this.animationDeployer.update((IAnimatedEntity)entity);
		resetPartInfos();

		float angularVelocity = ((IMobMotionTracker)entity).getAngularVelocity();
		float verticalVelocity = (float)((IMobMotionTracker)entity).getHeightVelocity();

		float idleDampener = 1F;

		float tick = animationDeployer.getEntity().getAnimationTick() + KindredLegacyMain.proxy.getPartialTick();

		if((animationDeployer.getEntity().getAnimationID() == LibraryAhriNinetalesAttackID.FOXFIRE_SUMMON || 
				animationDeployer.getEntity().getAnimationID() == LibraryAhriNinetalesAttackID.FOXFIRE_STORM ||
				(animationDeployer.getEntity().getAnimationID() == LibraryAhriNinetalesAttackID.FIREBLAST && tick <= 28)))
		{
			idleDampener = animateFoxfireSummon(animationDeployer.getEntity(), distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
		}
		else if(animationDeployer.getEntity().getAnimationID() == LibraryAhriNinetalesAttackID.FIREBALL)
		{
			idleDampener = animateFireballAttack(animationDeployer.getEntity(), distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
		}
		else if(((animationDeployer.getEntity().getAnimationID() == LibraryAhriNinetalesAttackID.JUMP_FIREBALL || 
				(animationDeployer.getEntity().getAnimationID() == LibraryAhriNinetalesAttackID.FIREBLAST && tick > 28))))
		{
			if(animationDeployer.getEntity().getAnimationID() == LibraryAhriNinetalesAttackID.FIREBLAST) tick -= 28F;
			idleDampener = animateJumpFireballAttack(animationDeployer.getEntity(), distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, tick);
		}
		else if(animationDeployer.getEntity().getAnimationID() == LibraryUniversalAttackID.IGNITE)
		{
			idleDampener = animateIgnite(animationDeployer.getEntity(), distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
		}


		animateBody((IAnimateAhriNinetales)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, idleDampener, verticalVelocity);
		animateArms((IAnimateAhriNinetales)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, idleDampener, verticalVelocity);
		animateOrb((IAnimateAhriNinetales)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, idleDampener);

		deployAnimations();
	}

	public void resetPartInfos()
	{
		bodyJointInfo.resetNewAngles();
		bodyJointInfo.resetNewPnt();

		bodyInfo.resetNewAngles();

		armRtJointInfo.resetNewAngles();

		armRtInfo.resetNewAngles();

		forearmRtJointInfo.resetNewAngles();

		forearmRtInfo.resetNewAngles();

		for(int i = 0; i < energyBallInfo.length; i++)
		{
			energyBallInfo[i].resetNewAngles();
		}

		energyBallJointInfo.resetNewPnt();
	}

	public float animateFoxfireSummon(IAnimatedEntity entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize)
	{
		float idleDampener = 1F;

		float tick = animationDeployer.getEntity().getAnimationTick() + KindredLegacyMain.proxy.getPartialTick();

		float squatEnd = 5;
		float jumpEnd = 12;
		float landEnd = 19;
		float actionEnd = 25;

		if(tick < squatEnd && tick > 0)
		{
			idleDampener = (1F + (float)MathHelper.cos(PI*(tick/squatEnd)));
		}
		else if(tick < jumpEnd)
		{
			idleDampener = 0;
		}
		else if(tick < landEnd)
		{
			idleDampener = 0;
		}
		else if(tick < actionEnd)
		{
			idleDampener = (1F + (float)MathHelper.cos(PI + PI * ((tick - landEnd)/(actionEnd - landEnd))));
		}

		if(idleDampener > 1F)
		{
			idleDampener = 1F;
		}

		Vector3f bodyJointPointChanges = new Vector3f();

		Vector3f bodyJointAngleChanges = new Vector3f();
		Vector3f bodyAngleChanges = new Vector3f();

		float squatDurationStart = 0F;
		float squatDurationEnd = squatEnd;

		Vector3f bodyJointSquatPointChanges = new Vector3f(0F, 3.8F, 3F);

		Vector3f bodySquatAngleChanges = new Vector3f((float)Math.toRadians(25), 0F, 0F);

		Vector3f bodyJointTurnAngle = new Vector3f(0, (float)Math.toRadians(360), 0);

		float jumpDurationStart = squatDurationEnd;
		float jumpDurationEnd = jumpEnd;

		Vector3f bodyJointJumpPointChanges = new Vector3f(0F, -9.5F, -3.5F);

		Vector3f bodyJumpAngleChanges = new Vector3f((float)Math.toRadians(-20), 0F, 0F);

		float landDurationStart = jumpDurationEnd;
		float landDurationEnd = landEnd;

		Vector3f bodyJointLandPointChanges = new Vector3f(0F, -bodyJointJumpPointChanges.getY(), -bodyJointJumpPointChanges.getZ());

		Vector3f bodyLandAngleChanges = new Vector3f(-bodyJumpAngleChanges.getX(), 0F, 0F);
		Vector3f armLftJointLandAngleChanges = new Vector3f((float)Math.toRadians(30), 0F, 0F);
		Vector3f forearmLftJointLandAngleChanges = new Vector3f((float)Math.toRadians(5), 0F, 0F);


		float squatDurationCovered = PartAnimate.calculateDuration(tick, squatDurationStart, squatDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyJointPointChanges, bodyJointSquatPointChanges,
				squatDurationCovered);

		PartAnimate.changeOverDurationSCurve(bodyAngleChanges, bodySquatAngleChanges,
				squatDurationCovered);


		float jumpDurationCovered = PartAnimate.calculateDuration(tick, jumpDurationStart, jumpDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyJointPointChanges, bodyJointJumpPointChanges,
				jumpDurationCovered);

		PartAnimate.changeOverDurationSCurve(bodyAngleChanges, bodyJumpAngleChanges,
				jumpDurationCovered);

		float landDurationCovered = PartAnimate.calculateDuration(tick, landDurationStart, landDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyJointPointChanges, bodyJointLandPointChanges,
				landDurationCovered);


		PartAnimate.changeOverDurationSCurve(bodyAngleChanges, bodyLandAngleChanges,
				landDurationCovered);
		PartAnimate.changeOverDurationSCurve(bodyJointAngleChanges, bodyJointTurnAngle,
				PartAnimate.calculateDuration(tick, jumpDurationStart, landDurationEnd));

		float returnDampener = 1 - PartAnimate.calculateDuration(tick, landDurationEnd, actionEnd);

		bodyJointPointChanges.multiplyVector(returnDampener);

		bodyAngleChanges.multiplyVector(returnDampener);


		PartAnimate.applyPointChangeVectorToInfo(bodyJointInfo, bodyJointPointChanges);

		PartAnimate.applyRotationChangeVectorToInfo(bodyJointInfo, bodyJointAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(bodyInfo, bodyAngleChanges);

		//System.out.println( "Test Animation Tick Code in Model class" );
		//System.out.println( Float.toString( tick ) );

		return idleDampener;
	}

	public float animateJumpFireballAttack(IAnimatedEntity entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float tick)
	{
		float idleDampener = 1F;

		float squatEnd = 5;
		float jumpEnd = 12;
		float landEnd = 19;
		float actionEnd = 25;

		if(tick < squatEnd && tick > 0)
		{
			idleDampener = (1F + (float)MathHelper.cos(PI*(tick/squatEnd)));
		}
		else if(tick < jumpEnd)
		{
			idleDampener = 0;
		}
		else if(tick < landEnd)
		{
			idleDampener = 0;
		}
		else if(tick < actionEnd)
		{
			idleDampener = (1F + (float)MathHelper.cos(PI + PI * ((tick - landEnd)/(actionEnd - landEnd))));
		}

		if(idleDampener > 1F)
		{
			idleDampener = 1F;
		}

		Vector3f bodyJointPointChanges = new Vector3f();

		Vector3f bodyJointAngleChanges = new Vector3f();
		Vector3f bodyAngleChanges = new Vector3f();
		Vector3f armRtJointAngleChanges = new Vector3f();
		Vector3f forearmRtJointAngleChanges = new Vector3f();

		float squatDurationStart = 0F;
		float squatDurationEnd = squatEnd;

		Vector3f bodyJointSquatPointChanges = new Vector3f(0F, 3.8F, 3F);

		Vector3f bodySquatAngleChanges = new Vector3f((float)Math.toRadians(25), 0F, 0F);


		float jumpDurationStart = squatEnd;
		float jumpDurationEnd = jumpEnd;

		Vector3f bodyJointJumpPointChanges = new Vector3f(0F, -9.5F, -3.5F);

		Vector3f bodyJumpAngleChanges = new Vector3f((float)Math.toRadians(-25), 0F, 0F);
		Vector3f armRtJointJumpAngleChanges = new Vector3f((float)Math.toRadians(-55), (float)Math.toRadians(-13), 0F);
		Vector3f forearmRtJointJumpAngleChanges = new Vector3f((float)Math.toRadians(-10), 0F, 0F);

		float landDurationStart = jumpEnd;
		float landDurationEnd = landEnd;

		Vector3f bodyJointLandPointChanges = new Vector3f(0F, -bodyJointJumpPointChanges.getY(), -bodyJointJumpPointChanges.getZ());

		Vector3f bodyLandAngleChanges = new Vector3f((float)Math.toRadians(35), 0F, 0F);
		Vector3f armRtJointLandAngleChanges = new Vector3f((float)Math.toRadians(70), (float)Math.toRadians(-13), 0F);
		Vector3f forearmRtJointLandAngleChanges = new Vector3f((float)Math.toRadians(30), 0F, 0F);


		float squatDurationCovered = PartAnimate.calculateDuration(tick, squatDurationStart, squatDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyJointPointChanges, bodyJointSquatPointChanges,
				squatDurationCovered);

		PartAnimate.changeOverDurationSCurve(bodyAngleChanges, bodySquatAngleChanges,
				squatDurationCovered);


		float jumpDurationCovered = PartAnimate.calculateDuration(tick, jumpDurationStart, jumpDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyJointPointChanges, bodyJointJumpPointChanges,
				jumpDurationCovered);

		PartAnimate.changeOverDurationSCurve(bodyAngleChanges, bodyJumpAngleChanges,
				jumpDurationCovered);
		PartAnimate.changeOverDurationSCurve(armRtJointAngleChanges, armRtJointJumpAngleChanges,
				jumpDurationCovered);
		PartAnimate.changeOverDurationSCurve(forearmRtJointAngleChanges, forearmRtJointJumpAngleChanges,
				jumpDurationCovered);


		float landDurationCovered = PartAnimate.calculateDuration(tick, landDurationStart, landDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyJointPointChanges, bodyJointLandPointChanges,
				landDurationCovered);

		PartAnimate.changeOverDurationSCurve(bodyAngleChanges, bodyLandAngleChanges,
				landDurationCovered);
		PartAnimate.changeOverDurationSCurve(armRtJointAngleChanges, armRtJointLandAngleChanges,
				landDurationCovered);
		PartAnimate.changeOverDurationSCurve(forearmRtJointAngleChanges, forearmRtJointLandAngleChanges,
				landDurationCovered);


		float returnDampener = 1 - PartAnimate.calculateDuration(tick, landDurationEnd, actionEnd);

		bodyJointPointChanges.multiplyVector(returnDampener);

		bodyAngleChanges.multiplyVector(returnDampener);
		armRtJointAngleChanges.multiplyVector(returnDampener);
		forearmRtJointAngleChanges.multiplyVector(returnDampener);

		PartAnimate.applyPointChangeVectorToInfo(bodyJointInfo, bodyJointPointChanges);

		PartAnimate.applyRotationChangeVectorToInfo(bodyJointInfo, bodyJointAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(bodyInfo, bodyAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(armRtJointInfo, armRtJointAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(forearmRtJointInfo, forearmRtJointAngleChanges);

		return idleDampener;
	}

	public float animateFireballAttack(IAnimatedEntity entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize)
	{
		float idleDampener = 1F;

		float tick = animationDeployer.getEntity().getAnimationTick() + KindredLegacyMain.proxy.getPartialTick();

		float raiseEnd = 3;
		float thrustEnd = 8;
		float actionEnd = 12;

		Vector3f bodyAngleChanges = new Vector3f();
		Vector3f armRtJointAngleChanges = new Vector3f();
		Vector3f forearmRtJointAngleChanges = new Vector3f();

		float raiseDurationStart = 0F;
		float raiseDurationEnd = raiseEnd;

		Vector3f bodyRaiseAngleChanges = new Vector3f((float)Math.toRadians(-10), 0F, 0F);
		Vector3f armRtJointRaiseAngleChanges = new Vector3f((float)Math.toRadians(-40), (float)Math.toRadians(-13), 0F);
		Vector3f forearmRtJointRaiseAngleChanges = new Vector3f((float)Math.toRadians(-10), 0F, 0F);

		float thrustDurationStart = raiseEnd;
		float thrustDurationEnd = thrustEnd;

		Vector3f bodyThrustAngleChanges = new Vector3f((float)Math.toRadians(20), 0F, 0F);
		Vector3f armRtJointThrustAngleChanges = new Vector3f((float)Math.toRadians(55), (float)Math.toRadians(-13), 0F);
		Vector3f forearmRtJointThrustAngleChanges = new Vector3f((float)Math.toRadians(30), 0F, 0F);


		float raiseDurationCovered = PartAnimate.calculateDuration(tick, raiseDurationStart, raiseDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyAngleChanges, bodyRaiseAngleChanges,
				raiseDurationCovered);
		PartAnimate.changeOverDurationSCurve(armRtJointAngleChanges, armRtJointRaiseAngleChanges,
				raiseDurationCovered);
		PartAnimate.changeOverDurationSCurve(forearmRtJointAngleChanges, forearmRtJointRaiseAngleChanges,
				raiseDurationCovered);


		float thrustDurationCovered = PartAnimate.calculateDuration(tick, thrustDurationStart, thrustDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyAngleChanges, bodyThrustAngleChanges,
				thrustDurationCovered);
		PartAnimate.changeOverDurationSCurve(armRtJointAngleChanges, armRtJointThrustAngleChanges,
				thrustDurationCovered);
		PartAnimate.changeOverDurationSCurve(forearmRtJointAngleChanges, forearmRtJointThrustAngleChanges,
				thrustDurationCovered);


		float returnDampener = 1 - PartAnimate.calculateDuration(tick, thrustDurationEnd, actionEnd);

		bodyAngleChanges.multiplyVector(returnDampener);
		armRtJointAngleChanges.multiplyVector(returnDampener);
		forearmRtJointAngleChanges.multiplyVector(returnDampener);


		PartAnimate.applyRotationChangeVectorToInfo(bodyInfo, bodyAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(armRtJointInfo, armRtJointAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(forearmRtJointInfo, forearmRtJointAngleChanges);

		return idleDampener;
	}

	public float animateIgnite(IAnimatedEntity entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize)
	{
		float idleDampener = 1F;

		float tick = animationDeployer.getEntity().getAnimationTick() + KindredLegacyMain.proxy.getPartialTick();

		float raiseEnd = 5;
		float thrustEnd = 11;
		float actionEnd = 15;

		Vector3f bodyAngleChanges = new Vector3f();

		float raiseDurationStart = 0F;
		float raiseDurationEnd = raiseEnd;

		Vector3f bodyRaiseAngleChanges = new Vector3f(0F, (float)Math.toRadians(-40), (float)Math.toRadians(15));

		float thrustDurationStart = raiseEnd;
		float thrustDurationEnd = thrustEnd;

		Vector3f bodyThrustAngleChanges = new Vector3f(0F, (float)Math.toRadians(60), (float)Math.toRadians(-30));


		float raiseDurationCovered = PartAnimate.calculateDuration(tick, raiseDurationStart, raiseDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyAngleChanges, bodyRaiseAngleChanges,
				raiseDurationCovered);


		float thrustDurationCovered = PartAnimate.calculateDuration(tick, thrustDurationStart, thrustDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyAngleChanges, bodyThrustAngleChanges,
				thrustDurationCovered);


		float returnDampener = 1 - PartAnimate.calculateDuration(tick, thrustDurationEnd, actionEnd);

		bodyAngleChanges.multiplyVector(returnDampener);


		PartAnimate.applyRotationChangeVectorToInfo(bodyInfo, bodyAngleChanges);

		return idleDampener;
	}

	public void animateBody(IAnimateAhriNinetales entity, float distanceMoved, float horzVelocity, 
			float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener, float verticalVelocity)
	{
		float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
		float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);

		float walkMaximumChangeY = 0.75F;
		float runMaximumChangeY = 1.1F;

		float walkCyclePointChange = MathHelper.cos(2 * walkCycleInterval * 2 * PI + PI/2) * walkMaximumChangeY * (1 - horzVelocity);
		float runCyclePointChange = MathHelper.cos(2 * runCycleInterval * 2 * PI + PI/2) * runMaximumChangeY * horzVelocity;

		bodyJointInfo.setNewPointY(bodyJointInfo.getNewPointY() + (walkCyclePointChange + runCyclePointChange) * horzVelocity * (1F - Math.abs(verticalVelocity)));

		float bodyWalkAngle = (float)Math.toRadians(3);
		float bodyRunAngle = (float)Math.toRadians(5);

		float walkCycleAngleChange = MathHelper.cos(2 * walkCycleInterval * 2 * PI + PI/2) * bodyWalkAngle * (1 - horzVelocity)
				+ bodyWalkAngle * (1 - horzVelocity);
		float runCycleAngleChange = MathHelper.cos(2 * runCycleInterval * 2 * PI + PI/2) * bodyRunAngle * horzVelocity
				+ bodyRunAngle * horzVelocity;

		bodyInfo.setNewRotateX(bodyInfo.getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocity * idleDampener);

		/* ******************* */
		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockBody();

		float idleAmplitudeX = 0.05F;

		float angleChangeX = (-MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeX + idleAmplitudeX) * (1 - horzVelocity);

		bodyInfo.setNewRotateX(bodyInfo.getNewRotateX() + angleChangeX * idleDampener * (1F - Math.abs(verticalVelocity)));
	}

	public void animateArms(IAnimateAhriNinetales entity, float distanceMoved, float horzVelocity, 
			float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener, float verticalVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, armRtJointInfo);

		float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
		float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);

		float newVerticalVelocity = 2.5F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;

		/* ************************ */
		float runAngleChangeArmRtX = (float)Math.toRadians(8);

		float runCycleAngleChangeArmRtX = MathHelper.cos(0.5F * walkCycleInterval * 2 * PI + PI/2) * runAngleChangeArmRtX * horzVelocity
				+ runAngleChangeArmRtX * horzVelocity;
		armRtJointInfo.setNewRotateX(armRtJointInfo.getNewRotateX() + runCycleAngleChangeArmRtX * horzVelocity * idleDampener);

		float runAngleChangeForearmRtX = (float)Math.toRadians(8);

		float runCycleAngleChangeForearmRtX = MathHelper.cos(0.5F * walkCycleInterval * 2 * PI + PI/2) * runAngleChangeForearmRtX * horzVelocity
				+ runAngleChangeForearmRtX * horzVelocity;
		forearmRtJointInfo.setNewRotateX(forearmRtJointInfo.getNewRotateX() + runCycleAngleChangeForearmRtX * horzVelocity * idleDampener);

		/* ************************ */
		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockBody();

		float idleAmplitudeX = 0.15F;

		currentIdleAnimationClock = entity.getIdleAnimationClockArmRt();

		idleAmplitudeX = 0.15F;
		float idleAmplitudeY = 0.15F;

		float idleAngleChangeX = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeX * (1 - horzVelocity);
		float idleAngleChangeY = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI + PI/2) * idleAmplitudeY * (1 - horzVelocity);
		armRtJointInfo.setNewRotateX(armRtJointInfo.getNewRotateX() + idleAngleChangeX * idleDampener);
		armRtJointInfo.setNewRotateY(armRtJointInfo.getNewRotateY() + idleAngleChangeY * idleDampener);

		idleAngleChangeX = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI - (PI/2)) * idleAmplitudeX * (1 - horzVelocity);
		idleAngleChangeY = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeY * (1 - horzVelocity);
		forearmRtJointInfo.setNewRotateX(forearmRtJointInfo.getNewRotateX() + idleAngleChangeX * idleDampener);
		forearmRtJointInfo.setNewRotateY(forearmRtJointInfo.getNewRotateY() + idleAngleChangeY * idleDampener);
	}

	public void animateOrb(IAnimateAhriNinetales entity, float distanceMoved, float horzVelocity, 
			float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener)
	{
		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockOrb();

		float idleAmplitudeZ = 2.0F;

		for(int i = 0; i < energyBallInfo.length; i++)
		{
			float frequency = 2.0F;
			float angleChange = -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI * frequency) * 2 * PI;

			energyBallInfo[i].setNewRotateX(angleChange * (1 - (2 * i)));
			energyBallInfo[i].setNewRotateY(angleChange * (1 - (2 * i)));
		}

		float pntChangeZ = (-MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeZ) - idleAmplitudeZ;

		energyBallJointInfo.setNewPointZ(energyBallJointInfo.getNewPointZ() + pntChangeZ * idleDampener);
	}

	public void deployAnimations()
	{
		this.animationDeployer.rotate(bodyJoint, bodyJointInfo.getNewRotates());
		this.animationDeployer.move(bodyJoint, bodyJointInfo.getNewPnt());

		this.animationDeployer.rotate(body, bodyInfo.getNewRotates());


		this.animationDeployer.rotate(armRtJoint, armRtJointInfo.getNewRotates());

		this.animationDeployer.rotate(forearmRtJoint, forearmRtJointInfo.getNewRotates());

		for(int i = 0; i < energyBallInfo.length; i++)
		{
			this.animationDeployer.rotate(energyBall[i], energyBallInfo[i].getNewRotates());
		}

		this.animationDeployer.move(energyBallJoint, energyBallJointInfo.getNewPnt());

		this.animationDeployer.applyChanges();
	}
}