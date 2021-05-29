package fuzzyacornindustries.kindredlegacy.common.tileentity;

import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;

public abstract class TileEntityBase extends LockableTileEntity
{
    public TileEntityBase(TileEntityType<?> type) 
    {
    	super(type);
    } 
    
    /**
     * Called when a key is synced in the container.
     */
    public void onGuiUpdate() {}
}