package io.github.bi0qaw.tortoise.elements;

import org.bukkit.Location;

import ch.njol.skript.classes.Converter;
import ch.njol.skript.registrations.Converters;
import io.github.bi0qaw.tortoise.Turtle;

public class DefaultConverters {

	static {
		Converters.registerConverter(Turtle.class, Location.class, new Converter<Turtle, Location>() {
			public Location convert(Turtle turtle) {
				return turtle.getLocation();
			}
		});
		Converters.registerConverter(Turtle.class, String.class, new Converter<Turtle, String>() {
			public String convert(Turtle turtle) {
				return turtle.getId().toString();
			}
		});
	}

}
