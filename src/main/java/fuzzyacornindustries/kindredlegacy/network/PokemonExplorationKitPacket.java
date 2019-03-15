package fuzzyacornindustries.kindredlegacy.network;

import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemPokemonExplorationKit;
import fuzzyacornindustries.kindredlegacy.network.NetworkHelper.IPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class PokemonExplorationKitPacket implements IPacket
{
	private String user;
	private ItemStack poketamableItemStack;

	public PokemonExplorationKitPacket()
	{
	}

	public PokemonExplorationKitPacket(String udata, ItemStack idata)
	{
		user = udata;
		poketamableItemStack = idata;
	}

	@Override
	public void writeBytes(ChannelHandlerContext ctx, ByteBuf bytes)
	{
		ByteBufUtils.writeUTF8String(bytes, user);
		ByteBufUtils.writeItemStack(bytes, poketamableItemStack);
	}

	@Override
	public void readBytes(ChannelHandlerContext ctx, ByteBuf bytes)
	{
		user = ByteBufUtils.readUTF8String(bytes);
		poketamableItemStack = ByteBufUtils.readItemStack(bytes);
		FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(new ScheduledCode());
	}

	class ScheduledCode implements Runnable
	{
		@Override
		public void run()
		{
			EntityPlayerMP p = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(user);
			if (p != null)
			{
				if (p.getHeldItemMainhand() != null && p.getHeldItemMainhand().getItem() instanceof ItemPokemonExplorationKit)
				{
					p.getHeldItemMainhand().shrink(1);
					
					if(!p.inventory.addItemStackToInventory(poketamableItemStack))
						p.world.spawnEntity(new EntityItem(p.world, p.posX, p.posY, p.posZ, poketamableItemStack));
					if(!p.inventory.addItemStackToInventory(new ItemStack(KindredLegacyItems.ORANIAN_BERRY, 5)))
						p.world.spawnEntity(new EntityItem(p.world, p.posX, p.posY, p.posZ, new ItemStack(KindredLegacyItems.ORANIAN_BERRY, 5)));
					if(!p.inventory.addItemStackToInventory(new ItemStack(KindredLegacyItems.REVIVE_SEED)))
						p.world.spawnEntity(new EntityItem(p.world, p.posX, p.posY, p.posZ, new ItemStack(KindredLegacyItems.REVIVE_SEED)));
					if(!p.inventory.addItemStackToInventory(new ItemStack(KindredLegacyItems.ATTACK_ORDERER)))
						p.world.spawnEntity(new EntityItem(p.world, p.posX, p.posY, p.posZ, new ItemStack(KindredLegacyItems.ATTACK_ORDERER)));
					if(!p.inventory.addItemStackToInventory(new ItemStack(KindredLegacyItems.ESSENCE_RECALLER)))
						p.world.spawnEntity(new EntityItem(p.world, p.posX, p.posY, p.posZ, new ItemStack(KindredLegacyItems.ESSENCE_RECALLER)));
				}
			}
		}
	}
}