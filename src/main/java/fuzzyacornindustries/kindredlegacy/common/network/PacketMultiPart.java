package fuzzyacornindustries.kindredlegacy.common.network;

import java.util.function.Supplier;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * Received on: SERVER
 * Part of a multipart mesage from client (whole message too big to send at once)
 * 
 * Based on Pneumaticraft Code
 */
public class PacketMultiPart 
{
    private byte[] payload;

    public PacketMultiPart() 
    {
    }

    PacketMultiPart(byte[] payload) 
    {
        this.payload = payload;
    }

    PacketMultiPart(PacketBuffer buf) 
    {
        payload = new byte[buf.readInt()];
        buf.readBytes(payload);
    }

    public void toBytes(PacketBuffer buf) 
    {
        buf.writeInt(payload.length);
        buf.writeBytes(payload);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) 
    {
        ctx.get().enqueueWork(() -> PacketMultiHeader.receivePayload(ctx.get().getSender(), payload));
        ctx.get().setPacketHandled(true);
    }
}