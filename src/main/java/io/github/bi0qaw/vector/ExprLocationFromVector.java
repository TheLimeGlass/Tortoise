package io.github.bi0qaw.vector;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

public class ExprLocationFromVector extends SimpleExpression<Location> {

	private Expression<Vector> vector;
	private Expression<World> world;
	private Expression<Number> yaw;
	private Expression<Number> pitch;

	public boolean isSingle() {
		return true;
	}

	public Class<? extends Location> getReturnType() {
		return Location.class;
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		vector = (Expression<Vector>)expressions[0];
		world = (Expression<World>)expressions[1];
		yaw = (Expression<Number>)expressions[2];
		pitch = (Expression<Number>)expressions[3];
		return true;
	}

	@Override
	protected Location[] get(Event event) {
		Vector v = vector.getSingle(event);
		World w = world.getSingle(event);
		Number y = yaw.getSingle(event);
		Number p = pitch.getSingle(event);
		if (v == null || w == null){
			return null;
		}
		if (y == null || p == null){
			return new Location[]{ v.toLocation(w)};
		}
		else {
			return new Location[]{v.toLocation(w, y.floatValue(), p.floatValue())};
		}
	}

	public String toString(Event event, boolean b) {
		return "location from vector";
	}
}
