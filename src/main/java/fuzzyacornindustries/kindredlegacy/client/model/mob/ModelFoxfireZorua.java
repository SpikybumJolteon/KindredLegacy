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
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityFoxfireZorua;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryFoxfireZoruaAttackID;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFoxfireZorua extends ModelBase 
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public final float RUN_FREQUENCY = 1.8F;
	public final float WALK_FREQUENCY = 1.2F;
	public final float SNEAK_VELOCITY = 0.45F;

	public ModelRenderer body;
	PartInfo bodyInfo;
	PartInfo bodySitInfo;

	public ModelRenderer legFntRt;
	public ModelRenderer legFntLft;
	public ModelRenderer legBckLft;
	public ModelRenderer legBckRt;
	PartInfo legFntRtInfo;
	PartInfo legFntLftInfo;
	PartInfo legBckLftInfo;
	PartInfo legBckRtInfo;
	PartInfo legFntRtSitInfo;
	PartInfo legFntLftSitInfo;
	PartInfo legBckLftSitInfo;
	PartInfo legBckRtSitInfo;

	public ModelRenderer maneJoint;
	public ModelRenderer mane;
	public ModelRenderer headJoint;
	public ModelRenderer head;
	PartInfo maneJointInfo;
	PartInfo maneInfo;
	PartInfo headJointInfo;
	PartInfo headInfo;

	public ModelRenderer muzzle;

	public ModelRenderer cheekTuffLft[] = new ModelRenderer[2];
	public ModelRenderer cheekTuffRt[] = new ModelRenderer[2];
	PartInfo cheekTuffLftInfo[] = new PartInfo[cheekTuffLft.length];
	PartInfo cheekTuffRtInfo[] = new PartInfo[cheekTuffRt.length];

	public ModelRenderer headHair[] = new ModelRenderer[3];
	PartInfo headHairInfo[] = new PartInfo[headHair.length];

	public ModelRenderer earLftJoint;
	public ModelRenderer earLft;
	PartInfo earLftJointInfo;
	PartInfo earLftInfo;

	public ModelRenderer earRtJoint;
	public ModelRenderer earRt;
	PartInfo earRtJointInfo;
	PartInfo earRtInfo;

	public ModelRenderer tail[][] = new ModelRenderer[2][2];
	PartInfo tailInfo[][] = new PartInfo[tail.length][2];

	public ModelRenderer tail1Hair[] = new ModelRenderer[4];
	PartInfo tail1HairInfo[] = new PartInfo[tail1Hair.length];

	public ModelRenderer tail3Hair[] = new ModelRenderer[2];
	PartInfo tail3HairInfo[] = new PartInfo[tail3Hair.length];

	public ModelFoxfireZorua() 
	{
		this.textureWidth = 64;
		this.textureHeight = 32;

		int jointNumber = 0;
		int partNumber = 1;

		this.body = new ModelRenderer(this, 46, 23);
		this.body.setRotationPoint(0.0F, 19.5F, 0.0F);
		this.body.addBox(-2.0F, -2.0F, -2.5F, 4, 4, 5, 0.0F);
		this.bodyInfo = new PartInfo(body);

		this.body.setRotationPoint(0.0F, 21.5F, 0.0F);
		this.setRotateAngle(body, -0.7853981633974483F, -0.0F, 0.0F);
		this.bodySitInfo = new PartInfo(body);

		this.legFntRt = new ModelRenderer(this, 19, 21);
		this.legFntRt.setRotationPoint(-1.0F, 1.5F, -1.5F);
		this.legFntRt.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
		this.legFntRtInfo = new PartInfo(legFntRt);

		this.legFntRt.setRotationPoint(-1.0F, 1.5F, -1.5F);
		this.setRotateAngle(legFntRt, 0.7853981633974483F, -0.0F, 0.0F);
		this.legFntRtSitInfo = new PartInfo(legFntRt);

		this.legFntLft = new ModelRenderer(this, 24, 21);
		this.legFntLft.setRotationPoint(1.0F, 1.5F, -1.5F);
		this.legFntLft.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1, 0.0F);
		this.legFntLftInfo = new PartInfo(legFntLft);

		this.legFntLft.setRotationPoint(1.0F, 1.5F, -1.5F);
		this.setRotateAngle(legFntLft, 0.7853981633974483F, -0.0F, 0.0F);
		this.legFntLftSitInfo = new PartInfo(legFntLft);

		this.legBckLft = new ModelRenderer(this, 24, 26);
		this.legBckLft.setRotationPoint(1.0F, 1.5F, 1.5F);
		this.legBckLft.addBox(-0.5F, -0.3F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(legBckLft, -0.17453292519943295F, -0.0F, 0.0F);
		this.legBckLft.setTextureOffset(24, 29).addBox(-0.6F, 0.9F, 0.0F, 1, 2, 1, 0.0F); // legBckLftHind
		this.legBckLftInfo = new PartInfo(legBckLft);

		this.legBckLft.setRotationPoint(1.0F, 1.5F, 1.5F);
		this.setRotateAngle(legBckLft, -0.7853981633974483F, -0.7853981633974483F, 0.0F);
		this.legBckLftSitInfo = new PartInfo(legBckLft);

		this.legBckRt = new ModelRenderer(this, 19, 26);
		this.legBckRt.setRotationPoint(-1.0F, 1.5F, 1.5F);
		this.legBckRt.addBox(-0.5F, -0.3F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(legBckRt, -0.17453292519943295F, -0.0F, 0.0F);
		this.legBckRt.setTextureOffset(19, 29).addBox(-0.4F, 0.9F, 0.0F, 1, 2, 1, 0.0F); // legBckRtHind
		this.legBckRtInfo = new PartInfo(legBckRt);

		this.legBckRt.setRotationPoint(-1.0F, 1.5F, 1.5F);
		this.setRotateAngle(legBckRt, -0.7853981633974483F, 0.7853981633974483F, 0.0F);
		this.legBckRtSitInfo = new PartInfo(legBckRt);

		this.maneJoint = new ModelRenderer(this, 0, 25);
		this.maneJoint.setRotationPoint(0.0F, -1.0F, -2.0F);
		this.maneJoint.addBox(-3.0F, -2.5F, -2.0F, 0, 0, 0, 0.0F);
		this.maneJointInfo = new PartInfo(maneJoint);
		this.mane = new ModelRenderer(this, 0, 25);
		this.mane.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.mane.addBox(-3.0F, -2.5F, -2.0F, 6, 4, 3, 0.0F);
		this.maneInfo = new PartInfo(mane);
		this.headJoint = new ModelRenderer(this, 0, 0);
		this.headJoint.setRotationPoint(0.0F, 0.0F, -1.5F);
		this.headJoint.addBox(-2.5F, -3.0F, -3.0F, 0, 0, 0, 0.0F);
		this.headJointInfo = new PartInfo(headJoint);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-2.5F, -3.0F, -3.0F, 5, 4, 4, 0.0F);
		this.headInfo = new PartInfo(head);

		this.muzzle = new ModelRenderer(this, 0, 9);
		this.muzzle.setRotationPoint(0.0F, 0.0F, -3.0F);
		this.muzzle.addBox(-1.0F, -0.9F, -0.6F, 2, 2, 1, 0.0F);
		this.setRotateAngle(muzzle, 0.17453292519943295F, -0.0F, 0.0F);
		this.muzzle.setTextureOffset(0, 13).addBox(-0.5F, -0.8F, -1.0F, 1, 1, 1, 0.0F); // nose

		int cheeekTuffNumber = 0;
		this.cheekTuffLft[cheeekTuffNumber] = new ModelRenderer(this, 23, 9); // cheekTuffLftTop
		this.cheekTuffLft[cheeekTuffNumber].setRotationPoint(2.0F, -1.0F, -0.5F);
		this.cheekTuffLft[cheeekTuffNumber].addBox(-0.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(cheekTuffLft[cheeekTuffNumber], 0.0F, -0.0F, -0.17453292519943295F);
		this.cheekTuffLftInfo[cheeekTuffNumber] = new PartInfo(this.cheekTuffLft[cheeekTuffNumber]);
		cheeekTuffNumber++;
		this.cheekTuffLft[cheeekTuffNumber] = new ModelRenderer(this, 23, 12); // cheekTuffLftBot
		this.cheekTuffLft[cheeekTuffNumber].setRotationPoint(2.0F, -0.5F, -0.5F);
		this.cheekTuffLft[cheeekTuffNumber].addBox(-0.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(cheekTuffLft[cheeekTuffNumber], 0.0F, -0.0F, 0.4363323129985824F);
		this.cheekTuffLftInfo[cheeekTuffNumber] = new PartInfo(this.cheekTuffLft[cheeekTuffNumber]);
		cheeekTuffNumber = 0;
		this.cheekTuffRt[cheeekTuffNumber] = new ModelRenderer(this, 14, 9); // cheekTuffRtTop
		this.cheekTuffRt[cheeekTuffNumber].setRotationPoint(-2.0F, -1.0F, -0.5F);
		this.cheekTuffRt[cheeekTuffNumber].addBox(-2.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(cheekTuffRt[cheeekTuffNumber], 0.0F, -0.0F, 0.17453292519943295F);
		this.cheekTuffRtInfo[cheeekTuffNumber] = new PartInfo(this.cheekTuffRt[cheeekTuffNumber]);
		cheeekTuffNumber++;
		this.cheekTuffRt[cheeekTuffNumber] = new ModelRenderer(this, 14, 12); // cheekTuffRtBot
		this.cheekTuffRt[cheeekTuffNumber].setRotationPoint(-2.0F, -0.5F, -0.5F);
		this.cheekTuffRt[cheeekTuffNumber].addBox(-2.5F, -0.5F, -0.5F, 3, 1, 1, 0.0F);
		this.setRotateAngle(cheekTuffRt[cheeekTuffNumber], 0.0F, -0.0F, -0.4363323129985824F);
		this.cheekTuffRtInfo[cheeekTuffNumber] = new PartInfo(this.cheekTuffRt[cheeekTuffNumber]);

		int headHairNumber = 0;
		this.headHair[headHairNumber] = new ModelRenderer(this, 3, 18); // headHair1
		this.headHair[headHairNumber].setRotationPoint(0.0F, -1.8F, -1.2F);
		this.headHair[headHairNumber].addBox(-1.5F, -2.5F, -1.5F, 3, 3, 3, 0.0F);
		this.setRotateAngle(headHair[headHairNumber], -0.5235987755982988F, 0.0F, 0.0F);
		this.headHairInfo[headHairNumber] = new PartInfo(this.headHair[headHairNumber]);
		headHairNumber++;
		this.headHair[headHairNumber] = new ModelRenderer(this, 5, 13); // headHair2
		this.headHair[headHairNumber].setRotationPoint(0.0F, -2.0F, -0.2F);
		this.headHair[headHairNumber].addBox(-1.0F, -1.5F, -1.0F, 2, 2, 2, 0.0F);
		this.setRotateAngle(headHair[headHairNumber], -0.6981317007977318F, 0.0F, 0.0F);
		this.headHairInfo[headHairNumber] = new PartInfo(this.headHair[headHairNumber]);
		headHairNumber++;
		this.headHair[headHairNumber] = new ModelRenderer(this, 7, 9); // headHair3
		this.headHair[headHairNumber].setRotationPoint(0.0F, -0.9F, -0.5F);
		this.headHair[headHairNumber].addBox(-0.5F, -1.5F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(headHair[headHairNumber], 0.6981317007977318F, 0.0F, 0.0F);
		this.headHairInfo[headHairNumber] = new PartInfo(this.headHair[headHairNumber]);

		this.earLftJoint = new ModelRenderer(this, 28, 4);
		this.earLftJoint.setRotationPoint(2.0F, -2.4F, -0.7F);
		this.earLftJoint.addBox(-1.5F, -2.0F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earLftJoint, 0.0F, -0.0F, 0.7853981633974483F);
		this.earLftJointInfo = new PartInfo(earLftJoint);
		this.earLft = new ModelRenderer(this, 28, 4);
		this.earLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earLft.addBox(-1.5F, -2.0F, -0.5F, 3, 3, 1, 0.0F);
		this.earLft.setTextureOffset(29, 2).addBox(-1.0F, -3.0F, -0.5F, 2, 1, 1, 0.0F); // earLftMid
		this.earLft.setTextureOffset(30, 0).addBox(-0.5F, -4.0F, -0.5F, 1, 1, 1, 0.0F); // earLftTip
		this.earLftInfo = new PartInfo(earLft);

		this.earRtJoint = new ModelRenderer(this, 19, 4);
		this.earRtJoint.setRotationPoint(-2.0F, -2.4F, -0.7F);
		this.earRtJoint.addBox(-1.5F, -2.0F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earRtJoint, 0.0F, -0.0F, -0.7853981633974483F);
		this.earRtJointInfo = new PartInfo(earRtJoint);
		this.earRt = new ModelRenderer(this, 19, 4);
		this.earRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earRt.addBox(-1.5F, -2.0F, -0.5F, 3, 3, 1, 0.0F);
		this.earRt.setTextureOffset(20, 2).addBox(-1.0F, -3.0F, -0.5F, 2, 1, 1, 0.0F); // earRtMid
		this.earRt.setTextureOffset(21, 0).addBox(-0.5F, -4.0F, -0.5F, 1, 1, 1, 0.0F); // earRtTop
		this.earRtInfo = new PartInfo(earRt);

		int tailNumber = 0;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 44, 16); // tail1Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, -1.0F, 2.5F);
		this.tail[tailNumber][jointNumber].addBox(-1.5F, -1.5F, -1.5F, 0, 0, 0, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 44, 16); // tail1
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-1.5F, -1.5F, -1.5F, 3, 3, 3, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 45, 10); // tail2Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 0.9F);
		this.tail[tailNumber][jointNumber].addBox(-1.0F, -1.0F, -0.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(tail[tailNumber][jointNumber], 0.5235987755982988F, 0.0F, 0.0F);
		this.tailInfo[tailNumber][jointNumber] = new PartInfo(this.tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 45, 10); // tail2
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-1.0F, -1.0F, -0.5F, 2, 2, 3, 0.0F);
		this.tailInfo[tailNumber][partNumber] = new PartInfo(this.tail[tailNumber][partNumber]);

		int tailHairNumer = 0;
		this.tail1Hair[tailHairNumer] = new ModelRenderer(this, 40, 15); // tail1TopRt
		this.tail1Hair[tailHairNumer].setRotationPoint(-1.0F, -0.9F, -0.2F);
		this.tail1Hair[tailHairNumer].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(tail1Hair[tailHairNumer], 0.6981317007977318F, -0.17453292519943295F, 0.0F);
		this.tail1HairInfo[tailHairNumer] = new PartInfo(this.tail1Hair[tailHairNumer]);
		tailHairNumer++;
		this.tail1Hair[tailHairNumer] = new ModelRenderer(this, 54, 15); // tail1TopLft
		this.tail1Hair[tailHairNumer].setRotationPoint(0.9F, -0.7F, 0.5F);
		this.tail1Hair[tailHairNumer].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(tail1Hair[tailHairNumer], 0.6981317007977318F, 0.5235987755982988F, 0.0F);
		this.tail1HairInfo[tailHairNumer] = new PartInfo(this.tail1Hair[tailHairNumer]);
		tailHairNumer++;
		this.tail1Hair[tailHairNumer] = new ModelRenderer(this, 57, 19); // tail1Lft
		this.tail1Hair[tailHairNumer].setRotationPoint(0.9F, 0.7F, 0.5F);
		this.tail1Hair[tailHairNumer].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(tail1Hair[tailHairNumer], -0.5235987755982988F, 0.8726646259971648F, 0.0F);
		this.tail1HairInfo[tailHairNumer] = new PartInfo(this.tail1Hair[tailHairNumer]);
		tailHairNumer++;
		this.tail1Hair[tailHairNumer] = new ModelRenderer(this, 37, 19); // tail1Rt
		this.tail1Hair[tailHairNumer].setRotationPoint(-1.0F, 0.0F, 0.3F);
		this.tail1Hair[tailHairNumer].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(tail1Hair[tailHairNumer], -0.17453292519943295F, -0.7853981633974483F, 0.0F);
		this.tail1HairInfo[tailHairNumer] = new PartInfo(this.tail1Hair[tailHairNumer]);

		tailHairNumer = 0;
		this.tail3Hair[tailHairNumer] = new ModelRenderer(this, 51, 5); // tail3Bot
		this.tail3Hair[tailHairNumer].setRotationPoint(0.0F, 0.3F, 2.0F);
		this.tail3Hair[tailHairNumer].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, 0.0F);
		this.setRotateAngle(tail3Hair[tailHairNumer], 0.2617993877991494F, 0.0F, 0.0F);
		this.tail3HairInfo[tailHairNumer] = new PartInfo(this.tail3Hair[tailHairNumer]);
		tailHairNumer++;
		this.tail3Hair[tailHairNumer] = new ModelRenderer(this, 41, 5); // tail3Top
		this.tail3Hair[tailHairNumer].setRotationPoint(-0.2F, -0.5F, 1.8F);
		this.tail3Hair[tailHairNumer].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, 0.0F);
		this.setRotateAngle(tail3Hair[tailHairNumer], 0.5235987755982988F, 0.0F, 0.0F);
		this.tail3HairInfo[tailHairNumer] = new PartInfo(this.tail3Hair[tailHairNumer]);

		this.body.addChild(this.legBckLft);
		this.body.addChild(this.legBckRt);
		this.body.addChild(this.legFntLft);
		this.body.addChild(this.legFntRt);
		this.body.addChild(this.maneJoint);
		this.body.addChild(this.tail[0][0]);

		this.maneJoint.addChild(this.mane);
		this.mane.addChild(this.headJoint);
		this.headJoint.addChild(this.head);

		this.head.addChild(this.cheekTuffLft[0]);
		this.head.addChild(this.cheekTuffLft[1]);
		this.head.addChild(this.cheekTuffRt[0]);
		this.head.addChild(this.cheekTuffRt[1]);

		this.head.addChild(this.earLftJoint);
		this.head.addChild(this.earRtJoint);
		this.head.addChild(this.headHair[0]);
		this.head.addChild(this.muzzle);

		this.earLftJoint.addChild(this.earLft);
		this.earRtJoint.addChild(this.earRt);

		this.headHair[0].addChild(this.headHair[1]);
		this.headHair[1].addChild(this.headHair[2]);

		this.tail[0][0].addChild(this.tail[0][1]);
		this.tail[0][1].addChild(this.tail[1][0]);
		this.tail[1][0].addChild(this.tail[1][1]);

		this.tail[0][1].addChild(this.tail1Hair[0]);
		this.tail[0][1].addChild(this.tail1Hair[1]);
		this.tail[0][1].addChild(this.tail1Hair[2]);
		this.tail[0][1].addChild(this.tail1Hair[3]);

		this.tail[1][1].addChild(this.tail3Hair[0]);
		this.tail[1][1].addChild(this.tail3Hair[1]);

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

		if(!((EntityFoxfireZorua)entity).isSitting())
		{
			if(animationDeployer.getEntity().getAnimationID() == LibraryFoxfireZoruaAttackID.SUMMON_FOXFIRE)
			{
				idleDampener = animateSummonFoxfire(animationDeployer.getEntity(), distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
			}
		}

		animateBody((EntityFoxfireZorua)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, idleDampener, verticleVelocity);
		animateHead((EntityFoxfireZorua)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, idleDampener, verticleVelocity);
		animateLegs((EntityFoxfireZorua)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, idleDampener, verticleVelocity);
		animateTail((EntityFoxfireZorua)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, idleDampener, angularVelocity, verticleVelocity);

		deployAnimations();
	}

	public void resetPartInfos()
	{
		maneJointInfo.resetNewAngles();
		maneInfo.resetNewAngles();
		headJointInfo.resetNewAngles();
		headInfo.resetNewAngles();

		earRtInfo.resetNewAngles();
		earLftInfo.resetNewAngles();

		bodyInfo.resetNewAngles();
		bodyInfo.resetNewPnt();

		legFntRtInfo.resetNewAngles();
		legFntLftInfo.resetNewAngles();
		legBckRtInfo.resetNewAngles();
		legBckLftInfo.resetNewAngles();

		legFntRtInfo.resetNewPnt();
		legFntLftInfo.resetNewPnt();
		legBckRtInfo.resetNewPnt();
		legBckLftInfo.resetNewPnt();

		for(int i = 0; i < tail.length; i++)
		{
			tailInfo[i][0].resetNewAngles();
			tailInfo[i][1].resetNewAngles();
		}

		for(int i = 0; i < tail1Hair.length; i++)
		{
			tail1HairInfo[i].resetNewAngles();
		}

		for(int i = 0; i < tail3Hair.length; i++)
		{
			tail3HairInfo[i].resetNewAngles();
		}
	}

	public float animateSummonFoxfire(IAnimatedEntity entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize)
	{
		float idleDampener = 1F;

		float tick = animationDeployer.getEntity().getAnimationTick() + KindredLegacyMain.proxy.getPartialTick();

		float squatEnd = 5;
		float jumpEnd = 11.5F;
		float landEnd = 16;
		float actionEnd = 21;

		if(tick < squatEnd && tick > 0)
		{
			idleDampener = (1F + (float)MathHelper.cos(PI*(tick/squatEnd)));
		}
		else if(tick < landEnd)
		{
			idleDampener = 0;
		}
		else if(tick < actionEnd)
		{
			idleDampener = (1F + (float)MathHelper.cos(PI + PI * ((tick - landEnd)/(actionEnd - landEnd))));
		}

		Vector3f bodyPointChanges = new Vector3f(0,0,0);
		Vector3f bodyAngleChanges = new Vector3f(0,0,0);

		Vector3f legPointChanges = new Vector3f(0,0,0);

		/* ******** Squat Timers *********/
		float squatDurationStart = 0;
		float squatDurationEnd = squatEnd;
		float squatDuration = squatEnd;

		Vector3f squatBodyPointChanges = new Vector3f(0F, 1F, 0F);

		float legShiftAmount = -1F;

		Vector3f squatLegPointChanges = new Vector3f(0F, -1F, 0F);

		/* ******** Jump Timers *********/
		float jumpDurationStart = squatEnd;
		float jumpDurationEnd = jumpEnd;
		float jumpDuration = jumpEnd - squatEnd;

		Vector3f jumpBodyPointChanges = new Vector3f(0F, -3.5F, 0F);

		Vector3f jumpLegPointChanges = new Vector3f(0F, 1F, 0F);

		Vector3f jumpBodyAngleChanges = new Vector3f(
				(float)Math.toRadians(-60),
				(float)Math.toRadians(0),
				(float)Math.toRadians(0));

		/* ******** Land Timers *********/
		float landDurationStart = jumpEnd;
		float landDurationEnd = landEnd;
		float landDuration = landEnd - jumpEnd;

		Vector3f landBodyPointChanges = new Vector3f(0F, 3.5F, 0F);

		Vector3f landLegPointChanges = new Vector3f(0F, -1F, 0F);

		/* ******** End Timers *********/
		float endDurationStart = landEnd;
		float endDurationEnd = actionEnd;
		float endDuration = actionEnd - landEnd;

		/*
		 * Squat animations
		 */
		float squatDurationCovered = PartAnimate.calculateDuration(tick, squatDurationStart, squatDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyPointChanges, squatBodyPointChanges,
				squatDurationCovered);

		PartAnimate.changeOverDurationSCurve(legPointChanges, squatLegPointChanges,
				squatDurationCovered);

		/*
		 * Jump animations
		 */
		float jumpDurationCovered = PartAnimate.calculateDuration(tick, jumpDurationStart, jumpDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyPointChanges, jumpBodyPointChanges,
				jumpDurationCovered);

		PartAnimate.changeOverDurationUCurve(bodyAngleChanges, jumpBodyAngleChanges,
				jumpDurationCovered);

		PartAnimate.changeOverDurationSCurve(legPointChanges, jumpLegPointChanges,
				jumpDurationCovered);

		/*
		 * Land animations
		 */
		float landDurationCovered = PartAnimate.calculateDuration(tick, landDurationStart, landDurationEnd);

		PartAnimate.changeOverDurationSCurve(bodyPointChanges, landBodyPointChanges,
				landDurationCovered);

		PartAnimate.changeOverDurationUCurve(bodyAngleChanges, Vector3f.negative(jumpBodyAngleChanges),
				landDurationCovered);

		PartAnimate.changeOverDurationSCurve(legPointChanges, landLegPointChanges,
				landDurationCovered);

		/*
		 * Return to idle animations
		 */
		float returnDampener = 1 - PartAnimate.calculateDuration(tick, endDurationStart, endDuration);

		bodyPointChanges.multiplyVector(returnDampener);

		bodyAngleChanges.multiplyVector(returnDampener);

		legPointChanges.multiplyVector(returnDampener);

		PartAnimate.applyPointChangeVectorToInfo(bodyInfo, bodyPointChanges);
		PartAnimate.applyRotationChangeVectorToInfo(bodyInfo, bodyAngleChanges);

		PartAnimate.applyPointChangeVectorToInfo(legFntRtInfo, legPointChanges);
		PartAnimate.applyPointChangeVectorToInfo(legFntLftInfo, legPointChanges);
		PartAnimate.applyPointChangeVectorToInfo(legBckRtInfo, legPointChanges);
		PartAnimate.applyPointChangeVectorToInfo(legBckLftInfo, legPointChanges);

		return idleDampener;
	}

	public void animateBody(EntityFoxfireZorua entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener, float verticalVelocity)
	{
		if(!entity.isSitting())
		{
			float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
			float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);

			float bodyWalkAngle = (float)Math.toRadians(14);
			float bodyRunAngle = (float)Math.toRadians(22);

			float walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI + PI/2) * bodyWalkAngle * (1 - horzVelocity);
			float runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI + PI/2) * bodyRunAngle * horzVelocity;

			float bodyJumpAngle = (float)Math.toRadians(-55);

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

	public void animateHead(EntityFoxfireZorua entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener, float verticalVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, maneJointInfo);

		PartAnimate.headAnimateInfoOnlyWithAngleModifiers(maneInfo, yawHeadOffsetDifference, pitchRotation, 0.2F, 0.2F);

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

			maneInfo.setNewRotateX(maneInfo.getNewRotateX() + (walkCycleAngleChange + runCycleAngleChange) * horzVelocity);

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
		}

		float angleChangeX = -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeX + idleAmplitudeX;

		maneInfo.setNewRotateX(maneInfo.getNewRotateX() + angleChangeX);

		JointAnimation.reverseJointRotatesChange(maneInfo, headJointInfo);

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
	}

	public void animateLegs(EntityFoxfireZorua entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener, float verticalVelocity)
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
		}
		else
		{
			legFntRtInfo.setNewRotatesWithVector(legFntRtSitInfo.getNewRotates());
			legFntLftInfo.setNewRotatesWithVector(legFntLftSitInfo.getNewRotates());

			legBckRtInfo.setNewRotatesWithVector(legBckRtSitInfo.getNewRotates());
			legBckLftInfo.setNewRotatesWithVector(legBckLftSitInfo.getNewRotates());
		}
	}

	public void animateTail(EntityFoxfireZorua entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float idleDampener, float angularVelocity, float verticalVelocity)
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

		tailInfo[0][1].setNewRotateX(tailInfo[0][1].getNewRotateX() + bodyJumpAngle * newVerticalVelocity);

		for(int i = 0; i < tail.length; i++)
		{
			IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockTail(i);

			float idleAmplitudeX = 0.28F;
			float idleAmplitudeY = 0.22F;

			float moveAmplitude = 0.1F;

			// + (float)Math.toRadians(45)
			float angleChangeX = 0;
			float angleChangeY = 0;

			angleChangeX += (MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2) * idleAmplitudeX * ( 1 - (Math.pow(Math.E, -0.4F * (float)(i + 1))))
					+ PartAnimate.negCosRotateAnimationAdjusted(distanceMoved - PI/5F * (float)i, horzVelocity, RUN_FREQUENCY, moveAmplitude)) * (1F - (Math.abs(verticalVelocity) * 0.5F)) 
					+ verticalVelocity * verticalVelocityChangeAngle
					+ (1F - entity.getHealthPercent()) * healthChangeAngle;
			angleChangeY += ((MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2) * idleAmplitudeY)
					* ( 1 - (Math.pow(Math.E, -0.4F * (float)(i + 1)))) * (1F - Math.abs(angularVelocity)))* (1F - (Math.abs(verticalVelocity) * 0.5F))
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

		this.animationDeployer.rotate(maneJoint, maneJointInfo.getNewRotates());
		this.animationDeployer.rotate(mane, maneInfo.getNewRotates());
		this.animationDeployer.rotate(headJoint, headJointInfo.getNewRotates());
		this.animationDeployer.rotate(head, headInfo.getNewRotates());

		this.animationDeployer.rotate(earLft, earLftInfo.getNewRotates());
		this.animationDeployer.rotate(earRt, earRtInfo.getNewRotates());

		this.animationDeployer.rotate(legFntRt, legFntRtInfo.getNewRotates());
		this.animationDeployer.rotate(legFntLft, legFntLftInfo.getNewRotates());
		this.animationDeployer.rotate(legBckRt, legBckRtInfo.getNewRotates());
		this.animationDeployer.rotate(legBckLft, legBckLftInfo.getNewRotates());

		this.animationDeployer.move(legFntRt, legFntRtInfo.getNewPnt());
		this.animationDeployer.move(legFntLft, legFntLftInfo.getNewPnt());
		this.animationDeployer.move(legBckRt, legBckRtInfo.getNewPnt());
		this.animationDeployer.move(legBckLft, legBckLftInfo.getNewPnt());

		for(int i = 0; i < tail.length; i++)
		{
			this.animationDeployer.rotate(tail[i][0], tailInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(tail[i][1], tailInfo[i][1].getNewRotates());
		}

		for(int i = 0; i < tail1Hair.length; i++)
		{
			this.animationDeployer.rotate(tail1Hair[i], tail1HairInfo[i].getNewRotates());
		}

		for(int i = 0; i < tail3Hair.length; i++)
		{
			this.animationDeployer.rotate(tail3Hair[i], tail3HairInfo[i].getNewRotates());
		}

		this.animationDeployer.applyChanges();
	}
}