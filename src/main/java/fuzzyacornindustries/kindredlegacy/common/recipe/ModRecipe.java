package fuzzyacornindustries.kindredlegacy.common.recipe;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * Base class for all Kindred Legacy machine recipes, which are registered in the vanilla RecipeManager.
 * 
 * Based on PnumaticCraft's style
 */
public abstract class ModRecipe implements IRecipe<ModRecipe.DummyIInventory> 
{
    private final ResourceLocation id;

    protected ModRecipe(ResourceLocation id) 
    {
        this.id = id;
    }

    /**
     * Writes this recipe to a PacketBuffer.
     *
     * @param buffer The buffer to write to.
     */
    public abstract void write(PacketBuffer buffer);

    @Override
    public boolean matches(DummyIInventory inv, World worldIn) 
    {
        return true;
    }

    @Override
    public ItemStack getCraftingResult(DummyIInventory inv) 
    {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canFit(int width, int height) 
    {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() 
    {
        return ItemStack.EMPTY;
    }

    @Override
    public ResourceLocation getId() 
    {
        return id;
    }

    @Override
    public boolean isDynamic() 
    {
        return true;
    }

    public static class DummyIInventory implements IInventory 
    {
        private static final DummyIInventory INSTANCE = new DummyIInventory();

        public static DummyIInventory getInstance() 
        {
            return INSTANCE;
        }

        @Override
        public int getSizeInventory() 
        {
            return 0;
        }

        @Override
        public boolean isEmpty() 
        {
            return true;
        }

        @Override
        public ItemStack getStackInSlot(int index) 
        {
            return ItemStack.EMPTY;
        }

        @Override
        public ItemStack decrStackSize(int index, int count) 
        {
            return ItemStack.EMPTY;
        }

        @Override
        public ItemStack removeStackFromSlot(int index) 
        {
            return ItemStack.EMPTY;
        }

        @Override
        public void setInventorySlotContents(int index, ItemStack stack) {}

        @Override
        public void markDirty() {}

        @Override
        public boolean isUsableByPlayer(PlayerEntity player) 
        {
            return false;
        }

        @Override
        public void clear() {}
    }
}