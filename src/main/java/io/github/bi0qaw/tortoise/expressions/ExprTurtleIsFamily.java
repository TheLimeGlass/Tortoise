package io.github.bi0qaw.tortoise.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;
import org.bukkit.event.Event;

public class ExprTurtleIsFamily extends SimpleExpression<Boolean> {

	private Expression<Turtle> turtle, family;
	private int mark;

	@Override
	protected Boolean[] get(Event event) {
		Turtle t = turtle.getSingle(event);
		Turtle f = family.getSingle(event);
		if (t == null || f == null){
			return null;
		}
		switch (mark) {
			case 0:
				return new Boolean[]{t.getParent().equals(f)};
			case 1:
				return new Boolean[]{t.hasChild(f)};
			case 2:
				return new Boolean[]{t.hasDescendant(f)};
			default:
				break;
		}
		return null;
	}

	public boolean isSingle() {
		return true;
	}

	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
	}

	public String toString(Event event, boolean b) {
		return "is child of";
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		family = (Expression<Turtle>) expressions[0];
		turtle = (Expression<Turtle>) expressions[1];
		mark = parseResult.mark;
		return true;
	}
}
