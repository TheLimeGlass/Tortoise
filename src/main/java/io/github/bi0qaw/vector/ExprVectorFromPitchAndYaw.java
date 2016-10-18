package io.github.bi0qaw.vector;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import com.avaje.ebean.Expr;
import com.avaje.ebean.ValidationException;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

public class ExprVectorFromPitchAndYaw extends SimpleExpression<Vector> {

	private Expression<Number> pitch, yaw;

	@Override
	protected Vector[] get(Event event) {
		Number p = pitch.getSingle(event);
		Number y = yaw.getSingle(event);
		if (p == null || y == null) {
			return null;
		}
		float yaw = VectorMath.fromSkriptYaw(VectorMath.wrapAngleDeg(y.floatValue()));
		float pitch = VectorMath.fromSkriptPitch(VectorMath.wrapAngleDeg(p.floatValue()));
		return new Vector[]{ VectorMath.fromYawAndPitch( yaw, pitch)};
	}

	public boolean isSingle() {
		return true;
	}

	public Class<? extends Vector> getReturnType() {
		return Vector.class;
	}

	public String toString(Event event, boolean b) {
		return "from yaw and pitch";
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		pitch = (Expression<Number>) expressions[1];
		yaw = (Expression<Number>) expressions[0];
		return true;
	}
}
