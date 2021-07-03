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
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.OkamiUmbreonEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.TamablePokemon;
import fuzzyacornindustries.kindredlegacy.lib.action.LibraryOkamiPokemonAttackID;
import net.minecraft.client.renderer.entity.model.TintedAgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class OkamiMoonGlaiveModel<T extends TamablePokemon> extends TintedAgeableModel<T>
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

	public ModelRenderer glaiveHandleJoint;
	public ModelRenderer glaiveBladeMain;
	public ModelRenderer glaiveBladeBase1;
	public ModelRenderer glaiveBladeBase2;
	public ModelRenderer glaiveBladeBase3;
	public ModelRenderer glaiveBladeBase4;
	public ModelRenderer glaiveBladeBase5;
	public ModelRenderer glaiveBladeBase6;
	public ModelRenderer glaiveBladeBase7;
	public ModelRenderer glaiveBladeBase8;

	public ModelRenderer glaiveBladeJoint;
	public ModelRenderer glaiveBladeEndLft;
	public ModelRenderer glaiveBladeEndRt;
	public ModelRenderer glaiveBladeEndTip;
	public ModelRenderer glaiveBladeEdgeRtJoint;
	public ModelRenderer glaiveBladeEdgeLftJoint;
	public ModelRenderer glaiveBladeEdgeRt;
	public ModelRenderer glaiveBladeEdgeLft;

	public ModelRenderer glaiveCoreJoint;
	public ModelRenderer glaiveCoreBckRt;
	public ModelRenderer glaiveCoreBckLft;

	public OkamiMoonGlaiveModel() 
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
		this.glaiveJointMain.setRotationPoint(0.0F, 0.0F, 12.0F);
		this.glaiveJointMain.addBox(0.0F, -0.5F, 0.0F, 0, 0, 0, 0.0F);
		this.glaiveJointMainInfo = new PartInfo(glaiveJointMain);

		this.glaiveHandleJoint = new ModelRenderer(this, 0, 0);
		this.glaiveHandleJoint.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveHandleJoint.addBox(-1.0F, -0.5F, -3.0F, 0, 0, 0, 0.0F);
		this.glaiveBladeMain = new ModelRenderer(this, 26, 22);
		this.glaiveBladeMain.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveBladeMain.addBox(-1.0F, -0.51F, -7.3F, 2, 1, 5, 0.0F);
		this.setRotateAngle(glaiveBladeMain, 0.0F, 3.141592653589793F, 0.0F);
		this.glaiveBladeBase1 = new ModelRenderer(this, 9, 13);
		this.glaiveBladeBase1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveBladeBase1.addBox(-1.0F, -0.49F, -3.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(glaiveBladeBase1, 0.0F, 0.9075712110370513F, 0.0F);
		this.glaiveBladeBase2 = new ModelRenderer(this, 16, 13);
		this.glaiveBladeBase2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveBladeBase2.addBox(-1.0F, -0.5F, -3.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(glaiveBladeBase2, 0.0F, 1.5533430342749532F, 0.0F);
		this.glaiveBladeBase3 = new ModelRenderer(this, 23, 13);
		this.glaiveBladeBase3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveBladeBase3.addBox(-1.0F, -0.49F, -3.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(glaiveBladeBase3, 0.0F, 2.199114857512855F, 0.0F);
		this.glaiveBladeBase4 = new ModelRenderer(this, 30, 13);
		this.glaiveBladeBase4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveBladeBase4.addBox(-1.0F, -0.5F, -3.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(glaiveBladeBase4, 0.0F, 2.844886680750757F, 0.0F);
		this.glaiveBladeBase5 = new ModelRenderer(this, 37, 13);
		this.glaiveBladeBase5.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveBladeBase5.addBox(-1.0F, -0.49F, -3.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(glaiveBladeBase5, 0.0F, -2.844886680750757F, 0.0F);
		this.glaiveBladeBase6 = new ModelRenderer(this, 44, 13);
		this.glaiveBladeBase6.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveBladeBase6.addBox(-1.0F, -0.5F, -3.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(glaiveBladeBase6, 0.0F, -2.199114857512855F, 0.0F);
		this.glaiveBladeBase7 = new ModelRenderer(this, 51, 13);
		this.glaiveBladeBase7.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveBladeBase7.addBox(-1.0F, -0.49F, -3.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(glaiveBladeBase7, 0.0F, -1.5533430342749532F, 0.0F);
		this.glaiveBladeBase8 = new ModelRenderer(this, 58, 13);
		this.glaiveBladeBase8.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveBladeBase8.addBox(-1.0F, -0.5F, -3.0F, 2, 1, 1, 0.0F);
		this.setRotateAngle(glaiveBladeBase8, 0.0F, -0.9075712110370513F, 0.0F);

		this.glaiveBladeJoint = new ModelRenderer(this, 0, 0);
		this.glaiveBladeJoint.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveBladeJoint.addBox(-1.0F, -0.5F, -3.0F, 0, 0, 0, 0.0F);
		this.glaiveBladeEndLft = new ModelRenderer(this, 43, 17);
		this.glaiveBladeEndLft.setRotationPoint(-0.5F, 0.0F, -7.1F);
		this.glaiveBladeEndLft.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(glaiveBladeEndLft, 0.0F, -0.10471975511965977F, 0.0F);
		this.glaiveBladeEndRt = new ModelRenderer(this, 55, 17);
		this.glaiveBladeEndRt.setRotationPoint(0.5F, 0.0F, -7.1F);
		this.glaiveBladeEndRt.addBox(-0.5F, -0.49F, -3.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(glaiveBladeEndRt, 0.0F, 0.10471975511965977F, 0.0F);
		this.glaiveBladeEndTip = new ModelRenderer(this, 51, 16);
		this.glaiveBladeEndTip.setRotationPoint(0.0F, 0.0F, -10.1F);
		this.glaiveBladeEndTip.addBox(-0.5F, -0.51F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(glaiveBladeEndTip, 0.0F, 0.7853981633974483F, 0.0F);
		this.glaiveBladeEdgeRtJoint = new ModelRenderer(this, 0, 0);
		this.glaiveBladeEdgeRtJoint.setRotationPoint(1.0F, 0.0F, -3.0F);
		this.glaiveBladeEdgeRtJoint.addBox(-0.5F, -0.51F, -8.3F, 0, 0, 0, 0.0F);
		this.setRotateAngle(glaiveBladeEdgeRtJoint, 0.0F, 0.13962634015954636F, 0.0F);
		this.glaiveBladeEdgeLftJoint = new ModelRenderer(this, 0, 0);
		this.glaiveBladeEdgeLftJoint.setRotationPoint(-1.0F, 0.0F, -3.0F);
		this.glaiveBladeEdgeLftJoint.addBox(-0.5F, -0.51F, -8.3F, 0, 0, 0, 0.0F);
		this.setRotateAngle(glaiveBladeEdgeLftJoint, 0.0F, -0.13962634015954636F, 0.0F);
		this.glaiveBladeEdgeRt = new ModelRenderer(this, 44, 19);
		this.glaiveBladeEdgeRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveBladeEdgeRt.addBox(-0.5F, -0.51F, -7.1F, 1, 1, 8, 0.0F);
		this.setRotateAngle(glaiveBladeEdgeRt, 0.0F, 0.0F, 0.7853981633974483F);
		this.glaiveBladeEdgeLft = new ModelRenderer(this, 33, 21);
		this.glaiveBladeEdgeLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveBladeEdgeLft.addBox(-0.5F, -0.51F, -7.1F, 1, 1, 8, 0.0F);
		this.setRotateAngle(glaiveBladeEdgeLft, 0.0F, 0.0F, 0.7853981633974483F);

		this.glaiveCoreJoint = new ModelRenderer(this, 0, 0);
		this.glaiveCoreJoint.setRotationPoint(0.0F, 0.0F, 1.0F);
		this.glaiveCoreJoint.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
		this.glaiveCoreBckRt = new ModelRenderer(this, 8, 0);
		this.glaiveCoreBckRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveCoreBckRt.addBox(-1.0F, -0.52F, -1.0F, 2, 1, 2, 0.0F);
		this.setRotateAngle(glaiveCoreBckRt, 0.0F, -0.7853981633974483F, 0.0F);
		this.glaiveCoreBckLft = new ModelRenderer(this, 0, 0);
		this.glaiveCoreBckLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.glaiveCoreBckLft.addBox(-1.0F, -0.53F, -1.0F, 2, 1, 2, 0.0F);

		this.body.addChild(this.glaiveJointBase);

		this.glaiveJointBase.addChild(this.glaiveJointSwingPoint);
		this.glaiveJointSwingPoint.addChild(this.glaiveJointMain);

		this.glaiveJointMain.addChild(this.glaiveBladeJoint);
		this.glaiveJointMain.addChild(this.glaiveCoreJoint);
		this.glaiveJointMain.addChild(this.glaiveHandleJoint);

		this.glaiveBladeJoint.addChild(this.glaiveBladeMain);
		this.glaiveBladeJoint.addChild(this.glaiveBladeBase1);
		this.glaiveBladeJoint.addChild(this.glaiveBladeBase2);
		this.glaiveBladeJoint.addChild(this.glaiveBladeBase3);
		this.glaiveBladeJoint.addChild(this.glaiveBladeBase4);
		this.glaiveBladeJoint.addChild(this.glaiveBladeBase5);
		this.glaiveBladeJoint.addChild(this.glaiveBladeBase6);
		this.glaiveBladeJoint.addChild(this.glaiveBladeBase7);
		this.glaiveBladeJoint.addChild(this.glaiveBladeBase8);

		this.glaiveBladeMain.addChild(this.glaiveBladeEdgeLftJoint);
		this.glaiveBladeEdgeLftJoint.addChild(this.glaiveBladeEdgeLft);
		this.glaiveBladeMain.addChild(this.glaiveBladeEdgeRtJoint);
		this.glaiveBladeEdgeRtJoint.addChild(this.glaiveBladeEdgeRt);

		this.glaiveBladeMain.addChild(this.glaiveBladeEndLft);
		this.glaiveBladeMain.addChild(this.glaiveBladeEndRt);
		this.glaiveBladeMain.addChild(this.glaiveBladeEndTip);

		this.glaiveCoreJoint.addChild(this.glaiveCoreBckLft);
		this.glaiveCoreJoint.addChild(this.glaiveCoreBckRt);

		animationDeployer = new AnimationDeployer(this);
	}

	@Override
	public void setRotationAngles(T entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch)
	{
		animate((OkamiUmbreonEntity)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch);
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) 
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	public void animate(OkamiUmbreonEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.animationDeployer.update((IAnimatedEntity)entity);
		resetPartInfos();

		//float angularVelocity = ((OkamiPokemon)entity).getAngularVelocity();
		float verticleVelocity = (float)((OkamiPokemon)entity).getHeightVelocity();

		float idleDampener = 1F;
		
		float sizeModifier = 1F;

		if(animationDeployer.getEntity().getAnimationID() == LibraryOkamiPokemonAttackID.GLAIVE_SLASH || 
				animationDeployer.getEntity().getAnimationID() == LibraryOkamiPokemonAttackID.GLAIVE_SLASH_REVERSE)
		{
			idleDampener = animateSlash(animationDeployer.getEntity(), distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier);
		}

		animateBody((OkamiPokemon)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, idleDampener, verticleVelocity);
		animateGlaive((OkamiPokemon)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, idleDampener);

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
	}
	
	public float animateSlash(IAnimatedEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float sizeModifier)
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
	
	public void animateBody(OkamiPokemon entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float sizeModifier, float idleDampener, float verticalVelocity)
	{
		if(!entity.isSitting())
		{
			float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
			float runCycleInterval = (RUN_FREQUENCY * entity.getGravityFactor() * distanceMoved % (2 * PI))/(2 * PI);

			float bodyWalkAngle = (float)Math.toRadians(10);
			float bodyRunAngle = (float)Math.toRadians(18);

			float walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI + PI/2) * bodyWalkAngle * (1 - horzVelocity);
			float runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI + PI/2) * bodyRunAngle * horzVelocity;

			float bodyJumpAngle = (float)Math.toRadians(-65);
			//float verticalVelocityChangeAngle = (float)Math.toRadians(10);

			float newVerticalVelocity = 1.5F * verticalVelocity;

			if(newVerticalVelocity > 1F)
				newVerticalVelocity = 1F;
			else if(newVerticalVelocity < -1F)
				newVerticalVelocity = -1F;

			bodyInfo.setNewRotateX(bodyInfo.getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocity * idleDampener * (1F - Math.abs(newVerticalVelocity)) 
					+ bodyJumpAngle * newVerticalVelocity);

			walkCycleInterval = (WALK_FREQUENCY * (distanceMoved - PI/2) % (2 * PI))/(2 * PI);
			runCycleInterval = (RUN_FREQUENCY * entity.getGravityFactor() * (distanceMoved - PI/2) % (2 * PI))/(2 * PI);

			bodyWalkAngle *= 2F;
			bodyRunAngle *= 2F;

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
	
	public void animateGlaive(OkamiPokemon entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float sizeModifier, float idleDampener)
	{
		glaiveJointSwingPointInfo.setNewRotateY(glaiveJointSwingPointInfo.getNewRotateY() + (float)Math.toRadians(-10));
		glaiveJointMainInfo.setNewRotateY(glaiveJointMainInfo.getNewRotateY() + (float)Math.toRadians(50));

		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockBody();

		float idleAmplitudeY = 0.5F;

		float pointChangeY = -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeY - idleAmplitudeY;

		glaiveJointMainInfo.setNewPointY(glaiveJointMainInfo.getNewPointY() + pointChangeY);

		if(!entity.isSitting())
		{

		}
		else
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