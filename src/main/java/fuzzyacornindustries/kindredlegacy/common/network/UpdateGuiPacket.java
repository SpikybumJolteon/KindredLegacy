package fuzzyacornindustries.kindredlegacy.common.network;

import java.util.function.Supplier;

import fuzzyacornindustries.kindredlegacy.common.inventory.container.ContainerBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.inventory.container.Container;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * Received on: CLIENT
 *
 * The primary mechanism for sync'ing TE fields to an open GUI.  TE fields annotated with @GuiSynced will be synced
 * in this packet, via {@link ContainerBase#detectAndSendChanges()}.
 * 
 * Based on PneumaticCraft's code 
 */
public class UpdateGuiPacket 
{
    private int syncId;
    private Object value;
    private byte type;

    public UpdateGuiPacket() {
    }

    public UpdateGuiPacket(int syncId, SyncedField<?> syncField) 
    {
        this.syncId = syncId;
        value = syncField.getValue();
        type = SyncedField.getType(syncField);
    }

    public UpdateGuiPacket(PacketBuffer buf) 
    {
        syncId = buf.readVarInt();
        type = buf.readByte();
        value = SyncedField.fromBytes(buf, type);
    }

    public void toBytes(PacketBuffer buf) 
    {
        buf.writeVarInt(syncId);
        buf.writeByte(type);
        SyncedField.toBytes(buf, value, type);
    }

    @SuppressWarnings("rawtypes")
	public void handle(Supplier<NetworkEvent.Context> ctx) 
    {
        ctx.get().enqueueWork(() -> 
        {
            if (Minecraft.getInstance().currentScreen instanceof ContainerScreen) 
            {
                Container container = ((ContainerScreen) Minecraft.getInstance().currentScreen).getContainer();
                if (container instanceof ContainerBase) 
                {
                    ((ContainerBase) container).updateField(syncId, value);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}