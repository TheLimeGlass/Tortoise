package io.github.bi0qaw.tortoise.elements;

import java.util.UUID;
import java.util.regex.Pattern;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import io.github.bi0qaw.tortoise.Turtle;
import io.github.bi0qaw.tortoise.TurtleManager;

public class Types {

	static {
		Classes.registerClass(new ClassInfo<Turtle>(Turtle.class, "turtle")
			.name("Turtle")
			.defaultExpression(new EventValueExpression<Turtle>(Turtle.class))
			.parser(new Parser<Turtle>() {

				@Override
				public Turtle parse(String input, ParseContext context) {
					if (input.startsWith("turtle "))
						input = input.split(Pattern.quote(" "))[1];
					UUID uuid;
					try {
						uuid = UUID.fromString(input);
					} catch (Exception e) {
						return null;
					}
					if (TurtleManager.isRegistered(uuid))
						return TurtleManager.getTurtle(uuid);
					return null;
				}

				@Override
				public boolean canParse(ParseContext context) {
					return true;
				}

				@Override
				public String toString(Turtle turtle, int i) {
					if (turtle.getName() != null)
						return "turtle " + turtle.getId().toString() + " named \"" + turtle.getName() + "\"";
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
