package io.github.bi0qaw.tortoise.expressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;
import io.github.bi0qaw.tortoise.TurtleManager;
import org.bukkit.event.Event;

import java.util.UUID;

public class ExprTurtleFromId extends SimpleExpression<Turtle> {

	private Expression<String> id;

	@Override
	protected Turtle[] get(Event event) {
		String id = this.id.getSingle(event);
		if (id == null) {
			return null;
		}
		UUID uuid;
		try {
			uuid = UUID.fromString(id);
		}
		catch (Exception e){
			return null;
		}
		if (TurtleManager.isRegistered(uuid)) {
			return new Turtle[] {TurtleManager.getTurtle(uuid)};
		}
		return null;
	}

	public boolean isSingle() {
		return true;
	}

	public Class<? extends Turtle> getReturnType() {
		return Turtle.class;
	}

	public String toString(Event event, boolean b) {
		return "turtle from string";
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		id = (Expression<String>) expressions[0];
		return true;
	}
}
