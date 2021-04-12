package io.github.bi0qaw.tortoise;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TurtleManager {

	private final static Map<UUID, Turtle> turtles = new HashMap<>();

	public static void register(Turtle turtle) {
		turtles.put(turtle.getId(), turtle);
	}

	public static void remove(Turtle turtle) {
		turtles.remove(turtle.getId());
	}

	public static void release(Turtle turtle) {
		if (!turtle.isFree() && turtle.getHeartbeat() > 0) {
			turtle.setIsFree(true);
			final int task = Bukkit.getServer().getScheduler().runTaskTimer(Tortoise.getInstance(),
					turtle, 0, turtle.getHeartbeat()).getTaskId();
			turtle.setTask(task);
		}
	}

	public static void stop(Turtle turtle) {
		if (turtle.isFree() && turtle.getTask() != -1) {
			Bukkit.getScheduler().cancelTask(turtle.getTask());
		}
	}

	public static boolean isRegistered(UUID id){
		return turtles.containsKey(id);
	}

	public static ArrayList<Turtle> getAll(){
		ArrayList<Turtle> t = new ArrayList<Turtle>();
		t.addAll(turtles.values());
		return t;
	}

	public static Turtle getTurtle(UUID id){
		if (turtles.containsKey(id)){
			return turtles.get(id);
		}
		return null;
	}
}
