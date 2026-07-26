package com.notrace.client.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.notrace.NoTrace;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Client-only settings stored in Fabric's standard config directory.
 */
public final class MultiplayerCompatibilityConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE = FabricLoader.getInstance()
            .getConfigDir()
            .resolve("notrace")
            .resolve("multiplayer-compatibility.json");

    private static Values values = new Values();

    private MultiplayerCompatibilityConfig() {
    }

    public static boolean advancedMode() {
        return values.advancedMode;
    }

    public static void toggleAdvancedMode() {
        values.advancedMode = !values.advancedMode;
        if (!values.advancedMode) {
            setAllEnabled(true);
        }
        save();
    }

    public static boolean modifyBrand() {
        return values.modifyBrand;
    }

    public static void toggleModifyBrand() {
        values.modifyBrand = !values.modifyBrand;
        save();
    }

    public static boolean modifyChannels() {
        return values.modifyChannels;
    }

    public static void toggleModifyChannels() {
        values.modifyChannels = !values.modifyChannels;
        save();
    }

    public static boolean modifyTranslationKeys() {
        return values.modifyTranslationKeys;
    }

    public static void toggleModifyTranslationKeys() {
        values.modifyTranslationKeys = !values.modifyTranslationKeys;
        save();
    }

    public static boolean allEnabled() {
        return values.modifyBrand && values.modifyChannels && values.modifyTranslationKeys;
    }

    public static void setAllEnabled(boolean enabled) {
        values.modifyBrand = enabled;
        values.modifyChannels = enabled;
        values.modifyTranslationKeys = enabled;
    }

    public static void toggleAllEnabled() {
        setAllEnabled(!allEnabled());
        save();
    }

    public static void load() {
        if (!Files.exists(FILE)) {
            save();
            return;
        }

        try (Reader reader = Files.newBufferedReader(FILE, StandardCharsets.UTF_8)) {
            Values loaded = GSON.fromJson(reader, Values.class);
            if (loaded != null) {
                values = loaded;
            }
        } catch (IOException | JsonParseException exception) {
            NoTrace.LOGGER.warn("Could not load multiplayer compatibility settings from {}", FILE, exception);
        }
    }

    public static void save() {
        try {
            Files.createDirectories(FILE.getParent());
            try (Writer writer = Files.newBufferedWriter(FILE, StandardCharsets.UTF_8)) {
                GSON.toJson(values, writer);
            }
        } catch (IOException exception) {
            NoTrace.LOGGER.warn("Could not save multiplayer compatibility settings to {}", FILE, exception);
        }
    }

    private static final class Values {
        private boolean advancedMode;
        private boolean modifyBrand = true;
        private boolean modifyChannels = true;
        private boolean modifyTranslationKeys = true;
    }
}
