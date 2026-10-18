
package br.nebula.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class NebulaKeybinds {

    public static final KeyBinding EDIT_KEY =
        new KeyBinding(
            "key.nebula.edit",
            Keyboard.KEY_C,
            "key.categories.nebula"
        );

    public static final KeyBinding CLICK_GUI_KEY =
        new KeyBinding(
            "key.nebula.clickgui",
            Keyboard.KEY_RSHIFT,
            "key.categories.nebula"
        );

    private static boolean registered = false;

    public static void register() {
        if (!registered) {
            ClientRegistry.registerKeyBinding(EDIT_KEY);
            ClientRegistry.registerKeyBinding(CLICK_GUI_KEY);
            registered = true;
        }
    }
}

