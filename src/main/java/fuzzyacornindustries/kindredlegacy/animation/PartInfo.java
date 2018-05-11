package fuzzyacornindustries.kindredlegacy.animation;

import net.minecraft.client.model.ModelRenderer;

public class PartInfo
{
	// Rotates refers to the Model Renderer class angles called rotates
	protected Vector3f defaultRotates;
	protected Vector3f defaultPnt;
	
	protected Vector3f newRotates;
	protected Vector3f newPnt;
	
	public PartInfo(ModelRenderer modelPart)
	{
		defaultRotates = new Vector3f(modelPart.rotateAngleX, modelPart.rotateAngleY, modelPart.rotateAngleZ);
		defaultPnt = new Vector3f(modelPart.rotationPointX, modelPart.rotationPointY, modelPart.rotationPointZ);
		
		newRotates = defaultRotates;
		newPnt = defaultPnt;
	}
	
	public Vector3f getDefaultRotates()
	{
		return defaultRotates;
	}
	
	public Vector3f getDefaultPnt()
	{
		return defaultPnt;
	}
	
	// ***************************
	// * Get new rotation angles *
	// ***************************
	public Vector3f getNewRotates()
	{
		return newRotates;
	}
	
	public float getNewRotateX()
	{
		return newRotates.getX();
	}
	
	public float getNewRotateY()
	{
		return newRotates.getY();
	}
	
	public float getNewRotateZ()
	{
		return newRotates.getZ();
	}

	// **************************************
	// * Get new rotation point coordinates *
	// **************************************
	public Vector3f getNewPnt()
	{
		return newPnt;
	}
	
	public float getNewPointX()
	{
		return newPnt.getX();
	}
	
	public float getNewPointY()
	{
		return newPnt.getY();
	}
	
	public float getNewPointZ()
	{
		return newPnt.getZ();
	}
	
	// ***********************
	// * Set rotation angles *
	// ***********************
	public void setNewRotatesWithVector(Vector3f rotatesVector)
	{
		newRotates = rotatesVector;
	}
	
	public void setNewRotates(float newAngleX, float newAngleY, float newAngleZ)
	{
		newRotates = new Vector3f(newAngleX, newAngleY, newAngleZ);
	}
	
	public void setNewRotateX(float newAngle)
	{
		newRotates = new Vector3f(newAngle, newRotates.getY(), newRotates.getZ());
	}
	
	public void setNewRotateY(float newAngle)
	{
		newRotates = new Vector3f(newRotates.getX(), newAngle, newRotates.getZ());
	}
	
	public void setNewRotateZ(float newAngle)
	{
		newRotates = new Vector3f(newRotates.getX(), newRotates.getY(), newAngle);
	}

	// **************************************
	// * Set new rotation point coordinates *
	// **************************************
	public void setNewPnt(Vector3f rotationPnt)
	{
		newPnt = rotationPnt;
	}
	
	public void setNewPointX(float newPointX)
	{
		newPnt = new Vector3f(newPointX, newPnt.getY(), newPnt.getZ());
	}
	
	public void setNewPointY(float newPointY)
	{
		newPnt = new Vector3f(newPnt.getX(), newPointY, newPnt.getZ());
	}
	
	public void setNewPointZ(float newPointZ)
	{
		newPnt = new Vector3f(newPnt.getX(), newPnt.getY(), newPointZ);
	}

	// *************************************************************
	// * Get difference in rotation angles between default and new *
	// *************************************************************
	public Vector3f getRotatesChangeVector()
	{
		return Vector3f.vectorSubtVector(newRotates, defaultRotates);
	}
	
	public float getRotateChangeX()
	{
		return Vector3f.vectorSubtVector(newRotates, defaultRotates).getX();
	}
	
	public float getRotateChangeY()
	{
		return Vector3f.vectorSubtVector(newRotates, defaultRotates).getY();
	}
	
	public float getRotateChangeZ()
	{
		return Vector3f.vectorSubtVector(newRotates, defaultRotates).getZ();
	}

	// ************************************************************************
	// * Get difference in rotation point coordinates between default and new *
	// ************************************************************************
	public float getPointChangeX()
	{
		return (float)Vector3f.vectorSubtVector(newPnt, defaultPnt).getX();
	}
	
	public float getPointChangeY()
	{
		return (float)Vector3f.vectorSubtVector(newPnt, defaultPnt).getY();
	}
	
	public float getPointChangeZ()
	{
		return (float)Vector3f.vectorSubtVector(newPnt, defaultPnt).getZ();
	}
	
	// Get current rotation point from ModelRenderer part
	public static Vector3f rotatesToVector(ModelRenderer part)
	{
		return new Vector3f(part.rotateAngleX, part.rotateAngleY, part.rotateAngleZ);
	}

	// Get current rotation angles from ModelRenderer part
	public static Vector3f rotationPointToVector(ModelRenderer part)
	{
		return new Vector3f(part.rotationPointX, part.rotationPointY, part.rotationPointZ);
	}
	
	// Assign the stored default rotation point coordinates to ModelRenderer part
	public void assignDefaultRotationPointToPart(ModelRenderer part)
	{
		part.rotationPointX = defaultPnt.getX();
		part.rotationPointY = defaultPnt.getY();
		part.rotationPointZ = defaultPnt.getZ();
	}

	// Assign the stored new rotation point coordinates to ModelRenderer part
	public void assignNewRotationPointToPart(ModelRenderer part)
	{
		part.rotationPointX = newPnt.getX();
		part.rotationPointY = newPnt.getY();
		part.rotationPointZ = newPnt.getZ();
	}
	
	// Assign stored default rotation angles to ModelRenderer part
	public void assignDefaultRotationAnglesToPart(ModelRenderer part)
	{
		part.rotateAngleX = defaultRotates.getX();
		part.rotateAngleY = defaultRotates.getY();
		part.rotateAngleZ = defaultRotates.getZ();
	}
	
	// Assign stored new rotation angles to ModelRenderer part
	public void assignNewRotationAnglesToPart(ModelRenderer part)
	{
		part.rotateAngleX = newRotates.getX();
		part.rotateAngleY = newRotates.getY();
		part.rotateAngleZ = newRotates.getZ();
	}
	
	// Set new rotation point coordinates to stored default point
	public void resetNewPnt()
	{
		newPnt = defaultPnt;
	}

	// Set new rotate angles to store default angles
	public void resetNewAngles()
	{
		newRotates = defaultRotates;
	}
}