package fuzzyacornindustries.kindredlegacy.item;

import java.util.List;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.creativetab.KindredLegacyCreativeTabs;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemVoidScissors extends ItemBase
{
	public ItemVoidScissors(String name)
	{
		super(name);
		
		this.maxStackSize = 1;

		setMaxDamage(128);
	}

	@SideOnly(Side.CLIENT)
	@Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add("A recipe tool used to cut certain vanilla");
		tooltip.add("items down to their raw materials;");
		tooltip.add("some material is lost in the process.");
	}

	@Override
	public ItemStack getContainerItem(ItemStack itemStack)
	{
		ItemStack copiedStack = itemStack.copy();

		copiedStack.setItemDamage(copiedStack.getItemDamage() + 1);
		copiedStack.setCount(1);

		return copiedStack;
	}

	@Override
	public boolean hasContainerItem()
	{
		return true;
	}
}