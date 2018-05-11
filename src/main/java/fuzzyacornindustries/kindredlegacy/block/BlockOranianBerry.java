package fuzzyacornindustries.kindredlegacy.block;

import java.util.Random;

import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockOranianBerry extends BlockCropsBase
{
	public BlockOranianBerry(String name) 
	{
		super(name);
	}

	private static final AxisAlignedBB[] ORANIAN_BERRY_AABB = new AxisAlignedBB[] {new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.1875D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.25D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.3125D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.4375D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5625D, 1.0D)};

    @Override
	protected Item getSeed()
	{
		return KindredLegacyItems.ORANIAN_BERRY;
	}

    @Override
	protected Item getCrop()
	{
		return KindredLegacyItems.ORANIAN_BERRY;
	}

    @Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return ORANIAN_BERRY_AABB[((Integer)state.getValue(this.getAgeProperty())).intValue()];
	}

	@Override
	public java.util.List<ItemStack> getDrops(net.minecraft.world.IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
	{
		java.util.List<ItemStack> ret = super.getDrops(world, pos, state, fortune);
		int age = getAge(state);
		Random rand = world instanceof World ? ((World)world).rand : new Random();

		if (age >= getMaxAge() && rand.nextInt(20) == 0)
		{
			ret.add(new ItemStack(KindredLegacyItems.REVIVE_SEED, 1, 0));
		}

		return ret;
	}

	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
	{
		super.dropBlockAsItemWithChance(worldIn, pos, state, chance, 0);

		if (!worldIn.isRemote) // Forge: NOP all this.
		{
			int i = this.getAge(state);

			if (i >= this.getMaxAge() && worldIn.rand.nextInt(20) == 0)
			{
				spawnAsEntity(worldIn, pos, new ItemStack(KindredLegacyItems.REVIVE_SEED));
			}
		}
	}
}