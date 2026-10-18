
package br.nebula.client;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

public class NebulaClickGUI extends GuiScreen {

    private static final int BLUE = 0x55CCFF;

    @Override
    public void initGui() {
        buttonList.clear();

        int w = 220;
        int h = 20;
        int x = (width - w) / 2;
        int y = 45;

        buttonList.add(new GuiButton(
            1, x, y, w, h,
            "Chat Control: " + status(NebulaConfig.chatControlEnabled)
        ));

        buttonList.add(new GuiButton(
            2, x, y + 25, w, h,
            "Armor HUD: " + status(NebulaConfig.armorHudEnabled)
        ));

        buttonList.add(new GuiButton(
            3, x, y + 50, w, h,
            "Held Item HUD: " + status(NebulaConfig.heldItemHudEnabled)
        ));

        buttonList.add(new GuiButton(
            4, x, y + 75, w, h,
            "Performance HUD: " + status(NebulaConfig.performanceHudEnabled)
        ));

        buttonList.add(new GuiButton(
            5, x, y + 110, w, h,
            "Chat: menos linhas (" + NebulaConfig.chatMaxLines + ")"
        ));

        buttonList.add(new GuiButton(
            6, x, y + 135, w, h,
            "Largura do chat: " + NebulaConfig.chatWidthPercent + "%"
        ));

        buttonList.add(new GuiButton(
            7, x, y + 160, w, h,
            "Opacidade do chat: " + NebulaConfig.chatBackgroundAlpha + "%"
        ));

        buttonList.add(new GuiButton(
            8, x, y + 190, w, h,
            "Restaurar padrões"
        ));

        buttonList.add(new GuiButton(
            9, x, y + 215, w, h,
            "Fechar"
        ));
    }

    private static String status(boolean enabled) {
        return enabled ? "ON" : "OFF";
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 1:
                NebulaConfig.chatControlEnabled =
                    !NebulaConfig.chatControlEnabled;
                break;

            case 2:
                NebulaConfig.armorHudEnabled =
                    !NebulaConfig.armorHudEnabled;
                break;

            case 3:
                NebulaConfig.heldItemHudEnabled =
                    !NebulaConfig.heldItemHudEnabled;
                break;

            case 4:
                NebulaConfig.performanceHudEnabled =
                    !NebulaConfig.performanceHudEnabled;
                break;

            case 5:
                NebulaConfig.chatMaxLines =
                    NebulaConfig.chatMaxLines >= 12
                        ? 3
                        : NebulaConfig.chatMaxLines + 3;
                break;

            case 6:
                NebulaConfig.chatWidthPercent =
                    NebulaConfig.chatWidthPercent >= 100
                        ? 50
                        : NebulaConfig.chatWidthPercent + 10;
                break;

            case 7:
                NebulaConfig.chatBackgroundAlpha =
                    NebulaConfig.chatBackgroundAlpha >= 100
                        ? 0
                        : NebulaConfig.chatBackgroundAlpha + 25;
                break;

            case 8:
                NebulaConfig.chatControlEnabled = true;
                NebulaConfig.armorHudEnabled = true;
                NebulaConfig.heldItemHudEnabled = true;
                NebulaConfig.performanceHudEnabled = true;
                NebulaConfig.chatMaxLines = 6;
                NebulaConfig.chatWidthPercent = 100;
                NebulaConfig.chatBackgroundAlpha = 65;
                NebulaConfig.hudX = 20;
                NebulaConfig.hudY = 40;
                NebulaConfig.hudScalePercent = 100;
                break;

            case 9:
                NebulaConfig.save();
                mc.displayGuiScreen(null);
                return;
        }

        NebulaConfig.save();
        initGui();
    }

    @Override
    public void drawScreen(
        int mouseX, int mouseY, float partialTicks
    ) {
        drawDefaultBackground();

        drawCenteredString(
            fontRendererObj,
            "NEBULA CLIENT",
            width / 2,
            15,
            BLUE
        );

        drawCenteredString(
            fontRendererObj,
            "CLICKGUI - COSMIC BLUE",
            width / 2,
            28,
            0xFFFFFF
        );

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
