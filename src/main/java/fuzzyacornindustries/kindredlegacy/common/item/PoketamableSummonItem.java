package fuzzyacornindustries.kindredlegacy.common.item;

import java.util.List;
import java.util.function.Function;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.KindredLegacy;
import fuzzyacornindustries.kindredlegacy.common.core.ModItems;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.TamablePokemon;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext.FluidMode;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PoketamableSummonItem extends Item
{
	public Function<World, TamablePokemon> poketamable;

	public static int maximumDamage = 20;

	public PoketamableSummonItem(Function<World, TamablePokemon> poketamable)
	{
		super(ModItems.defaultSummonProps());
		
		this.poketamable = poketamable;
	}
	
	public Function<World, TamablePokemon> getPoketamable()
	{
		return poketamable;
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) 
	{
		ItemStack itemstack = context.getPlayer().getHeldItem(context.getHand());

		if (context.getWorld().isRemote)
		{
			return ActionResultType.SUCCESS;
		}
		else if (!context.getPlayer().canPlayerEdit(context.getPos().offset(context.getFace()), context.getFace(), itemstack))
		{
			return ActionResultType.FAIL;
		}
		else
		{
			if(itemstack.getDamage()  < itemstack.getMaxDamage())
			{
				BlockState iblockstate = context.getWorld().getBlockState(context.getPos());

				BlockPos pos = context.getPos().offset(context.getFace());
				double d0 = 0.0D;

				if (context.getFace() == Direction.UP && iblockstate.getBlock() instanceof FenceBlock) //Forge: Fix Vanilla bug comparing state instead of block
				{
					d0 = 0.5D;
				}

				Entity entity = spawnEntity(context.getWorld(), context.getPlayer(), itemstack, (double)pos.getX() + 0.5D, (double)pos.getY() + d0, (double)pos.getZ() + 0.5D);

				if (entity != null)
				{
					if (!context.getPlayer().abilities.isCreativeMode)
					{
						itemstack.shrink(1); // Decrease itemStack by 1
					}
				}
			}

			return ActionResultType.SUCCESS;
		}
	}

	public static void retrieveEntityStats(TamablePokemon poketamableEntity, ItemStack poketamableStack)
	{
		poketamableStack.setDisplayName(poketamableEntity.getDisplayName());
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
		poketamableStack.getOrCreateChildTag("pokemonmd").putFloat("health", poketamableEntity.getHealth());
		poketamableStack.setDamage((int) itemDamageHealthValue(poketamableEntity.getHealth(), poketamableEntity.getMaximumHealth()));
		poketamableStack.getOrCreateChildTag("pokemonmd").putFloat("maxhealth", poketamableEntity.getMaximumHealth());
		poketamableStack.getOrCreateChildTag("pokemonmd").putFloat("attackpower", poketamableEntity.getAttackPower());
		poketamableStack.getOrCreateChildTag("pokemonmd").putFloat("speed", poketamableEntity.getSpeed());
		poketamableStack.getOrCreateChildTag("pokemonmd").putFloat("fireimmunity", poketamableEntity.hasFireImmunityEssence());
		poketamableStack.getOrCreateChildTag("pokemonmd").putFloat("drowningimmunity", poketamableEntity.hasDrowningImmunityEssence());
		poketamableStack.getOrCreateChildTag("pokemonmd").putFloat("fallgimmunity", poketamableEntity.hasFallImmunityEssence());
		poketamableStack.getOrCreateChildTag("pokemonmd").putFloat("blocksuffocationavoidance", poketamableEntity.hasBlockSuffocationAvoidanceEssence());
		poketamableStack.getOrCreateChildTag("pokemonmd").putFloat("toxinimmunity", poketamableEntity.hasToxinImmunityEssence());
		poketamableStack.getOrCreateChildTag("pokemonmd").putInt("regenlevel", poketamableEntity.getRegenLevel());
		poketamableStack.getOrCreateChildTag("pokemonmd").putFloat("spacesurvivability", poketamableEntity.hasSpaceSurvivabilityEssence());
	}

	public static void applyEntityStats(TamablePokemon poketamableEntity, ItemStack poketamableStack, PlayerEntity player)
	{
		poketamableEntity.setMaximumHealth((poketamableStack.getTag() != null && poketamableStack.getOrCreateChildTag("pokemonmd").getFloat("maxhealth") != 0) ? 
				poketamableStack.getOrCreateChildTag("pokemonmd").getFloat("maxhealth") : poketamableEntity.getMaximumHealth());
		poketamableEntity.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(poketamableEntity.getMaximumHealth());
		poketamableEntity.setHealth((poketamableStack.getTag() != null  && poketamableStack.getOrCreateChildTag("pokemonmd").getFloat("health") != 0) ? 
				poketamableStack.getOrCreateChildTag("pokemonmd").getFloat("health") : poketamableEntity.getMaximumHealth());
		poketamableEntity.setAttackPower((poketamableStack.getTag() != null  && poketamableStack.getOrCreateChildTag("pokemonmd").getFloat("attackpower") != 0) ? 
				poketamableStack.getOrCreateChildTag("pokemonmd").getFloat("attackpower") : poketamableEntity.getAttackPower());
		poketamableEntity.setSpeed((poketamableStack.getTag() != null  && poketamableStack.getOrCreateChildTag("pokemonmd").getFloat("speed") != 0) ? 
				poketamableStack.getOrCreateChildTag("pokemonmd").getFloat("speed") : poketamableEntity.getSpeed());

		poketamableEntity.setFireImmunityEssence((poketamableStack.getTag() != null && poketamableStack.getOrCreateChildTag("pokemonmd").getInt("fireimmunity") == 1) || poketamableEntity.hasFireImmunityEssence() == 1 ? 1 : 0);
		poketamableEntity.setDrowningImmunityEssence((poketamableStack.getTag() != null && poketamableStack.getOrCreateChildTag("pokemonmd").getInt("drowningimmunity") == 1) || poketamableEntity.hasDrowningImmunityEssence() == 1 ? 1 : 0);
		poketamableEntity.setFallImmunityEssence((poketamableStack.getTag() != null && poketamableStack.getOrCreateChildTag("pokemonmd").getInt("fallimmunity") == 1) || poketamableEntity.hasFallImmunityEssence() == 1 ? 1 : 0);
		poketamableEntity.setBlockSuffocationAvoidanceEssence((poketamableStack.getTag() != null  && poketamableStack.getOrCreateChildTag("pokemonmd").getInt("blocksuffocationavoidance") == 1) || poketamableEntity.hasBlockSuffocationAvoidanceEssence() == 1 ? 1 : 0);
		poketamableEntity.setToxinImmunityEssence((poketamableStack.getTag() != null && poketamableStack.getOrCreateChildTag("pokemonmd").getInt("toxinimmunity") == 1) || poketamableEntity.hasToxinImmunityEssence() == 1 ? 1 : 0);
		poketamableEntity.setSpaceSurvivabilityEssence((poketamableStack.getTag() != null && poketamableStack.getOrCreateChildTag("pokemonmd").getInt("spacesurvivability") == 1) || poketamableEntity.hasSpaceSurvivabilityEssence() == 1 ? 1 : 0);

		poketamableEntity.setRegenLevel(((poketamableStack.getTag() != null && poketamableStack.getOrCreateChildTag("pokemonmd").getInt("regenlevel") != 0) ? 
				poketamableStack.getOrCreateChildTag("pokemonmd").getInt("regenlevel") : poketamableEntity.getRegenLevel()));

		poketamableEntity.setOwnerId(player.getUniqueID());
		poketamableEntity.setTamed(true);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand hand)
	{
		ItemStack itemStack = playerIn.getHeldItem(hand);

		if (playerIn.getEntityWorld().isRemote)
		{
			if(playerIn.isCrouching())
			{
				KindredLegacy.proxy.displayRenameGui(itemStack);
			}

			return new ActionResult<ItemStack>(ActionResultType.PASS, itemStack);
		}
		else if(!playerIn.isCrouching())
		{
			RayTraceResult raytraceresult = PoketamableSummonItem.rayTrace(worldIn, playerIn, FluidMode.ANY);

			if (raytraceresult != null && raytraceresult.getType() == RayTraceResult.Type.BLOCK)
			{
				BlockPos blockpos = new BlockPos(raytraceresult.getHitVec());

				if (!(worldIn.getBlockState(blockpos).getBlock() instanceof IFluidState))
				{
					return new ActionResult<ItemStack>(ActionResultType.PASS, itemStack);
				}
				else if (worldIn.isBlockModifiable(playerIn, blockpos))// && playerIn.canPlayerEdit(blockpos, BlockMode.OUTLINE, itemStack))
				{
					Entity entity = spawnEntity(worldIn, playerIn, itemStack, (double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.5D, (double)blockpos.getZ() + 0.5D);

					if (entity == null)
					{
						return new ActionResult<ItemStack>(ActionResultType.PASS, itemStack);
					}
					else
					{
						if (entity instanceof LivingEntity && itemStack.hasDisplayName())
						{
							((LivingEntity)entity).setCustomName(itemStack.getDisplayName());
						}

						itemStack.shrink(1); // Decrease itemStack by 1

						return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemStack);
					}
				}
				else
				{
					return new ActionResult<ItemStack>(ActionResultType.FAIL, itemStack);
				}
			}
			else
			{
				return new ActionResult<ItemStack>(ActionResultType.PASS, itemStack);
			}
		}

		return new ActionResult<ItemStack>(ActionResultType.PASS, itemStack);
	}

	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		if(worldIn.getGameTime() % 2000 == 0)
		{
			if(stack.getDamage() > 0 && stack.getDamage() < maximumDamage)
				stack.setDamage(stack.getDamage() - 1);
		}
	}

	@Override
	public boolean getIsRepairable(ItemStack batStack, ItemStack repairStack)
	{
		return false;
	}

	public TamablePokemon spawnEntity(World worldIn, PlayerEntity parPlayer, ItemStack par1ItemStack, double parX, double parY, double parZ)
	{
		TamablePokemon entityToSpawn = null;
		
		if (!worldIn.isRemote)
		{
			entityToSpawn = toPoketamableEntity(worldIn, par1ItemStack, parPlayer, poketamable.apply(worldIn));

			entityToSpawn.setLocationAndAngles(parX, parY, parZ, MathHelper.wrapDegrees(worldIn.rand.nextFloat() * 360.0F), 0.0F);
			entityToSpawn.rotationYawHead = entityToSpawn.rotationYaw;
			entityToSpawn.renderYawOffset = entityToSpawn.rotationYaw;
			worldIn.addEntity(entityToSpawn);
            entityToSpawn.playAmbientSound();
		}

		return entityToSpawn;
	}

	public static ItemStack fromPoketamableEntity(TamablePokemon poketamableEntity, ItemStack poketamableStack)
	{
		if (poketamableEntity.world.isRemote)
		{
			return null;
		}
		
		retrieveEntityStats(poketamableEntity, poketamableStack);

		return poketamableStack;
	}

	public static TamablePokemon toPoketamableEntity(World world, ItemStack poketamableStack, PlayerEntity player, TamablePokemon poketamableEntity)
	{
		applyEntityStats(poketamableEntity, poketamableStack, player);

		String name = poketamableStack.getDisplayName().getUnformattedComponentText();

		name = poketamableEntity.defaultNameCheck(name);	
		poketamableEntity.setCustomNameVisible(poketamableEntity.isCustomNameVisible());

		poketamableEntity.setMobName(name);
		poketamableEntity.setCustomName(new StringTextComponent(name));

		return poketamableEntity;
	}
	
	public static double itemDamageHealthValue(double input, double max)
	{
		return Math.abs((1 - (input / max)) * maximumDamage);
	}

	public static void writePoketamableNameToItemStack(ItemStack stack, String name)
	{
		stack.setDisplayName(new StringTextComponent(name));
	}

	public static void writeCompoundIntegerToItemStack(ItemStack stack, String tag, String key, int data) 
	{
		stack.getOrCreateChildTag(tag).putInt(key, data);
	}

	public static void writeCompoundFloatToItemStack(ItemStack stack, String tag, String key, float data) 
	{
		stack.getOrCreateChildTag(tag).putFloat(key, data);
	}

	public static ItemStack writeCompoundStringToItemStack(ItemStack stack, String tag, String key, String data) 
	{
		stack.getOrCreateChildTag(tag).putString(key, data);
		return stack;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(new StringTextComponent("Hold 'sneak' and right click"));
		tooltip.add(new StringTextComponent("to bring up Poketamable stats"));
		tooltip.add(new StringTextComponent("and rename tamable. Press"));
		tooltip.add(new StringTextComponent("escape to save and exit menu."));
	}
}