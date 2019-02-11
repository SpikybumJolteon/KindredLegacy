package fuzzyacornindustries.kindredlegacy.item;

import java.util.List;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.creativetab.KindredLegacyCreativeTabs;
import fuzzyacornindustries.kindredlegacy.entity.projectile.EntityHunterBolt;
import fuzzyacornindustries.kindredlegacy.utility.IHasModel;
import fuzzyacornindustries.kindredlegacy.utility.UtilityTargeting;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
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
	//CHANGE: no more durability
	//int maximumDurability = 64;
	int maximumDurability = 0;
	//CHANGE: the durability each hunters charge can recover
	//int huntersChargeRepair = 64;
	//CHANGE: added damage multiplier
	float baseArrowDamage = 1.0F;
	float killToArrowDamage = 0.0005F;
	//CHANGE: added arrow speed multiplier
	float baseArrowSpeed = 6.0F;
	float killToArrowSpeed = 0.001F;
	//CHANGE: added charge velocity multiplier
	float baseChargeVelocity = 1.0F;
	float killToChargeVelocity = 0.001F;
	//CHANGE: added search range multiplier
	float baseSearchRange = 32.0F;
	float killToSearchRange = 0.0005F;
	float baseSearchRadius = 8.0F;
	//CHANGE: added target number multiplier
	int baseTargetNumber = 3;
	float killToTargetNumber = 0.0005F;
	//CHANGE: added mode
	public static final int MODE_COUNT = 2;
	public static final int SINGLE_SHOT = 1;
	public static final int MOB_TRACKING = 2;

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
				return entityIn == null ? 0.0F : (entityIn.getActiveItemStack().getItem() != KindredLegacyItems.BOW_OF_KINDRED ? 0.0F : (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) * getChargeVelocity(stack) / 20.0F);
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		//CHANGE
		tooltip.add("A crude imitation of the one in myth");
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("KillCount")) {
			tooltip.add("Prey: " + stack.getTagCompound().getInteger("KillCount"));
		}
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Mode")) {
			if (stack.getTagCompound().getInteger("Mode") == SINGLE_SHOT) {
				tooltip.add("Current Mode: Single Shot");
			} else if (stack.getTagCompound().getInteger("Mode") == MOB_TRACKING) {
				tooltip.add("Current Mode: Mob Tracking");
			}
		}
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("AutoFire")) {
			if (stack.getTagCompound().getBoolean("AutoFire")) {
				tooltip.add("Auto Fire: On");
			} else {
				tooltip.add("Auto Fire: Off");
			}
		}
		//tooltip.add("Hold Sneak + Right Click to");
		//tooltip.add("to fire up to three arrows at");
		//tooltip.add("three nearest monster mobs each.");
	}

	//CHANGE
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int getKillCount(ItemStack stack) {
		if(stack.hasTagCompound() && stack.getTagCompound().hasKey("KillCount")) {
			return stack.getTagCompound().getInteger("KillCount");
		} else {
			return 0;
		}
	}

	//CHANGE: changeable damage
	public float getArrowDamage(ItemStack stack) {
		return baseArrowDamage * (float)Math.exp(getKillCount(stack) * killToArrowDamage);
	}

	//CHANGE: changeable arrow speed
	public float getArrowSpeed(ItemStack stack) {
		return baseArrowSpeed * (float)Math.exp(getKillCount(stack) * killToArrowSpeed);
	}

	//CHANGE: changeable charge velocity
	public float getChargeVelocity(ItemStack stack) {
		return baseChargeVelocity * (float)Math.exp(getKillCount(stack) * killToChargeVelocity);
	}

	//CHANGE: changeable search range
	public float getSearchRange(ItemStack stack) {
		return baseSearchRange * (float)Math.exp(getKillCount(stack) * killToSearchRange);
	}

	//CHANGE: changeable search radius
	public float getSearchRadius(ItemStack stack) {
		return baseSearchRadius;
	}

	//CHANGE: changeable target number
	public int getTargetNumber(ItemStack stack) {
		return (int)((float)baseTargetNumber * (float)Math.exp(getKillCount(stack) * killToTargetNumber));
	}

	//CHANGE: mode switch
	//CHANGE: no more needed
	/*
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	{
		if (!Keybinds.bowOfKindredModeSwitch.isPressed()) {
			return;
		}
		if (worldIn.isRemote) {
			return;
		}
		if (!(entityIn instanceof EntityPlayer)) {
			return;
		}
		EntityPlayer entityPlayer = (EntityPlayer)entityIn;
		ItemStack bowOfKindred = BowOfKindredEvents.getBowOfKindred(entityPlayer);
		if (bowOfKindred == null) {
			return;
		}
		if(bowOfKindred.hasTagCompound() && bowOfKindred.getTagCompound().hasKey("Mode")) {
			if (bowOfKindred.getTagCompound().getInteger("Mode") == modeCount) {
				bowOfKindred.getTagCompound().setInteger("Mode", 1);
			} else {
				bowOfKindred.getTagCompound().setInteger("Mode", bowOfKindred.getTagCompound().getInteger("Mode") + 1);
			}
			if (bowOfKindred.getTagCompound().getInteger("Mode") == SINGLE_SHOT) {
				entityPlayer.sendMessage(new TextComponentString("Current Mode: Single Shot"));
			} else if (bowOfKindred.getTagCompound().getInteger("Mode") == MOB_TRACKING) {
				entityPlayer.sendMessage(new TextComponentString("Current Mode: Mob Tracking"));
			}
		} else {
			NBTTagCompound nbt;
			if (bowOfKindred.hasTagCompound()) {
				nbt = bowOfKindred.getTagCompound();
			}
			else {
				nbt = new NBTTagCompound();
			}
			nbt.setInteger("Mode", 1);
			bowOfKindred.setTagCompound(nbt);
		}
	}
	*/

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

	//CHANGE: no more durability
	public boolean canFireBow(EntityPlayer player, ItemStack bow)
	{
		//CHANGE: no more durability
		//boolean canFireBow = false;

		//CHANGE
		//if(this.getDamage(bow) < this.getMaxDamage(bow) || player.inventory.hasItemStack(new ItemStack(KindredLegacyItems.HUNTERS_CHARGE)))
		//if(this.getDamage(bow) < this.getMaxDamage(bow))
		//{
		//	canFireBow = true;
		//}

		//return canFireBow;

		return true;
	}

	//CHANGE: added auto fire
	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		int drawbackTimer = stack.getMaxItemUseDuration() - count;
		int drawbackCharge = (int)((float)drawbackTimer * this.getChargeVelocity(stack));
		if (stack.hasTagCompound() && stack.getTagCompound().getBoolean("AutoFire")) {
			if (drawbackCharge >= 20) {
				player.stopActiveHand();
			}
		}
	}

	/**
	 * Called when the player stops using an Item (stops holding the right mouse button).
	 */
	//CHANGE: rewrite
	@Override
	public void onPlayerStoppedUsing(ItemStack bow, World world, EntityLivingBase player, int timeLeft)
	{
		if (player instanceof EntityPlayer)
		{
			EntityPlayer entityplayer = (EntityPlayer)player;
			boolean flag = this.canFireBow(entityplayer, bow);

			//CHANGE: unnecessary code
			//ItemStack ammoItemstack = this.findAmmo(entityplayer);

			int drawbackTimer = this.getMaxItemUseDuration(bow) - timeLeft;
			//CHANGE: unnecessary code
			//drawbackTimer = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(bow, world, (EntityPlayer)player, drawbackTimer, ammoItemstack != null || flag);
			if (drawbackTimer < 0) return;

			if (flag)
			{
				//CHANGE: unnecessary code
				/*
				if (ammoItemstack.isEmpty())
				{
					ammoItemstack = new ItemStack(KindredLegacyItems.HUNTERS_CHARGE);
				}
				*/

				//CHANGE: altered the calculation
				int drawbackCharge = (int)((float)drawbackTimer * this.getChargeVelocity(bow));
				//float drawbackCharge = (float)drawbackTimer * getChargeVelocityMultiplier() / 20.0F;
				//drawbackCharge = (drawbackCharge * drawbackCharge + drawbackCharge * 2.0F) / 3.0F;

				//if ((double)drawbackCharge < 0.2D)
				//{
				//	return;
				//}

				//if (drawbackCharge > 1.0F)
				//{
				//	drawbackCharge = 1.0F;
				//}

				float f = getArrowVelocity(drawbackCharge);

				if ((double)f >= 0.1D)
				{
					if(bow.hasTagCompound() && (bow.getTagCompound().getInteger("Mode") == MOB_TRACKING))
					{
						//CHANGE
						//int danceOfArrowsQuantity = 3;

						//CHANGE: another searching method
						List<EntityMob> nearestTargetMobs = UtilityTargeting.acquireNearestLookMobTargetListAltered(player, this.getSearchRange(bow), this.getSearchRadius(bow), this.getTargetNumber(bow));

						int numberOfShotsToFire = nearestTargetMobs.size();

						if (numberOfShotsToFire > 0)
						{
							for (EntityMob target : nearestTargetMobs)
							{
								if(!world.isRemote)
								{
									//CHANGE: no more durability
									//applyHunterCharge(bow, entityplayer);
									//bow.damageItem(1, player);

									//CHANGE: base speed x4
									EntityHunterBolt entityBolt = new EntityHunterBolt(world, player, (EntityLivingBase)target, f * this.getArrowSpeed(bow), 0F);

									//CHANGE: change modifier to 0.5 to match floor
									UtilityTargeting.applyHunterBoltSettings(entityBolt, bow, f, 0.5F);

									entityBolt.setDamage(this.getArrowDamage(bow));

									world.spawnEntity(entityBolt);

									playShootSound(world, entityplayer, f);

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
						//CHANGE: unnecessary code
						//boolean flag1 = entityplayer.capabilities.isCreativeMode || this.getDamage(bow) < this.maximumDurability || ammoItemstack != null;

						if (!world.isRemote)
						{
							//CHANGE: base speed x4
							EntityHunterBolt entityBolt = new EntityHunterBolt(world, player, f * this.getArrowSpeed(bow), 0.0F);
							//CHANGE: unnecessary code
							//entityBolt.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * 12.0F, 0.0F);

							UtilityTargeting.applyHunterBoltSettings(entityBolt, bow, f);

							entityBolt.setDamage(this.getArrowDamage(bow));

							//CHANGE: no more durability
							//applyHunterCharge(bow, entityplayer);
							//bow.damageItem(1, player);

							playShootSound(world, entityplayer, f);

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

	//CHANGE: no more durability
	//CHANGE: rewrite
	/*
	public void applyHunterCharge(ItemStack bow, EntityPlayer player)
	{
		ItemStack itemstack = this.findAmmo(player);

		if(this.getDamage(bow) >= this.huntersChargeRepair && itemstack != null && itemstack != ItemStack.EMPTY)
		{
			bow.damageItem(-this.huntersChargeRepair, player);

			itemstack.shrink(1);

			if (itemstack.isEmpty())
			{
				player.inventory.deleteStack(itemstack);
			}
		}
	}
	*/

	/**
	 * Gets the velocity of the arrow entity from the bow's charge
	 */
	//CHANGE: unnecessary code
	/*
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
	 */

	/**
	 * How long it takes to use or consume an item
	 */
	//CHANGE: unnecessary code
	/*
	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 72000;
	}
	*/

	/**
	 * returns the action that specifies what animation to play when the items is being used
	 */
	//CHANGE: unnecessary code
	/*
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.BOW;
	}
	*/

	//CHANGE: rewrite
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		boolean flag = this.canFireBow(playerIn, itemstack);
		//CHANGE: no more durability
		//applyHunterCharge(itemstack, playerIn);

		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
		if (ret != null) return ret;

		playerIn.setActiveHand(handIn);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}

	/**
	 * Return the enchantability factor of the item, most of the time is based on material.
	 */
	//CHANGE: unnecessary code
	/*
	@Override
	public int getItemEnchantability()
	{
		return 1;
	}
	*/

	//CHANGE: unnecessary code
	/*
	public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.entity.player.EntityPlayer player)
	{
		int enchant = net.minecraft.enchantment.EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.INFINITY, bow);
		return enchant > 0;
	}
	 */

	@Override
	public void registerModels() 
	{
		KindredLegacyMain.proxy.registerItemRenderer(this, 0, "inventory");
	}
}