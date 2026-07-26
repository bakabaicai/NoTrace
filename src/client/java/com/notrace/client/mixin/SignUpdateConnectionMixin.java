package com.notrace.client.mixin;

import com.notrace.client.ServerNetworkingCompatibility;
import com.notrace.client.ServerTranslationContents;
import com.notrace.client.config.MultiplayerCompatibilityConfig;
import io.netty.channel.ChannelFutureListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ServerboundSignUpdatePacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.network.Connection.class)
public class SignUpdateConnectionMixin {
    @Inject(
            method = "send(Lnet/minecraft/network/protocol/Packet;Lio/netty/channel/ChannelFutureListener;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void notrace$blockRegistrationChannels(Packet<?> packet, ChannelFutureListener listener, CallbackInfo ci) {
        if (ServerNetworkingCompatibility.shouldBlock(packet)) {
            ci.cancel();
        }
    }

    @ModifyVariable(
            method = "send(Lnet/minecraft/network/protocol/Packet;Lio/netty/channel/ChannelFutureListener;)V",
            at = @At("HEAD"),
            argsOnly = true
    )
    private Packet<?> notrace$replaceOutgoingPacket(Packet<?> packet) {
        if (MultiplayerCompatibilityConfig.modifyTranslationKeys()
                && packet instanceof ServerboundSignUpdatePacket signUpdate) {
            return ServerTranslationContents.replaceSignUpdate(signUpdate);
        }
        return ServerNetworkingCompatibility.replaceBrand(packet);
    }
}
