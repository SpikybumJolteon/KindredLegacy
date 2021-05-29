package fuzzyacornindustries.kindredlegacy.common.network;

import java.util.function.Supplier;

import fuzzyacornindustries.kindredlegacy.common.item.PoketamableSummonItem;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;
import net.minecraftforge.fml.network.NetworkEvent;

public class PoketamableNameMessage 
{
	private String userName, poketamableName;

	public PoketamableNameMessage(String userName, String poketamableName) 
	{
		this.userName = userName;
		this.poketamableName = poketamableName;
	}

	public static PoketamableNameMessage decode(final PacketBuffer buffer) 
	{
		return new PoketamableNameMessage(buffer.readString(32767), buffer.readString(32767));
	}

	public static void encode(final PoketamableNameMessage message, final PacketBuffer buffer) 
	{
		buffer.writeString(message.userName);
		buffer.writeString(message.poketamableName);
	}
	
	public static void handle(final PoketamableNameMessage message, final Supplier<NetworkEvent.Context> ctx) 
	{
		PoketamableNameMessage packet = message;

        MinecraftServer server = LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER);
        
        server.deferTask(() -> {
            ServerPlayerEntity player = server.getPlayerList().getPlayerByUsername(packet.getUserName());
            if (player != null) 
            {
                if (player.getHeldItemMainhand().getItem() instanceof PoketamableSummonItem) 
                {
                    PoketamableSummonItem.writePoketamableNameToItemStack(player.getHeldItemMainhand(), packet.getPoketamableName());
                    //player.getHeldItemMainhand().setDisplayName(new TranslationTextComponent(packet.getPoketamableName()));
                }
            }
        });

        ctx.get().setPacketHandled(true);
	}

	public String getUserName()
	{
		return userName;
	}
	
	public String getPoketamableName()
	{
		return poketamableName;
	}
}