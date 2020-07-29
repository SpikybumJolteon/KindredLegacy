package fuzzyacornindustries.kindredlegacy.item.tamable;

import java.util.List;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.item.ItemBase;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class QuakagaEssenceItem extends ItemBase implements IEssenceItem
{
	public QuakagaEssenceItem(Properties properties)
	{
		super(properties);
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