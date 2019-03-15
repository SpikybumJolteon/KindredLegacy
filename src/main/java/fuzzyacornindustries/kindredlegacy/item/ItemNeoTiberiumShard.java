package fuzzyacornindustries.kindredlegacy.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemNeoTiberiumShard extends ItemBase implements ITiberiumShard
{
	public ItemNeoTiberiumShard(String name)
	{
		super(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add("The highest grade of tiberium,");
		tooltip.add("utilized in the most advanced mystic");
		tooltip.add("tech; lethal to terran creatures.");
	}
}