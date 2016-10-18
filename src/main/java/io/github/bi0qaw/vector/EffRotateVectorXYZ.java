package io.github.bi0qaw.vector;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

public class EffRotateVectorXYZ extends Effect{

	Expression<Vector> vector;
	Expression<Number> number;
	int parseMark;

	public String toString(Event event, boolean b) {
		return "rotate round XYZ";
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		vector = (Expression<Vector>)expressions[0];
		number = (Expression<Number>)expressions[1];
		parseMark = parseResult.mark;
		return true;
	}

	@Override
	protected void execute(Event event) {
		Vector v = vector.getSingle(event);
		Number n = number.getSingle(event);
		if (v == null || n == null){
			return;
		}
		switch (parseMark) {
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
