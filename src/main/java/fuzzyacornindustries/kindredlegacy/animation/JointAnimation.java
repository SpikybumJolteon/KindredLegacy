package fuzzyacornindustries.kindredlegacy.animation;

public class JointAnimation 
{
	public static void reverseJointRotatesChange(PartInfo jointInfo, PartInfo partInfo)
	{
		partInfo.setNewRotateX(partInfo.getNewRotateX() - jointInfo.getRotateChangeX());
		partInfo.setNewRotateY(partInfo.getNewRotateY() - jointInfo.getRotateChangeY());
		partInfo.setNewRotateZ(partInfo.getNewRotateZ() - jointInfo.getRotateChangeZ());
	}

	public static void reverseJointRotatesChangeWithIdleDampener(PartInfo jointInfo, PartInfo partInfo, float idleDampener)
	{
		partInfo.setNewRotateX(partInfo.getNewRotateX() - jointInfo.getRotateChangeX() * idleDampener);
		partInfo.setNewRotateY(partInfo.getNewRotateY() - jointInfo.getRotateChangeY() * idleDampener);
		partInfo.setNewRotateZ(partInfo.getNewRotateZ() - jointInfo.getRotateChangeZ() * idleDampener);
	}

	public static void reverseJointRotatesChangeX(PartInfo jointInfo, PartInfo partInfo)
	{
		partInfo.setNewRotateX(partInfo.getNewRotateX() - jointInfo.getRotateChangeX());
	}
	
	public static void reverseJointRotatesChangeXFloatAdjustment(PartInfo jointInfo, PartInfo partInfo, float floatAdjustment)
	{
		partInfo.setNewRotateX(partInfo.getNewRotateX() - jointInfo.getRotateChangeX() - floatAdjustment);
	}

	public static void reverseJointRotatesChangeXWithModifier(PartInfo jointInfo, PartInfo partInfo, float modifier)
	{
		partInfo.setNewRotateX(partInfo.getNewRotateX() - (jointInfo.getRotateChangeX() * modifier));
	}

	public static void reverseJointRotatesChangeZ(PartInfo jointInfo, PartInfo partInfo)
	{
		partInfo.setNewRotateZ(partInfo.getNewRotateZ() - jointInfo.getRotateChangeZ());
	}

	public static void reverseJointRotatesChangeZWithModifier(PartInfo jointInfo, PartInfo partInfo, float modifier)
	{
		partInfo.setNewRotateZ(partInfo.getNewRotateZ() - (jointInfo.getRotateChangeZ() * modifier));
	}
}