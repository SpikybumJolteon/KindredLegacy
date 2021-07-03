package fuzzyacornindustries.kindredlegacy.client.renderer.entity.model.tool;

import com.google.common.collect.ImmutableList;

import fuzzyacornindustries.kindredlegacy.KindredLegacy;
import fuzzyacornindustries.kindredlegacy.client.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.client.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.client.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.animation.PartAnimate;
import fuzzyacornindustries.kindredlegacy.client.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.client.animation.Vector3f;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.OkamiPokemon;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.TamablePokemon;
import fuzzyacornindustries.kindredlegacy.lib.action.LibraryOkamiPokemonAttackID;
import net.minecraft.client.renderer.entity.model.TintedAgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class OkamiSunGlaiveModel<T extends TamablePokemon> extends TintedAgeableModel<T>
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public final float RUN_FREQUENCY = 1.2F;
	public final float WALK_FREQUENCY = 1.2F;
	public final float SNEAK_VELOCITY = 0.45F;

	public ModelRenderer body;
	PartInfo bodyInfo;
	PartInfo bodySitInfo;

	public ModelRenderer glaiveJointBase;
	public ModelRenderer glaiveJointSwingPoint;
	public ModelRenderer glaiveJointMain;
	PartInfo glaiveJointBaseInfo;
	PartInfo glaiveJointSwingPointInfo;
	PartInfo glaiveJointMainInfo;

	public ModelRenderer glaiveOuterJoint;
	public ModelRenderer glaiveOuterRing1;
	public ModelRenderer glaiveOuterRing2;
	public ModelRenderer glaiveOuterRing3;
	public ModelRenderer glaiveOuterRing4;
	public ModelRenderer glaiveOuterRing5;
	public ModelRenderer glaiveOuterRing6;
	public ModelRenderer glaiveOuterRing7;
	public ModelRenderer glaiveOuterRing8;
	public ModelRenderer glaiveOuterRing9;
	public ModelRenderer glaiveOuterRing10;

	public ModelRenderer ringSpikeSmallFntRt;
	public ModelRenderer ringSpikeSmallFntLft;
	public ModelRenderer ringSpikeSmallBckLft;
	public ModelRenderer ringSpikeSmallBckRt;

	public ModelRenderer ringSpikeRtLarge;
	public ModelRenderer ringSpikeRtLargeTip;
	public ModelRenderer ringSpikeFntLarge;
	public ModelRenderer ringSpikeFntLargeTip;
	public ModelRenderer ringSpikeLftLarge;
	public ModelRenderer ringSpikeLftLargeTip;

	public ModelRenderer glaiveBladeJoint;
	public ModelRenderer glaiveBladeEdgeRtJoint;
	public ModelRenderer glaiveBladeEdgeLftJoint;
	public ModelRenderer glaiveBladeEdgeRt;
	public ModelRenderer glaiveBladeEdgeLft;

	public ModelRenderer glaiveCoreJoint;
	public ModelRenderer glaiveCore1;
	public ModelRenderer glaiveCore2;
	PartInfo glaiveCoreJointInfo;

	public OkamiSunGlaiveModel() 
	{
		this.textureWidth = 64;
		this.textureHeight = 32;

		this.body = new ModelRenderer(this, 46, 55);
		this.body.setRotationPoint(0.0F, 17.0F, 0.0F);
		this.body.addBox(-2.0F, -2.5F, -0.5F, 0, 0, 0, 0.0F);
		this.bodyInfo = new PartInfo(body);

		this.body.setRotationPoint(0.0F, 20.0F, 0.0F);
		this.setRotateAngle(body, -0.7853981633974483F, 0.0F, 0.0F);
		this.bodySitInfo = new PartInfo(body);

		this.glaiveJointBase = new ModelRenderer(this, 0, 0);
		this.glaiveJointBase.setRotationPoint(0.0F, -6.0F, 1.0F);
		this.glaiveJointBase.addBox(0.0F, -0.5F, 0.0F, 0, 0, 0, 0.0F);
		this.glaiveJointBaseInfo = new PartInfo(glaiveJointBase);
		this.glaiveJointSwingPoint = new ModelRenderer(this, 0, 0);
		this.glaiveJointSwingPoint.setRotationPoint(0.0F, 0.0F, -14.0F);
		this.glaiveJointSwingPoint.addBox(0.0F, -0.5F, 0.0F, 0, 0, 0, 0.0F);
		this.glaiveJointSwingPointInfo = new PartInfo(glaiveJointSwingPoint);
		this.glaiveJointMain = new ModelRenderer(this, 0, 0);
		this.glaiveJointMain.setRotationPoint(0.0F, 0.0F, 15.0F);
		this.glaiveJointMain.addBox(0.0F, -0.5F, 0.0F, 0, 0, 0, 0.0F);
		this.glaiveJointMainInfo = new PartInfo(glaiveJointMain);

		this.glaiveOuterJoint = new ModelRenderer(this, 0, 0);
		this.glaiveOuterJoint.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveOuterJoint.addBox(-1.0F, -0.5F, -3.0F, 0, 0, 0, 0.0F);
		this.glaiveOuterRing1 = new ModelRenderer(this, 0, 0);
		this.glaiveOuterRing1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveOuterRing1.addBox(-1.0F, -0.5F, -3.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(glaiveOuterRing1, 0.0F, -3.141592653589793F, 0.0F);
		this.glaiveOuterRing2 = new ModelRenderer(this, 6, 0);
		this.glaiveOuterRing2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveOuterRing2.addBox(-1.0F, -0.49F, -3.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(glaiveOuterRing2, 0.0F, -2.5132741228718345F, 0.0F);
		this.glaiveOuterRing3 = new ModelRenderer(this, 12, 0);
		this.glaiveOuterRing3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveOuterRing3.addBox(-1.0F, -0.5F, -3.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(glaiveOuterRing3, 0.0F, -1.8849555921538759F, 0.0F);
		this.glaiveOuterRing4 = new ModelRenderer(this, 18, 0);
		this.glaiveOuterRing4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveOuterRing4.addBox(-1.0F, -0.49F, -3.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(glaiveOuterRing4, 0.0F, -1.2566370614359172F, 0.0F);
		this.glaiveOuterRing5 = new ModelRenderer(this, 24, 0);
		this.glaiveOuterRing5.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveOuterRing5.addBox(-1.0F, -0.5F, -3.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(glaiveOuterRing5, 0.0F, -0.6283185307179586F, 0.0F);
		this.glaiveOuterRing6 = new ModelRenderer(this, 30, 0);
		this.glaiveOuterRing6.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveOuterRing6.addBox(-1.0F, -0.49F, -3.0F, 2, 1, 1, 0.0F);
		this.glaiveOuterRing7 = new ModelRenderer(this, 36, 0);
		this.glaiveOuterRing7.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveOuterRing7.addBox(-1.0F, -0.5F, -3.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(glaiveOuterRing7, 0.0F, 0.6283185307179586F, 0.0F);
		this.glaiveOuterRing8 = new ModelRenderer(this, 42, 0);
		this.glaiveOuterRing8.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveOuterRing8.addBox(-1.0F, -0.49F, -3.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(glaiveOuterRing8, 0.0F, 1.2566370614359172F, 0.0F);
		this.glaiveOuterRing9 = new ModelRenderer(this, 48, 0);
		this.glaiveOuterRing9.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveOuterRing9.addBox(-1.0F, -0.5F, -3.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(glaiveOuterRing9, 0.0F, 1.8849555921538759F, 0.0F);
		this.glaiveOuterRing10 = new ModelRenderer(this, 54, 0);
		this.glaiveOuterRing10.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveOuterRing10.addBox(-1.0F, -0.49F, -3.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(glaiveOuterRing10, 0.0F, 2.5132741228718345F, 0.0F);

		this.ringSpikeSmallFntRt = new ModelRenderer(this, 8, 14);
		this.ringSpikeSmallFntRt.setRotationPoint(-2.1F, 0.0F, -2.1F);
		this.ringSpikeSmallFntRt.addBox(-0.5F, -0.51F, -0.5F, 1, 1, 1, 0.0F);
		this.ringSpikeSmallFntLft = new ModelRenderer(this, 14, 14);
		this.ringSpikeSmallFntLft.setRotationPoint(2.1F, 0.0F, -2.1F);
		this.ringSpikeSmallFntLft.addBox(-0.5F, -0.51F, -0.5F, 1, 1, 1, 0.0F);
		this.ringSpikeSmallBckLft = new ModelRenderer(this, 14, 11);
		this.ringSpikeSmallBckLft.setRotationPoint(2.1F, 0.0F, 2.1F);
		this.ringSpikeSmallBckLft.addBox(-0.5F, -0.51F, -0.5F, 1, 1, 1, 0.0F);
		this.ringSpikeSmallBckRt = new ModelRenderer(this, 8, 11);
		this.ringSpikeSmallBckRt.setRotationPoint(-2.1F, 0.0F, 2.1F);
		this.ringSpikeSmallBckRt.addBox(-0.5F, -0.51F, -0.5F, 1, 1, 1, 0.0F);

		this.ringSpikeRtLarge = new ModelRenderer(this, 0, 3);
		this.ringSpikeRtLarge.setRotationPoint(-3.0F, 0.0F, 0.0F);
		this.ringSpikeRtLarge.addBox(-0.5F, -0.51F, -2.5F, 1, 1, 3, 0.0F);
		this.setRotateAngle(ringSpikeRtLarge, 0.0F, 1.5707963267948966F, 0.0F);
		this.ringSpikeRtLargeTip = new ModelRenderer(this, 2, 8);
		this.ringSpikeRtLargeTip.setRotationPoint(0.0F, 0.0F, -2.5F);
		this.ringSpikeRtLargeTip.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(ringSpikeRtLargeTip, 0.0F, 0.7853981633974483F, 0.0F);
		this.ringSpikeFntLarge = new ModelRenderer(this, 9, 3);
		this.ringSpikeFntLarge.setRotationPoint(0.0F, 0.0F, -3.0F);
		this.ringSpikeFntLarge.addBox(-0.5F, -0.51F, -2.5F, 1, 1, 3, 0.0F);
		this.ringSpikeFntLargeTip = new ModelRenderer(this, 11, 8);
		this.ringSpikeFntLargeTip.setRotationPoint(0.0F, 0.0F, -2.5F);
		this.ringSpikeFntLargeTip.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(ringSpikeFntLargeTip, 0.0F, 0.7853981633974483F, 0.0F);
		this.ringSpikeLftLarge = new ModelRenderer(this, 18, 3);
		this.ringSpikeLftLarge.setRotationPoint(3.0F, 0.0F, 0.0F);
		this.ringSpikeLftLarge.addBox(-0.5F, -0.51F, -2.5F, 1, 1, 3, 0.0F);
		this.setRotateAngle(ringSpikeLftLarge, 0.0F, -1.5707963267948966F, 0.0F);
		this.ringSpikeLftLargeTip = new ModelRenderer(this, 20, 8);
		this.ringSpikeLftLargeTip.setRotationPoint(0.0F, 0.0F, -2.5F);
		this.ringSpikeLftLargeTip.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(ringSpikeLftLargeTip, 0.0F, 0.7853981633974483F, 0.0F);

		this.glaiveBladeJoint = new ModelRenderer(this, 22, 22);
		this.glaiveBladeJoint.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveBladeJoint.addBox(-1.0F, -0.51F, -9.3F, 0, 0, 0, 0.0F);
		this.setRotateAngle(glaiveBladeJoint, 0.0F, 3.141592653589793F, 0.0F);
		this.glaiveBladeEdgeRtJoint = new ModelRenderer(this, 0, 0);
		this.glaiveBladeEdgeRtJoint.setRotationPoint(1.0F, 0.0F, -3.0F);
		this.glaiveBladeEdgeRtJoint.addBox(-0.5F, -0.51F, -8.3F, 0, 0, 0, 0.0F);
		this.setRotateAngle(glaiveBladeEdgeRtJoint, 0.0F, 0.08726646259971647F, 0.0F);
		this.glaiveBladeEdgeRt = new ModelRenderer(this, 38, 17);
		this.glaiveBladeEdgeRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveBladeEdgeRt.addBox(-0.5F, -0.51F, -11.4F, 1, 1, 12, 0.0F);
		this.setRotateAngle(glaiveBladeEdgeRt, 0.0F, 0.0F, 0.7853981633974483F);
		this.glaiveBladeEdgeLftJoint = new ModelRenderer(this, 0, 0);
		this.glaiveBladeEdgeLftJoint.setRotationPoint(-1.0F, 0.0F, -3.0F);
		this.glaiveBladeEdgeLftJoint.addBox(-0.5F, -0.51F, -8.3F, 0, 0, 0, 0.0F);
		this.setRotateAngle(glaiveBladeEdgeLftJoint, 0.0F, -0.08726646259971647F, 0.0F);
		this.glaiveBladeEdgeLft = new ModelRenderer(this, 23, 19);
		this.glaiveBladeEdgeLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveBladeEdgeLft.addBox(-0.5F, -0.51F, -11.4F, 1, 1, 12, 0.0F);
		this.setRotateAngle(glaiveBladeEdgeLft, 0.0F, 0.0F, 0.7853981633974483F);

		this.glaiveCoreJoint = new ModelRenderer(this, 0, 0);
		this.glaiveCoreJoint.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveCoreJoint.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
		this.glaiveCore1 = new ModelRenderer(this, 47, 3);
		this.glaiveCore1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveCore1.addBox(-1.0F, -0.49F, -1.0F, 2, 1, 2, 0.0F);
		this.setRotateAngle(glaiveCore1, 0.0F, -0.7853981633974483F, 0.0F);
		this.glaiveCore2 = new ModelRenderer(this, 56, 3);
		this.glaiveCore2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveCore2.addBox(-1.0F, -0.5F, -1.0F, 2, 1, 2, 0.0F);
		this.glaiveCoreJointInfo = new PartInfo(glaiveCoreJoint);

		this.body.addChild(this.glaiveJointBase);
		this.glaiveJointBase.addChild(this.glaiveJointSwingPoint);

		this.glaiveJointSwingPoint.addChild(this.glaiveJointMain);
		this.glaiveJointMain.addChild(this.glaiveCoreJoint);
		this.glaiveJointMain.addChild(this.glaiveOuterJoint);

		this.glaiveBladeEdgeLftJoint.addChild(this.glaiveBladeEdgeLft);
		this.glaiveBladeJoint.addChild(this.glaiveBladeEdgeLftJoint);
		this.glaiveBladeEdgeRtJoint.addChild(this.glaiveBladeEdgeRt);
		this.glaiveBladeJoint.addChild(this.glaiveBladeEdgeRtJoint);

		this.glaiveCoreJoint.addChild(this.glaiveCore1);
		this.glaiveCoreJoint.addChild(this.glaiveCore2);

		this.glaiveOuterJoint.addChild(this.glaiveBladeJoint);
		this.glaiveOuterJoint.addChild(this.glaiveOuterRing1);
		this.glaiveOuterJoint.addChild(this.glaiveOuterRing2);
		this.glaiveOuterJoint.addChild(this.glaiveOuterRing3);
		this.glaiveOuterJoint.addChild(this.glaiveOuterRing4);
		this.glaiveOuterJoint.addChild(this.glaiveOuterRing5);
		this.glaiveOuterJoint.addChild(this.glaiveOuterRing6);
		this.glaiveOuterJoint.addChild(this.glaiveOuterRing7);
		this.glaiveOuterJoint.addChild(this.glaiveOuterRing8);
		this.glaiveOuterJoint.addChild(this.glaiveOuterRing9);
		this.glaiveOuterJoint.addChild(this.glaiveOuterRing10);

		this.glaiveOuterJoint.addChild(this.ringSpikeSmallBckLft);
		this.glaiveOuterJoint.addChild(this.ringSpikeSmallBckRt);
		this.glaiveOuterJoint.addChild(this.ringSpikeSmallFntLft);
		this.glaiveOuterJoint.addChild(this.ringSpikeSmallFntRt);

		this.glaiveOuterJoint.addChild(this.ringSpikeFntLarge);
		this.ringSpikeFntLarge.addChild(this.ringSpikeFntLargeTip);
		this.glaiveOuterJoint.addChild(this.ringSpikeLftLarge);
		this.ringSpikeLftLarge.addChild(this.ringSpikeLftLargeTip);
		this.glaiveOuterJoint.addChild(this.ringSpikeRtLarge);
		this.ringSpikeRtLarge.addChild(this.ringSpikeRtLargeTip);

		animationDeployer = new AnimationDeployer(this);
	}
	
	@Override
	public void setRotationAngles(T entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch)
	{
		animate((OkamiPokemon)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch);
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) 
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void animate(OkamiPokemon entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch)
	{	
		this.animationDeployer.update(entity);
		resetPartInfos();

		float verticleVelocity = (float)entity.getHeightVelocity();

		float idleDampener = 1F;

		float sizeModifier = 1F;//((OkamiEspeonEntity) entity).defaultHeight / ((OkamiEspeonEntity) entity).height;

		if(animationDeployer.getEntity().getAnimationID() == LibraryOkamiPokemonAttackID.GLAIVE_SLASH || 
				animationDeployer.getEntity().getAnimationID() == LibraryOkamiPokemonAttackID.GLAIVE_SLASH_REVERSE)
		{
			idleDampener = animateSlash(animationDeployer.getEntity(), distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier);
		}
		
		animateBody(entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, idleDampener, verticleVelocity);
		animateGlaive(entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, idleDampener);

		deployAnimations();
	}

	public void resetPartInfos()
	{
		bodyInfo.resetNewAngles();
		bodyInfo.resetNewPnt();

		glaiveJointBaseInfo.resetNewAngles();
		glaiveJointSwingPointInfo.resetNewAngles();

		glaiveJointMainInfo.resetNewAngles();
		glaiveJointMainInfo.resetNewPnt();

		glaiveCoreJointInfo.resetNewAngles();
		glaiveCoreJointInfo.resetNewPnt();
	}

	public float animateSlash(IAnimatedEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize)
	{
		float directionModifier = 1.0F;

		if(animationDeployer.getEntity().getAnimationID() == LibraryOkamiPokemonAttackID.GLAIVE_SLASH_REVERSE)
		{
			directionModifier *= -1.0F;
		}

		float idleDampener = 1F;

		float tick = animationDeployer.getEntity().getAnimationTick() + KindredLegacy.proxy.getPartialTick();

		float firstPhaseEnd = 2;
		float secondPhaseEnd = 6;
		float endDuration = 8;

		if(tick < firstPhaseEnd && tick > 0)
		{
			idleDampener = (1F + (float)MathHelper.cos(PI*(tick/firstPhaseEnd)));
		}
		else if(tick < secondPhaseEnd)
		{
			idleDampener = 0;
		}
		else if(tick < endDuration)
		{
			idleDampener = (1F + (float)MathHelper.cos(PI + PI * ((tick - secondPhaseEnd)/(endDuration - secondPhaseEnd))));
		}

		Vector3f glaiveJointSwingPointChanges = new Vector3f(0,0,0);
		Vector3f glaiveJointMainChanges = new Vector3f(0,0,0);

		Vector3f wholeGlaiveJointSwingPointChanges = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(360 * directionModifier),
				(float)Math.toRadians(0));

		Vector3f wholeGlaiveJointMainInfo = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(720 * directionModifier),
				(float)Math.toRadians(0));

		/* ******** Beginning Slash Timers *********/
		//float beginningSlashDurationStart = 0;
		//float beginningSlashDurationEnd = firstPhaseEnd;
		//float beginningSlashDuration = firstPhaseEnd;

		/* ******** Mid Slash Timers *********/
		//float midSlashDurationStart = firstPhaseEnd;
		//float midSlashDurationEnd = secondPhaseEnd;
		//float midSlashDuration = secondPhaseEnd - firstPhaseEnd;

		/* ******** End Slash Timers *********/
		//float endSlashDurationStart = secondPhaseEnd;
		//float endSlashDurationEnd = endDuration;
		//float endSlashDuration = endDuration - secondPhaseEnd;

		/*
		 * Whole slash animations
		 */
		float wholeSlashDurationCovered = PartAnimate.calculateDuration(tick, 0, endDuration);

		PartAnimate.changeOverDurationSCurve(glaiveJointSwingPointChanges, wholeGlaiveJointSwingPointChanges,
				wholeSlashDurationCovered);
		PartAnimate.changeOverDurationSCurve(glaiveJointMainChanges, wholeGlaiveJointMainInfo,
				wholeSlashDurationCovered);

		/*
		 * Return to idle animations
		 */
		//float returnDampener = 1 - PartAnimate.calculateDuration(tick, midSlashDurationEnd, endDuration);

		//shoulderLftJointChanges.multiplyVector(returnDampener);

		PartAnimate.applyRotationChangeVectorToInfo(glaiveJointSwingPointInfo, glaiveJointSwingPointChanges);
		PartAnimate.applyRotationChangeVectorToInfo(glaiveJointMainInfo, glaiveJointMainChanges);

		PartAnimate.headAnimateInfoOnlyWithAngleModifiers(glaiveJointBaseInfo, netHeadYaw, headPitch, (1 - idleDampener) * 1.2F, 0);

		return idleDampener;
	}

	public void animateBody(OkamiPokemon entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float idleDampener, float verticalVelocity)
	{
		if(!entity.isSitting())
		{
			float walkCycleInterval = (WALK_FREQUENCY * modelSize * distanceMoved % (2 * PI))/(2 * PI);
			float runCycleInterval = (RUN_FREQUENCY * modelSize * entity.getGravityFactor() * distanceMoved % (2 * PI))/(2 * PI);

			float bodyWalkAngle = (float)Math.toRadians(10);
			float bodyRunAngle = (float)Math.toRadians(18);

			float walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI + PI/2) * bodyWalkAngle * (1 - horzVelocity);
			float runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI + PI/2) * bodyRunAngle * horzVelocity;

			float bodyJumpAngle = (float)Math.toRadians(-65);

			float newVerticalVelocity = 1.5F * verticalVelocity;

			if(newVerticalVelocity > 1F)
				newVerticalVelocity = 1F;
			else if(newVerticalVelocity < -1F)
				newVerticalVelocity = -1F;

			bodyInfo.setNewRotateX(bodyInfo.getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocity * idleDampener * (1F - Math.abs(newVerticalVelocity)) 
					+ bodyJumpAngle * newVerticalVelocity);

			walkCycleInterval = (WALK_FREQUENCY * modelSize * (distanceMoved - PI/2) % (2 * PI))/(2 * PI);
			runCycleInterval = (RUN_FREQUENCY * modelSize * entity.getGravityFactor() * (distanceMoved - PI/2) % (2 * PI))/(2 * PI);

			//bodyWalkAngle *= 2F;
			//bodyRunAngle *= 2F;

			walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI + PI/2) * bodyWalkAngle * (1 - horzVelocity)
					+ bodyWalkAngle * (1 - horzVelocity);
			runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI + PI/2) * bodyRunAngle * horzVelocity
					+ bodyRunAngle * horzVelocity;

			glaiveJointBaseInfo.setNewRotateX(glaiveJointBaseInfo.getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocity * idleDampener * (1F - Math.abs(newVerticalVelocity)));

			float bodyWalkPoint = 1.0F;
			float bodyRunPoint = 1.5F;

			float walkCyclePointChange = MathHelper.cos(walkCycleInterval * 2 * PI + PI/2) * bodyWalkPoint * (1 - horzVelocity)
					+ bodyWalkPoint * (1 - horzVelocity);
			float runCyclePointChange = MathHelper.cos(runCycleInterval * 2 * PI + PI/2) * bodyRunPoint * horzVelocity
					+ bodyRunPoint * horzVelocity;

			glaiveJointMainInfo.setNewPointY(glaiveJointMainInfo.getNewPointY() + ((walkCyclePointChange + runCyclePointChange) * horzVelocity * idleDampener * (1F - Math.abs(newVerticalVelocity))));
		}
		else
		{
			bodyInfo.setNewRotateX(bodySitInfo.getNewRotateX());
			bodyInfo.setNewPointY(bodySitInfo.getNewPointY());
		}
	}

	public void animateGlaive(OkamiPokemon entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float idleDampener)
	{
		glaiveJointSwingPointInfo.setNewRotateY(glaiveJointSwingPointInfo.getNewRotateY() + (float)Math.toRadians(-10));
		glaiveJointMainInfo.setNewRotateY(glaiveJointMainInfo.getNewRotateY() + (float)Math.toRadians(50));

		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockBody();

		float idleAmplitudeY = 0.5F;

		float pointChangeY = -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeY - idleAmplitudeY;

		glaiveJointMainInfo.setNewPointY(glaiveJointMainInfo.getNewPointY() + pointChangeY);

		float idleRotateY = currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI;

		glaiveCoreJointInfo.setNewRotateY(idleRotateY);

		if(entity.isSitting())
		{
			glaiveJointBaseInfo.setNewRotateX(glaiveJointBaseInfo.getNewRotateX() + (float)Math.toRadians(20));
		}
	}

	public void deployAnimations()
	{
		this.animationDeployer.move(body, bodyInfo.getNewPnt());
		this.animationDeployer.rotate(body, bodyInfo.getNewRotates());

		this.animationDeployer.rotate(glaiveJointBase, glaiveJointBaseInfo.getNewRotates());
		this.animationDeployer.rotate(glaiveJointSwingPoint, glaiveJointSwingPointInfo.getNewRotates());
		this.animationDeployer.rotate(glaiveJointMain, glaiveJointMainInfo.getNewRotates());

		this.animationDeployer.move(glaiveJointMain, glaiveJointMainInfo.getNewPnt());

		this.animationDeployer.rotate(glaiveCoreJoint, glaiveCoreJointInfo.getNewRotates());

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