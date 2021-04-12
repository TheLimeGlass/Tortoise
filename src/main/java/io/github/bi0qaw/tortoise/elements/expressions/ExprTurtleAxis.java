package io.github.bi0qaw.tortoise.elements.expressions;

import org.bukkit.event.Event;
import org.bukkit.util.Vector;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;

@Name("Turtle Axis")
@Description("Returns the axis of a turtle.")
public class ExprTurtleAxis extends SimplePropertyExpression<Turtle, Vector> {

	static {
		register(ExprTurtleAxis.class, Vector.class, "(0¦forward|1¦backward|2¦upward|3¦downward|4¦right[ward]|5¦left[ward])(-| )axis", "turtles");
	}

	private int axis;

	public Class<? extends Vector> getReturnType() {
		return Vector.class;
	}

	@Override
	public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		super.init(expressions, matchedPattern, isDelayed, parseResult);
		axis = parseResult.mark;
		return true;
	}

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

	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return new Class[]{Vector.class};
		return null;
	}

	@Override
	public void change(Event e, Object[] delta, ChangeMode mode) {
		super.change(e, delta, mode);
	}

}
