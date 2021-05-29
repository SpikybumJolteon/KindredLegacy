package fuzzyacornindustries.kindredlegacy.common.item;

import net.minecraft.item.Item;

public class BerryItem extends Item
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