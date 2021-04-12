package io.github.bi0qaw.tortoise.elements.expressions;

import org.bukkit.event.Event;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import io.github.bi0qaw.tortoise.Turtle;

@Name("Turtle Name")
@Description("Returns the name of the turtles. Can be set.")
public class ExprTurtleName extends SimplePropertyExpression<Turtle, String> {

	static {
		register(ExprTurtleName.class, String.class, "[turtle] name[s]", "turtles");
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	public String convert(Turtle turtle) {
		return turtle.getName();
	}

	@Override
	protected String getPropertyName() {
		return "turtle name";
	}

	@Override
	public Class<?>[] acceptChange(ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return new Class[]{String.class};
		return null;
	}

	@Override
	public void change(Event event, Object[] delta, ChangeMode mode) {
		Expression<? extends Turtle> turtles = getExpr();
		if (turtles == null)
			return;
		String name = (String) delta[0];
		if (name == null)
			return;
		for (Turtle turtle : turtles.getArray(event))
			turtle.setName(name);
	}

}
