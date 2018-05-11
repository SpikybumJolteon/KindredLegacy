package fuzzyacornindustries.kindredlegacy.animation;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

public class PartAnimate
{
	public static void transposePart(PartInfo parentInfo, ModelRenderer slavePart, PartInfo slaveInfo)
	{
		AniMatrix matrix = new AniMatrix();

		if(parentInfo.getRotateChangeZ() != 0)
		{
			matrix = matrix.rotateZ(parentInfo.getRotateChangeZ());
		}

		if(parentInfo.getRotateChangeY() != 0)
		{
			matrix = matrix.rotateY(parentInfo.getRotateChangeY());
		}

		if(parentInfo.getRotateChangeX() != 0)
		{
			matrix = matrix.rotateX(parentInfo.getRotateChangeX());
		}

		Vector3f startingVector = new Vector3f(slaveInfo.getNewPnt());

		Vector3f startVectorSetOrigin = Vector3f.vectorSubtVector(startingVector, parentInfo.getNewPnt());

		Vector3f vectorMultiMatrix = AniMatrix.multiMatrixByVector3f(matrix, startVectorSetOrigin);

		Vector3f vectorFromOrigin = Vector3f.vectorPlusVector(vectorMultiMatrix, parentInfo.getNewPnt());

		slaveInfo.setNewPnt(vectorFromOrigin);

		assignVectorToPnt(slavePart, slaveInfo.getNewPnt());
	}

	public static void transposePartInfoOnly(PartInfo parentInfo, PartInfo slaveInfo)
	{
		AniMatrix matrix = new AniMatrix();

		if(parentInfo.getRotateChangeZ() != 0)
		{
			matrix = matrix.rotateZ(parentInfo.getRotateChangeZ());
		}

		if(parentInfo.getRotateChangeY() != 0)
		{
			matrix = matrix.rotateY(parentInfo.getRotateChangeY());
		}

		if(parentInfo.getRotateChangeX() != 0)
		{
			matrix = matrix.rotateX(parentInfo.getRotateChangeX());
		}

		Vector3f startingVector = new Vector3f(slaveInfo.getNewPnt());

		Vector3f startVectorSetOrigin = Vector3f.vectorSubtVector(startingVector, parentInfo.getNewPnt());

		Vector3f vectorMultiMatrix = AniMatrix.multiMatrixByVector3f(matrix, startVectorSetOrigin);

		Vector3f vectorFromOrigin = Vector3f.vectorPlusVector(vectorMultiMatrix, parentInfo.getNewPnt());

		slaveInfo.setNewPnt(vectorFromOrigin);
	}

	// *****************************************************
	// * Mirror the shift from one part's rotation point   *
	// * to a based on slave part's default rotation point *
	// *****************************************************
	public static void shiftSlaveRotationPntWithParent(PartInfo parentInfo, PartInfo slaveInfo)
	{
		slaveInfo.setNewPnt(Vector3f.vectorPlusVector(slaveInfo.getDefaultPnt(), Vector3f.vectorSubtVector(parentInfo.getNewPnt(), parentInfo.getDefaultPnt())));
	}

	public static void shiftSlaveRotationPntWithParentWithModifiers(PartInfo parentInfo, PartInfo slaveInfo, float modifier)
	{
		slaveInfo.setNewPnt(Vector3f.vectorPlusVector(slaveInfo.getDefaultPnt(), Vector3f.vectorMultiScalar(Vector3f.vectorSubtVector(parentInfo.getNewPnt(), parentInfo.getDefaultPnt()), modifier)));
	}

	// ***************************************************
	// * Mirror the shift from one part's rotation point *
	// * to a based on slave part's new rotation point   *
	// ***************************************************
	public static void shiftSlaveRotationNewPntWithParent(PartInfo parentInfo, PartInfo slaveInfo)
	{
		slaveInfo.setNewPnt(Vector3f.vectorPlusVector(slaveInfo.getNewPnt(), Vector3f.vectorSubtVector(parentInfo.getNewPnt(), parentInfo.getDefaultPnt())));
	}

	public static void shiftSlaveRotationNewPntWithParentWithModifiers(PartInfo parentInfo, PartInfo slaveInfo, float modifier)
	{
		slaveInfo.setNewPnt(Vector3f.vectorPlusVector(slaveInfo.getNewPnt(), Vector3f.vectorMultiScalar(Vector3f.vectorSubtVector(parentInfo.getNewPnt(), parentInfo.getDefaultPnt()), modifier)));
	}

