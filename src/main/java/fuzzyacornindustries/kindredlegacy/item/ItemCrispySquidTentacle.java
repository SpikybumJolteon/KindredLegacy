package fuzzyacornindustries.kindredlegacy.item;

import fuzzyacornindustries.kindredlegacy.creativetab.KindredLegacyCreativeTabs;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.item.ItemFood;

public class ItemCrispySquidTentacle extends ItemFoodBase
{	
	static int healAmount = 6;
	static float saturationValue = 0.6F;
	
	public ItemCrispySquidTentacle(String name)
	{
		super(name, healAmount, saturationValue);
	}
}