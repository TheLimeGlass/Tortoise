package io.github.bi0qaw.vector;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Converter;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.registrations.Converters;
import ch.njol.skript.util.Direction;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class VectorRegister {

	public VectorRegister(){}

	static {

		// Effects
		Skript.registerEffect(EffRotateVectorAroundAnother.class, "rotate %vector% around %vector% by %number% [degrees]");
		Skript.registerEffect(EffRotateVectorXYZ.class, "rotate %vector% around (1¦x|2¦y|3¦z)(-| )axis by %number% [degrees]");

		// Expressions
		Skript.registerExpression(ExprAngleBetweenVectors.class, Float.class, ExpressionType.SIMPLE, "angle between %vector% and %vector%");
		Skript.registerExpression(ExprVectorArithmetic.class, Vector.class, ExpressionType.SIMPLE, ExprVectorArithmetic.patterns.getPatterns());
		Skript.registerExpression(ExprCrossProduct.class, Vector.class, ExpressionType.SIMPLE, "%vector% cross %vector%");
		Skript.registerExpression(ExprCylindricalVector.class, Vector.class, ExpressionType.SIMPLE, "[new] cylindrical vector [from|with] [radius] %number%, [yaw] %number%(,| and) [height] %number%");
		Skript.registerExpression(ExprDotProduct.class, Double.class, ExpressionType.SIMPLE, "%vector% dot %vector%");
		Skript.registerExpression(ExprLocationFromVector.class, Location.class, ExpressionType.SIMPLE,
				"%vector% [to location] [in] %world%", "location (from|of) %vector% [(from|in)] %world%",
				"%vector% [to location] [in] %world% with yaw %number% and pitch %number%",
				"location (from|of) %vector% [(in|from)] %world% with yaw %number% and pitch %number%");
		Skript.registerExpression(ExprRandomVector.class, Vector.class, ExpressionType.SIMPLE, "random vector");
		Skript.registerExpression(ExprSphericalVector.class, Vector.class, ExpressionType.SIMPLE, "[new] spherical vector [from|with] [radius] %number%, [yaw] %number%(,| and) [pitch] %number%");
		Skript.registerExpression(ExprVectorBetweenLocations.class, Vector.class, ExpressionType.SIMPLE, "vector (from|between) %location% (to|and) %location%");
		Skript.registerExpression(ExprVectorFromYawAndPitch.class, Vector.class, ExpressionType.SIMPLE, "[new] vector from yaw %number% and pitch %number%");
		Skript.registerExpression(ExprVectorFromXYZ.class, Vector.class, ExpressionType.SIMPLE, "[new] vector [(from|at|to)] %number%,[ ]%number%(,[ ]| and )%number%");
		Skript.registerExpression(ExprVectorLength.class, Double.class, ExpressionType.PROPERTY, "length of %vector%", "%vector%['s] length%");
		Skript.registerExpression(ExprVectorNormalize.class, Vector.class, ExpressionType.SIMPLE, "normalize %vector%", "%vector% normalized");
		Skript.registerExpression(ExprVectorOfLocation.class, Vector.class, ExpressionType.SIMPLE, "vector (of|from|to) %location%", "%location%['s] vector");
		Skript.registerExpression(ExprVectorSquaredLength.class, Double.class, ExpressionType.SIMPLE, "squared length of %vector%", "%vector%['s] squared length");
		Skript.registerExpression(ExprVectorXYZ.class, Number.class, ExpressionType.PROPERTY, "(0¦xx|1¦yy|2¦zz)(-| )(coord[inate]|pos[ition]|loc[ation])[s] of %vector%");
		Skript.registerExpression(ExprVectorYawPitch.class, Number.class, ExpressionType.PROPERTY,"(0¦yyaw|1¦ppitch) of %vector%");

		// Converters
		Converters.registerConverter(Location.class, Vector.class, new Converter<Location, Vector>() {
			public Vector convert(Location location) {
				if (location == null) {
					return null;
				}
				return new Vector(location.getX(), location.getY(), location.getZ());
			}
		});

		Converters.registerConverter(Number.class, Vector.class, new Converter<Number, Vector>() {
			public Vector convert(Number number) {
				if (number == null) {
					return null;
				}
				return new Vector(number.doubleValue(), number.doubleValue(), number.doubleValue());
			}
		});

		Converters.registerConverter(Vector.class, Direction.class, new Converter<Vector, Direction>() {
			public Direction convert(Vector vector) {
				if (vector == null) {
					return null;
				}
				float pitch =(float) (VectorMath.skriptPitch(VectorMath.getPitch(vector)) * VectorMath.DEG_TO_RAD);
				float yaw = (float) (VectorMath.skriptYaw(VectorMath.getYaw(vector)) * VectorMath.DEG_TO_RAD);
				double length = vector.length();
				return new Direction(pitch, yaw, length);
			}
		});
	}
}
