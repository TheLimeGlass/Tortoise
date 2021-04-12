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

@Name("Turtle Has Family")
@Description("Checks if a turtle has a child, parent or descendant.")
public class CondTurtleFamily extends Condition {

	static {
		Skript.registerCondition(CondTurtleFamily.class, "%turtles% (is|are) (0¦parent|1¦child|2¦descendant)[s] of %turtles%");
	}

	private Expression<Turtle> turtles, family;
	private int mark;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		turtles = (Expression<Turtle>) exprs[0];
		family = (Expression<Turtle>) exprs[1];
		mark = parseResult.mark;
		return true;
	}

	@Override
	public boolean check(Event event) {
		return turtles.check(event, new Checker<Turtle>() {
			@Override
			public boolean check(Turtle turtle) {
				return family.check(event, new Checker<Turtle>() {
					@Override
					public boolean check(Turtle check) {
						switch (mark) {
							case 0:
								return turtle.hasChild(check);
							case 1:
								return turtle.getParent().equals(check);
							case 2:
								return turtle.hasDescendant(check);
						}
						return false;
					}
				});
			}
		});
	}

	@Override
	public String toString(Event event, boolean debug) {
		if (event == null)
			return "is family of";
		return turtles.toString(event, debug) + " is " + (mark == 0 ? "parent" : mark == 1 ? "child" : "descendant") + " of " + family.toString(event, debug);
	}

}
