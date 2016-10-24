package io.github.bi0qaw.vector;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Arithmetic;
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

					@Override
					public Vector parse(String s, ParseContext parseContext) {
						return null;
					}

					@Override
					public boolean canParse(ParseContext context) {
						return false;
					}

					@Override
					public String toString(Vector v, int i) {
						return "x: " + Skript.toString(v.getX())
								+ ", y: " + Skript.toString(v.getY())
								+ ", z: " + Skript.toString(v.getZ());
					}

					@Override
					public String toVariableNameString(Vector v) {
							return v.getX() + "," + v.getY() + "," + v.getZ();
					}

					@Override
					public String getVariableNamePattern() {
						return "\\d+(\\.\\d+)?,-?\\d+(\\.\\d+)?,-?\\d+(\\.\\d+)?";
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
				.math(Vector.class, new VectorArithmethic())
		);
	}
}
