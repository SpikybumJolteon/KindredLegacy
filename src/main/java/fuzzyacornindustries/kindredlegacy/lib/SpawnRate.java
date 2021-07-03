package fuzzyacornindustries.kindredlegacy.lib;

import java.util.HashMap;

import fuzzyacornindustries.kindredlegacy.common.entity.mob.SpawnInfo;
import net.minecraft.entity.EntityType;

public class SpawnRate 
{
	private HashMap<EntityType<?>, SpawnInfo> spawnInfoMap;

	public SpawnRate()
	{
		spawnInfoMap = new HashMap<EntityType<?>, SpawnInfo>();
	}

	public void setInfo(EntityType<?> type, int rate, int min, int max)
	{
		if(!spawnInfoMap.containsKey(type)) 
		{
			spawnInfoMap.put(type, new SpawnInfo(rate, min, max));
		}
	}
	
	public SpawnInfo getInfo(EntityType<?> type)
	{
		if(spawnInfoMap.containsKey(type)) 
		{
			return spawnInfoMap.get(type);
		}

		return null;
	}

	public int getRate(EntityType<?> type)
	{
		if(spawnInfoMap.containsKey(type)) 
		{
			return spawnInfoMap.get(type).getRate();
		}

		return 0;
	}

	public int getMin(EntityType<?> type)
	{
		if(spawnInfoMap.containsKey(type)) 
		{
			return spawnInfoMap.get(type).getMin();
		}

		return 0;
	}

	public int getMax(EntityType<?> type)
	{
		if(spawnInfoMap.containsKey(type)) 
		{
			return spawnInfoMap.get(type).getMax();
		}

		return 0;
	}
}