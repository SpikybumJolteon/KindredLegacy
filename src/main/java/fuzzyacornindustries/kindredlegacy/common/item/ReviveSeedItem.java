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

public class ReviveSeedItem extends Item
{
	public ReviveSeedItem()
	{
		super(ModItems.defaultProps());
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(new StringTextComponent("Toss onto a Poketamable summon"));
		tooltip.add(new StringTextComponent("item on the ground in order to"));
		tooltip.add(new StringTextComponent("revive it."));
	}
}