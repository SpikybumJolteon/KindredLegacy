package fuzzyacornindustries.kindredlegacy.common.block;

import java.util.Random;

import fuzzyacornindustries.kindredlegacy.common.tileentity.XelNagaPylonTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
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
	public XelNagaPylonBlock()
	{
		super(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(5.0F, 1200.0F).notSolid().lightValue(10));
	}

	@Override
	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) 
	{
		return false;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		if(!worldIn.isBlockPowered(pos))
		{
			float xx = (float) pos.getX() + 0.5F, yy = (float) pos.getY() + rand.nextFloat() * 6.0F / 16.0F, zz = (float) pos.getZ() + 0.5F;

			worldIn.addParticle(ParticleTypes.INSTANT_EFFECT, (double) (xx), (double) yy, (double) (zz), 0.0F, 0.0F, 0.0F);
		}
	}

	@Override
	public boolean hasTileEntity(BlockState state)
	{
		return true;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world)
	{
		try {
			return XelNagaPylonTileEntity.class.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) 
	{
		return BlockRenderType.ENTITYBLOCK_ANIMATED;
	}
}