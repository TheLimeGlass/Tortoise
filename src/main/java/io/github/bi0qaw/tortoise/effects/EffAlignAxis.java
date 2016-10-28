package io.github.bi0qaw.tortoise.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

public class EffAlignAxis extends Effect {

	private Expression<Turtle> turtles;
	private Expression<Vector> vector;
	int mark;

	@Override
	protected void execute(Event event) {
		Turtle[] ts = turtles.getArray(event);
		Vector v = vector.getSingle(event);
		if ( ts == null || v == null){
			return;
		}
		switch (mark){
			case 0:
				for (Turtle t : ts) {
					t.setU(v);
				}
				break;
			case 1:
				for (Turtle t : ts) {
					t.setU(v.clone().multiply(-1));
				}
				break;
			case 2:
				for (Turtle t : ts) {
					t.setV(v);
				}
				break;
			case 3:
				for (Turtle t : ts) {
					t.setV(v.clone().multiply(-1));
				}
				break;
			case 4:
				for (Turtle t : ts) {
					t.setW(v);
				}
				break;
			case 5:
				for (Turtle t : ts) {
					t.setW(v.clone().multiply(-1));
				}
				break;
			default:
				break;
		}
	}

	public String toString(Event event, boolean b) {
		return "align axis";
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		turtles = (Expression<Turtle>) expressions[0];
		vector = (Expression<Vector>) expressions[1];
		mark = parseResult.mark;
		return true;
	}
}
