package io.github.bi0qaw.tortoise.effects;


import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;
import org.bukkit.event.Event;

public class EffReleaseTurtle extends Effect {

	private Expression<Turtle> turtle;
	private int mark;

	@Override
	protected void execute(Event event) {
		switch (mark) {
			case 0:
				for (Turtle t : turtle.getAll(event)){
					t.release();
				}
				return;
			case 1:
				for (Turtle t : turtle.getAll(event)){
					t.releaseWithChildren();
				}
				return;
			case 2:
				for (Turtle t : turtle.getAll(event)){
					t.releaseWithDescendants();
				}
				return;
			default:
				break;
		}

	}

	public String toString(Event event, boolean b) {
		return null;
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		turtle = (Expression<Turtle>) expressions[0];
		mark = parseResult.mark;
		return true;
	}
}
