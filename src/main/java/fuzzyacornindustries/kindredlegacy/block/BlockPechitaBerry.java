package fuzzyacornindustries.kindredlegacy.block;

import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockPechitaBerry extends BlockCropsBase
{
    public BlockPechitaBerry(String name) 
    {
		super(name);
	}

	private static final AxisAlignedBB[] PECHITA_BERRY_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.4375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D)};

    @Override
    protected Item getSeed()
    {
        return KindredLegacyItems.PECHITA_BERRY;
    }

    @Override
    protected Item getCrop()
    {
        return KindredLegacyItems.PECHITA_BERRY;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return PECHITA_BERRY_AABB[((Integer)state.getValue(this.getAgeProperty())).intValue()];
    }
}