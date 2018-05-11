package fuzzyacornindustries.kindredlegacy.client.model.mob;

import fuzzyacornindustries.kindredlegacy.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.animation.JointAnimation;
import fuzzyacornindustries.kindredlegacy.animation.PartAnimate;
import fuzzyacornindustries.kindredlegacy.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.entity.mob.IMobMotionTracker;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityArmoredLuxray;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelArmoredLuxray extends ModelBase 
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public final float RUN_FREQUENCY = 0.9F;
	public final float WALK_FREQUENCY = 1.4F;
	public final float SNEAK_VELOCITY = 0.45F;

    public ModelRenderer body;
    public ModelRenderer neckJoint;
    public ModelRenderer neck;
	PartInfo bodyInfo;
	PartInfo neckJointInfo;
	PartInfo neckInfo;
    
    public ModelRenderer legFntRt;
    public ModelRenderer legFntLft;
    public ModelRenderer legBckRt;
    public ModelRenderer legBckLft;
	PartInfo legFntRtInfo;
	PartInfo legFntLftInfo;
	PartInfo legBckRtInfo;
	PartInfo legBckLftInfo;
    
    public ModelRenderer bodyArmorFntFlapRtJoint;
    public ModelRenderer bodyArmorFntFlapRt;
    public ModelRenderer bodyArmorFntFlapLftJoint;
    public ModelRenderer bodyArmorFntFlapLft;
    public ModelRenderer bodyArmorBckFlapRtJoint;
    public ModelRenderer bodyArmorBckFlapRt;
    public ModelRenderer bodyArmorBckFlapLftJoint;
    public ModelRenderer bodyArmorBckFlapLft;
	PartInfo bodyArmorFntFlapRtJointInfo;
	PartInfo bodyArmorFntFlapRtInfo;
	PartInfo bodyArmorFntFlapLftJointInfo;
	PartInfo bodyArmorFntFlapLftInfo;
	PartInfo bodyArmorBckFlapRtJointInfo;
	PartInfo bodyArmorBckFlapRtInfo;
	PartInfo bodyArmorBckFlapLftJointInfo;
	PartInfo bodyArmorBckFlapLftInfo;

	public ModelRenderer backFur[][] = new ModelRenderer[5][2];
	PartInfo backFurInfo[][] = new PartInfo[backFur.length][backFur[0].length];
    
    public ModelRenderer headJoint;
    public ModelRenderer head;
	PartInfo headJointInfo;
	PartInfo headInfo;
    
    public ModelRenderer muzzle;
    public ModelRenderer helmetRtFlap;
    public ModelRenderer helmetLftFlap;

    public ModelRenderer helmetHorn1;
    public ModelRenderer helmetHorn2;
    public ModelRenderer helmetHorn3;
    public ModelRenderer helmetHorn4;
    public ModelRenderer helmetHornTip;
    
    public ModelRenderer cheekFurRtJoint;
    public ModelRenderer cheekFurRt;
    public ModelRenderer cheekFurLftJoint;
    public ModelRenderer cheekFurLft;
	PartInfo cheekFurRtJointInfo;
	PartInfo cheekFurRtInfo;
	PartInfo cheekFurLftJointInfo;
	PartInfo cheekFurLftInfo;
    
    public ModelRenderer earRtJoint;
    public ModelRenderer earRt;
    public ModelRenderer earLftJoint;
    public ModelRenderer earLft;
	PartInfo earRtJointInfo;
	PartInfo earRtInfo;
	PartInfo earLftJointInfo;
	PartInfo earLftInfo;

	public ModelRenderer tail[][] = new ModelRenderer[11][2];
	PartInfo tailInfo[][] = new PartInfo[tail.length][tail[0].length];

    public ModelArmoredLuxray() 
    {
        this.textureWidth = 64;
        this.textureHeight = 64;

		int jointNumber = 0;
		int partNumber = 1;

        this.body = new ModelRenderer(this, 50, 35);
        this.body.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.body.addBox(-2.5F, -2.5F, -1.0F, 5, 5, 2, 0.0F);
        this.body.setTextureOffset(42, 42).addBox(-3.0F, -3.0F, -6.0F, 6, 6, 5, 0.0F); // bodyArmorFnt
        this.body.setTextureOffset(42, 53).addBox(-3.0F, -3.0F, 1.0F, 6, 6, 5, 0.0F); // bodyArmorBck
        bodyInfo = new PartInfo(body);
        this.neckJoint = new ModelRenderer(this, 52, 28);
        this.neckJoint.setRotationPoint(0.0F, -1.5F, -4.0F);
        this.neckJoint.addBox(-1.5F, -4.5F, -1.5F, 0, 0, 0, 0.0F);
        this.setRotateAngle(neckJoint, 0.5235987755982988F, -0.0F, 0.0F);
        neckJointInfo = new PartInfo(neckJoint);
        this.neck = new ModelRenderer(this, 52, 28);
        this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.neck.addBox(-1.5F, -4.5F, -1.5F, 3, 4, 3, 0.0F);
        neckInfo = new PartInfo(neck);

        this.legFntRt = new ModelRenderer(this, 16, 42);
        this.legFntRt.setRotationPoint(-1.4F, 2.0F, -3.5F);
        this.legFntRt.addBox(-1.0F, -1.0F, -1.0F, 2, 9, 2, 0.0F);
        this.legFntRt.setTextureOffset(0, 40).addBox(-1.6F, 3.5F, -1.5F, 3, 3, 1, 0.0F); // legFntRtArmor
        legFntRtInfo = new PartInfo(legFntRt);
        this.legFntLft = new ModelRenderer(this, 16, 53);
        this.legFntLft.setRotationPoint(1.4F, 2.0F, -3.5F);
        this.legFntLft.addBox(-1.0F, -1.0F, -1.0F, 2, 9, 2, 0.0F);
        this.legFntLft.setTextureOffset(8, 40).addBox(-1.4F, 3.5F, -1.5F, 3, 3, 1, 0.0F); // legFntLftArmor
        legFntLftInfo = new PartInfo(legFntLft);
        this.legBckRt = new ModelRenderer(this, 0, 50);
        this.legBckRt.setRotationPoint(-1.4F, 2.0F, 4.0F);
        this.legBckRt.addBox(-1.0F, -0.5F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(legBckRt, -0.17453292519943295F, -0.0F, 0.0F);
        this.legBckRt.setTextureOffset(0, 57).addBox(-0.99F, 3.0F, 0.5F, 2, 5, 2, 0.0F); // legBckRtHind
        this.legBckRt.setTextureOffset(0, 44).addBox(-1.5F, 4.0F, 0.0F, 1, 3, 3, 0.0F); // legBckRtArmor
        legBckRtInfo = new PartInfo(legBckRt);
        this.legBckLft = new ModelRenderer(this, 8, 50);
        this.legBckLft.setRotationPoint(1.4F, 2.0F, 4.0F);
        this.legBckLft.addBox(-1.0F, -0.5F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(legBckLft, -0.17453292519943295F, -0.0F, 0.0F);
        this.legBckLft.setTextureOffset(8, 57).addBox(-1.01F, 3.0F, 0.5F, 2, 5, 2, 0.0F); // legBckLftHind
        this.legBckLft.setTextureOffset(8, 44).addBox(0.5F, 4.0F, 0.0F, 1, 3, 3, 0.0F); // legBckLftArmor
        legBckLftInfo = new PartInfo(legBckLft);

        this.bodyArmorFntFlapRtJoint = new ModelRenderer(this, 26, 44);
        this.bodyArmorFntFlapRtJoint.setRotationPoint(-3.0F, 2.0F, -3.5F);
        this.bodyArmorFntFlapRtJoint.addBox(-0.5F, 0.0F, -1.5F, 0, 0, 0, 0.0F);
        bodyArmorFntFlapRtJointInfo = new PartInfo(bodyArmorFntFlapRtJoint);
        this.bodyArmorFntFlapRt = new ModelRenderer(this, 26, 44);
        this.bodyArmorFntFlapRt.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bodyArmorFntFlapRt.addBox(-0.5F, 0.0F, -1.5F, 1, 2, 3, 0.0F);
        this.bodyArmorFntFlapRt.setTextureOffset(27, 49).addBox(-0.5F, 2.0F, -1.0F, 1, 1, 2, 0.0F); // bodyArmorFntFlapRtMid
        this.bodyArmorFntFlapRt.setTextureOffset(28, 52).addBox(-0.5F, 3.0F, -0.5F, 1, 1, 1, 0.0F); // bodyArmorFntFlapRtEnd
        bodyArmorFntFlapRtInfo = new PartInfo(bodyArmorFntFlapRt);
        this.bodyArmorFntFlapLftJoint = new ModelRenderer(this, 34, 44);
        this.bodyArmorFntFlapLftJoint.setRotationPoint(3.0F, 2.0F, -3.5F);
        this.bodyArmorFntFlapLftJoint.addBox(-0.5F, 0.0F, -1.5F, 0, 0, 0, 0.0F);
        bodyArmorFntFlapLftJointInfo = new PartInfo(bodyArmorFntFlapLftJoint);
        this.bodyArmorFntFlapLft = new ModelRenderer(this, 34, 44);
        this.bodyArmorFntFlapLft.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bodyArmorFntFlapLft.addBox(-0.5F, 0.0F, -1.5F, 1, 2, 3, 0.0F);
        this.bodyArmorFntFlapLft.setTextureOffset(35, 49).addBox(-0.5F, 2.0F, -1.0F, 1, 1, 2, 0.0F); // bodyArmorFntFlapLftMid
        this.bodyArmorFntFlapLft.setTextureOffset(36, 52).addBox(-0.5F, 3.0F, -0.5F, 1, 1, 1, 0.0F); // bodyArmorFntFlapLftEnd
        bodyArmorFntFlapLftInfo = new PartInfo(bodyArmorFntFlapLft);
        this.bodyArmorBckFlapRtJoint = new ModelRenderer(this, 26, 54);
        this.bodyArmorBckFlapRtJoint.setRotationPoint(-3.0F, 2.0F, 3.5F);
        this.bodyArmorBckFlapRtJoint.addBox(-0.5F, 0.0F, -1.5F, 0, 0, 0, 0.0F);
        bodyArmorBckFlapRtJointInfo = new PartInfo(bodyArmorBckFlapRtJoint);
        this.bodyArmorBckFlapRt = new ModelRenderer(this, 26, 54);
        this.bodyArmorBckFlapRt.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bodyArmorBckFlapRt.addBox(-0.5F, 0.0F, -1.5F, 1, 2, 3, 0.0F);
        this.bodyArmorBckFlapRt.setTextureOffset(27, 59).addBox(-0.5F, 2.0F, -1.0F, 1, 1, 2, 0.0F); // bodyArmorBckFlapRtMid
        this.bodyArmorBckFlapRt.setTextureOffset(28, 62).addBox(-0.5F, 3.0F, -0.5F, 1, 1, 1, 0.0F); // bodyArmorBckFlapRtEnd
        bodyArmorBckFlapRtInfo = new PartInfo(bodyArmorBckFlapRt);
        this.bodyArmorBckFlapLftJoint = new ModelRenderer(this, 34, 54);
        this.bodyArmorBckFlapLftJoint.setRotationPoint(3.0F, 2.0F, 3.5F);
        this.bodyArmorBckFlapLftJoint.addBox(-0.5F, 0.2F, -1.5F, 0, 0, 0, 0.0F);
        bodyArmorBckFlapLftJointInfo = new PartInfo(bodyArmorBckFlapLftJoint);
        this.bodyArmorBckFlapLft = new ModelRenderer(this, 34, 54);
        this.bodyArmorBckFlapLft.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.bodyArmorBckFlapLft.addBox(-0.5F, 0.0F, -1.5F, 1, 2, 3, 0.0F);
        this.bodyArmorBckFlapLft.setTextureOffset(35, 59).addBox(-0.5F, 2.0F, -1.0F, 1, 1, 2, 0.0F); // bodyArmorBckFlapLftMid
        this.bodyArmorBckFlapLft.setTextureOffset(36, 62).addBox(-0.5F, 3.0F, -0.5F, 1, 1, 1, 0.0F); // bodyArmorBckFlapLftEnd
        bodyArmorBckFlapLftInfo = new PartInfo(bodyArmorBckFlapLft);

        int backFurNumber = 0;
        int backFurBaseNumber = 0;
        int backFurTipNumber = 1;
        this.backFur[backFurNumber][backFurBaseNumber] = new ModelRenderer(this, 52, 18); // backFurMid
        this.backFur[backFurNumber][backFurBaseNumber].setRotationPoint(0.0F, -1.5F, 5.5F);
        this.backFur[backFurNumber][backFurBaseNumber].addBox(-1.0F, -0.5F, 0.0F, 2, 1, 4, 0.0F);
        this.setRotateAngle(backFur[backFurNumber][backFurBaseNumber], 1.1344640137963142F, -0.0F, 0.0F);
        backFurInfo[backFurNumber][backFurBaseNumber] = new PartInfo(backFur[backFurNumber][backFurBaseNumber]);
        this.backFur[backFurNumber][backFurTipNumber] = new ModelRenderer(this, 50, 19); // backFurMidTip
        this.backFur[backFurNumber][backFurTipNumber].setRotationPoint(0.0F, 0.0F, 3.5F);
        this.backFur[backFurNumber][backFurTipNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
        backFurInfo[backFurNumber][backFurTipNumber] = new PartInfo(backFur[backFurNumber][backFurTipNumber]);
        backFurNumber++;
        this.backFur[backFurNumber][backFurBaseNumber] = new ModelRenderer(this, 54, 14); // backFurLft
        this.backFur[backFurNumber][backFurBaseNumber].setRotationPoint(0.0F, -1.5F, 5.5F);
        this.backFur[backFurNumber][backFurBaseNumber].addBox(-1.0F, -0.5F, 0.0F, 2, 1, 3, 0.0F);
        this.setRotateAngle(backFur[backFurNumber][backFurBaseNumber], 1.1344640137963142F, -0.0F, 0.7853981633974483F);
        backFurInfo[backFurNumber][backFurBaseNumber] = new PartInfo(backFur[backFurNumber][backFurBaseNumber]);
        this.backFur[backFurNumber][backFurTipNumber] = new ModelRenderer(this, 53, 15); // backFurLftTip
        this.backFur[backFurNumber][backFurTipNumber].setRotationPoint(0.0F, 0.0F, 2.8F);
        this.backFur[backFurNumber][backFurTipNumber].addBox(-0.5F, -0.5F, -0.1F, 1, 1, 1, 0.0F);
        backFurInfo[backFurNumber][backFurTipNumber] = new PartInfo(backFur[backFurNumber][backFurTipNumber]);
        backFurNumber++;
        this.backFur[backFurNumber][backFurBaseNumber] = new ModelRenderer(this, 46, 15); // backFurRt
        this.backFur[backFurNumber][backFurBaseNumber].setRotationPoint(0.0F, -1.5F, 5.5F);
        this.backFur[backFurNumber][backFurBaseNumber].addBox(-1.0F, -0.5F, 0.0F, 2, 1, 3, 0.0F);
        this.setRotateAngle(backFur[backFurNumber][backFurBaseNumber], 1.1344640137963142F, -0.0F, -0.7853981633974483F);
        backFurInfo[backFurNumber][backFurBaseNumber] = new PartInfo(backFur[backFurNumber][backFurBaseNumber]);
        this.backFur[backFurNumber][backFurTipNumber] = new ModelRenderer(this, 47, 19); // backFurRtTip
        this.backFur[backFurNumber][backFurTipNumber].setRotationPoint(0.0F, 0.0F, 2.8F);
        this.backFur[backFurNumber][backFurTipNumber].addBox(-0.5F, -0.5F, -0.1F, 1, 1, 1, 0.0F);
        backFurInfo[backFurNumber][backFurTipNumber] = new PartInfo(backFur[backFurNumber][backFurTipNumber]);
        backFurNumber++;
        this.backFur[backFurNumber][backFurBaseNumber] = new ModelRenderer(this, 44, 22); // backFurBotRt
        this.backFur[backFurNumber][backFurBaseNumber].setRotationPoint(0.0F, -1.5F, 5.5F);
        this.backFur[backFurNumber][backFurBaseNumber].addBox(-1.0F, -0.5F, 0.0F, 2, 1, 4, 0.0F);
        this.setRotateAngle(backFur[backFurNumber][backFurBaseNumber], -1.1344640137963142F, -0.0F, 0.7853981633974483F);
        backFurInfo[backFurNumber][backFurBaseNumber] = new PartInfo(backFur[backFurNumber][backFurBaseNumber]);
        this.backFur[backFurNumber][backFurTipNumber] = new ModelRenderer(this, 52, 23); // backFurBotRtTip
        this.backFur[backFurNumber][backFurTipNumber].setRotationPoint(0.0F, 0.0F, 3.8F);
        this.backFur[backFurNumber][backFurTipNumber].addBox(-0.5F, -0.5F, -0.1F, 1, 1, 1, 0.0F);
        backFurInfo[backFurNumber][backFurTipNumber] = new PartInfo(backFur[backFurNumber][backFurTipNumber]);
        backFurNumber++;
        this.backFur[backFurNumber][backFurBaseNumber] = new ModelRenderer(this, 52, 23); // backFurBotLft
        this.backFur[backFurNumber][backFurBaseNumber].setRotationPoint(0.0F, -1.5F, 5.5F);
        this.backFur[backFurNumber][backFurBaseNumber].addBox(-1.0F, -0.5F, 0.0F, 2, 1, 4, 0.0F);
        this.setRotateAngle(backFur[backFurNumber][backFurBaseNumber], -1.1344640137963142F, -0.0F, -0.7853981633974483F);
        backFurInfo[backFurNumber][backFurBaseNumber] = new PartInfo(backFur[backFurNumber][backFurBaseNumber]);
        this.backFur[backFurNumber][backFurTipNumber] = new ModelRenderer(this, 60, 24); // backFurBotLftTip
        this.backFur[backFurNumber][backFurTipNumber].setRotationPoint(0.0F, 0.0F, 3.8F);
        this.backFur[backFurNumber][backFurTipNumber].addBox(-0.5F, -0.5F, -0.1F, 1, 1, 1, 0.0F);
        backFurInfo[backFurNumber][backFurTipNumber] = new PartInfo(backFur[backFurNumber][backFurTipNumber]);

        this.headJoint = new ModelRenderer(this, 0, 0);
        this.headJoint.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.headJoint.addBox(-2.5F, -4.0F, -3.5F, 0, 0, 0, 0.0F);
        this.setRotateAngle(headJoint, -0.5235987755982988F, -0.0F, 0.0F);
        headJointInfo = new PartInfo(headJoint);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.head.addBox(-2.5F, -4.0F, -3.5F, 5, 5, 5, 0.0F);
        this.head.setTextureOffset(15, 5).addBox(-2.5F, -5.0F, -3.5F, 5, 1, 5, 0.0F); // helmetTop
        this.head.setTextureOffset(21, 2).addBox(-3.0F, -4.5F, -4.5F, 6, 2, 1, 0.0F); // helmetFnt
        this.head.setTextureOffset(17, 11).addBox(-3.0F, -4.5F, 1.5F, 6, 4, 1, 0.0F); // helmetBck
        headInfo = new PartInfo(head);

        this.muzzle = new ModelRenderer(this, 15, 0);
        this.muzzle.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.muzzle.addBox(-1.0F, -1.5F, -4.0F, 2, 2, 1, 0.0F);
        this.setRotateAngle(muzzle, 0.17453292519943295F, -0.0F, 0.0F);
        this.muzzle.setTextureOffset(16, 3).addBox(-0.5F, -1.4F, -4.3F, 1, 1, 1, 0.0F); // nose
        this.helmetRtFlap = new ModelRenderer(this, 30, 0);
        this.helmetRtFlap.setRotationPoint(-2.0F, -3.0F, -1.0F);
        this.helmetRtFlap.addBox(-3.0F, -2.0F, -3.0F, 4, 2, 6, 0.0F);
        this.setRotateAngle(helmetRtFlap, 0.0F, -0.0F, -0.7853981633974483F);
        this.helmetLftFlap = new ModelRenderer(this, 44, 6);
        this.helmetLftFlap.setRotationPoint(2.0F, -3.0F, -1.0F);
        this.helmetLftFlap.addBox(-1.0F, -2.0F, -3.0F, 4, 2, 6, 0.0F);
        this.setRotateAngle(helmetLftFlap, 0.0F, -0.0F, 0.7853981633974483F);

        this.helmetHorn1 = new ModelRenderer(this, 0, 10);
        this.helmetHorn1.setRotationPoint(0.0F, -3.6F, -4.0F);
        this.helmetHorn1.addBox(-0.5F, -0.5F, -3.5F, 1, 1, 4, 0.0F);
        this.setRotateAngle(helmetHorn1, -0.5235987755982988F, -0.0F, 0.0F);
        this.helmetHorn2 = new ModelRenderer(this, 7, 12);
        this.helmetHorn2.setRotationPoint(0.0F, 0.0F, -3.3F);
        this.helmetHorn2.addBox(-0.49F, -0.5F, -0.3F, 1, 1, 3, 0.0F);
        this.setRotateAngle(helmetHorn2, 0.6981317007977318F, -0.0F, 0.0F);
        this.helmetHorn3 = new ModelRenderer(this, 0, 15);
        this.helmetHorn3.setRotationPoint(0.0F, 0.0F, 2.5F);
        this.helmetHorn3.addBox(-0.5F, -0.5F, -3.2F, 1, 1, 3, 0.0F);
        this.setRotateAngle(helmetHorn3, -0.5235987755982988F, -0.0F, 0.0F);
        this.helmetHorn4 = new ModelRenderer(this, 5, 16);
        this.helmetHorn4.setRotationPoint(0.0F, 0.0F, -3.0F);
        this.helmetHorn4.addBox(-0.49F, -0.5F, -2.7F, 1, 1, 3, 0.0F);
        this.setRotateAngle(helmetHorn4, -0.12217304763960307F, -0.0F, 0.0F);
        this.helmetHornTip = new ModelRenderer(this, 10, 15);
        this.helmetHornTip.setRotationPoint(0.0F, 0.0F, -2.5F);
        this.helmetHornTip.addBox(-0.5F, -0.5F, -4.8F, 1, 1, 5, 0.0F);
        this.setRotateAngle(helmetHornTip, -0.08726646259971647F, -0.0F, 0.0F);

        this.cheekFurRtJoint = new ModelRenderer(this, 23, 17);
        this.cheekFurRtJoint.setRotationPoint(-2.0F, -1.0F, 0.0F);
        this.cheekFurRtJoint.addBox(-1.0F, -1.0F, -1.0F, 0, 0, 0, 0.0F);
        this.setRotateAngle(cheekFurRtJoint, 0.5235987755982988F, -0.0F, 0.7853981633974483F);
        cheekFurRtJointInfo = new PartInfo(cheekFurRtJoint);
        this.cheekFurRt = new ModelRenderer(this, 23, 17);
        this.cheekFurRt.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.cheekFurRt.addBox(-1.0F, -1.0F, -1.0F, 2, 4, 2, 0.0F);
        this.cheekFurRt.setTextureOffset(25, 23).addBox(-0.5F, 3.0F, -0.5F, 1, 1, 1, 0.0F); // cheekFurRtEnd
        cheekFurRtInfo = new PartInfo(cheekFurRt);
        this.cheekFurLftJoint = new ModelRenderer(this, 31, 17);
        this.cheekFurLftJoint.setRotationPoint(2.0F, -1.0F, 0.0F);
        this.cheekFurLftJoint.addBox(-1.0F, -1.0F, -1.0F, 0, 0, 0, 0.0F);
        this.setRotateAngle(cheekFurLftJoint, 0.5235987755982988F, -0.0F, -0.7853981633974483F);
        cheekFurLftJointInfo = new PartInfo(cheekFurLftJoint);
        this.cheekFurLft = new ModelRenderer(this, 31, 17);
        this.cheekFurLft.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.cheekFurLft.addBox(-1.0F, -1.0F, -1.0F, 2, 4, 2, 0.0F);
        this.cheekFurLft.setTextureOffset(33, 23).addBox(-0.5F, 3.0F, -0.5F, 1, 1, 1, 0.0F); // cheekFurLftEnd
        cheekFurLftInfo = new PartInfo(cheekFurLft);

        this.earRtJoint = new ModelRenderer(this, 1, 28);
        this.earRtJoint.setRotationPoint(-1.5F, -3.0F, -1.0F);
        this.earRtJoint.addBox(-1.5F, -3.0F, -0.5F, 0, 0, 0, 0.0F);
        this.setRotateAngle(earRtJoint, 0.0F, -0.0F, -0.7853981633974483F);
        earRtJointInfo = new PartInfo(earRtJoint);
        this.earRt = new ModelRenderer(this, 1, 28);
        this.earRt.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.earRt.addBox(-1.5F, -3.0F, -0.5F, 3, 2, 1, 0.0F);
        this.earRt.setTextureOffset(0, 25).addBox(-2.0F, -5.0F, -0.5F, 4, 2, 1, 0.0F); // earRtMid
        this.earRt.setTextureOffset(1, 23).addBox(-1.5F, -6.0F, -0.5F, 3, 1, 1, 0.0F); // earRtEnd
        this.earRt.setTextureOffset(2, 21).addBox(-1.0F, -7.0F, -0.5F, 2, 1, 1, 0.0F); // earRtTip
        this.earRt.setTextureOffset(2, 34).addBox(-1.0F, -5.0F, 0.5F, 2, 3, 1, 0.0F); // earRtBck
        this.earRt.setTextureOffset(3, 32).addBox(-0.5F, -6.0F, 0.0F, 1, 1, 1, 0.0F); // earRtBckTop
        earRtInfo = new PartInfo(earRt);
        this.earLftJoint = new ModelRenderer(this, 11, 28);
        this.earLftJoint.setRotationPoint(1.5F, -3.0F, -1.0F);
        this.earLftJoint.addBox(-1.5F, -3.0F, -0.5F, 0, 0, 0, 0.0F);
        this.setRotateAngle(earLftJoint, 0.0F, -0.0F, 0.7853981633974483F);
        earLftJointInfo = new PartInfo(earLftJoint);
        this.earLft = new ModelRenderer(this, 11, 28);
        this.earLft.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.earLft.addBox(-1.5F, -3.0F, -0.5F, 3, 2, 1, 0.0F);
        this.earLft.setTextureOffset(10, 25).addBox(-2.0F, -5.0F, -0.5F, 4, 2, 1, 0.0F); // earLftMid
        this.earLft.setTextureOffset(11, 23).addBox(-1.5F, -6.0F, -0.5F, 3, 1, 1, 0.0F); // earLftEnd
        this.earLft.setTextureOffset(12, 21).addBox(-1.0F, -7.0F, -0.5F, 2, 1, 1, 0.0F); // earLftTip
        this.earLft.setTextureOffset(12, 34).addBox(-1.0F, -5.0F, 0.5F, 2, 3, 1, 0.0F); // earLftBck
        this.earLft.setTextureOffset(13, 32).addBox(-0.5F, -6.0F, 0.0F, 1, 1, 1, 0.0F); // earLftBckTop
        earLftInfo = new PartInfo(earLft);

        int tailNumber = 0;
        this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 40, 14); // tail1Joint
        this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, -1.5F, 5.0F);
        this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, 0.0F, 0, 0, 0, 0.0F);
        tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
        this.tail[tailNumber][partNumber] = new ModelRenderer(this, 40, 14); // tail1
        this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
        tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
        tailNumber++;
        this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 40, 17); // tail2Joint
        this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
        this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
        tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
        this.tail[tailNumber][partNumber] = new ModelRenderer(this, 40, 17); // tail2
        this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
        tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
        tailNumber++;
        this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 40, 20); // tail3Joint
        this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
        this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
        tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
        this.tail[tailNumber][partNumber] = new ModelRenderer(this, 40, 20); // tail3
        this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
        tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
        tailNumber++;
        this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 40, 23); // tail4Joint
        this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
        this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
        tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
        this.tail[tailNumber][partNumber] = new ModelRenderer(this, 40, 23); // tail4
        this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
        tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
        tailNumber++;
        this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 40, 26); // tail5Joint
        this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
        this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
        tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
        this.tail[tailNumber][partNumber] = new ModelRenderer(this, 40, 26); // tail5
        this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
        tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
        tailNumber++;
        this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 40, 29); // tail6Joint
        this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
        this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
        tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
        this.tail[tailNumber][partNumber] = new ModelRenderer(this, 40, 29); // tail6
        this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
        tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
        tailNumber++;
        this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 40, 32); // tail7Joint
        this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
        this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
        tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
        this.tail[tailNumber][partNumber] = new ModelRenderer(this, 40, 32); // tail7
        this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
        tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
        tailNumber++;
        this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 40, 35); // tail8Joint
        this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
        this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
        tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
        this.tail[tailNumber][partNumber] = new ModelRenderer(this, 40, 35); // tail8
        this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
        tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
        tailNumber++;
        this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 40, 38); // tail9Joint
        this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
        this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
        tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
        this.tail[tailNumber][partNumber] = new ModelRenderer(this, 40, 38); // tail9
        this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
        tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
        tailNumber++;
        this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 40, 41); // tail10Joint
        this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
        this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
        tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
        this.tail[tailNumber][partNumber] = new ModelRenderer(this, 40, 41); // tail10
        this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
        tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
        tailNumber++;
        this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 40, 44); // tail11Joint
        this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
        this.tail[tailNumber][jointNumber].addBox(-1.0F, -1.0F, -0.2F, 0, 0, 0, 0.0F);
        tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
        this.tail[tailNumber][partNumber] = new ModelRenderer(this, 40, 44); // tail11
        this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
        this.tail[tailNumber][partNumber].addBox(-1.0F, -1.0F, -0.2F, 2, 2, 1, 0.0F);
        this.tail[tailNumber][partNumber].setTextureOffset(46, 34).addBox(-1.5F, -1.5F, -0.1F, 1, 1, 1, 0.0F); // tail11TopRt
        this.tail[tailNumber][partNumber].setTextureOffset(46, 36).addBox(0.5F, -1.5F, -0.1F, 1, 1, 1, 0.0F); // tail11TopLft
        this.tail[tailNumber][partNumber].setTextureOffset(46, 38).addBox(0.5F, 0.5F, -0.1F, 1, 1, 1, 0.0F); // tail11BotLft
        this.tail[tailNumber][partNumber].setTextureOffset(46, 40).addBox(-1.5F, 0.5F, -0.1F, 1, 1, 1, 0.0F); // tail11BotRt
        tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);

        this.body.addChild(this.legBckLft);
        this.body.addChild(this.legBckRt);
        this.body.addChild(this.legFntLft);
        this.body.addChild(this.legFntRt);
        this.body.addChild(this.bodyArmorBckFlapLftJoint);
        this.body.addChild(this.bodyArmorBckFlapRtJoint);
        this.body.addChild(this.bodyArmorFntFlapLftJoint);
        this.body.addChild(this.bodyArmorFntFlapRtJoint);
        this.body.addChild(this.neckJoint);
        this.body.addChild(this.backFur[0][0]); // backFurMid
        this.body.addChild(this.backFur[1][0]); // backFurLft
        this.body.addChild(this.backFur[2][0]); // backFurRt
        this.body.addChild(this.backFur[3][0]); // backFurBotRt
        this.body.addChild(this.backFur[4][0]); // backFurBotLft
        this.body.addChild(this.tail[0][0]);
        
        this.bodyArmorBckFlapLftJoint.addChild(this.bodyArmorBckFlapLft);
        this.bodyArmorBckFlapRtJoint.addChild(this.bodyArmorBckFlapRt);
        this.bodyArmorFntFlapLftJoint.addChild(this.bodyArmorFntFlapLft);
        this.bodyArmorFntFlapRtJoint.addChild(this.bodyArmorFntFlapRt);

        this.backFur[0][0].addChild(this.backFur[0][1]); // backFurMid * backFurMidTip
        this.backFur[1][0].addChild(this.backFur[1][1]); // backFurLft * backFurLftTip
        this.backFur[2][0].addChild(this.backFur[2][1]); // backFurRt * backFurRtTip
        this.backFur[3][0].addChild(this.backFur[3][1]); // backFurBotRt * backFurBotRtTip
        this.backFur[4][0].addChild(this.backFur[4][1]); // backFurBotLft * backFurBotLftTip
        
        this.neckJoint.addChild(this.neck);
        this.neck.addChild(this.headJoint);

        this.headJoint.addChild(this.head);
        this.head.addChild(this.cheekFurLftJoint);
        this.head.addChild(this.cheekFurRtJoint);
        this.head.addChild(this.earLftJoint);
        this.head.addChild(this.earRtJoint);
        this.head.addChild(this.helmetHorn1);
        this.head.addChild(this.helmetLftFlap);
        this.head.addChild(this.helmetRtFlap);
        this.head.addChild(this.muzzle);

        this.helmetHorn1.addChild(this.helmetHorn2);
        this.helmetHorn2.addChild(this.helmetHorn3);
        this.helmetHorn3.addChild(this.helmetHorn4);
        this.helmetHorn4.addChild(this.helmetHornTip);

        this.cheekFurLftJoint.addChild(this.cheekFurLft);
        this.cheekFurRtJoint.addChild(this.cheekFurRt);
        
        this.earLftJoint.addChild(this.earLft);
        this.earRtJoint.addChild(this.earRt);

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
		float verticalVelocity = (float)((IMobMotionTracker)entity).getHeightVelocity();

		animateBody((EntityArmoredLuxray)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity);
		animateHead((EntityArmoredLuxray)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity);
		animateLegs((EntityArmoredLuxray)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity);
		animateTail((EntityArmoredLuxray)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity, angularVelocity);

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
		
		legFntRtInfo.resetNewAngles();
		legFntLftInfo.resetNewAngles();
		legBckRtInfo.resetNewAngles();
		legBckLftInfo.resetNewAngles();
		
		for(int i = 0; i < tail.length; i++)
		{
			tailInfo[i][0].resetNewAngles();
			tailInfo[i][1].resetNewAngles();
		}
	}
	
	public void animateBody(EntityArmoredLuxray entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity)
	{
		float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
		float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);

		float bodyWalkAngle = (float)Math.toRadians(7);
		float bodyRunAngle = (float)Math.toRadians(14);

		float walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI) * bodyWalkAngle * (1 - horzVelocity);
		float runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI) * bodyRunAngle * horzVelocity;

		float bodyJumpAngle = (float)Math.toRadians(-65);
		float verticalVelocityChangeAngle = (float)Math.toRadians(10);

		float newVerticalVelocity = 1.5F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;

		bodyInfo.setNewRotateX(bodyInfo.getNewRotateX() - (walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity)) 
				+ bodyJumpAngle * newVerticalVelocity);
		
		float amplitude = 0.8F;

		this.backFur[0][0].rotateAngleY = PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, this.RUN_FREQUENCY, amplitude);
		this.backFur[1][0].rotateAngleY = PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, this.RUN_FREQUENCY, amplitude);
		this.backFur[2][0].rotateAngleY = PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, this.RUN_FREQUENCY, amplitude);
		this.backFur[3][0].rotateAngleY = PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, this.RUN_FREQUENCY, amplitude);
		this.backFur[4][0].rotateAngleY = PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, this.RUN_FREQUENCY, amplitude);

		amplitude = -0.8F;
		
		this.backFur[0][1].rotateAngleY = PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, this.RUN_FREQUENCY, amplitude);
		this.backFur[1][1].rotateAngleY = PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, this.RUN_FREQUENCY, amplitude);
		this.backFur[2][1].rotateAngleY = PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, this.RUN_FREQUENCY, amplitude);
		this.backFur[3][1].rotateAngleY = PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, this.RUN_FREQUENCY, amplitude);
		this.backFur[4][1].rotateAngleY = PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, this.RUN_FREQUENCY, amplitude);

		amplitude = 0.10F;
}
	
	public void animateHead(EntityArmoredLuxray entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, neckJointInfo);

		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockNeckBobble();

		float idleAmplitudeX = (float)Math.toRadians(10);

		float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
		float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);

		float walkAngle = (float)Math.toRadians(20);
		float runAngle = (float)Math.toRadians(28);

		float walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI - PI/2F) * walkAngle * (1 - horzVelocity)
				+ walkAngle * (1 - horzVelocity);
		float runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI - PI/2F) * runAngle * horzVelocity
				+ runAngle * horzVelocity;

		float idleAngleChangeX = MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeX;

		neckInfo.setNewRotateX(neckInfo.getNewRotateX() + idleAngleChangeX + (walkCycleAngleChange + runCycleAngleChange) * horzVelocity);

		JointAnimation.reverseJointRotatesChange(neckInfo, headJointInfo);

		PartAnimate.headAnimateInfoOnly(headInfo, yawHeadOffsetDifference, pitchRotation);

		/* ******* Ears ******** */
		walkAngle = (float)Math.toRadians(20);
		runAngle = (float)Math.toRadians(28);

		walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI) * walkAngle * (1 - horzVelocity);
		runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI) * runAngle * horzVelocity;

		float moveAngleZ = (walkCycleAngleChange + runCycleAngleChange) * horzVelocity;

		float idleAmplitudeZ = 0.20F;

		float newVerticalVelocity = 1.5F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;

		float fallingEarAngle = (float)Math.toRadians(40);

		// + (float)Math.toRadians(45)
		float angleChangeX = MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI + PI) * idleAmplitudeZ * (1 - horzVelocity);

		earRtInfo.setNewRotateZ(earRtInfo.getNewRotateZ() + ((angleChangeX - moveAngleZ) * (1F - newVerticalVelocity) + fallingEarAngle * newVerticalVelocity));
		earLftInfo.setNewRotateZ(earLftInfo.getNewRotateZ() - ((angleChangeX - moveAngleZ) * (1F - newVerticalVelocity) + fallingEarAngle * newVerticalVelocity));

		/* ******* Hairs ******** */
		cheekFurRtInfo.setNewRotateZ(cheekFurRtInfo.getNewRotateX() + angleChangeX * 1.2F);
		cheekFurLftInfo.setNewRotateZ(cheekFurLftInfo.getNewRotateX() - angleChangeX * 1.2F);
	}

	public void animateLegs(EntityArmoredLuxray entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity)
	{
		float amplitude = 0.55F;

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

		legFntRtInfo.setNewRotateX(legFntRtInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legFntRtModifier, newHorizontalVelocity, this.RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)) + fallingLegAngle * fallingModifier);
		legFntLftInfo.setNewRotateX(legFntLftInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, newHorizontalVelocity, this.RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)) + fallingLegAngle * fallingModifier);
		legBckRtInfo.setNewRotateX(legBckRtInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legBckRtModifier, newHorizontalVelocity, this.RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)));
		legBckLftInfo.setNewRotateX(legBckLftInfo.getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legBckLftModifier, newHorizontalVelocity, this.RUN_FREQUENCY, amplitude) * (1F - Math.abs(newVerticalVelocity)));

		this.bodyArmorBckFlapLft.rotateAngleZ = PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, this.RUN_FREQUENCY, amplitude * 0.5F);
		this.bodyArmorBckFlapRt.rotateAngleZ = PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, this.RUN_FREQUENCY, amplitude * 0.5F);
		this.bodyArmorFntFlapLft.rotateAngleZ = PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, this.RUN_FREQUENCY, amplitude * 0.5F);
		this.bodyArmorFntFlapRt.rotateAngleZ = PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, this.RUN_FREQUENCY, amplitude * 0.5F);
	}

	public void animateTail(EntityArmoredLuxray entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity, float angularVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, tailInfo[0][0]);

		float yawChangeAngle = -(float)Math.toRadians(15);
		float verticalVelocityChangeAngle = (float)Math.toRadians(-9);

		float bodyJumpAngle = (float)Math.toRadians(-35);

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

			float moveAmplitudeX = (float)Math.toRadians(8);

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
		
		this.animationDeployer.rotate(cheekFurRt, cheekFurRtInfo.getNewRotates());
		this.animationDeployer.rotate(cheekFurLft, cheekFurLftInfo.getNewRotates());

		this.animationDeployer.rotate(legFntRt, legFntRtInfo.getNewRotates());
		this.animationDeployer.rotate(legFntLft, legFntLftInfo.getNewRotates());
		this.animationDeployer.rotate(legBckRt, legBckRtInfo.getNewRotates());
		this.animationDeployer.rotate(legBckLft, legBckLftInfo.getNewRotates());

		for(int i = 0; i < tail.length; i++)
		{
			this.animationDeployer.rotate(tail[i][0], tailInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(tail[i][1], tailInfo[i][1].getNewRotates());
		}

		this.animationDeployer.applyChanges();
	}
}