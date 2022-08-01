package com.ocraftyone.rebindnarrator.client.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.ocraftyone.rebindnarrator.RebindNarrator;
import com.ocraftyone.rebindnarrator.config.ModConfig;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class ClientCommands {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(literal("rebindnarrator")
                .then(literal("reloadconfig")
                        .executes(context -> {
                            RebindNarrator.config.load();
                            context.getSource().sendFeedback(Text.of("Reloading RebindNarrator config..."));
                            return Command.SINGLE_SUCCESS;
                        }))
                .then(literal("modifierkey")
                        .then(argument("modifierKey", ModifierKeyArgument.modifierKeyArgument())
                                .executes(context -> {
                                    ModConfig.Modifier modifierKey = ModifierKeyArgument.getModifierKey(context, "modifierKey");
                                    modifierKey.updateConfig();
                                    context.getSource().sendFeedback(Text.literal("Modifier key set to " + modifierKey.toString()));
                                    return Command.SINGLE_SUCCESS;
                                }))
                        .executes(context -> {
                            context.getSource().sendFeedback(Text.literal("Modifier key is currently set to " + RebindNarrator.config.getModifierKey().toString()));
                            return Command.SINGLE_SUCCESS;
                        })));
    }
}
