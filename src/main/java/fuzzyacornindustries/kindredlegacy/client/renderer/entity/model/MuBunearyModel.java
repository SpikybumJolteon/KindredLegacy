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
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.HostilePokemon;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.MuBunearyEntity;
import net.minecraft.client.renderer.entity.model.TintedAgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class MuBunearyModel<T extends HostilePokemon> extends TintedAgeableModel<T>
{
	public final float PI = (float)Math.PI;

	public final float RUN_FREQUENCY = 0.6F;
	public final float WALK_FREQUENCY = 1.2F;

	public final float NEW_BODYJOINT_RUN_ANGLE_X = (float)Math.toRadians(90);

	private AnimationDeployer animationDeployer;

	public ModelRenderer bodyJoint;
	public ModelRenderer body;
	PartInfo bodyJointInfo;
	PartInfo bodyInfo;

	public ModelRenderer cottonSkirtFntLft;
	public ModelRenderer cottonSkirtFntRt;
	public ModelRenderer cottonSkirtBckLft;
	public ModelRenderer cottonSkirtBckRt;

	public ModelRenderer legLft;
	public ModelRenderer legRt;
	PartInfo legLftInfo;
	PartInfo legRtInfo;

	public ModelRenderer armLft;
	public ModelRenderer armRt;
	PartInfo armLftInfo;
	PartInfo armRtInfo;

	public ModelRenderer neck;
	public ModelRenderer mane;
	PartInfo neckInfo;
	PartInfo maneInfo;

	public ModelRenderer headJoint;
	public ModelRenderer head;
	public ModelRenderer muzzle;
	PartInfo headJointInfo;
	PartInfo headInfo;

	public ModelRenderer earLft[] = new ModelRenderer[14];
	PartInfo earLftInfo[] = new PartInfo[earLft.length];

	public ModelRenderer earRt[] = new ModelRenderer[14];
	PartInfo earRtInfo[] = new PartInfo[earRt.length];

	public ModelRenderer antennaLft[] = new ModelRenderer[15];
	PartInfo antennaLftInfo[] = new PartInfo[antennaLft.length];

	public ModelRenderer antennaRt[] = new ModelRenderer[15];
	PartInfo antennaRtInfo[] = new PartInfo[antennaRt.length];

	public ModelRenderer tail[] = new ModelRenderer[3];
	PartInfo tailInfo[] = new PartInfo[tail.length];

	public MuBunearyModel() 
	{
		this.textureWidth = 64;
		this.textureHeight = 32;

		this.bodyJoint = new ModelRenderer(this, 0, 0);
		this.bodyJoint.setRotationPoint(0.0F, 19.3F, 0.0F);
		this.bodyJoint.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
		bodyJointInfo = new PartInfo(bodyJoint);
		this.body = new ModelRenderer(this, 4, 18);
		this.body.setRotationPoint(0.0F, 2.0F, 0.0F);
		this.body.addBox(-1.0F, -4.3F, -1.0F, 2, 4, 2, 0.0F);
		bodyInfo = new PartInfo(body);

		this.cottonSkirtFntLft = new ModelRenderer(this, 0, 24);
		this.cottonSkirtFntLft.setRotationPoint(0.0F, -0.5F, -0.6F);
		this.cottonSkirtFntLft.addBox(-0.4F, -0.8F, -1.2F, 2, 2, 2, 0.0F);
		this.cottonSkirtFntRt = new ModelRenderer(this, 8, 24);
		this.cottonSkirtFntRt.setRotationPoint(0.0F, -0.5F, -0.6F);
		this.cottonSkirtFntRt.addBox(-1.9F, -0.5F, -1.0F, 2, 2, 2, 0.0F);
		this.cottonSkirtBckLft = new ModelRenderer(this, 8, 28);
		this.cottonSkirtBckLft.setRotationPoint(0.0F, -0.5F, 0.6F);
		this.cottonSkirtBckLft.addBox(-0.2F, -0.5F, -1.1F, 2, 2, 2, 0.0F);
		this.cottonSkirtBckRt = new ModelRenderer(this, 0, 28);
		this.cottonSkirtBckRt.setRotationPoint(0.0F, -0.5F, 0.6F);
		this.cottonSkirtBckRt.addBox(-2.0F, -0.8F, -0.9F, 2, 2, 2, 0.0F);

		this.legLft = new ModelRenderer(this, 24, 8);
		this.legLft.setRotationPoint(0.5F, 0.0F, 0.0F);
		this.legLft.addBox(-0.49F, -0.3F, -0.5F, 1, 3, 1, 0.0F);
		legLftInfo = new PartInfo(legLft);
		this.legRt = new ModelRenderer(this, 19, 8);
		this.legRt.setRotationPoint(-0.5F, 0.0F, 0.0F);
		this.legRt.addBox(-0.51F, -0.3F, -0.5F, 1, 3, 1, 0.0F);
		legRtInfo = new PartInfo(legRt);

		this.armLft = new ModelRenderer(this, 24, 2);
		this.armLft.setRotationPoint(0.99F, -3.5F, 0.0F);
		this.armLft.addBox(-0.5F, -0.5F, -3.5F, 1, 1, 4, 0.0F);
		this.setRotateAngle(armLft, 0.6108652381980153F, -0.4363323129985824F, 0.0F);
		armLftInfo = new PartInfo(armLft);
		this.armRt = new ModelRenderer(this, 13, 2);
		this.armRt.setRotationPoint(-0.99F, -3.5F, 0.0F);
		this.armRt.addBox(-0.5F, -0.5F, -3.5F, 1, 1, 4, 0.0F);
		this.setRotateAngle(armRt, 0.6108652381980153F, 0.4363323129985824F, 0.0F);
		armRtInfo = new PartInfo(armRt);

		this.neck = new ModelRenderer(this, 6, 7);
		this.neck.setRotationPoint(0.0F, -4.0F, 0.0F);
		this.neck.addBox(-0.5F, -2.3F, -0.5F, 1, 3, 1, 0.0F);
		neckInfo = new PartInfo(neck);
		this.mane = new ModelRenderer(this, 1, 11);
		this.mane.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.mane.addBox(-2.0F, -1.5F, -1.2F, 4, 3, 3, 0.0F);
		this.setRotateAngle(mane, 0.17453292519943295F, 0.0F, 0.0F);
		maneInfo = new PartInfo(mane);

		this.headJoint = new ModelRenderer(this, 0, 0);
		this.headJoint.setRotationPoint(0.0F, -2.0F, 0.0F);
		this.headJoint.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
		headJointInfo = new PartInfo(headJoint);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-1.5F, -2.3F, -2.0F, 3, 3, 3, 0.0F);
		headInfo = new PartInfo(head);
		this.muzzle = new ModelRenderer(this, 1, 7);
		this.muzzle.setRotationPoint(0.0F, 0.0F, -1.9F);
		this.muzzle.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
		this.setRotateAngle(muzzle, 0.17453292519943295F, 0.0F, 0.0F);

		int earNumber = 0;
		this.earLft[earNumber] = new ModelRenderer(this, 49, 28); // earLft1
		this.earLft[earNumber].setRotationPoint(0.8F, -1.8F, -0.5F);
		this.earLft[earNumber].addBox(-1.0F, -1.5F, -0.5F, 2, 2, 1, 0.0F);
		this.setRotateAngle(earLft[earNumber], 0.0F, 0.0F, 0.5235987755982988F);
		earLftInfo[earNumber] = new PartInfo(earLft[earNumber]);
		earNumber++;
		this.earLft[earNumber] = new ModelRenderer(this, 49, 26); // earLft2
		this.earLft[earNumber].setRotationPoint(0.0F, -1.3F, 0.0F);
		this.earLft[earNumber].addBox(-1.0F, -0.7F, -0.5F, 2, 1, 1, 0.0F);
		earLftInfo[earNumber] = new PartInfo(earLft[earNumber]);
		earNumber++;
		this.earLft[earNumber] = new ModelRenderer(this, 48, 24); // earLft3
		this.earLft[earNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earLft[earNumber].addBox(-1.5F, -0.8F, -0.5F, 3, 1, 1, 0.0F);
		earLftInfo[earNumber] = new PartInfo(earLft[earNumber]);
		earNumber++;
		this.earLft[earNumber] = new ModelRenderer(this, 48, 22); // earLft4
		this.earLft[earNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earLft[earNumber].addBox(-1.5F, -0.8F, -0.5F, 3, 1, 1, 0.0F);
		earLftInfo[earNumber] = new PartInfo(earLft[earNumber]);
		earNumber++;
		this.earLft[earNumber] = new ModelRenderer(this, 48, 20); // earLft5
		this.earLft[earNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earLft[earNumber].addBox(-1.5F, -0.8F, -0.5F, 3, 1, 1, 0.0F);
		earLftInfo[earNumber] = new PartInfo(earLft[earNumber]);
		earNumber++;
		this.earLft[earNumber] = new ModelRenderer(this, 48, 18); // earLft6
		this.earLft[earNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earLft[earNumber].addBox(-1.5F, -0.8F, -0.5F, 3, 1, 1, 0.0F);
		earLftInfo[earNumber] = new PartInfo(earLft[earNumber]);
		earNumber++;
		this.earLft[earNumber] = new ModelRenderer(this, 48, 16); // earLft7
		this.earLft[earNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earLft[earNumber].addBox(-1.5F, -0.8F, -0.5F, 3, 1, 1, 0.0F);
		earLftInfo[earNumber] = new PartInfo(earLft[earNumber]);
		earNumber++;
		this.earLft[earNumber] = new ModelRenderer(this, 48, 14); // earLft8
		this.earLft[earNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earLft[earNumber].addBox(-1.5F, -0.8F, -0.5F, 3, 1, 1, 0.0F);
		earLftInfo[earNumber] = new PartInfo(earLft[earNumber]);
		earNumber++;
		this.earLft[earNumber] = new ModelRenderer(this, 48, 12); // earLft9
		this.earLft[earNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earLft[earNumber].addBox(-1.5F, -0.8F, -0.5F, 3, 1, 1, 0.0F);
		earLftInfo[earNumber] = new PartInfo(earLft[earNumber]);
		earNumber++;
		this.earLft[earNumber] = new ModelRenderer(this, 48, 10); // earLft10
		this.earLft[earNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earLft[earNumber].addBox(-1.5F, -0.8F, -0.5F, 3, 1, 1, 0.0F);
		earLftInfo[earNumber] = new PartInfo(earLft[earNumber]);
		earNumber++;
		this.earLft[earNumber] = new ModelRenderer(this, 48, 8); // earLft11
		this.earLft[earNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earLft[earNumber].addBox(-1.5F, -0.8F, -0.5F, 3, 1, 1, 0.0F);
		earLftInfo[earNumber] = new PartInfo(earLft[earNumber]);
		earNumber++;
		this.earLft[earNumber] = new ModelRenderer(this, 48, 6); // earLft12
		this.earLft[earNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earLft[earNumber].addBox(-1.5F, -0.8F, -0.5F, 3, 1, 1, 0.0F);
		earLftInfo[earNumber] = new PartInfo(earLft[earNumber]);
		earNumber++;
		this.earLft[earNumber] = new ModelRenderer(this, 48, 4); // earLft13
		this.earLft[earNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earLft[earNumber].addBox(-1.5F, -0.8F, -0.5F, 3, 1, 1, 0.0F);
		earLftInfo[earNumber] = new PartInfo(earLft[earNumber]);
		earNumber++;
		this.earLft[earNumber] = new ModelRenderer(this, 48, 0); // earLft14
		this.earLft[earNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earLft[earNumber].addBox(-1.0F, -1.8F, -1.0F, 2, 2, 2, 0.0F);
		earLftInfo[earNumber] = new PartInfo(earLft[earNumber]);

		earNumber = 0;
		this.earRt[earNumber] = new ModelRenderer(this, 41, 28); // earRt1
		this.earRt[earNumber].setRotationPoint(-0.8F, -1.8F, -0.5F);
		this.earRt[earNumber].addBox(-1.0F, -1.5F, -0.5F, 2, 2, 1, 0.0F);
		this.setRotateAngle(earRt[earNumber], 0.0F, 0.0F, -0.5235987755982988F);
		earRtInfo[earNumber] = new PartInfo(earRt[earNumber]);
		earNumber++;
		this.earRt[earNumber] = new ModelRenderer(this, 41, 26); // earRt2
		this.earRt[earNumber].setRotationPoint(0.0F, -1.3F, 0.0F);
		this.earRt[earNumber].addBox(-1.0F, -0.7F, -0.5F, 2, 1, 1, 0.0F);
		earRtInfo[earNumber] = new PartInfo(earRt[earNumber]);
		earNumber++;
		this.earRt[earNumber] = new ModelRenderer(this, 40, 24); // earRt3
		this.earRt[earNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earRt[earNumber].addBox(-1.5F, -0.8F, -0.5F, 3, 1, 1, 0.0F);
		earRtInfo[earNumber] = new PartInfo(earRt[earNumber]);
		earNumber++;
		this.earRt[earNumber] = new ModelRenderer(this, 40, 22); // earRt4
		this.earRt[earNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earRt[earNumber].addBox(-1.5F, -0.8F, -0.5F, 3, 1, 1, 0.0F);
		earRtInfo[earNumber] = new PartInfo(earRt[earNumber]);
		earNumber++;
		this.earRt[earNumber] = new ModelRenderer(this, 40, 20); // earRt5
		this.earRt[earNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earRt[earNumber].addBox(-1.5F, -0.8F, -0.5F, 3, 1, 1, 0.0F);
		earRtInfo[earNumber] = new PartInfo(earRt[earNumber]);
		earNumber++;
		this.earRt[earNumber] = new ModelRenderer(this, 40, 18); // earRt6
		this.earRt[earNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earRt[earNumber].addBox(-1.5F, -0.8F, -0.5F, 3, 1, 1, 0.0F);
		earRtInfo[earNumber] = new PartInfo(earRt[earNumber]);
		earNumber++;
		this.earRt[earNumber] = new ModelRenderer(this, 40, 16); // earRt7
		this.earRt[earNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earRt[earNumber].addBox(-1.5F, -0.8F, -0.5F, 3, 1, 1, 0.0F);
		earRtInfo[earNumber] = new PartInfo(earRt[earNumber]);
		earNumber++;
		this.earRt[earNumber] = new ModelRenderer(this, 40, 14); // earRt8
		this.earRt[earNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earRt[earNumber].addBox(-1.5F, -0.8F, -0.5F, 3, 1, 1, 0.0F);
		earRtInfo[earNumber] = new PartInfo(earRt[earNumber]);
		earNumber++;
		this.earRt[earNumber] = new ModelRenderer(this, 40, 12); // earRt9
		this.earRt[earNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earRt[earNumber].addBox(-1.5F, -0.8F, -0.5F, 3, 1, 1, 0.0F);
		earRtInfo[earNumber] = new PartInfo(earRt[earNumber]);
		earNumber++;
		this.earRt[earNumber] = new ModelRenderer(this, 40, 10); // earRt10
		this.earRt[earNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earRt[earNumber].addBox(-1.5F, -0.8F, -0.5F, 3, 1, 1, 0.0F);
		earRtInfo[earNumber] = new PartInfo(earRt[earNumber]);
		earNumber++;
		this.earRt[earNumber] = new ModelRenderer(this, 40, 8); // earRt11
		this.earRt[earNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earRt[earNumber].addBox(-1.5F, -0.8F, -0.5F, 3, 1, 1, 0.0F);
		earRtInfo[earNumber] = new PartInfo(earRt[earNumber]);
		earNumber++;
		this.earRt[earNumber] = new ModelRenderer(this, 40, 6); // earRt12
		this.earRt[earNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earRt[earNumber].addBox(-1.5F, -0.8F, -0.5F, 3, 1, 1, 0.0F);
		earRtInfo[earNumber] = new PartInfo(earRt[earNumber]);
		earNumber++;
		this.earRt[earNumber] = new ModelRenderer(this, 40, 4); // earRt13
		this.earRt[earNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earRt[earNumber].addBox(-1.5F, -0.8F, -0.5F, 3, 1, 1, 0.0F);
		earRtInfo[earNumber] = new PartInfo(earRt[earNumber]);
		earNumber++;
		this.earRt[earNumber] = new ModelRenderer(this, 40, 0); // earRt14
		this.earRt[earNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.earRt[earNumber].addBox(-1.0F, -1.8F, -1.0F, 2, 2, 2, 0.0F);
		earRtInfo[earNumber] = new PartInfo(earRt[earNumber]);

		int antennaNumber = 0;
		this.antennaLft[antennaNumber] = new ModelRenderer(this, 56, 29); // antennaLft1
		this.antennaLft[antennaNumber].setRotationPoint(1.3F, -0.2F, -0.5F);
		this.antennaLft[antennaNumber].addBox(-0.5F, -1.7F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(antennaLft[antennaNumber], 0.0F, 0.0F, 1.5707963267948966F);
		antennaLftInfo[antennaNumber] = new PartInfo(antennaLft[antennaNumber]);
		antennaNumber++;
		this.antennaLft[antennaNumber] = new ModelRenderer(this, 60, 28); // antennaLft2
		this.antennaLft[antennaNumber].setRotationPoint(0.0F, -1.6F, 0.0F);
		this.antennaLft[antennaNumber].addBox(-0.5F, -0.8F, -0.5F, 1, 1, 1, 0.0F);
		antennaLftInfo[antennaNumber] = new PartInfo(antennaLft[antennaNumber]);
		antennaNumber++;
		this.antennaLft[antennaNumber] = new ModelRenderer(this, 57, 26); // antennaLft3
		this.antennaLft[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaLft[antennaNumber].addBox(-1.0F, -0.8F, -0.5F, 2, 1, 1, 0.0F);
		antennaLftInfo[antennaNumber] = new PartInfo(antennaLft[antennaNumber]);
		antennaNumber++;
		this.antennaLft[antennaNumber] = new ModelRenderer(this, 57, 24); // antennaLft4
		this.antennaLft[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaLft[antennaNumber].addBox(-1.0F, -0.8F, -0.5F, 2, 1, 1, 0.0F);
		antennaLftInfo[antennaNumber] = new PartInfo(antennaLft[antennaNumber]);
		antennaNumber++;
		this.antennaLft[antennaNumber] = new ModelRenderer(this, 57, 22); // antennaLft5
		this.antennaLft[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaLft[antennaNumber].addBox(-1.0F, -0.8F, -0.5F, 2, 1, 1, 0.0F);
		antennaLftInfo[antennaNumber] = new PartInfo(antennaLft[antennaNumber]);
		antennaNumber++;
		this.antennaLft[antennaNumber] = new ModelRenderer(this, 57, 20); // antennaLft6
		this.antennaLft[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaLft[antennaNumber].addBox(-1.0F, -0.8F, -0.5F, 2, 1, 1, 0.0F);
		antennaLftInfo[antennaNumber] = new PartInfo(antennaLft[antennaNumber]);
		antennaNumber++;
		this.antennaLft[antennaNumber] = new ModelRenderer(this, 57, 18); // antennaLft7
		this.antennaLft[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaLft[antennaNumber].addBox(-1.0F, -0.8F, -0.5F, 2, 1, 1, 0.0F);
		antennaLftInfo[antennaNumber] = new PartInfo(antennaLft[antennaNumber]);
		antennaNumber++;
		this.antennaLft[antennaNumber] = new ModelRenderer(this, 57, 16); // antennaLft8
		this.antennaLft[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaLft[antennaNumber].addBox(-1.0F, -0.8F, -0.5F, 2, 1, 1, 0.0F);
		antennaLftInfo[antennaNumber] = new PartInfo(antennaLft[antennaNumber]);
		antennaNumber++;
		this.antennaLft[antennaNumber] = new ModelRenderer(this, 57, 14); // antennaLft9
		this.antennaLft[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaLft[antennaNumber].addBox(-1.0F, -0.8F, -0.5F, 2, 1, 1, 0.0F);
		antennaLftInfo[antennaNumber] = new PartInfo(antennaLft[antennaNumber]);
		antennaNumber++;
		this.antennaLft[antennaNumber] = new ModelRenderer(this, 57, 12); // antennaLft10
		this.antennaLft[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaLft[antennaNumber].addBox(-1.0F, -0.8F, -0.5F, 2, 1, 1, 0.0F);
		antennaLftInfo[antennaNumber] = new PartInfo(antennaLft[antennaNumber]);
		antennaNumber++;
		this.antennaLft[antennaNumber] = new ModelRenderer(this, 57, 10); // antennaLft11
		this.antennaLft[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaLft[antennaNumber].addBox(-1.0F, -0.8F, -0.5F, 2, 1, 1, 0.0F);
		antennaLftInfo[antennaNumber] = new PartInfo(antennaLft[antennaNumber]);
		antennaNumber++;
		this.antennaLft[antennaNumber] = new ModelRenderer(this, 58, 8); // antennaLft12
		this.antennaLft[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaLft[antennaNumber].addBox(-0.5F, -0.8F, -0.5F, 1, 1, 1, 0.0F);
		antennaLftInfo[antennaNumber] = new PartInfo(antennaLft[antennaNumber]);
		antennaNumber++;
		this.antennaLft[antennaNumber] = new ModelRenderer(this, 58, 6); // antennaLft13
		this.antennaLft[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaLft[antennaNumber].addBox(-0.5F, -0.8F, -0.5F, 1, 1, 1, 0.0F);
		antennaLftInfo[antennaNumber] = new PartInfo(antennaLft[antennaNumber]);
		antennaNumber++;
		this.antennaLft[antennaNumber] = new ModelRenderer(this, 58, 4); // antennaLft14
		this.antennaLft[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaLft[antennaNumber].addBox(-0.5F, -0.8F, -0.5F, 1, 1, 1, 0.0F);
		antennaLftInfo[antennaNumber] = new PartInfo(antennaLft[antennaNumber]);
		antennaNumber++;
		this.antennaLft[antennaNumber] = new ModelRenderer(this, 56, 0); // antennaLft15
		this.antennaLft[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaLft[antennaNumber].addBox(-1.0F, -1.8F, -1.0F, 2, 2, 2, 0.0F);
		antennaLftInfo[antennaNumber] = new PartInfo(antennaLft[antennaNumber]);

		antennaNumber = 0;
		this.antennaRt[antennaNumber] = new ModelRenderer(this, 36, 29); // antennaRt1
		this.antennaRt[antennaNumber].setRotationPoint(-1.3F, -0.2F, -0.5F);
		this.antennaRt[antennaNumber].addBox(-0.5F, -1.7F, -0.5F, 1, 2, 1, 0.0F);
		this.setRotateAngle(antennaRt[antennaNumber], 0.0F, 0.0F, -1.5707963267948966F);
		antennaRtInfo[antennaNumber] = new PartInfo(antennaRt[antennaNumber]);
		antennaNumber++;
		this.antennaRt[antennaNumber] = new ModelRenderer(this, 32, 28); // antennaRt2
		this.antennaRt[antennaNumber].setRotationPoint(0.0F, -1.6F, 0.0F);
		this.antennaRt[antennaNumber].addBox(-0.5F, -0.8F, -0.5F, 1, 1, 1, 0.0F);
		antennaRtInfo[antennaNumber] = new PartInfo(antennaRt[antennaNumber]);
		antennaNumber++;
		this.antennaRt[antennaNumber] = new ModelRenderer(this, 33, 26); // antennaRt3
		this.antennaRt[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaRt[antennaNumber].addBox(-1.0F, -0.8F, -0.5F, 2, 1, 1, 0.0F);
		antennaRtInfo[antennaNumber] = new PartInfo(antennaRt[antennaNumber]);
		antennaNumber++;
		this.antennaRt[antennaNumber] = new ModelRenderer(this, 33, 24); // antennaRt4
		this.antennaRt[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaRt[antennaNumber].addBox(-1.0F, -0.8F, -0.5F, 2, 1, 1, 0.0F);
		antennaRtInfo[antennaNumber] = new PartInfo(antennaRt[antennaNumber]);
		antennaNumber++;
		this.antennaRt[antennaNumber] = new ModelRenderer(this, 33, 22); // antennaRt5
		this.antennaRt[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaRt[antennaNumber].addBox(-1.0F, -0.8F, -0.5F, 2, 1, 1, 0.0F);
		antennaRtInfo[antennaNumber] = new PartInfo(antennaRt[antennaNumber]);
		antennaNumber++;
		this.antennaRt[antennaNumber] = new ModelRenderer(this, 33, 20); // antennaRt6
		this.antennaRt[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaRt[antennaNumber].addBox(-1.0F, -0.8F, -0.5F, 2, 1, 1, 0.0F);
		antennaRtInfo[antennaNumber] = new PartInfo(antennaRt[antennaNumber]);
		antennaNumber++;
		this.antennaRt[antennaNumber] = new ModelRenderer(this, 33, 18); // antennaRt7
		this.antennaRt[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaRt[antennaNumber].addBox(-1.0F, -0.8F, -0.5F, 2, 1, 1, 0.0F);
		antennaRtInfo[antennaNumber] = new PartInfo(antennaRt[antennaNumber]);
		antennaNumber++;
		this.antennaRt[antennaNumber] = new ModelRenderer(this, 33, 16); // antennaRt8
		this.antennaRt[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaRt[antennaNumber].addBox(-1.0F, -0.8F, -0.5F, 2, 1, 1, 0.0F);
		antennaRtInfo[antennaNumber] = new PartInfo(antennaRt[antennaNumber]);
		antennaNumber++;
		this.antennaRt[antennaNumber] = new ModelRenderer(this, 33, 14); // antennaRt9
		this.antennaRt[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaRt[antennaNumber].addBox(-1.0F, -0.8F, -0.5F, 2, 1, 1, 0.0F);
		antennaRtInfo[antennaNumber] = new PartInfo(antennaRt[antennaNumber]);
		antennaNumber++;
		this.antennaRt[antennaNumber] = new ModelRenderer(this, 33, 12); // antennaRt10
		this.antennaRt[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaRt[antennaNumber].addBox(-1.0F, -0.8F, -0.5F, 2, 1, 1, 0.0F);
		antennaRtInfo[antennaNumber] = new PartInfo(antennaRt[antennaNumber]);
		antennaNumber++;
		this.antennaRt[antennaNumber] = new ModelRenderer(this, 33, 10); // antennaRt11
		this.antennaRt[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaRt[antennaNumber].addBox(-1.0F, -0.8F, -0.5F, 2, 1, 1, 0.0F);
		antennaRtInfo[antennaNumber] = new PartInfo(antennaRt[antennaNumber]);
		antennaNumber++;
		this.antennaRt[antennaNumber] = new ModelRenderer(this, 34, 8); // antennaRt12
		this.antennaRt[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaRt[antennaNumber].addBox(-0.5F, -0.8F, -0.5F, 1, 1, 1, 0.0F);
		antennaRtInfo[antennaNumber] = new PartInfo(antennaRt[antennaNumber]);
		antennaNumber++;
		this.antennaRt[antennaNumber] = new ModelRenderer(this, 34, 6); // antennaRt13
		this.antennaRt[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaRt[antennaNumber].addBox(-0.5F, -0.8F, -0.5F, 1, 1, 1, 0.0F);
		antennaRtInfo[antennaNumber] = new PartInfo(antennaRt[antennaNumber]);
		antennaNumber++;
		this.antennaRt[antennaNumber] = new ModelRenderer(this, 34, 4); // antennaRt14
		this.antennaRt[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaRt[antennaNumber].addBox(-0.5F, -0.8F, -0.5F, 1, 1, 1, 0.0F);
		antennaRtInfo[antennaNumber] = new PartInfo(antennaRt[antennaNumber]);
		antennaNumber++;
		this.antennaRt[antennaNumber] = new ModelRenderer(this, 32, 0); // antennaRt15
		this.antennaRt[antennaNumber].setRotationPoint(0.0F, -0.6F, 0.0F);
		this.antennaRt[antennaNumber].addBox(-1.0F, -1.8F, -1.0F, 2, 2, 2, 0.0F);
		antennaRtInfo[antennaNumber] = new PartInfo(antennaRt[antennaNumber]);

		int tailNumber = 0;
		this.tail[tailNumber] = new ModelRenderer(this, 19, 13); // tail1
		this.tail[tailNumber].setRotationPoint(0.01F, -1.0F, 1.2F);
		this.tail[tailNumber].addBox(-1.5F, -1.5F, -0.3F, 3, 3, 2, 0.0F);
		this.setRotateAngle(tail[tailNumber], 0.3490658503988659F, 0.0F, 0.0F);
		tailInfo[tailNumber] = new PartInfo(tail[tailNumber]);
		tailNumber++;
		this.tail[tailNumber] = new ModelRenderer(this, 16, 18); // tail2
		this.tail[tailNumber].setRotationPoint(0.0F, 0.0F, 1.2F);
		this.tail[tailNumber].addBox(-2.5F, -2.5F, -0.3F, 5, 5, 3, 0.0F);
		tailInfo[tailNumber] = new PartInfo(tail[tailNumber]);
		tailNumber++;
		this.tail[tailNumber] = new ModelRenderer(this, 18, 26); // tail3
		this.tail[tailNumber].setRotationPoint(0.0F, 0.0F, 1.2F);
		this.tail[tailNumber].addBox(-2.0F, -2.0F, 0.9F, 4, 4, 2, 0.0F);
		tailInfo[tailNumber] = new PartInfo(tail[tailNumber]);

		this.bodyJoint.addChild(this.body);
		this.body.addChild(this.armLft);
		this.body.addChild(this.armRt);
		this.body.addChild(this.cottonSkirtBckLft);
		this.body.addChild(this.cottonSkirtBckRt);
		this.body.addChild(this.cottonSkirtFntLft);
		this.body.addChild(this.cottonSkirtFntRt);
		this.body.addChild(this.legLft);
		this.body.addChild(this.legRt);
		this.body.addChild(this.neck);
		this.body.addChild(this.tail[0]);

		this.neck.addChild(this.mane);
		this.neck.addChild(this.headJoint);
		this.headJoint.addChild(this.head);
		this.head.addChild(this.antennaLft[0]);
		this.head.addChild(this.antennaRt[0]);
		this.head.addChild(this.earLft[0]);
		this.head.addChild(this.earRt[0]);
		this.head.addChild(this.muzzle);

		for(int i = 0; i < earLft.length - 1; i++)
		{
			this.earLft[i].addChild(this.earLft[i + 1]);
			this.earRt[i].addChild(this.earRt[i + 1]);
		}

		for(int i = 0; i < earLft.length - 1; i++)
		{
			this.antennaLft[i].addChild(this.antennaLft[i + 1]);
			this.antennaRt[i].addChild(this.antennaRt[i + 1]);
		}

		for(int i = 0; i < tail.length - 1; i++)
		{
			this.tail[i].addChild(this.tail[i + 1]);
		}

		animationDeployer = new AnimationDeployer(this);
	}

	@Override
	public void setRotationAngles(T entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch)
	{
		animate(entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch);
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

		float verticalVelocity = (float)((IMobMotionTracker)entity).getHeightVelocity();

		float velocityPercentChangeApplied = calculateVelocityPercentChangeApplied(horzVelocity, verticalVelocity);

		float idleDampener = 1F;

		float tick = animationDeployer.getEntity().getAnimationTick() + KindredLegacy.proxy.getPartialTick();

		float sizeModifier = 1F;//(float) Math.sqrt(((EntityOkamiEspeon) entity).defaultHeight / entity.height);

		if(animationDeployer.getEntity().getAnimationID() == MuBunearyEntity.actionIDSpinAttack)
		{
			idleDampener = animateSpin(animationDeployer.getEntity(), distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, tick);
		}

		animateBody((MuBunearyEntity)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, idleDampener, verticalVelocity, velocityPercentChangeApplied);
		animateHead((MuBunearyEntity)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, idleDampener, verticalVelocity, velocityPercentChangeApplied);
		animateArms((MuBunearyEntity)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, idleDampener, verticalVelocity, velocityPercentChangeApplied);
		animateLegs((MuBunearyEntity)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, idleDampener, verticalVelocity, velocityPercentChangeApplied);
		animateTail((MuBunearyEntity)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, idleDampener, verticalVelocity, velocityPercentChangeApplied);

		deployAnimations();
	}

	public void resetPartInfos()
	{
		bodyJointInfo.resetNewAngles();
		bodyJointInfo.resetNewPnt();
		bodyInfo.resetNewAngles();

		armLftInfo.resetNewAngles();
		armRtInfo.resetNewAngles();

		neckInfo.resetNewAngles();

		headJointInfo.resetNewAngles();
		headInfo.resetNewAngles();

		legRtInfo.resetNewAngles();
		legLftInfo.resetNewAngles();
		legRtInfo.resetNewPnt();
		legLftInfo.resetNewPnt();

		for(int i = 0; i < earRtInfo.length; i++)
		{
			earRtInfo[i].resetNewAngles();
			earLftInfo[i].resetNewAngles();
		}

		for(int i = 0; i < antennaRtInfo.length; i++)
		{
			antennaRtInfo[i].resetNewAngles();
			antennaLftInfo[i].resetNewAngles();
		}

		for(int i = 0; i < tailInfo.length; i++)
		{
			tailInfo[i].resetNewAngles();
		}
	}

	public float animateSpin(IAnimatedEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float tick)
	{
		float idleDampener = 1F;

		float actionEnd = 15;
		float earlyActionStart = 4F;
		float preActionEnd = actionEnd - 4F;


		if(tick < earlyActionStart && tick > 0F)
		{
			idleDampener = (1F + (float)MathHelper.cos(PI*(tick/5F)));
		}
		else if(tick < preActionEnd)
		{
			idleDampener = 0;
		}
		else if(tick < actionEnd)
		{
			idleDampener = (1F + (float)MathHelper.cos(PI + PI * ((tick - preActionEnd)/(actionEnd - preActionEnd))));
		}

		if(idleDampener > 1F)
		{
			idleDampener = 1F;
		}

		Vector3f bodyJointPointChanges = new Vector3f();
		Vector3f bodyJointAngleChanges = new Vector3f();

		Vector3f armsAngleChanges = new Vector3f();

		Vector3f earsBaseAngleChanges = new Vector3f();
		Vector3f earsAngleChanges = new Vector3f();
		Vector3f antennaBaseAngleChanges = new Vector3f();
		Vector3f antennaAngleChanges = new Vector3f();

		Vector3f headAngleChanges = new Vector3f();

		float jumpDurationStart = 0F;
		float jumpDurationEnd = actionEnd;

		Vector3f bodyJointJumpPointChanges = new Vector3f(0F, -9.5F, -7F);
		Vector3f bodyJointJumpAngleChanges = new Vector3f(0, (float)Math.toRadians(360), 0);

		Vector3f armsJumpAngleChanges = new Vector3f((float)Math.toRadians(-35), (float)Math.toRadians(-30), 0F);

		Vector3f earsBaseJumpAngleChanges = new Vector3f(0F, 0F, (float)Math.toRadians(15)); // positive for left side; negative for right side
		Vector3f earsJumpAngleChanges = new Vector3f((float)Math.toRadians(-8), (float)Math.toRadians(-4), 0); // negative x for left; positive x for right

		Vector3f antennaBaseJumpAngleChanges = new Vector3f(0F, 0F, (float)Math.toRadians(25)); // positive for left side; negative for right side
		Vector3f antennaJumpAngleChanges = new Vector3f((float)Math.toRadians(-8), (float)Math.toRadians(4), 0); // negative x for left; positive x for right

		Vector3f headJumpAngleChanges = new Vector3f(0, (float)Math.toRadians(75), 0);

		float jumpDurationCovered = PartAnimate.calculateDuration(tick, jumpDurationStart, jumpDurationEnd);

		PartAnimate.changeOverDurationUCurve(bodyJointPointChanges, bodyJointJumpPointChanges,
				jumpDurationCovered);
		PartAnimate.changeOverDurationSCurve(bodyJointAngleChanges, bodyJointJumpAngleChanges,
				jumpDurationCovered);

		PartAnimate.changeOverDurationUCurve(headAngleChanges, headJumpAngleChanges,
				jumpDurationCovered);
		
		PartAnimate.changeOverDurationUCurve(armsAngleChanges, armsJumpAngleChanges,
				jumpDurationCovered);

		PartAnimate.changeOverDurationUCurve(earsBaseAngleChanges, earsBaseJumpAngleChanges,
				jumpDurationCovered);
		PartAnimate.changeOverDurationUCurve(earsAngleChanges, earsJumpAngleChanges,
				jumpDurationCovered);

		PartAnimate.changeOverDurationUCurve(antennaBaseAngleChanges, antennaBaseJumpAngleChanges,
				jumpDurationCovered);
		PartAnimate.changeOverDurationUCurve(antennaAngleChanges, antennaJumpAngleChanges,
				jumpDurationCovered);

		/* ************************* */

		PartAnimate.applyPointChangeVectorToInfo(bodyJointInfo, bodyJointPointChanges);
		PartAnimate.applyRotationChangeVectorToInfo(bodyInfo, bodyJointAngleChanges);

		PartAnimate.applyRotationChangeVectorToInfo(headInfo, headAngleChanges);

		PartAnimate.applyRotationChangeVectorToInfo(earLftInfo[0], earsBaseAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(earRtInfo[0], Vector3f.negative(earsBaseAngleChanges));
		PartAnimate.applyRotationChangeVectorToInfo(antennaLftInfo[0], antennaBaseAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(antennaRtInfo[0], Vector3f.negative(antennaBaseAngleChanges));

		PartAnimate.applyRotationChangeVectorToInfo(armLftInfo, armsAngleChanges);
		PartAnimate.applyRotationChangeVectorToInfo(armRtInfo, armsAngleChanges);

		for(int i = 0; i < earLft.length; i++)
		{
			PartAnimate.applyRotationChangeVectorToInfo(earLftInfo[i], earsAngleChanges);
		}
		earsAngleChanges.setX(earsAngleChanges.getX() * -1F);
		for(int i = 0; i < earRt.length; i++)
		{
			PartAnimate.applyRotationChangeVectorToInfo(earRtInfo[i], earsAngleChanges);
		}

		for(int i = 0; i < antennaLft.length; i++)
		{
			PartAnimate.applyRotationChangeVectorToInfo(antennaLftInfo[i], antennaAngleChanges);
		}
		antennaAngleChanges.setX(antennaAngleChanges.getX() * -1F);
		for(int i = 0; i < antennaLft.length; i++)
		{
			PartAnimate.applyRotationChangeVectorToInfo(antennaRtInfo[i], antennaAngleChanges);
		}

		return idleDampener;		
	}

	public float calculateVelocityPercentChangeApplied(float horzVelocity, float verticalVelocity)
	{
		float appliedPercentage = 0F;
		float horzVelocityValue = 0F;
		float vertVelocityValue = 0F;

		float velocityMax = 0.7F;
		float velocityMin = 0.4F;

		if(horzVelocity > velocityMax)
		{
			horzVelocityValue = 1F;
		}
		else if(horzVelocity > velocityMin)
		{
			horzVelocityValue = (horzVelocity - velocityMin)/(velocityMax - velocityMin);
		}

		if(-verticalVelocity > velocityMax)
		{
			vertVelocityValue = 1F;
		}
		else if(-verticalVelocity > velocityMin)
		{
			vertVelocityValue = (-verticalVelocity - velocityMin)/(velocityMax - velocityMin);
		}

		appliedPercentage = horzVelocityValue + vertVelocityValue;

		if(appliedPercentage >= 1F)
		{
			appliedPercentage = 1F;
		}

		return appliedPercentage;
	}

	public void animateBody(MuBunearyEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float idleDampener, float verticalVelocity, float velocityPercentChangeApplied)
	{
		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockBody();

		float idleAmplitudeX = 0.03F;

		float bodyJumpAngle = (float)Math.toRadians(-55);

		float runAmplitudeX = 0.10F;

		float newVerticalVelocity = 1.5F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;

		float changeInAngleX = (MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeX) * (1F - velocityPercentChangeApplied) * (1F - Math.abs(verticalVelocity));

		changeInAngleX += (PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, WALK_FREQUENCY, runAmplitudeX) + NEW_BODYJOINT_RUN_ANGLE_X + bodyJumpAngle * newVerticalVelocity)
				* velocityPercentChangeApplied;

		bodyJointInfo.setNewRotateX(changeInAngleX * idleDampener);

		float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
		float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);

		float walkMaximumChangeY = 0.75F;
		float runMaximumChangeY = 1.1F;

		float walkCyclePointChange = MathHelper.cos(2 * walkCycleInterval * 2 * PI + PI/2) * walkMaximumChangeY * (1 - horzVelocity);
		float runCyclePointChange = MathHelper.cos(2 * runCycleInterval * 2 * PI + PI/2) * runMaximumChangeY * horzVelocity;

		bodyJointInfo.setNewPointY(bodyJointInfo.getNewPointY() + (walkCyclePointChange + runCyclePointChange) * horzVelocity * (1F - Math.abs(verticalVelocity) * idleDampener));

		float runPointChangeY = 1.2F;

		bodyJointInfo.setNewPointY(bodyJointInfo.getNewPointY() + runPointChangeY * velocityPercentChangeApplied * idleDampener);
	}

	public void animateHead(MuBunearyEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float idleDampener, float verticalVelocity, float velocityPercentChangeApplied)
	{
		neckInfo.setNewRotateX((float)Math.toRadians(-35) * velocityPercentChangeApplied * idleDampener);

		JointAnimation.reverseJointRotatesChangeX(bodyJointInfo, headJointInfo);
		JointAnimation.reverseJointRotatesChangeX(neckInfo, headJointInfo);

		PartAnimate.headAnimateInfoOnlyWithAngleModifiers(headInfo, netHeadYaw, headPitch, 0.9F * idleDampener, 0.9F * idleDampener);

		float verticalVelocityChangeAngle = (float)Math.toRadians(-7);
		float horzontalVelocityChangeAngle = (float)Math.toRadians(-9);

		float newVerticalVelocity = 0;

		if(verticalVelocity > 0F)
			newVerticalVelocity = verticalVelocity;
		else if(verticalVelocity < 0F)
			newVerticalVelocity = verticalVelocity * 0.5F;

		float newHorizontalVelocity = 1.5F * horzVelocity;

		if(newHorizontalVelocity > 1F)
			newHorizontalVelocity = 1F;

		float horizontalAngleCounter = 0F;
		float horizontalTotalAngleLimit = (float)Math.toRadians(-90);

		float totalVelocity = Math.abs(verticalVelocity) + horzVelocity;
		float moveAmplitudeX = 0.07F;

		/* ******* Ears ******** */
		for(int i = 0; i < earRtInfo.length; i++)
		{
			IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockEars(i);

			//float moveFrequencyZ = 0.7F;
			//float moveAmplitudeZ = 0.10F;

			//float moveAngleZ = PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, moveFrequencyZ, moveAmplitudeZ);

			float idleAmplitudeX = 0.03F;
			float idleAmplitudeZ = 0.06F * (1F - Math.abs(verticalVelocity));

			float angleChangeX = 0;
			float angleChangeZ = 0;//moveAngleZ;

			angleChangeX += MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeX;
			angleChangeZ += MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredZ(0) * 2 * PI) * idleAmplitudeZ;

			float horizontalVelocityAngleChange = 0;

			if(horizontalAngleCounter > horizontalTotalAngleLimit)
			{
				horizontalVelocityAngleChange = horzontalVelocityChangeAngle * newHorizontalVelocity;

				angleChangeX += horizontalVelocityAngleChange;
				horizontalAngleCounter += horizontalVelocityAngleChange;
			}

			angleChangeX += PartAnimate.negCosRotateAnimationAdjusted(distanceMoved - (2F * PI * ((float)i / (float)(earRtInfo.length - 1))), totalVelocity, WALK_FREQUENCY, moveAmplitudeX) + totalVelocity * (float)Math.toRadians(5)
					* ((float)i / (float)(earRtInfo.length - 1));

			earRtInfo[i].setNewRotateX(earRtInfo[i].getNewRotateX() + (angleChangeX + verticalVelocityChangeAngle * (-headInfo.getNewRotateX() / (float)Math.toRadians(110)) * verticalVelocity) * idleDampener);
			earLftInfo[i].setNewRotateX(earLftInfo[i].getNewRotateX() + (angleChangeX + verticalVelocityChangeAngle * (-headInfo.getNewRotateX() / (float)Math.toRadians(110)) * verticalVelocity) * idleDampener);

			earRtInfo[i].setNewRotateZ(earRtInfo[i].getNewRotateZ() + (angleChangeZ + newVerticalVelocity * verticalVelocityChangeAngle) * idleDampener);
			earLftInfo[i].setNewRotateZ(earLftInfo[i].getNewRotateZ() + (angleChangeZ - newVerticalVelocity * verticalVelocityChangeAngle) * idleDampener);
		}

		horizontalAngleCounter = 0F;

		/* ******* Antenna ******** */
		for(int i = 0; i < antennaRtInfo.length; i++)
		{
			IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockEars(i);

			//float moveFrequencyZ = 0.7F;
			//float moveAmplitudeZ = 0.10F;

			//float moveAngleZ = PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, moveFrequencyZ, moveAmplitudeZ);

			float idleAmplitudeX = 0.03F;
			float idleAmplitudeZ = 0.06F * (1F - Math.abs(verticalVelocity));

			// + (float)Math.toRadians(45)
			float angleChangeX = 0;
			float angleChangeZ = 0;//moveAngleZ;

			angleChangeX += MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeX;
			angleChangeZ += MathHelper.sin(currentIdleAnimationClock.getPhaseDurationCoveredZ(0) * 2 * PI) * idleAmplitudeZ;

			float horizontalVelocityAngleChange = 0;

			if(horizontalAngleCounter > horizontalTotalAngleLimit)
			{
				horizontalVelocityAngleChange = horzontalVelocityChangeAngle * newHorizontalVelocity;

				angleChangeX += horizontalVelocityAngleChange;
				horizontalAngleCounter += horizontalVelocityAngleChange;
			}

			angleChangeX += PartAnimate.negCosRotateAnimationAdjusted(distanceMoved - (2F * PI * ((float)i / (float)(antennaRtInfo.length - 1))), totalVelocity, WALK_FREQUENCY, moveAmplitudeX) + totalVelocity * (float)Math.toRadians(5)
					* ((float)i / (float)(antennaRtInfo.length - 1));

			antennaRtInfo[i].setNewRotateX(antennaRtInfo[i].getNewRotateX() + angleChangeX * idleDampener);
			antennaLftInfo[i].setNewRotateX(antennaLftInfo[i].getNewRotateX() + angleChangeX * idleDampener);

			antennaRtInfo[i].setNewRotateZ(antennaRtInfo[i].getNewRotateZ() + (angleChangeZ + verticalVelocity * verticalVelocityChangeAngle) * idleDampener);
			antennaLftInfo[i].setNewRotateZ(antennaLftInfo[i].getNewRotateZ() + (angleChangeZ - verticalVelocity * verticalVelocityChangeAngle) * idleDampener);
		}
	}

	public void animateArms(MuBunearyEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float idleDampener, float verticalVelocity, float velocityPercentChangeApplied)
	{
		float idleAmplitudeX = 0.15F * (1F - velocityPercentChangeApplied);
		float idleAmplitudeY = 0.10F * (1F - velocityPercentChangeApplied);

		float moveAmplitude = 0.8F;

		// Run constants
		float legFntRtModifierRun = 2F * PI * (1F/4F);

		// Walk constants
		float legFntRtModifierWalk = 2F * PI * (1F/2F);

		float legFntRtModifierDifference = legFntRtModifierRun - legFntRtModifierWalk;

		float legFntRtModifier;

		float sprint = 1.0F;
		float sneak = 0.35F;

		if(horzVelocity > sneak)
		{
			float modifier = (float)(1F - (Math.pow(Math.E, -5F * ((horzVelocity - sneak)/(sprint - sneak)))));
			legFntRtModifier = legFntRtModifierWalk + legFntRtModifierDifference * modifier;
		}
		else
		{
			legFntRtModifier = legFntRtModifierWalk;
		}

		float angleChangeX = 0; 
		float angleChangeY = 0;

		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockArms(0);

		angleChangeX = -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeX;
		angleChangeY = -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * 2 * PI) * idleAmplitudeY;

		armLftInfo.setNewRotateX((armLftInfo.getNewRotateX() + angleChangeX) * (1F - velocityPercentChangeApplied * idleDampener)
				+ PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, WALK_FREQUENCY, moveAmplitude) * velocityPercentChangeApplied * (1F - Math.abs(verticalVelocity)) * idleDampener);
		armLftInfo.setNewRotateY((armLftInfo.getNewRotateY() + angleChangeY) * (1F - velocityPercentChangeApplied * idleDampener));

		currentIdleAnimationClock = entity.getIdleAnimationClockArms(1);

		angleChangeX = -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeX;
		angleChangeY = -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * 2 * PI) * idleAmplitudeY;

		armRtInfo.setNewRotateX((armRtInfo.getNewRotateX() + angleChangeX) * (1F - velocityPercentChangeApplied * idleDampener)
				+ PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legFntRtModifier, horzVelocity, WALK_FREQUENCY, moveAmplitude) * velocityPercentChangeApplied * (1F - Math.abs(verticalVelocity)));
		armRtInfo.setNewRotateY((armRtInfo.getNewRotateY() + angleChangeY) * (1F - velocityPercentChangeApplied * idleDampener));
	}

	public void animateLegs(MuBunearyEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float idleDampener, float verticalVelocity, float velocityPercentChangeApplied)
	{
		float moveAmplitude = 0.8F;

		// Run constants
		float legBckRtModifierRun = 2F * PI * (5F/16F);
		float legBckLftModifierRun = 2F * PI * (8F/16F);

		// Walk constants
		float legBckRtModifierWalk = 2F * PI * (3F/4F);
		float legBckLftModifierWalk = 2F * PI * (1F/4F);

		float legBckRtModifierDifference = legBckRtModifierRun - legBckRtModifierWalk;
		float legBckLftModifierDifference = legBckLftModifierRun - legBckLftModifierWalk;

		float legBckRtModifier;
		float legBckLftModifier;

		float sprint = 1.0F;
		float sneak = 0.35F;

		if(horzVelocity > sneak)
		{
			float modifier = (float)(1F - (Math.pow(Math.E, -5F * ((horzVelocity - sneak)/(sprint - sneak)))));
			legBckRtModifier = legBckRtModifierWalk + legBckRtModifierDifference * modifier;
			legBckLftModifier = legBckLftModifierWalk + legBckLftModifierDifference * modifier;
		}
		else
		{
			legBckRtModifier = legBckRtModifierWalk;
			legBckLftModifier = legBckLftModifierWalk;	
		}

		legRtInfo.setNewRotateX(PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legBckRtModifier, horzVelocity, WALK_FREQUENCY, moveAmplitude));
		legLftInfo.setNewRotateX(PartAnimate.posCosRotateAnimationAdjusted(distanceMoved + legBckLftModifier, horzVelocity, WALK_FREQUENCY, moveAmplitude));

		JointAnimation.reverseJointRotatesChangeXFloatAdjustment(bodyJointInfo, legRtInfo, NEW_BODYJOINT_RUN_ANGLE_X * velocityPercentChangeApplied);
		JointAnimation.reverseJointRotatesChangeXFloatAdjustment(bodyJointInfo, legLftInfo, NEW_BODYJOINT_RUN_ANGLE_X * velocityPercentChangeApplied);

		float runPointChangeZ = -0.8F;

		legRtInfo.setNewPointZ(legRtInfo.getNewPointZ() + runPointChangeZ * velocityPercentChangeApplied);
		legLftInfo.setNewPointZ(legLftInfo.getNewPointZ() + runPointChangeZ * velocityPercentChangeApplied);
	}

	public void animateTail(MuBunearyEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float idleDampener, float verticalVelocity, float velocityPercentChangeApplied)
	{
		float tailRunAdjustmentX = (float)Math.toRadians(-110) * velocityPercentChangeApplied;

		tailInfo[0].setNewRotateX(tailInfo[0].getNewRotateX() + tailRunAdjustmentX);

		for(int i = 0; i < tail.length; i++)
		{
			IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockTail(i);

			float idleAmplitudeX = 0.22F;
			float idleAmplitudeY = 0.23F;

			float moveAmplitude = 0.1F;

			// + (float)Math.toRadians(45)
			float angleChangeX = 0;
			float angleChangeY = 0;

			angleChangeX += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * PI * 2) * idleAmplitudeX
					+ PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, WALK_FREQUENCY, moveAmplitude);
			angleChangeY += MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * PI * 2) * idleAmplitudeY;
			//PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, moveFrequency, moveAmplitude);

			tailInfo[i].setNewRotateX(tailInfo[i].getNewRotateX() + angleChangeX);
			tailInfo[i].setNewRotateY(tailInfo[i].getNewRotateY() + angleChangeY);
		}
	}

	public void deployAnimations()
	{
		this.animationDeployer.move(bodyJoint, bodyJointInfo.getNewPnt());
		this.animationDeployer.rotate(bodyJoint, bodyJointInfo.getNewRotates());
		this.animationDeployer.rotate(body, bodyInfo.getNewRotates());

		this.animationDeployer.rotate(neck, neckInfo.getNewRotates());

		this.animationDeployer.rotate(armLft, armLftInfo.getNewRotates());
		this.animationDeployer.rotate(armRt, armRtInfo.getNewRotates());

		for(int i = 0; i < earLft.length; i++)
		{
			this.animationDeployer.rotate(earLft[i], earLftInfo[i].getNewRotates());
			this.animationDeployer.rotate(earRt[i], earRtInfo[i].getNewRotates());
		}

		for(int i = 0; i < antennaLft.length; i++)
		{
			this.animationDeployer.rotate(antennaLft[i], antennaLftInfo[i].getNewRotates());
			this.animationDeployer.rotate(antennaRt[i], antennaRtInfo[i].getNewRotates());
		}

		this.animationDeployer.rotate(headJoint, headJointInfo.getNewRotates());
		this.animationDeployer.rotate(head, headInfo.getNewRotates());

		this.animationDeployer.rotate(legRt, legRtInfo.getNewRotates());
		this.animationDeployer.rotate(legLft, legLftInfo.getNewRotates());
		this.animationDeployer.move(legRt, legRtInfo.getNewPnt());
		this.animationDeployer.move(legLft, legLftInfo.getNewPnt());

		for(int i = 0; i < tail.length; i++)
		{
			this.animationDeployer.rotate(tail[i], tailInfo[i].getNewRotates());
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
		return ImmutableList.of(this.bodyJoint);
	}
}