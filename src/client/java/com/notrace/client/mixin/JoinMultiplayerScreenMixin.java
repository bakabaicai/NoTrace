package com.notrace.client.mixin;

import com.notrace.client.gui.MultiplayerCompatibilityScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(JoinMultiplayerScreen.class)
public abstract class JoinMultiplayerScreenMixin extends Screen {
    protected JoinMultiplayerScreenMixin(Component title) {
        super(title);
    }

    @Inject(method = "init", at = @At("TAIL"))
    private void notrace$addCompatibilitySettingsButton(CallbackInfo ci) {
        this.addRenderableWidget(Button.builder(Component.literal("NoTrace"), button ->
                        this.minecraft.setScreen(new MultiplayerCompatibilityScreen(this)))
                .bounds(5, 6, 100, 20)
                .build());
    }
}
