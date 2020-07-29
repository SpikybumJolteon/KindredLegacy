package fuzzyacornindustries.kindredlegacy.item.tamable;

import fuzzyacornindustries.kindredlegacy.item.ItemBase;
import net.minecraft.item.Item;

public class BerryItem extends ItemBase
{
	private float poketamableHealAmount;

	public BerryItem(Item.Properties properties, float pokemonHealAmount) 
	{
		super(properties);

		poketamableHealAmount = pokemonHealAmount;
	}
	
	public float getPokemonHealAmount()
	{
		return poketamableHealAmount;
	}
}