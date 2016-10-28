package io.github.bi0qaw.tortoise;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.classes.Serializer;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.yggdrasil.Fields;
import org.bukkit.util.Vector;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VectorType {

	public VectorType(){}

	static {
		Classes.registerClass(new ClassInfo<Vector>(Vector.class, "vector")
				.user("vectors?")
				.name("Vector")
				.description("")
				.usage("")
				.examples("")
				.defaultExpression(new EventValueExpression<Vector>(Vector.class))
				.parser(new Parser<Vector>() {

					private final Pattern parsePattern = Pattern.compile("x?(:|=)? ?(-?\\d+(\\.\\d+)?), ?y?(:|=)? ?(-?\\d+(\\.\\d+)?), ?z?(:|=)? ?(-?\\d+(\\.\\d+)?)"
							, Pattern.CASE_INSENSITIVE);

					@Override
					public Vector parse(String s, ParseContext parseContext) {
						final Matcher m = parsePattern.matcher(s);
						if (m.matches()) {
							double x = Double.parseDouble(m.group(2));
							double y = Double.parseDouble(m.group(5));
							double z = Double.parseDouble(m.group(8));
							return new Vector(x, y, z);
						}
						return null;
					}

					@Override
					public boolean canParse(ParseContext context) {
						return (context == ParseContext.COMMAND || context == ParseContext.EVENT);
					}

					@Override
					public String toString(Vector v, int i) {
						return "x: " + Skript.toString(v.getX())
								+ ", y: " + Skript.toString(v.getY())
								+ ", z: " + Skript.toString(v.getZ());
					}

					@Override
					public String toVariableNameString(Vector v) {
						return v.getX() + ", " + v.getY() + ", " + v.getZ();
					}

					@Override
					public String getVariableNamePattern() {
						return "-?\\d+(\\.\\d+)?,-?\\d+(\\.\\d+)?,-?\\d+(\\.\\d+)?";
					}

					@Override
					public String getDebugMessage(Vector v) {
						return "(" + v.getX() + "," + v.getY() + "," + v.getZ() + ")";
					}
				})
				.serializer(new Serializer<Vector>() {
					@Override
					public Fields serialize(Vector v) throws NotSerializableException {
						final Fields f = new Fields();
						f.putPrimitive("x", v.getX());
						f.putPrimitive("y", v.getY());
						f.putPrimitive("z", v.getZ());
						return f;
					}

					@Override
					public void deserialize(Vector vector, Fields fields) throws StreamCorruptedException, NotSerializableException {
						assert false;
					}

					@Override
					protected Vector deserialize(Fields f) throws StreamCorruptedException, NotSerializableException {
						return new Vector(f.getPrimitive("x", double.class),
								f.getPrimitive("y", double.class), f.getPrimitive("z", double.class));
					}

					@Override
					public boolean mustSyncDeserialization() {
						return true;
					}

					@Override
					protected boolean canBeInstantiated() {
						return false;
					}
				})
		);
	}
}
