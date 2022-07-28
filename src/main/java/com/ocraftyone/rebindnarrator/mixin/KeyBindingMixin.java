package com.ocraftyone.rebindnarrator.mixin;

import com.ocraftyone.rebindnarrator.util.KeyBindingModifiedAccessor;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyBinding.class)
public abstract class KeyBindingMixin implements KeyBindingModifiedAccessor {
    @Shadow
    private InputUtil.Key boundKey;
    
    @Override
    public InputUtil.Key getBoundKey() {
        return boundKey;
    }
}
