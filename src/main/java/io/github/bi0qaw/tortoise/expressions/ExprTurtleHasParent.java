package io.github.bi0qaw.tortoise.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import io.github.bi0qaw.tortoise.Turtle;

public class ExprTurtleHasParent extends SimplePropertyExpression<Turtle, Boolean> {

	@Override
	public Boolean convert(Turtle turtle) {
		return turtle.hasParent();
	}

	@Override
	protected String getPropertyName() {
		return "turtle has parent";
	}

	public Class<? extends Boolean> getReturnType() {
		return Boolean.class;
	}
}
