package io.github.bi0qaw.tortoise.elements.conditions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import io.github.bi0qaw.tortoise.Turtle;
import io.github.bi0qaw.tortoise.util.PropertyCondition;

@Name("Turtle Has Parent")
@Description("Checks if a turtle has a parent. (This is not the mob.)")
public class CondTurtleParent extends PropertyCondition<Turtle> {

	static {
		register(CondTurtleParent.class, "[turtle] parent", "turtles");
	}

	@Override
	public boolean check(Turtle turtle) {
		return turtle.hasParent();
	}

	@Override
	protected String getPropertyName() {
		return "parent";
	}

}
