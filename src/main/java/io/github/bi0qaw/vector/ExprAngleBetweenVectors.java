package io.github.bi0qaw.vector;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

public class ExprAngleBetweenVectors extends SimpleExpression<Float> {

	private Expression<Vector> first;
	private Expression<Vector> second;

	public boolean isSingle() {
		return true;
	}

	public String toString(Event event, boolean b) {
		return "angle between " + first.toString() + " and " + second.toString() ;
	}

	public Class<? extends Float> getReturnType() {
		return Float.class;
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		first = (Expression<Vector>)expressions[0];
		second = (Expression<Vector>)expressions[1];
		return true;
	}

	@Override
	protected Float[] get(Event event) {
		Vector v1 = first.getSingle(event);
		Vector v2 = second.getSingle(event);
		if (v1 == null || v2 == null){
			return null;
		}
		return new Float[] { v1.angle(v2) * (float) VectorMath.RAD_TO_DEG };
	}

}
