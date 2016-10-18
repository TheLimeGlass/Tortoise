package io.github.bi0qaw.vector;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.util.Vector;

public class ExprVectorYawPitch extends SimplePropertyExpression<Vector, Number> {

	private int mark;

	@Override
	public Number convert(Vector vector) {
		if (vector != null){
			switch (mark){
				case 0:
					return VectorMath.skriptYaw(VectorMath.getYaw(vector));
				case 1:
					return VectorMath.skriptPitch(VectorMath.getPitch(vector));
				default:
					break;
			}
		}
		return null;
	}

	@Override
	protected String getPropertyName() {
		return "yaw/pitch of vector";
	}

	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@Override
	public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
		super.init(exprs, matchedPattern, isDelayed, parseResult);
		mark = parseResult.mark;
		return true;
	}
}
