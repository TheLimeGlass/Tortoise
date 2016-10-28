package io.github.bi0qaw.tortoise.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;
import org.bukkit.event.Event;

public class ExprTurtleOffspring extends SimpleExpression<Turtle> {

	private Expression<Turtle> turtle;
	private int mark;

	@Override
	protected Turtle[] get(Event event) {
		Turtle t = turtle.getSingle(event);
		if (t == null){
			return null;
		}
		if (mark == 0) {
			return t.getChildren().toArray(new Turtle[t.getChildren().size()]);
		} else if (mark == 1){
			return t.getDescendants().toArray(new Turtle[t.getDescendants().size()]);
		}
		return null;
	}

	public boolean isSingle() {
		return false;
	}

	public Class<? extends Turtle> getReturnType() {
		return Turtle.class;
	}

	public String toString(Event event, boolean b) {
		return "children / descendants of turtle";
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		turtle = (Expression<Turtle>) expressions[0];
		mark = parseResult.mark;
		return true;
	}
}
