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

public class VerdantizerItem extends Item
{
	public VerdantizerItem() 
	{
		super(ModItems.defaultUnstackableProps());
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(new StringTextComponent("A mystic tech item that converts"));
		tooltip.add(new StringTextComponent("certain plant materials into dust"));
		tooltip.add(new StringTextComponent("for verdancy crafting."));
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack)
	{
		ItemStack copiedStack = itemStack.copy();

		copiedStack.setCount(1);

		return copiedStack;
	}

	@Override
	public boolean hasContainerItem()
	{
		return true;
	}
}