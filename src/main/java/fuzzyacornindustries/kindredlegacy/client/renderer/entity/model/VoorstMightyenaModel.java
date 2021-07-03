package fuzzyacornindustries.kindredlegacy.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;

import fuzzyacornindustries.kindredlegacy.client.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.client.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.client.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.animation.JointAnimation;
import fuzzyacornindustries.kindredlegacy.client.animation.PartAnimate;
import fuzzyacornindustries.kindredlegacy.client.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.client.renderer.entity.VoorstMightyenaEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.IMobMotionTracker;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.HostilePokemon;
import net.minecraft.client.renderer.entity.model.TintedAgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class VoorstMightyenaModel<T extends HostilePokemon> extends TintedAgeableModel<T>
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public final float RUN_FREQUENCY = 0.8F;
	public final float WALK_FREQUENCY = 1.25F;
	public final float SNEAK_VELOCITY = 0.35F;

	public ModelRenderer body;
	PartInfo bodyInfo;

	public ModelRenderer neckJoint;
	public ModelRenderer neck;
	PartInfo neckJointInfo;
	PartInfo neckInfo;

	public ModelRenderer neckFurLftJoint;
	public ModelRenderer neckFurLft;
	public ModelRenderer neckFurRtJoint;
	public ModelRenderer neckFurRt;
	PartInfo neckFurLftJointInfo;
	PartInfo neckFurLftInfo;
	PartInfo neckFurRtJointInfo;
	PartInfo neckFurRtInfo;

	public ModelRenderer legFntLft;
	public ModelRenderer legFntRt;
	public ModelRenderer legBckLft;
	public ModelRenderer legBckRt;
	PartInfo legFntLftInfo;
	PartInfo legFntRtInfo;
	PartInfo legBckLftInfo;
	PartInfo legBckRtInfo;

	public ModelRenderer bodyFufLftBckMain;
	public ModelRenderer bodyFurLftBckBase;
	public ModelRenderer bodyFurLftBckBck;
	public ModelRenderer bodyFurLftBckMid;
	public ModelRenderer bodyFurLftBckFnt;
	public ModelRenderer bodyFurLftBckTop;
	PartInfo bodyFurLftBckBaseInfo;
	PartInfo bodyFurLftBckBckInfo;
	PartInfo bodyFurLftBckMidInfo;
	PartInfo bodyFurLftBckFntInfo;
	PartInfo bodyFurLftBckTopInfo;

	public ModelRenderer bodyFufRtBckMain;
	public ModelRenderer bodyFurRtBckBase;
	public ModelRenderer bodyFurRtBckBck;
	public ModelRenderer bodyFurRtBckMid;
	public ModelRenderer bodyFurRtBckFnt;
	public ModelRenderer bodyFurRtBckTop;
	PartInfo bodyFurRtBckBaseInfo;
	PartInfo bodyFurRtBckBckInfo;
	PartInfo bodyFurRtBckMidInfo;
	PartInfo bodyFurRtBckFntInfo;
	PartInfo bodyFurRtBckTopInfo;

	public ModelRenderer headFaceJoint;
	public ModelRenderer headFace;
	public ModelRenderer head;
	public ModelRenderer headMuzzle;
	PartInfo headFaceJointInfo;
	PartInfo headFaceInfo;

	public ModelRenderer headJawJoint;
	public ModelRenderer headJaw;
	PartInfo headJawJointInfo;
	PartInfo headJawInfo;

	public ModelRenderer headEarRtJoint;
	public ModelRenderer headEarRt;
	public ModelRenderer headEarLftJoint;
	public ModelRenderer headEarLft;
	PartInfo headEarRtJointInfo;
	PartInfo headEarRtInfo;
	PartInfo headEarLftJointInfo;
	PartInfo headEarLftInfo;

	public ModelRenderer headLowerFaceJoint;
	public ModelRenderer headLowerFace;
	public ModelRenderer headLower;
	public ModelRenderer headLowerMuzzle;
	PartInfo headLowerFaceJointInfo;
	PartInfo headLowerFaceInfo;

	public ModelRenderer headLowerJawJoint;
	public ModelRenderer headLowerJaw;
	PartInfo headLowerJawJointInfo;
	PartInfo headLowerJawInfo;

	public ModelRenderer tailMain[][] = new ModelRenderer[2][2];
	PartInfo tailMainInfo[][] = new PartInfo[tailMain.length][tailMain[0].length];

	public ModelRenderer tailTips[][] = new ModelRenderer[4][2];
	PartInfo tailTipsInfo[][] = new PartInfo[tailTips.length][tailTips[0].length];

	public VoorstMightyenaModel() 
	{
		this.textureWidth = 64;
		this.textureHeight = 64;

		int jointNumber = 0;
		int partNumber = 1;

		this.body = new ModelRenderer(this, 28, 46);
		this.body.setRotationPoint(0.0F, 15.0F, 0.0F);
		this.body.addBox(-3.0F, -3.0F, -6.0F, 6, 6, 12, 0.0F);
		bodyInfo = new PartInfo(body);

		this.neckJoint = new ModelRenderer(this, 50, 24);
		this.neckJoint.setRotationPoint(0.0F, -2.0F, -5.0F);
		this.neckJoint.addBox(-1.5F, -1.5F, -3.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(neckJoint, -0.3490658503988659F, -0.0F, 0.0F);
		neckJointInfo = new PartInfo(neckJoint);
		this.neck = new ModelRenderer(this, 50, 24);
		this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.neck.addBox(-1.5F, -1.5F, -3.0F, 3, 3, 4, 0.0F);
		neckInfo = new PartInfo(neck);

		this.neckFurLftJoint = new ModelRenderer(this, 40, 37);
		this.neckFurLftJoint.setRotationPoint(1.6F, 0.0F, 0.0F);
		this.neckFurLftJoint.addBox(-1.5F, -2.0F, -3.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(neckFurLftJoint, 0.0F, -0.8726646259971648F, -0.2617993877991494F);
		neckFurLftJointInfo = new PartInfo(neckFurLftJoint);
		this.neckFurLft = new ModelRenderer(this, 40, 37);
		this.neckFurLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.neckFurLft.addBox(-1.5F, -2.0F, -3.0F, 3, 5, 3, 0.0F);
		neckFurLftInfo = new PartInfo(neckFurLft);
		this.neckFurRtJoint = new ModelRenderer(this, 40, 29);
		this.neckFurRtJoint.setRotationPoint(-1.6F, 0.0F, 0.0F);
		this.neckFurRtJoint.addBox(-1.5F, -2.0F, -3.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(neckFurRtJoint, 0.0F, 0.8726646259971648F, 0.2617993877991494F);
		neckFurRtJointInfo = new PartInfo(neckFurRtJoint);
		this.neckFurRt = new ModelRenderer(this, 40, 29);
		this.neckFurRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.neckFurRt.addBox(-1.5F, -2.0F, -3.0F, 3, 5, 3, 0.0F);
		neckFurRtInfo = new PartInfo(neckFurRt);

		this.legFntLft = new ModelRenderer(this, 28, 31);
		this.legFntLft.setRotationPoint(1.49F, 2.5F, -3.49F);
		this.legFntLft.addBox(-1.5F, -0.5F, -1.5F, 3, 7, 3, 0.0F);
		legFntLftInfo = new PartInfo(legFntLft);
		this.legFntRt = new ModelRenderer(this, 52, 31);
		this.legFntRt.setRotationPoint(-1.49F, 2.5F, -3.5F);
		this.legFntRt.addBox(-1.5F, -0.5F, -1.5F, 3, 7, 3, 0.0F);
		legFntRtInfo = new PartInfo(legFntRt);
		this.legBckLft = new ModelRenderer(this, 28, 41);
		this.legBckLft.setRotationPoint(1.4F, 2.5F, 4.1F);
		this.legBckLft.addBox(-1.5F, -1.0F, -1.5F, 3, 5, 3, 0.0F);
		this.setRotateAngle(legBckLft, -0.17453292519943295F, -0.0F, 0.0F);
		this.legBckLft.setTextureOffset(28, 49).addBox(-1.49F, 1.5F, 0.0F, 3, 5, 3, 0.0F); // legBckLftHind
		legBckLftInfo = new PartInfo(legBckLft);
		this.legBckRt = new ModelRenderer(this, 52, 41);
		this.legBckRt.setRotationPoint(-1.4F, 2.5F, 4.0F);
		this.legBckRt.addBox(-1.5F, -1.0F, -1.5F, 3, 5, 3, 0.0F);
		this.setRotateAngle(legBckRt, -0.17453292519943295F, -0.0F, 0.0F);
		this.legBckRt.setTextureOffset(52, 49).addBox(-1.59F, 1.5F, 0.0F, 3, 5, 3, 0.0F); // legBckRtHind
		legBckRtInfo = new PartInfo(legBckRt);

		this.bodyFufLftBckMain = new ModelRenderer(this, 19, 14);
		this.bodyFufLftBckMain.setRotationPoint(2.0F, -2.0F, 0.51F);
		this.bodyFufLftBckMain.addBox(-1.5F, -1.5F, -5.5F, 3, 3, 11, 0.0F);
		this.setRotateAngle(bodyFufLftBckMain, 0.0F, 0.0F, 0.7853981633974483F);
		this.bodyFurLftBckBase = new ModelRenderer(this, 28, 0);
		this.bodyFurLftBckBase.setRotationPoint(2.2F, -2.5F, 5.5F);
		this.bodyFurLftBckBase.addBox(-1.5F, -1.0F, -0.5F, 3, 2, 6, 0.0F);
		this.setRotateAngle(bodyFurLftBckBase, -0.7853981633974483F, 0.4363323129985824F, 0.0F);
		bodyFurLftBckBaseInfo = new PartInfo(bodyFurLftBckBase);
		this.bodyFurLftBckBck = new ModelRenderer(this, 37, 16);
		this.bodyFurLftBckBck.setRotationPoint(0.0F, 0.0F, 5.0F);
		this.bodyFurLftBckBck.addBox(-1.0F, -0.5F, 0.0F, 2, 1, 3, 0.0F);
		this.setRotateAngle(bodyFurLftBckBck, 0.4363323129985824F, -0.17453292519943295F, 0.0F);
		bodyFurLftBckBckInfo = new PartInfo(bodyFurLftBckBck);
		this.bodyFurLftBckMid = new ModelRenderer(this, 38, 12);
		this.bodyFurLftBckMid.setRotationPoint(0.0F, 0.5F, 2.0F);
		this.bodyFurLftBckMid.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(bodyFurLftBckMid, -0.6108652381980153F, 0.0F, 0.0F);
		bodyFurLftBckMidInfo = new PartInfo(bodyFurLftBckMid);
		this.bodyFurLftBckFnt = new ModelRenderer(this, 38, 8);
		this.bodyFurLftBckFnt.setRotationPoint(0.0F, 0.5F, 3.5F);
		this.bodyFurLftBckFnt.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(bodyFurLftBckFnt, -0.3490658503988659F, 0.0F, 0.0F);
		bodyFurLftBckFntInfo = new PartInfo(bodyFurLftBckFnt);
		this.bodyFurLftBckTop = new ModelRenderer(this, 31, 9);
		this.bodyFurLftBckTop.setRotationPoint(0.0F, -0.5F, 0.0F);
		this.bodyFurLftBckTop.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 4, 0.0F);
		this.setRotateAngle(bodyFurLftBckTop, 1.1344640137963142F, -0.17453292519943295F, 0.0F);
		bodyFurLftBckTopInfo = new PartInfo(bodyFurLftBckTop);

		this.bodyFufRtBckMain = new ModelRenderer(this, 36, 10);
		this.bodyFufRtBckMain.setRotationPoint(-2.0F, -2.0F, 0.51F);
		this.bodyFufRtBckMain.addBox(-1.5F, -1.5F, -5.5F, 3, 3, 11, 0.0F);
		this.setRotateAngle(bodyFufRtBckMain, 0.0F, 0.0F, -0.7853981633974483F);
		this.bodyFurRtBckBase = new ModelRenderer(this, 46, 0);
		this.bodyFurRtBckBase.setRotationPoint(-2.2F, -2.5F, 5.5F);
		this.bodyFurRtBckBase.addBox(-1.5F, -1.0F, 0.0F, 3, 2, 6, 0.0F);
		this.setRotateAngle(bodyFurRtBckBase, -0.7853981633974483F, -0.4363323129985824F, 0.0F);
		bodyFurRtBckBaseInfo = new PartInfo(bodyFurRtBckBase);
		this.bodyFurRtBckBck = new ModelRenderer(this, 54, 8);
		this.bodyFurRtBckBck.setRotationPoint(0.0F, 0.0F, 5.0F);
		this.bodyFurRtBckBck.addBox(-1.0F, -0.5F, 0.0F, 2, 1, 3, 0.0F);
		this.setRotateAngle(bodyFurRtBckBck, 0.4363323129985824F, 0.17453292519943295F, 0.0F);
		bodyFurRtBckBckInfo = new PartInfo(bodyFurRtBckBck);
		this.bodyFurRtBckMid = new ModelRenderer(this, 55, 12);
		this.bodyFurRtBckMid.setRotationPoint(0.0F, 0.5F, 2.0F);
		this.bodyFurRtBckMid.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(bodyFurRtBckMid, -0.6108652381980153F, 0.0F, 0.0F);
		bodyFurRtBckMidInfo = new PartInfo(bodyFurRtBckMid);
		this.bodyFurRtBckFnt = new ModelRenderer(this, 56, 15);
		this.bodyFurRtBckFnt.setRotationPoint(0.0F, 0.5F, 3.5F);
		this.bodyFurRtBckFnt.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(bodyFurRtBckFnt, -0.3490658503988659F, 0.0F, 0.0F);
		bodyFurRtBckFntInfo = new PartInfo(bodyFurRtBckFnt);
		this.bodyFurRtBckTop = new ModelRenderer(this, 42, 0);
		this.bodyFurRtBckTop.setRotationPoint(0.0F, -0.5F, 0.0F);
		this.bodyFurRtBckTop.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 4, 0.0F);
		this.setRotateAngle(bodyFurRtBckTop, 1.1344640137963142F, 0.17453292519943295F, 0.0F);
		bodyFurRtBckTopInfo = new PartInfo(bodyFurRtBckTop);

		this.headFaceJoint = new ModelRenderer(this, 0, 11);
		this.headFaceJoint.setRotationPoint(0.0F, 0.0F, -1.0F);
		this.headFaceJoint.addBox(-2.51F, -2.9F, -6.6F, 0, 0, 0, 0.0F);
		this.setRotateAngle(headFaceJoint, 0.3490658503988659F, -0.0F, 0.0F);
		headFaceJointInfo = new PartInfo(headFaceJoint);
		this.headFace = new ModelRenderer(this, 0, 11);
		this.headFace.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.headFace.addBox(-2.51F, -3.1F, -5.65F, 5, 5, 1, 0.0F);
		headFaceInfo = new PartInfo(headFace);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-2.5F, -4.0F, -5.0F, 5, 5, 5, 0.0F);
		this.setRotateAngle(head, 0.17453292519943295F, -0.0F, 0.0F);
		this.headMuzzle = new ModelRenderer(this, 12, 11);
		this.headMuzzle.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.headMuzzle.addBox(-1.0F, -1.6F, -8.2F, 2, 2, 3, 0.0F);
		this.setRotateAngle(headMuzzle, 0.17453292519943295F, -0.0F, 0.0F);
		this.headMuzzle.setTextureOffset(15, 17).addBox(-0.5F, -1.5F, -8.7F, 1, 1, 1, 0.0F); // headNose

		this.headJawJoint = new ModelRenderer(this, 20, 9);
		this.headJawJoint.setRotationPoint(0.0F, 1.1F, -5.4F);
		this.headJawJoint.addBox(-1.01F, 0.1F, -2.6F, 0, 0, 0, 0.0F);
		this.setRotateAngle(headJawJoint, 0.08726646259971647F, -0.0F, 0.0F);
		headJawJointInfo = new PartInfo(headJawJoint);
		this.headJaw = new ModelRenderer(this, 20, 9);
		this.headJaw.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.headJaw.addBox(-1.01F, 0.1F, -2.6F, 2, 1, 3, 0.0F);
		this.setRotateAngle(headJaw, 0.08726646259971647F, -0.0F, 0.0F);
		headJawInfo = new PartInfo(headJaw);

		this.headEarRtJoint = new ModelRenderer(this, 10, 28);
		this.headEarRtJoint.setRotationPoint(-2.0F, -2.5F, -3.0F);
		this.headEarRtJoint.addBox(-1.5F, -2.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(headEarRtJoint, 0.17453292519943295F, 0.0F, 0.0F);
		headEarRtJointInfo = new PartInfo(headEarRtJoint);
		this.headEarRt = new ModelRenderer(this, 10, 28);
		this.headEarRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.headEarRt.addBox(-1.5F, -2.5F, -0.5F, 3, 3, 1, 0.0F);
		this.headEarRt.setTextureOffset(11, 25).addBox(-1.3F, -3.5F, -0.5F, 2, 1, 1, 0.0F); // headEarRtMid
		this.headEarRt.setTextureOffset(12, 22).addBox(-1.1F, -4.5F, -0.5F, 1, 1, 1, 0.0F); // headEarRtTip
		headEarRtInfo = new PartInfo(headEarRt);
		this.headEarLftJoint = new ModelRenderer(this, 0, 28);
		this.headEarLftJoint.setRotationPoint(2.0F, -2.5F, -3.0F);
		this.headEarLftJoint.addBox(-1.5F, -2.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(headEarLftJoint, 0.17453292519943295F, -0.0F, 0.0F);
		headEarLftJointInfo = new PartInfo(headEarLftJoint);
		this.headEarLft = new ModelRenderer(this, 0, 28);
		this.headEarLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.headEarLft.addBox(-1.5F, -2.5F, -0.5F, 3, 3, 1, 0.0F);
		this.headEarLft.setTextureOffset(1, 25).addBox(-0.8F, -3.5F, -0.5F, 2, 1, 1, 0.0F); // headEarLftMid
		this.headEarLft.setTextureOffset(2, 22).addBox(-0.1F, -4.5F, -0.5F, 1, 1, 1, 0.0F); // headEarLftTip
		headEarLftInfo = new PartInfo(headEarLft);

		this.headLowerFaceJoint = new ModelRenderer(this, 17, 35);
		this.headLowerFaceJoint.setRotationPoint(0.0F, 2.0F, -5.5F);
		this.headLowerFaceJoint.addBox(-2.01F, -1.9F, -4.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(headLowerFaceJoint, 0.08726646259971647F, -0.0F, 0.0F);
		headLowerFaceJointInfo = new PartInfo(headLowerFaceJoint);
		this.headLowerFace = new ModelRenderer(this, 17, 35);
		this.headLowerFace.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.headLowerFace.addBox(-2.01F, -1.9F, -4.0F, 4, 4, 1, 0.0F);
		headLowerFaceInfo = new PartInfo(headLowerFace);
		this.headLower = new ModelRenderer(this, 0, 39);
		this.headLower.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.headLower.addBox(-2.0F, -2.5F, -3.5F, 4, 4, 4, 0.0F);
		this.setRotateAngle(headLower, 0.17453292519943295F, -0.0F, 0.0F);
		this.headLowerMuzzle = new ModelRenderer(this, 6, 34);
		this.headLowerMuzzle.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.headLowerMuzzle.addBox(-1.0F, -0.9F, -5.63F, 2, 2, 3, 0.0F);
		this.setRotateAngle(headLowerMuzzle, 0.24434609527920614F, -0.0F, 0.0F);
		this.headLowerMuzzle.setTextureOffset(13, 33).addBox(-0.5F, -0.8F, -6.0F, 1, 1, 1, 0.0F); // headLowerNose

		this.headLowerJawJoint = new ModelRenderer(this, 0, 34);
		this.headLowerJawJoint.setRotationPoint(0.0F, 1.4F, -3.3F);
		this.headLowerJawJoint.addBox(-1.01F, 0.0F, -2.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(headLowerJawJoint, 0.17453292519943295F, 0.0F, 0.0F);
		headLowerJawJointInfo = new PartInfo(headLowerJawJoint);
		this.headLowerJaw = new ModelRenderer(this, 0, 34);
		this.headLowerJaw.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.headLowerJaw.addBox(-1.01F, 0.0F, -2.0F, 2, 1, 2, 0.0F);
		headLowerJawInfo = new PartInfo(headLowerJaw);

		int tailMainNumber = 0;
		this.tailMain[tailMainNumber][jointNumber] = new ModelRenderer(this, 0, 54); // tailMainBaseJoint
		this.tailMain[tailMainNumber][jointNumber].setRotationPoint(0.0F, -2.5F, 5.5F);
		this.tailMain[tailMainNumber][jointNumber].addBox(-1.0F, -1.0F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tailMain[tailMainNumber][jointNumber], 0.5235987755982988F, 0.0F, 0.0F);
		tailMainInfo[tailMainNumber][jointNumber] = new PartInfo(tailMain[tailMainNumber][jointNumber]);
		this.tailMain[tailMainNumber][partNumber] = new ModelRenderer(this, 0, 54); // tailMainBase
		this.tailMain[tailMainNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tailMain[tailMainNumber][partNumber].addBox(-1.0F, -1.0F, -1.0F, 2, 2, 8, 0.0F);
		tailMainInfo[tailMainNumber][partNumber] = new PartInfo(tailMain[tailMainNumber][partNumber]);
		tailMainNumber++;
		this.tailMain[tailMainNumber][jointNumber] = new ModelRenderer(this, 12, 54); // tailMainEndJoint
		this.tailMain[tailMainNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 6.4F);
		this.tailMain[tailMainNumber][jointNumber].addBox(-1.0F, -1.0F, -0.7F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tailMain[tailMainNumber][jointNumber], -1.3089969389957472F, 0.0F, 0.0F);
		tailMainInfo[tailMainNumber][jointNumber] = new PartInfo(tailMain[tailMainNumber][jointNumber]);
		this.tailMain[tailMainNumber][partNumber] = new ModelRenderer(this, 12, 54); // tailMainEnd
		this.tailMain[tailMainNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tailMain[tailMainNumber][partNumber].addBox(-1.0F, -1.0F, -0.7F, 2, 2, 6, 0.0F);
		tailMainInfo[tailMainNumber][partNumber] = new PartInfo(tailMain[tailMainNumber][partNumber]);

		int tailTipsNumber = 0;
		this.tailTips[tailTipsNumber][jointNumber] = new ModelRenderer(this, 16, 40); // tail0Joint
		this.tailTips[tailTipsNumber][jointNumber].setRotationPoint(0.0F, 0.6F, 0.7F);
		this.tailTips[tailTipsNumber][jointNumber].addBox(-0.5F, -0.5F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tailTips[tailTipsNumber][jointNumber], -0.7853981633974483F, 0.0F, 0.0F);
		tailTipsInfo[tailTipsNumber][jointNumber] = new PartInfo(tailTips[tailTipsNumber][jointNumber]);
		this.tailTips[tailTipsNumber][partNumber] = new ModelRenderer(this, 16, 40); // tail0
		this.tailTips[tailTipsNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tailTips[tailTipsNumber][partNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 5, 0.0F);
		tailTipsInfo[tailTipsNumber][partNumber] = new PartInfo(tailTips[tailTipsNumber][partNumber]);
		tailTipsNumber++;
		this.tailTips[tailTipsNumber][jointNumber] = new ModelRenderer(this, 14, 46); // tail1Joint
		this.tailTips[tailTipsNumber][jointNumber].setRotationPoint(0.0F, 0.6F, 2.0F);
		this.tailTips[tailTipsNumber][jointNumber].addBox(-0.5F, -0.5F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tailTips[tailTipsNumber][jointNumber], -0.6108652381980153F, 0.0F, 0.0F);
		tailTipsInfo[tailTipsNumber][jointNumber] = new PartInfo(tailTips[tailTipsNumber][jointNumber]);
		this.tailTips[tailTipsNumber][partNumber] = new ModelRenderer(this, 14, 46); // tail1
		this.tailTips[tailTipsNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tailTips[tailTipsNumber][partNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 5, 0.0F);
		tailTipsInfo[tailTipsNumber][partNumber] = new PartInfo(tailTips[tailTipsNumber][partNumber]);
		tailTipsNumber++;
		this.tailTips[tailTipsNumber][jointNumber] = new ModelRenderer(this, 7, 47); // tail2Joint
		this.tailTips[tailTipsNumber][jointNumber].setRotationPoint(0.0F, 0.5F, 3.5F);
		this.tailTips[tailTipsNumber][jointNumber].addBox(-0.5F, -0.5F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tailTips[tailTipsNumber][jointNumber], -0.3490658503988659F, 0.0F, 0.0F);
		tailTipsInfo[tailTipsNumber][jointNumber] = new PartInfo(tailTips[tailTipsNumber][jointNumber]);
		this.tailTips[tailTipsNumber][partNumber] = new ModelRenderer(this, 7, 47); // tail2
		this.tailTips[tailTipsNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tailTips[tailTipsNumber][partNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 5, 0.0F);
		tailTipsInfo[tailTipsNumber][partNumber] = new PartInfo(tailTips[tailTipsNumber][partNumber]);
		tailTipsNumber++;
		this.tailTips[tailTipsNumber][jointNumber] = new ModelRenderer(this, 0, 48); // tail3Joint
		this.tailTips[tailTipsNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 4.7F);
		this.tailTips[tailTipsNumber][jointNumber].addBox(-0.5F, -0.5F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tailTips[tailTipsNumber][jointNumber], 0.17453292519943295F, 0.0F, 0.0F);
		tailTipsInfo[tailTipsNumber][jointNumber] = new PartInfo(tailTips[tailTipsNumber][jointNumber]);
		this.tailTips[tailTipsNumber][partNumber] = new ModelRenderer(this, 0, 48); // tail3
		this.tailTips[tailTipsNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tailTips[tailTipsNumber][partNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 5, 0.0F);
		tailTipsInfo[tailTipsNumber][partNumber] = new PartInfo(tailTips[tailTipsNumber][partNumber]);

		this.body.addChild(this.legFntLft);
		this.body.addChild(this.legFntRt);
		this.body.addChild(this.legBckLft);
		this.body.addChild(this.legBckRt);
		this.body.addChild(this.bodyFufLftBckMain);
		this.body.addChild(this.bodyFufRtBckMain);
		this.body.addChild(this.bodyFurLftBckBase);
		this.body.addChild(this.bodyFurRtBckBase);
		this.body.addChild(this.neckJoint);
		this.body.addChild(this.headLowerFaceJoint);
		this.body.addChild(this.tailMain[0][0]); // tailMainBaseJoint

		this.bodyFurLftBckBase.addChild(this.bodyFurLftBckBck);
		this.bodyFurLftBckBase.addChild(this.bodyFurLftBckFnt);
		this.bodyFurLftBckBase.addChild(this.bodyFurLftBckMid);
		this.bodyFurLftBckBase.addChild(this.bodyFurLftBckTop);
		this.bodyFurRtBckBase.addChild(this.bodyFurRtBckBck);
		this.bodyFurRtBckBase.addChild(this.bodyFurRtBckFnt);
		this.bodyFurRtBckBase.addChild(this.bodyFurRtBckMid);
		this.bodyFurRtBckBase.addChild(this.bodyFurRtBckTop);

		this.neckFurLftJoint.addChild(this.neckFurLft);
		this.neckFurRtJoint.addChild(this.neckFurRt);

		this.neckJoint.addChild(this.neck);
		this.neck.addChild(this.headFaceJoint);
		this.neck.addChild(this.neckFurLftJoint);
		this.neck.addChild(this.neckFurRtJoint);

		this.headFaceJoint.addChild(this.headFace);
		this.headFace.addChild(this.head);
		this.headFace.addChild(this.headEarLftJoint);
		this.headFace.addChild(this.headEarRtJoint);
		this.headFace.addChild(this.headJawJoint);
		this.headFace.addChild(this.headMuzzle);
		this.headJawJoint.addChild(this.headJaw);

		this.headEarLftJoint.addChild(this.headEarLft);
		this.headEarRtJoint.addChild(this.headEarRt);

		this.headLowerFaceJoint.addChild(this.headLowerFace);
		this.headLowerFace.addChild(this.headLower);
		this.headLowerFace.addChild(this.headLowerMuzzle);
		this.headLowerFace.addChild(this.headLowerJawJoint);
		this.headLowerJawJoint.addChild(this.headLowerJaw);

		this.tailMain[0][0].addChild(this.tailMain[0][1]); // tailMainBaseJoint * tailMainBase
		this.tailMain[0][1].addChild(this.tailMain[1][0]); // tailMainBase * tailMainEndJoint
		this.tailMain[1][0].addChild(this.tailMain[1][1]); // tailMainEndJoint * tailMainEnd

		this.tailMain[1][1].addChild(this.tailTips[0][0]); // tailMainEnd * tail0Joint
		this.tailMain[1][1].addChild(this.tailTips[1][0]); // tailMainEnd * tail1Joint
		this.tailMain[1][1].addChild(this.tailTips[2][0]); // tailMainEnd * tail2Joint
		this.tailMain[1][1].addChild(this.tailTips[3][0]); // tailMainEnd * tail3Joint

		this.tailTips[0][0].addChild(this.tailTips[0][1]); // tail0Joint * tail0
		this.tailTips[1][0].addChild(this.tailTips[1][1]); // tail1Joint * tail1
		this.tailTips[2][0].addChild(this.tailTips[2][1]); // tail2Joint * tail2
		this.tailTips[3][0].addChild(this.tailTips[3][1]); // tail3Joint * tail3

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

		float angularVelocity = ((IMobMotionTracker)entity).getAngularVelocity();
		float verticalVelocity = (float)((IMobMotionTracker)entity).getHeightVelocity();

		float sizeModifier = 1F;//(float) Math.sqrt(((EntityOkamiEspeon) entity).defaultHeight / entity.height);

		animateBody((VoorstMightyenaEntity)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, verticalVelocity);
		animateHead((VoorstMightyenaEntity)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, verticalVelocity);
		animateLegs((VoorstMightyenaEntity)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, verticalVelocity);
		animateTail((VoorstMightyenaEntity)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, verticalVelocity, angularVelocity);

		deployAnimations();
	}

	public void resetPartInfos()
	{
		bodyInfo.resetNewAngles();

		legFntLftInfo.resetNewAngles();
		legFntRtInfo.resetNewAngles();
		legBckLftInfo.resetNewAngles();
		legBckRtInfo.resetNewAngles();

		neckJointInfo.resetNewAngles();
		neckInfo.resetNewAngles();
		headFaceJointInfo.resetNewAngles();
		headFaceInfo.resetNewAngles();
		headLowerFaceJointInfo.resetNewAngles();
		headLowerFaceInfo.resetNewAngles();

		headEarRtInfo.resetNewAngles();
		headEarLftInfo.resetNewAngles();

		neckFurLftJointInfo.resetNewAngles();
		neckFurLftInfo.resetNewAngles();
		neckFurRtJointInfo.resetNewAngles();
		neckFurRtInfo.resetNewAngles();

		headJawInfo.resetNewAngles();
		headLowerJawInfo.resetNewAngles();

		for(int i = 0; i < tailMain.length; i++)
		{
			tailMainInfo[i][0].resetNewAngles();
			tailMainInfo[i][1].resetNewAngles();
		}

		for(int i = 0; i < tailTips.length; i++)
		{
			tailTipsInfo[i][0].resetNewAngles();
			tailTipsInfo[i][1].resetNewAngles();
		}
	}

	public void animateBody(VoorstMightyenaEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float verticalVelocity)
	{
		float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
		float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);

		float bodyWalkAngle = (float)Math.toRadians(8);
		float bodyRunAngle = (float)Math.toRadians(12);

		float walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI + PI/2) * bodyWalkAngle * (1 - horzVelocity);
		float runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI + PI/2) * bodyRunAngle * horzVelocity;

		float bodyJumpAngle = (float)Math.toRadians(-65);

		float newVerticalVelocity = 1.5F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;

		bodyInfo.setNewRotateX(bodyInfo.getNewRotateX() - (walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity)) 
				+ bodyJumpAngle * newVerticalVelocity);

		float amplitude = 0.3F;

		this.bodyFurLftBckBase.rotateAngleY = bodyFurLftBckBaseInfo.getNewRotateY() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, RUN_FREQUENCY, amplitude);
		this.bodyFurRtBckBase.rotateAngleY = bodyFurRtBckBaseInfo.getNewRotateY() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, RUN_FREQUENCY, amplitude);
		this.bodyFurLftBckBck.rotateAngleY = bodyFurLftBckBckInfo.getNewRotateY() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, RUN_FREQUENCY, amplitude);
		this.bodyFurRtBckBck.rotateAngleY = bodyFurRtBckBckInfo.getNewRotateY() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, RUN_FREQUENCY, amplitude);
		amplitude = amplitude * 2.5F;
		this.bodyFurLftBckMid.rotateAngleY = bodyFurLftBckMidInfo.getNewRotateY() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, RUN_FREQUENCY, amplitude);
		this.bodyFurRtBckMid.rotateAngleY = bodyFurRtBckMidInfo.getNewRotateY() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, RUN_FREQUENCY, amplitude);
		this.bodyFurLftBckFnt.rotateAngleY = bodyFurLftBckFntInfo.getNewRotateY() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, RUN_FREQUENCY, amplitude);
		this.bodyFurRtBckFnt.rotateAngleY = bodyFurRtBckFntInfo.getNewRotateY() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, RUN_FREQUENCY, amplitude);
		this.bodyFurLftBckTop.rotateAngleY = bodyFurLftBckTopInfo.getNewRotateY() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, RUN_FREQUENCY, amplitude);
		this.bodyFurRtBckTop.rotateAngleY = bodyFurRtBckTopInfo.getNewRotateY() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, RUN_FREQUENCY, amplitude);
		
		bodyJumpAngle *= (newVerticalVelocity < 0 ? 0.65F : 0.25F);
		
		this.bodyFurLftBckBase.rotateAngleX = bodyFurLftBckBaseInfo.getNewRotateX() + bodyJumpAngle * newVerticalVelocity;
		this.bodyFurRtBckBase.rotateAngleX = bodyFurRtBckBaseInfo.getNewRotateX() + bodyJumpAngle * newVerticalVelocity;
		this.bodyFurLftBckBck.rotateAngleX = bodyFurLftBckBckInfo.getNewRotateX() + bodyJumpAngle * newVerticalVelocity;
		this.bodyFurRtBckBck.rotateAngleX = bodyFurRtBckBckInfo.getNewRotateX() + bodyJumpAngle * newVerticalVelocity;
				
		this.bodyFurLftBckMid.rotateAngleX = bodyFurLftBckMidInfo.getNewRotateX() + bodyJumpAngle * newVerticalVelocity;
		this.bodyFurRtBckMid.rotateAngleX = bodyFurRtBckMidInfo.getNewRotateX() + bodyJumpAngle * newVerticalVelocity;
		this.bodyFurLftBckFnt.rotateAngleX = bodyFurLftBckFntInfo.getNewRotateX() + bodyJumpAngle * newVerticalVelocity;
		this.bodyFurRtBckFnt.rotateAngleX = bodyFurRtBckFntInfo.getNewRotateX() + bodyJumpAngle * newVerticalVelocity;
		this.bodyFurLftBckTop.rotateAngleX = bodyFurLftBckTopInfo.getNewRotateX() + bodyJumpAngle * newVerticalVelocity;
		this.bodyFurRtBckTop.rotateAngleX = bodyFurRtBckTopInfo.getNewRotateX() + bodyJumpAngle * newVerticalVelocity;
	}

	public void animateHead(VoorstMightyenaEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float verticalVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, neckJointInfo);
		
		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockNeckBobble();

		float idleAmplitudeX = 0.1F;

		// + (float)Math.toRadians(45)
		float movementAmplitude = 0.25F;
		float angleChangeX = PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, RUN_FREQUENCY, movementAmplitude);

		angleChangeX += -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeX + idleAmplitudeX;

		neckInfo.setNewRotateX(neckInfo.getNewRotateX() + angleChangeX);
		neckInfo.setNewRotateY(neckInfo.getNewRotateY());

		JointAnimation.reverseJointRotatesChange(neckInfo, headFaceJointInfo);

		PartAnimate.headAnimateInfoOnly(headFaceInfo, netHeadYaw, headPitch);
		PartAnimate.headAnimateInfoOnlyWithAngleModifiers(headLowerFaceInfo, netHeadYaw, headPitch, 0.7F, 0.7F);

		if(headFaceInfo.getNewRotateX() > 0)
			headFaceInfo.setNewRotateX(headFaceInfo.getNewRotateX() * 0.7F);

		JointAnimation.reverseJointRotatesChangeXWithModifier(bodyInfo, headLowerFaceInfo, 0.85F);

		/* ******* Ears ******** */
		float moveAmplitudeZ = 0.15F;

		float firstAngleChangeZ = PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, RUN_FREQUENCY, moveAmplitudeZ);

		float idleAmplitudeZ = 0.20F;

		// + (float)Math.toRadians(45)
		float angleChangeZ = firstAngleChangeZ;

		angleChangeZ += MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI + PI) * idleAmplitudeZ;

		headEarRtInfo.setNewRotateZ(headEarRtInfo.getNewRotateZ() - angleChangeZ - (float)Math.toRadians(10));
		headEarLftInfo.setNewRotateZ(headEarLftInfo.getNewRotateZ() + angleChangeZ + (float)Math.toRadians(10));

		/* ******* Neck Fur ******** */
		angleChangeZ = angleChangeZ * 0.5F;

		neckFurLftInfo.setNewRotateY(neckFurLftInfo.getNewRotateY() + angleChangeZ);
		neckFurLftInfo.setNewRotateZ(neckFurLftInfo.getNewRotateZ() + angleChangeZ);
		neckFurRtInfo.setNewRotateY(neckFurRtInfo.getNewRotateY() - angleChangeZ);
		neckFurRtInfo.setNewRotateZ(neckFurRtInfo.getNewRotateZ() - angleChangeZ);

		/* ******* Main Jaw ******** */
		currentIdleAnimationClock = entity.getIdleAnimationClockJawMain();

		idleAmplitudeX = 0.2F;

		// + (float)Math.toRadians(45)
		angleChangeX = 0;

		if(currentIdleAnimationClock.getPhaseDurationCoveredX(0) < 1F)
		{
			angleChangeX += -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeX + idleAmplitudeX;
		}
		else if(currentIdleAnimationClock.getPhaseDurationCoveredX(1) < 1F)
		{
			angleChangeX += -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(1) * 2 * PI) * idleAmplitudeX + idleAmplitudeX;
		}
		else
		{
			angleChangeX = 0;

			int randomInt = entity.world.rand.nextInt(3);

			if(randomInt == 1)
			{
				currentIdleAnimationClock.setClockX(currentIdleAnimationClock.getClockX() - 1); 
				entity.setIdleAnimationClockJawMain(currentIdleAnimationClock);
			}
		}

		float newAngleX = headJawInfo.getNewRotateX() + angleChangeX;

		headJawInfo.setNewRotateX(newAngleX);

		/* ******* Lower Jaw ******** */
		currentIdleAnimationClock = entity.getIdleAnimationClockJawLower();

		idleAmplitudeX = 0.2F;
		float idleJitterAmplitudeX = 0.1F;

		// + (float)Math.toRadians(45)
		angleChangeX = 0.5F;

		if(currentIdleAnimationClock.getPhaseDurationCoveredX(0) < 1F)
		{
			angleChangeX += -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI) * idleAmplitudeX + idleAmplitudeX;

			angleChangeX += -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * 5 * PI) * idleJitterAmplitudeX + idleJitterAmplitudeX;
		}
		else
		{
			angleChangeX += -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(1) * PI + PI) * idleAmplitudeX + idleAmplitudeX;
		}

		newAngleX = headLowerJawInfo.getNewRotateX() + angleChangeX;

		headLowerJawInfo.setNewRotateX(newAngleX);
	}

	public void animateLegs(VoorstMightyenaEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float verticalVelocity)
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

		legFntRtInfo.setNewRotateX(legFntRtInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legFntRtModifier, newHorizontalVelocity, this.RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)) + fallingLegAngle * fallingModifier);
		legFntLftInfo.setNewRotateX(legFntLftInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, newHorizontalVelocity, this.RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)) + fallingLegAngle * fallingModifier);
		legBckRtInfo.setNewRotateX(legBckRtInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legBckRtModifier, newHorizontalVelocity, this.RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)));
		legBckLftInfo.setNewRotateX(legBckLftInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legBckLftModifier, newHorizontalVelocity, this.RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)));
	}

	public void animateTail(VoorstMightyenaEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float verticalVelocity, float angularVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, tailMainInfo[0][0]);

		float yawChangeAngle = -(float)Math.toRadians(15);
		float verticalVelocityChangeAngle = (float)Math.toRadians(-9);

		float moveAmplitudeX = (float)Math.toRadians(8);

		float idleAmplitudeX = 0.18F;
		float idleAmplitudeY = 0.25F;

		float newVerticalVelocity = 1.5F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;

		float absoluteMoveVelocity = horzVelocity + Math.abs(newVerticalVelocity);

		if(absoluteMoveVelocity > 1F)
			absoluteMoveVelocity = 1F;

		for(int i = 0; i < tailMain.length; i++)
		{
			IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockTail(i);

			// + (float)Math.toRadians(45)
			float angleChangeX = 0;
			float angleChangeY = 0;

			// Idle Animations
			angleChangeX += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2) * idleAmplitudeX 
					* (1F - absoluteMoveVelocity) * (1F - Math.abs(angularVelocity) * 0.5F);
			angleChangeY += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2) * idleAmplitudeY
					* (1F - absoluteMoveVelocity) * (1F - Math.abs(angularVelocity) * 0.5F);

			// Movement Animations
			angleChangeX += PartAnimate.negCosRotateAnimationAdjusted(distanceMoved - (2F * PI * ((float)i / (float)(tailMain.length - 1))), absoluteMoveVelocity, RUN_FREQUENCY, moveAmplitudeX) 
					+ verticalVelocityChangeAngle * newVerticalVelocity * (newVerticalVelocity < 0 ? 8F : 1F);
			angleChangeY += angularVelocity * yawChangeAngle;

			tailMainInfo[i][1].setNewRotateX(tailMainInfo[i][1].getNewRotateX() + angleChangeX);
			tailMainInfo[i][1].setNewRotateY(tailMainInfo[i][1].getNewRotateY() + angleChangeY);
		}

		for(int i = 0; i < tailTips.length; i++)
		{
			IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockTail(i + 2);

			// + (float)Math.toRadians(45)
			float angleChangeX = 0;
			float angleChangeY = 0;

			angleChangeX += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2) * idleAmplitudeX
					+ PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, RUN_FREQUENCY, moveAmplitudeX);
			angleChangeY += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2) * idleAmplitudeY;

			tailTipsInfo[i][1].setNewRotateX(tailTipsInfo[i][1].getNewRotateX() + angleChangeX);
			tailTipsInfo[i][1].setNewRotateY(tailTipsInfo[i][1].getNewRotateY() + angleChangeY * 1.8F);
		}
	}

	public void deployAnimations()
	{
		this.animationDeployer.rotate(body, bodyInfo.getNewRotates());

		this.animationDeployer.rotate(legFntLft, legFntLftInfo.getNewRotates());
		this.animationDeployer.rotate(legFntRt, legFntRtInfo.getNewRotates());
		this.animationDeployer.rotate(legBckLft, legBckLftInfo.getNewRotates());
		this.animationDeployer.rotate(legBckRt, legBckRtInfo.getNewRotates());

		this.animationDeployer.rotate(neckJoint, neckJointInfo.getNewRotates());
		this.animationDeployer.rotate(neck, neckInfo.getNewRotates());
		this.animationDeployer.rotate(headFaceJoint, headFaceJointInfo.getNewRotates());
		this.animationDeployer.rotate(headFace, headFaceInfo.getNewRotates());

		this.animationDeployer.rotate(headLowerFaceJoint, headLowerFaceJointInfo.getNewRotates());
		this.animationDeployer.rotate(headLowerFace, headLowerFaceInfo.getNewRotates());

		this.animationDeployer.rotate(headEarLft, headEarLftInfo.getNewRotates());
		this.animationDeployer.rotate(headEarLftJoint, headEarLftJointInfo.getNewRotates());
		this.animationDeployer.rotate(headEarRt, headEarRtInfo.getNewRotates());
		this.animationDeployer.rotate(headEarRtJoint, headEarRtJointInfo.getNewRotates());

		this.animationDeployer.rotate(neckFurLftJoint, neckFurLftJointInfo.getNewRotates());
		this.animationDeployer.rotate(neckFurLft, neckFurLftInfo.getNewRotates());
		this.animationDeployer.rotate(neckFurRtJoint, neckFurRtJointInfo.getNewRotates());
		this.animationDeployer.rotate(neckFurRt, neckFurRtInfo.getNewRotates());

		this.animationDeployer.rotate(headJaw, headJawInfo.getNewRotates());
		this.animationDeployer.rotate(headLowerJaw, headLowerJawInfo.getNewRotates());

		for(int i = 0; i < tailMain.length; i++)
		{
			this.animationDeployer.rotate(tailMain[i][0], tailMainInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(tailMain[i][1], tailMainInfo[i][1].getNewRotates());
		}

		for(int i = 0; i < tailTips.length; i++)
		{
			this.animationDeployer.rotate(tailTips[i][0], tailTipsInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(tailTips[i][1], tailTipsInfo[i][1].getNewRotates());
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