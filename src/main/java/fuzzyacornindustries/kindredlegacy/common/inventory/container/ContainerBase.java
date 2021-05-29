package fuzzyacornindustries.kindredlegacy.common.inventory.container;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import fuzzyacornindustries.kindredlegacy.common.network.GuiSynced;
import fuzzyacornindustries.kindredlegacy.common.network.NetworkHandler;
import fuzzyacornindustries.kindredlegacy.common.network.NetworkUtilities;
import fuzzyacornindustries.kindredlegacy.common.network.PacketUpdateGui;
import fuzzyacornindustries.kindredlegacy.common.network.SyncedField;
import fuzzyacornindustries.kindredlegacy.common.tileentity.TileEntityBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.IContainerListener;
import net.minecraft.inventory.container.RecipeBookContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

/**
 * Based on Pneumaticraft's Container Base 
 */
public abstract class ContainerBase<T extends TileEntityBase> extends RecipeBookContainer<IInventory> 
{
	public final TileEntityBase te;
	private final List<SyncedField<?>> syncedFields = new ArrayList<>();
	private boolean firstTick = true;
	int playerSlotsStart;

	public ContainerBase(ContainerType<?> type, int windowId, PlayerInventory invPlayer, PacketBuffer extraData) 
	{
		this(type, windowId, invPlayer, getTilePos(extraData));
	}

	public ContainerBase(ContainerType<?> type, int windowId, PlayerInventory invPlayer) 
	{
		this(type, windowId, invPlayer, (BlockPos) null);
	}

	@SuppressWarnings("unchecked")
	public ContainerBase(ContainerType<?> type, int windowId, PlayerInventory invPlayer, BlockPos tilePos) 
	{
		super(type, windowId);

		if (tilePos != null) 
		{
			TileEntity te0 = invPlayer.player.world.getTileEntity(tilePos);
			if (te0 instanceof TileEntityBase) 
			{
				//noinspection unchecked
				te = (T) te0;  // should be safe: T extends TileEntityBase, and we're doing an instanceof
				addSyncedFields(te);
			} 
			else 
			{
				te = null;
			}
		} 
		else 
		{
			te = null;
		}
	}
	static BlockPos getTilePos(PacketBuffer extraData) 
	{
		return extraData.readBlockPos();
	}

	void addSyncedField(SyncedField<?> field) 
	{
		syncedFields.add(field);
		field.setLazy(false);
	}

	void addSyncedFields(Object annotatedObject) 
	{
		List<SyncedField<?>> fields = NetworkUtilities.getSyncedFields(annotatedObject, GuiSynced.class);
		for (SyncedField<?> field : fields)
			addSyncedField(field);
	}

	public void updateField(int index, Object value) 
	{
		syncedFields.get(index).setValue(value);
		if (te != null) te.onGuiUpdate();
	}

