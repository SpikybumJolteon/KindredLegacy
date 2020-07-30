package fuzzyacornindustries.kindredlegacy.item.tamable;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GravigaEssenceItem extends Item implements IEssenceItem
{
	public GravigaEssenceItem(Item.Properties properties)
	{
		super(properties);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(new StringTextComponent("Right click a Poketamable"));
		tooltip.add(new StringTextComponent("to permanently add"));
		tooltip.add(new StringTextComponent("fall damage immunity."));
	}
}