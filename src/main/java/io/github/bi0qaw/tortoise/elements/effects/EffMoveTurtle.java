package io.github.bi0qaw.tortoise.elements.effects;

import org.bukkit.Location;
import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;

@Name("Move Turtle Location")
@Description("Moves a turtle to a location")
public class EffMoveTurtle extends Effect {

	static {
		Skript.registerEffect(EffMoveTurtle.class, "move %turtles% to %location%");
	}

	private Expression<Location> location;
	private Expression<Turtle> turtles;

	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		this.turtles = (Expression<Turtle>)expressions[0];
		this.location = (Expression<Location>)expressions[1];
		return true;
	}

	protected void execute(Event event) {
		if (turtles == null || location == null)
			return;
		Location location = this.location.getSingle(event);
		for (Turtle turtle : this.turtles.getArray(event))
			turtle.setLocation(location);
	}
	
	public String toString(final Event event, final boolean b) {
		return "move turtles " + this.turtles.toString() + " to " + this.location.toString();
	}

}
