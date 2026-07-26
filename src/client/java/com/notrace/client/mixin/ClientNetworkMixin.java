package com.notrace.client.mixin;

import com.notrace.client.config.MultiplayerCompatibilityConfig;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.fabricmc.fabric.impl.networking.client.ClientCommonNetworkAddon")
public class ClientNetworkMixin {
    @Inject(method = "handleRegistration", at = @At("HEAD"), cancellable = true)
    private void notrace$ignoreRegistration(Identifier channel, CallbackInfo ci) {
        if (MultiplayerCompatibilityConfig.modifyChannels()) {
            ci.cancel();
        }
    }

    @Inject(method = "handleUnregistration", at = @At("HEAD"), cancellable = true)
    private void notrace$ignoreUnregistration(Identifier channel, CallbackInfo ci) {
        if (MultiplayerCompatibilityConfig.modifyChannels()) {
            ci.cancel();
        }
    }
}
