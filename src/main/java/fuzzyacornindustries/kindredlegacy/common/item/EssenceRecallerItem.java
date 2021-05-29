package fuzzyacornindustries.kindredlegacy.common.item;

import java.util.List;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.common.core.ModItems;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.TamablePokemon;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EssenceRecallerItem extends Item
{
	public EssenceRecallerItem()
	{
		super(ModItems.defaultUnstackableProps());
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(new StringTextComponent("An item to return your own Poketamable"));
		tooltip.add(new StringTextComponent("to their summon item form; will not work"));
		tooltip.add(new StringTextComponent("on other players' or ones spawned with eggs."));
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand)
	{
		if (target.world.isRemote)
		{
			return false;
		}
		else if(target instanceof TamablePokemon && ((TamablePokemon)target).isOwner(playerIn))
		{
			((TamablePokemon)target).returnToItem();

			playerIn.container.detectAndSendChanges();
		}

		return false;
	}
}