package io.github.bi0qaw.tortoise.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

public class ExprLocationVectorFromTurtle extends SimpleExpression<Location> {

	private Expression<Vector> vectors;
	private Expression<Turtle> turtle;

	@Override
	protected Location[] get(Event event) {
		Turtle t = turtle.getSingle(event);
		if (t == null) {
			return null;
		}
		Location l = t.getLocation();
		for (Vector v : vectors.getArray(event)) {
			l.add(t.getPosition(v));
		}
		return new Location[] {l};
	}

	public boolean isSingle() {
		return true;
	}

	public Class<? extends Location> getReturnType() {
		return Location.class;
	}

	public String toString(Event event, boolean b) {
		return "location vector " + vectors.toString() + " from turtle " + turtle.toString() ;
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		vectors = (Expression<Vector>) expressions[0];
		turtle = (Expression<Turtle>) expressions[1];
		return true;
	}
}

