
package br.nebula.client;

import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public final class NebulaConfig {

    private static final Properties PROPERTIES = new Properties();
    private static File configFile;

    public static boolean clickGuiEnabled = true;
    public static boolean chatControlEnabled = true;
    public static boolean armorHudEnabled = true;
    public static boolean heldItemHudEnabled = true;
    public static boolean performanceHudEnabled = true;

    // Preferências do Chat Control.
    public static int chatMaxLines = 6;
    public static int chatWidthPercent = 100;
    public static int chatBackgroundAlpha = 65;

    // Preferências visuais do HUD.
    public static int hudX = 20;
    public static int hudY = 40;
    public static int hudScalePercent = 100;

    private NebulaConfig() {
    }

    public static void load() {
        File directory = new File(
            Minecraft.getMinecraft().mcDataDir,
            "config"
        );

        if (!directory.exists() && !directory.mkdirs()) {
            return;
        }

        configFile = new File(directory, "nebula-client.properties");

        if (!configFile.isFile()) {
            save();
            return;
        }

        try (FileInputStream input = new FileInputStream(configFile)) {
            PROPERTIES.clear();
            PROPERTIES.load(input);

            clickGuiEnabled = getBoolean(
                "clickGuiEnabled", clickGuiEnabled
            );
            chatControlEnabled = getBoolean(
                "chatControlEnabled", chatControlEnabled
            );
            armorHudEnabled = getBoolean(
                "armorHudEnabled", armorHudEnabled
            );
            heldItemHudEnabled = getBoolean(
                "heldItemHudEnabled", heldItemHudEnabled
            );
            performanceHudEnabled = getBoolean(
                "performanceHudEnabled", performanceHudEnabled
            );

            chatMaxLines = clamp(
                getInt("chatMaxLines", chatMaxLines), 1, 20
            );
            chatWidthPercent = clamp(
                getInt("chatWidthPercent", chatWidthPercent), 50, 100
            );
            chatBackgroundAlpha = clamp(
                getInt("chatBackgroundAlpha", chatBackgroundAlpha),
                0, 100
            );

            hudX = getInt("hudX", hudX);
            hudY = getInt("hudY", hudY);
            hudScalePercent = clamp(
                getInt("hudScalePercent", hudScalePercent), 50, 150
            );

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void save() {
        if (configFile == null) {
            File directory = new File(
                Minecraft.getMinecraft().mcDataDir,
                "config"
            );

            if (!directory.exists() && !directory.mkdirs()) {
                return;
            }

            configFile = new File(
                directory, "nebula-client.properties"
            );
        }

        PROPERTIES.setProperty(
            "clickGuiEnabled", Boolean.toString(clickGuiEnabled)
        );
        PROPERTIES.setProperty(
            "chatControlEnabled", Boolean.toString(chatControlEnabled)
        );
        PROPERTIES.setProperty(
            "armorHudEnabled", Boolean.toString(armorHudEnabled)
        );
        PROPERTIES.setProperty(
            "heldItemHudEnabled", Boolean.toString(heldItemHudEnabled)
        );
        PROPERTIES.setProperty(
            "performanceHudEnabled", Boolean.toString(performanceHudEnabled)
        );

        PROPERTIES.setProperty("chatMaxLines", Integer.toString(chatMaxLines));
        PROPERTIES.setProperty(
            "chatWidthPercent", Integer.toString(chatWidthPercent)
        );
        PROPERTIES.setProperty(
            "chatBackgroundAlpha", Integer.toString(chatBackgroundAlpha)
        );

        PROPERTIES.setProperty("hudX", Integer.toString(hudX));
        PROPERTIES.setProperty("hudY", Integer.toString(hudY));
        PROPERTIES.setProperty(
            "hudScalePercent", Integer.toString(hudScalePercent)
        );

        try (FileOutputStream output = new FileOutputStream(configFile)) {
            PROPERTIES.store(output, "Nebula Client configuration");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static boolean getBoolean(String key, boolean fallback) {
        return Boolean.parseBoolean(
            PROPERTIES.getProperty(key, Boolean.toString(fallback))
        );
    }

    private static int getInt(String key, int fallback) {
        try {
            return Integer.parseInt(
                PROPERTIES.getProperty(key, Integer.toString(fallback))
            );
        } catch (NumberFormatException exception) {
            return fallback;
        }
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}
