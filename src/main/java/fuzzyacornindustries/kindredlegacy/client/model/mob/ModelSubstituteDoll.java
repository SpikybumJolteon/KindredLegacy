package fuzzyacornindustries.kindredlegacy.client.model.mob;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSubstituteDoll extends ModelBase 
{
    public ModelRenderer body;
    
    public ModelRenderer armRt;
    public ModelRenderer armLft;
    public ModelRenderer legRt;
    public ModelRenderer legLft;
    
    public ModelRenderer head;
    public ModelRenderer earRt;
    public ModelRenderer earLft;
    public ModelRenderer earRtTip;
    public ModelRenderer earLftTip;

    public ModelSubstituteDoll() 
    {
        this.textureWidth = 64;
        this.textureHeight = 32;

        this.body = new ModelRenderer(this, 36, 18);
        this.body.setRotationPoint(0.0F, 20.5F, 0.0F);
        this.body.addBox(-3.5F, -3.5F, -3.5F, 7, 7, 7, 0.0F);

        this.armRt = new ModelRenderer(this, 0, 14);
        this.armRt.setRotationPoint(-2.5F, -2.0F, -3.5F);
        this.armRt.addBox(-1.5F, -1.5F, -2.5F, 3, 3, 3, 0.0F);
        this.setRotateAngle(armRt, 0.0F, 0.17453292519943295F, 0.0F);
        this.armLft = new ModelRenderer(this, 13, 14);
        this.armLft.setRotationPoint(2.5F, -2.0F, -3.5F);
        this.armLft.addBox(-1.5F, -1.5F, -2.5F, 3, 3, 3, 0.0F);
        this.setRotateAngle(armLft, 0.0F, -0.17453292519943295F, 0.0F);
        this.legRt = new ModelRenderer(this, 0, 21);
        this.legRt.setRotationPoint(-2.5F, 2.0F, -3.5F);
        this.legRt.addBox(-1.5F, -1.5F, -2.5F, 3, 3, 3, 0.0F);
        this.setRotateAngle(legRt, 0.0F, 0.3490658503988659F, 0.0F);
        this.legLft = new ModelRenderer(this, 13, 21);
        this.legLft.setRotationPoint(2.5F, 2.0F, -3.5F);
        this.legLft.addBox(-1.5F, -1.5F, -2.5F, 3, 3, 3, 0.0F);
        this.setRotateAngle(legLft, 0.0F, -0.3490658503988659F, 0.0F);

        this.head = new ModelRenderer(this, 0, 0);
        this.head.setRotationPoint(0.0F, -3.5F, 0.0F);
        this.head.addBox(-4.0F, -6.0F, -4.5F, 8, 6, 7, 0.0F);
        this.earRt = new ModelRenderer(this, 31, 4);
        this.earRt.setRotationPoint(-3.0F, -5.0F, 0.0F);
        this.earRt.addBox(-1.5F, -2.5F, -1.5F, 3, 3, 3, 0.0F);
        this.setRotateAngle(earRt, 0.0F, 0.0F, -0.4363323129985824F);
        this.earRtTip = new ModelRenderer(this, 33, 0);
        this.earRtTip.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.earRtTip.addBox(-1.0F, -3.5F, -1.0F, 2, 1, 2, 0.0F);
        this.earLft = new ModelRenderer(this, 44, 4);
        this.earLft.setRotationPoint(3.0F, -5.0F, 0.0F);
        this.earLft.addBox(-1.5F, -2.5F, -1.5F, 3, 3, 3, 0.0F);
        this.setRotateAngle(earLft, 0.0F, 0.0F, 0.4363323129985824F);
        this.earLftTip = new ModelRenderer(this, 46, 0);
        this.earLftTip.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.earLftTip.addBox(-1.0F, -3.5F, -1.0F, 2, 1, 2, 0.0F);
        
        this.body.addChild(this.armLft);
        this.body.addChild(this.armRt);
        this.body.addChild(this.head);
        this.body.addChild(this.legLft);
        this.body.addChild(this.legRt);
        this.earLft.addChild(this.earLftTip);
        this.earRt.addChild(this.earRtTip);
        this.head.addChild(this.earLft);
        this.head.addChild(this.earRt);
    }

    @Override
    public void render(Entity entity, float distanceMoved, float horzVelocity, float yawRotationDifference, float yawHeadOffsetDifference, float pitchRotation, float modelSize)
    { 
        this.body.render(modelSize);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}