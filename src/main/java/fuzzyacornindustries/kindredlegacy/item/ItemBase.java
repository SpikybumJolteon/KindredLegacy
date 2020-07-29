package fuzzyacornindustries.kindredlegacy.item;

import fuzzyacornindustries.kindredlegacy.lists.KindredLegacyItems;
import net.minecraft.item.Item;

public class ItemBase extends Item
{
	public ItemBase(Item.Properties properties)
	{
		super(properties);
		
		KindredLegacyItems.ITEMS.add(this);
	}
}