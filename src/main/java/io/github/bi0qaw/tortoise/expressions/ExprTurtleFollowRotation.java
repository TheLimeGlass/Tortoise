package io.github.bi0qaw.tortoise.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import io.github.bi0qaw.tortoise.Turtle;
import org.bukkit.event.Event;

public class ExprTurtleFollowRotation extends SimplePropertyExpression<Turtle, Boolean> {

	@Override
	public Boolean convert(Turtle turtle) {
		return turtle.isFollowRotation();
	}

	@Override
	protected String getPropertyName() {
		return "follows rotation of parent";
	}

	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
	}

	@Override
	public Class<?>[] acceptChange(Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET && getExpr().isSingle()){
			return new Class[]{ Boolean.class };
		}
		return null;
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		if (delta[0] == null) {
			return;
		}
		Turtle t = getExpr().getSingle(e);
		t.setFollowRotation((Boolean) delta[0]);
	}
}
