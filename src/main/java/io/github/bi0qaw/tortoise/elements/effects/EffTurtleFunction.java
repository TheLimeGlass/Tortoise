package io.github.bi0qaw.tortoise.elements.effects;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;

import org.bukkit.event.Event;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.function.Function;
import ch.njol.skript.lang.function.Functions;
import ch.njol.skript.lang.function.Parameter;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;

@Name("Function Turtle")
@Description("Set a function on a turtle which can be called later with the run turtle function syntax.")
@Examples("set function of {_turtle} to openGUI(player, \"&6Example Header\")")
public class EffTurtleFunction extends Effect {

	static {
		Skript.registerEffect(EffTurtleFunction.class, "set function of %turtles% to <(.+)>\\([%-objects%[, %-objects%][, %-objects%][, %-objects%][, %-objects%][, %-objects%][, %-objects%][, %-objects%][, %-objects%][, %-objects%]]\\)");
	}

	private final List<Expression<?>> parameters = new ArrayList<Expression<?>>();
	private Expression<Turtle> turtles;
	private Function<?> function;

	@Override
	protected void execute(Event event) {
		for (Turtle turtle : turtles.getAll(event)){
			turtle.setFunction(function);
			turtle.setParameters(getParam(function, parameters, event));
		}
	}

	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
		int max = expressions.length;
		turtles = (Expression<Turtle>) expressions[0];
		function = Functions.getFunction(((MatchResult) parseResult.regexes.get(0)).group(0));
		if (this.function == null) {
			Skript.error("There isn't any function called '" + (parseResult.regexes.get(0)).group(0) + "'.");
			return false;
		}
		for (int j = 1; j < max - 1; j++)
			parameters.add(expressions[j]);
		return true;
	}

	public String toString(Event event, boolean debug) {
		return "turtle function";
	}

	private Object[][] getParam(Function<?> f, List<Expression<?>> param, Event e) {
		int max = param.size() < 1 ? 1 : f.getParameters().length < param.size() ? f.getParameters().length : param.size();
		Object[][] params = new Object[max][];
		if (param.size() > 0) {
			for (int x = 0; x < max; x++) {
				if (x < param.size() && param.get(x) != null)
					params[x] = ((Expression<?>)param.get(x)).getArray(e);
			}
		}
		if (params[0] == null) {
			Expression<?> def = getDefault(f.getParameter(0));
			if (def != null)
				params[0] = def.getArray(e);
		}
		return params;
	}

	private Expression<?> getDefault(Parameter<?> param) {
		Field field = null;
		try {
			field = Parameter.class.getDeclaredField("def");
			field.setAccessible(true);
			return (Expression<?>)field.get(param);
		}
		catch (Exception e) {}
		return null;
	}

}
