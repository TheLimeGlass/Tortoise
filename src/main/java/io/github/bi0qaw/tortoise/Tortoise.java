package io.github.bi0qaw.tortoise;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;

public class Tortoise extends JavaPlugin {

	private static Tortoise instance;
	private SkriptAddon addon;

	@Override
	public void onEnable() {
		instance = this;
		try {
			addon = Skript.registerAddon(this).loadClasses(getClass().getPackage().getName(), "elements");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public SkriptAddon getAddonInstance() {
		return addon;
	}

	public static Tortoise getInstance() {
		return instance;
	}

}
