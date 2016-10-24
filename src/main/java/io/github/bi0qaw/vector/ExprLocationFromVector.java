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
	private boolean yawpitch;

	public boolean isSingle() {
		return true;
	}

	public Class<? extends Location> getReturnType() {
		return Location.class;
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		if (expressions.length > 3) {
			yawpitch = true;
		}
		vector = (Expression<Vector>)expressions[0];
		world = (Expression<World>)expressions[1];
		if (yawpitch) {
			yaw = (Expression<Number>)expressions[2];
			pitch = (Expression<Number>)expressions[3];
		}
		return true;
	}

	@Override
	protected Location[] get(Event event) {
		Vector v = vector.getSingle(event);
		World w = world.getSingle(event);
		Number y = yaw != null ? yaw.getSingle(event) : null;
		Number p = pitch != null ? pitch.getSingle(event) : null;
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
		if (yawpitch) {
			return "location from " + vector.toString() + " with yaw " + yaw.toString() + " and pitch " + pitch.toString();
		}
		return "location from " + vector.toString();
	}
}
