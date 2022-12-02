package com.dke.simulator.Solvers;

import java.util.ArrayList;
import java.util.List;

import com.dke.simulator.SpaceObject;
import com.dke.simulator.State;
import com.dke.simulator.Vector3d;

public class RungeKuttaSolver {
	final static double G = 6.67408e-11;

	public static void step(List<SpaceObject> spaceObjects, double step) {
		ArrayList<Vector3d> xs = new ArrayList<>();
		ArrayList<Vector3d> vs = new ArrayList<>();

		State s = new State(spaceObjects);

		for(int i=0; i<s.getSize(); i++) {
			// Apply Runge Kutta method here
			SpaceObject obj = spaceObjects.get(i);
			Vector3d objPos = obj.position(false);
			Vector3d objVel = obj.velocity();

			Vector3d vk1 = F(0, s, objPos, i);
			Vector3d pk1 = obj.velocity();

			Vector3d vk2 = F(0, s, (pk1.mul(step/2)).add(objPos), i);
			Vector3d pk2 = (vk1.mul(step/2)).add(objVel);

			Vector3d vk3 = F(0, s, (pk2.mul(step/2)).add(objPos), i);
			Vector3d pk3 = (vk2.mul(step/2)).add(objVel);

			Vector3d vk4 = F(0, s, (pk3.mul(step/2)).add(objPos), i);
			Vector3d pk4 = (vk3.mul(step/2)).add(objVel);

			Vector3d v = ((vk1.add((vk2.mul(2)).add((vk3.mul(2)).add(vk4)))).mul(step/6)).add(objVel);
			Vector3d x = ((pk1.add((pk2.mul(2)).add((pk3.mul(2)).add(pk4)))).mul(step/6)).add(objPos);

			vs.add(v);
			xs.add(x);
		}

		// Update the positions and velocities
		for(int i=0; i<spaceObjects.size(); i++) {
			spaceObjects.get(i).setVelocity(vs.get(i));
			spaceObjects.get(i).setPosition(xs.get(i));
		}
	}

	// Get value of f(t, y)
	private static Vector3d F(double t, State y, Vector3d obj1P, int i) {
		Vector3d obj1V = y.v.get(i);

		Vector3d a = new Vector3d(0, 0, 0);
		for(int j=0; j<y.getSize(); j++) {
			if(i == j)
				continue;

			Vector3d obj2P = y.x.get(j);
			Vector3d obj2V = y.v.get(j);
			double obj2M = y.m.get(j);

			Vector3d r = obj2P.sub(obj1P);
			double denominator = (obj2P.dist(obj1P));
			Vector3d currentA = r.mul(G*obj2M / Math.pow(denominator, 3));
			a = a.add(currentA);
		}

		return a;
	}
}
