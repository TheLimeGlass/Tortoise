package io.github.bi0qaw.tortoise.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;
import org.bukkit.event.Event;

public class EffMoveRotateTurtle extends Effect {

	private Expression<Turtle> turtles;
	private Expression<Number> u, v, w;
	private int mark;
	private String[] type = new String[] {"move", "rotate"};

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
				for (Turtle t : turtles.getArray(event)){
					t.move(u.doubleValue(),v.doubleValue(),w.doubleValue());
				}
				break;
			case 1:
				for (Turtle t : turtles.getArray(event)){
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
		return type[mark] + " turtle by " + u.toString() + ", " + v.toString() + ", " + w.toString();
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		turtles = (Expression<Turtle>) expressions[0];
		u = (Expression<Number>) expressions[1];
		v = (Expression<Number>) expressions[2];
		w = (Expression<Number>) expressions[3];
		mark = parseResult.mark;
		return true;
	}
}
