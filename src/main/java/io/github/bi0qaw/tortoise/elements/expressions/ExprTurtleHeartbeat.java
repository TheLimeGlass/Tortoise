package io.github.bi0qaw.tortoise.elements.expressions;

import org.bukkit.event.Event;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import io.github.bi0qaw.tortoise.Turtle;

@Name("Turtle Heartbeat")
@Description("Returns the heartbeat tick of a turtle.")
public class ExprTurtleHeartbeat extends SimplePropertyExpression<Turtle, Number> {

	static {
		register(ExprTurtleHeartbeat.class, Number.class, "[turtle] heartbeat[s]", "turtles");
	}

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@Override
	public Number convert(Turtle turtle) {
		return turtle.getHeartbeat();
	}

	@Override
	protected String getPropertyName() {
		return "turtle heartbeat";
	}

	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return new Class[]{Number.class};
		return null;
	}

	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		Expression<? extends Turtle> turtles = getExpr();
		if (turtles == null)
			return;
		Number heartbeat = (Number) delta[0];
		if (heartbeat == null)
			return;
		for (Turtle turtle : turtles.getArray(event))
			turtle.setHeartbeat(heartbeat.intValue());
	}

}
