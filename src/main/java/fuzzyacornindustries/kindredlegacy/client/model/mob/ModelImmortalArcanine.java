package fuzzyacornindustries.kindredlegacy.client.model.mob;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.animation.JointAnimation;
import fuzzyacornindustries.kindredlegacy.animation.PartAnimate;
import fuzzyacornindustries.kindredlegacy.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.animation.Vector3f;
import fuzzyacornindustries.kindredlegacy.entity.mob.IMobMotionTracker;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityImmortalArcanine;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryImmortalArcanineAttackID;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelImmortalArcanine extends ModelBase 
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public final float RUN_FREQUENCY = 0.5F;
	public final float WALK_FREQUENCY = 0.7F;
	public final float WALK_MAX = 0.4F;
	public final float RUN_MIN = 0.5F;

	public ModelRenderer bodyJoint;
	public ModelRenderer bodyFront;
	public ModelRenderer bodyBack;
	PartInfo bodyJointInfo;
	PartInfo bodyJointSitInfo;
	PartInfo bodyFrontInfo;
	PartInfo bodyBackInfo;

	public ModelRenderer bodyFntTopArmor;
	public ModelRenderer bodyMidTopArmor;
	public ModelRenderer bodyBckTopArmor;

	public ModelRenderer chestPlate;

	public ModelRenderer neckJoint;
	public ModelRenderer neck;
	public ModelRenderer headJoint;
	public ModelRenderer head;
	PartInfo neckJointInfo;
	PartInfo neckJointSitInfo;
	PartInfo neckInfo;
	PartInfo headJointInfo;
	PartInfo headInfo;

	public ModelRenderer muzzle;
	public ModelRenderer jaw;
	PartInfo jawInfo;

	public ModelRenderer helmet;
	public ModelRenderer helmetFnt;
	public ModelRenderer helmetFntTop;
	public ModelRenderer helmetHorn1;
	public ModelRenderer helmetHorn2;
	public ModelRenderer helmetHorn3;
	public ModelRenderer helmetSideLft1;
	public ModelRenderer helmetSideLft2;
	public ModelRenderer helmetSideLft3;
	public ModelRenderer helmetSideRt1;
	public ModelRenderer helmetSideRt2;
	public ModelRenderer helmetSideRt3;

	public ModelRenderer headHair[] = new ModelRenderer[2];
	PartInfo headHairInfo[] = new PartInfo[headHair.length];

	public ModelRenderer earLftJoint;
	public ModelRenderer earLft;
	public ModelRenderer earRtJoint;
	public ModelRenderer earRt;
	PartInfo earLftJointInfo;
	PartInfo earRtJointInfo;

	public ModelRenderer legFntLft[][] = new ModelRenderer[2][2];
	PartInfo legFntLftInfo[][] = new PartInfo[legFntLft.length][2];
	PartInfo legFntLftSitInfo[][] = new PartInfo[legFntLft.length][2];

	public ModelRenderer legFntLftArmorPlate;

	public ModelRenderer legFntLftHair[] = new ModelRenderer[4];
	PartInfo legFntLftHairInfo[] = new PartInfo[legFntLftHair.length];
	public ModelRenderer legFntLftHair3_Lft;
	public ModelRenderer legFntLftHair3_Rt;

	public ModelRenderer legFntRt[][] = new ModelRenderer[2][2];
	PartInfo legFntRtInfo[][] = new PartInfo[legFntRt.length][2];
	PartInfo legFntRtSitInfo[][] = new PartInfo[legFntRt.length][2];

	public ModelRenderer legFntRtArmorPlate;

	public ModelRenderer legFntRtHair[] = new ModelRenderer[4];
	PartInfo legFntRtHairInfo[] = new PartInfo[legFntRtHair.length];
	public ModelRenderer legFntRtHair3_Lft;
	public ModelRenderer legFntRtHair3_Rt;

	public ModelRenderer legBckLft[][] = new ModelRenderer[3][2];
	PartInfo legBckLftInfo[][] = new PartInfo[legBckLft.length][2];
	PartInfo legBckLftSitInfo[][] = new PartInfo[legBckLft.length][2];

	public ModelRenderer legBckLftArmorPlate;

	public ModelRenderer legBckLftHair[] = new ModelRenderer[2];
	public ModelRenderer legBckLftHair3[] = new ModelRenderer[3];
	PartInfo legBckLftHairInfo[] = new PartInfo[legBckLftHair.length];
	PartInfo legBckLftHair3Info[] = new PartInfo[legBckLftHair3.length];

	public ModelRenderer legBckRt[][] = new ModelRenderer[3][2];
	PartInfo legBckRtInfo[][] = new PartInfo[legBckRt.length][2];
	PartInfo legBckRtSitInfo[][] = new PartInfo[legBckRt.length][2];

	public ModelRenderer legBckRtArmorPlate;

	public ModelRenderer legBckRtHair[] = new ModelRenderer[2];
	public ModelRenderer legBckRtHair3[] = new ModelRenderer[3];
	PartInfo legBckRtHairInfo[] = new PartInfo[legBckRtHair.length];
	PartInfo legBckRtHair3Info[] = new PartInfo[legBckRtHair3.length];

	public ModelRenderer gunLftBase;
	public ModelRenderer gunLftAimingPivot;
	public ModelRenderer gunLftBaseJoint;
	public ModelRenderer gunLftMain;
	public ModelRenderer gunLftPlate;
	PartInfo gunLftAimingPivotInfo;
	PartInfo gunLftBaseJointInfo;
	PartInfo gunLftMainInfo;

	public ModelRenderer gunLftBarrelBrace;
	PartInfo gunLftBarrelBraceInfo;

	public ModelRenderer gunLftBarrelRt[] = new ModelRenderer[6];
	PartInfo gunLftBarrelRtInfo[] = new PartInfo[gunLftBarrelRt.length];

	public ModelRenderer gunLftBarrelLft[] = new ModelRenderer[6];
	PartInfo gunLftBarrelLftInfo[] = new PartInfo[gunLftBarrelLft.length];

	public ModelRenderer gunRtBase;
	public ModelRenderer gunRtAimingPivot;
	public ModelRenderer gunRtBaseJoint;
	public ModelRenderer gunRtMain;
	public ModelRenderer gunRtPlate;
	PartInfo gunRtAimingPivotInfo;
	PartInfo gunRtBaseJointInfo;
	PartInfo gunRtMainInfo;

	public ModelRenderer gunRtBarrelBrace;
	PartInfo gunRtBarrelBraceInfo;

	public ModelRenderer gunRtBarrelRt[] = new ModelRenderer[6];
	PartInfo gunRtBarrelRtInfo[] = new PartInfo[gunRtBarrelRt.length];

	public ModelRenderer gunRtBarrelLft[] = new ModelRenderer[6];
	PartInfo gunRtBarrelLftInfo[] = new PartInfo[gunRtBarrelLft.length];

	public ModelRenderer tail[][] = new ModelRenderer[11][2];
	PartInfo tailInfo[][] = new PartInfo[tail.length][2];
	PartInfo tailSitInfo;

	public ModelImmortalArcanine() 
	{
		this.textureWidth = 128;
		this.textureHeight = 128;

		int jointNumber = 0;
		int partNumber = 1;

		this.bodyJoint = new ModelRenderer(this, 0, 0);
		this.bodyJoint.setRotationPoint(0.0F, 6.0F, 0.0F);
		this.bodyJoint.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
		this.bodyJointInfo = new PartInfo(bodyJoint);

		this.bodyJoint.setRotationPoint(0.0F, 13.0F, 3.0F);
		this.setRotateAngle(bodyJoint, -0.8726646259971648F, 0.0F, 0.0F);
		this.bodyJointSitInfo = new PartInfo(bodyJoint);

		this.bodyFront = new ModelRenderer(this, 26, 106);
		this.bodyFront.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bodyFront.addBox(-5.0F, -2.5F, -11.0F, 10, 10, 12, 0.0F);
		this.bodyFrontInfo = new PartInfo(bodyFront);
		this.bodyBack = new ModelRenderer(this, 81, 108);
		this.bodyBack.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bodyBack.addBox(-4.5F, -2.2F, -2.0F, 9, 8, 12, 0.0F);
		this.bodyBackInfo = new PartInfo(bodyBack);

		this.bodyFntTopArmor = new ModelRenderer(this, 41, 58);
		this.bodyFntTopArmor.setRotationPoint(0.0F, -1.0F, -4.5F);
		this.bodyFntTopArmor.addBox(-6.0F, -2.0F, -2.5F, 12, 2, 5, 0.0F);
		this.bodyMidTopArmor = new ModelRenderer(this, 42, 51);
		this.bodyMidTopArmor.setRotationPoint(0.0F, -0.6F, 0.5F);
		this.bodyMidTopArmor.addBox(-5.5F, -2.0F, -2.5F, 11, 2, 5, 0.0F);
		this.bodyBckTopArmor = new ModelRenderer(this, 41, 44);
		this.bodyBckTopArmor.setRotationPoint(0.0F, -1.0F, 4.5F);
		this.bodyBckTopArmor.addBox(-6.0F, -2.0F, -2.5F, 12, 2, 5, 0.0F);

		this.chestPlate = new ModelRenderer(this, 35, 66);
		this.chestPlate.setRotationPoint(0.0F, 0.0F, -10.5F);
		this.chestPlate.addBox(-5.5F, -1.0F, -1.5F, 11, 6, 2, 0.0F);
		this.chestPlate.setTextureOffset(39, 74).addBox(-4.0F, 5.0F, -1.2F, 8, 2, 1, 0.0F); // chestPlateBot
		this.chestPlate.setTextureOffset(40, 77).addBox(-3.0F, 7.1F, -1.0F, 6, 1, 2, 0.0F); // chestPlateBotEnd

		this.neckJoint = new ModelRenderer(this, 0, 0);
		this.neckJoint.setRotationPoint(0.0F, 0.0F, -9.0F);
		this.neckJoint.addBox(-2.5F, -2.49F, -8.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(neckJoint, -0.7853981633974483F, 0.0F, 0.0F);
		this.neckJointInfo = new PartInfo(neckJoint);

		this.neckJoint.setRotationPoint(0.0F, 0.0F, -9.0F);
		this.setRotateAngle(neckJoint, 0.08726646259971647F, 0.0F, 0.0F);
		this.neckJointSitInfo = new PartInfo(neckJoint);

		this.neck = new ModelRenderer(this, 0, 116);
		this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.neck.addBox(-2.5F, -2.49F, -7.5F, 5, 5, 7, 0.0F);
		this.neckInfo = new PartInfo(neck);
		this.headJoint = new ModelRenderer(this, 0, 0);
		this.headJoint.setRotationPoint(0.0F, 0.0F, -7.0F);
		this.headJoint.addBox(-4.5F, -7.0F, -6.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(headJoint, 0.7853981633974483F, 0.0F, 0.0F);
		this.headJointInfo = new PartInfo(headJoint);
		this.head = new ModelRenderer(this, 0, 49);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-4.5F, -7.0F, -6.5F, 9, 9, 9, 0.0F);
		this.headInfo = new PartInfo(head);

		this.muzzle = new ModelRenderer(this, 0, 69);
		this.muzzle.setRotationPoint(0.0F, 0.0F, -6.0F);
		this.muzzle.addBox(-2.5F, -3.0F, -4.0F, 5, 4, 4, 0.0F);
		this.setRotateAngle(muzzle, 0.17453292519943295F, 0.0F, 0.0F);
		this.muzzle.setTextureOffset(15, 69).addBox(-1.0F, -2.8F, -4.7F, 2, 2, 1, 0.0F); // nose
		this.jaw = new ModelRenderer(this, 0, 78);
		this.jaw.setRotationPoint(0.0F, 1.1F, -5.0F);
		this.jaw.addBox(-2.51F, -1.0F, -4.6F, 5, 2, 5, 0.0F);
		this.setRotateAngle(jaw, 0.08726646259971647F, 0.0F, 0.0F);
		this.jawInfo = new PartInfo(jaw);

		this.helmet = new ModelRenderer(this, 32, 32);
		this.helmet.setRotationPoint(0.0F, -7.0F, 0.0F);
		this.helmet.addBox(-5.0F, -1.5F, -6.6F, 10, 2, 9, 0.0F);
		this.setRotateAngle(helmet, 0.08726646259971647F, 0.0F, 0.0F);
		this.helmetFnt = new ModelRenderer(this, 65, 37);
		this.helmetFnt.setRotationPoint(0.0F, 0.0F, -6.4F);
		this.helmetFnt.addBox(-1.0F, -2.1F, -0.5F, 2, 3, 1, 0.0F);
		this.setRotateAngle(helmetFnt, 0.3141592653589793F, 0.0F, 0.0F);
		this.helmetFntTop = new ModelRenderer(this, 62, 35);
		this.helmetFntTop.setRotationPoint(0.0F, 0.0F, -6.4F);
		this.helmetFntTop.addBox(-0.5F, -4.1F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(helmetFntTop, 0.3141592653589793F, 0.0F, 0.0F);
		this.helmetHorn1 = new ModelRenderer(this, 16, 35);
		this.helmetHorn1.setRotationPoint(0.0F, -1.0F, -5.5F);
		this.helmetHorn1.addBox(-2.0F, -1.6F, -1.2F, 4, 2, 4, 0.0F);
		this.setRotateAngle(helmetHorn1, 0.2792526803190927F, 0.0F, 0.0F);
		this.helmetHorn2 = new ModelRenderer(this, 28, 34);
		this.helmetHorn2.setRotationPoint(0.0F, -1.0F, 0.4F);
		this.helmetHorn2.addBox(-1.5F, -2.1F, -1.5F, 3, 2, 3, 0.0F);
		this.setRotateAngle(helmetHorn2, 0.2792526803190927F, 0.0F, 0.0F);
		this.helmetHorn3 = new ModelRenderer(this, 23, 31);
		this.helmetHorn3.setRotationPoint(0.0F, -1.0F, -0.3F);
		this.helmetHorn3.addBox(-1.0F, -2.5F, -1.0F, 2, 2, 2, 0.0F);
		this.setRotateAngle(helmetHorn3, 0.2792526803190927F, 0.0F, 0.0F);
		this.helmetSideLft1 = new ModelRenderer(this, 53, 13);
		this.helmetSideLft1.setRotationPoint(4.5F, 0.0F, -2.0F);
		this.helmetSideLft1.addBox(-1.0F, -0.6F, -3.5F, 2, 4, 7, 0.0F);
		this.setRotateAngle(helmetSideLft1, -0.17453292519943295F, 0.0F, -0.17453292519943295F);
		this.helmetSideLft2 = new ModelRenderer(this, 50, 24);
		this.helmetSideLft2.setRotationPoint(-0.2F, 3.0F, 0.0F);
		this.helmetSideLft2.addBox(-1.0F, -0.6F, -2.5F, 2, 3, 5, 0.0F);
		this.setRotateAngle(helmetSideLft2, -0.17453292519943295F, 0.0F, 0.12217304763960307F);
		this.helmetSideLft3 = new ModelRenderer(this, 62, 30);
		this.helmetSideLft3.setRotationPoint(-0.2F, 3.0F, 0.0F);
		this.helmetSideLft3.addBox(-1.0F, -1.0F, -2.0F, 2, 2, 3, 0.0F);
		this.setRotateAngle(helmetSideLft3, -0.17453292519943295F, 0.0F, 0.12217304763960307F);
		this.helmetSideRt1 = new ModelRenderer(this, 53, 13);
		this.helmetSideRt1.setRotationPoint(-4.5F, 0.0F, -2.0F);
		this.helmetSideRt1.addBox(-1.0F, -0.6F, -3.5F, 2, 4, 7, 0.0F);
		this.setRotateAngle(helmetSideRt1, -0.17453292519943295F, 0.0F, 0.17453292519943295F);
		this.helmetSideRt2 = new ModelRenderer(this, 50, 24);
		this.helmetSideRt2.setRotationPoint(0.2F, 3.0F, 0.0F);
		this.helmetSideRt2.addBox(-1.0F, -0.6F, -2.5F, 2, 3, 5, 0.0F);
		this.setRotateAngle(helmetSideRt2, -0.17453292519943295F, 0.0F, -0.12217304763960307F);
		this.helmetSideRt3 = new ModelRenderer(this, 62, 30);
		this.helmetSideRt3.setRotationPoint(0.2F, 3.0F, 0.0F);
		this.helmetSideRt3.addBox(-1.0F, -1.0F, -2.0F, 2, 2, 3, 0.0F);
		this.setRotateAngle(helmetSideRt3, -0.17453292519943295F, 0.0F, -0.12217304763960307F);

		int hairNumber = 0;
		this.headHair[hairNumber] = new ModelRenderer(this, 0, 39); // headHair1
		this.headHair[hairNumber].setRotationPoint(0.0F, -5.0F, 2.0F);
		this.headHair[hairNumber].addBox(-3.0F, -3.0F, -1.0F, 6, 6, 4, 0.0F);
		this.setRotateAngle(headHair[hairNumber], 0.17453292519943295F, 0.0F, 0.0F);
		this.headHairInfo[hairNumber] = new PartInfo(this.headHair[hairNumber]);
		hairNumber++;
		this.headHair[hairNumber] = new ModelRenderer(this, 3, 32); // headHair2
		this.headHair[hairNumber].setRotationPoint(0.0F, 0.0F, 3.0F);
		this.headHair[hairNumber].addBox(-2.0F, -2.0F, -1.0F, 4, 4, 3, 0.0F);
		this.setRotateAngle(headHair[hairNumber], -0.12217304763960307F, 0.0F, 0.0F);
		this.headHairInfo[hairNumber] = new PartInfo(this.headHair[hairNumber]);

		this.earLftJoint = new ModelRenderer(this, 0, 0);
		this.earLftJoint.setRotationPoint(4.0F, -6.5F, -2.0F);
		this.earLftJoint.addBox(-2.0F, -3.5F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earLftJoint, 0.0F, 0.0F, 0.7853981633974483F);
		this.earLftJointInfo = new PartInfo(earLftJoint);
		this.earLft = new ModelRenderer(this, 8, 112);
		this.earLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earLft.addBox(-2.0F, -0.5F, -1.0F, 4, 2, 2, 0.0F);
		this.earLft.setTextureOffset(7, 107).addBox(-2.5F, -3.5F, -1.0F, 5, 3, 2, 0.0F); // earLftMid
		this.earLft.setTextureOffset(9, 103).addBox(-2.0F, -5.5F, -1.0F, 4, 2, 2, 0.0F); // earLftTop
		this.earLft.setTextureOffset(13, 99).addBox(-1.0F, -7.5F, -1.0F, 2, 2, 2, 0.0F); // earLftTopTip
		this.earRtJoint = new ModelRenderer(this, 0, 0);
		this.earRtJoint.setRotationPoint(-4.0F, -6.5F, -2.0F);
		this.earRtJoint.addBox(-2.0F, -3.5F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earRtJoint, 0.0F, 0.0F, -0.7853981633974483F);
		this.earRtJointInfo = new PartInfo(earRtJoint);
		this.earRt = new ModelRenderer(this, 0, 99);
		this.earRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earRt.addBox(-2.0F, -0.5F, -1.0F, 4, 2, 2, 0.0F);
		this.earRt.setTextureOffset(0, 94).addBox(-2.5F, -3.5F, -1.0F, 5, 3, 2, 0.0F); // earRtMid
		this.earRt.setTextureOffset(0, 90).addBox(-2.0F, -5.5F, -1.0F, 4, 2, 2, 0.0F); // earRtTop
		this.earRt.setTextureOffset(0, 86).addBox(-1.0F, -7.5F, -1.0F, 2, 2, 2, 0.0F); // earRtTopTip

		int legPartNumber = 0;
		this.legFntLft[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legFntLft1Joint
		this.legFntLft[legPartNumber][jointNumber].setRotationPoint(4.0F, 2.0F, -7.0F);
		this.legFntLft[legPartNumber][jointNumber].addBox(-2.0F, -1.5F, -2.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legFntLft[legPartNumber][jointNumber], 0.17453292519943295F, 0.0F, 0.0F);
		this.legFntLftInfo[legPartNumber][jointNumber] = new PartInfo(this.legFntLft[legPartNumber][jointNumber]);
		this.legFntLft[legPartNumber][partNumber] = new ModelRenderer(this, 59, 90); // legFntLft1
		this.legFntLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legFntLft[legPartNumber][partNumber].addBox(-2.0F, -1.5F, -2.0F, 4, 7, 4, 0.0F);
		this.legFntLftInfo[legPartNumber][partNumber] = new PartInfo(this.legFntLft[legPartNumber][partNumber]);
		legPartNumber++;
		this.legFntLft[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legFntLft2Joint
		this.legFntLft[legPartNumber][jointNumber].setRotationPoint(0.0F, 5.4F, 0.0F);
		this.legFntLft[legPartNumber][jointNumber].addBox(-2.0F, -1.0F, -2.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legFntLft[legPartNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.legFntLftInfo[legPartNumber][jointNumber] = new PartInfo(this.legFntLft[legPartNumber][jointNumber]);
		this.legFntLft[legPartNumber][partNumber] = new ModelRenderer(this, 59, 101); // legFntLft2
		this.legFntLft[legPartNumber][partNumber].setRotationPoint(-0.01F, 0.0F, 0.0F);
		this.legFntLft[legPartNumber][partNumber].addBox(-2.0F, -1.0F, -2.0F, 4, 12, 4, 0.0F);
		this.legFntLftInfo[legPartNumber][partNumber] = new PartInfo(this.legFntLft[legPartNumber][partNumber]);

		legPartNumber = 0;
		this.legFntLft[legPartNumber][jointNumber].setRotationPoint(4.0F, 2.0F, -7.0F); // legFntLft1Joint
		this.setRotateAngle(legFntLft[legPartNumber][jointNumber], 0.7853981633974483F, 0.0F, 0.0F);
		this.legFntLftSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legFntLft[legPartNumber][jointNumber]);
		this.legFntLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legFntLft1
		this.legFntLftSitInfo[legPartNumber][partNumber] = new PartInfo(this.legFntLft[legPartNumber][partNumber]);
		legPartNumber++;
		this.legFntLft[legPartNumber][jointNumber].setRotationPoint(0.0F, 5.4F, 0.0F); // legFntLft2Joint
		this.setRotateAngle(legFntLft[legPartNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.legFntLftSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legFntLft[legPartNumber][jointNumber]);
		this.legFntLft[legPartNumber][partNumber].setRotationPoint(-0.01F, 0.0F, 0.0F); // legFntLft2
		this.legFntLftSitInfo[legPartNumber][partNumber] = new PartInfo(this.legFntLft[legPartNumber][partNumber]);

		this.legFntLftArmorPlate = new ModelRenderer(this, 41, 97);
		this.legFntLftArmorPlate.setRotationPoint(0.0F, 5.0F, -2.0F);
		this.legFntLftArmorPlate.addBox(-2.5F, -2.0F, -1.0F, 5, 6, 2, 0.0F);
		this.legFntLftArmorPlate.setTextureOffset(54, 93).addBox(1.7F, -1.5F, 1.0F, 1, 5, 1, 0.0F); // legFntLftArmorPlateRt
		this.legFntLftArmorPlate.setTextureOffset(38, 93).addBox(-2.7F, -1.5F, 1.0F, 1, 5, 1, 0.0F); // legFntLftArmorPlateLft

		hairNumber = 0;
		this.legFntLftHair[hairNumber] = new ModelRenderer(this, 60, 83); // legFntLftHair1
		this.legFntLftHair[hairNumber].setRotationPoint(0.0F, 6.0F, 1.5F);
		this.legFntLftHair[hairNumber].addBox(-2.5F, -2.0F, -0.5F, 5, 4, 2, 0.0F);
		this.legFntLftHairInfo[hairNumber] = new PartInfo(this.legFntLftHair[hairNumber]);
		hairNumber++;
		this.legFntLftHair[hairNumber] = new ModelRenderer(this, 61, 78); // legFntLftHair2
		this.legFntLftHair[hairNumber].setRotationPoint(0.0F, -0.1F, 1.5F);
		this.legFntLftHair[hairNumber].addBox(-2.0F, -2.0F, -1.0F, 4, 3, 2, 0.0F);
		this.setRotateAngle(legFntLftHair[hairNumber], 0.3839724354387525F, 0.0F, 0.0F);
		this.legFntLftHairInfo[hairNumber] = new PartInfo(this.legFntLftHair[hairNumber]);
		hairNumber++;
		this.legFntLftHair[hairNumber] = new ModelRenderer(this, 63, 73); // legFntLftHair3
		this.legFntLftHair[hairNumber].setRotationPoint(0.0F, -0.7F, 0.9F);
		this.legFntLftHair[hairNumber].addBox(-1.0F, -0.8F, -1.0F, 2, 2, 2, 0.0F);
		this.setRotateAngle(legFntLftHair[hairNumber], 0.3839724354387525F, 0.0F, 0.0F);
		this.legFntLftHairInfo[hairNumber] = new PartInfo(this.legFntLftHair[hairNumber]);
		hairNumber++;
		this.legFntLftHair[hairNumber] = new ModelRenderer(this, 64, 70); // legFntLftHair4
		this.legFntLftHair[hairNumber].setRotationPoint(0.0F, 0.1F, 0.7F);
		this.legFntLftHair[hairNumber].addBox(-0.5F, -0.5F, -0.4F, 1, 1, 2, 0.0F);
		this.setRotateAngle(legFntLftHair[hairNumber], 0.2617993877991494F, 0.0F, 0.0F);
		this.legFntLftHairInfo[hairNumber] = new PartInfo(this.legFntLftHair[hairNumber]);
		this.legFntLftHair3_Lft = new ModelRenderer(this, 68, 74);
		this.legFntLftHair3_Lft.setRotationPoint(1.1F, -0.4F, 0.7F);
		this.legFntLftHair3_Lft.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(legFntLftHair3_Lft, 0.2617993877991494F, 0.2617993877991494F, 0.0F);
		this.legFntLftHair3_Rt = new ModelRenderer(this, 58, 74);
		this.legFntLftHair3_Rt.setRotationPoint(-1.1F, -0.4F, 0.7F);
		this.legFntLftHair3_Rt.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(legFntLftHair3_Rt, 0.2617993877991494F, -0.2617993877991494F, 0.0F);

		legPartNumber = 0;
		this.legFntRt[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legFntRt1Joint
		this.legFntRt[legPartNumber][jointNumber].setRotationPoint(-4.0F, 2.0F, -7.0F);
		this.legFntRt[legPartNumber][jointNumber].addBox(-2.0F, -1.5F, -2.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legFntRt[legPartNumber][jointNumber], 0.17453292519943295F, 0.0F, 0.0F);
		this.legFntRtInfo[legPartNumber][jointNumber] = new PartInfo(this.legFntRt[legPartNumber][jointNumber]);
		this.legFntRt[legPartNumber][partNumber] = new ModelRenderer(this, 21, 90); // legFntRt1
		this.legFntRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legFntRt[legPartNumber][partNumber].addBox(-2.0F, -1.5F, -2.0F, 4, 7, 4, 0.0F);
		this.legFntRtInfo[legPartNumber][partNumber] = new PartInfo(this.legFntRt[legPartNumber][partNumber]);
		legPartNumber++;
		this.legFntRt[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legFntRt2Joint
		this.legFntRt[legPartNumber][jointNumber].setRotationPoint(0.0F, 5.4F, 0.0F);
		this.legFntRt[legPartNumber][jointNumber].addBox(-2.0F, -1.0F, -2.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legFntRt[legPartNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.legFntRtInfo[legPartNumber][jointNumber] = new PartInfo(this.legFntRt[legPartNumber][jointNumber]);
		this.legFntRt[legPartNumber][partNumber] = new ModelRenderer(this, 21, 101); // legFntRt2
		this.legFntRt[legPartNumber][partNumber].setRotationPoint(0.01F, 0.0F, 0.0F);
		this.legFntRt[legPartNumber][partNumber].addBox(-2.0F, -1.0F, -2.0F, 4, 12, 4, 0.0F);
		this.legFntRtInfo[legPartNumber][partNumber] = new PartInfo(this.legFntRt[legPartNumber][partNumber]);

		legPartNumber = 0;
		this.legFntRt[legPartNumber][jointNumber].setRotationPoint(-4.0F, 2.0F, -7.0F); // legFntRt1Joint
		this.setRotateAngle(legFntRt[legPartNumber][jointNumber], 0.7853981633974483F, 0.0F, 0.0F);
		this.legFntRtSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legFntRt[legPartNumber][jointNumber]);
		this.legFntRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legFntRt1
		this.legFntRtSitInfo[legPartNumber][partNumber] = new PartInfo(this.legFntRt[legPartNumber][partNumber]);
		legPartNumber++;
		this.legFntRt[legPartNumber][jointNumber].setRotationPoint(0.0F, 5.4F, 0.0F); // legFntRt2Joint
		this.setRotateAngle(legFntRt[legPartNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		this.legFntRtSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legFntRt[legPartNumber][jointNumber]);
		this.legFntRt[legPartNumber][partNumber].setRotationPoint(0.01F, 0.0F, 0.0F); // legFntRt2
		this.legFntRtSitInfo[legPartNumber][partNumber] = new PartInfo(this.legFntRt[legPartNumber][partNumber]);


		this.legFntRtArmorPlate = new ModelRenderer(this, 41, 85);
		this.legFntRtArmorPlate.setRotationPoint(0.0F, 5.0F, -2.0F);
		this.legFntRtArmorPlate.addBox(-2.5F, -2.0F, -1.0F, 5, 6, 2, 0.0F);
		this.legFntRtArmorPlate.setTextureOffset(54, 81).addBox(1.7F, -1.5F, 1.0F, 1, 5, 1, 0.0F); // legFntRtArmorPlateRt
		this.legFntRtArmorPlate.setTextureOffset(38, 81).addBox(-2.7F, -1.5F, 1.0F, 1, 5, 1, 0.0F); // legFntRtArmorPlateLft

		hairNumber = 0;
		this.legFntRtHair[hairNumber] = new ModelRenderer(this, 22, 83); // legFntRtHair1
		this.legFntRtHair[hairNumber].setRotationPoint(0.0F, 6.0F, 1.5F);
		this.legFntRtHair[hairNumber].addBox(-2.5F, -2.0F, -0.5F, 5, 4, 2, 0.0F);
		this.legFntRtHairInfo[hairNumber] = new PartInfo(this.legFntRtHair[hairNumber]);
		hairNumber++;
		this.legFntRtHair[hairNumber] = new ModelRenderer(this, 23, 78); // legFntRtHair2
		this.legFntRtHair[hairNumber].setRotationPoint(0.0F, -0.1F, 1.5F);
		this.legFntRtHair[hairNumber].addBox(-2.0F, -2.0F, -1.0F, 4, 3, 2, 0.0F);
		this.setRotateAngle(legFntRtHair[hairNumber], 0.3839724354387525F, 0.0F, 0.0F);
		this.legFntRtHairInfo[hairNumber] = new PartInfo(this.legFntRtHair[hairNumber]);
		hairNumber++;
		this.legFntRtHair[hairNumber] = new ModelRenderer(this, 25, 73); // legFntRtHair3
		this.legFntRtHair[hairNumber].setRotationPoint(0.0F, -0.7F, 0.9F);
		this.legFntRtHair[hairNumber].addBox(-1.0F, -0.8F, -1.0F, 2, 2, 2, 0.0F);
		this.setRotateAngle(legFntRtHair[hairNumber], 0.3839724354387525F, 0.0F, 0.0F);
		this.legFntRtHairInfo[hairNumber] = new PartInfo(this.legFntRtHair[hairNumber]);
		hairNumber++;
		this.legFntRtHair[hairNumber] = new ModelRenderer(this, 26, 70); // legFntRtHair4
		this.legFntRtHair[hairNumber].setRotationPoint(0.0F, 0.1F, 0.7F);
		this.legFntRtHair[hairNumber].addBox(-0.5F, -0.5F, -0.4F, 1, 1, 2, 0.0F);
		this.setRotateAngle(legFntRtHair[hairNumber], 0.2617993877991494F, 0.0F, 0.0F);
		this.legFntRtHairInfo[hairNumber] = new PartInfo(this.legFntRtHair[hairNumber]);
		this.legFntRtHair3_Lft = new ModelRenderer(this, 30, 74);
		this.legFntRtHair3_Lft.setRotationPoint(1.1F, -0.4F, 0.7F);
		this.legFntRtHair3_Lft.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(legFntRtHair3_Lft, 0.2617993877991494F, 0.2617993877991494F, 0.0F);
		this.legFntRtHair3_Rt = new ModelRenderer(this, 20, 74);
		this.legFntRtHair3_Rt.setRotationPoint(-1.1F, -0.4F, 0.7F);
		this.legFntRtHair3_Rt.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(legFntRtHair3_Rt, 0.2617993877991494F, -0.2617993877991494F, 0.0F);

		legPartNumber = 0;
		this.legBckLft[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legBckLft1Joint
		this.legBckLft[legPartNumber][jointNumber].setRotationPoint(3.5F, 2.0F, 7.0F);
		this.legBckLft[legPartNumber][jointNumber].addBox(-2.0F, -1.8F, -2.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legBckLft[legPartNumber][jointNumber], -0.2617993877991494F, 0.0F, 0.0F);
		this.legBckLftInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckLft[legPartNumber][jointNumber]);
		this.legBckLft[legPartNumber][partNumber] = new ModelRenderer(this, 112, 86); // legBckLft1
		this.legBckLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckLft[legPartNumber][partNumber].addBox(-2.0F, -1.8F, -2.0F, 4, 8, 4, 0.0F);
		this.legBckLftInfo[legPartNumber][partNumber] = new PartInfo(this.legBckLft[legPartNumber][partNumber]);
		legPartNumber++;
		this.legBckLft[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legBckLft2Joint
		this.legBckLft[legPartNumber][jointNumber].setRotationPoint(0.0F, 5.4F, 0.0F);
		this.legBckLft[legPartNumber][jointNumber].addBox(-2.0F, -0.4F, -2.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legBckLft[legPartNumber][jointNumber], 0.6108652381980153F, 0.0F, 0.0F);
		this.legBckLftInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckLft[legPartNumber][jointNumber]);
		this.legBckLft[legPartNumber][partNumber] = new ModelRenderer(this, 112, 98); // legBckLft2
		this.legBckLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckLft[legPartNumber][partNumber].addBox(-2.01F, -0.4F, -2.0F, 4, 6, 4, 0.0F);
		this.legBckLftInfo[legPartNumber][partNumber] = new PartInfo(this.legBckLft[legPartNumber][partNumber]);
		legPartNumber++;
		this.legBckLft[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legBckLft3Joint
		this.legBckLft[legPartNumber][jointNumber].setRotationPoint(0.0F, 5.1F, 0.0F);
		this.legBckLft[legPartNumber][jointNumber].addBox(-2.0F, -0.7F, -2.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legBckLft[legPartNumber][jointNumber], -0.5235987755982988F, 0.0F, 0.0F);
		this.legBckLftInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckLft[legPartNumber][jointNumber]);
		this.legBckLft[legPartNumber][partNumber] = new ModelRenderer(this, 112, 108); // legBckLft3
		this.legBckLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckLft[legPartNumber][partNumber].addBox(-2.0F, -0.7F, -2.0F, 4, 7, 4, 0.0F);
		this.legBckLftInfo[legPartNumber][partNumber] = new PartInfo(this.legBckLft[legPartNumber][partNumber]);

		legPartNumber = 0;
		this.legBckLft[legPartNumber][jointNumber].setRotationPoint(3.5F, 2.0F, 7.0F); // legBckLft1Joint
		this.setRotateAngle(legBckLft[legPartNumber][jointNumber], -1.117010721276371F, -0.2617993877991494F, -0.3490658503988659F);
		this.legBckLftSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckLft[legPartNumber][jointNumber]);
		this.legBckLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legBckLft1
		this.legBckLftSitInfo[legPartNumber][partNumber] = new PartInfo(this.legBckLft[legPartNumber][partNumber]);
		legPartNumber++;
		this.legBckLft[legPartNumber][jointNumber].setRotationPoint(0.0F, 5.4F, 0.0F); // legBckLft2Joint
		this.setRotateAngle(legBckLft[legPartNumber][jointNumber], 1.7453292519943295F, 0.0F, 0.0F);
		this.legBckLftSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckLft[legPartNumber][jointNumber]);
		this.legBckLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legBckLft2
		this.legBckLftSitInfo[legPartNumber][partNumber] = new PartInfo(this.legBckLft[legPartNumber][partNumber]);
		legPartNumber++;
		this.legBckLft[legPartNumber][jointNumber].setRotationPoint(0.0F, 5.1F, 0.0F); // legBckLft3Joint
		this.setRotateAngle(legBckLft[legPartNumber][jointNumber], -1.2566370614359172F, 0.0F, 0.0F);
		this.legBckLftSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckLft[legPartNumber][jointNumber]);
		this.legBckLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legBckLft3
		this.legBckLftSitInfo[legPartNumber][partNumber] = new PartInfo(this.legBckLft[legPartNumber][partNumber]);

		this.legBckLftArmorPlate = new ModelRenderer(this, 95, 100);
		this.legBckLftArmorPlate.setRotationPoint(0.0F, 2.0F, 0.0F);
		this.legBckLftArmorPlate.addBox(-2.5F, -2.0F, -3.0F, 5, 5, 2, 0.0F);
		this.setRotateAngle(legBckLftArmorPlate, 0.0F, -1.5707963267948966F, 0.0F);
		this.legBckLftArmorPlate.setTextureOffset(92, 97).addBox(-3.0F, -1.5F, -1.0F, 1, 4, 1, 0.0F); // legBckLftArmorPlateLft
		this.legBckLftArmorPlate.setTextureOffset(108, 97).addBox(2.0F, -1.5F, -1.0F, 1, 4, 1, 0.0F); // legBckLftArmorPlateRt

		hairNumber = 0;
		this.legBckLftHair[hairNumber] = new ModelRenderer(this, 103, 75); // legBckLftHair1
		this.legBckLftHair[hairNumber].setRotationPoint(-1.0F, 2.0F, 0.0F);
		this.legBckLftHair[hairNumber].addBox(-1.5F, -2.0F, -2.3F, 3, 4, 5, 0.0F);
		this.legBckLftHairInfo[hairNumber] = new PartInfo(this.legBckLftHair[hairNumber]);
		hairNumber++;
		this.legBckLftHair[hairNumber] = new ModelRenderer(this, 120, 79); // legBckLftHair2
		this.legBckLftHair[hairNumber].setRotationPoint(0.0F, 0.0F, 2.5F);
		this.legBckLftHair[hairNumber].addBox(-1.0F, -1.5F, -0.3F, 2, 3, 2, 0.0F);
		this.setRotateAngle(legBckLftHair[hairNumber], 0.3141592653589793F, 0.0F, 0.0F);
		this.legBckLftHairInfo[hairNumber] = new PartInfo(this.legBckLftHair[hairNumber]);

		hairNumber = 0;
		this.legBckLftHair3[hairNumber] = new ModelRenderer(this, 115, 75); // legBckLftHair3_1
		this.legBckLftHair3[hairNumber].setRotationPoint(0.5F, 0.1F, 1.3F);
		this.legBckLftHair3[hairNumber].addBox(-0.5F, -0.5F, -1.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(legBckLftHair3[hairNumber], 0.03490658503988659F, 0.2617993877991494F, 0.0F);
		this.legBckLftHair3Info[hairNumber] = new PartInfo(this.legBckLftHair3[hairNumber]);
		hairNumber++;
		this.legBckLftHair3[hairNumber] = new ModelRenderer(this, 120, 74); // legBckLftHair3_2
		this.legBckLftHair3[hairNumber].setRotationPoint(0.0F, -0.3F, 1.3F);
		this.legBckLftHair3[hairNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, 0.0F);
		this.setRotateAngle(legBckLftHair3[hairNumber], 0.3141592653589793F, 0.0F, 0.0F);
		this.legBckLftHair3Info[hairNumber] = new PartInfo(this.legBckLftHair3[hairNumber]);
		hairNumber++;
		this.legBckLftHair3[hairNumber] = new ModelRenderer(this, 115, 71); // legBckLftHair3_3
		this.legBckLftHair3[hairNumber].setRotationPoint(-0.5F, 0.1F, 1.3F);
		this.legBckLftHair3[hairNumber].addBox(-0.5F, -0.5F, -1.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(legBckLftHair3[hairNumber], 0.03490658503988659F, -0.2617993877991494F, 0.0F);
		this.legBckLftHair3Info[hairNumber] = new PartInfo(this.legBckLftHair3[hairNumber]);

		legPartNumber = 0;
		this.legBckRt[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legBckRt1Joint
		this.legBckRt[legPartNumber][jointNumber].setRotationPoint(-3.5F, 2.0F, 7.0F);
		this.legBckRt[legPartNumber][jointNumber].addBox(-2.0F, -1.8F, -2.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legBckRt[legPartNumber][jointNumber], -0.2617993877991494F, 0.0F, 0.0F);
		this.legBckRtInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckRt[legPartNumber][jointNumber]);
		this.legBckRt[legPartNumber][partNumber] = new ModelRenderer(this, 76, 86); // legBckRt1
		this.legBckRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckRt[legPartNumber][partNumber].addBox(-2.0F, -1.8F, -2.0F, 4, 8, 4, 0.0F);
		this.legBckRtInfo[legPartNumber][partNumber] = new PartInfo(this.legBckRt[legPartNumber][partNumber]);
		legPartNumber++;
		this.legBckRt[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legBckRt2Joint
		this.legBckRt[legPartNumber][jointNumber].setRotationPoint(0.0F, 5.4F, 0.0F);
		this.legBckRt[legPartNumber][jointNumber].addBox(-2.0F, -0.4F, -2.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legBckRt[legPartNumber][jointNumber], 0.6108652381980153F, 0.0F, 0.0F);
		this.legBckRtInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckRt[legPartNumber][jointNumber]);
		this.legBckRt[legPartNumber][partNumber] = new ModelRenderer(this, 76, 98); // legBckRt2
		this.legBckRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckRt[legPartNumber][partNumber].addBox(-2.01F, -0.4F, -2.0F, 4, 6, 4, 0.0F);
		this.legBckRtInfo[legPartNumber][partNumber] = new PartInfo(this.legBckRt[legPartNumber][partNumber]);
		legPartNumber++;
		this.legBckRt[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legBckRt3Joint
		this.legBckRt[legPartNumber][jointNumber].setRotationPoint(0.0F, 5.1F, 0.0F);
		this.legBckRt[legPartNumber][jointNumber].addBox(-2.0F, -0.7F, -2.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legBckRt[legPartNumber][jointNumber], -0.5235987755982988F, 0.0F, 0.0F);
		this.legBckRtInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckRt[legPartNumber][jointNumber]);
		this.legBckRt[legPartNumber][partNumber] = new ModelRenderer(this, 76, 108); // legBckRt3
		this.legBckRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckRt[legPartNumber][partNumber].addBox(-2.0F, -0.7F, -2.0F, 4, 7, 4, 0.0F);
		this.legBckRtInfo[legPartNumber][partNumber] = new PartInfo(this.legBckRt[legPartNumber][partNumber]);

		legPartNumber = 0;
		this.legBckRt[legPartNumber][jointNumber].setRotationPoint(-3.5F, 2.0F, 7.0F); // legBckRt1Joint
		this.setRotateAngle(legBckRt[legPartNumber][jointNumber], -1.117010721276371F, 0.2617993877991494F, 0.3490658503988659F);
		this.legBckRtSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckRt[legPartNumber][jointNumber]);
		this.legBckRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legBckRt1
		this.legBckRtSitInfo[legPartNumber][partNumber] = new PartInfo(this.legBckRt[legPartNumber][partNumber]);
		legPartNumber++;
		this.legBckRt[legPartNumber][jointNumber].setRotationPoint(0.0F, 5.4F, 0.0F); // legBckRt2Joint
		this.setRotateAngle(legBckRt[legPartNumber][jointNumber], 1.7453292519943295F, 0.0F, 0.0F);
		this.legBckRtSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckRt[legPartNumber][jointNumber]);
		this.legBckRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legBckRt2
		this.legBckRtSitInfo[legPartNumber][partNumber] = new PartInfo(this.legBckRt[legPartNumber][partNumber]);
		legPartNumber++;
		this.legBckRt[legPartNumber][jointNumber].setRotationPoint(0.0F, 5.1F, 0.0F); // legBckRt3Joint
		this.setRotateAngle(legBckRt[legPartNumber][jointNumber], -1.2566370614359172F, 0.0F, 0.0F);
		this.legBckRtSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckRt[legPartNumber][jointNumber]);
		this.legBckRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legBckRt3
		this.legBckRtSitInfo[legPartNumber][partNumber] = new PartInfo(this.legBckRt[legPartNumber][partNumber]);

		this.legBckRtArmorPlate = new ModelRenderer(this, 95, 90);
		this.legBckRtArmorPlate.setRotationPoint(0.0F, 2.0F, 0.0F);
		this.legBckRtArmorPlate.addBox(-2.5F, -2.0F, -3.0F, 5, 5, 2, 0.0F);
		this.setRotateAngle(legBckRtArmorPlate, 0.0F, 1.5707963267948966F, 0.0F);
		this.legBckRtArmorPlate.setTextureOffset(92, 87).addBox(-3.0F, -1.5F, -1.0F, 1, 4, 1, 0.0F); // legBckRtArmorPlateLft
		this.legBckRtArmorPlate.setTextureOffset(108, 87).addBox(2.0F, -1.5F, -1.0F, 1, 4, 1, 0.0F); // legBckRtArmorPlateRt

		hairNumber = 0;
		this.legBckRtHair[hairNumber] = new ModelRenderer(this, 85, 75); // legBckRtHair1
		this.legBckRtHair[hairNumber].setRotationPoint(1.0F, 2.0F, 0.0F);
		this.legBckRtHair[hairNumber].addBox(-1.5F, -2.0F, -2.3F, 3, 4, 5, 0.0F);
		this.legBckRtHairInfo[hairNumber] = new PartInfo(this.legBckRtHair[hairNumber]);
		hairNumber++;
		this.legBckRtHair[hairNumber] = new ModelRenderer(this, 76, 79); // legBckRtHair2
		this.legBckRtHair[hairNumber].setRotationPoint(0.0F, 0.0F, 2.5F);
		this.legBckRtHair[hairNumber].addBox(-1.0F, -1.5F, -0.3F, 2, 3, 2, 0.0F);
		this.setRotateAngle(legBckRtHair[hairNumber], 0.3141592653589793F, 0.0F, 0.0F);
		this.legBckRtHairInfo[hairNumber] = new PartInfo(this.legBckRtHair[hairNumber]);

		hairNumber = 0;
		this.legBckRtHair3[hairNumber] = new ModelRenderer(this, 81, 75); // legBckRtHair3_1
		this.legBckRtHair3[hairNumber].setRotationPoint(0.5F, 0.1F, 1.3F);
		this.legBckRtHair3[hairNumber].addBox(-0.5F, -0.5F, -1.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(legBckRtHair3[hairNumber], 0.03490658503988659F, 0.2617993877991494F, 0.0F);
		this.legBckRtHair3Info[hairNumber] = new PartInfo(this.legBckRtHair3[hairNumber]);
		hairNumber++;
		this.legBckRtHair3[hairNumber] = new ModelRenderer(this, 76, 74); // legBckRtHair3_2
		this.legBckRtHair3[hairNumber].setRotationPoint(0.0F, -0.3F, 1.3F);
		this.legBckRtHair3[hairNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, 0.0F);
		this.setRotateAngle(legBckRtHair3[hairNumber], 0.3141592653589793F, 0.0F, 0.0F);
		this.legBckRtHair3Info[hairNumber] = new PartInfo(this.legBckRtHair3[hairNumber]);
		hairNumber++;
		this.legBckRtHair3[hairNumber] = new ModelRenderer(this, 81, 71); // legBckRtHair3_3
		this.legBckRtHair3[hairNumber].setRotationPoint(-0.5F, 0.1F, 1.3F);
		this.legBckRtHair3[hairNumber].addBox(-0.5F, -0.5F, -1.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(legBckRtHair3[hairNumber], 0.03490658503988659F, -0.2617993877991494F, 0.0F);
		this.legBckRtHair3Info[hairNumber] = new PartInfo(this.legBckRtHair3[hairNumber]);

		this.gunLftBase = new ModelRenderer(this, 38, 0);
		this.gunLftBase.setRotationPoint(5.0F, 1.0F, 6.0F);
		this.gunLftBase.addBox(-4.5F, -3.0F, -4.5F, 9, 4, 9, 0.0F);
		this.setRotateAngle(gunLftBase, 0.0F, 0.0F, 1.3089969389957472F);
		this.gunLftAimingPivot = new ModelRenderer(this, 66, -1);
		this.gunLftAimingPivot.setRotationPoint(-1.5F, -3.0F, 0.0F);
		this.gunLftAimingPivot.addBox(0.0F, -2.0F, -2.0F, 2, 4, 4, 0.0F);
		this.setRotateAngle(gunLftAimingPivot, 0.0F, 0.0F, -1.5707963267948966F);
		this.gunLftAimingPivotInfo = new PartInfo(gunLftAimingPivot);
		this.gunLftBaseJoint = new ModelRenderer(this, 0, 0);
		this.gunLftBaseJoint.setRotationPoint(2.0F, 0.0F, 0.0F);
		this.gunLftBaseJoint.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(gunLftBaseJoint, 0.0F, 0.0F, 1.5707963267948966F);
		this.gunLftBaseJointInfo = new PartInfo(gunLftBaseJoint);
		this.gunLftMain = new ModelRenderer(this, 16, 5);
		this.gunLftMain.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.gunLftMain.addBox(-3.5F, -4.0F, -4.0F, 7, 4, 8, 0.0F);
		this.gunLftMainInfo = new PartInfo(gunLftMain);
		this.gunLftPlate = new ModelRenderer(this, 7, 16); // gunLftPlate1
		this.gunLftPlate.setRotationPoint(0.0F, -3.0F, -3.0F);
		this.gunLftPlate.addBox(-1.0F, -1.8F, -8.0F, 2, 1, 2, 0.0F);
		this.gunLftPlate.setTextureOffset(6, 13).addBox(-1.5F, -1.8F, -6.0F, 3, 1, 2, 0.0F); // gunLftPlate2
		this.gunLftPlate.setTextureOffset(0, 3).addBox(-2.0F, -2.5F, -4.0F, 4, 2, 8, 0.0F); // gunLftPlate3
		this.gunLftPlate.setTextureOffset(7, 0).addBox(-1.5F, -2.0F, 4.0F, 3, 1, 2, 0.0F); // gunLftPlate4

		this.gunLftBarrelBrace = new ModelRenderer(this, 18, 17);
		this.gunLftBarrelBrace.setRotationPoint(0.0F, -2.0F, -14.0F);
		this.gunLftBarrelBrace.addBox(-3.5F, -1.5F, -2.0F, 7, 3, 4, 0.0F);
		this.gunLftBarrelBraceInfo = new PartInfo(gunLftBarrelBrace);

		int gunBarrelNumber = 0;
		this.gunLftBarrelRt[gunBarrelNumber] = new ModelRenderer(this, 39, 26); // gunLftBarrelRt1
		this.gunLftBarrelRt[gunBarrelNumber].setRotationPoint(-2.0F, -2.0F, -4.0F);
		this.gunLftBarrelRt[gunBarrelNumber].addBox(-1.01F, -1.01F, -3.0F, 2, 2, 3, 0.0F);
		this.gunLftBarrelRtInfo[gunBarrelNumber] = new PartInfo(this.gunLftBarrelRt[gunBarrelNumber]);
		gunBarrelNumber++;
		this.gunLftBarrelRt[gunBarrelNumber] = new ModelRenderer(this, 32, 24); // gunLftBarrelRt2
		this.gunLftBarrelRt[gunBarrelNumber].setRotationPoint(0.0F, 0.0F, -3.0F);
		this.gunLftBarrelRt[gunBarrelNumber].addBox(-1.0F, -1.0F, -3.0F, 2, 2, 3, 0.0F);
		this.gunLftBarrelRtInfo[gunBarrelNumber] = new PartInfo(this.gunLftBarrelRt[gunBarrelNumber]);
		gunBarrelNumber++;
		this.gunLftBarrelRt[gunBarrelNumber] = new ModelRenderer(this, 25, 26); // gunLftBarrelRt3
		this.gunLftBarrelRt[gunBarrelNumber].setRotationPoint(0.0F, 0.0F, -3.0F);
		this.gunLftBarrelRt[gunBarrelNumber].addBox(-1.01F, -1.01F, -3.0F, 2, 2, 3, 0.0F);
		this.gunLftBarrelRtInfo[gunBarrelNumber] = new PartInfo(this.gunLftBarrelRt[gunBarrelNumber]);
		gunBarrelNumber++;
		this.gunLftBarrelRt[gunBarrelNumber] = new ModelRenderer(this, 18, 24); // gunLftBarrelRt4
		this.gunLftBarrelRt[gunBarrelNumber].setRotationPoint(0.0F, 0.0F, -3.0F);
		this.gunLftBarrelRt[gunBarrelNumber].addBox(-1.0F, -1.0F, -3.0F, 2, 2, 3, 0.0F);
		this.gunLftBarrelRtInfo[gunBarrelNumber] = new PartInfo(this.gunLftBarrelRt[gunBarrelNumber]);
		gunBarrelNumber++;
		this.gunLftBarrelRt[gunBarrelNumber] = new ModelRenderer(this, 11, 26); // gunLftBarrelRt5
		this.gunLftBarrelRt[gunBarrelNumber].setRotationPoint(0.0F, 0.0F, -3.0F);
		this.gunLftBarrelRt[gunBarrelNumber].addBox(-1.0F, -1.0F, -3.0F, 2, 2, 3, 0.0F);
		this.gunLftBarrelRtInfo[gunBarrelNumber] = new PartInfo(this.gunLftBarrelRt[gunBarrelNumber]);
		gunBarrelNumber++;
		this.gunLftBarrelRt[gunBarrelNumber] = new ModelRenderer(this, 0, 22); // gunLftBarrelRt6
		this.gunLftBarrelRt[gunBarrelNumber].setRotationPoint(0.0F, 0.0F, -3.0F);
		this.gunLftBarrelRt[gunBarrelNumber].addBox(-1.5F, -1.5F, -4.0F, 3, 3, 4, 0.0F);
		this.gunLftBarrelRtInfo[gunBarrelNumber] = new PartInfo(this.gunLftBarrelRt[gunBarrelNumber]);

		gunBarrelNumber = 0;
		this.gunLftBarrelLft[gunBarrelNumber] = new ModelRenderer(this, 39, 26); // gunLftBarrelLft1
		this.gunLftBarrelLft[gunBarrelNumber].setRotationPoint(2.0F, -2.0F, -4.0F);
		this.gunLftBarrelLft[gunBarrelNumber].addBox(-1.01F, -1.01F, -3.0F, 2, 2, 3, 0.0F);
		this.gunLftBarrelLftInfo[gunBarrelNumber] = new PartInfo(this.gunLftBarrelLft[gunBarrelNumber]);
		gunBarrelNumber++;
		this.gunLftBarrelLft[gunBarrelNumber] = new ModelRenderer(this, 32, 24); // gunLftBarrelLft2
		this.gunLftBarrelLft[gunBarrelNumber].setRotationPoint(0.0F, 0.0F, -3.0F);
		this.gunLftBarrelLft[gunBarrelNumber].addBox(-1.0F, -1.0F, -3.0F, 2, 2, 3, 0.0F);
		this.gunLftBarrelLftInfo[gunBarrelNumber] = new PartInfo(this.gunLftBarrelLft[gunBarrelNumber]);
		gunBarrelNumber++;
		this.gunLftBarrelLft[gunBarrelNumber] = new ModelRenderer(this, 25, 26); // gunLftBarrelLft3
		this.gunLftBarrelLft[gunBarrelNumber].setRotationPoint(0.0F, 0.0F, -3.0F);
		this.gunLftBarrelLft[gunBarrelNumber].addBox(-1.01F, -1.01F, -3.0F, 2, 2, 3, 0.0F);
		this.gunLftBarrelLftInfo[gunBarrelNumber] = new PartInfo(this.gunLftBarrelLft[gunBarrelNumber]);
		gunBarrelNumber++;
		this.gunLftBarrelLft[gunBarrelNumber] = new ModelRenderer(this, 18, 24); // gunLftBarrelLft4
		this.gunLftBarrelLft[gunBarrelNumber].setRotationPoint(0.0F, 0.0F, -3.0F);
		this.gunLftBarrelLft[gunBarrelNumber].addBox(-1.0F, -1.0F, -3.0F, 2, 2, 3, 0.0F);
		this.gunLftBarrelLftInfo[gunBarrelNumber] = new PartInfo(this.gunLftBarrelLft[gunBarrelNumber]);
		gunBarrelNumber++;
		this.gunLftBarrelLft[gunBarrelNumber] = new ModelRenderer(this, 11, 26); // gunLftBarrelLft5
		this.gunLftBarrelLft[gunBarrelNumber].setRotationPoint(0.0F, 0.0F, -3.0F);
		this.gunLftBarrelLft[gunBarrelNumber].addBox(-1.0F, -1.0F, -3.0F, 2, 2, 3, 0.0F);
		this.gunLftBarrelLftInfo[gunBarrelNumber] = new PartInfo(this.gunLftBarrelLft[gunBarrelNumber]);
		gunBarrelNumber++;
		this.gunLftBarrelLft[gunBarrelNumber] = new ModelRenderer(this, 0, 22); // gunLftBarrelLft6
		this.gunLftBarrelLft[gunBarrelNumber].setRotationPoint(0.0F, 0.0F, -3.0F);
		this.gunLftBarrelLft[gunBarrelNumber].addBox(-1.5F, -1.5F, -4.0F, 3, 3, 4, 0.0F);
		this.gunLftBarrelLftInfo[gunBarrelNumber] = new PartInfo(this.gunLftBarrelLft[gunBarrelNumber]);

		this.gunRtBase = new ModelRenderer(this, 38, 0);
		this.gunRtBase.setRotationPoint(-5.0F, 1.0F, 6.0F);
		this.gunRtBase.addBox(-4.5F, -3.0F, -4.5F, 9, 4, 9, 0.0F);
		this.setRotateAngle(gunRtBase, 0.0F, 0.0F, -1.3089969389957472F);
		this.gunRtAimingPivot = new ModelRenderer(this, 66, -1);
		this.gunRtAimingPivot.setRotationPoint(1.5F, -3.0F, 0.0F);
		this.gunRtAimingPivot.addBox(-2.0F, -2.0F, -2.0F, 2, 4, 4, 0.0F);
		this.setRotateAngle(gunRtAimingPivot, 0.0F, 0.0F, 1.5707963267948966F);
		this.gunRtAimingPivotInfo = new PartInfo(gunRtAimingPivot);
		this.gunRtBaseJoint = new ModelRenderer(this, 0, 0);
		this.gunRtBaseJoint.setRotationPoint(-2.0F, 0.0F, 0.0F);
		this.gunRtBaseJoint.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(gunRtBaseJoint, 0.0F, 0.0F, -1.5707963267948966F);
		this.gunRtBaseJointInfo = new PartInfo(gunRtBaseJoint);
		this.gunRtMain = new ModelRenderer(this, 16, 5);
		this.gunRtMain.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.gunRtMain.addBox(-3.5F, -4.0F, -4.0F, 7, 4, 8, 0.0F);
		this.gunRtMainInfo = new PartInfo(gunRtMain);
		this.gunRtPlate = new ModelRenderer(this, 7, 16); // gunRtPlate1
		this.gunRtPlate.setRotationPoint(0.0F, -3.0F, -3.0F);
		this.gunRtPlate.addBox(-1.0F, -1.8F, -8.0F, 2, 1, 2, 0.0F);
		this.gunRtPlate.setTextureOffset(6, 13).addBox(-1.5F, -1.8F, -6.0F, 3, 1, 2, 0.0F); // gunRtPlate2
		this.gunRtPlate.setTextureOffset(0, 3).addBox(-2.0F, -2.5F, -4.0F, 4, 2, 8, 0.0F); // gunRtPlate3
		this.gunRtPlate.setTextureOffset(7, 0).addBox(-1.5F, -2.0F, 4.0F, 3, 1, 2, 0.0F); // gunRtPlate4

		this.gunRtBarrelBrace = new ModelRenderer(this, 18, 17);
		this.gunRtBarrelBrace.setRotationPoint(0.0F, -2.0F, -14.0F);
		this.gunRtBarrelBrace.addBox(-3.5F, -1.5F, -2.0F, 7, 3, 4, 0.0F);
		this.gunRtBarrelBraceInfo = new PartInfo(gunRtBarrelBrace);

		gunBarrelNumber = 0;
		this.gunRtBarrelRt[gunBarrelNumber] = new ModelRenderer(this, 39, 26); // gunRtBarrelRt1
		this.gunRtBarrelRt[gunBarrelNumber].setRotationPoint(-2.0F, -2.0F, -4.0F);
		this.gunRtBarrelRt[gunBarrelNumber].addBox(-1.01F, -1.01F, -3.0F, 2, 2, 3, 0.0F);
		this.gunRtBarrelRtInfo[gunBarrelNumber] = new PartInfo(this.gunRtBarrelRt[gunBarrelNumber]);
		gunBarrelNumber++;
		this.gunRtBarrelRt[gunBarrelNumber] = new ModelRenderer(this, 32, 24); // gunRtBarrelRt2
		this.gunRtBarrelRt[gunBarrelNumber].setRotationPoint(0.0F, 0.0F, -3.0F);
		this.gunRtBarrelRt[gunBarrelNumber].addBox(-1.0F, -1.0F, -3.0F, 2, 2, 3, 0.0F);
		this.gunRtBarrelRtInfo[gunBarrelNumber] = new PartInfo(this.gunRtBarrelRt[gunBarrelNumber]);
		gunBarrelNumber++;
		this.gunRtBarrelRt[gunBarrelNumber] = new ModelRenderer(this, 25, 26); // gunRtBarrelRt3
		this.gunRtBarrelRt[gunBarrelNumber].setRotationPoint(0.0F, 0.0F, -3.0F);
		this.gunRtBarrelRt[gunBarrelNumber].addBox(-1.01F, -1.01F, -3.0F, 2, 2, 3, 0.0F);
		this.gunRtBarrelRtInfo[gunBarrelNumber] = new PartInfo(this.gunRtBarrelRt[gunBarrelNumber]);
		gunBarrelNumber++;
		this.gunRtBarrelRt[gunBarrelNumber] = new ModelRenderer(this, 18, 24); // gunRtBarrelRt4
		this.gunRtBarrelRt[gunBarrelNumber].setRotationPoint(0.0F, 0.0F, -3.0F);
		this.gunRtBarrelRt[gunBarrelNumber].addBox(-1.0F, -1.0F, -3.0F, 2, 2, 3, 0.0F);
		this.gunRtBarrelRtInfo[gunBarrelNumber] = new PartInfo(this.gunRtBarrelRt[gunBarrelNumber]);
		gunBarrelNumber++;
		this.gunRtBarrelRt[gunBarrelNumber] = new ModelRenderer(this, 11, 26); // gunRtBarrelRt5
		this.gunRtBarrelRt[gunBarrelNumber].setRotationPoint(0.0F, 0.0F, -3.0F);
		this.gunRtBarrelRt[gunBarrelNumber].addBox(-1.0F, -1.0F, -3.0F, 2, 2, 3, 0.0F);
		this.gunRtBarrelRtInfo[gunBarrelNumber] = new PartInfo(this.gunRtBarrelRt[gunBarrelNumber]);
		gunBarrelNumber++;
		this.gunRtBarrelRt[gunBarrelNumber] = new ModelRenderer(this, 0, 22); // gunRtBarrelRt6
		this.gunRtBarrelRt[gunBarrelNumber].setRotationPoint(0.0F, 0.0F, -3.0F);
		this.gunRtBarrelRt[gunBarrelNumber].addBox(-1.5F, -1.5F, -4.0F, 3, 3, 4, 0.0F);
		this.gunRtBarrelRtInfo[gunBarrelNumber] = new PartInfo(this.gunRtBarrelRt[gunBarrelNumber]);

		gunBarrelNumber = 0;
		this.gunRtBarrelLft[gunBarrelNumber] = new ModelRenderer(this, 39, 26); // gunRtBarrelLft1
		this.gunRtBarrelLft[gunBarrelNumber].setRotationPoint(2.0F, -2.0F, -4.0F);
		this.gunRtBarrelLft[gunBarrelNumber].addBox(-1.01F, -1.01F, -3.0F, 2, 2, 3, 0.0F);
		this.gunRtBarrelLftInfo[gunBarrelNumber] = new PartInfo(this.gunRtBarrelLft[gunBarrelNumber]);
		gunBarrelNumber++;
		this.gunRtBarrelLft[gunBarrelNumber] = new ModelRenderer(this, 32, 24); // gunRtBarrelLft2
		this.gunRtBarrelLft[gunBarrelNumber].setRotationPoint(0.0F, 0.0F, -3.0F);
		this.gunRtBarrelLft[gunBarrelNumber].addBox(-1.0F, -1.0F, -3.0F, 2, 2, 3, 0.0F);
		this.gunRtBarrelLftInfo[gunBarrelNumber] = new PartInfo(this.gunRtBarrelLft[gunBarrelNumber]);
		gunBarrelNumber++;
		this.gunRtBarrelLft[gunBarrelNumber] = new ModelRenderer(this, 25, 26); // gunRtBarrelLft3
		this.gunRtBarrelLft[gunBarrelNumber].setRotationPoint(0.0F, 0.0F, -3.0F);
		this.gunRtBarrelLft[gunBarrelNumber].addBox(-1.01F, -1.01F, -3.0F, 2, 2, 3, 0.0F);
		this.gunRtBarrelLftInfo[gunBarrelNumber] = new PartInfo(this.gunRtBarrelLft[gunBarrelNumber]);
		gunBarrelNumber++;
		this.gunRtBarrelLft[gunBarrelNumber] = new ModelRenderer(this, 18, 24); // gunRtBarrelLft4
		this.gunRtBarrelLft[gunBarrelNumber].setRotationPoint(0.0F, 0.0F, -3.0F);
		this.gunRtBarrelLft[gunBarrelNumber].addBox(-1.0F, -1.0F, -3.0F, 2, 2, 3, 0.0F);
		this.gunRtBarrelLftInfo[gunBarrelNumber] = new PartInfo(this.gunRtBarrelLft[gunBarrelNumber]);
		gunBarrelNumber++;
		this.gunRtBarrelLft[gunBarrelNumber] = new ModelRenderer(this, 11, 26); // gunRtBarrelLft5
		this.gunRtBarrelLft[gunBarrelNumber].setRotationPoint(0.0F, 0.0F, -3.0F);
		this.gunRtBarrelLft[gunBarrelNumber].addBox(-1.0F, -1.0F, -3.0F, 2, 2, 3, 0.0F);
		this.gunRtBarrelLftInfo[gunBarrelNumber] = new PartInfo(this.gunRtBarrelLft[gunBarrelNumber]);
		gunBarrelNumber++;
		this.gunRtBarrelLft[gunBarrelNumber] = new ModelRenderer(this, 0, 22); // gunRtBarrelLft6
		this.gunRtBarrelLft[gunBarrelNumber].setRotationPoint(0.0F, 0.0F, -3.0F);
		this.gunRtBarrelLft[gunBarrelNumber].addBox(-1.5F, -1.5F, -4.0F, 3, 3, 4, 0.0F);
		this.gunRtBarrelLftInfo[gunBarrelNumber] = new PartInfo(this.gunRtBarrelLft[gunBarrelNumber]);

		int tailNumber = 0;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail1Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, -1.0F, 9.5F);
		this.tail[tailNumber][jointNumber].addBox(-3.0F, -3.0F, -2.0F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 75, 3); // tail1
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-3.0F, -3.0F, -2.0F, 6, 6, 6, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail2Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 3.5F);
		this.tail[tailNumber][jointNumber].addBox(-4.0F, -4.0F, -1.5F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 100, 6); // tail2
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-4.0F, -4.0F, -1.5F, 8, 8, 4, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail3Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.0F);
		this.tail[tailNumber][jointNumber].addBox(-5.0F, -5.0F, -1.0F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 71, 16); // tail3
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-5.0F, -5.0F, -1.0F, 10, 10, 4, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail4Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.0F);
		this.tail[tailNumber][jointNumber].addBox(-5.0F, -5.0F, -1.0F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 100, 19); // tail4
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-5.0F, -5.0F, -1.0F, 10, 10, 4, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail5Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.0F);
		this.tail[tailNumber][jointNumber].addBox(-5.0F, -5.0F, -1.0F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 71, 31); // tail5
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-5.0F, -5.0F, -1.0F, 10, 10, 4, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail6Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.0F);
		this.tail[tailNumber][jointNumber].addBox(-5.0F, -5.0F, -1.0F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 100, 34); // tail6
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-5.0F, -5.0F, -1.0F, 10, 10, 4, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail7Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.0F);
		this.tail[tailNumber][jointNumber].addBox(-4.0F, -4.0F, -1.0F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 76, 46); // tail7
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-4.0F, -4.0F, -1.0F, 8, 8, 4, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail8Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.0F);
		this.tail[tailNumber][jointNumber].addBox(-4.0F, -4.0F, -0.5F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 101, 49); // tail8
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-4.0F, -4.0F, -0.5F, 8, 8, 4, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail9Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.0F);
		this.tail[tailNumber][jointNumber].addBox(-3.0F, -3.0F, -0.5F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 84, 59); // tail9
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-3.0F, -3.0F, -0.5F, 6, 6, 4, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail10Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.0F);
		this.tail[tailNumber][jointNumber].addBox(-2.0F, -2.0F, -0.5F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 105, 62); // tail10
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-2.0F, -2.0F, -0.5F, 4, 4, 4, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail11Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.0F);
		this.tail[tailNumber][jointNumber].addBox(-1.0F, -1.0F, 1.0F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 97, 70); // tail11
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-1.0F, -1.0F, 1.0F, 2, 2, 3, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);

		this.tail[0][0].setRotationPoint(0.0F, -1.0F, 9.5F); // tail1Joint
		this.setRotateAngle(tail[0][0], 0.8726646259971648F, 0.0F, 0.0F);
		this.tailSitInfo = new PartInfo(this.tail[0][0]);

		this.bodyJoint.addChild(this.bodyBack);
		this.bodyJoint.addChild(this.bodyFront);
		this.bodyJoint.addChild(this.bodyMidTopArmor);

		this.bodyFront.addChild(this.bodyFntTopArmor);
		this.bodyFront.addChild(this.chestPlate);
		this.bodyFront.addChild(this.legFntLft[0][0]);
		this.bodyFront.addChild(this.legFntRt[0][0]);
		this.bodyFront.addChild(this.neckJoint);

		this.bodyBack.addChild(this.bodyBckTopArmor);
		this.bodyBack.addChild(this.gunLftBase);
		this.bodyBack.addChild(this.gunRtBase);
		this.bodyBack.addChild(this.legBckLft[0][0]);
		this.bodyBack.addChild(this.legBckRt[0][0]);
		this.bodyBack.addChild(this.tail[0][0]);

		this.neckJoint.addChild(this.neck);
		this.neck.addChild(this.headJoint);
		this.headJoint.addChild(this.head);

		this.head.addChild(this.earLftJoint);
		this.head.addChild(this.earRtJoint);
		this.head.addChild(this.headHair[0]);
		this.head.addChild(this.helmet);
		this.head.addChild(this.jaw);
		this.head.addChild(this.muzzle);

		this.earLftJoint.addChild(this.earLft);
		this.earRtJoint.addChild(this.earRt);

		this.helmet.addChild(this.helmetFnt);
		this.helmet.addChild(this.helmetFntTop);
		this.helmet.addChild(this.helmetHorn1);
		this.helmet.addChild(this.helmetSideLft1);
		this.helmet.addChild(this.helmetSideRt1);
		this.helmetHorn1.addChild(this.helmetHorn2);
		this.helmetHorn2.addChild(this.helmetHorn3);
		this.helmetSideLft1.addChild(this.helmetSideLft2);
		this.helmetSideLft2.addChild(this.helmetSideLft3);
		this.helmetSideRt1.addChild(this.helmetSideRt2);
		this.helmetSideRt2.addChild(this.helmetSideRt3);

		this.headHair[1].addChild(this.headHair[1]);

		this.gunLftBase.addChild(this.gunLftAimingPivot);
		this.gunLftAimingPivot.addChild(this.gunLftBaseJoint);
		this.gunLftBaseJoint.addChild(this.gunLftMain);
		this.gunLftMain.addChild(this.gunLftPlate);

		this.gunRtBase.addChild(this.gunRtAimingPivot);
		this.gunRtAimingPivot.addChild(this.gunRtBaseJoint);
		this.gunRtBaseJoint.addChild(this.gunRtMain);
		this.gunRtMain.addChild(this.gunRtPlate);

		this.gunRtMain.addChild(this.gunRtBarrelLft[0]);
		this.gunRtMain.addChild(this.gunRtBarrelRt[0]);
		this.gunRtMain.addChild(this.gunRtBarrelBrace);
		this.gunLftMain.addChild(this.gunLftBarrelLft[0]);
		this.gunLftMain.addChild(this.gunLftBarrelRt[0]);
		this.gunLftMain.addChild(this.gunLftBarrelBrace);

		for(int i = 0; i < gunLftBarrelLft.length - 1; i++)
		{
			this.gunLftBarrelLft[i].addChild(this.gunLftBarrelLft[i + 1]);
			this.gunLftBarrelRt[i].addChild(this.gunLftBarrelRt[i + 1]);
			this.gunRtBarrelLft[i].addChild(this.gunRtBarrelLft[i + 1]);
			this.gunRtBarrelRt[i].addChild(this.gunRtBarrelRt[i + 1]);
		}

		this.legBckLft[0][0].addChild(this.legBckLft[0][1]);
		this.legBckLft[0][1].addChild(this.legBckLft[1][0]);
		this.legBckLft[1][0].addChild(this.legBckLft[1][1]);
		this.legBckLft[1][1].addChild(this.legBckLft[2][0]);
		this.legBckLft[2][0].addChild(this.legBckLft[2][1]);

		this.legBckLft[2][1].addChild(this.legBckLftArmorPlate);
		this.legBckLft[2][1].addChild(this.legBckLftHair[0]);
		this.legBckLftHair[0].addChild(this.legBckLftHair[1]);
		this.legBckLftHair[1].addChild(this.legBckLftHair3[0]);
		this.legBckLftHair[1].addChild(this.legBckLftHair3[1]);
		this.legBckLftHair[1].addChild(this.legBckLftHair3[2]);

		this.legBckRt[0][0].addChild(this.legBckRt[0][1]);
		this.legBckRt[0][1].addChild(this.legBckRt[1][0]);
		this.legBckRt[1][0].addChild(this.legBckRt[1][1]);
		this.legBckRt[1][1].addChild(this.legBckRt[2][0]);
		this.legBckRt[2][0].addChild(this.legBckRt[2][1]);

		this.legBckRt[2][1].addChild(this.legBckRtArmorPlate);
		this.legBckRt[2][1].addChild(this.legBckRtHair[0]);
		this.legBckRtHair[0].addChild(this.legBckRtHair[1]);
		this.legBckRtHair[1].addChild(this.legBckRtHair3[0]);
		this.legBckRtHair[1].addChild(this.legBckRtHair3[1]);
		this.legBckRtHair[1].addChild(this.legBckRtHair3[2]);

		this.legFntLft[0][0].addChild(this.legFntLft[0][1]);
		this.legFntLft[0][1].addChild(this.legFntLft[1][0]);
		this.legFntLft[1][0].addChild(this.legFntLft[1][1]);

		this.legFntLft[1][1].addChild(this.legFntLftArmorPlate);
		this.legFntLft[1][1].addChild(this.legFntLftHair[0]);
		this.legFntLftHair[0].addChild(this.legFntLftHair[1]);
		this.legFntLftHair[1].addChild(this.legFntLftHair[2]);
		this.legFntLftHair[1].addChild(this.legFntLftHair3_Lft);
		this.legFntLftHair[1].addChild(this.legFntLftHair3_Rt);
		this.legFntLftHair[2].addChild(this.legFntLftHair[3]);

		this.legFntRt[0][0].addChild(this.legFntRt[0][1]);
		this.legFntRt[0][1].addChild(this.legFntRt[1][0]);
		this.legFntRt[1][0].addChild(this.legFntRt[1][1]);

		this.legFntRt[1][1].addChild(this.legFntRtArmorPlate);
		this.legFntRt[1][1].addChild(this.legFntRtHair[0]);
		this.legFntRtHair[0].addChild(this.legFntRtHair[1]);
		this.legFntRtHair[1].addChild(this.legFntRtHair[2]);
		this.legFntRtHair[1].addChild(this.legFntRtHair3_Lft);
		this.legFntRtHair[1].addChild(this.legFntRtHair3_Rt);
		this.legFntRtHair[2].addChild(this.legFntRtHair[3]);

		for(int i = 0; i < tail.length - 1; i++)
		{
			this.tail[i][0].addChild(this.tail[i][1]);
			this.tail[i][1].addChild(this.tail[i + 1][0]);
		}

		this.tail[tail.length - 1][0].addChild(this.tail[tail.length - 1][1]);

		animationDeployer = new AnimationDeployer(this);
	}

	@Override
	public void render(Entity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelScale)
	{
		animate(entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, modelScale);

		this.bodyJoint.render(modelScale);
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) 
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void animate(Entity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize)
	{	
		this.animationDeployer.update((IAnimatedEntity)entity);
		resetPartInfos();

		float angularVelocity = ((IMobMotionTracker)entity).getAngularVelocity();
		float verticleVelocity = (float)((IMobMotionTracker)entity).getHeightVelocity();
		float totalChangeInYaw = (float)((IMobMotionTracker)entity).getTotalAngularChange();

		float idleDampener = 1F;

		if(!((EntityImmortalArcanine)entity).isSitting())
		{
			if(animationDeployer.getEntity().getAnimationID() == LibraryImmortalArcanineAttackID.SHOOT)
			{
				idleDampener = animateShoot(animationDeployer.getEntity(), distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, modelSize);
			}
		}

		animateBody((EntityImmortalArcanine)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, modelSize, idleDampener, angularVelocity, verticleVelocity);
		animateHead((EntityImmortalArcanine)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, modelSize, idleDampener, verticleVelocity);
		animateLegs((EntityImmortalArcanine)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, modelSize, idleDampener, angularVelocity, verticleVelocity, totalChangeInYaw);
		animateTail((EntityImmortalArcanine)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, modelSize, idleDampener, angularVelocity, verticleVelocity);

		animateCannons((EntityImmortalArcanine)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, modelSize, idleDampener);

		deployAnimations();
	}

	public void resetPartInfos()
	{
		bodyJointInfo.resetNewAngles();

		bodyJointInfo.resetNewAngles();
		bodyJointInfo.resetNewPnt();

		neckJointInfo.resetNewAngles();
		neckInfo.resetNewAngles();
		headJointInfo.resetNewAngles();
		headInfo.resetNewAngles();

		earRtJointInfo.resetNewAngles();
		earLftJointInfo.resetNewAngles();

		gunRtAimingPivotInfo.resetNewAngles();
		gunLftAimingPivotInfo.resetNewAngles();

		gunRtBaseJointInfo.resetNewAngles();
		gunLftBaseJointInfo.resetNewAngles();

		for(int i = 0; i < gunRtBarrelRtInfo.length; i++)
		{
			gunRtBarrelRtInfo[i].resetNewPnt();
			gunRtBarrelLftInfo[i].resetNewPnt();
			gunLftBarrelRtInfo[i].resetNewPnt();
			gunLftBarrelLftInfo[i].resetNewPnt();
		}

		gunLftBarrelBraceInfo.resetNewPnt();
		gunRtBarrelBraceInfo.resetNewPnt();

		for(int i = 0; i < legFntRtInfo.length; i++)
		{
			legFntRtInfo[i][0].resetNewAngles();
			legFntRtInfo[i][1].resetNewAngles();
			legFntLftInfo[i][0].resetNewAngles();
			legFntLftInfo[i][1].resetNewAngles();
		}

		for(int i = 0; i < legBckRtInfo.length; i++)
		{
			legBckRtInfo[i][0].resetNewAngles();
			legBckRtInfo[i][1].resetNewAngles();
			legBckLftInfo[i][0].resetNewAngles();
			legBckLftInfo[i][1].resetNewAngles();
		}

		for(int i = 0; i < tailInfo.length; i++)
		{
			for(int j = 0; j < tailInfo[i].length; j++)
			{
				tailInfo[i][j].resetNewAngles();
			}
		}

	}

	public float animateShoot(IAnimatedEntity entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize)
	{
		float idleDampener = 1F;

		float tick = animationDeployer.getEntity().getAnimationTick() + KindredLegacyMain.proxy.getPartialTick();

		float recoilEnd = 3;
		float actionEnd = 14;

		float bodyRecoilEnd = 7;

		if(tick < recoilEnd && tick > 0)
		{
			idleDampener = (1F + (float)MathHelper.cos(PI*(tick/recoilEnd)));
		}
		else if(tick < actionEnd)
		{
			idleDampener = (1F + (float)MathHelper.cos(PI + PI * ((tick - recoilEnd)/(actionEnd - recoilEnd))));
		}

		if(idleDampener > 1F)
		{
			idleDampener = 1F;
		}

		//Vector3f bodyAngleChanges = new Vector3f();
		Vector3f barrelPointChanges = new Vector3f();
		Vector3f barrelBracePointChanges = new Vector3f();

		Vector3f bodyPointChanges = new Vector3f();
		Vector3f bodyAngleChanges = new Vector3f();

		Vector3f legAngleChanges = new Vector3f();

		Vector3f neckAngleChanges = new Vector3f();

		Vector3f earsAngleChanges = new Vector3f();

		Vector3f tailAngleChanges = new Vector3f();

		//Vector3f bodyRaiseAngleChanges = new Vector3f(0F, (float)Math.toRadians(-40), (float)Math.toRadians(15));
		Vector3f barrelRecoilPointChanges = new Vector3f(0F, 0F, 1.5F);
		Vector3f barrelBraceRecoilPointChanges = new Vector3f(0F, 0F, 6.0F);

		Vector3f bodyRecoilPointChanges = new Vector3f(0F, 2.5F, -2.5F);
		Vector3f bodyRecoilAngleChanges = new Vector3f((float)Math.toRadians(7), 0F, 0F);

		Vector3f legRecoilAngleChanges = new Vector3f((float)Math.toRadians(30), 0F, 0F);

		Vector3f neckRecoilAngleChanges = new Vector3f((float)Math.toRadians(45), 0F, 0F);

		Vector3f earsRecoilAngleChanges = new Vector3f(0F, 0F, (float)Math.toRadians(-45));

		Vector3f tailRecoilAngleChanges = new Vector3f((float)Math.toRadians(-8), 0F, 0F);

		float bodyRecoilDurationCovered = PartAnimate.calculateDuration(tick, 0, bodyRecoilEnd);

		PartAnimate.changeOverDurationUCurve(bodyPointChanges, bodyRecoilPointChanges,
				bodyRecoilDurationCovered);
		PartAnimate.changeOverDurationUCurve(bodyAngleChanges, bodyRecoilAngleChanges,
				bodyRecoilDurationCovered);

		PartAnimate.changeOverDurationUCurve(legAngleChanges, legRecoilAngleChanges,
				bodyRecoilDurationCovered);

		PartAnimate.changeOverDurationSCurve(neckAngleChanges, neckRecoilAngleChanges,
				bodyRecoilDurationCovered);

		PartAnimate.changeOverDurationSCurve(earsAngleChanges, earsRecoilAngleChanges,
				bodyRecoilDurationCovered);

		//PartAnimate.changeOverDurationUCurve(tailAngleChanges, tailRecoilAngleChanges,
		//		bodyRecoilDurationCovered);

		float recoilDurationCovered = PartAnimate.calculateDuration(tick, 0, recoilEnd);

		PartAnimate.changeOverDurationSCurve(barrelPointChanges, barrelRecoilPointChanges,
				recoilDurationCovered);
		PartAnimate.changeOverDurationSCurve(barrelBracePointChanges, barrelBraceRecoilPointChanges,
				recoilDurationCovered);

		float fullDurationCovered = PartAnimate.calculateDuration(tick, 0, actionEnd);

		PartAnimate.changeOverDurationUCurve(tailAngleChanges, tailRecoilAngleChanges,
				fullDurationCovered);

		float returnDampener = 1 - PartAnimate.calculateDuration(tick, recoilEnd, actionEnd);

		barrelPointChanges.multiplyVector(returnDampener);
		barrelBracePointChanges.multiplyVector(returnDampener);

		neckAngleChanges.multiplyVector(returnDampener);

		earsAngleChanges.multiplyVector(returnDampener);

		for(int i = 0; i < gunRtBarrelRtInfo.length; i++)
		{
			PartAnimate.applyPointChangeVectorToInfo(gunRtBarrelRtInfo[i], barrelPointChanges);
			PartAnimate.applyPointChangeVectorToInfo(gunRtBarrelLftInfo[i], barrelPointChanges);
			PartAnimate.applyPointChangeVectorToInfo(gunLftBarrelRtInfo[i], barrelPointChanges);
			PartAnimate.applyPointChangeVectorToInfo(gunLftBarrelLftInfo[i], barrelPointChanges);
		}

		PartAnimate.applyPointChangeVectorToInfo(gunRtBarrelBraceInfo, barrelBracePointChanges);
		PartAnimate.applyPointChangeVectorToInfo(gunLftBarrelBraceInfo, barrelBracePointChanges);

		PartAnimate.applyPointChangeVectorToInfo(bodyJointInfo, bodyPointChanges);
		PartAnimate.applyRotationChangeVectorToInfo(bodyJointInfo, bodyAngleChanges);

		PartAnimate.applyRotationChangeVectorToInfo(neckInfo, neckAngleChanges);

		PartAnimate.applyRotationChangeVectorToInfo(earLftJointInfo, earsAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(earRtJointInfo, Vector3f.negative(earsAngleChanges));

		float leftAngleModifier = 1.8F;
		PartAnimate.applyRotationChangeVectorToInfo(legFntRtInfo[0][0], Vector3f.vectorMultiScalar(legAngleChanges, leftAngleModifier));
		PartAnimate.applyRotationChangeVectorToInfo(legFntRtInfo[1][0], Vector3f.negative(Vector3f.vectorMultiScalar(legAngleChanges, leftAngleModifier)));
		PartAnimate.applyRotationChangeVectorToInfo(legFntLftInfo[0][0], Vector3f.vectorMultiScalar(legAngleChanges, leftAngleModifier));
		PartAnimate.applyRotationChangeVectorToInfo(legFntLftInfo[1][0], Vector3f.negative(Vector3f.vectorMultiScalar(legAngleChanges, leftAngleModifier)));

		//leftAngleModifier = 0.5F;
		//PartAnimate.applyRotationChangeVectorToInfo(legBckRtInfo[0][0], Vector3f.negative(Vector3f.vectorMultiScalar(legAngleChanges, leftAngleModifier)));
		//PartAnimate.applyRotationChangeVectorToInfo(legBckLftInfo[0][0], Vector3f.negative(Vector3f.vectorMultiScalar(legAngleChanges, leftAngleModifier)));

		leftAngleModifier = 1.3F;
		PartAnimate.applyRotationChangeVectorToInfo(legBckRtInfo[1][0], Vector3f.vectorMultiScalar(legAngleChanges, leftAngleModifier));
		PartAnimate.applyRotationChangeVectorToInfo(legBckLftInfo[1][0], Vector3f.vectorMultiScalar(legAngleChanges, leftAngleModifier));

		leftAngleModifier = 1.5F;
		PartAnimate.applyRotationChangeVectorToInfo(legBckRtInfo[2][0], Vector3f.negative(Vector3f.vectorMultiScalar(legAngleChanges, leftAngleModifier)));
		PartAnimate.applyRotationChangeVectorToInfo(legBckLftInfo[2][0], Vector3f.negative(Vector3f.vectorMultiScalar(legAngleChanges, leftAngleModifier)));
		//PartAnimate.applyRotationChangeVectorToInfo(gunRtBarrelRtInfo[i], barrelPointChanges);

		for(int i = 0; i < tailInfo.length; i++)
		{
			float j =  2F * (float)i / ((float)(tailInfo.length - 1)) - 1F;

			PartAnimate.applyRotationChangeVectorToInfo(tailInfo[i][0], Vector3f.vectorMultiScalar(tailAngleChanges, j));
		}

		return idleDampener;
	}

	public void animateBody(EntityImmortalArcanine entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float idleDampener, float angularVelocity, float verticalVelocity)
	{
		float tempIdleDampener = 1F;
		/*
		if(entity.getAnimationID() == LibraryImmortalArcanineAttackID.POUNCE)
		{
			tempIdleDampener = idleDampener;
		}*/

		if(!entity.isSitting())
		{
			float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
			float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);

			float bodyWalkAngle = (float)Math.toRadians(10);
			float bodyRunAngle = (float)Math.toRadians(18);

			float walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI + PI/2) * bodyWalkAngle * (1.0F - (horzVelocity < 0.6F ? (horzVelocity / 0.6F) : 1F));
			float runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI + PI/2) * bodyRunAngle * (horzVelocity >= 0.4F ? ((horzVelocity - 0.4F) / 0.6F) : 0F);

			float bodyJumpAngle = (float)Math.toRadians(-60);
			float verticalVelocityChangeAngle = (float)Math.toRadians(10);

			float newVerticalVelocity = 1.5F * verticalVelocity;

			float maximumChangeY = 1.0F;
			float runCyclePointChange = (MathHelper.cos(2F * runCycleInterval * PI + PI) * maximumChangeY + maximumChangeY) * horzVelocity;

			if(newVerticalVelocity > 1F)
				newVerticalVelocity = 1F;
			else if(newVerticalVelocity < -1F)
				newVerticalVelocity = -1F;

			bodyJointInfo.setNewPointY(bodyJointInfo.getNewPointY() + runCyclePointChange * horzVelocity * (1F - Math.abs(newVerticalVelocity)) * tempIdleDampener);

			bodyJointInfo.setNewRotateX(bodyJointInfo.getNewRotateX() + ((walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity)) 
					+ bodyJumpAngle * newVerticalVelocity) * tempIdleDampener);
		}
		else
		{
			bodyJointInfo.setNewRotateX(bodyJointSitInfo.getNewRotateX());
			bodyJointInfo.setNewPointY(bodyJointSitInfo.getNewPointY());
		}
	}

	public void animateHead(EntityImmortalArcanine entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float idleDampener, float verticalVelocity)
	{
		float tempIdleDampener = 1F;
		/*
		if(entity.getAnimationID() == LibraryImmortalArcanineAttackID.SHOOT)
		{
			tempIdleDampener = idleDampener;
		}*/

		JointAnimation.reverseJointRotatesChangeWithDampener(bodyJointInfo, neckJointInfo, tempIdleDampener);

		PartAnimate.headAnimateInfoOnlyWithAngleModifiers(neckInfo, netHeadYaw, headPitch, 0.2F * tempIdleDampener, 0.2F * tempIdleDampener);

		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockBody();

		float newVerticalVelocity = 1.5F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;

		float fallingEarAngle = (float)Math.toRadians(10);

		float idleAmplitudeX = (float)Math.toRadians(8);

		if(entity.isSitting())
		{
			idleAmplitudeX *= 0.5F;
		}
		else
		{
			float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
			float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);

			float walkAngle = (float)Math.toRadians(6);
			float runAngle = (float)Math.toRadians(18);

			float walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI) * walkAngle * (1 - horzVelocity)
					+ walkAngle * (1.0F - (horzVelocity < 0.6F ? (horzVelocity / 0.6F) : 1F));
			float runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI) * runAngle * horzVelocity
					+ runAngle * (horzVelocity >= 0.4F ? ((horzVelocity - 0.4F) / 0.6F) : 0F);

			neckInfo.setNewRotateX(neckInfo.getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocity * tempIdleDampener);

			if(entity.getIsMounted())
			{
				neckInfo.setNewRotateX(neckInfo.getNewRotateX() + (float)Math.toRadians(45) * entity.getMountedValue());
			}

			/* ******************* */
			walkAngle = (float)Math.toRadians(6);
			runAngle = (float)Math.toRadians(18);

			walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI) * walkAngle * (1 - horzVelocity)
					+ walkAngle * (1.0F - (horzVelocity < 0.6F ? (horzVelocity / 0.6F) : 1F));
			runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI) * runAngle * horzVelocity
					+ runAngle * (horzVelocity >= 0.4F ? ((horzVelocity - 0.4F) / 0.6F) : 0F);

			earLftJointInfo.setNewRotateZ(earLftJointInfo.getNewRotateZ() + (((walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity))
					+ fallingEarAngle * newVerticalVelocity)) * tempIdleDampener);
			earRtJointInfo.setNewRotateZ(earRtJointInfo.getNewRotateZ() - (((walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity))
					+ fallingEarAngle * newVerticalVelocity)) * tempIdleDampener);
		}

		float angleChangeX = -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeX + idleAmplitudeX;

		neckInfo.setNewRotateX(neckInfo.getNewRotateX() + angleChangeX * tempIdleDampener);

		JointAnimation.reverseJointRotatesChangeWithDampener(neckInfo, headJointInfo, tempIdleDampener);

		PartAnimate.headAnimateInfoOnlyWithAngleModifiers(headInfo, netHeadYaw, headPitch, 0.9F * tempIdleDampener, 0.9F * tempIdleDampener);

		/* *************** */
		float idleAmplitudeZ = (float)Math.toRadians(8);

		// + (float)Math.toRadians(45)
		float angleChangeZ = MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI + PI) * idleAmplitudeZ * (1 - horzVelocity);

		earLftJointInfo.setNewRotateZ(earLftJointInfo.getNewRotateZ() + angleChangeZ * tempIdleDampener);
		earRtJointInfo.setNewRotateZ(earRtJointInfo.getNewRotateZ() - angleChangeZ * tempIdleDampener);


		if(entity.isTamed())
		{
			float healthChangeAngle = (float)Math.toRadians(-20);

			earLftJointInfo.setNewRotateZ(earLftJointInfo.getNewRotateZ() - (1F - entity.getHealthPercent()) * healthChangeAngle * tempIdleDampener);
			earRtJointInfo.setNewRotateZ(earRtJointInfo.getNewRotateZ() + (1F - entity.getHealthPercent()) * healthChangeAngle * tempIdleDampener);
		}
	}

	public void animateLegs(EntityImmortalArcanine entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float idleDampener, float angularVelocity, float verticalVelocity, float totalChangeInYaw)
	{
		float tempIdleDampener = 1F;
		/*
		if(entity.getAnimationID() == LibraryImmortalArcanineAttackID.POUNCE)
		{
			tempIdleDampener = idleDampener;
		}
		 */
		if(!entity.isSitting())
		{
			JointAnimation.reverseJointRotatesChangeWithDampener(bodyJointInfo, legFntRtInfo[0][0], tempIdleDampener);
			JointAnimation.reverseJointRotatesChangeWithDampener(bodyJointInfo, legFntLftInfo[0][0], tempIdleDampener);
			JointAnimation.reverseJointRotatesChangeWithDampener(bodyJointInfo, legBckRtInfo[0][0], tempIdleDampener);
			JointAnimation.reverseJointRotatesChangeWithDampener(bodyJointInfo, legBckLftInfo[0][0], tempIdleDampener);

			/* ******* Relative Angles/Absolute Commented Out ******* */
			float legFntWalkStartAngles[] = new float[2];

			legFntWalkStartAngles[0] = (float)Math.toRadians(45);
			legFntWalkStartAngles[1] = (float)Math.toRadians(-25);

			float legFntWalkAngles[][] = new float[2][4];

			// legFntWalkAngles[0][0] = (float)Math.toRadians(50);
			// legFntWalkAngles[0][1] = (float)Math.toRadians(40);
			// legFntWalkAngles[0][2] = (float)Math.toRadians(-20);
			// legFntWalkAngles[0][3] = (float)Math.toRadians(45);

			legFntWalkAngles[0][0] = (float)Math.toRadians(5);
			legFntWalkAngles[0][1] = (float)Math.toRadians(-10);
			legFntWalkAngles[0][2] = (float)Math.toRadians(-60);
			legFntWalkAngles[0][3] = (float)Math.toRadians(65);

			// legFntWalkAngles[1][0] = (float)Math.toRadians(-25);
			// legFntWalkAngles[1][1] = (float)Math.toRadians(-90);
			// legFntWalkAngles[1][2] = (float)Math.toRadians(-13);
			// legFntWalkAngles[1][3] = (float)Math.toRadians(-45);

			legFntWalkAngles[1][0] = (float)Math.toRadians(20);
			legFntWalkAngles[1][1] = (float)Math.toRadians(-75);
			legFntWalkAngles[1][2] = (float)Math.toRadians(77);
			legFntWalkAngles[1][3] = (float)Math.toRadians(-32);

			float legBckWalkStartAngles[] = new float[3];

			legBckWalkStartAngles[0] = (float)Math.toRadians(30);
			legBckWalkStartAngles[1] = (float)Math.toRadians(60);
			legBckWalkStartAngles[2] = (float)Math.toRadians(-30);

			float legBckWalkAngles[][] = new float[3][4];
			float tempAdjust = (float)Math.toRadians(5);

			// legBckWalkAngles[0][0] = (float)Math.toRadians(-30);
			// legBckWalkAngles[0][1] = (float)Math.toRadians(-20);
			// legBckWalkAngles[0][2] = (float)Math.toRadians(40);
			// legBckWalkAngles[0][3] = (float)Math.toRadians(20);

			legBckWalkAngles[0][0] = (float)Math.toRadians(-50);// - tempAdjust;
			legBckWalkAngles[0][1] = (float)Math.toRadians(10);// + tempAdjust;
			legBckWalkAngles[0][2] = (float)Math.toRadians(60);// + tempAdjust;
			legBckWalkAngles[0][3] = (float)Math.toRadians(-20);// - tempAdjust;

			// legBckWalkAngles[1][0] = (float)Math.toRadians(60);
			// legBckWalkAngles[1][1] = (float)Math.toRadians(55);
			// legBckWalkAngles[1][2] = (float)Math.toRadians(15);
			// legBckWalkAngles[1][3] = (float)Math.toRadians(65);

			legBckWalkAngles[1][0] = (float)Math.toRadians(-5) - tempAdjust;
			legBckWalkAngles[1][1] = (float)Math.toRadians(-5) - tempAdjust;
			legBckWalkAngles[1][2] = (float)Math.toRadians(-40) - tempAdjust;
			legBckWalkAngles[1][3] = (float)Math.toRadians(50) + tempAdjust;

			// legBckWalkAngles[2][0] = (float)Math.toRadians(-70);
			// legBckWalkAngles[2][1] = (float)Math.toRadians(-60);
			// legBckWalkAngles[2][2] = (float)Math.toRadians(-45);
			// legBckWalkAngles[2][3] = (float)Math.toRadians(-5);

			legBckWalkAngles[2][0] = (float)Math.toRadians(-65) - tempAdjust;
			legBckWalkAngles[2][1] = (float)Math.toRadians(10) + tempAdjust;
			legBckWalkAngles[2][2] = (float)Math.toRadians(15) + tempAdjust;
			legBckWalkAngles[2][3] = (float)Math.toRadians(40) + tempAdjust;

			float legFntRunStartAngles[] = new float[2];

			legFntRunStartAngles[0] = (float)Math.toRadians(35);
			legFntRunStartAngles[1] = (float)Math.toRadians(-5);

			float legFntRunAngles[][] = new float[2][4];

			// legFntRunAngles[0][0] = (float)Math.toRadians(75);
			// legFntRunAngles[0][1] = (float)Math.toRadians(20);
			// legFntRunAngles[0][2] = (float)Math.toRadians(-30);
			// legFntRunAngles[0][3] = (float)Math.toRadians(60);

			legFntRunAngles[0][0] = (float)Math.toRadians(15);
			legFntRunAngles[0][1] = (float)Math.toRadians(-55);
			legFntRunAngles[0][2] = (float)Math.toRadians(-50);
			legFntRunAngles[0][3] = (float)Math.toRadians(90);

			// legFntRunAngles[1][0] = (float)Math.toRadians(-40);
			// legFntRunAngles[1][1] = (float)Math.toRadians(-90);
			// legFntRunAngles[1][2] = (float)Math.toRadians(-25);
			// legFntRunAngles[1][3] = (float)Math.toRadians(-5);

			legFntRunAngles[1][0] = (float)Math.toRadians(-35);
			legFntRunAngles[1][1] = (float)Math.toRadians(-50);
			legFntRunAngles[1][2] = (float)Math.toRadians(65);
			legFntRunAngles[1][3] = (float)Math.toRadians(20);

			float legBckRunStartAngles[] = new float[3];

			legBckRunStartAngles[0] = (float)Math.toRadians(30);
			legBckRunStartAngles[1] = (float)Math.toRadians(60);
			legBckRunStartAngles[2] = (float)Math.toRadians(-30);

			float legBckRunAngles[][] = new float[3][4];
			tempAdjust = (float)Math.toRadians(5);
			// legBckRunAngles[0][0] = (float)Math.toRadians(-35);
			// legBckRunAngles[0][1] = (float)Math.toRadians(-65);
			// legBckRunAngles[0][2] = (float)Math.toRadians(10);
			// legBckRunAngles[0][3] = (float)Math.toRadians(40);

			legBckRunAngles[0][0] = (float)Math.toRadians(-75);// - tempAdjust;
			legBckRunAngles[0][1] = (float)Math.toRadians(-30);// - tempAdjust;
			legBckRunAngles[0][2] = (float)Math.toRadians(75);// + tempAdjust;
			legBckRunAngles[0][3] = (float)Math.toRadians(30);// + tempAdjust;

			// legBckRunAngles[1][0] = (float)Math.toRadians(110);
			// legBckRunAngles[1][1] = (float)Math.toRadians(55);
			// legBckRunAngles[1][2] = (float)Math.toRadians(65);
			// legBckRunAngles[1][3] = (float)Math.toRadians(75);

			legBckRunAngles[1][0] = (float)Math.toRadians(35) + tempAdjust;
			legBckRunAngles[1][1] = (float)Math.toRadians(-55) - tempAdjust;
			legBckRunAngles[1][2] = (float)Math.toRadians(10) + tempAdjust;
			legBckRunAngles[1][3] = (float)Math.toRadians(10) + tempAdjust;

			// legBckRunAngles[2][0] = (float)Math.toRadians(-85);
			// legBckRunAngles[2][1] = (float)Math.toRadians(-55);
			// legBckRunAngles[2][2] = (float)Math.toRadians(-70);
			// legBckRunAngles[2][3] = (float)Math.toRadians(-30);

			legBckRunAngles[2][0] = (float)Math.toRadians(-55) - tempAdjust;
			legBckRunAngles[2][1] = (float)Math.toRadians(30) + tempAdjust;
			legBckRunAngles[2][2] = (float)Math.toRadians(-15) - tempAdjust;
			legBckRunAngles[2][3] = (float)Math.toRadians(40) + tempAdjust;

			float walkRatio = 0;
			float runRatio = 0;

			if(horzVelocity > 0)
			{
				if(horzVelocity >= this.WALK_MAX)
				{
					runRatio = (horzVelocity - this.WALK_MAX) / (1F - this.WALK_MAX);
				}

				if(horzVelocity <= this.RUN_MIN)
				{
					walkRatio = 1F - ((this.RUN_MIN - horzVelocity) / this.RUN_MIN);
				}
			}

			float horzVelocityModifier = horzVelocity * 3F > 1F ?  1F : horzVelocity * 3F;

			float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
			float walkOppositeCycleInterval = ((WALK_FREQUENCY * distanceMoved + PI) % (2 * PI))/(2 * PI);
			float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
			float runOppositeCycleInterval = ((RUN_FREQUENCY * distanceMoved + (PI/3F)) % (2 * PI))/(2 * PI);

			float newVerticalVelocity = 2F * verticalVelocity;

			if(newVerticalVelocity > 1F)
				newVerticalVelocity = 1F;
			else if(newVerticalVelocity < -1F)
				newVerticalVelocity = -1F;

			for(int i = 0; i < legFntRt.length; i++)
			{
				float walkCycleAngleChange = 0;
				float runCycleAngleChange = 0;

				walkCycleAngleChange = calculateMoveLegAngleChange(walkCycleInterval, 
						legFntRtInfo[i][0].getNewRotateX(), legFntWalkStartAngles[i], legFntWalkAngles[i]) * walkRatio;

				runCycleAngleChange = calculateMoveLegAngleChange(runCycleInterval, 
						legFntRtInfo[i][0].getNewRotateX(), legFntRunStartAngles[i], legFntRunAngles[i]) * runRatio;

				legFntRtInfo[i][0].setNewRotateX((legFntRtInfo[i][0].getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocityModifier * (1F - Math.abs(newVerticalVelocity)) * tempIdleDampener));

				walkCycleAngleChange = calculateMoveLegAngleChange(walkOppositeCycleInterval, 
						legFntLftInfo[i][0].getNewRotateX(), legFntWalkStartAngles[i], legFntWalkAngles[i]) * walkRatio;

				runCycleAngleChange = calculateMoveLegAngleChange(runOppositeCycleInterval, 
						legFntLftInfo[i][0].getNewRotateX(), legFntRunStartAngles[i], legFntRunAngles[i]) * runRatio;

				legFntLftInfo[i][0].setNewRotateX((legFntLftInfo[i][0].getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocityModifier * (1F - Math.abs(newVerticalVelocity)) * tempIdleDampener));
			}

			for(int i = 0; i < legBckRt.length; i++)
			{
				float walkCycleAngleChange = 0;
				float runCycleAngleChange = 0;

				walkCycleAngleChange = calculateMoveLegAngleChange(walkCycleInterval, 
						legBckRtInfo[i][0].getNewRotateX(), legBckWalkStartAngles[i], legBckWalkAngles[i]) * walkRatio;

				runCycleAngleChange = calculateMoveLegAngleChange(runCycleInterval, 
						legBckRtInfo[i][0].getNewRotateX(), legBckRunStartAngles[i], legBckRunAngles[i]) * runRatio;

				legBckRtInfo[i][0].setNewRotateX((legBckRtInfo[i][0].getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocityModifier * (1F - Math.abs(newVerticalVelocity)) * tempIdleDampener));

				walkCycleAngleChange = calculateMoveLegAngleChange(walkOppositeCycleInterval, 
						legBckLftInfo[i][0].getNewRotateX(), legBckWalkStartAngles[i], legBckWalkAngles[i]) * walkRatio;

				runCycleAngleChange = calculateMoveLegAngleChange(runOppositeCycleInterval, 
						legBckLftInfo[i][0].getNewRotateX(), legBckRunStartAngles[i], legBckRunAngles[i]) * runRatio;

				legBckLftInfo[i][0].setNewRotateX((legBckLftInfo[i][0].getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocityModifier * (1F - Math.abs(newVerticalVelocity)) * tempIdleDampener));
			}

			float fallingModifier = 0;

			if(newVerticalVelocity < 0)
				fallingModifier = newVerticalVelocity;

			float fallingLegAngle = (float)Math.toRadians(25);

			legFntRtInfo[0][0].setNewRotateX(legFntRtInfo[0][0].getNewRotateX() + fallingLegAngle * fallingModifier * tempIdleDampener);
			legFntLftInfo[0][0].setNewRotateX(legFntLftInfo[0][0].getNewRotateX() + fallingLegAngle * fallingModifier * tempIdleDampener);

			/* ****************** */
			/* Leg turn animation */
			/* ****************** */

			float newHorzVelocity = 1.5F * horzVelocity;

			if(newHorzVelocity > 1F)
				newHorzVelocity = 1F;
			else if(newHorzVelocity < -1F)
				newHorzVelocity = -1F;

			float turnFrequency = 0.35F;
			float turnAmplitude = (float)Math.toRadians(-30);

			legFntRtInfo[0][0].setNewRotateX(legFntRtInfo[0][0].getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(totalChangeInYaw, angularVelocity, turnFrequency, turnAmplitude) * (1F - Math.abs(newHorzVelocity)) * (1F - Math.abs(newVerticalVelocity)));
			legFntLftInfo[0][0].setNewRotateX(legFntLftInfo[0][0].getNewRotateX() + PartAnimate.negCosRotateAnimationAdjusted(totalChangeInYaw, angularVelocity, turnFrequency, turnAmplitude) * (1F - Math.abs(newHorzVelocity)) * (1F - Math.abs(newVerticalVelocity)));

			turnAmplitude = (float)Math.toRadians(15);

			legFntRtInfo[1][0].setNewRotateX(legFntRtInfo[1][0].getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(totalChangeInYaw, angularVelocity, turnFrequency, turnAmplitude) * (1F - Math.abs(newHorzVelocity)) * (1F - Math.abs(newVerticalVelocity)));
			legFntLftInfo[1][0].setNewRotateX(legFntLftInfo[1][0].getNewRotateX() + PartAnimate.negCosRotateAnimationAdjusted(totalChangeInYaw, angularVelocity, turnFrequency, turnAmplitude) * (1F - Math.abs(newHorzVelocity)) * (1F - Math.abs(newVerticalVelocity)));

			turnAmplitude = (float)Math.toRadians(-30);

			legBckRtInfo[0][0].setNewRotateX(legBckRtInfo[0][0].getNewRotateX() + PartAnimate.negCosRotateAnimationAdjusted(totalChangeInYaw, angularVelocity, turnFrequency, turnAmplitude) * (1F - Math.abs(newHorzVelocity)) * (1F - Math.abs(newVerticalVelocity)));
			legBckLftInfo[0][0].setNewRotateX(legBckLftInfo[0][0].getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(totalChangeInYaw, angularVelocity, turnFrequency, turnAmplitude) * (1F - Math.abs(newHorzVelocity)) * (1F - Math.abs(newVerticalVelocity)));

			turnAmplitude = (float)Math.toRadians(25);

			legBckRtInfo[1][0].setNewRotateX(legBckRtInfo[1][0].getNewRotateX() + PartAnimate.negCosRotateAnimationAdjusted(totalChangeInYaw, angularVelocity, turnFrequency, turnAmplitude) * (1F - Math.abs(newHorzVelocity)) * (1F - Math.abs(newVerticalVelocity)));
			legBckLftInfo[1][0].setNewRotateX(legBckLftInfo[1][0].getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(totalChangeInYaw, angularVelocity, turnFrequency, turnAmplitude) * (1F - Math.abs(newHorzVelocity)) * (1F - Math.abs(newVerticalVelocity)));

			turnAmplitude = (float)Math.toRadians(-25);

			legBckRtInfo[2][0].setNewRotateX(legBckRtInfo[2][0].getNewRotateX() + PartAnimate.negCosRotateAnimationAdjusted(totalChangeInYaw, angularVelocity, turnFrequency, turnAmplitude) * (1F - Math.abs(newHorzVelocity)) * (1F - Math.abs(newVerticalVelocity)));
			legBckLftInfo[2][0].setNewRotateX(legBckLftInfo[2][0].getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(totalChangeInYaw, angularVelocity, turnFrequency, turnAmplitude) * (1F - Math.abs(newHorzVelocity)) * (1F - Math.abs(newVerticalVelocity)));
		}
		else
		{
			for(int i = 0; i < legFntRtInfo.length; i++)
			{
				legFntRtInfo[i][0].setNewRotatesWithVector(legFntRtSitInfo[i][0].getNewRotates());
				legFntRtInfo[i][1].setNewRotatesWithVector(legFntRtSitInfo[i][1].getNewRotates());
				legFntLftInfo[i][0].setNewRotatesWithVector(legFntLftSitInfo[i][0].getNewRotates());
				legFntLftInfo[i][1].setNewRotatesWithVector(legFntLftSitInfo[i][1].getNewRotates());
			}

			for(int i = 0; i < legBckRtInfo.length; i++)
			{
				legBckRtInfo[i][0].setNewRotatesWithVector(legBckRtSitInfo[i][0].getNewRotates());
				legBckRtInfo[i][1].setNewRotatesWithVector(legBckRtSitInfo[i][1].getNewRotates());
				legBckLftInfo[i][0].setNewRotatesWithVector(legBckLftSitInfo[i][0].getNewRotates());
				legBckLftInfo[i][1].setNewRotatesWithVector(legBckLftSitInfo[i][1].getNewRotates());
			}
		}
	}

	public float calculateMoveLegAngleChange(float cycleInterval, float standingLegAngle, 
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

	public void animateTail(EntityImmortalArcanine entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float idleDampener, float angularVelocity, float verticalVelocity)
	{
		float tempIdleDampener = 1F;

		if(entity.getAnimationID() == LibraryImmortalArcanineAttackID.SHOOT)
		{
			tempIdleDampener = idleDampener;
		}

		if(entity.isSitting())
		{
			tailInfo[0][0].setNewRotateX(tailSitInfo.getNewRotateX());
		}

		JointAnimation.reverseJointRotatesChangeWithDampener(bodyJointInfo, tailInfo[0][0], tempIdleDampener);

		float yawChangeAngle = -(float)Math.toRadians(7);
		float verticalVelocityChangeAngle = (float)Math.toRadians(-2);

		float bodyJumpAngle = (float)Math.toRadians(-55);

		float newVerticalVelocity = 1.5F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;

		float absoluteMoveVelocity = horzVelocity + Math.abs(newVerticalVelocity);

		if(absoluteMoveVelocity > 1F)
			absoluteMoveVelocity = 1F;

		tailInfo[0][0].setNewRotateX(tailInfo[0][0].getNewRotateX() + bodyJumpAngle * newVerticalVelocity * tempIdleDampener);

		for(int i = 0; i < tail.length; i++)
		{
			IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockTail(i);

			if(entity.isSitting())
			{
				tailInfo[0][0].setNewRotateX(tailSitInfo.getNewRotateX());
			}

			this.tailAnimation(entity, tailInfo[i][0], currentIdleAnimationClock,
					absoluteMoveVelocity, distanceMoved, horzVelocity, angularVelocity, newVerticalVelocity,
					verticalVelocityChangeAngle, yawChangeAngle, i, tail.length, 0F, false, tempIdleDampener);
		}
	}

	public void tailAnimation(EntityImmortalArcanine entity, PartInfo partInfo, IdleAnimationClock currentIdleAnimationClock,
			float absoluteMoveVelocity, float distanceMoved, float horzVelocity, float angularVelocity,
			float newVerticalVelocity, float verticalVelocityChangeAngle, float yawChangeAngle,
			int partNumber, int partArrayLength, float phaseOffset, boolean inverseXAnimation, float tempIdleDampener)
	{
		float healthChangeAngle = entity.isTamed() ? (float)Math.toRadians(-8) : 0;

		float idleAmplitudeX = (float)Math.toRadians(6);
		float idleAmplitudeY = (float)Math.toRadians(6);

		float moveAmplitudeX = (float)Math.toRadians(6);

		float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI) - PI / 2F;
		float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI) - PI / 2F;

		// + (float)Math.toRadians(45)
		float angleChangeX = 0;
		float angleChangeY = 0;

		float acceleratedAbsoluteMoveVelocity = absoluteMoveVelocity * 1.75F;

		if(acceleratedAbsoluteMoveVelocity > 1F)
			acceleratedAbsoluteMoveVelocity = 1F;

		// Idle Animations
		angleChangeX += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2 + phaseOffset) * idleAmplitudeX 
				* (1F - acceleratedAbsoluteMoveVelocity) * (1F - Math.abs(angularVelocity) * 0.5F) * (inverseXAnimation ? -1F : 1F);
		angleChangeY += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2 + phaseOffset) * idleAmplitudeY
				* (1F - acceleratedAbsoluteMoveVelocity) * (1F - Math.abs(angularVelocity) * 0.75F);

		// Tamable Animation
		angleChangeX += (entity.isTamed() ? (1F - entity.getHealthPercent()) * healthChangeAngle : 0);

		// Part Number Dampener
		angleChangeX *= (1F - (Math.pow(Math.E, -0.95F * (float)(partNumber + 1))));
		angleChangeY *= (1F - (Math.pow(Math.E, -0.95F * (float)(partNumber + 1))));

		// Movement Animations
		float distanceMovedModifier = 1.1F;
		float walkCycleAngleChange = (PartAnimate.negCosRotateAnimationAdjusted(distanceMoved - (float)partNumber * distanceMovedModifier, horzVelocity, WALK_FREQUENCY, moveAmplitudeX * 4.0F) * (1.0F - (horzVelocity < 0.6F ? (horzVelocity / 0.6F) : 1F)));
		float runCycleAngleChange = (PartAnimate.negCosRotateAnimationAdjusted(distanceMoved - (float)partNumber * distanceMovedModifier, horzVelocity, RUN_FREQUENCY, moveAmplitudeX) * (horzVelocity >= 0.4F ? ((horzVelocity - 0.4F) / 0.6F) : 0F));

		angleChangeX += ((walkCycleAngleChange + runCycleAngleChange)) + newVerticalVelocity * (newVerticalVelocity < 0 ? verticalVelocityChangeAngle : verticalVelocityChangeAngle * 0.75F);
		angleChangeY += angularVelocity * yawChangeAngle;

		// Apply Angle Changes
		partInfo.setNewRotateX(partInfo.getNewRotateX() * (1F - (acceleratedAbsoluteMoveVelocity * tempIdleDampener)) + (angleChangeX * (entity.isSitting() ? 0.5F : 1.0F)) * tempIdleDampener);// - partInfo.getDefaultRotates().getX() * (1F - tempIdleDampener));
		partInfo.setNewRotateY(partInfo.getNewRotateY() + angleChangeY * (entity.isSitting() ? 0.5F : 1.0F) * tempIdleDampener);
	}

	public void animateCannons(EntityImmortalArcanine entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float idleDampener)
	{
		if(!entity.isSitting())
		{
			if(entity.getMountedValue() > 0)
			{
				gunRtBaseJointInfo.setNewRotateY(-(float)Math.toRadians(115) * entity.getMountedValue());
				gunLftBaseJointInfo.setNewRotateY((float)Math.toRadians(115) * entity.getMountedValue());
			}
			else if(entity.getAggroValue() > 0)
			{
				PartAnimate.headAnimateHorzInfoOnlyWithModifier(gunRtAimingPivotInfo, netHeadYaw, entity.getAggroValue());
				PartAnimate.headAnimateHorzInfoOnlyWithModifier(gunLftAimingPivotInfo, netHeadYaw, entity.getAggroValue());

				PartAnimate.headAnimateVertInfoOnlyWithModifier(gunRtAimingPivotInfo, netHeadYaw * 0.25F, entity.getAggroValue());
				PartAnimate.headAnimateVertInfoOnlyWithModifier(gunLftAimingPivotInfo, netHeadYaw * -0.25F, entity.getAggroValue());

				if(gunRtAimingPivotInfo.getNewRotateY() <= (float)Math.toRadians(-15))
				{
					gunRtAimingPivotInfo.setNewRotateX((float)Math.toRadians(-15) * 0.25F);
					gunRtAimingPivotInfo.setNewRotateY((float)Math.toRadians(-15));
				}
				else if(gunLftAimingPivotInfo.getNewRotateY() >= (float)Math.toRadians(15))
				{
					gunLftAimingPivotInfo.setNewRotateX((float)Math.toRadians(-15) * 0.25F);
					gunLftAimingPivotInfo.setNewRotateY((float)Math.toRadians(15));
				}

				PartAnimate.headAnimateHorzInfoOnlyWithModifier(gunRtAimingPivotInfo, headPitch * -0.25F, entity.getAggroValue());
				PartAnimate.headAnimateHorzInfoOnlyWithModifier(gunLftAimingPivotInfo, headPitch * 0.25F, entity.getAggroValue());

				PartAnimate.headAnimateVertInfoOnlyWithModifier(gunRtAimingPivotInfo, headPitch, entity.getAggroValue());
				PartAnimate.headAnimateVertInfoOnlyWithModifier(gunLftAimingPivotInfo, headPitch, entity.getAggroValue());

				if(gunRtAimingPivotInfo.getNewRotateX() >= (float)Math.toRadians(50))
				{
					gunRtAimingPivotInfo.setNewRotateX((float)Math.toRadians(50));
				}

				if(gunLftAimingPivotInfo.getNewRotateX() >= (float)Math.toRadians(50))
				{
					gunLftAimingPivotInfo.setNewRotateX((float)Math.toRadians(50));
				}
			}
		}
	}

	public void deployAnimations()
	{
		this.animationDeployer.rotate(bodyJoint, bodyJointInfo.getNewRotates());

		this.animationDeployer.move(bodyJoint, bodyJointInfo.getNewPnt());
		this.animationDeployer.rotate(bodyJoint, bodyJointInfo.getNewRotates());

		this.animationDeployer.rotate(neckJoint, neckJointInfo.getNewRotates());
		this.animationDeployer.rotate(neck, neckInfo.getNewRotates());
		this.animationDeployer.rotate(headJoint, headJointInfo.getNewRotates());
		this.animationDeployer.rotate(head, headInfo.getNewRotates());

		this.animationDeployer.rotate(earLftJoint, earLftJointInfo.getNewRotates());
		this.animationDeployer.rotate(earRtJoint, earRtJointInfo.getNewRotates());

		this.animationDeployer.rotate(earLftJoint, earLftJointInfo.getNewRotates());
		this.animationDeployer.rotate(earRtJoint, earRtJointInfo.getNewRotates());

		this.animationDeployer.rotate(gunRtAimingPivot, gunRtAimingPivotInfo.getNewRotates());
		this.animationDeployer.rotate(gunLftAimingPivot, gunLftAimingPivotInfo.getNewRotates());

		this.animationDeployer.rotate(gunLftBaseJoint, gunLftBaseJointInfo.getNewRotates());
		this.animationDeployer.rotate(gunRtBaseJoint, gunRtBaseJointInfo.getNewRotates());

		for(int i = 0; i < gunRtBarrelRtInfo.length; i++)
		{
			this.animationDeployer.move(gunRtBarrelRt[i], gunRtBarrelRtInfo[i].getNewPnt());
			this.animationDeployer.move(gunRtBarrelLft[i], gunRtBarrelLftInfo[i].getNewPnt());
			this.animationDeployer.move(gunLftBarrelRt[i], gunLftBarrelRtInfo[i].getNewPnt());
			this.animationDeployer.move(gunLftBarrelLft[i], gunLftBarrelLftInfo[i].getNewPnt());
		}

		this.animationDeployer.move(gunLftBarrelBrace, gunLftBarrelBraceInfo.getNewPnt());
		this.animationDeployer.move(gunRtBarrelBrace, gunRtBarrelBraceInfo.getNewPnt());

		for(int i = 0; i < legFntLftInfo.length; i++)
		{
			this.animationDeployer.rotate(legFntLft[i][0], legFntLftInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(legFntLft[i][1], legFntLftInfo[i][1].getNewRotates());
			this.animationDeployer.rotate(legFntRt[i][0], legFntRtInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(legFntRt[i][1], legFntRtInfo[i][1].getNewRotates());
		}

		for(int i = 0; i < legBckLftInfo.length; i++)
		{
			this.animationDeployer.rotate(legBckLft[i][0], legBckLftInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(legBckLft[i][1], legBckLftInfo[i][1].getNewRotates());
			this.animationDeployer.rotate(legBckRt[i][0], legBckRtInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(legBckRt[i][1], legBckRtInfo[i][1].getNewRotates());
		}

		for(int i = 0; i < tail.length; i++)
		{
			this.animationDeployer.rotate(tail[i][0], tailInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(tail[i][1], tailInfo[i][0].getNewRotates());
		}

		this.animationDeployer.applyChanges();
	}
}