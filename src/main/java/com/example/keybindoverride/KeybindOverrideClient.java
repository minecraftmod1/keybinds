package com.example.keybindoverride;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import org.lwjgl.glfw.GLFW;

public class KeybindOverrideClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		ClientLifecycleEvents.CLIENT_STARTED.register(KeybindOverrideClient::applyOverrides);
	}

	private static void applyOverrides(Minecraft client) {
		Options o = client.options;

		bind(o.keyHotbarSlots[4], GLFW.GLFW_KEY_X);     // Hotbar slot 5 -> X
		bind(o.keyHotbarSlots[5], GLFW.GLFW_KEY_C);     // Hotbar slot 6 -> C
		bind(o.keyHotbarSlots[8], GLFW.GLFW_KEY_Q);     // Hotbar slot 9 -> Q
		bind(o.keyDrop,           GLFW.GLFW_KEY_R);     // Drop          -> R
		bind(o.keyChat,           GLFW.GLFW_KEY_SLASH); // Chat          -> /
		bind(o.keyCommand,        GLFW.GLFW_KEY_T);     // Command       -> T

		KeyMapping.resetMapping();
		o.save();
	}

	private static void bind(KeyMapping mapping, int glfwKey) {
		mapping.setKey(InputConstants.Type.KEYSYM.getOrCreate(glfwKey));
	}
}
