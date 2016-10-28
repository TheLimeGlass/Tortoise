package io.github.bi0qaw.tortoise.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;
import org.bukkit.event.Event;

public class ExprCloneTurtle extends SimpleExpression<Turtle> {

	private Expression<Turtle> turtle;

	@Override
	protected Turtle[] get(Event event) {
		Turtle t = turtle.getSingle(event);
		if (t == null) {
			return null;
		}
		return new Turtle[] {t.clone()};
	}

	public boolean isSingle() {
		return true;
	}

	public Class<? extends Turtle> getReturnType() {
		return Turtle.class;
	}

	public String toString(Event event, boolean b) {
		return null;
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		turtle = (Expression<Turtle>) expressions[0];
		return true;
	}
}
