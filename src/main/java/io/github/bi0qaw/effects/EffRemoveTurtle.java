package io.github.bi0qaw.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.Turtle;
import io.github.bi0qaw.TurtleManager;
import org.bukkit.event.Event;

public class EffRemoveTurtle extends Effect {

	private Expression<Turtle> turtles;
	private int mark;

	@Override
	protected void execute(Event event) {
		switch (mark){
			case 0:
				for (Turtle t : turtles.getAll(event)) {
					t.remove();
				}
				return;
			case 1:
				for (Turtle t : turtles.getAll(event)) {
					t.removeWithChildren();
				}
				return;
			case 2:
				for (Turtle t : turtles.getAll(event)) {
					t.removeWithDescendants();
				}
				return;
			default:
				break;
		}
	}

	public String toString(Event event, boolean b) {
		return "remove turtle";
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		turtles = (Expression<Turtle>) expressions[0];
		mark = parseResult.mark;
		return true;
	}
}
