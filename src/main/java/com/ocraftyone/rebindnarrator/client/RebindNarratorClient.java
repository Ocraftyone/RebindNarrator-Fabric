package com.ocraftyone.rebindnarrator.client;

import com.ocraftyone.rebindnarrator.event.KeybindHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class RebindNarratorClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeybindHandler.register();
    }
}
