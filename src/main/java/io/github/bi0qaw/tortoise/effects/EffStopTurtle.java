package io.github.bi0qaw.tortoise.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;
import org.bukkit.event.Event;

public class EffStopTurtle extends Effect {

	private Expression<Turtle> turtles;
	private int mark;

	@Override
	protected void execute(Event event) {
		switch (mark){
			case 0:
				for (Turtle t : turtles.getAll(event)){
					t.stop();
				}
				break;
			case 1:
				for (Turtle t : turtles.getAll(event)){
					t.stopWithChildren();
				}
				break;
			case 2:
				for (Turtle t : turtles.getAll(event)){
					t.stopWithDescendants();
				}
				break;
			default:
				break;
		}
	}

	public String toString(Event event, boolean b) {
		return "stop turtle";
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		turtles = (Expression<Turtle>) expressions[0];
		mark = parseResult.mark;
		return true;
	}
}
