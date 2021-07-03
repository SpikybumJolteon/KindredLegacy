package fuzzyacornindustries.kindredlegacy.common.item;

import java.util.List;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.common.core.ModItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class VoidScissorsItem extends Item
{
	public VoidScissorsItem() 
	{
		super(ModItems.defaultUnstackableProps().maxDamage(128));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(new StringTextComponent("A recipe tool used to cut certain vanilla"));
		tooltip.add(new StringTextComponent("items down to their raw materials;"));
		tooltip.add(new StringTextComponent("some material is lost in the process."));
		//tooltip.add(new StringTextComponent("Items must be at full durability."));
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack)
	{
		ItemStack copiedStack = itemStack.copy();

		copiedStack.setDamage(copiedStack.getDamage() + 1);
		copiedStack.setCount(1);

		return copiedStack;
	}

	@Override
	public boolean hasContainerItem()
	{
		return true;
	}
}