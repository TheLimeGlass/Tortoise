package io.github.bi0qaw.vector;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

public class ExprVectorYawPitch extends SimplePropertyExpression<Vector, Number> {

	private int mark;
	private final static String[] type = new String[] {"yyaw", "ppitch"};

	@Override
	public Number convert(Vector vector) {
		if (vector != null) {
			switch (mark) {
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
		return type[mark] + " of vector";
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

	@Override
	public Class<?>[] acceptChange(Changer.ChangeMode mode) {
		if ((mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.ADD || mode == Changer.ChangeMode.REMOVE) && getExpr().isSingle() && Changer.ChangerUtils.acceptsChange(getExpr(), Changer.ChangeMode.SET, Vector.class))
			return new Class[] { Number.class };
		return null;
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		Vector v = getExpr().getSingle(e);
		if (v == null){
			return;
		}
		float n = ((Number) delta[0]).floatValue();
		float yaw = VectorMath.getYaw(v);
		float pitch = VectorMath.getPitch(v);
		switch (mode) {
			case REMOVE:
				n = -n;
			case ADD:
				if (mark == 0){
					yaw += n;
				} else if (mark == 1){
					pitch -= n; // Negative because of minecraft's / skript's upside down pitch
				}
				v = VectorMath.fromYawAndPitch(yaw, pitch);
				getExpr().change(e, new Vector[]{v}, Changer.ChangeMode.SET);
				break;
			case SET:
				if (mark == 0){
					yaw = VectorMath.fromSkriptYaw(n);
				} else if (mark == 1){
					pitch = VectorMath.fromSkriptPitch(n);
				}
				v = VectorMath.fromYawAndPitch(yaw, pitch);
				getExpr().change(e, new Vector[]{v}, Changer.ChangeMode.SET);
				break;
			case REMOVE_ALL:
			case DELETE:
			case RESET:
				assert false;
		}
	}
}
