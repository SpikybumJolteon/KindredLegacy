package fuzzyacornindustries.kindredlegacy.tileentity;

import java.util.Random;

import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.block.BlockVerdantPurifier;
import fuzzyacornindustries.kindredlegacy.recipe.VerdantPurifierRecipes;
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

public class TileEntityVerdantPurifier extends TileEntity implements IInventory, ITickable, IAnimatedEntity
{
	private IdleAnimationClock petalsIdleAnimationClock;
	private IdleAnimationClock chamberIdleAnimationClock;
	private IdleAnimationClock chamberShiftIdleAnimationClock;
	private IdleAnimationClock verticalShiftIdleAnimationClock[] = new IdleAnimationClock[2];

	public static final int actionIDNone = 0;

	private int animationID;
	private int animationTick;

	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(2, ItemStack.EMPTY);
	private String customName;

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
	public TileEntityVerdantPurifier()
	{
		animationID = actionIDNone;
		animationTick = 0;

		setClockDefaults();
	}

	@Override
	public String getName() 
	{
		return this.hasCustomName() ? this.customName : "container.verdant_purifier";
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
			this.totalProcessingTime = VerdantPurifierRecipes.getInstance().getVerdantPurifierItemDuration((ItemStack)this.inventory.get(0));
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
		this.currentActiveTime = VerdantPurifierRecipes.getInstance().getVerdantPurifierItemDuration((ItemStack)this.inventory.get(0));

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
		return inventory.getField(0) > 0;
	}

	public void update() 
	{
		boolean flag = this.isProcessing();
		boolean flag1 = false;

		if (BlockVerdantPurifier.isPylonNearby(this.world, this.pos) == true)
		{
			if(this.isProcessing()) --this.activeTime;

			if(!this.world.isRemote) 
			{
				//ItemStack stack = (ItemStack)this.inventory.get(2);

				if(this.isProcessing() || !((((ItemStack)this.inventory.get(0)).isEmpty()))) 
				{
					if(!this.isProcessing() && this.canProcess()) 
					{
						this.activeTime = VerdantPurifierRecipes.getInstance().getVerdantPurifierItemDuration((ItemStack)this.inventory.get(0));
						this.currentActiveTime = this.activeTime;
						/*
						if(this.isProcessing()) 
						{
							flag1 = true;

							if(!stack.isEmpty()) 
							{
								Item item = stack.getItem();
								stack.shrink(1);

								if(stack.isEmpty()) 
								{
									ItemStack item1 = item.getContainerItem(stack);
									this.inventory.set(2, item1);
								}
							}
						}*/
					} 
					if(this.isProcessing() && this.canProcess()) 
					{
						++this.processingTime;

						if(this.processingTime == this.totalProcessingTime) 
						{
							this.processingTime = 0;
							this.totalProcessingTime = VerdantPurifierRecipes.getInstance().getVerdantPurifierItemDuration((ItemStack)this.inventory.get(0));
							this.processItem();
							flag1 = true;
						}
					} 
					else this.processingTime = 0;
				} 

				if(flag != this.isProcessing()) 
				{
					flag1 = true;
					BlockVerdantPurifier.setState(this.isProcessing(), this.world, this.pos);
				}
			} 

			incrementPartClocks();
		}

		if(flag1) this.markDirty();
	}

	private boolean canProcess() 
	{
		if(((ItemStack)this.inventory.get(0)).isEmpty()) return false;
		else 
		{
			ItemStack result = VerdantPurifierRecipes.getInstance().getPurificationResult((ItemStack)this.inventory.get(0));	
			if(result.isEmpty()) return false;
			else
			{
				ItemStack output = (ItemStack)this.inventory.get(1);
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
			ItemStack input = (ItemStack)this.inventory.get(0);
			ItemStack result = VerdantPurifierRecipes.getInstance().getPurificationResult(input);
			ItemStack output = (ItemStack)this.inventory.get(1);

			if(output.isEmpty()) this.inventory.set(1, result.copy());
			else if(output.getItem() == result.getItem()) output.grow(result.getCount());

			input.shrink(1);
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
		if(index == 1) return false;
		else 
		{
			return VerdantPurifierRecipes.getInstance().getPurificationResult((ItemStack)this.inventory.get(0)) != null;
		}
	}

	public String getGuiID() 
	{
		return "kindredlegacy:verdant_purifier";
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
		return 2;
	}

	@Override
	public void clear() 
	{
		this.inventory.clear();
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