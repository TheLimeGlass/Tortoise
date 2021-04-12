package io.github.bi0qaw.tortoise.elements.expressions;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;

@Name("Location Vector Turtle")
@Description("Returns the location of a turtle based on the vectors.")
public class ExprLocationVectorFromTurtle extends SimpleExpression<Location> {

	static {
		Skript.registerExpression(ExprLocationVectorFromTurtle.class, Location.class, ExpressionType.SIMPLE, "location %vectors% from %turtle%");
	}

	private Expression<Vector> vectors;
	private Expression<Turtle> turtle;

	public Class<? extends Location> getReturnType() {
		return Location.class;
	}

	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		vectors = (Expression<Vector>) expressions[0];
		turtle = (Expression<Turtle>) expressions[1];
		return true;
	}

	@Override
	protected Location[] get(Event event) {
		if (turtle == null)
			return null;
		Turtle turtle = this.turtle.getSingle(event);
		Location location = turtle.getLocation();
		for (Vector vector : vectors.getArray(event))
			location.add(turtle.getPosition(vector));
		return new Location[] {location};
	}

	public String toString(Event event, boolean debug) {
		if (event == null)
			return "location vector from turtles";
		return "location vector " + vectors.toString(event, debug) + " from turtle " + turtle.toString(event, debug);
	}

}
