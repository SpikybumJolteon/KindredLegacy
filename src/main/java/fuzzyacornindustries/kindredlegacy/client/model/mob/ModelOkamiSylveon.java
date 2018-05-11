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
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityOkamiSylveon;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryOkamiPokemonAttackID;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelOkamiSylveon extends ModelBase 
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public final float RUN_FREQUENCY = 1.2F;
	public final float WALK_FREQUENCY = 1.2F;
	public final float SNEAK_VELOCITY = 0.35F;

	public ModelRenderer body;
	PartInfo bodyInfo;
	PartInfo bodySitInfo;

	public ModelRenderer legFntLft[] = new ModelRenderer[2];
	PartInfo legFntLftInfo[] = new PartInfo[legFntLft.length];
	PartInfo legFntLftSitInfo[] = new PartInfo[legFntLft.length];

	public ModelRenderer legFntRt[] = new ModelRenderer[2];
	PartInfo legFntRtInfo[] = new PartInfo[legFntRt.length];
	PartInfo legFntRtSitInfo[] = new PartInfo[legFntRt.length];

	public ModelRenderer legBckLft[] = new ModelRenderer[3];
	PartInfo legBckLftInfo[] = new PartInfo[legBckLft.length];
	PartInfo legBckLftSitInfo[] = new PartInfo[legBckLft.length];

	public ModelRenderer legBckRt[] = new ModelRenderer[3];
	PartInfo legBckRtInfo[] = new PartInfo[legBckRt.length];
	PartInfo legBckRtSitInfo[] = new PartInfo[legBckRt.length];

	public ModelRenderer legFntLftBaseCurlJoint;
	public ModelRenderer legFntLftBaseCurlUpper;
	public ModelRenderer legFntLftBaseCurlLower;
	public ModelRenderer legFntLftBaseCurlMiddle;

	public ModelRenderer legFntLftBaseCurlLonghair[] = new ModelRenderer[3];
	PartInfo legFntLftBaseCurlLonghairInfo[] = new PartInfo[legFntLftBaseCurlLonghair.length];

	public ModelRenderer legFntLftForelegCurlJoint;
	public ModelRenderer legFntLftForelegCurl;

	public ModelRenderer legFntLftForelegCurlLongHair[] = new ModelRenderer[3];
	PartInfo legFntLftForelegCurlLongHairInfo[] = new PartInfo[legFntLftForelegCurlLongHair.length];

	public ModelRenderer legFntRtBaseCurlJoint;
	public ModelRenderer legFntRtBaseCurlUpper;
	public ModelRenderer legFntRtBaseCurlLower;
	public ModelRenderer legFntRtBaseCurlMiddle;

	public ModelRenderer legFntRtBaseCurlLonghair[] = new ModelRenderer[3];
	PartInfo legFntRtBaseCurlLonghairInfo[] = new PartInfo[legFntRtBaseCurlLonghair.length];

	public ModelRenderer legFntRtForelegCurlJoint;
	public ModelRenderer legFntRtForelegCurl;

	public ModelRenderer legFntRtForelegCurlLongHair[] = new ModelRenderer[3];
	PartInfo legFntRtForelegCurlLongHairInfo[] = new PartInfo[legFntRtForelegCurlLongHair.length];

	public ModelRenderer legBckLftHindCurlJoint;
	public ModelRenderer legBckLftHindCurl;

	public ModelRenderer legBckLftHindCurlLongHair[] = new ModelRenderer[3];
	PartInfo legBckLftHindCurlLongHairInfo[] = new PartInfo[legBckLftHindCurlLongHair.length];

	public ModelRenderer legBckRtHindCurlJoint;
	public ModelRenderer legBckRtHindCurl;

	public ModelRenderer legBckRtHindCurlLongHair[] = new ModelRenderer[3];
	PartInfo legBckRtHindCurlLongHairInfo[] = new PartInfo[legBckRtHindCurlLongHair.length];

	public ModelRenderer neckJoint;
	public ModelRenderer neck;
	public ModelRenderer headJoint;
	public ModelRenderer head;
	PartInfo neckJointInfo;
	PartInfo neckInfo;
	PartInfo headJointInfo;
	PartInfo headInfo;

	public ModelRenderer muzzle;

	public ModelRenderer earLftJoint;
	public ModelRenderer earLft;
	public ModelRenderer earRtJoint;
	public ModelRenderer earRt;
	PartInfo earLftInfo;
	PartInfo earRtInfo;

	public ModelRenderer headBowRt;
	public ModelRenderer headBowLft;
	PartInfo headBowRtInfo;
	PartInfo headBowLftInfo;

	public ModelRenderer headRtFeeler[] = new ModelRenderer[17];
	public ModelRenderer headRtFeeler18[] = new ModelRenderer[2];
	PartInfo headRtFeelerInfo[] = new PartInfo[headRtFeeler.length];
	PartInfo headRtFeeler18Info[] = new PartInfo[headRtFeeler18.length];

	public ModelRenderer headLftFeeler[] = new ModelRenderer[17];
	public ModelRenderer headLftFeeler18[] = new ModelRenderer[2];
	PartInfo headLftFeelerInfo[] = new PartInfo[headLftFeeler.length];
	PartInfo headLftFeeler18Info[] = new PartInfo[headLftFeeler18.length];

	public ModelRenderer tail[][] = new ModelRenderer[7][2];
	PartInfo tailInfo[][] = new PartInfo[tail.length][2];

	public ModelRenderer tailCurlBot;
	public ModelRenderer tailCurlTop;

	public ModelRenderer bodyBowRt;
	public ModelRenderer bodyBowLft;

	public ModelRenderer bodyLftFeeler[] = new ModelRenderer[18];
	public ModelRenderer bodyLftFeeler19[] = new ModelRenderer[2];
	PartInfo bodyLftFeelerInfo[] = new PartInfo[bodyLftFeeler.length];
	PartInfo bodyLftFeelerSitInfo[] = new PartInfo[bodyLftFeeler.length];
	PartInfo bodyLftFeeler19Info[] = new PartInfo[bodyLftFeeler19.length];

	public ModelRenderer bodyRtFeeler[] = new ModelRenderer[18];
	public ModelRenderer bodyRtFeeler19[] = new ModelRenderer[2];
	PartInfo bodyRtFeelerInfo[] = new PartInfo[bodyRtFeeler.length];
	PartInfo bodyRtFeelerSitInfo[] = new PartInfo[bodyRtFeeler.length];
	PartInfo bodyRtFeeler19Info[] = new PartInfo[bodyRtFeeler19.length];

	public ModelOkamiSylveon() 
	{
		this.textureWidth = 64;
		this.textureHeight = 64;

		int jointNumber = 0;
		int partNumber = 1;

		this.body = new ModelRenderer(this, 29, 55); // bodyFnt
		this.body.setRotationPoint(0.0F, 17.0F, 0.0F);
		this.body.addBox(-2.0F, -2.5F, -4.5F, 4, 5, 4, 0.0F);
		this.body.setTextureOffset(46, 55).addBox(-2.0F, -2.5F, -0.5F, 4, 4, 5, 0.0F); // bodyBck
		this.bodyInfo = new PartInfo(body);

		this.body.setRotationPoint(0.0F, 20.0F, 0.0F); // bodyFnt
		this.setRotateAngle(body, -0.7853981633974483F, 0.0F, 0.0F);
		this.bodySitInfo = new PartInfo(body);

		int legPartNumber = 0;
		this.legFntLft[legPartNumber] = new ModelRenderer(this, 52, 23); // legFntLftBase
		this.legFntLft[legPartNumber].setRotationPoint(1.3F, -0.3F, -3.0F);
		this.legFntLft[legPartNumber].addBox(-1.0F, -1.0F, -1.0F, 2, 3, 2, 0.0F);
		this.setRotateAngle(legFntLft[legPartNumber], 0.17453292519943295F, 0.0F, 0.0F);
		this.legFntLftInfo[legPartNumber] = new PartInfo(this.legFntLft[legPartNumber]);
		legPartNumber++;
		this.legFntLft[legPartNumber] = new ModelRenderer(this, 52, 28); // legFntLftForeleg
		this.legFntLft[legPartNumber].setRotationPoint(0.0F, 2.0F, 0.0F);
		this.legFntLft[legPartNumber].addBox(-1.01F, -0.6F, -1.0F, 2, 6, 2, 0.0F);
		this.setRotateAngle(legFntLft[legPartNumber], -0.2617993877991494F, 0.0F, 0.0F);
		this.legFntLftInfo[legPartNumber] = new PartInfo(this.legFntLft[legPartNumber]);

		legPartNumber = 0;
		this.setRotateAngle(legFntLft[legPartNumber], 0.6981317007977318F, 0.0F, 0.0F); // legFntLftBase
		this.legFntLftSitInfo[legPartNumber] = new PartInfo(this.legFntLft[legPartNumber]);
		legPartNumber++;
		this.setRotateAngle(legFntLft[legPartNumber], -0.2617993877991494F, 0.0F, 0.0F); // legFntLftForeleg
		this.legFntLftSitInfo[legPartNumber] = new PartInfo(this.legFntLft[legPartNumber]);

		legPartNumber = 0;
		this.legFntRt[legPartNumber] = new ModelRenderer(this, 44, 23); // legFntRtBase
		this.legFntRt[legPartNumber].setRotationPoint(-1.3F, -0.3F, -3.0F);
		this.legFntRt[legPartNumber].addBox(-1.0F, -1.0F, -1.0F, 2, 3, 2, 0.0F);
		this.setRotateAngle(legFntRt[legPartNumber], 0.17453292519943295F, 0.0F, 0.0F);
		this.legFntRtInfo[legPartNumber] = new PartInfo(this.legFntRt[legPartNumber]);
		legPartNumber++;
		this.legFntRt[legPartNumber] = new ModelRenderer(this, 44, 28); // legFntRtForeleg
		this.legFntRt[legPartNumber].setRotationPoint(0.0F, 2.0F, 0.0F);
		this.legFntRt[legPartNumber].addBox(-0.99F, -0.6F, -1.0F, 2, 6, 2, 0.0F);
		this.setRotateAngle(legFntRt[legPartNumber], -0.2617993877991494F, 0.0F, 0.0F);
		this.legFntRtInfo[legPartNumber] = new PartInfo(this.legFntRt[legPartNumber]);

		legPartNumber = 0;
		this.setRotateAngle(legFntRt[legPartNumber], 0.6981317007977318F, 0.0F, 0.0F); // legFntRtBase
		this.legFntRtSitInfo[legPartNumber] = new PartInfo(this.legFntRt[legPartNumber]);
		legPartNumber++;
		this.setRotateAngle(legFntRt[legPartNumber], -0.2617993877991494F, 0.0F, 0.0F); // legFntRtForeleg
		this.legFntRtSitInfo[legPartNumber] = new PartInfo(this.legFntRt[legPartNumber]);

		legPartNumber = 0;
		this.legBckLft[legPartNumber] = new ModelRenderer(this, 52, 37); // legBckLftBase
		this.legBckLft[legPartNumber].setRotationPoint(1.3F, -0.3F, 3.0F);
		this.legBckLft[legPartNumber].addBox(-1.0F, -0.5F, -1.0F, 2, 4, 2, 0.0F);
		this.setRotateAngle(legBckLft[legPartNumber], -0.17453292519943295F, 0.0F, 0.0F);
		this.legBckLftInfo[legPartNumber] = new PartInfo(this.legBckLft[legPartNumber]);
		legPartNumber++;
		this.legBckLft[legPartNumber] = new ModelRenderer(this, 52, 43); // legBckLftCalf
		this.legBckLft[legPartNumber].setRotationPoint(0.0F, 2.9F, 0.0F);
		this.legBckLft[legPartNumber].addBox(-0.99F, -0.5F, -1.0F, 2, 3, 2, 0.0F);
		this.setRotateAngle(legBckLft[legPartNumber], 1.2217304763960306F, 0.0F, 0.0F);
		this.legBckLftInfo[legPartNumber] = new PartInfo(this.legBckLft[legPartNumber]);
		legPartNumber++;
		this.legBckLft[legPartNumber] = new ModelRenderer(this, 52, 48); // legBckLftHind
		this.legBckLft[legPartNumber].setRotationPoint(0.0F, 1.7F, 0.0F);
		this.legBckLft[legPartNumber].addBox(-1.0F, -0.3F, -1.0F, 2, 4, 2, 0.0F);
		this.setRotateAngle(legBckLft[legPartNumber], -1.2217304763960306F, 0.0F, 0.0F);
		this.legBckLftInfo[legPartNumber] = new PartInfo(this.legBckLft[legPartNumber]);

		legPartNumber = 0;
		this.setRotateAngle(legBckLft[legPartNumber], -0.7853981633974483F, 0.0F, 0.0F); // legBckLftBase
		this.legBckLftSitInfo[legPartNumber] = new PartInfo(this.legBckLft[legPartNumber]);
		legPartNumber++;
		this.setRotateAngle(legBckLft[legPartNumber], 2.443460952792061F, 0.0F, 0.0F); // legBckLftCalf
		this.legBckLftSitInfo[legPartNumber] = new PartInfo(this.legBckLft[legPartNumber]);
		legPartNumber++;
		this.setRotateAngle(legBckLft[legPartNumber], -2.443460952792061F, 0.0F, 0.0F); // legBckLftHind
		this.legBckLftSitInfo[legPartNumber] = new PartInfo(this.legBckLft[legPartNumber]);

		legPartNumber = 0;
		this.legBckRt[legPartNumber] = new ModelRenderer(this, 44, 37); // legBckRtBase
		this.legBckRt[legPartNumber].setRotationPoint(-1.3F, -0.3F, 3.0F);
		this.legBckRt[legPartNumber].addBox(-1.0F, -0.5F, -1.0F, 2, 4, 2, 0.0F);
		this.setRotateAngle(legBckRt[legPartNumber], -0.17453292519943295F, 0.0F, 0.0F);
		this.legBckRtInfo[legPartNumber] = new PartInfo(this.legBckRt[legPartNumber]);
		legPartNumber++;
		this.legBckRt[legPartNumber] = new ModelRenderer(this, 44, 43); // legBckRtCalf
		this.legBckRt[legPartNumber].setRotationPoint(0.0F, 2.9F, 0.0F);
		this.legBckRt[legPartNumber].addBox(-1.01F, -0.5F, -1.0F, 2, 3, 2, 0.0F);
		this.setRotateAngle(legBckRt[legPartNumber], 1.2217304763960306F, 0.0F, 0.0F);
		this.legBckRtInfo[legPartNumber] = new PartInfo(this.legBckRt[legPartNumber]);
		legPartNumber++;
		this.legBckRt[legPartNumber] = new ModelRenderer(this, 44, 48); // legBckRtHind
		this.legBckRt[legPartNumber].setRotationPoint(0.0F, 1.7F, 0.0F);
		this.legBckRt[legPartNumber].addBox(-1.0F, -0.3F, -1.0F, 2, 4, 2, 0.0F);
		this.setRotateAngle(legBckRt[legPartNumber], -1.2217304763960306F, 0.0F, 0.0F);
		this.legBckRtInfo[legPartNumber] = new PartInfo(this.legBckRt[legPartNumber]);

		legPartNumber = 0;
		this.setRotateAngle(legBckRt[legPartNumber], -0.7853981633974483F, 0.0F, 0.0F); // legBckRtBase
		this.legBckRtSitInfo[legPartNumber] = new PartInfo(this.legBckRt[legPartNumber]);
		legPartNumber++;
		this.setRotateAngle(legBckRt[legPartNumber], 2.443460952792061F, 0.0F, 0.0F); // legBckRtCalf
		this.legBckRtSitInfo[legPartNumber] = new PartInfo(this.legBckRt[legPartNumber]);
		legPartNumber++;
		this.setRotateAngle(legBckRt[legPartNumber], -2.443460952792061F, 0.0F, 0.0F); // legBckRtHind
		this.legBckRtSitInfo[legPartNumber] = new PartInfo(this.legBckRt[legPartNumber]);

		this.legFntLftBaseCurlJoint = new ModelRenderer(this, 0, 0);
		this.legFntLftBaseCurlJoint.setRotationPoint(0.7F, 0.0F, 0.0F);
		this.legFntLftBaseCurlJoint.addBox(-0.5F, -5.0F, -0.5F, 0, 0, 0, 0.0F);
		this.legFntLftBaseCurlUpper = new ModelRenderer(this, 28, 52);
		this.legFntLftBaseCurlUpper.setRotationPoint(0.0F, -0.2F, -0.2F);
		this.legFntLftBaseCurlUpper.addBox(0.0F, -0.7F, -1.0F, 1, 1, 1, 0.0F);
		this.setRotateAngle(legFntLftBaseCurlUpper, -0.2617993877991494F, 0.0F, 0.0F);
		this.legFntLftBaseCurlMiddle = new ModelRenderer(this, 28, 54);
		this.legFntLftBaseCurlMiddle.setRotationPoint(0.0F, -0.2F, -0.2F);
		this.legFntLftBaseCurlMiddle.addBox(0.15F, -0.7F, -1.0F, 1, 1, 1, 0.0F);
		this.setRotateAngle(legFntLftBaseCurlMiddle, -1.5707963267948966F, 0.0F, 0.0F);
		this.legFntLftBaseCurlLower = new ModelRenderer(this, 28, 56);
		this.legFntLftBaseCurlLower.setRotationPoint(0.0F, -0.2F, -0.2F);
		this.legFntLftBaseCurlLower.addBox(0.3F, -0.7F, 0.0F, 1, 1, 1, 0.0F);
		this.setRotateAngle(legFntLftBaseCurlLower, -1.3962634015954636F, 0.0F, 0.0F);

		int curlLonghairNumber = 0;
		this.legFntLftBaseCurlLonghair[curlLonghairNumber] = new ModelRenderer(this, 28, 36); // legFntLftBaseCurlLonghair1
		this.legFntLftBaseCurlLonghair[curlLonghairNumber].setRotationPoint(0.0F, -0.2F, -0.2F);
		this.legFntLftBaseCurlLonghair[curlLonghairNumber].addBox(-0.5F, -4.8F, -0.5F, 1, 5, 1, 0.0F);
		this.setRotateAngle(legFntLftBaseCurlLonghair[curlLonghairNumber], -0.6981317007977318F, 0.0F, 0.0F);
		this.legFntLftBaseCurlLonghairInfo[curlLonghairNumber] = new PartInfo(this.legFntLftBaseCurlLonghair[curlLonghairNumber]);
		curlLonghairNumber++;
		this.legFntLftBaseCurlLonghair[curlLonghairNumber] = new ModelRenderer(this, 28, 42); // legFntLftBaseCurlLonghair2
		this.legFntLftBaseCurlLonghair[curlLonghairNumber].setRotationPoint(0.2F, 0.0F, 0.0F);
		this.legFntLftBaseCurlLonghair[curlLonghairNumber].addBox(-0.5F, -4.0F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(legFntLftBaseCurlLonghair[curlLonghairNumber], -0.8726646259971648F, 0.0F, 0.0F);
		this.legFntLftBaseCurlLonghairInfo[curlLonghairNumber] = new PartInfo(this.legFntLftBaseCurlLonghair[curlLonghairNumber]);
		curlLonghairNumber++;
		this.legFntLftBaseCurlLonghair[curlLonghairNumber] = new ModelRenderer(this, 28, 47); // legFntLftBaseCurlLonghair3
		this.legFntLftBaseCurlLonghair[curlLonghairNumber].setRotationPoint(0.4F, 0.2F, 0.2F);
		this.legFntLftBaseCurlLonghair[curlLonghairNumber].addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(legFntLftBaseCurlLonghair[curlLonghairNumber], -1.0471975511965976F, 0.0F, 0.0F);
		this.legFntLftBaseCurlLonghairInfo[curlLonghairNumber] = new PartInfo(this.legFntLftBaseCurlLonghair[curlLonghairNumber]);

		this.legFntLftForelegCurlJoint = new ModelRenderer(this, 0, 0);
		this.legFntLftForelegCurlJoint.setRotationPoint(1.0F, 3.8F, 0.5F);
		this.legFntLftForelegCurlJoint.addBox(0.0F, -1.0F, 0.0F, 0, 0, 0, 0.0F);
		this.legFntLftForelegCurl = new ModelRenderer(this, 60, 37);
		this.legFntLftForelegCurl.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legFntLftForelegCurl.addBox(-0.5F, -0.5F, -0.3F, 1, 1, 1, 0.0F);
		this.setRotateAngle(legFntLftForelegCurl, 0.2617993877991494F, 0.0F, 0.0F);

		curlLonghairNumber = 0;
		this.legFntLftForelegCurlLongHair[curlLonghairNumber] = new ModelRenderer(this, 60, 30); // legFntLftForelegCurlLongHairTop
		this.legFntLftForelegCurlLongHair[curlLonghairNumber].setRotationPoint(-0.2F, 0.0F, 0.0F);
		this.legFntLftForelegCurlLongHair[curlLonghairNumber].addBox(-0.6F, -2.8F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(legFntLftForelegCurlLongHair[curlLonghairNumber], -0.5235987755982988F, 0.0F, 0.0F);
		this.legFntLftForelegCurlLongHairInfo[curlLonghairNumber] = new PartInfo(this.legFntLftForelegCurlLongHair[curlLonghairNumber]);
		curlLonghairNumber++;
		this.legFntLftForelegCurlLongHair[curlLonghairNumber] = new ModelRenderer(this, 60, 34); // legFntLftForelegCurlLongHairBot
		this.legFntLftForelegCurlLongHair[curlLonghairNumber].setRotationPoint(-0.2F, 0.0F, 0.0F);
		this.legFntLftForelegCurlLongHair[curlLonghairNumber].addBox(-0.5F, -1.8F, -0.4F, 1, 2, 1, 0.0F);
		this.setRotateAngle(legFntLftForelegCurlLongHair[curlLonghairNumber], -0.9105382707654417F, 0.0F, 0.0F);
		this.legFntLftForelegCurlLongHairInfo[curlLonghairNumber] = new PartInfo(this.legFntLftForelegCurlLongHair[curlLonghairNumber]);

		this.legFntRtBaseCurlJoint = new ModelRenderer(this, 0, 0);
		this.legFntRtBaseCurlJoint.setRotationPoint(-0.6F, 0.0F, 0.0F);
		this.legFntRtBaseCurlJoint.addBox(-0.5F, -5.0F, -0.5F, 0, 0, 0, 0.0F);
		this.legFntRtBaseCurlUpper = new ModelRenderer(this, 28, 16);
		this.legFntRtBaseCurlUpper.setRotationPoint(0.0F, -0.2F, -0.2F);
		this.legFntRtBaseCurlUpper.addBox(-1.0F, -0.7F, -1.0F, 1, 1, 1, 0.0F);
		this.setRotateAngle(legFntRtBaseCurlUpper, -0.2617993877991494F, 0.0F, 0.0F);
		this.legFntRtBaseCurlMiddle = new ModelRenderer(this, 28, 20);
		this.legFntRtBaseCurlMiddle.setRotationPoint(0.0F, -0.2F, -0.2F);
		this.legFntRtBaseCurlMiddle.addBox(-1.15F, -0.7F, -1.0F, 1, 1, 1, 0.0F);
		this.setRotateAngle(legFntRtBaseCurlMiddle, -1.5707963267948966F, 0.0F, 0.0F);
		this.legFntRtBaseCurlLower = new ModelRenderer(this, 28, 18);
		this.legFntRtBaseCurlLower.setRotationPoint(0.0F, -0.2F, -0.2F);
		this.legFntRtBaseCurlLower.addBox(-1.3F, -0.7F, 0.0F, 1, 1, 1, 0.0F);
		this.setRotateAngle(legFntRtBaseCurlLower, -1.3962634015954636F, 0.0F, 0.0F);

		curlLonghairNumber = 0;
		this.legFntRtBaseCurlLonghair[curlLonghairNumber] = new ModelRenderer(this, 28, 0); // legFntRtBaseCurlLonghair1
		this.legFntRtBaseCurlLonghair[curlLonghairNumber].setRotationPoint(0.0F, -0.2F, -0.2F);
		this.legFntRtBaseCurlLonghair[curlLonghairNumber].addBox(-0.5F, -4.8F, -0.5F, 1, 5, 1, 0.0F);
		this.setRotateAngle(legFntRtBaseCurlLonghair[curlLonghairNumber], -0.6981317007977318F, 0.0F, 0.0F);
		this.legFntRtBaseCurlLonghairInfo[curlLonghairNumber] = new PartInfo(this.legFntRtBaseCurlLonghair[curlLonghairNumber]);
		curlLonghairNumber++;
		this.legFntRtBaseCurlLonghair[curlLonghairNumber] = new ModelRenderer(this, 28, 6); // legFntRtBaseCurlLonghair2
		this.legFntRtBaseCurlLonghair[curlLonghairNumber].setRotationPoint(-0.2F, 0.0F, 0.0F);
		this.legFntRtBaseCurlLonghair[curlLonghairNumber].addBox(-0.5F, -4.0F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(legFntRtBaseCurlLonghair[curlLonghairNumber], -0.8726646259971648F, 0.0F, 0.0F);
		this.legFntRtBaseCurlLonghairInfo[curlLonghairNumber] = new PartInfo(this.legFntRtBaseCurlLonghair[curlLonghairNumber]);
		curlLonghairNumber++;
		this.legFntRtBaseCurlLonghair[curlLonghairNumber] = new ModelRenderer(this, 28, 11); // legFntRtBaseCurlLonghair3
		this.legFntRtBaseCurlLonghair[curlLonghairNumber].setRotationPoint(-0.4F, 0.2F, 0.2F);
		this.legFntRtBaseCurlLonghair[curlLonghairNumber].addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(legFntRtBaseCurlLonghair[curlLonghairNumber], -1.0471975511965976F, 0.0F, 0.0F);
		this.legFntRtBaseCurlLonghairInfo[curlLonghairNumber] = new PartInfo(this.legFntRtBaseCurlLonghair[curlLonghairNumber]);

		this.legFntRtForelegCurlJoint = new ModelRenderer(this, 0, 0);
		this.legFntRtForelegCurlJoint.setRotationPoint(-1.0F, 3.8F, 0.5F);
		this.legFntRtForelegCurlJoint.addBox(0.0F, -1.0F, 0.0F, 0, 0, 0, 0.0F);
		this.legFntRtForelegCurl = new ModelRenderer(this, 60, 27);
		this.legFntRtForelegCurl.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legFntRtForelegCurl.addBox(-0.5F, -0.5F, -0.3F, 1, 1, 1, 0.0F);
		this.setRotateAngle(legFntRtForelegCurl, 0.2617993877991494F, 0.0F, 0.0F);

		curlLonghairNumber = 0;
		this.legFntRtForelegCurlLongHair[curlLonghairNumber] = new ModelRenderer(this, 60, 20); // legFntRtForelegCurlLongHairTop
		this.legFntRtForelegCurlLongHair[curlLonghairNumber].setRotationPoint(-0.2F, 0.0F, 0.0F);
		this.legFntRtForelegCurlLongHair[curlLonghairNumber].addBox(0.1F, -2.8F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(legFntRtForelegCurlLongHair[curlLonghairNumber], -0.5235987755982988F, 0.0F, 0.0F);
		this.legFntRtForelegCurlLongHairInfo[curlLonghairNumber] = new PartInfo(this.legFntRtForelegCurlLongHair[curlLonghairNumber]);
		curlLonghairNumber++;
		this.legFntRtForelegCurlLongHair[curlLonghairNumber] = new ModelRenderer(this, 60, 24); // legFntRtForelegCurlLongHairBot
		this.legFntRtForelegCurlLongHair[curlLonghairNumber].setRotationPoint(-0.2F, 0.0F, 0.0F);
		this.legFntRtForelegCurlLongHair[curlLonghairNumber].addBox(0.0F, -1.8F, -0.4F, 1, 2, 1, 0.0F);
		this.setRotateAngle(legFntRtForelegCurlLongHair[curlLonghairNumber], -0.9105382707654417F, 0.0F, 0.0F);
		this.legFntRtForelegCurlLongHairInfo[curlLonghairNumber] = new PartInfo(this.legFntRtForelegCurlLongHair[curlLonghairNumber]);

		this.legBckLftHindCurlJoint = new ModelRenderer(this, 0, 0);
		this.legBckLftHindCurlJoint.setRotationPoint(1.0F, 1.8F, 0.5F);
		this.legBckLftHindCurlJoint.addBox(0.0F, -1.0F, 0.0F, 0, 0, 0, 0.0F);
		this.legBckLftHindCurl = new ModelRenderer(this, 60, 57);
		this.legBckLftHindCurl.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckLftHindCurl.addBox(-0.5F, -0.5F, -0.3F, 1, 1, 1, 0.0F);
		this.setRotateAngle(legBckLftHindCurl, 0.2617993877991494F, 0.0F, 0.0F);

		curlLonghairNumber = 0;
		this.legBckLftHindCurlLongHair[curlLonghairNumber] = new ModelRenderer(this, 60, 50); // legBckLftHindCurlLongHairTop
		this.legBckLftHindCurlLongHair[curlLonghairNumber].setRotationPoint(-0.2F, 0.0F, 0.0F);
		this.legBckLftHindCurlLongHair[curlLonghairNumber].addBox(-0.6F, -2.8F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(legBckLftHindCurlLongHair[curlLonghairNumber], -0.5235987755982988F, 0.0F, 0.0F);
		this.legBckLftHindCurlLongHairInfo[curlLonghairNumber] = new PartInfo(this.legBckLftHindCurlLongHair[curlLonghairNumber]);
		curlLonghairNumber++;
		this.legBckLftHindCurlLongHair[curlLonghairNumber] = new ModelRenderer(this, 60, 54); // legBckLftHindCurlLongHairBot
		this.legBckLftHindCurlLongHair[curlLonghairNumber].setRotationPoint(-0.2F, 0.0F, 0.0F);
		this.legBckLftHindCurlLongHair[curlLonghairNumber].addBox(-0.5F, -1.8F, -0.4F, 1, 2, 1, 0.0F);
		this.setRotateAngle(legBckLftHindCurlLongHair[curlLonghairNumber], -0.8726646259971648F, 0.0F, 0.0F);
		this.legBckLftHindCurlLongHairInfo[curlLonghairNumber] = new PartInfo(this.legBckLftHindCurlLongHair[curlLonghairNumber]);

		this.legBckRtHindCurlJoint = new ModelRenderer(this, 0, 0);
		this.legBckRtHindCurlJoint.setRotationPoint(-1.0F, 1.8F, 0.5F);
		this.legBckRtHindCurlJoint.addBox(0.0F, -1.0F, 0.0F, 0, 0, 0, 0.0F);
		this.legBckRtHindCurl = new ModelRenderer(this, 60, 47);
		this.legBckRtHindCurl.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckRtHindCurl.addBox(-0.5F, -0.5F, -0.3F, 1, 1, 1, 0.0F);
		this.setRotateAngle(legBckRtHindCurl, 0.2617993877991494F, 0.0F, 0.0F);

		curlLonghairNumber = 0;
		this.legBckRtHindCurlLongHair[curlLonghairNumber] = new ModelRenderer(this, 60, 40); // legBckRtHindCurlLongHairTop
		this.legBckRtHindCurlLongHair[curlLonghairNumber].setRotationPoint(-0.2F, 0.0F, 0.0F);
		this.legBckRtHindCurlLongHair[curlLonghairNumber].addBox(0.1F, -2.8F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(legBckRtHindCurlLongHair[curlLonghairNumber], -0.5235987755982988F, 0.0F, 0.0F);
		this.legBckRtHindCurlLongHairInfo[curlLonghairNumber] = new PartInfo(this.legBckRtHindCurlLongHair[curlLonghairNumber]);
		curlLonghairNumber++;
		this.legBckRtHindCurlLongHair[curlLonghairNumber] = new ModelRenderer(this, 60, 44); // legBckRtHindCurlLongHairBot
		this.legBckRtHindCurlLongHair[curlLonghairNumber].setRotationPoint(-0.2F, 0.0F, 0.0F);
		this.legBckRtHindCurlLongHair[curlLonghairNumber].addBox(0.0F, -1.8F, -0.4F, 1, 2, 1, 0.0F);
		this.setRotateAngle(legBckRtHindCurlLongHair[curlLonghairNumber], -0.8726646259971648F, 0.0F, 0.0F);
		this.legBckRtHindCurlLongHairInfo[curlLonghairNumber] = new PartInfo(this.legBckRtHindCurlLongHair[curlLonghairNumber]);

		this.neckJoint = new ModelRenderer(this, 0, 0);
		this.neckJoint.setRotationPoint(0.0F, -1.3F, -3.4F);
		this.neckJoint.addBox(-1.0F, -1.0F, -4.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(neckJoint, -0.7853981633974483F, 0.0F, 0.0F);
		this.neckJointInfo = new PartInfo(neckJoint);
		this.neck = new ModelRenderer(this, 52, 10);
		this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.neck.addBox(-1.0F, -1.0F, -3.5F, 2, 2, 4, 0.0F);
		this.neckInfo = new PartInfo(neck);
		this.headJoint = new ModelRenderer(this, 0, 0);
		this.headJoint.setRotationPoint(0.0F, 0.0F, -2.5F);
		this.headJoint.addBox(-2.5F, -4.5F, -4.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(headJoint, 0.7853981633974483F, 0.0F, 0.0F);
		this.headJointInfo = new PartInfo(headJoint);
		this.head = new ModelRenderer(this, 44, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-2.5F, -4.5F, -4.0F, 5, 5, 5, 0.0F);
		this.headInfo = new PartInfo(head);

		this.muzzle = new ModelRenderer(this, 48, 10);
		this.muzzle.setRotationPoint(0.0F, -0.6F, -4.0F);
		this.muzzle.addBox(-1.0F, -1.0F, -1.2F, 2, 2, 2, 0.0F);
		this.setRotateAngle(muzzle, 0.17453292519943295F, 0.0F, 0.0F);
		this.muzzle.setTextureOffset(60, 11).addBox(-0.5F, -0.9F, -1.5F, 1, 1, 1, 0.0F); // nose

		this.earLftJoint = new ModelRenderer(this, 0, 0);
		this.earLftJoint.setRotationPoint(2.0F, -4.0F, -2.0F);
		this.earLftJoint.addBox(-1.0F, -1.5F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earLftJoint, 0.0F, 0.0F, 0.3490658503988659F);
		this.earLft = new ModelRenderer(this, 36, 21);
		this.earLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earLft.addBox(-1.0F, -0.5F, 0.0F, 2, 1, 1, 0.0F);
		this.earLft.setTextureOffset(35, 16).addBox(-1.5F, -4.5F, 0.0F, 3, 4, 1, 0.0F); // earLftMid
		this.earLft.setTextureOffset(36, 14).addBox(-1.0F, -5.5F, 0.0F, 2, 1, 1, 0.0F); // earLftEnd
		this.earLft.setTextureOffset(37, 12).addBox(-0.5F, -6.5F, 0.0F, 1, 1, 1, 0.0F); // earLftTip
		this.earLftInfo = new PartInfo(earLft);
		this.earRtJoint = new ModelRenderer(this, 0, 0);
		this.earRtJoint.setRotationPoint(-2.0F, -4.0F, -2.0F);
		this.earRtJoint.addBox(-1.0F, -1.5F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earRtJoint, 0.0F, 0.0F, -0.3490658503988659F);
		this.earRt = new ModelRenderer(this, 36, 9);
		this.earRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earRt.addBox(-1.0F, -0.5F, 0.0F, 2, 1, 1, 0.0F);
		this.earRt.setTextureOffset(35, 4).addBox(-1.5F, -4.5F, 0.0F, 3, 4, 1, 0.0F); // earRtMid
		this.earRt.setTextureOffset(36, 2).addBox(-1.0F, -5.5F, 0.0F, 2, 1, 1, 0.0F); // earRtEnd
		this.earRt.setTextureOffset(37, 0).addBox(-0.5F, -6.5F, 0.0F, 1, 1, 1, 0.0F); // earRtTip
		this.earRtInfo = new PartInfo(earRt);

		this.headBowRt = new ModelRenderer(this, 11, 10);
		this.headBowRt.setRotationPoint(0.3F, 0.0F, 0.0F);
		this.headBowRt.addBox(0.0F, -1.0F, -1.5F, 1, 2, 2, 0.0F);
		this.setRotateAngle(headBowRt, -0.7853981633974483F, -0.17453292519943295F, -0.6981317007977318F);
		this.headBowRtInfo = new PartInfo(headBowRt);
		this.headBowLft = new ModelRenderer(this, 11, 14);
		this.headBowLft.setRotationPoint(-0.5F, -0.2F, 0.0F);
		this.headBowLft.addBox(-1.0F, -0.5F, -1.5F, 1, 2, 2, 0.0F);
		this.setRotateAngle(headBowLft, -0.7853981633974483F, 0.17453292519943295F, 0.5235987755982988F);
		this.headBowLftInfo = new PartInfo(headBowLft);

		int feelerNumber = 0;
		this.headRtFeeler[feelerNumber] = new ModelRenderer(this, 18, 0); // headRtFeeler1
		this.headRtFeeler[feelerNumber].setRotationPoint(0.8F, -4.5F, -2.5F);
		this.headRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headRtFeeler[feelerNumber], -0.5235987755982988F, 2.2689280275926285F, 0.0F);
		this.headRtFeelerInfo[feelerNumber] = new PartInfo(this.headRtFeeler[feelerNumber]);
		feelerNumber++;
		this.headRtFeeler[feelerNumber] = new ModelRenderer(this, 18, 3); // headRtFeeler2
		this.headRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headRtFeeler[feelerNumber], 0.0F, 0.2617993877991494F, 0.0F);
		this.headRtFeelerInfo[feelerNumber] = new PartInfo(this.headRtFeeler[feelerNumber]);
		feelerNumber++;
		this.headRtFeeler[feelerNumber] = new ModelRenderer(this, 18, 6); // headRtFeeler3
		this.headRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headRtFeeler[feelerNumber], 0.0F, 0.2617993877991494F, 0.0F);
		this.headRtFeelerInfo[feelerNumber] = new PartInfo(this.headRtFeeler[feelerNumber]);
		feelerNumber++;
		this.headRtFeeler[feelerNumber] = new ModelRenderer(this, 18, 9); // headRtFeeler4
		this.headRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headRtFeeler[feelerNumber], 0.08726646259971647F, -0.2617993877991494F, 0.0F);
		this.headRtFeelerInfo[feelerNumber] = new PartInfo(this.headRtFeeler[feelerNumber]);
		feelerNumber++;
		this.headRtFeeler[feelerNumber] = new ModelRenderer(this, 18, 12); // headRtFeeler5
		this.headRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headRtFeeler[feelerNumber], 0.13962634015954636F, -0.2617993877991494F, 0.0F);
		this.headRtFeelerInfo[feelerNumber] = new PartInfo(this.headRtFeeler[feelerNumber]);
		feelerNumber++;
		this.headRtFeeler[feelerNumber] = new ModelRenderer(this, 18, 15); // headRtFeeler6
		this.headRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headRtFeeler[feelerNumber], 0.13962634015954636F, -0.2617993877991494F, 0.0F);
		this.headRtFeelerInfo[feelerNumber] = new PartInfo(this.headRtFeeler[feelerNumber]);
		feelerNumber++;
		this.headRtFeeler[feelerNumber] = new ModelRenderer(this, 18, 18); // headRtFeeler7
		this.headRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headRtFeeler[feelerNumber], 0.13962634015954636F, -0.17453292519943295F, 0.0F);
		this.headRtFeelerInfo[feelerNumber] = new PartInfo(this.headRtFeeler[feelerNumber]);
		feelerNumber++;
		this.headRtFeeler[feelerNumber] = new ModelRenderer(this, 18, 21); // headRtFeeler8
		this.headRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headRtFeeler[feelerNumber], 0.13962634015954636F, -0.17453292519943295F, 0.0F);
		this.headRtFeelerInfo[feelerNumber] = new PartInfo(this.headRtFeeler[feelerNumber]);
		feelerNumber++;
		this.headRtFeeler[feelerNumber] = new ModelRenderer(this, 18, 24); // headRtFeeler9
		this.headRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headRtFeeler[feelerNumber], 0.13962634015954636F, -0.2617993877991494F, 0.0F);
		this.headRtFeelerInfo[feelerNumber] = new PartInfo(this.headRtFeeler[feelerNumber]);
		feelerNumber++;
		this.headRtFeeler[feelerNumber] = new ModelRenderer(this, 18, 27); // headRtFeeler10
		this.headRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headRtFeeler[feelerNumber], 0.08726646259971647F, -0.2617993877991494F, 0.0F);
		this.headRtFeelerInfo[feelerNumber] = new PartInfo(this.headRtFeeler[feelerNumber]);
		feelerNumber++;
		this.headRtFeeler[feelerNumber] = new ModelRenderer(this, 18, 30); // headRtFeeler11
		this.headRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headRtFeeler[feelerNumber], 0.08726646259971647F, -0.3490658503988659F, 0.0F);
		this.headRtFeelerInfo[feelerNumber] = new PartInfo(this.headRtFeeler[feelerNumber]);
		feelerNumber++;
		this.headRtFeeler[feelerNumber] = new ModelRenderer(this, 18, 33); // headRtFeeler12
		this.headRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headRtFeeler[feelerNumber], -0.08726646259971647F, -0.3490658503988659F, 0.0F);
		this.headRtFeelerInfo[feelerNumber] = new PartInfo(this.headRtFeeler[feelerNumber]);
		feelerNumber++;
		this.headRtFeeler[feelerNumber] = new ModelRenderer(this, 18, 36); // headRtFeeler13
		this.headRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headRtFeeler[feelerNumber], -0.08726646259971647F, -0.3490658503988659F, 0.0F);
		this.headRtFeelerInfo[feelerNumber] = new PartInfo(this.headRtFeeler[feelerNumber]);
		feelerNumber++;
		this.headRtFeeler[feelerNumber] = new ModelRenderer(this, 18, 39); // headRtFeeler14
		this.headRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headRtFeeler[feelerNumber], -0.08726646259971647F, -0.3490658503988659F, 0.0F);
		this.headRtFeelerInfo[feelerNumber] = new PartInfo(this.headRtFeeler[feelerNumber]);
		feelerNumber++;
		this.headRtFeeler[feelerNumber] = new ModelRenderer(this, 18, 42); // headRtFeeler15
		this.headRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headRtFeeler[feelerNumber], -0.08726646259971647F, -0.3490658503988659F, 0.0F);
		this.headRtFeelerInfo[feelerNumber] = new PartInfo(this.headRtFeeler[feelerNumber]);
		feelerNumber++;
		this.headRtFeeler[feelerNumber] = new ModelRenderer(this, 18, 45); // headRtFeeler16
		this.headRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headRtFeeler[feelerNumber], -0.08726646259971647F, -0.3490658503988659F, 0.0F);
		this.headRtFeelerInfo[feelerNumber] = new PartInfo(this.headRtFeeler[feelerNumber]);
		feelerNumber++;
		this.headRtFeeler[feelerNumber] = new ModelRenderer(this, 18, 48); // headRtFeeler17
		this.headRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headRtFeeler[feelerNumber], -0.08726646259971647F, -0.3490658503988659F, 0.0F);
		this.headRtFeelerInfo[feelerNumber] = new PartInfo(this.headRtFeeler[feelerNumber]);

		feelerNumber = 0;
		this.headRtFeeler18[feelerNumber] = new ModelRenderer(this, 17, 54); // headRtFeeler18Top
		this.headRtFeeler18[feelerNumber].setRotationPoint(0.0F, -0.5F, -0.7F);
		this.headRtFeeler18[feelerNumber].addBox(-0.5F, -0.5F, -1.7F, 1, 1, 2, 0.0F);
		this.setRotateAngle(headRtFeeler18[feelerNumber], -0.17453292519943295F, -0.3490658503988659F, 0.0F);
		this.headRtFeeler18Info[feelerNumber] = new PartInfo(this.headRtFeeler18[feelerNumber]);
		feelerNumber++;
		this.headRtFeeler18[feelerNumber] = new ModelRenderer(this, 17, 58); // headRtFeeler18Bot
		this.headRtFeeler18[feelerNumber].setRotationPoint(0.0F, 0.5F, -0.7F);
		this.headRtFeeler18[feelerNumber].addBox(-0.5F, -0.5F, -1.7F, 1, 1, 2, 0.0F);
		this.setRotateAngle(headRtFeeler18[feelerNumber], -0.08726646259971647F, -0.3490658503988659F, 0.0F);
		this.headRtFeeler18Info[feelerNumber] = new PartInfo(this.headRtFeeler18[feelerNumber]);

		feelerNumber = 0;
		this.headLftFeeler[feelerNumber] = new ModelRenderer(this, 23, 0); // headLftFeeler1
		this.headLftFeeler[feelerNumber].setRotationPoint(2.2F, -3.9F, -2.5F);
		this.headLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headLftFeeler[feelerNumber], -0.2617993877991494F, -1.3962634015954636F, 0.0F);
		this.headLftFeelerInfo[feelerNumber] = new PartInfo(this.headLftFeeler[feelerNumber]);
		feelerNumber++;
		this.headLftFeeler[feelerNumber] = new ModelRenderer(this, 23, 3); // headLftFeeler2
		this.headLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headLftFeeler[feelerNumber], 0.17453292519943295F, -0.12217304763960307F, 0.0F);
		this.headLftFeelerInfo[feelerNumber] = new PartInfo(this.headLftFeeler[feelerNumber]);
		feelerNumber++;
		this.headLftFeeler[feelerNumber] = new ModelRenderer(this, 23, 6); // headLftFeeler3
		this.headLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headLftFeeler[feelerNumber], 0.17453292519943295F, -0.12217304763960307F, 0.0F);
		this.headLftFeelerInfo[feelerNumber] = new PartInfo(this.headLftFeeler[feelerNumber]);
		feelerNumber++;
		this.headLftFeeler[feelerNumber] = new ModelRenderer(this, 23, 9); // headLftFeeler4
		this.headLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headLftFeeler[feelerNumber], 0.17453292519943295F, -0.2617993877991494F, 0.0F);
		this.headLftFeelerInfo[feelerNumber] = new PartInfo(this.headLftFeeler[feelerNumber]);
		feelerNumber++;
		this.headLftFeeler[feelerNumber] = new ModelRenderer(this, 23, 12); // headLftFeeler5
		this.headLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headLftFeeler[feelerNumber], 0.20943951023931953F, -0.2617993877991494F, 0.0F);
		this.headLftFeelerInfo[feelerNumber] = new PartInfo(this.headLftFeeler[feelerNumber]);
		feelerNumber++;
		this.headLftFeeler[feelerNumber] = new ModelRenderer(this, 23, 15); // headLftFeeler6
		this.headLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headLftFeeler[feelerNumber], 0.2617993877991494F, -0.2617993877991494F, 0.0F);
		this.headLftFeelerInfo[feelerNumber] = new PartInfo(this.headLftFeeler[feelerNumber]);
		feelerNumber++;
		this.headLftFeeler[feelerNumber] = new ModelRenderer(this, 23, 18); // headLftFeeler7
		this.headLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headLftFeeler[feelerNumber], 0.2617993877991494F, -0.17453292519943295F, 0.0F);
		this.headLftFeelerInfo[feelerNumber] = new PartInfo(this.headLftFeeler[feelerNumber]);
		feelerNumber++;
		this.headLftFeeler[feelerNumber] = new ModelRenderer(this, 23, 21); // headLftFeeler8
		this.headLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headLftFeeler[feelerNumber], 0.2617993877991494F, -0.17453292519943295F, -0.08726646259971647F);
		this.headLftFeelerInfo[feelerNumber] = new PartInfo(this.headLftFeeler[feelerNumber]);
		feelerNumber++;
		this.headLftFeeler[feelerNumber] = new ModelRenderer(this, 23, 24); // headLftFeeler9
		this.headLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headLftFeeler[feelerNumber], 0.2617993877991494F, -0.2617993877991494F, -0.08726646259971647F);
		this.headLftFeelerInfo[feelerNumber] = new PartInfo(this.headLftFeeler[feelerNumber]);
		feelerNumber++;
		this.headLftFeeler[feelerNumber] = new ModelRenderer(this, 23, 27); // headLftFeeler10
		this.headLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headLftFeeler[feelerNumber], 0.08726646259971647F, -0.2617993877991494F, -0.08726646259971647F);
		this.headLftFeelerInfo[feelerNumber] = new PartInfo(this.headLftFeeler[feelerNumber]);
		feelerNumber++;
		this.headLftFeeler[feelerNumber] = new ModelRenderer(this, 23, 30); // headLftFeeler11
		this.headLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headLftFeeler[feelerNumber], -0.08726646259971647F, -0.3490658503988659F, -0.08726646259971647F);
		this.headLftFeelerInfo[feelerNumber] = new PartInfo(this.headLftFeeler[feelerNumber]);
		feelerNumber++;
		this.headLftFeeler[feelerNumber] = new ModelRenderer(this, 23, 33); // headLftFeeler12
		this.headLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headLftFeeler[feelerNumber], -0.12217304763960307F, -0.3490658503988659F, -0.08726646259971647F);
		this.headLftFeelerInfo[feelerNumber] = new PartInfo(this.headLftFeeler[feelerNumber]);
		feelerNumber++;
		this.headLftFeeler[feelerNumber] = new ModelRenderer(this, 23, 36); // headLftFeeler13
		this.headLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headLftFeeler[feelerNumber], -0.15707963267948966F, -0.3490658503988659F, -0.08726646259971647F);
		this.headLftFeelerInfo[feelerNumber] = new PartInfo(this.headLftFeeler[feelerNumber]);
		feelerNumber++;
		this.headLftFeeler[feelerNumber] = new ModelRenderer(this, 23, 39); // headLftFeeler14
		this.headLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headLftFeeler[feelerNumber], -0.08726646259971647F, -0.3490658503988659F, 0.08726646259971647F);
		this.headLftFeelerInfo[feelerNumber] = new PartInfo(this.headLftFeeler[feelerNumber]);
		feelerNumber++;
		this.headLftFeeler[feelerNumber] = new ModelRenderer(this, 23, 42); // headLftFeeler15
		this.headLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headLftFeeler[feelerNumber], -0.08726646259971647F, 0.0F, 0.08726646259971647F);
		this.headLftFeelerInfo[feelerNumber] = new PartInfo(this.headLftFeeler[feelerNumber]);
		feelerNumber++;
		this.headLftFeeler[feelerNumber] = new ModelRenderer(this, 23, 45); // headLftFeeler16
		this.headLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headLftFeeler[feelerNumber], -0.08726646259971647F, 0.2617993877991494F, 0.0F);
		this.headLftFeelerInfo[feelerNumber] = new PartInfo(this.headLftFeeler[feelerNumber]);
		feelerNumber++;
		this.headLftFeeler[feelerNumber] = new ModelRenderer(this, 23, 48); // headLftFeeler17
		this.headLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.headLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headLftFeeler[feelerNumber], -0.08726646259971647F, 0.2617993877991494F, 0.0F);
		this.headLftFeelerInfo[feelerNumber] = new PartInfo(this.headLftFeeler[feelerNumber]);

		feelerNumber = 0;
		this.headLftFeeler18[feelerNumber] = new ModelRenderer(this, 21, 52); // headLftFeeler18Top
		this.headLftFeeler18[feelerNumber].setRotationPoint(0.0F, -0.5F, -0.7F);
		this.headLftFeeler18[feelerNumber].addBox(-0.5F, -0.5F, -1.7F, 1, 1, 2, 0.0F);
		this.setRotateAngle(headLftFeeler18[feelerNumber], -0.17453292519943295F, 0.3490658503988659F, 0.0F);
		this.headLftFeeler18Info[feelerNumber] = new PartInfo(this.headLftFeeler18[feelerNumber]);
		feelerNumber++;
		this.headLftFeeler18[feelerNumber] = new ModelRenderer(this, 21, 56); // headLftFeeler18Bot
		this.headLftFeeler18[feelerNumber].setRotationPoint(0.0F, 0.5F, -0.7F);
		this.headLftFeeler18[feelerNumber].addBox(-0.5F, -0.5F, -1.7F, 1, 1, 2, 0.0F);
		this.setRotateAngle(headLftFeeler18[feelerNumber], -0.08726646259971647F, 0.3490658503988659F, 0.0F);
		this.headLftFeeler18Info[feelerNumber] = new PartInfo(this.headLftFeeler18[feelerNumber]);

		int tailNumber = 0;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail1Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, -1.8F, 4.0F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.3F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 36, 51); // tail1
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.3F, 1, 1, 2, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail2Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.4F);
		this.tail[tailNumber][jointNumber].addBox(-1.0F, -0.7F, -0.3F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 35, 47); // tail2
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-1.0F, -0.7F, -0.3F, 2, 2, 2, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail3Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.4F);
		this.tail[tailNumber][jointNumber].addBox(-1.0F, -0.7F, -0.3F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 35, 43); // tail3
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-1.0F, -0.7F, -0.3F, 2, 2, 2, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail4Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.4F);
		this.tail[tailNumber][jointNumber].addBox(-1.0F, -0.7F, -0.3F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 35, 39); // tail4
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-1.0F, -0.7F, -0.3F, 2, 2, 2, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail5Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.4F);
		this.tail[tailNumber][jointNumber].addBox(-1.0F, -0.7F, -0.3F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 35, 35); // tail5
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-1.0F, -0.7F, -0.3F, 2, 2, 2, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail6Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.4F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.3F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 36, 32); // tail6
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.3F, 1, 1, 2, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail7Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.5F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 36, 29); // tail7
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);

		this.tailCurlBot = new ModelRenderer(this, 32, 24);
		this.tailCurlBot.setRotationPoint(-0.6F, 0.9F, 0.9F);
		this.tailCurlBot.addBox(-0.5F, -0.5F, -1.5F, 1, 2, 2, 0.0F);
		this.setRotateAngle(tailCurlBot, 0.7853981633974483F, 0.0F, 0.0F);
		this.tailCurlTop = new ModelRenderer(this, 38, 24);
		this.tailCurlTop.setRotationPoint(0.6F, -0.5F, 0.9F);
		this.tailCurlTop.addBox(-0.5F, -1.5F, -0.5F, 1, 2, 2, 0.0F);
		this.setRotateAngle(tailCurlTop, 0.7853981633974483F, 0.0F, 0.0F);

		this.bodyBowRt = new ModelRenderer(this, 10, 0);
		this.bodyBowRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bodyBowRt.addBox(0.0F, -0.5F, -1.5F, 1, 2, 2, 0.0F);
		this.setRotateAngle(bodyBowRt, -0.7853981633974483F, -0.17453292519943295F, 0.0F);
		this.bodyBowLft = new ModelRenderer(this, 10, 4);
		this.bodyBowLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bodyBowLft.addBox(-1.0F, -0.5F, -1.5F, 1, 2, 2, 0.0F);
		this.setRotateAngle(bodyBowLft, -0.7853981633974483F, 0.17453292519943295F, 0.0F);

		feelerNumber = 0;
		this.bodyLftFeeler[feelerNumber] = new ModelRenderer(this, 5, 0); // bodyLftFeeler1
		this.bodyLftFeeler[feelerNumber].setRotationPoint(0.5F, 0.0F, -4.6F);
		this.bodyLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.8F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], 0.0F, -1.1344640137963142F, 0.0F);
		this.bodyLftFeelerInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], 0.0F, -1.1344640137963142F, 0.0F);
		this.bodyLftFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyLftFeeler[feelerNumber] = new ModelRenderer(this, 5, 3); // bodyLftFeeler2
		this.bodyLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], 0.0F, -0.20943951023931953F, 0.0F);
		this.bodyLftFeelerInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], 0.0F, -0.20943951023931953F, 0.0F);
		this.bodyLftFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyLftFeeler[feelerNumber] = new ModelRenderer(this, 5, 6); // bodyLftFeeler3
		this.bodyLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], 0.0F, -0.20943951023931953F, 0.0F);
		this.bodyLftFeelerInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], 0.0F, -0.20943951023931953F, 0.0F);
		this.bodyLftFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyLftFeeler[feelerNumber] = new ModelRenderer(this, 5, 9); // bodyLftFeeler4
		this.bodyLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], 0.0F, -0.20943951023931953F, 0.0F);
		this.bodyLftFeelerInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], 0.0F, -0.20943951023931953F, 0.0F);
		this.bodyLftFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyLftFeeler[feelerNumber] = new ModelRenderer(this, 5, 12); // bodyLftFeeler5
		this.bodyLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], 0.0F, -0.20943951023931953F, 0.0F);
		this.bodyLftFeelerInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], 0.0F, -0.20943951023931953F, 0.0F);
		this.bodyLftFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyLftFeeler[feelerNumber] = new ModelRenderer(this, 5, 15); // bodyLftFeeler6
		this.bodyLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], 0.0F, -0.20943951023931953F, 0.0F);
		this.bodyLftFeelerInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], 0.0F, -0.20943951023931953F, 0.0F);
		this.bodyLftFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyLftFeeler[feelerNumber] = new ModelRenderer(this, 5, 18); // bodyLftFeeler7
		this.bodyLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], 0.0F, -0.20943951023931953F, 0.0F);
		this.bodyLftFeelerInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], 0.0F, -0.20943951023931953F, 0.0F);
		this.bodyLftFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyLftFeeler[feelerNumber] = new ModelRenderer(this, 5, 21); // bodyLftFeeler8
		this.bodyLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], 0.0F, -0.20943951023931953F, 0.0F);
		this.bodyLftFeelerInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], 0.0F, -0.20943951023931953F, 0.0F);
		this.bodyLftFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyLftFeeler[feelerNumber] = new ModelRenderer(this, 5, 24); // bodyLftFeeler9
		this.bodyLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], 0.0F, -0.20943951023931953F, 0.0F);
		this.bodyLftFeelerInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], 0.0F, -0.20943951023931953F, 0.0F);
		this.bodyLftFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyLftFeeler[feelerNumber] = new ModelRenderer(this, 5, 27); // bodyLftFeeler10
		this.bodyLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], 0.0F, -0.20943951023931953F, 0.0F);
		this.bodyLftFeelerInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], 0.0F, -0.20943951023931953F, 0.0F);
		this.bodyLftFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyLftFeeler[feelerNumber] = new ModelRenderer(this, 5, 30); // bodyLftFeeler11
		this.bodyLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], 0.0F, -0.12217304763960307F, 0.0F);
		this.bodyLftFeelerInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], -0.10471975511965977F, -0.12217304763960307F, 0.0F);
		this.bodyLftFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyLftFeeler[feelerNumber] = new ModelRenderer(this, 5, 33); // bodyLftFeeler12
		this.bodyLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.bodyLftFeelerInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], -0.10471975511965977F, 0.0F, 0.0F);
		this.bodyLftFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyLftFeeler[feelerNumber] = new ModelRenderer(this, 5, 36); // bodyLftFeeler13
		this.bodyLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.bodyLftFeelerInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], -0.10471975511965977F, 0.0F, 0.0F);
		this.bodyLftFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyLftFeeler[feelerNumber] = new ModelRenderer(this, 5, 39); // bodyLftFeeler14
		this.bodyLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.bodyLftFeelerInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], -0.10471975511965977F, 0.0F, 0.0F);
		this.bodyLftFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyLftFeeler[feelerNumber] = new ModelRenderer(this, 5, 42); // bodyLftFeeler15
		this.bodyLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.bodyLftFeelerInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], -0.10471975511965977F, 0.0F, 0.0F);
		this.bodyLftFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyLftFeeler[feelerNumber] = new ModelRenderer(this, 5, 45); // bodyLftFeeler16
		this.bodyLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.bodyLftFeelerInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], -0.10471975511965977F, 0.0F, 0.0F);
		this.bodyLftFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyLftFeeler[feelerNumber] = new ModelRenderer(this, 5, 48); // bodyLftFeeler17
		this.bodyLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.bodyLftFeelerInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], -0.10471975511965977F, 0.0F, 0.0F);
		this.bodyLftFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyLftFeeler[feelerNumber] = new ModelRenderer(this, 5, 51); // bodyLftFeeler18
		this.bodyLftFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyLftFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.bodyLftFeelerInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);
		this.setRotateAngle(bodyLftFeeler[feelerNumber], -0.10471975511965977F, 0.0F, 0.0F);
		this.bodyLftFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyLftFeeler[feelerNumber]);

		feelerNumber = 0;
		this.bodyLftFeeler19[feelerNumber] = new ModelRenderer(this, 4, 55); // bodyLftFeeler19Top
		this.bodyLftFeeler19[feelerNumber].setRotationPoint(0.0F, -0.5F, -0.7F);
		this.bodyLftFeeler19[feelerNumber].addBox(-0.5F, -0.5F, -1.7F, 1, 1, 2, 0.0F);
		this.bodyLftFeeler19Info[feelerNumber] = new PartInfo(this.bodyLftFeeler19[feelerNumber]);
		feelerNumber++;
		this.bodyLftFeeler19[feelerNumber] = new ModelRenderer(this, 4, 59); // bodyLftFeeler19Bot
		this.bodyLftFeeler19[feelerNumber].setRotationPoint(0.0F, 0.5F, -0.7F);
		this.bodyLftFeeler19[feelerNumber].addBox(-0.5F, -0.5F, -1.7F, 1, 1, 2, 0.0F);
		this.bodyLftFeeler19Info[feelerNumber] = new PartInfo(this.bodyLftFeeler19[feelerNumber]);

		feelerNumber = 0;
		this.bodyRtFeeler[feelerNumber] = new ModelRenderer(this, 0, 0); // bodyRtFeeler1
		this.bodyRtFeeler[feelerNumber].setRotationPoint(-0.5F, 0.0F, -4.6F);
		this.bodyRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.8F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], 0.0F, 1.1344640137963142F, 0.0F);
		this.bodyRtFeelerInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], 0.0F, 1.1344640137963142F, 0.0F);
		this.bodyRtFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyRtFeeler[feelerNumber] = new ModelRenderer(this, 0, 3); // bodyRtFeeler2
		this.bodyRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], 0.0F, 0.20943951023931953F, 0.0F);
		this.bodyRtFeelerInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], 0.0F, 0.20943951023931953F, 0.0F);
		this.bodyRtFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyRtFeeler[feelerNumber] = new ModelRenderer(this, 0, 6); // bodyRtFeeler3
		this.bodyRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], 0.0F, 0.20943951023931953F, 0.0F);
		this.bodyRtFeelerInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], 0.0F, 0.20943951023931953F, 0.0F);
		this.bodyRtFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyRtFeeler[feelerNumber] = new ModelRenderer(this, 0, 9); // bodyRtFeeler4
		this.bodyRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], 0.0F, 0.20943951023931953F, 0.0F);
		this.bodyRtFeelerInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], 0.0F, 0.20943951023931953F, 0.0F);
		this.bodyRtFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyRtFeeler[feelerNumber] = new ModelRenderer(this, 0, 12); // bodyRtFeeler5
		this.bodyRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], 0.0F, 0.20943951023931953F, 0.0F);
		this.bodyRtFeelerInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], 0.0F, 0.20943951023931953F, 0.0F);
		this.bodyRtFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyRtFeeler[feelerNumber] = new ModelRenderer(this, 0, 15); // bodyRtFeeler6
		this.bodyRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], 0.0F, 0.20943951023931953F, 0.0F);
		this.bodyRtFeelerInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], 0.0F, 0.20943951023931953F, 0.0F);
		this.bodyRtFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyRtFeeler[feelerNumber] = new ModelRenderer(this, 0, 18); // bodyRtFeeler7
		this.bodyRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], 0.0F, 0.20943951023931953F, 0.0F);
		this.bodyRtFeelerInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], 0.0F, 0.20943951023931953F, 0.0F);
		this.bodyRtFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyRtFeeler[feelerNumber] = new ModelRenderer(this, 0, 21); // bodyRtFeeler8
		this.bodyRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], 0.0F, 0.20943951023931953F, 0.0F);
		this.bodyRtFeelerInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], 0.0F, 0.20943951023931953F, 0.0F);
		this.bodyRtFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyRtFeeler[feelerNumber] = new ModelRenderer(this, 0, 24); // bodyRtFeeler9
		this.bodyRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], 0.0F, 0.20943951023931953F, 0.0F);
		this.bodyRtFeelerInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], 0.0F, 0.20943951023931953F, 0.0F);
		this.bodyRtFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyRtFeeler[feelerNumber] = new ModelRenderer(this, 0, 27); // bodyRtFeeler10
		this.bodyRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], 0.0F, 0.20943951023931953F, 0.0F);
		this.bodyRtFeelerInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], 0.0F, 0.20943951023931953F, 0.0F);
		this.bodyRtFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyRtFeeler[feelerNumber] = new ModelRenderer(this, 0, 30); // bodyRtFeeler11
		this.bodyRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], 0.0F, 0.12217304763960307F, 0.0F);
		this.bodyRtFeelerInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], -0.10471975511965977F, 0.12217304763960307F, 0.0F);
		this.bodyRtFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyRtFeeler[feelerNumber] = new ModelRenderer(this, 0, 33); // bodyRtFeeler12
		this.bodyRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.bodyRtFeelerInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], -0.10471975511965977F, 0.0F, 0.0F);
		this.bodyRtFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyRtFeeler[feelerNumber] = new ModelRenderer(this, 0, 36); // bodyRtFeeler13
		this.bodyRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.bodyRtFeelerInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], -0.10471975511965977F, 0.0F, 0.0F);
		this.bodyRtFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyRtFeeler[feelerNumber] = new ModelRenderer(this, 0, 39); // bodyRtFeeler14
		this.bodyRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.bodyRtFeelerInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], -0.10471975511965977F, 0.0F, 0.0F);
		this.bodyRtFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyRtFeeler[feelerNumber] = new ModelRenderer(this, 0, 42); // bodyRtFeeler15
		this.bodyRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.bodyRtFeelerInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], -0.10471975511965977F, 0.0F, 0.0F);
		this.bodyRtFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyRtFeeler[feelerNumber] = new ModelRenderer(this, 0, 45); // bodyRtFeeler16
		this.bodyRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.bodyRtFeelerInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], -0.10471975511965977F, 0.0F, 0.0F);
		this.bodyRtFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyRtFeeler[feelerNumber] = new ModelRenderer(this, 0, 48); // bodyRtFeeler17
		this.bodyRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.bodyRtFeelerInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], -0.10471975511965977F, 0.0F, 0.0F);
		this.bodyRtFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		feelerNumber++;
		this.bodyRtFeeler[feelerNumber] = new ModelRenderer(this, 0, 51); // bodyRtFeeler18
		this.bodyRtFeeler[feelerNumber].setRotationPoint(0.0F, 0.0F, -0.7F);
		this.bodyRtFeeler[feelerNumber].addBox(-0.5F, -1.0F, -0.7F, 1, 2, 1, 0.0F);
		this.bodyRtFeelerInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);
		this.setRotateAngle(bodyRtFeeler[feelerNumber], -0.10471975511965977F, 0.0F, 0.0F);
		this.bodyRtFeelerSitInfo[feelerNumber] = new PartInfo(this.bodyRtFeeler[feelerNumber]);

		feelerNumber = 0;
		this.bodyRtFeeler19[feelerNumber] = new ModelRenderer(this, 0, 57); // bodyRtFeeler19Top
		this.bodyRtFeeler19[feelerNumber].setRotationPoint(0.0F, -0.5F, -0.7F);
		this.bodyRtFeeler19[feelerNumber].addBox(-0.5F, -0.5F, -1.7F, 1, 1, 2, 0.0F);
		this.bodyRtFeeler19Info[feelerNumber] = new PartInfo(this.bodyRtFeeler19[feelerNumber]);
		feelerNumber++;
		this.bodyRtFeeler19[feelerNumber] = new ModelRenderer(this, 0, 61); // bodyRtFeeler19Bot
		this.bodyRtFeeler19[feelerNumber].setRotationPoint(0.0F, 0.5F, -0.7F);
		this.bodyRtFeeler19[feelerNumber].addBox(-0.5F, -0.5F, -1.7F, 1, 1, 2, 0.0F);
		this.bodyRtFeeler19Info[feelerNumber] = new PartInfo(this.bodyRtFeeler19[feelerNumber]);

		this.body.addChild(this.bodyLftFeeler[0]);
		this.body.addChild(this.bodyRtFeeler[0]);
		this.body.addChild(this.legBckLft[0]);
		this.body.addChild(this.legBckRt[0]);
		this.body.addChild(this.legFntLft[0]);
		this.body.addChild(this.legFntRt[0]);
		this.body.addChild(this.neckJoint);
		this.body.addChild(this.tail[0][0]);

		this.bodyLftFeeler[0].addChild(this.bodyBowLft);
		this.bodyRtFeeler[0].addChild(this.bodyBowRt);

		for(int i = 0; i < bodyLftFeeler.length - 1; i++)
		{
			this.bodyLftFeeler[i].addChild(this.bodyLftFeeler[i + 1]);
			this.bodyRtFeeler[i].addChild(this.bodyRtFeeler[i + 1]);
		}

		for(int i = 0; i < bodyLftFeeler19.length; i++)
		{
			this.bodyLftFeeler[this.bodyLftFeeler.length - 1].addChild(this.bodyLftFeeler19[i]);
			this.bodyRtFeeler[this.bodyRtFeeler.length - 1].addChild(this.bodyRtFeeler19[i]);
		}

		this.earLftJoint.addChild(this.earLft);
		this.earRtJoint.addChild(this.earRt);

		this.head.addChild(this.earLftJoint);
		this.head.addChild(this.earRtJoint);
		this.head.addChild(this.headLftFeeler[0]);
		this.head.addChild(this.headRtFeeler[0]);

		this.head.addChild(this.muzzle);

		this.headJoint.addChild(this.head);

		this.headLftFeeler[0].addChild(this.headBowLft);
		this.headRtFeeler[0].addChild(this.headBowRt);

		for(int i = 0; i < headLftFeeler.length - 1; i++)
		{
			this.headLftFeeler[i].addChild(this.headLftFeeler[i + 1]);
			this.headRtFeeler[i].addChild(this.headRtFeeler[i + 1]);
		}

		for(int i = 0; i < headLftFeeler18.length; i++)
		{
			this.headLftFeeler[this.headLftFeeler.length - 1].addChild(this.headLftFeeler18[i]);
			this.headRtFeeler[this.headRtFeeler.length - 1].addChild(this.headRtFeeler18[i]);
		}

		this.legBckLft[2].addChild(this.legBckLftHindCurlJoint);
		this.legBckLftHindCurlJoint.addChild(this.legBckLftHindCurl);
		this.legBckLftHindCurlJoint.addChild(this.legBckLftHindCurlLongHair[0]);
		this.legBckLftHindCurlJoint.addChild(this.legBckLftHindCurlLongHair[1]);

		for(int i = 0; i < legBckRt.length - 1; i++)
		{
			this.legBckRt[i].addChild(this.legBckRt[i + 1]);
			this.legBckLft[i].addChild(this.legBckLft[i + 1]);
		}

		this.legBckRt[2].addChild(this.legBckRtHindCurlJoint);
		this.legBckRtHindCurlJoint.addChild(this.legBckRtHindCurl);
		this.legBckRtHindCurlJoint.addChild(this.legBckRtHindCurlLongHair[0]);
		this.legBckRtHindCurlJoint.addChild(this.legBckRtHindCurlLongHair[1]);

		this.legFntLft[0].addChild(this.legFntLftBaseCurlJoint);
		this.legFntLft[0].addChild(this.legFntLft[1]);
		this.legFntLftBaseCurlJoint.addChild(this.legFntLftBaseCurlLonghair[0]);
		this.legFntLftBaseCurlJoint.addChild(this.legFntLftBaseCurlLonghair[1]);
		this.legFntLftBaseCurlJoint.addChild(this.legFntLftBaseCurlLonghair[2]);
		this.legFntLftBaseCurlJoint.addChild(this.legFntLftBaseCurlLower);
		this.legFntLftBaseCurlJoint.addChild(this.legFntLftBaseCurlMiddle);
		this.legFntLftBaseCurlJoint.addChild(this.legFntLftBaseCurlUpper);
		this.legFntLft[1].addChild(this.legFntLftForelegCurlJoint);
		this.legFntLftForelegCurlJoint.addChild(this.legFntLftForelegCurl);
		this.legFntLftForelegCurlJoint.addChild(this.legFntLftForelegCurlLongHair[0]);
		this.legFntLftForelegCurlJoint.addChild(this.legFntLftForelegCurlLongHair[1]);
		this.legFntRt[0].addChild(this.legFntRtBaseCurlJoint);
		this.legFntRt[0].addChild(this.legFntRt[1]);
		this.legFntRtBaseCurlJoint.addChild(this.legFntRtBaseCurlLonghair[0]);
		this.legFntRtBaseCurlJoint.addChild(this.legFntRtBaseCurlLonghair[1]);
		this.legFntRtBaseCurlJoint.addChild(this.legFntRtBaseCurlLonghair[2]);
		this.legFntRtBaseCurlJoint.addChild(this.legFntRtBaseCurlLower);
		this.legFntRtBaseCurlJoint.addChild(this.legFntRtBaseCurlMiddle);
		this.legFntRtBaseCurlJoint.addChild(this.legFntRtBaseCurlUpper);
		this.legFntRt[1].addChild(this.legFntRtForelegCurlJoint);
		this.legFntRtForelegCurlJoint.addChild(this.legFntRtForelegCurl);
		this.legFntRtForelegCurlJoint.addChild(this.legFntRtForelegCurlLongHair[0]);
		this.legFntRtForelegCurlJoint.addChild(this.legFntRtForelegCurlLongHair[1]);
		this.neck.addChild(this.headJoint);
		this.neckJoint.addChild(this.neck);

		for(int i = 0; i < tail.length - 1; i++)
		{
			this.tail[i][0].addChild(this.tail[i][1]);
			this.tail[i][1].addChild(this.tail[i + 1][0]);
		}

		this.tail[1][1].addChild(this.tailCurlBot);
		this.tail[2][1].addChild(this.tailCurlTop);

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
		float verticleVelocity = (float)((IMobMotionTracker)entity).getHeightVelocity();

		float idleDampener = 1F;

		if(!((EntityOkamiSylveon)entity).isSitting())
		{
			if(animationDeployer.getEntity().getAnimationID() == LibraryOkamiPokemonAttackID.GLAIVE_SLASH || 
					animationDeployer.getEntity().getAnimationID() == LibraryOkamiPokemonAttackID.GLAIVE_SLASH_REVERSE)
			{
				idleDampener = animateSlash(animationDeployer.getEntity(), distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
			}
		}

		animateBody((EntityOkamiSylveon)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, idleDampener, angularVelocity, verticleVelocity);
		animateHead((EntityOkamiSylveon)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, idleDampener, verticleVelocity);
		animateLegs((EntityOkamiSylveon)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, idleDampener, verticleVelocity);
		animateTail((EntityOkamiSylveon)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, idleDampener, angularVelocity, verticleVelocity);

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

		headBowRtInfo.resetNewAngles();
		headBowLftInfo.resetNewAngles();

		bodyInfo.resetNewAngles();
		bodyInfo.resetNewPnt();

		for(int i = 0; i < headLftFeelerInfo.length; i++)
		{
			headLftFeelerInfo[i].resetNewAngles();
			headRtFeelerInfo[i].resetNewAngles();
		}

		for(int i = 0; i < bodyLftFeelerInfo.length; i++)
		{
			bodyLftFeelerInfo[i].resetNewAngles();
			bodyRtFeelerInfo[i].resetNewAngles();
		}

		for(int i = 0; i < legFntRt.length; i++)
		{
			legFntRtInfo[i].resetNewAngles();
			legFntLftInfo[i].resetNewAngles();
		}

		for(int i = 0; i < legBckRtInfo.length; i++)
		{
			legBckRtInfo[i].resetNewAngles();
			legBckLftInfo[i].resetNewAngles();
		}

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

		Vector3f bodyPointChanges = new Vector3f(0,0,0);
		Vector3f bodyAngleChanges = new Vector3f(0,0,0);

		Vector3f legFntLftAngleChanges = new Vector3f(0,0,0);
		Vector3f forelegFntLftAngleChanges = new Vector3f(0,0,0);

		Vector3f legFntRtAngleChanges = new Vector3f(0,0,0);
		Vector3f forelegFntRtAngleChanges = new Vector3f(0,0,0);

		Vector3f legBckAngleChanges = new Vector3f(0,0,0);

		/* ******** Beginning Slash Timers *********/
		float halfSlashDurationStart = 0;
		float halfSlashDurationEnd = endDuration / 2;
		float halfSlashDuration = endDuration / 2;

		Vector3f halfSlashBodyPointChanges = new Vector3f(0F, 3F, -5F);

		Vector3f halfSlashBodyAngleChanges = new Vector3f(
				(float)Math.toRadians(40),
				(float)Math.toRadians(0),
				(float)Math.toRadians(0));

		Vector3f halfSlashlegFntLftAngleChanges = new Vector3f(
				(float)Math.toRadians(125),
				(float)Math.toRadians(0),
				(float)Math.toRadians(0));
		Vector3f halfSlashForelegFntLftAngleChanges = new Vector3f(
				(float)Math.toRadians(-95),
				(float)Math.toRadians(0),
				(float)Math.toRadians(0));

		Vector3f halfSlashlegFntRtAngleChanges = new Vector3f(
				(float)Math.toRadians(-40),
				(float)Math.toRadians(0),
				(float)Math.toRadians(0));
		Vector3f halfSlashForelegFntRtAngleChanges = new Vector3f(
				(float)Math.toRadians(-20),
				(float)Math.toRadians(0),
				(float)Math.toRadians(0));

		Vector3f halfSlashLegBckAngleChanges = new Vector3f(
				(float)Math.toRadians(25),
				(float)Math.toRadians(0),
				(float)Math.toRadians(0));

		/* ******** Beginning Slash Timers *********/
		float beginningSlashDurationStart = 0;
		float beginningSlashDurationEnd = firstPhaseEnd;
		float beginningSlashDuration = firstPhaseEnd;

		/* ******** Mid Slash Timers *********/
		float midSlashDurationStart = firstPhaseEnd;
		float midSlashDurationEnd = secondPhaseEnd;
		float midSlashDuration = secondPhaseEnd - firstPhaseEnd;

		/* ******** End Slash Timers *********/
		float endSlashDurationStart = secondPhaseEnd;
		float endSlashDurationEnd = endDuration;
		float endSlashDuration = endDuration - secondPhaseEnd;

		/*
		 * Whole slash animations
		 */
		float halfSlashDurationCovered = PartAnimate.calculateDuration(tick, 0, halfSlashDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyPointChanges, halfSlashBodyPointChanges,
				halfSlashDurationCovered);

		PartAnimate.changeOverDurationSCurve(bodyAngleChanges, halfSlashBodyAngleChanges,
				halfSlashDurationCovered);

		PartAnimate.changeOverDurationSCurve(legFntLftAngleChanges, halfSlashlegFntLftAngleChanges,
				halfSlashDurationCovered);
		PartAnimate.changeOverDurationSCurve(forelegFntLftAngleChanges, halfSlashForelegFntLftAngleChanges,
				halfSlashDurationCovered);

		PartAnimate.changeOverDurationSCurve(legFntRtAngleChanges, halfSlashlegFntRtAngleChanges,
				halfSlashDurationCovered);
		PartAnimate.changeOverDurationSCurve(forelegFntRtAngleChanges, halfSlashForelegFntRtAngleChanges,
				halfSlashDurationCovered);

		PartAnimate.changeOverDurationSCurve(legBckAngleChanges, halfSlashLegBckAngleChanges,
				halfSlashDurationCovered);

		/*
		 * Return to idle animations
		 */
		float returnDampener = 1 - PartAnimate.calculateDuration(tick, midSlashDurationEnd, endDuration);

		bodyPointChanges.multiplyVector(returnDampener);
		bodyAngleChanges.multiplyVector(returnDampener);
		legFntLftAngleChanges.multiplyVector(returnDampener);
		forelegFntLftAngleChanges.multiplyVector(returnDampener);
		legFntRtAngleChanges.multiplyVector(returnDampener);
		forelegFntRtAngleChanges.multiplyVector(returnDampener);
		legBckAngleChanges.multiplyVector(returnDampener);

		PartAnimate.applyPointChangeVectorToInfo(bodyInfo, bodyPointChanges);
		PartAnimate.applyRotationChangeVectorToInfo(bodyInfo, bodyAngleChanges);

		PartAnimate.applyRotationChangeVectorToInfo(legFntLftInfo[0], legFntLftAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(legFntLftInfo[1], forelegFntLftAngleChanges);

		PartAnimate.applyRotationChangeVectorToInfo(legFntRtInfo[0], legFntRtAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(legFntRtInfo[1], forelegFntRtAngleChanges);

		PartAnimate.applyRotationChangeVectorToInfo(legBckLftInfo[0], legBckAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(legBckRtInfo[0], Vector3f.negative(legBckAngleChanges));

		//PartAnimate.headAnimateInfoOnlyWithAngleModifiers(glaiveJointBaseInfo, par4, par5, (1 - idleDampener) * 1.2F, 0);

		return idleDampener;
	}

	public void animateBody(EntityOkamiSylveon entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener, float angularVelocity, float verticalVelocity)
	{
		if(!entity.isSitting())
		{
			float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
			float runCycleInterval = (RUN_FREQUENCY * entity.getGravityFactor() * distanceMoved % (2 * PI))/(2 * PI);

			float bodyWalkAngle = (float)Math.toRadians(13);
			float bodyRunAngle = (float)Math.toRadians(20);

			float walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI + PI/2) * bodyWalkAngle * (1 - horzVelocity);
			float runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI + PI/2) * bodyRunAngle * horzVelocity;

			float bodyJumpAngle = (float)Math.toRadians(-65);
			float verticalVelocityChangeAngle = (float)Math.toRadians(10);

			float newVerticalVelocity = 1.5F * verticalVelocity;

			if(newVerticalVelocity > 1F)
				newVerticalVelocity = 1F;
			else if(newVerticalVelocity < -1F)
				newVerticalVelocity = -1F;

			bodyInfo.setNewRotateX(bodyInfo.getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocity * idleDampener * (1F - Math.abs(newVerticalVelocity)) 
					+ bodyJumpAngle * newVerticalVelocity);

			float totalVelocity = Math.abs(verticalVelocity) + horzVelocity;

			if(totalVelocity > 1F)
				totalVelocity = 1F;

			float yawChangeAngle = -(float)Math.toRadians(15);

			/* *************** */
			for(int i = 0; i < bodyLftFeeler.length; i++)
			{
				IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockRibbons(i);

				float idleAmplitudeX = 0.13F * (1F - horzVelocity);
				float idleAmplitudeY = 0.07F * (1F - horzVelocity);

				// + (float)Math.toRadians(45)
				float angleChangeX = 0;
				float angleChangeY = 0;

				float moveAmplitudeY = 0.07F;

				float moveAngleY = PartAnimate.negCosRotateAnimationAdjusted(distanceMoved - (2F * PI * ((float)i / (float)(bodyLftFeeler.length - 1))), totalVelocity, WALK_FREQUENCY, moveAmplitudeY) + totalVelocity * (float)Math.toRadians(5)
						* ((float)i / (float)(bodyLftFeeler.length - 1));

				angleChangeX += (MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2) * idleAmplitudeX)
						* (1F - (Math.abs(newVerticalVelocity) * 0.9F))
						* (1F - (Math.pow(Math.E, -0.4F * (float)(i + 1)))) + newVerticalVelocity * verticalVelocityChangeAngle * ((float)i / (float)(bodyLftFeeler.length - 1));
				angleChangeY += (MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2) * idleAmplitudeY)
						* (1F - (Math.pow(Math.E, -0.4F * (float)(i + 1)))) * (1F - Math.abs(angularVelocity)) * (1F - (Math.abs(newVerticalVelocity) * 0.5F))
						+ (angularVelocity <= 0 ? angularVelocity : angularVelocity * 0.1F) * yawChangeAngle * ((float)i / (float)(bodyLftFeeler.length - 1));

				bodyLftFeelerInfo[i].setNewRotateX(bodyLftFeelerInfo[i].getNewRotateX() + angleChangeX);
				bodyLftFeelerInfo[i].setNewRotateY(bodyLftFeelerInfo[i].getNewRotateY() + angleChangeY + moveAngleY);

				angleChangeX = 0;
				angleChangeY = 0;

				angleChangeX += (MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2 + (PI/4)) * idleAmplitudeX)
						* (1F - (Math.abs(newVerticalVelocity) * 0.9F)) 
						* (1F - (Math.pow(Math.E, -0.4F * (float)(i + 1)))) + newVerticalVelocity * verticalVelocityChangeAngle * ((float)i / (float)(bodyLftFeeler.length - 1));
				angleChangeY += (MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2 + (PI/4)) * idleAmplitudeY)
						* (1F - (Math.pow(Math.E, -0.4F * (float)(i + 1)))) * (1F - Math.abs(angularVelocity)) * (1F - (Math.abs(newVerticalVelocity) * 0.5F))
						+ (angularVelocity >= 0 ? angularVelocity : angularVelocity * 0.1F) * yawChangeAngle * ((float)i / (float)(bodyLftFeeler.length - 1));

				bodyRtFeelerInfo[i].setNewRotateX(bodyRtFeelerInfo[i].getNewRotateX() + angleChangeX);
				bodyRtFeelerInfo[i].setNewRotateY(bodyRtFeelerInfo[i].getNewRotateY() + angleChangeY - moveAngleY);
			}
		}
		else
		{
			bodyInfo.setNewRotateX(bodySitInfo.getNewRotateX());
			bodyInfo.setNewPointY(bodySitInfo.getNewPointY());
			/* *************** */

			for(int i = 0; i < bodyLftFeeler.length; i++)
			{
				IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockRibbons(i);

				float idleAmplitudeX = 0.13F;
				float idleAmplitudeY = 0.07F;

				// + (float)Math.toRadians(45)
				float angleChangeX = 0;
				float angleChangeY = 0;

				angleChangeX += (MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2) * idleAmplitudeX)
						* ( 1 - (Math.pow(Math.E, -0.4F * (float)(i + 1))));
				angleChangeY += (MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2) * idleAmplitudeY)
						* ( 1 - (Math.pow(Math.E, -0.4F * (float)(i + 1))));

				bodyLftFeelerInfo[i].setNewRotateX(bodyLftFeelerSitInfo[i].getNewRotateX() + angleChangeX);
				bodyLftFeelerInfo[i].setNewRotateY(bodyLftFeelerSitInfo[i].getNewRotateY() + angleChangeY);

				angleChangeX = 0;
				angleChangeY = 0;

				angleChangeX += (MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2 + (PI/4)) * idleAmplitudeX)
						* ( 1 - (Math.pow(Math.E, -0.4F * (float)(i + 1))));
				angleChangeY += (MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2 + (PI/4)) * idleAmplitudeY)
						* ( 1 - (Math.pow(Math.E, -0.4F * (float)(i + 1))));

				bodyRtFeelerInfo[i].setNewRotateX(bodyRtFeelerSitInfo[i].getNewRotateX() + angleChangeX);
				bodyRtFeelerInfo[i].setNewRotateY(bodyRtFeelerSitInfo[i].getNewRotateY() + angleChangeY);
			}
		}
	}

	public void animateHead(EntityOkamiSylveon entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener, float verticalVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, neckJointInfo);

		PartAnimate.headAnimateInfoOnlyWithAngleModifiers(neckInfo, yawHeadOffsetDifference, pitchRotation, 0.2F, 0.2F);

		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockBody();

		float idleAmplitudeX = 0.1F;

		float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
		float runCycleInterval = (RUN_FREQUENCY * entity.getGravityFactor() * distanceMoved % (2 * PI))/(2 * PI);

		float newVerticalVelocity = 1.5F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;

		float fallingEarAngle = (float)Math.toRadians(25);

		if(entity.isSitting())
		{
			idleAmplitudeX *= 0.5F;
		}
		else
		{
			float walkAngle = (float)Math.toRadians(4);
			float runAngle = (float)Math.toRadians(8);

			float walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI) * walkAngle * (1 - horzVelocity)
					+ walkAngle * (1 - horzVelocity);
			float runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI) * runAngle * horzVelocity
					+ runAngle * horzVelocity;

			neckInfo.setNewRotateX(neckInfo.getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocity);

			/* ******************* */
			walkAngle = (float)Math.toRadians(5);
			runAngle = (float)Math.toRadians(10);

			walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI) * walkAngle * (1 - horzVelocity)
					+ walkAngle * (1 - horzVelocity);
			runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI) * runAngle * horzVelocity
					+ runAngle * horzVelocity;

			earLftInfo.setNewRotateZ(earLftInfo.getNewRotateZ() + ((walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity)) + fallingEarAngle * newVerticalVelocity));
			earRtInfo.setNewRotateZ(earRtInfo.getNewRotateZ() - ((walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity)) + fallingEarAngle * newVerticalVelocity));
		}

		float angleChangeX = -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeX + idleAmplitudeX;

		neckInfo.setNewRotateX(neckInfo.getNewRotateX() + angleChangeX);

		JointAnimation.reverseJointRotatesChange(neckInfo, headJointInfo);

		PartAnimate.headAnimateInfoOnlyWithAngleModifiers(headInfo, yawHeadOffsetDifference, pitchRotation, 0.9F, 0.9F);

		/* *************** */
		float idleAmplitudeZ = 0.10F;

		// + (float)Math.toRadians(45)
		float angleChangeZ = MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI + PI) * idleAmplitudeZ * (1 - horzVelocity);

		earRtInfo.setNewRotateZ(earRtInfo.getNewRotateZ() - angleChangeZ);
		earLftInfo.setNewRotateZ(earLftInfo.getNewRotateZ() + angleChangeZ);

		float verticalVelocityChangeAngle = (float)Math.toRadians(4);

		float totalVelocity = Math.abs(verticalVelocity) + horzVelocity;

		if(totalVelocity > 1F)
			totalVelocity = 1F;

		float fallOnlyVelocity = verticalVelocity;

		if(fallOnlyVelocity >= 0)
			fallOnlyVelocity = 0;

		/* *************** */
		for(int i = 0; i < headLftFeeler.length; i++)
		{
			currentIdleAnimationClock = entity.getIdleAnimationClockRibbons(i);

			float moveAmplitudeX = 0.03F;
			float moveAmplitudeY = 0.03F;

			float moveAngleX = PartAnimate.negCosRotateAnimationAdjusted(distanceMoved - (2F * PI * ((float)i / (float)(bodyLftFeeler.length - 1))), totalVelocity, WALK_FREQUENCY * 0.8F, moveAmplitudeY) + totalVelocity * (float)Math.toRadians(5)
					* ((float)i / (float)(bodyLftFeeler.length - 1));
			float moveAngleY = PartAnimate.negCosRotateAnimationAdjusted(distanceMoved - (2F * PI * ((float)i / (float)(bodyLftFeeler.length - 1))), totalVelocity, WALK_FREQUENCY, moveAmplitudeY) + totalVelocity * (float)Math.toRadians(5)
					* ((float)i / (float)(bodyLftFeeler.length - 1));

			idleAmplitudeX = 0.07F;
			float idleAmplitudeY = 0.03F;

			// + (float)Math.toRadians(45)
			angleChangeX = moveAngleX;
			float angleChangeY = moveAngleY;

			angleChangeX += (MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2F) * idleAmplitudeX)
					* (1F - (Math.pow(Math.E, -0.4F * (float)(i + 1))));
			angleChangeY += (MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2F) * idleAmplitudeY)
					* (1F - (Math.pow(Math.E, -0.4F * (float)(i + 1))));

			headLftFeelerInfo[i].setNewRotateX(headLftFeelerInfo[i].getNewRotateX() + angleChangeX);
			headLftFeelerInfo[i].setNewRotateY(headLftFeelerInfo[i].getNewRotateY() + angleChangeY);
		}

		for(int i = 0; i < headRtFeeler.length; i++)
		{
			currentIdleAnimationClock = entity.getIdleAnimationClockRibbonsHeadRt(i);

			idleAmplitudeX = 0.03F;
			float idleAmplitudeY = 0.05F;

			float moveAmplitude = 0.1F;

			// + (float)Math.toRadians(45)
			angleChangeX = 0;
			float angleChangeY = 0;

			angleChangeX += (MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2F) * idleAmplitudeX)
					* (1F - (Math.pow(Math.E, -0.4F * (float)(i + 1))))
					+ verticalVelocityChangeAngle * newVerticalVelocity;
			angleChangeY += (MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2F) * idleAmplitudeY)
					* (1F - (Math.pow(Math.E, -0.4F * (float)(i + 1))))
					- 6F * verticalVelocityChangeAngle * fallOnlyVelocity * (float)((float)i / (float)(headRtFeeler.length - 1));

			headRtFeelerInfo[i].setNewRotateX(headRtFeelerInfo[i].getNewRotateX() + angleChangeX);
			headRtFeelerInfo[i].setNewRotateY(headRtFeelerInfo[i].getNewRotateY() + angleChangeY);
		}

		float walkAngle = (float)Math.toRadians(4);
		float runAngle = (float)Math.toRadians(8);

		float walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI) * walkAngle * (1 - horzVelocity)
				+ walkAngle * (1 - horzVelocity);
		float runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI) * runAngle * horzVelocity
				+ runAngle * horzVelocity;

		float fallingBowAngle = (float)Math.toRadians(-45);

		headBowLftInfo.setNewRotateZ(headBowLftInfo.getNewRotateZ() + ((walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity)) + fallingBowAngle * newVerticalVelocity));
		headBowRtInfo.setNewRotateZ(headBowRtInfo.getNewRotateZ() - ((walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity)) + fallingBowAngle * newVerticalVelocity));

		if(entity.isTamed())
		{
			float healthChangeAngle = (float)Math.toRadians(-55);

			earLftInfo.setNewRotateZ(earLftInfo.getNewRotateZ() - (1F - entity.getHealthPercent()) * healthChangeAngle);
		}
	}

	public void animateLegs(EntityOkamiSylveon entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener, float verticalVelocity)
	{
		if(!entity.isSitting())
		{
			float frequency = this.RUN_FREQUENCY * entity.getGravityFactor();
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

			/**/
			JointAnimation.reverseJointRotatesChange(bodyInfo, legFntRtInfo[0]);
			JointAnimation.reverseJointRotatesChange(bodyInfo, legFntLftInfo[0]);
			JointAnimation.reverseJointRotatesChange(bodyInfo, legBckRtInfo[0]);
			JointAnimation.reverseJointRotatesChange(bodyInfo, legBckLftInfo[0]);

			//( 1 - (Math.pow(Math.E, -0.4F * (float)(i + 1))));

			legFntRtInfo[0].setNewRotateX(legFntRtInfo[0].getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legFntRtModifier, horzVelocity, frequency, amplitude) * (1F - Math.abs(newVerticalVelocity)) + fallingLegAngle * fallingModifier);
			legFntLftInfo[0].setNewRotateX(legFntLftInfo[0].getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, frequency, amplitude) * (1F - Math.abs(newVerticalVelocity)) + fallingLegAngle * fallingModifier);
			legBckRtInfo[0].setNewRotateX(legBckRtInfo[0].getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legBckRtModifier, horzVelocity, frequency, amplitude) * (1F - Math.abs(newVerticalVelocity)));
			legBckLftInfo[0].setNewRotateX(legBckLftInfo[0].getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legBckLftModifier, horzVelocity, frequency, amplitude) * (1F - Math.abs(newVerticalVelocity)));
		}
		else
		{
			for(int i = 0; i < legFntRtInfo.length; i++)
			{
				legFntRtInfo[i].setNewRotateX(legFntRtSitInfo[i].getNewRotateX());
				legFntLftInfo[i].setNewRotateX(legFntLftSitInfo[i].getNewRotateX());
			}

			for(int i = 0; i < legBckRtInfo.length; i++)
			{
				legBckRtInfo[i].setNewRotateX(legBckRtSitInfo[i].getNewRotateX());
				legBckLftInfo[i].setNewRotateX(legBckLftSitInfo[i].getNewRotateX());
			}
		}
	}

	public void animateTail(EntityOkamiSylveon entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener, float angularVelocity, float verticalVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, tailInfo[0][0]);

		float yawChangeAngle = -(float)Math.toRadians(15);
		float verticalVelocityChangeAngle = (float)Math.toRadians(-9);
		float healthChangeAngle = entity.isTamed() ? (float)Math.toRadians(-8) : 0;

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

			float moveAmplitudeX = 0.1F;

			// + (float)Math.toRadians(45)
			float angleChangeX = 0;
			float angleChangeY = 0;

			// Idle Animations
			angleChangeX += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2) * idleAmplitudeX 
					* (1F - absoluteMoveVelocity) * (1F - Math.abs(angularVelocity) * 0.5F);
			angleChangeY += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2) * idleAmplitudeY
					* (1F - absoluteMoveVelocity) * (1F - Math.abs(angularVelocity) * 0.5F);

			// Movement Animations
			angleChangeX += PartAnimate.negCosRotateAnimationAdjusted(distanceMoved - (2F * PI * ((float)i / (float)(tail.length - 1))), absoluteMoveVelocity, RUN_FREQUENCY * entity.getGravityFactor(), moveAmplitudeX) 
					+ verticalVelocityChangeAngle * newVerticalVelocity;
			angleChangeY += angularVelocity * yawChangeAngle;

			// Tamable Animation
			angleChangeX += (entity.isTamed() ? (1F - entity.getHealthPercent()) * healthChangeAngle : 0);

			// Part Number Dampener
			angleChangeX *= (1F - (Math.pow(Math.E, -0.95F * (float)(i + 1))));
			angleChangeY *= (1F - (Math.pow(Math.E, -0.95F * (float)(i + 1))));

			tailInfo[i][1].setNewRotateX(tailInfo[i][1].getNewRotateX() + angleChangeX);
			tailInfo[i][1].setNewRotateY(tailInfo[i][1].getNewRotateY() + angleChangeY);
		}

		if(entity.isSitting())
		{
			tailInfo[0][1].setNewRotateX(tailInfo[0][1].getNewRotateX() + (float)Math.toRadians(25));
		}
	}

	public void deployAnimations()
	{
		this.animationDeployer.move(body, bodyInfo.getNewPnt());
		this.animationDeployer.rotate(body, bodyInfo.getNewRotates());

		this.animationDeployer.rotate(neckJoint, neckJointInfo.getNewRotates());
		this.animationDeployer.rotate(neck, neckInfo.getNewRotates());
		this.animationDeployer.rotate(headJoint, headJointInfo.getNewRotates());
		this.animationDeployer.rotate(head, headInfo.getNewRotates());

		this.animationDeployer.rotate(earLft, earLftInfo.getNewRotates());
		this.animationDeployer.rotate(earRt, earRtInfo.getNewRotates());

		this.animationDeployer.rotate(headBowLft, headBowLftInfo.getNewRotates());
		this.animationDeployer.rotate(headBowRt, headBowRtInfo.getNewRotates());

		for(int i = 0; i < headLftFeelerInfo.length; i++)
		{
			this.animationDeployer.rotate(headLftFeeler[i], headLftFeelerInfo[i].getNewRotates());
			this.animationDeployer.rotate(headRtFeeler[i], headRtFeelerInfo[i].getNewRotates());
		}

		for(int i = 0; i < headLftFeelerInfo.length; i++)
		{
			this.animationDeployer.rotate(bodyLftFeeler[i], bodyLftFeelerInfo[i].getNewRotates());
			this.animationDeployer.rotate(bodyRtFeeler[i], bodyRtFeelerInfo[i].getNewRotates());
		}

		for(int i = 0; i < legFntRtInfo.length; i++)
		{
			this.animationDeployer.rotate(legFntRt[i], legFntRtInfo[i].getNewRotates());
			this.animationDeployer.rotate(legFntLft[i], legFntLftInfo[i].getNewRotates());
		}

		for(int i = 0; i < legBckRtInfo.length; i++)
		{
			this.animationDeployer.rotate(legBckRt[i], legBckRtInfo[i].getNewRotates());
			this.animationDeployer.rotate(legBckLft[i], legBckLftInfo[i].getNewRotates());
		}

		for(int i = 0; i < tail.length; i++)
		{
			this.animationDeployer.rotate(tail[i][0], tailInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(tail[i][1], tailInfo[i][1].getNewRotates());
		}

		this.animationDeployer.applyChanges();
	}
}