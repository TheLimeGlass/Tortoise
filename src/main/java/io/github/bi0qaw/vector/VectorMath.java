package io.github.bi0qaw.vector;

import org.bukkit.util.Vector;

public class VectorMath {

	private VectorMath() {}

	public static final double PI = Math.PI;
	public static final double HALF_PI = PI / 2;
	public static final double DEG_TO_RAD = PI / 180;
	public static final double RAD_TO_DEG =  180 / PI;

	public static Vector fromSphericalCoordinates(double radius, double theta, double phi) {
		double r = Math.abs(radius);
		double t = theta * DEG_TO_RAD;
		double p = phi * DEG_TO_RAD;
		double x = r * Math.sin(t) * Math.cos(p);
		double z = r * Math.sin(t) * Math.sin(p);
		double y = r * Math.cos(t);
		return new Vector(x, y, z);
	}

	public static Vector fromCylindricalCoordinates(double radius, double phi, double height) {
		double r = Math.abs(radius);
		double p = phi * DEG_TO_RAD;
		double x = r * Math.cos(p);
		double z = r * Math.sin(p);
		return new Vector(x, height, z);

	}

	public static Vector fromYawAndPitch(float yaw, float pitch) {
		double y = Math.sin(pitch * DEG_TO_RAD);
		double div = Math.cos(pitch * DEG_TO_RAD);
		double x = Math.cos(yaw * DEG_TO_RAD);
		double z = Math.sin(yaw * DEG_TO_RAD);
		return new Vector(x,0,z).multiply(div).setY(y);
	}

	public static float getYaw(Vector vector) {
		return (float) (Math.atan2(vector.getZ(), vector.getX()) * RAD_TO_DEG);
	}

	public static float getPitch(Vector vector) {
		double xy = Math.sqrt(vector.getX() * vector.getX() + vector.getZ() * vector.getZ());
		return (float) (Math.atan(vector.getY() / xy) * RAD_TO_DEG);
	}

	public static Vector setYaw(Vector vector, float yaw) {
		vector = fromYawAndPitch(yaw, getPitch(vector));
		return vector;
	}

	public static Vector setPitch(Vector vector, float pitch) {
		vector = fromYawAndPitch(getYaw(vector), pitch);
		return vector;
	}

	public static Vector rotX(Vector vector, double angle) {
		double sin = Math.sin(angle * DEG_TO_RAD);
		double cos = Math.cos(angle * DEG_TO_RAD);
		Vector vy = new Vector(0, cos, -sin);
		Vector vz = new Vector(0, sin, cos);
		vector.setY(vector.dot(vy));
		vector.setZ(vector.dot(vz));
		return vector;
	}

	public static Vector rotY(Vector vector, double angle) {
		double sin = Math.sin(angle * DEG_TO_RAD);
		double cos = Math.cos(angle * DEG_TO_RAD);
		Vector vx = new Vector(cos, 0, sin);
		Vector vz = new Vector(-sin, 0, cos);
		vector.setX(vector.dot(vx));
		vector.setZ(vector.dot(vz));
		return vector;
	}

	public static Vector rotZ(Vector vector, double angle) {
		double sin = Math.sin(angle * DEG_TO_RAD);
		double cos = Math.cos(angle * DEG_TO_RAD);
		Vector vx = new Vector(cos, -sin, 0);
		Vector vy = new Vector(sin, cos, 0);
		vector.setX(vector.dot(vx));
		vector.setY(vector.dot(vy));
		return vector;
	}

	public static Vector rot(Vector vector, Vector axis, double angle) {
		double sin = Math.sin(angle * DEG_TO_RAD);
		double cos = Math.cos(angle * DEG_TO_RAD);
		Vector a = axis.clone().normalize();
		double ax = a.getX();
		double ay = a.getY();
		double az = a.getZ();
		Vector rotx = new Vector(cos+ax*ax*(1-cos), ax*ay*(1-cos)-az*sin, ax*az*(1-cos)+ay*sin);
		Vector roty = new Vector(ay*ax*(1-cos)+az*sin, cos+ay*ay*(1-cos), ay*az*(1-cos)-ax*sin);
		Vector rotz = new Vector(az*ax*(1-cos)-ay*sin, az*ay*(1-cos)+ax*sin, cos+az*az*(1-cos));
		double x = rotx.dot(vector);
		double y = roty.dot(vector);
		double z = rotz.dot(vector);
		vector.setX(x).setY(y).setZ(z);
		return vector;
	}

	public static float notchYaw(float yaw){
		float y = yaw - 90;
		if (y < -180){
			y += 360;
		}
		return y;
	}

	public static float notchPitch(float pitch){
		return -pitch;
	}

	public static float fromNotchYaw(float notchYaw){
		float y = notchYaw + 90;
		if (y > 180){
			y -= 360;
		}
		return y;
	}

	public static float fromNotchPitch(float notchPitch){
		return -notchPitch;
	}

	public static float skriptYaw(float yaw){
		float y = yaw - 90;
		if (y < 0){
			y += 360;
		}
		return y;
	}

	public static float skriptPitch(float pitch){
		return -pitch;
	}

	public static float fromSkriptYaw(float yaw){
		float y = yaw + 90;
		if (y > 360){
			y -= 360;
		}
		return y;
	}

	public static float fromSkriptPitch(float pitch){
		return -pitch;
	}

	public static float wrapAngleDeg(float angle) {
		angle %= 360f;
		if (angle <= -180) {
			return angle + 360;
		} else if (angle > 180) {
			return angle - 360;
		} else {
			return angle;
		}
	}
}