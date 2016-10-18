package io.github.bi0qaw.vector;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

public class ExprVectorXYZ extends SimplePropertyExpression<Vector, Number> {

	private final static char[] axes = {'x', 'y', 'z'};

	private int axis;

	@Override
	public boolean init(final Expression<?>[] exprs, final int matchedPattern, final Kleenean isDelayed, final SkriptParser.ParseResult parseResult) {
		super.init(exprs, matchedPattern, isDelayed, parseResult);
		axis = parseResult.mark;
		return true;
	}

	@SuppressWarnings("null")
	@Override
	public Double convert(final Vector v) {
		return axis == 0 ? v.getX() : axis == 1 ? v.getY() : v.getZ();
	}

	@Override
	protected String getPropertyName() {
		return "the " + axes[axis] + "-coordinate";
	}

	public Class<Number> getReturnType() {
		return Number.class;
	}

	@Override
	public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
		if ((mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.REMOVE) && getExpr().isSingle() && Changer.ChangerUtils.acceptsChange(getExpr(), Changer.ChangeMode.SET, Vector.class))
			return new Class[] { Number.class };
		return null;
	}

	@Override
	public void change(final Event e, final Object[] delta, final Changer.ChangeMode mode) throws UnsupportedOperationException {
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
				if (axis == 0) {
					v.setX(v.getX() + n);
				} else if (axis == 1) {
					v.setY(v.getY() + n);
				} else {
					v.setZ(v.getZ() + n);
				}
				getExpr().change(e, new Vector[] {v}, Changer.ChangeMode.SET);
				break;
			case SET:
				if (axis == 0) {
					v.setX(n);
				} else if (axis == 1) {
					v.setY(n);
				} else {
					v.setZ(n);
				}
				getExpr().change(e, new Vector[] {v}, Changer.ChangeMode.SET);
				break;
			case DELETE:
			case REMOVE_ALL:
			case RESET:
				assert false;
		}
	}

}
