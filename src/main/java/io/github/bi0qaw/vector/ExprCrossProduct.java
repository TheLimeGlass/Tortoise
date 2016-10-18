package io.github.bi0qaw.vector;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

public class ExprCrossProduct extends SimpleExpression<Vector> {

	private Expression<Vector> vector1;
	private Expression<Vector> vector2;

	public boolean isSingle() {
		return true;
	}

	public String toString(Event event, boolean b) {
		return "cross product";
	}

	public Class<? extends Vector> getReturnType() {
		return Vector.class;
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		vector1 = (Expression<Vector>)expressions[0];
		vector2 = (Expression<Vector>)expressions[1];
		return true;
	}

	@Override
	protected Vector[] get(Event event) {
		Vector v1 = vector1.getSingle(event);
		Vector v2 = vector2.getSingle(event);
		if (v1 == null || v2 == null) {
			return null;
		}
		return new Vector[]{ v1.clone().crossProduct(v2)};
	}
}
