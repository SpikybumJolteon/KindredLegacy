package fuzzyacornindustries.kindredlegacy.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PotionPowderItem extends ItemBase
{
	public String potionRecipe;
	
	public PotionPowderItem(Properties properties, String potionForRecipe)
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