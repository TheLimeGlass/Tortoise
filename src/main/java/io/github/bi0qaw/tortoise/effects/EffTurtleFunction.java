package io.github.bi0qaw.tortoise.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.function.Function;
import ch.njol.skript.lang.function.Functions;
import ch.njol.skript.lang.function.Parameter;
import ch.njol.util.Kleenean;
import io.github.bi0qaw.tortoise.Turtle;
import org.bukkit.event.Event;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;

public class EffTurtleFunction extends Effect {

	private Expression<Turtle> turtle;
	private Function<?> function;
	private List<Expression<?>> parameters = new ArrayList<Expression<?>>();

	@Override
	protected void execute(Event event) {
		for (Turtle t : turtle.getAll(event)){
			t.setFunction(function);
			t.setParameters(getParam(function, parameters, event));
		}
	}

	public String toString(Event event, boolean b) {
		return "turtle function";
	}

	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		int max = expressions.length;
		turtle = (Expression<Turtle>) expressions[0];
		function = Functions.getFunction(((MatchResult) parseResult.regexes.get(0)).group(0));
		if (this.function == null) {
			Skript.error("There isn't any function called '" + (parseResult.regexes.get(0)).group(0) + "'.");
			return false;
		}
		for (int j = 1; j < max - 1; j++) {
			parameters.add(expressions[j]);
		}
		return true;
	}

	private Object[][] getParam(Function<?> f, List<Expression<?>> param, Event e)
	{
		int max = param.size() < 1 ? 1 : f.getParameters().length < param.size() ? f.getParameters().length : param.size();
		Object[][] params = new Object[max][];
		if (param.size() > 0) {
			for (int x = 0; x < max; x++) {
				if ((x < param.size()) && (param.get(x) != null)) {
					params[x] = ((Expression)param.get(x)).getArray(e);
				}
			}
		}
		if (params[0] == null)
		{
			Expression<?> def = getDefault(f.getParameter(0));
			if (def != null) {
				params[0] = def.getArray(e);
			}
		}
		return params;
	}

	private Expression<?> getDefault(Parameter<?> param)
	{
		Field field = null;
		try
		{
			field = Parameter.class.getDeclaredField("def");
			field.setAccessible(true);
			return (Expression)field.get(param);
		}
		catch (Exception e) {}
		return null;
	}

}


