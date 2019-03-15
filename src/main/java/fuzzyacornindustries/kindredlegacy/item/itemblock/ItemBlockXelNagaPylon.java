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

public class ItemBlockXelNagaPylon extends ItemBlock 
{
	public ItemBlockXelNagaPylon(final Block block)
	{
		super(block);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(final ItemStack stack, @Nullable final World world, final List<String> tooltip, final ITooltipFlag advanced) 
	{
		tooltip.add("A block that supplies power");
		tooltip.add("to nearby Mystic Tech blocks");
	}
}