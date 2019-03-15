package fuzzyacornindustries.kindredlegacy.item;

import java.util.List;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.item.tamable.IPowerUp;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRegenCream extends ItemBase
{
	public ItemRegenCream(String name)
	{
		super(name);
	}

	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add("A cream that increases");
		tooltip.add("Poketamable regernation");
		tooltip.add("a several minutes.");
	}
}