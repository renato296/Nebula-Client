package br.nebula.client;

import net.minecraft.client.gui.GuiScreen;
import java.io.IOException;

public class NebulaEditorScreen extends GuiScreen {

    private int hudX = 20;
    private int hudY = 40;
    private boolean dragging = false;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();

        drawCenteredString(
            fontRendererObj,
            "NEBULA EDITOR",
            width / 2,
            15,
            0x55CCFF
        );

        drawString(
            fontRendererObj,
            "Arraste o texto HUD com o mouse.",
            10,
            height - 30,
            0xFFFFFF
        );

        drawString(
            fontRendererObj,
            "ESC: Fechar editor",
            10,
            height - 18,
            0xAAAAAA
        );

        drawString(
            fontRendererObj,
            "[ NEBULA HUD ]",
            hudX,
            hudY,
            0x55CCFF
        );

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(
        int mouseX, int mouseY, int mouseButton
    ) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        int textWidth = fontRendererObj.getStringWidth(
            "[ NEBULA HUD ]"
        );

        if (mouseButton == 0
                && mouseX >= hudX
                && mouseX <= hudX + textWidth
                && mouseY >= hudY
                && mouseY <= hudY + 10) {
            dragging = true;
        }
    }

    @Override
    protected void mouseClickMove(
        int mouseX,
        int mouseY,
        int clickedMouseButton,
        long timeSinceLastClick
    ) {
        super.mouseClickMove(
            mouseX, mouseY, clickedMouseButton, timeSinceLastClick
        );

        if (dragging) {
            hudX = mouseX;
            hudY = mouseY;
        }
    }

    @Override
    protected void mouseReleased(
        int mouseX, int mouseY, int state
    ) {
        super.mouseReleased(mouseX, mouseY, state);
        dragging = false;
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
