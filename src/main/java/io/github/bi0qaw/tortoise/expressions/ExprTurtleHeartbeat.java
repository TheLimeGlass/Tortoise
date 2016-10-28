package io.github.bi0qaw.tortoise.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import io.github.bi0qaw.tortoise.Turtle;
import org.bukkit.event.Event;

public class ExprTurtleHeartbeat extends SimplePropertyExpression<Turtle, Number> {

	@Override
	public Integer convert(Turtle turtle) {
		return turtle.getHeartbeat();
	}

	@Override
	protected String getPropertyName() {
		return "turtle heartbeat";
	}

	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@Override
	public Class<?>[] acceptChange(Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET && getExpr().isSingle()) {
			return new Class[]{ Number.class };
		}
		return null;
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		if (delta[0] == null) return;
		Number num = (Number) delta[0];
		int n = num.intValue() < 0 ? -num.intValue() : num.intValue();
		switch (mode) {
			case SET:
				getExpr().getSingle(e).setHeartbeat(n);
				break;
			case ADD:
			case REMOVE:
			case REMOVE_ALL:
			case DELETE:
			case RESET:
				break;
		}
	}
}