	// This automatically applies vanilla mob head animations to a part, 
	//   applies its default rotations from an array.
	public static void headAnimate(ModelRenderer part, PartInfo partInfo, float par4, float par5)
	{
		float xRotate = par5 / (180F / (float)Math.PI);
		float yRotate = par4 / (180F / (float)Math.PI);

		partInfo.setNewRotateX(partInfo.getNewRotateX() + xRotate);
		partInfo.setNewRotateY(partInfo.getNewRotateY() + yRotate);

		part.rotateAngleX = partInfo.getNewRotateX();
		part.rotateAngleY = partInfo.getNewRotateY();
		part.rotateAngleZ = partInfo.getNewRotateZ();
	}

	public static void headAnimateInfoOnly(PartInfo partInfo, float par4, float par5)
	{
		float xRotate = par5 / (180F / (float)Math.PI);
		float yRotate = par4 / (180F / (float)Math.PI);

		partInfo.setNewRotateX(partInfo.getNewRotateX() + xRotate);
		partInfo.setNewRotateY(partInfo.getNewRotateY() + yRotate);
	}

	// This method mimics the head animation but amplifies or reduces
	//    the angle of rotation
	public static void headAnimateWithAngleModifiers(ModelRenderer part, PartInfo partInfo, float par4, float par5, float xModifier, float yModifier)
	{
		float xRotate = par5 / (180F / (float)Math.PI) * xModifier;
		float yRotate = par4 / (180F / (float)Math.PI) * yModifier;

		partInfo.setNewRotateX(partInfo.getNewRotates().getX() + xRotate);
		partInfo.setNewRotateY(partInfo.getNewRotates().getY() + yRotate);

		part.rotateAngleX = partInfo.getNewRotateX();
		part.rotateAngleY = partInfo.getNewRotateY();
		part.rotateAngleZ = partInfo.getNewRotateZ();
	}

	public static void headAnimateInfoOnlyWithAngleModifiers(PartInfo partInfo, float par4, float par5, float xModifier, float yModifier)
	{
		float xRotate = par5 / (180F / (float)Math.PI) * xModifier;
		float yRotate = par4 / (180F / (float)Math.PI) * yModifier;

		partInfo.setNewRotateX(partInfo.getNewRotateX() + xRotate);
		partInfo.setNewRotateY(partInfo.getNewRotateY() + yRotate);
	}

	public static void headAnimateInfoOnlyWithAngleModifiersAndIdleDampener(PartInfo partInfo, float par4, float par5, float xModifier, float yModifier, float idleDampener)
	{
		float xRotate = par5 / (180F / (float)Math.PI) * xModifier;
		float yRotate = par4 / (180F / (float)Math.PI) * yModifier;

		partInfo.setNewRotateX(partInfo.getNewRotateX() + xRotate * idleDampener);
		partInfo.setNewRotateY(partInfo.getNewRotateY() + yRotate * idleDampener);
	}

	public static void headAnimateHorz(ModelRenderer part, PartInfo partInfo, float par4, float par5)
	{
		float yRotate = par4 / (180F / (float)Math.PI);

		part.rotateAngleX = partInfo.getNewRotateX();
		part.rotateAngleY = partInfo.getNewRotateY() + yRotate;
		part.rotateAngleZ = partInfo.getNewRotateZ();
	}

	public static void headAnimateHorzWithAngleModifiers(ModelRenderer part, PartInfo partInfo, float par4, float par5, float yModifier)
	{
		float yRotate = par4 / (180F / (float)Math.PI) * yModifier;;

		part.rotateAngleX = partInfo.getNewRotateX();
		part.rotateAngleY = partInfo.getNewRotateY() + yRotate;
		part.rotateAngleZ = partInfo.getNewRotateZ();
	}

	public static void headAnimateVert(ModelRenderer part, PartInfo partInfo, float par4, float par5)
	{
		float xRotate = par5 / (180F / (float)Math.PI);

		part.rotateAngleX = partInfo.getNewRotateX() + xRotate;
		part.rotateAngleY = partInfo.getNewRotateY();
		part.rotateAngleZ = partInfo.getNewRotateZ();
	}

	public static void mirrorPartRotatesInfoChangesToSlavePart(PartInfo mainPartInfo, ModelRenderer slavePart, PartInfo slavePartInfo)
	{
		slavePartInfo.setNewRotateX(mainPartInfo.getRotateChangeX() + slavePartInfo.getNewRotateX());
		slavePartInfo.setNewRotateY(mainPartInfo.getRotateChangeY() + slavePartInfo.getNewRotateY());
		slavePartInfo.setNewRotateZ(mainPartInfo.getRotateChangeZ() + slavePartInfo.getNewRotateZ());

		slavePart.rotateAngleX = slavePartInfo.getNewRotateX();
		slavePart.rotateAngleY = slavePartInfo.getNewRotateY();
		slavePart.rotateAngleZ = slavePartInfo.getNewRotateZ();
	}

