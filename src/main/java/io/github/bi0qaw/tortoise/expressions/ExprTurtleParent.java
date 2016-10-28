package io.github.bi0qaw.tortoise.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import io.github.bi0qaw.tortoise.Turtle;
import org.bukkit.event.Event;

public class ExprTurtleParent extends SimplePropertyExpression<Turtle, Turtle> {

	@Override
	public Turtle convert(Turtle turtle) {
		if (turtle.hasParent()) return turtle.getParent();
		return null;
	}

	@Override
	protected String getPropertyName() {
		return "parent of turtle";
	}

	public Class<? extends Turtle> getReturnType() {
		return Turtle.class;
	}

	@Override
	public Class<?>[] acceptChange(Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET && getExpr().isSingle()) return new Class[]{Turtle.class};
		return null;
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		Turtle parent = (Turtle) delta[0];
		Turtle turtle = getExpr().getSingle(e);
		if (parent == null || turtle == null){
			return;
		}
		switch (mode){
			case SET:
				turtle.setParent(parent);
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
