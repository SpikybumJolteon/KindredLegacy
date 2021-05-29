package fuzzyacornindustries.kindredlegacy.common.item;

import java.util.List;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.common.core.ModItems;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.HostilePokemon;
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

public class PokeraserItem extends Item
{
	public PokeraserItem()
	{
		super(ModItems.defaultUnstackableProps());
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(new StringTextComponent("A creative mode item for removing"));
		tooltip.add(new StringTextComponent("Pokemobs from this mod; won't remove"));
		tooltip.add(new StringTextComponent("other player's Poketamables."));
	}

	@Override
	public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker)
	{
		if(target instanceof HostilePokemon)
		{
			((LivingEntity)target).remove();
		}
		else if(target instanceof TamablePokemon && attacker instanceof PlayerEntity)
		{
			if(((TamablePokemon)target).isTamed() && !((TamablePokemon)target).isOwner((PlayerEntity)attacker))
			{
				return true;
			}

			((LivingEntity)target).remove();
		}

		return true;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand)
	{
		if (target.world.isRemote)
		{
			return false;
		}
		else if(target instanceof HostilePokemon)// || target instanceof EntitySubstituteDoll)
		{
			((LivingEntity)target).remove();
		}
		else if(target instanceof TamablePokemon)
		{
			if(((TamablePokemon)target).isTamed() && !((TamablePokemon)target).isOwner(playerIn))
			{
				return true;
			}

			((LivingEntity)target).remove();
		}

		return false;
	}
}