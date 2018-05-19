package fuzzyacornindustries.kindredlegacy.item;

import java.util.List;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.creativetab.KindredLegacyCreativeTabs;
import fuzzyacornindustries.kindredlegacy.entity.ability.EntitySubstituteDoll;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSubstituteDoll extends ItemBase
{
	protected EntitySubstituteDoll entityToSpawn = null;

	public ItemSubstituteDoll(String name)
	{
		super(name);
	}

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
			IBlockState iblockstate = worldIn.getBlockState(pos);

			pos = pos.offset(facing);
			double d0 = 0.0D;

			if (facing == EnumFacing.UP && iblockstate.getBlock() instanceof BlockFence) //Forge: Fix Vanilla bug comparing state instead of block
			{
				d0 = 0.5D;
			}

			Entity entity = spawnEntity(worldIn, itemstack, (double)pos.getX() + 0.5D, (double)pos.getY() + d0, (double)pos.getZ() + 0.5D);

			if (entity != null)
			{
				if (!playerIn.capabilities.isCreativeMode)
				{
					itemstack.shrink(1); // Decrease itemStack by 1
				}
			}

			return EnumActionResult.SUCCESS;
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
	{
		ItemStack itemStack = playerIn.getHeldItem(hand);

		RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);

		if (raytraceresult != null && raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK)
		{
			BlockPos blockpos = raytraceresult.getBlockPos();

			if (!(worldIn.getBlockState(blockpos).getBlock() instanceof BlockLiquid))
			{
				return new ActionResult(EnumActionResult.PASS, itemStack);
			}
			else if (worldIn.isBlockModifiable(playerIn, blockpos) && playerIn.canPlayerEdit(blockpos, raytraceresult.sideHit, itemStack))
			{
				Entity entity = spawnEntity(worldIn, itemStack, (double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.5D, (double)blockpos.getZ() + 0.5D);

				if (entity == null)
				{
					return new ActionResult(EnumActionResult.PASS, itemStack);
				}
				else
				{
					itemStack.shrink(1); // Decrease itemStack by 1

					playerIn.addStat(StatList.getObjectUseStats(this));
					return new ActionResult(EnumActionResult.SUCCESS, itemStack);
				}
			}
			else
			{
				return new ActionResult(EnumActionResult.FAIL, itemStack);
			}
		}
		else
		{
			return new ActionResult(EnumActionResult.PASS, itemStack);
		}
	}

	public Entity spawnEntity(World parWorld, ItemStack par1ItemStack, double parX, double parY, double parZ)
	{
		if (!parWorld.isRemote)
		{
			entityToSpawn = new EntitySubstituteDoll(parWorld);

			entityToSpawn.setLocationAndAngles(parX, parY, parZ, MathHelper.wrapDegrees(parWorld.rand.nextFloat() * 360.0F), 0.0F);
			entityToSpawn.rotationYawHead = entityToSpawn.rotationYaw;
			entityToSpawn.renderYawOffset = entityToSpawn.rotationYaw;
			parWorld.spawnEntity(entityToSpawn);
		}

		return entityToSpawn;
	}

	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add("Creative mode 'mob' for Pokemobs");
		tooltip.add("at attack.");
	}
}