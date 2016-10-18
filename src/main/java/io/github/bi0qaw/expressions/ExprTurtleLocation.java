package io.github.bi0qaw.expressions;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import io.github.bi0qaw.Turtle;
import org.bukkit.Location;
import org.bukkit.event.Event;

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

	@Override
	public Class<?>[] acceptChange(Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET) return new Class[]{Location.class};
		return null;
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		if (delta[0] == null) return;
		Location l = (Location) delta[0];
		switch (mode) {
			case SET:
				getExpr().getSingle(e).setLocation(l);
				break;
			case ADD:
			case REMOVE:
			case REMOVE_ALL:
			case DELETE:
				break;
		}
	}
}
