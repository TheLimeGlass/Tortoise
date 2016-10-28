package io.github.bi0qaw.tortoise.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import io.github.bi0qaw.tortoise.Turtle;
import org.bukkit.Location;

public class ExprTurtleLocation extends SimplePropertyExpression<Turtle, Location> {

	@Override
	protected String getPropertyName() {
		return "location of turtle";
	}

	@Override
	public Location convert(Turtle turtle) {
		return turtle.getLocation();
	}

	public Class<? extends Location> getReturnType() {
		return Location.class;
	}

}
