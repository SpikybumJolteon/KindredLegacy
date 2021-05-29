package fuzzyacornindustries.kindredlegacy.common.block;

import java.util.Random;

import fuzzyacornindustries.kindredlegacy.common.tileentity.VerdantPurifierTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class VerdantPurifierBlock extends MysticTechBlock implements IPylonPowered
{
	public VerdantPurifierBlock()
	{
		super(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(10F).notSolid().lightValue(10));

		this.setDefaultState(this.stateContainer.getBaseState().with(LIT, Boolean.valueOf(false)));
	}

	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) 
	{
		try {
			return VerdantPurifierTileEntity.class.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) 
	{
		try {
			return VerdantPurifierTileEntity.class.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult p_225533_6_) 
	{
		TileEntity tileentity = worldIn.getTileEntity(pos);
		if (tileentity instanceof VerdantPurifierTileEntity) 
		{
			if (!worldIn.isRemote)
			{
				if (tileentity instanceof INamedContainerProvider) 
				{
					NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileentity, pos);
				}
			}
		} 

		return ActionResultType.SUCCESS;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) 
	{
		if (stack.hasDisplayName()) 
		{
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if (tileentity instanceof VerdantPurifierTileEntity) 
			{
				((VerdantPurifierTileEntity)tileentity).setCustomName(stack.getDisplayName());
			}
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) 
	{
		if (state.getBlock() != newState.getBlock()) 
		{
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if (tileentity instanceof VerdantPurifierTileEntity) 
			{
				InventoryHelper.dropInventoryItems(worldIn, pos, (VerdantPurifierTileEntity)tileentity);
				worldIn.updateComparatorOutputLevel(pos, this);
			}

			super.onReplaced(state, worldIn, pos, newState, isMoving);
		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) 
	{
		if (((VerdantPurifierTileEntity)worldIn.getTileEntity(pos)).isPowered() && ((VerdantPurifierTileEntity)worldIn.getTileEntity(pos)).getProcessTime() > 0)
		{
			float xx = (float) pos.getX() + 0.5F, yy = (float) pos.getY() + rand.nextFloat() * 6.0F / 16.0F, zz = (float) pos.getZ() + 0.5F;

			worldIn.addParticle(ParticleTypes.HAPPY_VILLAGER, (double) (xx), (double) yy, (double) (zz), 0.0F, 0.0F, 0.0F);
		}
	}
}