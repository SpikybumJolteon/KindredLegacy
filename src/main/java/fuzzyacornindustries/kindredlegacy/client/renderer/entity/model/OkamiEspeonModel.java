package fuzzyacornindustries.kindredlegacy.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;

import fuzzyacornindustries.kindredlegacy.KindredLegacy;
import fuzzyacornindustries.kindredlegacy.client.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.client.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.client.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.animation.JointAnimation;
import fuzzyacornindustries.kindredlegacy.client.animation.PartAnimate;
import fuzzyacornindustries.kindredlegacy.client.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.client.animation.Vector3f;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.OkamiEspeonEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.TamablePokemon;
import fuzzyacornindustries.kindredlegacy.lib.action.LibraryOkamiPokemonAttackID;
import net.minecraft.client.renderer.entity.model.TintedAgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class OkamiEspeonModel<T extends TamablePokemon> extends TintedAgeableModel<T>
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public final float RUN_FREQUENCY = 1.2F;
	public final float WALK_FREQUENCY = 1.2F;
	public final float SNEAK_VELOCITY = 0.45F;

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
	public ModelRenderer gem;

	public ModelRenderer earLftJoint;
	public ModelRenderer earLft;
	public ModelRenderer earLftTip;
	PartInfo earLftInfo;

	public ModelRenderer earRtJoint;
	public ModelRenderer earRt;
	public ModelRenderer earRtTip;
	PartInfo earRtInfo;

	public ModelRenderer cheekTuffs[][] = new ModelRenderer[2][3];
	PartInfo cheekTuffsInfo[][] = new PartInfo[cheekTuffs.length][cheekTuffs[0].length];

	public ModelRenderer tail[][] = new ModelRenderer[5][2];
	PartInfo tailInfo[][] = new PartInfo[tail.length][2];

	public ModelRenderer tailFork[][][] = new ModelRenderer[2][5][2];
	PartInfo tailForkInfo[][][] = new PartInfo[tailFork.length][tailFork[0].length][2];

	public ModelRenderer tailCurlBot;
	public ModelRenderer tailCurlTop;

	public OkamiEspeonModel() 
	{
		this.textureWidth = 64;
		this.textureHeight = 64;

		int jointNumber = 0;
		int partNumber = 1;

		int leftSide = 0;
		int rightSide = 1;

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
		this.muzzle.addBox(-1.0F, -1.0F, -0.7F, 2, 2, 2, 0.0F);
		this.setRotateAngle(muzzle, 0.17453292519943295F, 0.0F, 0.0F);
		this.muzzle.setTextureOffset(60, 11).addBox(-0.5F, -0.9F, -1.0F, 1, 1, 1, 0.0F); // nose
		this.gem = new ModelRenderer(this, 44, 0);
		this.gem.setRotationPoint(0.0F, -3.7F, -3.9F);
		this.gem.addBox(-0.5F, -0.5F, -0.3F, 1, 1, 1, 0.0F);
		this.setRotateAngle(gem, 0.0F, 0.0F, -0.7853981633974483F);

		this.earLftJoint = new ModelRenderer(this, 0, 0);
		this.earLftJoint.setRotationPoint(2.0F, -4.0F, -2.0F);
		this.earLftJoint.addBox(-1.0F, -1.5F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earLftJoint, 0.0F, 0.0F, 1.0471975511965976F);
		this.earLft = new ModelRenderer(this, 0, 0);
		this.earLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earLft.addBox(-1.0F, -1.5F, 0.0F, 4, 3, 1, 0.0F);
		this.earLft.setTextureOffset(11, 0).addBox(-0.7F, -3.5F, 0.0F, 3, 2, 1, 0.0F); // earLftMid
		this.earLft.setTextureOffset(20, 0).addBox(-0.5F, -4.5F, 0.0F, 2, 1, 1, 0.0F); // earLftEnd
		this.earLftInfo = new PartInfo(earLft);
		this.earLftTip = new ModelRenderer(this, 19, 3);
		this.earLftTip.setRotationPoint(0.4F, -4.5F, 0.0F);
		this.earLftTip.addBox(-2.0F, -0.5F, 0.01F, 3, 1, 1, 0.0F);
		this.setRotateAngle(earLftTip, 0.0F, 0.0F, 0.7853981633974483F);
		this.earRtJoint = new ModelRenderer(this, 0, 0);
		this.earRtJoint.setRotationPoint(-2.0F, -4.0F, -2.0F);
		this.earRtJoint.addBox(-1.0F, -1.5F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earRtJoint, 0.0F, 0.0F, -1.0471975511965976F);
		this.earRt = new ModelRenderer(this, 17, 6);
		this.earRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earRt.addBox(-3.0F, -1.5F, 0.0F, 4, 3, 1, 0.0F);
		this.earRt.setTextureOffset(8, 6).addBox(-2.3F, -3.5F, 0.0F, 3, 2, 1, 0.0F); // earRtMid
		this.earRt.setTextureOffset(1, 6).addBox(-1.5F, -4.5F, 0.0F, 2, 1, 1, 0.0F); // earRtEnd
		this.earRtInfo = new PartInfo(earRt);
		this.earRtTip = new ModelRenderer(this, 0, 9);
		this.earRtTip.setRotationPoint(-0.4F, -4.5F, 0.0F);
		this.earRtTip.addBox(-1.0F, -0.5F, 0.01F, 3, 1, 1, 0.0F);
		this.setRotateAngle(earRtTip, 0.0F, 0.0F, -0.7853981633974483F);

		int cheekTuffNumber = 0;
		this.cheekTuffs[leftSide][cheekTuffNumber] = new ModelRenderer(this, 50, 17); // cheekTuffLftTop1
		this.cheekTuffs[leftSide][cheekTuffNumber].setRotationPoint(2.0F, -1.5F, -2.5F);
		this.cheekTuffs[leftSide][cheekTuffNumber].addBox(-0.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(cheekTuffs[leftSide][cheekTuffNumber], 0.0F, 0.0F, 0.5235987755982988F);
		this.cheekTuffsInfo[leftSide][cheekTuffNumber] = new PartInfo(this.cheekTuffs[leftSide][cheekTuffNumber]);
		cheekTuffNumber++;
		this.cheekTuffs[leftSide][cheekTuffNumber] = new ModelRenderer(this, 58, 17); // cheekTuffLftTop2
		this.cheekTuffs[leftSide][cheekTuffNumber].setRotationPoint(2.3F, 0.0F, 0.0F);
		this.cheekTuffs[leftSide][cheekTuffNumber].addBox(-0.2F, -0.5F, -0.51F, 2, 1, 1, 0.0F);
		this.cheekTuffsInfo[leftSide][cheekTuffNumber] = new PartInfo(this.cheekTuffs[leftSide][cheekTuffNumber]);
		cheekTuffNumber++;
		this.cheekTuffs[leftSide][cheekTuffNumber] = new ModelRenderer(this, 50, 20); // cheekTuffLftBot
		this.cheekTuffs[leftSide][cheekTuffNumber].setRotationPoint(2.0F, -1.0F, -2.4F);
		this.cheekTuffs[leftSide][cheekTuffNumber].addBox(0.0F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		this.setRotateAngle(cheekTuffs[leftSide][cheekTuffNumber], 0.0F, 0.0F, 0.7853981633974483F);
		this.cheekTuffsInfo[leftSide][cheekTuffNumber] = new PartInfo(this.cheekTuffs[leftSide][cheekTuffNumber]);
		cheekTuffNumber = 0;
		this.cheekTuffs[rightSide][cheekTuffNumber] = new ModelRenderer(this, 41, 17); // cheekTuffRtTop1
		this.cheekTuffs[rightSide][cheekTuffNumber].setRotationPoint(-2.0F, -1.5F, -2.5F);
		this.cheekTuffs[rightSide][cheekTuffNumber].addBox(-2.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(cheekTuffs[rightSide][cheekTuffNumber], 0.0F, 0.0F, -0.5235987755982988F);
		this.cheekTuffsInfo[rightSide][cheekTuffNumber] = new PartInfo(this.cheekTuffs[rightSide][cheekTuffNumber]);
		cheekTuffNumber++;
		this.cheekTuffs[rightSide][cheekTuffNumber] = new ModelRenderer(this, 35, 17); // cheekTuffRtTop2
		this.cheekTuffs[rightSide][cheekTuffNumber].setRotationPoint(-2.3F, 0.0F, 0.0F);
		this.cheekTuffs[rightSide][cheekTuffNumber].addBox(-1.8F, -0.5F, -0.51F, 2, 1, 1, 0.0F);
		this.cheekTuffsInfo[rightSide][cheekTuffNumber] = new PartInfo(this.cheekTuffs[rightSide][cheekTuffNumber]);
		cheekTuffNumber++;
		this.cheekTuffs[rightSide][cheekTuffNumber] = new ModelRenderer(this, 43, 20); // cheekTuffRtBot
		this.cheekTuffs[rightSide][cheekTuffNumber].setRotationPoint(-2.0F, -1.0F, -2.4F);
		this.cheekTuffs[rightSide][cheekTuffNumber].addBox(-2.0F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		this.setRotateAngle(cheekTuffs[rightSide][cheekTuffNumber], 0.0F, 0.0F, -0.7853981633974483F);
		this.cheekTuffsInfo[rightSide][cheekTuffNumber] = new PartInfo(this.cheekTuffs[rightSide][cheekTuffNumber]);

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
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.5F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 36, 48); // tail2
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail3Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.5F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 36, 45); // tail3
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail4Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.5F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 36, 42); // tail4
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail5Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.5F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 36, 39); // tail5
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);

		tailNumber = 0;
		this.tailFork[rightSide][tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tailRt6Joint
		this.tailFork[rightSide][tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.5F);
		this.tailFork[rightSide][tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tailFork[rightSide][tailNumber][jointNumber], 0.0F, -0.17453292519943295F, 0.0F);
		this.tailForkInfo[rightSide][tailNumber][jointNumber] = new PartInfo(this.tailFork[rightSide][tailNumber][jointNumber]);
		this.tailFork[rightSide][tailNumber][partNumber] = new ModelRenderer(this, 32, 36); // tailRt6
		this.tailFork[rightSide][tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tailFork[rightSide][tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		this.tailForkInfo[rightSide][tailNumber][partNumber] = new PartInfo(this.tailFork[rightSide][tailNumber][partNumber]);
		tailNumber++;
		this.tailFork[rightSide][tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tailRt7Joint
		this.tailFork[rightSide][tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.5F);
		this.tailFork[rightSide][tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.tailForkInfo[rightSide][tailNumber][jointNumber] = new PartInfo(this.tailFork[rightSide][tailNumber][jointNumber]);
		this.tailFork[rightSide][tailNumber][partNumber] = new ModelRenderer(this, 32, 33); // tailRt7
		this.tailFork[rightSide][tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tailFork[rightSide][tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		this.tailForkInfo[rightSide][tailNumber][partNumber] = new PartInfo(this.tailFork[rightSide][tailNumber][partNumber]);
		tailNumber++;
		this.tailFork[rightSide][tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tailRt8Joint
		this.tailFork[rightSide][tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.5F);
		this.tailFork[rightSide][tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.tailForkInfo[rightSide][tailNumber][jointNumber] = new PartInfo(this.tailFork[rightSide][tailNumber][jointNumber]);
		this.tailFork[rightSide][tailNumber][partNumber] = new ModelRenderer(this, 32, 29); // tailRt8
		this.tailFork[rightSide][tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tailFork[rightSide][tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		this.tailForkInfo[rightSide][tailNumber][partNumber] = new PartInfo(this.tailFork[rightSide][tailNumber][partNumber]);
		tailNumber++;
		this.tailFork[rightSide][tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tailRt9Joint
		this.tailFork[rightSide][tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.5F);
		this.tailFork[rightSide][tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.tailForkInfo[rightSide][tailNumber][jointNumber] = new PartInfo(this.tailFork[rightSide][tailNumber][jointNumber]);
		this.tailFork[rightSide][tailNumber][partNumber] = new ModelRenderer(this, 32, 26); // tailRt9
		this.tailFork[rightSide][tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tailFork[rightSide][tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		this.tailForkInfo[rightSide][tailNumber][partNumber] = new PartInfo(this.tailFork[rightSide][tailNumber][partNumber]);
		tailNumber++;
		this.tailFork[rightSide][tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tailRt10Joint
		this.tailFork[rightSide][tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.5F);
		this.tailFork[rightSide][tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.tailForkInfo[rightSide][tailNumber][jointNumber] = new PartInfo(this.tailFork[rightSide][tailNumber][jointNumber]);
		this.tailFork[rightSide][tailNumber][partNumber] = new ModelRenderer(this, 32, 23); // tailRt10
		this.tailFork[rightSide][tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tailFork[rightSide][tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		this.tailForkInfo[rightSide][tailNumber][partNumber] = new PartInfo(this.tailFork[rightSide][tailNumber][partNumber]);

		tailNumber = 0;
		this.tailFork[leftSide][tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tailLft6Joint
		this.tailFork[leftSide][tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.5F);
		this.tailFork[leftSide][tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tailFork[leftSide][tailNumber][jointNumber], 0.0F, 0.17453292519943295F, 0.0F);
		this.tailForkInfo[leftSide][tailNumber][jointNumber] = new PartInfo(this.tailFork[leftSide][tailNumber][jointNumber]);
		this.tailFork[leftSide][tailNumber][partNumber] = new ModelRenderer(this, 38, 36); // tailLft6
		this.tailFork[leftSide][tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tailFork[leftSide][tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		this.tailForkInfo[leftSide][tailNumber][partNumber] = new PartInfo(this.tailFork[leftSide][tailNumber][partNumber]);
		tailNumber++;
		this.tailFork[leftSide][tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tailLft7Joint
		this.tailFork[leftSide][tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.5F);
		this.tailFork[leftSide][tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.tailForkInfo[leftSide][tailNumber][jointNumber] = new PartInfo(this.tailFork[leftSide][tailNumber][jointNumber]);
		this.tailFork[leftSide][tailNumber][partNumber] = new ModelRenderer(this, 38, 33); // tailLft7
		this.tailFork[leftSide][tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tailFork[leftSide][tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		this.tailForkInfo[leftSide][tailNumber][partNumber] = new PartInfo(this.tailFork[leftSide][tailNumber][partNumber]);
		tailNumber++;
		this.tailFork[leftSide][tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tailLft8Joint
		this.tailFork[leftSide][tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.5F);
		this.tailFork[leftSide][tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.tailForkInfo[leftSide][tailNumber][jointNumber] = new PartInfo(this.tailFork[leftSide][tailNumber][jointNumber]);
		this.tailFork[leftSide][tailNumber][partNumber] = new ModelRenderer(this, 38, 29); // tailLft8
		this.tailFork[leftSide][tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tailFork[leftSide][tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		this.tailForkInfo[leftSide][tailNumber][partNumber] = new PartInfo(this.tailFork[leftSide][tailNumber][partNumber]);
		tailNumber++;
		this.tailFork[leftSide][tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tailLft9Joint
		this.tailFork[leftSide][tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.5F);
		this.tailFork[leftSide][tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.tailForkInfo[leftSide][tailNumber][jointNumber] = new PartInfo(this.tailFork[leftSide][tailNumber][jointNumber]);
		this.tailFork[leftSide][tailNumber][partNumber] = new ModelRenderer(this, 38, 26); // tailLft9
		this.tailFork[leftSide][tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tailFork[leftSide][tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		this.tailForkInfo[leftSide][tailNumber][partNumber] = new PartInfo(this.tailFork[leftSide][tailNumber][partNumber]);
		tailNumber++;
		this.tailFork[leftSide][tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tailLft10Joint
		this.tailFork[leftSide][tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.5F);
		this.tailFork[leftSide][tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		this.tailForkInfo[leftSide][tailNumber][jointNumber] = new PartInfo(this.tailFork[leftSide][tailNumber][jointNumber]);
		this.tailFork[leftSide][tailNumber][partNumber] = new ModelRenderer(this, 38, 23); // tailLft10
		this.tailFork[leftSide][tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tailFork[leftSide][tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		this.tailForkInfo[leftSide][tailNumber][partNumber] = new PartInfo(this.tailFork[leftSide][tailNumber][partNumber]);

		this.tailCurlBot = new ModelRenderer(this, 20, 24);
		this.tailCurlBot.setRotationPoint(-0.6F, 0.9F, 0.9F);
		this.tailCurlBot.addBox(-0.5F, -0.5F, -1.5F, 1, 2, 2, 0.0F);
		this.setRotateAngle(tailCurlBot, 0.7853981633974483F, 0.0F, 0.0F);
		this.tailCurlTop = new ModelRenderer(this, 26, 24);
		this.tailCurlTop.setRotationPoint(0.6F, -0.5F, 0.9F);
		this.tailCurlTop.addBox(-0.5F, -1.5F, -0.5F, 1, 2, 2, 0.0F);
		this.setRotateAngle(tailCurlTop, 0.7853981633974483F, 0.0F, 0.0F);

		this.body.addChild(this.legBckLft[0]);
		this.body.addChild(this.legBckRt[0]);
		this.body.addChild(this.legFntLft[0]);
		this.body.addChild(this.legFntRt[0]);
		this.body.addChild(this.neckJoint);
		this.body.addChild(this.tail[0][0]);

		this.earLftJoint.addChild(this.earLft);
		this.earRtJoint.addChild(this.earRt);

		this.neckJoint.addChild(this.neck);
		this.neck.addChild(this.headJoint);
		this.headJoint.addChild(this.head);

		this.head.addChild(this.earLftJoint);
		this.head.addChild(this.earRtJoint);

		this.head.addChild(this.muzzle);

		this.head.addChild(this.cheekTuffs[leftSide][2]);
		this.head.addChild(this.cheekTuffs[leftSide][0]);
		this.head.addChild(this.cheekTuffs[rightSide][2]);
		this.head.addChild(this.cheekTuffs[rightSide][0]);
		this.head.addChild(this.gem);

		this.cheekTuffs[leftSide][0].addChild(this.cheekTuffs[leftSide][1]);
		this.cheekTuffs[rightSide][0].addChild(this.cheekTuffs[rightSide][1]);

		this.earLft.addChild(this.earLftTip);
		this.earLftJoint.addChild(this.earLft);
		this.earRt.addChild(this.earRtTip);
		this.earRtJoint.addChild(this.earRt);

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

		this.tail[2][1].addChild(this.tailCurlBot);
		this.tail[4][1].addChild(this.tailCurlTop);

		for(int i = 0; i < tail.length - 1; i++)
		{
			this.tail[i][0].addChild(this.tail[i][1]);
			this.tail[i][1].addChild(this.tail[i + 1][0]);
		}

		this.tail[tail.length - 1][0].addChild(tail[tail.length - 1][1]);

		this.tail[tail.length - 1][1].addChild(this.tailFork[leftSide][0][0]);
		this.tail[tail.length - 1][1].addChild(this.tailFork[rightSide][0][0]);

		for(int i = 0; i < tailFork.length; i++)
		{
			for(int j = 0; j < tailFork[i].length - 1; j++)
			{
				this.tailFork[i][j][0].addChild(this.tailFork[i][j][1]);
				this.tailFork[i][j][1].addChild(this.tailFork[i][j + 1][0]);
			}
		}

		animationDeployer = new AnimationDeployer(this);
	}

	@Override
	public void setRotationAngles(T entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch)
	{
		animate((OkamiEspeonEntity)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch);
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) 
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void animate(OkamiEspeonEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch)
	{	
		this.animationDeployer.update((IAnimatedEntity)entity);
		resetPartInfos();

		float angularVelocity = entity.getAngularVelocity();
		float verticleVelocity = (float)entity.getHeightVelocity();

		float idleDampener = 1F;

		//System.out.println( "Test change in pitch" );
		//System.out.println( Float.toString( pitchChangeRate ) );

		float sizeModifier = 1F;//(float) Math.sqrt(((EntityOkamiEspeon) entity).defaultHeight / entity.height);

		if(!entity.isSitting())
		{
			if(animationDeployer.getEntity().getAnimationID() == LibraryOkamiPokemonAttackID.GLAIVE_SLASH || 
					animationDeployer.getEntity().getAnimationID() == LibraryOkamiPokemonAttackID.GLAIVE_SLASH_REVERSE)
			{
				idleDampener = animateSlash(animationDeployer.getEntity(), distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier);
			}
		}
		
		animateBody(entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, idleDampener, verticleVelocity);
		animateHead(entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, idleDampener, verticleVelocity);
		animateLegs(entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, idleDampener, verticleVelocity);
		animateTail(entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, idleDampener, angularVelocity, verticleVelocity);

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

		bodyInfo.resetNewAngles();
		bodyInfo.resetNewPnt();

		for(int i = 0; i < cheekTuffs.length; i++)
		{
			for(int j = 0; j < cheekTuffs[i].length; j++)
			{
				cheekTuffsInfo[i][j].resetNewAngles();
			}
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

		for(int i = 0; i < tailFork.length; i++)
		{
			for(int j = 0; j < tailFork[i].length - 1; j++)
			{
				this.tailForkInfo[i][j][0].resetNewAngles();
				this.tailForkInfo[i][j][1].resetNewAngles();
			}
		}
	}

	public float animateSlash(IAnimatedEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize)
	{
		float idleDampener = 1F;

		float tick = animationDeployer.getEntity().getAnimationTick() + KindredLegacy.proxy.getPartialTick();
		
		float firstPhaseEnd = 2;
		float secondPhaseEnd = 6;
		float endDuration = 8;

		if(tick < firstPhaseEnd && tick > 0)
		{
			idleDampener = (1F + (float)MathHelper.cos(PI * (tick/firstPhaseEnd)));
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
		//float halfSlashDurationStart = 0;
		float halfSlashDurationEnd = endDuration / 2;
		//float halfSlashDuration = endDuration / 2;

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
		//float beginningSlashDurationStart = 0;
		//float beginningSlashDurationEnd = firstPhaseEnd;
		//float beginningSlashDuration = firstPhaseEnd;

		/* ******** Mid Slash Timers *********/
		//float midSlashDurationStart = firstPhaseEnd;
		float midSlashDurationEnd = secondPhaseEnd;
		//float midSlashDuration = secondPhaseEnd - firstPhaseEnd;

		/* ******** End Slash Timers *********/
		//float endSlashDurationStart = secondPhaseEnd;
		//float endSlashDurationEnd = endDuration;
		//float endSlashDuration = endDuration - secondPhaseEnd;

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

		//PartAnimate.headAnimateInfoOnlyWithAngleModifiers(glaiveJointBaseInfo, netHeadYaw, headPitch, (1 - idleDampener) * 1.2F, 0);

		return idleDampener;
	}

	public void animateBody(OkamiEspeonEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float idleDampener, float verticalVelocity)
	{
		if(!entity.isSitting())
		{
			float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
			float runCycleInterval = (RUN_FREQUENCY * modelSize * entity.getGravityFactor() * distanceMoved % (2 * PI))/(2 * PI);

			float bodyWalkAngle = (float)Math.toRadians(13);
			float bodyRunAngle = (float)Math.toRadians(20);

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
		}
		else
		{
			bodyInfo.setNewRotateX(bodySitInfo.getNewRotateX());
			bodyInfo.setNewPointY(bodySitInfo.getNewPointY());
		}
	}

	public void animateHead(OkamiEspeonEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float idleDampener, float verticalVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, neckJointInfo);

		PartAnimate.headAnimateInfoOnlyWithAngleModifiers(neckInfo, netHeadYaw, headPitch, 0.2F, 0.2F);

		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockBody();

		float idleAmplitudeX = 0.1F;

		if(entity.isSitting())
		{
			idleAmplitudeX *= 0.5F;
		}
		else
		{
			float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
			float runCycleInterval = (RUN_FREQUENCY * modelSize * entity.getGravityFactor() * distanceMoved % (2 * PI))/(2 * PI);

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

			float newVerticalVelocity = 1.5F * verticalVelocity;

			if(newVerticalVelocity > 1F)
				newVerticalVelocity = 1F;
			else if(newVerticalVelocity < -1F)
				newVerticalVelocity = -1F;

			float fallingEarAngle = (float)Math.toRadians(40);

			earLftInfo.setNewRotateZ(earLftInfo.getNewRotateZ() + ((walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity)) + fallingEarAngle * newVerticalVelocity));
			earRtInfo.setNewRotateZ(earRtInfo.getNewRotateZ() - ((walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity)) + fallingEarAngle * newVerticalVelocity));	

			/* ******************* */
			walkAngle = (float)Math.toRadians(10);
			runAngle = (float)Math.toRadians(20);

			float fallingTuffsAngle = (float)Math.toRadians(-35);

			for(int i = 0; i < cheekTuffs.length; i++)
			{
				for(int j = 0; j < cheekTuffs[i].length; j++)
				{
					walkCycleAngleChange = MathHelper.cos(2 * walkCycleInterval * 2 * PI - ((j + 1) * (PI/2))) * walkAngle * (1 - horzVelocity)
							+ walkAngle * (1 - horzVelocity);
					runCycleAngleChange = MathHelper.cos(2 * runCycleInterval * 2 * PI - ((j + 1) * (PI/2))) * runAngle * horzVelocity
							+ runAngle * horzVelocity;

					cheekTuffsInfo[i][j].setNewRotateZ(cheekTuffsInfo[i][j].getNewRotateZ() 
							+ ((walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity)) 
									+ fallingTuffsAngle * newVerticalVelocity) * (float)Math.pow(-1F, i + 1));
				}
			}
		}

		float angleChangeX = -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeX + idleAmplitudeX;

		neckInfo.setNewRotateX(neckInfo.getNewRotateX() + angleChangeX);

		JointAnimation.reverseJointRotatesChange(neckInfo, headJointInfo);

		PartAnimate.headAnimateInfoOnlyWithAngleModifiers(headInfo, netHeadYaw, headPitch, 0.9F, 0.9F);

		/* *************** */
		float idleAmplitudeZ = 0.10F;

		// + (float)Math.toRadians(45)
		float angleChangeZ = MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI + PI) * idleAmplitudeZ * (1 - horzVelocity);

		earRtInfo.setNewRotateZ(earRtInfo.getNewRotateZ() - angleChangeZ);
		earLftInfo.setNewRotateZ(earLftInfo.getNewRotateZ() + angleChangeZ);

		/* ******************* */
		idleAmplitudeZ = 0.18F;

		for(int i = 0; i < cheekTuffs.length; i++)
		{
			for(int j = 0; j < cheekTuffs[i].length; j++)
			{
				angleChangeZ = MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI
						- ((j + 2) * (PI/2))) * idleAmplitudeZ * (1 - horzVelocity);

				cheekTuffsInfo[i][j].setNewRotateZ(cheekTuffsInfo[i][j].getNewRotateZ() - angleChangeZ * (float)Math.pow(-1F, i + 1));
			}
		}

		if(entity.isTamed())
		{
			float healthChangeAngle = (float)Math.toRadians(-55);

			earLftInfo.setNewRotateZ(earLftInfo.getNewRotateZ() - (1F - entity.getHealthPercent()) * healthChangeAngle);
			earRtInfo.setNewRotateZ(earRtInfo.getNewRotateZ() + (1F - entity.getHealthPercent()) * healthChangeAngle);
		}
	}

	public void animateLegs(OkamiEspeonEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float idleDampener, float verticalVelocity)
	{
		if(!entity.isSitting())
		{
			float frequency = this.RUN_FREQUENCY * modelSize * entity.getGravityFactor();
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
			float sneak = 0.45F;

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

	public void animateTail(OkamiEspeonEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float idleDampener, float angularVelocity, float verticalVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, tailInfo[0][0]);

		float idleAmplitudeX = 0.22F * (1F - horzVelocity);
		float idleAmplitudeY = 0.28F * (1F - horzVelocity);

		float yawChangeAngle = -(float)Math.toRadians(15);
		float verticalVelocityChangeAngle = (float)Math.toRadians(-18);
		float healthChangeAngle = entity.isTamed() ? (float)Math.toRadians(-8) : 0;

		float bodyJumpAngle = (float)Math.toRadians(-25);

		float newVerticalVelocity = 1.5F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;

		float absoluteMoveVelocity = (horzVelocity + Math.abs(newVerticalVelocity)) * 2F;

		if(absoluteMoveVelocity > 1F)
			absoluteMoveVelocity = 1F;

		float moveAmplitudeX = 0.2F;

		tailInfo[0][1].setNewRotateX(tailInfo[0][1].getNewRotateX() + bodyJumpAngle * newVerticalVelocity);

		for(int i = 0; i < tail.length; i++)
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
			angleChangeX += PartAnimate.negCosRotateAnimationAdjusted(modelSize * distanceMoved - PI/4F * (float)i, horzVelocity, this.RUN_FREQUENCY * entity.getGravityFactor(), moveAmplitudeX) * (1F - Math.abs(newVerticalVelocity));
			angleChangeY += angularVelocity * yawChangeAngle;

			// Tamable Animation
			angleChangeX += (entity.isTamed() ? (1F - entity.getHealthPercent()) * healthChangeAngle : 0);

			// Part Number Dampener
			angleChangeX *= (1F - (Math.pow(Math.E, -0.95F * (float)(i + 1))));
			angleChangeY *= (1F - (Math.pow(Math.E, -0.95F * (float)(i + 1))));

			// Fall/Jump Animations
			angleChangeX += + verticalVelocityChangeAngle * newVerticalVelocity * (1F - ((float)i / (float)(tail.length + tailFork.length)));
			/*
			angleChangeX += (MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2) * idleAmplitudeX * ( 1 - (Math.pow(Math.E, -0.4F * (float)(i + 1))))
					+ PartAnimate.negCosRotateAnimationAdjusted(distanceMoved - PI/5F * (float)i, horzVelocity, RUN_FREQUENCY, moveAmplitude)) * (1F - (Math.abs(verticalVelocity) * 0.5F)) 
					+ verticalVelocity * verticalVelocityChangeAngle
					+ (1F - entity.getHealthPercent()) * healthChangeAngle;
			angleChangeY += ((MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2) * idleAmplitudeY)
			 * ( 1 - (Math.pow(Math.E, -0.4F * (float)(i + 1)))) * (1F - Math.abs(angularVelocity))) * (1F - (Math.abs(verticalVelocity) * 0.5F))
					+ angularVelocity * yawChangeAngle;
			 */
			tailInfo[i][1].setNewRotateX(tailInfo[i][1].getNewRotateX() + angleChangeX);
			tailInfo[i][1].setNewRotateY(tailInfo[i][1].getNewRotateY() + angleChangeY);
		}

		for(int i = 0; i < tailFork.length; i++)
		{
			IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockTail(tail.length - 1 + i);

			// + (float)Math.toRadians(45)
			float angleChangeX = 0;
			float angleChangeY = 0;

			// Idle Animations
			angleChangeX += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2) * idleAmplitudeX 
					* (1F - absoluteMoveVelocity) * (1F - Math.abs(angularVelocity) * 0.5F);
			angleChangeY += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2) * idleAmplitudeY
					* (1F - absoluteMoveVelocity) * (1F - Math.abs(angularVelocity) * 0.5F);

			// Movement Animations
			angleChangeX += PartAnimate.negCosRotateAnimationAdjusted(modelSize * distanceMoved - PI/4F * (float)(i + tail.length), horzVelocity, this.RUN_FREQUENCY * entity.getGravityFactor(), moveAmplitudeX) * (1F - Math.abs(newVerticalVelocity));
			angleChangeY += angularVelocity * yawChangeAngle;

			// Fall/Jump Animations
			angleChangeX += verticalVelocityChangeAngle * newVerticalVelocity * (1F - ((float)(i + tail.length) / (float)(tail.length + tailFork.length)));
			
			/*
			angleChangeX += (MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2) * idleAmplitudeX * ( 1 - (Math.pow(Math.E, -0.4F * (float)(i + 1))))
					+ PartAnimate.negCosRotateAnimationAdjusted(distanceMoved - PI/5F * (float)i, horzVelocity, RUN_FREQUENCY, moveAmplitude))
			 * (1F - (Math.abs(verticalVelocity) * 0.5F)) + verticalVelocity * verticalVelocityChangeAngle;
			angleChangeY += ((MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2) * idleAmplitudeY)
			 * ( 1 - (Math.pow(Math.E, -0.4F * (float)(i + 1)))) * (1F - Math.abs(angularVelocity)) * (1F - (Math.abs(verticalVelocity) * 0.5F))) 
					+ angularVelocity * yawChangeAngle;
			 */
			tailForkInfo[0][i][1].setNewRotateX(tailForkInfo[0][i][1].getNewRotateX() + angleChangeX);
			tailForkInfo[0][i][1].setNewRotateY(tailForkInfo[0][i][1].getNewRotateY() + angleChangeY);

			angleChangeX = 0;
			angleChangeY = 0;

			// Idle Animations
			angleChangeX += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2F - PI/4F) * idleAmplitudeX 
					* (1F - absoluteMoveVelocity) * (1F - Math.abs(angularVelocity) * 0.8F);
			angleChangeY += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2F - PI/4F) * idleAmplitudeY
					* (1F - absoluteMoveVelocity) * (1F - Math.abs(angularVelocity) * 0.8F);

			// Movement Animations
			angleChangeX += PartAnimate.negCosRotateAnimationAdjusted(modelSize * distanceMoved - PI/4F * (float)(i + tail.length), horzVelocity, this.RUN_FREQUENCY * entity.getGravityFactor(), moveAmplitudeX) * (1F - Math.abs(newVerticalVelocity));
			angleChangeY += angularVelocity * yawChangeAngle;

			// Fall/Jump Animations
			angleChangeX += verticalVelocityChangeAngle * newVerticalVelocity * (1F - ((float)(i + tail.length) / (float)(tail.length + tailFork.length)));
			/*
			angleChangeX += (MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2 - PI/4F) * idleAmplitudeX
					+ PartAnimate.negCosRotateAnimationAdjusted(distanceMoved - PI/5F * (float)i, horzVelocity, RUN_FREQUENCY, moveAmplitude))
			 * (1F - Math.abs(verticalVelocity)) + verticalVelocity * verticalVelocityChangeAngle;
			angleChangeY += (MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2 - PI/4) * idleAmplitudeY) * (1F - Math.abs(angularVelocity)) * (1F - (Math.abs(verticalVelocity) * 0.5F))
					+ angularVelocity * yawChangeAngle;
			 */
			tailForkInfo[1][i][1].setNewRotateX(tailForkInfo[1][i][1].getNewRotateX() + angleChangeX);
			tailForkInfo[1][i][1].setNewRotateY(tailForkInfo[1][i][1].getNewRotateY() + angleChangeY);
		}

		if(entity.isSitting())
		{
			tailInfo[0][1].setNewRotateX(tailInfo[0][1].getNewRotateX() + (float)Math.toRadians(35));
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

		for(int i = 0; i < cheekTuffs.length; i++)
		{
			for(int j = 0; j < cheekTuffs[i].length; j++)
			{
				this.animationDeployer.rotate(cheekTuffs[i][j], cheekTuffsInfo[i][j].getNewRotates());
			}
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

		for(int i = 0; i < tailFork.length; i++)
		{
			for(int j = 0; j < tailFork[i].length - 1; j++)
			{
				this.animationDeployer.rotate(tailFork[i][j][0], tailForkInfo[i][j][0].getNewRotates());
				this.animationDeployer.rotate(tailFork[i][j][1], tailForkInfo[i][j][1].getNewRotates());
			}
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