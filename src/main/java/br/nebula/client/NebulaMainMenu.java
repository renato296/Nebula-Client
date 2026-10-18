
package br.nebula.client;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;

import java.io.IOException;

public class NebulaMainMenu extends GuiScreen {

    @Override
    public void initGui() {
        buttonList.clear();

        int w = 150;
        int h = 20;
        int x = width - w - 15;
        int y = height / 2 - 35;

        buttonList.add(new GuiButton(0, x, y, w, h, "Singleplayer"));
        buttonList.add(new GuiButton(1, x, y + 25, w, h, "Multiplayer"));
        buttonList.add(new GuiButton(2, x, y + 50, w, h, "Options"));
        buttonList.add(new GuiButton(3, x, y + 75, w, h, "Sair"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();

        drawCenteredString(
            fontRendererObj,
            "NEBULA CLIENT",
            width / 2,
            height / 4,
            0x55CCFF
        );

        drawCenteredString(
            fontRendererObj,
            "Minecraft 1.8.8",
            width / 2,
            height / 4 + 20,
            0xFFFFFF
        );

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 0:
                mc.displayGuiScreen(new GuiSelectWorld(this));
                break;
            case 1:
                mc.displayGuiScreen(new GuiMultiplayer(this));
                break;
            case 2:
                mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
                break;
            case 3:
                mc.shutdown();
                break;
        }
    }
}
