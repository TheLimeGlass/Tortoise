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

@Name("Run Turtle Function")
@Description("Execute the set function for a turtle.")
public class EffRunTurtleFunction extends Effect {

	static {
		Skript.registerEffect(EffRunTurtleFunction.class, "(run|execute) function of %turtles%", "(run|execute) %turtles%['s] function");
	}

	private Expression<Turtle> turtles;

	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		turtles = (Expression<Turtle>) expressions[0];
		return true;
	}

	@Override
	protected void execute(Event event) {
		for (Turtle turtle : turtles.getAll(event)) {
			if (turtle.getFunction() != null)
				turtle.run();
		}
	}

	public String toString(Event event, boolean debug) {
		if (event == null)
			return "run function";
		return "run function of " + turtles.toString(event, debug);
	}

}
