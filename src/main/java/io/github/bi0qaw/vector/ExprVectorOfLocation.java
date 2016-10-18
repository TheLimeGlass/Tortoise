package io.github.bi0qaw.vector;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

public class ExprVectorOfLocation extends SimpleExpression<Vector> {

	private Expression<Location> location;

	public boolean isSingle() {
		return true;
	}

	public Class<? extends Vector> getReturnType() {
		return Vector.class;
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		location = (Expression<Location>)expressions[0];
		return true;
	}

	@Override
	protected Vector[] get(Event event) {
		Location l = location.getSingle(event);
		if (l == null){
			return null;
		}
		return new Vector[] { l.toVector() };
	}

	public String toString(Event event, boolean b) {
		return "vector of location";
	}
}
