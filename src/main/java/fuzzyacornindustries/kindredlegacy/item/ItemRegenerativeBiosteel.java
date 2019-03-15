package fuzzyacornindustries.kindredlegacy.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRegenerativeBiosteel extends ItemBase
{
	public ItemRegenerativeBiosteel(String name)
	{
		super(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add("Iron that has been made to be");
		tooltip.add("like living flesh by being");
		tooltip.add("infested by Pokezerg biology.");
	}
}