package fuzzyacornindustries.kindredlegacy.client.model.mob;

import fuzzyacornindustries.kindredlegacy.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.animation.JointAnimation;
import fuzzyacornindustries.kindredlegacy.animation.PartAnimate;
import fuzzyacornindustries.kindredlegacy.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.entity.mob.IMobMotionTracker;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityDemonVulpix;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelDemonVulpix extends ModelBase 
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public final float RUN_FREQUENCY = 1.2F;
	public final float WALK_FREQUENCY = 0.3F;
	public final float SNEAK_VELOCITY = 0.35F;

	public ModelRenderer body;
	public PartInfo bodyInfo;

	public ModelRenderer neckJoint;
	public ModelRenderer neck;
	public PartInfo neckJointInfo;
	public PartInfo neckInfo;

	public ModelRenderer legFntRt;
	public ModelRenderer legFntLft;
	public ModelRenderer legBckRt;
	public ModelRenderer legBckLft;
	public PartInfo legFntRtInfo;
	public PartInfo legFntLftInfo;
	public PartInfo legBckRtInfo;
	public PartInfo legBckLftInfo;

	public ModelRenderer headJoint;
	public ModelRenderer head;
	public PartInfo headJointInfo;
	public PartInfo headInfo;

	public ModelRenderer mask;
	public ModelRenderer maskMuzzle;

	public ModelRenderer earRtJoint;
	public ModelRenderer earRt;
	public ModelRenderer earLftJoint;
	public ModelRenderer earLft;
	public PartInfo earRtJointInfo;
	public PartInfo earRtInfo;
	public PartInfo earLftJointInfo;
	public PartInfo earLftInfo;

	public ModelRenderer tail[][][] = new ModelRenderer[6][3][2];
	public PartInfo tailInfo[][][] = new PartInfo[tail.length][tail[0].length][tail[0][0].length];

	public ModelDemonVulpix() 
	{
		this.textureWidth = 64;
		this.textureHeight = 64;

		int jointNumber = 0;
		int partNumber = 1;

		this.body = new ModelRenderer(this, 12, 5);
		this.body.setRotationPoint(0.0F, 19.0F, 0.0F);
		this.body.addBox(-1.5F, -1.5F, -3.0F, 3, 3, 6, 0.0F);
		bodyInfo = new PartInfo(body);

		this.neckJoint = new ModelRenderer(this, 12, 0);
		this.neckJoint.setRotationPoint(0.0F, -1.0F, -2.5F);
		this.neckJoint.addBox(-1.0F, -1.5F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(neckJoint, 0.7853981633974483F, 0.0F, 0.0F);
		neckJointInfo = new PartInfo(neckJoint);
		this.neck = new ModelRenderer(this, 12, 0);
		this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.neck.addBox(-1.0F, -1.5F, -1.0F, 2, 2, 2, 0.0F);
		neckInfo = new PartInfo(neck);

		this.legFntRt = new ModelRenderer(this, 25, 0);
		this.legFntRt.setRotationPoint(-0.8F, 1.0F, -1.8F);
		this.legFntRt.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
		legFntRtInfo = new PartInfo(legFntRt);
		this.legFntLft = new ModelRenderer(this, 30, 0);
		this.legFntLft.setRotationPoint(0.8F, 1.0F, -1.8F);
		this.legFntLft.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1, 0.0F);
		legFntLftInfo = new PartInfo(legFntLft);
		this.legBckRt = new ModelRenderer(this, 35, 0);
		this.legBckRt.setRotationPoint(-0.8F, 1.0F, 2.0F);
		this.legBckRt.addBox(-0.5F, -0.5F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(legBckRt, -0.20943951023931953F, -0.0F, 0.0F);
		this.legBckRt.setTextureOffset(35, 5).addBox(-0.4F, 2.0F, 0.03F, 1, 2, 1, 0.0F); // legBckRtEnd
		legBckRtInfo = new PartInfo(legBckRt);
		this.legBckLft = new ModelRenderer(this, 40, 0);
		this.legBckLft.setRotationPoint(0.8F, 1.0F, 2.0F);
		this.legBckLft.addBox(-0.5F, -0.5F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(legBckLft, -0.20943951023931953F, -0.0F, 0.0F);
		this.legBckLft.setTextureOffset(40, 5).addBox(-0.6F, 2.0F, 0.03F, 1, 2, 1, 0.0F); // legBckLftEnd
		legBckLftInfo = new PartInfo(legBckLft);

		this.headJoint = new ModelRenderer(this, 0, 2);
		this.headJoint.setRotationPoint(0.0F, -1.0F, 0.0F);
		this.headJoint.addBox(-2.0F, -3.5F, -2.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(headJoint, -0.7853981633974483F, 0.0F, 0.0F);
		headJointInfo = new PartInfo(headJoint);
		this.head = new ModelRenderer(this, 0, 2);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-2.0F, -3.5F, -2.0F, 4, 4, 3, 0.0F);
		headInfo = new PartInfo(head);

		this.mask = new ModelRenderer(this, 9, 15);
		this.mask.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.mask.addBox(-2.5F, -3.2F, -1.0F, 5, 2, 4, 0.0F);
		this.setRotateAngle(mask, 1.3089969389957472F, 0.0F, 0.0F);
		this.maskMuzzle = new ModelRenderer(this, 11, 23);
		this.maskMuzzle.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.maskMuzzle.addBox(-1.0F, -2.5F, -4.0F, 2, 2, 2, 0.0F);
		this.setRotateAngle(maskMuzzle, 0.2617993877991494F, 0.0F, 0.0F);
		this.maskMuzzle.setTextureOffset(18, 22).addBox(-0.5F, -2.4F, -4.5F, 1, 1, 1, 0.0F); // maskMuzzleEnd

		this.earRtJoint = new ModelRenderer(this, 0, 10);
		this.earRtJoint.setRotationPoint(-1.5F, -3.0F, 0.0F);
		this.earRtJoint.addBox(-1.0F, -1.5F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earRtJoint, 0.0F, 0.0F, -0.3490658503988659F);
		earRtJointInfo = new PartInfo(earRtJoint);
		this.earRt = new ModelRenderer(this, 0, 10);
		this.earRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earRt.addBox(-1.0F, -1.5F, -0.5F, 2, 2, 1, 0.0F);
		this.earRt.setTextureOffset(7, 10).addBox(-0.5F, -2.3F, -0.49F, 1, 1, 1, 0.0F); // earRtTip
		earRtInfo = new PartInfo(earRt);

		this.earLftJoint = new ModelRenderer(this, 0, 14);
		this.earLftJoint.setRotationPoint(1.5F, -3.0F, 0.0F);
		this.earLftJoint.addBox(-1.0F, -1.5F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earLftJoint, 0.0F, 0.0F, 0.3490658503988659F);
		earLftJointInfo = new PartInfo(earLftJoint);
		this.earLft = new ModelRenderer(this, 0, 14);
		this.earLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earLft.addBox(-1.0F, -1.5F, -0.5F, 2, 2, 1, 0.0F);
		this.earLft.setTextureOffset(7, 14).addBox(-0.5F, -2.3F, -0.49F, 1, 1, 1, 0.0F); // earLftTip
		earLftInfo = new PartInfo(earLft);

		int tailNumber = 0;
		int tailPartNumber = 0;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 38); // tail1BaseJoint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.5F, -0.8F, 2.7F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.5F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.0F, 0.8726646259971648F, 0.0F);
		tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 0, 38); // tail1Base
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 0, 33); // tail1MidJoint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.8F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 0, 33); // tail1Mid
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 3, 0.0F);
		tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 1, 28); // tail1EndJoint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.7F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 1, 28); // tail1End
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.7F, -0.2F, 1, 2, 2, 0.0F);
		tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(tail[tailNumber][tailPartNumber][partNumber]);
		tailNumber++;
		tailPartNumber = 0;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 9, 38); // tail2BaseJoint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.3F, -0.8F, 2.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.5F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.0F, 0.5235987755982988F, 0.0F);
		tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 9, 38); // tail2Base
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 9, 33); // tail2MidJoint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.8F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 9, 33); // tail2Mid
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 3, 0.0F);
		tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 10, 28); // tail2EndJoint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.7F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 10, 28); // tail2End
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.7F, -0.2F, 1, 2, 2, 0.0F);
		tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(tail[tailNumber][tailPartNumber][partNumber]);
		tailNumber++;
		tailPartNumber = 0;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 18, 38); // tail3BaseJoint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.1F, -0.7F, 2.3F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.5F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.0F, 0.17453292519943295F, 0.0F);
		tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 18, 38); // tail3Base
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 18, 33); // tail3MidJoint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.8F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 18, 33); // tail3Mid
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 3, 0.0F);
		tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 19, 28); // tail3EndJoint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 19, 28); // tail3End
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 2, 2, 0.0F);
		tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(tail[tailNumber][tailPartNumber][partNumber]);
		tailNumber++;
		tailPartNumber = 0;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 27, 38); // tail4BaseJoint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(-0.1F, -0.7F, 2.3F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.5F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.0F, -0.17453292519943295F, 0.0F);
		tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 27, 38); // tail4Base
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 27, 33); // tail4MidJoint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.8F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 27, 33); // tail4Mid
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 3, 0.0F);
		tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 28, 28); // tail4EndJoint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 28, 28); // tail4End
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 2, 2, 0.0F);
		tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(tail[tailNumber][tailPartNumber][partNumber]);
		tailNumber++;
		tailPartNumber = 0;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 36, 38); // tail5BaseJoint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(-0.3F, -0.7F, 2.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.5F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.0F, -0.5235987755982988F, 0.0F);
		tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 36, 38); // tail5Base
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 36, 33); // tail5MidJoint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.8F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 36, 33); // tail5Mid
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 3, 0.0F);
		tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 37, 28); // tail5EndJoint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.5F, 0.0F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 37, 28); // tail5End
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 2, 2, 0.0F);
		tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(tail[tailNumber][tailPartNumber][partNumber]);
		tailNumber++;
		tailPartNumber = 0;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 45, 38); // tail6BaseJoint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(-0.5F, -0.7F, 2.7F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.5F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][tailPartNumber][jointNumber], 0.0F, -1.0471975511965976F, 0.0F);
		tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 45, 38); // tail6Base
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 45, 33); // tail6MidJoint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.8F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 45, 33); // tail6Mid
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 3, 0.0F);
		tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(tail[tailNumber][tailPartNumber][partNumber]);
		tailPartNumber++;
		this.tail[tailNumber][tailPartNumber][jointNumber] = new ModelRenderer(this, 46, 28); // tail6EndJoint
		this.tail[tailNumber][tailPartNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 2.5F);
		this.tail[tailNumber][tailPartNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][tailPartNumber][jointNumber] = new PartInfo(tail[tailNumber][tailPartNumber][jointNumber]);
		this.tail[tailNumber][tailPartNumber][partNumber] = new ModelRenderer(this, 46, 28); // tail6End
		this.tail[tailNumber][tailPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][tailPartNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 2, 2, 0.0F);
		tailInfo[tailNumber][tailPartNumber][partNumber] = new PartInfo(tail[tailNumber][tailPartNumber][partNumber]);

		this.body.addChild(this.legFntLft);
		this.body.addChild(this.legFntRt);
		this.body.addChild(this.legBckLft);
		this.body.addChild(this.legBckRt);

		this.body.addChild(this.neckJoint);
		this.body.addChild(this.tail[0][0][0]); // tail1BaseJoint
		this.body.addChild(this.tail[1][0][0]); // tail2BaseJoint
		this.body.addChild(this.tail[2][0][0]); // tail3BaseJoint
		this.body.addChild(this.tail[3][0][0]); // tail4BaseJoint
		this.body.addChild(this.tail[4][0][0]); // tail5BaseJoint
		this.body.addChild(this.tail[5][0][0]); // tail6BaseJoint

		this.neckJoint.addChild(this.neck);
		this.neck.addChild(this.headJoint);

		this.headJoint.addChild(this.head);
		this.head.addChild(this.earRtJoint);
		this.head.addChild(this.earLftJoint);
		this.head.addChild(this.mask);
		this.head.addChild(this.maskMuzzle);

		this.earLftJoint.addChild(this.earLft);
		this.earRtJoint.addChild(this.earRt);

		this.tail[0][0][0].addChild(this.tail[0][0][1]); // tail1BaseJoint * tail1Base
		this.tail[0][0][1].addChild(this.tail[0][1][0]); // tail1Base      * tail1MidJoint
		this.tail[0][1][0].addChild(this.tail[0][1][1]); // tail1MidJoint * tail1Mid
		this.tail[0][1][1].addChild(this.tail[0][2][0]); // tail1Mid       * tail1EndJoint
		this.tail[0][2][0].addChild(this.tail[0][2][1]); // tail1EndJoint  * tail1End

		this.tail[1][0][0].addChild(this.tail[1][0][1]); // tail2BaseJoint * tail2Base
		this.tail[1][0][1].addChild(this.tail[1][1][0]); // tail2Base      * tail2MidJoint
		this.tail[1][1][0].addChild(this.tail[1][1][1]); // tail2MidJoint  * tail2Mid
		this.tail[1][1][1].addChild(this.tail[1][2][0]); // tail2Mid       * tail2EndJoint
		this.tail[1][2][0].addChild(this.tail[1][2][1]); // tail2EndJoint  * tail2End

		this.tail[2][0][0].addChild(this.tail[2][0][1]); // tail3BaseJoint * tail3Base
		this.tail[2][0][1].addChild(this.tail[2][1][0]); // tail3Base      * tail3MidJoint
		this.tail[2][1][0].addChild(this.tail[2][1][1]); // tail3MidJoint  * tail3Mid
		this.tail[2][1][1].addChild(this.tail[2][2][0]); // tail3Mid       * tail3EndJoint
		this.tail[2][2][0].addChild(this.tail[2][2][1]); // tail3EndJoint  * tail3End

		this.tail[3][0][0].addChild(this.tail[3][0][1]); // tail4BaseJoint * tail4Base
		this.tail[3][0][1].addChild(this.tail[3][1][0]); // tail4Base      * tail4MidJoint
		this.tail[3][1][0].addChild(this.tail[3][1][1]); // tail4MidJoint  * tail4Mid
		this.tail[3][1][1].addChild(this.tail[3][2][0]); // tail4Mid       * tail4EndJoint
		this.tail[3][2][0].addChild(this.tail[3][2][1]); // tail4EndJoint  * tail4End

		this.tail[4][0][0].addChild(this.tail[4][0][1]); // tail5BaseJoint * tail5Base
		this.tail[4][0][1].addChild(this.tail[4][1][0]); // tail5Base      * tail5MidJoint
		this.tail[4][1][0].addChild(this.tail[4][1][1]); // tail5MidJoint  * tail5Mid
		this.tail[4][1][1].addChild(this.tail[4][2][0]); // tail5Mid       * tail5EndJoint
		this.tail[4][2][0].addChild(this.tail[4][2][1]); // tail5EndJoint  * tail5End

		this.tail[5][0][0].addChild(this.tail[5][0][1]); // tail6BaseJoint * tail6Base
		this.tail[5][0][1].addChild(this.tail[5][1][0]); // tail6Base      * tail6MidJoint
		this.tail[5][1][0].addChild(this.tail[5][1][1]); // tail6MidJoint  * tail6Mid
		this.tail[5][1][1].addChild(this.tail[5][2][0]); // tail6Mid       * tail6EndJoint
		this.tail[5][2][0].addChild(this.tail[5][2][1]); // tail6EndJoint  * tail6End

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

		animateBody((EntityDemonVulpix)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity);
		animateHead((EntityDemonVulpix)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity);
		animateLegs((EntityDemonVulpix)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity);
		animateTails((EntityDemonVulpix)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, verticalVelocity, angularVelocity);

		deployAnimations();
	}

	public void resetPartInfos()
	{
		bodyInfo.resetNewAngles();
		bodyInfo.resetNewPnt();
		
		neckJointInfo.resetNewAngles();
		neckInfo.resetNewAngles();
		headJointInfo.resetNewAngles();
		headInfo.resetNewAngles();

		legFntRtInfo.resetNewAngles();
		legFntLftInfo.resetNewAngles();
		legBckRtInfo.resetNewAngles();
		legBckLftInfo.resetNewAngles();

		earRtInfo.resetNewAngles();
		earLftInfo.resetNewAngles();

		for(int i = 0; i < tail.length; i++)
		{
			for(int j = 0; j < tail[i].length; j++)
			{
				tailInfo[i][j][0].resetNewAngles();
				tailInfo[i][j][1].resetNewAngles();
			}
		}
	}

	public void animateBody(EntityDemonVulpix entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity)
	{		
		float walkCycleInterval = ((WALK_FREQUENCY * distanceMoved + PI) % (2 * PI))/(2 * PI);
		float runCycleInterval = ((RUN_FREQUENCY * distanceMoved + PI) % (2 * PI))/(2 * PI);

		float bodyWalkAngle = (float)Math.toRadians(21);
		float bodyRunAngle = (float)Math.toRadians(34);

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

		float maximumChangeY = -1.0F;
		float runCyclePointChange = (MathHelper.cos(2F * runCycleInterval * PI + PI) * maximumChangeY + maximumChangeY) * horzVelocity;

		bodyInfo.setNewPointY(bodyInfo.getNewPointY() + runCyclePointChange * horzVelocity * (1F - Math.abs(newVerticalVelocity)));
	}

	public void animateHead(EntityDemonVulpix entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity)
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

	public void animateLegs(EntityDemonVulpix entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity)
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

	public void animateTails(EntityDemonVulpix entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float verticalVelocity, float angularVelocity)
	{
		for(int i = 0; i < tail.length; i++)
		{
			JointAnimation.reverseJointRotatesChange(bodyInfo, tailInfo[i][0][0]);

			float yawChangeAngle = -(float)Math.toRadians(18);
			float verticalVelocityChangeAngle = (float)Math.toRadians(-35);

			float bodyJumpAngle = (float)Math.toRadians(-55);

			float newHorzVelocity = 1.5F * Math.abs(horzVelocity);

			if(newHorzVelocity > 1F)
				newHorzVelocity = 1F;
			
			float newVerticalVelocity = 1.5F * verticalVelocity;

			if(newVerticalVelocity > 1F)
				newVerticalVelocity = 1F;
			else if(newVerticalVelocity < -1F)
				newVerticalVelocity = -1F;

			float absoluteMoveVelocity = horzVelocity + Math.abs(newVerticalVelocity);

			if(absoluteMoveVelocity > 1F)
				absoluteMoveVelocity = 1F;
			
			for(int j = 0; j < tail[i].length; j++)
			{
				IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockTail(i, j);

				float idleAmplitudeX = 0.6F;
				float idleAmplitudeY = 0.15F;

				float moveAmplitude = 0.15F;

				// + (float)Math.toRadians(45)
				float angleChangeX = 0;
				float angleChangeY = 0;

				// Idle Animations
				angleChangeX += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2) * idleAmplitudeX 
						* (1F - absoluteMoveVelocity) * (1F - Math.abs(angularVelocity) * 0.5F);
				angleChangeY += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2) * idleAmplitudeY
						* (1F - absoluteMoveVelocity) * (1F - Math.abs(angularVelocity) * 0.5F);

				// Movement Animations
				float runYawChangeAngleModifier = (float)(tail[i].length - j) / (float)(tail.length - 1);
				
				float runYawChangeAngle = -(float)Math.toRadians(105) * MathHelper.cos(PI * ((float)i / (float)(tail.length - 1))) 
						* runYawChangeAngleModifier * runYawChangeAngleModifier * runYawChangeAngleModifier;
				
				angleChangeX += PartAnimate.negCosRotateAnimationAdjusted(distanceMoved - (2F * PI * ((float)i / (float)(tail.length - 1))), absoluteMoveVelocity, RUN_FREQUENCY, moveAmplitude) 
						+ verticalVelocityChangeAngle * newVerticalVelocity;
				angleChangeY += angularVelocity * yawChangeAngle + newHorzVelocity * runYawChangeAngle;

				// Part Number Dampener
				angleChangeX *= (1F - (Math.pow(Math.E, -0.95F * (float)(i + 1))));
				angleChangeY *= (1F - (Math.pow(Math.E, -0.95F * (float)(i + 1))));

				tailInfo[i][j][1].setNewRotateX(tailInfo[i][j][1].getNewRotateX() + angleChangeX);
				tailInfo[i][j][1].setNewRotateY(tailInfo[i][j][1].getNewRotateY() + angleChangeY);
			}
		}
	}

	public void deployAnimations()
	{
		this.animationDeployer.rotate(body, bodyInfo.getNewRotates());
		this.animationDeployer.move(body, bodyInfo.getNewPnt());

		this.animationDeployer.rotate(neckJoint, neckJointInfo.getNewRotates());
		this.animationDeployer.rotate(neck, neckInfo.getNewRotates());
		this.animationDeployer.rotate(headJoint, headJointInfo.getNewRotates());
		this.animationDeployer.rotate(head, headInfo.getNewRotates());

		this.animationDeployer.rotate(legFntRt, legFntRtInfo.getNewRotates());
		this.animationDeployer.rotate(legFntLft, legFntLftInfo.getNewRotates());
		this.animationDeployer.rotate(legBckRt, legBckRtInfo.getNewRotates());
		this.animationDeployer.rotate(legBckLft, legBckLftInfo.getNewRotates());

		this.animationDeployer.rotate(earLft, earLftInfo.getNewRotates());
		this.animationDeployer.rotate(earLftJoint, earLftJointInfo.getNewRotates());
		this.animationDeployer.rotate(earRt, earRtInfo.getNewRotates());
		this.animationDeployer.rotate(earRtJoint, earRtJointInfo.getNewRotates());

		for(int i = 0; i < tail.length; i++)
		{
			for(int j = 0; j < tail[i].length; j++)
			{
				for(int k = 0; k < tail[i][j].length; k++)
				{
					this.animationDeployer.rotate(tail[i][j][k], tailInfo[i][j][k].getNewRotates());
				}
			}
		}

		this.animationDeployer.applyChanges();
	}
}