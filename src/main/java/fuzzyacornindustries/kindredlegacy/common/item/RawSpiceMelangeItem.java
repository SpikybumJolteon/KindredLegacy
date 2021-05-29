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

public class RawSpiceMelangeItem extends Item implements IPowerUp
{
	public RawSpiceMelangeItem()
	{
		super(ModItems.defaultProps());
	}

	@Override
	@OnlyIn(Dist.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(new StringTextComponent("A substance that heightens"));
		tooltip.add(new StringTextComponent("senses for Poketamables; too"));
		tooltip.add(new StringTextComponent("potent for player use."));
	}
}