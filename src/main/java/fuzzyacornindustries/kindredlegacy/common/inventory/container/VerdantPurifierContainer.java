package fuzzyacornindustries.kindredlegacy.common.inventory.container;

import fuzzyacornindustries.kindredlegacy.common.core.ModContainers;
import fuzzyacornindustries.kindredlegacy.common.recipe.ModRecipeType;
import fuzzyacornindustries.kindredlegacy.common.tileentity.VerdantPurifierTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class VerdantPurifierContainer extends ContainerBase<VerdantPurifierTileEntity>
{
	private final VerdantPurifierTileEntity tileEntity;
	private final IInventory purifierInventory;
	//private final IIntArray purifierData;
	protected final World world;
	//private int cookTime, totalCookTime, burnTime, currentBurnTime;
	private final int sizeInventory;

	/* Condensation Item Slot */
	public static final int smeltItemSlotX = 52;
	public static final int smeltItemSlotY = 35;

	/* Product Item Slot */
	public static final int productItemSlotX = 109;
	public static final int productItemSlotY = 35;

	public VerdantPurifierContainer(int windowId, PlayerInventory invPlayer, PacketBuffer extra) 
	{
		this(windowId, invPlayer, getTilePos(extra));
	}

	public VerdantPurifierContainer(int windowId, PlayerInventory invPlayer, BlockPos tePos) 
	{
		super(ModContainers.VERDANT_PURIFIER.get(), windowId, invPlayer, tePos);

		tileEntity = (VerdantPurifierTileEntity) invPlayer.player.world.getTileEntity(tePos);
		sizeInventory = tileEntity.getSizeInventory();
		
		IInventory inventoryIn = new Inventory(2);
		//IIntArray intArray = new IntArray(2);
		assertInventorySize(inventoryIn, 2);
//		assertIntArraySize(tileEntity.getPurifierData(), 2);
//		this.purifierData = tileEntity.getPurifierData();
		this.purifierInventory = inventoryIn;
		this.world = invPlayer.player.world;

//		if(world.isRemote)
//		{
//			Log.info("VerdantPurfierContainer data array 0 " + purifierData.get(0));
//			Log.info("VerdantPurfierContainer data array 1 " + purifierData.get(1));
//		}

		addSlot(new Slot(tileEntity, 0, smeltItemSlotX, smeltItemSlotY));
		addSlot(new Slot(tileEntity, 1, productItemSlotX, productItemSlotY));

		addPlayerSlots(invPlayer, 84);
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int slotIndex)
	{
		ItemStack itemStack1 = ItemStack.EMPTY;
		Slot slot = (Slot)inventorySlots.get(slotIndex);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemStack2 = slot.getStack();
			itemStack1 = itemStack2.copy();

			if (slotIndex == 1)//TileEntityVespeneCondenser.slotEnum.OUTPUT_SLOT.ordinal())
			{
				if (!mergeItemStack(itemStack2, sizeInventory, sizeInventory+36, true))
				{
					return ItemStack.EMPTY;
				}

				slot.onSlotChange(itemStack2, itemStack1);
			}
			else if (slotIndex != 0)//TileEntityVespeneCondenser.slotEnum.INPUT_SLOT.ordinal())
			{
				if (recipeCheck(itemStack2))
				{
					if (!mergeItemStack(itemStack2, 0, 1, false))
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

	protected boolean recipeCheck(ItemStack itemStack) 
	{
		return getOutputForItem(itemStack) != ItemStack.EMPTY;//true;//this.world.getRecipeManager().getRecipe((IRecipeType)this.recipeType, new Inventory(itemStack), this.world).isPresent();
	}

	private ItemStack getOutputForItem(ItemStack input) 
	{
		return ModRecipeType.VERDANT_PURIFYING.stream(world)
				.filter(recipe -> recipe.matches(input))
				.findFirst()
				.map(recipe -> recipe.getOutput().copy())
				.orElse(ItemStack.EMPTY);
	}

	public void fillStackedContents(RecipeItemHelper itemHelperIn) 
	{
		if (this.purifierInventory instanceof IRecipeHelperPopulator)
		{
			((IRecipeHelperPopulator)this.purifierInventory).fillStackedContents(itemHelperIn);
		}
	}

	public void clear() 
	{
		this.purifierInventory.clear();
	}

//	@SuppressWarnings("unchecked")
//	public void func_217056_a(boolean p_217056_1_, IRecipe<?> p_217056_2_, ServerPlayerEntity p_217056_3_) 
//	{
//		(new ServerRecipePlacerFurnace<>(this)).place(p_217056_3_, (IRecipe<IInventory>)p_217056_2_, p_217056_1_);
//	}
//
//	public boolean matches(IRecipe<? super IInventory> recipeIn) 
//	{
//		return recipeIn.matches(this.purifierInventory, this.world);
//	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean matches(@SuppressWarnings("rawtypes") IRecipe recipeIn) 
	{
		return recipeIn.matches(this.purifierInventory, this.world);
	}

	public int getOutputSlot() 
	{
		return 1;
	}

	public int getWidth() 
	{
		return 1;
	}

	public int getHeight() 
	{
		return 1;
	}

	@OnlyIn(Dist.CLIENT)
	public int getSize() 
	{
		return 2;
	}
}