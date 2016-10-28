package io.github.bi0qaw.tortoise;

import ch.njol.skript.Skript;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Tortoise extends JavaPlugin {

	private static Tortoise tortoise;
	public static boolean VectorsSkript;
	public static boolean RandomSk;

	@Override
	public void onEnable(){
		tortoise = this;
		Skript.registerAddon(this);
		new TurtleType();
		new TurtleRegister();

		// Vectors-Skript and RandomSk already register vectors.
		VectorsSkript = Bukkit.getPluginManager().getPlugin("Vectors-Skript") != null;
		RandomSk = Bukkit.getPluginManager().getPlugin("RandomSK") != null;
		if (!VectorsSkript && !RandomSk) {
			new VectorType();
		}
	}

	public static Tortoise getPlugin(){
		return tortoise;
	}
}
