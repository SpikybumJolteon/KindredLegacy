package fuzzyacornindustries.kindredlegacy.keybind;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class Keybinds {
    public static KeyBinding bowOfKindredModeSwitch;
    public static KeyBinding bowOfKindredAutoFire;

    public static void register() {
        bowOfKindredModeSwitch = new KeyBinding("key.bowofkindredmodeswitch", Keyboard.KEY_V, "key.categories.kindredlegacy");
        bowOfKindredAutoFire = new KeyBinding("key.bowofkindredautofire", Keyboard.KEY_B, "key.categories.kindredlegacy");

        ClientRegistry.registerKeyBinding(bowOfKindredModeSwitch);
        ClientRegistry.registerKeyBinding(bowOfKindredAutoFire);
    }
}
