package fuzzyacornindustries.kindredlegacy.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;

import fuzzyacornindustries.kindredlegacy.client.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.client.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.client.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.animation.JointAnimation;
import fuzzyacornindustries.kindredlegacy.client.animation.PartAnimate;
import fuzzyacornindustries.kindredlegacy.client.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.IMobMotionTracker;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.FirecrackerLittenEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.TamablePokemon;
import net.minecraft.client.renderer.entity.model.TintedAgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class FirecrackerLittenModel<T extends TamablePokemon> extends TintedAgeableModel<T>
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public final float RUN_FREQUENCY = 1.8F;
	public final float WALK_FREQUENCY = 1.2F;
	public final float SNEAK_VELOCITY = 0.45F;

	public ModelRenderer body;
	PartInfo bodyInfo;
	PartInfo bodySitInfo;

	public ModelRenderer neckJoint;
	public ModelRenderer neck;
	PartInfo neckJointInfo;
	PartInfo neckInfo;

	public ModelRenderer legFntLft;
	public ModelRenderer legFntRt;
	public ModelRenderer legBckLft;
	public ModelRenderer legBckLftHind;
	public ModelRenderer legBckRt;
	public ModelRenderer legBckRtHind;
	PartInfo legFntRtInfo;
	PartInfo legFntLftInfo;
	PartInfo legBckLftInfo;
	PartInfo legBckLftHindInfo;
	PartInfo legBckRtInfo;
	PartInfo legBckRtHindInfo;

	PartInfo legFntRtSitInfo;
	PartInfo legFntLftSitInfo;
	PartInfo legBckLftSitInfo;
	PartInfo legBckLftHindSitInfo;
	PartInfo legBckRtSitInfo;
	PartInfo legBckRtHindSitInfo;

	public ModelRenderer cannonLftHolderFnt;
	public ModelRenderer cannonLftHolderBck;
	public ModelRenderer cannonLft;
	public ModelRenderer cannonLftMouth;
	public ModelRenderer cannonLftMouthTop;
	public ModelRenderer cannonLftJaw;
	public ModelRenderer cannonLftFangLft;
	public ModelRenderer cannonLftFangRt;
	public ModelRenderer cannonLftEyeRt;
	public ModelRenderer cannonLftEyeLft;

	public ModelRenderer cannonRtHolderFnt;
	public ModelRenderer cannonRtHolderBck;
	public ModelRenderer cannonRt;
	public ModelRenderer cannonRtMouth;
	public ModelRenderer cannonRtMouthTop;
	public ModelRenderer cannonRtJaw;
	public ModelRenderer cannonRtFangLft;
	public ModelRenderer cannonRtFangRt;
	public ModelRenderer cannonRtEyeRt;
	public ModelRenderer cannonRtEyeLft;

	public ModelRenderer headJoint;
	public ModelRenderer head;
	public ModelRenderer nose;
	PartInfo headJointInfo;
	PartInfo headInfo;

	public ModelRenderer whiskers[][] = new ModelRenderer[2][2];
	PartInfo whiskersInfo[][] = new PartInfo[whiskers.length][2];

	public ModelRenderer headHairLft;
	public ModelRenderer headHairRt;

	public ModelRenderer earLftJoint;
	public ModelRenderer earLft;
	public ModelRenderer earRtJoint;
	public ModelRenderer earRt;
	PartInfo earLftJointInfo;
	PartInfo earLftInfo;
	PartInfo earRtJointInfo;
	PartInfo earRtInfo;

	public ModelRenderer tail[][] = new ModelRenderer[6][2];
	PartInfo tailInfo[][] = new PartInfo[tail.length][2];

	public ModelRenderer tailTip[] = new ModelRenderer[4];
	PartInfo tailTipInfo[] = new PartInfo[tailTip.length];

	public FirecrackerLittenModel() 
	{
		this.textureWidth = 64;
		this.textureHeight = 64;

		int jointNumber = 0;
		int partNumber = 1;

		this.body = new ModelRenderer(this, 31, 56);
		this.body.setRotationPoint(0.0F, 19.7F, 0.0F);
		this.body.addBox(-1.5F, -1.5F, -2.5F, 3, 3, 5, 0.0F);
		this.bodyInfo = new PartInfo(body);

		this.body = new ModelRenderer(this, 31, 56);
		this.body.setRotationPoint(0.0F, 22.5F, 0.0F);
		this.body.addBox(-1.5F, -1.5F, -2.5F, 3, 3, 5, 0.0F);
		this.bodySitInfo = new PartInfo(body);

		this.neckJoint = new ModelRenderer(this, 0, 0);
		this.neckJoint.setRotationPoint(0.0F, -0.8F, -2.0F);
		this.neckJoint.addBox(-0.5F, -0.5F, -3.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(neckJoint, -0.5235987755982988F, 0.0F, 0.0F);
		this.neckJointInfo = new PartInfo(neckJoint);
		this.neck = new ModelRenderer(this, 5, 9);
		this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.neck.addBox(-0.5F, -0.5F, -1.8F, 1, 1, 2, 0.0F);
		this.neckInfo = new PartInfo(neck);

		this.legFntLft = new ModelRenderer(this, 45, 49);
		this.legFntLft.setRotationPoint(1.5F, 1.01F, -1.4F);
		this.legFntLft.addBox(-0.5F, -0.7F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(legFntLft, -1.5707963267948966F, 0.3490658503988659F, 0.0F);
		this.legFntLftSitInfo = new PartInfo(legFntLft);

		this.legFntLft = new ModelRenderer(this, 45, 49);
		this.legFntLft.setRotationPoint(0.7F, 1.0F, -1.4F);
		this.legFntLft.addBox(-0.5F, -0.7F, -0.5F, 1, 4, 1, 0.0F);
		this.legFntLftInfo = new PartInfo(legFntLft);

		this.legFntRt = new ModelRenderer(this, 29, 49);
		this.legFntRt.setRotationPoint(-1.5F, 1.01F, -1.4F);
		this.legFntRt.addBox(-0.5F, -0.7F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(legFntRt, -1.5707963267948966F, -0.3490658503988659F, 0.0F);
		this.legFntRtSitInfo = new PartInfo(legFntRt);

		this.legFntRt = new ModelRenderer(this, 29, 49);
		this.legFntRt.setRotationPoint(-0.7F, 1.0F, -1.4F);
		this.legFntRt.addBox(-0.5F, -0.7F, -0.5F, 1, 4, 1, 0.0F);
		this.legFntRtInfo = new PartInfo(legFntRt);

		this.legBckLft = new ModelRenderer(this, 40, 49);
		this.legBckLft.setRotationPoint(1.5F, 1.0F, 1.4F);
		this.legBckLft.addBox(-0.5F, -0.3F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(legBckLft, -2.1816615649929116F, -0.3490658503988659F, 0.0F);
		this.legBckLftSitInfo = new PartInfo(legBckLft);

		this.legBckLft = new ModelRenderer(this, 40, 49);
		this.legBckLft.setRotationPoint(0.7F, 1.0F, 1.4F);
		this.legBckLft.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(legBckLft, -0.17453292519943295F, 0.0F, 0.0F);
		this.legBckLftInfo = new PartInfo(legBckLft);

		this.legBckLftHind = new ModelRenderer(this, 40, 52);
		this.legBckLftHind.setRotationPoint(0.0F, 1.0F, 0.5F);
		this.legBckLftHind.addBox(-0.51F, -0.2F, -0.3F, 1, 2, 1, 0.0F);
		this.setRotateAngle(legBckLftHind, 0.6108652381980153F, 0.0F, 0.0F);
		this.legBckLftHindSitInfo = new PartInfo(legBckLftHind);

		this.legBckLftHind = new ModelRenderer(this, 40, 52);
		this.legBckLftHind.setRotationPoint(0.0F, 1.5F, 0.4F);
		this.legBckLftHind.addBox(-0.51F, -0.2F, -0.3F, 1, 2, 1, 0.0F);
		this.legBckLftHindInfo = new PartInfo(legBckLftHind);

		this.legBckRt = new ModelRenderer(this, 34, 49);
		this.legBckRt.setRotationPoint(-1.5F, 1.0F, 1.4F);
		this.legBckRt.addBox(-0.5F, -0.3F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(legBckRt, -2.1816615649929116F, 0.3490658503988659F, 0.0F);
		this.legBckRtSitInfo = new PartInfo(legBckRt);

		this.legBckRt = new ModelRenderer(this, 34, 49);
		this.legBckRt.setRotationPoint(-0.7F, 1.0F, 1.4F);
		this.legBckRt.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(legBckRt, -0.17453292519943295F, 0.0F, 0.0F);
		this.legBckRtInfo = new PartInfo(legBckRt);

		this.legBckRtHind = new ModelRenderer(this, 34, 52);
		this.legBckRtHind.setRotationPoint(0.0F, 1.0F, 0.5F);
		this.legBckRtHind.addBox(-0.49F, -0.2F, -0.3F, 1, 2, 1, 0.0F);
		this.setRotateAngle(legBckRtHind, 0.6108652381980153F, 0.0F, 0.0F);
		this.legBckRtHindSitInfo = new PartInfo(legBckRtHind);

		this.legBckRtHind = new ModelRenderer(this, 34, 52);
		this.legBckRtHind.setRotationPoint(0.0F, 1.5F, 0.4F);
		this.legBckRtHind.addBox(-0.49F, -0.2F, -0.3F, 1, 2, 1, 0.0F);
		this.legBckRtHindInfo = new PartInfo(legBckRtHind);

		this.cannonLftHolderFnt = new ModelRenderer(this, 44, 55);
		this.cannonLftHolderFnt.setRotationPoint(1.5F, -0.1F, -0.5F);
		this.cannonLftHolderFnt.addBox(0.0F, -0.5F, -0.5F, 4, 1, 1, 0.0F);
		this.cannonLftHolderBck = new ModelRenderer(this, 44, 58);
		this.cannonLftHolderBck.setRotationPoint(1.5F, 0.5F, 1.9F);
		this.cannonLftHolderBck.addBox(0.0F, -0.5F, -0.5F, 4, 1, 1, 0.0F);
		this.cannonLft = new ModelRenderer(this, 48, 56);
		this.cannonLft.setRotationPoint(3.5F, -0.1F, 0.0F);
		this.cannonLft.addBox(-1.0F, -2.1F, -2.8F, 2, 2, 6, 0.0F);
		this.setRotateAngle(cannonLft, -0.2617993877991494F, 0.0F, 0.0F);
		this.cannonLftMouth = new ModelRenderer(this, 52, 51);
		this.cannonLftMouth.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cannonLftMouth.addBox(-1.5F, -2.6F, -3.8F, 3, 3, 1, 0.0F);
		this.cannonLftMouthTop = new ModelRenderer(this, 50, 41);
		this.cannonLftMouthTop.setRotationPoint(0.0F, -2.3F, -3.8F);
		this.cannonLftMouthTop.addBox(-1.51F, -0.8F, -2.5F, 3, 1, 3, 0.0F);
		this.setRotateAngle(cannonLftMouthTop, 0.17453292519943295F, 0.0F, 0.0F);
		this.cannonLftJaw = new ModelRenderer(this, 50, 46);
		this.cannonLftJaw.setRotationPoint(0.0F, 0.3F, -3.8F);
		this.cannonLftJaw.addBox(-1.51F, -0.3F, -2.1F, 3, 1, 3, 0.0F);
		this.setRotateAngle(cannonLftJaw, -0.08726646259971647F, 0.0F, 0.0F);
		this.cannonLftFangLft = new ModelRenderer(this, 60, 46);
		this.cannonLftFangLft.setRotationPoint(1.0F, 0.0F, -2.0F);
		this.cannonLftFangLft.addBox(-0.6F, 0.0F, -0.4F, 1, 1, 1, 0.0F);
		this.cannonLftFangRt = new ModelRenderer(this, 48, 46);
		this.cannonLftFangRt.setRotationPoint(-1.0F, 0.0F, -2.0F);
		this.cannonLftFangRt.addBox(-0.4F, 0.0F, -0.4F, 1, 1, 1, 0.0F);
		this.cannonLftEyeRt = new ModelRenderer(this, 48, 41);
		this.cannonLftEyeRt.setRotationPoint(0.0F, -0.7F, 0.0F);
		this.cannonLftEyeRt.addBox(-1.5F, -0.6F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(cannonLftEyeRt, -0.2617993877991494F, 0.0F, 0.0F);
		this.cannonLftEyeLft = new ModelRenderer(this, 60, 41);
		this.cannonLftEyeLft.setRotationPoint(0.0F, -0.7F, 0.0F);
		this.cannonLftEyeLft.addBox(0.5F, -0.6F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(cannonLftEyeLft, -0.2617993877991494F, 0.0F, 0.0F);

		this.cannonRtHolderFnt = new ModelRenderer(this, 24, 55);
		this.cannonRtHolderFnt.setRotationPoint(-1.5F, -0.1F, -0.5F);
		this.cannonRtHolderFnt.addBox(-4.0F, -0.5F, -0.5F, 4, 1, 1, 0.0F);
		this.cannonRtHolderBck = new ModelRenderer(this, 24, 58);
		this.cannonRtHolderBck.setRotationPoint(-1.5F, 0.5F, 1.9F);
		this.cannonRtHolderBck.addBox(-4.0F, -0.5F, -0.5F, 4, 1, 1, 0.0F);
		this.cannonRt = new ModelRenderer(this, 14, 56);
		this.cannonRt.setRotationPoint(-3.5F, -0.1F, 0.0F);
		this.cannonRt.addBox(-1.0F, -2.1F, -2.8F, 2, 2, 6, 0.0F);
		this.setRotateAngle(cannonRt, -0.2617993877991494F, 0.0F, 0.0F);
		this.cannonRtMouth = new ModelRenderer(this, 18, 51);
		this.cannonRtMouth.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cannonRtMouth.addBox(-1.5F, -2.6F, -3.8F, 3, 3, 1, 0.0F);
		this.cannonRtMouthTop = new ModelRenderer(this, 16, 41);
		this.cannonRtMouthTop.setRotationPoint(0.0F, -2.3F, -3.8F);
		this.cannonRtMouthTop.addBox(-1.51F, -0.8F, -2.5F, 3, 1, 3, 0.0F);
		this.setRotateAngle(cannonRtMouthTop, 0.17453292519943295F, 0.0F, 0.0F);
		this.cannonRtJaw = new ModelRenderer(this, 16, 46);
		this.cannonRtJaw.setRotationPoint(0.0F, 0.3F, -3.8F);
		this.cannonRtJaw.addBox(-1.51F, -0.3F, -2.1F, 3, 1, 3, 0.0F);
		this.setRotateAngle(cannonRtJaw, -0.08726646259971647F, 0.0F, 0.0F);
		this.cannonRtFangLft = new ModelRenderer(this, 26, 46);
		this.cannonRtFangLft.setRotationPoint(1.0F, 0.0F, -2.0F);
		this.cannonRtFangLft.addBox(-0.6F, 0.0F, -0.4F, 1, 1, 1, 0.0F);
		this.cannonRtFangRt = new ModelRenderer(this, 14, 46);
		this.cannonRtFangRt.setRotationPoint(-1.0F, 0.0F, -2.0F);
		this.cannonRtFangRt.addBox(-0.4F, 0.0F, -0.4F, 1, 1, 1, 0.0F);
		this.cannonRtEyeRt = new ModelRenderer(this, 14, 41);
		this.cannonRtEyeRt.setRotationPoint(0.0F, -0.7F, 0.0F);
		this.cannonRtEyeRt.addBox(-1.5F, -0.6F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(cannonRtEyeRt, -0.2617993877991494F, 0.0F, 0.0F);
		this.cannonRtEyeLft = new ModelRenderer(this, 26, 41);
		this.cannonRtEyeLft.setRotationPoint(0.0F, -0.7F, 0.0F);
		this.cannonRtEyeLft.addBox(0.5F, -0.6F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(cannonRtEyeLft, -0.2617993877991494F, 0.0F, 0.0F);

		this.headJoint = new ModelRenderer(this, 0, 0);
		this.headJoint.setRotationPoint(0.0F, 0.0F, -1.7F);
		this.headJoint.addBox(-2.0F, -3.4F, -3.4F, 0, 0, 0, 0.0F);
		this.setRotateAngle(headJoint, 0.5235987755982988F, 0.0F, 0.0F);
		this.headJointInfo = new PartInfo(headJoint);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-2.0F, -3.4F, -3.4F, 4, 4, 4, 0.0F);
		this.headInfo = new PartInfo(head);
		this.nose = new ModelRenderer(this, 12, 9);
		this.nose.setRotationPoint(0.0F, -0.5F, -3.0F);
		this.nose.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 1, 0.0F);
		this.setRotateAngle(nose, 0.17453292519943295F, 0.0F, 0.0F);

		int whiskerNumber = 0;
		this.whiskers[0][whiskerNumber] = new ModelRenderer(this, 24, 7); // whiskerLftTop
		this.whiskers[0][whiskerNumber].setRotationPoint(1.8F, -0.6F, -2.8F);
		this.whiskers[0][whiskerNumber].addBox(-0.2F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		this.whiskersInfo[0][whiskerNumber] = new PartInfo(this.whiskers[0][whiskerNumber]);
		this.setRotateAngle(whiskers[0][whiskerNumber], 0.0F, 0.0F, 0.17453292519943295F);
		whiskerNumber++;
		this.whiskers[0][whiskerNumber] = new ModelRenderer(this, 24, 10); // whiskerLftBot
		this.whiskers[0][whiskerNumber].setRotationPoint(1.8F, -0.1F, -2.7F);
		this.whiskers[0][whiskerNumber].addBox(-0.2F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		this.setRotateAngle(whiskers[0][whiskerNumber], 0.0F, 0.0F, 0.5235987755982988F);
		this.whiskersInfo[0][whiskerNumber] = new PartInfo(this.whiskers[0][whiskerNumber]);
		whiskerNumber = 0;
		this.whiskers[1][whiskerNumber] = new ModelRenderer(this, 17, 7); // whiskerRtTop
		this.whiskers[1][whiskerNumber].setRotationPoint(-1.8F, -0.6F, -2.8F);
		this.whiskers[1][whiskerNumber].addBox(-1.8F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		this.setRotateAngle(whiskers[1][whiskerNumber], 0.0F, 0.0F, -0.17453292519943295F);
		this.whiskersInfo[1][whiskerNumber] = new PartInfo(this.whiskers[1][whiskerNumber]);
		whiskerNumber++;
		this.whiskers[1][whiskerNumber] = new ModelRenderer(this, 17, 10); // whiskerRtBot
		this.whiskers[1][whiskerNumber].setRotationPoint(-1.8F, -0.1F, -2.7F);
		this.whiskers[1][whiskerNumber].addBox(-1.8F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		this.setRotateAngle(whiskers[1][whiskerNumber], 0.0F, 0.0F, -0.5235987755982988F);
		this.whiskersInfo[1][whiskerNumber] = new PartInfo(this.whiskers[1][whiskerNumber]);

		this.headHairLft = new ModelRenderer(this, 12, 0);
		this.headHairLft.setRotationPoint(0.3F, -3.1F, -2.8F);
		this.headHairLft.addBox(-0.5F, -1.2F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headHairLft, 0.0F, 0.0F, 0.8726646259971648F);
		this.headHairRt = new ModelRenderer(this, 0, 0);
		this.headHairRt.setRotationPoint(-0.3F, -3.1F, -2.8F);
		this.headHairRt.addBox(-0.5F, -1.2F, -0.51F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headHairRt, 0.0F, 0.0F, -0.8726646259971648F);

		this.earLftJoint = new ModelRenderer(this, 0, 0);
		this.earLftJoint.setRotationPoint(1.5F, -3.0F, -2.0F);
		this.earLftJoint.addBox(-1.0F, -1.0F, -0.5F, 0, 0, 0, 0.0F);
		this.earLftJointInfo = new PartInfo(earLftJoint);
		this.earLft = new ModelRenderer(this, 24, 3);
		this.earLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earLft.addBox(-0.8F, -1.2F, -0.5F, 2, 2, 1, 0.0F);
		this.earLft.setTextureOffset(25, 0).addBox(-0.3F, -2.2F, -0.5F, 1, 1, 1, 0.0F); // earLftTip
		this.earLftInfo = new PartInfo(earLft);
		this.earRtJoint = new ModelRenderer(this, 0, 0);
		this.earRtJoint.setRotationPoint(-1.5F, -3.0F, -2.0F);
		this.earRtJoint.addBox(-1.0F, -1.0F, -0.5F, 0, 0, 0, 0.0F);
		this.earRtJointInfo = new PartInfo(earRtJoint);
		this.earRt = new ModelRenderer(this, 17, 3);
		this.earRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earRt.addBox(-1.2F, -1.2F, -0.5F, 2, 2, 1, 0.0F);
		this.earRt.setTextureOffset(18, 0).addBox(-0.7F, -2.2F, -0.5F, 1, 1, 1, 0.0F); // earRtTip
		this.earRtInfo = new PartInfo(earRt);

		int tailNumber = 0;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail1Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, -0.8F, 2.1F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.3F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 36, 27); // tail1
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.3F, 1, 1, 2, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail2Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.7F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.3F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 36, 23); // tail2
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.3F, 1, 1, 2, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail3Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.7F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.3F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 36, 19); // tail3
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.3F, 1, 1, 2, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail4Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.7F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.3F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 36, 15); // tail4
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.3F, 1, 1, 2, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail5Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.7F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.3F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 36, 11); // tail5
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.3F, 1, 1, 2, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail6Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.7F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.3F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 36, 7); // tail6
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.3F, 1, 1, 2, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);

		int tailTipNumber = 0;
		this.tailTip[tailTipNumber] = new ModelRenderer(this, 32, 4); // tailTipBotRt
		this.tailTip[tailTipNumber].setRotationPoint(0.0F, 0.0F, 1.7F);
		this.tailTip[tailTipNumber].addBox(-0.5F, -0.5F, -0.4F, 1, 1, 2, 0.0F);
		this.setRotateAngle(tailTip[tailTipNumber], -0.6981317007977318F, 0.0F, 0.7853981633974483F);
		this.tailTipInfo[tailTipNumber] = new PartInfo(this.tailTip[tailTipNumber]);
		tailTipNumber++;
		this.tailTip[tailTipNumber] = new ModelRenderer(this, 40, 4); // tailTipBotLft
		this.tailTip[tailTipNumber].setRotationPoint(0.0F, 0.0F, 1.7F);
		this.tailTip[tailTipNumber].addBox(-0.5F, -0.5F, -0.4F, 1, 1, 2, 0.0F);
		this.setRotateAngle(tailTip[tailTipNumber], -0.6981317007977318F, 0.0F, -0.7853981633974483F);
		this.tailTipInfo[tailTipNumber] = new PartInfo(this.tailTip[tailTipNumber]);
		tailTipNumber++;
		this.tailTip[tailTipNumber] = new ModelRenderer(this, 40, 0); // tailTipTopLft
		this.tailTip[tailTipNumber].setRotationPoint(0.0F, 0.0F, 1.7F);
		this.tailTip[tailTipNumber].addBox(-0.5F, -0.5F, -0.4F, 1, 1, 2, 0.0F);
		this.setRotateAngle(tailTip[tailTipNumber], 0.6981317007977318F, 0.0F, 0.7853981633974483F);
		this.tailTipInfo[tailTipNumber] = new PartInfo(this.tailTip[tailTipNumber]);
		tailTipNumber++;
		this.tailTip[tailTipNumber] = new ModelRenderer(this, 32, 0); // tailTipTopRt
		this.tailTip[tailTipNumber].setRotationPoint(0.0F, 0.0F, 1.7F);
		this.tailTip[tailTipNumber].addBox(-0.5F, -0.5F, -0.4F, 1, 1, 2, 0.0F);
		this.setRotateAngle(tailTip[tailTipNumber], 0.6981317007977318F, 0.0F, -0.7853981633974483F);
		this.tailTipInfo[tailTipNumber] = new PartInfo(this.tailTip[tailTipNumber]);

		this.body.addChild(this.cannonLftHolderBck);
		this.body.addChild(this.cannonLftHolderFnt);
		this.body.addChild(this.cannonRtHolderBck);
		this.body.addChild(this.cannonRtHolderFnt);
		this.body.addChild(this.legBckLft);
		this.body.addChild(this.legBckRt);
		this.body.addChild(this.legFntLft);
		this.body.addChild(this.legFntRt);
		this.body.addChild(this.neckJoint);
		this.body.addChild(this.tail[0][0]);

		this.legBckLft.addChild(this.legBckLftHind);
		this.legBckRt.addChild(this.legBckRtHind);

		this.cannonLft.addChild(this.cannonLftJaw);
		this.cannonLft.addChild(this.cannonLftMouth);
		this.cannonLft.addChild(this.cannonLftMouthTop);
		this.cannonLftHolderBck.addChild(this.cannonLft);
		this.cannonLftMouthTop.addChild(this.cannonLftEyeLft);
		this.cannonLftMouthTop.addChild(this.cannonLftEyeRt);
		this.cannonLftMouthTop.addChild(this.cannonLftFangLft);
		this.cannonLftMouthTop.addChild(this.cannonLftFangRt);

		this.cannonRt.addChild(this.cannonRtJaw);
		this.cannonRt.addChild(this.cannonRtMouth);
		this.cannonRt.addChild(this.cannonRtMouthTop);
		this.cannonRtHolderBck.addChild(this.cannonRt);
		this.cannonRtMouthTop.addChild(this.cannonRtEyeLft);
		this.cannonRtMouthTop.addChild(this.cannonRtEyeRt);
		this.cannonRtMouthTop.addChild(this.cannonRtFangLft);
		this.cannonRtMouthTop.addChild(this.cannonRtFangRt);

		this.head.addChild(this.earLftJoint);
		this.head.addChild(this.earRtJoint);
		this.head.addChild(this.headHairLft);
		this.head.addChild(this.headHairRt);
		this.head.addChild(this.nose);

		this.earLftJoint.addChild(this.earLft);
		this.earRtJoint.addChild(this.earRt);

		this.headJoint.addChild(this.head);
		this.neck.addChild(this.headJoint);
		this.neckJoint.addChild(this.neck);

		for(int i = 0; i < whiskers.length; i++)
		{
			for(int j = 0; j < whiskers[i].length; j++)
			{
				this.head.addChild(this.whiskers[i][j]);
			}
		}

		this.tail[0][0].addChild(this.tail[0][1]);

		for(int i = 1; i < tail.length; i++)
		{
			this.tail[i - 1][1].addChild(this.tail[i][0]);
			this.tail[i][0].addChild(this.tail[i][1]);
		}

		for(int i = 0; i < tailTip.length; i++)
		{
			this.tail[tail.length - 1][1].addChild(this.tailTip[i]);
		}

		animationDeployer = new AnimationDeployer(this);
	}

	@Override
	public void setRotationAngles(T entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch)
	{
		animate((FirecrackerLittenEntity)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch);
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) 
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void animate(Entity entity, float distanceMoved, float horzVelocity, float ageInTicks, float yawHeadOffsetDifference, float pitchRotation)
	{	
		this.animationDeployer.update((IAnimatedEntity)entity);
		resetPartInfos();

		float angularVelocity = ((IMobMotionTracker)entity).getAngularVelocity();
		float verticleVelocity = (float)((IMobMotionTracker)entity).getHeightVelocity();

		float idleDampener = 1F;
		/*
		if(!((EntityFoxfireZorua)entity).isSitting())
		{
			if(animationDeployer.getEntity().getAnimationID() == LibraryFoxfireZoruaAttackID.SUMMON_FOXFIRE)
			{
				idleDampener = animateSummonFoxfire(animationDeployer.getEntity(), distanceMoved, horzVelocity, ageInTicks, yawHeadOffsetDifference, pitchRotation, modelSize);
			}
		}
		 */

		float sizeModifier = 1F;//(float) Math.sqrt(((EntityOkamiEspeon) entity).defaultHeight / entity.height);
		
		animateBody((FirecrackerLittenEntity)entity, distanceMoved, horzVelocity, ageInTicks, yawHeadOffsetDifference, pitchRotation, sizeModifier, idleDampener, verticleVelocity);
		animateHead((FirecrackerLittenEntity)entity, distanceMoved, horzVelocity, ageInTicks, yawHeadOffsetDifference, pitchRotation, sizeModifier, idleDampener, verticleVelocity);
		animateLegs((FirecrackerLittenEntity)entity, distanceMoved, horzVelocity, ageInTicks, yawHeadOffsetDifference, pitchRotation, sizeModifier, idleDampener, verticleVelocity);
		animateTail((FirecrackerLittenEntity)entity, distanceMoved, horzVelocity, ageInTicks, yawHeadOffsetDifference, pitchRotation, sizeModifier, idleDampener, angularVelocity, verticleVelocity);

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

		legFntRtInfo.resetNewAngles();
		legFntLftInfo.resetNewAngles();
		legBckRtInfo.resetNewAngles();
		legBckRtHindInfo.resetNewAngles();
		legBckLftInfo.resetNewAngles();
		legBckLftHindInfo.resetNewAngles();

		legFntRtInfo.resetNewPnt();
		legFntLftInfo.resetNewPnt();
		legBckRtInfo.resetNewPnt();
		legBckRtHindInfo.resetNewPnt();
		legBckLftInfo.resetNewPnt();
		legBckLftHindInfo.resetNewPnt();

		for(int i = 0; i < whiskers.length; i++)
		{
			for(int j = 0; j < whiskers[i].length; j++)
			{
				whiskersInfo[i][j].resetNewAngles();
			}
		}

		for(int i = 0; i < tail.length; i++)
		{
			tailInfo[i][0].resetNewAngles();
			tailInfo[i][1].resetNewAngles();
		}

		for(int i = 0; i < tailTip.length; i++)
		{
			tailTipInfo[i].resetNewAngles();
		}
	}

	public void animateBody(FirecrackerLittenEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener, float verticalVelocity)
	{
		if(!entity.isSitting())
		{
			float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
			float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);

			float bodyWalkAngle = (float)Math.toRadians(28);
			float bodyRunAngle = (float)Math.toRadians(44);

			float maximumChangeY = -2.0F;
			float runCyclePointChange = (MathHelper.cos(2F * runCycleInterval * PI + PI) * maximumChangeY + maximumChangeY) * horzVelocity;

			float newVerticalVelocity = 2.0F * verticalVelocity;

			if(newVerticalVelocity > 1F)
				newVerticalVelocity = 1F;
			else if(newVerticalVelocity < -1F)
				newVerticalVelocity = -1F;

			bodyInfo.setNewPointY(bodyInfo.getNewPointY() + runCyclePointChange * horzVelocity * (1F - Math.abs(newVerticalVelocity)));

			float walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI + PI/2) * bodyWalkAngle * (1 - horzVelocity);
			float runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI + PI/2) * bodyRunAngle * horzVelocity;

			float bodyJumpAngle = (float)Math.toRadians(-55);

			bodyInfo.setNewRotateX(bodyInfo.getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity)) 
					+ bodyJumpAngle * newVerticalVelocity);
		}
		else
		{
			bodyInfo.setNewRotateX(bodySitInfo.getNewRotateX());
			bodyInfo.setNewPointY(bodySitInfo.getNewPointY());
		}
	}

	public void animateHead(FirecrackerLittenEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener, float verticalVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, neckJointInfo);

		PartAnimate.headAnimateInfoOnlyWithAngleModifiers(neckInfo, yawHeadOffsetDifference, pitchRotation, 0.2F, 0.2F);

		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockBody();

		float idleAmplitudeX = 0.1F;

		if(entity.isSitting())
		{
			idleAmplitudeX *= 0.5F;
		}
		else
		{
			float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
			float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);

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
			float fallingWhiskersAngle = (float)Math.toRadians(-35);
			
			for(int i = 0; i < whiskers.length; i++)
			{
				for(int j = 0; j < whiskers[i].length; j++)
				{
					walkCycleAngleChange = MathHelper.cos(2 * walkCycleInterval * 2 * PI - ((j + 1) * (PI/2))) * walkAngle * (1 - horzVelocity)
							+ walkAngle * (1 - horzVelocity);
					runCycleAngleChange = MathHelper.cos(2 * runCycleInterval * 2 * PI - ((j + 1) * (PI/2))) * runAngle * horzVelocity
							+ runAngle * horzVelocity;

					whiskersInfo[i][j].setNewRotateZ(whiskersInfo[i][j].getNewRotateZ() 
							+ ((walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity)) 
							+ fallingWhiskersAngle * newVerticalVelocity) * (float)Math.pow(-1F, i + 1));
				}
			}
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

		if(entity.isTamed())
		{
			float healthChangeAngle = (float)Math.toRadians(-55);

			earLftInfo.setNewRotateZ(earLftInfo.getNewRotateZ() - (1F - entity.getHealthPercent()) * healthChangeAngle);
			earRtInfo.setNewRotateZ(earRtInfo.getNewRotateZ() + (1F - entity.getHealthPercent()) * healthChangeAngle);
		}

		/* ******************* */
		idleAmplitudeZ = 0.18F;

		for(int i = 0; i < whiskers.length; i++)
		{
			for(int j = 0; j < whiskers[i].length; j++)
			{
				angleChangeZ = MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI
						- ((j + 2) * (PI/2))) * idleAmplitudeZ * (1 - horzVelocity);

				whiskersInfo[i][j].setNewRotateZ(whiskersInfo[i][j].getNewRotateZ() - angleChangeZ * (float)Math.pow(-1F, i + 1));
			}
		}
	}

	public void animateLegs(FirecrackerLittenEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener, float verticalVelocity)
	{
		if(!entity.isSitting())
		{
			float amplitude = 0.8F;

			// Run constants
			float legFntRtModifierRun = 2F * PI * (1F/4F);
			float legBckRtModifierRun = 2F * PI * (5F/16F);
			float legBckLftModifierRun = 2F * PI * (8F/16F);

			// Walk constants
			float legFntRtModifierWalk = 2F * PI * (1F/2F);
			float legBckRtModifierWalk = 2F * PI * (3F/4F);
			float legBckLftModifierWalk = 2F * PI * (1F/4F);

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
			JointAnimation.reverseJointRotatesChange(bodyInfo, legFntRtInfo);
			JointAnimation.reverseJointRotatesChange(bodyInfo, legFntLftInfo);
			JointAnimation.reverseJointRotatesChange(bodyInfo, legBckRtInfo);
			JointAnimation.reverseJointRotatesChange(bodyInfo, legBckLftInfo);

			//( 1 - (Math.pow(Math.E, -0.4F * (float)(i + 1))));

			legFntRtInfo.setNewRotateX(legFntRtInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legFntRtModifier, horzVelocity, RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)) + fallingLegAngle * fallingModifier);
			legFntLftInfo.setNewRotateX(legFntLftInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)) + fallingLegAngle * fallingModifier);
			legBckRtInfo.setNewRotateX(legBckRtInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legBckRtModifier, horzVelocity, RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)));
			legBckLftInfo.setNewRotateX(legBckLftInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legBckLftModifier, horzVelocity, RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)));

			legBckRtHindInfo.setNewRotatesWithVector(legBckRtInfo.getNewRotates());
			legBckLftHindInfo.setNewRotatesWithVector(legBckLftInfo.getNewRotates());
