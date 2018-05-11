package fuzzyacornindustries.kindredlegacy.client.model.mob;

import fuzzyacornindustries.kindredlegacy.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.animation.JointAnimation;
import fuzzyacornindustries.kindredlegacy.animation.PartAnimate;
import fuzzyacornindustries.kindredlegacy.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.entity.mob.IMobMotionTracker;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityInfestedDeerling;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelInfestedDeerling extends ModelBase 
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public final float RUN_FREQUENCY = 1.2F;
	public final float WALK_FREQUENCY = 0.25F;
	public final float SNEAK_VELOCITY = 0.35F;

    public ModelRenderer body;
	PartInfo bodyInfo;

    public ModelRenderer neckJoint;
    public ModelRenderer neck;
	PartInfo neckJointInfo;
	PartInfo neckInfo;
    
    public ModelRenderer legFntLft;
    public ModelRenderer legFntRt;
    public ModelRenderer legBckLft;
    public ModelRenderer legBckRt;
	PartInfo legFntRtInfo;
	PartInfo legFntLftInfo;
	PartInfo legBckLftInfo;
	PartInfo legBckRtInfo;
    public ModelRenderer legFntRtInfest;
    
    public ModelRenderer bodyInfestLft;
    public ModelRenderer bodyInfestRt;
    
    public ModelRenderer headJoint;
    public ModelRenderer head;
	PartInfo headJointInfo;
	PartInfo headInfo;
    
    public ModelRenderer cheekInfest;
    public ModelRenderer headTopInfest;
    
    public ModelRenderer earLftJoint;
    public ModelRenderer earLft;
    public ModelRenderer earLftInfest;
	PartInfo earLftJointInfo;
	PartInfo earLftInfo;
    
    public ModelRenderer earRtJoint;
    public ModelRenderer earRt;
	PartInfo earRtJointInfo;
	PartInfo earRtInfo;
    
    public ModelRenderer snout;
    public ModelRenderer nose;

    public ModelRenderer tailJoint;
    public ModelRenderer tail;
    public ModelRenderer tailBotLft;
    public ModelRenderer tailBotRt;
	PartInfo tailJointInfo;
	PartInfo tailInfo;

    public ModelInfestedDeerling() 
    {
        this.textureWidth = 64;
        this.textureHeight = 64;

		int jointNumber = 0;
		int partNumber = 1;

        this.body = new ModelRenderer(this, 20, 0);
        this.body.setRotationPoint(0.0F, 19.0F, 0.0F);
        this.body.addBox(-1.5F, -1.5F, -2.5F, 3, 3, 5, 0.0F);
		bodyInfo = new PartInfo(body);

        this.neckJoint = new ModelRenderer(this, 20, 9);
        this.neckJoint.setRotationPoint(0.0F, -1.0F, -2.0F);
        this.neckJoint.addBox(-1.0F, -1.6F, -1.0F, 0, 0, 0, 0.0F);
        this.setRotateAngle(neckJoint, 0.7853981633974483F, 0.0F, 0.0F);
        neckJointInfo = new PartInfo(neckJoint);
        this.neck = new ModelRenderer(this, 20, 9);
        this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.neck.addBox(-1.0F, -1.6F, -1.0F, 2, 2, 2, 0.0F);
        neckInfo = new PartInfo(neck);

        this.legFntLft = new ModelRenderer(this, 50, 0);
        this.legFntLft.setRotationPoint(0.9F, 1.0F, -1.5F);
        this.legFntLft.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
        legFntLftInfo = new PartInfo(legFntLft);
        this.legFntRt = new ModelRenderer(this, 55, 0);
        this.legFntRt.setRotationPoint(-0.9F, 1.0F, -1.5F);
        this.legFntRt.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
        legFntRtInfo = new PartInfo(legFntRt);
        this.legBckLft = new ModelRenderer(this, 50, 6);
        this.legBckLft.setRotationPoint(0.9F, 1.0F, 1.5F);
        this.legBckLft.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
        legBckLftInfo = new PartInfo(legBckLft);
        this.legBckRt = new ModelRenderer(this, 55, 6);
        this.legBckRt.setRotationPoint(-0.9F, 1.0F, 1.5F);
        this.legBckRt.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
        legBckRtInfo = new PartInfo(legBckRt);

        this.legFntRtInfest = new ModelRenderer(this, 60, 0);
        this.legFntRtInfest.setRotationPoint(-0.3F, 2.5F, 0.0F);
        this.legFntRtInfest.addBox(-0.8F, -0.5F, -0.6F, 1, 1, 1, 0.0F);
        this.setRotateAngle(legFntRtInfest, 0.5235987755982988F, -0.0F, 0.0F);

        this.bodyInfestLft = new ModelRenderer(this, 20, 19);
        this.bodyInfestLft.setRotationPoint(1.2F, -0.4F, 1.0F);
        this.bodyInfestLft.addBox(0.0F, -1.0F, -1.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(bodyInfestLft, 0.7853981633974483F, -0.0F, 0.0F);
        this.bodyInfestRt = new ModelRenderer(this, 20, 14);
        this.bodyInfestRt.setRotationPoint(-1.0F, -1.0F, 0.4F);
        this.bodyInfestRt.addBox(-1.0F, -1.3F, -1.2F, 2, 2, 2, 0.0F);
        this.setRotateAngle(bodyInfestRt, 0.5235987755982988F, 0.5235987755982988F, -0.12217304763960307F);

        this.headJoint = new ModelRenderer(this, 0, 0);
        this.headJoint.setRotationPoint(0.0F, -1.2F, 0.0F);
        this.headJoint.addBox(-2.0F, -3.5F, -3.0F, 0, 0, 0, 0.0F);
        this.setRotateAngle(headJoint, -0.7853981633974483F, 0.0F, 0.0F);
        headJointInfo = new PartInfo(headJoint);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-2.0F, -3.5F, -3.0F, 4, 4, 4, 0.0F);
        headInfo = new PartInfo(head);
		
        this.cheekInfest = new ModelRenderer(this, 9, 28);
        this.cheekInfest.setRotationPoint(1.5F, -0.6F, -1.6F);
        this.cheekInfest.addBox(-0.2F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(cheekInfest, -0.5235987755982988F, -0.6108652381980153F, -0.3490658503988659F);
        this.headTopInfest = new ModelRenderer(this, 0, 28);
        this.headTopInfest.setRotationPoint(-1.0F, -3.0F, -1.8F);
        this.headTopInfest.addBox(-1.0F, -1.5F, -1.0F, 2, 2, 2, 0.0F);
        this.setRotateAngle(headTopInfest, 0.7853981633974483F, 0.0F, -0.6108652381980153F);

        this.earLftJoint = new ModelRenderer(this, 0, 14);
        this.earLftJoint.setRotationPoint(1.3F, -3.3F, -0.5F);
        this.earLftJoint.addBox(-0.5F, -0.7F, -0.7F, 0, 0, 0, 0.0F);
        this.setRotateAngle(earLftJoint, 0.6108652381980153F, 0.7853981633974483F, 0.0F);
        earLftJointInfo = new PartInfo(earLftJoint);
        this.earLft = new ModelRenderer(this, 0, 14);
        this.earLft.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.earLft.addBox(-0.5F, -0.7F, -0.7F, 1, 1, 5, 0.0F);
		this.earLft.setTextureOffset(8, 14).addBox(-0.3F, -1.0F, 1.0F, 1, 2, 2, 0.0F); // earLftMid
        earLftInfo = new PartInfo(earLft);
        this.earLftInfest = new ModelRenderer(this, 13, 13);
        this.earLftInfest.setRotationPoint(0.0F, 0.0F, 3.8F);
        this.earLftInfest.addBox(-0.5F, -0.5F, -0.2F, 1, 1, 1, 0.0F);
        this.setRotateAngle(earLftInfest, 0.0F, 0.9599310885968813F, 0.5235987755982988F);

        this.earRtJoint = new ModelRenderer(this, 0, 21);
        this.earRtJoint.setRotationPoint(-1.3F, -3.3F, -0.5F);
        this.earRtJoint.addBox(-0.5F, -0.7F, -0.7F, 0, 0, 0, 0.0F);
        this.setRotateAngle(earRtJoint, 0.6108652381980153F, -0.7853981633974483F, 0.0F);
        earRtJointInfo = new PartInfo(earRtJoint);
        this.earRt = new ModelRenderer(this, 0, 21);
        this.earRt.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.earRt.addBox(-0.5F, -0.7F, -0.7F, 1, 1, 5, 0.0F);
		this.earRt.setTextureOffset(8, 21).addBox(-0.7F, -1.0F, 1.0F, 1, 2, 2, 0.0F); // earRtMid
        earRtInfo = new PartInfo(earRt);

        this.snout = new ModelRenderer(this, 0, 9);
        this.snout.setRotationPoint(0.0F, -0.5F, -3.0F);
        this.snout.addBox(-1.0F, -1.0F, -0.8F, 2, 2, 2, 0.0F);
        this.setRotateAngle(snout, 0.2617993877991494F, 0.0F, 0.0F);
        this.nose = new ModelRenderer(this, 9, 10);
        this.nose.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.nose.addBox(-0.5F, -0.85F, -1.3F, 1, 1, 1, 0.0F);
        this.setRotateAngle(nose, -0.2617993877991494F, 0.0F, 0.0F);

        this.tailJoint = new ModelRenderer(this, 30, 9);
        this.tailJoint.setRotationPoint(0.0F, -1.0F, 2.0F);
        this.tailJoint.addBox(-0.5F, -3.0F, -0.5F, 0, 0, 0, 0.0F);
        this.setRotateAngle(tailJoint, -0.6981317007977318F, 0.0F, 0.0F);
        tailJointInfo = new PartInfo(tailJoint);
        this.tail = new ModelRenderer(this, 30, 9);
        this.tail.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
        tailInfo = new PartInfo(tail);
        this.tailBotLft = new ModelRenderer(this, 30, 14);
        this.tailBotLft.setRotationPoint(0.0F, -0.5F, 0.0F);
        this.tailBotLft.addBox(-0.4F, -1.9F, -0.4F, 1, 2, 1, 0.0F);
        this.setRotateAngle(tailBotLft, -0.6108652381980153F, -0.0F, 0.296705972839036F);
        this.tailBotRt = new ModelRenderer(this, 30, 18);
        this.tailBotRt.setRotationPoint(0.0F, -0.7F, 0.0F);
        this.tailBotRt.addBox(-0.6F, -1.0F, -0.5F, 1, 1, 1, 0.0F);
        this.setRotateAngle(tailBotRt, -0.8726646259971648F, 0.0F, -0.296705972839036F);
        
        this.body.addChild(this.bodyInfestLft);
        this.body.addChild(this.bodyInfestRt);
        this.body.addChild(this.legBckLft);
        this.body.addChild(this.legBckRt);
        this.body.addChild(this.legFntLft);
        this.body.addChild(this.legFntRt);
        this.body.addChild(this.neckJoint);
        this.body.addChild(this.tailJoint);

        this.legFntRt.addChild(this.legFntRtInfest);
        
        this.neckJoint.addChild(this.neck);
        this.neck.addChild(this.headJoint);
        
        this.headJoint.addChild(this.head);
        this.head.addChild(this.cheekInfest);
        this.head.addChild(this.earLftJoint);
        this.head.addChild(this.earRtJoint);
        this.head.addChild(this.headTopInfest);
        this.head.addChild(this.snout);

        this.snout.addChild(this.nose);
        
        this.earLft.addChild(this.earLftInfest);
        this.earLftJoint.addChild(this.earLft);
        this.earRtJoint.addChild(this.earRt);

        this.tail.addChild(this.tailBotLft);
        this.tail.addChild(this.tailBotRt);
        this.tailJoint.addChild(this.tail);

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

		animateBody((EntityInfestedDeerling)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity);
		animateHead((EntityInfestedDeerling)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity);
		animateLegs((EntityInfestedDeerling)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity);
		animateTail((EntityInfestedDeerling)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity, angularVelocity);

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

		tailJointInfo.resetNewAngles();
		tailInfo.resetNewAngles();
	}
    
	public void animateBody(EntityInfestedDeerling entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity)
	{
		float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
		float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);

		float bodyWalkAngle = (float)Math.toRadians(13);
		float bodyRunAngle = (float)Math.toRadians(18);

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

	public void animateHead(EntityInfestedDeerling entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, neckJointInfo);

		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockNeckBobble();

		float idleAmplitudeX = (float)Math.toRadians(25);
		
		float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
		float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);

		float walkAngle = (float)Math.toRadians(4);
		float runAngle = (float)Math.toRadians(8);

		float walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI) * walkAngle * (1 - horzVelocity)
				+ walkAngle * (1 - horzVelocity);
		float runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI) * runAngle * horzVelocity
				+ runAngle * horzVelocity;

		// + (float)Math.toRadians(45)
		float movementAmplitudeX = 0.2F;

		float idleAngleChangeX = -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeX + idleAmplitudeX;

		neckInfo.setNewRotateX(neckInfo.getNewRotateX() + idleAngleChangeX + movementAmplitudeX * (walkCycleAngleChange + runCycleAngleChange) * horzVelocity);

		JointAnimation.reverseJointRotatesChange(neckInfo, headJointInfo);

		PartAnimate.headAnimateInfoOnly(headInfo, yawHeadOffsetDifference, pitchRotation);

		/* ******* Ears ******** */
		float moveAmplitudeX = (float)Math.toRadians(85);

		float moveAngleX = moveAmplitudeX * (walkCycleAngleChange + runCycleAngleChange) * horzVelocity;

		idleAmplitudeX = 0.20F;

		float newVerticalVelocity = 1.5F * verticalVelocity;
		
		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;
		
		float fallingEarAngle = (float)Math.toRadians(40);
		
		// + (float)Math.toRadians(45)
		float angleChangeX = MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI + PI) * idleAmplitudeX;

		earRtInfo.setNewRotateX(earRtInfo.getNewRotateX() - ((angleChangeX + moveAngleX) * (1F - newVerticalVelocity) + fallingEarAngle * newVerticalVelocity) * (1F - horzVelocity));
		earLftInfo.setNewRotateX(earLftInfo.getNewRotateX() - ((angleChangeX + moveAngleX) * (1F - newVerticalVelocity) + fallingEarAngle * newVerticalVelocity) * (1F - horzVelocity));

	}

	public void animateLegs(EntityInfestedDeerling entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity)
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

		//System.out.println( "Test Model Parameters in Tiberium Model class" );
		//System.out.println( "Velocity = " + Float.toString( velocity ) );
	}

	public void animateTail(EntityInfestedDeerling entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity, float angularVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, tailJointInfo);

		float yawChangeAngle = -(float)Math.toRadians(7);
		float verticalVelocityChangeAngle = (float)Math.toRadians(-30);

		float bodyJumpAngle = (float)Math.toRadians(-55);

		float newVerticalVelocity = 1.5F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;
		
		float newHorizontalVelocity = (2F * horzVelocity > 1F ? 1F : horzVelocity * 2F);

		tailJointInfo.setNewRotateX(tailJointInfo.getNewRotateX() + bodyJumpAngle * newVerticalVelocity);
		
		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockTail();

		float idleAmplitudeX = 0.20F;
		float idleAmplitudeZ = 0.45F;

		float moveFrequency = 1F;
		float moveAmplitude = 0.2F;

		// + (float)Math.toRadians(45)
		float angleChangeX = 0;
		float angleChangeZ = 0;

		angleChangeX += ((MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2) * idleAmplitudeX  * (1F - newHorizontalVelocity))
				+ PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, this.RUN_FREQUENCY, moveAmplitude))
				* (1F - Math.abs(newVerticalVelocity))
				+ verticalVelocity * verticalVelocityChangeAngle;
		angleChangeZ += ((MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2) * idleAmplitudeZ)
				* (1F - Math.abs(angularVelocity))) * (1F - (Math.abs(newVerticalVelocity) * 0.5F))
				+ angularVelocity * yawChangeAngle;
		
		tailInfo.setNewRotateX(tailInfo.getNewRotateX() + angleChangeX);
		tailInfo.setNewRotateZ(tailInfo.getNewRotateZ() + angleChangeZ);
	}

	public void deployAnimations()
	{
		this.animationDeployer.rotate(body, bodyInfo.getNewRotates());

		this.animationDeployer.rotate(neckJoint, neckJointInfo.getNewRotates());
		this.animationDeployer.rotate(neck, neckInfo.getNewRotates());
		this.animationDeployer.rotate(headJoint, headJointInfo.getNewRotates());
		this.animationDeployer.rotate(head, headInfo.getNewRotates());

		this.animationDeployer.rotate(legFntRt, legFntRtInfo.getNewRotates());
		this.animationDeployer.rotate(legFntLft, legFntLftInfo.getNewRotates());
		this.animationDeployer.rotate(legBckRt, legBckRtInfo.getNewRotates());
		this.animationDeployer.rotate(legBckLft, legBckLftInfo.getNewRotates());

		this.animationDeployer.rotate(earLft, earLftInfo.getNewRotates());
		this.animationDeployer.rotate(earLftJoint, earLftJointInfo.getNewRotates());
		this.animationDeployer.rotate(earRt, earRtInfo.getNewRotates());
		this.animationDeployer.rotate(earRtJoint, earRtJointInfo.getNewRotates());

		this.animationDeployer.rotate(tail, tailInfo.getNewRotates());

		this.animationDeployer.applyChanges();
	}
}