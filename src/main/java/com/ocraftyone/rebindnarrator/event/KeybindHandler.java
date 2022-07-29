package com.ocraftyone.rebindnarrator.event;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class KeybindHandler {
    public static final KeyBinding TOGGLE_NARRATOR = new KeyBinding("key.rebindnarrator.togglenarrator", InputUtil.Type.KEYSYM, InputUtil.GLFW_KEY_B, KeyBinding.MISC_CATEGORY);
    
    public static void register() {
        KeyBindingHelper.registerKeyBinding(TOGGLE_NARRATOR);
    }
}
