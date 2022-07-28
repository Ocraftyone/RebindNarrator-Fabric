package com.ocraftyone.rebindnarrator.mixin;

import com.ocraftyone.rebindnarrator.event.KeybindHandler;
import com.ocraftyone.rebindnarrator.util.KeyBindingModifiedAccessor;
import net.minecraft.client.Keyboard;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.InputUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Keyboard.class)
public abstract class KeyboardHandlerMixin {
    @ModifyConstant(method = "onKey", constant = @Constant(intValue = 66))
    private int getNarratorKey(int constant) {
        int code = ((KeyBindingModifiedAccessor) KeybindHandler.TOGGLE_NARRATOR).getBoundKey().getCode();
        Logger logger = LoggerFactory.getLogger("rebindnarrator");
        logger.info(String.valueOf(code));
        return code;
    }
    
    @Redirect(method = "onKey", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;hasControlDown()Z", ordinal = 1))
    private boolean getModifierKey() {
        Logger logger = LoggerFactory.getLogger("rebindnarrator");
        if (KeybindHandler.TOGGLE_NARRATOR.isUnbound()) {
            logger.info("main key unbound");
            return false;
        }
        if (KeybindHandler.MODIFIER_KEY.isUnbound()) {
            logger.info("modifier key unbound");
            return true;
        }
        int code = ((KeyBindingModifiedAccessor) KeybindHandler.MODIFIER_KEY).getBoundKey().getCode();
        if (code == InputUtil.GLFW_KEY_LEFT_CONTROL || code == InputUtil.GLFW_KEY_RIGHT_CONTROL) {
            logger.info("control");
            return Screen.hasControlDown();
        } else if (code == InputUtil.GLFW_KEY_LEFT_ALT || code == InputUtil.GLFW_KEY_RIGHT_ALT) {
            logger.info("alt");
            return Screen.hasAltDown();
        } else if (code == InputUtil.GLFW_KEY_LEFT_SHIFT || code == InputUtil.GLFW_KEY_RIGHT_SHIFT) {
            logger.info("shift");
            return Screen.hasShiftDown();
        }
        logger.info("invalid modifier");
        return false;
    }
}
