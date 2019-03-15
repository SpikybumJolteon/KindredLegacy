package fuzzyacornindustries.kindredlegacy.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRichTiberiumShard extends ItemBase implements ITiberiumShard
{
	public ItemRichTiberiumShard(String name)
	{
		super(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add("A more potent form of tiberium,");
		tooltip.add("it allows for greater technology.");
		tooltip.add("It's more toxic to terran creatures.");
	}
}