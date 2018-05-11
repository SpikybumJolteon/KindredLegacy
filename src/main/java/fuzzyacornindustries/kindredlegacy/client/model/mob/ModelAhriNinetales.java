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
import fuzzyacornindustries.kindredlegacy.entity.mob.IGravityTracker;
import fuzzyacornindustries.kindredlegacy.entity.mob.IMobMotionTracker;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityVastayaNinetales;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryAhriNinetalesAttackID;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryUniversalAttackID;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelAhriNinetales extends ModelBase 
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public final float RUN_FREQUENCY = 0.6F;
	public final float WALK_FREQUENCY = 1.2F;
	public final float SNEAK_VELOCITY = 0.45F;

	public ModelRenderer legLft[] = new ModelRenderer[3]; // 0-legLft, 1-legLftCalf, 2-legLftHind
	public ModelRenderer legRt[] = new ModelRenderer[3]; // 0-legRt, 1-legRtCalf, 2-legRtHind
	public PartInfo legLftInfo[] = new PartInfo[legLft.length];
	public PartInfo legRtInfo[] = new PartInfo[legRt.length];

	public ModelRenderer bodyJoint;
	public ModelRenderer body;
	public PartInfo bodyJointInfo;
	public PartInfo bodyInfo;

	public ModelRenderer neck;
	public PartInfo neckInfo;

	public ModelRenderer headJoint;
	public ModelRenderer head;
	public PartInfo headJointInfo;
	public PartInfo headInfo;

	public ModelRenderer muzzle;
	public ModelRenderer nose;
	public ModelRenderer jaw;
	public PartInfo jawInfo;

	public ModelRenderer cheekTuff[][] = new ModelRenderer[2][2];
	public PartInfo cheekTuffInfo[][] = new PartInfo[cheekTuff.length][cheekTuff[0].length];

	public ModelRenderer earLftJoint;
	public ModelRenderer earLft;
	public ModelRenderer earRtJoint;
	public ModelRenderer earRt;
	public PartInfo earLftJointInfo;
	public PartInfo earLftInfo;
	public PartInfo earRtJointInfo;
	public PartInfo earRtInfo;

	public ModelRenderer headHair[] = new ModelRenderer[8];
	public PartInfo headHairInfo[] = new PartInfo[headHair.length];

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
	public PartInfo armRtJointSittingInfo;
	public PartInfo armRtSittingInfo;
	public PartInfo forearmRtJointSittingInfo;
	public PartInfo forearmRtSittingInfo;

	public ModelRenderer shoulderLft;
	public ModelRenderer armLftJoint;
	public ModelRenderer armLft;
	public ModelRenderer forearmLftJoint;
	public ModelRenderer forearmLft;
	public PartInfo shoulderLftInfo;
	public PartInfo armLftJointInfo;
	public PartInfo armLftInfo;
	public PartInfo forearmLftJointInfo;
	public PartInfo forearmLftInfo;
	public PartInfo armLftJointSittingInfo;
	public PartInfo armLftSittingInfo;
	public PartInfo forearmLftJointSittingInfo;
	public PartInfo forearmLftSittingInfo;

	public ModelRenderer tailJoint;
	public PartInfo tailJointInfo;

	public ModelRenderer tail[][][] = new ModelRenderer[9][8][2];
	public PartInfo tailInfo[][][] = new PartInfo[tail.length][tail[0].length][tail[0][0].length];

	public ModelAhriNinetales() 
	{
		this.textureWidth = 128;
		this.textureHeight = 64;

		int jointNumber = 0;
		int partNumber = 1;

		int legPartNumber = 0;
		this.legLft[legPartNumber] = new ModelRenderer(this, 38, 39); // legLft
		this.legLft[legPartNumber].setRotationPoint(1.4F, 0.0F, 0.0F);
		this.legLft[legPartNumber].addBox(-1.5F, -1.0F, -1.5F, 3, 6, 3, 0.0F);
		this.setRotateAngle(legLft[legPartNumber], -0.3490658503988659F, -0.22689280275926282F, -0.08726646259971647F);
		this.legLftInfo[legPartNumber] = new PartInfo(this.legLft[legPartNumber]);
		legPartNumber++;
		this.legLft[legPartNumber] = new ModelRenderer(this, 38, 48); // legLftCalf
		this.legLft[legPartNumber].setRotationPoint(0.0F, 4.0F, 0.0F);
		this.legLft[legPartNumber].addBox(-1.51F, -0.5F, -1.5F, 3, 4, 3, 0.0F);
		this.setRotateAngle(legLft[legPartNumber], 1.0471975511965976F, 0.0F, 0.0F);
		this.legLftInfo[legPartNumber] = new PartInfo(this.legLft[legPartNumber]);
		legPartNumber++;
		this.legLft[legPartNumber] = new ModelRenderer(this, 38, 55); // legLftHind
		this.legLft[legPartNumber].setRotationPoint(0.0F, 2.7F, 0.0F);
		this.legLft[legPartNumber].addBox(-1.52F, -0.7F, -1.5F, 3, 6, 3, 0.0F);
		this.setRotateAngle(legLft[legPartNumber], -1.0471975511965976F, 0.0F, 0.0F);
		this.legLftInfo[legPartNumber] = new PartInfo(this.legLft[legPartNumber]);
		legPartNumber = 0;
		this.legRt[legPartNumber] = new ModelRenderer(this, 25, 39); // legRt
		this.legRt[legPartNumber].setRotationPoint(-1.4F, 0.0F, 0.0F);
		this.legRt[legPartNumber].addBox(-1.5F, -1.0F, -1.5F, 3, 6, 3, 0.0F);
		this.setRotateAngle(legRt[legPartNumber], -0.3490658503988659F, 0.22689280275926282F, 0.08726646259971647F);
		this.legRtInfo[legPartNumber] = new PartInfo(this.legRt[legPartNumber]);
		legPartNumber++;
		this.legRt[legPartNumber] = new ModelRenderer(this, 25, 48); // legRtCalf
		this.legRt[legPartNumber].setRotationPoint(0.0F, 4.0F, 0.0F);
		this.legRt[legPartNumber].addBox(-1.51F, -0.5F, -1.5F, 3, 4, 3, 0.0F);
		this.setRotateAngle(legRt[legPartNumber], 1.0471975511965976F, 0.0F, 0.0F);
		this.legRtInfo[legPartNumber] = new PartInfo(this.legRt[legPartNumber]);
		legPartNumber++;
		this.legRt[legPartNumber] = new ModelRenderer(this, 25, 55); // legRtHind
		this.legRt[legPartNumber].setRotationPoint(0.0F, 2.7F, 0.0F);
		this.legRt[legPartNumber].addBox(-1.52F, -0.7F, -1.5F, 3, 6, 3, 0.0F);
		this.setRotateAngle(legRt[legPartNumber], -1.0471975511965976F, 0.0F, 0.0F);
		this.legRtInfo[legPartNumber] = new PartInfo(this.legRt[legPartNumber]);

		this.bodyJoint = new ModelRenderer(this, 0, 0);
		this.bodyJoint.setRotationPoint(0.0F, 13.5F, 1.0F);
		this.bodyJoint.addBox(-3.0F, -12.0F, -3.0F, 0, 0, 0, 0.0F);
		this.bodyJointInfo = new PartInfo(this.bodyJoint);
		this.body = new ModelRenderer(this, 0, 45);
		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.addBox(-3.0F, -12.0F, -3.0F, 6, 13, 6, 0.0F);
		this.setRotateAngle(body, 0.17453292519943295F, 0.0F, 0.0F);
		this.bodyInfo = new PartInfo(this.body);

		this.neck = new ModelRenderer(this, 0, 12);
		this.neck.setRotationPoint(0.0F, -11.5F, 0.0F);
		this.neck.addBox(-1.0F, -3.5F, -1.0F, 2, 4, 2, 0.0F);
		this.neckInfo = new PartInfo(this.neck);

		this.headJoint = new ModelRenderer(this, 0, 0);
		this.headJoint.setRotationPoint(0.0F, -3.0F, 0.0F);
		this.headJoint.addBox(-3.0F, -5.0F, -3.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(headJoint, -0.17453292519943295F, 0.0F, 0.0F);
		this.headJointInfo = new PartInfo(this.headJoint);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-3.0F, -5.5F, -3.0F, 6, 6, 6, 0.0F);
		this.headInfo = new PartInfo(this.head);

		this.muzzle = new ModelRenderer(this, 19, 0);
		this.muzzle.setRotationPoint(0.0F, -1.5F, -2.5F);
		this.muzzle.addBox(-1.0F, -1.0F, -4.0F, 2, 2, 4, 0.0F);
		this.setRotateAngle(muzzle, 0.17453292519943295F, 0.0F, 0.0F);
		this.nose = new ModelRenderer(this, 19, 0);
		this.nose.setRotationPoint(0.0F, -1.5F, -2.5F);
		this.nose.addBox(-0.5F, -0.9F, -4.3F, 1, 1, 1, 0.0F);
		this.setRotateAngle(nose, 0.17453292519943295F, 0.0F, 0.0F);
		this.jaw = new ModelRenderer(this, 25, 7);
		this.jaw.setRotationPoint(0.0F, -0.3F, -3.0F);
		this.jaw.addBox(-1.01F, -0.2F, -3.0F, 2, 1, 4, 0.0F);
		this.setRotateAngle(jaw, 0.05235987755982988F, 0.0F, 0.0F);
		this.jawInfo = new PartInfo(this.jaw);

		int cheekTuffNumber = 0;
		int cheekTuffPartNumber = 0;
		this.cheekTuff[cheekTuffNumber][cheekTuffPartNumber] = new ModelRenderer(this, 57, 0); // cheekTuffLft1
		this.cheekTuff[cheekTuffNumber][cheekTuffPartNumber].setRotationPoint(3.0F, -0.5F, 1.0F);
		this.cheekTuff[cheekTuffNumber][cheekTuffPartNumber].addBox(-1.6F, -1.0F, -1.0F, 3, 2, 2, 0.0F);
		this.setRotateAngle(cheekTuff[cheekTuffNumber][cheekTuffPartNumber], 0.0F, -0.6981317007977318F, 0.4363323129985824F);
		this.cheekTuffInfo[cheekTuffNumber][cheekTuffPartNumber] = new PartInfo(this.cheekTuff[cheekTuffNumber][cheekTuffPartNumber]);
		cheekTuffPartNumber++;
		this.cheekTuff[cheekTuffNumber][cheekTuffPartNumber] = new ModelRenderer(this, 59, 5); // cheekTuffLft2
		this.cheekTuff[cheekTuffNumber][cheekTuffPartNumber].setRotationPoint(1.0F, 0.0F, 0.0F);
		this.cheekTuff[cheekTuffNumber][cheekTuffPartNumber].addBox(0.0F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		this.cheekTuffInfo[cheekTuffNumber][cheekTuffPartNumber] = new PartInfo(this.cheekTuff[cheekTuffNumber][cheekTuffPartNumber]);
		cheekTuffNumber++;
		cheekTuffPartNumber = 0;
		this.cheekTuff[cheekTuffNumber][cheekTuffPartNumber] = new ModelRenderer(this, 68, 0); // cheekTuffRt1
		this.cheekTuff[cheekTuffNumber][cheekTuffPartNumber].setRotationPoint(-3.0F, -0.5F, 1.0F);
		this.cheekTuff[cheekTuffNumber][cheekTuffPartNumber].addBox(-1.6F, -1.0F, -1.0F, 3, 2, 2, 0.0F);
		this.setRotateAngle(cheekTuff[cheekTuffNumber][cheekTuffPartNumber], 0.0F, 0.6981317007977318F, -0.4363323129985824F);
		this.cheekTuffInfo[cheekTuffNumber][cheekTuffPartNumber] = new PartInfo(this.cheekTuff[cheekTuffNumber][cheekTuffPartNumber]);
		cheekTuffPartNumber++;
		this.cheekTuff[cheekTuffNumber][cheekTuffPartNumber] = new ModelRenderer(this, 70, 5); // cheekTuffRt2
		this.cheekTuff[cheekTuffNumber][cheekTuffPartNumber].setRotationPoint(-2.0F, 0.0F, 0.0F);
		this.cheekTuff[cheekTuffNumber][cheekTuffPartNumber].addBox(-1.0F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		this.cheekTuffInfo[cheekTuffNumber][cheekTuffPartNumber] = new PartInfo(this.cheekTuff[cheekTuffNumber][cheekTuffPartNumber]);

		this.earLftJoint = new ModelRenderer(this, 0, 0);
		this.earLftJoint.setRotationPoint(2.0F, -5.0F, 0.0F);
		this.earLftJoint.addBox(-1.5F, -2.0F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earLftJoint, 0.0F, 0.0F, 0.3490658503988659F);
		this.earLftJointInfo = new PartInfo(this.earLftJoint);
		this.earLft = new ModelRenderer(this, 34, 6);
		this.earLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earLft.addBox(-1.5F, -2.0F, -0.5F, 3, 3, 1, 0.0F);
		this.earLft.setTextureOffset(35, 3).addBox(-1.0F, -3.0F, -0.5F, 2, 1, 1, 0.0F); // earLftMid
		this.earLft.setTextureOffset(36, 0).addBox(-0.5F, -4.0F, -0.5F, 1, 1, 1, 0.0F); // earLftTip
		this.earLft.setTextureOffset(29, 0).addBox(-1.0F, -1.9F, 0.0F, 2, 2, 1, 0.0F); // earLftBck
		this.earLftInfo = new PartInfo(this.earLft);
		this.earRtJoint = new ModelRenderer(this, 0, 0);
		this.earRtJoint.setRotationPoint(-2.0F, -5.0F, 0.0F);
		this.earRtJoint.addBox(-1.5F, -2.0F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earRtJoint, 0.0F, 0.0F, -0.3490658503988659F);
		this.earRtJointInfo = new PartInfo(this.earRtJoint);
		this.earRt = new ModelRenderer(this, 43, 6);
		this.earRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earRt.addBox(-1.5F, -2.0F, -0.5F, 3, 3, 1, 0.0F);
		this.earRt.setTextureOffset(44, 3).addBox(-1.0F, -3.0F, -0.5F, 2, 1, 1, 0.0F); // earRtMid
		this.earRt.setTextureOffset(45, 0).addBox(-0.5F, -4.0F, -0.5F, 1, 1, 1, 0.0F); // earRtTip
		this.earRt.setTextureOffset(50, 0).addBox(-1.0F, -1.9F, 0.0F, 2, 2, 1, 0.0F); // earRtBck
		this.earRtInfo = new PartInfo(this.earRt);

		int headHairNumber = 0;
		this.headHair[headHairNumber] = new ModelRenderer(this, 35, 30); // headHairMain1
		this.headHair[headHairNumber].setRotationPoint(0.0F, -6.0F, -1.5F);
		this.headHair[headHairNumber].addBox(-1.5F, -1.5F, -1.5F, 3, 3, 5, 0.0F);
		this.setRotateAngle(headHair[headHairNumber], 0.3490658503988659F, 0.0F, 0.0F);
		this.headHairInfo[headHairNumber] = new PartInfo(this.headHair[headHairNumber]);
		headHairNumber++;
		this.headHair[headHairNumber] = new ModelRenderer(this, 28, 19); // headHairMain1Top
		this.headHair[headHairNumber].setRotationPoint(0.0F, -1.0F, 1.5F);
		this.headHair[headHairNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 5, 0.0F);
		this.setRotateAngle(headHair[headHairNumber], 0.3490658503988659F, 0.0F, 0.0F);
		this.headHairInfo[headHairNumber] = new PartInfo(this.headHair[headHairNumber]);
		headHairNumber++;
		this.headHair[headHairNumber] = new ModelRenderer(this, 35, 22); // headHairSub1
		this.headHair[headHairNumber].setRotationPoint(0.0F, -0.3F, 3.0F);
		this.headHair[headHairNumber].addBox(-1.0F, -1.0F, -0.5F, 2, 2, 5, 0.0F);
		this.setRotateAngle(headHair[headHairNumber], -0.17453292519943295F, 0.0F, 0.0F);
		this.headHairInfo[headHairNumber] = new PartInfo(this.headHair[headHairNumber]);
		headHairNumber++;
		this.headHair[headHairNumber] = new ModelRenderer(this, 45, 20); // headHairSub2
		this.headHair[headHairNumber].setRotationPoint(0.0F, 0.7F, 3.0F);
		this.headHair[headHairNumber].addBox(-1.01F, -1.0F, -0.5F, 2, 2, 4, 0.0F);
		this.setRotateAngle(headHair[headHairNumber], -0.5235987755982988F, 0.0F, 0.0F);
		this.headHairInfo[headHairNumber] = new PartInfo(this.headHair[headHairNumber]);
		headHairNumber++;
		this.headHair[headHairNumber] = new ModelRenderer(this, 38, 14); // headHairSub1Top
		this.headHair[headHairNumber].setRotationPoint(0.0F, -0.3F, 0.5F);
		this.headHair[headHairNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 5, 0.0F);
		this.setRotateAngle(headHair[headHairNumber], 0.3490658503988659F, 0.0F, 0.0F);
		this.headHairInfo[headHairNumber] = new PartInfo(this.headHair[headHairNumber]);
		headHairNumber++;
		this.headHair[headHairNumber] = new ModelRenderer(this, 31, 12); // headHairSub1End
		this.headHair[headHairNumber].setRotationPoint(0.0F, 0.0F, 4.0F);
		this.headHair[headHairNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 5, 0.0F);
		this.setRotateAngle(headHair[headHairNumber], -0.17453292519943295F, 0.0F, 0.0F);
		this.headHairInfo[headHairNumber] = new PartInfo(this.headHair[headHairNumber]);
		headHairNumber++;
		this.headHair[headHairNumber] = new ModelRenderer(this, 46, 12); // headHairSub2End
		this.headHair[headHairNumber].setRotationPoint(0.0F, -0.3F, 3.1F);
		this.headHair[headHairNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 5, 0.0F);
		this.setRotateAngle(headHair[headHairNumber], -0.2617993877991494F, 0.0F, 0.0F);
		this.headHairInfo[headHairNumber] = new PartInfo(this.headHair[headHairNumber]);
		headHairNumber++;
		this.headHair[headHairNumber] = new ModelRenderer(this, 48, 7); // headHairSub2Bot
		this.headHair[headHairNumber].setRotationPoint(0.0F, 0.4F, 2.6F);
		this.headHair[headHairNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 4, 0.0F);
		this.setRotateAngle(headHair[headHairNumber], -0.6981317007977318F, 0.0F, 0.0F);
		this.headHairInfo[headHairNumber] = new PartInfo(this.headHair[headHairNumber]);

		this.shoulderRt = new ModelRenderer(this, 3, 18);
		this.shoulderRt.setRotationPoint(-2.4F, -10.4F, 0.0F);
		this.shoulderRt.addBox(-3.0F, -1.5F, -1.5F, 4, 3, 3, 0.0F);
		this.shoulderRtInfo = new PartInfo(this.shoulderRt);
		this.armRtJoint = new ModelRenderer(this, 0, 0);
		this.armRtJoint.setRotationPoint(-1.5F, 0.1F, 0.0F);
		this.armRtJoint.addBox(-1.5F, -1.5F, -7.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(armRtJoint, 0.4363323129985824F + (float)Math.toRadians(-10), 0.22689280275926282F, 0.0F);
		this.armRtJointInfo = new PartInfo(this.armRtJoint);
		this.armRt = new ModelRenderer(this, 0, 24);
		this.armRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.armRt.addBox(-1.5F, -1.5F, -7.0F, 3, 3, 7, 0.0F);
		this.armRtInfo = new PartInfo(this.armRt);
		this.forearmRtJoint = new ModelRenderer(this, 0, 0);
		this.forearmRtJoint.setRotationPoint(0.0F, 0.0F, -6.5F);
		this.forearmRtJoint.addBox(-1.5F, -1.5F, -6.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(forearmRtJoint, -0.8726646259971648F, 0.0F, 0.0F);
		this.forearmRtJointInfo = new PartInfo(this.forearmRtJoint);
		this.forearmRt = new ModelRenderer(this, 0, 34);
		this.forearmRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.forearmRt.addBox(-1.5F, -1.5F, -6.5F, 3, 3, 7, 0.0F);
		this.forearmRtInfo = new PartInfo(this.forearmRt);

		/* Arm right sit position defaults */
		this.armRtJoint = new ModelRenderer(this, 0, 0);
		this.armRtJoint.setRotationPoint(-1.5F, 0.1F, 0.0F);
		this.armRtJoint.addBox(-1.5F, -1.5F, -7.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(armRtJoint, 1.2217304763960306F, -0.17453292519943295F, 0.0F);
		this.armRtJointSittingInfo = new PartInfo(this.armRtJoint);
		this.armRt = new ModelRenderer(this, 0, 24);
		this.armRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.armRt.addBox(-1.5F, -1.5F, -7.0F, 3, 3, 7, 0.0F);
		this.armRtSittingInfo = new PartInfo(this.armRt);
		this.forearmRtJoint = new ModelRenderer(this, 0, 0);
		this.forearmRtJoint.setRotationPoint(0.0F, 0.0F, -6.5F);
		this.forearmRtJoint.addBox(-1.5F, -1.5F, -6.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(forearmRtJoint, 0.0F, -0.5235987755982988F, 0.0F);
		this.forearmRtJointSittingInfo = new PartInfo(this.forearmRtJoint);
		this.forearmRt = new ModelRenderer(this, 0, 34);
		this.forearmRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.forearmRt.addBox(-1.5F, -1.5F, -6.5F, 3, 3, 7, 0.0F);
		this.setRotateAngle(forearmRt, -0.17453292519943295F, 0.0F, 0.0F);
		this.forearmRtSittingInfo = new PartInfo(this.forearmRt);
		/* ********* */

		this.shoulderLft = new ModelRenderer(this, 17, 13);
		this.shoulderLft.setRotationPoint(2.4F, -10.4F, 0.0F);
		this.shoulderLft.addBox(-1.0F, -1.5F, -1.5F, 4, 3, 3, 0.0F);
		this.shoulderLftInfo = new PartInfo(this.shoulderLft);
		this.armLftJoint = new ModelRenderer(this, 0, 0);
		this.armLftJoint.setRotationPoint(1.5F, 0.1F, 0.0F);
		this.armLftJoint.addBox(-1.5F, -1.5F, -7.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(armLftJoint, 1.2217304763960306F, -0.22689280275926282F, 0.0F);
		this.armLftJointInfo = new PartInfo(this.armLftJoint);
		this.armLft = new ModelRenderer(this, 14, 19);
		this.armLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.armLft.addBox(-1.5F, -1.5F, -7.0F, 3, 3, 7, 0.0F);
		this.armLftInfo = new PartInfo(this.armLft);
		this.forearmLftJoint = new ModelRenderer(this, 0, 0);
		this.forearmLftJoint.setRotationPoint(0.0F, 0.0F, -6.5F);
		this.forearmLftJoint.addBox(-1.5F, -1.5F, -6.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(forearmLftJoint, -0.6981317007977318F, 0.0F, 0.0F);
		this.forearmLftJointInfo = new PartInfo(this.forearmLftJoint);
		this.forearmLft = new ModelRenderer(this, 14, 29);
		this.forearmLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.forearmLft.addBox(-1.5F, -1.5F, -6.5F, 3, 3, 7, 0.0F);
		this.forearmLftInfo = new PartInfo(this.forearmLft);

		/* Arm left sit position defaults */
		this.armLftJoint = new ModelRenderer(this, 0, 0);
		this.armLftJoint.setRotationPoint(1.5F, 0.1F, 0.0F);
		this.armLftJoint.addBox(-1.5F, -1.5F, -7.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(armLftJoint, 1.2217304763960306F, 0.17453292519943295F, 0.0F);
		this.armLftJointSittingInfo = new PartInfo(this.armLftJoint);
		this.armLft = new ModelRenderer(this, 14, 19);
		this.armLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.armLft.addBox(-1.5F, -1.5F, -7.0F, 3, 3, 7, 0.0F);
		this.armLftSittingInfo = new PartInfo(this.armLft);
		this.forearmLftJoint = new ModelRenderer(this, 0, 0);
		this.forearmLftJoint.setRotationPoint(0.0F, 0.0F, -6.5F);
		this.forearmLftJoint.addBox(-1.5F, -1.5F, -6.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(forearmLftJoint, 0.0F, 0.5235987755982988F, 0.0F);
		this.forearmLftJointSittingInfo = new PartInfo(this.forearmLftJoint);
		this.forearmLft = new ModelRenderer(this, 14, 29);
		this.forearmLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.forearmLft.addBox(-1.5F, -1.5F, -6.5F, 3, 3, 7, 0.0F);
		this.setRotateAngle(forearmLft, -0.3490658503988659F, 0.0F, 0.0F);
		this.forearmLftSittingInfo = new PartInfo(this.forearmLft);
		/* ********* */

		this.tailJoint = new ModelRenderer(this, 0, 0);
		this.tailJoint.setRotationPoint(0.0F, -1.0F, 2.0F);
		this.tailJoint.addBox(-1.5F, -1.5F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tailJoint, -0.17453292519943295F, 0.0F, 0.0F);
		this.tailJointInfo = new PartInfo(this.tailJoint);

		int tailNumber = 0;
		int tailPartNumber = 0;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail1_1Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(1.2F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.5F, -1.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.7853981633974483F, 1.7453292519943295F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 51, 50); // tail1_1
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.5F, -1.5F, -0.5F, 3, 3, 6, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail1_2Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 4.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.0F, -2.0F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.3490658503988659F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 63, 53); // tail1_2
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.0F, -2.0F, 0.0F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail1_3Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.01F, -2.0F, -0.7F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.6108652381980153F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 86, 53); // tail1_3
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.01F, -2.0F, -0.7F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail1_4Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.0F, -2.0F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.4363323129985824F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 106, 41); // tail1_4
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.0F, -2.0F, -1.0F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail1_5Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 5.6F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.01F, -2.0F, -0.7F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.2617993877991494F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 73, 19); // tail1_5
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.01F, -2.0F, -0.7F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail1_6Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.5F, -1.5F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 102, 52); // tail1_6
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.5F, -1.5F, -1.0F, 3, 3, 5, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail1_7Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 4.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.0F, -1.0F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 115, 58); // tail1_7
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.0F, -1.0F, -1.0F, 2, 2, 4, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail1_8Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 118, 52); // tail1_8
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 4, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailNumber++;
		tailPartNumber = 0;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail2_1Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.9F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.5F, -1.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.6981317007977318F, 1.3089969389957472F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 51, 50); // tail2_1
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.5F, -1.5F, -0.5F, 3, 3, 6, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail2_2Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 4.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.0F, -2.0F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.4363323129985824F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 63, 53); // tail2_2
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.0F, -2.0F, 0.0F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail2_3Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.01F, -2.0F, -0.7F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.6108652381980153F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 86, 53); // tail2_3
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.01F, -2.0F, -0.7F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail2_4Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.0F, -2.0F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.6108652381980153F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 106, 41); // tail2_4
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.0F, -2.0F, -1.0F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail2_5Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 5.6F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.01F, -2.0F, -0.7F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 73, 31); // tail2_5
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.01F, -2.0F, -0.7F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail2_6Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.5F, -1.5F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.4363323129985824F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 102, 52); // tail2_6
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.5F, -1.5F, -1.0F, 3, 3, 5, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail2_7Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 4.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.0F, -1.0F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 115, 58); // tail2_7
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.0F, -1.0F, -1.0F, 2, 2, 4, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail2_8Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 118, 52); // tail2_8
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 4, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailNumber++;
		tailPartNumber = 0;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail3_1Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.6F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.5F, -1.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.5235987755982988F, 0.8726646259971648F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 51, 50); // tail3_1
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.5F, -1.5F, -0.5F, 3, 3, 6, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail3_2Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 4.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.0F, -2.0F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.5235987755982988F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 63, 53); // tail3_2
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.0F, -2.0F, 0.0F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail3_3Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.01F, -2.0F, -0.7F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.6108652381980153F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 86, 53); // tail3_3
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.01F, -2.0F, -0.7F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail3_4Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.0F, -2.0F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.5235987755982988F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 106, 41); // tail3_4
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.0F, -2.0F, -1.0F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail3_5Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 5.6F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.01F, -2.0F, -0.7F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.5235987755982988F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 89, 36); // tail3_5
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.01F, -2.0F, -0.7F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail3_6Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.5F, -1.5F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.4363323129985824F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 102, 52); // tail3_6
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.5F, -1.5F, -1.0F, 3, 3, 5, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail3_7Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 4.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.0F, -1.0F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 115, 58); // tail3_7
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.0F, -1.0F, -1.0F, 2, 2, 4, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail3_8Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 118, 52); // tail3_8
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 4, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailNumber++;
		tailPartNumber = 0;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail4_1Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.3F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.5F, -1.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.4363323129985824F, 0.4363323129985824F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 51, 50); // tail4_1
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.5F, -1.5F, -0.5F, 3, 3, 6, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail4_2Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 4.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.0F, -2.0F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.6108652381980153F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 63, 53); // tail4_2
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.0F, -2.0F, 0.0F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail4_3Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.01F, -2.0F, -0.7F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.6981317007977318F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 86, 53); // tail4_3
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.01F, -2.0F, -0.7F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail4_4Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.0F, -2.0F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.5235987755982988F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 106, 41); // tail4_4
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.0F, -2.0F, -1.0F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail4_5Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 5.6F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.01F, -2.0F, -0.7F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.5235987755982988F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 90, 24); // tail4_5
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.01F, -2.0F, -0.7F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail4_6Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.5F, -1.5F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.5235987755982988F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 102, 52); // tail4_6
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.5F, -1.5F, -1.0F, 3, 3, 5, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail4_7Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 4.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.0F, -1.0F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.4363323129985824F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 115, 58); // tail4_7
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.0F, -1.0F, -1.0F, 2, 2, 4, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail4_8Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.4363323129985824F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 118, 52); // tail4_8
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 4, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailNumber++;
		tailPartNumber = 0;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail5_1Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.5F, -1.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 51, 50); // tail5_1
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.5F, -1.5F, -0.5F, 3, 3, 6, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail5_2Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 4.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.0F, -2.0F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.6981317007977318F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 63, 53); // tail5_2
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.0F, -2.0F, 0.0F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail5_3Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.01F, -2.0F, -0.7F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.7853981633974483F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 86, 53); // tail5_3
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.01F, -2.0F, -0.7F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail5_4Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.0F, -2.0F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.6108652381980153F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 106, 41); // tail5_4
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.0F, -2.0F, -1.0F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail5_5Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 5.6F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.01F, -2.0F, -0.7F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.5235987755982988F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 90, 12); // tail5_5
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.01F, -2.0F, -0.7F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail5_6Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.5F, -1.5F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.5235987755982988F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 102, 52); // tail5_6
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.5F, -1.5F, -1.0F, 3, 3, 5, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail5_7Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 4.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.0F, -1.0F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.5235987755982988F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 115, 58); // tail5_7
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.0F, -1.0F, -1.0F, 2, 2, 4, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail5_8Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 118, 52); // tail5_8
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 4, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailNumber++;
		tailPartNumber = 0;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail6_1Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.5F, -1.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.4363323129985824F, -0.4363323129985824F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 51, 50); // tail6_1
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.5F, -1.5F, -0.5F, 3, 3, 6, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail6_2Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 4.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.0F, -2.0F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.6108652381980153F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 63, 53); // tail6_2
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.0F, -2.0F, 0.0F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail6_3Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.01F, -2.0F, -0.7F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.6981317007977318F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 86, 53); // tail6_3
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.01F, -2.0F, -0.7F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail6_4Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.0F, -2.0F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.5235987755982988F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 106, 41); // tail6_4
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.0F, -2.0F, -1.0F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail6_5Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 5.6F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.01F, -2.0F, -0.7F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.5235987755982988F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 89, 0); // tail6_5
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.01F, -2.0F, -0.7F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail6_6Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.5F, -1.5F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.5235987755982988F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 102, 52); // tail6_6
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.5F, -1.5F, -1.0F, 3, 3, 5, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail6_7Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 4.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.0F, -1.0F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.4363323129985824F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 115, 58); // tail6_7
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.0F, -1.0F, -1.0F, 2, 2, 4, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail6_8Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.4363323129985824F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 118, 52); // tail6_8
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 4, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailNumber++;
		tailPartNumber = 0;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail7_1Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.5F, -1.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.5235987755982988F, -0.8726646259971648F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 51, 50); // tail7_1
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.5F, -1.5F, -0.5F, 3, 3, 6, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail7_2Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 4.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.0F, -2.0F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.5235987755982988F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 63, 53); // tail7_2
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.0F, -2.0F, 0.0F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail7_3Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.01F, -2.0F, -0.7F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.6108652381980153F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 86, 53); // tail7_3
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.01F, -2.0F, -0.7F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail7_4Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.0F, -2.0F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.5235987755982988F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 106, 41); // tail7_4
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.0F, -2.0F, -1.0F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail7_5Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 5.6F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.01F, -2.0F, -0.7F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.5235987755982988F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 106, 5); // tail7_5
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.01F, -2.0F, -0.7F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail7_6Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.5F, -1.5F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.4363323129985824F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 102, 52); // tail7_6
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.5F, -1.5F, -1.0F, 3, 3, 5, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail7_7Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 4.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.0F, -1.0F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 115, 58); // tail7_7
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.0F, -1.0F, -1.0F, 2, 2, 4, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail7_8Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 118, 52); // tail7_8
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 4, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailNumber++;
		tailPartNumber = 0;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail8_1Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.5F, -1.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.6981317007977318F, -1.3089969389957472F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 51, 50); // tail8_1
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.5F, -1.5F, -0.5F, 3, 3, 6, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail8_2Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 4.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.0F, -2.0F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.4363323129985824F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 63, 53); // tail8_2
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.0F, -2.0F, 0.0F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail8_3Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.01F, -2.0F, -0.7F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.6108652381980153F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 86, 53); // tail8_3
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.01F, -2.0F, -0.7F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail8_4Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.0F, -2.0F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.6108652381980153F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 106, 41); // tail8_4
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.0F, -2.0F, -1.0F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail8_5Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 5.6F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.01F, -2.0F, -0.7F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 106, 17); // tail8_5
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.01F, -2.0F, -0.7F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail8_6Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.5F, -1.5F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.4363323129985824F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 102, 52); // tail8_6
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.5F, -1.5F, -1.0F, 3, 3, 5, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail8_7Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 4.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.0F, -1.0F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 115, 58); // tail8_7
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.0F, -1.0F, -1.0F, 2, 2, 4, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail8_8Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 118, 52); // tail8_8
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 4, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailNumber++;
		tailPartNumber = 0;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail9_1Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.5F, -1.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.7853981633974483F, -1.7453292519943295F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 51, 50); // tail9_1
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.5F, -1.5F, -0.5F, 3, 3, 6, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail9_2Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 4.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.0F, -2.0F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.3490658503988659F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 63, 53); // tail9_2
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.0F, -2.0F, 0.0F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail9_3Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.01F, -2.0F, -0.7F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.6108652381980153F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 86, 53); // tail9_3
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.01F, -2.0F, -0.7F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail9_4Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.0F, -2.0F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.4363323129985824F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 106, 41); // tail9_4
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.0F, -2.0F, -1.0F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail9_5Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 5.6F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-2.01F, -2.0F, -0.7F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.2617993877991494F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 106, 29); // tail9_5
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-2.01F, -2.0F, -0.7F, 4, 4, 7, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail9_6Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.5F, -1.5F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 102, 52); // tail9_6
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.5F, -1.5F, -1.0F, 3, 3, 5, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail9_7Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 4.0F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-1.0F, -1.0F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 115, 58); // tail9_7
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-1.0F, -1.0F, -1.0F, 2, 2, 4, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail9_8Joint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 118, 52); // tail9_8
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 4, 0.0F);
		this.tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(this.tail[tailNumber][tailPartNumber][partNumber]);

		this.bodyJoint.addChild(this.body);
		this.bodyJoint.addChild(this.legLft[0]);
		this.bodyJoint.addChild(this.legRt[0]);
		this.body.addChild(this.neck);
		this.body.addChild(this.shoulderLft);
		this.body.addChild(this.shoulderRt);
		this.body.addChild(this.tailJoint);

		this.legLft[0].addChild(this.legLft[1]);
		this.legLft[1].addChild(this.legLft[2]);
		this.legRt[0].addChild(this.legRt[1]);
		this.legRt[1].addChild(this.legRt[2]);

		this.shoulderLft.addChild(this.armLftJoint);
		this.armLftJoint.addChild(this.armLft);
		this.armLft.addChild(this.forearmLftJoint);
		this.forearmLftJoint.addChild(this.forearmLft);
		this.shoulderRt.addChild(this.armRtJoint);
		this.armRtJoint.addChild(this.armRt);
		this.armRt.addChild(this.forearmRtJoint);
		this.forearmRtJoint.addChild(this.forearmRt);

		this.neck.addChild(this.headJoint);
		this.headJoint.addChild(this.head);

		this.head.addChild(this.cheekTuff[0][0]);
		this.head.addChild(this.cheekTuff[1][0]);
		this.head.addChild(this.earLftJoint);
		this.head.addChild(this.earRtJoint);
		this.head.addChild(this.headHair[0]);
		this.head.addChild(this.jaw);
		this.head.addChild(this.muzzle);
		this.head.addChild(this.nose);

		this.cheekTuff[0][0].addChild(this.cheekTuff[0][1]);
		this.cheekTuff[1][0].addChild(this.cheekTuff[1][1]);

		this.earLftJoint.addChild(this.earLft);
		this.earRtJoint.addChild(this.earRt);

		this.headHair[0].addChild(this.headHair[1]); // headHairMain1 - headHairMain1Top
		this.headHair[0].addChild(this.headHair[2]); // headHairMain1 - headHairSub1
		this.headHair[0].addChild(this.headHair[3]); // headHairMain1 - headHairSub2
		this.headHair[2].addChild(this.headHair[4]); // headHairSub1 - headHairSub1End
		this.headHair[2].addChild(this.headHair[5]); // headHairSub1 - headHairSub1Top
		this.headHair[3].addChild(this.headHair[6]); // headHairSub2 - headHairSub2Bot
		this.headHair[3].addChild(this.headHair[7]); // headHairSub2 - headHairSub2End

		for(int i = 0; i < tail.length; i++)
		{
			this.tailJoint.addChild(this.tail[i][0][0]);
		}

		for(int i = 0; i < tail.length; i++)
		{
			for(int j = 0; j < tail[i].length - 1; j++)
			{
				this.tail[i][j][0].addChild(this.tail[i][j][1]);
				this.tail[i][j][1].addChild(this.tail[i][j + 1][0]);
			}
		}

		animationDeployer = new AnimationDeployer(this);
	}

	@Override
	public void render(Entity entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize) 
	{
		animate(entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);

		this.bodyJoint.render(modelSize);
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
		this.animationDeployer.update((IAnimatedEntity)entity);
		resetPartInfos();

		float angularVelocity = ((IMobMotionTracker)entity).getAngularVelocity();
		float verticalVelocity = (float)((IMobMotionTracker)entity).getHeightVelocity();
		float gravityFactor = (float)((IGravityTracker)entity).getGravityFactor();

		float idleDampener = 1F;
		boolean isSitting = false;

		float vastayaNinetalesHealthPercent = 1F;

		if(entity instanceof EntityVastayaNinetales)
		{
			isSitting = ((EntityVastayaNinetales) entity).isSitting();

			if(((EntityVastayaNinetales) entity).isTamed())
			{
				vastayaNinetalesHealthPercent = ((EntityVastayaNinetales) entity).getHealthPercent();
			}
		}
		else
		{
			isSitting = false;
		}

		float tick = animationDeployer.getEntity().getAnimationTick() + KindredLegacyMain.proxy.getPartialTick();

		if((animationDeployer.getEntity().getAnimationID() == LibraryAhriNinetalesAttackID.FOXFIRE_SUMMON || 
				animationDeployer.getEntity().getAnimationID() == LibraryAhriNinetalesAttackID.FOXFIRE_STORM ||
				(animationDeployer.getEntity().getAnimationID() == LibraryAhriNinetalesAttackID.FIREBLAST && tick <= 28)) && !isSitting)
		{
			idleDampener = animateFoxfireSummon(animationDeployer.getEntity(), distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
		}
		else if(animationDeployer.getEntity().getAnimationID() == LibraryAhriNinetalesAttackID.FIREBALL && !isSitting)
		{
			idleDampener = animateFireballAttack(animationDeployer.getEntity(), distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
		}
		else if(((animationDeployer.getEntity().getAnimationID() == LibraryAhriNinetalesAttackID.JUMP_FIREBALL || 
				(animationDeployer.getEntity().getAnimationID() == LibraryAhriNinetalesAttackID.FIREBLAST && tick > 28))) && !isSitting)
		{
			if(animationDeployer.getEntity().getAnimationID() == LibraryAhriNinetalesAttackID.FIREBLAST) tick -= 28F;
			idleDampener = animateJumpFireballAttack(animationDeployer.getEntity(), distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, tick);
		}
		else if(animationDeployer.getEntity().getAnimationID() == LibraryUniversalAttackID.IGNITE && !isSitting)
		{
			idleDampener = animateIgnite(animationDeployer.getEntity(), distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
		}

		animateBody((IAnimateAhriNinetales)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, idleDampener, isSitting, verticalVelocity, gravityFactor);
		animateHead((IAnimateAhriNinetales)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, idleDampener, isSitting, vastayaNinetalesHealthPercent, verticalVelocity, gravityFactor);
		animateArms((IAnimateAhriNinetales)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, idleDampener, isSitting, verticalVelocity, gravityFactor);
		animateLegs((IAnimateAhriNinetales)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, idleDampener, isSitting, verticalVelocity, gravityFactor);
		animateTail((IAnimateAhriNinetales)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, idleDampener, isSitting, angularVelocity, verticalVelocity);

		deployAnimations();
	}

	public void resetPartInfos()
	{
		bodyJointInfo.resetNewAngles();
		bodyJointInfo.resetNewPnt();

		bodyInfo.resetNewAngles();

		headJointInfo.resetNewAngles();
		headInfo.resetNewAngles();

		for(int i = 0; i < headHair.length; i++)
		{
			headHairInfo[i].resetNewAngles();
		}

		earLftInfo.resetNewAngles();
		earRtInfo.resetNewAngles();

		for(int i = 0; i < cheekTuff.length; i++)
		{
			for(int j = 0; j < cheekTuff[i].length; j++)
			{
				cheekTuffInfo[i][j].resetNewAngles();
				cheekTuffInfo[i][j].resetNewAngles();
			}
		}

		armRtJointInfo.resetNewAngles();
		armLftJointInfo.resetNewAngles();
		armRtJointSittingInfo.resetNewAngles();
		armLftJointSittingInfo.resetNewAngles();

		armRtInfo.resetNewAngles();
		armLftInfo.resetNewAngles();
		armRtSittingInfo.resetNewAngles();
		armLftSittingInfo.resetNewAngles();

		forearmRtJointInfo.resetNewAngles();
		forearmLftJointInfo.resetNewAngles();
		forearmRtJointSittingInfo.resetNewAngles();
		forearmLftJointSittingInfo.resetNewAngles();

		forearmRtInfo.resetNewAngles();
		forearmLftInfo.resetNewAngles();
		forearmRtSittingInfo.resetNewAngles();
		forearmLftSittingInfo.resetNewAngles();

		for(int i = 0; i < legRt.length; i++)
		{
			legLftInfo[i].resetNewAngles();
			legRtInfo[i].resetNewAngles();
		}

		tailJointInfo.resetNewAngles();

		for(int i = 0; i < tail.length; i++)
		{
			for(int j = 0; j < tail[i].length; j++)
			{
				tailInfo[i][j][0].resetNewAngles();
				tailInfo[i][j][1].resetNewAngles();
			}
		}
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
		Vector3f armLftJointAngleChanges = new Vector3f();
		Vector3f forearmLftJointAngleChanges = new Vector3f();

		Vector3f legAngleChanges[] = {new Vector3f(), new Vector3f(), new Vector3f()};

		Vector3f tailJointDefaultXModifier = new Vector3f(1F, 0, 0);
		Vector3f tailJointAngleChanges = new Vector3f();

		Vector3f headAngleChanges = new Vector3f();
		Vector3f headHairAngleChanges = new Vector3f();

		float squatDurationStart = 0F;
		float squatDurationEnd = squatEnd;

		Vector3f bodyJointSquatPointChanges = new Vector3f(0F, 3.8F, 3F);

		Vector3f legSquatAngleChanges[] = {new Vector3f((float)Math.toRadians(-42), (float)Math.toRadians(-13), (float)Math.toRadians(-5)),
				new Vector3f((float)Math.toRadians(70), (float)Math.toRadians(0), (float)Math.toRadians(0)),
				new Vector3f((float)Math.toRadians(-48), (float)Math.toRadians(0), (float)Math.toRadians(0))};

		Vector3f bodySquatAngleChanges = new Vector3f((float)Math.toRadians(25), 0F, 0F);
		Vector3f armLftJointSquatAngleChanges = new Vector3f((float)Math.toRadians(-50), (float)Math.toRadians(-30), 0F);
		Vector3f forearmLftJointSquatAngleChanges = new Vector3f(0F, (float)Math.toRadians(-10), 0F);

		Vector3f tailJointDefaultModifierMaximum = new Vector3f(-0.85F, 0, 0);
		Vector3f tailJointTurnAngle = new Vector3f(0, (float)Math.toRadians(-30), 0);

		Vector3f headTurnAngle = new Vector3f(0, (float)Math.toRadians(55), 0);
		Vector3f headHairTurnAngle = new Vector3f(0, (float)Math.toRadians(-60), 0);

		Vector3f bodyJointTurnAngle = new Vector3f(0, (float)Math.toRadians(360), 0);

		float jumpDurationStart = squatDurationEnd;
		float jumpDurationEnd = jumpEnd;

		Vector3f bodyJointJumpPointChanges = new Vector3f(0F, -9.5F, -3.5F);

		Vector3f legJumpAngleChanges[] = {new Vector3f((float)Math.toRadians(52), (float)Math.toRadians(0), (float)Math.toRadians(0)),
				new Vector3f((float)Math.toRadians(-60), (float)Math.toRadians(0), (float)Math.toRadians(0)),
				new Vector3f((float)Math.toRadians(60), (float)Math.toRadians(0), (float)Math.toRadians(0))};

		Vector3f bodyJumpAngleChanges = new Vector3f((float)Math.toRadians(-20), 0F, 0F);
		Vector3f armLftJointJumpAngleChanges = new Vector3f((float)Math.toRadians(-30), (float)Math.toRadians(60), 0F);
		Vector3f forearmLftJointJumpAngleChanges = new Vector3f((float)Math.toRadians(-5), (float)Math.toRadians(60), 0F);

		float landDurationStart = jumpDurationEnd;
		float landDurationEnd = landEnd;

		Vector3f bodyJointLandPointChanges = new Vector3f(0F, -bodyJointJumpPointChanges.getY(), -bodyJointJumpPointChanges.getZ());

		Vector3f legLandAngleChanges[] = {new Vector3f(-legJumpAngleChanges[0].getX(), (float)Math.toRadians(0), (float)Math.toRadians(0)),
				new Vector3f(-legJumpAngleChanges[1].getX(), (float)Math.toRadians(0), (float)Math.toRadians(0)),
				new Vector3f(-legJumpAngleChanges[2].getX(), (float)Math.toRadians(0), (float)Math.toRadians(0))};

		Vector3f bodyLandAngleChanges = new Vector3f(-bodyJumpAngleChanges.getX(), 0F, 0F);
		Vector3f armLftJointLandAngleChanges = new Vector3f((float)Math.toRadians(30), 0F, 0F);
		Vector3f forearmLftJointLandAngleChanges = new Vector3f((float)Math.toRadians(5), 0F, 0F);


		float squatDurationCovered = PartAnimate.calculateDuration(tick, squatDurationStart, squatDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyJointPointChanges, bodyJointSquatPointChanges,
				squatDurationCovered);

		for(int i = 0; i < legAngleChanges.length; i++)
		{
			PartAnimate.changeOverDurationSCurve(legAngleChanges[i], legSquatAngleChanges[i],
					squatDurationCovered);
		}

		PartAnimate.changeOverDurationSCurve(bodyAngleChanges, bodySquatAngleChanges,
				squatDurationCovered);
		PartAnimate.changeOverDurationSCurve(armLftJointAngleChanges, armLftJointSquatAngleChanges,
				squatDurationCovered);
		PartAnimate.changeOverDurationSCurve(forearmLftJointAngleChanges, forearmLftJointSquatAngleChanges,
				squatDurationCovered);


		float jumpDurationCovered = PartAnimate.calculateDuration(tick, jumpDurationStart, jumpDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyJointPointChanges, bodyJointJumpPointChanges,
				jumpDurationCovered);

		for(int i = 0; i < legAngleChanges.length; i++)
		{
			PartAnimate.changeOverDurationSCurve(legAngleChanges[i], legJumpAngleChanges[i],
					jumpDurationCovered);
		}

		PartAnimate.changeOverDurationSCurve(bodyAngleChanges, bodyJumpAngleChanges,
				jumpDurationCovered);
		PartAnimate.changeOverDurationSCurve(armLftJointAngleChanges, armLftJointJumpAngleChanges,
				jumpDurationCovered);
		PartAnimate.changeOverDurationSCurve(forearmLftJointAngleChanges, forearmLftJointJumpAngleChanges,
				jumpDurationCovered);

		PartAnimate.changeOverDurationSCurve(tailJointAngleChanges, tailJointTurnAngle,
				jumpDurationCovered);

		PartAnimate.changeOverDurationSCurve(tailJointDefaultXModifier, tailJointDefaultModifierMaximum,
				PartAnimate.calculateDuration(tick, squatDurationStart, jumpDurationEnd));

		PartAnimate.changeOverDurationSCurve(headAngleChanges, headTurnAngle,
				jumpDurationCovered);
		PartAnimate.changeOverDurationSCurve(headHairAngleChanges, headHairTurnAngle,
				jumpDurationCovered);


		float landDurationCovered = PartAnimate.calculateDuration(tick, landDurationStart, landDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyJointPointChanges, bodyJointLandPointChanges,
				landDurationCovered);

		for(int i = 0; i < legAngleChanges.length; i++)
		{
			PartAnimate.changeOverDurationSCurve(legAngleChanges[i], legLandAngleChanges[i],
					landDurationCovered);
		}

		PartAnimate.changeOverDurationSCurve(bodyAngleChanges, bodyLandAngleChanges,
				landDurationCovered);
		PartAnimate.changeOverDurationSCurve(armLftJointAngleChanges, armLftJointLandAngleChanges,
				landDurationCovered);
		PartAnimate.changeOverDurationSCurve(forearmLftJointAngleChanges, forearmLftJointLandAngleChanges,
				landDurationCovered);
		PartAnimate.changeOverDurationSCurve(bodyJointAngleChanges, bodyJointTurnAngle,
				PartAnimate.calculateDuration(tick, jumpDurationStart, landDurationEnd));

		float returnDampener = 1 - PartAnimate.calculateDuration(tick, landDurationEnd, actionEnd);

		bodyJointPointChanges.multiplyVector(returnDampener);

		for(int i = 0; i < legAngleChanges.length; i++)
		{
			legAngleChanges[i].multiplyVector(returnDampener);
		}

		bodyAngleChanges.multiplyVector(returnDampener);
		armLftJointAngleChanges.multiplyVector(returnDampener);
		forearmLftJointAngleChanges.multiplyVector(returnDampener);

		PartAnimate.changeOverDurationSCurve(tailJointDefaultXModifier, Vector3f.negative(tailJointDefaultModifierMaximum),
				PartAnimate.calculateDuration(tick, landDurationStart, actionEnd));

		PartAnimate.changeOverDurationSCurve(tailJointAngleChanges, Vector3f.negative(tailJointTurnAngle),
				PartAnimate.calculateDuration(tick, landDurationStart, actionEnd));

		PartAnimate.changeOverDurationSCurve(headAngleChanges, Vector3f.negative(headTurnAngle),
				PartAnimate.calculateDuration(tick, landDurationStart, actionEnd));
		PartAnimate.changeOverDurationSCurve(headHairAngleChanges, Vector3f.negative(headHairTurnAngle),
				PartAnimate.calculateDuration(tick, landDurationStart, actionEnd));


		PartAnimate.applyPointChangeVectorToInfo(bodyJointInfo, bodyJointPointChanges);

		for(int i = 0; i < legAngleChanges.length; i++)
		{
			PartAnimate.applyRotationChangeVectorToInfo(legRtInfo[i], new Vector3f(legAngleChanges[i].getX(), legAngleChanges[i].getY(), legAngleChanges[i].getZ()));
			PartAnimate.applyRotationChangeVectorToInfo(legLftInfo[i], new Vector3f(legAngleChanges[i].getX(), -legAngleChanges[i].getY(), -legAngleChanges[i].getZ()));
		}

		PartAnimate.applyRotationChangeVectorToInfo(bodyJointInfo, bodyJointAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(bodyInfo, bodyAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(armLftJointInfo, armLftJointAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(forearmLftJointInfo, forearmLftJointAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(headInfo, headAngleChanges);

		for(int i = 0; i < tail.length; i++)
		{
			for(int j = 0; j < tail[i].length; j++)
			{
				tailInfo[i][j][0].setNewRotateX(tailInfo[i][j][0].getNewRotateX() * tailJointDefaultXModifier.getX());
				PartAnimate.applyRotationChangeVectorToInfo(tailInfo[i][j][0], tailJointAngleChanges);
			}
		}

		for(int i = 0; i < headHair.length; i++)
		{
			PartAnimate.applyRotationChangeVectorToInfo(headHairInfo[i], headHairAngleChanges);
		}

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
		Vector3f armLftJointAngleChanges = new Vector3f();
		Vector3f forearmLftJointAngleChanges = new Vector3f();
		Vector3f armRtJointAngleChanges = new Vector3f();
		Vector3f forearmRtJointAngleChanges = new Vector3f();

		Vector3f legAngleChanges[] = {new Vector3f(), new Vector3f(), new Vector3f()};

		Vector3f tailJointDefaultXModifier = new Vector3f(1F, 0, 0);
		Vector3f tailJointAngleChanges = new Vector3f();

		Vector3f headAngleChanges = new Vector3f();
		Vector3f headHairAngleChanges = new Vector3f();

		float squatDurationStart = 0F;
		float squatDurationEnd = squatEnd;

		Vector3f bodyJointSquatPointChanges = new Vector3f(0F, 3.8F, 3F);

		Vector3f legSquatAngleChanges[] = {new Vector3f((float)Math.toRadians(-42), (float)Math.toRadians(-13), (float)Math.toRadians(-5)),
				new Vector3f((float)Math.toRadians(70), (float)Math.toRadians(0), (float)Math.toRadians(0)),
				new Vector3f((float)Math.toRadians(-48), (float)Math.toRadians(0), (float)Math.toRadians(0))};

		Vector3f bodySquatAngleChanges = new Vector3f((float)Math.toRadians(25), 0F, 0F);
		Vector3f armLftJointSquatAngleChanges = new Vector3f((float)Math.toRadians(-50), (float)Math.toRadians(-30), 0F);
		Vector3f forearmLftJointSquatAngleChanges = new Vector3f(0F, (float)Math.toRadians(-10), 0F);

		float jumpDurationStart = squatEnd;
		float jumpDurationEnd = jumpEnd;

		Vector3f bodyJointJumpPointChanges = new Vector3f(0F, -9.5F, -3.5F);

		Vector3f legJumpAngleChanges[] = {new Vector3f((float)Math.toRadians(100), (float)Math.toRadians(0), (float)Math.toRadians(0)),
				new Vector3f((float)Math.toRadians(-120), (float)Math.toRadians(0), (float)Math.toRadians(0)),
				new Vector3f((float)Math.toRadians(120), (float)Math.toRadians(0), (float)Math.toRadians(0))};

		Vector3f bodyJumpAngleChanges = new Vector3f((float)Math.toRadians(-25), 0F, 0F);
		Vector3f armRtJointJumpAngleChanges = new Vector3f((float)Math.toRadians(-55), (float)Math.toRadians(-13), 0F);
		Vector3f forearmRtJointJumpAngleChanges = new Vector3f((float)Math.toRadians(-10), 0F, 0F);
		Vector3f armLftJointJumpAngleChanges = new Vector3f(0F, (float)Math.toRadians(25), 0F);

		float landDurationStart = jumpEnd;
		float landDurationEnd = landEnd;

		Vector3f bodyJointLandPointChanges = new Vector3f(0F, -bodyJointJumpPointChanges.getY(), -bodyJointJumpPointChanges.getZ());

		Vector3f legLandAngleChanges[] = {new Vector3f(-legJumpAngleChanges[0].getX(), (float)Math.toRadians(0), (float)Math.toRadians(0)),
				new Vector3f(-legJumpAngleChanges[1].getX(), (float)Math.toRadians(0), (float)Math.toRadians(0)),
				new Vector3f(-legJumpAngleChanges[2].getX(), (float)Math.toRadians(0), (float)Math.toRadians(0))};

		Vector3f bodyLandAngleChanges = new Vector3f((float)Math.toRadians(35), 0F, 0F);
		Vector3f armRtJointLandAngleChanges = new Vector3f((float)Math.toRadians(70), (float)Math.toRadians(-13), 0F);
		Vector3f forearmRtJointLandAngleChanges = new Vector3f((float)Math.toRadians(30), 0F, 0F);
		Vector3f armLftJointLandAngleChanges = new Vector3f((float)Math.toRadians(-20), (float)Math.toRadians(-60), 0F);


		float squatDurationCovered = PartAnimate.calculateDuration(tick, squatDurationStart, squatDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyJointPointChanges, bodyJointSquatPointChanges,
				squatDurationCovered);

		for(int i = 0; i < legAngleChanges.length; i++)
		{
			PartAnimate.changeOverDurationSCurve(legAngleChanges[i], legSquatAngleChanges[i],
					squatDurationCovered);
		}

		PartAnimate.changeOverDurationSCurve(bodyAngleChanges, bodySquatAngleChanges,
				squatDurationCovered);
		PartAnimate.changeOverDurationSCurve(armLftJointAngleChanges, armLftJointSquatAngleChanges,
				squatDurationCovered);
		PartAnimate.changeOverDurationSCurve(forearmLftJointAngleChanges, forearmLftJointSquatAngleChanges,
				squatDurationCovered);


		float jumpDurationCovered = PartAnimate.calculateDuration(tick, jumpDurationStart, jumpDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyJointPointChanges, bodyJointJumpPointChanges,
				jumpDurationCovered);

		for(int i = 0; i < legAngleChanges.length; i++)
		{
			PartAnimate.changeOverDurationSCurve(legAngleChanges[i], legJumpAngleChanges[i],
					jumpDurationCovered);
		}

		PartAnimate.changeOverDurationSCurve(bodyAngleChanges, bodyJumpAngleChanges,
				jumpDurationCovered);
		PartAnimate.changeOverDurationSCurve(armRtJointAngleChanges, armRtJointJumpAngleChanges,
				jumpDurationCovered);
		PartAnimate.changeOverDurationSCurve(forearmRtJointAngleChanges, forearmRtJointJumpAngleChanges,
				jumpDurationCovered);
		PartAnimate.changeOverDurationSCurve(armLftJointAngleChanges, armLftJointJumpAngleChanges,
				jumpDurationCovered);


		float landDurationCovered = PartAnimate.calculateDuration(tick, landDurationStart, landDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyJointPointChanges, bodyJointLandPointChanges,
				landDurationCovered);

		for(int i = 0; i < legAngleChanges.length; i++)
		{
			PartAnimate.changeOverDurationSCurve(legAngleChanges[i], legLandAngleChanges[i],
					landDurationCovered);
		}

		PartAnimate.changeOverDurationSCurve(bodyAngleChanges, bodyLandAngleChanges,
				landDurationCovered);
		PartAnimate.changeOverDurationSCurve(armRtJointAngleChanges, armRtJointLandAngleChanges,
				landDurationCovered);
		PartAnimate.changeOverDurationSCurve(forearmRtJointAngleChanges, forearmRtJointLandAngleChanges,
				landDurationCovered);
		PartAnimate.changeOverDurationSCurve(armLftJointAngleChanges, armLftJointLandAngleChanges,
				landDurationCovered);


		float returnDampener = 1 - PartAnimate.calculateDuration(tick, landDurationEnd, actionEnd);

		bodyJointPointChanges.multiplyVector(returnDampener);

		for(int i = 0; i < legAngleChanges.length; i++)
		{
			legAngleChanges[i].multiplyVector(returnDampener);
		}

		bodyAngleChanges.multiplyVector(returnDampener);
		armRtJointAngleChanges.multiplyVector(returnDampener);
		forearmRtJointAngleChanges.multiplyVector(returnDampener);
		armLftJointAngleChanges.multiplyVector(returnDampener);

		PartAnimate.applyPointChangeVectorToInfo(bodyJointInfo, bodyJointPointChanges);

		for(int i = 0; i < legAngleChanges.length; i++)
		{
			PartAnimate.applyRotationChangeVectorToInfo(legRtInfo[i], new Vector3f(legAngleChanges[i].getX(), legAngleChanges[i].getY(), legAngleChanges[i].getZ()));
			PartAnimate.applyRotationChangeVectorToInfo(legLftInfo[i], new Vector3f(legAngleChanges[i].getX(), -legAngleChanges[i].getY(), -legAngleChanges[i].getZ()));
		}

		PartAnimate.applyRotationChangeVectorToInfo(bodyJointInfo, bodyJointAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(bodyInfo, bodyAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(armRtJointInfo, armRtJointAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(forearmRtJointInfo, forearmRtJointAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(armLftJointInfo, armLftJointAngleChanges);

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
		Vector3f armLftJointAngleChanges = new Vector3f();

		float raiseDurationStart = 0F;
		float raiseDurationEnd = raiseEnd;

		Vector3f bodyRaiseAngleChanges = new Vector3f((float)Math.toRadians(-10), 0F, 0F);
		Vector3f armRtJointRaiseAngleChanges = new Vector3f((float)Math.toRadians(-40), (float)Math.toRadians(-13), 0F);
		Vector3f forearmRtJointRaiseAngleChanges = new Vector3f((float)Math.toRadians(-10), 0F, 0F);
		Vector3f armLftJointRaiseAngleChanges = new Vector3f(0F, (float)Math.toRadians(25), 0F);

		float thrustDurationStart = raiseEnd;
		float thrustDurationEnd = thrustEnd;

		Vector3f bodyThrustAngleChanges = new Vector3f((float)Math.toRadians(20), 0F, 0F);
		Vector3f armRtJointThrustAngleChanges = new Vector3f((float)Math.toRadians(55), (float)Math.toRadians(-13), 0F);
		Vector3f forearmRtJointThrustAngleChanges = new Vector3f((float)Math.toRadians(30), 0F, 0F);
		Vector3f armLftJointThrustAngleChanges = new Vector3f((float)Math.toRadians(-20), (float)Math.toRadians(-60), 0F);


		float raiseDurationCovered = PartAnimate.calculateDuration(tick, raiseDurationStart, raiseDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyAngleChanges, bodyRaiseAngleChanges,
				raiseDurationCovered);
		PartAnimate.changeOverDurationSCurve(armRtJointAngleChanges, armRtJointRaiseAngleChanges,
				raiseDurationCovered);
		PartAnimate.changeOverDurationSCurve(forearmRtJointAngleChanges, forearmRtJointRaiseAngleChanges,
				raiseDurationCovered);
		PartAnimate.changeOverDurationSCurve(armLftJointAngleChanges, armLftJointRaiseAngleChanges,
				raiseDurationCovered);


		float thrustDurationCovered = PartAnimate.calculateDuration(tick, thrustDurationStart, thrustDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyAngleChanges, bodyThrustAngleChanges,
				thrustDurationCovered);
		PartAnimate.changeOverDurationSCurve(armRtJointAngleChanges, armRtJointThrustAngleChanges,
				thrustDurationCovered);
		PartAnimate.changeOverDurationSCurve(forearmRtJointAngleChanges, forearmRtJointThrustAngleChanges,
				thrustDurationCovered);
		PartAnimate.changeOverDurationSCurve(armLftJointAngleChanges, armLftJointThrustAngleChanges,
				thrustDurationCovered);


		float returnDampener = 1 - PartAnimate.calculateDuration(tick, thrustDurationEnd, actionEnd);

		bodyAngleChanges.multiplyVector(returnDampener);
		armRtJointAngleChanges.multiplyVector(returnDampener);
		forearmRtJointAngleChanges.multiplyVector(returnDampener);
		armLftJointAngleChanges.multiplyVector(returnDampener);


		PartAnimate.applyRotationChangeVectorToInfo(bodyInfo, bodyAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(armRtJointInfo, armRtJointAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(forearmRtJointInfo, forearmRtJointAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(armLftJointInfo, armLftJointAngleChanges);

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
		Vector3f armLftJointAngleChanges = new Vector3f();
		Vector3f forearmLftJointAngleChanges = new Vector3f();
		Vector3f headAngleChanges = new Vector3f();

		float raiseDurationStart = 0F;
		float raiseDurationEnd = raiseEnd;

		Vector3f bodyRaiseAngleChanges = new Vector3f(0F, (float)Math.toRadians(-40), (float)Math.toRadians(15));
		Vector3f armLftJointRaiseAngleChanges = new Vector3f((float)Math.toRadians(-20), (float)Math.toRadians(-45), 0F);
		Vector3f forearmLftJointRaiseAngleChanges = new Vector3f((float)Math.toRadians(-10), 0F, 0F);
		Vector3f headRaiseAngleChanges = new Vector3f(0F, (float)Math.toRadians(5), 0F);

		float thrustDurationStart = raiseEnd;
		float thrustDurationEnd = thrustEnd;

		Vector3f bodyThrustAngleChanges = new Vector3f(0F, (float)Math.toRadians(60), (float)Math.toRadians(-30));
		Vector3f armLftJointThrustAngleChanges = new Vector3f((float)Math.toRadians(-40), (float)Math.toRadians(80), 0F);
		Vector3f forearmLftJointThrustAngleChanges = new Vector3f((float)Math.toRadians(20), (float)Math.toRadians(40), 0F);
		Vector3f headThrustAngleChanges = new Vector3f(0F, (float)Math.toRadians(-10), 0F);


		float raiseDurationCovered = PartAnimate.calculateDuration(tick, raiseDurationStart, raiseDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyAngleChanges, bodyRaiseAngleChanges,
				raiseDurationCovered);
		PartAnimate.changeOverDurationSCurve(armLftJointAngleChanges, armLftJointRaiseAngleChanges,
				raiseDurationCovered);
		PartAnimate.changeOverDurationSCurve(forearmLftJointAngleChanges, forearmLftJointRaiseAngleChanges,
				raiseDurationCovered);
		PartAnimate.changeOverDurationSCurve(headAngleChanges, headRaiseAngleChanges,
				raiseDurationCovered);


		float thrustDurationCovered = PartAnimate.calculateDuration(tick, thrustDurationStart, thrustDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyAngleChanges, bodyThrustAngleChanges,
				thrustDurationCovered);
		PartAnimate.changeOverDurationSCurve(armLftJointAngleChanges, armLftJointThrustAngleChanges,
				thrustDurationCovered);
		PartAnimate.changeOverDurationSCurve(forearmLftJointAngleChanges, forearmLftJointThrustAngleChanges,
				thrustDurationCovered);
		PartAnimate.changeOverDurationSCurve(headAngleChanges, headThrustAngleChanges,
				thrustDurationCovered);


		float returnDampener = 1 - PartAnimate.calculateDuration(tick, thrustDurationEnd, actionEnd);

		bodyAngleChanges.multiplyVector(returnDampener);
		armLftJointAngleChanges.multiplyVector(returnDampener);
		forearmLftJointAngleChanges.multiplyVector(returnDampener);
		headAngleChanges.multiplyVector(returnDampener);


		PartAnimate.applyRotationChangeVectorToInfo(bodyInfo, bodyAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(armLftJointInfo, armLftJointAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(forearmLftJointInfo, forearmLftJointAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(headInfo, headAngleChanges);

		return idleDampener;
	}

	public void animateBody(IAnimateAhriNinetales entity, float distanceMoved, float horzVelocity, 
			float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener, boolean isSitting, float verticalVelocity, float gravityFactor)
	{
		if(!isSitting)
		{
			float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
			float runCycleInterval = (RUN_FREQUENCY * gravityFactor  * distanceMoved % (2 * PI))/(2 * PI);

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
		else
		{
			bodyInfo.setNewRotateX(0F);
		}
	}

	public void animateHead(IAnimateAhriNinetales entity, float distanceMoved, float horzVelocity, 
			float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener, boolean isSitting, float vastayaNinetalesHealthPercent, float verticalVelocity, float gravityFactor)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, headJointInfo);

		PartAnimate.headAnimateInfoOnlyWithAngleModifiers(headInfo, yawHeadOffsetDifference, pitchRotation, 0.9F * idleDampener, 0.9F * idleDampener);

		if(!isSitting)
		{
			float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
			float runCycleInterval = (RUN_FREQUENCY * gravityFactor * distanceMoved % (2 * PI))/(2 * PI);

			float walkAngle = (float)Math.toRadians(-3F);
			float runAngle = (float)Math.toRadians(-5);

			float walkCycleAngleChange = MathHelper.cos(2 * walkCycleInterval * 2 * PI) * walkAngle * (1 - horzVelocity)
					+ walkAngle * (1 - horzVelocity);
			float runCycleAngleChange = MathHelper.cos(2 * runCycleInterval * 2 * PI) * runAngle * horzVelocity
					+ runAngle * horzVelocity;

			float newVerticalVelocity = 1.5F * verticalVelocity;

			if(newVerticalVelocity > 1F)
				newVerticalVelocity = 1F;
			else if(newVerticalVelocity < -1F)
				newVerticalVelocity = -1F;

			float fallingHeadHairAngle = (float)Math.toRadians(-20);

			for(int i = 0; i < headHair.length; i++)
			{
				headHairInfo[i].setNewRotateX(headHairInfo[i].getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (float)(i * 0.51F) * (1F - Math.abs(newVerticalVelocity))
				+ fallingHeadHairAngle * newVerticalVelocity);
			}

			/* ******************* */
			walkAngle = (float)Math.toRadians(7F);
			runAngle = (float)Math.toRadians(15);

			walkCycleAngleChange = MathHelper.cos(2 * walkCycleInterval * 2 * PI) * walkAngle * (1 - horzVelocity)
					+ walkAngle * (1 - horzVelocity);
			runCycleAngleChange = MathHelper.cos(2 * runCycleInterval * 2 * PI) * runAngle * horzVelocity
					+ runAngle * horzVelocity;

			float fallingEarAngle = (float)Math.toRadians(30);

			earLftInfo.setNewRotateZ(earLftInfo.getNewRotateZ() + ((walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity)) + fallingEarAngle * newVerticalVelocity));
			earRtInfo.setNewRotateZ(earRtInfo.getNewRotateZ() - ((walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity)) + fallingEarAngle * newVerticalVelocity));	

			/* ******************* */
			walkAngle = (float)Math.toRadians(10);
			runAngle = (float)Math.toRadians(20);

			float fallingTuffsAngle = (float)Math.toRadians(-35);

			for(int i = 0; i < cheekTuff.length; i++)
			{
				for(int j = 0; j < cheekTuff[i].length; j++)
				{
					walkCycleAngleChange = MathHelper.cos(2 * walkCycleInterval * 2 * PI - ((j + 1) * (PI/2))) * walkAngle * (1 - horzVelocity)
							+ walkAngle * (1 - horzVelocity);
					runCycleAngleChange = MathHelper.cos(2 * runCycleInterval * 2 * PI - ((j + 1) * (PI/2))) * runAngle * horzVelocity
							+ runAngle * horzVelocity;

					cheekTuffInfo[i][j].setNewRotateZ(cheekTuffInfo[i][j].getNewRotateZ() 
							+ ((walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity)) 
									+ fallingTuffsAngle * newVerticalVelocity) * (float)Math.pow(-1F, i + 1));
				}
			}

			/* ******************* */
			IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockBody();

			float idleAmplitudeX = 0.10F;

			for(int i = 0; i < headHair.length; i++)
			{
				float angleChangeX = MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI - (PI/16) * (i + 1) - (PI/2)) * idleAmplitudeX * (1 - horzVelocity);

				headHairInfo[i].setNewRotateX(headHairInfo[i].getNewRotateX() + angleChangeX);
			}

			/* ******************* */
			float idleAmplitudeZ = 0.13F;

			// + (float)Math.toRadians(45)
			float angleChangeZ = MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI + PI) * idleAmplitudeZ * (1 - horzVelocity);

			earRtInfo.setNewRotateZ(earRtInfo.getNewRotateZ() - angleChangeZ);
			earLftInfo.setNewRotateZ(earLftInfo.getNewRotateZ() + angleChangeZ);

			/* ******************* */
			idleAmplitudeZ = 0.18F;

			for(int i = 0; i < cheekTuff.length; i++)
			{
				for(int j = 0; j < cheekTuff[i].length; j++)
				{
					angleChangeZ = MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI
							- ((j + 2) * (PI/2))) * idleAmplitudeZ * (1 - horzVelocity);

					cheekTuffInfo[i][j].setNewRotateZ(cheekTuffInfo[i][j].getNewRotateZ() - angleChangeZ * (float)Math.pow(-1F, i + 1));
				}
			}
		}

		float healthChangeAngle = (float)Math.toRadians(-75);

		earLftInfo.setNewRotateZ(earLftInfo.getNewRotateZ() - (1F - vastayaNinetalesHealthPercent) * healthChangeAngle);
		earRtInfo.setNewRotateZ(earRtInfo.getNewRotateZ() + (1F - vastayaNinetalesHealthPercent) * healthChangeAngle);
	}

	public void animateArms(IAnimateAhriNinetales entity, float distanceMoved, float horzVelocity, 
			float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener, boolean isSitting, float verticalVelocity, float gravityFactor)
	{
		if(!isSitting)
		{
			JointAnimation.reverseJointRotatesChange(bodyInfo, armLftJointInfo);
			JointAnimation.reverseJointRotatesChange(bodyInfo, armRtJointInfo);

			float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
			float runCycleInterval = (RUN_FREQUENCY * gravityFactor * distanceMoved % (2 * PI))/(2 * PI);

			float newVerticalVelocity = 2.5F * verticalVelocity;

			if(newVerticalVelocity > 1F)
				newVerticalVelocity = 1F;
			else if(newVerticalVelocity < -1F)
				newVerticalVelocity = -1F;

			float walkAngleChangeArmLftY = (float)Math.toRadians(-15);
			float runAngleChangeArmLftY = (float)Math.toRadians(-30);

			float walkCycleAngleChangeY = MathHelper.cos(walkCycleInterval * 2 * PI + PI/2) * walkAngleChangeArmLftY * (1 - horzVelocity)
					+ walkAngleChangeArmLftY * (1 - horzVelocity);
			float runCycleAngleChangeY = MathHelper.cos(runCycleInterval * 2 * PI + PI/2) * runAngleChangeArmLftY * horzVelocity
					+ runAngleChangeArmLftY * horzVelocity;

			armLftJointInfo.setNewRotateY(armLftJointInfo.getNewRotateY() + (walkCycleAngleChangeY + runCycleAngleChangeY) * horzVelocity * idleDampener * (1F - Math.abs(newVerticalVelocity) * 0.75F));

			float walkAngleChangeForearmLftY = (float)Math.toRadians(15);
			float runAngleChangeForearmLftY = (float)Math.toRadians(35);

			walkCycleAngleChangeY = MathHelper.cos(walkCycleInterval * 2 * PI - PI/2) * walkAngleChangeForearmLftY * (1 - horzVelocity)
					+ walkAngleChangeForearmLftY * (1 - horzVelocity);
			runCycleAngleChangeY = MathHelper.cos(runCycleInterval * 2 * PI - PI/2) * runAngleChangeForearmLftY * horzVelocity
					+ runAngleChangeForearmLftY * horzVelocity;

			forearmLftJointInfo.setNewRotateY(forearmLftJointInfo.getNewRotateY() + (walkCycleAngleChangeY + runCycleAngleChangeY) * horzVelocity * idleDampener * (1F - Math.abs(newVerticalVelocity) * 0.75F));

			float runDefaultAngleChangeArmLftX = (float)Math.toRadians(-45);
			float runAngleChangeArmLftX = (float)Math.toRadians(-15);

			float runCycleAngleChangeX = runDefaultAngleChangeArmLftX * horzVelocity
					+ MathHelper.cos(0.5F * walkCycleInterval * 2 * PI - PI/2) * runAngleChangeArmLftX * horzVelocity;

			armLftJointInfo.setNewRotateX(armLftJointInfo.getNewRotateX() + runCycleAngleChangeX * horzVelocity * idleDampener * (1F - Math.abs(newVerticalVelocity) * 0.75F));

			forearmLftJointInfo.setNewRotateX(forearmLftJointInfo.getNewRotateX() * (1 - horzVelocity * (1F - Math.abs(newVerticalVelocity) * 0.75F)));

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

			float idleAngleChangeX = (MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeX - idleAmplitudeX) * (1 - horzVelocity);
			armLftJointInfo.setNewRotateX(armLftJointInfo.getNewRotateX() + idleAngleChangeX * idleDampener);

			idleAngleChangeX = (MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI - (PI/2)) * idleAmplitudeX - idleAmplitudeX) * (1 - horzVelocity);
			forearmLftJointInfo.setNewRotateX(forearmLftJointInfo.getNewRotateX() + idleAngleChangeX * idleDampener);

			currentIdleAnimationClock = entity.getIdleAnimationClockArmRt();

			idleAmplitudeX = 0.15F;
			float idleAmplitudeY = 0.15F;

			idleAngleChangeX = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeX * (1 - horzVelocity);
			float idleAngleChangeY = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI + PI/2) * idleAmplitudeY * (1 - horzVelocity);
			armRtJointInfo.setNewRotateX(armRtJointInfo.getNewRotateX() + idleAngleChangeX * idleDampener);
			armRtJointInfo.setNewRotateY(armRtJointInfo.getNewRotateY() + idleAngleChangeY * idleDampener);

			idleAngleChangeX = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI - (PI/2)) * idleAmplitudeX * (1 - horzVelocity);
			idleAngleChangeY = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeY * (1 - horzVelocity);
			forearmRtJointInfo.setNewRotateX(forearmRtJointInfo.getNewRotateX() + idleAngleChangeX * idleDampener);
			forearmRtJointInfo.setNewRotateY(forearmRtJointInfo.getNewRotateY() + idleAngleChangeY * idleDampener);
		}
		else
		{
			armRtJointInfo.setNewRotatesWithVector(armRtJointSittingInfo.getNewRotates());
			armRtInfo.setNewRotatesWithVector(armRtSittingInfo.getNewRotates());

			armLftJointInfo.setNewRotatesWithVector(armLftJointSittingInfo.getNewRotates());
			armLftInfo.setNewRotatesWithVector(armLftSittingInfo.getNewRotates());

			forearmRtJointInfo.setNewRotatesWithVector(forearmRtJointSittingInfo.getNewRotates());
			forearmRtInfo.setNewRotatesWithVector(forearmRtSittingInfo.getNewRotates());

			forearmLftJointInfo.setNewRotatesWithVector(forearmLftJointSittingInfo.getNewRotates());
			forearmLftInfo.setNewRotatesWithVector(forearmLftSittingInfo.getNewRotates());
		}
	}

	public void animateLegs(IAnimateAhriNinetales entity, float distanceMoved, float horzVelocity, 
			float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener, boolean isSitting, float verticalVelocity, float gravityFactor)
	{	
		if(!isSitting)
		{
			float legRunStartAngles[] = new float[3];
			float legsRunAngleChange[][] = new float[3][4];

			// (float)Math.toRadians()

			legRunStartAngles[0] = (float)Math.toRadians(-20);
			legRunStartAngles[1] = (float)Math.toRadians(90);
			legRunStartAngles[2] = (float)Math.toRadians(-60);

			// These are absolute angles
			legsRunAngleChange[0][0] = (float)Math.toRadians(-10); // at 0
			legsRunAngleChange[0][1] = (float)Math.toRadians(20); // at PI/2
			legsRunAngleChange[0][2] = (float)Math.toRadians(60); // at PI
			legsRunAngleChange[0][3] = (float)Math.toRadians(-70); // at 3PI/2

			legsRunAngleChange[1][0] = (float)Math.toRadians(-40); // at 0
			legsRunAngleChange[1][1] = (float)Math.toRadians(25); // at PI/2
			legsRunAngleChange[1][2] = (float)Math.toRadians(-20); // at PI
			legsRunAngleChange[1][3] = (float)Math.toRadians(35); // at 3PI/2

			legsRunAngleChange[2][0] = (float)Math.toRadians(-20); // at 0
			legsRunAngleChange[2][1] = (float)Math.toRadians(25); // at PI/2
			legsRunAngleChange[2][2] = (float)Math.toRadians(25); // at PI
			legsRunAngleChange[2][3] = (float)Math.toRadians(-30); // at 3PI/2

			float moveModifierOtherAngles = 1F;

			if(horzVelocity < SNEAK_VELOCITY)
			{
				moveModifierOtherAngles = (float)(Math.pow(Math.E, -5F * (horzVelocity/SNEAK_VELOCITY)));
			}
			else
			{
				moveModifierOtherAngles = 0;
			}

			float jumpAngleChange = (float)Math.toRadians(30);

			float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
			float runCycleInterval = (RUN_FREQUENCY * gravityFactor * distanceMoved % (2 * PI))/(2 * PI);

			for(int i = 0; i < legLft.length; i++)
			{
				float walkCycleAngleChange = calculateRunLegAngleChange(walkCycleInterval, 
						legLftInfo[i].getNewRotateX(), legRunStartAngles[i], legsRunAngleChange[i]) * (1 - horzVelocity);

				float runCycleAngleChange = calculateRunLegAngleChange(runCycleInterval, 
						legLftInfo[i].getNewRotateX(), legRunStartAngles[i], legsRunAngleChange[i]) * horzVelocity;

				legLftInfo[i].setNewRotateX((legLftInfo[i].getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocity * idleDampener) 
						* (1F - Math.abs(verticalVelocity)));
			}

			legLftInfo[0].setNewRotateX(legLftInfo[0].getNewRotateX() + jumpAngleChange * verticalVelocity + 0.5F * Math.abs(verticalVelocity));
			legLftInfo[0].setNewRotateY(legLftInfo[0].getNewRotateY() * moveModifierOtherAngles * idleDampener * (1F - Math.abs(verticalVelocity)));
			legLftInfo[0].setNewRotateZ(legLftInfo[0].getNewRotateZ() * moveModifierOtherAngles * idleDampener * (1F - Math.abs(verticalVelocity)));

			float oppositeWalkCycleInterval = (((WALK_FREQUENCY * distanceMoved) + PI) % (2 * PI))/(2 * PI);
			float oppositeRunCycleInterval = (((RUN_FREQUENCY * gravityFactor * distanceMoved) + PI) % (2 * PI))/(2 * PI);

			for(int i = 0; i < legRt.length; i++)
			{
				float walkCycleAngleChange = calculateRunLegAngleChange(oppositeWalkCycleInterval, 
						legRtInfo[i].getNewRotateX(), legRunStartAngles[i], legsRunAngleChange[i]) * (1- horzVelocity);

				float runCycleAngleChange = calculateRunLegAngleChange(oppositeRunCycleInterval, 
						legRtInfo[i].getNewRotateX(), legRunStartAngles[i], legsRunAngleChange[i]) * horzVelocity;

				legRtInfo[i].setNewRotateX((legRtInfo[i].getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocity * idleDampener) 
						* (1F - Math.abs(verticalVelocity)));
			}

			legRtInfo[0].setNewRotateX(legRtInfo[0].getNewRotateX() + jumpAngleChange * verticalVelocity + 0.5F * Math.abs(verticalVelocity));
			legRtInfo[0].setNewRotateY(legRtInfo[0].getNewRotateY() * moveModifierOtherAngles * idleDampener * (1F - Math.abs(verticalVelocity)));
			legRtInfo[0].setNewRotateZ(legRtInfo[0].getNewRotateZ() * moveModifierOtherAngles * idleDampener * (1F - Math.abs(verticalVelocity)));
		}
		else
		{
			legRtInfo[0].setNewRotates(legRtInfo[0].getNewRotateX(), 0F, 0F);
			legLftInfo[0].setNewRotates(legLftInfo[0].getNewRotateX(), 0F, 0F);
		}
	}

	public float calculateRunLegAngleChange(float cycleInterval, float standingLegAngle, 
			float startingRunLegAngle, float legAngleChange[])
	{
		float newAngle = 0;

		float angleChangeFromStarting = startingRunLegAngle - standingLegAngle;

		if(cycleInterval <= 0.25F && cycleInterval >= 0)
		{
			float subInterval = cycleInterval/0.25F;

			newAngle =  angleChangeFromStarting
					+ subInterval * legAngleChange[0];
		}
		else if(cycleInterval <= 0.5F)
		{
			float subInterval = (cycleInterval - 0.25F)/0.25F;

			newAngle = angleChangeFromStarting + legAngleChange[0]
					+ subInterval * legAngleChange[1];
		}
		else if(cycleInterval <= 0.75F)
		{
			float subInterval = (cycleInterval - 0.5F)/0.25F;

			newAngle = angleChangeFromStarting + legAngleChange[0] + legAngleChange[1]
					+ subInterval * legAngleChange[2];
		}
		else if(cycleInterval <= 1F)
		{
			float subInterval = (cycleInterval - 0.75F)/0.25F;

			newAngle = angleChangeFromStarting + legAngleChange[0] + legAngleChange[1] + legAngleChange[2]
					+ subInterval * legAngleChange[3];
		}

		return newAngle;
	}

	public void animateTail(IAnimateAhriNinetales entity, float distanceMoved, float horzVelocity, 
			float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener, boolean isSitting, float angularVelocity, float verticalVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, tailJointInfo);

		float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
		float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);

		float walkAngle = (float)Math.toRadians(-0.5F);
		float runAngle = (float)Math.toRadians(-1);

		for(int i = 0; i < tail.length; i++)
		{
			for(int j = 0; j < tail[i].length; j++)
			{
				float walkCycleAngleChange = MathHelper.cos(2 * walkCycleInterval * 2 * PI - (2 * PI)/(i + j + 1)) * walkAngle * (1 - horzVelocity)
						+ walkAngle * (1 - horzVelocity);
				float runCycleAngleChange = MathHelper.cos(2 * runCycleInterval * 2 * PI - (2 * PI)/(i + j + 1)) * runAngle * horzVelocity
						+ runAngle * horzVelocity;

				tailInfo[i][j][1].setNewRotateX(tailInfo[i][j][1].getNewRotateX() + 
						(walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (float)Math.pow(Math.E, -0.4F * (float)(j + 1)) * idleDampener);
			}
		}

		/* ************************ */
		float tailIdleDampener = (float)Math.pow((0.5F * MathHelper.cos(idleDampener * (PI/2) - (PI/2)) + 0.5F), 2);

		float newHorzVelocity = 1.5F * Math.abs(horzVelocity);

		if(newHorzVelocity > 1F)
			newHorzVelocity = 1F;

		for(int i = 0; i < tail.length; i++)
		{
			for(int j = 0; j < tail[i].length; j++)
			{
				IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockTails(j);

				float idleAmplitudeX = 0.30F;
				float idleAmplitudeY = 0.08F;

				// + (float)Math.toRadians(45)
				float angleChangeX = 0;
				float angleChangeY = 0;

				float runYawChangeAngleModifier = (float)(tail[i].length - j) / (float)(tail.length - 1);
				
				float yawChangeAngle = -(float)Math.toRadians(15);
				float verticalVelocityChangeAngle = (float)Math.toRadians(-15);
				float runYawChangeAngle = -(float)Math.toRadians(35) * MathHelper.cos(PI * ((float)i / (float)(tail.length - 1))) 
						* runYawChangeAngleModifier * runYawChangeAngleModifier * runYawChangeAngleModifier;

				angleChangeX += (MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2 + ((2 * PI)/(float)tail.length) * i) 
						* idleAmplitudeX * (float)Math.pow(-1F, i + 1)) * ( 1 - (Math.pow(Math.E, -0.4F * (float)(j + 1))));
				angleChangeY += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2 + ((2 * PI)/(float)tail.length) * i) 
						* idleAmplitudeY * ( 1 - (Math.pow(Math.E, -0.4F * (float)(j + 1))));

				tailInfo[i][j][0].setNewRotateX(tailInfo[i][j][0].getNewRotateX() * (1F - Math.abs(angularVelocity)) * (1F - horzVelocity * 0.8F) * (1F - Math.abs(verticalVelocity) * 0.9F));
				tailInfo[i][j][0].setNewRotateY(tailInfo[i][j][0].getNewRotateY() + newHorzVelocity * runYawChangeAngle);

				tailInfo[i][j][1].setNewRotateX(tailInfo[i][j][1].getNewRotateX() + angleChangeX * tailIdleDampener * (1F - Math.abs(angularVelocity) * 0.75F) * (1F - Math.abs(verticalVelocity) * 0.75F)
						+ verticalVelocity * verticalVelocityChangeAngle);
				tailInfo[i][j][1].setNewRotateY(tailInfo[i][j][1].getNewRotateY() + angleChangeY * idleDampener * (1F - Math.abs(angularVelocity) * 0.75F) + yawChangeAngle * angularVelocity);
			}
		}
	}

	public void deployAnimations()
	{
		this.animationDeployer.rotate(bodyJoint, bodyJointInfo.getNewRotates());
		this.animationDeployer.move(bodyJoint, bodyJointInfo.getNewPnt());

		this.animationDeployer.rotate(body, bodyInfo.getNewRotates());

		this.animationDeployer.rotate(headJoint, headJointInfo.getNewRotates());
		this.animationDeployer.rotate(head, headInfo.getNewRotates());

		this.animationDeployer.rotate(earRt, earRtInfo.getNewRotates());
		this.animationDeployer.rotate(earLft, earLftInfo.getNewRotates());

		for(int i = 0; i < headHair.length; i++)
		{
			this.animationDeployer.rotate(headHair[i], headHairInfo[i].getNewRotates());
		}

		for(int i = 0; i < cheekTuff.length; i++)
		{
			for(int j = 0; j < cheekTuff[i].length; j++)
			{
				this.animationDeployer.rotate(cheekTuff[i][j], cheekTuffInfo[i][j].getNewRotates());
			}
		}

		this.animationDeployer.rotate(armRtJoint, armRtJointInfo.getNewRotates());
		this.animationDeployer.rotate(armLftJoint, armLftJointInfo.getNewRotates());

		this.animationDeployer.rotate(forearmRtJoint, forearmRtJointInfo.getNewRotates());
		this.animationDeployer.rotate(forearmLftJoint, forearmLftJointInfo.getNewRotates());

		for(int i = 0; i < legLft.length; i++)
		{
			this.animationDeployer.rotate(legRt[i], legRtInfo[i].getNewRotates());
			this.animationDeployer.rotate(legLft[i], legLftInfo[i].getNewRotates());
		}

		this.animationDeployer.rotate(tailJoint, tailJointInfo.getNewRotates());

		for(int i = 0; i < tail.length; i++)
		{
			for(int j = 0; j < tail[i].length; j++)
			{
				for(int k = 0; k < tail[i][j].length; k++)
				{
					this.animationDeployer.rotate(tail[i][j][k], tailInfo[i][j][k].getNewRotates());
				}
			}
		}

		this.animationDeployer.applyChanges();
	}
}