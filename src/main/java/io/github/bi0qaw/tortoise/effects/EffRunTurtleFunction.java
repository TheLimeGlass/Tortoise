package io.github.bi0qaw.tortoise.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;
import org.bukkit.event.Event;

public class EffRunTurtleFunction extends Effect {

	private Expression<Turtle> turtle;

	@Override
	protected void execute(Event event) {
		for (Turtle t : turtle.getAll(event)){
			if (t.getFunction() != null){
				t.run();
			}
		}
	}

	public String toString(Event event, boolean b) {
		return null;
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		turtle = (Expression<Turtle>) expressions[0];
		return true;
	}
}
