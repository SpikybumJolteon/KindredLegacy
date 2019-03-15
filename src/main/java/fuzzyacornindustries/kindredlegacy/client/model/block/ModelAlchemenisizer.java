package fuzzyacornindustries.kindredlegacy.client.model.block;

import fuzzyacornindustries.kindredlegacy.animation.AnimationDeployer;
import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.animation.PartInfo;
import fuzzyacornindustries.kindredlegacy.tileentity.TileEntityAlchemenisizer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelAlchemenisizer extends ModelBase 
{
	public final float PI = (float)Math.PI;

	private AnimationDeployer animationDeployer;

	public ModelRenderer antenna;
	PartInfo antennaInfo;

	public ModelRenderer antennaPanel[] = new ModelRenderer[4];
	PartInfo antennaPanelInfo[] = new PartInfo[antennaPanel.length];

	public ModelRenderer panelLower[] = new ModelRenderer[4];
	PartInfo panelLowerInfo[] = new PartInfo[panelLower.length];

	public ModelRenderer panelUpper[] = new ModelRenderer[4];
	PartInfo panelUpperInfo[] = new PartInfo[panelUpper.length];

	public ModelAlchemenisizer() 
	{
		this.textureWidth = 64;
		this.textureHeight = 32;

		this.antenna = new ModelRenderer(this, 2, 29); // antenna1
		this.antenna.setRotationPoint(0.0F, 21.0F, 0.0F);
		this.antenna.addBox(-1.0F, 1.0F, -1.0F, 2, 1, 2, 0.0F);
		this.antenna.setTextureOffset(0, 23).addBox(-1.5F, -1.0F, -1.5F, 3, 2, 3, 0.0F); // antenna2
		this.antenna.setTextureOffset(2, 18).addBox(-1.0F, -3.0F, -1.0F, 2, 2, 2, 0.0F); // antenna3
		this.antenna.setTextureOffset(4, 14).addBox(-0.5F, -5.0F, -0.5F, 1, 2, 1, 0.0F); // antenna4
		this.antenna.setTextureOffset(2, 9).addBox(-1.0F, -7.0F, -1.0F, 2, 2, 2, 0.0F); // antenna5
		this.antenna.setTextureOffset(4, 2).addBox(-0.5F, -12.0F, -0.5F, 1, 5, 1, 0.0F); // antenna6
		antennaInfo = new PartInfo(antenna);

		int partNumber = 0;
		this.antennaPanel[partNumber] = new ModelRenderer(this, 17, 0); // antennaPanel1
		this.antennaPanel[partNumber].setRotationPoint(0.0F, 15.0F, 0.0F);
		this.antennaPanel[partNumber].addBox(-0.5F, -0.5F, -5.5F, 1, 1, 3, 0.0F);
		this.setRotateAngle(antennaPanel[partNumber], -0.9599310885968813F, 0.0F, 0.0F);
		antennaPanelInfo[partNumber] = new PartInfo(antennaPanel[partNumber]);
		partNumber++;
		this.antennaPanel[partNumber] = new ModelRenderer(this, 22, 4); // antennaPanel2
		this.antennaPanel[partNumber].setRotationPoint(0.0F, 15.0F, 0.0F);
		this.antennaPanel[partNumber].addBox(-0.5F, -0.5F, -5.5F, 1, 1, 3, 0.0F);
		this.setRotateAngle(antennaPanel[partNumber], -0.9599310885968813F, 1.5707963267948966F, 0.0F);
		antennaPanelInfo[partNumber] = new PartInfo(antennaPanel[partNumber]);
		partNumber++;
		this.antennaPanel[partNumber] = new ModelRenderer(this, 17, 6); // antennaPanel3
		this.antennaPanel[partNumber].setRotationPoint(0.0F, 15.0F, 0.0F);
		this.antennaPanel[partNumber].addBox(-0.5F, -0.5F, -5.5F, 1, 1, 3, 0.0F);
		this.setRotateAngle(antennaPanel[partNumber], -0.9599310885968813F, 3.141592653589793F, 0.0F);
		antennaPanelInfo[partNumber] = new PartInfo(antennaPanel[partNumber]);
		partNumber++;
		this.antennaPanel[partNumber] = new ModelRenderer(this, 12, 4); // antennaPanel4
		this.antennaPanel[partNumber].setRotationPoint(0.0F, 15.0F, 0.0F);
		this.antennaPanel[partNumber].addBox(-0.5F, -0.5F, -5.5F, 1, 1, 3, 0.0F);
		this.setRotateAngle(antennaPanel[partNumber], -0.9599310885968813F, 4.71238898038469F, 0.0F);
		antennaPanelInfo[partNumber] = new PartInfo(antennaPanel[partNumber]);

		partNumber = 0;
		this.panelLower[partNumber] = new ModelRenderer(this, 40, 17); // panelLower1
		this.panelLower[partNumber].setRotationPoint(0.0F, 22.0F, 0.0F);
		this.panelLower[partNumber].addBox(-2.0F, -0.5F, -7.5F, 4, 1, 4, 0.0F);
		panelLowerInfo[partNumber] = new PartInfo(panelLower[partNumber]);
		partNumber++;
		this.panelLower[partNumber] = new ModelRenderer(this, 48, 22); // panelLower2
		this.panelLower[partNumber].setRotationPoint(0.0F, 22.0F, 0.0F);
		this.panelLower[partNumber].addBox(-2.0F, -0.5F, -7.5F, 4, 1, 4, 0.0F);
		this.setRotateAngle(panelLower[partNumber], 0.0F, 1.5707963267948966F, 0.0F);
		panelLowerInfo[partNumber] = new PartInfo(panelLower[partNumber]);
		partNumber++;
		this.panelLower[partNumber] = new ModelRenderer(this, 40, 27); // panelLower3
		this.panelLower[partNumber].setRotationPoint(0.0F, 22.0F, 0.0F);
		this.panelLower[partNumber].addBox(-2.0F, -0.5F, -7.5F, 4, 1, 4, 0.0F);
		this.setRotateAngle(panelLower[partNumber], 0.0F, 3.141592653589793F, 0.0F);
		panelLowerInfo[partNumber] = new PartInfo(panelLower[partNumber]);
		partNumber++;
		this.panelLower[partNumber] = new ModelRenderer(this, 32, 22); // panelLower4
		this.panelLower[partNumber].setRotationPoint(0.0F, 22.0F, 0.0F);
		this.panelLower[partNumber].addBox(-2.0F, -0.5F, -7.5F, 4, 1, 4, 0.0F);
		this.setRotateAngle(panelLower[partNumber], 0.0F, 4.71238898038469F, 0.0F);
		panelLowerInfo[partNumber] = new PartInfo(panelLower[partNumber]);
		
		partNumber = 0;
		this.panelUpper[partNumber] = new ModelRenderer(this, 40, 0); // panelUpper1
		this.panelUpper[partNumber].setRotationPoint(0.0F, 20.5F, 0.0F);
		this.panelUpper[partNumber].addBox(-2.0F, -0.5F, -7.5F, 4, 1, 4, 0.0F);
		this.setRotateAngle(panelUpper[partNumber], 0.0F, 0.7853981633974483F, 0.0F);
		panelUpperInfo[partNumber] = new PartInfo(panelUpper[partNumber]);
		partNumber++;
		this.panelUpper[partNumber] = new ModelRenderer(this, 40, 0); // panelUpper2
		this.panelUpper[partNumber].setRotationPoint(0.0F, 20.5F, 0.0F);
		this.panelUpper[partNumber].addBox(-2.0F, -0.5F, -7.5F, 4, 1, 4, 0.0F);
		this.setRotateAngle(panelUpper[partNumber], 0.0F, 2.356194490192345F, 0.0F);
		panelUpperInfo[partNumber] = new PartInfo(panelUpper[partNumber]);
		partNumber++;
		this.panelUpper[partNumber] = new ModelRenderer(this, 40, 0); // panelUpper3
		this.panelUpper[partNumber].setRotationPoint(0.0F, 20.5F, 0.0F);
		this.panelUpper[partNumber].addBox(-2.0F, -0.5F, -7.5F, 4, 1, 4, 0.0F);
		this.setRotateAngle(panelUpper[partNumber], 0.0F, 3.9269908169872414F, 0.0F);
		panelUpperInfo[partNumber] = new PartInfo(panelUpper[partNumber]);
		partNumber++;
		this.panelUpper[partNumber] = new ModelRenderer(this, 40, 0); // panelUpper4
		this.panelUpper[partNumber].setRotationPoint(0.0F, 20.5F, 0.0F);
		this.panelUpper[partNumber].addBox(-2.0F, -0.5F, -7.5F, 4, 1, 4, 0.0F);
		this.setRotateAngle(panelUpper[partNumber], 0.0F, 5.497787143782138F, 0.0F);
		panelUpperInfo[partNumber] = new PartInfo(panelUpper[partNumber]);

		animationDeployer = new AnimationDeployer(this);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) 
	{
		this.antenna.render(f5);

		for(int i = 0; i < antennaPanel.length; i++)
		{
			this.antennaPanel[i].render(f5);
		}

		for(int i = 0; i < panelLower.length; i++)
		{
			this.panelLower[i].render(f5);
		}

		for(int i = 0; i < panelUpper.length; i++)
		{
			this.panelUpper[i].render(f5);
		}
	}

	public void renderModel(float f, TileEntityAlchemenisizer tileEntity)
	{
		animate(tileEntity);

		this.antenna.render(f);

		for(int i = 0; i < antennaPanel.length; i++)
		{
			this.antennaPanel[i].render(f);
		}

		for(int i = 0; i < panelLower.length; i++)
		{
			this.panelLower[i].render(f);
		}

		for(int i = 0; i < panelUpper.length; i++)
		{
			this.panelUpper[i].render(f);
		}
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) 
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void animate(TileEntityAlchemenisizer tileEntity)
	{	
		this.animationDeployer.update((IAnimatedEntity)tileEntity);
		resetPartInfos();

		animateVerticalShift((TileEntityAlchemenisizer)tileEntity);
		animateAntennaPanels((TileEntityAlchemenisizer)tileEntity);
		animatePanels((TileEntityAlchemenisizer)tileEntity);

		deployAnimations();
	}

	public void resetPartInfos()
	{
		antennaInfo.resetNewAngles();
		antennaInfo.resetNewPnt();

		for(int i = 0; i < antennaPanelInfo.length; i++)
		{
			antennaPanelInfo[i].resetNewAngles();
			antennaPanelInfo[i].resetNewPnt();
		}

		for(int i = 0; i < panelLowerInfo.length; i++)
		{
			panelLowerInfo[i].resetNewAngles();
			panelLowerInfo[i].resetNewPnt();
			panelUpperInfo[i].resetNewAngles();
			panelUpperInfo[i].resetNewPnt();
		}
	}

	public void animateVerticalShift(TileEntityAlchemenisizer tileEntity)
	{
		IdleAnimationClock currentIdleAnimationClock = tileEntity.getIdleAnimationClockVerticalShift(0);

		float amplitudeShiftY = -1F;

		antennaInfo.setNewPointY(antennaInfo.getNewPointY() + 
				MathHelper.cos(2F * PI * currentIdleAnimationClock.getPhaseDurationCoveredY(0)) * amplitudeShiftY);

		currentIdleAnimationClock = tileEntity.getIdleAnimationClockVerticalShift(1);
		
		for(int i = 0; i < antennaPanelInfo.length; i++)
		{
			antennaPanelInfo[i].setNewPointY(antennaPanelInfo[i].getNewPointY() + 
					MathHelper.cos(2F * PI * currentIdleAnimationClock.getPhaseDurationCoveredY(0)) * amplitudeShiftY);
		}

		currentIdleAnimationClock = tileEntity.getIdleAnimationClockVerticalShift(2);
		
		amplitudeShiftY = -0.5F;
		
		for(int i = 0; i < panelLowerInfo.length; i++)
		{
			panelLowerInfo[i].setNewPointY(panelLowerInfo[i].getNewPointY() + 
					MathHelper.cos(2F * PI * currentIdleAnimationClock.getPhaseDurationCoveredY(0)) * amplitudeShiftY);
			panelUpperInfo[i].setNewPointY(panelUpperInfo[i].getNewPointY() + 
					MathHelper.cos(2F * PI * currentIdleAnimationClock.getPhaseDurationCoveredY(0)) * amplitudeShiftY);
		}
	}

	public void animateAntennaPanels(TileEntityAlchemenisizer tileEntity)
	{
		IdleAnimationClock currentIdleAnimationClock = tileEntity.getIdleAnimationClockRotation();

		for(int i = 0; i < antennaPanelInfo.length; i++)
		{
			antennaPanelInfo[i].setNewRotateY(antennaPanelInfo[i].getNewRotateY() + (2F * PI * currentIdleAnimationClock.getPhaseDurationCoveredY(0)));
		}
	}

	public void animatePanels(TileEntityAlchemenisizer tileEntity)
	{
		IdleAnimationClock currentIdleAnimationClock = tileEntity.getIdleAnimationClockRotation();

		for(int i = 0; i < panelLowerInfo.length; i++)
		{
			panelLowerInfo[i].setNewRotateY(panelLowerInfo[i].getNewRotateY() + (2F * PI * currentIdleAnimationClock.getPhaseDurationCoveredY(0)) * 4F);
			panelUpperInfo[i].setNewRotateY(panelUpperInfo[i].getNewRotateY() - (2F * PI * currentIdleAnimationClock.getPhaseDurationCoveredY(0)) * 4F);
		}
	}

	public void deployAnimations()
	{
		this.animationDeployer.rotate(antenna, antennaInfo.getNewRotates());
		this.animationDeployer.move(antenna, antennaInfo.getNewPnt());

		for(int i = 0; i < antennaPanel.length; i++)
		{
			this.animationDeployer.rotate(antennaPanel[i], antennaPanelInfo[i].getNewRotates());
			this.animationDeployer.move(antennaPanel[i], antennaPanelInfo[i].getNewPnt());
		}

		for(int i = 0; i < panelLower.length; i++)
		{
			this.animationDeployer.rotate(panelLower[i], panelLowerInfo[i].getNewRotates());
			this.animationDeployer.move(panelLower[i], panelLowerInfo[i].getNewPnt());
			this.animationDeployer.rotate(panelUpper[i], panelUpperInfo[i].getNewRotates());
			this.animationDeployer.move(panelUpper[i], panelUpperInfo[i].getNewPnt());
		}

		this.animationDeployer.applyChanges();
	}
}