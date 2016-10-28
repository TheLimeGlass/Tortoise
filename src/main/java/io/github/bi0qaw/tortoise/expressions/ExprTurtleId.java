package io.github.bi0qaw.tortoise.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import io.github.bi0qaw.tortoise.Turtle;

public class ExprTurtleId extends SimplePropertyExpression<Turtle,String> {

	@Override
	protected String getPropertyName() {
		return "turtle id";
	}

	@Override
	public String convert(Turtle turtle) {
		return turtle.getId().toString();
	}

	public Class<? extends String> getReturnType() {
		return String.class;
	}
}
