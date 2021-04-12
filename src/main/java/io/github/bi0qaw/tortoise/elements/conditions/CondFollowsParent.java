package io.github.bi0qaw.tortoise.elements.conditions;

import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Checker;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;

@Name("Turtle Follows Parent")
@Description("Checks if a turtle is following the rotation of their parent.")
public class CondFollowsParent extends Condition {

	static {
		Skript.registerCondition(CondFollowsParent.class, "%turtles% follow[s] rotation of parent[s]");
	}

	private Expression<Turtle> turtles;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		turtles = (Expression<Turtle>) exprs[0];
		return false;
	}

	@Override
	public String toString(Event event, boolean debug) {
		if (event == null)
			return "turtles follow rotation of parent";
		return "turtles" + turtles.toString(event, debug) + " follow rotations of parents";
	}

	@Override
	public boolean check(Event event) {
		if (turtles == null)
			return false;
		return turtles.check(event, new Checker<Turtle>() {
			@Override
			public boolean check(Turtle turtle) {
				return turtle.isFollowRotation();
			}
		});
	}

}
