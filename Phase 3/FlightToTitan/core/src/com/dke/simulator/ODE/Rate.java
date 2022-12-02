package com.dke.simulator.ODE;

import com.dke.simulator.Vector3d;
import com.dke.simulator.interfaces.RateInterface;
import com.dke.simulator.interfaces.Vector3dInterface;

public class Rate implements RateInterface {
	private Vector3dInterface velocityChanges = new Vector3d(0, 0, 0);
	private Vector3dInterface positionChanges = new Vector3d(0, 0, 0);

	public void setVChanges(Vector3dInterface x) { velocityChanges.add(x); }
	public void setPChanges(Vector3dInterface x) { positionChanges.add(x); }

	public Vector3dInterface getVChanges() { return velocityChanges; }
	public Vector3dInterface getPChanges() { return positionChanges; }
}
