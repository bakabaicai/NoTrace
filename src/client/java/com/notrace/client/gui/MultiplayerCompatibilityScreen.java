package com.notrace.client.gui;

import com.notrace.client.config.MultiplayerCompatibilityConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class MultiplayerCompatibilityScreen extends Screen {
    private final Screen previousScreen;

    public MultiplayerCompatibilityScreen(Screen previousScreen) {
        super(Component.literal(titleText()));
        this.previousScreen = previousScreen;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int buttonWidth = Math.min(400, this.width - 20);
        int buttonX = centerX - buttonWidth / 2;
        int firstButtonY = this.height / 2 - 48;

        this.addRenderableWidget(Button.builder(modeLabel(), button -> {
            MultiplayerCompatibilityConfig.toggleAdvancedMode();
            ScreenNavigator.show(this.minecraft, new MultiplayerCompatibilityScreen(previousScreen));
        }).bounds(buttonX, firstButtonY, buttonWidth, 20).build());

        if (MultiplayerCompatibilityConfig.advancedMode()) {
            addAdvancedSettings(buttonX, firstButtonY + 24, buttonWidth);
        } else {
            this.addRenderableWidget(Button.builder(oneClickLabel(), button -> {
                MultiplayerCompatibilityConfig.toggleAllEnabled();
                button.setMessage(oneClickLabel());
            }).bounds(buttonX, firstButtonY + 24, buttonWidth, 20).build());
        }

        this.addRenderableWidget(Button.builder(Component.literal(chinese() ? "完成" : "Done"), button -> this.onClose())
                .bounds(centerX - 100, this.height - 28, 200, 20).build());
    }

    private void addAdvancedSettings(int x, int y, int width) {
        this.addRenderableWidget(Button.builder(brandLabel(), button -> {
            MultiplayerCompatibilityConfig.toggleModifyBrand();
            button.setMessage(brandLabel());
        }).bounds(x, y, width, 20).build());
        this.addRenderableWidget(Button.builder(channelLabel(), button -> {
            MultiplayerCompatibilityConfig.toggleModifyChannels();
            button.setMessage(channelLabel());
        }).bounds(x, y + 24, width, 20).build());
        this.addRenderableWidget(Button.builder(translationKeyLabel(), button -> {
            MultiplayerCompatibilityConfig.toggleModifyTranslationKeys();
            button.setMessage(translationKeyLabel());
        }).bounds(x, y + 48, width, 20).build());
    }

    @Override
    public void onClose() {
        ScreenNavigator.show(this.minecraft, previousScreen);
    }

    private static Component modeLabel() {
        return Component.literal(chinese()
                ? "设置模式：" + (MultiplayerCompatibilityConfig.advancedMode() ? "高级" : "简单")
                : "Settings Mode: " + (MultiplayerCompatibilityConfig.advancedMode() ? "Advanced" : "Simple"));
    }

    private static Component oneClickLabel() {
        return toggleLabel(chinese() ? "一键隐藏客户端痕迹" : "Hide Client Traces", MultiplayerCompatibilityConfig.allEnabled());
    }

    private static Component brandLabel() {
        return toggleLabel(chinese() ? "替换客户端品牌" : "Replace Client Brand", MultiplayerCompatibilityConfig.modifyBrand());
    }

    private static Component channelLabel() {
        return toggleLabel(chinese() ? "阻止频道注册" : "Block Channel Registration", MultiplayerCompatibilityConfig.modifyChannels());
    }

    private static Component translationKeyLabel() {
        return toggleLabel(chinese() ? "处理翻译键" : "Handle Translation Keys", MultiplayerCompatibilityConfig.modifyTranslationKeys());
    }

    private static Component toggleLabel(String name, boolean enabled) {
        return Component.literal(name + "：" + (enabled ? (chinese() ? "开启" : "On") : (chinese() ? "关闭" : "Off")));
    }

    private static String titleText() {
        return chinese() ? "一键隐藏客户端痕迹" : "Hide Client Traces";
    }

    private static boolean chinese() {
        return Minecraft.getInstance().getLanguageManager().getSelected().startsWith("zh_");
    }
}
