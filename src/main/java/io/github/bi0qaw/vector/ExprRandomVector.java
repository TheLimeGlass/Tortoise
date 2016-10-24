package io.github.bi0qaw.vector;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

public class ExprRandomVector extends SimpleExpression<Vector> {

	@Override
	protected Vector[] get(Event event) {
		return new Vector[] {Vector.getRandom()};
	}

	public boolean isSingle() {
		return true;
	}

	public Class<? extends Vector> getReturnType() {
		return Vector.class;
	}

	public String toString(Event event, boolean b) {
		return "random vector";
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		return true;
	}
}
