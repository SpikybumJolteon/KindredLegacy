package fuzzyacornindustries.kindredlegacy.block;

import java.util.Random;

import fuzzyacornindustries.kindredlegacy.tileentity.TileEntityXelNagaPylon;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockXelNagaPylon extends BlockBase
{
	public BlockXelNagaPylon()
	{
		super("xelnaga_pylon", Material.ROCK);
		
		this.setHardness(2.0F);
		this.setResistance(10.0F);
		this.setLightLevel(0.5F);
	}

	@Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	
	@Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		float xx = (float) pos.getX() + 0.5F, yy = (float) pos.getY() + rand.nextFloat() * 6.0F / 16.0F, zz = (float) pos.getZ() + 0.5F, xx2 = rand.nextFloat() * 0.3F - 0.2F, zz2 = 0.5F;
		
		worldIn.spawnParticle(EnumParticleTypes.SPELL_INSTANT, (double) (xx), (double) yy, (double) (zz), 0.0F, 0.0F, 0.0F);
	}
	
	@Override
    public boolean hasTileEntity(IBlockState state)
    {
    	return true;
    }
    
	@Override
    public TileEntity createTileEntity(World world, IBlockState state)
	{
		return new TileEntityXelNagaPylon();
	}
}