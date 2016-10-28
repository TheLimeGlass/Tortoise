package io.github.bi0qaw.tortoise;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;

public class TurtleType {

	public TurtleType(){}

	static {
		Classes.registerClass(new ClassInfo<Turtle>(Turtle.class, "turtle")
			.name("Turtle")
			.defaultExpression(new EventValueExpression<Turtle>(Turtle.class))
			.parser(new Parser<Turtle>() {
				@Override
				public Turtle parse(String s, ParseContext parseContext) {
					return null;
				}

				@Override
				public String toString(Turtle turtle, int i) {
					if (turtle.getName() != null) {
						return "turtle " + turtle.getId().toString() + " named \"" + turtle.getName() + "\"";
					}
					return "turtle " + turtle.getId().toString();
				}

				@Override
				public String toVariableNameString(Turtle turtle) {
					return turtle.getId().toString();
				}

				@Override
				public String getVariableNamePattern() {
					return "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";
				}
			}));
	}
}
