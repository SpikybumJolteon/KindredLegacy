package fuzzyacornindustries.kindredlegacy.client.model.mob;

import fuzzyacornindustries.kindredlegacy.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.animation.PartAnimate;
import fuzzyacornindustries.kindredlegacy.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.entity.mob.IMobMotionTracker;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityForcewindEelektrik;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelForcewindEelektrik extends ModelBase 
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public final float RUN_FREQUENCY = 0.45F;

	public ModelRenderer mouth;
	PartInfo mouthInfo;

	public ModelRenderer mouthFangRt;
	public ModelRenderer mouthFangLft;
	public ModelRenderer mouthFangTop;
	public ModelRenderer mouthFangBot;
	PartInfo mouthFangRtInfo;
	PartInfo mouthFangLftInfo;
	PartInfo mouthFangTopInfo;
	PartInfo mouthFangBotInfo;

	public ModelRenderer body1TopSpk;
	public ModelRenderer body1BotSpk;

	public ModelRenderer body3SpikeLft;
	public ModelRenderer body3SpikeRt;

	public ModelRenderer body5SpikeRt;
	public ModelRenderer body5SpikeLft;

	public ModelRenderer body7SpikeRt;
	public ModelRenderer body7SpikeLft;

	public ModelRenderer body[][] = new ModelRenderer[12][2];
	PartInfo bodyInfo[][] = new PartInfo[body.length][body[0].length];

	public ModelRenderer tailFinRtEnd;
	public ModelRenderer tailFinRtMid;
	public ModelRenderer tailFinCenter;
	public ModelRenderer tailFinLftMid;
	public ModelRenderer tailFinLftEnd;

	public ModelForcewindEelektrik() 
	{
		this.textureWidth = 64;
		this.textureHeight = 64;

		int jointNumber = 0;
		int partNumber = 1;

		this.mouth = new ModelRenderer(this, 0, 0);
		this.mouth.setRotationPoint(0.0F, 16.0F, -15.5F);
		this.mouth.addBox(-1.0F, -1.0F, 9.0F, 2, 2, 2, 0.0F);
		mouthInfo = new PartInfo(mouth);

		this.mouthFangRt = new ModelRenderer(this, 8, 0);
		this.mouthFangRt.setRotationPoint(-1.0F, 0.0F, 9.5F);
		this.mouthFangRt.addBox(-1.9F, 0.0F, -5.0F, 2, 0, 6, 0.0F);
		this.setRotateAngle(mouthFangRt, 0.0F, -0.08726646259971647F, 0.0F);
		this.mouthFangRt.setTextureOffset(12, 6).addBox(-0.9F, -0.5F, -2.5F, 1, 1, 3, 0.0F); // mouthFangRtBase
		mouthFangRtInfo = new PartInfo(mouthFangRt);
		this.mouthFangLft = new ModelRenderer(this, 24, 0);
		this.mouthFangLft.setRotationPoint(1.0F, 0.0F, 9.5F);
		this.mouthFangLft.addBox(0.0F, 0.0F, -5.0F, 2, 0, 6, 0.0F);
		this.setRotateAngle(mouthFangLft, 0.0F, 0.08726646259971647F, 0.0F);
		this.mouthFangLft.setTextureOffset(28, 6).addBox(-0.1F, -0.5F, -2.5F, 1, 1, 3, 0.0F); // mouthFangLftBase
		mouthFangLftInfo = new PartInfo(mouthFangLft);
		this.mouthFangTop = new ModelRenderer(this, 21, 6);
		this.mouthFangTop.setRotationPoint(0.0F, -1.0F, 9.5F);
		this.mouthFangTop.addBox(-0.5F, -0.9F, -1.5F, 1, 1, 2, 0.0F);
		this.setRotateAngle(mouthFangTop, 0.08726646259971647F, -0.0F, 0.0F);
		mouthFangTopInfo = new PartInfo(mouthFangTop);
		this.mouthFangBot = new ModelRenderer(this, 21, 9);
		this.mouthFangBot.setRotationPoint(0.0F, 1.0F, 9.5F);
		this.mouthFangBot.addBox(-0.5F, -0.1F, -1.5F, 1, 1, 2, 0.0F);
		this.setRotateAngle(mouthFangBot, -0.08726646259971647F, -0.0F, 0.0F);
		mouthFangBotInfo = new PartInfo(mouthFangBot);

		this.body1TopSpk = new ModelRenderer(this, 8, 10);
		this.body1TopSpk.setRotationPoint(0.0F, -0.5F, 0.5F);
		this.body1TopSpk.addBox(-0.5F, -3.0F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(body1TopSpk, -0.4461061568097506F, -0.0F, 0.0F);
		this.body1TopSpk.setTextureOffset(12, 10).addBox(-0.5F, -5.8F, -1.5F, 1, 4, 1, 0.0F); // body1TopSpkEnd
		this.body1BotSpk = new ModelRenderer(this, 17, 10);
		this.body1BotSpk.setRotationPoint(0.0F, 0.5F, 0.5F);
		this.body1BotSpk.addBox(-0.5F, -0.5F, -0.5F, 1, 4, 1, 0.0F);
		this.setRotateAngle(body1BotSpk, 0.5235987755982988F, -0.0F, 0.0F);

		this.body3SpikeLft = new ModelRenderer(this, 16, 16);
		this.body3SpikeLft.setRotationPoint(0.5F, -0.5F, 0.5F);
		this.body3SpikeLft.addBox(0.0F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		this.setRotateAngle(body3SpikeLft, 0.0F, -0.0F, -0.5235987755982988F);
		this.body3SpikeRt = new ModelRenderer(this, 9, 16);
		this.body3SpikeRt.setRotationPoint(-0.5F, -0.5F, 0.5F);
		this.body3SpikeRt.addBox(-2.0F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		this.setRotateAngle(body3SpikeRt, 0.0F, -0.0F, 0.5235987755982988F);

		this.body5SpikeRt = new ModelRenderer(this, 9, 21);
		this.body5SpikeRt.setRotationPoint(-0.5F, -0.5F, 0.5F);
		this.body5SpikeRt.addBox(-2.0F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		this.setRotateAngle(body5SpikeRt, 0.0F, -0.0F, 0.5235987755982988F);
		this.body5SpikeLft = new ModelRenderer(this, 16, 21);
		this.body5SpikeLft.setRotationPoint(0.5F, -0.5F, 0.5F);
		this.body5SpikeLft.addBox(0.0F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		this.setRotateAngle(body5SpikeLft, 0.0F, -0.0F, -0.5235987755982988F);

		this.body7SpikeRt = new ModelRenderer(this, 9, 29);
		this.body7SpikeRt.setRotationPoint(-0.5F, -0.5F, 0.5F);
		this.body7SpikeRt.addBox(-2.0F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		this.setRotateAngle(body7SpikeRt, 0.0F, -0.0F, 0.5235987755982988F);
		this.body7SpikeLft = new ModelRenderer(this, 16, 29);
		this.body7SpikeLft.setRotationPoint(0.5F, -0.5F, 0.5F);
		this.body7SpikeLft.addBox(0.0F, -0.5F, -0.5F, 2, 1, 1, 0.0F);
		this.setRotateAngle(body7SpikeLft, 0.0F, -0.0F, -0.5235987755982988F);

		int bodyNumber = 0;
		this.body[bodyNumber][jointNumber] = new ModelRenderer(this, 0, 4); // body1Joint
		this.body[bodyNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 11.0F);
		this.body[bodyNumber][jointNumber].addBox(-1.0F, -1.0F, -0.5F, 0, 0, 0, 0.0F);
		bodyInfo[bodyNumber][jointNumber] = new PartInfo(body[bodyNumber][jointNumber]);
		this.body[bodyNumber][partNumber] = new ModelRenderer(this, 0, 4); // body1
		this.body[bodyNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body[bodyNumber][partNumber].addBox(-1.0F, -1.0F, -0.5F, 2, 2, 2, 0.0F);
		bodyInfo[bodyNumber][partNumber] = new PartInfo(body[bodyNumber][partNumber]);
		bodyNumber++;
		this.body[bodyNumber][jointNumber] = new ModelRenderer(this, 0, 8); // body2Joint
		this.body[bodyNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.5F);
		this.body[bodyNumber][jointNumber].addBox(-1.0F, -1.0F, -0.5F, 0, 0, 0, 0.0F);
		bodyInfo[bodyNumber][jointNumber] = new PartInfo(body[bodyNumber][jointNumber]);
		this.body[bodyNumber][partNumber] = new ModelRenderer(this, 0, 8); // body2
		this.body[bodyNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body[bodyNumber][partNumber].addBox(-1.0F, -1.0F, -0.5F, 2, 2, 2, 0.0F);
		bodyInfo[bodyNumber][partNumber] = new PartInfo(body[bodyNumber][partNumber]);
		bodyNumber++;
		this.body[bodyNumber][jointNumber] = new ModelRenderer(this, 0, 12); // body3Joint
		this.body[bodyNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.5F);
		this.body[bodyNumber][jointNumber].addBox(-1.0F, -1.0F, -0.5F, 0, 0, 0, 0.0F);
		bodyInfo[bodyNumber][jointNumber] = new PartInfo(body[bodyNumber][jointNumber]);
		this.body[bodyNumber][partNumber] = new ModelRenderer(this, 0, 12); // body3
		this.body[bodyNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body[bodyNumber][partNumber].addBox(-1.0F, -1.0F, -0.5F, 2, 2, 2, 0.0F);
		bodyInfo[bodyNumber][partNumber] = new PartInfo(body[bodyNumber][partNumber]);
		bodyNumber++;
		this.body[bodyNumber][jointNumber] = new ModelRenderer(this, 0, 16); // body4Joint
		this.body[bodyNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.5F);
		this.body[bodyNumber][jointNumber].addBox(-1.0F, -1.0F, -0.5F, 0, 0, 0, 0.0F);
		bodyInfo[bodyNumber][jointNumber] = new PartInfo(body[bodyNumber][jointNumber]);
		this.body[bodyNumber][partNumber] = new ModelRenderer(this, 0, 16); // body4
		this.body[bodyNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body[bodyNumber][partNumber].addBox(-1.0F, -1.0F, -0.5F, 2, 2, 2, 0.0F);
		bodyInfo[bodyNumber][partNumber] = new PartInfo(body[bodyNumber][partNumber]);
		bodyNumber++;
		this.body[bodyNumber][jointNumber] = new ModelRenderer(this, 0, 20); // body5Joint
		this.body[bodyNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.5F);
		this.body[bodyNumber][jointNumber].addBox(-1.0F, -1.0F, -0.5F, 0, 0, 0, 0.0F);
		bodyInfo[bodyNumber][jointNumber] = new PartInfo(body[bodyNumber][jointNumber]);
		this.body[bodyNumber][partNumber] = new ModelRenderer(this, 0, 20); // body5
		this.body[bodyNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body[bodyNumber][partNumber].addBox(-1.0F, -1.0F, -0.5F, 2, 2, 2, 0.0F);
		bodyInfo[bodyNumber][partNumber] = new PartInfo(body[bodyNumber][partNumber]);
		bodyNumber++;
		this.body[bodyNumber][jointNumber] = new ModelRenderer(this, 0, 24); // body6Joint
		this.body[bodyNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.5F);
		this.body[bodyNumber][jointNumber].addBox(-1.0F, -1.0F, -0.5F, 0, 0, 0, 0.0F);
		bodyInfo[bodyNumber][jointNumber] = new PartInfo(body[bodyNumber][jointNumber]);
		this.body[bodyNumber][partNumber] = new ModelRenderer(this, 0, 24); // body6
		this.body[bodyNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body[bodyNumber][partNumber].addBox(-1.0F, -1.0F, -0.5F, 2, 2, 2, 0.0F);
		bodyInfo[bodyNumber][partNumber] = new PartInfo(body[bodyNumber][partNumber]);
		bodyNumber++;
		this.body[bodyNumber][jointNumber] = new ModelRenderer(this, 0, 28); // body7Joint
		this.body[bodyNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.5F);
		this.body[bodyNumber][jointNumber].addBox(-1.0F, -1.0F, -0.5F, 0, 0, 0, 0.0F);
		bodyInfo[bodyNumber][jointNumber] = new PartInfo(body[bodyNumber][jointNumber]);
		this.body[bodyNumber][partNumber] = new ModelRenderer(this, 0, 28); // body7
		this.body[bodyNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body[bodyNumber][partNumber].addBox(-1.0F, -1.0F, -0.5F, 2, 2, 2, 0.0F);
		bodyInfo[bodyNumber][partNumber] = new PartInfo(body[bodyNumber][partNumber]);
		bodyNumber++;
		this.body[bodyNumber][jointNumber] = new ModelRenderer(this, 1, 32); // body8Joint
		this.body[bodyNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.5F);
		this.body[bodyNumber][jointNumber].addBox(-0.5F, -0.5F, -0.5F, 0, 0, 0, 0.0F);
		bodyInfo[bodyNumber][jointNumber] = new PartInfo(body[bodyNumber][jointNumber]);
		this.body[bodyNumber][partNumber] = new ModelRenderer(this, 1, 32); // body8
		this.body[bodyNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body[bodyNumber][partNumber].addBox(-0.5F, -0.5F, -0.5F, 1, 1, 2, 0.0F);
		bodyInfo[bodyNumber][partNumber] = new PartInfo(body[bodyNumber][partNumber]);
		bodyNumber++;
		this.body[bodyNumber][jointNumber] = new ModelRenderer(this, 1, 35); // body9Joint
		this.body[bodyNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.3F);
		this.body[bodyNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		bodyInfo[bodyNumber][jointNumber] = new PartInfo(body[bodyNumber][jointNumber]);
		this.body[bodyNumber][partNumber] = new ModelRenderer(this, 1, 35); // body9
		this.body[bodyNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body[bodyNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		bodyInfo[bodyNumber][partNumber] = new PartInfo(body[bodyNumber][partNumber]);
		bodyNumber++;
		this.body[bodyNumber][jointNumber] = new ModelRenderer(this, 1, 38); // body10Joint
		this.body[bodyNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
		this.body[bodyNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		bodyInfo[bodyNumber][jointNumber] = new PartInfo(body[bodyNumber][jointNumber]);
		this.body[bodyNumber][partNumber] = new ModelRenderer(this, 1, 38); // body10
		this.body[bodyNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body[bodyNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		bodyInfo[bodyNumber][partNumber] = new PartInfo(body[bodyNumber][partNumber]);
		bodyNumber++;
		this.body[bodyNumber][jointNumber] = new ModelRenderer(this, 1, 41); // body11Joint
		this.body[bodyNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
		this.body[bodyNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		bodyInfo[bodyNumber][jointNumber] = new PartInfo(body[bodyNumber][jointNumber]);
		this.body[bodyNumber][partNumber] = new ModelRenderer(this, 1, 41); // body11
		this.body[bodyNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body[bodyNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		bodyInfo[bodyNumber][partNumber] = new PartInfo(body[bodyNumber][partNumber]);
		bodyNumber++;
		this.body[bodyNumber][jointNumber] = new ModelRenderer(this, 1, 44); // body12Joint
		this.body[bodyNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.6F);
		this.body[bodyNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		bodyInfo[bodyNumber][jointNumber] = new PartInfo(body[bodyNumber][jointNumber]);
		this.body[bodyNumber][partNumber] = new ModelRenderer(this, 1, 44); // body12
		this.body[bodyNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body[bodyNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		bodyInfo[bodyNumber][partNumber] = new PartInfo(body[bodyNumber][partNumber]);

		this.tailFinRtEnd = new ModelRenderer(this, 0, 48);
		this.tailFinRtEnd.setRotationPoint(0.0F, 0.0F, 1.6F);
		this.tailFinRtEnd.addBox(-0.5F, -0.51F, -0.5F, 1, 1, 3, 0.0F);
		this.setRotateAngle(tailFinRtEnd, 0.0F, -0.8726646259971648F, 0.0F);
		this.tailFinRtMid = new ModelRenderer(this, 5, 53);
		this.tailFinRtMid.setRotationPoint(0.0F, 0.0F, 1.6F);
		this.tailFinRtMid.addBox(-0.5F, 0.0F, -0.2F, 1, 0, 3, 0.0F);
		this.setRotateAngle(tailFinRtMid, 0.0F, -0.4363323129985824F, 0.0F);
		this.tailFinCenter = new ModelRenderer(this, 10, 48);
		this.tailFinCenter.setRotationPoint(0.0F, 0.0F, 1.6F);
		this.tailFinCenter.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, 0.0F);
		this.tailFinLftMid = new ModelRenderer(this, 15, 53);
		this.tailFinLftMid.setRotationPoint(0.0F, 0.0F, 1.6F);
		this.tailFinLftMid.addBox(-0.5F, 0.0F, -0.2F, 1, 0, 3, 0.0F);
		this.setRotateAngle(tailFinLftMid, 0.0F, 0.4363323129985824F, 0.0F);
		this.tailFinLftEnd = new ModelRenderer(this, 20, 48);
		this.tailFinLftEnd.setRotationPoint(0.0F, 0.0F, 1.6F);
		this.tailFinLftEnd.addBox(-0.5F, -0.49F, -0.5F, 1, 1, 3, 0.0F);
		this.setRotateAngle(tailFinLftEnd, 0.0F, 0.8726646259971648F, 0.0F);

		this.mouth.addChild(this.mouthFangBot);
		this.mouth.addChild(this.mouthFangLft);
		this.mouth.addChild(this.mouthFangRt);
		this.mouth.addChild(this.mouthFangTop);
		this.mouth.addChild(this.body[0][0]);

		this.body[0][1].addChild(this.body1BotSpk); // body1
		this.body[0][1].addChild(this.body1TopSpk); // body1

		this.body[2][1].addChild(this.body3SpikeLft); // body3
		this.body[2][1].addChild(this.body3SpikeRt); // body3

		this.body[4][1].addChild(this.body5SpikeLft); // body5
		this.body[4][1].addChild(this.body5SpikeRt); // body5

		this.body[6][1].addChild(this.body7SpikeLft); // body7
		this.body[6][1].addChild(this.body7SpikeRt); // body7

		this.body[0][0].addChild(this.body[0][1]); // body1Joint * body1

		for(int i = 1; i < body.length; i++)
		{
			this.body[i - 1][1].addChild(this.body[i][0]);
			this.body[i][0].addChild(this.body[i][1]);
		}

		this.body[11][1].addChild(this.tailFinCenter); // body12
		this.body[11][1].addChild(this.tailFinLftEnd); // body12
		this.body[11][1].addChild(this.tailFinLftMid); // body12
		this.body[11][1].addChild(this.tailFinRtEnd); // body12
		this.body[11][1].addChild(this.tailFinRtMid); // body12

		animationDeployer = new AnimationDeployer(this);
	}

	@Override
	public void render(Entity entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize) 
	{ 
		animate(entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);

		this.mouth.render(modelSize);
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

		animateFangs((EntityForcewindEelektrik)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);
		animateBody((EntityForcewindEelektrik)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize, angularVelocity, verticleVelocity);

		deployAnimations();
	}

	public void resetPartInfos()
	{
		mouthFangBotInfo.resetNewAngles();
		mouthFangLftInfo.resetNewAngles();
		mouthFangRtInfo.resetNewAngles();
		mouthFangTopInfo.resetNewAngles();

		mouthInfo.resetNewAngles();

		for(int i = 0; i < body.length; i++)
		{
			bodyInfo[i][0].resetNewAngles();
			bodyInfo[i][1].resetNewAngles();
		}
	}

	public void animateFangs(EntityForcewindEelektrik entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize)
	{
		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockFangs();

		float idleAmplitude = 0.05F;
		float idleJitterAmplitude = 0.05F;

		// + (float)Math.toRadians(45)
		float angleChange = 0F;

		if(currentIdleAnimationClock.getPhaseDurationCoveredX(0) < 1F)
		{
			angleChange += -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI) * idleAmplitude + idleAmplitude;

			angleChange += -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * 5 * PI) * idleJitterAmplitude + idleJitterAmplitude;
		}
		else
		{
			angleChange += -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(1) * PI + PI) * idleAmplitude + idleAmplitude;
		}

		mouthFangBotInfo.setNewRotateX(mouthFangBotInfo.getNewRotateX() - angleChange);
		mouthFangLftInfo.setNewRotateY(mouthFangLftInfo.getNewRotateY() + angleChange);
		mouthFangRtInfo.setNewRotateY(mouthFangRtInfo.getNewRotateY() - angleChange);
		mouthFangTopInfo.setNewRotateX(mouthFangTopInfo.getNewRotateX() + angleChange);
	}

	public void animateBody(EntityForcewindEelektrik entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize, float angularVelocity, float verticalVelocity)
	{
		applyIdleBodyAnimation(entity.getIdleAnimationClockBody(0), mouthInfo, 0, distanceMoved, horzVelocity, angularVelocity, verticalVelocity);

		for(int i = 0; i < body.length; i++)
		{
			applyIdleBodyAnimation(entity.getIdleAnimationClockBody(i + 1), bodyInfo[i][1], i + 1, distanceMoved, horzVelocity, angularVelocity, verticalVelocity);
		}
	}

	public void applyIdleBodyAnimation(IdleAnimationClock currentIdleAnimationClock, PartInfo partInfo, int partNumber, float distanceMoved, float horzVelocity, float angularVelocity, float verticalVelocity)
	{
		float yawChangeAngle = -(float)Math.toRadians(12);
		float verticalVelocityChangeAngle = (float)Math.toRadians(-9);

		float newVerticalVelocity = 1.5F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;

		float absoluteMoveVelocity = horzVelocity + Math.abs(newVerticalVelocity);

		if(absoluteMoveVelocity > 1F)
			absoluteMoveVelocity = 1F;

		float idleAmplitudeX = 0.07F;
		float idleAmplitudeY = 0.15F;

		float moveAmplitudeX = 0.175F;

		// + (float)Math.toRadians(45)
		float angleChangeX = 0;
		float angleChangeY = 0;

		// Idle Animations
		angleChangeX += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2) * idleAmplitudeX 
				* (1F - absoluteMoveVelocity) * (1F - Math.abs(angularVelocity) * 0.5F);
		angleChangeY += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2) * idleAmplitudeY
				* (1F - absoluteMoveVelocity) * (1F - Math.abs(angularVelocity) * 0.5F);

		// Movement Animations
		angleChangeX += PartAnimate.negCosRotateAnimationAdjusted(distanceMoved - (8F * PI * ((float)partNumber / (float)(body.length))), absoluteMoveVelocity, RUN_FREQUENCY, moveAmplitudeX) 
				+ verticalVelocityChangeAngle * newVerticalVelocity;
		angleChangeY += angularVelocity * yawChangeAngle;

		partInfo.setNewRotateX(partInfo.getNewRotateX() + angleChangeX);
		partInfo.setNewRotateY(partInfo.getNewRotateY() + angleChangeY);
	}

	public void deployAnimations()
	{
		this.animationDeployer.rotate(mouthFangBot, mouthFangBotInfo.getNewRotates());
		this.animationDeployer.rotate(mouthFangLft, mouthFangLftInfo.getNewRotates());
		this.animationDeployer.rotate(mouthFangRt, mouthFangRtInfo.getNewRotates());
		this.animationDeployer.rotate(mouthFangTop, mouthFangTopInfo.getNewRotates());

		this.animationDeployer.rotate(mouth, mouthInfo.getNewRotates());

		for(int i = 0; i < body.length; i++)
		{
			this.animationDeployer.rotate(body[i][0], bodyInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(body[i][1], bodyInfo[i][1].getNewRotates());
		}

		this.animationDeployer.applyChanges();
	}
}