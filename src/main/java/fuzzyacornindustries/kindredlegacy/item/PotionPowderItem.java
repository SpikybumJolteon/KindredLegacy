package fuzzyacornindustries.kindredlegacy.item;

import net.minecraft.item.Item;

public class PotionPowderItem extends Item
{
	public String potionRecipe;
	
	public PotionPowderItem(Item.Properties properties, String potionForRecipe)
	{
		super(properties);
		
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