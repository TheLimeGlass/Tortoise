package io.github.bi0qaw.tortoise.elements.effects;

import org.bukkit.event.Event;
import org.bukkit.util.Vector;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;

@Name("Move Turtle Vectory")
@Description("Moves/Rotates a turtle based on a vector")
public class EffMoveRotateTurtleByVector extends Effect {

	static {
		Skript.registerEffect(EffMoveRotateTurtleByVector.class, "(0¦move|1¦rotate) %turtles%[ by][ ]%vectors%)");
	}

	private Expression<Turtle> turtles;
	private Expression<Vector> vectors;
	private int mark;

	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		this.turtles = (Expression<Turtle>)expressions[0];
		this.vectors = (Expression<Vector>)expressions[1];
		this.mark = parseResult.mark;
		return true;
	}

	protected void execute(final Event event) {
		for (Turtle turtle : this.turtles.getArray(event)) {
			for (Vector vector : this.vectors.getArray(event)) {
				if (this.mark == 1) {
					turtle.rotateU(vector.getX());
					turtle.rotateV(vector.getY());
					turtle.rotateW(vector.getZ());
				} else {
					turtle.move(vector.getX(), vector.getY(), vector.getZ());
				}
			}
		}
	}

	public String toString(Event event, boolean debug) {
		return mark == 0 ? "move" : "rotate" + " turtle " + turtles.toString(event, debug) + " by " + vectors.toString();
	}

}
