package fuzzyacornindustries.kindredlegacy.network;

import fuzzyacornindustries.kindredlegacy.item.tamable.ItemPoketamableSummon;
import fuzzyacornindustries.kindredlegacy.network.NetworkHelper.IPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class PoketamableNamePacket implements IPacket
{
	private String user, poketamableName;

	public PoketamableNamePacket()
	{
	}

	public PoketamableNamePacket(String udata, String ndata)
	{
		user = udata;
		poketamableName = ndata;
	}

	@Override
	public void writeBytes(ChannelHandlerContext ctx, ByteBuf bytes)
	{
		ByteBufUtils.writeUTF8String(bytes, user);
		ByteBufUtils.writeUTF8String(bytes, poketamableName);
	}

	@Override
	public void readBytes(ChannelHandlerContext ctx, ByteBuf bytes)
	{
		user = ByteBufUtils.readUTF8String(bytes);
		poketamableName = ByteBufUtils.readUTF8String(bytes);
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
				if (p.getHeldItemMainhand() != null && p.getHeldItemMainhand().getItem() instanceof ItemPoketamableSummon)
				{
					ItemPoketamableSummon.writePoketamableNameToItemStack(p.getHeldItemMainhand(), poketamableName);
				}
			}
		}
	}
}