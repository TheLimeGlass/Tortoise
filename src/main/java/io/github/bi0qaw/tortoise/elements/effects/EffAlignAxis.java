package io.github.bi0qaw.tortoise.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;
import org.bukkit.event.Event;
import org.bukkit.util.Vector;

@Name("Turtle Align Axis")
@Description("Align the axis of a turtle with a vector.")
public class EffAlignAxis extends Effect {

	static {
		Skript.registerEffect(EffAlignAxis.class, "align (0¦forward|1¦backward|2¦upward|3¦downward|4¦right[ward]|5¦left[ward])(-| )axis of %turtles% with %vector%");
	}

	private Expression<Turtle> turtles;
	private Expression<Vector> vector;
	int mark;

	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		turtles = (Expression<Turtle>) expressions[0];
		vector = (Expression<Vector>) expressions[1];
		mark = parseResult.mark;
		return true;
	}

	@Override
	protected void execute(Event event) {
		if (turtles == null || vector == null)
			return;
		Turtle[] turtles = this.turtles.getArray(event);
		Vector vector = this.vector.getSingle(event);
		switch (mark) {
			case 0:
				for (Turtle turtle : turtles)
					turtle.setU(vector);
				break;
			case 1:
				for (Turtle turtle : turtles)
					turtle.setU(vector.clone().multiply(-1));
				break;
			case 2:
				for (Turtle turtle : turtles)
					turtle.setV(vector);
				break;
			case 3:
				for (Turtle turtle : turtles)
					turtle.setV(vector.clone().multiply(-1));
				break;
			case 4:
				for (Turtle turtle : turtles)
					turtle.setW(vector);
				break;
			case 5:
				for (Turtle turtle : turtles)
					turtle.setW(vector.clone().multiply(-1));
				break;
			default:
				break;
		}
	}

	public String toString(Event event, boolean debug) {
		if (event == null)
			return "align axis";
		return "align axis of turtles " + turtles.toString(event, debug) + " with vector " + vector.toString(event, debug);
	}

}
