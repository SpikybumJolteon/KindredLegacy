package fuzzyacornindustries.kindredlegacy.client.animation;

import java.util.HashMap;

import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AnimationDeployer
{
	private IAnimatedEntity animatedEntity;

	private HashMap<ModelRenderer, PartInfo> partInfoMap;

	public static final float PI = (float)Math.PI;

	public AnimationDeployer(Model model) 
	{
		partInfoMap = new HashMap<ModelRenderer, PartInfo>();
	}

	public IAnimatedEntity getEntity()
	{
		return animatedEntity;
	}

	public void update(IAnimatedEntity entity) 
	{
		animatedEntity = entity;

		partInfoMap.clear();
	}

	public void rotate(ModelRenderer box, float newAngleX, float newAngleY, float newAngleZ) 
	{
		if(!partInfoMap.containsKey(box)) 
		{
			partInfoMap.put(box, new PartInfo(box));
		}

		partInfoMap.get(box).setNewRotates(newAngleX, newAngleY, newAngleZ);
	}

	public void rotate(ModelRenderer box, Vector3f rotateVector) 
	{
		if(!partInfoMap.containsKey(box)) 
		{
			partInfoMap.put(box, new PartInfo(box));
		}

		partInfoMap.get(box).setNewRotates(rotateVector.getX(), rotateVector.getY(), rotateVector.getZ());
	}

	public void rotateAdd(ModelRenderer box, float newAngleX, float newAngleY, float newAngleZ) 
	{
		if(!partInfoMap.containsKey(box)) 
		{
			partInfoMap.put(box, new PartInfo(box));
		}

		partInfoMap.get(box).setNewRotatesWithVector(Vector3f.vectorPlusVector(partInfoMap.get(box).getNewRotates(), new Vector3f(newAngleX, newAngleY, newAngleZ)));
	}

	public void move(ModelRenderer box, float newPointX, float newPointY, float newPointZ) 
	{
		if(!partInfoMap.containsKey(box)) 
		{
			partInfoMap.put(box, new PartInfo(box));
		}

		partInfoMap.get(box).setNewPnt(new Vector3f(newPointX, newPointY, newPointZ));
	}

	public void move(ModelRenderer box, Vector3f moveVector) 
	{
		if(!partInfoMap.containsKey(box)) 
		{
			partInfoMap.put(box, new PartInfo(box));
		}

		partInfoMap.get(box).setNewPnt(moveVector);
		}

	public void moveAdd(ModelRenderer box, float addToPointX, float addToPointY, float addToPointZ) 
	{
		if(!partInfoMap.containsKey(box)) 
		{
			partInfoMap.put(box, new PartInfo(box));
		}

		partInfoMap.get(box).setNewPnt(Vector3f.vectorPlusVector(partInfoMap.get(box).getNewPnt(), new Vector3f(addToPointX, addToPointY, addToPointZ)));
	}

	public void applyChanges()
	{
		for(ModelRenderer box : partInfoMap.keySet()) 
		{
			PartInfo partInfo = partInfoMap.get(box);

			box.rotateAngleX = partInfo.getNewRotateX();
			box.rotateAngleY = partInfo.getNewRotateY();
			box.rotateAngleZ = partInfo.getNewRotateZ();
			box.rotationPointX += partInfo.getPointChangeX();
			box.rotationPointY += partInfo.getPointChangeY();
			box.rotationPointZ += partInfo.getPointChangeZ();
		}

		partInfoMap.clear();
	}
}