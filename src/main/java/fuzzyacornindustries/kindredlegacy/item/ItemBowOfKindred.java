package fuzzyacornindustries.kindredlegacy.item;

import java.util.List;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.creativetab.KindredLegacyCreativeTabs;
import fuzzyacornindustries.kindredlegacy.entity.projectile.EntityHunterBolt;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import fuzzyacornindustries.kindredlegacy.utility.IHasModel;
import fuzzyacornindustries.kindredlegacy.utility.UtilityTargeting;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBowOfKindred extends ItemBow implements IHasModel
{
	int maximumDurability = 64;

	public ItemBowOfKindred(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(KindredLegacyCreativeTabs.tabMain);

		KindredLegacyItems.ITEMS.add(this);
		
		this.setMaxDamage(maximumDurability);

		this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter()
		{
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
			{
				return entityIn == null ? 0.0F : (entityIn.getActiveItemStack().getItem() != KindredLegacyItems.BOW_OF_KINDRED ? 0.0F : (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F);
			}
		});
		this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter()
		{
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
			{
				return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
			}
		});
	}

	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add("Hold Sneak + Right Click to");
		tooltip.add("to fire up to three arrows at");
		tooltip.add("three nearest monster mobs each.");
	}

	private ItemStack findAmmo(EntityPlayer player)
	{
		if (this.isHuntersCharge(player.getHeldItem(EnumHand.OFF_HAND)))
		{
			return player.getHeldItem(EnumHand.OFF_HAND);
		}
		else if (this.isHuntersCharge(player.getHeldItem(EnumHand.MAIN_HAND)))
		{
			return player.getHeldItem(EnumHand.MAIN_HAND);
		}
		else
		{
			for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
			{
				ItemStack itemstack = player.inventory.getStackInSlot(i);

				if (this.isHuntersCharge(itemstack))
				{
					return itemstack;
				}
			}

			return ItemStack.EMPTY;
		}
	}

	protected boolean isHuntersCharge(ItemStack stack)
	{
		return stack.getItem() == KindredLegacyItems.HUNTERS_CHARGE;
	}

	public boolean canFireBow(EntityPlayer player, ItemStack bow)
	{
		boolean canFireBow = false;

		if(this.getDamage(bow) < this.getMaxDamage(bow) || player.inventory.hasItemStack(new ItemStack(KindredLegacyItems.HUNTERS_CHARGE)))
		{
			canFireBow = true;
		}

		return canFireBow;
	}

	/**
	 * Called when the player stops using an Item (stops holding the right mouse button).
	 */
	@Override
	public void onPlayerStoppedUsing(ItemStack bow, World world, EntityLivingBase player, int timeLeft)
	{
		if (player instanceof EntityPlayer)
		{
			EntityPlayer entityplayer = (EntityPlayer)player;
			boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, bow) > 0;
			ItemStack ammoItemstack = this.findAmmo(entityplayer);

			int drawbackTimer = this.getMaxItemUseDuration(bow) - timeLeft + 10;
			drawbackTimer = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(bow, world, (EntityPlayer)player, drawbackTimer, ammoItemstack != null || flag);
			if (drawbackTimer < 0) return;

			if (!ammoItemstack.isEmpty() || flag)
			{
				if (ammoItemstack.isEmpty())
				{
					ammoItemstack = new ItemStack(KindredLegacyItems.HUNTERS_CHARGE);
				}

				float drawbackCharge = (float)drawbackTimer / 20.0F;
				drawbackCharge = (drawbackCharge * drawbackCharge + drawbackCharge * 2.0F) / 3.0F;

				if ((double)drawbackCharge < 0.2D)
				{
					return;
				}

				if (drawbackCharge > 1.0F)
				{
					drawbackCharge = 1.0F;
				}

				float f = getArrowVelocity(drawbackTimer);

				if ((double)f >= 0.2D)
				{
					if(player.isSneaking())
					{
						int danceOfArrowsQuantity = 3;

						List<EntityMob> nearestThreeTargetMobs = UtilityTargeting.acquireNearestLookMobTargetList(player, 10, 5D, danceOfArrowsQuantity); 

						int numberOfShotsToFire = nearestThreeTargetMobs.size();

						if (numberOfShotsToFire > 0)
						{
							for (EntityMob target : nearestThreeTargetMobs) 
							{
								if(entityplayer.capabilities.isCreativeMode || this.getDamage(bow) < this.maximumDurability || ammoItemstack != null)
								{
									applyHunterCharge(bow, entityplayer);
									bow.damageItem(1, player);

									EntityHunterBolt entityBolt = new EntityHunterBolt(world, player, (EntityLivingBase)target, drawbackCharge * 2.0F, 0F);

									UtilityTargeting.applyHunterBoltSettings(entityBolt, bow, drawbackCharge, 0.49F);

									if (!world.isRemote)
									{
										world.spawnEntity(entityBolt);
									}

									playShootSound(world, entityplayer, drawbackCharge);

									//System.out.println( "Test Entity Detected By Dance of Arrows" );
									//System.out.println( Integer.toString( target.getEntityId() ) );
								}
							}

							//System.out.println( "Test Entity Detected By Dance of Arrows" );
							//System.out.println( Integer.toString( nearestThreeTargetMobs.size() ) );
						}
					}
					else
					{
						boolean flag1 = entityplayer.capabilities.isCreativeMode || this.getDamage(bow) < this.maximumDurability || ammoItemstack != null;

						if (!world.isRemote && flag1 == true)
						{
							EntityHunterBolt entityBolt = new EntityHunterBolt(world, player, drawbackCharge * 2.0F);
							entityBolt.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * 3.0F, 1.0F);

							UtilityTargeting.applyHunterBoltSettings(entityBolt, bow, drawbackCharge);

							applyHunterCharge(bow, entityplayer);
							bow.damageItem(1, player);

							playShootSound(world, entityplayer, drawbackCharge);

							world.spawnEntity(entityBolt);
						}
					}

					/*
					if (!flag1 && !entityplayer.capabilities.isCreativeMode)
					{
						itemstack.func_190918_g(1);

						if (itemstack.func_190926_b())
						{
							entityplayer.inventory.deleteStack(itemstack);
						}
					}
					 */
				}
			}
		}
	}

	public void playShootSound(World world, EntityPlayer player, float arrowVelocity)
	{
		world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, KindredLegacySoundEvents.BOW_OF_KINDRED_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + arrowVelocity * 0.5F);
	}

	public void applyHunterCharge(ItemStack bow, EntityPlayer player)
	{
		ItemStack itemstack = this.findAmmo(player);

		if(this.getDamage(bow) >= this.maximumDurability - 1 && itemstack != null)
		{
			bow.setItemDamage(0);

			itemstack.shrink(1);

			if (itemstack.isEmpty())
			{
				player.inventory.deleteStack(itemstack);
			}
		}
	}

	/**
	 * Gets the velocity of the arrow entity from the bow's charge
	 */
	public static float getArrowVelocity(int charge)
	{
		float f = (float)charge / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;

		if (f > 1.0F)
		{
			f = 1.0F;
		}

		return f;
	}

	/**
	 * How long it takes to use or consume an item
	 */
	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 72000;
	}

	/**
	 * returns the action that specifies what animation to play when the items is being used
	 */
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.BOW;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World itemStackIn, EntityPlayer worldIn, EnumHand playerIn)
	{
		ItemStack itemstack = worldIn.getHeldItem(playerIn);
		boolean flag = !this.findAmmo(worldIn).isEmpty();

		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, itemStackIn, worldIn, playerIn, flag);
		if (ret != null) return ret;

		if (!worldIn.capabilities.isCreativeMode && !flag)
		{
			return flag ? new ActionResult(EnumActionResult.PASS, itemstack) : new ActionResult(EnumActionResult.FAIL, itemstack);
		}
		else
		{
			worldIn.setActiveHand(playerIn);
			return new ActionResult(EnumActionResult.SUCCESS, itemstack);
		}
	}

	/**
	 * Return the enchantability factor of the item, most of the time is based on material.
	 */
	@Override
	public int getItemEnchantability()
	{
		return 1;
	}

	public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.EntityPlayer player)
	{
		int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.INFINITY, bow);
		return enchant > 0;
	}

	@Override
	public void registerModels() 
	{
		KindredLegacyMain.proxy.registerItemRenderer(this, 0, "inventory");
	}
}