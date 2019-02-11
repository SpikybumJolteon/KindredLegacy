package fuzzyacornindustries.kindredlegacy.network;

import fuzzyacornindustries.kindredlegacy.handler.BowOfKindredEvents;
import fuzzyacornindustries.kindredlegacy.item.ItemBowOfKindred;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class BowOfKindredModeSwitch implements IMessage {

    public BowOfKindredModeSwitch() {}

    @Override
    public void toBytes(ByteBuf buffer)
    {
    }

    @Override
    public void fromBytes(ByteBuf buffer)
    {
    }

    public static class Handler implements IMessageHandler<BowOfKindredModeSwitch, IMessage>
    {
        @Override
        public IMessage onMessage(BowOfKindredModeSwitch packet, MessageContext ctx)
        {
            EntityPlayerMP entityPlayer = ctx.getServerHandler().player;
            ItemStack bowOfKindred = BowOfKindredEvents.getBowOfKindred(entityPlayer);
            if (bowOfKindred == null) {
                return null;
            }
            if(bowOfKindred.hasTagCompound() && bowOfKindred.getTagCompound().hasKey("Mode")) {
                if (bowOfKindred.getTagCompound().getInteger("Mode") == ItemBowOfKindred.MODE_COUNT) {
                    bowOfKindred.getTagCompound().setInteger("Mode", 1);
                } else {
                    bowOfKindred.getTagCompound().setInteger("Mode", bowOfKindred.getTagCompound().getInteger("Mode") + 1);
                }
                if (bowOfKindred.getTagCompound().getInteger("Mode") == ItemBowOfKindred.SINGLE_SHOT) {
                    entityPlayer.sendMessage(new TextComponentString("Current Mode: Single Shot"));
                } else if (bowOfKindred.getTagCompound().getInteger("Mode") == ItemBowOfKindred.MOB_TRACKING) {
                    entityPlayer.sendMessage(new TextComponentString("Current Mode: Mob Tracking"));
                }
            } else {
                NBTTagCompound nbt;
                if (bowOfKindred.hasTagCompound()) {
                    nbt = bowOfKindred.getTagCompound();
                }
                else {
                    nbt = new NBTTagCompound();
                }
                nbt.setInteger("Mode", ItemBowOfKindred.SINGLE_SHOT);
                bowOfKindred.setTagCompound(nbt);
                entityPlayer.sendMessage(new TextComponentString("Current Mode: Single Shot"));
            }
            return null;
        }
    }
}
