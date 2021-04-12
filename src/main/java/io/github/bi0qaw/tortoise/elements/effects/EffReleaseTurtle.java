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

@Name("Release Turtle")
@Description("Release a turtle from connections and optionally with childern or descendants too.")
public class EffReleaseTurtle extends Effect {

	static {
		Skript.registerEffect(EffReleaseTurtle.class, "release %turtles% [(1¦with children|2¦with descendants)]");
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
					turtle.release();
				return;
			case 1:
				for (Turtle turtle : turtles.getAll(event))
					turtle.releaseWithChildren();
				return;
			case 2:
				for (Turtle turtle : turtles.getAll(event))
					turtle.releaseWithDescendants();
				return;
			default:
				break;
		}
	}

	public String toString(Event event, boolean debug) {
		if (event == null)
			return "";
		return "release childern from " + turtles.toString(event, debug);
	}

}
