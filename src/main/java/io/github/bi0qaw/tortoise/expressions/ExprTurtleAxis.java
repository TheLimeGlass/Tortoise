package io.github.bi0qaw.tortoise.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

public class ExprTurtleAxis extends SimplePropertyExpression<Turtle, Vector> {

	private int axis;

	@Override
	public Vector convert(Turtle turtle) {
		switch (axis) {
			case 0:
				return turtle.getU().clone();
			case 1:
				return turtle.getU().clone().multiply(-1);
			case 2:
				return turtle.getV().clone();
			case 3:
				return turtle.getV().clone().multiply(-1);
			case 4:
				return turtle.getW().clone();
			case 5:
				return turtle.getW().clone().multiply(-1);
			default:
				break;
		}
		return null;
	}

	@Override
	protected String getPropertyName() {
		return "axis";
	}

	public Class<? extends Vector> getReturnType() {
		return Vector.class;
	}

	public String toString(Event event, boolean b) {
		return "axis";
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		super.init(expressions, i, kleenean, parseResult);
		axis = parseResult.mark;
		return true;
	}

	@Override
	public Class<?>[] acceptChange(Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET) return new Class[]{Vector.class};
		return null;
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		super.change(e, delta, mode);
	}
}
