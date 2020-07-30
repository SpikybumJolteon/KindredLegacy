package fuzzyacornindustries.kindredlegacy.block;

import java.util.Random;

import fuzzyacornindustries.kindredlegacy.tileentity.XelNagaPylonTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class XelNagaPylonBlock extends Block
{
	public XelNagaPylonBlock(Block.Properties properties)
	{
		super(properties);
	}
	//
	//	@Override
	//    public boolean isFullCube(IBlockState state)
	//    {
	//        return false;
	//    }
	//	
	//	@Override
	//    public boolean isOpaqueCube(IBlockState state)
	//    {
	//        return false;
	//    }
//
//	@Override
//	public boolean isTransparent(BlockState state) 
//	{
//		return true;
//	}
//
//	@Override
//	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) 
//	{
//		return false;
//	}
	//	
	//	@Override
	//	public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) 
	//	{
	//		return 0;
	//	}

//	//	
//	@Override
//	public boolean isVariableOpacity()
//	{
//		return false;
//	}
	//
	//	@Override
	//	public boolean getRenderLayer()
	//	{
	//		
	//	}

	@Override
	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) 
	{
		return false;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		float xx = (float) pos.getX() + 0.5F, yy = (float) pos.getY() + rand.nextFloat() * 6.0F / 16.0F, zz = (float) pos.getZ() + 0.5F;

		worldIn.addParticle(ParticleTypes.INSTANT_EFFECT, (double) (xx), (double) yy, (double) (zz), 0.0F, 0.0F, 0.0F);
	}

	@Override
	public boolean hasTileEntity(BlockState state)
	{
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world)
	{
		//this.shouldSideBeRendered(adjacentState, blockState, blockAccess, pos);
		//this.isSideInvisible(state, adjacentBlockState, side)
		return new XelNagaPylonTileEntity();
	}
//
//	@Override
//	@OnlyIn(Dist.CLIENT)
//	public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) 
//	{
//		return true;
//	}

	@Override
	public BlockRenderType getRenderType(BlockState state) 
	{
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}
}