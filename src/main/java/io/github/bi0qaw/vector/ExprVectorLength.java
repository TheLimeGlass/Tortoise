package io.github.bi0qaw.vector;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

public class ExprVectorLength extends SimplePropertyExpression<Vector, Double> {

	@Override
	public Double convert(Vector vector) {
		if (vector == null) return null;
		return vector.length();
	}

	@Override
	protected String getPropertyName() {
		return "length";
	}

	public Class<? extends Double> getReturnType() {
		return Double.class;
	}

	@Override
	public Class<?>[] acceptChange(Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.SET){
			return new Class[]{ Number.class };
		}
		return null;
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		assert delta != null;
		final Vector v = getExpr().getSingle(e);
		if (v == null)
			return;
		double n = ((Number) delta[0]).doubleValue();
		switch (mode) {
			case REMOVE:
				n = -n;
				//$FALL-THROUGH$
			case ADD:
				if (n < 0 && v.lengthSquared() < n * n) {
					v.zero();
				} else {
					double l = n + v.length();
					v.normalize().multiply(l);
				}
				getExpr().change(e, new Vector[]{v}, Changer.ChangeMode.SET);
				break;
			case SET:
				if (n < 0) {
					v.zero();
				} else {
					v.normalize().multiply(n);
				}
				getExpr().change(e, new Vector[]{v}, Changer.ChangeMode.SET);
				break;
			case DELETE:
			case REMOVE_ALL:
			case RESET:
				assert false;
		}
	}
}
