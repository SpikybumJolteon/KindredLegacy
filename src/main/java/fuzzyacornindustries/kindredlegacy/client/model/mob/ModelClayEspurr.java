package fuzzyacornindustries.kindredlegacy.client.model.mob;

import fuzzyacornindustries.kindredlegacy.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.animation.JointAnimation;
import fuzzyacornindustries.kindredlegacy.animation.PartAnimate;
import fuzzyacornindustries.kindredlegacy.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityClayEspurr;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelClayEspurr extends ModelBase 
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public ModelRenderer body;
	public ModelRenderer chestHair;
	PartInfo bodyInfo;

	public ModelRenderer legRt;
	public ModelRenderer legLft;
	PartInfo legLftInfo;
	PartInfo legRtInfo;

	public ModelRenderer armRt;
	public ModelRenderer armLft;
	PartInfo armRtInfo;
	PartInfo armLftInfo;

	public ModelRenderer headJoint;
	public ModelRenderer head;
	PartInfo headJointInfo;
	PartInfo headInfo;

	public ModelRenderer cheekHairRt;
	public ModelRenderer cheekHairLft;
	public ModelRenderer headHair;

	public ModelRenderer earRtJoint;
	public ModelRenderer earRt;
	public ModelRenderer earLftJoint;
	public ModelRenderer earLft;
	PartInfo earRtJointInfo;
	PartInfo earRtInfo;
	PartInfo earLftJointInfo;
	PartInfo earLftInfo;

	public ModelRenderer tail[][] = new ModelRenderer[2][2];
	PartInfo tailInfo[][] = new PartInfo[tail.length][tail[0].length];

	public ModelClayEspurr() 
	{
		this.textureWidth = 64;
		this.textureHeight = 32;

		int jointNumber = 0;
		int partNumber = 1;

		this.body = new ModelRenderer(this, 13, 0);
		this.body.setRotationPoint(0.0F, 22.7F, 0.0F);
		this.body.addBox(-1.0F, -2.5F, -1.0F, 2, 3, 2, 0.0F);
		bodyInfo = new PartInfo(body);
		this.chestHair = new ModelRenderer(this, 22, 0);
		this.chestHair.setRotationPoint(0.0F, -1.5F, -0.5F);
		this.chestHair.addBox(-0.5F, -0.5F, -1.3F, 1, 1, 1, 0.0F);
		this.setRotateAngle(chestHair, 0.5235987755982988F, -0.0F, 0.0F);

		this.legRt = new ModelRenderer(this, 13, 6);
		this.legRt.setRotationPoint(-0.5F, 0.3F, 0.0F);
		this.legRt.addBox(-0.49F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
		legRtInfo = new PartInfo(legRt);
		this.legLft = new ModelRenderer(this, 18, 6);
		this.legLft.setRotationPoint(0.5F, 0.3F, 0.0F);
		this.legLft.addBox(-0.51F, 0.0F, -0.5F, 1, 1, 1, 0.0F);
		legLftInfo = new PartInfo(legLft);

		this.armRt = new ModelRenderer(this, 13, 9);
		this.armRt.setRotationPoint(-1.0F, -0.9F, 0.0F);
		this.armRt.addBox(-0.8F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
		armRtInfo = new PartInfo(armRt);
		this.armLft = new ModelRenderer(this, 18, 9);
		this.armLft.setRotationPoint(1.0F, -0.9F, 0.0F);
		this.armLft.addBox(-0.2F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
		armLftInfo = new PartInfo(armLft);

		this.headJoint = new ModelRenderer(this, 0, 0);
		this.headJoint.setRotationPoint(0.0F, -2.0F, 0.0F);
		this.headJoint.addBox(-1.5F, -3.0F, -1.5F, 0, 0, 0, 0.0F);
		headJointInfo = new PartInfo(headJoint);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-1.5F, -3.0F, -1.5F, 3, 3, 3, 0.0F);
		headInfo = new PartInfo(head);

		this.cheekHairRt = new ModelRenderer(this, 0, 19);
		this.cheekHairRt.setRotationPoint(1.0F, -0.5F, 0.5F);
		this.cheekHairRt.addBox(0.3F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(cheekHairRt, 0.0F, -0.0F, 0.5235987755982988F);
		this.cheekHairLft = new ModelRenderer(this, 5, 19);
		this.cheekHairLft.setRotationPoint(-1.0F, -0.5F, 0.5F);
		this.cheekHairLft.addBox(-1.3F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(cheekHairLft, 0.0F, -0.0F, -0.5235987755982988F);
		this.headHair = new ModelRenderer(this, 0, 15);
		this.headHair.setRotationPoint(0.0F, -2.8F, -0.5F);
		this.headHair.addBox(-0.5F, -0.5F, -1.5F, 1, 1, 2, 0.0F);
		this.setRotateAngle(headHair, -1.0471975511965976F, 0.0F, 0.0F);

		this.earRtJoint = new ModelRenderer(this, 0, 7);
		this.earRtJoint.setRotationPoint(-1.0F, -2.3F, 0.0F);
		this.earRtJoint.addBox(-1.5F, -1.0F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earRtJoint, 0.0F, 0.0F, -0.7853981633974483F);
		earRtJointInfo = new PartInfo(earRtJoint);
		this.earRt = new ModelRenderer(this, 0, 7);
		this.earRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earRt.addBox(-1.5F, -1.0F, -1.0F, 2, 1, 2, 0.0F);
		earRtInfo = new PartInfo(earRt);
		this.earLftJoint = new ModelRenderer(this, 4, 11);
		this.earLftJoint.setRotationPoint(1.0F, -2.3F, 0.0F);
		this.earLftJoint.addBox(-0.5F, -1.0F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earLftJoint, 0.0F, -0.0F, 0.7853981633974483F);
		earLftJointInfo = new PartInfo(earLftJoint);
		this.earLft = new ModelRenderer(this, 4, 11);
		this.earLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earLft.addBox(-0.5F, -1.0F, -1.0F, 2, 1, 2, 0.0F);
		earLftInfo = new PartInfo(earLft);

		int tailNumber = 0;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 15, 17); // tailBaseJoint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.8F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][jointNumber], 0.3490658503988659F, -0.0F, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 15, 17); // tailBase
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 15, 13); // tailEndJoint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.7F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.1F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][jointNumber], 1.0471975511965976F, -0.0F, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 15, 13);  // tailEnd
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.1F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);

		this.body.addChild(this.armLft);
		this.body.addChild(this.armRt);
		this.body.addChild(this.chestHair);
		this.body.addChild(this.headJoint);
		this.body.addChild(this.legLft);
		this.body.addChild(this.legRt);
		this.body.addChild(this.tail[0][0]); // tailBaseJoint

		this.headJoint.addChild(this.head);
		this.head.addChild(this.cheekHairLft);
		this.head.addChild(this.cheekHairRt);
		this.head.addChild(this.earLftJoint);
		this.head.addChild(this.earRtJoint);
		this.head.addChild(this.headHair);

		this.earLftJoint.addChild(this.earLft);
		this.earRtJoint.addChild(this.earRt);

		this.tail[0][0].addChild(this.tail[0][1]); // tailBaseJoint * tailBase
		this.tail[0][1].addChild(this.tail[1][0]); // tailBase * tailEndJoint
		this.tail[1][0].addChild(this.tail[1][1]); // tailEndJoint * tailEnd

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

		animateHead((EntityClayEspurr)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
		animateArms((EntityClayEspurr)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
		animateLegs((EntityClayEspurr)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
		animateTail((EntityClayEspurr)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);

		deployAnimations();
	}

	public void resetPartInfos()
	{
		bodyInfo.resetNewAngles();
		headJointInfo.resetNewAngles();
		headInfo.resetNewAngles();

		legLftInfo.resetNewAngles();
		legRtInfo.resetNewAngles();
		
		armRtInfo.resetNewAngles();
		armLftInfo.resetNewAngles();

		earRtJointInfo.resetNewAngles();
		earRtInfo.resetNewAngles();
		earLftJointInfo.resetNewAngles();
		earLftInfo.resetNewAngles();

		for(int i = 0; i < tail.length; i++)
		{
			tailInfo[i][0].resetNewAngles();
			tailInfo[i][1].resetNewAngles();
		}
	}

	public void animateHead(EntityClayEspurr entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize)
	{
		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockEars();

		PartAnimate.headAnimateInfoOnlyWithAngleModifiers(headInfo, yawHeadOffsetDifference, pitchRotation, 0.6F, 0.6F);

		float moveFrequencyZ = 0.7F;
		float moveAmplitudeZ = 0.25F;

		float firstAngleChangeZ = PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, moveFrequencyZ, moveAmplitudeZ);

		float idleAmplitudeZ = 0.20F;

		// + (float)Math.toRadians(45)
		float angleChangeZ = firstAngleChangeZ;

		angleChangeZ += MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredZ(0) * 2 * PI + PI) * idleAmplitudeZ;

		earRtInfo.setNewRotateZ(earRtInfo.getNewRotateZ() - angleChangeZ);
		earLftInfo.setNewRotateZ(earLftInfo.getNewRotateZ() + angleChangeZ);
	}

	public void animateArms(EntityClayEspurr entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize)
	{
		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockArms();

		float moveFrequencyY = 2.0F;
		float moveAmplitudeY = 0.65F;

		float angleChangeY = PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, moveFrequencyY, moveAmplitudeY);

		float idleAmplitudeZ = 0.20F;

		// + (float)Math.toRadians(45)
		float angleChangeZ = 0;

		angleChangeZ += MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredZ(0) * 2 * PI + PI) * idleAmplitudeZ;

		armRtInfo.setNewRotateY(armRtInfo.getNewRotateY() + angleChangeY);
		armRtInfo.setNewRotateZ(armRtInfo.getNewRotateZ() - angleChangeZ);
		armLftInfo.setNewRotateY(armLftInfo.getNewRotateY() + angleChangeY);
		armLftInfo.setNewRotateZ(armLftInfo.getNewRotateZ() + angleChangeZ);
	}

	public void animateLegs(EntityClayEspurr entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize)
	{
		float moveFrequency = 2.5F;
		float moveAmplitude = 0.9F;

		legRtInfo.setNewRotateX(legRtInfo.getNewRotateX() + PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, moveFrequency, moveAmplitude));
		legLftInfo.setNewRotateX(legLftInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, moveFrequency, moveAmplitude));
	}

	public void animateTail(EntityClayEspurr entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize)
	{
		for(int i = 0; i < tail.length; i++)
		{
			IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockTail(i);

			float idleAmplitudeX = 0.30F;
			float idleAmplitudeZ = 0.85F;
			
			float moveFrequency = 1F;
			float moveAmplitude = 0.2F;

			// + (float)Math.toRadians(45)
			float angleChangeX = 0;
			float angleChangeZ = 0;

			angleChangeX += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2) * idleAmplitudeX
					+ PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, moveFrequency, moveAmplitude);
			angleChangeZ += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredZ(0) * PI * 2) * idleAmplitudeZ;

			tailInfo[i][1].setNewRotateX(tailInfo[i][1].getNewRotateX() + angleChangeX);
			tailInfo[i][1].setNewRotateZ(tailInfo[i][1].getNewRotateZ() + angleChangeZ);

			JointAnimation.reverseJointRotatesChange(tailInfo[i][1], tailInfo[i][0]);
		}
	}

	public void deployAnimations()
	{
		this.animationDeployer.rotate(body, bodyInfo.getNewRotates());
		this.animationDeployer.rotate(headJoint, headJointInfo.getNewRotates());
		this.animationDeployer.rotate(head, headInfo.getNewRotates());

		this.animationDeployer.rotate(legLft, legLftInfo.getNewRotates());
		this.animationDeployer.rotate(legRt, legRtInfo.getNewRotates());

		this.animationDeployer.rotate(armLft, armLftInfo.getNewRotates());
		this.animationDeployer.rotate(armRt, armRtInfo.getNewRotates());

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