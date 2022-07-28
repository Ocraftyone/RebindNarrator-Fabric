package com.ocraftyone.rebindnarrator.event;

import com.ocraftyone.rebindnarrator.util.KeyBindingModifiedAccessor;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.gui.screen.option.KeybindsScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;

import java.util.Arrays;

public class KeybindHandler {
    public static final KeyBinding TOGGLE_NARRATOR = new KeyBinding("key.rebindnarrator.togglenarrator", InputUtil.Type.KEYSYM, InputUtil.GLFW_KEY_B, KeyBinding.MISC_CATEGORY);
    public static final KeyBinding MODIFIER_KEY = new KeyBinding("key.rebindnarrator.togglenarratormodifier", InputUtil.Type.KEYSYM, InputUtil.GLFW_KEY_LEFT_CONTROL, KeyBinding.MISC_CATEGORY);
    public static final int[] VALID_MODIFIERS = {InputUtil.GLFW_KEY_LEFT_CONTROL, InputUtil.GLFW_KEY_RIGHT_CONTROL, InputUtil.GLFW_KEY_LEFT_ALT, InputUtil.GLFW_KEY_RIGHT_ALT, InputUtil.GLFW_KEY_LEFT_SHIFT, InputUtil.GLFW_KEY_RIGHT_SHIFT};
    
    public static void register() {
        KeyBindingHelper.registerKeyBinding(TOGGLE_NARRATOR);
        KeyBindingHelper.registerKeyBinding(MODIFIER_KEY);
        registerEvent();
    }
    
    public static void registerEvent() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (Arrays.stream(VALID_MODIFIERS).noneMatch(value -> value == ((KeyBindingModifiedAccessor) MODIFIER_KEY).getBoundKey().getCode()) && !MODIFIER_KEY.isUnbound() && !(client.currentScreen instanceof KeybindsScreen)) {
                if (client.player != null) {
                    client.player.sendMessage(Text.of("Modifier key not valid"));
                }
                MODIFIER_KEY.setBoundKey(InputUtil.UNKNOWN_KEY);
            }
            if (TOGGLE_NARRATOR.isUnbound() && !MODIFIER_KEY.isUnbound() && !(client.currentScreen instanceof KeybindsScreen)) {
                if (client.player != null) {
                    client.player.sendMessage(Text.of("Modifier key unbound"));
                }
                MODIFIER_KEY.setBoundKey(InputUtil.UNKNOWN_KEY);
            }
        });
    }
}
