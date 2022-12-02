package com.dke.simulator.ODE;

import java.util.ArrayList;

import com.dke.simulator.SpaceObject;
import com.dke.simulator.Vector3d;
import com.dke.simulator.interfaces.ODEFunctionInterface;
import com.dke.simulator.interfaces.RateInterface;
import com.dke.simulator.interfaces.StateInterface;

public class VelocityFunction implements ODEFunctionInterface {

	private static final double G = 6.67408e-11;
	private ArrayList<SpaceObject> spaceObjects;

	// Constructor
	public VelocityFunction(ArrayList<SpaceObject> spaceObjects) {
		this.spaceObjects = spaceObjects;
	}

	@Override
	public RateInterface call(double t, StateInterface y) {
		
		for(int i=0; i<spaceObjects.size(); i++) {
			SpaceObject obj1 = spaceObjects.get(i);
			Vector3d a = new Vector3d(0, 0, 0);

			for(int j=0; j<spaceObjects.size(); j++) {
				if(i == j)
					continue;

				SpaceObject obj2 = spaceObjects.get(j);
				Vector3d r = obj2.position(false).sub(obj1.position(false));
				Vector3d currentA = r.mul(G*obj2.mass() / Math.pow(obj2.position(false).dist(obj1.position(false)), 3));
				a = a.add(currentA);
			}
		}

		return null;
	}
	
}
