package com.ocraftyone.rebindnarrator;

import com.ocraftyone.rebindnarrator.config.ModConfig;
import net.fabricmc.api.ModInitializer;

public class RebindNarrator implements ModInitializer {
    public static final String modid = "rebindnarrator";
    public static ModConfig config;
    @Override
    public void onInitialize() {
        config = new ModConfig();
        config.load();
    }
}
