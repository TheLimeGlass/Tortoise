package io.github.bi0qaw.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.Turtle;
import org.bukkit.Location;
import org.bukkit.event.Event;

public class ExprLocationXYZFromTurtle extends SimpleExpression<Location> {

	private Expression<Number> x,y,z;
	private Expression<Turtle> turtle;

	@Override
	protected Location[] get(Event event) {
		Number x = this.x.getSingle(event);
		Number y = this.y.getSingle(event);
		Number z = this.z.getSingle(event);
		Turtle t = this.turtle.getSingle(event);
		if ( x == null || y == null || z == null || t == null){
			return null;
		}
		return new Location[]{t.getPosition(x.doubleValue(), y.doubleValue(), z.doubleValue()).toLocation(t.getWorld())};
	}

	public boolean isSingle() {
		return true;
	}

	public Class<? extends Location> getReturnType() {
		return Location.class;
	}

	public String toString(Event event, boolean b) {
		return "location x, y, z from turtle";
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		x = (Expression<Number>) expressions[0];
		y = (Expression<Number>) expressions[1];
		z = (Expression<Number>) expressions[2];
		turtle = (Expression<Turtle>) expressions[3];
		return true;
	}
}
