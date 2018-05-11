package fuzzyacornindustries.kindredlegacy.client.model.mob;

import fuzzyacornindustries.kindredlegacy.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.animation.JointAnimation;
import fuzzyacornindustries.kindredlegacy.animation.PartAnimate;
import fuzzyacornindustries.kindredlegacy.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntitySnowSorceressBraixen;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSnowSorceressBraixen extends ModelBase 
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public final float RUN_FREQUENCY = 0.6F;
	public final float WALK_FREQUENCY = 1.2F;
	public final float SNEAK_VELOCITY = 0.45F;

	public ModelRenderer bodyLowerJoint;
	public ModelRenderer bodyLower;
	public ModelRenderer bodyUpperJoint;
	public ModelRenderer bodyUpper;
	PartInfo bodyLowerJointInfo;
	PartInfo bodyLowerInfo;
	PartInfo bodyUpperJointInfo;
	PartInfo bodyUpperInfo;

	public ModelRenderer waistFluffLftFnt;
	public ModelRenderer waistFluffLftMid;
	public ModelRenderer waistFluffLftBck;
	public ModelRenderer waistFluffRtFnt;
	public ModelRenderer waistFluffRtMid;
	public ModelRenderer waistFluffRtBck;
	PartInfo waistFluffLftFntInfo;
	PartInfo waistFluffLftMidInfo;
	PartInfo waistFluffLftBckInfo;
	PartInfo waistFluffRtFntInfo;
	PartInfo waistFluffRtMidInfo;
	PartInfo waistFluffRtBckInfo;

	public ModelRenderer legLft;
	public ModelRenderer legRt;
	PartInfo legLftInfo;
	PartInfo legRtInfo;

	public ModelRenderer shlderFluffLftFnt;
	public ModelRenderer shlderFluffLftBck;
	public ModelRenderer shlderFluffRtFnt;
	public ModelRenderer shlderFluffRtBck;
	PartInfo shlderFluffLftFntInfo;
	PartInfo shlderFluffLftBckInfo;
	PartInfo shlderFluffRtFntInfo;
	PartInfo shlderFluffRtBckInfo;

	public ModelRenderer bodyFluffFnt;
	public ModelRenderer bodyFluffBck;

	public ModelRenderer neckJoint;
	public ModelRenderer neck;
	public ModelRenderer headJoint;
	public ModelRenderer head;
	PartInfo neckJointInfo;
	PartInfo neckInfo;
	PartInfo headJointInfo;
	PartInfo headInfo;

	public ModelRenderer muzzle;

	public ModelRenderer cheekTuffLftJoint;
	public ModelRenderer cheekTuffLft;
	public ModelRenderer cheekTuffRtJoint;
	public ModelRenderer cheekTuffRt;
	PartInfo cheekTuffLftJointInfo;
	PartInfo cheekTuffLftInfo;
	PartInfo cheekTuffRtJointInfo;
	PartInfo cheekTuffRtInfo;

	public ModelRenderer earLftJoint;
	public ModelRenderer earLft;
	public ModelRenderer earRtJoint;
	public ModelRenderer earRt;
	PartInfo earLftJointInfo;
	PartInfo earLftInfo;
	PartInfo earRtJointInfo;
	PartInfo earRtInfo;

	public ModelRenderer earLftTuffJoint;
	public ModelRenderer earLftTuff;
	public ModelRenderer earLftTuffTipJoint;
	public ModelRenderer earLftTuffTip;
	PartInfo earLftTuffJointInfo;
	PartInfo earLftTuffInfo;
	PartInfo earLftTuffTipJointInfo;
	PartInfo earLftTuffTipInfo;

	public ModelRenderer earRtTuffJoint;
	public ModelRenderer earRtTuff;
	public ModelRenderer earRtTuffTipJoint;
	public ModelRenderer earRtTuffTip;
	PartInfo earRtTuffJointInfo;
	PartInfo earRtTuffInfo;
	PartInfo earRtTuffTipJointInfo;
	PartInfo earRtTuffTipInfo;

	public ModelRenderer armLftBaseJoint;
	public ModelRenderer armLftBase;
	public ModelRenderer armLftEndJoint;
	public ModelRenderer armLftEnd;
	PartInfo armLftBaseJointInfo;
	PartInfo armLftBaseInfo;
	PartInfo armLftEndJointInfo;
	PartInfo armLftEndInfo;

	public ModelRenderer armRtBaseJoint;
	public ModelRenderer armRtBase;
	public ModelRenderer armRtEndJoint;
	public ModelRenderer armRtEnd;
	PartInfo armRtBaseJointInfo;
	PartInfo armRtBaseInfo;
	PartInfo armRtEndJointInfo;
	PartInfo armRtEndInfo;

	public ModelRenderer wandJoint;
	public ModelRenderer wand;
	PartInfo wandJointInfo;
	PartInfo wandInfo;

	public ModelRenderer tail[][] = new ModelRenderer[4][2];
	PartInfo tailInfo[][] = new PartInfo[tail.length][tail[0].length];

	public ModelRenderer tailStickJoint;
	public ModelRenderer tailStick;
	PartInfo tailStickJointInfo;
	PartInfo tailStickInfo;

	public ModelSnowSorceressBraixen() 
	{
		this.textureWidth = 64;
		this.textureHeight = 64;

		int jointNumber = 0;
		int partNumber = 1;

		this.bodyLowerJoint = new ModelRenderer(this, 0, 0);
        this.bodyLowerJoint.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.bodyLowerJoint.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        bodyLowerJointInfo = new PartInfo(bodyLowerJoint);
        this.bodyLower = new ModelRenderer(this, 54, 59);
		this.bodyLower.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bodyLower.addBox(-1.5F, -2.0F, -1.0F, 3, 3, 2, 0.0F);
		bodyLowerInfo = new PartInfo(bodyLower);
		this.bodyUpperJoint = new ModelRenderer(this, 55, 54);
		this.bodyUpperJoint.setRotationPoint(0.0F, -2.0F, 0.0F);
		this.bodyUpperJoint.addBox(-1.0F, -2.5F, -1.0F, 0, 0, 0, 0.0F);
		bodyUpperJointInfo = new PartInfo(bodyUpperJoint);
		this.bodyUpper = new ModelRenderer(this, 55, 54);
		this.bodyUpper.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bodyUpper.addBox(-1.0F, -2.5F, -1.0F, 2, 3, 2, 0.0F);
		bodyUpperInfo = new PartInfo(bodyUpper);

		this.waistFluffLftFnt = new ModelRenderer(this, 32, 54);
        this.waistFluffLftFnt.setRotationPoint(0.7F, 0.2F, -0.2F);
        this.waistFluffLftFnt.addBox(0.0F, -0.5F, -1.0F, 3, 1, 2, 0.0F);
        this.setRotateAngle(waistFluffLftFnt, 0.6108652381980153F, 0.6108652381980153F, 0.6108652381980153F);
        waistFluffLftFntInfo = new PartInfo(waistFluffLftFnt);
		this.waistFluffLftMid = new ModelRenderer(this, 33, 58);
		this.waistFluffLftMid.setRotationPoint(1.0F, 0.3F, 0.0F);
		this.waistFluffLftMid.addBox(-0.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(waistFluffLftMid, 0.0F, -0.0F, -0.17453292519943295F);
		waistFluffLftMidInfo = new PartInfo(waistFluffLftMid);
		this.waistFluffLftBck = new ModelRenderer(this, 32, 61);
        this.waistFluffLftBck.setRotationPoint(0.7F, 0.2F, 0.2F);
        this.waistFluffLftBck.addBox(0.0F, -0.5F, -1.0F, 3, 1, 2, 0.0F);
        this.setRotateAngle(waistFluffLftBck, -0.6108652381980153F, -0.6108652381980153F, 0.6108652381980153F);
        waistFluffLftBckInfo = new PartInfo(waistFluffLftBck);
        this.waistFluffRtFnt = new ModelRenderer(this, 43, 54);
        this.waistFluffRtFnt.setRotationPoint(-0.7F, 0.2F, -0.2F);
        this.waistFluffRtFnt.addBox(-3.0F, -0.5F, -1.0F, 3, 1, 2, 0.0F);
        this.setRotateAngle(waistFluffRtFnt, 0.6108652381980153F, -0.6108652381980153F, -0.6108652381980153F);
        waistFluffRtFntInfo = new PartInfo(waistFluffRtFnt);
		this.waistFluffRtMid = new ModelRenderer(this, 44, 58);
		this.waistFluffRtMid.setRotationPoint(-1.0F, 0.3F, 0.0F);
		this.waistFluffRtMid.addBox(-2.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(waistFluffRtMid, 0.0F, -0.0F, 0.17453292519943295F);
		waistFluffRtMidInfo = new PartInfo(waistFluffRtMid);
		this.waistFluffRtBck = new ModelRenderer(this, 43, 61);
        this.waistFluffRtBck.setRotationPoint(-1.0F, 0.7F, 0.5F);
        this.waistFluffRtBck.addBox(-2.5F, -0.5F, -1.0F, 3, 1, 2, 0.0F);
        this.setRotateAngle(waistFluffRtBck, -0.6108652381980153F, 0.6108652381980153F, -0.6108652381980153F);
        waistFluffRtBckInfo = new PartInfo(waistFluffRtBck);

		this.legLft = new ModelRenderer(this, 26, 0);
		this.legLft.setRotationPoint(0.7F, 1.0F, 0.1F);
		this.legLft.addBox(-0.5F, -0.5F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(legLft, -0.17453292519943295F, -0.0F, 0.0F);
		this.legLft.setTextureOffset(26, 5).addBox(-0.5F, 1.5F, 0.5F, 1, 4, 1, 0.0F); // legLftHind
		legLftInfo = new PartInfo(legLft);
		this.legRt = new ModelRenderer(this, 31, 0);
		this.legRt.setRotationPoint(-0.7F, 1.0F, 0.0F);
		this.legRt.addBox(-0.5F, -0.5F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(legRt, -0.17453292519943295F, -0.0F, 0.0F);
		this.legRt.setTextureOffset(31, 5).addBox(-0.5F, 1.5F, 0.5F, 1, 4, 1, 0.0F); // legRtHind
		legRtInfo = new PartInfo(legRt);

		this.shlderFluffLftFnt = new ModelRenderer(this, 47, 44);
		this.shlderFluffLftFnt.setRotationPoint(0.7F, -2.0F, -0.5F);
		this.shlderFluffLftFnt.addBox(0.0F, -0.5F, -1.0F, 2, 1, 2, 0.0F);
		this.setRotateAngle(shlderFluffLftFnt, 0.19250981649497453F, 0.2527236756887789F, -0.27052603405912107F);
		shlderFluffLftFntInfo = new PartInfo(shlderFluffLftFnt);
		this.shlderFluffLftBck = new ModelRenderer(this, 47, 48);
		this.shlderFluffLftBck.setRotationPoint(0.7F, -2.0F, 0.5F);
		this.shlderFluffLftBck.addBox(0.0F, -0.5F, -1.0F, 2, 1, 2, 0.0F);
		this.setRotateAngle(shlderFluffLftBck, -0.19250981649497453F, -0.2527236756887789F, -0.27052603405912107F);
		shlderFluffLftBckInfo = new PartInfo(shlderFluffLftBck);
		this.shlderFluffRtFnt = new ModelRenderer(this, 56, 44);
		this.shlderFluffRtFnt.setRotationPoint(-0.7F, -2.0F, -0.5F);
		this.shlderFluffRtFnt.addBox(-2.0F, -0.5F, -1.0F, 2, 1, 2, 0.0F);
		this.setRotateAngle(shlderFluffRtFnt, 0.19250981649497453F, -0.2527236756887789F, 0.27052603405912107F);
		shlderFluffRtFntInfo = new PartInfo(shlderFluffRtFnt);
		this.shlderFluffRtBck = new ModelRenderer(this, 56, 48);
		this.shlderFluffRtBck.setRotationPoint(-0.7F, -2.0F, 0.5F);
		this.shlderFluffRtBck.addBox(-2.0F, -0.5F, -1.0F, 2, 1, 2, 0.0F);
		this.setRotateAngle(shlderFluffRtBck, -0.19250981649497453F, 0.2527236756887789F, 0.27052603405912107F);
		shlderFluffRtBckInfo = new PartInfo(shlderFluffRtBck);

		this.bodyFluffFnt = new ModelRenderer(this, 40, 46);
		this.bodyFluffFnt.setRotationPoint(0.0F, -2.0F, -0.5F);
		this.bodyFluffFnt.addBox(-0.5F, -0.5F, -2.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(bodyFluffFnt, 0.9599310885968813F, -0.0F, 0.0F);
		this.bodyFluffBck = new ModelRenderer(this, 40, 50);
		this.bodyFluffBck.setRotationPoint(0.0F, -2.0F, 0.5F);
		this.bodyFluffBck.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(bodyFluffBck, -0.9599310885968813F, -0.0F, 0.0F);

		this.neckJoint = new ModelRenderer(this, 57, 51);
		this.neckJoint.setRotationPoint(0.0F, -2.0F, 0.0F);
		this.neckJoint.addBox(-0.5F, -2.0F, -0.5F, 0, 0, 0, 0.0F);
		neckJointInfo = new PartInfo(neckJoint);
		this.neck = new ModelRenderer(this, 57, 51);
		this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.neck.addBox(-0.5F, -2.0F, -0.5F, 1, 2, 1, 0.0F);
		neckInfo = new PartInfo(neck);
		this.headJoint = new ModelRenderer(this, 0, 0);
		this.headJoint.setRotationPoint(0.0F, -2.0F, 0.0F);
		this.headJoint.addBox(-1.5F, -2.5F, -1.5F, 0, 0, 0, 0.0F);
		headJointInfo = new PartInfo(headJoint);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-1.5F, -2.5F, -1.5F, 3, 3, 3, 0.0F);
		headInfo = new PartInfo(head);

		this.muzzle = new ModelRenderer(this, 10, 0);
		this.muzzle.setRotationPoint(0.0F, -0.2F, -1.0F);
		this.muzzle.addBox(-0.5F, -0.5F, -1.0F, 1, 1, 1, 0.0F);
		this.setRotateAngle(muzzle, 0.2617993877991494F, -0.0F, 0.0F);

		this.cheekTuffLftJoint = new ModelRenderer(this, 15, 0);
		this.cheekTuffLftJoint.setRotationPoint(1.0F, -0.1F, 0.0F);
		this.cheekTuffLftJoint.addBox(0.0F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(cheekTuffLftJoint, 0.0F, 0.0F, 0.17453292519943295F);
		cheekTuffLftJointInfo = new PartInfo(cheekTuffLftJoint);
		this.cheekTuffLft = new ModelRenderer(this, 15, 0);
		this.cheekTuffLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cheekTuffLft.addBox(0.0F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		cheekTuffLftInfo = new PartInfo(cheekTuffLft);
		this.cheekTuffRtJoint = new ModelRenderer(this, 15, 3);
		this.cheekTuffRtJoint.setRotationPoint(-1.0F, -0.1F, 0.0F);
		this.cheekTuffRtJoint.addBox(-2.0F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(cheekTuffRtJoint, 0.0F, 0.0F, -0.17453292519943295F);
		cheekTuffRtJointInfo = new PartInfo(cheekTuffRtJoint);
		this.cheekTuffRt = new ModelRenderer(this, 15, 3);
		this.cheekTuffRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.cheekTuffRt.addBox(-2.0F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		cheekTuffRtInfo = new PartInfo(cheekTuffRt);

		this.earLftJoint = new ModelRenderer(this, 2, 22);
		this.earLftJoint.setRotationPoint(0.8F, -2.0F, 0.5F);
		this.earLftJoint.addBox(-1.0F, -4.0F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earLftJoint, 0.0F, 0.0F, 0.3490658503988659F);
		earLftJointInfo = new PartInfo(earLftJoint);
		this.earLft = new ModelRenderer(this, 2, 22);
		this.earLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earLft.addBox(-1.0F, -4.0F, -0.5F, 2, 4, 1, 0.0F);
		this.earLft.setTextureOffset(3, 28).addBox(-0.5F, -5.0F, -0.5F, 1, 1, 1, 0.0F); // earLftTip
		earLftInfo = new PartInfo(earLft);
		this.earRtJoint = new ModelRenderer(this, 13, 22);
		this.earRtJoint.setRotationPoint(-0.8F, -2.0F, 0.5F);
		this.earRtJoint.addBox(-1.0F, -4.0F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earRtJoint, 0.0F, 0.0F, -0.3490658503988659F);
		earRtJointInfo = new PartInfo(earRtJoint);
		this.earRt = new ModelRenderer(this, 13, 22);
		this.earRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earRt.addBox(-1.0F, -4.0F, -0.5F, 2, 4, 1, 0.0F);
		this.earRt.setTextureOffset(14, 28).addBox(-0.5F, -5.0F, -0.5F, 1, 1, 1, 0.0F); // earRtTip
		earRtInfo = new PartInfo(earRt);

		this.earLftTuffJoint = new ModelRenderer(this, 1, 34);
		this.earLftTuffJoint.setRotationPoint(0.5F, -1.5F, 0.0F);
		this.earLftTuffJoint.addBox(0.5F, -1.0F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earLftTuffJoint, 0.17453292519943295F, 0.15707963267948966F, -0.5235987755982988F);
		earLftTuffJointInfo = new PartInfo(earLftTuffJoint);
		this.earLftTuff = new ModelRenderer(this, 1, 34);
		this.earLftTuff.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earLftTuff.addBox(0.5F, -1.0F, -0.5F, 3, 2, 1, 0.0F);
		earLftTuffInfo = new PartInfo(earLftTuff);
		this.earLftTuffTipJoint = new ModelRenderer(this, 2, 31);
		this.earLftTuffTipJoint.setRotationPoint(3.0F, 0.0F, 0.0F);
		this.earLftTuffTipJoint.addBox(0.0F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earLftTuffTipJoint, 0.0F, 0.0F, 0.2617993877991494F);
		earLftTuffTipJointInfo = new PartInfo(earLftTuffTipJoint);
		this.earLftTuffTip = new ModelRenderer(this, 2, 31);
		this.earLftTuffTip.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earLftTuffTip.addBox(0.0F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		earLftTuffTipInfo = new PartInfo(earLftTuffTip);
		this.earRtTuffJoint = new ModelRenderer(this, 12, 34);
		this.earRtTuffJoint.setRotationPoint(-0.5F, -1.5F, 0.0F);
		this.earRtTuffJoint.addBox(-3.5F, -1.0F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earRtTuffJoint, 0.17453292519943295F, -0.15707963267948966F, 0.5235987755982988F);
		earRtTuffJointInfo = new PartInfo(earRtTuffJoint);
		this.earRtTuff = new ModelRenderer(this, 12, 34);
		this.earRtTuff.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earRtTuff.addBox(-3.5F, -1.0F, -0.5F, 3, 2, 1, 0.0F);
		earRtTuffInfo = new PartInfo(earRtTuff);
		this.earRtTuffTipJoint = new ModelRenderer(this, 13, 31);
		this.earRtTuffTipJoint.setRotationPoint(-3.0F, 0.0F, 0.0F);
		this.earRtTuffTipJoint.addBox(-2.0F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earRtTuffTipJoint, 0.0F, 0.0F, -0.2617993877991494F);
		earRtTuffTipJointInfo = new PartInfo(earRtTuffTipJoint);
		this.earRtTuffTip = new ModelRenderer(this, 13, 31);
		this.earRtTuffTip.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earRtTuffTip.addBox(-2.0F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		earRtTuffTipInfo = new PartInfo(earRtTuffTip);

		this.armLftBaseJoint = new ModelRenderer(this, 29, 35);
		this.armLftBaseJoint.setRotationPoint(1.0F, -1.3F, 0.0F);
		this.armLftBaseJoint.addBox(-0.5F, -0.5F, -2.5F, 0, 0, 0, 0.0F);
		armLftBaseJointInfo = new PartInfo(armLftBaseJoint);
		this.armLftBase = new ModelRenderer(this, 29, 35);
		this.armLftBase.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.armLftBase.addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3, 0.0F);
		this.setRotateAngle(armLftBase, (float)Math.toRadians(35), (float)Math.toRadians(-45), 0.0F);
		armLftBaseInfo = new PartInfo(armLftBase);
		this.armLftEndJoint = new ModelRenderer(this, 29, 40);
		this.armLftEndJoint.setRotationPoint(0.0F, 0.0F, -2.2F);
		this.armLftEndJoint.addBox(-0.5F, -0.5F, -2.8F, 0, 0, 0, 0.0F);
		this.setRotateAngle(armLftEndJoint, (float)Math.toRadians(-25), 0.0F, 0.0F);
		armLftEndJointInfo = new PartInfo(armLftEndJoint);
		this.armLftEnd = new ModelRenderer(this, 29, 40);
		this.armLftEnd.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.armLftEnd.addBox(-0.5F, -0.5F, -2.8F, 1, 1, 3, 0.0F);
		this.setRotateAngle(armLftEnd, 0.0F, (float)Math.toRadians(35), 0.0F);
		armLftEndInfo = new PartInfo(armLftEnd);
		this.armRtBaseJoint = new ModelRenderer(this, 38, 35);
		this.armRtBaseJoint.setRotationPoint(-1.0F, -1.3F, 0.0F);
		this.armRtBaseJoint.addBox(-0.5F, -0.5F, -2.5F, 0, 0, 0, 0.0F);
		armRtBaseJointInfo = new PartInfo(armRtBaseJoint);
		this.armRtBase = new ModelRenderer(this, 38, 35);
		this.armRtBase.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.armRtBase.addBox(-0.5F, -0.5F, -2.5F, 1, 1, 3, 0.0F);
		this.setRotateAngle(armRtBase, (float)Math.toRadians(25), (float)Math.toRadians(15), 0.0F);
		armRtBaseInfo = new PartInfo(armRtBase);
		this.armRtEndJoint = new ModelRenderer(this, 38, 40);
		this.armRtEndJoint.setRotationPoint(0.0F, 0.0F, -2.2F);
		this.armRtEndJoint.addBox(-0.5F, -0.5F, -2.8F, 0, 0, 0, 0.0F);
		this.setRotateAngle(armRtEndJoint, (float)Math.toRadians(-35), 0.0F, 0.0F);
		armRtEndJointInfo = new PartInfo(armRtEndJoint);
		this.armRtEnd = new ModelRenderer(this, 38, 40);
		this.armRtEnd.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.armRtEnd.addBox(-0.51F, -0.51F, -2.8F, 1, 1, 3, 0.0F);
		this.setRotateAngle(armRtEnd, 0.0F, (float)Math.toRadians(-30), 0.0F);
		armRtEndInfo = new PartInfo(armRtEnd);

		this.wandJoint = new ModelRenderer(this, 38, 30);
		this.wandJoint.setRotationPoint(0.0F, 0.0F, -2.5F);
		this.wandJoint.addBox(-0.5F, -0.5F, -2.8F, 0, 0, 0, 0.0F);
		this.setRotateAngle(wandJoint, (float)Math.toRadians(-55), 0.0F, 0.0F);
		wandJointInfo = new PartInfo(wandJoint);
		this.wand = new ModelRenderer(this, 38, 30);
		this.wand.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.wand.addBox(-0.5F, -0.5F, -2.8F, 1, 1, 3, 0.0F);
		wandInfo = new PartInfo(wand);

		int tailNumber = 0;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 54, 15); // tailBaseJoint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.0F);
		this.tail[tailNumber][jointNumber].addBox(-1.0F, -1.0F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][jointNumber], 0.08726646259971647F, 0.0F, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 54, 15); // tailBase
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-1.0F, -1.0F, -0.5F, 2, 2, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 52, 20); // tailMidJoint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.5F);
		this.tail[tailNumber][jointNumber].addBox(-1.5F, -1.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][jointNumber], -0.4363323129985824F, 0.0F, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 52, 20); // tailMid
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-1.5F, -1.5F, -0.5F, 3, 3, 3, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 53, 27); // tailEndJoint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.2F);
		this.tail[tailNumber][jointNumber].addBox(-1.0F, -1.0F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][jointNumber], -0.6108652381980153F, 0.0F, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 53, 27); // tailEnd
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-1.0F, -1.0F, -0.5F, 2, 2, 3, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 54, 33); // tailTipJoint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.1F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][jointNumber], 0.4363323129985824F, 0.0F, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 54, 33); // tailTip
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);

		this.tailStickJoint = new ModelRenderer(this, 47, 30);
		this.tailStickJoint.setRotationPoint(0.0F, -1.0F, 1.0F);
		this.tailStickJoint.addBox(-0.5F, -0.5F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tailStickJoint, 1.5707963267948966F, -0.0F, 0.0F);
		tailStickJointInfo = new PartInfo(tailStickJoint);
		this.tailStick = new ModelRenderer(this, 47, 30);
		this.tailStick.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tailStick.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		tailStickInfo = new PartInfo(tailStick);

		this.bodyLowerJoint.addChild(this.bodyLower);
		this.bodyLowerJoint.addChild(this.legLft);
		this.bodyLowerJoint.addChild(this.legRt);
		
		this.bodyLower.addChild(this.waistFluffLftBck);
		this.bodyLower.addChild(this.waistFluffLftFnt);
		this.bodyLower.addChild(this.waistFluffLftMid);
		this.bodyLower.addChild(this.waistFluffRtBck);
		this.bodyLower.addChild(this.waistFluffRtFnt);
		this.bodyLower.addChild(this.waistFluffRtMid);
		this.bodyLower.addChild(this.bodyUpperJoint);
		this.bodyLower.addChild(this.tail[0][0]); // tailBaseJoint

		this.bodyUpperJoint.addChild(this.bodyFluffBck);
		this.bodyUpperJoint.addChild(this.bodyFluffFnt);
		this.bodyUpperJoint.addChild(this.bodyUpper);
		this.bodyUpperJoint.addChild(this.shlderFluffLftBck);
		this.bodyUpperJoint.addChild(this.shlderFluffLftFnt);
		this.bodyUpperJoint.addChild(this.shlderFluffRtBck);
		this.bodyUpperJoint.addChild(this.shlderFluffRtFnt);
		this.bodyUpperJoint.addChild(this.armLftBaseJoint);
		this.bodyUpperJoint.addChild(this.armRtBaseJoint);
		this.bodyUpper.addChild(this.neckJoint);

		this.armLftBaseJoint.addChild(this.armLftBase);
		this.armLftBase.addChild(this.armLftEndJoint);
		this.armLftEndJoint.addChild(this.armLftEnd);

		this.armRtBaseJoint.addChild(this.armRtBase);
		this.armRtBase.addChild(this.armRtEndJoint);
		this.armRtEndJoint.addChild(this.armRtEnd);
		this.armRtEnd.addChild(this.wandJoint);

		this.neckJoint.addChild(this.neck);
		this.neck.addChild(this.headJoint);

		this.headJoint.addChild(this.head);
		this.head.addChild(this.cheekTuffLftJoint);
		this.head.addChild(this.cheekTuffRtJoint);
		this.head.addChild(this.earLftJoint);
		this.head.addChild(this.earLftTuffJoint);
		this.head.addChild(this.earRtJoint);
		this.head.addChild(this.earRtTuffJoint);
		this.head.addChild(this.muzzle);

		this.cheekTuffLftJoint.addChild(this.cheekTuffLft);
		this.cheekTuffRtJoint.addChild(this.cheekTuffRt);
		this.earLftJoint.addChild(this.earLft);
		this.earLftTuff.addChild(this.earLftTuffTipJoint);
		this.earLftTuffJoint.addChild(this.earLftTuff);
		this.earLftTuffTipJoint.addChild(this.earLftTuffTip);
		this.earRtJoint.addChild(this.earRt);
		this.earRtTuff.addChild(this.earRtTuffTipJoint);
		this.earRtTuffJoint.addChild(this.earRtTuff);
		this.earRtTuffTipJoint.addChild(this.earRtTuffTip);

        this.tail[0][0].addChild(this.tail[0][1]);
        
		for(int i = 1; i < tail.length; i++)
		{
	        this.tail[i - 1][1].addChild(this.tail[i][0]);
	        this.tail[i][0].addChild(this.tail[i][1]);
		}
		
		this.tail[1][1].addChild(this.tailStickJoint); // tailMid
		this.tailStickJoint.addChild(this.tailStick);
		this.wandJoint.addChild(this.wand);

		animationDeployer = new AnimationDeployer(this);
	}

	@Override
	public void render(Entity entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize) 
	{
		animate(entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);

		this.bodyLowerJoint.render(modelSize);
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

		if(animationDeployer.getEntity().getAnimationID() == EntitySnowSorceressBraixen.actionIDSpell)
		{
			//idleDampener = animateSpell(animationDeployer.getEntity(), distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
		}

		animateBody((EntitySnowSorceressBraixen)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, idleDampener);
		animateHead((EntitySnowSorceressBraixen)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
		animateArms((EntitySnowSorceressBraixen)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, idleDampener);
		animateLegs((EntitySnowSorceressBraixen)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, idleDampener);
		animateTail((EntitySnowSorceressBraixen)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);

		deployAnimations();
	}

	public void resetPartInfos()
	{
		bodyLowerJointInfo.resetNewAngles();
		bodyLowerJointInfo.resetNewPnt();
		bodyLowerInfo.resetNewAngles();
		bodyUpperJointInfo.resetNewAngles();
		bodyUpperInfo.resetNewAngles();

		waistFluffLftFntInfo.resetNewAngles();
		waistFluffLftMidInfo.resetNewAngles();
		waistFluffLftBckInfo.resetNewAngles();
		waistFluffRtFntInfo.resetNewAngles();
		waistFluffRtMidInfo.resetNewAngles();
		waistFluffRtBckInfo.resetNewAngles();

		legLftInfo.resetNewAngles();
		legRtInfo.resetNewAngles();

		shlderFluffLftFntInfo.resetNewAngles();
		shlderFluffLftBckInfo.resetNewAngles();
		shlderFluffRtFntInfo.resetNewAngles();
		shlderFluffRtBckInfo.resetNewAngles();

		neckJointInfo.resetNewAngles();
		neckInfo.resetNewAngles();
		headJointInfo.resetNewAngles();
		headInfo.resetNewAngles();

		cheekTuffLftJointInfo.resetNewAngles();
		cheekTuffLftInfo.resetNewAngles();
		cheekTuffRtJointInfo.resetNewAngles();
		cheekTuffRtInfo.resetNewAngles();

		earLftJointInfo.resetNewAngles();
		earLftInfo.resetNewAngles();
		earRtJointInfo.resetNewAngles();
		earRtInfo.resetNewAngles();

		earLftTuffJointInfo.resetNewAngles();
		earLftTuffInfo.resetNewAngles();
		earLftTuffTipJointInfo.resetNewAngles();
		earLftTuffTipInfo.resetNewAngles();

		earRtTuffJointInfo.resetNewAngles();
		earRtTuffInfo.resetNewAngles();
		earRtTuffTipJointInfo.resetNewAngles();
		earRtTuffTipInfo.resetNewAngles();

		armLftBaseJointInfo.resetNewAngles();
		armLftBaseInfo.resetNewAngles();
		armLftEndJointInfo.resetNewAngles();
		armLftEndInfo.resetNewAngles();

		armRtBaseJointInfo.resetNewAngles();
		armRtBaseInfo.resetNewAngles();
		armRtEndJointInfo.resetNewAngles();
		armRtEndInfo.resetNewAngles();

		wandJointInfo.resetNewAngles();
		wandInfo.resetNewAngles();

		tailStickJointInfo.resetNewAngles();
		tailStickInfo.resetNewAngles();

		for(int i = 0; i < tail.length; i++)
		{
			tailInfo[i][0].resetNewAngles();
			tailInfo[i][1].resetNewAngles();
		}
	}

	public float animateSpell(IAnimatedEntity entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize)
	{
		float idleDampener = 1F;

		return idleDampener;
	}

	public void animateBody(EntitySnowSorceressBraixen entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener)
	{
		float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
		float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);

		float walkMaximumChangeY = 0.85F;
		float runMaximumChangeY = 1.2F;

		float walkCyclePointChange = MathHelper.cos(2 * walkCycleInterval * 2 * PI + PI/2) * walkMaximumChangeY * (1 - horzVelocity);
		float runCyclePointChange = MathHelper.cos(2 * runCycleInterval * 2 * PI + PI/2) * runMaximumChangeY * horzVelocity;

		bodyLowerJointInfo.setNewPointY(bodyLowerJointInfo.getNewPointY() + (walkCyclePointChange + runCyclePointChange) * horzVelocity);

		float bodyWalkAngle = (float)Math.toRadians(6);
		float bodyRunAngle = (float)Math.toRadians(10);

		float walkCycleAngleChange = MathHelper.cos(2 * walkCycleInterval * 2 * PI + PI/2) * bodyWalkAngle * (1 - horzVelocity)
				+ bodyWalkAngle * (1 - horzVelocity);
		float runCycleAngleChange = MathHelper.cos(2 * runCycleInterval * 2 * PI + PI/2) * bodyRunAngle * horzVelocity
				+ bodyRunAngle * horzVelocity;

		bodyLowerInfo.setNewRotateX(bodyLowerInfo.getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocity * idleDampener);

		/* ******************* */		
		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockBodySway(0);

		float idleAmplitudeZ = 0.10F;
		float angleChangeZ = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredZ(0) * PI * 2) * idleAmplitudeZ;
		bodyLowerInfo.setNewRotateZ(bodyLowerInfo.getNewRotateZ() + angleChangeZ);

		angleChangeZ = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredZ(0) * PI * 2 - (PI/4)) * idleAmplitudeZ * 2F;
		waistFluffLftMidInfo.setNewRotateZ(waistFluffLftMidInfo.getNewRotateZ() + angleChangeZ);
		waistFluffRtBckInfo.setNewRotateZ(waistFluffRtBckInfo.getNewRotateZ() + angleChangeZ);

		angleChangeZ = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredZ(0) * PI * 2 - (PI/2)) * idleAmplitudeZ * 2F;
		waistFluffLftFntInfo.setNewRotateZ(waistFluffLftFntInfo.getNewRotateZ() + angleChangeZ);
		waistFluffRtMidInfo.setNewRotateZ(waistFluffRtMidInfo.getNewRotateZ() + angleChangeZ);

		angleChangeZ = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredZ(0) * PI * 2 - ((3*PI)/4)) * idleAmplitudeZ * 2F;
		waistFluffLftBckInfo.setNewRotateZ(waistFluffLftBckInfo.getNewRotateZ() + angleChangeZ);
		waistFluffRtFntInfo.setNewRotateZ(waistFluffRtFntInfo.getNewRotateZ() + angleChangeZ);
		
		currentIdleAnimationClock = entity.getIdleAnimationClockBodySway(1);

		angleChangeZ = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredZ(0) * PI * 2) * idleAmplitudeZ * 0.7F;
		bodyUpperInfo.setNewRotateZ(bodyUpperInfo.getNewRotateZ() + angleChangeZ);

		angleChangeZ = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredZ(0) * PI * 2 - (PI/2)) * idleAmplitudeZ * 2.5F;
		shlderFluffLftFntInfo.setNewRotateZ(shlderFluffLftFntInfo.getNewRotateZ() + angleChangeZ);
		shlderFluffRtBckInfo.setNewRotateZ(shlderFluffRtBckInfo.getNewRotateZ() + angleChangeZ);

		angleChangeZ = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredZ(0) * PI * 2 - ((3*PI)/4)) * idleAmplitudeZ * 2.5F;
		shlderFluffLftBckInfo.setNewRotateZ(shlderFluffLftBckInfo.getNewRotateZ() + angleChangeZ);
		shlderFluffRtFntInfo.setNewRotateZ(shlderFluffRtFntInfo.getNewRotateZ() + angleChangeZ);

		currentIdleAnimationClock = entity.getIdleAnimationClockBodySway(2);

		angleChangeZ = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredZ(0) * PI * 2) * idleAmplitudeZ * 0.3F;
		neckInfo.setNewRotateZ(neckInfo.getNewRotateZ() + angleChangeZ);
	}
	
	public void animateArms(EntitySnowSorceressBraixen entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener)
	{
		JointAnimation.reverseJointRotatesChange(bodyLowerJointInfo, armRtBaseJointInfo);
		JointAnimation.reverseJointRotatesChange(bodyLowerJointInfo, armLftBaseJointInfo);

		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockBodySway(4);

		float idleAmplitudeX = 0.0F;
		float idleAmplitudeY = 0.80F;

		float angleChangeX = 0F;
		float angleChangeY = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredZ(0) * PI * 2) * idleAmplitudeY;
		wandInfo.setNewRotateY(wandInfo.getNewRotateY() - angleChangeY);

		currentIdleAnimationClock = entity.getIdleAnimationClockBodySway(2);

		idleAmplitudeX = 0.1F;
		idleAmplitudeY = 0.40F;
		
		angleChangeX = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2) * idleAmplitudeX;
		angleChangeY = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredZ(0) * PI * 2) * idleAmplitudeY;
		armRtEndInfo.setNewRotateX(armRtEndInfo.getNewRotateX() - angleChangeX);
		armRtEndInfo.setNewRotateY(armRtEndInfo.getNewRotateY() - angleChangeY);

		currentIdleAnimationClock = entity.getIdleAnimationClockBodySway(3);

		idleAmplitudeX = 0.1F;
		idleAmplitudeY = 0.35F;

		angleChangeX = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2) * idleAmplitudeX;
		angleChangeY = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredZ(0) * PI * 2) * idleAmplitudeY;
		armRtBaseInfo.setNewRotateX(armRtBaseInfo.getNewRotateX() - angleChangeX);
		armRtBaseInfo.setNewRotateY(armRtBaseInfo.getNewRotateY() - angleChangeY);

		currentIdleAnimationClock = entity.getIdleAnimationClockArmLft(0);

		idleAmplitudeX = 0.2F;
		idleAmplitudeY = 0.2F;
		
		angleChangeX = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2) * idleAmplitudeX;
		angleChangeY = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredZ(0) * PI * 2) * idleAmplitudeY * (1F - horzVelocity);

		float movementAmplitude = 0.5F;
		angleChangeY =+ PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, WALK_FREQUENCY/2F, movementAmplitude) * idleDampener;
		
		armLftBaseInfo.setNewRotateX(armLftBaseInfo.getNewRotateX() - angleChangeX);
		armLftBaseInfo.setNewRotateY(armLftBaseInfo.getNewRotateY() - angleChangeY);

		currentIdleAnimationClock = entity.getIdleAnimationClockArmLft(1);

		idleAmplitudeX = 0.2F;
		idleAmplitudeY = 0.25F;

		angleChangeX = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2) * idleAmplitudeX;
		angleChangeY = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredZ(0) * PI * 2) * idleAmplitudeY * (1F - horzVelocity);
		armLftEndInfo.setNewRotateX(armLftEndInfo.getNewRotateX() - angleChangeX);
		armLftEndInfo.setNewRotateY(armLftEndInfo.getNewRotateY() - angleChangeY);
	}

	public void animateHead(EntitySnowSorceressBraixen entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize)
	{
		JointAnimation.reverseJointRotatesChange(bodyLowerInfo, headJointInfo);
		JointAnimation.reverseJointRotatesChangeZ(bodyUpperInfo, headJointInfo);
		JointAnimation.reverseJointRotatesChangeZ(neckInfo, headJointInfo);

		PartAnimate.headAnimateInfoOnlyWithAngleModifiers(headInfo, yawHeadOffsetDifference, pitchRotation, 0.8F, 0.8F);

		/* ******* Ears ******** */
		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockEars(0);
		
		float moveFrequencyZ = 0.7F;
		float moveAmplitudeZ = 0.10F;

		float moveAngleZ = PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, moveFrequencyZ, moveAmplitudeZ);

		float idleAmplitudeZ = 0.20F;

		// + (float)Math.toRadians(45)
		float angleChangeZ = moveAngleZ;

		angleChangeZ += MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredZ(0) * 2 * PI) * idleAmplitudeZ;

		earRtInfo.setNewRotateZ(earRtInfo.getNewRotateZ() - angleChangeZ);
		earLftInfo.setNewRotateZ(earLftInfo.getNewRotateZ() + angleChangeZ);

		/* ******* Ear Hairs ******** */
		currentIdleAnimationClock = entity.getIdleAnimationClockEars(1);
		
		moveFrequencyZ = 1.4F;
		moveAmplitudeZ = 0.10F;

		idleAmplitudeZ = 0.25F;

		// + (float)Math.toRadians(45)
		angleChangeZ = moveAngleZ * 1.3F;

		angleChangeZ += MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredZ(0) * 2 * PI) * idleAmplitudeZ;

		earRtTuffInfo.setNewRotateZ(earRtTuffInfo.getNewRotateZ() - angleChangeZ);
		earLftTuffInfo.setNewRotateZ(earLftTuffInfo.getNewRotateZ() + angleChangeZ);

		angleChangeZ = moveAngleZ * 1.3F;

		angleChangeZ += MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredZ(0) * 2 * PI - (PI/2)) * idleAmplitudeZ;

		earRtTuffTipInfo.setNewRotateZ(earRtTuffTipInfo.getNewRotateZ() - angleChangeZ);
		earLftTuffTipInfo.setNewRotateZ(earLftTuffTipInfo.getNewRotateZ() + angleChangeZ);

		/* ******* Cheek Hairs ******** */
		angleChangeZ = moveAngleZ * 1.5F;

		cheekTuffLftInfo.setNewRotateZ(cheekTuffLftInfo.getNewRotateZ() - angleChangeZ);
		cheekTuffRtInfo.setNewRotateZ(cheekTuffRtInfo.getNewRotateZ() + angleChangeZ);
	}

	public void animateLegs(EntitySnowSorceressBraixen entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener)
	{
		float moveAmplitude = 0.8F;

		legRtInfo.setNewRotateX(legRtInfo.getNewRotateX() + PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, WALK_FREQUENCY, moveAmplitude) * idleDampener);
		legLftInfo.setNewRotateX(legLftInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, WALK_FREQUENCY, moveAmplitude) * idleDampener);
	}

	public void animateTail(EntitySnowSorceressBraixen entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize)
	{
		JointAnimation.reverseJointRotatesChange(bodyLowerInfo, tailInfo[0][0]);
		
		for(int i = 0; i < tail.length; i++)
		{
			IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockTail(i);

			float idleAmplitudeX = 0.12F;
			float idleAmplitudeY = 0.28F;

			float moveFrequency = 1F;
			float moveAmplitude = 0.12F;

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
		this.animationDeployer.move(bodyLowerJoint, bodyLowerJointInfo.getNewPnt());
		this.animationDeployer.rotate(bodyLowerJoint, bodyLowerJointInfo.getNewRotates());
		this.animationDeployer.rotate(bodyLower, bodyLowerInfo.getNewRotates());
		this.animationDeployer.rotate(bodyUpperJoint, bodyUpperJointInfo.getNewRotates());
		this.animationDeployer.rotate(bodyUpper, bodyUpperInfo.getNewRotates());

		this.animationDeployer.rotate(waistFluffLftFnt, waistFluffLftFntInfo.getNewRotates());
		this.animationDeployer.rotate(waistFluffLftMid, waistFluffLftMidInfo.getNewRotates());
		this.animationDeployer.rotate(waistFluffLftBck, waistFluffLftBckInfo.getNewRotates());
		this.animationDeployer.rotate(waistFluffRtFnt, waistFluffRtFntInfo.getNewRotates());
		this.animationDeployer.rotate(waistFluffRtMid, waistFluffRtMidInfo.getNewRotates());
		this.animationDeployer.rotate(waistFluffRtBck, waistFluffRtBckInfo.getNewRotates());

		this.animationDeployer.rotate(legLft, legLftInfo.getNewRotates());
		this.animationDeployer.rotate(legRt, legRtInfo.getNewRotates());

		this.animationDeployer.rotate(shlderFluffLftFnt, shlderFluffLftFntInfo.getNewRotates());
		this.animationDeployer.rotate(shlderFluffLftBck, shlderFluffLftBckInfo.getNewRotates());
		this.animationDeployer.rotate(shlderFluffRtFnt, shlderFluffRtFntInfo.getNewRotates());
		this.animationDeployer.rotate(shlderFluffRtBck, shlderFluffRtBckInfo.getNewRotates());

		this.animationDeployer.rotate(neckJoint, neckJointInfo.getNewRotates());
		this.animationDeployer.rotate(neck, neckInfo.getNewRotates());
		this.animationDeployer.rotate(headJoint, headJointInfo.getNewRotates());
		this.animationDeployer.rotate(head, headInfo.getNewRotates());

		this.animationDeployer.rotate(cheekTuffLftJoint, cheekTuffLftJointInfo.getNewRotates());
		this.animationDeployer.rotate(cheekTuffLft, cheekTuffLftInfo.getNewRotates());
		this.animationDeployer.rotate(cheekTuffRtJoint, cheekTuffRtJointInfo.getNewRotates());
		this.animationDeployer.rotate(cheekTuffRt, cheekTuffRtInfo.getNewRotates());

		this.animationDeployer.rotate(earLftJoint, earLftJointInfo.getNewRotates());
		this.animationDeployer.rotate(earLft, earLftInfo.getNewRotates());
		this.animationDeployer.rotate(earRtJoint, earRtJointInfo.getNewRotates());
		this.animationDeployer.rotate(earRt, earRtInfo.getNewRotates());

		this.animationDeployer.rotate(earLftTuffJoint, earLftTuffJointInfo.getNewRotates());
		this.animationDeployer.rotate(earLftTuff, earLftTuffInfo.getNewRotates());
		this.animationDeployer.rotate(earLftTuffTipJoint, earLftTuffTipJointInfo.getNewRotates());
		this.animationDeployer.rotate(earLftTuffTip, earLftTuffTipInfo.getNewRotates());

		this.animationDeployer.rotate(earRtTuffJoint, earRtTuffJointInfo.getNewRotates());
		this.animationDeployer.rotate(earRtTuff, earRtTuffInfo.getNewRotates());
		this.animationDeployer.rotate(earRtTuffTipJoint, earRtTuffTipJointInfo.getNewRotates());
		this.animationDeployer.rotate(earRtTuffTip, earRtTuffTipInfo.getNewRotates());

		this.animationDeployer.rotate(armLftBaseJoint, armLftBaseJointInfo.getNewRotates());
		this.animationDeployer.rotate(armLftBase, armLftBaseInfo.getNewRotates());
		this.animationDeployer.rotate(armLftEndJoint, armLftEndJointInfo.getNewRotates());
		this.animationDeployer.rotate(armLftEnd, armLftEndInfo.getNewRotates());

		this.animationDeployer.rotate(armRtBaseJoint, armRtBaseJointInfo.getNewRotates());
		this.animationDeployer.rotate(armRtBase, armRtBaseInfo.getNewRotates());
		this.animationDeployer.rotate(armRtEndJoint, armRtEndJointInfo.getNewRotates());
		this.animationDeployer.rotate(armRtEnd, armRtEndInfo.getNewRotates());

		this.animationDeployer.rotate(wandJoint, wandJointInfo.getNewRotates());
		this.animationDeployer.rotate(wand, wandInfo.getNewRotates());

		this.animationDeployer.rotate(tailStickJoint, tailStickJointInfo.getNewRotates());
		this.animationDeployer.rotate(tailStick, tailStickInfo.getNewRotates());
		
		for(int i = 0; i < tail.length; i++)
		{
			this.animationDeployer.rotate(tail[i][0], tailInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(tail[i][1], tailInfo[i][1].getNewRotates());
		}

		this.animationDeployer.applyChanges();
	}
}