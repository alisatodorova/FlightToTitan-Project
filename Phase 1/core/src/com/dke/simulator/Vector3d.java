package com.dke.simulator;

import com.dke.simulator.interfaces.Vector3dInterface;

public class Vector3d implements Vector3dInterface {
	private double x;
	private double y;
	private double z;

	public Vector3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	// Vector opertations
	public Vector3d add(Vector3dInterface other) {
		return new Vector3d(
			x + other.getX(),
			y +	other.getY(),
			z + other.getZ()
		);
	}
	public Vector3d add(double n) {
		return new Vector3d(x + n, y + n, z + n);
	}
	public Vector3d sub(Vector3dInterface other) {
		return new Vector3d(
			x - other.getX(),
			y - other.getY(),
			z - other.getZ()
		);
	}
	public Vector3d sub(double n) {
		return new Vector3d(x - n, y - n, z - n);
	}
	public Vector3d mul(double scalar) {
		return new Vector3d(
			x * scalar,
			y * scalar,
			z * scalar
		);
	}
	public Vector3d addMul(double scalar, Vector3dInterface other) {
		return mul(scalar).add(other);
	}

	// Calculate euclidean distance between two vectors
	public double dist(Vector3dInterface other) {
		return Math.sqrt(
			Math.pow(x - other.getX(), 2) + Math.pow(y - other.getY(), 2) + Math.pow(z - other.getZ(), 2)
		);
	}

	// Calculate euclidean norm (magnitude) of the vector
	public double norm() {
		return dist(new Vector3d(0, 0, 0));
	}

	// Get methods
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getZ() {
		return z;
	}

	// Set methods
	public void setX(double value) {
		x = value;
	}
	public void setY(double value) {
		y = value;
	}
	public void setZ(double value) {
		z = value;
	}

	// Display the vector in a familiar way
	@Override
	public String toString() {
		return "[" + x + ", " + y + ", " + z + "].T";
	}
}