package fuzzyacornindustries.kindredlegacy.network;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import io.netty.buffer.ByteBuf;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketAnimation implements IMessage 
{
	private byte animationID;
	private int entityID;

	public PacketAnimation() {}

	public PacketAnimation(byte animationByte, int entity) 
	{
		animationID = animationByte;
		entityID = entity;
	}

	@Override
	public void toBytes(ByteBuf buffer) 
	{
		buffer.writeByte(animationID);
		buffer.writeInt(entityID);
	}

	@Override
	public void fromBytes(ByteBuf buffer) 
	{
		animationID = buffer.readByte();
		entityID = buffer.readInt();
	}

	public static class Handler implements IMessageHandler<PacketAnimation, IMessage> 
	{
		@Override
		public IMessage onMessage(PacketAnimation packet, MessageContext ctx) 
		{
			World world = KindredLegacyMain.proxy.getWorldClient();
			IAnimatedEntity entity = (IAnimatedEntity)world.getEntityByID(packet.entityID);

			if(entity != null && packet.animationID != -1) 
			{
				entity.setAnimationID(packet.animationID);
				if(packet.animationID == 0) entity.setAnimationTick(0);
			}

			return null;
		}
	}
}