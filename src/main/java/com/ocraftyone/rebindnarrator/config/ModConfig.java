package com.ocraftyone.rebindnarrator.config;

import com.ocraftyone.rebindnarrator.RebindNarrator;
import lombok.Getter;
import lombok.Setter;
import me.lortseam.completeconfig.api.ConfigEntry;
import me.lortseam.completeconfig.data.Config;

public class ModConfig extends Config {
    public ModConfig() {
        super(RebindNarrator.modid);
    }
    @ConfigEntry(comment = "Please select a modifier key. [CTRL, ALT, SHIFT, NONE] Default: CTRL")
    @ConfigEntry.Dropdown
    @Getter
    @Setter
    private Modifier modifierKey = Modifier.CTRL;
    
    public enum Modifier {
        CTRL, ALT, SHIFT, NONE;
        public void updateConfig() {
            ModConfig config = RebindNarrator.config;
            config.modifierKey = this;
            config.save();
        }
    }
}
