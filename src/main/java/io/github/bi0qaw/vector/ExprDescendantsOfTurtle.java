package io.github.bi0qaw.vector;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.Turtle;
import org.bukkit.event.Event;

public class ExprDescendantsOfTurtle extends SimpleExpression<Turtle> {

	private Expression<Turtle> turtle;

	@Override
	protected Turtle[] get(Event event) {
		return new Turtle[0];
	}

	public boolean isSingle() {
		return false;
	}

	public Class<? extends Turtle> getReturnType() {
		return Turtle.class;
	}

	public String toString(Event event, boolean b) {
		return "descendants of turtle";
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		turtle = (Expression<Turtle>) expressions[0];
		return true;
	}
}
