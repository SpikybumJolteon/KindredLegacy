package fuzzyacornindustries.kindredlegacy.common.block;

import java.util.Iterator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ContainerBlock;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.inventory.container.Container;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class MysticTechBlock  extends ContainerBlock implements IPylonPowered
{
	public boolean isPowered;

	public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;

	protected MysticTechBlock(Properties properties) 
	{
		super(properties);

	}

	@Override
	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) 
	{
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getLightValue(BlockState state) 
	{
		return super.getLightValue(state);
	}

	@Override
	public boolean hasComparatorInputOverride(BlockState state) 
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) 
	{
		return Container.calcRedstone(worldIn.getTileEntity(pos));
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) 
	{
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}
	
	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) 
	{
		return null;
	}

	@Override
	public boolean hasTileEntity(BlockState state)
	{
		return true;
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(LIT);
	}

	public static boolean isPylonNearby(World world, BlockPos pos)
	{
		Iterator<BlockPos> iterator = BlockPos.getAllInBoxMutable(pos.add(-4, -4, -4), pos.add(4, 4, 4)).iterator();
		BlockPos.Mutable mutableblockpos;

		do
		{
			do
			{
				if (!iterator.hasNext())
				{
					return false;
				}

				mutableblockpos = (BlockPos.Mutable)iterator.next();
			}
			while(!(world.getBlockState(mutableblockpos).getBlock() instanceof XelNagaPylonBlock));
		}
		while(world.isBlockPowered(mutableblockpos));

		return true;
	}

	@Override
	public void setPylonPoweredState(Boolean isPylonPowered)
	{
		isPowered = isPylonPowered;
	}

	@Override
	public Boolean getPylonPoweredState() 
	{
		return isPowered;
	}
}