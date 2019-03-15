package fuzzyacornindustries.kindredlegacy.item.itemblock;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBlockVespeneCondenser extends ItemBlock 
{
	public ItemBlockVespeneCondenser(final Block block)
	{
		super(block);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(final ItemStack stack, @Nullable final World world, final List<String> tooltip, final ITooltipFlag advanced) 
	{
		tooltip.add("A Mystic Tech block that creates");
		tooltip.add("Tiberium and Vespene Fuel items.");
		tooltip.add("Requires nearby pylon to run.");
	}
}