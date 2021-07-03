package fuzzyacornindustries.kindredlegacy.common.item;

import java.util.List;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.KindredLegacy;
import fuzzyacornindustries.kindredlegacy.common.core.ModItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PokemonExplorationKitItem extends Item
{
	public PokemonExplorationKitItem()
	{
		super(ModItems.defaultUnstackableProps());
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand hand)
	{
		ItemStack itemStack = playerIn.getHeldItem(hand);

		if (worldIn.isRemote)
		{
			KindredLegacy.proxy.displayExplorationKitGui();
		}

		return new ActionResult<ItemStack>(ActionResultType.PASS, itemStack);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(new StringTextComponent("A kit for starting a Pokemon"));
		tooltip.add(new StringTextComponent("adventure! Open to receive a"));
		tooltip.add(new StringTextComponent("Poketamable and items and"));
		tooltip.add(new StringTextComponent("berries to care for it."));
	}
}