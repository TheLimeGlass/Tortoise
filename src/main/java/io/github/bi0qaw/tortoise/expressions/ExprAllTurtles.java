package io.github.bi0qaw.tortoise.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;
import io.github.bi0qaw.tortoise.TurtleManager;
import org.bukkit.event.Event;

import java.util.ArrayList;

public class ExprAllTurtles extends SimpleExpression<Turtle> {

	@Override
	protected Turtle[] get(Event event) {
		ArrayList<Turtle> turtles = TurtleManager.getAll();
		return turtles.toArray(new Turtle[turtles.size()]);
	}

	public boolean isSingle() {
		return false;
	}

	public Class<? extends Turtle> getReturnType() {
		return Turtle.class;
	}

	public String toString(Event event, boolean b) {
		return "all turtles";
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		return true;
	}
}