	@Override
	public boolean canInteractWith(PlayerEntity player) 
	{
		return player.getDistanceSq((double)te.getPos().getX() + 0.5D, (double)te.getPos().getY() + 0.5D, (double)te.getPos().getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void detectAndSendChanges() 
	{
		super.detectAndSendChanges();
		for (int i = 0; i < syncedFields.size(); i++) 
		{
			if (syncedFields.get(i).update() || firstTick) 
			{
				sendToContainerListeners(new PacketUpdateGui(i, syncedFields.get(i)));
			}
		}
		firstTick = false;
	}

	void sendToContainerListeners(Object message) 
	{
//		List<IContainerListener> listeners = ;//ObfuscationReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getInstance(), "field_75149_d");

		for (IContainerListener listener : listeners) 
		{
			if (listener instanceof ServerPlayerEntity) 
			{
				NetworkHandler.sendToPlayer(message, (ServerPlayerEntity) listener);
			}
		}
	}

	protected void addPlayerSlots(PlayerInventory inventoryPlayer, int yOffset) 
	{
		addPlayerSlots(inventoryPlayer, 8, yOffset);
	}

	protected void addPlayerSlots(PlayerInventory inventoryPlayer, int xOffset, int yOffset) 
	{
		playerSlotsStart = inventorySlots.size();

		// Add the player's inventory slots to the container
		for (int inventoryRowIndex = 0; inventoryRowIndex < 3; ++inventoryRowIndex) 
		{
			for (int inventoryColumnIndex = 0; inventoryColumnIndex < 9; ++inventoryColumnIndex) 
			{
				addSlot(new Slot(inventoryPlayer, inventoryColumnIndex + inventoryRowIndex * 9 + 9, xOffset + inventoryColumnIndex * 18, yOffset + inventoryRowIndex * 18));
			}
		}

		// Add the player's action bar slots to the container
		for (int actionBarSlotIndex = 0; actionBarSlotIndex < 9; ++actionBarSlotIndex) 
		{
			addSlot(new Slot(inventoryPlayer, actionBarSlotIndex, xOffset + actionBarSlotIndex * 18, yOffset + 58));
		}
	}

	@Override
	@Nonnull
	public ItemStack transferStackInSlot(PlayerEntity player, int slot) 
	{
		Slot srcSlot = inventorySlots.get(slot);
		if (srcSlot == null || !srcSlot.getHasStack()) 
		{
			return ItemStack.EMPTY;
		}
		ItemStack srcStack = srcSlot.getStack().copy();
		ItemStack copyOfSrcStack = srcStack.copy();

		if (slot < playerSlotsStart) 
		{
			if (!mergeItemStack(srcStack, playerSlotsStart, playerSlotsStart + 36, false))
				return ItemStack.EMPTY;
		} 
		else 
		{
			if (!mergeItemStack(srcStack, 0, playerSlotsStart, false))
				return ItemStack.EMPTY;
		}

		srcSlot.putStack(srcStack);
		srcSlot.onSlotChange(srcStack, copyOfSrcStack);
		srcSlot.onTake(player, srcStack);

		return copyOfSrcStack;
	}

	// almost the same as the super method, but pays attention to slot itemstack limits
	protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection) 
	{
		boolean flag = false;
		int i = startIndex;
		if (reverseDirection) 
		{
			i = endIndex - 1;
		}

		if (stack.isStackable()) 
		{
			while(!stack.isEmpty()) 
			{
				if (reverseDirection) 
				{
					if (i < startIndex) 
					{
						break;
					}
				} 
				else if (i >= endIndex) 
				{
					break;
				}

				Slot slot = this.inventorySlots.get(i);
				ItemStack itemstack = slot.getStack();
				if (!itemstack.isEmpty() && areItemsAndTagsEqual(stack, itemstack)) 
				{
					int j = itemstack.getCount() + stack.getCount();
					// modified HERE
					int maxSize = Math.min(slot.getItemStackLimit(itemstack), Math.min(slot.getSlotStackLimit(), stack.getMaxStackSize()));
					if (j <= maxSize) 
					{
						stack.setCount(0);
						itemstack.setCount(j);
						slot.onSlotChanged();
						flag = true;
					} 
					else if (itemstack.getCount() < maxSize) 
					{
						stack.shrink(maxSize - itemstack.getCount());
						itemstack.setCount(maxSize);
						slot.onSlotChanged();
						flag = true;
					}
				}

				if (reverseDirection) 
				{
					--i;
				} 
				else 
				{
					++i;
				}
			}
		}

		if (!stack.isEmpty()) 
		{
			if (reverseDirection) 
			{
				i = endIndex - 1;
			} else 
			{
				i = startIndex;
			}

			while(true) 
			{
				if (reverseDirection) 
				{
					if (i < startIndex) 
					{
						break;
					}
				} 
				else if (i >= endIndex) 
				{
					break;
				}

				Slot slot1 = this.inventorySlots.get(i);
				ItemStack itemstack1 = slot1.getStack();
				if (itemstack1.isEmpty() && slot1.isItemValid(stack)) 
				{
					// modified HERE
					int limit = Math.min(slot1.getSlotStackLimit(), slot1.getItemStackLimit(stack));
					if (stack.getCount() > limit) 
					{
						slot1.putStack(stack.split(limit));
					} 
					else 
					{
						slot1.putStack(stack.split(stack.getCount()));
					}

					slot1.onSlotChanged();
					flag = true;
					break;
				}

				if (reverseDirection) 
				{
					--i;
				} 
				else 
				{
					++i;
				}
			}
		}

		return flag;
	}

	@Nonnull
	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickType, PlayerEntity player) 
	{
		//Slot slot = slotId < 0 ? null : inventorySlots.get(slotId);

		return super.slotClick(slotId, dragType, clickType, player);

	}

	protected boolean canStacksMerge(ItemStack stack1, ItemStack stack2) 
	{
		return !(stack1.isEmpty() || stack2.isEmpty()) && stack1.isItemEqual(stack2) && ItemStack.areItemStackTagsEqual(stack1, stack2);
	}
	//
	//  @Override
	//  public void handleGUIButtonPress(String tag, boolean shiftHeld, PlayerEntity player) 
	//  {
	//      if (te != null) 
	//      {
	//          te.handleGUIButtonPress(tag, shiftHeld, player);
	//      }
	//  }

}