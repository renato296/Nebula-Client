
package br.nebula.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod(
    modid = "nebulaclient",
    name = "Nebula Client",
    version = "0.2.0",
    clientSideOnly = true
)
public class NebulaClient {

    @Mod.EventHandler
    public void init(
        net.minecraftforge.fml.common.event.FMLInitializationEvent event
    ) {
        NebulaConfig.load();

        MinecraftForge.EVENT_BUS.register(this);
        NebulaKeybinds.register();
    }

    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {
        if (event.gui instanceof GuiMainMenu
                && !(event.gui instanceof NebulaMainMenu)) {
            event.gui = new NebulaMainMenu();
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        Minecraft mc = Minecraft.getMinecraft();

        if (mc.thePlayer == null || mc.theWorld == null) {
            return;
        }

        if (mc.currentScreen == null) {
            if (NebulaKeybinds.EDIT_KEY.isPressed()) {
                mc.displayGuiScreen(new NebulaEditorScreen());
                return;
            }

            if (NebulaKeybinds.CLICK_GUI_KEY.isPressed()) {
                mc.displayGuiScreen(new NebulaClickGUI());
            }
        }
    }
}
