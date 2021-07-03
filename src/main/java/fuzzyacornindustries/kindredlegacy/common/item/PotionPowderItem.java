package fuzzyacornindustries.kindredlegacy.common.item;

import fuzzyacornindustries.kindredlegacy.common.core.ModItems;
import net.minecraft.item.Item;

public class PotionPowderItem extends Item
{
	public String potionRecipe;
	
	public PotionPowderItem(String potionForRecipe)
	{
		super(ModItems.defaultProps());
		
		this.potionRecipe = potionForRecipe;
	}

//	@Override
//	@OnlyIn(Dist.CLIENT)
//	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
//	{
//		tooltip.add(new StringTextComponent("Crafted with Verdantizer and"));
//		tooltip.add(new StringTextComponent("Potion of " + potionRecipe + "."));
//	}
}