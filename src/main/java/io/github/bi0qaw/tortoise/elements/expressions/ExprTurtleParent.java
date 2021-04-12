package io.github.bi0qaw.tortoise.elements.expressions;

import org.bukkit.event.Event;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import io.github.bi0qaw.tortoise.Turtle;

@Name("Turtle Parent")
@Description("Returns the parent of a turtle.")
public class ExprTurtleParent extends SimplePropertyExpression<Turtle, Turtle> {

	static {
		register(ExprTurtleParent.class, Turtle.class, "parent", "turtles");
	}

	@Override
	public Class<? extends Turtle> getReturnType() {
		return Turtle.class;
	}

	@Override
	public Turtle convert(Turtle turtle) {
		return turtle.getParent();
	}

	@Override
	protected String getPropertyName() {
		return "parent";
	}

	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET && getExpr().isSingle())
			return new Class[]{Turtle.class};
		return null;
	}

	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		Turtle parent = (Turtle) delta[0];
		if (parent == null || getExpr() == null)
			return;
		Turtle turtle = getExpr().getSingle(event);
		turtle.setParent(parent);
	}

}
