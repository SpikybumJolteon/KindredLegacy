package fuzzyacornindustries.kindredlegacy.common.network;

import static fuzzyacornindustries.kindredlegacy.common.network.ILargePayload.MAX_PAYLOAD_SIZE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.PacketDistributor.PacketTarget;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

/**
 * Network Handler code logic based on PneumaticCraft
 **/
public class NetworkHandler 
{
	private static final String PROTOCOL_VERSION = "1";
	private static final SimpleChannel NETWORK = NetworkRegistry.ChannelBuilder
			.named(UtilityFunctions.location("main_channel"))
			.clientAcceptedVersions(PROTOCOL_VERSION::equals)
			.serverAcceptedVersions(PROTOCOL_VERSION::equals)
			.networkProtocolVersion(() -> PROTOCOL_VERSION)
			.simpleChannel();
	private static int det = 0;

	private static int nextId() 
	{
		return det++;
	}

	/*
	 * The integer is the ID of the message, the Side is the side this message will be handled (received) on!
	 */
	public static void init() 
	{
    	registerMessage(ActionAnimationMessage.class,
    			ActionAnimationMessage::encode, ActionAnimationMessage::decode, ActionAnimationMessage::handle);
    	registerMessage(PoketamableNameMessage.class,
    			PoketamableNameMessage::encode, PoketamableNameMessage::decode, PoketamableNameMessage::handle);
    	registerMessage(PokemonExplorationKitMessage.class,
    			PokemonExplorationKitMessage::encode, PokemonExplorationKitMessage::decode, PokemonExplorationKitMessage::handle);
    	registerMessage(PacketMultiHeader.class,
				PacketMultiHeader::toBytes, PacketMultiHeader::new, PacketMultiHeader::handle);
		registerMessage(PacketMultiPart.class,
				PacketMultiPart::toBytes, PacketMultiPart::new, PacketMultiPart::handle);
		registerMessage(SpawnParticlePacket.class,
				SpawnParticlePacket::toBytes, SpawnParticlePacket::new, SpawnParticlePacket::handle);
    	registerMessage(UpdateGuiPacket.class,
    			UpdateGuiPacket::toBytes, UpdateGuiPacket::new, UpdateGuiPacket::handle);
	}

	public static <MSG> void registerMessage(Class<MSG> messageType, BiConsumer<MSG, PacketBuffer> encoder, Function<PacketBuffer, MSG> decoder, BiConsumer<MSG, Supplier<NetworkEvent.Context>> messageConsumer) 
	{
		NETWORK.registerMessage(nextId(), messageType, encoder, decoder, messageConsumer);
	}

	public static void sendToAll(Object message) 
	{
		NETWORK.send(PacketDistributor.ALL.noArg(), message);
	}

	public static void sendToPlayer(Object message, ServerPlayerEntity player) 
	{
		NETWORK.send(PacketDistributor.PLAYER.with(() -> player), message);
	}

	public static void sendToAllAround(LocationIntPacket message, World world, double distance) 
	{
		sendToAllAround(message, message.getTargetPoint(world, distance));
	}

	public static void sendToAllAround(LocationIntPacket message, World world) 
	{
		sendToAllAround(message, message.getTargetPoint(world));
	}

	public static void sendToAllAround(LocationDoublePacket message, World world) 
	{
		sendToAllAround(message, message.getTargetPoint(world));
	}

	public static void sendToTarget(Object message, PacketTarget packetTarget) 
	{
		NETWORK.send(packetTarget, message);
	}

	public static void sendToAllAround(Object message, PacketDistributor.TargetPoint point) 
	{
		if (message instanceof ILargePayload) 
		{
			getSplitMessages((ILargePayload) message).forEach(part -> NETWORK.send(PacketDistributor.NEAR.with(() -> point), part));
		} 
		else 
		{
			NETWORK.send(PacketDistributor.NEAR.with(() -> point), message);
		}
	}
	
    public static void sendPacketToTrakingEntity(Object packetIn, Entity trackingEntityIn) 
    {
    	NETWORK.send(PacketDistributor.TRACKING_ENTITY.with(() -> trackingEntityIn), packetIn);
    }

	public static void sendToDimension(Object message, DimensionType type) 
	{
		NETWORK.send(PacketDistributor.DIMENSION.with(() -> type), message);
	}

	public static void sendToServer(Object message) 
	{
		NETWORK.sendToServer(message);
	}

	/**
	 * Send a packet to all non-local players, which is everyone for a dedicated server, and everyone except the
	 * server owner for an integrated server.
	 * @param packet the packet to send
	 */
	public static void sendNonLocal(Object packet) 
	{
		MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
		if (server.isDedicatedServer()) 
		{
			NetworkHandler.sendToAll(packet);
		} else 
		{
			for (ServerPlayerEntity player : server.getPlayerList().getPlayers()) 
			{
				if (!player.getGameProfile().getName().equals(player.server.getServerOwner())) 
				{
					sendToPlayer(packet, player);
				}
			}
		}
	}

	/**
	 * Send a packet to the player, unless the player is local (i.e. player owner of the integrated server)
	 * @param player the player
	 * @param packet the packet to send
	 */
	public static void sendNonLocal(ServerPlayerEntity player, Object packet) 
	{
		if (player.server.isDedicatedServer() || !player.getGameProfile().getName().equals(player.server.getServerOwner())) 
		{
			sendToPlayer(packet, player);
		}
	}
	
	private static List<Object> getSplitMessages(ILargePayload message) 
	{
		// see PacketMultiHeader#receivePayload for message reassembly
		PacketBuffer buf = message.dumpToBuffer();
        byte[] bytes = buf.array();
        if (buf.writerIndex() < MAX_PAYLOAD_SIZE) 
        {
            return Collections.singletonList(message);
        } 
        else 
        {
            List<Object> messages = new ArrayList<>();
            messages.add(new PacketMultiHeader(buf.writerIndex(), message.getClass().getName()));
            int offset = 0;
            while (offset < buf.writerIndex()) 
            {
                messages.add(new PacketMultiPart(Arrays.copyOfRange(bytes, offset, Math.min(offset + MAX_PAYLOAD_SIZE, buf.writerIndex()))));
                offset += MAX_PAYLOAD_SIZE;
            }
            return messages;
        }
    }
}