package fuzzyacornindustries.kindredlegacy.network;

import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class KindredLegacyNetwork 
{
	public static final ResourceLocation CHANNEL_NAME = UtilityFunctions.location("network");

	public static final String NETWORK_VERSION = UtilityFunctions.location("1").toString();

	public static SimpleChannel getNetworkChannel() 
	{
		final SimpleChannel channel = NetworkRegistry.ChannelBuilder.named(CHANNEL_NAME)
				.clientAcceptedVersions(version -> true)
				.serverAcceptedVersions(version -> true)
				.networkProtocolVersion(() -> NETWORK_VERSION)
				.simpleChannel();
		
		channel.messageBuilder(PoketamableNameMessage.class, 1)
		.decoder(PoketamableNameMessage::decode)
		.encoder(PoketamableNameMessage::encode)
		.consumer(PoketamableNameMessage::handle)
		.add();

		channel.messageBuilder(ActionAnimationMessage.class, 2)
		.decoder(ActionAnimationMessage::decode)
		.encoder(ActionAnimationMessage::encode)
		.consumer(ActionAnimationMessage::handle)
		.add();

		channel.messageBuilder(PokemonExplorationKitMessage.class, 3)
		.decoder(PokemonExplorationKitMessage::decode)
		.encoder(PokemonExplorationKitMessage::encode)
		.consumer(PokemonExplorationKitMessage::handle)
		.add();

		return channel;
	}
}