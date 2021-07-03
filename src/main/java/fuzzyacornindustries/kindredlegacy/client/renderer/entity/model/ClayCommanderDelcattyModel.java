package fuzzyacornindustries.kindredlegacy.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;

import fuzzyacornindustries.kindredlegacy.client.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.client.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.client.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.animation.JointAnimation;
import fuzzyacornindustries.kindredlegacy.client.animation.PartAnimate;
import fuzzyacornindustries.kindredlegacy.client.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.IMobMotionTracker;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.ClayCommanderDelcattyEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.HostilePokemon;
import net.minecraft.client.renderer.entity.model.TintedAgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ClayCommanderDelcattyModel<T extends HostilePokemon> extends TintedAgeableModel<T>
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public final float RUN_FREQUENCY = 0.8F;
	public final float WALK_FREQUENCY = 1.1F;
	public final float SNEAK_VELOCITY = 0.35F;

	public ModelRenderer body;
	PartInfo bodyInfo;

	public ModelRenderer neckJoint;
	public ModelRenderer neck;
	PartInfo neckJointInfo;
	PartInfo neckInfo;

	public ModelRenderer legFntRt;
	public ModelRenderer legFntLft;
	public ModelRenderer legBckLft;
	public ModelRenderer legBckRt;
	PartInfo legFntRtInfo;
	PartInfo legFntLftInfo;
	PartInfo legBckLftInfo;
	PartInfo legBckRtInfo;

	public ModelRenderer armorFntFlpLft;
	public ModelRenderer armorFntFlpRt;
	public ModelRenderer armorBckFlpLft;
	public ModelRenderer armorBckFlpRt;
	PartInfo armorFntFlpLftInfo;
	PartInfo armorFntFlpRtInfo;
	PartInfo armorBckFlpLftInfo;
	PartInfo armorBckFlpRtInfo;

	public ModelRenderer collarBckJoint;
	public ModelRenderer collarBck;
	PartInfo collarBckJointInfo;
	PartInfo collarBckInfo;

	public ModelRenderer collarLftJoint;
	public ModelRenderer collarLft;
	PartInfo collarLftJointInfo;
	PartInfo collarLftInfo;

	public ModelRenderer collarRtJoint;
	public ModelRenderer collarRt;
	PartInfo collarRtJointInfo;
	PartInfo collarRtInfo;

	public ModelRenderer headJoint;
	public ModelRenderer head;
	public ModelRenderer headArmorJoint;
	public ModelRenderer headArmor;
	public ModelRenderer headArmorInsig;
	PartInfo headJointInfo;
	PartInfo headInfo;
	PartInfo headArmorJointInfo;
	PartInfo headArmorInfo;

	public ModelRenderer muzzle;

	public ModelRenderer earRtJoint;
	public ModelRenderer earRt;
	public ModelRenderer earLftJoint;
	public ModelRenderer earLft;
	PartInfo earRtJointInfo;
	PartInfo earRtInfo;
	PartInfo earLftJointInfo;
	PartInfo earLftInfo;

	public ModelRenderer tail[][] = new ModelRenderer[8][2];
	PartInfo tailInfo[][] = new PartInfo[tail.length][tail[0].length];

	public ClayCommanderDelcattyModel() 
	{
		this.textureWidth = 64;
		this.textureHeight = 64;

		int jointNumber = 0;
		int partNumber = 1;

		this.body = new ModelRenderer(this, 31, 36);
		this.body.setRotationPoint(0.0F, 15.5F, 0.0F);
		this.body.addBox(-2.5F, -2.5F, -1.0F, 5, 5, 2, 0.0F);
		this.body.setTextureOffset(13, 39).addBox(-3.0F, -2.8F, -5.6F, 6, 6, 5, 0.0F); // armorFnt
		this.body.setTextureOffset(42, 39).addBox(-3.0F, -2.8F, 0.6F, 6, 6, 5, 0.0F); // armorBck
		bodyInfo = new PartInfo(body);

		this.neckJoint = new ModelRenderer(this, 9, 37);
		this.neckJoint.setRotationPoint(0.0F, -1.5F, -4.0F);
		this.neckJoint.addBox(-1.0F, -3.5F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(neckJoint, 0.4363323129985824F, -0.0F, 0.0F);
		neckJointInfo = new PartInfo(neckJoint);
		this.neck = new ModelRenderer(this, 9, 37);
		this.neck.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.neck.addBox(-1.0F, -3.5F, -1.0F, 2, 4, 2, 0.0F);
		neckInfo = new PartInfo(neck);

		this.legFntRt = new ModelRenderer(this, 47, 24);
		this.legFntRt.setRotationPoint(-1.4F, 2.0F, -3.5F);
		this.legFntRt.addBox(-1.0F, -0.5F, -1.0F, 2, 7, 2, 0.0F);
		legFntRtInfo = new PartInfo(legFntRt);
		this.legFntLft = new ModelRenderer(this, 56, 24);
		this.legFntLft.setRotationPoint(1.4F, 2.0F, -3.5F);
		this.legFntLft.addBox(-1.0F, -0.5F, -1.0F, 2, 7, 2, 0.0F);
		legFntLftInfo = new PartInfo(legFntLft);
		this.legBckLft = new ModelRenderer(this, 38, 22);
		this.legBckLft.setRotationPoint(1.4F, 2.0F, 3.5F);
		this.legBckLft.addBox(-1.0F, -0.5F, -1.0F, 2, 4, 2, 0.0F);
		this.setRotateAngle(legBckLft, -0.17453292519943295F, -0.0F, 0.0F);
		this.legBckLft.setTextureOffset(38, 29).addBox(-1.1F, 2.5F, 0.0F, 2, 4, 2, 0.0F); // legBckLftHind
		legBckLftInfo = new PartInfo(legBckLft);
		this.legBckRt = new ModelRenderer(this, 29, 21);
		this.legBckRt.setRotationPoint(-1.4F, 2.0F, 3.5F);
		this.legBckRt.addBox(-1.0F, -0.5F, -1.0F, 2, 4, 2, 0.0F);
		this.setRotateAngle(legBckRt, -0.17453292519943295F, -0.0F, 0.0F);
		this.legBckRt.setTextureOffset(29, 28).addBox(-0.9F, 2.5F, 0.0F, 2, 4, 2, 0.0F); // legBckRtHind
		legBckRtInfo = new PartInfo(legBckRt);

		this.armorFntFlpLft = new ModelRenderer(this, 25, 51);
		this.armorFntFlpLft.setRotationPoint(3.0F, 2.5F, -3.0F);
		this.armorFntFlpLft.addBox(-0.5F, -0.5F, -2.0F, 1, 2, 4, 0.0F);
		this.armorFntFlpLft.setTextureOffset(26, 57).addBox(-0.5F, 1.5F, -1.5F, 1, 1, 3, 0.0F); // armorFntFlpLftMid
		this.armorFntFlpLft.setTextureOffset(27, 61).addBox(-0.5F, 2.5F, -1.0F, 1, 1, 2, 0.0F); // armorFntFlpLftEnd
		armorFntFlpLftInfo = new PartInfo(armorFntFlpLft);
		this.armorFntFlpRt = new ModelRenderer(this, 13, 51);
		this.armorFntFlpRt.setRotationPoint(-3.0F, 2.5F, -3.0F);
		this.armorFntFlpRt.addBox(-0.5F, -0.5F, -2.0F, 1, 2, 4, 0.0F);
		this.armorFntFlpRt.setTextureOffset(14, 57).addBox(-0.5F, 1.5F, -1.5F, 1, 1, 3, 0.0F); // armorFntFlpRtMid
		this.armorFntFlpRt.setTextureOffset(15, 61).addBox(-0.5F, 2.5F, -1.0F, 1, 1, 2, 0.0F); // armorFntFlpRtEnd
		armorFntFlpRtInfo = new PartInfo(armorFntFlpRt);
		this.armorBckFlpLft = new ModelRenderer(this, 54, 51);
		this.armorBckFlpLft.setRotationPoint(3.0F, 2.5F, 3.0F);
		this.armorBckFlpLft.addBox(-0.5F, -0.5F, -2.0F, 1, 2, 4, 0.0F);
		this.armorBckFlpLft.setTextureOffset(55, 57).addBox(-0.5F, 1.5F, -1.5F, 1, 1, 3, 0.0F); // armorBckFlpLftMid
		this.armorBckFlpLft.setTextureOffset(56, 61).addBox(-0.5F, 2.5F, -1.0F, 1, 1, 2, 0.0F); // armorBckFlpLftEnd
		armorBckFlpLftInfo = new PartInfo(armorBckFlpLft);
		this.armorBckFlpRt = new ModelRenderer(this, 42, 51);
		this.armorBckFlpRt.setRotationPoint(-3.0F, 2.5F, 3.0F);
		this.armorBckFlpRt.addBox(-0.5F, -0.5F, -2.0F, 1, 2, 4, 0.0F);
		this.armorBckFlpRt.setTextureOffset(43, 57).addBox(-0.5F, 1.5F, -1.5F, 1, 1, 3, 0.0F); // armorBckFlpRtMid
		this.armorBckFlpRt.setTextureOffset(44, 61).addBox(-0.5F, 2.5F, -1.0F, 1, 1, 2, 0.0F); // armorBckFlpRtEnd
		armorBckFlpRtInfo = new PartInfo(armorBckFlpRt);

		this.collarBckJoint = new ModelRenderer(this, 46, 16);
		this.collarBckJoint.setRotationPoint(0.0F, -1.0F, 0.5F);
		this.collarBckJoint.addBox(-3.1F, -1.5F, 0.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(collarBckJoint, 0.2617993877991494F, 0.0F, 0.0F);
		collarBckJointInfo = new PartInfo(collarBckJoint);
		this.collarBck = new ModelRenderer(this, 46, 16);
		this.collarBck.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.collarBck.addBox(-3.1F, -1.5F, 0.0F, 6, 3, 3, 0.0F);
		collarBckInfo = new PartInfo(collarBck);

		this.collarLftJoint = new ModelRenderer(this, 48, 0);
		this.collarLftJoint.setRotationPoint(1.3F, -1.4F, 0.0F);
		this.collarLftJoint.addBox(0.0F, -1.5F, -2.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(collarLftJoint, 0.2617993877991494F, 0.17453292519943295F, 0.0F);
		collarLftJointInfo = new PartInfo(collarLftJoint);
		this.collarLft = new ModelRenderer(this, 48, 0);
		this.collarLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.collarLft.addBox(0.0F, -1.5F, -2.5F, 3, 3, 5, 0.0F);
		this.collarLft.setTextureOffset(51, 9).addBox(3.0F, -0.5F, -1.5F, 1, 1, 1, 0.0F); // collarLftFntStem
		this.collarLft.setTextureOffset(50, 11).addBox(3.5F, -1.0F, -2.0F, 1, 2, 2, 0.0F); // collarLftFntPom
		this.collarLft.setTextureOffset(59, 9).addBox(3.0F, -0.5F, 1.0F, 1, 1, 1, 0.0F); // collarLftBckStem
		this.collarLft.setTextureOffset(58, 11).addBox(3.5F, -1.0F, 0.5F, 1, 2, 2, 0.0F); // collarLftBckPom
		collarLftInfo = new PartInfo(collarLft);

		this.collarRtJoint = new ModelRenderer(this, 31, 0);
		this.collarRtJoint.setRotationPoint(-1.3F, -1.4F, 0.0F);
		this.collarRtJoint.addBox(-3.0F, -1.5F, -2.5F, 0, 0, 0, 0.0F);
		this.setRotateAngle(collarRtJoint, 0.2617993877991494F, -0.17453292519943295F, 0.0F);
		collarRtJointInfo = new PartInfo(collarRtJoint);
		this.collarRt = new ModelRenderer(this, 31, 0);
		this.collarRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.collarRt.addBox(-3.0F, -1.5F, -2.5F, 3, 3, 5, 0.0F);
		this.collarRt.setTextureOffset(32, 9).addBox(-4.0F, -0.5F, -1.5F, 1, 1, 1, 0.0F); // collarRtFntStem
		this.collarRt.setTextureOffset(31, 11).addBox(-4.5F, -1.0F, -2.0F, 1, 2, 2, 0.0F); // collarRtFntPom
		this.collarRt.setTextureOffset(41, 9).addBox(-4.0F, -0.5F, 1.0F, 1, 1, 1, 0.0F); // collarRtBckStem
		this.collarRt.setTextureOffset(40, 11).addBox(-4.5F, -1.0F, 0.5F, 1, 2, 2, 0.0F); // collarRtBckPom
		collarRtInfo = new PartInfo(collarRt);

		this.headJoint = new ModelRenderer(this, 0, 0);
		this.headJoint.setRotationPoint(0.0F, -3.7F, 0.0F);
		this.headJoint.addBox(-2.5F, -3.5F, -3.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(headJoint, -0.4363323129985824F, -0.0F, 0.0F);
		headJointInfo = new PartInfo(headJoint);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.head.addBox(-2.5F, -3.5F, -3.0F, 5, 5, 5, 0.0F);
		headInfo = new PartInfo(head);
		this.headArmorJoint = new ModelRenderer(this, 8, 29);
		this.headArmorJoint.setRotationPoint(0.0F, -3.5F, -0.5F);
		this.headArmorJoint.addBox(-3.0F, -1.0F, -3.0F, 0, 0, 0, 0.0F);
		headArmorJointInfo = new PartInfo(headArmorJoint);
		this.headArmor = new ModelRenderer(this, 8, 29);
		this.headArmor.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.headArmor.addBox(-3.0F, -1.0F, -3.0F, 6, 2, 6, 0.0F);
		headArmorInfo = new PartInfo(headArmor);
		this.headArmorInsig = new ModelRenderer(this, 0, 29);
		this.headArmorInsig.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.headArmorInsig.addBox(-2.5F, -6.5F, -1.5F, 5, 5, 1, 0.0F);
		this.setRotateAngle(headArmorInsig, -0.2617993877991494F, -0.0F, 0.0F);
		this.headArmorInsig.setTextureOffset(2, 38).addBox(-0.5F, -1.5F, -1.5F, 1, 1, 1, 0.0F); // headArmorInsigStem

		this.muzzle = new ModelRenderer(this, 17, 0);
		this.muzzle.setRotationPoint(0.0F, 0.3F, -2.7F);
		this.muzzle.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 1, 0.0F);
		this.setRotateAngle(muzzle, 0.17453292519943295F, -0.0F, 0.0F);

		this.earRtJoint = new ModelRenderer(this, 18, 11);
		this.earRtJoint.setRotationPoint(-2.0F, -2.5F, 0.0F);
		this.earRtJoint.addBox(-2.5F, -1.8F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earRtJoint, 0.0F, -0.0F, 0.5235987755982988F);
		earRtJointInfo = new PartInfo(earRtJoint);
		this.earRt = new ModelRenderer(this, 18, 11);
		this.earRt.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earRt.addBox(-2.5F, -1.8F, -1.0F, 3, 4, 2, 0.0F);
		this.earRt.setTextureOffset(10, 11).addBox(-4.5F, -1.5F, -1.0F, 2, 3, 2, 0.0F); // earRtMid
		this.earRt.setTextureOffset(4, 11).addBox(-5.5F, -1.2F, -1.0F, 1, 2, 2, 0.0F); // earRtEnd
		this.earRt.setTextureOffset(0, 11).addBox(-6.0F, -1.8F, -0.5F, 1, 1, 1, 0.0F); // earRtTipTop
		this.earRt.setTextureOffset(0, 13).addBox(-6.5F, -0.6F, -0.5F, 1, 1, 1, 0.0F); // earRtTipMid
		this.earRt.setTextureOffset(0, 15).addBox(-6.0F, 0.6F, -0.5F, 1, 1, 1, 0.0F); // earRtTipBot
		earRtInfo = new PartInfo(earRt);
		this.earLftJoint = new ModelRenderer(this, 0, 18);
		this.earLftJoint.setRotationPoint(2.0F, -2.5F, 0.0F);
		this.earLftJoint.addBox(-0.5F, -1.8F, -1.0F, 0, 0, 0, 0.0F);
		this.setRotateAngle(earLftJoint, 0.0F, -0.0F, -0.5235987755982988F);
		earLftJointInfo = new PartInfo(earLftJoint);
		this.earLft = new ModelRenderer(this, 0, 18);
		this.earLft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.earLft.addBox(-0.5F, -1.8F, -1.0F, 3, 4, 2, 0.0F);
		this.earLft.setTextureOffset(10, 18).addBox(2.5F, -1.5F, -1.0F, 2, 3, 2, 0.0F); // earLftMid
		this.earLft.setTextureOffset(18, 18).addBox(4.5F, -1.2F, -1.0F, 1, 2, 2, 0.0F); // earLftEnd
		this.earLft.setTextureOffset(24, 18).addBox(5.0F, -1.8F, -0.5F, 1, 1, 1, 0.0F); // earLftTipTop
		this.earLft.setTextureOffset(24, 20).addBox(5.5F, -0.7F, -0.5F, 1, 1, 1, 0.0F); // earLftTipMid
		this.earLft.setTextureOffset(24, 22).addBox(5.0F, 0.6F, -0.5F, 1, 1, 1, 0.0F); // earLftTipBot
		earLftInfo = new PartInfo(earLft);

		int tailNumber = 0;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 61); // tail1Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, -1.5F, 5.0F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, 0.0F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 0, 61); // tail1
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 58); // tail2Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.8F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 0, 58); // tail2
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 55); // tail3Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.8F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 0, 55); // tail3
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 52); // tail4Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.8F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 0, 52); // tail4
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 49); // tail5Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.8F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 0, 49); // tail5
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 46); // tail6Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.8F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 0, 46); // tail6
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 0, 43); // tail7Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.8F);
		this.tail[tailNumber][jointNumber].addBox(-0.5F, -0.5F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 0, 43); // tail7
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);
		tailNumber++;
		this.tail[tailNumber][jointNumber] = new ModelRenderer(this, 6, 60); // tail8Joint
		this.tail[tailNumber][jointNumber].setRotationPoint(0.0F, 0.0F, 1.8F);
		this.tail[tailNumber][jointNumber].addBox(-1.0F, -1.0F, -0.2F, 0, 0, 0, 0.0F);
		tailInfo[tailNumber][jointNumber] = new PartInfo(tail[tailNumber][jointNumber]);
		this.tail[tailNumber][partNumber] = new ModelRenderer(this, 6, 60); // tail8
		this.tail[tailNumber][partNumber].setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tail[tailNumber][partNumber].addBox(-1.0F, -1.0F, -0.2F, 2, 2, 2, 0.0F);
		this.tail[tailNumber][partNumber].setTextureOffset(7, 58).addBox(-1.8F, -0.2F, 1.5F, 1, 1, 1, 0.0F); // tailEndTipRt
		this.tail[tailNumber][partNumber].setTextureOffset(7, 56).addBox(-0.5F, -0.8F, 2.0F, 1, 1, 1, 0.0F); // tailEndTipMid
		this.tail[tailNumber][partNumber].setTextureOffset(7, 54).addBox(0.8F, -0.2F, 1.5F, 1, 1, 1, 0.0F); // tailEndTipLft
		tailInfo[tailNumber][partNumber] = new PartInfo(tail[tailNumber][partNumber]);

		this.body.addChild(this.armorBckFlpLft);
		this.body.addChild(this.armorBckFlpRt);
		this.body.addChild(this.armorFntFlpLft);
		this.body.addChild(this.armorFntFlpRt);

		this.body.addChild(this.legFntLft);
		this.body.addChild(this.legFntRt);
		this.body.addChild(this.legBckLft);
		this.body.addChild(this.legBckRt);
		this.body.addChild(this.neckJoint);
		this.body.addChild(this.tail[0][0]);

		this.neckJoint.addChild(this.neck);
		this.neck.addChild(this.collarBckJoint);
		this.neck.addChild(this.collarLftJoint);
		this.neck.addChild(this.collarRtJoint);
		this.neck.addChild(this.headJoint);

		this.collarBckJoint.addChild(this.collarBck);
		this.collarLftJoint.addChild(this.collarLft);
		this.collarRtJoint.addChild(this.collarRt);

		this.headJoint.addChild(this.head);

		this.head.addChild(this.muzzle);
		this.head.addChild(this.earLftJoint);
		this.head.addChild(this.earRtJoint);
		this.head.addChild(this.headArmorJoint);

		this.earLftJoint.addChild(this.earLft);
		this.earRtJoint.addChild(this.earRt);

		this.headArmorJoint.addChild(this.headArmor);
		this.headArmorJoint.addChild(this.headArmorInsig);

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

		float angularVelocity = ((IMobMotionTracker)entity).getAngularVelocity();
		float verticalVelocity = (float)((IMobMotionTracker)entity).getHeightVelocity();

		float sizeModifier = 1F;//(float) Math.sqrt(((EntityOkamiEspeon) entity).defaultHeight / entity.height);
		
		animateBody((ClayCommanderDelcattyEntity)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, verticalVelocity);
		animateHead((ClayCommanderDelcattyEntity)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, verticalVelocity);
		animateLegs((ClayCommanderDelcattyEntity)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, verticalVelocity);
		animateTail((ClayCommanderDelcattyEntity)entity, distanceMoved, horzVelocity, ageInTicks, netHeadYaw, headPitch, sizeModifier, verticalVelocity, angularVelocity);

		deployAnimations();
	}

	public void resetPartInfos()
	{
		bodyInfo.resetNewAngles();

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

		collarBckInfo.resetNewAngles();
		collarLftInfo.resetNewAngles();
		collarRtInfo.resetNewAngles();

		for(int i = 0; i < tail.length; i++)
		{
			tailInfo[i][0].resetNewAngles();
			tailInfo[i][1].resetNewAngles();
		}
	}

	public void animateBody(ClayCommanderDelcattyEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float verticalVelocity)
	{
		float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
		float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);

		float bodyWalkAngle = (float)Math.toRadians(8);
		float bodyRunAngle = (float)Math.toRadians(12);

		float walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI + PI/2) * bodyWalkAngle * (1 - horzVelocity);
		float runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI + PI/2) * bodyRunAngle * horzVelocity;

		float bodyJumpAngle = (float)Math.toRadians(-65);

		float newVerticalVelocity = 1.5F * verticalVelocity;

		if(newVerticalVelocity > 1F)
			newVerticalVelocity = 1F;
		else if(newVerticalVelocity < -1F)
			newVerticalVelocity = -1F;

		bodyInfo.setNewRotateX(bodyInfo.getNewRotateX() - (walkCycleAngleChange + runCycleAngleChange) * horzVelocity * (1F - Math.abs(newVerticalVelocity)) 
				+ bodyJumpAngle * newVerticalVelocity);
	}

	public void animateHead(ClayCommanderDelcattyEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float verticalVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, neckJointInfo);

		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockNeckBobble();

		float idleAmplitudeX = (float)Math.toRadians(15);

		float walkCycleInterval = (WALK_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);
		float runCycleInterval = (RUN_FREQUENCY * distanceMoved % (2 * PI))/(2 * PI);

		float walkAngle = (float)Math.toRadians(20);
		float runAngle = (float)Math.toRadians(28);

		float walkCycleAngleChange = MathHelper.cos(walkCycleInterval * 2 * PI) * walkAngle * (1 - horzVelocity)
				+ walkAngle * (1 - horzVelocity);
		float runCycleAngleChange = MathHelper.cos(runCycleInterval * 2 * PI) * runAngle * horzVelocity
				+ runAngle * horzVelocity;

		float idleAngleChangeX = -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredX(0) * 2 * PI) * idleAmplitudeX + idleAmplitudeX;

		neckInfo.setNewRotateX(neckInfo.getNewRotateX() + idleAngleChangeX + (walkCycleAngleChange + runCycleAngleChange) * horzVelocity);

		JointAnimation.reverseJointRotatesChange(neckInfo, headJointInfo);

		PartAnimate.headAnimateInfoOnly(headInfo, netHeadYaw, headPitch);

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

		earRtInfo.setNewRotateZ(earRtInfo.getNewRotateZ() - ((angleChangeX - moveAngleZ) * (1F - newVerticalVelocity) + fallingEarAngle * newVerticalVelocity));
		earLftInfo.setNewRotateZ(earLftInfo.getNewRotateZ() + ((angleChangeX - moveAngleZ) * (1F - newVerticalVelocity) + fallingEarAngle * newVerticalVelocity));

		/* ******* Collar ******** */
		float angleChangeZ = angleChangeX * 0.9F + moveAngleZ * 0.5F;

		collarBckInfo.setNewRotateX(collarBckInfo.getNewRotateX() + angleChangeZ);
		collarLftInfo.setNewRotateY(collarLftInfo.getNewRotateY() + angleChangeZ);
		collarLftInfo.setNewRotateZ(collarLftInfo.getNewRotateZ() + angleChangeZ);
		collarRtInfo.setNewRotateY(collarRtInfo.getNewRotateY() - angleChangeZ);
		collarRtInfo.setNewRotateZ(collarRtInfo.getNewRotateZ() - angleChangeZ);
	}

	public void animateLegs(ClayCommanderDelcattyEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float verticalVelocity)
	{
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

		float flapAmplitude = 0.4F;

		this.armorFntFlpLft.rotateAngleZ = PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, RUN_FREQUENCY, flapAmplitude);
		this.armorFntFlpRt.rotateAngleZ = PartAnimate.posCosRotateAnimationAdjusted(distanceMoved, horzVelocity, RUN_FREQUENCY, flapAmplitude);
		this.armorBckFlpLft.rotateAngleZ = PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, RUN_FREQUENCY, flapAmplitude);
		this.armorBckFlpRt.rotateAngleZ = PartAnimate.negCosRotateAnimationAdjusted(distanceMoved, horzVelocity, RUN_FREQUENCY, flapAmplitude);
	}

	public void animateTail(ClayCommanderDelcattyEntity entity, float distanceMoved, float horzVelocity, float ageInTicks, float netHeadYaw, float headPitch, float modelSize, float verticalVelocity, float angularVelocity)
	{
		JointAnimation.reverseJointRotatesChange(bodyInfo, tailInfo[0][0]);

		float yawChangeAngle = -(float)Math.toRadians(15);
		float verticalVelocityChangeAngle = (float)Math.toRadians(-9);

		float bodyJumpAngle = (float)Math.toRadians(-55);

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

		this.animationDeployer.rotate(legFntRt, legFntRtInfo.getNewRotates());
		this.animationDeployer.rotate(legFntLft, legFntLftInfo.getNewRotates());
		this.animationDeployer.rotate(legBckRt, legBckRtInfo.getNewRotates());
		this.animationDeployer.rotate(legBckLft, legBckLftInfo.getNewRotates());

		this.animationDeployer.rotate(earLft, earLftInfo.getNewRotates());
		this.animationDeployer.rotate(earLftJoint, earLftJointInfo.getNewRotates());
		this.animationDeployer.rotate(earRt, earRtInfo.getNewRotates());
		this.animationDeployer.rotate(earRtJoint, earRtJointInfo.getNewRotates());

		this.animationDeployer.rotate(collarBck, collarBckInfo.getNewRotates());
		this.animationDeployer.rotate(collarLft, collarLftInfo.getNewRotates());
		this.animationDeployer.rotate(collarRt, collarRtInfo.getNewRotates());

		for(int i = 0; i < tail.length; i++)
		{
			this.animationDeployer.rotate(tail[i][0], tailInfo[i][0].getNewRotates());
			this.animationDeployer.rotate(tail[i][1], tailInfo[i][1].getNewRotates());
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