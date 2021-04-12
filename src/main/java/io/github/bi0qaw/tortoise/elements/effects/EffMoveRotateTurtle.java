package io.github.bi0qaw.tortoise.elements.effects;

import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;

@Name("Move Turtle XYZ")
@Description("Moves/Rotates a turtle based on x, y and z coords")
public class EffMoveRotateTurtle extends Effect {

	static {
		Skript.registerEffect(EffMoveRotateTurtle.class, "(0¦move|1¦rotate) %turtles%[ by][ ]%number%,[ ]%number%,[ ]%number%");
	}

	private Expression<Turtle> turtles;
	private Expression<Number> u, v, w;
	private int mark;

	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		turtles = (Expression<Turtle>) expressions[0];
		u = (Expression<Number>) expressions[1];
		v = (Expression<Number>) expressions[2];
		w = (Expression<Number>) expressions[3];
		mark = parseResult.mark;
		return true;
	}

	@Override
	protected void execute(Event event) {
		if (u == null || v == null || w == null)
			return;
		double u = this.u.getSingle(event).doubleValue();
		double v = this.v.getSingle(event).doubleValue();
		double w = this.w.getSingle(event).doubleValue();
		switch (mark) {
			case 0:
				for (Turtle turtle : turtles.getArray(event))
					turtle.move(u, v, w);
				break;
			case 1:
				for (Turtle turtle : turtles.getArray(event)) {
					turtle.rotateU(u);
					turtle.rotateV(v);
					turtle.rotateW(w);
				}
				break;
			default:
				break;
		}
	}

	public String toString(Event event, boolean debug) {
		if (event == null)
			return "move/rotate turtles";
		return mark == 0 ? "move" : "rotate" + " turtle by " + u.toString(event, debug) + ", " + v.toString(event, debug) + ", " + w.toString(event, debug);
	}

}
