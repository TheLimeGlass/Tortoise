package io.github.bi0qaw.vector;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import org.bukkit.util.Vector;

public class ExprVectorSquaredLength extends SimplePropertyExpression<Vector, Double> {

	@Override
	public Double convert(Vector vector) {
		if (vector == null) return null;
		return vector.lengthSquared();
	}

	@Override
	protected String getPropertyName() {
		return "squared length of vector";
	}

	public Class<? extends Double> getReturnType() {
		return Double.class;
	}
}
