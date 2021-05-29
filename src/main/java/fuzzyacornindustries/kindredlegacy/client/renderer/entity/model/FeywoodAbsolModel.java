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
import fuzzyacornindustries.kindredlegacy.common.entity.mob.IMobMotionTracker;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.FeywoodAbsolEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.TamablePokemon;
import fuzzyacornindustries.kindredlegacy.lib.action.LibraryFeywoodAbsolAttackID;
import net.minecraft.client.renderer.entity.model.TintedAgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class FeywoodAbsolModel<T extends TamablePokemon> extends TintedAgeableModel<T>
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public final float RUN_FREQUENCY = 0.6F;
	public final float WALK_FREQUENCY = 0.95F;
	public final float WALK_MAX = 0.4F;
	public final float RUN_MIN = 0.5F;

	public ModelRenderer bodyJoint;
	public ModelRenderer body;
	PartInfo bodyJointInfo;
	PartInfo bodyJointSitInfo;
	PartInfo bodyInfo;
	PartInfo bodySitInfo;

	public ModelRenderer legFntLft[][] = new ModelRenderer[2][2];
	PartInfo legFntLftInfo[][] = new PartInfo[legFntLft.length][legFntLft[0].length];
	PartInfo legFntLftSitInfo[][] = new PartInfo[legFntLft.length][legFntLft[0].length];
	public ModelRenderer legFntLftSpike;
	PartInfo legFntLftSpikeInfo;
	PartInfo legFntLftSpikeSitInfo;

	public ModelRenderer legFntRt[][] = new ModelRenderer[2][2];
	PartInfo legFntRtInfo[][] = new PartInfo[legFntRt.length][legFntRt[0].length];
	PartInfo legFntRtSitInfo[][] = new PartInfo[legFntRt.length][legFntRt[0].length];
	public ModelRenderer legFntRtSpike;
	PartInfo legFntRtSpikeInfo;
	PartInfo legFntRtSpikeSitInfo;

	public ModelRenderer legBckLft[][] = new ModelRenderer[3][2];
	PartInfo legBckLftInfo[][] = new PartInfo[legBckLft.length][legBckLft[0].length];
	PartInfo legBckLftSitInfo[][] = new PartInfo[legBckLft.length][legBckLft[0].length];
	public ModelRenderer legBckLftSpike;
	PartInfo legBckLftSpikeInfo;
	PartInfo legBckLftSpikeSitInfo;

	public ModelRenderer legBckRt[][] = new ModelRenderer[3][2];
	PartInfo legBckRtInfo[][] = new PartInfo[legBckRt.length][legBckRt[0].length];
	PartInfo legBckRtSitInfo[][] = new PartInfo[legBckRt.length][legBckRt[0].length];
	public ModelRenderer legBckRtSpike;
	PartInfo legBckRtSpikeInfo;
	PartInfo legBckRtSpikeSitInfo;

	public ModelRenderer neckJoint;
	public ModelRenderer neck;
	public ModelRenderer headJoint;
	public ModelRenderer head;
	PartInfo neckJointInfo;
	PartInfo neckJointSitInfo;
	PartInfo neckInfo;
	PartInfo headJointInfo;
	PartInfo headInfo;

	public ModelRenderer muzzle;

	public ModelRenderer headHairMid;
	public ModelRenderer headHair;
	public ModelRenderer headHairTop;

	public ModelRenderer headBang[] = new ModelRenderer[5];
	PartInfo headBangInfo[] = new PartInfo[headBang.length];

	public ModelRenderer headHornBase;
	public ModelRenderer headHorn1;
	public ModelRenderer headHornBot;
	public ModelRenderer headHorn2;
	public ModelRenderer headHornEdge1;
	public ModelRenderer headHorn3;
	public ModelRenderer headHorn4;
	public ModelRenderer headHornEdge2;
	public ModelRenderer headHorn5;
	public ModelRenderer headHornEdge3;

	public ModelRenderer tail1;
	public ModelRenderer tail2;
	public ModelRenderer tailEdge1;
	public ModelRenderer tailEdge2;
	public ModelRenderer tailEdge3;
	PartInfo tail1Info;

	public FeywoodAbsolModel() 
	{
		this.textureWidth = 64;
		this.textureHeight = 64;

		int jointNumber = 0;
		int partNumber = 1;

		this.bodyJoint = new ModelRenderer(this, 0, 0);
		this.bodyJoint.setRotationPoint(0.0F, 15.4F, 0.0F);
		this.bodyJoint.addBox(0.0F, -3.0F, -6.0F, 0, 0, 0, 0.0F);
		this.bodyJointInfo = new PartInfo(bodyJoint);
		this.body = new ModelRenderer(this, 29, 52);
		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.addBox(-2.5F, -3.0F, -6.0F, 5, 6, 6, 0.0F);
		this.body.setTextureOffset(29, 41).addBox(-2.5F, -2.8F, 0.0F, 5, 5, 6, 0.0F); // bodyBck
		this.bodyInfo = new PartInfo(body);

		this.bodyJoint.setRotationPoint(0.0F, 18.5F, 0.0F);
		this.bodyJointSitInfo = new PartInfo(bodyJoint);
		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.setRotateAngle(body, -0.7853981633974483F, 0.0F, 0.0F);
		this.bodySitInfo = new PartInfo(body);

		int legPartNumber = 0;
		this.legFntLft[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legFntLft1Joint
		this.legFntLft[legPartNumber][jointNumber].setRotationPoint(1.1F, 0.0F, -3.9F);
		this.legFntLft[legPartNumber][jointNumber].addBox(-1.0F, -1.0F, -1.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legFntLft[legPartNumber][jointNumber], 0.17453292519943295F, 0.0F, 0.0F);
		this.legFntLftInfo[legPartNumber][jointNumber] = new PartInfo(this.legFntLft[legPartNumber][jointNumber]);
		this.legFntLft[legPartNumber][partNumber] = new ModelRenderer(this, 52, 24); // legFntLft1
		this.legFntLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legFntLft[legPartNumber][partNumber].addBox(-1.0F, -1.4F, -1.5F, 3, 4, 3, 0.0F);
		this.legFntLftInfo[legPartNumber][partNumber] = new PartInfo(this.legFntLft[legPartNumber][partNumber]);
		legPartNumber++;
		this.legFntLft[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legFntLft2Joint
		this.legFntLft[legPartNumber][jointNumber].setRotationPoint(1.0F, 2.7F, -0.2F);
		this.legFntLft[legPartNumber][jointNumber].addBox(-1.0F, -1.0F, -1.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legFntLft[legPartNumber][jointNumber], -0.2617993877991494F, 0.0F, 0.0F);
		this.legFntLftInfo[legPartNumber][jointNumber] = new PartInfo(this.legFntLft[legPartNumber][jointNumber]);
		this.legFntLft[legPartNumber][partNumber] = new ModelRenderer(this, 52, 31); // legFntLft2
		this.legFntLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legFntLft[legPartNumber][partNumber].addBox(-1.99F, -1.0F, -1.5F, 3, 7, 3, 0.0F);
		this.legFntLftInfo[legPartNumber][partNumber] = new PartInfo(this.legFntLft[legPartNumber][partNumber]);
		this.legFntLftSpike = new ModelRenderer(this, 46, 40);
		this.legFntLftSpike.setRotationPoint(0.45F, 0.0F, 1.0F);
		this.legFntLftSpike.addBox(-0.5F, -0.5F, -0.4F, 1, 1, 3, 0.0F);
		this.setRotateAngle(legFntLftSpike, 0.6283185307179586F, 0.20943951023931953F, 0.0F);
		this.legFntLftSpikeInfo = new PartInfo(legFntLftSpike);

		legPartNumber = 0;
		this.legFntLft[legPartNumber][jointNumber].setRotationPoint(1.1F, 0.0F, -3.9F); // legFntLft1Joint
		this.setRotateAngle(legFntLft[legPartNumber][jointNumber], 0.7853981633974483F, 0.0F, 0.0F);
		this.legFntLftSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legFntLft[legPartNumber][jointNumber]);
		this.legFntLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legFntLft1
		this.legFntLftSitInfo[legPartNumber][partNumber] = new PartInfo(this.legFntLft[legPartNumber][partNumber]);
		legPartNumber++;
		this.legFntLft[legPartNumber][jointNumber].setRotationPoint(1.0F, 2.7F, -0.2F); // legFntLft2Joint
		this.setRotateAngle(legFntLft[legPartNumber][jointNumber], -0.2617993877991494F, 0.0F, 0.0F);
		this.legFntLftSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legFntLft[legPartNumber][jointNumber]);
		this.legFntLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legFntLft2
		this.legFntLftSitInfo[legPartNumber][partNumber] = new PartInfo(this.legFntLft[legPartNumber][partNumber]);
		this.legFntLftSpike.setRotationPoint(0.45F, 0.0F, 1.0F);
		this.setRotateAngle(legFntLftSpike, 0.6283185307179586F, 0.20943951023931953F, 0.0F);
		this.legFntLftSpikeSitInfo = new PartInfo(legFntLftSpike);

		legPartNumber = 0;
		this.legFntRt[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legFntRt1Joint
		this.legFntRt[legPartNumber][jointNumber].setRotationPoint(-1.1F, 0.0F, -3.9F);
		this.legFntRt[legPartNumber][jointNumber].addBox(-1.0F, -1.0F, -1.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legFntRt[legPartNumber][jointNumber], 0.17453292519943295F, 0.0F, 0.0F);
		this.legFntRtInfo[legPartNumber][jointNumber] = new PartInfo(this.legFntRt[legPartNumber][jointNumber]);
		this.legFntRt[legPartNumber][partNumber] = new ModelRenderer(this, 16, 24); // legFntRt1
		this.legFntRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legFntRt[legPartNumber][partNumber].addBox(-2.0F, -1.4F, -1.5F, 3, 4, 3, 0.0F);
		this.legFntRtInfo[legPartNumber][partNumber] = new PartInfo(this.legFntRt[legPartNumber][partNumber]);
		legPartNumber++;
		this.legFntRt[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legFntRt2Joint
		this.legFntRt[legPartNumber][jointNumber].setRotationPoint(-1.0F, 2.7F, -0.2F);
		this.legFntRt[legPartNumber][jointNumber].addBox(-1.0F, -1.0F, -1.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legFntRt[legPartNumber][jointNumber], -0.2617993877991494F, 0.0F, 0.0F);
		this.legFntRtInfo[legPartNumber][jointNumber] = new PartInfo(this.legFntRt[legPartNumber][jointNumber]);
		this.legFntRt[legPartNumber][partNumber] = new ModelRenderer(this, 16, 31); // legFntRt2
		this.legFntRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legFntRt[legPartNumber][partNumber].addBox(-1.01F, -1.0F, -1.5F, 3, 7, 3, 0.0F);
		this.legFntRtInfo[legPartNumber][partNumber] = new PartInfo(this.legFntRt[legPartNumber][partNumber]);
		this.legFntRtSpike = new ModelRenderer(this, 26, 40);
		this.legFntRtSpike.setRotationPoint(-0.45F, 0.0F, 1.0F);
		this.legFntRtSpike.addBox(-0.5F, -0.5F, -0.4F, 1, 1, 3, 0.0F);
		this.setRotateAngle(legFntRtSpike, 0.6283185307179586F, -0.20943951023931953F, 0.0F);
		this.legFntRtSpikeInfo = new PartInfo(legFntRtSpike);

		legPartNumber = 0;
		this.legFntRt[legPartNumber][jointNumber].setRotationPoint(-1.1F, 0.0F, -3.9F); // legFntRt1Joint
		this.setRotateAngle(legFntRt[legPartNumber][jointNumber], 0.7853981633974483F, 0.0F, 0.0F);
		this.legFntRtSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legFntRt[legPartNumber][jointNumber]);
		this.legFntRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legFntRt1
		this.legFntRtSitInfo[legPartNumber][partNumber] = new PartInfo(this.legFntRt[legPartNumber][partNumber]);
		legPartNumber++;
		this.legFntRt[legPartNumber][jointNumber].setRotationPoint(-1.0F, 2.7F, -0.2F); // legFntRt2Joint
		this.setRotateAngle(legFntRt[legPartNumber][jointNumber], -0.2617993877991494F, 0.0F, 0.0F);
		this.legFntRtSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legFntRt[legPartNumber][jointNumber]);
		this.legFntRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legFntRt2
		this.legFntRt[legPartNumber][partNumber].addBox(-1.01F, -1.0F, -1.5F, 3, 7, 3, 0.0F);
		this.legFntRtSitInfo[legPartNumber][partNumber] = new PartInfo(this.legFntRt[legPartNumber][partNumber]);
		this.legFntRtSpike.setRotationPoint(-0.45F, 0.0F, 1.0F);
		this.legFntRtSpike.addBox(-0.5F, -0.5F, -0.4F, 1, 1, 3, 0.0F);
		this.setRotateAngle(legFntRtSpike, 0.6283185307179586F, -0.20943951023931953F, 0.0F);
		this.legFntRtSpikeSitInfo = new PartInfo(legFntRtSpike);

		legPartNumber = 0;
		this.legBckLft[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legBckLft1Joint
		this.legBckLft[legPartNumber][jointNumber].setRotationPoint(2.1F, 0.0F, 4.0F);
		this.legBckLft[legPartNumber][jointNumber].addBox(-2.0F, -0.5F, -1.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legBckLft[legPartNumber][jointNumber], -0.17453292519943295F, 0.0F, 0.0F);
		this.legBckLftInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckLft[legPartNumber][jointNumber]);
		this.legBckLft[legPartNumber][partNumber] = new ModelRenderer(this, 52, 41); // legBckLft1
		this.legBckLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckLft[legPartNumber][partNumber].addBox(-2.0F, -0.8F, -1.5F, 3, 5, 3, 0.0F);
		this.legBckLftInfo[legPartNumber][partNumber] = new PartInfo(this.legBckLft[legPartNumber][partNumber]);
		legPartNumber++;
		this.legBckLft[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legBckLft2Joint
		this.legBckLft[legPartNumber][jointNumber].setRotationPoint(-0.5F, 3.1F, 0.0F);
		this.legBckLft[legPartNumber][jointNumber].addBox(-1.5F, -0.5F, -1.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legBckLft[legPartNumber][jointNumber], 1.1344640137963142F, 0.0F, 0.0F);
		this.legBckLftInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckLft[legPartNumber][jointNumber]);
		this.legBckLft[legPartNumber][partNumber] = new ModelRenderer(this, 52, 49); // legBckLft2
		this.legBckLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckLft[legPartNumber][partNumber].addBox(-1.51F, -0.5F, -1.5F, 3, 4, 3, 0.0F);
		this.legBckLftInfo[legPartNumber][partNumber] = new PartInfo(this.legBckLft[legPartNumber][partNumber]);
		legPartNumber++;
		this.legBckLft[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legBckLft3Joint
		this.legBckLft[legPartNumber][jointNumber].setRotationPoint(0.0F, 2.4F, 0.0F);
		this.legBckLft[legPartNumber][jointNumber].addBox(-1.5F, -0.6F, -1.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legBckLft[legPartNumber][jointNumber], -1.1344640137963142F, 0.0F, 0.0F);
		this.legBckLftInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckLft[legPartNumber][jointNumber]);
		this.legBckLft[legPartNumber][partNumber] = new ModelRenderer(this, 52, 56); // legBckLft3
		this.legBckLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckLft[legPartNumber][partNumber].addBox(-1.52F, -0.6F, -1.5F, 3, 5, 3, 0.0F);
		this.legBckLftInfo[legPartNumber][partNumber] = new PartInfo(this.legBckLft[legPartNumber][partNumber]);
		this.legBckLftSpike = new ModelRenderer(this, 46, 54);
		this.legBckLftSpike.setRotationPoint(0.0F, 0.0F, 1.0F);
		this.legBckLftSpike.addBox(-0.5F, -0.5F, -0.4F, 1, 1, 3, 0.0F);
		this.setRotateAngle(legBckLftSpike, 0.8726646259971648F, 0.0F, 0.0F);
		this.legBckLftSpikeInfo = new PartInfo(legBckLftSpike);

		legPartNumber = 0;
		this.legBckLft[legPartNumber][jointNumber].setRotationPoint(2.1F, 0.0F, 4.0F); // legBckLft1Joint
		this.setRotateAngle(legBckLft[legPartNumber][jointNumber], -1.2217304763960306F, 0.0F, 0.0F);
		this.legBckLftSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckLft[legPartNumber][jointNumber]);
		this.legBckLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legBckLft1
		this.setRotateAngle(legBckLft[legPartNumber][partNumber], 0.0F, 0.17453292519943295F, -0.4363323129985824F);
		this.legBckLftSitInfo[legPartNumber][partNumber] = new PartInfo(this.legBckLft[legPartNumber][partNumber]);
		legPartNumber++;
		this.legBckLft[legPartNumber][jointNumber].setRotationPoint(-0.5F, 3.1F, 0.0F); // legBckLft2Joint
		this.setRotateAngle(legBckLft[legPartNumber][jointNumber], 1.8325957145940461F, 0.0F, 0.0F);
		this.legBckLftSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckLft[legPartNumber][jointNumber]);
		this.legBckLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legBckLft2
		this.legBckLftSitInfo[legPartNumber][partNumber] = new PartInfo(this.legBckLft[legPartNumber][partNumber]);
		legPartNumber++;
		this.legBckLft[legPartNumber][jointNumber].setRotationPoint(0.0F, 2.4F, 0.0F); // legBckLft3Joint
		this.setRotateAngle(legBckLft[legPartNumber][jointNumber], -1.3962634015954636F, 0.0F, 0.0F);
		this.legBckLftSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckLft[legPartNumber][jointNumber]);
		this.legBckLft[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legBckLft3
		this.legBckLftSitInfo[legPartNumber][partNumber] = new PartInfo(this.legBckLft[legPartNumber][partNumber]);
		this.legBckLftSpike.setRotationPoint(0.0F, 0.0F, 1.0F);
		this.setRotateAngle(legBckLftSpike, 0.8726646259971648F, 0.0F, 0.0F);
		this.legBckLftSpikeSitInfo = new PartInfo(legBckLftSpike);

		legPartNumber = 0;
		this.legBckRt[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legBckRt1Joint
		this.legBckRt[legPartNumber][jointNumber].setRotationPoint(-2.1F, 0.0F, 4.0F);
		this.legBckRt[legPartNumber][jointNumber].addBox(-2.0F, -0.5F, -1.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legBckRt[legPartNumber][jointNumber], -0.17453292519943295F, 0.0F, 0.0F);
		this.legBckRtInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckRt[legPartNumber][jointNumber]);
		this.legBckRt[legPartNumber][partNumber] = new ModelRenderer(this, 16, 41); // legBckRt1
		this.legBckRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckRt[legPartNumber][partNumber].addBox(-1.0F, -0.8F, -1.5F, 3, 5, 3, 0.0F);
		this.legBckRtInfo[legPartNumber][partNumber] = new PartInfo(this.legBckRt[legPartNumber][partNumber]);
		legPartNumber++;
		this.legBckRt[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legBckRt2Joint
		this.legBckRt[legPartNumber][jointNumber].setRotationPoint(0.5F, 3.1F, 0.0F);
		this.legBckRt[legPartNumber][jointNumber].addBox(-1.5F, -0.8F, -1.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legBckRt[legPartNumber][jointNumber], 1.1344640137963142F, 0.0F, 0.0F);
		this.legBckRtInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckRt[legPartNumber][jointNumber]);
		this.legBckRt[legPartNumber][partNumber] = new ModelRenderer(this, 16, 49); // legBckRt2
		this.legBckRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckRt[legPartNumber][partNumber].addBox(-1.49F, -0.5F, -1.5F, 3, 4, 3, 0.0F);
		this.legBckRtInfo[legPartNumber][partNumber] = new PartInfo(this.legBckRt[legPartNumber][partNumber]);
		legPartNumber++;
		this.legBckRt[legPartNumber][jointNumber] = new ModelRenderer(this, 0, 0); // legBckRt3Joint
		this.legBckRt[legPartNumber][jointNumber].setRotationPoint(0.0F, 2.4F, 0.0F);
		this.legBckRt[legPartNumber][jointNumber].addBox(-1.5F, -0.6F, -1.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(legBckRt[legPartNumber][jointNumber], -1.1344640137963142F, 0.0F, 0.0F);
		this.legBckRtInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckRt[legPartNumber][jointNumber]);
		this.legBckRt[legPartNumber][partNumber] = new ModelRenderer(this, 16, 56); // legBckRt3
		this.legBckRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.legBckRt[legPartNumber][partNumber].addBox(-1.48F, -0.6F, -1.5F, 3, 5, 3, 0.0F);
		this.legBckRtInfo[legPartNumber][partNumber] = new PartInfo(this.legBckRt[legPartNumber][partNumber]);
		this.legBckRtSpike = new ModelRenderer(this, 26, 54);
		this.legBckRtSpike.setRotationPoint(0.0F, 0.0F, 1.0F);
		this.legBckRtSpike.addBox(-0.5F, -0.5F, -0.4F, 1, 1, 3, 0.0F);
		this.setRotateAngle(legBckRtSpike, 0.8726646259971648F, 0.0F, 0.0F);
		this.legBckRtSpikeInfo = new PartInfo(legBckRtSpike);

		legPartNumber = 0;
		this.legBckRt[legPartNumber][jointNumber].setRotationPoint(-2.1F, 0.0F, 4.0F); // legBckRt1Joint
		this.setRotateAngle(legBckRt[legPartNumber][jointNumber], -1.2217304763960306F, 0.0F, 0.0F);
		this.legBckRtSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckRt[legPartNumber][jointNumber]);
		this.legBckRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legBckRt1
		this.setRotateAngle(legBckRt[legPartNumber][partNumber], 0.0F, -0.17453292519943295F, 0.4363323129985824F);
		this.legBckRtSitInfo[legPartNumber][partNumber] = new PartInfo(this.legBckRt[legPartNumber][partNumber]);
		legPartNumber++;
		this.legBckRt[legPartNumber][jointNumber].setRotationPoint(0.5F, 3.1F, 0.0F); // legBckRt2Joint
		this.setRotateAngle(legBckRt[legPartNumber][jointNumber], 1.8325957145940461F, 0.0F, 0.0F);
		this.legBckRtSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckRt[legPartNumber][jointNumber]);
		this.legBckRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legBckRt2
		this.legBckRtSitInfo[legPartNumber][partNumber] = new PartInfo(this.legBckRt[legPartNumber][partNumber]);
		legPartNumber++;
		this.legBckRt[legPartNumber][jointNumber].setRotationPoint(0.0F, 2.4F, 0.0F); // legBckRt3Joint
		this.setRotateAngle(legBckRt[legPartNumber][jointNumber], -1.3962634015954636F, 0.0F, 0.0F);
		this.legBckRtSitInfo[legPartNumber][jointNumber] = new PartInfo(this.legBckRt[legPartNumber][jointNumber]);
		this.legBckRt[legPartNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F); // legBckRt3
		this.legBckRtSitInfo[legPartNumber][partNumber] = new PartInfo(this.legBckRt[legPartNumber][partNumber]);
		this.legBckRtSpike.setRotationPoint(0.0F, 0.0F, 1.0F);
		this.setRotateAngle(legBckRtSpike, 0.8726646259971648F, 0.0F, 0.0F);
		this.legBckRtSpikeSitInfo = new PartInfo(legBckRtSpike);

		this.neckJoint = new ModelRenderer(this, 0, 0);
		this.neckJoint.setRotationPoint(0.0F, -1.4F, -5.0F);
		this.neckJoint.addBox(-1.5F, -1.5F, -5.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(neckJoint, -0.7853981633974483F, 0.0F, 0.0F);
		this.neckJointInfo = new PartInfo(neckJoint);
		this.neck = new ModelRenderer(this, 29, 30);
		this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.neck.addBox(-3.0F, -2.5F, -4.5F, 6, 5, 5, 0.0F);
		this.neckInfo = new PartInfo(neck);
		this.headJoint = new ModelRenderer(this, 0, 0);
		this.headJoint.setRotationPoint(0.0F, 0.0F, -6.0F);
		this.headJoint.addBox(-2.5F, -2.5F, -2.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(headJoint, 0.7853981633974483F, 0.0F, 0.0F);
		this.headJointInfo = new PartInfo(headJoint);
		this.head = new ModelRenderer(this, 30, 16);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-2.5F, -2.5F, -2.5F, 5, 5, 5, 0.0F);
		this.headInfo = new PartInfo(head);

		this.neckJoint.setRotationPoint(0.0F, -1.4F, -5.0F);
		this.neckJointSitInfo = new PartInfo(neckJoint);

		this.muzzle = new ModelRenderer(this, 54, 17);
		this.muzzle.setRotationPoint(0.0F, 1.0F, -2.5F);
		this.muzzle.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 2, 0.0F);
		this.setRotateAngle(muzzle, 0.17453292519943295F, 0.0F, 0.0F);
		this.muzzle.setTextureOffset(56, 21).addBox(-0.5F, -0.9F, -1.7F, 1, 1, 1, 0.0F); // nose

		this.headHairMid = new ModelRenderer(this, 28, 0);
		this.headHairMid.setRotationPoint(0.0F, -3.0F, 0.0F);
		this.headHairMid.addBox(-3.0F, -1.0F, -3.0F, 6, 2, 6, 0.0F);
		this.setRotateAngle(headHairMid, 0.08726646259971647F, 0.0F, 0.0F);
		this.headHair = new ModelRenderer(this, 28, 8);
		this.headHair.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.headHair.addBox(-3.01F, -2.5F, -2.9F, 6, 1, 6, 0.0F);
		this.headHairTop = new ModelRenderer(this, 9, 19);
		this.headHairTop.setRotationPoint(0.0F, -3.0F, 0.0F);
		this.headHairTop.addBox(-2.0F, -2.0F, -2.0F, 4, 1, 4, 0.0F);
		this.setRotateAngle(headHairTop, 0.08726646259971647F, 0.0F, 0.0F);

		int bangPartNumber = 0;
		this.headBang[bangPartNumber] = new ModelRenderer(this, 52, 0); // headBang1
		this.headBang[bangPartNumber].setRotationPoint(2.7F, -1.6F, -0.9F);
		this.headBang[bangPartNumber].addBox(-2.0F, -0.6F, -1.0F, 4, 2, 2, 0.0F);
		this.setRotateAngle(headBang[bangPartNumber], -0.4363323129985824F, -1.3962634015954636F, 0.0F);
		this.headBangInfo[bangPartNumber] = new PartInfo(this.headBang[bangPartNumber]);
		bangPartNumber++;
		this.headBang[bangPartNumber] = new ModelRenderer(this, 53, 4); // headBang2
		this.headBang[bangPartNumber].setRotationPoint(-0.4F, 1.6F, 0.2F);
		this.headBang[bangPartNumber].addBox(-1.5F, -0.6F, -1.0F, 3, 2, 2, 0.0F);
		this.setRotateAngle(headBang[bangPartNumber], 0.17453292519943295F, 0.17453292519943295F, 0.08726646259971647F);
		this.headBangInfo[bangPartNumber] = new PartInfo(this.headBang[bangPartNumber]);
		bangPartNumber++;
		this.headBang[bangPartNumber] = new ModelRenderer(this, 54, 8); // headBang3
		this.headBang[bangPartNumber].setRotationPoint(0.1F, 1.6F, 0.2F);
		this.headBang[bangPartNumber].addBox(-1.5F, -0.6F, -1.0F, 2, 2, 2, 0.0F);
		this.setRotateAngle(headBang[bangPartNumber], 0.17453292519943295F, 0.3490658503988659F, 0.08726646259971647F);
		this.headBangInfo[bangPartNumber] = new PartInfo(this.headBang[bangPartNumber]);
		bangPartNumber++;
		this.headBang[bangPartNumber] = new ModelRenderer(this, 56, 12); // headBang4
		this.headBang[bangPartNumber].setRotationPoint(-0.9F, 1.6F, 0.2F);
		this.headBang[bangPartNumber].addBox(-0.5F, -0.4F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(headBang[bangPartNumber], 0.08726646259971647F, 0.3490658503988659F, 0.2617993877991494F);
		this.headBangInfo[bangPartNumber] = new PartInfo(this.headBang[bangPartNumber]);
		bangPartNumber++;
		this.headBang[bangPartNumber] = new ModelRenderer(this, 56, 14); // headBang5
		this.headBang[bangPartNumber].setRotationPoint(0.0F, 0.6F, 0.0F);
		this.headBang[bangPartNumber].addBox(-0.5F, -0.4F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(headBang[bangPartNumber], 0.08726646259971647F, 0.17453292519943295F, 0.2617993877991494F);
		this.headBangInfo[bangPartNumber] = new PartInfo(this.headBang[bangPartNumber]);

		this.headHornBase = new ModelRenderer(this, 26, 16);
		this.headHornBase.setRotationPoint(-2.0F, -0.7F, 0.0F);
		this.headHornBase.addBox(-3.0F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
		this.headHorn1 = new ModelRenderer(this, 20, 14);
		this.headHorn1.setRotationPoint(-3.0F, 0.0F, 0.0F);
		this.headHorn1.addBox(-2.0F, -2.7F, -0.51F, 2, 4, 1, 0.0F);
		this.headHornBot = new ModelRenderer(this, 21, 19);
		this.headHornBot.setRotationPoint(-1.0F, 1.2F, 0.0F);
		this.headHornBot.addBox(-0.5F, -0.7F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headHornBot, 0.0F, 0.0F, -0.2617993877991494F);
		this.headHorn2 = new ModelRenderer(this, 20, 10);
		this.headHorn2.setRotationPoint(-1.0F, -2.5F, 0.0F);
		this.headHorn2.addBox(-1.0F, -2.8F, -0.5F, 2, 3, 1, 0.0F);
		this.setRotateAngle(headHorn2, 0.0F, 0.0F, 0.3490658503988659F);
		this.headHornEdge1 = new ModelRenderer(this, 0, 16);
		this.headHornEdge1.setRotationPoint(-1.0F, 0.0F, 0.0F);
		this.headHornEdge1.addBox(-2.1F, -2.8F, 0.0F, 4, 6, 1, 0.0F);
		this.setRotateAngle(headHornEdge1, 0.0F, 0.0F, 0.06981317007977318F);
		this.headHorn3 = new ModelRenderer(this, 20, 6);
		this.headHorn3.setRotationPoint(0.1F, -3.0F, 0.0F);
		this.headHorn3.addBox(-1.0F, -2.4F, -0.51F, 2, 3, 1, 0.0F);
		this.setRotateAngle(headHorn3, 0.0F, 0.0F, 0.3490658503988659F);
		this.headHorn4 = new ModelRenderer(this, 21, 3);
		this.headHorn4.setRotationPoint(0.1F, -2.5F, 0.0F);
		this.headHorn4.addBox(-0.5F, -1.7F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headHorn4, 0.0F, 0.0F, 0.3490658503988659F);
		this.headHornEdge2 = new ModelRenderer(this, 0, 8);
		this.headHornEdge2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.headHornEdge2.addBox(-1.8F, -2.5F, 0.01F, 4, 6, 1, 0.0F);
		this.setRotateAngle(headHornEdge2, 0.0F, 0.0F, -0.22689280275926282F);
		this.headHorn5 = new ModelRenderer(this, 21, 0);
		this.headHorn5.setRotationPoint(0.1F, -1.8F, 0.0F);
		this.headHorn5.addBox(-0.5F, -1.7F, -0.51F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headHorn5, 0.0F, 0.0F, 0.3490658503988659F);
		this.headHornEdge3 = new ModelRenderer(this, 0, 0);
		this.headHornEdge3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.headHornEdge3.addBox(-1.8F, -5.3F, 0.0F, 3, 7, 1, 0.0F);
		this.setRotateAngle(headHornEdge3, 0.0F, 0.0F, 0.2617993877991494F);

		this.tail1 = new ModelRenderer(this, 0, 56);
		this.tail1.setRotationPoint(0.0F, -2.0F, 5.0F);
		this.tail1.addBox(-0.5F, -1.0F, -1.0F, 1, 2, 6, 0.0F);
		this.setRotateAngle(tail1, 0.47123889803846897F, 0.0F, 0.0F);
		this.tail1Info = new PartInfo(tail1);
		this.tail2 = new ModelRenderer(this, 0, 49);
		this.tail2.setRotationPoint(0.0F, 0.0F, 5.0F);
		this.tail2.addBox(-0.51F, -1.0F, -0.3F, 1, 2, 5, 0.0F);
		this.setRotateAngle(tail2, -0.2617993877991494F, 0.0F, 0.0F);
		this.tailEdge1 = new ModelRenderer(this, 0, 40);
		this.tailEdge1.setRotationPoint(0.0F, 0.0F, -1.0F);
		this.tailEdge1.addBox(-1.0F, -2.5F, 1.0F, 1, 3, 6, 0.0F);
		this.setRotateAngle(tailEdge1, -0.45378560551852565F, 0.0F, 0.0F);
		this.tailEdge2 = new ModelRenderer(this, 0, 32);
		this.tailEdge2.setRotationPoint(0.0F, -3.0F, 7.0F);
		this.tailEdge2.addBox(-1.01F, -0.5F, -0.6F, 1, 3, 5, 0.0F);
		this.setRotateAngle(tailEdge2, 0.19198621771937624F, 0.0F, 0.0F);
		this.tailEdge3 = new ModelRenderer(this, 0, 23);
		this.tailEdge3.setRotationPoint(0.0F, 0.0F, 3.0F);
		this.tailEdge3.addBox(-1.02F, -0.8F, -0.6F, 1, 3, 6, 0.0F);
		this.setRotateAngle(tailEdge3, -0.17453292519943295F, 0.0F, 0.0F);

		this.body.addChild(this.legBckLft[0][0]);
		this.body.addChild(this.legBckRt[0][0]);
		this.body.addChild(this.legFntLft[0][0]);
		this.body.addChild(this.legFntRt[0][0]);
		this.body.addChild(this.neckJoint);
		this.body.addChild(this.tail1);
		this.bodyJoint.addChild(this.body);
		this.head.addChild(this.headBang[0]);
		this.head.addChild(this.headHair);
		this.head.addChild(this.headHairMid);
		this.head.addChild(this.headHairTop);
		this.head.addChild(this.headHornBase);
		this.head.addChild(this.muzzle);

		for(int i = 0; i < headBang.length - 1; i++)
		{
			this.headBang[i].addChild(this.headBang[i + 1]);
		}

		this.headHorn1.addChild(this.headHorn2);
		this.headHorn1.addChild(this.headHornBot);
		this.headHorn1.addChild(this.headHornEdge1);
		this.headHorn2.addChild(this.headHorn3);
		this.headHorn3.addChild(this.headHorn4);
		this.headHorn3.addChild(this.headHornEdge2);
		this.headHorn4.addChild(this.headHorn5);
		this.headHorn4.addChild(this.headHornEdge3);
		this.headHornBase.addChild(this.headHorn1);
		this.headJoint.addChild(this.head);

		this.neckJoint.addChild(this.neck);
		this.neck.addChild(this.headJoint);

		this.legBckLft[0][0].addChild(this.legBckLft[0][1]);
		this.legBckLft[0][1].addChild(this.legBckLft[1][0]);
		this.legBckLft[1][0].addChild(this.legBckLft[1][1]);
		this.legBckLft[1][1].addChild(this.legBckLft[2][0]);
		this.legBckLft[2][0].addChild(this.legBckLft[2][1]);
		this.legBckLft[2][1].addChild(this.legBckLftSpike);

		this.legBckRt[0][0].addChild(this.legBckRt[0][1]);
		this.legBckRt[0][1].addChild(this.legBckRt[1][0]);
		this.legBckRt[1][0].addChild(this.legBckRt[1][1]);
		this.legBckRt[1][1].addChild(this.legBckRt[2][0]);
		this.legBckRt[2][0].addChild(this.legBckRt[2][1]);
		this.legBckRt[2][1].addChild(this.legBckRtSpike);

		this.legFntLft[0][0].addChild(this.legFntLft[0][1]);
		this.legFntLft[0][1].addChild(this.legFntLft[1][0]);
		this.legFntLft[1][0].addChild(this.legFntLft[1][1]);
		this.legFntLft[1][1].addChild(this.legFntLftSpike);

		this.legFntRt[0][0].addChild(this.legFntRt[0][1]);
		this.legFntRt[0][1].addChild(this.legFntRt[1][0]);
		this.legFntRt[1][0].addChild(this.legFntRt[1][1]);
		this.legFntRt[1][1].addChild(this.legFntRtSpike);

		this.tail1.addChild(this.tail2);
		this.tail1.addChild(this.tailEdge1);
		this.tailEdge1.addChild(this.tailEdge2);
		this.tailEdge2.addChild(this.tailEdge3);

		animationDeployer = new AnimationDeployer(this);
	}

	@Override
	public void setRotationAngles(T entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch)
	{
		animate((FeywoodAbsolEntity)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch);
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
		float verticleVelocity = (float)((IMobMotionTracker)entity).getHeightVelocity();

		float idleDampener = 1F;

		float sizeModifier = 1F;//(float) Math.sqrt(((EntityOkamiEspeon) entity).defaultHeight / entity.height);

		if(!((FeywoodAbsolEntity)entity).isSitting())
		{
			if(animationDeployer.getEntity().getAnimationID() == LibraryFeywoodAbsolAttackID.MEGAHORN)
			{
				idleDampener = animateMegahorn(animationDeployer.getEntity(), distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier);
			}
		}

		animateBody((FeywoodAbsolEntity)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, idleDampener, angularVelocity, verticleVelocity);
		animateHead((FeywoodAbsolEntity)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, idleDampener, verticleVelocity);
		animateLegs((FeywoodAbsolEntity)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, idleDampener, verticleVelocity);
		animateTail((FeywoodAbsolEntity)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, idleDampener, angularVelocity, verticleVelocity);

		deployAnimations();
	}

	public void resetPartInfos()
	{
		neckJointInfo.resetNewAngles();
		neckInfo.resetNewAngles();
		headJointInfo.resetNewAngles();
		headInfo.resetNewAngles();

		bodyInfo.resetNewAngles();
		bodyJointInfo.resetNewPnt();
		bodyJointInfo.resetNewAngles();

		for(int i = 0; i < headBangInfo.length; i++)
		{
			headBangInfo[i].resetNewAngles();
		}

		tail1Info.resetNewAngles();

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
	}

	public float animateMegahorn(IAnimatedEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize)
	{
		float idleDampener = 1F;

		float tick = animationDeployer.getEntity().getAnimationTick() + KindredLegacy.proxy.getPartialTick();

		float firstPhaseEnd = 3;
		float secondPhaseEnd = 9;
		float endDuration = 12;

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

		Vector3f bodyJointAngleChanges = new Vector3f(0,0,0);
		Vector3f bodyJointPointChanges = new Vector3f(0,0,0);

		Vector3f neckJointAngleChanges = new Vector3f(0,0,0);
		Vector3f neckAngleChanges = new Vector3f(0,0,0);

		Vector3f headJointAngleChanges = new Vector3f(0,0,0);
		Vector3f headAngleChanges = new Vector3f(0,0,0);

		Vector3f legFntRtJoint1AngleChanges = new Vector3f(0,0,0);
		Vector3f legFntLftJoint1AngleChanges = new Vector3f(0,0,0);

		Vector3f legBckRtJoint1AngleChanges = new Vector3f(0,0,0);
		Vector3f legBckLftJoint1AngleChanges = new Vector3f(0,0,0);

		Vector3f legBckJoints2_3AngleChanges = new Vector3f(0,0,0);
		
		/* *************************************/
		/* ******** Animation Timers ***********/
		/* *************************************/
		/* ******** First Phase Timers *********/
//		float firstPhaseDurationStart = 0;
		float firstPhaseDurationEnd = firstPhaseEnd;
//		float firstPhaseDuration = firstPhaseEnd;

		/* ******** Mid Phase Timers *********/
		float midPhaseDurationStart = firstPhaseEnd;
		float midPhaseDurationEnd = secondPhaseEnd;
//		float midPhaseDuration = secondPhaseEnd - firstPhaseEnd;

		/* ******** End Phase Timers *********/
//		float endPhaseDurationStart = secondPhaseEnd;
//		float endPhaseDurationEnd = endDuration;
//		float endPhaseDuration = endDuration - secondPhaseEnd;

		/* *************************************/
		/* ********** Phase Vectors ************/
		/* *************************************/
		/* ******* First Phase Vectors *********/
		Vector3f bodyFirstPhaseChanges = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(45),
				(float)Math.toRadians(0));
		
		Vector3f bodyPntFirstPhaseChanges = new Vector3f(
				-3.5F,
				3.0F,
				0F);

		Vector3f neckJointFirstPhaseChanges = new Vector3f(
				(float)Math.toRadians(75),
				(float)Math.toRadians(0),
				(float)Math.toRadians(0));

		Vector3f neckFirstPhaseChanges = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(45),
				(float)Math.toRadians(0));

		Vector3f headJointFirstPhaseChanges = new Vector3f(
				(float)Math.toRadians(35),
				(float)Math.toRadians(0),
				(float)Math.toRadians(0));

		Vector3f headFirstPhaseChanges = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(20),
				(float)Math.toRadians(0));

		Vector3f legFntRtJoint1FirstPhaseChanges = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(0),
				(float)Math.toRadians(30));

		Vector3f legFntLftJoint1FirstPhaseChanges = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(0),
				(float)Math.toRadians(-30));

		Vector3f legBckRtJoint1FirstPhaseChanges = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(0),
				(float)Math.toRadians(15));

		Vector3f legBckLftJoint1FirstPhaseChanges = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(0),
				(float)Math.toRadians(-15));

		Vector3f legBckJoints2_3FirstPhaseChanges = new Vector3f(
				(float)Math.toRadians(35),
				(float)Math.toRadians(0),
				(float)Math.toRadians(0));

		/* ******* Mid Phase Vectors *********/
		Vector3f bodyMidPhaseChanges = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(-75),
				(float)Math.toRadians(0));
		
		Vector3f bodyPntMidPhaseChanges = new Vector3f(
				7.0F,
				0F,
				0F);

		Vector3f neckJointMidPhaseChanges = new Vector3f(
				(float)Math.toRadians(-70),
				(float)Math.toRadians(0),
				(float)Math.toRadians(0));

		Vector3f neckMidPhaseChanges = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(-90),
				(float)Math.toRadians(0));

		Vector3f headJointMidPhaseChanges = new Vector3f(
				(float)Math.toRadians(-30),
				(float)Math.toRadians(0),
				(float)Math.toRadians(0));

		Vector3f headMidPhaseChanges = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(-40),
				(float)Math.toRadians(0));

		Vector3f legFntRtJoint1MidPhaseChanges = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(0),
				(float)Math.toRadians(-45));

		Vector3f legFntLftJoint1MidPhaseChanges = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(0),
				(float)Math.toRadians(-20));

		Vector3f legBckRtJoint1MidPhaseChanges = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(0),
				(float)Math.toRadians(-20));

		Vector3f legBckLftJoint1MidPhaseChanges = new Vector3f(
				(float)Math.toRadians(0),
				(float)Math.toRadians(0),
				(float)Math.toRadians(-10));

		Vector3f legBckJoints2_3MidPhaseChanges = new Vector3f(
				(float)Math.toRadians(15),
				(float)Math.toRadians(0),
				(float)Math.toRadians(0));

		/* ******* End Phase Vectors *********/
		

		/* *************************************/
		/* ***** First Phase Animations ********/
		/* *************************************/
		float firstPhaseDurationCovered = PartAnimate.calculateDuration(tick, 0, firstPhaseDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyJointAngleChanges, bodyFirstPhaseChanges,
				firstPhaseDurationCovered);

		PartAnimate.changeOverDurationSCurve(bodyJointPointChanges, bodyPntFirstPhaseChanges,
				firstPhaseDurationCovered);

		PartAnimate.changeOverDurationSCurve(neckJointAngleChanges, neckJointFirstPhaseChanges,
				firstPhaseDurationCovered);

		PartAnimate.changeOverDurationSCurve(neckAngleChanges, neckFirstPhaseChanges,
				firstPhaseDurationCovered);

		PartAnimate.changeOverDurationSCurve(headJointAngleChanges, headJointFirstPhaseChanges,
				firstPhaseDurationCovered);

		PartAnimate.changeOverDurationSCurve(headAngleChanges, headFirstPhaseChanges,
				firstPhaseDurationCovered);

		PartAnimate.changeOverDurationSCurve(legFntRtJoint1AngleChanges, legFntRtJoint1FirstPhaseChanges,
				firstPhaseDurationCovered);

		PartAnimate.changeOverDurationSCurve(legFntLftJoint1AngleChanges, legFntLftJoint1FirstPhaseChanges,
				firstPhaseDurationCovered);

		PartAnimate.changeOverDurationSCurve(legBckRtJoint1AngleChanges, legBckRtJoint1FirstPhaseChanges,
				firstPhaseDurationCovered);

		PartAnimate.changeOverDurationSCurve(legBckLftJoint1AngleChanges, legBckLftJoint1FirstPhaseChanges,
				firstPhaseDurationCovered);

		PartAnimate.changeOverDurationSCurve(legBckJoints2_3AngleChanges, legBckJoints2_3FirstPhaseChanges,
				firstPhaseDurationCovered);

		/* *************************************/
		/* ******* Mid Phase Animations ********/
		/* *************************************/
		float midPhaseDurationCovered = PartAnimate.calculateDuration(tick, midPhaseDurationStart, midPhaseDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyJointAngleChanges, bodyMidPhaseChanges,
				midPhaseDurationCovered);

		PartAnimate.changeOverDurationSCurve(bodyJointPointChanges, bodyPntMidPhaseChanges,
				midPhaseDurationCovered);

		PartAnimate.changeOverDurationSCurve(neckJointAngleChanges, neckJointMidPhaseChanges,
				midPhaseDurationCovered);

		PartAnimate.changeOverDurationSCurve(neckAngleChanges, neckMidPhaseChanges,
				midPhaseDurationCovered);

		PartAnimate.changeOverDurationSCurve(headJointAngleChanges, headJointMidPhaseChanges,
				midPhaseDurationCovered);

		PartAnimate.changeOverDurationSCurve(headAngleChanges, headMidPhaseChanges,
				midPhaseDurationCovered);

		PartAnimate.changeOverDurationUCurve(legFntRtJoint1AngleChanges, legFntRtJoint1MidPhaseChanges,
				midPhaseDurationCovered);

		PartAnimate.changeOverDurationUCurve(legFntLftJoint1AngleChanges, legFntLftJoint1MidPhaseChanges,
				midPhaseDurationCovered);

		PartAnimate.changeOverDurationUCurve(legBckRtJoint1AngleChanges, legBckRtJoint1MidPhaseChanges,
				midPhaseDurationCovered);

		PartAnimate.changeOverDurationUCurve(legBckLftJoint1AngleChanges, legBckLftJoint1MidPhaseChanges,
				midPhaseDurationCovered);

		PartAnimate.changeOverDurationUCurve(legBckJoints2_3AngleChanges, legBckJoints2_3MidPhaseChanges,
				midPhaseDurationCovered);


		/* *************************************/
		/* ******* End Phase Animations ********/
		/* *************************************/
		float returnDampener = 1 - PartAnimate.calculateDuration(tick, midPhaseDurationEnd, endDuration);

		bodyJointAngleChanges.multiplyVector(returnDampener);
		bodyJointPointChanges.multiplyVector(returnDampener);

		neckJointAngleChanges.multiplyVector(returnDampener);
		neckAngleChanges.multiplyVector(returnDampener);

		headJointAngleChanges.multiplyVector(returnDampener);
		headAngleChanges.multiplyVector(returnDampener);
		
		legFntRtJoint1AngleChanges.multiplyVector(returnDampener);
		legFntLftJoint1AngleChanges.multiplyVector(returnDampener);
		
		legBckRtJoint1AngleChanges.multiplyVector(returnDampener);
		legBckLftJoint1AngleChanges.multiplyVector(returnDampener);

		legBckJoints2_3AngleChanges.multiplyVector(returnDampener);

		PartAnimate.applyRotationChangeVectorToInfo(bodyJointInfo, bodyJointAngleChanges);
		PartAnimate.applyPointChangeVectorToInfo(bodyJointInfo, bodyJointPointChanges);

		PartAnimate.applyRotationChangeVectorToInfo(neckJointInfo, neckJointAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(neckInfo, neckAngleChanges);

		PartAnimate.applyRotationChangeVectorToInfo(headJointInfo, headJointAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(headInfo, headAngleChanges);

		PartAnimate.applyRotationChangeVectorToInfo(legFntRtInfo[0][0], legFntRtJoint1AngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(legFntLftInfo[0][0], legFntLftJoint1AngleChanges);

		PartAnimate.applyRotationChangeVectorToInfo(legBckRtInfo[0][0], legBckRtJoint1AngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(legBckLftInfo[0][0], legBckLftJoint1AngleChanges);

		PartAnimate.applyRotationChangeVectorToInfo(legBckRtInfo[1][0], legBckJoints2_3AngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(legBckLftInfo[1][0], legBckJoints2_3AngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(legBckRtInfo[2][0], Vector3f.negative(legBckJoints2_3AngleChanges));
		PartAnimate.applyRotationChangeVectorToInfo(legBckLftInfo[2][0], Vector3f.negative(legBckJoints2_3AngleChanges));

		PartAnimate.headAnimateInfoOnlyWithAngleModifiers(neckJointInfo, netHeadYaw, headPitch, (1F - idleDampener) * 1.2F, 0);
		
		return idleDampener;
	}

	public void animateBody(FeywoodAbsolEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float idleDampener, float angularVelocity, float verticalVelocity)
	{
		if(!entity.isSitting())
		{
			float walkCycleInterval = (WALK_FREQUENCY * entity.getGravityFactor() * distanceMoved % (2 * PI))/(2 * PI);
			float runCycleInterval = (RUN_FREQUENCY * entity.getGravityFactor() * distanceMoved % (2 * PI))/(2 * PI);

			float bodyWalkAngle = (float)Math.toRadians(13);
			float bodyRunAngle = (float)Math.toRadians(18);

			float walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI + PI/2) * bodyWalkAngle * (1 - horzVelocity);
			float runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI + PI/2) * bodyRunAngle * horzVelocity;

			float bodyJumpAngle = (float)Math.toRadians(-65);

			float newVerticalVelocity = 1.5F * verticalVelocity;

			if(newVerticalVelocity > 1F)
				newVerticalVelocity = 1F;
			else if(newVerticalVelocity < -1F)
				newVerticalVelocity = -1F;

			bodyInfo.setNewRotateX(bodyInfo.getNewRotateX() + ((walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity)) 
					+ bodyJumpAngle * newVerticalVelocity) * idleDampener);
		}
		else
		{
			bodyInfo.setNewRotateX(bodySitInfo.getNewRotateX());
			bodyJointInfo.setNewPointY(bodyJointSitInfo.getNewPointY());
		}
	}

	public void animateHead(FeywoodAbsolEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float idleDampener, float verticalVelocity)
	{
		JointAnimation.reverseJointRotatesChangeWithDampener(bodyInfo, neckJointInfo, idleDampener);

		PartAnimate.headAnimateInfoOnlyWithAngleModifiersAndIdleDampener(neckInfo, netHeadYaw, headPitch, 0.2F, 0.2F, idleDampener);

		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockBody();

		float idleAmplitudeX = 0.1F;

		float walkCycleInterval = (WALK_FREQUENCY * entity.getGravityFactor() * distanceMoved % (2 * PI))/(2 * PI);
		float runCycleInterval = (RUN_FREQUENCY * entity.getGravityFactor() * distanceMoved % (2 * PI))/(2 * PI);

		if(entity.isSitting())
		{
			JointAnimation.reverseJointRotatesChange(bodyJointInfo, neckJointInfo);
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

			neckInfo.setNewRotateX(neckInfo.getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocity * idleDampener);

			/* ******************* */
			walkAngle = (float)Math.toRadians(5);
			runAngle = (float)Math.toRadians(10);

			walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI) * walkAngle * (1 - horzVelocity)
					+ walkAngle * (1 - horzVelocity);
			runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI) * runAngle * horzVelocity
					+ runAngle * horzVelocity;
		}

		float angleChangeX = -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeX + idleAmplitudeX;

		neckInfo.setNewRotateX(neckInfo.getNewRotateX() + angleChangeX * idleDampener);

		JointAnimation.reverseJointRotatesChangeWithDampener(neckInfo, headJointInfo, idleDampener);

		PartAnimate.headAnimateInfoOnlyWithAngleModifiersAndIdleDampener(headInfo, netHeadYaw, headPitch, 0.9F, 0.9F, idleDampener);
	}

	public void animateLegs(FeywoodAbsolEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float idleDampener, float verticalVelocity)
	{
		if(!entity.isSitting())
		{	
			JointAnimation.reverseJointRotatesChangeWithDampener(bodyInfo, legFntRtInfo[0][0], idleDampener);
			JointAnimation.reverseJointRotatesChangeWithDampener(bodyInfo, legFntLftInfo[0][0], idleDampener);
			JointAnimation.reverseJointRotatesChangeWithDampener(bodyInfo, legBckRtInfo[0][0], idleDampener);
			JointAnimation.reverseJointRotatesChangeWithDampener(bodyInfo, legBckLftInfo[0][0], idleDampener);

			/* ******* Relative Angles/Absolute Commented Out ******* */
			float legFntWalkStartAngles[] = new float[2];

			legFntWalkStartAngles[0] = (float)Math.toRadians(45);
			legFntWalkStartAngles[1] = (float)Math.toRadians(-25);

			float legFntWalkAngles[][] = new float[2][4];

			// legFntWalkAngles[0][0] = (float)Math.toRadians(50);
			// legFntWalkAngles[0][1] = (float)Math.toRadians(40);
			// legFntWalkAngles[0][2] = (float)Math.toRadians(-20);
			// legFntWalkAngles[0][3] = (float)Math.toRadians(45);

			legFntWalkAngles[0][0] = (float)Math.toRadians(5);
			legFntWalkAngles[0][1] = (float)Math.toRadians(-10);
			legFntWalkAngles[0][2] = (float)Math.toRadians(-60);
			legFntWalkAngles[0][3] = (float)Math.toRadians(65);

			// legFntWalkAngles[1][0] = (float)Math.toRadians(-25);
			// legFntWalkAngles[1][1] = (float)Math.toRadians(-90);
			// legFntWalkAngles[1][2] = (float)Math.toRadians(-13);
			// legFntWalkAngles[1][3] = (float)Math.toRadians(-45);

			legFntWalkAngles[1][0] = (float)Math.toRadians(20);
			legFntWalkAngles[1][1] = (float)Math.toRadians(-75);
			legFntWalkAngles[1][2] = (float)Math.toRadians(77);
			legFntWalkAngles[1][3] = (float)Math.toRadians(-32);

			float legBckWalkStartAngles[] = new float[3];

			legBckWalkStartAngles[0] = (float)Math.toRadians(30);
			legBckWalkStartAngles[1] = (float)Math.toRadians(60);
			legBckWalkStartAngles[2] = (float)Math.toRadians(-30);

			float legBckWalkAngles[][] = new float[3][4];

			// legBckWalkAngles[0][0] = (float)Math.toRadians(-30);
			// legBckWalkAngles[0][1] = (float)Math.toRadians(-20);
			// legBckWalkAngles[0][2] = (float)Math.toRadians(40);
			// legBckWalkAngles[0][3] = (float)Math.toRadians(20);

			legBckWalkAngles[0][0] = (float)Math.toRadians(-50);
			legBckWalkAngles[0][1] = (float)Math.toRadians(10);
			legBckWalkAngles[0][2] = (float)Math.toRadians(60);
			legBckWalkAngles[0][3] = (float)Math.toRadians(-20);

			// legBckWalkAngles[1][0] = (float)Math.toRadians(60);
			// legBckWalkAngles[1][1] = (float)Math.toRadians(55);
			// legBckWalkAngles[1][2] = (float)Math.toRadians(15);
			// legBckWalkAngles[1][3] = (float)Math.toRadians(65);

			legBckWalkAngles[1][0] = (float)Math.toRadians(-5);
			legBckWalkAngles[1][1] = (float)Math.toRadians(-5);
			legBckWalkAngles[1][2] = (float)Math.toRadians(-40);
			legBckWalkAngles[1][3] = (float)Math.toRadians(50);

			// legBckWalkAngles[2][0] = (float)Math.toRadians(-70);
			// legBckWalkAngles[2][1] = (float)Math.toRadians(-60);
			// legBckWalkAngles[2][2] = (float)Math.toRadians(-45);
			// legBckWalkAngles[2][3] = (float)Math.toRadians(-5);

			legBckWalkAngles[2][0] = (float)Math.toRadians(-65);
			legBckWalkAngles[2][1] = (float)Math.toRadians(10);
			legBckWalkAngles[2][2] = (float)Math.toRadians(15);
			legBckWalkAngles[2][3] = (float)Math.toRadians(40);

			float legFntRunStartAngles[] = new float[2];

			legFntRunStartAngles[0] = (float)Math.toRadians(35);
			legFntRunStartAngles[1] = (float)Math.toRadians(-5);

			float legFntRunAngles[][] = new float[2][4];

			// legFntRunAngles[0][0] = (float)Math.toRadians(75);
			// legFntRunAngles[0][1] = (float)Math.toRadians(20);
			// legFntRunAngles[0][2] = (float)Math.toRadians(-30);
			// legFntRunAngles[0][3] = (float)Math.toRadians(60);

			legFntRunAngles[0][0] = (float)Math.toRadians(15);
			legFntRunAngles[0][1] = (float)Math.toRadians(-55);
			legFntRunAngles[0][2] = (float)Math.toRadians(-50);
			legFntRunAngles[0][3] = (float)Math.toRadians(90);

			// legFntRunAngles[1][0] = (float)Math.toRadians(-40);
			// legFntRunAngles[1][1] = (float)Math.toRadians(-90);
			// legFntRunAngles[1][2] = (float)Math.toRadians(-25);
			// legFntRunAngles[1][3] = (float)Math.toRadians(-5);

			legFntRunAngles[1][0] = (float)Math.toRadians(-35);
			legFntRunAngles[1][1] = (float)Math.toRadians(-50);
			legFntRunAngles[1][2] = (float)Math.toRadians(65);
			legFntRunAngles[1][3] = (float)Math.toRadians(20);

			float legBckRunStartAngles[] = new float[3];

			legBckRunStartAngles[0] = (float)Math.toRadians(30);
			legBckRunStartAngles[1] = (float)Math.toRadians(60);
			legBckRunStartAngles[2] = (float)Math.toRadians(-30);

			float legBckRunAngles[][] = new float[3][4];

			// legBckRunAngles[0][0] = (float)Math.toRadians(-35);
			// legBckRunAngles[0][1] = (float)Math.toRadians(-65);
			// legBckRunAngles[0][2] = (float)Math.toRadians(10);
			// legBckRunAngles[0][3] = (float)Math.toRadians(40);

			legBckRunAngles[0][0] = (float)Math.toRadians(-75);
			legBckRunAngles[0][1] = (float)Math.toRadians(-30);
			legBckRunAngles[0][2] = (float)Math.toRadians(75);
			legBckRunAngles[0][3] = (float)Math.toRadians(30);

			// legBckRunAngles[1][0] = (float)Math.toRadians(110);
			// legBckRunAngles[1][1] = (float)Math.toRadians(55);
			// legBckRunAngles[1][2] = (float)Math.toRadians(65);
			// legBckRunAngles[1][3] = (float)Math.toRadians(75);

			legBckRunAngles[1][0] = (float)Math.toRadians(35);
			legBckRunAngles[1][1] = (float)Math.toRadians(-55);
			legBckRunAngles[1][2] = (float)Math.toRadians(10);
			legBckRunAngles[1][3] = (float)Math.toRadians(10);

			// legBckRunAngles[2][0] = (float)Math.toRadians(-85);
			// legBckRunAngles[2][1] = (float)Math.toRadians(-55);
			// legBckRunAngles[2][2] = (float)Math.toRadians(-70);
			// legBckRunAngles[2][3] = (float)Math.toRadians(-30);

			legBckRunAngles[2][0] = (float)Math.toRadians(-55);
			legBckRunAngles[2][1] = (float)Math.toRadians(30);
			legBckRunAngles[2][2] = (float)Math.toRadians(-15);
			legBckRunAngles[2][3] = (float)Math.toRadians(40);

			float walkRatio = 0;
			float runRatio = 0;

			//System.out.println( "Testing code at ModelFeywoodAbsol" );
			//System.out.println( "linearVelocity: " + Float.toString( linearVelocity ) );

			//			if(Math.abs(linearVelocity) > 0)
			//			{
			//				if(Math.abs(linearVelocity) >= this.WALK_MAX)
			//				{
			//					runRatio = (Math.abs(linearVelocity) - this.WALK_MAX) / (1F - this.WALK_MAX);
			//				}
			//				
			//				if(Math.abs(linearVelocity) <= this.RUN_MIN)
			//				{
			//					walkRatio = 1F - ((this.RUN_MIN - Math.abs(linearVelocity)) / this.RUN_MIN);
			//				}
			//			}

			if(horzVelocity > 0)
			{
				if(horzVelocity >= this.WALK_MAX)
				{
					runRatio = (horzVelocity - this.WALK_MAX) / (1F - this.WALK_MAX);
				}

				if(horzVelocity <= this.RUN_MIN)
				{
					walkRatio = 1F - ((this.RUN_MIN - horzVelocity) / this.RUN_MIN);
				}
			}

			float horzVelocityModifier = horzVelocity * 3F > 1F ?  1F : horzVelocity * 3F;

			float walkCycleInterval = (WALK_FREQUENCY * entity.getGravityFactor() * distanceMoved % (2 * PI))/(2 * PI);
			float walkOppositeCycleInterval = ((WALK_FREQUENCY * entity.getGravityFactor() * distanceMoved + PI) % (2 * PI))/(2 * PI);
			float runCycleInterval = (RUN_FREQUENCY * entity.getGravityFactor() * distanceMoved % (2 * PI))/(2 * PI);
			float runOppositeCycleInterval = ((RUN_FREQUENCY * entity.getGravityFactor() * distanceMoved + (PI/3F)) % (2 * PI))/(2 * PI);

			for(int i = 0; i < legFntRt.length; i++)
			{
				float walkCycleAngleChange = 0;
				float runCycleAngleChange = 0;

				walkCycleAngleChange = calculateMoveLegAngleChange(walkCycleInterval, 
						legFntRtInfo[i][0].getNewRotateX(), legFntWalkStartAngles[i], legFntWalkAngles[i]) * walkRatio;

				runCycleAngleChange = calculateMoveLegAngleChange(runCycleInterval, 
						legFntRtInfo[i][0].getNewRotateX(), legFntRunStartAngles[i], legFntRunAngles[i]) * runRatio;

				legFntRtInfo[i][0].setNewRotateX((legFntRtInfo[i][0].getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocityModifier * idleDampener));

				walkCycleAngleChange = calculateMoveLegAngleChange(walkOppositeCycleInterval, 
						legFntLftInfo[i][0].getNewRotateX(), legFntWalkStartAngles[i], legFntWalkAngles[i]) * walkRatio;

				runCycleAngleChange = calculateMoveLegAngleChange(runOppositeCycleInterval, 
						legFntLftInfo[i][0].getNewRotateX(), legFntRunStartAngles[i], legFntRunAngles[i]) * runRatio;

				legFntLftInfo[i][0].setNewRotateX((legFntLftInfo[i][0].getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocityModifier * idleDampener));
			}

			for(int i = 0; i < legBckRt.length; i++)
			{
				float walkCycleAngleChange = 0;
				float runCycleAngleChange = 0;

				walkCycleAngleChange = calculateMoveLegAngleChange(walkCycleInterval, 
						legBckRtInfo[i][0].getNewRotateX(), legBckWalkStartAngles[i], legBckWalkAngles[i]) * walkRatio;

				runCycleAngleChange = calculateMoveLegAngleChange(runCycleInterval, 
						legBckRtInfo[i][0].getNewRotateX(), legBckRunStartAngles[i], legBckRunAngles[i]) * runRatio;

				legBckRtInfo[i][0].setNewRotateX((legBckRtInfo[i][0].getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocityModifier * idleDampener));

				walkCycleAngleChange = calculateMoveLegAngleChange(walkOppositeCycleInterval, 
						legBckLftInfo[i][0].getNewRotateX(), legBckWalkStartAngles[i], legBckWalkAngles[i]) * walkRatio;

				runCycleAngleChange = calculateMoveLegAngleChange(runOppositeCycleInterval, 
						legBckLftInfo[i][0].getNewRotateX(), legBckRunStartAngles[i], legBckRunAngles[i]) * runRatio;

				legBckLftInfo[i][0].setNewRotateX((legBckLftInfo[i][0].getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocityModifier * idleDampener));
			}
		}
		else
		{
			for(int i = 0; i < legFntRtInfo.length; i++)
			{
				legFntRtInfo[i][0].setNewRotatesWithVector(legFntRtSitInfo[i][0].getNewRotates());
				legFntRtInfo[i][1].setNewRotatesWithVector(legFntRtSitInfo[i][1].getNewRotates());
				legFntLftInfo[i][0].setNewRotatesWithVector(legFntLftSitInfo[i][0].getNewRotates());
				legFntLftInfo[i][1].setNewRotatesWithVector(legFntLftSitInfo[i][1].getNewRotates());
			}

			for(int i = 0; i < legBckRtInfo.length; i++)
			{
				legBckRtInfo[i][0].setNewRotatesWithVector(legBckRtSitInfo[i][0].getNewRotates());
				legBckRtInfo[i][1].setNewRotatesWithVector(legBckRtSitInfo[i][1].getNewRotates());
				legBckLftInfo[i][0].setNewRotatesWithVector(legBckLftSitInfo[i][0].getNewRotates());
				legBckLftInfo[i][1].setNewRotatesWithVector(legBckLftSitInfo[i][1].getNewRotates());
			}
		}
	}

	public float calculateMoveLegAngleChange(float cycleInterval, float standingLegAngle, 
			float startingRunLegAngle, float legAngleChange[])
	{
		float newAngle = 0;

		float angleChangeFromStarting = startingRunLegAngle - standingLegAngle;

		if(cycleInterval <= 0.25F && cycleInterval >= 0)
		{
			float subInterval = cycleInterval/0.25F;

			newAngle =  angleChangeFromStarting
					+ subInterval * legAngleChange[0];
		}
		else if(cycleInterval <= 0.5F)
		{
			float subInterval = (cycleInterval - 0.25F)/0.25F;

			newAngle = angleChangeFromStarting + legAngleChange[0]
					+ subInterval * legAngleChange[1];
		}
		else if(cycleInterval <= 0.75F)
		{
			float subInterval = (cycleInterval - 0.5F)/0.25F;

			newAngle = angleChangeFromStarting + legAngleChange[0] + legAngleChange[1]
					+ subInterval * legAngleChange[2];
		}
		else if(cycleInterval <= 1F)
		{
			float subInterval = (cycleInterval - 0.75F)/0.25F;

			newAngle = angleChangeFromStarting + legAngleChange[0] + legAngleChange[1] + legAngleChange[2]
					+ subInterval * legAngleChange[3];
		}

		return newAngle;
	}

	public void animateTail(FeywoodAbsolEntity entity, float distanceMoved, float horzVelocity, float par3, float par4, float par5, float par6, float idleDampener, float angularVelocity, float verticalVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, tail1Info);

		float yawChangeAngle = -(float)Math.toRadians(35);
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

		tail1Info.setNewRotateX(tail1Info.getNewRotateX() + bodyJumpAngle * newVerticalVelocity);

		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockTail();

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
		angleChangeX += PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, absoluteMoveVelocity, RUN_FREQUENCY * entity.getGravityFactor(), moveAmplitudeX) 
				+ verticalVelocityChangeAngle * newVerticalVelocity;
		angleChangeY += angularVelocity * yawChangeAngle;

		// Tamable Animation
		angleChangeX += (entity.isTamed() ? (1F - entity.getHealthPercent()) * healthChangeAngle : 0);

		tail1Info.setNewRotateX(tail1Info.getNewRotateX() + angleChangeX);
		tail1Info.setNewRotateY(tail1Info.getNewRotateY() + angleChangeY);

		if(entity.isSitting())
		{
			tail1Info.setNewRotateX(tail1Info.getNewRotateX() + (float)Math.toRadians(30));
		}
	}

	public void deployAnimations()
	{
		this.animationDeployer.move(bodyJoint, bodyJointInfo.getNewPnt());
		this.animationDeployer.rotate(bodyJoint, bodyJointInfo.getNewRotates());
		this.animationDeployer.rotate(body, bodyInfo.getNewRotates());

		this.animationDeployer.rotate(neckJoint, neckJointInfo.getNewRotates());
		this.animationDeployer.rotate(neck, neckInfo.getNewRotates());
		this.animationDeployer.rotate(headJoint, headJointInfo.getNewRotates());
		this.animationDeployer.rotate(head, headInfo.getNewRotates());

		for(int i = 0; i < headBangInfo.length; i++)
		{
			this.animationDeployer.rotate(headBang[i], headBangInfo[i].getNewRotates());
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

		this.animationDeployer.rotate(tail1, tail1Info.getNewRotates());

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
		return ImmutableList.of(this.bodyJoint);
	}
}