package fuzzyacornindustries.kindredlegacy.common.item;

import java.util.function.Function;

import fuzzyacornindustries.kindredlegacy.common.core.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext.FluidMode;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ModSpawnEggItem extends Item
{
	private final Function<World, CreatureEntity> mobCreator;

	public ModSpawnEggItem(Function<World, CreatureEntity> mobCreator)
	{
		super(ModItems.defaultProps());

		this.mobCreator = mobCreator;
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) 
	{
		ItemStack itemstack = context.getPlayer().getHeldItem(context.getHand());

		if (context.getWorld().isRemote)
		{
			return ActionResultType.SUCCESS;
		}
		else if (!context.getPlayer().canPlayerEdit(context.getPos().offset(context.getFace()), context.getFace(), itemstack))
		{
			return ActionResultType.FAIL;
		}
		else
		{
			BlockState iblockstate = context.getWorld().getBlockState(context.getPos());

			BlockPos pos = context.getPos().offset(context.getFace());
			double d0 = 0.0D;

			if (context.getFace() == Direction.UP && iblockstate.getBlock() instanceof FenceBlock) //Forge: Fix Vanilla bug comparing state instead of block
			{
				d0 = 0.5D;
			}

			Entity entity = spawnEntity(context.getWorld(), context.getPlayer(), itemstack, (double)pos.getX() + 0.5D, (double)pos.getY() + d0, (double)pos.getZ() + 0.5D);

			if (entity != null)
			{
				if (!context.getPlayer().abilities.isCreativeMode)
				{
					itemstack.shrink(1); // Decrease itemStack by 1
				}
			}

			return ActionResultType.SUCCESS;
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand hand)
	{
		ItemStack itemStack = playerIn.getHeldItem(hand);

		RayTraceResult raytraceresult = ModSpawnEggItem.rayTrace(worldIn, playerIn, FluidMode.ANY);

		if (raytraceresult != null && raytraceresult.getType() == RayTraceResult.Type.BLOCK)
		{
			BlockPos blockpos = new BlockPos(raytraceresult.getHitVec());

			if (!(worldIn.getBlockState(blockpos).getBlock() instanceof IFluidState))
			{
				return new ActionResult<ItemStack>(ActionResultType.PASS, itemStack);
			}
			else if (worldIn.isBlockModifiable(playerIn, blockpos))// && playerIn.canPlayerEdit(blockpos, BlockMode.OUTLINE, itemStack))
			{
				Entity entity = spawnEntity(worldIn, playerIn, itemStack, (double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.5D, (double)blockpos.getZ() + 0.5D);

				if (entity == null)
				{
					return new ActionResult<ItemStack>(ActionResultType.PASS, itemStack);
				}
				else
				{
					if (entity instanceof LivingEntity && itemStack.hasDisplayName())
					{
						((LivingEntity)entity).setCustomName(itemStack.getDisplayName());
					}

					itemStack.shrink(1); // Decrease itemStack by 1

					return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemStack);
				}
			}
			else
			{
				return new ActionResult<ItemStack>(ActionResultType.FAIL, itemStack);
			}
		}
		else
		{
			return new ActionResult<ItemStack>(ActionResultType.PASS, itemStack);
		}
	}

	public CreatureEntity spawnEntity(World parWorld, PlayerEntity parPlayer, ItemStack par1ItemStack, double parX, double parY, double parZ)
	{
		CreatureEntity entityToSpawn = mobCreator.apply(parWorld);

		if (!parWorld.isRemote)
		{
			entityToSpawn.setLocationAndAngles(parX, parY, parZ, MathHelper.wrapDegrees(parWorld.rand.nextFloat() * 360.0F), 0.0F);
			entityToSpawn.rotationYawHead = entityToSpawn.rotationYaw;
			entityToSpawn.renderYawOffset = entityToSpawn.rotationYaw;
			parWorld.addEntity(entityToSpawn);
			entityToSpawn.playAmbientSound();
		}

		return entityToSpawn;
	}

	@Override
	public boolean getIsRepairable(ItemStack batStack, ItemStack repairStack)
	{
		return false;
	}
}