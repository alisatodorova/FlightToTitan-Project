package com.dke.simulator;

import java.util.ArrayList;
import java.util.List;

public class State {

	public ArrayList<Vector3d> x;
	public ArrayList<Vector3d> v;
	public ArrayList<Double> m;

	public State(List<SpaceObject> spaceObjects) {
		x = new ArrayList<Vector3d>();
		v = new ArrayList<Vector3d>();
		m = new ArrayList<Double>();

		// Initialize state with current values
		for(int i=0; i<spaceObjects.size(); i++) {
			SpaceObject obj = spaceObjects.get(i);
			addX(obj.position(false));
			addV(obj.velocity());
			addM(obj.mass());
		}
	}

	public void addX(Vector3d vec) {
		x.add(new Vector3d(vec.getX(), vec.getY(), vec.getZ()));
	}

	public void addV(Vector3d vec) {
		v.add(new Vector3d(vec.getX(), vec.getY(), vec.getZ()));
	}

	public void addM(double val) {
		m.add(val);
	}
	
	public int getSize() {
		return x.size();
	}
}
