package io.github.bi0qaw.tortoise.elements.expressions;

import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;

@Name("Clone Turtle")
@Description("Returns the exact clone of a turtle.")
public class ExprCloneTurtle extends SimpleExpression<Turtle> {

	static {
		Skript.registerExpression(ExprCloneTurtle.class, Turtle.class, ExpressionType.SIMPLE, "clone [of] %turtle%");
	}

	private Expression<Turtle> turtle;

	public Class<? extends Turtle> getReturnType() {
		return Turtle.class;
	}

	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		turtle = (Expression<Turtle>) expressions[0];
		return true;
	}

	@Override
	protected Turtle[] get(Event event) {
		Turtle turtle = this.turtle.getSingle(event);
		if (turtle == null)
			return null;
		return new Turtle[] {turtle.clone()};
	}

	public String toString(Event event, boolean debug) {
		return null;
	}

}
