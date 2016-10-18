package io.github.bi0qaw.vector;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

public class ExprVectorFromXYZ extends SimpleExpression<Vector> {

	private Expression<Number> x, y, z;

	@Override
	protected Vector[] get(Event event) {
		Number x = this.x.getSingle(event);
		Number y = this.y.getSingle(event);
		Number z = this.z.getSingle(event);
		if ( x == null || y == null || z == null) {
			return null;
		}
		return new Vector[] {new Vector(x.doubleValue(), y.doubleValue(), z.doubleValue())};
	}

	public boolean isSingle() {
		return true;
	}

	public Class<? extends Vector> getReturnType() {
		return Vector.class;
	}

	public String toString(Event event, boolean b) {
		return "from x, y, z";
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		x = (Expression<Number>) expressions[0];
		y = (Expression<Number>) expressions[1];
		z = (Expression<Number>) expressions[2];
		return true;
	}
}
