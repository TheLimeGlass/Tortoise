package io.github.bi0qaw.vector;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Patterns;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

import java.lang.reflect.Array;

public class ExprVectorArithmetic extends SimpleExpression<Vector> {

	private static enum Operator {
		PLUS("++") {
			@SuppressWarnings("null")
			public Vector calculate(final Vector v1, final Vector v2) {
				return v1.clone().add(v2);
			}
		},
		MINUS("--") {
			@SuppressWarnings("null")
			public Vector calculate(final Vector v1, final Vector v2) {
				return v1.clone().subtract(v2);
			}
		},
		MULT("**") {
			@SuppressWarnings("null")
			public Vector calculate(final Vector v1, final Vector v2) {
				return v1.clone().multiply(v2);
			}
		},
		DIV("//") {
			@SuppressWarnings("null")
			public Vector calculate(final Vector v1, final Vector v2) {
				return v1.clone().divide(v2);
			}
		};

		public final String sign;

		private Operator(final String sign) {
			this.sign = sign;
		}

		public abstract Vector calculate(Vector v1, Vector v2);

		@Override
		public String toString() {
			return sign;
		}
	}


	protected final static Patterns<Operator> patterns = new Patterns<Operator>(new Object[][] {

			{"%vector%[ ]++[ ]%vector%", Operator.PLUS},
			{"%vector%[ ]--[ ]%vector%", Operator.MINUS},

			{"%vector%[ ]**[ ]%vector%", Operator.MULT},
			{"%vector%[ ]//[ ]%vector%", Operator.DIV},
	});

	private Expression<Vector> first, second;
	private Operator op;

	@Override
	protected Vector[] get(Event event) {
		final Vector[] vectors = (Vector[]) Array.newInstance(Vector.class, 1);
		Vector v1 = first.getSingle(event), v2 = second.getSingle(event);
		if (v1 == null) {
			v1 = new Vector();
		}
		if (v2 == null) {
			v2 = new Vector();
		}
		vectors[0] = op.calculate(v1, v2);
		return vectors;
	}

	public boolean isSingle() {
		return true;
	}

	public Class<? extends Vector> getReturnType() {
		return Vector.class;
	}

	public String toString(Event event, boolean b) {
		return first.toString(event, b) + " " + op +  " " + second.toString(event, b);
	}

	public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		first = (Expression<Vector>) expressions[0];
		second = (Expression<Vector>) expressions[1];
		op = patterns.getInfo(matchedPattern);
		return true;
	}
}
