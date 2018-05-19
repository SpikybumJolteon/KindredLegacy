package fuzzyacornindustries.kindredlegacy.client.model.ability;

import fuzzyacornindustries.kindredlegacy.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.entity.ability.IAnimateFoxfire;
import fuzzyacornindustries.kindredlegacy.entity.mob.IMobMotionTracker;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFoxfire extends ModelBase 
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public ModelRenderer foxfireOrbJoint;
	public ModelRenderer foxfireOrb[] = new ModelRenderer[2];
	public PartInfo foxfireOrbJointInfo;
	public PartInfo foxfireOrbInfo[] = new PartInfo[foxfireOrb.length];

    public ModelFoxfire() 
    {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.foxfireOrbJoint = new ModelRenderer(this, 0, 0);
        this.foxfireOrbJoint.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.foxfireOrbJoint.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        foxfireOrbJointInfo = new PartInfo(foxfireOrbJoint);
        this.foxfireOrb[0] = new ModelRenderer(this, 0, 0); // foxfireOrb1
        this.foxfireOrb[0].setRotationPoint(0.0F, 0.0F, 0.0F);
        this.foxfireOrb[0].addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F);
        foxfireOrbInfo[0] = new PartInfo(foxfireOrb[0]);
        this.foxfireOrb[1] = new ModelRenderer(this, 0, 13); // foxfireOrb2
        this.foxfireOrb[1].setRotationPoint(0.0F, 0.0F, 0.0F);
        this.foxfireOrb[1].addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F);
        this.setRotateAngle(foxfireOrb[1], -0.7853981633974483F, -0.7853981633974483F, -0.7853981633974483F);
        foxfireOrbInfo[1] = new PartInfo(foxfireOrb[1]);
        
        this.foxfireOrbJoint.addChild(this.foxfireOrb[0]);
        this.foxfireOrbJoint.addChild(this.foxfireOrb[1]);

		animationDeployer = new AnimationDeployer(this);
    }

    @Override
	public void render(Entity entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize) 
	{
		animate(entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);

        this.foxfireOrbJoint.render(modelSize);
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

		float verticalVelocity = (float)((IMobMotionTracker)entity).getHeightVelocity();
		animateFoxfire((IAnimateFoxfire)entity, distanceMoved, horzVelocity, yawRotationDifference, yawHeadOffsetDifference, pitchRotation, modelSize);

		deployAnimations();
	}

	public void resetPartInfos()
	{
		for(int i = 0; i < foxfireOrbInfo.length; i++)
		{
			foxfireOrbInfo[i].resetNewAngles();
		}

		foxfireOrbJointInfo.resetNewPnt();
	}

	public void animateFoxfire(IAnimateFoxfire entity, float distanceMoved, float horzVelocity, 
			float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize)
	{
		IdleAnimationClock currentIdleAnimationClock = entity.getIdleAnimationClockSpin();

		float idleAmplitudeZ = 2.0F;

		for(int i = 0; i < foxfireOrbInfo.length; i++)
		{
			float frequency = 2.0F;
			float angleChange = -MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * 2 * PI * frequency) * 2 * PI;

			foxfireOrbInfo[i].setNewRotateX(angleChange * (1 - (2 * i)));
			foxfireOrbInfo[i].setNewRotateY(angleChange * (1 - (2 * i)));
		}

		float pntChangeZ = (-MathHelper.cos(currentIdleAnimationClock.getPhaseDurationCoveredY(0) * 2 * PI) * idleAmplitudeZ) - idleAmplitudeZ;

		foxfireOrbJointInfo.setNewPointZ(foxfireOrbJointInfo.getNewPointZ() + pntChangeZ);
	}
    
	public void deployAnimations()
	{
		for(int i = 0; i < foxfireOrbInfo.length; i++)
		{
			this.animationDeployer.rotate(foxfireOrb[i], foxfireOrbInfo[i].getNewRotates());
		}

		this.animationDeployer.move(foxfireOrbJoint, foxfireOrbJointInfo.getNewPnt());

		this.animationDeployer.applyChanges();
	}
}