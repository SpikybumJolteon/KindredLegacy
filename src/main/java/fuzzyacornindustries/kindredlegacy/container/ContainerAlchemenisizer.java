package fuzzyacornindustries.kindredlegacy.container;

import fuzzyacornindustries.kindredlegacy.recipe.AlchemenisizerRecipes;
import fuzzyacornindustries.kindredlegacy.tileentity.TileEntityAlchemenisizer;
import fuzzyacornindustries.kindredlegacy.tileentity.TileEntityVespeneCondenser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerAlchemenisizer extends Container
{
	private final TileEntityAlchemenisizer tileEntity;
	private int cookTime, totalCookTime, burnTime, currentBurnTime;
    private final int sizeInventory;

	/* Netherwart Item Slot */
	public static final int netherwartSlotX = 64;
	public static final int netherwartSlotY = 17;

	/* Main Item Slot */
	public static final int mainItemSlotX = 56;
	public static final int mainItemSlotY = 37;

	/* Dust Item Slot */
	public static final int dustSlotX = 64;
	public static final int dustSlotY = 57;

	/* Product Item Slot */
	public static final int productItemSlotX = 110;
	public static final int productItemSlotY = 37;
	
	public ContainerAlchemenisizer(InventoryPlayer player, TileEntityAlchemenisizer tileEntity) 
	{
		this.tileEntity = tileEntity;
        sizeInventory = tileEntity.getSizeInventory();

        addSlotToContainer(new Slot(tileEntity, tileEntity.NETHERWART_SLOT_ID, netherwartSlotX, netherwartSlotY));
        addSlotToContainer(new Slot(tileEntity, tileEntity.MAIN_ITEM_SLOT_ID, mainItemSlotX, mainItemSlotY));
        addSlotToContainer(new Slot(tileEntity, tileEntity.DUST_SLOT_ID, dustSlotX, dustSlotY));
        addSlotToContainer(new SlotOutput(player.player, tileEntity, tileEntity.PRODUCT_SLOT_ID, productItemSlotX, productItemSlotY));
        
        // add player inventory slots
        // note that the slot numbers are within the player inventory so can 
        // be same as the tile entity inventory
        int i;
        
        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        // add hotbar slots
        for (i = 0; i < 9; ++i)
        {
            addSlotToContainer(new Slot(player, i, 8 + i * 18, 142));
        }
	}
	
	@Override
	public void addListener(IContainerListener listener) 
	{
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.tileEntity);
	}
	
	@Override
	public void detectAndSendChanges() 
	{
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.listeners.size(); ++i) 
		{
			IContainerListener listener = (IContainerListener)this.listeners.get(i);
			
			if(this.cookTime != this.tileEntity.getField(2)) listener.sendWindowProperty(this, 2, this.tileEntity.getField(2));
			if(this.burnTime != this.tileEntity.getField(0)) listener.sendWindowProperty(this, 0, this.tileEntity.getField(0));
			if(this.currentBurnTime != this.tileEntity.getField(1)) listener.sendWindowProperty(this, 1, this.tileEntity.getField(1));
			if(this.totalCookTime != this.tileEntity.getField(3)) listener.sendWindowProperty(this, 3, this.tileEntity.getField(3));
		}
		
		this.cookTime = this.tileEntity.getField(2);
		this.burnTime = this.tileEntity.getField(0);
		this.currentBurnTime = this.tileEntity.getField(1);
		this.totalCookTime = this.tileEntity.getField(3);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) 
	{
		this.tileEntity.setField(id, data);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) 
	{
		return this.tileEntity.isUsableByPlayer(playerIn);
	}
	
	@Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int slotIndex)
    {
        ItemStack itemStack1 = ItemStack.EMPTY;
        Slot slot = (Slot)inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemStack2 = slot.getStack();
            itemStack1 = itemStack2.copy();

            if (slotIndex == TileEntityAlchemenisizer.PRODUCT_SLOT_ID)//TileEntityVespeneCondenser.slotEnum.OUTPUT_SLOT.ordinal())
            {
                if (!mergeItemStack(itemStack2, sizeInventory, sizeInventory+36, true))
                {
                	return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemStack2, itemStack1);
            }
            else if (slotIndex < 0 || slotIndex > tileEntity.getSizeInventory() - 1)//TileEntityVespeneCondenser.slotEnum.INPUT_SLOT.ordinal())
            {
                if (AlchemenisizerRecipes.getInstance().itemValidForInputSlot(TileEntityAlchemenisizer.NETHERWART_SLOT_ID, itemStack2))
                {
                    if (!mergeItemStack(itemStack2, 0, 1, true))
                    {
                    	return ItemStack.EMPTY;
                    }
                }
                else if (AlchemenisizerRecipes.getInstance().itemValidForInputSlot(TileEntityAlchemenisizer.MAIN_ITEM_SLOT_ID, itemStack2))
                {
                    if (!mergeItemStack(itemStack2, 1, 2, true))
                    {
                    	return ItemStack.EMPTY;
                    }
                }
                else if (AlchemenisizerRecipes.getInstance().itemValidForInputSlot(TileEntityAlchemenisizer.DUST_SLOT_ID, itemStack2))
                {
                    if (!mergeItemStack(itemStack2, 2, 3, true))
                    {
                    	return ItemStack.EMPTY;
                    }
                }
                else if (slotIndex >= sizeInventory && slotIndex < sizeInventory+27) // player inventory slots
                {
                    if (!mergeItemStack(itemStack2, sizeInventory+27, sizeInventory + 36, false))
                    {
                    	return ItemStack.EMPTY;
                    }
                }
                else if (slotIndex >= sizeInventory + 27 && slotIndex < sizeInventory + 36 && !mergeItemStack(itemStack2, sizeInventory + 1, sizeInventory+27, false)) // hotbar slots
                {
                	return ItemStack.EMPTY;
                }
            }
            else if (!mergeItemStack(itemStack2, sizeInventory, sizeInventory + 36, false))
            {
            	return ItemStack.EMPTY;
            }

            if (itemStack2.getCount() == 0)
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemStack2.getCount() == itemStack1.getCount())
            {
            	return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemStack2);
        }

        return itemStack1;
    }
}