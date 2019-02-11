package fuzzyacornindustries.kindredlegacy.handler;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.item.ItemBowOfKindred;
import fuzzyacornindustries.kindredlegacy.keybind.Keybinds;
import fuzzyacornindustries.kindredlegacy.network.BowOfKindredAutoFire;
import fuzzyacornindustries.kindredlegacy.network.BowOfKindredModeSwitch;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BowOfKindredEvents {
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @SubscribeEvent
    public void hunt(LivingDeathEvent event) {
        if (!(event.getSource().getTrueSource() instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer attacker = (EntityPlayer)event.getSource().getTrueSource();
        EntityLivingBase deceased = event.getEntityLiving();
        ItemStack bowOfKindred = getBowOfKindred(attacker);
        if (bowOfKindred == null) {
            return;
        }
        if(bowOfKindred.hasTagCompound() && bowOfKindred.getTagCompound().hasKey("KillCount")) {
            if (deceased.getMaxHealth() >= 10.0F * Math.exp((float)bowOfKindred.getTagCompound().getInteger("KillCount") * 0.001F)) {
                NBTTagCompound nbt = bowOfKindred.getTagCompound();
                nbt.setInteger("KillCount", nbt.getInteger("KillCount") + 1);
                bowOfKindred.setTagCompound(nbt);
            }
        } else {
            if (deceased.getMaxHealth() >= 10.0F) {
                NBTTagCompound nbt;
                if (bowOfKindred.hasTagCompound()) {
                    nbt = bowOfKindred.getTagCompound();
                }
                else {
                    nbt = new NBTTagCompound();
                }
                nbt.setInteger("KillCount", 1);
                bowOfKindred.setTagCompound(nbt);
            }
        }
    }

    public static ItemStack getBowOfKindred(EntityPlayer entityPlayer) {
        ItemStack bowOfKindred = null;
        if (entityPlayer.getHeldItemMainhand().getItem() instanceof ItemBowOfKindred) {
            bowOfKindred = entityPlayer.getHeldItemMainhand();
        } else if (entityPlayer.getHeldItemOffhand().getItem() instanceof ItemBowOfKindred) {
            bowOfKindred = entityPlayer.getHeldItemOffhand();
        }
        return bowOfKindred;
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void modeSwitch(InputEvent.KeyInputEvent event) {
        if (Keybinds.bowOfKindredModeSwitch.isPressed()) {
            KindredLegacyMain.network.sendToServer(new BowOfKindredModeSwitch());
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void AutoFire(InputEvent.KeyInputEvent event) {
        if (Keybinds.bowOfKindredAutoFire.isPressed()) {
            KindredLegacyMain.network.sendToServer(new BowOfKindredAutoFire());
        }
    }
}
