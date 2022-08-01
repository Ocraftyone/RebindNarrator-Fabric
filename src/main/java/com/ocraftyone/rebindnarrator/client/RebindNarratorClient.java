package com.ocraftyone.rebindnarrator.client;

import com.ocraftyone.rebindnarrator.RebindNarrator;
import com.ocraftyone.rebindnarrator.client.commands.ClientCommands;
import com.ocraftyone.rebindnarrator.client.commands.ModifierKeyArgument;
import com.ocraftyone.rebindnarrator.event.KeybindHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class RebindNarratorClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeybindHandler.register();
        ArgumentTypeRegistry.registerArgumentType(new Identifier(RebindNarrator.modid, "modifierkey"), ModifierKeyArgument.class, ConstantArgumentSerializer.of(ModifierKeyArgument::new));
        ClientCommandRegistrationCallback.EVENT.register(ClientCommands::register);
    }
}
