package fuzzyacornindustries.kindredlegacy.common.item;

import java.util.List;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.common.core.ModBlocks;
import fuzzyacornindustries.kindredlegacy.common.core.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IPlantable;

public class OranianBerryItem extends BerryItem implements IPlantable
{	
	public OranianBerryItem(float pokemonHealAmount)
	{
		super(ModItems.defaultProps().food(ModItems.ORANIAN_BERRY_FOOD), pokemonHealAmount);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(new StringTextComponent("A berry food item that"));
		tooltip.add(new StringTextComponent("heals health. Can be given"));
		tooltip.add(new StringTextComponent("to Poketamable as well."));
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) 
	{
		if (this.isFood())
		{
			if (entityLiving.getHealth() < entityLiving.getMaxHealth() && entityLiving.getHealth() > 0);
			{
				entityLiving.heal(1F);

				entityLiving.onFoodEaten(worldIn, stack);
			}
		}
		return stack;
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) 
	{
		ItemStack stack = context.getPlayer().getHeldItem(context.getHand());
		BlockState state = context.getWorld().getBlockState(context.getPos());

		if(context.getFace() == Direction.UP && context.getPlayer().canPlayerEdit(context.getPos().offset(context.getFace()), context.getFace(), stack) && state.getBlock()== Blocks.FARMLAND && context.getWorld().isAirBlock(context.getPos().up()))
		{
			context.getWorld().setBlockState(context.getPos().up(), ModBlocks.ORANIAN_BERRIES.get().getDefaultState());
			stack.shrink(1);

			return ActionResultType.SUCCESS;
		}
		else
		{
			return ActionResultType.FAIL;
		}
	}

	@Override
	public BlockState getPlant(IBlockReader world, BlockPos pos) 
	{
		return ModBlocks.ORANIAN_BERRIES.get().getDefaultState();
	}
}