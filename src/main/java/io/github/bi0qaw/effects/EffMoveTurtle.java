package io.github.bi0qaw.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.Turtle;
import org.bukkit.event.Event;

public class EffMoveTurtle extends Effect {

	private Expression<Turtle> turtle;
	private Expression<Number> u, v, w;
	private int mark;

	@Override
	protected void execute(Event event) {
		Number u = this.u.getSingle(event);
		Number v = this.v.getSingle(event);
		Number w = this.w.getSingle(event);
		if ( u == null || v == null || w == null) {
			return;
		}
		switch (mark) {
			case 0:
				for (Turtle t : turtle.getAll(event)){
					t.move(u.doubleValue(),v.doubleValue(),w.doubleValue());
				}
				break;
			case 1:
				for (Turtle t : turtle.getAll(event)){
					t.rotateU(u.doubleValue());
					t.rotateV(v.doubleValue());
					t.rotateW(w.doubleValue());
				}
				break;
			default:
				break;
		}
	}

	public String toString(Event event, boolean b) {
		return "move turtle";
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		turtle = (Expression<Turtle>) expressions[0];
		u = (Expression<Number>) expressions[1];
		v = (Expression<Number>) expressions[2];
		w = (Expression<Number>) expressions[3];
		mark = parseResult.mark;
		return true;
	}
}
