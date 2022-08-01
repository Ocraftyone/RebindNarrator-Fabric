package com.ocraftyone.rebindnarrator.mixin;

import com.ocraftyone.rebindnarrator.RebindNarrator;
import com.ocraftyone.rebindnarrator.config.ModConfig;
import com.ocraftyone.rebindnarrator.event.KeybindHandler;
import com.ocraftyone.rebindnarrator.util.KeyBindingModifiedAccessor;
import net.minecraft.client.Keyboard;
import net.minecraft.client.gui.screen.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        Logger logger = LoggerFactory.getLogger(RebindNarrator.modid);
        logger.info(String.valueOf(code));
        return code;
    }
    
    @Redirect(method = "onKey", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;hasControlDown()Z", ordinal = 1))
    private boolean getModifierKey() {
        Logger logger = LoggerFactory.getLogger(RebindNarrator.modid);
        if (KeybindHandler.TOGGLE_NARRATOR.isUnbound()) {
            logger.info("main key unbound");
            return false;
        }
        ModConfig.Modifier modifier = RebindNarrator.config.getModifierKey();
        if (modifier == ModConfig.Modifier.CTRL) {
            logger.info("control");
            return Screen.hasControlDown();
        } else if (modifier == ModConfig.Modifier.ALT) {
            logger.info("alt");
            return Screen.hasAltDown();
        } else if (modifier == ModConfig.Modifier.SHIFT) {
            logger.info("shift");
            return Screen.hasShiftDown();
        } else if (modifier == ModConfig.Modifier.NONE) {
            logger.info("none");
            return true;
        }
        logger.info("invalid modifier");
        return false;
    }
}
