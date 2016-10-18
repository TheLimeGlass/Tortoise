package io.github.bi0qaw.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.Turtle;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

public class EffAlignAxis extends Effect {

	private Expression<Turtle> turtle;
	private Expression<Vector> vector;
	int mark;

	@Override
	protected void execute(Event event) {
		Turtle t = turtle.getSingle(event);
		Vector v = vector.getSingle(event);
		if ( t == null || v == null){
			return;
		}
		switch (mark){
			case 0:
				t.setU(v);
				break;
			case 1:
				t.setU(v.clone().multiply(-1));
				break;
			case 2:
				t.setV(v);
				break;
			case 3:
				t.setV(v.clone().multiply(-1));
				break;
			case 4:
				t.setW(v);
				break;
			case 5:
				t.setW(v.clone().multiply(-1));
				break;
			default:
				break;
		}
	}

	public String toString(Event event, boolean b) {
		return "align axis";
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		turtle = (Expression<Turtle>) expressions[0];
		vector = (Expression<Vector>) expressions[1];
		mark = parseResult.mark;
		return true;
	}
}
