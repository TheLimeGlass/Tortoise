package io.github.bi0qaw.vector;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

public class ExprVectorLocationArithmetic extends SimpleExpression<Location> {

	private Expression<Location> location;
	private Expression<Vector> vector;
	private int parseMark;

	public boolean isSingle() {
		return true;
	}

	public String toString(Event event, boolean b) {
		return "location vector arithmetic";
	}

	public Class<? extends Location> getReturnType() {
		return Location.class;
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		location = (Expression<Location>) expressions[0];
		vector = (Expression<Vector>) expressions[1];
		parseMark = parseResult.mark;
		return true;
	}

	@Override
	public Location[] get(Event event) {
		Location l = location.getSingle(event);
		Vector v = vector.getSingle(event);
		if (l == null || v == null) {
			return null;
		}
		switch (parseMark) {
			case 1:
				return new Location[]{l.clone().add(v)};
			case 2:
				return new Location[]{l.clone().subtract(v)};
		}
		return null;
	}
}
