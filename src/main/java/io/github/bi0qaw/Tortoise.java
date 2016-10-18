package io.github.bi0qaw;

import ch.njol.skript.Skript;
import io.github.bi0qaw.vector.VectorRegister;
import io.github.bi0qaw.vector.VectorType;
import org.bukkit.plugin.java.JavaPlugin;

public class Tortoise extends JavaPlugin {

	private static Tortoise tortoise;

	@Override
	public void onEnable(){

		tortoise = this;
		Skript.registerAddon(this);
		new VectorType();
		new VectorRegister();
		new TurtleType();
		new TurtleRegister();

	}

	public static Tortoise getInstance(){
		return tortoise;
	}
}
