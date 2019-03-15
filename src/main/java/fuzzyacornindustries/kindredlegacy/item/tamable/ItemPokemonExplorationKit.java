package fuzzyacornindustries.kindredlegacy.item.tamable;

import java.util.List;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.TamablePokemon;
import fuzzyacornindustries.kindredlegacy.item.ItemBase;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPokemonExplorationKit extends ItemBase
{
	public ItemPokemonExplorationKit(String name)
	{
		super(name);

		this.maxStackSize = 1;
	}
	/*
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack itemstack = playerIn.getHeldItem(hand);

		if (worldIn.isRemote)
		{
			return EnumActionResult.SUCCESS;
		}
		else if (!playerIn.canPlayerEdit(pos.offset(facing), facing, itemstack))
		{
			return EnumActionResult.FAIL;
		}
		else
		{
			if(itemstack.getItemDamage()  < itemstack.getMaxDamage())
			{
				IBlockState iblockstate = worldIn.getBlockState(pos);

				pos = pos.offset(facing);
				double d0 = 0.0D;

				if (facing == EnumFacing.UP && iblockstate.getBlock() instanceof BlockFence) //Forge: Fix Vanilla bug comparing state instead of block
				{
					d0 = 0.5D;
				}

				Entity entity = spawnEntity(worldIn, playerIn, itemstack, (double)pos.getX() + 0.5D, (double)pos.getY() + d0, (double)pos.getZ() + 0.5D);

				//System.out.println( "Test entity summon run, General Class." );
				//System.out.println( "Entity is not null:" + Boolean.toString(entity != null) );

				if (entity != null)
				{
					if (!playerIn.capabilities.isCreativeMode)
					{
						itemstack.shrink(1); // Decrease itemStack by 1
					}
				}
			}

			return EnumActionResult.SUCCESS;
		}
	}
	 */

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
	{
		ItemStack itemStack = playerIn.getHeldItem(hand);

		if (worldIn.isRemote)
		{
			KindredLegacyMain.proxy.displayPokemonExplorationKitGui(itemStack);
		}

		return new ActionResult(EnumActionResult.PASS, itemStack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add("A kit for starting a Pokemon");
		tooltip.add("adventure! Open to receive a");
		tooltip.add("Poketamable and items and");
		tooltip.add("berries to care for it.");
	}
}