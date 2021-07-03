package fuzzyacornindustries.kindredlegacy.common.entity.mob;

public class SpawnInfo
{
	protected static int rate;
	protected static int min;
	protected static int max;
	
	public SpawnInfo(int rate, int min, int max)
	{
		SpawnInfo.rate = rate;
		SpawnInfo.min = min;
		SpawnInfo.max = max;
	}
	
	public int getRate()
	{
		return SpawnInfo.rate;
	}
	
	public int getMin()
	{
		return SpawnInfo.min;
	}
	
	public int getMax()
	{
		return SpawnInfo.max;
	}
}