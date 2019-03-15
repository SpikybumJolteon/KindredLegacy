package fuzzyacornindustries.kindredlegacy.block;

import java.util.Iterator;
import java.util.Random;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import fuzzyacornindustries.kindredlegacy.tileentity.TileEntityVespeneCondenser;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockVespeneCondenser extends BlockBase implements IPylonPowered
{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool ACTIVE = PropertyBool.create("active");

	public static boolean isPowered;

	public BlockVespeneCondenser()
	{
		super("vespene_condenser", Material.ROCK);

		this.setSoundType(SoundType.STONE);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVE, false));
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
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(KindredLegacyBlocks.VESPENE_CONDENSER);
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		return new ItemStack(KindredLegacyBlocks.VESPENE_CONDENSER);
	}

	@Override 
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(!worldIn.isRemote)
		{
			playerIn.openGui(KindredLegacyMain.instance, ModInfo.GUI_VESPENE_CONDENSER, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}

		return true;
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		if(!worldIn.isRemote) 
		{
			IBlockState north = worldIn.getBlockState(pos.north());
			IBlockState south = worldIn.getBlockState(pos.south());
			IBlockState west = worldIn.getBlockState(pos.west());
			IBlockState east = worldIn.getBlockState(pos.east());
			EnumFacing face = (EnumFacing)state.getValue(FACING);

			if (face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.SOUTH;
			else if (face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) face = EnumFacing.NORTH;
			else if (face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) face = EnumFacing.EAST;
			else if (face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) face = EnumFacing.WEST;
			worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
		}
	}

	public static void setState(boolean active, World worldIn, BlockPos pos) 
	{
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileEntity = worldIn.getTileEntity(pos);

		if(active) worldIn.setBlockState(pos, KindredLegacyBlocks.VESPENE_CONDENSER.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(ACTIVE, true), 3);
		else worldIn.setBlockState(pos, KindredLegacyBlocks.VESPENE_CONDENSER.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(ACTIVE, false), 3);

		if(tileEntity != null) 
		{
			tileEntity.validate();
			worldIn.setTileEntity(pos, tileEntity);
		}
	}

	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state)
	{
		return new TileEntityVespeneCondenser();
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) 
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) 
	{
		worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) 
	{
		TileEntityVespeneCondenser tileEntity = (TileEntityVespeneCondenser)worldIn.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(worldIn, pos, tileEntity);
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot)
	{
		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) 
	{
		return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[] {ACTIVE, FACING});
	}

	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		EnumFacing facing = EnumFacing.getFront(meta);
		if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
		return this.getDefaultState().withProperty(FACING, facing);
	}

	@Override
	public int getMetaFromState(IBlockState state) 
	{
		return ((EnumFacing)state.getValue(FACING)).getIndex();
	} 
	/*
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		//if(worldIn.getTileEntity(pos) instanceof TileEntityVespeneCondenser)
		{
			//TileEntityVespeneCondenser tileentity = (TileEntityVespeneCondenser)worldIn.getTileEntity(pos);

			//if(tileentity.isProcessing()) 
			if(worldIn.getBlockState(pos).getValue(ACTIVE))//withProperty(FACING, state.getValue(FACING)).withProperty(ACTIVE, false), 3))
			{
				float xx = (float) pos.getX() + rand.nextFloat() * 12.0F / 16.0F + 0.25F, yy = (float) pos.getY() + rand.nextFloat() * 12.0F / 16.0F, zz = (float) pos.getZ() + rand.nextFloat() * 12.0F / 16.0F + 0.25F;

				worldIn.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, (double) (xx), (double) yy, (double) (zz), 0.0F, 0.0F, 0.0F);
			}
		}
	}*/

	public static boolean isPylonNearby(World world, BlockPos pos)
	{
		Iterator iterator = BlockPos.getAllInBoxMutable(pos.add(-4, -4, -4), pos.add(4, 4, 4)).iterator();
		BlockPos.MutableBlockPos mutableblockpos;

		do
		{
			if (!iterator.hasNext())
			{
				return false;
			}

			mutableblockpos = (BlockPos.MutableBlockPos)iterator.next();
		}
		while (world.getBlockState(mutableblockpos).getBlock() != KindredLegacyBlocks.XELNAGA_PYLON);

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