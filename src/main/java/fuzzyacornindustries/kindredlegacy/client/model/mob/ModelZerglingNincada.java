package fuzzyacornindustries.kindredlegacy.client.model.mob;

import fuzzyacornindustries.kindredlegacy.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.animation.JointAnimation;
import fuzzyacornindustries.kindredlegacy.animation.PartAnimate;
import fuzzyacornindustries.kindredlegacy.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.entity.mob.IMobMotionTracker;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityZerglingNincada;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelZerglingNincada extends ModelBase 
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public final float RUN_FREQUENCY = 1.2F;
	public final float WALK_FREQUENCY = 0.3F;

	public ModelRenderer bodyJoint;
	public ModelRenderer body;
	PartInfo bodyInfo;

	public ModelRenderer bodySpikeFnt;
	public ModelRenderer bodySpikeMid;
	public ModelRenderer bodySpikeBck;

	public ModelRenderer legFntRtJoint;
	public ModelRenderer legFntLftJoint;
	PartInfo legFntRtJointInfo;
	PartInfo legFntLftJointInfo;

	public ModelRenderer legFntRt[] = new ModelRenderer[3]; // 0-legFntRt, 1-legFntRtForeleg, 2-legFntRtWrist
	public ModelRenderer legFntLft[] = new ModelRenderer[3]; // 0-legFntLft, 1-legFntLftForeleg, 2-legFntLftWrist
	public PartInfo legFntRtInfo[] = new PartInfo[legFntRt.length];
	public PartInfo legFntLftInfo[] = new PartInfo[legFntLft.length];

	public ModelRenderer legBckRtJoint;
	public ModelRenderer legBckLftJoint;
	PartInfo legBckRtJointInfo;
	PartInfo legBckLftJointInfo;

	public ModelRenderer legBckRt[] = new ModelRenderer[2]; // 0-legBckRt, 1-legBckRtHind
	public ModelRenderer legBckLft[] = new ModelRenderer[2]; // 0-legBckLft, 1-legBckLftHind
	public PartInfo legBckRtInfo[] = new PartInfo[legFntRt.length];
	public PartInfo legBckLftInfo[] = new PartInfo[legBckLft.length];

	public ModelRenderer legFntRtSpike;
	public ModelRenderer legFntLftSpike;
	public ModelRenderer legBckRtSpike;
	public ModelRenderer legBckLftSpike;

	public ModelRenderer neckJoint;
	public ModelRenderer neck;
	PartInfo neckJointInfo;
	PartInfo neckInfo;

	public ModelRenderer headJoint;
	public ModelRenderer head;
	public ModelRenderer headFace;
	PartInfo headJointInfo;
	PartInfo headInfo;

	public ModelRenderer headHornLft;
	public ModelRenderer headHornLftTip;
	public ModelRenderer headHornRt;
	public ModelRenderer headHornRtTip;

	public ModelRenderer backArmLft;
	public ModelRenderer backArmLftForearm;
	public ModelRenderer backArmLftClawTop;
	public ModelRenderer backArmLftClawMid;
	public ModelRenderer backArmLftClawBot;
	PartInfo backArmLftInfo;
	PartInfo backArmLftForearmInfo;

	public ModelRenderer backArmRt;
	public ModelRenderer backArmRtForearm;
	public ModelRenderer backArmRtClawTop;
	public ModelRenderer backArmRtClawMid;
	public ModelRenderer backArmRtClawBot;
	PartInfo backArmRtInfo;
	PartInfo backArmRtForearmInfo;

	public ModelRenderer wingLowerRtJoint;
	public ModelRenderer wingLowerRtBase;
	public ModelRenderer wingLowerRtFlap;
	PartInfo wingLowerRtJointInfo;

	public ModelRenderer wingLowerLftJoint;
	public ModelRenderer wingLowerLftBase;
	public ModelRenderer wingLowerLftFlap;
	PartInfo wingLowerLftJointInfo;

	public ModelRenderer wingUpperRtJoint;
	public ModelRenderer wingUpperRtBase;
	public ModelRenderer wingUpperRtEnd;
	public ModelRenderer wingUpperRtFlap;
	public ModelRenderer wingUpperRtClaw;
	PartInfo wingUpperRtJointInfo;

	public ModelRenderer wingUpperLftJoint;
	public ModelRenderer wingUpperLftBase;
	public ModelRenderer wingUpperLftEnd;
	public ModelRenderer wingUpperLftFlap;
	public ModelRenderer wingUpperLftClaw;
	PartInfo wingUpperLftJointInfo;

	public ModelZerglingNincada() 
	{
		this.textureWidth = 64;
		this.textureHeight = 32;

		this.bodyJoint = new ModelRenderer(this, 42, 21);
		this.bodyJoint.setRotationPoint(0.0F, 19.0F, 0.0F);
		this.bodyJoint.addBox(-2.0F, -2.0F, -3.5F, 0, 0, 0, 0.0F);
		this.body = new ModelRenderer(this, 42, 21);
		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.addBox(-2.0F, -2.0F, -3.5F, 4, 4, 7, 0.0F);
		this.bodyInfo = new PartInfo(this.body);

		this.bodySpikeFnt = new ModelRenderer(this, 58, 18);
		this.bodySpikeFnt.setRotationPoint(0.0F, -2.0F, -2.5F);
		this.bodySpikeFnt.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 2, 0.0F);
		this.setRotateAngle(bodySpikeFnt, 0.5235987755982988F, -0.0F, 0.0F);
		this.bodySpikeMid = new ModelRenderer(this, 58, 21);
		this.bodySpikeMid.setRotationPoint(0.0F, -2.0F, -0.5F);
		this.bodySpikeMid.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 2, 0.0F);
		this.setRotateAngle(bodySpikeMid, 0.5235987755982988F, -0.0F, 0.0F);
		this.bodySpikeBck = new ModelRenderer(this, 58, 24);
		this.bodySpikeBck.setRotationPoint(0.0F, -2.0F, 1.5F);
		this.bodySpikeBck.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 2, 0.0F);
		this.setRotateAngle(bodySpikeBck, 0.5235987755982988F, -0.0F, 0.0F);

		int legPartNumber = 0;
		this.legFntRtJoint = new ModelRenderer(this, 60, 0);
		this.legFntRtJoint.setRotationPoint(-2.0F, 0.0F, -2.0F);
		this.legFntRtJoint.addBox(-0.5F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legFntRtJoint, 0.2617993877991494F, 0.0F, 0.0F);
		this.legFntRtJointInfo = new PartInfo(this.legFntRtJoint);
		this.legFntRt[legPartNumber] = new ModelRenderer(this, 60, 0); // legFntRt
		this.legFntRt[legPartNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legFntRt[legPartNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F);
		this.legFntRtInfo[legPartNumber] = new PartInfo(this.legFntRt[legPartNumber]);
		legPartNumber++;
		this.legFntRt[legPartNumber] = new ModelRenderer(this, 60, 3); // legFntRtForeleg
		this.legFntRt[legPartNumber].setRotationPoint(0.0F, 1.5F, 0.0F);
		this.legFntRt[legPartNumber].addBox(-0.51F, -0.3F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(legFntRt[legPartNumber], -0.5235987755982988F, 0.0F, 0.0F);
		this.legFntRtInfo[legPartNumber] = new PartInfo(this.legFntRt[legPartNumber]);
		legPartNumber++;
		this.legFntRt[legPartNumber] = new ModelRenderer(this, 58, 8); // legFntRtWrist
		this.legFntRt[legPartNumber].setRotationPoint(0.0F, 2.6F, 0.0F);
		this.legFntRt[legPartNumber].addBox(-0.5F, -0.3F, -0.3F, 1, 1, 2, 0.0F);
		this.legFntRtInfo[legPartNumber] = new PartInfo(this.legFntRt[legPartNumber]);

		this.legFntLftJoint = new ModelRenderer(this, 55, 0);
		this.legFntLftJoint.setRotationPoint(2.0F, 0.0F, -2.0F);
		this.legFntLftJoint.addBox(-0.5F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legFntLftJoint, 0.2617993877991494F, 0.0F, 0.0F);
		this.legFntLftJointInfo = new PartInfo(this.legFntLftJoint);
		legPartNumber = 0;;
		this.legFntLft[legPartNumber] = new ModelRenderer(this, 55, 0); // legFntLft
		this.legFntLft[legPartNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legFntLft[legPartNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F);
		this.legFntLftInfo[legPartNumber] = new PartInfo(this.legFntLft[legPartNumber]);
		legPartNumber++;
		this.legFntLft[legPartNumber] = new ModelRenderer(this, 55, 3); // legFntLftForeleg
		this.legFntLft[legPartNumber].setRotationPoint(0.0F, 1.5F, 0.0F);
		this.legFntLft[legPartNumber].addBox(-0.51F, -0.3F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(legFntLft[legPartNumber], -0.5235987755982988F, 0.0F, 0.0F);
		this.legFntLftInfo[legPartNumber] = new PartInfo(this.legFntLft[legPartNumber]);
		legPartNumber++;
		this.legFntLft[legPartNumber] = new ModelRenderer(this, 54, 7); // legFntLftWrist
		this.legFntLft[legPartNumber].setRotationPoint(0.0F, 2.6F, 0.0F);
		this.legFntLft[legPartNumber].addBox(-0.5F, -0.3F, -0.3F, 1, 1, 2, 0.0F);
		this.legFntLftInfo[legPartNumber] = new PartInfo(this.legFntLft[legPartNumber]);

		this.legBckRtJoint = new ModelRenderer(this, 48, 0);
		this.legBckRtJoint.setRotationPoint(-2.0F, 0.0F, 2.2F);
		this.legBckRtJoint.addBox(-0.5F, -0.5F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legBckRtJoint, -0.2617993877991494F, 0.0F, 0.0F);
		this.legBckRtJointInfo = new PartInfo(this.legBckRtJoint);
		legPartNumber = 0;
		this.legBckRt[legPartNumber] = new ModelRenderer(this, 48, 0); // legBckRt
		this.legBckRt[legPartNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckRt[legPartNumber].addBox(-0.5F, -0.5F, -1.0F, 1, 3, 2, 0.0F);
		this.legBckRtInfo[legPartNumber] = new PartInfo(this.legBckRt[legPartNumber]);
		legPartNumber++;
		this.legBckRt[legPartNumber] = new ModelRenderer(this, 50, 5); // legBckRtHind
		this.legBckRt[legPartNumber].setRotationPoint(0.0F, 2.3F, 0.8F);
		this.legBckRt[legPartNumber].addBox(-0.51F, -0.3F, -0.3F, 1, 3, 1, 0.0F);
		this.legBckRtInfo[legPartNumber] = new PartInfo(this.legBckRt[legPartNumber]);

		this.legBckLftJoint = new ModelRenderer(this, 44, 4);
		this.legBckLftJoint.setRotationPoint(2.0F, 0.0F, 2.2F);
		this.legBckLftJoint.addBox(-0.5F, -0.5F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legBckLftJoint, -0.2617993877991494F, 0.0F, 0.0F);
		this.legBckLftJointInfo = new PartInfo(this.legBckLftJoint);
		legPartNumber = 0;
		this.legBckLft[legPartNumber] = new ModelRenderer(this, 44, 4); // legBckLft
		this.legBckLft[legPartNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckLft[legPartNumber].addBox(-0.5F, -0.5F, -1.0F, 1, 3, 2, 0.0F);
		this.legBckLftInfo[legPartNumber] = new PartInfo(this.legBckLft[legPartNumber]);
		legPartNumber++;
		this.legBckLft[legPartNumber] = new ModelRenderer(this, 45, 9); // legBckLftHind
		this.legBckLft[legPartNumber].setRotationPoint(0.0F, 2.3F, 0.8F);
		this.legBckLft[legPartNumber].addBox(-0.51F, -0.3F, -0.3F, 1, 3, 1, 0.0F);
		this.legBckLftInfo[legPartNumber] = new PartInfo(this.legBckLft[legPartNumber]);

		this.legFntRtSpike = new ModelRenderer(this, 60, 12);
		this.legFntRtSpike.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legFntRtSpike.addBox(-0.3F, -3.8F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(legFntRtSpike, -0.7853981633974483F, 0.0F, 0.0F);
		this.legFntLftSpike = new ModelRenderer(this, 55, 11);
		this.legFntLftSpike.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legFntLftSpike.addBox(-0.7F, -3.8F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(legFntLftSpike, -0.7853981633974483F, 0.0F, 0.0F);
		this.legBckRtSpike = new ModelRenderer(this, 50, 9);
		this.legBckRtSpike.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckRtSpike.addBox(-0.3F, -3.2F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(legBckRtSpike, -0.4363323129985824F, 0.0F, 0.0F);
		this.legBckLftSpike = new ModelRenderer(this, 44, 0);
		this.legBckLftSpike.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckLftSpike.addBox(-0.7F, -3.2F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(legBckLftSpike, -0.4363323129985824F, 0.0F, 0.0F);

		this.neckJoint = new ModelRenderer(this, 32, 27);
		this.neckJoint.setRotationPoint(0.0F, 0.0F, -3.0F);
		this.neckJoint.addBox(-1.0F, -1.0F, -2.5F, 0, 0, 0, 0.0F);
		this.neckJointInfo = new PartInfo(this.neckJoint);
		this.neck = new ModelRenderer(this, 32, 27);
		this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.neck.addBox(-1.0F, -1.0F, -2.5F, 2, 2, 3, 0.0F);
		this.neckInfo = new PartInfo(this.neck);

		this.headJoint = new ModelRenderer(this, 0, 7);
		this.headJoint.setRotationPoint(0.0F, 0.0F, -2.0F);
		this.headJoint.addBox(-1.5F, -1.8F, -2.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(headJoint, 0.17453292519943295F, 0.0F, 0.0F);
		this.headJointInfo = new PartInfo(this.headJoint);
		this.head = new ModelRenderer(this, 0, 7);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-1.5F, -1.8F, -2.5F, 3, 3, 3, 0.0F);
		this.headInfo = new PartInfo(this.head);
		this.headFace = new ModelRenderer(this, 0, 0);
		this.headFace.setRotationPoint(0.0F, 0.0F, -2.0F);
		this.headFace.addBox(-1.51F, -1.75F, -1.0F, 3, 3, 1, 0.0F);
		this.setRotateAngle(headFace, -0.17453292519943295F, 0.0F, 0.0F);

		this.headHornLft = new ModelRenderer(this, 10, 7);
		this.headHornLft.setRotationPoint(1.0F, 0.0F, -1.0F);
		this.headHornLft.addBox(0.0F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		this.setRotateAngle(headHornLft, -0.17453292519943295F, 0.4363323129985824F, 0.0F);
		this.headHornLftTip = new ModelRenderer(this, 5, 1);
		this.headHornLftTip.setRotationPoint(1.6F, 0.0F, 0.0F);
		this.headHornLftTip.addBox(-0.5F, -0.5F, -3.5F, 1, 1, 4, 0.0F);
		this.setRotateAngle(headHornLftTip, -0.17453292519943295F, -0.2617993877991494F, 0.0F);
		this.headHornRt = new ModelRenderer(this, 15, 6);
		this.headHornRt.setRotationPoint(-1.0F, 0.0F, -1.0F);
		this.headHornRt.addBox(-2.0F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		this.setRotateAngle(headHornRt, -0.17453292519943295F, -0.4363323129985824F, 0.0F);
		this.headHornRtTip = new ModelRenderer(this, 11, 0);
		this.headHornRtTip.setRotationPoint(-1.6F, 0.0F, 0.0F);
		this.headHornRtTip.addBox(-0.5F, -0.5F, -3.5F, 1, 1, 4, 0.0F);
		this.setRotateAngle(headHornRtTip, -0.17453292519943295F, 0.2617993877991494F, 0.0F);

		this.backArmLft = new ModelRenderer(this, 44, 22);
		this.backArmLft.setRotationPoint(1.0F, -1.5F, -2.5F);
		this.backArmLft.addBox(-0.5F, -3.5F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(backArmLft, 0.5235987755982988F, 0.0F, 0.0F);
		this.backArmLftInfo = new PartInfo(this.backArmLft);
		this.backArmLftForearm = new ModelRenderer(this, 44, 16);
		this.backArmLftForearm.setRotationPoint(0.0F, -3.5F, 0.0F);
		this.backArmLftForearm.addBox(-0.51F, -4.7F, -0.5F, 1, 5, 1, 0.0F);
		this.setRotateAngle(backArmLftForearm, 0.5235987755982988F, 0.0F, 0.0F);
		this.backArmLftForearmInfo = new PartInfo(this.backArmLftForearm);

		this.backArmLftClawTop = new ModelRenderer(this, 49, 15);
		this.backArmLftClawTop.setRotationPoint(0.0F, -4.3F, 0.0F);
		this.backArmLftClawTop.addBox(-0.5F, -0.5F, -3.7F, 1, 1, 4, 0.0F);
		this.setRotateAngle(backArmLftClawTop, -0.3490658503988659F, 0.0F, 0.0F);
		this.backArmLftClawMid = new ModelRenderer(this, 47, 13);
		this.backArmLftClawMid.setRotationPoint(0.0F, -2.7F, 0.0F);
		this.backArmLftClawMid.addBox(-0.5F, -0.5F, -2.2F, 1, 1, 2, 0.0F);
		this.setRotateAngle(backArmLftClawMid, 0.08726646259971647F, -0.0F, 0.0F);
		this.backArmLftClawBot = new ModelRenderer(this, 49, 16);
		this.backArmLftClawBot.setRotationPoint(0.0F, -1.3F, 0.0F);
		this.backArmLftClawBot.addBox(-0.5F, -0.5F, -1.2F, 1, 1, 1, 0.0F);
		this.setRotateAngle(backArmLftClawBot, 0.3490658503988659F, -0.0F, 0.0F);

		this.backArmRt = new ModelRenderer(this, 39, 22);
		this.backArmRt.setRotationPoint(-1.0F, -1.5F, -2.5F);
		this.backArmRt.addBox(-0.5F, -3.5F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(backArmRt, 0.5235987755982988F, 0.0F, 0.0F);
		this.backArmRtInfo = new PartInfo(this.backArmRt);
		this.backArmRtForearm = new ModelRenderer(this, 39, 16);
		this.backArmRtForearm.setRotationPoint(0.0F, -3.5F, 0.0F);
		this.backArmRtForearm.addBox(-0.49F, -4.7F, -0.5F, 1, 5, 1, 0.0F);
		this.setRotateAngle(backArmRtForearm, 0.5235987755982988F, 0.0F, 0.0F);
		this.backArmRtForearmInfo = new PartInfo(this.backArmRtForearm);

		this.backArmRtClawTop = new ModelRenderer(this, 39, 10);
		this.backArmRtClawTop.setRotationPoint(0.0F, -4.3F, 0.0F);
		this.backArmRtClawTop.addBox(-0.5F, -0.5F, -3.7F, 1, 1, 4, 0.0F);
		this.setRotateAngle(backArmRtClawTop, -0.3490658503988659F, -0.0F, 0.0F);
		this.backArmRtClawMid = new ModelRenderer(this, 39, 7);
		this.backArmRtClawMid.setRotationPoint(0.0F, -2.7F, 0.0F);
		this.backArmRtClawMid.addBox(-0.5F, -0.5F, -2.2F, 1, 1, 2, 0.0F);
		this.setRotateAngle(backArmRtClawMid, 0.08726646259971647F, -0.0F, 0.0F);
		this.backArmRtClawBot = new ModelRenderer(this, 39, 11);
		this.backArmRtClawBot.setRotationPoint(0.0F, -1.3F, 0.0F);
		this.backArmRtClawBot.addBox(-0.5F, -0.5F, -1.2F, 1, 1, 1, 0.0F);
		this.setRotateAngle(backArmRtClawBot, 0.3490658503988659F, 0.0F, 0.0F);

		this.wingLowerRtJoint = new ModelRenderer(this, 31, 22);
		this.wingLowerRtJoint.setRotationPoint(-1.3F, -1.0F, 3.0F);
		this.wingLowerRtJoint.addBox(-0.5F, -5.3F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(wingLowerRtJoint, -1.6580627893946132F, 0.0F, 0.0F);
		this.wingLowerRtJointInfo = new PartInfo(this.wingLowerRtJoint);
		this.wingLowerRtBase = new ModelRenderer(this, 31, 22);
		this.wingLowerRtBase.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.wingLowerRtBase.addBox(-0.5F, -5.3F, -0.5F, 1, 5, 1, 0.0F);
		this.wingLowerRtFlap = new ModelRenderer(this, 31, 0);
		this.wingLowerRtFlap.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.wingLowerRtFlap.addBox(0.0F, -8.5F, -0.9F, 1, 8, 3, 0.0F);
		this.setRotateAngle(wingLowerRtFlap, -0.08726646259971647F, 0.0F, 0.0F);

		this.wingLowerLftJoint = new ModelRenderer(this, 39, 0);
		this.wingLowerLftJoint.setRotationPoint(1.3F, -1.0F, 3.0F);
		this.wingLowerLftJoint.addBox(-0.5F, -5.3F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(wingLowerLftJoint, -1.6580627893946132F, 0.0F, 0.0F);
		this.wingLowerLftJointInfo = new PartInfo(this.wingLowerLftJoint);
		this.wingLowerLftBase = new ModelRenderer(this, 39, 0);
		this.wingLowerLftBase.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.wingLowerLftBase.addBox(-0.5F, -5.3F, -0.5F, 1, 5, 1, 0.0F);
		this.wingLowerLftFlap = new ModelRenderer(this, 31, 11);
		this.wingLowerLftFlap.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.wingLowerLftFlap.addBox(-1.0F, -8.5F, -0.9F, 1, 8, 3, 0.0F);
		this.setRotateAngle(wingLowerLftFlap, -0.08726646259971647F, 0.0F, 0.0F);

		this.wingUpperRtJoint = new ModelRenderer(this, 27, 0);
		this.wingUpperRtJoint.setRotationPoint(-1.3F, -1.5F, 0.0F);
		this.wingUpperRtJoint.addBox(-0.5F, -5.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(wingUpperRtJoint, -0.7853981633974483F, 0.0F, 0.0F);
		this.wingUpperRtJointInfo = new PartInfo(this.wingUpperRtJoint);
		this.wingUpperRtBase = new ModelRenderer(this, 27, 0);
		this.wingUpperRtBase.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.wingUpperRtBase.addBox(-0.5F, -5.5F, -0.5F, 1, 5, 1, 0.0F);
		this.wingUpperRtEnd = new ModelRenderer(this, 27, 6);
		this.wingUpperRtEnd.setRotationPoint(0.0F, -5.0F, 0.0F);
		this.wingUpperRtEnd.addBox(-0.49F, -4.9F, -0.5F, 1, 5, 1, 0.0F);
		this.setRotateAngle(wingUpperRtEnd, -0.3490658503988659F, 0.0F, 0.0F);
		this.wingUpperRtFlap = new ModelRenderer(this, 15, 16);
		this.wingUpperRtFlap.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.wingUpperRtFlap.addBox(-0.1F, -13.5F, -2.0F, 1, 13, 3, 0.0F);
		this.setRotateAngle(wingUpperRtFlap, -0.3490658503988659F, 0.0F, 0.0F);
		this.wingUpperRtClaw = new ModelRenderer(this, 27, 12);
		this.wingUpperRtClaw.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.wingUpperRtClaw.addBox(-0.51F, -2.2F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(wingUpperRtClaw, 0.6981317007977318F, 0.0F, 0.0F);

		this.wingUpperLftJoint = new ModelRenderer(this, 22, 0);
		this.wingUpperLftJoint.setRotationPoint(1.3F, -1.5F, 0.0F);
		this.wingUpperLftJoint.addBox(-0.5F, -5.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(wingUpperLftJoint, -0.7853981633974483F, 0.0F, 0.0F);
		this.wingUpperLftJointInfo = new PartInfo(this.wingUpperLftJoint);
		this.wingUpperLftBase = new ModelRenderer(this, 22, 0);
		this.wingUpperLftBase.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.wingUpperLftBase.addBox(-0.5F, -5.5F, -0.5F, 1, 5, 1, 0.0F);
		this.wingUpperLftEnd = new ModelRenderer(this, 22, 6);
		this.wingUpperLftEnd.setRotationPoint(0.0F, -5.0F, 0.0F);
		this.wingUpperLftEnd.addBox(-0.51F, -4.9F, -0.5F, 1, 5, 1, 0.0F);
		this.setRotateAngle(wingUpperLftEnd, -0.3490658503988659F, 0.0F, 0.0F);
		this.wingUpperLftFlap = new ModelRenderer(this, 23, 16);
		this.wingUpperLftFlap.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.wingUpperLftFlap.addBox(-0.9F, -13.5F, -2.0F, 1, 13, 3, 0.0F);
		this.setRotateAngle(wingUpperLftFlap, -0.3490658503988659F, 0.0F, 0.0F);
		this.wingUpperLftClaw = new ModelRenderer(this, 22, 12);
		this.wingUpperLftClaw.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.wingUpperLftClaw.addBox(-0.49F, -2.2F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(wingUpperLftClaw, 0.6981317007977318F, 0.0F, 0.0F);

		this.bodyJoint.addChild(this.body);

		this.body.addChild(this.backArmLft);
		this.body.addChild(this.backArmRt);
		this.body.addChild(this.bodySpikeBck);
		this.body.addChild(this.bodySpikeFnt);
		this.body.addChild(this.bodySpikeMid);
		this.body.addChild(this.legBckLftJoint);
		this.body.addChild(this.legBckRtJoint);
		this.body.addChild(this.legFntLftJoint);
		this.body.addChild(this.legFntRtJoint);
		this.body.addChild(this.neckJoint);
		this.body.addChild(this.wingLowerLftJoint);
		this.body.addChild(this.wingLowerRtJoint);
		this.body.addChild(this.wingUpperLftJoint);
		this.body.addChild(this.wingUpperRtJoint);

		this.backArmLft.addChild(this.backArmLftForearm);
		this.backArmLftForearm.addChild(this.backArmLftClawBot);
		this.backArmLftForearm.addChild(this.backArmLftClawMid);
		this.backArmLftForearm.addChild(this.backArmLftClawTop);

		this.backArmRt.addChild(this.backArmRtForearm);
		this.backArmRtForearm.addChild(this.backArmRtClawBot);
		this.backArmRtForearm.addChild(this.backArmRtClawMid);
		this.backArmRtForearm.addChild(this.backArmRtClawTop);

		this.neckJoint.addChild(this.neck);
		this.neck.addChild(this.headJoint);

		this.headJoint.addChild(this.head);
		this.head.addChild(this.headFace);
		this.head.addChild(this.headHornLft);
		this.head.addChild(this.headHornRt);

		this.headHornLft.addChild(this.headHornLftTip);
		this.headHornRt.addChild(this.headHornRtTip);

		this.legBckLftJoint.addChild(this.legBckLft[0]);
		this.legBckLft[0].addChild(this.legBckLft[1]);
		this.legBckLftJoint.addChild(this.legBckLftSpike);

		this.legBckRtJoint.addChild(this.legBckRt[0]);
		this.legBckRt[0].addChild(this.legBckRt[1]);
		this.legBckRtJoint.addChild(this.legBckRtSpike);

		this.legFntLftJoint.addChild(this.legFntLft[0]);
		this.legFntLft[0].addChild(this.legFntLft[1]);
		this.legFntLft[1].addChild(this.legFntLft[2]);
		this.legFntLftJoint.addChild(this.legFntLftSpike);

		this.legFntRtJoint.addChild(this.legFntRt[0]);
		this.legFntRt[0].addChild(this.legFntRt[1]);
		this.legFntRt[1].addChild(this.legFntRt[2]);
		this.legFntRtJoint.addChild(this.legFntRtSpike);

		this.wingLowerLftJoint.addChild(this.wingLowerLftBase);
		this.wingLowerLftJoint.addChild(this.wingLowerLftFlap);

		this.wingLowerRtJoint.addChild(this.wingLowerRtBase);
		this.wingLowerRtJoint.addChild(this.wingLowerRtFlap);

		this.wingUpperLftJoint.addChild(this.wingUpperLftBase);
		this.wingUpperLftBase.addChild(this.wingUpperLftEnd);
		this.wingUpperLftBase.addChild(this.wingUpperLftFlap);
		this.wingUpperLftEnd.addChild(this.wingUpperLftClaw);

		this.wingUpperRtJoint.addChild(this.wingUpperRtBase);
		this.wingUpperRtBase.addChild(this.wingUpperRtEnd);
		this.wingUpperRtBase.addChild(this.wingUpperRtFlap);
		this.wingUpperRtEnd.addChild(this.wingUpperRtClaw);

		animationDeployer = new AnimationDeployer(this);
	}

	@Override
	public void render(Entity entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize) 
	{ 
		animate(entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);

		this.bodyJoint.render(modelSize);
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

		animateBody((EntityZerglingNincada)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity);
		animateHead((EntityZerglingNincada)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity);
		animateLegsAndBackArms((EntityZerglingNincada)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity);
		animateWings((EntityZerglingNincada)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity);

		deployAnimations();
	}

	public void resetPartInfos()
	{
		bodyInfo.resetNewAngles();
		bodyInfo.resetNewPnt();

		neckJointInfo.resetNewAngles();
		neckInfo.resetNewAngles();

		headInfo.resetNewAngles();
		headJointInfo.resetNewAngles();

		backArmLftInfo.resetNewAngles();
		backArmLftForearmInfo.resetNewAngles();
		backArmRtInfo.resetNewAngles();
		backArmRtForearmInfo.resetNewAngles();

		for(int i = 0; i < legFntRt.length; i++)
		{
			legFntRtInfo[i].resetNewAngles();
			legFntLftInfo[i].resetNewAngles();
		}

		for(int i = 0; i < legBckRt.length; i++)
		{
			legBckRtInfo[i].resetNewAngles();
			legBckLftInfo[i].resetNewAngles();
		}

		wingLowerLftJointInfo.resetNewAngles();
		wingLowerRtJointInfo.resetNewAngles();
		wingUpperLftJointInfo.resetNewAngles();
		wingUpperRtJointInfo.resetNewAngles();
	}

	public void animateBody(EntityZerglingNincada entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity)
	{
		float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
		float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);

		float bodyWalkAngle = (float)Math.toRadians(28);
		float bodyRunAngle = (float)Math.toRadians(44);

		float maximumChangeY = -3.0F;
		//float runCycleIntervalModified = (RUN_FREQUENCY * distanceMoved * 0.5F % (2 * PI))/(2 * PI);
		float runCyclePointChange = (MathHelper.cos(2F * runCycleInterval * PI) * maximumChangeY + maximumChangeY) * horzVelocity;

		bodyInfo.setNewPointY(bodyInfo.getNewPointY() + runCyclePointChange * horzVelocity * (1F - Math.abs(verticalVelocity)));
		
		float walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI + PI/2) * bodyWalkAngle * (1 - horzVelocity);
		float runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI + PI/2) * bodyRunAngle * horzVelocity;

		float bodyJumpAngle = (float)Math.toRadians(-65);
		float verticalVelocityChangeAngle = (float)Math.toRadians(10);

		float newVerticalVelocity = 1.5F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;

		bodyInfo.setNewRotateX(bodyInfo.getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity)) 
				+ bodyJumpAngle * newVerticalVelocity);
	}

	public void animateHead(EntityZerglingNincada entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, neckJointInfo);

		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockNeckBobble();

		float idleAmplitudeX = 0.1F;

		float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
		float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);

		float walkAngle = (float)Math.toRadians(4);
		float runAngle = (float)Math.toRadians(8);

		float walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI) * walkAngle * (1 - horzVelocity)
				+ walkAngle * (1 - horzVelocity);
		float runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI) * runAngle * horzVelocity
				+ runAngle * horzVelocity;

		// + (float)Math.toRadians(45)
		float movementAmplitudeX = (float)Math.toRadians(60);

		neckInfo.setNewRotateX(neckInfo.getNewRotateX() + movementAmplitudeX * (walkCycleAngleChange + runCycleAngleChange) * horzVelocity);

		JointAnimation.reverseJointRotatesChange(neckInfo, headJointInfo);

		PartAnimate.headAnimateInfoOnly(headInfo, yawHeadOffsetDifference, pitchRotation);
	}

	public void animateLegsAndBackArms(EntityZerglingNincada entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity)
	{
		float amplitude = 0.8F;

		float legFntAmplitude[] = {
				(float)Math.toRadians(-30),
				(float)Math.toRadians(-30),
				(float)Math.toRadians(-60)};

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
		float sneak = 0.35F;

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

		/* ******* Legs ******** */
		JointAnimation.reverseJointRotatesChange(bodyInfo, legFntRtJointInfo);
		JointAnimation.reverseJointRotatesChange(bodyInfo, legFntLftJointInfo);
		JointAnimation.reverseJointRotatesChange(bodyInfo, legBckRtJointInfo);
		JointAnimation.reverseJointRotatesChange(bodyInfo, legBckLftJointInfo);

		//( 1 - (Math.pow(Math.E, -0.4F * (float)(i + 1))));

		float newVerticalVelocity = 1.5F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;

		float fallingModifier = 0;

		if(newVerticalVelocity < 0)
			fallingModifier = newVerticalVelocity;
		else
			fallingModifier = newVerticalVelocity * 0.5F;
		
		float legFntChanges[][] = new float[2][3]; // 0 = Rt; 1 = Lft

		legFntChanges[0][0] = PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legFntRtModifier, horzVelocity, RUN_FREQUENCY, legFntAmplitude[0]) * (1F - Math.abs(newVerticalVelocity));
		legFntChanges[1][0] = PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, RUN_FREQUENCY, legFntAmplitude[0]) * (1F - Math.abs(newVerticalVelocity));

		legFntChanges[0][1] = PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legFntRtModifier, horzVelocity, RUN_FREQUENCY, legFntAmplitude[1]) * (1F - Math.abs(newVerticalVelocity));
		legFntChanges[1][1] = PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, RUN_FREQUENCY, legFntAmplitude[1]) * (1F - Math.abs(newVerticalVelocity));

		legFntChanges[0][2] = PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legFntRtModifier, horzVelocity, RUN_FREQUENCY, legFntAmplitude[2]) * (1F - Math.abs(newVerticalVelocity));
		legFntChanges[1][2] = PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, RUN_FREQUENCY, legFntAmplitude[2]) * (1F - Math.abs(newVerticalVelocity));

		float fallAmplitudeModifier = 1.2F;
		
		legFntChanges[0][0] -= legFntAmplitude[0] * fallAmplitudeModifier * fallingModifier;
		legFntChanges[1][0] -= legFntAmplitude[0] * fallAmplitudeModifier * fallingModifier;

		legFntChanges[0][1] -= legFntAmplitude[1] * fallAmplitudeModifier * fallingModifier;
		legFntChanges[1][1] -= legFntAmplitude[1] * fallAmplitudeModifier * fallingModifier;

		legFntChanges[0][2] -= legFntAmplitude[2] * fallAmplitudeModifier * fallingModifier;
		legFntChanges[1][2] -= legFntAmplitude[2] * fallAmplitudeModifier * fallingModifier;

		for(int i = 0; i < legFntChanges.length; i++)
		{
			for(int j = 0; j < legFntChanges[i].length; j++)
			{
				if(legFntChanges[i][j] > 0F)
				{
					legFntChanges[i][j] = legFntChanges[i][j] * (float)Math.pow(Math.E, -0.4F * legFntChanges[i][j]);
				}
			}
		}

		legFntRtInfo[0].setNewRotateX(legFntRtInfo[0].getNewRotateX() + legFntChanges[0][0]);
		legFntLftInfo[0].setNewRotateX(legFntLftInfo[0].getNewRotateX() + legFntChanges[1][0]);
		legBckRtInfo[0].setNewRotateX(legBckRtInfo[0].getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legBckRtModifier, horzVelocity, RUN_FREQUENCY, amplitude));
		legBckLftInfo[0].setNewRotateX(legBckLftInfo[0].getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legBckLftModifier, horzVelocity, RUN_FREQUENCY, amplitude));

		legFntRtInfo[1].setNewRotateX(legFntRtInfo[1].getNewRotateX() + legFntChanges[0][1]);
		legFntLftInfo[1].setNewRotateX(legFntLftInfo[1].getNewRotateX() + legFntChanges[1][1]);

		legFntRtInfo[2].setNewRotateX(legFntRtInfo[2].getNewRotateX() + legFntChanges[0][2]);
		legFntLftInfo[2].setNewRotateX(legFntLftInfo[2].getNewRotateX() + legFntChanges[1][2]);

		/* ******* Back Arms ******** */
		JointAnimation.reverseJointRotatesChange(bodyInfo, backArmLftInfo);
		JointAnimation.reverseJointRotatesChange(bodyInfo, backArmRtInfo);

		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockBackArms();

		float idleAmplitudeX = 0.3F;
		float jittleAmplitudeX = 0.1F;

		float movementAmplitudeX = 0.2F;

		// + (float)Math.toRadians(45)
		float angleChangeX = 0;

		if(currentIdleAnimationClock.getPhaseDurationCoveredX(0) < 1F)
		{
			angleChangeX += -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI) * idleAmplitudeX;

			angleChangeX += -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * 5 * PI) * jittleAmplitudeX;
		}
		else
		{
			angleChangeX += -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(1) * PI + PI) * idleAmplitudeX;
		}

		backArmLftInfo.setNewRotateX(backArmLftInfo.getNewRotateX() - PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legFntRtModifier, horzVelocity, RUN_FREQUENCY, movementAmplitudeX));
		backArmLftForearmInfo.setNewRotateX(backArmLftForearmInfo.getNewRotateX() - PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legFntRtModifier, horzVelocity, RUN_FREQUENCY, movementAmplitudeX) + angleChangeX);

		angleChangeX = 0;

		if(currentIdleAnimationClock.getPhaseDurationCoveredX(0) < 1F)
		{
			angleChangeX += -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI - PI/4) * idleAmplitudeX;

			angleChangeX += -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * 5 * PI - PI/4) * jittleAmplitudeX;
		}
		else
		{
			angleChangeX += -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(1) * PI + PI - PI/4) * idleAmplitudeX;
		}

		backArmRtInfo.setNewRotateX(backArmRtInfo.getNewRotateX() - PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, RUN_FREQUENCY, movementAmplitudeX));
		backArmRtForearmInfo.setNewRotateX(backArmRtForearmInfo.getNewRotateX() - PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, RUN_FREQUENCY, movementAmplitudeX) + angleChangeX);

		//System.out.println( "Test Model Parameters in Zergling Model class" );
		//System.out.println( "Velocity = " + Float.toString( velocity ) );
	}

	public void animateWings(EntityZerglingNincada entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity)
	{
		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockWings();

		float idleAmplitudeY = 0.2F;
		float idleAmplitudeZ = 0.2F;

		float moveAmplitudeY = (float)Math.toRadians(40);
		float moveAmplitudeZ = (float)Math.toRadians(25);

		float angleChangeX = 0;
		float angleChangeY = 0;
		float angleChangeZ = 0;

		angleChangeX += 0;
		angleChangeY += (MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * 2 * PI + PI) * idleAmplitudeY + idleAmplitudeY) * (1F - horzVelocity);
		angleChangeZ += (MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * 2 * PI + PI) * idleAmplitudeZ + idleAmplitudeZ) * (1F - horzVelocity);

		angleChangeY += (moveAmplitudeY + moveAmplitudeY * MathHelper.cos(RUN_FREQUENCY * distanceMoved * 0.8F)) * horzVelocity;
		angleChangeZ += (moveAmplitudeZ + moveAmplitudeZ * MathHelper.cos(RUN_FREQUENCY * distanceMoved * 0.8F)) * horzVelocity;
		
		wingUpperLftJointInfo.setNewRotateY(angleChangeY);
		wingUpperRtJointInfo.setNewRotateY(-angleChangeY);
		wingUpperLftJointInfo.setNewRotateZ(angleChangeZ);
		wingUpperRtJointInfo.setNewRotateZ(-angleChangeZ);

		angleChangeY = angleChangeY * 0.5F;

		wingLowerLftJointInfo.setNewRotateY(angleChangeY);
		wingLowerRtJointInfo.setNewRotateY(-angleChangeY);
	}

	public void deployAnimations()
	{
		this.animationDeployer.rotate(body, bodyInfo.getNewRotates());
		this.animationDeployer.move(body, bodyInfo.getNewPnt());

		this.animationDeployer.rotate(neckJoint, neckJointInfo.getNewRotates());
		this.animationDeployer.rotate(neck, neckInfo.getNewRotates());
		this.animationDeployer.rotate(headJoint, headJointInfo.getNewRotates());
		this.animationDeployer.rotate(head, headInfo.getNewRotates());

		this.animationDeployer.rotate(wingUpperLftJoint, wingUpperLftJointInfo.getNewRotates());
		this.animationDeployer.rotate(wingUpperRtJoint, wingUpperRtJointInfo.getNewRotates());
		this.animationDeployer.rotate(wingLowerLftJoint, wingLowerLftJointInfo.getNewRotates());
		this.animationDeployer.rotate(wingLowerRtJoint, wingLowerRtJointInfo.getNewRotates());

		this.animationDeployer.rotate(backArmLft, backArmLftInfo.getNewRotates());
		this.animationDeployer.rotate(backArmLftForearm, backArmLftForearmInfo.getNewRotates());
		this.animationDeployer.rotate(backArmRt, backArmRtInfo.getNewRotates());
		this.animationDeployer.rotate(backArmRtForearm, backArmRtForearmInfo.getNewRotates());

		for(int i = 0; i < legFntRt.length; i++)
		{
			this.animationDeployer.rotate(legFntRt[i], legFntRtInfo[i].getNewRotates());
			this.animationDeployer.rotate(legFntLft[i], legFntLftInfo[i].getNewRotates());
		}

		for(int i = 0; i < legBckRt.length; i++)
		{
			this.animationDeployer.rotate(legBckRt[i], legBckRtInfo[i].getNewRotates());
			this.animationDeployer.rotate(legBckLft[i], legBckLftInfo[i].getNewRotates());
		}

		this.animationDeployer.applyChanges();
	}
}