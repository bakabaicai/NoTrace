package com.notrace.client.mixin;

import com.notrace.client.config.MultiplayerCompatibilityConfig;
import net.fabricmc.fabric.impl.networking.AbstractChanneledNetworkAddon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractChanneledNetworkAddon.class)
public class ClientConfigurationMixin {
    @Inject(method = "sendInitialChannelRegistrationPacket", at = @At("HEAD"), cancellable = true)
    private void notrace$skipInitialChannelRegistration(CallbackInfo ci) {
        if (MultiplayerCompatibilityConfig.modifyChannels()) {
            ci.cancel();
        }
    }
}
