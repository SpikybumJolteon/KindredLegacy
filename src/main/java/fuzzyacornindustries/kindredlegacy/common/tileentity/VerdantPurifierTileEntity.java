package fuzzyacornindustries.kindredlegacy.common.tileentity;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import fuzzyacornindustries.kindredlegacy.client.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.client.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.gui.VerdantPurifierGui;
import fuzzyacornindustries.kindredlegacy.common.block.VerdantPurifierBlock;
import fuzzyacornindustries.kindredlegacy.common.core.ModTileEntities;
import fuzzyacornindustries.kindredlegacy.common.inventory.container.VerdantPurifierContainer;
import fuzzyacornindustries.kindredlegacy.common.network.GuiSynced;
import fuzzyacornindustries.kindredlegacy.common.recipe.ModRecipeType;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class VerdantPurifierTileEntity extends TileEntityBase implements ISidedInventory, IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity, IAnimatedEntity
{
	private IdleAnimationClock petalsIdleAnimationClock;
	private IdleAnimationClock chamberIdleAnimationClock;
	private IdleAnimationClock verticalShiftIdleAnimationClock[] = new IdleAnimationClock[2];

	public static final int actionIDNone = 0;

	private int animationID;
	private int animationTick;

	public static final int INPUT_ID = 0;
	public static final int OUTPUT_ID = 1;

	private static final int[] SLOTS_UP = new int[]{0};
	private static final int[] SLOTS_DOWN = new int[]{1};

	protected NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);
	@GuiSynced
	private int processTime;
	@GuiSynced
	private int processTimeTotal;

	public int getProcessTime()
	{
		return processTime;
	}

	public int setProcessTime(int i)
	{
		return processTime = i;
	}

	public int getProcessTimeTotal()
	{
		return processTimeTotal;
	}

	public int setProcessTimeTotal(int i)
	{
		return processTimeTotal = i;
	}

	private final Map<ResourceLocation, Integer> field_214022_n = Maps.newHashMap();

	public VerdantPurifierTileEntity()
	{
		super(ModTileEntities.VERDANT_PURIFIER.get());

		animationID = actionIDNone;
		animationTick = 0;

		setClockDefaults();
	}

	@Override
	protected ITextComponent getDefaultName() 
	{
		return new StringTextComponent("Verdant Purifier");
	}

	public boolean isPowered() 
	{
		return VerdantPurifierBlock.isPylonNearby(this.world, this.pos);
	}

	@Override
	public void read(CompoundNBT compound) 
	{
		super.read(compound);
		this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.items);
		this.processTime = compound.getInt("CookTime");
		this.processTimeTotal = compound.getInt("CookTimeTotal");
		int i = compound.getShort("RecipesUsedSize");

		for(int j = 0; j < i; ++j) 
		{
			ResourceLocation resourcelocation = new ResourceLocation(compound.getString("RecipeLocation" + j));
			int k = compound.getInt("RecipeAmount" + j);
			this.field_214022_n.put(resourcelocation, k);
		}

	}

	@Override
	public CompoundNBT write(CompoundNBT compound) 
	{
		super.write(compound);
		compound.putInt("CookTime", this.processTime);
		compound.putInt("CookTimeTotal", this.processTimeTotal);
		ItemStackHelper.saveAllItems(compound, this.items);
		compound.putShort("RecipesUsedSize", (short)this.field_214022_n.size());
		int i = 0;

		for(Entry<ResourceLocation, Integer> entry : this.field_214022_n.entrySet()) 
		{
			compound.putString("RecipeLocation" + i, entry.getKey().toString());
			compound.putInt("RecipeAmount" + i, entry.getValue());
			++i;
		}

		return compound;
	}

	@Override
	public void tick() 
	{
		boolean flag = this.isPowered();
		boolean flag1 = false;

		if (!this.world.isRemote) 
		{
			if (this.isPowered() && !this.items.get(INPUT_ID).isEmpty()) 
			{
				if (this.isPowered() && this.canSmelt()) 
				{
					++this.processTime;
					if (this.processTime == this.processTimeTotal) 
					{
						this.processTime = 0;
						this.processTimeTotal = this.getTotalProcessTime();
						this.processItem();
						flag1 = true;
					}
				} 
				else 
				{
					this.processTime = 0;
				}
			} 
			else if (!this.isPowered() && this.processTime > 0) 
			{
				this.processTime = MathHelper.clamp(this.processTime - 2, 0, this.processTimeTotal);
			}

			if (flag != this.isPowered()) 
			{
				flag1 = true;
				this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(VerdantPurifierBlock.LIT, Boolean.valueOf(this.isPowered())), 3);
			}
		}
		else
		{
			if(this.isPowered())
				incrementPartClocks();
		}

		if (flag1) 
		{
			this.markDirty();
		}
	}

	protected boolean canSmelt() 
	{
		if (!this.items.get(INPUT_ID).isEmpty()) 
		{
			ItemStack itemstack = getOutputForItem(this.items.get(INPUT_ID));
			if (itemstack.isEmpty()) 
			{
				return false;
			} 
			else 
			{
				ItemStack itemstack1 = this.items.get(OUTPUT_ID);
				if (itemstack1.isEmpty()) 
				{
					return true;
				} 
				else if (!itemstack1.isItemEqual(itemstack)) 
				{
					return false;
				} 
				else if (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) // Forge fix: make furnace respect stack sizes in furnace recipes
				{
					return true;
				} 
				else 
				{
					return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
				}
			}
		} 
		else 
		{
			return false;
		}
	}

	private void processItem() 
	{
		if (this.canSmelt()) 
		{
			ItemStack itemstack = this.items.get(INPUT_ID);
			ItemStack itemstack1 = getOutputForItem(this.items.get(INPUT_ID));
			ItemStack itemstack2 = this.items.get(OUTPUT_ID);
			if (itemstack2.isEmpty()) 
			{
				this.items.set(OUTPUT_ID, itemstack1.copy());
			} 
			else if (itemstack2.getItem() == itemstack1.getItem()) 
			{
				itemstack2.grow(itemstack1.getCount());
			}

			itemstack.shrink(1);
		}
	}

	protected int getTotalProcessTime() 
	{
		if (!this.items.get(INPUT_ID).isEmpty()) 
		{
			return getProcessTimeForItem(this.items.get(INPUT_ID));
		}
		else
			return 0;
	}

	private int getProcessTimeForItem(ItemStack input) 
	{
		return ModRecipeType.VERDANT_PURIFYING.stream(world)
				.filter(recipe -> recipe.matches(input))
				.findFirst()
				.map(recipe -> recipe.getDuration())
				.orElse(0);
	}

	private ItemStack getOutputForItem(ItemStack input) 
	{
		return ModRecipeType.VERDANT_PURIFYING.stream(world)
				.filter(recipe -> recipe.matches(input))
				.findFirst()
				.map(recipe -> recipe.getOutput().copy())
				.orElse(ItemStack.EMPTY);
	}

	@Override
	public int[] getSlotsForFace(Direction side) 
	{
		if (side == Direction.DOWN) 
		{
			return SLOTS_DOWN;
		} 
		else 
		{
			return SLOTS_UP;
		}
	}

	/**
	 * Returns true if automation can insert the given item in the given slot from the given side.
	 */
	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, @Nullable Direction direction) 
	{
		return this.isItemValidForSlot(index, itemStackIn);
	}

	/**
	 * Returns true if automation can extract the given item in the given slot from the given side.
	 */
	@Override
	public boolean canExtractItem(int index, ItemStack stack, Direction direction) 
	{
		return true;
	}

	/**
	 * Returns the number of slots in the inventory.
	 */
	@Override
	public int getSizeInventory() 
	{
		return this.items.size();
	}

	@Override
	public boolean isEmpty() 
	{
		for(ItemStack itemstack : this.items) 
		{
			if (!itemstack.isEmpty()) 
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns the stack in the given slot.
	 */
	@Override
	public ItemStack getStackInSlot(int index) 
	{
		return this.items.get(index);
	}

	/**
	 * Removes up to a specified number of items from an inventory slot and returns them in a new stack.
	 */
	@Override
	public ItemStack decrStackSize(int index, int count) 
	{
		return ItemStackHelper.getAndSplit(this.items, index, count);
	}

	/**
	 * Removes a stack from the given slot and returns it.
	 */
	@Override
	public ItemStack removeStackFromSlot(int index) 
	{
		return ItemStackHelper.getAndRemove(this.items, index);
	}

	/**
	 * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
	 */
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) 
	{
		ItemStack itemstack = this.items.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.items.set(index, stack);
		if (stack.getCount() > this.getInventoryStackLimit()) 
		{
			stack.setCount(this.getInventoryStackLimit());
		}

		if (index == INPUT_ID && !flag) 
		{
			this.processTimeTotal = this.getTotalProcessTime();
			this.processTime = 0;
			this.markDirty();
		}

	}

	/**
	 * Don't rename this method to canInteractWith due to conflicts with Container
	 */
	@Override
	public boolean isUsableByPlayer(PlayerEntity player) 
	{
		if (this.world.getTileEntity(this.pos) != this) 
		{
			return false;
		} 
		else 
		{
			return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
		}
	}

	/**
	 * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot. For
	 * guis use Slot.isItemValid
	 */
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) 
	{
		return true;
	}

	@Override
	public void clear() 
	{
		this.items.clear();
	}

	@Override
	public void setRecipeUsed(@Nullable IRecipe<?> recipe) 
	{
		if (recipe != null) {
			this.field_214022_n.compute(recipe.getId(), (p_214004_0_, p_214004_1_) -> {
				return 1 + (p_214004_1_ == null ? 0 : p_214004_1_);
			});
		}

	}

	@Override
	@Nullable
	public IRecipe<?> getRecipeUsed() 
	{
		return null;
	}

	@Override
	public void onCrafting(PlayerEntity player) {}

	public void func_213995_d(PlayerEntity p_213995_1_) 
	{
		List<IRecipe<?>> list = Lists.newArrayList();

		for(Entry<ResourceLocation, Integer> entry : this.field_214022_n.entrySet()) 
		{
			p_213995_1_.world.getRecipeManager().getRecipe(entry.getKey()).ifPresent((p_213993_3_) -> {
				list.add(p_213993_3_);
				func_214003_a(p_213995_1_, entry.getValue(), ((AbstractCookingRecipe)p_213993_3_).getExperience());
			});
		}

		p_213995_1_.unlockRecipes(list);
		this.field_214022_n.clear();
	}

	private static void func_214003_a(PlayerEntity p_214003_0_, int p_214003_1_, float p_214003_2_) 
	{
		if (p_214003_2_ == 0.0F) 
		{
			p_214003_1_ = 0;
		} 
		else if (p_214003_2_ < 1.0F) 
		{
			int i = MathHelper.floor((float)p_214003_1_ * p_214003_2_);
			if (i < MathHelper.ceil((float)p_214003_1_ * p_214003_2_) && Math.random() < (double)((float)p_214003_1_ * p_214003_2_ - (float)i)) 
			{
				++i;
			}

			p_214003_1_ = i;
		}

		while(p_214003_1_ > 0) 
		{
			int j = ExperienceOrbEntity.getXPSplit(p_214003_1_);
			p_214003_1_ -= j;
			p_214003_0_.world.addEntity(new ExperienceOrbEntity(p_214003_0_.world, p_214003_0_.getPosX(), p_214003_0_.getPosY() + 0.5D, p_214003_0_.getPosZ() + 0.5D, j));
		}
	}

	@Override
	public void fillStackedContents(RecipeItemHelper helper) 
	{
		for(ItemStack itemstack : this.items) 
		{
			helper.accountStack(itemstack);
		}
	}

	net.minecraftforge.common.util.LazyOptional<? extends net.minecraftforge.items.IItemHandler>[] handlers =
			net.minecraftforge.items.wrapper.SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

	@Override
	public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable Direction facing) 
	{
		if (!this.removed && facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) 
		{
			if (facing == Direction.UP)
				return handlers[0].cast();
			else if (facing == Direction.DOWN)
				return handlers[1].cast();
			else
				return handlers[2].cast();
		}
		return super.getCapability(capability, facing);
	}

	/**
	 * invalidates a tile entity
	 */
	@Override
	public void remove() 
	{
		super.remove();
		for (int x = 0; x < handlers.length; x++)
			handlers[x].invalidate();
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}

	public String getGuiID() 
	{
		return "kindredlegacy:verdant_purifier";
	}

	@Override
	protected Container createMenu(int id, PlayerInventory playerInventory) 
	{
        return new VerdantPurifierContainer(id, playerInventory, getPos());
	}

	@OnlyIn(Dist.CLIENT)
	public int getProgressionScale()
	{
		int i = this.getProcessTime();
		int j = this.getProcessTimeTotal();
		return j != 0 && i != 0 ? i * VerdantPurifierGui.cookIconWidth / j : 0;
	}
	
	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	public IdleAnimationClock getIdleAnimationClockPetals() 
	{
		return petalsIdleAnimationClock;
	}

	public IdleAnimationClock getIdleAnimationClockChamber() 
	{

		return chamberIdleAnimationClock;
	}

	public IdleAnimationClock getIdleAnimationClockVerticalShift(int partNumber) 
	{
		return verticalShiftIdleAnimationClock[partNumber];
	}

	public void incrementPartClocks()
	{
		for(int i = 0; i < verticalShiftIdleAnimationClock.length; i++)
		{
			verticalShiftIdleAnimationClock[i].incrementClocks();
		}

		chamberIdleAnimationClock.incrementClocks();
		petalsIdleAnimationClock.incrementClocks();
	}

	public void setClockDefaults()
	{
		setVerticalShiftDefaults();
		setChamberDefaults();
		setPetalsClockDefaults();
	}

	public void setVerticalShiftDefaults()
	{
		Random rand = new Random();

		int randomInt = rand.nextInt(100);

		for(int i = 0; i < verticalShiftIdleAnimationClock.length; i++)
		{
			verticalShiftIdleAnimationClock[i] = new IdleAnimationClock(1, 1, 0);

			verticalShiftIdleAnimationClock[i].setPhaseDurationX(0, 80);
			verticalShiftIdleAnimationClock[i].setPhaseDurationY(0, 80);

			float frequencyY = 0.15F;

			int startingClockX = (int)(((float)(verticalShiftIdleAnimationClock.length - i) / (float)verticalShiftIdleAnimationClock.length) * (float)verticalShiftIdleAnimationClock[i].getTotalDurationLengthX() * frequencyY) + randomInt;
			int startingClockY = (int)(((float)(verticalShiftIdleAnimationClock.length - i) / (float)verticalShiftIdleAnimationClock.length) * (float)verticalShiftIdleAnimationClock[i].getTotalDurationLengthY() * frequencyY) + randomInt;

			while(startingClockX > verticalShiftIdleAnimationClock[i].getTotalDurationLengthX())
			{
				startingClockX -= verticalShiftIdleAnimationClock[i].getTotalDurationLengthX();
			}

			while(startingClockY > verticalShiftIdleAnimationClock[i].getTotalDurationLengthY())
			{
				startingClockY -= verticalShiftIdleAnimationClock[i].getTotalDurationLengthY();
			}

			verticalShiftIdleAnimationClock[i].setClockX(startingClockX);
			verticalShiftIdleAnimationClock[i].setClockY(startingClockY);
		}
	}

	public void setChamberDefaults()
	{
		Random rand = new Random();

		int startingClockX = rand.nextInt(100);
		int startingClockY = rand.nextInt(100);
		int startingClockZ = rand.nextInt(100);

		chamberIdleAnimationClock = new IdleAnimationClock(1, 1, 1);

		chamberIdleAnimationClock.setPhaseDurationX(0, 200);
		chamberIdleAnimationClock.setPhaseDurationY(0, 350);
		chamberIdleAnimationClock.setPhaseDurationZ(0, 230);

		while(startingClockX > chamberIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= chamberIdleAnimationClock.getTotalDurationLengthX();
		}

		while(startingClockY > chamberIdleAnimationClock.getTotalDurationLengthY())
		{
			startingClockY -= chamberIdleAnimationClock.getTotalDurationLengthY();
		}

		while(startingClockZ > chamberIdleAnimationClock.getTotalDurationLengthZ())
		{
			startingClockZ -= chamberIdleAnimationClock.getTotalDurationLengthZ();
		}

		chamberIdleAnimationClock.setClockX(startingClockX);
		chamberIdleAnimationClock.setClockY(startingClockY);
		chamberIdleAnimationClock.setClockZ(startingClockZ);
	}

	public void setPetalsClockDefaults()
	{
		Random rand = new Random();

		int startingClockX = rand.nextInt(100);
		int startingClockY = rand.nextInt(100);

		petalsIdleAnimationClock = new IdleAnimationClock(4, 1, 0);

		petalsIdleAnimationClock.setPhaseDurationX(0, 110);
		petalsIdleAnimationClock.setPhaseDurationX(1, 80);
		petalsIdleAnimationClock.setPhaseDurationX(2, 40);
		petalsIdleAnimationClock.setPhaseDurationX(3, 100);

		petalsIdleAnimationClock.setPhaseDurationY(0, 500);

		while(startingClockX > petalsIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= petalsIdleAnimationClock.getTotalDurationLengthX();
		}

		while(startingClockY > petalsIdleAnimationClock.getTotalDurationLengthY())
		{
			startingClockY -= petalsIdleAnimationClock.getTotalDurationLengthY();
		}

		//petalsIdleAnimationClock.setClockX(startingClockX);
		petalsIdleAnimationClock.setClockY(startingClockY);
	}

	@Override
	public void setAnimationID(int id) 
	{
		animationID = id;
	}

	@Override
	public void setAnimationTick(int tick) 
	{
		animationTick = tick;	
	}

	@Override
	public int getAnimationID() 
	{
		return animationID;
	}

	@Override
	public int getAnimationTick() 
	{
		return animationTick;
	}
}