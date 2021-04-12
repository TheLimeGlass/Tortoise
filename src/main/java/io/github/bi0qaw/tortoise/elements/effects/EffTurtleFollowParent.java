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

@Name("Set Turtle Follow")
@Description("Makes turtles follow their parent's rotation")
public class EffTurtleFollowParent extends Effect {

	static {
		Skript.registerEffect(EffTurtleFollowParent.class, "(set|make) [turtle[s]] %turtles% [to] (0¦(unfollow|stop following)|1¦follow) (their|rotation of) parents");
	}

	private Expression<Turtle> turtles;
	private boolean follow;

	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		turtles = (Expression<Turtle>) expressions[0];
		follow = parseResult.mark == 1;
		return true;
	}

	@Override
	protected void execute(Event event) {
		for (Turtle turtle : turtles.getAll(event))
			turtle.setFollowRotation(follow);
	}

	public String toString(Event event, boolean debug) {
		if (event == null)
			return follow == true ? "follow" : "unfollow" + " turtles parents";
		return follow == true ? "follow" : "unfollow" + " turtles " + turtles.toString(event, debug) + " parents";
	}

}
