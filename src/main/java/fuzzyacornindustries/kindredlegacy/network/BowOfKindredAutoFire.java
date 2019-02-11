package fuzzyacornindustries.kindredlegacy.network;

import fuzzyacornindustries.kindredlegacy.handler.BowOfKindredEvents;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class BowOfKindredAutoFire implements IMessage {

    public BowOfKindredAutoFire() {}

    @Override
    public void toBytes(ByteBuf buffer)
    {
    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
    }

    public static class Handler implements IMessageHandler<BowOfKindredAutoFire, IMessage>
    {
        @Override
        public IMessage onMessage(BowOfKindredAutoFire packet, MessageContext ctx)
        {
            EntityPlayerMP entityPlayer = ctx.getServerHandler().player;
            ItemStack bowOfKindred = BowOfKindredEvents.getBowOfKindred(entityPlayer);
            if (bowOfKindred == null) {
                return null;
            }
            if(bowOfKindred.hasTagCompound() && bowOfKindred.getTagCompound().hasKey("AutoFire")) {
                if (bowOfKindred.getTagCompound().getBoolean("AutoFire")) {
                    bowOfKindred.getTagCompound().setBoolean("AutoFire", false);
                } else {
                    bowOfKindred.getTagCompound().setBoolean("AutoFire", true);
                }
                if (bowOfKindred.getTagCompound().getBoolean("AutoFire")) {
                    entityPlayer.sendMessage(new TextComponentString("Auto Fire: On"));
                } else {
                    entityPlayer.sendMessage(new TextComponentString("Auto Fire: Off"));
                }
            } else {
                NBTTagCompound nbt;
                if (bowOfKindred.hasTagCompound()) {
                    nbt = bowOfKindred.getTagCompound();
                }
                else {
                    nbt = new NBTTagCompound();
                }
                nbt.setBoolean("AutoFire", true);
                bowOfKindred.setTagCompound(nbt);
                entityPlayer.sendMessage(new TextComponentString("Auto Fire: On"));
            }
            return null;
        }
    }
}