package fuzzyacornindustries.kindredlegacy.network;

import java.util.function.Supplier;

import fuzzyacornindustries.kindredlegacy.item.tamable.PokemonExplorationKitItem;
import fuzzyacornindustries.kindredlegacy.lists.KindredLegacyItems;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;
import net.minecraftforge.fml.network.NetworkEvent;

public class PokemonExplorationKitMessage 
{
	private String userName;
	private ItemStack poketamableItemStack;
	
	public PokemonExplorationKitMessage(String nameData, ItemStack idata) 
	{
		userName = nameData;
		poketamableItemStack = idata;
	}

	public static PokemonExplorationKitMessage decode(final PacketBuffer buffer) 
	{
		return new PokemonExplorationKitMessage(buffer.readString(32767), buffer.readItemStack());
	}

	public static void encode(final PokemonExplorationKitMessage message, final PacketBuffer buffer) 
	{
		buffer.writeString(message.userName);
		buffer.writeItemStack(message.poketamableItemStack);
	}
	
	public static void handle(final PokemonExplorationKitMessage message, final Supplier<NetworkEvent.Context> ctx) 
	{
		PokemonExplorationKitMessage packet = message;

        MinecraftServer server = LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER);
        
        server.deferTask(() -> {
            ServerPlayerEntity player = server.getPlayerList().getPlayerByUsername(packet.getUserName());
            if (player != null)
			{
				if (player.getHeldItemMainhand().getItem() instanceof PokemonExplorationKitItem)
				{
					player.getHeldItemMainhand().shrink(1);
					
					if(!player.inventory.addItemStackToInventory(message.getItemStack()))
						player.world.addEntity(new ItemEntity(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), message.getItemStack()));
					if(!player.inventory.addItemStackToInventory(new ItemStack(KindredLegacyItems.ORANIAN_BERRY, 5)))
						player.world.addEntity(new ItemEntity(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), new ItemStack(KindredLegacyItems.ORANIAN_BERRY, 5)));
					if(!player.inventory.addItemStackToInventory(new ItemStack(KindredLegacyItems.REVIVE_SEED)))
						player.world.addEntity(new ItemEntity(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), new ItemStack(KindredLegacyItems.REVIVE_SEED)));
					if(!player.inventory.addItemStackToInventory(new ItemStack(KindredLegacyItems.ATTACK_ORDERER)))
						player.world.addEntity(new ItemEntity(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), new ItemStack(KindredLegacyItems.ATTACK_ORDERER)));
					if(!player.inventory.addItemStackToInventory(new ItemStack(KindredLegacyItems.ESSENCE_RECALLER)))
						player.world.addEntity(new ItemEntity(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), new ItemStack(KindredLegacyItems.ESSENCE_RECALLER)));
					if(!player.inventory.addItemStackToInventory(new ItemStack(KindredLegacyItems.PHOENIX_HEARTHSTONE)))
						player.world.addEntity(new ItemEntity(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), new ItemStack(KindredLegacyItems.PHOENIX_HEARTHSTONE)));
				}
			}
        });

        ctx.get().setPacketHandled(true);
	}

	public String getUserName()
	{
		return userName;
	}

	public ItemStack getItemStack()
	{
		return poketamableItemStack;
	}
}