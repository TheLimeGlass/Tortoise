package io.github.bi0qaw.tortoise.elements.expressions;

import org.bukkit.Location;
import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;

@Name("Location XYZ Turtle")
@Description("Returns the location of a turtle based on XYZ coordinates.")
public class ExprLocationXYZFromTurtle extends SimpleExpression<Location> {

	static {
		Skript.registerExpression(ExprLocationXYZFromTurtle.class, Location.class, ExpressionType.SIMPLE, "location %number%,[ ]%number%, [ ]%number% from %turtle%");
	}

	private Expression<Number> x, y, z;
	private Expression<Turtle> turtle;

	public Class<? extends Location> getReturnType() {
		return Location.class;
	}

	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		x = (Expression<Number>) expressions[0];
		y = (Expression<Number>) expressions[1];
		z = (Expression<Number>) expressions[2];
		turtle = (Expression<Turtle>) expressions[3];
		return true;
	}

	@Override
	protected Location[] get(Event event) {
		if (x == null || y == null || z == null | turtle == null)
			return null;
		Number x = this.x.getSingle(event);
		Number y = this.y.getSingle(event);
		Number z = this.z.getSingle(event);
		Turtle turtle = this.turtle.getSingle(event);
		return new Location[]{turtle.getPosition(x.doubleValue(), y.doubleValue(), z.doubleValue()).toLocation(turtle.getWorld())};
	}

	public String toString(Event event, boolean debug) {
		return "location x, y, z from turtle";
	}

}
