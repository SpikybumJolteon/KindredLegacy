package fuzzyacornindustries.kindredlegacy.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;

import fuzzyacornindustries.kindredlegacy.client.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.client.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.client.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.animation.JointAnimation;
import fuzzyacornindustries.kindredlegacy.client.animation.PartAnimate;
import fuzzyacornindustries.kindredlegacy.client.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.ClayPurrloinEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.HostilePokemon;
import net.minecraft.client.renderer.entity.model.TintedAgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ClayPurrloinModel<T extends HostilePokemon> extends TintedAgeableModel<T>
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	float movementFrequency = 2.0F;

	public ModelRenderer body;
	PartInfo bodyInfo;

	public ModelRenderer legFntRt;
	public ModelRenderer legFntLft;
	public ModelRenderer legBckRt;
	public ModelRenderer legBckLft;
	PartInfo legFntLftInfo;
	PartInfo legFntRtInfo;
	PartInfo legBckRtInfo;
	PartInfo legBckLftInfo;

	public ModelRenderer neckJoint;
	public ModelRenderer neck;
	PartInfo neckJointInfo;
	PartInfo neckInfo;

	public ModelRenderer headJoint;
	public ModelRenderer head;
	PartInfo headJointInfo;
	PartInfo headInfo;

	public ModelRenderer nose;

	public ModelRenderer earRtJoint;
	public ModelRenderer earRt;
	PartInfo earRtJointInfo;
	PartInfo earRtInfo;
	public ModelRenderer earLftJoint;
	public ModelRenderer earLft;
	PartInfo earLftJointInfo;
	PartInfo earLftInfo;

	public ModelRenderer tail[][] = new ModelRenderer[8][2];
	PartInfo tailInfo[][] = new PartInfo[tail.length][tail[0].length];

	public ClayPurrloinModel() 
	{
		this.textureWidth = 64;
		this.textureHeight = 32;

		int jointNumber = 0;
		int partNumber = 1;

		this.body = new ModelRenderer(this, 52, 26);
		this.body.setRotationPoint(0.0F, 21.0F, 0.0F);
		this.body.addBox(-1.0F, -1.0F, -2.0F, 2, 2, 4, 0.0F);
		bodyInfo = new PartInfo(body);

		this.legFntRt = new ModelRenderer(this, 42, 21);
		this.legFntRt.setRotationPoint(-0.4F, 0.5F, -1.2F);
		this.legFntRt.addBox(-0.5F, -0.5F, -0.5F, 1, 3, 1, 0.0F);
		legFntRtInfo = new PartInfo(legFntRt);
		this.legFntLft = new ModelRenderer(this, 47, 21);
		this.legFntLft.setRotationPoint(0.4F, 0.5F, -1.1F);
		this.legFntLft.addBox(-0.5F, -0.5F, -0.5F, 1, 3, 1, 0.0F);
		legFntLftInfo = new PartInfo(legFntLft);
		this.legBckRt = new ModelRenderer(this, 42, 26);
		this.legBckRt.setRotationPoint(-0.4F, 0.5F, 1.1F);
		this.legBckRt.addBox(-0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(legBckRt, -0.17453292519943295F, -0.0F, 0.0F);
		this.legBckRt.setTextureOffset(42, 30).addBox(-0.5F, 1.5F, -0.2F, 1, 1, 1, 0.0F); // legBckRtHind
		legBckRtInfo = new PartInfo(legBckRt);
		this.legBckLft = new ModelRenderer(this, 47, 26);
		this.legBckLft.setRotationPoint(0.4F, 0.5F, 1.2F);
		this.legBckLft.addBox(-0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(legBckLft, -0.17453292519943295F, -0.0F, 0.0F);
		this.legBckLft.setTextureOffset(47, 30).addBox(-0.5F, 1.5F, -0.2F, 1, 1, 1, 0.0F); // legBckLftHind
		legBckLftInfo = new PartInfo(legBckLft);

		this.neckJoint = new ModelRenderer(this, 60, 23);
		this.neckJoint.setRotationPoint(0.0F, -0.5F, -1.5F);
		this.neckJoint.addBox(-0.5F, -2.0F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(neckJoint, 0.45378560551852565F, 0.0F, 0.0F);
		neckJointInfo = new PartInfo(neckJoint);
		this.neck = new ModelRenderer(this, 60, 23);
		this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.neck.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		neckInfo = new PartInfo(neck);

		this.headJoint = new ModelRenderer(this, 0, 0);
		this.headJoint.setRotationPoint(0.0F, -1.6F, 0.0F);
		this.headJoint.addBox(-1.5F, -2.5F, -2.3F, 0, 0, 0, 0.0F);
		this.setRotateAngle(headJoint, -0.4363323129985824F, -0.0F, 0.0F);
		headJointInfo = new PartInfo(headJoint);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-1.5F, -2.5F, -2.3F, 3, 3, 3, 0.0F);
		headInfo = new PartInfo(head);

		this.nose = new ModelRenderer(this, 10, 0);
		this.nose.setRotationPoint(0.0F, -0.1F, -2.0F);
		this.nose.addBox(-0.5F, -0.5F, -0.7F, 1, 1, 1, 0.0F);
		this.setRotateAngle(nose, 0.17453292519943295F, -0.0F, 0.0F);

		this.earRtJoint = new ModelRenderer(this, 0, 9);
		this.earRtJoint.setRotationPoint(-1.3F, -2.0F, -0.5F);
		this.earRtJoint.addBox(-1.0F, -1.2F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earRtJoint, 0.0F, -0.0F, -0.7853981633974483F);
		earRtJointInfo = new PartInfo(earRtJoint);
		this.earRt = new ModelRenderer(this, 0, 9);
		this.earRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earRt.addBox(-1.0F, -1.2F, -0.5F, 2, 2, 1, 0.0F);
		this.earRt.setTextureOffset(1, 7).addBox(-0.5F, -2.0F, -0.49F, 1, 1, 1, 0.0F); // earRtTip
		earRtInfo = new PartInfo(earRt);
		this.earLftJoint = new ModelRenderer(this, 7, 9);
		this.earLftJoint.setRotationPoint(1.3F, -2.0F, -0.5F);
		this.earLftJoint.addBox(-1.0F, -1.2F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earLftJoint, 0.0F, 0.0F, 0.7853981633974483F);
		earLftJointInfo = new PartInfo(earLftJoint);
		this.earLft = new ModelRenderer(this, 7, 9);
		this.earLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earLft.addBox(-1.0F, -1.2F, -0.5F, 2, 2, 1, 0.0F);
		this.earLft.setTextureOffset(8, 7).addBox(-0.5F, -2.0F, -0.49F, 1, 1, 1, 0.0F); // earLftTip
		earLftInfo = new PartInfo(earLft);

		int tailNumber = 0;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 18, 30); // tail1Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, -0.3F, 1.5F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, 0.0F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 18, 30); // tail1
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 1, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 18, 27); // tail2Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.7F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.1F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 18, 27); // tail2
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.1F, 1, 1, 1, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 18, 24); // tail3Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.7F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.1F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 18, 24); // tail3
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.1F, 1, 1, 1, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 18, 21); // tail4Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.7F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.1F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 18, 21); // tail4
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.1F, 1, 1, 1, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 18, 18); // tail5Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.7F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.1F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 18, 18); // tail5
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.1F, 1, 1, 1, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 18, 15); // tail6Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.7F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.1F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 18, 15); // tail6
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.1F, 1, 1, 1, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 18, 12); // tail7Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.7F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.1F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 18, 12); // tail7
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.1F, 1, 1, 1, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 17, 8); // tail8Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.7F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][jointNumber], 2.443460952792061F, 0.0F, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 17, 8); // tail8
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);

		this.body.addChild(this.legBckLft);
		this.body.addChild(this.legBckRt);
		this.body.addChild(this.legFntLft);
		this.body.addChild(this.legFntRt);
		this.body.addChild(this.neckJoint);
		this.body.addChild(this.tail[0][0]); // tail1Joint

		this.neckJoint.addChild(this.neck);
		this.neck.addChild(this.headJoint);
		
		this.head.addChild(this.nose);
		this.headJoint.addChild(this.head);
		
		this.head.addChild(this.earLftJoint);
		this.earLftJoint.addChild(this.earLft);
		this.head.addChild(this.earRtJoint);
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
	public void setRotationAngles(T entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch)
	{
		animate(entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch);
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) 
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	public void animate(Entity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.animationDeployer.update((IAnimatedEntity)entity);
		resetPartInfos();

		float sizeModifier = 1F;//(float) Math.sqrt(((EntityOkamiEspeon) entity).defaultHeight / entity.height);

		animateBody((ClayPurrloinEntity)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier);
		animateHead((ClayPurrloinEntity)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier);
		animateLegs((ClayPurrloinEntity)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier);
		animateTail((ClayPurrloinEntity)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier);

		deployAnimations();
	}

	public void resetPartInfos()
	{
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
    
	public void animateBody(ClayPurrloinEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize)
	{
		float moveAmplitude = 0.2F;

		bodyInfo.setNewRotateX(PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, movementFrequency, moveAmplitude));
	}

	public void animateHead(ClayPurrloinEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, neckJointInfo);

		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockNeckBobble();

		float idleAmplitudeX = 0.25F;

		// + (float)Math.toRadians(45)
		float movementAmplitude = 0.2F;
		float angleChangeX = PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, movementFrequency, movementAmplitude);

		angleChangeX += -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeX + idleAmplitudeX;

		neckInfo.setNewRotateX(neckInfo.getNewRotateX() + angleChangeX);
		neckInfo.setNewRotateY(neckInfo.getNewRotateY());

		JointAnimation.reverseJointRotatesChange(neckInfo, headJointInfo);

		PartAnimate.headAnimateInfoOnly(headInfo, netHeadYaw, headPitch);

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

	public void animateLegs(ClayPurrloinEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize)
	{
		float moveAmplitude = 0.8F;

		// Run constants
		float legFntRtModifierRun = 2F * PI * (1F/4F);
		float legBckRtModifierRun = 2F * PI * (5F/16F);
		float legBckLftModifierRun = 2F * PI * (8F/16F);

		// Walk constants
		float legFntRtModifierWalk = 2F * PI * (1F/2F);
		float legBckRtModifierWalk = 2F * PI * (3F/4F);
		float legBckLftModifierWalk = 2F * PI * (1F/4F);

		float legFntRtModifierDifference = legFntRtModifierRun - legFntRtModifierWalk;
		float legBckRtModifierDifference = legBckRtModifierRun - legBckRtModifierWalk;
		float legBckLftModifierDifference = legBckLftModifierRun - legBckLftModifierWalk;

		float legFntRtModifier;
		float legBckRtModifier;
		float legBckLftModifier;

		float sprint = 1.0F;
		float sneak = 0.35F;

		if(horzVelocity > sneak)
		{
			float modifier = (float)(1F - (Math.pow(Math.E, -5F * ((horzVelocity - sneak)/(sprint - sneak)))));
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

		/**/
		JointAnimation.reverseJointRotatesChange(bodyInfo, legFntRtInfo);
		JointAnimation.reverseJointRotatesChange(bodyInfo, legFntLftInfo);
		JointAnimation.reverseJointRotatesChange(bodyInfo, legBckRtInfo);
		JointAnimation.reverseJointRotatesChange(bodyInfo, legBckLftInfo);

		legFntRtInfo.setNewRotateX(PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legFntRtModifier, horzVelocity, movementFrequency, moveAmplitude));
		legFntLftInfo.setNewRotateX(PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, movementFrequency, moveAmplitude));
		legBckRtInfo.setNewRotateX(legBckRtInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legBckRtModifier, horzVelocity, movementFrequency, moveAmplitude));
		legBckLftInfo.setNewRotateX(legBckLftInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legBckLftModifier, horzVelocity, movementFrequency, moveAmplitude));
	}

	public void animateTail(ClayPurrloinEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, tailInfo[0][0]);

		for(int i = 0; i < tail.length; i++)
		{
			IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockTail(i);

			float idleAmplitudeX = 0.20F;
			float idleAmplitudeY = 0.45F;
			
			float moveAmplitude = 0.2F;

			// + (float)Math.toRadians(45)
			float angleChangeX = 0;
			float angleChangeY = 0;

			angleChangeX += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2) * idleAmplitudeX
					+ PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, movementFrequency, moveAmplitude);
			angleChangeY += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2) * idleAmplitudeY;

			tailInfo[i][1].setNewRotateX(tailInfo[i][1].getNewRotateX() + angleChangeX);
			tailInfo[i][1].setNewRotateY(tailInfo[i][1].getNewRotateY() + angleChangeY);
		}
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

		for(int i = 0; i < tail.length; i++)
		{
			this.animationDeployer.rotate(tail[i][0], tailInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(tail[i][1], tailInfo[i][1].getNewRotates());
		}

		this.animationDeployer.applyChanges();
	}

	@Override
	protected Iterable<ModelRenderer> getHeadParts()
	{
		return ImmutableList.of(); //this.head
	}

	@Override
	protected Iterable<ModelRenderer> getBodyParts() 
	{
		return ImmutableList.of(this.body);
	}
}