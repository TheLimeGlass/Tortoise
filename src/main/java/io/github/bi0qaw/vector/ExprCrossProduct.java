package io.github.bi0qaw.vector;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

public class ExprCrossProduct extends SimpleExpression<Vector> {

	private Expression<Vector> first;
	private Expression<Vector> second;

	public boolean isSingle() {
		return true;
	}

	public String toString(Event event, boolean b) {
		return  first.toString() + " cross " + second.toString();
	}

	public Class<? extends Vector> getReturnType() {
		return Vector.class;
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		first = (Expression<Vector>)expressions[0];
		second = (Expression<Vector>)expressions[1];
		return true;
	}

	@Override
	protected Vector[] get(Event event) {
		Vector v1 = first.getSingle(event);
		Vector v2 = second.getSingle(event);
		if (v1 == null || v2 == null) {
			return null;
		}
		return new Vector[]{ v1.clone().crossProduct(v2)};
	}
}
