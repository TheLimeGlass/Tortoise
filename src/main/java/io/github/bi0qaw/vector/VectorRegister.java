package io.github.bi0qaw.vector;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class VectorRegister {

	public VectorRegister(){}

	static {

		Skript.registerEffect(EffRotateVectorAroundAnother.class, "rotate %vector% around %vector% by %number%[ degrees]");
		Skript.registerEffect(EffRotateVectorXYZ.class, "rotate %vector% around (1¦x|2¦y|3¦z)(-| )axis by %number%[ degrees]");

		Skript.registerExpression(ExprAngleBetweenVectors.class, Float.class, ExpressionType.SIMPLE, "angle between %vector% and %vector%");
		Skript.registerExpression(ExprCrossProduct.class, Vector.class, ExpressionType.SIMPLE, "%vector% cross %vector%");
		Skript.registerExpression(ExprCylindricalVector.class, Vector.class, ExpressionType.SIMPLE, "[new ]cylindrical vector[ from] %number%, %number%(,| and) %number%");
		Skript.registerExpression(ExprDotProduct.class, Double.class, ExpressionType.SIMPLE, "%vector% dot %vector%");
		Skript.registerExpression(ExprLocationFromVector.class, Location.class, ExpressionType.SIMPLE,
				"%vector% to location in %world%", "location (from|of) %vector% (from|in) %world%",
				"%vector% to location in %world% with yaw %number% and pitch %number%",
				"location (from|of) %vector% (in|from) %world% with yaw %number% and pitch %number%");
		Skript.registerExpression(ExprRandomVector.class, Vector.class, ExpressionType.SIMPLE, "random vector");
		Skript.registerExpression(ExprSphericalVector.class, Vector.class, ExpressionType.SIMPLE, "[new ]spherical vector[ from] %number%, %number%(,| and) %number%");
		Skript.registerExpression(ExprVectorArithmetic.class, Vector.class, ExpressionType.SIMPLE, "%vector%[ ](1¦v+|2¦v-|3¦v*|4¦v/)[ ]%vector%");
		Skript.registerExpression(ExprVectorBetweenLocations.class, Vector.class, ExpressionType.SIMPLE, "vector (from|between) %location% (to|and) %location%");
		Skript.registerExpression(ExprVectorFromPitchAndYaw.class, Vector.class, ExpressionType.SIMPLE, "[new ]vector from yaw %number% and pitch %number%");
		Skript.registerExpression(ExprVectorFromXYZ.class, Vector.class, ExpressionType.SIMPLE, "[new ]vector[ from] %number%,[ ]%number%(,[ ]| and )%number%");
		Skript.registerExpression(ExprVectorLength.class, Double.class, ExpressionType.PROPERTY, "length of %vector%", "%vector%['s] length%");
		Skript.registerExpression(ExprVectorLocationArithmetic.class, Location.class, ExpressionType.SIMPLE, "%location%[ ](1¦l+|2¦l-)[ ]%vector%");
		Skript.registerExpression(ExprVectorNormalize.class, Vector.class, ExpressionType.SIMPLE, "normalize %vector%", "%vector% normalized");
		Skript.registerExpression(ExprVectorNumberArithmetic.class, Vector.class, ExpressionType.SIMPLE, "%vector%[ ](1¦n*|2¦n/)[ ]%number%", "%number%[ ](3¦*)[ ]%vector%");
		Skript.registerExpression(ExprVectorOfLocation.class, Vector.class, ExpressionType.SIMPLE, "vector (of|from|to) %location%", "%location%['s] vector");
		Skript.registerExpression(ExprVectorSquaredLength.class, Double.class, ExpressionType.SIMPLE, "squared length of %vector%", "%vector%['s] squared length");
		Skript.registerExpression(ExprVectorXYZ.class, Number.class, ExpressionType.PROPERTY, "(0¦vx|1¦vy|2¦vz)(-| )(coord[inate]|pos[ition]|loc[ation])[s] of %vector%");
		Skript.registerExpression(ExprVectorYawPitch.class, Number.class, ExpressionType.PROPERTY,"(0¦vyaw|1¦vpitch) of %vector%");
	}
}
