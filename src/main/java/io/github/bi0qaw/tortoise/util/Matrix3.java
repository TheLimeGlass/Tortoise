package io.github.bi0qaw.tortoise.util;

import org.bukkit.util.Vector;

public class Matrix3 {

	private double[] m = new double[9];

	public Matrix3(double[] d) {
		for (int i = 0; i < 9; i++) {
			m[i] = d[i];
		}
	}

	public Matrix3(Vector u, Vector v, Vector w){
		m[0] = u.getX();
		m[1] = u.getY();
		m[2] = u.getZ();
		m[3] = v.getX();
		m[4] = v.getY();
		m[5] = v.getZ();
		m[6] = w.getX();
		m[7] = w.getY();
		m[8] = w.getZ();
	}

	public double det(){
		return m[0]*m[4]*m[8] + m[1]*m[5]*m[6] + m[2]*m[3]*m[7]
				- m[2]*m[4]*m[6] - m[0]*m[5]*m[7] - m[1]*m[3]*m[8];
	}


	public void invert() {
		Double det = det();
		if (det.equals(0)) return;
		double invdet = 1 / det;
		double[] d = new double[9];
		d[0] = (m[4]*m[8]-m[5]*m[7]) * invdet;
		d[1] = (m[2]*m[7]-m[8]*m[1]) * invdet;
		d[2] = (m[1]*m[5]-m[2]*m[4]) * invdet;
		d[3] = (m[5]*m[6]-m[3]*m[8]) * invdet;
		d[4] = (m[0]*m[8]-m[2]*m[6]) * invdet;
		d[5] = (m[2]*m[3]-m[0]*m[5]) * invdet;
		d[6] = (m[3]*m[7]-m[4]*m[6]) * invdet;
		d[7] = (m[1]*m[6]-m[0]*m[7]) * invdet;
		d[8] = (m[0]*m[4]-m[1]*m[3]) * invdet;
		for (int i = 0; i < 9; i++){
			m[i] = d[i];
		}
	}

	public Vector mul(Vector v){
		double x = v.getX(), y = v.getY(), z = v.getZ();
		return new Vector(m[0]*x + m[1]*y + m[2]*z, m[3]*x + m[4]*y + m[5]*z, m[6]*x + m[7]*y + m[8]*z);
	}

}
