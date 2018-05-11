package fuzzyacornindustries.kindredlegacy.client.model.mob;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.animation.JointAnimation;
import fuzzyacornindustries.kindredlegacy.animation.PartAnimate;
import fuzzyacornindustries.kindredlegacy.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.animation.Vector3f;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntitySwordieMienshao;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSwordieMienshao extends ModelBase 
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	float movementFrequency = 1.2F;
	
	public ModelRenderer hips;
	PartInfo hipsInfo;

	public ModelRenderer legRt;
	public ModelRenderer legLft;
	PartInfo legRtInfo;
	PartInfo legLftInfo;

	public ModelRenderer bodyLowerJoint;
	public ModelRenderer bodyLower;
	public ModelRenderer bodyUpperJoint;
	public ModelRenderer bodyUpper;
	PartInfo bodyLowerJointInfo;
	PartInfo bodyLowerInfo;
	PartInfo bodyUpperJointInfo;
	PartInfo bodyUpperInfo;

	public ModelRenderer shoulderRtJoint;
	public ModelRenderer shoulderRt;
	public ModelRenderer shoulderLftJoint;
	public ModelRenderer shoulderLft;
	PartInfo shoulderRtJointInfo;
	PartInfo shoulderRtInfo;
	PartInfo shoulderLftJointInfo;
	PartInfo shoulderLftInfo;

	public ModelRenderer neckJoint;
	public ModelRenderer neck;
	PartInfo neckJointInfo;
	PartInfo neckInfo;

	public ModelRenderer armRtArmJoint;
	public ModelRenderer armRtArm;
	public ModelRenderer armRtForearmJoint;
	public ModelRenderer armRtForearm;
	public ModelRenderer armRtWristJoint;
	public ModelRenderer armRtWrist;
	PartInfo armRtArmJointInfo;
	PartInfo armRtArmInfo;
	PartInfo armRtForearmJointInfo;
	PartInfo armRtForearmInfo;
	PartInfo armRtWristJointInfo;
	PartInfo armRtWristInfo;

	public ModelRenderer armLftArmJoint;
	public ModelRenderer armLftArm;
	public ModelRenderer armLftForearmJoint;
	public ModelRenderer armLftForearm;
	public ModelRenderer armLftWristJoint;
	public ModelRenderer armLftWrist;
	PartInfo armLftArmJointInfo;
	PartInfo armLftArmInfo;
	PartInfo armLftForearmJointInfo;
	PartInfo armLftForearmInfo;
	PartInfo armLftWristJointInfo;
	PartInfo armLftWristInfo;

	public ModelRenderer headJoint;
	public ModelRenderer head;
	PartInfo headJointInfo;
	PartInfo headInfo;

	public ModelRenderer headBack;
	public ModelRenderer muzzle;
	public ModelRenderer nose;
	public ModelRenderer whiskerLft;
	public ModelRenderer whiskerRt;
	PartInfo whiskerLftInfo;
	PartInfo whiskerRtInfo;

	public ModelRenderer earLftJoint;
	public ModelRenderer earLft;
	public ModelRenderer earRtJoint;
	public ModelRenderer earRt;
	PartInfo earLftJointInfo;
	PartInfo earLftInfo;
	PartInfo earRtJointInfo;
	PartInfo earRtInfo;

	public ModelRenderer tail[][] = new ModelRenderer[11][2];
	PartInfo tailInfo[][] = new PartInfo[tail.length][tail[0].length];

	public ModelRenderer tail12Joint;
	public ModelRenderer tail12Rt;
	public ModelRenderer tail12Lft;
	PartInfo tail12JointInfo;
	PartInfo tail12RtInfo;
	PartInfo tail12LftInfo;

	public ModelSwordieMienshao() 
	{
		this.textureWidth = 64;
		this.textureHeight = 64;

		int jointNumber = 0;
		int partNumber = 1;

		this.hips = new ModelRenderer(this, 0, 56);
		this.hips.setRotationPoint(0.0F, 17.0F, 0.0F);
		this.hips.addBox(-2.5F, 0.0F, -2.0F, 5, 2, 4, 0.0F);
		this.setRotateAngle(hips, -0.17453292012214658F, -0.0F, 0.0F);
		hipsInfo = new PartInfo(hips);

		this.legRt = new ModelRenderer(this, 21, 16);
		this.legRt.setRotationPoint(-1.4F, 1.5F, -0.5F);
		this.legRt.addBox(-1.0F, -1.0F, -1.0F, 2, 4, 2, 0.0F);
		this.legRt.setTextureOffset(23, 23).addBox(-0.5F, 1.5F, 0.5F, 1, 4, 1, 0.0F); // legRtHind
		legRtInfo = new PartInfo(legRt);
		this.legLft = new ModelRenderer(this, 30, 16);
		this.legLft.setRotationPoint(1.4F, 1.5F, -0.5F);
		this.legLft.addBox(-1.0F, -1.0F, -1.0F, 2, 4, 2, 0.0F);
		this.legLft.setTextureOffset(32, 23).addBox(-0.5F, 1.5F, 0.5F, 1, 4, 1, 0.0F); // legLftHind
		legLftInfo = new PartInfo(legLft);

		this.bodyLowerJoint = new ModelRenderer(this, 3, 47);
		this.bodyLowerJoint.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bodyLowerJoint.addBox(-1.5F, -4.0F, -1.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(bodyLowerJoint, 0.08726646259971647F, 0.0F, 0.0F);
		bodyLowerJointInfo = new PartInfo(bodyLowerJoint);
		this.bodyLower = new ModelRenderer(this, 3, 47);
		this.bodyLower.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bodyLower.addBox(-1.5F, -4.0F, -1.5F, 3, 5, 3, 0.0F);
		bodyLowerInfo = new PartInfo(bodyLower);
		this.bodyUpperJoint = new ModelRenderer(this, 3, 38);
		this.bodyUpperJoint.setRotationPoint(0.0F, -4.0F, -0.2F);
		this.bodyUpperJoint.addBox(-1.5F, -4.5F, -1.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(bodyUpperJoint, 0.6108652381980153F, 0.0F, 0.0F);
		bodyUpperJointInfo = new PartInfo(bodyUpperJoint);
		this.bodyUpper = new ModelRenderer(this, 3, 38);
		this.bodyUpper.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bodyUpper.addBox(-1.5F, -4.0F, -1.5F, 3, 5, 3, 0.0F);
		bodyUpperInfo = new PartInfo(bodyUpper);

		this.shoulderRtJoint = new ModelRenderer(this, 0, 35);
		this.shoulderRtJoint.setRotationPoint(-1.5F, -3.1F, 0.2F);
		this.shoulderRtJoint.addBox(-1.5F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(shoulderRtJoint, -0.7853981633974483F, 0.0F, 0.0F);
		shoulderRtJointInfo = new PartInfo(shoulderRtJoint);
		this.shoulderRt = new ModelRenderer(this, 0, 35);
		this.shoulderRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shoulderRt.addBox(-1.5F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		shoulderRtInfo = new PartInfo(shoulderRt);
		this.shoulderLftJoint = new ModelRenderer(this, 12, 35);
		this.shoulderLftJoint.setRotationPoint(1.5F, -3.1F, 0.2F);
		this.shoulderLftJoint.addBox(-0.5F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(shoulderLftJoint, -0.7853981633974483F, -0.0F, 0.0F);
		shoulderLftJointInfo = new PartInfo(shoulderLftJoint);
		this.shoulderLft = new ModelRenderer(this, 12, 35);
		this.shoulderLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shoulderLft.addBox(-0.5F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		shoulderLftInfo = new PartInfo(shoulderLft);

		this.neckJoint = new ModelRenderer(this, 17, 0);
		this.neckJoint.setRotationPoint(0.0F, -4.0F, 0.0F);
		this.neckJoint.addBox(-1.0F, -4.5F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(neckJoint, (float)Math.toRadians(-35), 0.0F, 0.0F);
		neckJointInfo = new PartInfo(neckJoint);
		this.neck = new ModelRenderer(this, 17, 0);
		this.neck.setRotationPoint(0.0F, 0.0F, 0.2F);
		this.neck.addBox(-1.0F, -4.5F, -1.0F, 2, 5, 2, 0.0F);
		this.setRotateAngle(neck, (float)Math.toRadians(50), 0.0F, 0.0F);
		neckInfo = new PartInfo(neck);

		this.armRtArmJoint = new ModelRenderer(this, 52, 43);
		this.armRtArmJoint.setRotationPoint(-1.3F, 0.0F, 0.0F);
		this.armRtArmJoint.addBox(-0.5F, -0.5F, -4.8F, 0, 0, 0, 0.0F);
		this.setRotateAngle(armRtArmJoint, 0.7853981633974483F, 1.0471975511965976F, 0.0F);
		armRtArmJointInfo = new PartInfo(armRtArmJoint);
		this.armRtArm = new ModelRenderer(this, 32, 45);
		this.armRtArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.armRtArm.addBox(-0.5F, -0.5F, -4.8F, 1, 1, 5, 0.0F);
		armRtArmInfo = new PartInfo(armRtArm);
		this.armRtForearmJoint = new ModelRenderer(this, 52, 46);
		this.armRtForearmJoint.setRotationPoint(0.0F, 0.0F, -4.6F);
		this.armRtForearmJoint.addBox(-4.8F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(armRtForearmJoint, 0.7853981633974483F, -1.2217304763960306F, 0.0F);
		armRtForearmJointInfo = new PartInfo(armRtForearmJoint);
		this.armRtForearm = new ModelRenderer(this, 32, 39);
		this.armRtForearm.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.armRtForearm.addBox(-0.5F, -0.5F, -4.8F, 1, 1, 5, 0.0F);
		armRtForearmInfo = new PartInfo(armRtForearm);
		this.armRtWristJoint = new ModelRenderer(this, 56, 49);
		this.armRtWristJoint.setRotationPoint(0.0F, 0.0F, -4.6F);
		this.armRtWristJoint.addBox(-0.5F, -0.5F, -2.8F, 0, 0, 0, 0.0F);
		this.setRotateAngle(armRtWristJoint, 0.7853981633974483F, 1.2217304763960306F, 0.5235987755982988F);
		armRtWristJointInfo = new PartInfo(armRtWristJoint);
		this.armRtWrist = new ModelRenderer(this, 28, 38);
		this.armRtWrist.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.armRtWrist.addBox(-0.5F, -0.5F, -2.8F, 1, 1, 3, 0.0F);
		this.armRtWrist.setTextureOffset(14, 58).addBox(-20.0F, 0.0F, -4.5F, 20, 1, 5, 0.0F); // armRtBlade
		armRtWristInfo = new PartInfo(armRtWrist);

		this.armLftArmJoint = new ModelRenderer(this, 52, 43);
		this.armLftArmJoint.setRotationPoint(1.3F, 0.0F, 0.0F);
		this.armLftArmJoint.addBox(-0.5F, -0.5F, -4.8F, 0, 0, 0, 0.0F);
		this.setRotateAngle(armLftArmJoint, 0.7853981633974483F, -1.0471975511965976F, 0.0F);
		armLftArmJointInfo = new PartInfo(armLftArmJoint);
		this.armLftArm = new ModelRenderer(this, 45, 45);
		this.armLftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.armLftArm.addBox(-0.5F, -0.5F, -4.8F, 1, 1, 5, 0.0F);
		armLftArmInfo = new PartInfo(armLftArm);
		this.armLftForearmJoint = new ModelRenderer(this, 52, 46);
		this.armLftForearmJoint.setRotationPoint(0.0F, 0.0F, -4.6F);
		this.armLftForearmJoint.addBox(-4.8F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(armLftForearmJoint, 0.7853981633974483F, 1.2217304763960306F, 0.0F);
		armLftForearmJointInfo = new PartInfo(armLftForearmJoint);
		this.armLftForearm = new ModelRenderer(this, 45, 39);
		this.armLftForearm.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.armLftForearm.addBox(-0.5F, -0.5F, -4.8F, 1, 1, 5, 0.0F);
		armLftForearmInfo = new PartInfo(armLftForearm);
		this.armLftWristJoint = new ModelRenderer(this, 56, 49);
		this.armLftWristJoint.setRotationPoint(0.0F, 0.0F, -4.6F);
		this.armLftWristJoint.addBox(-0.5F, -0.5F, -2.8F, 0, 0, 0, 0.0F);
		this.setRotateAngle(armLftWristJoint, 0.7853981633974483F, -1.2217304763960306F, -0.5235987755982988F);
		armLftWristJointInfo = new PartInfo(armLftWristJoint);
		this.armLftWrist = new ModelRenderer(this, 53, 38);
		this.armLftWrist.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.armLftWrist.addBox(-0.5F, -0.5F, -2.8F, 1, 1, 3, 0.0F);
		this.armLftWrist.setTextureOffset(14, 52).addBox(0.0F, 0.0F, -4.5F, 20, 1, 5, 0.0F); // armLftBlade
		armLftWristInfo = new PartInfo(armLftWrist);

		this.headJoint = new ModelRenderer(this, 3, 9);
		this.headJoint.setRotationPoint(0.0F, -4.0F, 0.0F);
		this.headJoint.addBox(-2.0F, -3.1F, -2.9F, 0, 0, 0, 0.0F);
		this.setRotateAngle(headJoint, (float)Math.toRadians(-50), 0.0F, 0.0F);
		headJointInfo = new PartInfo(headJoint);
		this.head = new ModelRenderer(this, 3, 9);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-2.01F, -3.1F, -2.9F, 4, 4, 1, 0.0F);
		headInfo = new PartInfo(head);

		this.headBack = new ModelRenderer(this, 0, 0);
		this.headBack.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.headBack.addBox(-2.0F, -3.5F, -2.0F, 4, 4, 4, 0.0F);
		this.setRotateAngle(headBack, 0.17453292519943295F, 0.0F, 0.0F);
		this.muzzle = new ModelRenderer(this, 4, 15);
		this.muzzle.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.muzzle.addBox(-1.0F, -1.7F, -4.6F, 2, 2, 2, 0.0F);
		this.setRotateAngle(muzzle, 0.17453292519943295F, -0.0F, 0.0F);
		this.nose = new ModelRenderer(this, 6, 20);
		this.nose.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.nose.addBox(-0.5F, -1.4F, -5.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(nose, 0.17453292519943295F, -0.0F, 0.0F);
		this.whiskerLft = new ModelRenderer(this, 6, 23);
		this.whiskerLft.setRotationPoint(1.0F, -0.7F, -3.7F);
		this.whiskerLft.addBox(-0.5F, -0.5F, -0.5F, 4, 1, 1, 0.0F);
		this.setRotateAngle(whiskerLft, 0.08726646259971647F, -0.5235987755982988F, -0.17453292519943295F);
		whiskerLftInfo = new PartInfo(whiskerLft);
		this.whiskerRt = new ModelRenderer(this, 0, 26);
		this.whiskerRt.setRotationPoint(-1.0F, -0.7F, -3.7F);
		this.whiskerRt.addBox(-3.5F, -0.5F, -0.5F, 4, 1, 1, 0.0F);
		this.setRotateAngle(whiskerRt, 0.08726646259971647F, 0.5235987755982988F, 0.17453292519943295F);
		whiskerRtInfo = new PartInfo(whiskerRt);

		this.earLftJoint = new ModelRenderer(this, 21, 12);
		this.earLftJoint.setRotationPoint(1.5F, -3.0F, -0.7F);
		this.earLftJoint.addBox(-1.0F, -1.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earLftJoint, 0.0F, 0.0F, 0.7853981633974483F);
		earLftJointInfo = new PartInfo(earLftJoint);
		this.earLft = new ModelRenderer(this, 21, 12);
		this.earLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earLft.addBox(-1.0F, -1.5F, -0.5F, 2, 2, 1, 0.0F);
		this.earLft.setTextureOffset(22, 9).addBox(-0.5F, -2.5F, -0.5F, 1, 1, 1, 0.0F); // earLftTip
		earLftInfo = new PartInfo(earLft);
		this.earRtJoint = new ModelRenderer(this, 14, 12);
		this.earRtJoint.setRotationPoint(-1.5F, -3.0F, -0.7F);
		this.earRtJoint.addBox(-1.0F, -1.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earRtJoint, 0.0F, -0.0F, -0.7853981633974483F);
		earRtJointInfo = new PartInfo(earRtJoint);
		this.earRt = new ModelRenderer(this, 14, 12);
		this.earRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earRt.addBox(-1.0F, -1.5F, -0.5F, 2, 2, 1, 0.0F);
		this.earRt.setTextureOffset(15, 9).addBox(-0.5F, -2.5F, -0.5F, 1, 1, 1, 0.0F); // earRtTip
		earRtInfo = new PartInfo(earRt);

		int tailNumber = 0;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 45, 0); // tail1Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.8F, 1.5F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][jointNumber], -0.17453292519943295F, 0.0F, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 45, 0); // tail1
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 45, 4); // tail2Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][jointNumber], 0.3490658503988659F, 0.0F, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 45, 4); // tail2
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 45, 8); // tail3Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][jointNumber], 0.3490658503988659F, 0.0F, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 45, 8); // tail3
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 45, 12); // tail4Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][jointNumber], 0.3490658503988659F, 0.0F, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 45, 12); // tail4
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 45, 16); // tail5Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][jointNumber], 0.3490658503988659F, 0.0F, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 45, 16); // tail5
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 45, 20); // tail6Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][jointNumber], 0.3490658503988659F, 0.0F, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 45, 20); // tail6
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 45, 24); // tail7Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][jointNumber], 0.3490658503988659F, 0.0F, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 45, 24); // tail7
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 45, 28); // tail8Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][jointNumber], 0.3490658503988659F, -0.0F, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 45, 28); // tail8
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 45, 32); // tail9Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 45, 32); // tail9
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 48, 36); // tail10Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][jointNumber], -0.3490658503988659F, -0.0F, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 48, 36); // tail10
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 52, 32); // tail11Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 52, 32); // tail11
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);

		this.tail12Joint = new ModelRenderer(this, 52, 26);
		this.tail12Joint.setRotationPoint(0.0F, 0.0F, 1.6F);
		this.tail12Joint.addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail12Joint, -0.3490658503988659F, 0.0F, 0.0F);
		tail12JointInfo = new PartInfo(tail12Joint);
		this.tail12Rt = new ModelRenderer(this, 52, 26);
		this.tail12Rt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail12Rt.addBox(-0.5F, -0.5F, -0.2F, 1, 1, 4, 0.0F);
		this.setRotateAngle(tail12Rt, 0.0F, -0.20943951023931953F, 0.0F);
		tail12RtInfo = new PartInfo(tail12Rt);
		this.tail12Lft = new ModelRenderer(this, 54, 20);
		this.tail12Lft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail12Lft.addBox(-0.5F, -0.49F, -0.2F, 1, 1, 4, 0.0F);
		this.setRotateAngle(tail12Lft, 0.0F, 0.20943951023931953F, 0.0F);
		tail12LftInfo = new PartInfo(tail12Lft);

		this.hips.addChild(this.bodyLowerJoint);
		this.hips.addChild(this.legLft);
		this.hips.addChild(this.legRt);
		this.hips.addChild(this.tail[0][0]); // tail1Joint

		this.bodyLower.addChild(this.bodyUpperJoint);
		this.bodyLowerJoint.addChild(this.bodyLower);
		this.bodyUpper.addChild(this.neckJoint);
		this.bodyUpper.addChild(this.shoulderLftJoint);
		this.bodyUpper.addChild(this.shoulderRtJoint);
		this.bodyUpperJoint.addChild(this.bodyUpper);

		this.shoulderLft.addChild(this.armLftArmJoint);
		this.shoulderLftJoint.addChild(this.shoulderLft);
		this.shoulderRt.addChild(this.armRtArmJoint);
		this.shoulderRtJoint.addChild(this.shoulderRt);

		this.neckJoint.addChild(this.neck);
		this.neck.addChild(this.headJoint);

		this.headJoint.addChild(this.head);
		this.head.addChild(this.earLftJoint);
		this.head.addChild(this.earRtJoint);
		this.head.addChild(this.headBack);
		this.head.addChild(this.muzzle);
		this.head.addChild(this.nose);

		this.armLftArmJoint.addChild(this.armLftArm);
		this.armLftArm.addChild(this.armLftForearmJoint);
		this.armLftForearmJoint.addChild(this.armLftForearm);
		this.armLftForearm.addChild(this.armLftWristJoint);
		this.armLftWristJoint.addChild(this.armLftWrist);

		this.armRtArmJoint.addChild(this.armRtArm);
		this.armRtArm.addChild(this.armRtForearmJoint);
		this.armRtForearmJoint.addChild(this.armRtForearm);
		this.armRtForearm.addChild(this.armRtWristJoint);
		this.armRtWristJoint.addChild(this.armRtWrist);

		this.earLftJoint.addChild(this.earLft);
		this.earRtJoint.addChild(this.earRt);
		this.muzzle.addChild(this.whiskerLft);
		this.muzzle.addChild(this.whiskerRt);

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
		this.tail[7][1].addChild(this.tail[8][0]); // tail8 * tail9Joint
		this.tail[8][0].addChild(this.tail[8][1]); // tail9Joint * tail9
		this.tail[8][1].addChild(this.tail[9][0]); // tail9 * tail10Joint
		this.tail[9][0].addChild(this.tail[9][1]); // tail10Joint * tail10
		this.tail[9][1].addChild(this.tail[10][0]); // tail10 * tail11Joint
		this.tail[10][0].addChild(this.tail[10][1]); // tail11Joint * tail11

		this.tail[10][1].addChild(this.tail12Joint); // tail11
		this.tail12Joint.addChild(this.tail12Lft);
		this.tail12Joint.addChild(this.tail12Rt);

		animationDeployer = new AnimationDeployer(this);
	}

	@Override
	public void render(Entity entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize) 
	{ 
		animate(entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);

		this.hips.render(modelSize);
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

		float idleDampener = 1F;

		if(animationDeployer.getEntity().getAnimationID() == EntitySwordieMienshao.actionIDSlash)
		{
			idleDampener = animateSlash(animationDeployer.getEntity(), distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
		}

		animateHead((EntitySwordieMienshao)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
		animateBody((EntitySwordieMienshao)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, idleDampener);
		animateLegs((EntitySwordieMienshao)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
		animateTail((EntitySwordieMienshao)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);

		deployAnimations();
	}

	public void resetPartInfos()
	{
		neckJointInfo.resetNewAngles();
		neckInfo.resetNewAngles();
		headJointInfo.resetNewAngles();
		headInfo.resetNewAngles();

		earRtInfo.resetNewAngles();
		earLftInfo.resetNewAngles();

		whiskerLftInfo.resetNewAngles();
		whiskerRtInfo.resetNewAngles();

		shoulderLftJointInfo.resetNewAngles();
		shoulderRtJointInfo.resetNewAngles();

		armRtArmJointInfo.resetNewAngles();
		armRtArmInfo.resetNewAngles();
		armRtForearmJointInfo.resetNewAngles();
		armRtForearmInfo.resetNewAngles();
		armRtWristJointInfo.resetNewAngles();
		armRtWristInfo.resetNewAngles();

		armLftArmJointInfo.resetNewAngles();
		armLftArmInfo.resetNewAngles();
		armLftForearmJointInfo.resetNewAngles();
		armLftForearmInfo.resetNewAngles();
		armLftWristJointInfo.resetNewAngles();
		armLftWristInfo.resetNewAngles();

		bodyLowerJointInfo.resetNewAngles();
		bodyLowerInfo.resetNewAngles();
		bodyUpperJointInfo.resetNewAngles();
		bodyUpperInfo.resetNewAngles();

		for(int i = 0; i < tail.length; i++)
		{
			tailInfo[i][0].resetNewAngles();
			tailInfo[i][1].resetNewAngles();
		}
	}

	public float animateSlash(IAnimatedEntity entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize)
	{
		float idleDampener = 1F;

		float tick = animationDeployer.getEntity().getAnimationTick() + KindredLegacyMain.proxy.getPartialTick();

		float firstPhaseEnd = 6;
		float secondPhaseEnd = 12;
		float endDuration = 20;

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

		/*
		 * Arm animations
		 */
		Vector3f shoulderLftJointChanges = new Vector3f(0,0,0);
		Vector3f armLftArmJointChanges = new Vector3f(0,0,0);
		Vector3f armLftForearmJointChanges = new Vector3f(0,0,0);
		Vector3f armLftWristJointChanges = new Vector3f(0,0,0);
		Vector3f bodyLowerJointChanges = new Vector3f(0,0,0);
		Vector3f bodyUpperJointChanges = new Vector3f(0,0,0);
		Vector3f neckJointChanges = new Vector3f(0,0,0);
		Vector3f neckChanges = new Vector3f(0,0,0);
		Vector3f headJointChanges = new Vector3f(0,0,0);
		Vector3f headChanges = new Vector3f(0,0,0);

		/* ******** Drawback Timers *********/
		float drawbackDurationStart = 0F;
		float drawbackDurationEnd = firstPhaseEnd;

		/* ******** Drawback Vectors *********/
		Vector3f armDrawbackShoulderLftJointChanges = new Vector3f(
				(float)Math.toRadians(10),
				(float)Math.toRadians(0),
				(float)Math.toRadians(-15));
		Vector3f armDrawbackArmLftArmJointChanges = new Vector3f(
				(float)Math.toRadians(-45),
				(float)Math.toRadians(-75),
				(float)Math.toRadians(0));
		Vector3f armDrawbackArmLftForearmJointChanges = new Vector3f(
				(float)Math.toRadians(-45),
				(float)Math.toRadians(0),
				(float)Math.toRadians(0));
		Vector3f armDrawbackArmLftWristJointChanges = new Vector3f(
				(float)Math.toRadians(-15),
				(float)Math.toRadians(0),
				(float)Math.toRadians(0));
		Vector3f armDrawbackBodyLowerJointChanges = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(0),
				(float)Math.toRadians(20));
		Vector3f armDrawbackBodyUpperJointChanges = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(0),
				(float)Math.toRadians(-10));
		Vector3f armDrawbackNeckJointChanges = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(0),
				(float)Math.toRadians(-10));
		Vector3f armDrawbackHeadChanges = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(-5),
				(float)Math.toRadians(0));

		/* ******** Thrust Timers *********/
		float thrustDurationStart = drawbackDurationEnd;
		float thrustDurationEnd = secondPhaseEnd;
		float thrustDuration = thrustDurationEnd - thrustDurationStart;

		/* ******** Thrust Vectors *********/
		Vector3f armThrustArmLftArmJointChanges = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(135),
				(float)Math.toRadians(0));
		Vector3f armThrustArmLftForearmJointChanges = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(-40),
				(float)Math.toRadians(0));
		Vector3f armThrustArmLftWristJointChanges = new Vector3f(
				(float)Math.toRadians(-30),
				(float)Math.toRadians(175),
				(float)Math.toRadians(30));
		Vector3f armThrustBodyLowerJointChanges = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(0),
				(float)Math.toRadians(-30));
		Vector3f armThrustBodyUpperJointChanges = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(25),
				(float)Math.toRadians(15));
		Vector3f armThrustNeckJointChanges = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(0),
				(float)Math.toRadians(10));
		Vector3f armThrustNeckChanges = new Vector3f(
				(float)Math.toRadians(30),
				(float)Math.toRadians(0),
				(float)Math.toRadians(10));
		Vector3f armThrustHeadJointChanges = new Vector3f(
				(float)Math.toRadians(-5),
				(float)Math.toRadians(0),
				(float)Math.toRadians(0));
		Vector3f armThrustHeadChanges = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(-25),
				(float)Math.toRadians(0));

		/*
		 * Drawback animations
		 */
		float drawbackDurationCovered = PartAnimate.calculateDuration(tick, drawbackDurationStart, drawbackDurationEnd);

		PartAnimate.changeOverDurationSCurve(shoulderLftJointChanges, armDrawbackShoulderLftJointChanges,
				drawbackDurationCovered);
		PartAnimate.changeOverDurationSCurve(armLftArmJointChanges, armDrawbackArmLftArmJointChanges,
				drawbackDurationCovered);
		PartAnimate.changeOverDurationSCurve(armLftForearmJointChanges, armDrawbackArmLftForearmJointChanges,
				drawbackDurationCovered);
		PartAnimate.changeOverDurationSCurve(armLftWristJointChanges, armDrawbackArmLftWristJointChanges,
				drawbackDurationCovered);
		PartAnimate.changeOverDurationSCurve(bodyLowerJointChanges, armDrawbackBodyLowerJointChanges,
				drawbackDurationCovered);
		PartAnimate.changeOverDurationSCurve(bodyUpperJointChanges, armDrawbackBodyUpperJointChanges,
				drawbackDurationCovered);
		PartAnimate.changeOverDurationSCurve(neckJointChanges, armDrawbackNeckJointChanges,
				drawbackDurationCovered);
		PartAnimate.changeOverDurationSCurve(headChanges, armDrawbackHeadChanges,
				drawbackDurationCovered);

		/*
		 * Thrust animations
		 */
		float thrustDurationCovered = PartAnimate.calculateDuration(tick, thrustDurationStart, thrustDurationEnd);

		PartAnimate.changeOverDurationSCurve(armLftArmJointChanges, armThrustArmLftArmJointChanges,
				thrustDurationCovered);
		PartAnimate.changeOverDurationSCurve(armLftForearmJointChanges, armThrustArmLftForearmJointChanges,
				thrustDurationCovered);
		PartAnimate.changeOverDurationSCurve(armLftWristJointChanges, armThrustArmLftWristJointChanges,
				thrustDurationCovered);
		PartAnimate.changeOverDurationSCurve(bodyLowerJointChanges, armThrustBodyLowerJointChanges,
				thrustDurationCovered);
		PartAnimate.changeOverDurationSCurve(bodyUpperJointChanges, armThrustBodyUpperJointChanges,
				thrustDurationCovered);
		PartAnimate.changeOverDurationSCurve(neckJointChanges, armThrustNeckJointChanges,
				thrustDurationCovered);
		PartAnimate.changeOverDurationSCurve(neckChanges, armThrustNeckChanges,
				thrustDurationCovered);
		PartAnimate.changeOverDurationSCurve(headJointChanges, armThrustHeadJointChanges,
				thrustDurationCovered);
		PartAnimate.changeOverDurationSCurve(headChanges, armThrustHeadChanges,
				thrustDurationCovered);

		/*
		 * Return to idle animations
		 */
		float returnDampener = 1 - PartAnimate.calculateDuration(tick, thrustDurationEnd, endDuration);

		shoulderLftJointChanges.multiplyVector(returnDampener);
		armLftArmJointChanges.multiplyVector(returnDampener);
		armLftForearmJointChanges.multiplyVector(returnDampener);
		armLftWristJointChanges.multiplyVector(returnDampener);
		bodyLowerJointChanges.multiplyVector(returnDampener);
		bodyUpperJointChanges.multiplyVector(returnDampener);
		neckJointChanges.multiplyVector(returnDampener);
		neckChanges.multiplyVector(returnDampener);
		headJointChanges.multiplyVector(returnDampener);
		headChanges.multiplyVector(returnDampener);

		PartAnimate.applyRotationChangeVectorToInfo(shoulderLftJointInfo, shoulderLftJointChanges);
		PartAnimate.applyRotationChangeVectorToInfo(armLftArmJointInfo, armLftArmJointChanges);
		PartAnimate.applyRotationChangeVectorToInfo(armLftForearmJointInfo, armLftForearmJointChanges);
		PartAnimate.applyRotationChangeVectorToInfo(armLftWristJointInfo, armLftWristJointChanges);
		PartAnimate.applyRotationChangeVectorToInfo(bodyLowerJointInfo, bodyLowerJointChanges);
		PartAnimate.applyRotationChangeVectorToInfo(bodyUpperJointInfo, bodyUpperJointChanges);
		PartAnimate.applyRotationChangeVectorToInfo(neckJointInfo, neckJointChanges);
		PartAnimate.applyRotationChangeVectorToInfo(neckInfo, neckChanges);
		PartAnimate.applyRotationChangeVectorToInfo(headJointInfo, headJointChanges);
		PartAnimate.applyRotationChangeVectorToInfo(headInfo, headChanges);

		PartAnimate.headAnimateInfoOnlyWithAngleModifiers(shoulderLftJointInfo, yawHeadOffsetDifference, pitchRotation, (1-idleDampener)*1.5F, 0);

		return idleDampener;
	}

	public void animateHead(EntitySwordieMienshao entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize)
	{
		PartAnimate.headAnimateInfoOnlyWithAngleModifiers(neckInfo, yawHeadOffsetDifference, pitchRotation, 0.4F, 0.4F);

		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockNeckBobble();

		float idleAmplitudeX = 0.1F;

		// + (float)Math.toRadians(45)
		float movementFrequency = 2.0F;
		float movementAmplitude = 0.2F;
		float angleChangeX = PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, movementFrequency, movementAmplitude);

		angleChangeX += -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeX + idleAmplitudeX;

		neckInfo.setNewRotateX(neckInfo.getNewRotateX() + angleChangeX);
		neckInfo.setNewRotateY(neckInfo.getNewRotateY());

		JointAnimation.reverseJointRotatesChangeX(neckInfo, headJointInfo);

		PartAnimate.headAnimateInfoOnlyWithAngleModifiers(headInfo, yawHeadOffsetDifference, pitchRotation, 0.8F, 0.8F);

		/* ******* Ears ******** */
		float moveFrequencyZ = movementFrequency;
		float moveAmplitudeZ = 0.10F;

		float firstAngleChangeZ = PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, moveFrequencyZ, moveAmplitudeZ);

		float idleAmplitudeZ = 0.20F;

		// + (float)Math.toRadians(45)
		float angleChangeZ = firstAngleChangeZ;

		angleChangeZ += MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI + PI) * idleAmplitudeZ;

		earRtInfo.setNewRotateZ(earRtInfo.getNewRotateZ() - angleChangeZ);
		earLftInfo.setNewRotateZ(earLftInfo.getNewRotateZ() + angleChangeZ);

		/* ******* Hairs ******** */
		whiskerLftInfo.setNewRotateZ(whiskerLftInfo.getNewRotateZ() + angleChangeZ * 1.2F);
		whiskerRtInfo.setNewRotateZ(whiskerRtInfo.getNewRotateZ() - angleChangeZ * 1.2F);
	}

	public void animateBody(EntitySwordieMienshao entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener)
	{
		float moveAmplitude = 0.3F;

		bodyLowerJointInfo.setNewRotateX(bodyLowerJointInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, movementFrequency, moveAmplitude * 0.15F));
		bodyUpperJointInfo.setNewRotateX(bodyUpperJointInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, movementFrequency, moveAmplitude * 0.15F));

		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockArm();

		float idleAmplitudeZ = 0.15F;

		float angleChangeZ = MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI - (PI/2)) * idleAmplitudeZ;

		armLftArmJointInfo.setNewRotateZ(armLftArmJointInfo.getNewRotateZ() + PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, movementFrequency, moveAmplitude) + angleChangeZ);
		armRtArmJointInfo.setNewRotateZ(armRtArmJointInfo.getNewRotateZ() + PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, movementFrequency, moveAmplitude) - angleChangeZ);

		armLftForearmJointInfo.setNewRotateZ(armLftForearmJointInfo.getNewRotateZ() + PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, movementFrequency, moveAmplitude * 2F) + angleChangeZ * 1.5F);
		armRtForearmJointInfo.setNewRotateZ(armRtForearmJointInfo.getNewRotateZ() + PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, movementFrequency, moveAmplitude * 2F) - angleChangeZ * 1.5F);
	}

	public void animateLegs(EntitySwordieMienshao entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize)
	{
		float moveAmplitude = 0.6F;

		this.legRt.rotateAngleX = legRtInfo.getNewRotateX() + PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, movementFrequency, moveAmplitude);
		this.legLft.rotateAngleX = legLftInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, movementFrequency, moveAmplitude);
	}

	public void animateTail(EntitySwordieMienshao entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize)
	{
		for(int i = 0; i < tail.length; i++)
		{
			IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockTail(i);

			float idleAmplitudeX = 0.08F;
			float idleAmplitudeY = 0.12F;

			float moveFrequency = 1F;
			float moveAmplitude = 0.1F;

			// + (float)Math.toRadians(45)
			float angleChangeX = 0;
			float angleChangeY = 0;
			float angleChangeZ = 0;

			angleChangeX += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2) * idleAmplitudeX;
			angleChangeY += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2) * idleAmplitudeY;
			angleChangeZ += PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, moveFrequency, moveAmplitude);

			tailInfo[i][1].setNewRotateX(tailInfo[i][1].getNewRotateX() + angleChangeX);
			tailInfo[i][1].setNewRotateY(tailInfo[i][1].getNewRotateY() + angleChangeY);
			tailInfo[i][1].setNewRotateZ(tailInfo[i][1].getNewRotateZ() + angleChangeZ);
		}
	}

	public void deployAnimations()
	{
		this.animationDeployer.rotate(neckJoint, neckJointInfo.getNewRotates());
		this.animationDeployer.rotate(neck, neckInfo.getNewRotates());
		this.animationDeployer.rotate(headJoint, headJointInfo.getNewRotates());
		this.animationDeployer.rotate(head, headInfo.getNewRotates());

		this.animationDeployer.rotate(earLft, earLftInfo.getNewRotates());
		this.animationDeployer.rotate(earLftJoint, earLftJointInfo.getNewRotates());
		this.animationDeployer.rotate(earRt, earRtInfo.getNewRotates());
		this.animationDeployer.rotate(earRtJoint, earRtJointInfo.getNewRotates());

		this.animationDeployer.rotate(whiskerLft, whiskerLftInfo.getNewRotates());
		this.animationDeployer.rotate(whiskerRt, whiskerRtInfo.getNewRotates());

		this.animationDeployer.rotate(shoulderLftJoint, shoulderLftJointInfo.getNewRotates());
		this.animationDeployer.rotate(shoulderRtJoint, shoulderRtJointInfo.getNewRotates());

		this.animationDeployer.rotate(armLftArmJoint, armLftArmJointInfo.getNewRotates());
		this.animationDeployer.rotate(armLftArm, armLftArmInfo.getNewRotates());
		this.animationDeployer.rotate(armLftForearmJoint, armLftForearmJointInfo.getNewRotates());
		this.animationDeployer.rotate(armLftForearm, armLftForearmInfo.getNewRotates());
		this.animationDeployer.rotate(armLftWristJoint, armLftWristJointInfo.getNewRotates());
		this.animationDeployer.rotate(armLftWrist, armLftWristInfo.getNewRotates());

		this.animationDeployer.rotate(armRtArmJoint, armRtArmJointInfo.getNewRotates());
		this.animationDeployer.rotate(armRtArm, armRtArmInfo.getNewRotates());
		this.animationDeployer.rotate(armRtForearmJoint, armRtForearmJointInfo.getNewRotates());
		this.animationDeployer.rotate(armRtForearm, armRtForearmInfo.getNewRotates());
		this.animationDeployer.rotate(armRtWristJoint, armRtWristJointInfo.getNewRotates());
		this.animationDeployer.rotate(armRtWrist, armRtWristInfo.getNewRotates());

		this.animationDeployer.rotate(bodyLowerJoint, bodyLowerJointInfo.getNewRotates());
		this.animationDeployer.rotate(bodyLower, bodyLowerInfo.getNewRotates());
		this.animationDeployer.rotate(bodyUpperJoint, bodyUpperJointInfo.getNewRotates());
		this.animationDeployer.rotate(bodyUpper, bodyUpperInfo.getNewRotates());

		for(int i = 0; i < tail.length; i++)
		{
			this.animationDeployer.rotate(tail[i][0], tailInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(tail[i][1], tailInfo[i][1].getNewRotates());
		}

		this.animationDeployer.applyChanges();
	}
}