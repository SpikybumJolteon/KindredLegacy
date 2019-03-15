package fuzzyacornindustries.kindredlegacy.tileentity;

import java.util.Random;

import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.block.BlockAlchemenisizer;
import fuzzyacornindustries.kindredlegacy.recipe.AlchemenisizerRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityAlchemenisizer extends TileEntity implements IInventory, ITickable, IAnimatedEntity
{
	private IdleAnimationClock rotationAnimationClock;
	private IdleAnimationClock verticalShiftIdleAnimationClock[] = new IdleAnimationClock[3];

	public static final int actionIDNone = 0;

	private int animationID;
	private int animationTick;

	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
	private String customName;

	public static final int NETHERWART_SLOT_ID = 0;
	public static final int MAIN_ITEM_SLOT_ID = 1;
	public static final int DUST_SLOT_ID = 2;
	public static final int PRODUCT_SLOT_ID = 3;
	
	private int activeTime;
	private int currentActiveTime;
	private int processingTime;
	private int totalProcessingTime;
	/*
	public enum slotEnum 
	{
		INPUT_SLOT, OUTPUT_SLOT
	}
	 */
	public TileEntityAlchemenisizer()
	{
		animationID = actionIDNone;
		animationTick = 0;

		setClockDefaults();
	}

	@Override
	public String getName() 
	{
		return this.hasCustomName() ? this.customName : "container.alchemenisizer";
	}

	@Override
	public boolean hasCustomName() 
	{
		return this.customName != null && !this.customName.isEmpty();
	}

	public void setCustomName(String customName) 
	{
		this.customName = customName;
	}

	@Override
	public ITextComponent getDisplayName() 
	{
		return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
	}

	@Override
	public int getSizeInventory() 
	{
		return this.inventory.size();
	}

	@Override
	public boolean isEmpty() 
	{
		for(ItemStack stack : this.inventory)
		{
			if(!stack.isEmpty()) return false;
		}
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		return (ItemStack)this.inventory.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) 
	{
		return ItemStackHelper.getAndSplit(this.inventory, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) 
	{
		return ItemStackHelper.getAndRemove(this.inventory, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) 
	{
		ItemStack itemstack = (ItemStack)this.inventory.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.inventory.set(index, stack);

		if(stack.getCount() > this.getInventoryStackLimit()) stack.setCount(this.getInventoryStackLimit());
		if(index == 0 && !flag)
		{
			this.totalProcessingTime = AlchemenisizerRecipes.getInstance().getAlchemenisizerItemDuration((ItemStack)this.inventory.get(MAIN_ITEM_SLOT_ID));
			this.processingTime = 0;
			this.markDirty();
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		this.activeTime = compound.getInteger("ActiveTime");
		this.processingTime = compound.getInteger("ProcessingTime");
		this.totalProcessingTime = compound.getInteger("ProcessingTimeTotal");
		this.currentActiveTime = AlchemenisizerRecipes.getInstance().getAlchemenisizerItemDuration((ItemStack)this.inventory.get(MAIN_ITEM_SLOT_ID));

		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setInteger("ActiveTime", (short)this.activeTime);
		compound.setInteger("ProcessingTime", (short)this.processingTime);
		compound.setInteger("ProcessingTimeTotal", (short)this.totalProcessingTime);
		ItemStackHelper.saveAllItems(compound, this.inventory);

		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}

	public boolean isProcessing() 
	{
		return this.activeTime > 0;
	}

	@SideOnly(Side.CLIENT)
	public static boolean isProcessing(IInventory inventory) 
	{
		return inventory.getField(NETHERWART_SLOT_ID) > 0 && inventory.getField(MAIN_ITEM_SLOT_ID) > 0 && inventory.getField(DUST_SLOT_ID) > 0;
	}

	public void update() 
	{
		boolean flag = this.isProcessing();
		boolean flag1 = false;

		if (BlockAlchemenisizer.isPylonNearby(this.world, this.pos) == true)
		{
			if(this.isProcessing()) --this.activeTime;

			if(!this.world.isRemote) 
			{
				//ItemStack stack = (ItemStack)this.inventory.get(2);

				if(this.isProcessing() || (!((((ItemStack)this.inventory.get(NETHERWART_SLOT_ID)).isEmpty()))) && !((((ItemStack)this.inventory.get(MAIN_ITEM_SLOT_ID)).isEmpty())) && !((((ItemStack)this.inventory.get(DUST_SLOT_ID)).isEmpty())))
				{
					if(!this.isProcessing() && this.canProcess()) 
					{
						this.activeTime = AlchemenisizerRecipes.getInstance().getAlchemenisizerItemDuration((ItemStack)this.inventory.get(MAIN_ITEM_SLOT_ID));
						this.currentActiveTime = this.activeTime;
					}
					
					if(this.isProcessing() && this.canProcess()) 
					{
						++this.processingTime;

						if(this.processingTime == this.totalProcessingTime) 
						{
							this.processingTime = 0;
							this.totalProcessingTime = AlchemenisizerRecipes.getInstance().getAlchemenisizerItemDuration((ItemStack)this.inventory.get(MAIN_ITEM_SLOT_ID));
							this.processItem();
							flag1 = true;
						}
					} 
					else this.processingTime = 0;
				} 

				if(flag != this.isProcessing()) 
				{
					flag1 = true;
					BlockAlchemenisizer.setState(this.isProcessing(), this.world, this.pos);
				}
			} 

			incrementPartClocks();
		}

		if(flag1) this.markDirty();
	}

	private boolean canProcess() 
	{
		if(((ItemStack)this.inventory.get(NETHERWART_SLOT_ID)).isEmpty() || ((ItemStack)this.inventory.get(MAIN_ITEM_SLOT_ID)).isEmpty() || ((ItemStack)this.inventory.get(DUST_SLOT_ID)).isEmpty()) return false;
		else 
		{
			//Table<ItemStack, ItemStack, ItemStack> ingrediantsList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
			
			ItemStack result = AlchemenisizerRecipes.getInstance().getAlchemyResult(new ItemStack[] {(ItemStack)this.inventory.get(NETHERWART_SLOT_ID), (ItemStack)this.inventory.get(MAIN_ITEM_SLOT_ID), (ItemStack)this.inventory.get(DUST_SLOT_ID)});	
			if(result.isEmpty()) return false;
			else
			{
				ItemStack output = (ItemStack)this.inventory.get(PRODUCT_SLOT_ID);
				if(output.isEmpty()) return true;
				if(!output.isItemEqual(result)) return false;
				int res = output.getCount() + result.getCount();
				return res <= getInventoryStackLimit() && res <= output.getMaxStackSize();
			}
		}
	}

	public void processItem() 
	{
		if(this.canProcess()) 
		{
			ItemStack netherwartInput = (ItemStack)this.inventory.get(NETHERWART_SLOT_ID);
			ItemStack mainItemInput = (ItemStack)this.inventory.get(MAIN_ITEM_SLOT_ID);
			ItemStack dustInput = (ItemStack)this.inventory.get(DUST_SLOT_ID);
			ItemStack result = AlchemenisizerRecipes.getInstance().getAlchemyResult(new ItemStack[] {netherwartInput, mainItemInput, dustInput});
			ItemStack output = (ItemStack)this.inventory.get(PRODUCT_SLOT_ID);

			if(output.isEmpty()) this.inventory.set(PRODUCT_SLOT_ID, result.copy());
			else if(output.getItem() == result.getItem()) output.grow(result.getCount());

			netherwartInput.shrink(1); mainItemInput.shrink(1); dustInput.shrink(1);
		}
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) 
	{
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) 
	{
		if(index == PRODUCT_SLOT_ID) return false;
		else if(index == NETHERWART_SLOT_ID)
		{
			return AlchemenisizerRecipes.getInstance().itemValidForInputSlot(NETHERWART_SLOT_ID, stack);
		}
		else if(index == MAIN_ITEM_SLOT_ID)
		{
			return AlchemenisizerRecipes.getInstance().itemValidForInputSlot(MAIN_ITEM_SLOT_ID, stack);
		}
		else if (index == DUST_SLOT_ID)
		{
			return AlchemenisizerRecipes.getInstance().itemValidForInputSlot(DUST_SLOT_ID, stack);
		}
		
		return true;
	}

	public String getGuiID() 
	{
		return "kindredlegacy:alchemenisizer";
	}

	@Override
	public int getField(int id) 
	{
		switch(id) 
		{
		case 0:
			return this.activeTime;
		case 1:
			return this.currentActiveTime;
		case 2:
			return this.processingTime;
		case 3:
			return this.totalProcessingTime;
		default:
			return 0;
		}
	}

	@Override
	public void setField(int id, int value) 
	{
		switch(id) 
		{
		case 0:
			this.activeTime = value;
			break;
		case 1:
			this.currentActiveTime = value;
			break;
		case 2:
			this.processingTime = value;
			break;
		case 3:
			this.totalProcessingTime = value;
		}
	}

	@Override
	public int getFieldCount() 
	{
		return 4;
	}

	@Override
	public void clear() 
	{
		this.inventory.clear();
	}

	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	public void incrementPartClocks()
	{
		rotationAnimationClock.incrementClocks();
		
		for(int i = 0; i < verticalShiftIdleAnimationClock.length; i++)
		{
			verticalShiftIdleAnimationClock[i].incrementClocks();
		}
	}

	public IdleAnimationClock getIdleAnimationClockRotation()
	{
		return rotationAnimationClock;
	}

	public IdleAnimationClock getIdleAnimationClockVerticalShift(int partNumber) 
	{
		return verticalShiftIdleAnimationClock[partNumber];
	}
	
	public void setClockDefaults()
	{
		setRotationClockDefaults();
		setVerticalShiftDefaults();
	}
	
	public void setRotationClockDefaults()
	{
		Random rand = new Random();

		int startingClockY = rand.nextInt(100);

		rotationAnimationClock = new IdleAnimationClock(0, 1, 0);

		rotationAnimationClock.setPhaseDurationY(0, 200);

		while(startingClockY > rotationAnimationClock.getTotalDurationLengthY())
		{
			startingClockY -= rotationAnimationClock.getTotalDurationLengthY();
		}

		rotationAnimationClock.setClockY(startingClockY);
	}
	
	public void setVerticalShiftDefaults()
	{
		Random rand = new Random();
		
		int randomInt = rand.nextInt(100);

		for(int i = 0; i < verticalShiftIdleAnimationClock.length; i++)
		{
			verticalShiftIdleAnimationClock[i] = new IdleAnimationClock(0, 1, 0);

			verticalShiftIdleAnimationClock[i].setPhaseDurationY(0, 80);

			float frequencyY = 0.35F;

			int startingClockY = (int)(((float)(verticalShiftIdleAnimationClock.length - i) / (float)verticalShiftIdleAnimationClock.length) * (float)verticalShiftIdleAnimationClock[i].getTotalDurationLengthY() * frequencyY) + randomInt;
			
			while(startingClockY > verticalShiftIdleAnimationClock[i].getTotalDurationLengthY())
			{
				startingClockY -= verticalShiftIdleAnimationClock[i].getTotalDurationLengthY();
			}

			verticalShiftIdleAnimationClock[i].setClockY(startingClockY);
		}
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