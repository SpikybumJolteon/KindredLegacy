package fuzzyacornindustries.kindredlegacy.common.capability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;

/*
 * Original author: Venrob - RobsStuff
 */
public interface IInventoryBackup 
{
    ItemStack[][] getInv();
    void storeInv(PlayerInventory store);
    void storeInv(ItemStack[][] inv);
    void setPlayer(PlayerEntity player);
}