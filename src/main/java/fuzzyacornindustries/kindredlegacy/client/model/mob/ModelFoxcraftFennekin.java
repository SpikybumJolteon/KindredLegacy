package fuzzyacornindustries.kindredlegacy.client.model.mob;

import fuzzyacornindustries.kindredlegacy.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.animation.JointAnimation;
import fuzzyacornindustries.kindredlegacy.animation.PartAnimate;
import fuzzyacornindustries.kindredlegacy.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.entity.mob.IMobMotionTracker;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityFoxcraftFennekin;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFoxcraftFennekin extends ModelBase 
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public final float RUN_FREQUENCY = 0.9F;
	public final float WALK_FREQUENCY = 0.3F;
	public final float SNEAK_VELOCITY = 0.35F;

	public ModelRenderer body;
	PartInfo bodyInfo;
	PartInfo bodySitInfo;

	public ModelRenderer bodyTuffLft;
	public ModelRenderer bodyTuffRt;

	public ModelRenderer legFntLft[][] = new ModelRenderer[2][2];
	PartInfo legFntLftInfo[][] = new PartInfo[legFntLft.length][legFntLft[0].length];
	PartInfo legFntLftSitInfo[][] = new PartInfo[legFntLft.length][legFntLft[0].length];

	public ModelRenderer legFntRt[][] = new ModelRenderer[2][2];
	PartInfo legFntRtInfo[][] = new PartInfo[legFntRt.length][legFntRt[0].length];
	PartInfo legFntRtSitInfo[][] = new PartInfo[legFntRt.length][legFntRt[0].length];

	public ModelRenderer legBckLft[][] = new ModelRenderer[2][2];
	PartInfo legBckLftInfo[][] = new PartInfo[legBckLft.length][legBckLft[0].length];
	PartInfo legBckLftSitInfo[][] = new PartInfo[legBckLft.length][legBckLft[0].length];

	public ModelRenderer legBckRt[][] = new ModelRenderer[2][2];
	PartInfo legBckRtInfo[][] = new PartInfo[legBckRt.length][legBckRt[0].length];
	PartInfo legBckRtSitInfo[][] = new PartInfo[legBckRt.length][legBckRt[0].length];

	public ModelRenderer neckJoint;
	public ModelRenderer neck;
	public ModelRenderer headJoint;
	public ModelRenderer head;
	PartInfo neckJointInfo;
	PartInfo neckInfo;
	PartInfo headJointInfo;
	PartInfo headInfo;

	public ModelRenderer eyeRt;
	public ModelRenderer eyeLft;

	public ModelRenderer nose;

	public ModelRenderer cheekTuffLft;
	public ModelRenderer cheekTuffRt;
	PartInfo cheekTuffLftInfo;
	PartInfo cheekTuffRtInfo;

	public ModelRenderer earLftJoint;
	public ModelRenderer earLft;
	PartInfo earLftJointInfo;
	PartInfo earLftInfo;

	public ModelRenderer earRtJoint;
	public ModelRenderer earRt;
	PartInfo earRtJointInfo;
	PartInfo earRtInfo;

	public ModelRenderer earTuffLftJoint;
	PartInfo earTuffLftJointInfo;
	public ModelRenderer earTuffLft[] = new ModelRenderer[4];
	PartInfo earTuffLftInfo[] = new PartInfo[earTuffLft.length];

	public ModelRenderer earTuffRtJoint;
	PartInfo earTuffRtJointInfo;
	public ModelRenderer earTuffRt[] = new ModelRenderer[4];
	PartInfo earTuffRtInfo[] = new PartInfo[earTuffRt.length];

	public ModelRenderer tail[][] = new ModelRenderer[5][2];
	PartInfo tailInfo[][] = new PartInfo[tail.length][2];

	public ModelFoxcraftFennekin() 
	{
		this.textureWidth = 64;
		this.textureHeight = 32;

		int jointNumber = 0;
		int partNumber = 1;

		this.body = new ModelRenderer(this, 51, 26);
		this.body.setRotationPoint(0.0F, 21.3F, 0.0F);
		this.body.addBox(-1.0F, -1.0F, -2.0F, 2, 2, 4, 0.0F);
		this.bodyInfo = new PartInfo(body);

		this.body.setRotationPoint(0.0F, 22.3F, 0.0F);
		this.setRotateAngle(body, -0.7853981633974483F, 0.0F, 0.0F);
		this.bodySitInfo = new PartInfo(body);

		this.bodyTuffLft = new ModelRenderer(this, 55, 22);
		this.bodyTuffLft.setRotationPoint(0.5F, -0.7F, 1.3F);
		this.bodyTuffLft.addBox(-0.5F, -1.6F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyTuffLft, -0.5235987755982988F, 0.17453292519943295F, 0.3490658503988659F);
		this.bodyTuffRt = new ModelRenderer(this, 55, 19);
		this.bodyTuffRt.setRotationPoint(-0.5F, -0.7F, 1.3F);
		this.bodyTuffRt.addBox(-0.5F, -1.6F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(bodyTuffRt, -0.5235987755982988F, -0.17453292519943295F, -0.3490658503988659F);

		int legPartNumber = 0;
		this.legFntLft[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legFntLft1Joint
		this.legFntLft[legPartNumber][jointNumber].setRotationPoint(0.55F, 0.2F, -1.1F);
		this.legFntLft[legPartNumber][jointNumber].addBox(-0.5F, -0.2F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legFntLft[legPartNumber][jointNumber], 0.08726646259971647F, 0.0F, 0.0F);
		this.legFntLftInfo[legPartNumber][jointNumber] = new PartInfo(this.legFntLft[legPartNumber][jointNumber]);
		this.legFntLft[legPartNumber][partNumber] = new ModelRenderer(this, 60, 17); // legFntLft1
		this.legFntLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legFntLft[legPartNumber][partNumber].addBox(-0.5F, -0.2F, -0.5F, 1, 1, 1, 0.0F);
		this.legFntLftInfo[legPartNumber][partNumber] = new PartInfo(this.legFntLft[legPartNumber][partNumber]);
		legPartNumber++;
		this.legFntLft[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legFntLft2Joint
		this.legFntLft[legPartNumber][jointNumber].setRotationPoint(0.0F, 0.7F, 0.0F);
		this.legFntLft[legPartNumber][jointNumber].addBox(-0.49F, -0.1F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legFntLft[legPartNumber][jointNumber], -0.17453292519943295F, 0.0F, 0.0F);
		this.legFntLftInfo[legPartNumber][jointNumber] = new PartInfo(this.legFntLft[legPartNumber][jointNumber]);
		this.legFntLft[legPartNumber][partNumber] = new ModelRenderer(this, 60, 19); // legFntLft2
		this.legFntLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legFntLft[legPartNumber][partNumber].addBox(-0.49F, -0.1F, -0.5F, 1, 2, 1, 0.0F);
		this.legFntLftInfo[legPartNumber][partNumber] = new PartInfo(this.legFntLft[legPartNumber][partNumber]);

		legPartNumber = 0;
		this.legFntLft[legPartNumber][jointNumber].setRotationPoint(0.55F, 0.2F, -1.1F); // legFntLft1Joint
		this.setRotateAngle(legFntLft[legPartNumber][jointNumber], 0.6981317007977318F, 0.0F, 0.0F);
		this.legFntLftSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legFntLft[legPartNumber][jointNumber]);
		this.legFntLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legFntLft1
		this.legFntLftSitInfo[legPartNumber][partNumber] = new PartInfo(this.legFntLft[legPartNumber][partNumber]);
		legPartNumber++;
		this.legFntLft[legPartNumber][jointNumber].setRotationPoint(0.0F, 0.7F, 0.0F); // legFntLft2Joint
		this.setRotateAngle(legFntLft[legPartNumber][jointNumber], -0.17453292519943295F, 0.0F, 0.0F);
		this.legFntLftSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legFntLft[legPartNumber][jointNumber]);
		this.legFntLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legFntLft2
		this.legFntLftSitInfo[legPartNumber][partNumber] = new PartInfo(this.legFntLft[legPartNumber][partNumber]);

		legPartNumber = 0;
		this.legFntRt[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legFntRt1Joint
		this.legFntRt[legPartNumber][jointNumber].setRotationPoint(-0.55F, 0.2F, -1.1F);
		this.legFntRt[legPartNumber][jointNumber].addBox(-0.5F, -0.2F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legFntRt[legPartNumber][jointNumber], 0.08726646259971647F, 0.0F, 0.0F);
		this.legFntRtInfo[legPartNumber][jointNumber] = new PartInfo(this.legFntRt[legPartNumber][jointNumber]);
		this.legFntRt[legPartNumber][partNumber] = new ModelRenderer(this, 50, 17); // legFntRt1
		this.legFntRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legFntRt[legPartNumber][partNumber].addBox(-0.5F, -0.2F, -0.5F, 1, 1, 1, 0.0F);
		this.legFntRtInfo[legPartNumber][partNumber] = new PartInfo(this.legFntRt[legPartNumber][partNumber]);
		legPartNumber++;
		this.legFntRt[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legFntRt2Joint
		this.legFntRt[legPartNumber][jointNumber].setRotationPoint(0.0F, 0.7F, 0.0F);
		this.legFntRt[legPartNumber][jointNumber].addBox(-0.49F, -0.1F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legFntRt[legPartNumber][jointNumber], -0.17453292519943295F, 0.0F, 0.0F);
		this.legFntRtInfo[legPartNumber][jointNumber] = new PartInfo(this.legFntRt[legPartNumber][jointNumber]);
		this.legFntRt[legPartNumber][partNumber] = new ModelRenderer(this, 50, 19); // legFntRt2
		this.legFntRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legFntRt[legPartNumber][partNumber].addBox(-0.49F, -0.1F, -0.5F, 1, 2, 1, 0.0F);
		this.legFntRtInfo[legPartNumber][partNumber] = new PartInfo(this.legFntRt[legPartNumber][partNumber]);

		legPartNumber = 0;
		this.legFntRt[legPartNumber][jointNumber].setRotationPoint(-0.55F, 0.2F, -1.1F); // legFntRt1Joint
		this.setRotateAngle(legFntRt[legPartNumber][jointNumber], 0.6981317007977318F, 0.0F, 0.0F);
		this.legFntRtSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legFntRt[legPartNumber][jointNumber]);
		this.legFntRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legFntRt1
		this.legFntRtSitInfo[legPartNumber][partNumber] = new PartInfo(this.legFntRt[legPartNumber][partNumber]);
		legPartNumber++;
		this.legFntRt[legPartNumber][jointNumber].setRotationPoint(0.0F, 0.7F, 0.0F); // legFntRt2Joint
		this.setRotateAngle(legFntRt[legPartNumber][jointNumber], -0.17453292519943295F, 0.0F, 0.0F);
		this.legFntRtSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legFntRt[legPartNumber][jointNumber]);
		this.legFntRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legFntRt2
		this.legFntRtSitInfo[legPartNumber][partNumber] = new PartInfo(this.legFntRt[legPartNumber][partNumber]);

		legPartNumber = 0;
		this.legBckLft[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legBckLft1Joint
		this.legBckLft[legPartNumber][jointNumber].setRotationPoint(0.55F, 0.2F, 1.3F);
		this.legBckLft[legPartNumber][jointNumber].addBox(-0.5F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legBckLft[legPartNumber][jointNumber], -0.17453292519943295F, 0.0F, 0.0F);
		this.legBckLftInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckLft[legPartNumber][jointNumber]);
		this.legBckLft[legPartNumber][partNumber] = new ModelRenderer(this, 60, 23); // legBckLft1
		this.legBckLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckLft[legPartNumber][partNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F);
		this.legBckLftInfo[legPartNumber][partNumber] = new PartInfo(this.legBckLft[legPartNumber][partNumber]);
		legPartNumber++;
		this.legBckLft[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legBckLft2Joint
		this.legBckLft[legPartNumber][jointNumber].setRotationPoint(0.0F, 0.9F, 0.4F);
		this.legBckLft[legPartNumber][jointNumber].addBox(-0.5F, -0.4F, -0.5F, 0, 0, 0, 0.0F);
		this.legBckLftInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckLft[legPartNumber][jointNumber]);
		this.legBckLft[legPartNumber][partNumber] = new ModelRenderer(this, 60, 26); // legBckLft2
		this.legBckLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckLft[legPartNumber][partNumber].addBox(-0.49F, -0.4F, -0.5F, 1, 2, 1, 0.0F);
		this.legBckLftInfo[legPartNumber][partNumber] = new PartInfo(this.legBckLft[legPartNumber][partNumber]);

		legPartNumber = 0;
		this.legBckLft[legPartNumber][jointNumber].setRotationPoint(0.55F, 0.2F, 1.3F); // legBckLft1Joint
		this.setRotateAngle(legBckLft[legPartNumber][jointNumber], -1.1344640137963142F, 0.0F, 0.0F);
		this.legBckLftSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckLft[legPartNumber][jointNumber]);
		this.legBckLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legBckLft1
		this.setRotateAngle(legBckLft[legPartNumber][partNumber], 0.0F, 0.0F, -0.5235987755982988F);
		this.legBckLftSitInfo[legPartNumber][partNumber] = new PartInfo(this.legBckLft[legPartNumber][partNumber]);
		legPartNumber++;
		this.legBckLft[legPartNumber][jointNumber].setRotationPoint(0.0F, 0.9F, 0.4F); // legBckLft2Joint
		this.setRotateAngle(legBckLft[legPartNumber][jointNumber], 0.3490658503988659F, 0.0F, 0.0F);
		this.legBckLftSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckLft[legPartNumber][jointNumber]);
		this.legBckLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legBckLft2
		this.legBckLftSitInfo[legPartNumber][partNumber] = new PartInfo(this.legBckLft[legPartNumber][partNumber]);

		legPartNumber = 0;
		this.legBckRt[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legBckRt1Joint
		this.legBckRt[legPartNumber][jointNumber].setRotationPoint(-0.55F, 0.2F, 1.3F);
		this.legBckRt[legPartNumber][jointNumber].addBox(-0.5F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legBckRt[legPartNumber][jointNumber], -0.17453292519943295F, 0.0F, 0.0F);
		this.legBckRtInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckRt[legPartNumber][jointNumber]);
		this.legBckRt[legPartNumber][partNumber] = new ModelRenderer(this, 50, 23); // legBckRt1
		this.legBckRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckRt[legPartNumber][partNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 2, 1, 0.0F);
		this.legBckRtInfo[legPartNumber][partNumber] = new PartInfo(this.legBckRt[legPartNumber][partNumber]);
		legPartNumber++;
		this.legBckRt[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legBckRt2Joint
		this.legBckRt[legPartNumber][jointNumber].setRotationPoint(0.0F, 0.9F, 0.4F);
		this.legBckRt[legPartNumber][jointNumber].addBox(-0.5F, -0.4F, -0.5F, 0, 0, 0, 0.0F);
		this.legBckRtInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckRt[legPartNumber][jointNumber]);
		this.legBckRt[legPartNumber][partNumber] = new ModelRenderer(this, 50, 26); // legBckRt2
		this.legBckRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckRt[legPartNumber][partNumber].addBox(-0.49F, -0.4F, -0.5F, 1, 2, 1, 0.0F);
		this.legBckRtInfo[legPartNumber][partNumber] = new PartInfo(this.legBckRt[legPartNumber][partNumber]);

		legPartNumber = 0;
		this.legBckRt[legPartNumber][jointNumber].setRotationPoint(-0.55F, 0.2F, 1.3F); // legBckRt1Joint
		this.setRotateAngle(legBckRt[legPartNumber][jointNumber], -1.1344640137963142F, 0.0F, 0.0F);
		this.legBckRtSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckRt[legPartNumber][jointNumber]);
		this.legBckRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legBckRt1
		this.setRotateAngle(legBckRt[legPartNumber][partNumber], 0.0F, 0.0F, 0.5235987755982988F);
		this.legBckRtSitInfo[legPartNumber][partNumber] = new PartInfo(this.legBckRt[legPartNumber][partNumber]);
		legPartNumber++;
		this.legBckRt[legPartNumber][jointNumber].setRotationPoint(0.0F, 0.9F, 0.4F); // legBckRt2Joint
		this.setRotateAngle(legBckRt[legPartNumber][jointNumber], 0.3490658503988659F, 0.0F, 0.0F);
		this.legBckRtSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckRt[legPartNumber][jointNumber]);
		this.legBckRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legBckRt2
		this.legBckRtSitInfo[legPartNumber][partNumber] = new PartInfo(this.legBckRt[legPartNumber][partNumber]);

		this.neckJoint = new ModelRenderer(this, 0, 0);
		this.neckJoint.setRotationPoint(0.0F, -0.5F, -1.5F);
		this.neckJoint.addBox(-0.5F, -0.5F, -2.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(neckJoint, -0.7853981633974483F, 0.0F, 0.0F);
		this.neckJointInfo = new PartInfo(neckJoint);
		this.neck = new ModelRenderer(this, 2, 7);
		this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.neck.addBox(-0.5F, -0.5F, -2.0F, 1, 1, 3, 0.0F);
		this.neckInfo = new PartInfo(neck);
		this.headJoint = new ModelRenderer(this, 0, 0);
		this.headJoint.setRotationPoint(0.0F, 0.0F, -1.8F);
		this.headJoint.addBox(-1.5F, -2.2F, -2.2F, 0, 0, 0, 0.0F);
		this.setRotateAngle(headJoint, 0.7853981633974483F, 0.0F, 0.0F);
		this.headJointInfo = new PartInfo(headJoint);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-1.5F, -2.2F, -2.2F, 3, 3, 3, 0.0F);
		this.headInfo = new PartInfo(head);

		this.eyeRt = new ModelRenderer(this, 0, 7);
		this.eyeRt.setRotationPoint(-1.0F, -1.2F, -1.71F);
		this.eyeRt.addBox(-0.25F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
		this.eyeLft = new ModelRenderer(this, 8, 7);
		this.eyeLft.setRotationPoint(1.0F, -1.2F, -1.71F);
		this.eyeLft.addBox(-0.75F, -0.5F, -0.5F, 1, 1, 1, 0.0F);

		this.nose = new ModelRenderer(this, 4, 12);
		this.nose.setRotationPoint(0.0F, -0.1F, -2.0F);
		this.nose.addBox(-0.5F, -0.5F, -0.8F, 1, 1, 1, 0.0F);
		this.setRotateAngle(nose, 0.17453292519943295F, 0.0F, 0.0F);

		this.cheekTuffLft = new ModelRenderer(this, 7, 15);
		this.cheekTuffLft.setRotationPoint(1.1F, 0.3F, -1.3F);
		this.cheekTuffLft.addBox(-0.5F, -0.3F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(cheekTuffLft, 0.0F, 0.0F, -1.2217304763960306F);
		this.cheekTuffLftInfo = new PartInfo(cheekTuffLft);
		this.cheekTuffRt = new ModelRenderer(this, 1, 15);
		this.cheekTuffRt.setRotationPoint(-1.1F, 0.3F, -1.3F);
		this.cheekTuffRt.addBox(-0.5F, -0.3F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(cheekTuffRt, 0.0F, 0.0F, 1.2217304763960306F);
		this.cheekTuffRtInfo = new PartInfo(cheekTuffRt);

		this.earLftJoint = new ModelRenderer(this, 0, 0);
		this.earLftJoint.setRotationPoint(1.0F, -1.5F, -0.2F);
		this.earLftJoint.addBox(-1.1F, -1.2F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earLftJoint, 0.0F, 0.0F, 1.0471975511965976F);
		this.earLftJointInfo = new PartInfo(earLftJoint);
		this.earLft = new ModelRenderer(this, 22, 4);
		this.earLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earLft.addBox(-1.1F, -1.2F, -0.5F, 3, 2, 1, 0.0F);
		this.earLft.setTextureOffset(23, 2).addBox(-0.7F, -2.2F, -0.5F, 2, 1, 1, 0.0F); // earLftMid
		this.earLft.setTextureOffset(24, 0).addBox(-0.3F, -3.2F, -0.5F, 1, 1, 1, 0.0F); // earLftTip
		this.earLftInfo = new PartInfo(earLft);

		this.earRtJoint = new ModelRenderer(this, 0, 0);
		this.earRtJoint.setRotationPoint(-1.0F, -1.5F, -0.2F);
		this.earRtJoint.addBox(-1.1F, -1.2F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earRtJoint, 0.0F, 0.0F, -1.0471975511965976F);
		this.earRtJointInfo = new PartInfo(earRtJoint);
		this.earRt = new ModelRenderer(this, 13, 4);
		this.earRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earRt.addBox(-1.8F, -1.2F, -0.5F, 3, 2, 1, 0.0F);
		this.earRt.setTextureOffset(14, 2).addBox(-1.2F, -2.2F, -0.5F, 2, 1, 1, 0.0F); // earRtMid
		this.earRt.setTextureOffset(15, 0).addBox(-0.6F, -3.2F, -0.5F, 1, 1, 1, 0.0F); // earRtTip
		this.earRtInfo = new PartInfo(earRt);

		int earTuffNumber = 0;
		this.earTuffLftJoint = new ModelRenderer(this, 0, 0);
		this.earTuffLftJoint.setRotationPoint(1.0F, -1.4F, -1.0F);
		this.earTuffLftJoint.addBox(-0.8F, -1.8F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earTuffLftJoint, 0.10471975511965977F, 0.0F, 1.3962634015954636F);
		this.earTuffLft[earTuffNumber] = new ModelRenderer(this, 23, 14); // earTuffLft1
		this.earTuffLft[earTuffNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earTuffLft[earTuffNumber].addBox(-0.8F, -1.8F, -0.5F, 2, 2, 1, 0.0F);
		this.earTuffLftInfo[earTuffNumber] = new PartInfo(this.earTuffLft[earTuffNumber]);
		earTuffNumber++;
		this.earTuffLft[earTuffNumber] = new ModelRenderer(this, 23, 12); // earTuffLft2
		this.earTuffLft[earTuffNumber].setRotationPoint(0.2F, -1.5F, 0.0F);
		this.earTuffLft[earTuffNumber].addBox(-1.0F, -0.8F, -0.51F, 2, 1, 1, 0.0F);
		this.earTuffLftInfo[earTuffNumber] = new PartInfo(this.earTuffLft[earTuffNumber]);
		earTuffNumber++;
		this.earTuffLft[earTuffNumber] = new ModelRenderer(this, 24, 10); // earTuffLft3
		this.earTuffLft[earTuffNumber].setRotationPoint(0.0F, -0.7F, 0.0F);
		this.earTuffLft[earTuffNumber].addBox(-0.5F, -0.8F, -0.5F, 1, 1, 1, 0.0F);
		this.earTuffLftInfo[earTuffNumber] = new PartInfo(this.earTuffLft[earTuffNumber]);
		earTuffNumber++;
		this.earTuffLft[earTuffNumber] = new ModelRenderer(this, 24, 8); // earTuffLft4
		this.earTuffLft[earTuffNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earTuffLft[earTuffNumber].addBox(-0.5F, -0.8F, -0.51F, 1, 1, 1, 0.0F);
		this.earTuffLftInfo[earTuffNumber] = new PartInfo(this.earTuffLft[earTuffNumber]);

		earTuffNumber = 0;
		this.earTuffRtJoint = new ModelRenderer(this, 0, 0);
		this.earTuffRtJoint.setRotationPoint(-1.0F, -1.4F, -1.0F);
		this.earTuffRtJoint.addBox(-0.8F, -1.8F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earTuffRtJoint, 0.10471975511965977F, 0.0F, -1.3962634015954636F);
		this.earTuffRt[earTuffNumber] = new ModelRenderer(this, 14, 14); // earTuffRt1
		this.earTuffRt[earTuffNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earTuffRt[earTuffNumber].addBox(-1.2F, -1.8F, -0.5F, 2, 2, 1, 0.0F);
		this.earTuffRtInfo[earTuffNumber] = new PartInfo(this.earTuffRt[earTuffNumber]);
		earTuffNumber++;
		this.earTuffRt[earTuffNumber] = new ModelRenderer(this, 14, 12); // earTuffRt2
		this.earTuffRt[earTuffNumber].setRotationPoint(-0.2F, -1.5F, 0.0F);
		this.earTuffRt[earTuffNumber].addBox(-1.0F, -0.8F, -0.51F, 2, 1, 1, 0.0F);
		this.earTuffRtInfo[earTuffNumber] = new PartInfo(this.earTuffRt[earTuffNumber]);
		earTuffNumber++;
		this.earTuffRt[earTuffNumber] = new ModelRenderer(this, 15, 10); // earTuffRt3
		this.earTuffRt[earTuffNumber].setRotationPoint(0.0F, -0.7F, 0.0F);
		this.earTuffRt[earTuffNumber].addBox(-0.5F, -0.8F, -0.5F, 1, 1, 1, 0.0F);
		this.earTuffRtInfo[earTuffNumber] = new PartInfo(this.earTuffRt[earTuffNumber]);
		earTuffNumber++;
		this.earTuffRt[earTuffNumber] = new ModelRenderer(this, 15, 8); // earTuffRt4
		this.earTuffRt[earTuffNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earTuffRt[earTuffNumber].addBox(-0.5F, -0.8F, -0.51F, 1, 1, 1, 0.0F);
		this.earTuffRtInfo[earTuffNumber] = new PartInfo(this.earTuffRt[earTuffNumber]);

		int tailNumber = 0;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail1Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, -0.2F, 1.6F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 41, 9); // tail1
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 2, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail2Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.4F);
		this.tail[tailNumber][jointNumber].addBox(-1.0F, -1.0F, -0.5F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 40, 13); // tail2
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-1.0F, -1.0F, -0.5F, 2, 2, 2, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail3Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.8F);
		this.tail[tailNumber][jointNumber].addBox(-1.5F, -1.5F, -0.5F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 39, 18); // tail3
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-1.5F, -1.5F, -0.5F, 3, 3, 2, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail4Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.3F);
		this.tail[tailNumber][jointNumber].addBox(-1.0F, -1.0F, -0.7F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 40, 24); // tail4
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-1.0F, -1.0F, -0.7F, 2, 2, 2, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 0); // tail5Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.1F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.6F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 41, 29); // tail5
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.6F, 1, 1, 2, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);

		this.body.addChild(this.bodyTuffLft);
		this.body.addChild(this.bodyTuffRt);
		this.body.addChild(this.legBckLft[0][0]);
		this.body.addChild(this.legBckRt[0][0]);
		this.body.addChild(this.legFntLft[0][0]);
		this.body.addChild(this.legFntRt[0][0]);
		this.body.addChild(this.neckJoint);
		this.body.addChild(this.tail[0][0]);

		this.earLftJoint.addChild(this.earLft);
		this.earRtJoint.addChild(this.earRt);

		this.earTuffLftJoint.addChild(this.earTuffLft[0]);
		this.earTuffRtJoint.addChild(this.earTuffRt[0]);

		for(int i = 0; i < earTuffLft.length - 1; i++)
		{
			this.earTuffLft[i].addChild(this.earTuffLft[i + 1]);
			this.earTuffRt[i].addChild(this.earTuffRt[i + 1]);
		}

		this.neckJoint.addChild(this.neck);
		this.neck.addChild(this.headJoint);

		this.headJoint.addChild(this.head);
		this.head.addChild(this.cheekTuffLft);
		this.head.addChild(this.cheekTuffRt);
		this.head.addChild(this.earLftJoint);
		this.head.addChild(this.earRtJoint);
		this.head.addChild(this.earTuffLftJoint);
		this.head.addChild(this.earTuffRtJoint);
		this.head.addChild(this.eyeLft);
		this.head.addChild(this.eyeRt);
		this.head.addChild(this.nose);

		this.legFntLft[0][0].addChild(this.legFntLft[0][1]);
		this.legFntLft[0][1].addChild(this.legFntLft[1][0]);
		this.legFntLft[1][0].addChild(this.legFntLft[1][1]);

		this.legFntRt[0][0].addChild(this.legFntRt[0][1]);
		this.legFntRt[0][1].addChild(this.legFntRt[1][0]);
		this.legFntRt[1][0].addChild(this.legFntRt[1][1]);

		this.legBckLft[0][0].addChild(this.legBckLft[0][1]);
		this.legBckLft[0][1].addChild(this.legBckLft[1][0]);
		this.legBckLft[1][0].addChild(this.legBckLft[1][1]);

		this.legBckRt[0][0].addChild(this.legBckRt[0][1]);
		this.legBckRt[0][1].addChild(this.legBckRt[1][0]);
		this.legBckRt[1][0].addChild(this.legBckRt[1][1]);

		for(int i = 0; i < tail.length - 1; i++)
		{
			this.tail[i][0].addChild(this.tail[i][1]);
			this.tail[i][1].addChild(this.tail[i + 1][0]);
		}

		this.tail[4][0].addChild(this.tail[4][1]);

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

		animateBody((EntityFoxcraftFennekin)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, idleDampener, verticleVelocity);
		animateHead((EntityFoxcraftFennekin)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, idleDampener, verticleVelocity);
		animateLegs((EntityFoxcraftFennekin)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, idleDampener, verticleVelocity);
		animateTail((EntityFoxcraftFennekin)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, idleDampener, angularVelocity, verticleVelocity);

		deployAnimations();
	}

	public void resetPartInfos()
	{
		neckJointInfo.resetNewAngles();
		neckInfo.resetNewAngles();
		headJointInfo.resetNewAngles();
		headInfo.resetNewAngles();

		bodyInfo.resetNewAngles();
		bodyInfo.resetNewPnt();

		cheekTuffRtInfo.resetNewAngles();
		cheekTuffLftInfo.resetNewAngles();

		earLftInfo.resetNewAngles();
		earRtInfo.resetNewAngles();

		for(int i = 0; i < earTuffLft.length; i++)
		{
			earTuffLftInfo[i].resetNewAngles();
			earTuffRtInfo[i].resetNewAngles();
		}

		for(int i = 0; i < legFntRt.length; i++)
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

		for(int i = 0; i < tail.length; i++)
		{
			tailInfo[i][0].resetNewAngles();
			tailInfo[i][1].resetNewAngles();
		}
	}

	public void animateBody(EntityFoxcraftFennekin entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener, float verticalVelocity)
	{
		if(!entity.isSitting())
		{
			float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
			float runCycleInterval = (RUN_FREQUENCY * entity.getGravityFactor() * distanceMoved % (2 * PI))/(2 * PI);

			float bodyWalkAngle = (float)Math.toRadians(10);
			float bodyRunAngle = (float)Math.toRadians(16);

			float walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI + PI/2) * bodyWalkAngle * (1 - horzVelocity)
					+ bodyWalkAngle * (1 - horzVelocity);
			float runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI + PI/2) * bodyRunAngle * horzVelocity
					+ bodyRunAngle * horzVelocity;

			float bodyJumpAngle = (float)Math.toRadians(-55);
			float verticalVelocityChangeAngle = (float)Math.toRadians(10);

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

	public void animateHead(EntityFoxcraftFennekin entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener, float verticalVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, neckJointInfo);

		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockBody();

		float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
		float runCycleInterval = (RUN_FREQUENCY * entity.getGravityFactor() * distanceMoved % (2 * PI))/(2 * PI);

		float walkAngle = (float)Math.toRadians(4);
		float runAngle = (float)Math.toRadians(8);

		float walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI) * walkAngle * (1 - horzVelocity)
				+ walkAngle * (1 - horzVelocity);
		float runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI) * runAngle * horzVelocity
				+ runAngle * horzVelocity;

		float newVerticalVelocity = 1.5F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;

		float fallingEarAngle = (float)Math.toRadians(10);

		float idleAmplitudeX = 0.1F;

		if(entity.isSitting())
		{
			idleAmplitudeX *= 0.5F;
		}
		else
		{
			neckInfo.setNewRotateX(neckInfo.getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocity);

			/* ******************* */
			walkAngle = (float)Math.toRadians(2);
			runAngle = (float)Math.toRadians(4);

			walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI) * walkAngle * (1 - horzVelocity)
					+ walkAngle * (1 - horzVelocity);
			runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI) * runAngle * horzVelocity
					+ runAngle * horzVelocity;

			for(int i = 0; i < earTuffLft.length; i++)
			{
				earTuffLftInfo[i].setNewRotateZ(earTuffLftInfo[i].getNewRotateZ() + ((walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity))
						+ fallingEarAngle * newVerticalVelocity));
				earTuffRtInfo[i].setNewRotateZ(earTuffRtInfo[i].getNewRotateZ() - ((walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity))
						+ fallingEarAngle * newVerticalVelocity));
			}
		}

		float angleChangeX = -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeX + idleAmplitudeX;

		neckInfo.setNewRotateX(neckInfo.getNewRotateX() + angleChangeX);

		JointAnimation.reverseJointRotatesChange(neckInfo, headJointInfo);

		PartAnimate.headAnimateInfoOnly(headInfo, yawHeadOffsetDifference, pitchRotation);

		/* ******* Ears ******** */
		float moveAmplitudeZ = (float)Math.toRadians(65);

		walkAngle = (float)Math.toRadians(4);
		runAngle = (float)Math.toRadians(8);

		walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI) * walkAngle * (1 - horzVelocity)
				+ walkAngle * (1 - horzVelocity);
		runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI) * runAngle * horzVelocity
				+ runAngle * horzVelocity;

		float moveAngleZ = moveAmplitudeZ * (walkCycleAngleChange + runCycleAngleChange) * horzVelocity;

		float idleAmplitudeZ = 0.15F;

		fallingEarAngle = (float)Math.toRadians(40);

		// + (float)Math.toRadians(45)
		float angleChangeZ = MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI + PI) * idleAmplitudeZ;

		earRtInfo.setNewRotateZ(earRtInfo.getNewRotateZ() - (angleChangeZ + moveAngleZ + fallingEarAngle * newVerticalVelocity));
		earLftInfo.setNewRotateZ(earLftInfo.getNewRotateZ() + (angleChangeZ + moveAngleZ + fallingEarAngle * newVerticalVelocity));

		if(entity.isTamed())
		{
			float healthChangeAngle = (float)Math.toRadians(-20);

			earLftInfo.setNewRotateZ(earLftInfo.getNewRotateZ() - (1F - entity.getHealthPercent()) * healthChangeAngle - (float)Math.toRadians(15));
			earRtInfo.setNewRotateZ(earRtInfo.getNewRotateZ() + (1F - entity.getHealthPercent()) * healthChangeAngle + (float)Math.toRadians(15));
		}
	}

	public void animateLegs(EntityFoxcraftFennekin entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener, float verticalVelocity)
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
			JointAnimation.reverseJointRotatesChange(bodyInfo, legFntRtInfo[0][0]);
			JointAnimation.reverseJointRotatesChange(bodyInfo, legFntLftInfo[0][0]);
			JointAnimation.reverseJointRotatesChange(bodyInfo, legBckRtInfo[0][0]);
			JointAnimation.reverseJointRotatesChange(bodyInfo, legBckLftInfo[0][0]);

			//( 1 - (Math.pow(Math.E, -0.4F * (float)(i + 1))));

			legFntRtInfo[0][0].setNewRotateX(legFntRtInfo[0][0].getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legFntRtModifier, horzVelocity, frequency, amplitude) * (1F - Math.abs(newVerticalVelocity)) + fallingLegAngle * fallingModifier);
			legFntLftInfo[0][0].setNewRotateX(legFntLftInfo[0][0].getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, frequency, amplitude) * (1F - Math.abs(newVerticalVelocity)) + fallingLegAngle * fallingModifier);
			legBckRtInfo[0][0].setNewRotateX(legBckRtInfo[0][0].getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legBckRtModifier, horzVelocity, frequency, amplitude) * (1F - Math.abs(newVerticalVelocity)));
			legBckLftInfo[0][0].setNewRotateX(legBckLftInfo[0][0].getNewRotateX() + PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legBckLftModifier, horzVelocity, frequency, amplitude) * (1F - Math.abs(newVerticalVelocity)));
		}
		else
		{
			for(int i = 0; i < legFntRtInfo.length; i++)
			{
				legFntRtInfo[i][0].setNewRotateX(legFntRtSitInfo[i][0].getNewRotateX());
				legFntRtInfo[i][1].setNewRotateX(legFntRtSitInfo[i][1].getNewRotateX());
				legFntLftInfo[i][0].setNewRotateX(legFntLftSitInfo[i][0].getNewRotateX());
				legFntLftInfo[i][1].setNewRotateX(legFntLftSitInfo[i][1].getNewRotateX());
			}

			for(int i = 0; i < legBckRtInfo.length; i++)
			{
				legBckRtInfo[i][0].setNewRotateX(legBckRtSitInfo[i][0].getNewRotateX());
				legBckRtInfo[i][1].setNewRotateX(legBckRtSitInfo[i][1].getNewRotateX());
				legBckLftInfo[i][0].setNewRotateX(legBckLftSitInfo[i][0].getNewRotateX());
				legBckLftInfo[i][1].setNewRotateX(legBckLftSitInfo[i][1].getNewRotateX());
			}
		}
	}

	public void animateTail(EntityFoxcraftFennekin entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener, float angularVelocity, float verticalVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, tailInfo[0][0]);

		float yawChangeAngle = -(float)Math.toRadians(15);
		float verticalVelocityChangeAngle = (float)Math.toRadians(-9);
		float healthChangeAngle = entity.isTamed() ? (float)Math.toRadians(-8) : 0;

		float bodyJumpAngle = (float)Math.toRadians(-65);

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

			// Health Animation
			angleChangeX += (entity.isTamed() ? (1F - entity.getHealthPercent()) * healthChangeAngle : 0);

			// Part Number Dampener
			angleChangeX *= (1F - (Math.pow(Math.E, -0.95F * (float)(i + 1))));
			angleChangeY *= (1F - (Math.pow(Math.E, -0.95F * (float)(i + 1))));

			tailInfo[i][1].setNewRotateX(tailInfo[i][1].getNewRotateX() + angleChangeX);
			tailInfo[i][1].setNewRotateY(tailInfo[i][1].getNewRotateY() + angleChangeY);
		}

		if(entity.isSitting())
		{
			tailInfo[0][1].setNewRotateX(tailInfo[0][1].getNewRotateX() + (float)Math.toRadians(55));
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

		this.animationDeployer.rotate(cheekTuffLft, cheekTuffLftInfo.getNewRotates());
		this.animationDeployer.rotate(cheekTuffRt, cheekTuffRtInfo.getNewRotates());

		this.animationDeployer.rotate(earLft, earLftInfo.getNewRotates());
		this.animationDeployer.rotate(earRt, earRtInfo.getNewRotates());

		for(int i = 0; i < earTuffLft.length; i++)
		{
			this.animationDeployer.rotate(earTuffLft[i], earTuffLftInfo[i].getNewRotates());
			this.animationDeployer.rotate(earTuffRt[i], earTuffRtInfo[i].getNewRotates());
		}

		for(int i = 0; i < legFntRtInfo.length; i++)
		{
			this.animationDeployer.rotate(legFntRt[i][0], legFntRtInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(legFntRt[i][1], legFntRtInfo[i][1].getNewRotates());
			this.animationDeployer.rotate(legFntLft[i][0], legFntLftInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(legFntLft[i][1], legFntLftInfo[i][1].getNewRotates());
		}

		for(int i = 0; i < legBckRtInfo.length; i++)
		{
			this.animationDeployer.rotate(legBckRt[i][0], legBckRtInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(legBckRt[i][1], legBckRtInfo[i][1].getNewRotates());
			this.animationDeployer.rotate(legBckLft[i][0], legBckLftInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(legBckLft[i][1], legBckLftInfo[i][1].getNewRotates());
		}

		for(int i = 0; i < tail.length; i++)
		{
			this.animationDeployer.rotate(tail[i][0], tailInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(tail[i][1], tailInfo[i][1].getNewRotates());
		}

		this.animationDeployer.applyChanges();
	}
}