/*
			legFntRtInfo.setNewPnt(legFntRtInfo.getNewPnt());
			legFntLftInfo.setNewPnt(legFntLftInfo.getNewPnt());

			legBckRtInfo.setNewPnt(legBckRtInfo.getNewPnt());
			legBckLftInfo.setNewPnt(legBckLftInfo.getNewPnt());

			legBckRtHindInfo.setNewPnt(legBckRtHindInfo.getNewPnt());
			legBckLftHindInfo.setNewPnt(legBckLftHindInfo.getNewPnt());*/
		}
		else
		{
			legFntRtInfo.setNewRotatesWithVector(legFntRtSitInfo.getNewRotates());
			legFntLftInfo.setNewRotatesWithVector(legFntLftSitInfo.getNewRotates());

			legBckRtInfo.setNewRotatesWithVector(legBckRtSitInfo.getNewRotates());
			legBckLftInfo.setNewRotatesWithVector(legBckLftSitInfo.getNewRotates());

			legBckRtHindInfo.setNewRotatesWithVector(legBckRtHindSitInfo.getNewRotates());
			legBckLftHindInfo.setNewRotatesWithVector(legBckLftHindSitInfo.getNewRotates());

			legFntRtInfo.setNewPnt(legFntRtSitInfo.getNewPnt());
			legFntLftInfo.setNewPnt(legFntLftSitInfo.getNewPnt());

			legBckRtInfo.setNewPnt(legBckRtSitInfo.getNewPnt());
			legBckLftInfo.setNewPnt(legBckLftSitInfo.getNewPnt());

			legBckRtHindInfo.setNewPnt(legBckRtHindSitInfo.getNewPnt());
			legBckLftHindInfo.setNewPnt(legBckLftHindSitInfo.getNewPnt());
		}
	}

	public void animateTail(FirecrackerLittenEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener, float angularVelocity, float verticalVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, tailInfo[0][0]);

		float yawChangeAngle = -(float)Math.toRadians(15);
		float verticalVelocityChangeAngle = (float)Math.toRadians(-22);
		float healthChangeAngle = entity.isTamed() ? (float)Math.toRadians(-6) : 0;


		float newVerticalVelocity = 1.5F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;

		for(int i = 0; i < tail.length; i++)
		{
			IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockTail(i);

			float idleAmplitudeX = 0.18F;
			float idleAmplitudeY = 0.22F;

			float moveFrequency = 0.5F;
			float moveAmplitude = 0.1F;

			// + (float)Math.toRadians(45)
			float angleChangeX = 0;
			float angleChangeY = 0;

			angleChangeX += (MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2F) * idleAmplitudeX
					+ PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, moveFrequency, moveAmplitude))
					* (1F - (Math.pow(Math.E, -0.4F * (float)(i + 1))))
					+ newVerticalVelocity * verticalVelocityChangeAngle
					+ (entity.isTamed() ? (1F - entity.getHealthPercent()) * healthChangeAngle : 0);
			angleChangeY += ((MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2F) * idleAmplitudeY)
					* (1F - (Math.pow(Math.E, -0.4F * (float)(i + 1)))) * (1F - Math.abs(angularVelocity))) * (1F - (Math.abs(verticalVelocity) * 0.5F))
					+ angularVelocity * yawChangeAngle;

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

		for(int i = 0; i < whiskers.length; i++)
		{
			for(int j = 0; j < whiskers[i].length; j++)
			{
				this.animationDeployer.rotate(whiskers[i][j], whiskersInfo[i][j].getNewRotates());
			}
		}

		this.animationDeployer.rotate(legFntRt, legFntRtInfo.getNewRotates());
		this.animationDeployer.rotate(legFntLft, legFntLftInfo.getNewRotates());
		this.animationDeployer.rotate(legBckRt, legBckRtInfo.getNewRotates());
		this.animationDeployer.rotate(legBckLft, legBckLftInfo.getNewRotates());
		this.animationDeployer.rotate(legBckRtHind, legBckRtHindInfo.getNewRotates());
		this.animationDeployer.rotate(legBckLftHind, legBckLftHindInfo.getNewRotates());

		this.animationDeployer.move(legFntRt, legFntRtInfo.getNewPnt());
		this.animationDeployer.move(legFntLft, legFntLftInfo.getNewPnt());
		this.animationDeployer.move(legBckRt, legBckRtInfo.getNewPnt());
		this.animationDeployer.move(legBckLft, legBckLftInfo.getNewPnt());
		this.animationDeployer.move(legBckRtHind, legBckRtHindInfo.getNewPnt());
		this.animationDeployer.move(legBckLftHind, legBckLftHindInfo.getNewPnt());

		for(int i = 0; i < tail.length; i++)
		{
			this.animationDeployer.rotate(tail[i][0], tailInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(tail[i][1], tailInfo[i][1].getNewRotates());
		}

		for(int i = 0; i < tailTip.length; i++)
		{
			this.animationDeployer.rotate(tailTip[i], tailTipInfo[i].getNewRotates());
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