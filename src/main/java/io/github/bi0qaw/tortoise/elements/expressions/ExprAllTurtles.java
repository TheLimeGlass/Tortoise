package io.github.bi0qaw.tortoise.elements.expressions;

import java.util.List;

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
import io.github.bi0qaw.tortoise.TurtleManager;

@Name("Turtles")
@Description("Returns list of all turtles registered.")
public class ExprAllTurtles extends SimpleExpression<Turtle> {

	static {
		Skript.registerExpression(ExprAllTurtles.class, Turtle.class, ExpressionType.SIMPLE, "(all [[of] the]|the) turtles");
	}

	public Class<? extends Turtle> getReturnType() {
		return Turtle.class;
	}

	public boolean isSingle() {
		return false;
	}

	public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		return true;
	}

	@Override
	protected Turtle[] get(Event event) {
		List<Turtle> turtles = TurtleManager.getAll();
		return turtles.toArray(new Turtle[turtles.size()]);
	}

	public String toString(Event event, boolean debug) {
		return "all turtles";
	}

}
