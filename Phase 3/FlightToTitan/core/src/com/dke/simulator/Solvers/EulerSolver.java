package com.dke.simulator.Solvers;

import java.util.ArrayList;
import java.util.List;

import com.dke.simulator.SpaceObject;
import com.dke.simulator.Vector3d;

public class EulerSolver {

	final static double G = 6.67408e-11;

	public static void step(List<SpaceObject> spaceObjects, double step) {
		ArrayList<Vector3d> xs = new ArrayList<>();
		ArrayList<Vector3d> vs = new ArrayList<>();

		for(int i=0; i<spaceObjects.size(); i++) {
			SpaceObject obj1 = spaceObjects.get(i);
			Vector3d a = new Vector3d(0, 0, 0);

			for(int j=0; j<spaceObjects.size(); j++) {
				if(i == j)
					continue;

				SpaceObject obj2 = spaceObjects.get(j);
				Vector3d r = obj2.position(false).sub(obj1.position(false));
				double denominator = (obj2.position(false).dist(obj1.position(false)));
				Vector3d currentA = r.mul(G*obj2.mass() / Math.pow(denominator, 3));
				a = a.add(currentA);
			}

			// Apply euler method here
			Vector3d x = obj1.velocity().mul(step).add(obj1.position(false));
			Vector3d v = a.mul(step).add(obj1.velocity());

			xs.add(x);
			vs.add(v);
		}

		// Update the positions and velocities
		for(int i=0; i<spaceObjects.size(); i++) {
			spaceObjects.get(i).setPosition(xs.get(i));
			spaceObjects.get(i).setVelocity(vs.get(i));
		}
	}
	
}
