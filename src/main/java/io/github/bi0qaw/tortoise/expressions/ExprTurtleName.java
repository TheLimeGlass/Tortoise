package io.github.bi0qaw.tortoise.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import io.github.bi0qaw.tortoise.Turtle;
import org.bukkit.event.Event;

public class ExprTurtleName extends SimplePropertyExpression<Turtle, String> {

	@Override
	public String convert(Turtle turtle) {
		return turtle.getName();
	}

	@Override
	protected String getPropertyName() {
		return "name of turtle";
	}

	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	public Class<?>[] acceptChange(Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET) return new Class[]{String.class};
		return null;
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		if (delta[0] == null) return;
		String name = (String) delta[0];
		switch (mode) {
			case SET:
				getExpr().getSingle(e).setName(name);
			case ADD:
			case REMOVE:
			case REMOVE_ALL:
			case DELETE:
			case RESET:
				break;
		}
	}
}
