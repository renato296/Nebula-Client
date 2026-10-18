
package br.nebula.client;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(
    modid = "nebulaclient",
    name = "Nebula Client",
    version = "0.1.0",
    clientSideOnly = true
)
public class NebulaClient {

    @Mod.EventHandler
    public void init(
        net.minecraftforge.fml.common.event.FMLInitializationEvent event
    ) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {
        if (event.gui instanceof GuiMainMenu
                && !(event.gui instanceof NebulaMainMenu)) {
            event.gui = new NebulaMainMenu();
        }
    }
}
NebulaKeybinds.register();
