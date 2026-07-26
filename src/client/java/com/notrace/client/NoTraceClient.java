package com.notrace.client;

import com.notrace.client.config.MultiplayerCompatibilityConfig;
import net.fabricmc.api.ClientModInitializer;

public class NoTraceClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MultiplayerCompatibilityConfig.load();
    }
}
