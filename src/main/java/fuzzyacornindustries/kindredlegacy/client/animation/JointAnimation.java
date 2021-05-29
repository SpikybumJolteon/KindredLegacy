package fuzzyacornindustries.kindredlegacy.client.animation;

public class JointAnimation 
{
	public static void reverseJointRotatesChange(PartInfo jointInfo, PartInfo partInfo)
	{
		partInfo.setNewRotateX(partInfo.getNewRotateX() - jointInfo.getRotateChangeX());
		partInfo.setNewRotateY(partInfo.getNewRotateY() - jointInfo.getRotateChangeY());
		partInfo.setNewRotateZ(partInfo.getNewRotateZ() - jointInfo.getRotateChangeZ());
	}

	public static void reverseJointRotatesChangeWithDampener(PartInfo jointInfo, PartInfo partInfo, float dampener)
	{
		partInfo.setNewRotateX(partInfo.getNewRotateX() - jointInfo.getRotateChangeX() * dampener);
		partInfo.setNewRotateY(partInfo.getNewRotateY() - jointInfo.getRotateChangeY() * dampener);
		partInfo.setNewRotateZ(partInfo.getNewRotateZ() - jointInfo.getRotateChangeZ() * dampener);
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