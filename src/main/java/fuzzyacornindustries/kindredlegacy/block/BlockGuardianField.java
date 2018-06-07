package fuzzyacornindustries.kindredlegacy.block;

import java.util.List;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.creativetab.KindredLegacyCreativeTabs;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.IMiniBoss;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.IMinorBoss;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.TamablePokemon;
import fuzzyacornindustries.kindredlegacy.entity.projectile.EntityVastayaFireball;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGuardianField extends BlockBase
{
	public BlockGuardianField()
	{
		super("guardian_field", Material.GLASS);

		this.setHardness(15.0F);
		this.setResistance(0.1F);
		this.setSoundType(SoundType.GLASS);

		this.translucent = true;

		setCreativeTab(null);
	}

	/**
	 * Called When an Entity Collided with the Block
	 */
	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entity)
	{
		if(entity instanceof EntityPlayer)
		{		
			entity.setInWeb();
		}
		else if(!entity.isNonBoss())
		{
			this.breakBlock(worldIn, pos, state);
		}
		else if(entity instanceof IProjectile || entity instanceof EntityVastayaFireball)
		{
			entity.setDead();
		}
		else if(entity instanceof EntityLiving && !(entity instanceof TamablePokemon) && !(entity instanceof IMiniBoss) && !(entity instanceof IMinorBoss))
		{
			((EntityLiving)entity).getNavigator().clearPath();
		}
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entity, boolean p_185477_7_)
	{
		if(!(entity instanceof TamablePokemon) && !(entity instanceof EntityPlayer) && !(entity instanceof IMiniBoss) && !(entity instanceof IMinorBoss) && (entity instanceof Entity && !entity.isNonBoss()))
		{
			AxisAlignedBB axisalignedbb = state.getBoundingBox(worldIn, pos).offset(pos);

			if (entityBox.intersects(axisalignedbb))
			{
				collidingBoxes.add(axisalignedbb);
			}
		}
	}

	/**
	 * Triggered whenever an entity collides with this block (enters into the block)
	 */
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entity)
	{
		if(entity instanceof EntityLiving)
		{
			if(!(entity instanceof TamablePokemon) && !(entity instanceof EntityPlayer) && !(entity instanceof IMiniBoss) && !(entity instanceof IMinorBoss) && !entity.isNonBoss())
			{
				double maxTeleportDistance = 32D;

				double d0 = entity.posX + (((EntityLiving)entity).world.rand.nextDouble() - 0.5D) * maxTeleportDistance;
				double d1 = entity.posY + (double)(((EntityLiving)entity).world.rand.nextInt(10) - 5);
				double d2 = entity.posZ + (((EntityLiving)entity).world.rand.nextDouble() - 0.5D) * maxTeleportDistance;

				net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent((EntityLiving)entity, d0, d1, d2, 0);
				if(!(net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)))
				{
					boolean flag = ((EntityLiving)entity).attemptTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ());

					if (flag)
					{
						entity.world.playSound((EntityPlayer)null, entity.prevPosX, entity.prevPosY, entity.prevPosZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, entity.getSoundCategory(), 1.0F, 1.0F);
						entity.playSound(SoundEvents.ENTITY_ENDERMEN_TELEPORT, 1.0F, 1.0F);
					}
				}
			}
		}

		super.onEntityWalk(worldIn, pos, entity);
	}

	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
	{
		return true;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
		return NULL_AABB;
	}

	@Override
	public EnumPushReaction getMobilityFlag(IBlockState state)
	{
		return EnumPushReaction.BLOCK;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	/**
	 * Used to determine ambient occlusion and culling when rebuilding chunks for render
	 */
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
	{
		IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
		Block block = iblockstate.getBlock();

		if (blockState != iblockstate)
		{
			return true;
		}

		if (block == this)
		{
			return false;
		}

		boolean ignoreSimilarity = false;

		return !ignoreSimilarity && block == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	}
}