package fuzzyacornindustries.kindredlegacy.client.animation;

import net.minecraft.util.math.MathHelper;

public class ModMathFunctions 
{
	public static Vector3f findShooterOffsetPoint(double shooterPosX, double shooterPosZ,
			double targetPosX, double targetPosZ, float offsetDistance)
	{
		Vector3f shooterPos = new Vector3f((float)shooterPosX, 0F, (float)shooterPosZ);
		Vector3f targetPos = new Vector3f((float)targetPosX, 0F, (float)targetPosZ);

		float t = offsetDistance / (float)Vector3f.distance(shooterPos, targetPos);

		Vector3f offsetPos = new Vector3f(Vector3f.vectorSubtVector(targetPos, shooterPos));
		offsetPos = Vector3f.vectorPlusVector(Vector3f.vectorMultiScalar(offsetPos, t), shooterPos);
		
		return offsetPos;
	}
	
	public static float[] rotatePointGeneric2D(float firstCoordinate, float secondCoordinate, float angle)
	{
		float newCoordinates[] = new float[2];
		
		newCoordinates[0] = firstCoordinate * MathHelper.cos(angle) - secondCoordinate * MathHelper.sin(angle);
		newCoordinates[1] = firstCoordinate * MathHelper.sin(angle) + secondCoordinate * MathHelper.cos(angle);
		
		return newCoordinates;
	}
	
	/*
	public static Vector3f findShooterOffsetPoint(double shooterPosX, double shooterPosY, double shooterPosZ,
			double targetPosX, double targetPosY, double targetPosZ, float offsetDistance)
	{
		Vector3f shooterPos = new Vector3f((float)shooterPosX, (float)shooterPosY, (float)shooterPosZ);
		Vector3f targetPos = new Vector3f((float)targetPosX, (float)targetPosY, (float)targetPosZ);

		float t = offsetDistance / (float)Vector3f.distance(shooterPos, targetPos);

		Vector3f offsetPos = new Vector3f(Vector3f.vectorSubtVector(targetPos, shooterPos));
		offsetPos = Vector3f.vectorPlusVector(Vector3f.vectorMultiScalar(offsetPos, t), shooterPos);
		
		return offsetPos;
	}*/
}