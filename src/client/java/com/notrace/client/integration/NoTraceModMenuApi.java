package com.notrace.client.integration;

import com.notrace.client.gui.MultiplayerCompatibilityScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public final class NoTraceModMenuApi implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return MultiplayerCompatibilityScreen::new;
    }
}
