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

@Name("New Turtle")
@Description("Creates and returns a new turtle at the defined location.")
public class ExprNewTurtle extends SimpleExpression<Turtle> {

	static {
		Skript.registerExpression(ExprNewTurtle.class, Turtle.class, ExpressionType.SIMPLE, "new turtle at %location%");
	}

	private Expression<Location> location;

	public Class<? extends Turtle> getReturnType() {
		return Turtle.class;
	}

	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		location = (Expression<Location>) expressions[0];
		return true;
	}

	@Override
	protected Turtle[] get(Event event) {
		Location location = this.location.getSingle(event);
		if (location == null)
			return null;
		return new Turtle[] {new Turtle(location)};
	}

	public String toString(Event event, boolean debug) {
		if (event == null)
			return "turtle";
		return "turtle at " + location.toString(event, debug);
	}

}
