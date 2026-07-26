package com.notrace.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class ScreenNavigator {
    private ScreenNavigator() {
    }

    public static void show(Minecraft minecraft, Screen screen) {
        Method method = findMethod(minecraft.getClass(), "setScreen");
        if (method == null) {
            method = findMethod(minecraft.getClass(), "setScreenAndShow");
        }
        if (method == null) {
            throw new IllegalStateException("Could not find a screen navigation method");
        }

        try {
            method.invoke(minecraft, screen);
        } catch (IllegalAccessException | InvocationTargetException exception) {
            throw new IllegalStateException("Could not open screen", exception);
        }
    }

    private static Method findMethod(Class<?> minecraftClass, String name) {
        try {
            return minecraftClass.getMethod(name, Screen.class);
        } catch (NoSuchMethodException ignored) {
            return null;
        }
    }
}
