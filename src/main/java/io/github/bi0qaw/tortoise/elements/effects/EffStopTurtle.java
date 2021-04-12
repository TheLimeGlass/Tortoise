package io.github.bi0qaw.tortoise.elements.effects;

import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;

@Name("Stop Turtle")
@Description("Stops a turtle from moving and optionally with childern or descendants.")
public class EffStopTurtle extends Effect {

	static {
		Skript.registerEffect(EffStopTurtle.class, "stop (0¦|1¦with childern|2¦with descendants) %turtles%");
	}

	private Expression<Turtle> turtles;
	private int mark;

	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		turtles = (Expression<Turtle>) expressions[0];
		mark = parseResult.mark;
		return true;
	}

	@Override
	protected void execute(Event event) {
		switch (mark) {
			case 0:
				for (Turtle turtle : turtles.getAll(event))
					turtle.stop();
				break;
			case 1:
				for (Turtle turtle : turtles.getAll(event))
					turtle.stopWithChildren();
				break;
			case 2:
				for (Turtle turtle : turtles.getAll(event))
					turtle.stopWithDescendants();
				break;
			default:
				break;
		}
	}

	public String toString(Event event, boolean debug) {
		if (event == null)
			return "stop turtles";
		return "stop turtles " + turtles.toString(event, debug) + (mark == 1 ? " with childern" : mark == 2 ? " with descendants" : "");
	}

}
