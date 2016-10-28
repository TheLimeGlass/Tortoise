package io.github.bi0qaw.tortoise.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;
import org.bukkit.Location;
import org.bukkit.event.Event;

public class ExprNewTurtle extends SimpleExpression<Turtle> {

	private Expression<Location> loc;

	@Override
	protected Turtle[] get(Event event) {
		Location l = loc.getSingle(event);
		if (l == null){
			return null;
		}
		return new Turtle[] { new Turtle(l) };
	}

	public boolean isSingle() {
		return true;
	}

	public Class<? extends Turtle> getReturnType() {
		return Turtle.class;
	}

	public String toString(Event event, boolean b) {
		return "turtle";
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		loc = (Expression<Location>) expressions[0];
		return true;
	}
}
