package io.github.bi0qaw.vector;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

public class ExprVectorNormalize extends SimpleExpression<Vector> {

	private Expression<Vector> vector;

	public boolean isSingle() {
		return true;
	}

	public String toString(Event event, boolean b) {
		return "normalize";
	}

	public Class<? extends Vector> getReturnType() {
		return Vector.class;
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		vector = (Expression<Vector>)expressions[0];
		return true;
	}

	@Override
	protected Vector[] get(Event event) {
		Vector v = vector.getSingle(event);
		if (v == null){
			return null;
		}
		return new Vector[]{ v.clone().normalize() };
	}
}
