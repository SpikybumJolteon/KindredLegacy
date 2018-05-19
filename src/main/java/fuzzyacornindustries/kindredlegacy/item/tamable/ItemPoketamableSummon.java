package fuzzyacornindustries.kindredlegacy.item.tamable;

import java.util.List;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.creativetab.KindredLegacyCreativeTabs;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.TamablePokemon;
import fuzzyacornindustries.kindredlegacy.item.ItemBase;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPoketamableSummon extends ItemBase
{
	public static TamablePokemon poketamable;

	protected TamablePokemon entityToSpawn = null;

	public static int maximumDamage = 20;

	public ItemPoketamableSummon(String name)
	{
		super(name);
		
		this.maxStackSize = 1;
		setMaxDamage(maximumDamage);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack itemstack = playerIn.getHeldItem(hand);

		if (worldIn.isRemote)
		{
			return EnumActionResult.SUCCESS;
		}
		else if (!playerIn.canPlayerEdit(pos.offset(facing), facing, itemstack))
		{
			return EnumActionResult.FAIL;
		}
		else
		{
			if(itemstack.getItemDamage()  < itemstack.getMaxDamage())
			{
				IBlockState iblockstate = worldIn.getBlockState(pos);

				pos = pos.offset(facing);
				double d0 = 0.0D;

				if (facing == EnumFacing.UP && iblockstate.getBlock() instanceof BlockFence) //Forge: Fix Vanilla bug comparing state instead of block
				{
					d0 = 0.5D;
				}

				Entity entity = spawnEntity(worldIn, playerIn, itemstack, (double)pos.getX() + 0.5D, (double)pos.getY() + d0, (double)pos.getZ() + 0.5D);

				//System.out.println( "Test entity summon run, General Class." );
				//System.out.println( "Entity is not null:" + Boolean.toString(entity != null) );

				if (entity != null)
				{
					if (!playerIn.capabilities.isCreativeMode)
					{
						itemstack.shrink(1); // Decrease itemStack by 1
					}
				}
			}

			return EnumActionResult.SUCCESS;
		}
	}

	public static void retrieveEntityStats(TamablePokemon poketamableEntity, ItemStack poketamableStack)
	{
		writeCompoundStringToItemStack(poketamableStack, "display", "Name", poketamableEntity.getDisplayName().getUnformattedText());
		writeCompoundFloatToItemStack(poketamableStack, "pokemonmd", "health", poketamableEntity.getHealth());
		writeCompoundFloatToItemStack(poketamableStack, "pokemonmd", "attackpower", poketamableEntity.getAttackPower());
		writeCompoundFloatToItemStack(poketamableStack, "pokemonmd", "speed", poketamableEntity.getSpeed());
		writeCompoundIntegerToItemStack(poketamableStack, "pokemonmd", "fireimmunity", poketamableEntity.hasFireImmunityEssence());
		writeCompoundIntegerToItemStack(poketamableStack, "pokemonmd", "drowningimmunity", poketamableEntity.hasDrowningImmunityEssence());
		writeCompoundIntegerToItemStack(poketamableStack, "pokemonmd", "fallimmunity", poketamableEntity.hasFallImmunityEssence());
		writeCompoundIntegerToItemStack(poketamableStack, "pokemonmd", "blocksuffocationavoidance", poketamableEntity.hasBlockSuffocationAvoidanceEssence());
		writeCompoundIntegerToItemStack(poketamableStack, "pokemonmd", "toxinimmunity", poketamableEntity.hasToxinImmunityEssence());
		writeCompoundIntegerToItemStack(poketamableStack, "pokemonmd", "regenlevel", poketamableEntity.getRegenLevel());
		writeCompoundIntegerToItemStack(poketamableStack, "pokemonmd", "spacesurvivability", poketamableEntity.hasSpaceSurvivabilityEssence());
		poketamableStack.getTagCompound().getCompoundTag("pokemonmd").setFloat("health", poketamableEntity.getHealth());
		poketamableStack.setItemDamage((int) itemDamageHealthValue(poketamableEntity.getHealth(), poketamableEntity.getMaximumHealth()));
		poketamableStack.getTagCompound().getCompoundTag("pokemonmd").setFloat("maxhealth", poketamableEntity.getMaximumHealth());
		poketamableStack.getTagCompound().getCompoundTag("pokemonmd").setFloat("attackpower", poketamableEntity.getAttackPower());
		poketamableStack.getTagCompound().getCompoundTag("pokemonmd").setFloat("speed", poketamableEntity.getSpeed());
		poketamableStack.getTagCompound().getCompoundTag("pokemonmd").setFloat("fireimmunity", poketamableEntity.hasFireImmunityEssence());
		poketamableStack.getTagCompound().getCompoundTag("pokemonmd").setFloat("drowningimmunity", poketamableEntity.hasDrowningImmunityEssence());
		poketamableStack.getTagCompound().getCompoundTag("pokemonmd").setFloat("fallgimmunity", poketamableEntity.hasFallImmunityEssence());
		poketamableStack.getTagCompound().getCompoundTag("pokemonmd").setFloat("blocksuffocationavoidance", poketamableEntity.hasBlockSuffocationAvoidanceEssence());
		poketamableStack.getTagCompound().getCompoundTag("pokemonmd").setFloat("toxinimmunity", poketamableEntity.hasToxinImmunityEssence());
		poketamableStack.getTagCompound().getCompoundTag("pokemonmd").setInteger("regenlevel", poketamableEntity.getRegenLevel());
		poketamableStack.getTagCompound().getCompoundTag("pokemonmd").setFloat("spacesurvivability", poketamableEntity.hasSpaceSurvivabilityEssence());
	}

	public static void applyEntityStats(TamablePokemon poketamableEntity, ItemStack poketamableStack, EntityPlayer player)
	{
		poketamableEntity.setMaximumHealth((poketamableStack.getTagCompound() != null && poketamableStack.getTagCompound().getCompoundTag("pokemonmd").getFloat("maxhealth") != 0) ? 
				poketamableStack.getTagCompound().getCompoundTag("pokemonmd").getFloat("maxhealth") : poketamableEntity.getMaximumHealth());
		poketamableEntity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(poketamableEntity.getMaximumHealth());
		poketamableEntity.setHealth((poketamableStack.getTagCompound() != null  && poketamableStack.getTagCompound().getCompoundTag("pokemonmd").getFloat("health") != 0) ? 
				poketamableStack.getTagCompound().getCompoundTag("pokemonmd").getFloat("health") : poketamableEntity.getMaximumHealth());
		poketamableEntity.setAttackPower((poketamableStack.getTagCompound() != null  && poketamableStack.getTagCompound().getCompoundTag("pokemonmd").getFloat("attackpower") != 0) ? 
				poketamableStack.getTagCompound().getCompoundTag("pokemonmd").getFloat("attackpower") : poketamableEntity.getAttackPower());
		poketamableEntity.setSpeed((poketamableStack.getTagCompound() != null  && poketamableStack.getTagCompound().getCompoundTag("pokemonmd").getFloat("speed") != 0) ? 
				poketamableStack.getTagCompound().getCompoundTag("pokemonmd").getFloat("speed") : poketamableEntity.getSpeed());

		poketamableEntity.setFireImmunityEssence((poketamableStack.getTagCompound() != null && poketamableStack.getTagCompound().getCompoundTag("pokemonmd").getInteger("fireimmunity") == 1) || poketamableEntity.hasFireImmunityEssence() == 1 ? 1 : 0);
		poketamableEntity.setDrowningImmunityEssence((poketamableStack.getTagCompound() != null && poketamableStack.getTagCompound().getCompoundTag("pokemonmd").getInteger("drowningimmunity") == 1) || poketamableEntity.hasDrowningImmunityEssence() == 1 ? 1 : 0);
		poketamableEntity.setFallImmunityEssence((poketamableStack.getTagCompound() != null && poketamableStack.getTagCompound().getCompoundTag("pokemonmd").getInteger("fallimmunity") == 1) || poketamableEntity.hasFallImmunityEssence() == 1 ? 1 : 0);
		poketamableEntity.setBlockSuffocationAvoidanceEssence((poketamableStack.getTagCompound() != null  && poketamableStack.getTagCompound().getCompoundTag("pokemonmd").getInteger("blocksuffocationavoidance") == 1) || poketamableEntity.hasBlockSuffocationAvoidanceEssence() == 1 ? 1 : 0);
		poketamableEntity.setToxinImmunityEssence((poketamableStack.getTagCompound() != null && poketamableStack.getTagCompound().getCompoundTag("pokemonmd").getInteger("toxinimmunity") == 1) || poketamableEntity.hasToxinImmunityEssence() == 1 ? 1 : 0);
		poketamableEntity.setSpaceSurvivabilityEssence((poketamableStack.getTagCompound() != null && poketamableStack.getTagCompound().getCompoundTag("pokemonmd").getInteger("spacesurvivability") == 1) || poketamableEntity.hasSpaceSurvivabilityEssence() == 1 ? 1 : 0);

		poketamableEntity.setRegenLevel(((poketamableStack.getTagCompound() != null && poketamableStack.getTagCompound().getCompoundTag("pokemonmd").getInteger("regenlevel") != 0) ? 
				poketamableStack.getTagCompound().getCompoundTag("pokemonmd").getInteger("regenlevel") : poketamableEntity.getRegenLevel()));
		
		poketamableEntity.setOwnerId(player.getUniqueID());
		poketamableEntity.setTamed(true);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
	{
		ItemStack itemStack = playerIn.getHeldItem(hand);
		
		if (worldIn.isRemote)
		{
			if(playerIn.isSneaking())
			{
				KindredLegacyMain.proxy.displayPoketamableRenameGui(itemStack);
			}

			return new ActionResult(EnumActionResult.PASS, itemStack);
		}
		else if(!playerIn.isSneaking())
		{
			RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);

			if (raytraceresult != null && raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK)
			{
				BlockPos blockpos = raytraceresult.getBlockPos();

				if (!(worldIn.getBlockState(blockpos).getBlock() instanceof BlockLiquid))
				{
					return new ActionResult(EnumActionResult.PASS, itemStack);
				}
				else if (worldIn.isBlockModifiable(playerIn, blockpos) && playerIn.canPlayerEdit(blockpos, raytraceresult.sideHit, itemStack))
				{
					Entity entity = spawnEntity(worldIn, playerIn, itemStack, (double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.5D, (double)blockpos.getZ() + 0.5D);

					if (entity == null)
					{
						return new ActionResult(EnumActionResult.PASS, itemStack);
					}
					else
					{
						if (entity instanceof EntityLivingBase && itemStack.hasDisplayName())
						{
							((EntityLiving)entity).setCustomNameTag(itemStack.getDisplayName());
						}

						itemStack.shrink(1); // Decrease itemStack by 1

						playerIn.addStat(StatList.getObjectUseStats(this));
						return new ActionResult(EnumActionResult.SUCCESS, itemStack);
					}
				}
				else
				{
					return new ActionResult(EnumActionResult.FAIL, itemStack);
				}
			}
			else
			{
				return new ActionResult(EnumActionResult.PASS, itemStack);
			}
		}

		return new ActionResult(EnumActionResult.PASS, itemStack);
	}

    @Override
    public boolean getIsRepairable(ItemStack batStack, ItemStack repairStack)
    {
        return false;
}

	public Entity spawnEntity(World parWorld, EntityPlayer parPlayer, ItemStack par1ItemStack, double parX, double parY, double parZ)
	{
		return null;
	}

	public static double itemDamageHealthValue(double input, double max)
	{
		return Math.abs((1 - (input / max)) * maximumDamage);
	}

	public static void writePoketamableNameToItemStack(ItemStack stack, String name)
	{
		writeCompoundStringToItemStack(stack, "display", "Name", TextFormatting.WHITE + name);
	}

	public static String getPoketamableNameFromItemStack(ItemStack stack)
	{
		return (stack.getTagCompound() != null ? stack.getTagCompound().getCompoundTag("display").getString("Name") : "");
	}

	public static void writeCompoundIntegerToItemStack(ItemStack stack, String tag, String key, int data)
	{
		checkCompoundTag(stack, tag);
		stack.getTagCompound().getCompoundTag(tag).setInteger(key, data);
	}

	public static void writeCompoundFloatToItemStack(ItemStack stack, String tag, String key, float data)
	{
		checkCompoundTag(stack, tag);
		stack.getTagCompound().getCompoundTag(tag).setFloat(key, data);
	}

	public static void writeCompoundStringToItemStack(ItemStack stack, String tag, String key, String data)
	{
		checkCompoundTag(stack, tag);
		stack.getTagCompound().getCompoundTag(tag).setString(key, data);
	}

	private static void checkCompoundTag(ItemStack stack, String tag)
	{
		if (stack.getTagCompound() == null)
		{
			stack.setTagCompound(new NBTTagCompound());
		}

		if (!stack.getTagCompound().hasKey(tag))
		{
			stack.getTagCompound().setTag(tag, new NBTTagCompound());
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add("Hold 'sneak' and right click");
		tooltip.add("to bring up Poketamable stats");
		tooltip.add("and rename tamable. Press");
		tooltip.add("escape to save and exit menu.");
	}
}