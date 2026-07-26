package com.notrace.client.mixin;

import com.notrace.client.ServerTranslationContents;
import com.notrace.client.config.MultiplayerCompatibilityConfig;
import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreen.class)
public class AnvilScreenMixin {
    @Inject(method = "slotChanged", at = @At("HEAD"))
    private void notrace$markItemNameTranslations(AbstractContainerMenu menu, int slot, ItemStack stack, CallbackInfo ci) {
        if (MultiplayerCompatibilityConfig.modifyTranslationKeys()
                && slot == AnvilMenu.INPUT_SLOT
                && !stack.isEmpty()) {
            ServerTranslationContents.markComponent(stack.getHoverName());
        }
    }
}
