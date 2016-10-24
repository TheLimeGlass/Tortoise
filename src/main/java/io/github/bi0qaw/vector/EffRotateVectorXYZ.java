package io.github.bi0qaw.vector;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

public class EffRotateVectorXYZ extends Effect{

	private final static Character[] axes = new Character[] {'x', 'y', 'z'};

	private Expression<Vector> vector;
	private Expression<Number> number;
	private int mark;

	public String toString(Event event, boolean b) {
		return "rotate " + vector.toString() + " around " + axes[mark] + "-axis";
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		vector = (Expression<Vector>)expressions[0];
		number = (Expression<Number>)expressions[1];
		mark = parseResult.mark;
		return true;
	}

	@Override
	protected void execute(Event event) {
		Vector v = vector.getSingle(event);
		Number n = number.getSingle(event);
		if (v == null || n == null){
			return;
		}
		switch (mark) {
			case 1:
				VectorMath.rotX(v, n.doubleValue());
				break;
			case 2:
				VectorMath.rotY(v, n.doubleValue());
				break;
			case 3:
				VectorMath.rotZ(v, n.doubleValue());
		}
		return;
	}
}
