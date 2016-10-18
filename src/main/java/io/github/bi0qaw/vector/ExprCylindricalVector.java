package io.github.bi0qaw.vector;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

public class ExprCylindricalVector extends SimpleExpression<Vector> {

	private Expression<Number> radius;
	private Expression<Number> phi;
	private Expression<Number> height;

	public boolean isSingle() {
		return true;
	}

	public String toString(Event event, boolean b) {
		return "cylindrical";
	}

	public Class<? extends Vector> getReturnType() {
		return Vector.class;
	}

	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		radius = (Expression<Number>) expressions[0];
		phi = (Expression<Number>) expressions[1];
		height = (Expression<Number>) expressions[2];
		return true;
	}

	@Override
	protected Vector[] get(Event event) {
		Number r = radius.getSingle(event);
		Number p = phi.getSingle(event);
		Number h = height.getSingle(event);
		if (r == null || p == null || h == null) {
			return null;
		}
		return new Vector[]{ VectorMath.fromCylindricalCoordinates(r.doubleValue(), p.doubleValue(), h.doubleValue()) };
	}

}
