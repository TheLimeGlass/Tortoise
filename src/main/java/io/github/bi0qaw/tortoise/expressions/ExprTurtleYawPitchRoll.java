package io.github.bi0qaw.tortoise.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;


/*
TODO
This is currently not supported and broken.
 */
public class ExprTurtleYawPitchRoll extends SimplePropertyExpression<Turtle, Number> {

	private int axis;

	@Override
	public Number convert(Turtle turtle) {
		return axis == 0 ? turtle.getYaw() : axis == 1 ? turtle.getPitch() : turtle.getRoll();
	}

	@Override
	protected String getPropertyName() {
		return "yaw, pitch, roll";
	}

	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
		super.init(exprs, matchedPattern, isDelayed, parseResult);
		axis = parseResult.mark;
		return true;
	}

	@Override
	public Class<?>[] acceptChange(Changer.ChangeMode mode) {
		if ((mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.REMOVE || mode == Changer.ChangeMode.SET)
				&& getExpr().isSingle() && Changer.ChangerUtils.acceptsChange(getExpr(), Changer.ChangeMode.SET, Turtle.class)) {
			return new Class[]{Number.class};
		}
		return null;
	}
}
