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

public class QuakagaEssenceItem extends Item implements IEssenceItem
{
	public QuakagaEssenceItem()
	{
		super(ModItems.defaultProps());
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(new StringTextComponent("Right click a Poketamable"));
		tooltip.add(new StringTextComponent("to permanently add an ability"));
		tooltip.add(new StringTextComponent("to try and escape sufficating"));
		tooltip.add(new StringTextComponent("in a block by teleporting"));
		tooltip.add(new StringTextComponent("to its owner."));
	}
}