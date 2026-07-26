package com.notrace.client;

import com.notrace.client.config.MultiplayerCompatibilityConfig;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.custom.BrandPayload;

public final class ServerNetworkingCompatibility {
    private ServerNetworkingCompatibility() {
    }

    public static boolean shouldBlock(Packet<?> packet) {
        if (!MultiplayerCompatibilityConfig.modifyChannels()
                || !(packet instanceof ServerboundCustomPayloadPacket customPayload)) {
            return false;
        }

        String channel = customPayload.payload().type().id().toString();
        return channel.equals("minecraft:register") || channel.equals("minecraft:unregister");
    }

    public static Packet<?> replaceBrand(Packet<?> packet) {
        if (MultiplayerCompatibilityConfig.modifyBrand()
                && packet instanceof ServerboundCustomPayloadPacket customPayload
                && customPayload.payload() instanceof BrandPayload) {
            return new ServerboundCustomPayloadPacket(new BrandPayload("vanilla"));
        }
        return packet;
    }
}
