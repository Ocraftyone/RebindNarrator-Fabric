package com.ocraftyone.rebindnarrator.config;

public class ModConfig {
    public static int MODIFIER_KEY_CONFIG;
    public static SimpleConfig config;
    
    public static void registerConfigs() {
        config = SimpleConfig.of("rebindnarrator.config")
                .provider(namespace -> """
            #Select the modifier key that invokes the narrator
            #[1: CTRL, 2: ALT, 3: SHIFT, 4: NONE] Default: 1
            """).request();
        assignValuesFromConfig();
    }
    
    private static void assignValuesFromConfig() {
        MODIFIER_KEY_CONFIG = config.getOrDefault("ModifierKey", 1);
    }
    

}
