package io.github.bi0qaw.vector;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

public class ExprVectorNumberArithmetic extends SimpleExpression<Vector> {

	private Expression<Vector> vector;
	private Expression<Number> number;
	private int parseMark;

	public boolean isSingle() {
		return true;
	}

	public String toString(Event event, boolean b) {
		return "vector number arithmetic";
	}

	public Class<? extends Vector> getReturnType() {
		return Vector.class;
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		parseMark = parseResult.mark;
		switch (parseMark) {
			case 1:
			case 2:
				vector = (Expression<Vector>) expressions[0];
				number = (Expression<Number>) expressions[1];
				break;
			case 3:
				number = (Expression<Number>) expressions[0];
				vector = (Expression<Vector>) expressions[1];
				break;
			default:
				assert false;

		}
		return true;
	}

	@Override
	public Vector[] get(Event event) {
		Vector v = vector.getSingle(event);
		Number n = number.getSingle(event);
		if (v == null || n == null) {
			return null;
		}
		switch (parseMark) {
			case 1:
			case 3:
				return new Vector[]{v.clone().multiply(n.doubleValue())};
			case 2:
				return new Vector[]{v.clone().multiply(1 / n.doubleValue())};
			default:
				assert false;
		}
		return null;
	}
}
