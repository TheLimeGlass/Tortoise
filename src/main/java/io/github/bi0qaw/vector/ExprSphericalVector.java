package io.github.bi0qaw.vector;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

public class ExprSphericalVector extends SimpleExpression<Vector> {

	private Expression<Number> radius;
	private Expression<Number> yaw;
	private Expression<Number> pitch;

	public boolean isSingle() {
		return true;
	}

	public String toString(Event event, boolean b) {
		return "spherical vector with radius " + radius.toString() + ", yaw " + yaw.toString() + ", pitch" + pitch.toString();
	}

	public Class<? extends Vector> getReturnType() {
		return Vector.class;
	}

	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		radius = (Expression<Number>) expressions[0];
		yaw = (Expression<Number>) expressions[1];
		pitch = (Expression<Number>) expressions[2];
		return true;
	}

	@Override
	protected Vector[] get(Event event) {
		Number r = radius.getSingle(event);
		Number y = yaw.getSingle(event);
		Number p = pitch.getSingle(event);
		if (r == null || y == null || p == null) {
			return null;
		}
		return new Vector[]{ VectorMath.fromSphericalCoordinates(r.doubleValue(), VectorMath.fromSkriptYaw(y.floatValue()), p.floatValue() + 90)};
	}
}
