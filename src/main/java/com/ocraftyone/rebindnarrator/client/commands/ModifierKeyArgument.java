package com.ocraftyone.rebindnarrator.client.commands;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.ocraftyone.rebindnarrator.config.ModConfig;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModifierKeyArgument implements ArgumentType<ModConfig.Modifier> {
    public static ModifierKeyArgument modifierKeyArgument() {
        return new ModifierKeyArgument();
    }
    
    public static ModConfig.Modifier getModifierKey(CommandContext<FabricClientCommandSource> context, String name) {
        return context.getArgument(name, ModConfig.Modifier.class);
    }
    
    @Override
    public ModConfig.Modifier parse(StringReader reader) throws CommandSyntaxException {
        int argBeginning = reader.getCursor();
        if (!reader.canRead()) {
            reader.skip();
        }
        while (reader.canRead() && reader.peek() != ' ') {
            reader.skip();
        }
        String modifierString = reader.getString().substring(argBeginning, reader.getCursor()).toUpperCase();
        try {
            return ModConfig.Modifier.valueOf(modifierString);
        } catch (Exception e) {
            throw new SimpleCommandExceptionType(Text.literal("\"" + modifierString + "\" is not a valid modifier keycode\n")).createWithContext(reader);
        }
    }
    
    public static final List<String> SUGGESTIONS = Arrays.asList("CTRL", "ALT", "SHIFT", "NONE");
    
    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        SUGGESTIONS.forEach(builder::suggest);
        return builder.buildFuture();
    }
    
    @Override
    public Collection<String> getExamples() {
        return ArgumentType.super.getExamples();
    }
}
