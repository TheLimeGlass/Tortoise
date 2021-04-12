package io.github.bi0qaw.tortoise.elements.expressions;

import java.util.Arrays;

import org.bukkit.event.Event;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;

@Name("Turtle Family")
@Description("Returns the family of turtles (Childern or Descendants)")
public class ExprTurtleOffspring extends PropertyExpression<Turtle, Turtle> {

	static {
		register(ExprTurtleOffspring.class, Turtle.class, "(0¦children|1¦descendants)", "turtles");
	}

	private int mark;

	public Class<? extends Turtle> getReturnType() {
		return Turtle.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		setExpr((Expression<? extends Turtle>) exprs[0]);
		mark = parseResult.mark;
		return true;
	}

	@Override
	protected Turtle[] get(Event event, Turtle[] turtles) {
		return Arrays.stream(turtles).flatMap(turtle -> {
			if (mark == 0)
				return turtle.getChildren().stream();
			return turtle.getDescendants().stream();
		}).toArray(Turtle[]::new);
	}

	public String toString(Event event, boolean debug) {
		if (event == null)
			return mark == 0 ? "children" : "descendants" + " of turtles";
		return mark == 0 ? "children" : "descendants" + " of turtles " + getExpr().toString(event, debug);
	}

}
