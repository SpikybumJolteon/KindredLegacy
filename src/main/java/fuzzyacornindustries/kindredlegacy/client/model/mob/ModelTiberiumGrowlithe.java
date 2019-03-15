package fuzzyacornindustries.kindredlegacy.client.model.mob;

import fuzzyacornindustries.kindredlegacy.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.animation.JointAnimation;
import fuzzyacornindustries.kindredlegacy.animation.PartAnimate;
import fuzzyacornindustries.kindredlegacy.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.entity.mob.IMobMotionTracker;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityTiberiumGrowlithe;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelTiberiumGrowlithe extends ModelBase 
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public final float RUN_FREQUENCY = 1.2F;
	public final float WALK_FREQUENCY = 1.2F;
	public final float SNEAK_VELOCITY = 0.35F;

	public ModelRenderer body;
	PartInfo bodyInfo;

	public ModelRenderer bodyCrystalFntMid;
	public ModelRenderer bodyCrystalFntMidRt;
	public ModelRenderer bodyCrystalFntRt;
	public ModelRenderer bodyCrystalFntMidLft;
	public ModelRenderer bodyCrystalFntLft;

	public ModelRenderer bodyCrystalRow1Top;
	public ModelRenderer bodyCrystalRow1TopLft;
	public ModelRenderer bodyCrystalRow1TopRt;
	public ModelRenderer bodyCrystalRow1Lft;
	public ModelRenderer bodyCrystalRow1Rt;

	public ModelRenderer bodyCrystalRow2Lft;
	public ModelRenderer bodyCrystalRow2Rt;
	public ModelRenderer bodyCrystalRow2TopLft;
	public ModelRenderer bodyCrystalRow2TopRt;

	public ModelRenderer legFntRt;
	public ModelRenderer legFntLft;
	public ModelRenderer legBckLft;
	public ModelRenderer legBckLftCrystalTop;
	public ModelRenderer legBckLftCrystalBot;
	public ModelRenderer legBckRt;
	public ModelRenderer legBckRtCrystalTop;
	public ModelRenderer legBckRtCrystalBot;
	PartInfo legFntRtInfo;
	PartInfo legFntLftInfo;
	PartInfo legBckLftInfo;
	PartInfo legBckRtInfo;

	public ModelRenderer neckJoint;
	public ModelRenderer neck;
	PartInfo neckJointInfo;
	PartInfo neckInfo;

	public ModelRenderer headJoint;
	public ModelRenderer head;
	PartInfo headJointInfo;
	PartInfo headInfo;

	public ModelRenderer cheekCrystalLft;
	public ModelRenderer cheekCrystalRt;

	public ModelRenderer muzzle;

	public ModelRenderer foreheadCrystalMid;
	public ModelRenderer foreheadCrystalMidRt;
	public ModelRenderer foreheadCrystalMidLft;
	public ModelRenderer foreheadCrystalRt;
	public ModelRenderer foreheadCrystalLft;

	public ModelRenderer earLftJoint;
	public ModelRenderer earLft;
	public ModelRenderer earRtJoint;
	public ModelRenderer earRt;
	PartInfo earLftJointInfo;
	PartInfo earLftInfo;
	PartInfo earRtJointInfo;
	PartInfo earRtInfo;

	public ModelRenderer tail[][] = new ModelRenderer[6][2];
	PartInfo tailInfo[][] = new PartInfo[tail.length][tail[0].length];

	public ModelRenderer tailCrystalRow[][] = new ModelRenderer[4][6];
	PartInfo tailCrystalRowInfo[][] = new PartInfo[tailCrystalRow.length][tailCrystalRow[0].length];

	public ModelTiberiumGrowlithe() 
	{
		this.textureWidth = 64;
		this.textureHeight = 64;

		int jointNumber = 0;
		int partNumber = 1;

		this.body = new ModelRenderer(this, 36, 50);
		this.body.setRotationPoint(0.0F, 17.0F, 0.0F);
		this.body.addBox(-2.5F, -2.5F, -4.5F, 5, 5, 9, 0.0F);
		bodyInfo = new PartInfo(body);

		this.bodyCrystalFntRt = new ModelRenderer(this, 0, 37);
		this.bodyCrystalFntRt.setRotationPoint(-2.0F, -1.0F, -4.0F);
		this.bodyCrystalFntRt.addBox(-0.5F, -0.5F, -0.8F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyCrystalFntRt, -0.2617993877991494F, 0.0F, 0.5235987755982988F);
		this.bodyCrystalFntMidRt = new ModelRenderer(this, 5, 36);
		this.bodyCrystalFntMidRt.setRotationPoint(-1.0F, -0.5F, -4.0F);
		this.bodyCrystalFntMidRt.addBox(-0.5F, -0.5F, -0.8F, 1, 3, 1, 0.0F);
		this.setRotateAngle(bodyCrystalFntMidRt, -0.2617993877991494F, 0.0F, 0.17453292519943295F);
		this.bodyCrystalFntMid = new ModelRenderer(this, 10, 35);
		this.bodyCrystalFntMid.setRotationPoint(0.0F, 0.0F, -4.0F);
		this.bodyCrystalFntMid.addBox(-0.5F, -0.5F, -0.8F, 1, 4, 1, 0.0F);
		this.setRotateAngle(bodyCrystalFntMid, -0.2617993877991494F, 0.0F, 0.0F);
		this.bodyCrystalFntMidLft = new ModelRenderer(this, 15, 36);
		this.bodyCrystalFntMidLft.setRotationPoint(1.0F, -0.5F, -4.0F);
		this.bodyCrystalFntMidLft.addBox(-0.5F, -0.5F, -0.8F, 1, 3, 1, 0.0F);
		this.setRotateAngle(bodyCrystalFntMidLft, -0.2617993877991494F, 0.0F, -0.17453292519943295F);
		this.bodyCrystalFntLft = new ModelRenderer(this, 20, 37);
		this.bodyCrystalFntLft.setRotationPoint(2.0F, -1.0F, -4.0F);
		this.bodyCrystalFntLft.addBox(-0.5F, -0.5F, -0.8F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyCrystalFntLft, -0.2617993877991494F, 0.0F, -0.5235987755982988F);

		this.bodyCrystalRow1Top = new ModelRenderer(this, 13, 47);
		this.bodyCrystalRow1Top.setRotationPoint(0.0F, -2.0F, -2.3F);
		this.bodyCrystalRow1Top.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 3, 0.0F);
		this.setRotateAngle(bodyCrystalRow1Top, 0.8726646259971648F, 0.0F, 0.0F);
		this.bodyCrystalRow1Top.setTextureOffset(13, 42).addBox(-0.5F, -1.0F, 2.5F, 1, 1, 3, 0.0F); // bodyCrystalRow1TopTip
		this.bodyCrystalRow1TopLft = new ModelRenderer(this, 21, 51);
		this.bodyCrystalRow1TopLft.setRotationPoint(2.0F, -2.0F, -1.7F);
		this.bodyCrystalRow1TopLft.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 2, 0.0F);
		this.setRotateAngle(bodyCrystalRow1TopLft, 0.8726646259971648F, 0.0F, 0.7853981633974483F);
		this.bodyCrystalRow1TopLft.setTextureOffset(19, 45).addBox(-0.5F, -1.0F, 1.5F, 1, 1, 3, 0.0F); // bodyCrystalRow1TopLftTip
		this.bodyCrystalRow1TopRt = new ModelRenderer(this, 7, 51);
		this.bodyCrystalRow1TopRt.setRotationPoint(-2.0F, -2.0F, -1.7F);
		this.bodyCrystalRow1TopRt.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 2, 0.0F);
		this.setRotateAngle(bodyCrystalRow1TopRt, 0.8726646259971648F, 0.0F, -0.7853981633974483F);
		this.bodyCrystalRow1TopRt.setTextureOffset(7, 45).addBox(-0.5F, -1.0F, 1.5F, 1, 1, 3, 0.0F); // bodyCrystalRow1TopRtTip
		this.bodyCrystalRow1Lft = new ModelRenderer(this, 28, 50);
		this.bodyCrystalRow1Lft.setRotationPoint(2.0F, 0.0F, -1.0F);
		this.bodyCrystalRow1Lft.addBox(-0.5F, -2.5F, -1.0F, 1, 3, 2, 0.0F);
		this.setRotateAngle(bodyCrystalRow1Lft, -0.6981317007977318F, 0.0F, 1.5707963267948966F);
		this.bodyCrystalRow1Lft.setTextureOffset(29, 45).addBox(-0.5F, -5.5F, -1.0F, 1, 3, 1, 0.0F); // bodyCrystalRow1LftTip
		this.bodyCrystalRow1Rt = new ModelRenderer(this, 0, 50);
		this.bodyCrystalRow1Rt.setRotationPoint(-2.0F, 0.0F, -1.0F);
		this.bodyCrystalRow1Rt.addBox(-0.5F, -2.5F, -1.0F, 1, 3, 2, 0.0F);
		this.setRotateAngle(bodyCrystalRow1Rt, -0.6981317007977318F, 0.0F, -1.5707963267948966F);
		this.bodyCrystalRow1Rt.setTextureOffset(1, 45).addBox(-0.5F, -5.5F, -1.0F, 1, 3, 1, 0.0F); // bodyCrystalRow1RtTip

		this.bodyCrystalRow2Lft = new ModelRenderer(this, 29, 60);
		this.bodyCrystalRow2Lft.setRotationPoint(2.0F, -1.0F, 2.5F);
		this.bodyCrystalRow2Lft.addBox(-0.5F, -1.5F, -1.0F, 1, 2, 2, 0.0F);
		this.setRotateAngle(bodyCrystalRow2Lft, -0.6981317007977318F, 0.0F, 1.2217304763960306F);
		this.bodyCrystalRow2Lft.setTextureOffset(30, 57).addBox(-0.5F, -2.5F, -1.0F, 1, 1, 1, 0.0F); // bodyCrystalRow2LftTip
		this.bodyCrystalRow2Rt = new ModelRenderer(this, 8, 60);
		this.bodyCrystalRow2Rt.setRotationPoint(-2.0F, -1.0F, 2.5F);
		this.bodyCrystalRow2Rt.addBox(-0.5F, -1.5F, -1.0F, 1, 2, 2, 0.0F);
		this.setRotateAngle(bodyCrystalRow2Rt, -0.6981317007977318F, 0.0F, -1.2217304763960306F);
		this.bodyCrystalRow2Rt.setTextureOffset(9, 57).addBox(-0.5F, -2.5F, -1.0F, 1, 1, 1, 0.0F); // bodyCrystalRow2RtTip
		this.bodyCrystalRow2TopLft = new ModelRenderer(this, 22, 60);
		this.bodyCrystalRow2TopLft.setRotationPoint(1.0F, -2.0F, 1.7F);
		this.bodyCrystalRow2TopLft.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 2, 0.0F);
		this.setRotateAngle(bodyCrystalRow2TopLft, 0.5235987755982988F, 0.0F, 0.5235987755982988F);
		this.bodyCrystalRow2TopLft.setTextureOffset(23, 57).addBox(-0.5F, -1.0F, 1.5F, 1, 1, 1, 0.0F); // bodyCrystalRow2TopLftTip
		this.bodyCrystalRow2TopRt = new ModelRenderer(this, 15, 60);
		this.bodyCrystalRow2TopRt.setRotationPoint(-1.0F, -2.0F, 1.7F);
		this.bodyCrystalRow2TopRt.addBox(-0.5F, -1.0F, -0.5F, 1, 2, 2, 0.0F);
		this.setRotateAngle(bodyCrystalRow2TopRt, 0.5235987755982988F, 0.0F, -0.5235987755982988F);
		this.bodyCrystalRow2TopRt.setTextureOffset(16, 57).addBox(-0.5F, -1.0F, 1.5F, 1, 1, 1, 0.0F); // bodyCrystalRow2TopRtTip

		this.legFntRt = new ModelRenderer(this, 36, 34);
		this.legFntRt.setRotationPoint(-1.2F, 2.0F, -2.5F);
		this.legFntRt.addBox(-1.0F, -1.0F, -1.0F, 2, 6, 2, 0.0F);
		legFntRtInfo = new PartInfo(legFntRt);
		this.legFntLft = new ModelRenderer(this, 56, 34);
		this.legFntLft.setRotationPoint(1.2F, 2.0F, -2.5F);
		this.legFntLft.addBox(-1.0F, -1.0F, -1.0F, 2, 6, 2, 0.0F);
		legFntLftInfo = new PartInfo(legFntLft);
		this.legBckLft = new ModelRenderer(this, 56, 43);
		this.legBckLft.setRotationPoint(1.2F, 2.0F, 2.5F);
		this.legBckLft.addBox(-1.0F, -1.0F, -1.0F, 2, 4, 2, 0.0F);
		this.setRotateAngle(legBckLft, -0.17453292519943295F, 0.0F, 0.0F);
		this.legBckLft.setTextureOffset(56, 53).addBox(-1.1F, 2.0F, 0.0F, 2, 3, 2, 0.0F); // legBckLftHind
		legBckLftInfo = new PartInfo(legBckLft);
		this.legBckLftCrystalTop = new ModelRenderer(this, 60, 49);
		this.legBckLftCrystalTop.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckLftCrystalTop.addBox(-0.5F, -1.0F, 2.3F, 1, 2, 1, 0.0F);
		this.setRotateAngle(legBckLftCrystalTop, -0.6981317007977318F, 0.0F, 0.0F);
		this.legBckLftCrystalBot = new ModelRenderer(this, 56, 50);
		this.legBckLftCrystalBot.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckLftCrystalBot.addBox(-0.6F, -1.6F, 3.1F, 1, 1, 1, 0.0F);
		this.setRotateAngle(legBckLftCrystalBot, -1.2217304763960306F, 0.0F, 0.0F);
		this.legBckRt = new ModelRenderer(this, 36, 43);
		this.legBckRt.setRotationPoint(-1.2F, 2.0F, 2.5F);
		this.legBckRt.addBox(-1.0F, -1.0F, -1.0F, 2, 4, 2, 0.0F);
		this.setRotateAngle(legBckRt, -0.17453292519943295F, 0.0F, 0.0F);
		this.legBckRt.setTextureOffset(36, 53).addBox(-0.9F, 2.0F, 0.0F, 2, 3, 2, 0.0F); // legBckRtHind
		legBckRtInfo = new PartInfo(legBckRt);
		this.legBckRtCrystalTop = new ModelRenderer(this, 36, 49);
		this.legBckRtCrystalTop.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckRtCrystalTop.addBox(-0.5F, -1.0F, 2.3F, 1, 2, 1, 0.0F);
		this.setRotateAngle(legBckRtCrystalTop, -0.6981317007977318F, 0.0F, 0.0F);
		this.legBckRtCrystalBot = new ModelRenderer(this, 40, 50);
		this.legBckRtCrystalBot.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckRtCrystalBot.addBox(-0.4F, -1.6F, 3.1F, 1, 1, 1, 0.0F);
		this.setRotateAngle(legBckRtCrystalBot, -1.2217304763960306F, 0.0F, 0.0F);

		this.neckJoint = new ModelRenderer(this, 0, 12);
		this.neckJoint.setRotationPoint(0.0F, -1.3F, -3.5F);
		this.neckJoint.addBox(-1.0F, -1.0F, -5.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(neckJoint, -0.4363323129985824F, 0.0F, 0.0F);
		neckJointInfo = new PartInfo(neckJoint);
		this.neck = new ModelRenderer(this, 1, 12);
		this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.neck.addBox(-1.0F, -1.0F, -4.5F, 2, 2, 5, 0.0F);
		neckInfo = new PartInfo(neck);

		this.headJoint = new ModelRenderer(this, 0, 0);
		this.headJoint.setRotationPoint(0.0F, 0.0F, -4.0F);
		this.headJoint.addBox(-2.5F, -3.5F, -4.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(headJoint, 0.4363323129985824F, 0.0F, 0.0F);
		headJointInfo = new PartInfo(headJoint);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-2.5F, -3.5F, -4.0F, 5, 5, 5, 0.0F);
		headInfo = new PartInfo(head);

		this.cheekCrystalLft = new ModelRenderer(this, 10, 11);
		this.cheekCrystalLft.setRotationPoint(1.7F, 0.0F, -1.0F);
		this.cheekCrystalLft.addBox(0.0F, -1.0F, -0.5F, 2, 2, 1, 0.0F);
		this.setRotateAngle(cheekCrystalLft, 0.0F, -0.6981317007977318F, 0.5235987755982988F);
		this.cheekCrystalLft.setTextureOffset(10, 15).addBox(2.0F, -1.0F, -0.5F, 2, 1, 1, 0.0F); // cheekCrystalLftTip
		this.cheekCrystalRt = new ModelRenderer(this, 0, 11);
		this.cheekCrystalRt.setRotationPoint(-1.7F, 0.0F, -1.0F);
		this.cheekCrystalRt.addBox(-2.0F, -1.0F, -0.5F, 2, 2, 1, 0.0F);
		this.setRotateAngle(cheekCrystalRt, 0.0F, 0.6981317007977318F, -0.5235987755982988F);
		this.cheekCrystalRt.setTextureOffset(0, 15).addBox(-4.0F, -1.0F, -0.5F, 2, 1, 1, 0.0F); // cheekCrystalRtTip

		this.muzzle = new ModelRenderer(this, 16, 0);
		this.muzzle.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.muzzle.addBox(-1.0F, -1.5F, -5.0F, 2, 2, 2, 0.0F);
		this.setRotateAngle(muzzle, 0.17453292519943295F, 0.0F, 0.0F);
		this.muzzle.setTextureOffset(23, 0).addBox(-0.5F, -1.4F, -5.3F, 1, 1, 1, 0.0F); // nose

		this.foreheadCrystalRt = new ModelRenderer(this, 17, 15);
		this.foreheadCrystalRt.setRotationPoint(-1.0F, -3.0F, -2.5F);
		this.foreheadCrystalRt.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(foreheadCrystalRt, -0.7853981633974483F, 0.0F, -0.8726646259971648F);
		this.foreheadCrystalMidRt = new ModelRenderer(this, 21, 13);
		this.foreheadCrystalMidRt.setRotationPoint(-0.5F, -3.0F, -2.8F);
		this.foreheadCrystalMidRt.addBox(-0.5F, -4.0F, -0.6F, 1, 4, 1, 0.0F);
		this.setRotateAngle(foreheadCrystalMidRt, -0.7853981633974483F, 0.0F, -0.4363323129985824F);
		this.foreheadCrystalMid = new ModelRenderer(this, 25, 11);
		this.foreheadCrystalMid.setRotationPoint(0.0F, -3.0F, -3.2F);
		this.foreheadCrystalMid.addBox(-0.5F, -5.5F, -0.5F, 1, 6, 1, 0.0F);
		this.setRotateAngle(foreheadCrystalMid, -0.7853981633974483F, 0.0F, 0.0F);
		this.foreheadCrystalMidLft = new ModelRenderer(this, 29, 13);
		this.foreheadCrystalMidLft.setRotationPoint(0.5F, -3.0F, -2.8F);
		this.foreheadCrystalMidLft.addBox(-0.5F, -4.0F, -0.6F, 1, 4, 1, 0.0F);
		this.setRotateAngle(foreheadCrystalMidLft, -0.7853981633974483F, 0.0F, 0.4363323129985824F);
		this.foreheadCrystalLft = new ModelRenderer(this, 33, 15);
		this.foreheadCrystalLft.setRotationPoint(1.0F, -3.0F, -2.5F);
		this.foreheadCrystalLft.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(foreheadCrystalLft, -0.7853981633974483F, 0.0F, 0.8726646259971648F);

		this.earLftJoint = new ModelRenderer(this, 7, 25);
		this.earLftJoint.setRotationPoint(2.0F, -3.0F, -1.0F);
		this.earLftJoint.addBox(-1.0F, -1.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earLftJoint, 0.0F, 0.0F, -0.5235987755982988F);
		earLftJointInfo = new PartInfo(earLftJoint);
		this.earLft = new ModelRenderer(this, 7, 25);
		this.earLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earLft.addBox(-1.0F, -1.5F, -0.5F, 2, 3, 1, 0.0F);
		this.earLft.setTextureOffset(8, 21).addBox(1.0F, -1.0F, -0.5F, 1, 2, 1, 0.0F); // earLftTip
		earLftInfo = new PartInfo(earLft);
		this.earRtJoint = new ModelRenderer(this, 0, 25);
		this.earRtJoint.setRotationPoint(-2.0F, -3.0F, -1.0F);
		this.earRtJoint.addBox(-1.0F, -1.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earRtJoint, 0.0F, 0.0F, 0.5235987755982988F);
		earRtJointInfo = new PartInfo(earRtJoint);
		this.earRt = new ModelRenderer(this, 0, 25);
		this.earRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earRt.addBox(-1.0F, -1.5F, -0.5F, 2, 3, 1, 0.0F);
		this.earRt.setTextureOffset(1, 21).addBox(-2.0F, -1.0F, -0.5F, 1, 2, 1, 0.0F); // earRtTip
		earRtInfo = new PartInfo(earRt);

		int tailNumber = 0;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 56, 0); // tail1Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, -1.9F, 3.9F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 56, 0); // tail1
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 57, 4); // tail2Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.3F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 57, 4); // tail2
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 57, 7); // tail3Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 57, 7); // tail3
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 57, 10); // tail4Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 57, 10); // tail4
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 57, 13); // tail5Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 57, 13); // tail5
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 48, 10); // tail6Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 48, 10); // tail6
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 7, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);

		int tailCrystalRowNumber = 0;
		int tailCrystalPartNumber = 0;
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber] = new ModelRenderer(this, 44, 1); // tailCrystalRow1Top
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].setRotationPoint(0.0F, 0.0F, 2.3F);
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber], 1.0471975511965976F, 0.0F, 0.0F);
		tailCrystalRowInfo[tailCrystalRowNumber][tailCrystalPartNumber] = new PartInfo(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber]);
		tailCrystalPartNumber++;
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber] = new ModelRenderer(this, 44, 4); // tailCrystalRow1Bot
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].setRotationPoint(0.0F, 0.0F, 2.3F);
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber], -1.0471975511965976F, 0.0F, 0.0F);
		tailCrystalRowInfo[tailCrystalRowNumber][tailCrystalPartNumber] = new PartInfo(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber]);
		tailCrystalPartNumber++;
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber] = new ModelRenderer(this, 50, 0); // tailCrystalRow1TopLft
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].setRotationPoint(0.2F, -0.2F, 2.6F);
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber], 1.0471975511965976F, 0.0F, 0.9599310885968813F);
		tailCrystalRowInfo[tailCrystalRowNumber][tailCrystalPartNumber] = new PartInfo(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber]);
		tailCrystalPartNumber++;
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber] = new ModelRenderer(this, 50, 3); // tailCrystalRow1BotLft
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].setRotationPoint(0.2F, 0.2F, 2.6F);
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber], -1.0471975511965976F, 0.0F, -0.9599310885968813F);
		tailCrystalRowInfo[tailCrystalRowNumber][tailCrystalPartNumber] = new PartInfo(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber]);
		tailCrystalPartNumber++;
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber] = new ModelRenderer(this, 38, 0); // tailCrystalRow1TopRt
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].setRotationPoint(-0.2F, -0.2F, 2.6F);
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber], 1.0471975511965976F, 0.0F, -0.9599310885968813F);
		tailCrystalRowInfo[tailCrystalRowNumber][tailCrystalPartNumber] = new PartInfo(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber]);
		tailCrystalPartNumber++;
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber] = new ModelRenderer(this, 38, 3); // tailCrystalRow1BotRt
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].setRotationPoint(-0.2F, 0.2F, 2.6F);
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber], -1.0471975511965976F, 0.0F, 0.9599310885968813F);
		tailCrystalRowInfo[tailCrystalRowNumber][tailCrystalPartNumber] = new PartInfo(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber]);
		tailCrystalRowNumber++;
		tailCrystalPartNumber = 0;
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber] = new ModelRenderer(this, 42, 9); // tailCrystalRow2Top
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].setRotationPoint(0.0F, 0.0F, 1.2F);
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber], 0.8726646259971648F, 0.0F, 0.0F);
		tailCrystalRowInfo[tailCrystalRowNumber][tailCrystalPartNumber] = new PartInfo(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber]);
		tailCrystalPartNumber++;
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber] = new ModelRenderer(this, 41, 14); // tailCrystalRow2Bot
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].setRotationPoint(0.0F, 0.0F, 1.2F);
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 4, 0.0F);
		this.setRotateAngle(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber], -0.8726646259971648F, 0.0F, 0.0F);
		tailCrystalRowInfo[tailCrystalRowNumber][tailCrystalPartNumber] = new PartInfo(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber]);
		tailCrystalPartNumber++;
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber] = new ModelRenderer(this, 47, 7); // tailCrystalRow2TopLft
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].setRotationPoint(0.2F, -0.2F, 1.7F);
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber], 0.8726646259971648F, 0.0F, 1.1344640137963142F);
		tailCrystalRowInfo[tailCrystalRowNumber][tailCrystalPartNumber] = new PartInfo(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber]);
		tailCrystalPartNumber++;
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber] = new ModelRenderer(this, 47, 11); // tailCrystalRow2BotLft
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].setRotationPoint(0.2F, 0.2F, 1.7F);
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber], -0.8726646259971648F, 0.0F, -1.1344640137963142F);
		tailCrystalRowInfo[tailCrystalRowNumber][tailCrystalPartNumber] = new PartInfo(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber]);
		tailCrystalPartNumber++;
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber] = new ModelRenderer(this, 37, 7); // tailCrystalRow2TopRt
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].setRotationPoint(-0.2F, -0.2F, 1.7F);
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber], 0.8726646259971648F, 0.0F, -1.1344640137963142F);
		tailCrystalRowInfo[tailCrystalRowNumber][tailCrystalPartNumber] = new PartInfo(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber]);
		tailCrystalPartNumber++;
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber] = new ModelRenderer(this, 37, 11); // tailCrystalRow2BotRt
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].setRotationPoint(-0.2F, 0.2F, 1.7F);
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber], -0.8726646259971648F, 0.0F, 1.1344640137963142F);
		tailCrystalRowInfo[tailCrystalRowNumber][tailCrystalPartNumber] = new PartInfo(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber]);
		tailCrystalRowNumber++;
		tailCrystalPartNumber = 0;
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber] = new ModelRenderer(this, 44, 19); // tailCrystalRow3Top
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].setRotationPoint(0.0F, 0.0F, 1.0F);
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 4, 0.0F);
		this.setRotateAngle(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber], 0.7853981633974483F, 0.0F, 0.0F);
		tailCrystalRowInfo[tailCrystalRowNumber][tailCrystalPartNumber] = new PartInfo(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber]);
		tailCrystalPartNumber++;
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber] = new ModelRenderer(this, 38, 20); // tailCrystalRow3Bot
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].setRotationPoint(0.0F, 0.0F, 1.0F);
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 4, 0.0F);
		this.setRotateAngle(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber], -0.7853981633974483F, 0.0F, 0.0F);
		tailCrystalRowInfo[tailCrystalRowNumber][tailCrystalPartNumber] = new PartInfo(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber]);
		tailCrystalPartNumber++;
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber] = new ModelRenderer(this, 47, 18); // tailCrystalRow3TopLft
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].setRotationPoint(0.2F, -0.2F, 1.5F);
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 7, 0.0F);
		this.setRotateAngle(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber], 0.7853981633974483F, 0.0F, 0.6981317007977318F);
		tailCrystalRowInfo[tailCrystalRowNumber][tailCrystalPartNumber] = new PartInfo(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber]);
		tailCrystalPartNumber++;
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber] = new ModelRenderer(this, 56, 19); // tailCrystalRow3BotLft
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].setRotationPoint(0.2F, 0.2F, 1.5F);
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber], -0.8726646259971648F, 0.0F, -0.6981317007977318F);
		tailCrystalRowInfo[tailCrystalRowNumber][tailCrystalPartNumber] = new PartInfo(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber]);
		tailCrystalPartNumber++;
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber] = new ModelRenderer(this, 29, 19); // tailCrystalRow3TopRt
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].setRotationPoint(-0.2F, -0.2F, 1.5F);
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 7, 0.0F);
		this.setRotateAngle(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber], 0.7853981633974483F, 0.0F, -0.6981317007977318F);
		tailCrystalRowInfo[tailCrystalRowNumber][tailCrystalPartNumber] = new PartInfo(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber]);
		tailCrystalPartNumber++;
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber] = new ModelRenderer(this, 28, 21); // tailCrystalRow3BotRt
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].setRotationPoint(-0.2F, 0.2F, 1.5F);
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber], -0.8726646259971648F, 0.0F, 0.6981317007977318F);
		tailCrystalRowInfo[tailCrystalRowNumber][tailCrystalPartNumber] = new PartInfo(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber]);
		tailCrystalRowNumber++;
		tailCrystalPartNumber = 0;
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber] = new ModelRenderer(this, 45, 32); // tailCrystalRow4Top
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].setRotationPoint(0.0F, 0.0F, 0.8F);
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 4, 0.0F);
		this.setRotateAngle(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber], 0.5235987755982988F, 0.0F, 0.0F);
		tailCrystalRowInfo[tailCrystalRowNumber][tailCrystalPartNumber] = new PartInfo(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber]);
		tailCrystalPartNumber++;
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber] = new ModelRenderer(this, 43, 37); // tailCrystalRow4Bot
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].setRotationPoint(0.0F, 0.0F, 0.8F);
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].addBox(-0.5F, -0.5F, -0.3F, 1, 1, 6, 0.0F);
		this.setRotateAngle(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber], -0.5235987755982988F, 0.0F, 0.0F);
		tailCrystalRowInfo[tailCrystalRowNumber][tailCrystalPartNumber] = new PartInfo(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber]);
		tailCrystalPartNumber++;
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber] = new ModelRenderer(this, 56, 26); // tailCrystalRow4TopLft
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].setRotationPoint(0.2F, -0.2F, 1.3F);
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, 0.0F);
		this.setRotateAngle(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber], 0.5235987755982988F, 0.0F, 0.9599310885968813F);
		tailCrystalRowInfo[tailCrystalRowNumber][tailCrystalPartNumber] = new PartInfo(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber]);
		tailCrystalPartNumber++;
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber] = new ModelRenderer(this, 56, 30); // tailCrystalRow4BotLft
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].setRotationPoint(0.2F, 0.2F, 1.3F);
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber], -0.6108652381980153F, 0.0F, -0.9599310885968813F);
		tailCrystalRowInfo[tailCrystalRowNumber][tailCrystalPartNumber] = new PartInfo(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber]);
		tailCrystalPartNumber++;
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber] = new ModelRenderer(this, 47, 27); // tailCrystalRow4TopRt
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].setRotationPoint(-0.2F, -0.2F, 1.3F);
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber], 0.5235987755982988F, 0.0F, -0.9599310885968813F);
		tailCrystalRowInfo[tailCrystalRowNumber][tailCrystalPartNumber] = new PartInfo(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber]);
		tailCrystalPartNumber++;
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber] = new ModelRenderer(this, 40, 28); // tailCrystalRow4BotRt
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].setRotationPoint(-0.2F, 0.2F, 1.3F);
		this.tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber], -0.6108652381980153F, 0.0F, 0.9599310885968813F);
		tailCrystalRowInfo[tailCrystalRowNumber][tailCrystalPartNumber] = new PartInfo(tailCrystalRow[tailCrystalRowNumber][tailCrystalPartNumber]);

		this.body.addChild(this.bodyCrystalFntLft);
		this.body.addChild(this.bodyCrystalFntMid);
		this.body.addChild(this.bodyCrystalFntMidLft);
		this.body.addChild(this.bodyCrystalFntMidRt);
		this.body.addChild(this.bodyCrystalFntRt);
		this.body.addChild(this.bodyCrystalRow1Lft);
		this.body.addChild(this.bodyCrystalRow1Rt);
		this.body.addChild(this.bodyCrystalRow1Top);
		this.body.addChild(this.bodyCrystalRow1TopLft);
		this.body.addChild(this.bodyCrystalRow1TopRt);
		this.body.addChild(this.bodyCrystalRow2Lft);
		this.body.addChild(this.bodyCrystalRow2Rt);
		this.body.addChild(this.bodyCrystalRow2TopLft);
		this.body.addChild(this.bodyCrystalRow2TopRt);
		this.body.addChild(this.legBckLft);
		this.body.addChild(this.legBckRt);
		this.body.addChild(this.legFntLft);
		this.body.addChild(this.legFntRt);
		this.body.addChild(this.neckJoint);
		this.body.addChild(this.tail[0][0]); // tail1Joint

		this.legBckLft.addChild(this.legBckLftCrystalBot);
		this.legBckLft.addChild(this.legBckLftCrystalTop);
		this.legBckRt.addChild(this.legBckRtCrystalBot);
		this.legBckRt.addChild(this.legBckRtCrystalTop);

		this.neckJoint.addChild(this.neck);
		this.neck.addChild(this.headJoint);

		this.headJoint.addChild(this.head);
		this.head.addChild(this.cheekCrystalLft);
		this.head.addChild(this.cheekCrystalRt);
		this.head.addChild(this.earLftJoint);
		this.head.addChild(this.earRtJoint);
		this.head.addChild(this.foreheadCrystalLft);
		this.head.addChild(this.foreheadCrystalMid);
		this.head.addChild(this.foreheadCrystalMidLft);
		this.head.addChild(this.foreheadCrystalMidRt);
		this.head.addChild(this.foreheadCrystalRt);
		this.head.addChild(this.muzzle);

		this.earLftJoint.addChild(this.earLft);
		this.earRtJoint.addChild(this.earRt);

		this.tail[0][0].addChild(this.tail[0][1]);

		for(int i = 1; i < tail.length; i++)
		{
			this.tail[i - 1][1].addChild(this.tail[i][0]);
			this.tail[i][0].addChild(this.tail[i][1]);
		}

		for(int i = 0; i < tailCrystalRow.length; i++)
		{
			for(int j = 0; j < tailCrystalRow[i].length; j++)
			{
				this.tail[i][1].addChild(this.tailCrystalRow[i][j]);
			}
		}

		animationDeployer = new AnimationDeployer(this);
	}

	@Override
	public void render(Entity entity, float distanceMoved, float horzVelocity, float ageInTicks, float yawHeadOffsetDifference, float pitchRotation, float modelSize) 
	{
		animate(entity, distanceMoved, horzVelocity, ageInTicks, yawHeadOffsetDifference, pitchRotation, modelSize);

		this.body.render(modelSize);
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) 
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void animate(Entity entity, float distanceMoved, float horzVelocity, float ageInTicks, float yawHeadOffsetDifference, float pitchRotation, float modelSize)
	{	
		this.animationDeployer.update((IAnimatedEntity)entity);
		resetPartInfos();

		float angularVelocity = ((IMobMotionTracker)entity).getAngularVelocity();
		float verticalVelocity = (float)((IMobMotionTracker)entity).getHeightVelocity();

		animateBody((EntityTiberiumGrowlithe)entity, distanceMoved, horzVelocity, ageInTicks, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity);
		animateHead((EntityTiberiumGrowlithe)entity, distanceMoved, horzVelocity, ageInTicks, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity);
		animateLegs((EntityTiberiumGrowlithe)entity, distanceMoved, horzVelocity, ageInTicks, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity);
		animateTail((EntityTiberiumGrowlithe)entity, distanceMoved, horzVelocity, ageInTicks, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity, angularVelocity);

		deployAnimations();
	}

	public void resetPartInfos()
	{
		bodyInfo.resetNewAngles();

		neckJointInfo.resetNewAngles();
		neckInfo.resetNewAngles();

		headInfo.resetNewAngles();
		headJointInfo.resetNewAngles();

		earRtInfo.resetNewAngles();
		earRtJointInfo.resetNewAngles();
		earLftInfo.resetNewAngles();
		earLftJointInfo.resetNewAngles();

		legFntRtInfo.resetNewAngles();
		legFntLftInfo.resetNewAngles();
		legBckRtInfo.resetNewAngles();
		legBckLftInfo.resetNewAngles();

		for(int i = 0; i < tail.length; i++)
		{
			tailInfo[i][0].resetNewAngles();
			tailInfo[i][1].resetNewAngles();
		}

		for(int i = 0; i < tailCrystalRowInfo.length; i++)
		{
			for(int j = 0; j < tailCrystalRowInfo[i].length; j++)
			{
				tailCrystalRowInfo[i][j].resetNewAngles();
			}
		}
	}

	public void animateBody(EntityTiberiumGrowlithe entity, float distanceMoved, float horzVelocity, float ageInTicks, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity)
	{
		float walkCycleInterval = ((WALK_FREQUENCY * distanceMoved + PI) % (2 * PI))/(2 * PI);
		float runCycleInterval = ((RUN_FREQUENCY * distanceMoved + PI) % (2 * PI))/(2 * PI);

		float bodyWalkAngle = (float)Math.toRadians(18);
		float bodyRunAngle = (float)Math.toRadians(28);

		float newVerticalVelocity = 2.0F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;

		float walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI + PI/2) * bodyWalkAngle * (1 - horzVelocity);
		float runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI + PI/2) * bodyRunAngle * horzVelocity;

		float bodyJumpAngle = (float)Math.toRadians(-55);
		float verticalVelocityChangeAngle = (float)Math.toRadians(10);

		bodyInfo.setNewRotateX(bodyInfo.getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity)) 
				+ bodyJumpAngle * newVerticalVelocity);
	}

	public void animateHead(EntityTiberiumGrowlithe entity, float distanceMoved, float horzVelocity, float ageInTicks, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity)
	{
		float walkCycleInterval = ((WALK_FREQUENCY * distanceMoved + PI/2F) % (2 * PI))/(2 * PI);
		float runCycleInterval = ((RUN_FREQUENCY * distanceMoved + PI/2F) % (2 * PI))/(2 * PI);

		float newVerticalVelocity = 2.0F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;

		JointAnimation.reverseJointRotatesChange(bodyInfo, neckJointInfo);

		float headWalkAngle = (float)Math.toRadians(15);
		float headRunAngle = (float)Math.toRadians(25);

		float walkCycleRateChange = MathHelper.cos(walkCycleInterval * 2 * PI + PI/2) * (1 - horzVelocity);
		float runCycleRateChange = MathHelper.cos(runCycleInterval * 2 * PI + PI/2) * horzVelocity;

		float walkCycleAngleChange = walkCycleRateChange * headWalkAngle;
		float runCycleAngleChange = runCycleRateChange * headRunAngle;

		// + (float)Math.toRadians(45)
		float angleChangeX = (walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity));

		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockNeckBobble();

		float idleHeadAmplitudeX = (float)Math.toRadians(15);

		angleChangeX += -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleHeadAmplitudeX + idleHeadAmplitudeX;

		neckInfo.setNewRotateX(neckInfo.getNewRotateX() + angleChangeX);
		neckInfo.setNewRotateY(neckInfo.getNewRotateY());

		JointAnimation.reverseJointRotatesChange(neckInfo, headJointInfo);

		PartAnimate.headAnimateInfoOnly(headInfo, yawHeadOffsetDifference, pitchRotation);

		/* ******* Ears ******** */
		float earAngleChangeZ = 0;

		/* ** Ears Movement ** */
		float earWalkAngle = (float)Math.toRadians(10);
		float earRunAngle = (float)Math.toRadians(25);

		walkCycleAngleChange = walkCycleRateChange * earWalkAngle;
		runCycleAngleChange = runCycleRateChange * earRunAngle;

		earAngleChangeZ = (walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity));

		/* ** Ears Idle ** */
		float idleAmplitudeZ = (float)Math.toRadians(10);
		earAngleChangeZ += MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI + PI) * idleAmplitudeZ;

		/* ** Ears Falling/Jumping ** */
		float fallingEarAngle = (float)Math.toRadians(30);
		earAngleChangeZ += fallingEarAngle * newVerticalVelocity;

		earRtInfo.setNewRotateZ(earRtInfo.getNewRotateZ() - earAngleChangeZ);
		earLftInfo.setNewRotateZ(earLftInfo.getNewRotateZ() + earAngleChangeZ);
	}

	public void animateLegs(EntityTiberiumGrowlithe entity, float distanceMoved, float horzVelocity, float ageInTicks, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity)
	{
		float amplitude = 0.8F;

		// Run constants
		float legFntRtModifierRun = 2F * PI * (1F/4F);
		float legBckRtModifierRun = 2F * PI * (7F/16F);
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

		JointAnimation.reverseJointRotatesChange(bodyInfo, legFntRtInfo);
		JointAnimation.reverseJointRotatesChange(bodyInfo, legFntLftInfo);
		JointAnimation.reverseJointRotatesChange(bodyInfo, legBckRtInfo);
		JointAnimation.reverseJointRotatesChange(bodyInfo, legBckLftInfo);

		legFntRtInfo.setNewRotateX(legFntRtInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legFntRtModifier + PI, newHorizontalVelocity, this.RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)) + fallingLegAngle * fallingModifier);
		legFntLftInfo.setNewRotateX(legFntLftInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + PI, newHorizontalVelocity, this.RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)) + fallingLegAngle * fallingModifier);
		legBckRtInfo.setNewRotateX(legBckRtInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legBckRtModifier + PI, newHorizontalVelocity, this.RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)));
		legBckLftInfo.setNewRotateX(legBckLftInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legBckLftModifier + PI, newHorizontalVelocity, this.RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)));
	}

	public void animateTail(EntityTiberiumGrowlithe entity, float distanceMoved, float horzVelocity, float ageInTicks, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity, float angularVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, tailInfo[0][0]);

		float yawChangeAngle = -(float)Math.toRadians(18);
		float verticalVelocityChangeAngle = (float)Math.toRadians(-9);

		float bodyJumpAngle = (float)Math.toRadians(-55);

		float newVerticalVelocity = 1.5F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;

		float absoluteMoveVelocity = horzVelocity + Math.abs(newVerticalVelocity);

		if(absoluteMoveVelocity > 1F)
			absoluteMoveVelocity = 1F;

		tailInfo[0][1].setNewRotateX(tailInfo[0][1].getNewRotateX() + bodyJumpAngle * newVerticalVelocity);

		for(int i = 0; i < tail.length; i++)
		{
			IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockTail(i);

			float idleAmplitudeX = 0.18F;
			float idleAmplitudeY = 0.22F;

			float moveAmplitudeX = (float)Math.toRadians(9);

			// + (float)Math.toRadians(45)
			float angleChangeX = 0;
			float angleChangeY = 0;

			// Idle Animations
			angleChangeX += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2) * idleAmplitudeX 
					* (1F - absoluteMoveVelocity) * (1F - Math.abs(angularVelocity) * 0.5F);
			angleChangeY += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2) * idleAmplitudeY
					* (1F - absoluteMoveVelocity) * (1F - Math.abs(angularVelocity) * 0.5F);

			// Movement Animations
			angleChangeX += PartAnimate.negCosRotateAnimationAdjusted(distanceMoved - (2F * PI * ((float)i / (float)(tail.length - 1))), absoluteMoveVelocity, RUN_FREQUENCY, moveAmplitudeX) 
					+ verticalVelocityChangeAngle * newVerticalVelocity;
			angleChangeY += angularVelocity * yawChangeAngle;

			// Part Number Dampener
			angleChangeX *= (1F - (Math.pow(Math.E, -0.95F * (float)(i + 1))));
			angleChangeY *= (1F - (Math.pow(Math.E, -0.95F * (float)(i + 1))));

			tailInfo[i][1].setNewRotateX(tailInfo[i][1].getNewRotateX() + angleChangeX);
			tailInfo[i][1].setNewRotateY(tailInfo[i][1].getNewRotateY() + angleChangeY);
		}

		for(int i = 0; i < tailCrystalRow.length; i++)
		{
			for(int j = 0; j < tailCrystalRow.length; j++)
			{
				IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockTail(0);

				float idleAmplitudeZ = 0.43F;

				// + (float)Math.toRadians(45)
				float angleChangeZ = 0;

				angleChangeZ -= MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2 + 0.05F * (float)(i + j)) * idleAmplitudeZ;

				tailCrystalRowInfo[i][j].setNewRotateZ(tailCrystalRowInfo[i][j].getNewRotateZ() + angleChangeZ);
			}
		}
	}

	public void deployAnimations()
	{
		this.animationDeployer.rotate(body, bodyInfo.getNewRotates());

		this.animationDeployer.rotate(neckJoint, neckJointInfo.getNewRotates());
		this.animationDeployer.rotate(neck, neckInfo.getNewRotates());
		this.animationDeployer.rotate(headJoint, headJointInfo.getNewRotates());
		this.animationDeployer.rotate(head, headInfo.getNewRotates());

		this.animationDeployer.rotate(earLft, earLftInfo.getNewRotates());
		this.animationDeployer.rotate(earLftJoint, earLftJointInfo.getNewRotates());
		this.animationDeployer.rotate(earRt, earRtInfo.getNewRotates());
		this.animationDeployer.rotate(earRtJoint, earRtJointInfo.getNewRotates());

		this.animationDeployer.rotate(legFntRt, legFntRtInfo.getNewRotates());
		this.animationDeployer.rotate(legFntLft, legFntLftInfo.getNewRotates());
		this.animationDeployer.rotate(legBckRt, legBckRtInfo.getNewRotates());
		this.animationDeployer.rotate(legBckLft, legBckLftInfo.getNewRotates());

		for(int i = 0; i < tail.length; i++)
		{
			this.animationDeployer.rotate(tail[i][0], tailInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(tail[i][1], tailInfo[i][1].getNewRotates());
		}

		for(int i = 0; i < tailCrystalRow.length; i++)
		{
			for(int j = 0; j < tailCrystalRow[i].length; j++)
			{
				this.animationDeployer.rotate(tailCrystalRow[i][j], tailCrystalRowInfo[i][j].getNewRotates());
			}
		}

		this.animationDeployer.applyChanges();
	}
}