package io.github.bi0qaw.vector;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

public class ExprSphericalVector extends SimpleExpression<Vector> {

	private Expression<Number> radius;
	private Expression<Number> theta;
	private Expression<Number> phi;

	public boolean isSingle() {
		return true;
	}

	public String toString(Event event, boolean b) {
		return "spherical";
	}

	public Class<? extends Vector> getReturnType() {
		return Vector.class;
	}

	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		radius = (Expression<Number>) expressions[0];
		theta = (Expression<Number>) expressions[1];
		phi = (Expression<Number>) expressions[2];
		return true;
	}

	@Override
	protected Vector[] get(Event event) {
		Number r = radius.getSingle(event);
		Number t = theta.getSingle(event);
		Number p = phi.getSingle(event);
		if (r == null || t == null || p == null) {
			return null;
		}
		return new Vector[]{fromSphericalCoordinates(r.doubleValue(), t.doubleValue(), p.doubleValue())};
	}

	private static final double PI = Math.PI;
	private static final double DEG_TO_RAD = PI / 180;

	private Vector fromSphericalCoordinates(double radius, double theta, double phi) {
		double r = Math.abs(radius);
		double t = theta * DEG_TO_RAD;
		double p = phi * DEG_TO_RAD;
		double x = r * Math.sin(t) * Math.cos(p);
		double z = r * Math.sin(t) * Math.sin(p);
		double y = r * Math.cos(t);
		return new Vector(x, y, z);
	}
}
