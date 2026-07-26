package com.notrace.client.mixin;

import com.notrace.client.ServerTranslationContents;
import com.notrace.client.config.MultiplayerCompatibilityConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.game.ClientboundOpenSignEditorPacket;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.multiplayer.ClientPacketListener.class)
public class ClientPacketListenerMixin {
    @Inject(method = "handleOpenSignEditor", at = @At("HEAD"))
    private void notrace$trackSignTranslations(ClientboundOpenSignEditorPacket packet, CallbackInfo ci) {
        Minecraft client = Minecraft.getInstance();
        if (MultiplayerCompatibilityConfig.modifyTranslationKeys()
                && client.level != null
                && client.level.getBlockEntity(packet.getPos()) instanceof SignBlockEntity sign) {
            ServerTranslationContents.trackSignText(packet.getPos(), packet.isFrontText(), sign.getText(packet.isFrontText()));
        }
    }
}