	public static void mirrorPartRotatesInfoChangesToSlavePartInfo(PartInfo mainPartInfo, PartInfo slavePartInfo)
	{
		slavePartInfo.setNewRotatesWithVector(Vector3f.vectorPlusVector(slavePartInfo.getNewRotates(), mainPartInfo.getRotatesChangeVector()));
	}

	public static void mirrorPartRotationPntInfoChangesToSlavePart(PartInfo mainPartInfo, ModelRenderer slavePart, PartInfo slavePartInfo)
	{
		slavePartInfo.setNewRotatesWithVector(mainPartInfo.getNewPnt());

		slavePart.rotationPointX = slavePartInfo.getNewPnt().getX();
		slavePart.rotationPointY = slavePartInfo.getNewPnt().getY();
		slavePart.rotationPointZ = slavePartInfo.getNewPnt().getZ();
	}

	public static void mirrorPartInfoChangesToSlavePart(PartInfo mainPartInfo, ModelRenderer slavePart, PartInfo slavePartInfo)
	{
		mirrorPartRotatesInfoChangesToSlavePart(mainPartInfo, slavePart, slavePartInfo);
		mirrorPartRotationPntInfoChangesToSlavePart(mainPartInfo, slavePart, slavePartInfo);
	}

	public static void assignVectorToPnt(ModelRenderer part, Vector3f vector)
	{
		part.rotationPointX = (float)vector.getX();
		part.rotationPointY = (float)vector.getY();
		part.rotationPointZ = (float)vector.getZ();
	}

	// The pos and neg cos/sin rotate animation functions are identical to vanilla 
	//   Minecraft wolf walk animation code. The two input values are par2 and 
	//   par3 respectively in Living Animations function of the model class.
	public static float posCosRotateAnimation(float par1, float par2)
	{
		return MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	}

	public static float negCosRotateAnimation(float par1, float par2)
	{
		return MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
	}

	public static float posCosRotateAnimationAdjusted(float par1, float par2, float frequency, float amplitude)
	{
		return amplitude * MathHelper.cos(frequency * par1 * 0.6662F) * 1.4F * par2;
	}

	public static float negCosRotateAnimationAdjusted(float par1, float par2, float frequency, float amplitude)
	{
		return amplitude * MathHelper.cos(frequency * par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
	}

	public static float posSinRotateAnimation(float par1, float par2)
	{
		return MathHelper.sin(par1 * 0.6662F) * 1.4F * par2;
	}

	public static float negSinRotateAnimation(float par1, float par2)
	{
		return MathHelper.sin(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
	}
	
    // Calculate the duration covered between a duration start and end for a supplied tick
	public static float calculateDuration(float tick, float durationStart, float durationEnd)
	{
		if(tick < durationStart)
		{
			return 0F;
		}
		else if(tick > durationEnd)
		{
			return 1F;
		}
		else
		{
			return (tick - durationStart)/(durationEnd - durationStart);
		}
	}
	
	// Add a rotation change vector to a rotation partInfo
	public static void applyRotationChangeVectorToInfo(PartInfo partInfo, Vector3f angleChanges)
	{
		partInfo.setNewRotatesWithVector(
				Vector3f.vectorPlusVector(partInfo.getNewRotates(), angleChanges));
	}
	
	// Rotate an angle change over a duration with an S-curve
	public static void changeOverDurationSCurve(Vector3f cumulativeVector, Vector3f additionVector, float durationCovered)
	{
		cumulativeVector.addVector(
				Vector3f.vectorMultiScalar(additionVector, 
						0.5F * (float)MathHelper.cos((float)Math.PI * durationCovered - (float)Math.PI) + 0.5F));
	}

	// Rotate an angle change over a duration with an U-curve
	public static void changeOverDurationUCurve(Vector3f cumulativeVector, Vector3f additionVector, float durationCovered)
	{
		cumulativeVector.addVector(
				Vector3f.vectorMultiScalar(additionVector, 
						0.5F * (float)MathHelper.cos(2 * (float)Math.PI * durationCovered - (float)Math.PI) + 0.5F));
	}
	
	// Add a point change vector to a rotation point partInfo
	public static void applyPointChangeVectorToInfo(PartInfo partInfo, Vector3f angleChanges)
	{
		partInfo.setNewPnt(Vector3f.vectorPlusVector(partInfo.getNewPnt(), angleChanges));
	}
}