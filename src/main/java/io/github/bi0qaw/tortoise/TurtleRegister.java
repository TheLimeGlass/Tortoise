package io.github.bi0qaw.tortoise;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Converter;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.registrations.Converters;
import io.github.bi0qaw.tortoise.effects.*;
import io.github.bi0qaw.tortoise.expressions.*;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class TurtleRegister {

	public TurtleRegister(){}

	static {

		// Effects
		Skript.registerEffect(EffAlignAxis.class, "align (0¦forward|1¦backward|2¦upward|3¦downward|4¦right[ward]|5¦left[ward])(-| )axis of %turtles% with %vector%");
		Skript.registerEffect(EffMoveRotateTurtle.class, "(0¦move|1¦rotate) %turtles%[ by][ ]%number%,[ ]%number%,[ ]%number%");
		Skript.registerEffect(EffMoveRotateTurtleByVector.class, "(0¦move|1¦rotate) %turtles%[ by][ ]%vectors%)");
		Skript.registerEffect(EffMoveTurtle.class, "move %turtles% to %location%");
		Skript.registerEffect(EffReleaseTurtle.class, "release %turtles%[ (1¦with children|2¦with descendants)]");
		Skript.registerEffect(EffRemoveTurtle.class, "remove %turtles%");
		Skript.registerEffect(EffRunTurtleFunction.class, "(run|execute) function of %turtles%", "(run|execute) %turtles%['s] function");
		Skript.registerEffect(EffStopTurtle.class, "stop %turtles%");
		Skript.registerEffect(EffTurtleFunction.class, "set function of %turtles% to <(.+)>\\([%-objects%[, %-objects%][, %-objects%][, %-objects%][, %-objects%][, %-objects%][, %-objects%][, %-objects%][, %-objects%][, %-objects%]]\\)");

		// Expressions
		Skript.registerExpression(ExprAllTurtles.class, Turtle.class, ExpressionType.SIMPLE, "all turtles");
		Skript.registerExpression(ExprLocationXYZFromTurtle.class, Location.class, ExpressionType.SIMPLE, "location %number%,[ ]%number%, [ ]%number% from %turtle%");
		Skript.registerExpression(ExprNewTurtle.class, Turtle.class, ExpressionType.SIMPLE, "new turtle at %location%");
		Skript.registerExpression(ExprTurtleAxis.class, Vector.class, ExpressionType.PROPERTY,
				"(0¦forward|1¦backward|2¦upward|3¦downward|4¦right[ward]|5¦left[ward])(-| )axis of %turtle%",
				"%turtle%['s] (0¦forward|1¦backward|2¦upward|3¦downward|4¦right[ward]|5¦left[ward])(-| )axis");
		Skript.registerExpression(ExprTurtleOffspring.class, Turtle.class, ExpressionType.SIMPLE, "(0¦children|1¦descendants) of %turtle%", "%turtle%['s] (0¦children|1¦descendants)");
		Skript.registerExpression(ExprTurtleFollowRotation.class, Boolean.class, ExpressionType.PROPERTY, "%turtle% follows rotation of parent");
		Skript.registerExpression(ExprTurtleFromId.class, Turtle.class, ExpressionType.SIMPLE, "turtle %string%");
		Skript.registerExpression(ExprTurtleHasParent.class, Boolean.class, ExpressionType.PROPERTY, "%turtle% has parent");
		Skript.registerExpression(ExprTurtleHeartbeat.class, Number.class, ExpressionType.PROPERTY, "heartbeat of %turtle%", "%turtle%['s] heartbeat");
		Skript.registerExpression(ExprTurtleId.class, String.class, ExpressionType.PROPERTY, "id of %turtle%", "%turtle%['s] id");
		Skript.registerExpression(ExprTurtleIsFamily.class, Boolean.class, ExpressionType.SIMPLE, "%turtle% is (0¦parent|1¦child|2¦descendant) of %turtle%");
		Skript.registerExpression(ExprTurtleLocation.class, Location.class, ExpressionType.PROPERTY, "location (of|at) %turtle%", "%turtle%['s] location");
		Skript.registerExpression(ExprTurtleName.class, String.class, ExpressionType.PROPERTY, "turtle name of %turtle%", "%turtle%['s] turtle name");
		Skript.registerExpression(ExprTurtleParent.class, Turtle.class, ExpressionType.PROPERTY, "parent of %turtle%", "%turtle%['s] parent");

		// Converter
		Converters.registerConverter(Turtle.class, Location.class, new Converter<Turtle, Location>() {
			public Location convert(Turtle turtle) {
				return turtle.getLocation();
			}
		});

	}
}
