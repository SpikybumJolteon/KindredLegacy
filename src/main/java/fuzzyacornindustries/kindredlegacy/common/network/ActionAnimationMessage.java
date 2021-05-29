package fuzzyacornindustries.kindredlegacy.common.network;

import java.util.Optional;
import java.util.function.Supplier;

import fuzzyacornindustries.kindredlegacy.client.animation.IAnimatedEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.LogicalSidedProvider;
import net.minecraftforge.fml.network.NetworkEvent;

public class ActionAnimationMessage 
{
	private byte animationID;
	private int entityID;

	public ActionAnimationMessage(byte animationID, int entityID) 
	{
		this.animationID = animationID;
		this.entityID = entityID;
	}

	public static ActionAnimationMessage decode(final PacketBuffer buffer) 
	{
		CompoundNBT nbt = buffer.readCompoundTag();

		final byte animationID = nbt.getByte("AnimationID");
		final int entityID = nbt.getInt("EntityID");

		return new ActionAnimationMessage(animationID, entityID);
	}

	public static void encode(final ActionAnimationMessage message, final PacketBuffer buffer) 
	{
		CompoundNBT nbt = new CompoundNBT();

		nbt.putByte("AnimationID", message.animationID);
		nbt.putInt("EntityID", message.entityID);

		buffer.writeCompoundTag(nbt);
	}

	public static void handle(final ActionAnimationMessage message, final Supplier<NetworkEvent.Context> ctx) 
	{
		ctx.get().enqueueWork(() -> {
			final Optional<World> optionalWorld = LogicalSidedProvider.CLIENTWORLD.get(ctx.get().getDirection().getReceptionSide());

			optionalWorld.ifPresent(world -> {
				IAnimatedEntity entity = (IAnimatedEntity)world.getEntityByID(message.getEntityID());

				if(entity != null && entity.getAnimationID() != -1) 
				{
					entity.setAnimationID(message.getAnimationID());
					if(message.getAnimationID() == 0) entity.setAnimationTick(0);
				}
			});
		});

		ctx.get().setPacketHandled(true);
	}

	public byte getAnimationID()
	{
		return animationID;
	}

	public int getEntityID()
	{
		return entityID;
	}
}