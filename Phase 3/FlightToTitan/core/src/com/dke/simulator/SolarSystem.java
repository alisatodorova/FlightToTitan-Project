package com.dke.simulator;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.dke.simulator.Solvers.EulerSolver;
import com.dke.simulator.Solvers.RungeKuttaSolver;
import com.dke.simulator.Solvers.VerletSolver;

public class SolarSystem {
	private ArrayList<SpaceObject> spaceObjects;
	private double dt = 5000;

	private boolean isPaused = false;
	private double currentStep = 0;

	public SolarSystem() {
		spaceObjects = new ArrayList<SpaceObject>();
	}

	public void add(SpaceObject obj) {
		spaceObjects.add(obj);
	}

	public void addProbe(SpaceObject host, double mass, double radius, Vector3d position, Vector3d velocity) {
		ProbeObject probe = new ProbeObject("Probe", 0.5, "FFFFFF", mass, radius,
			host.position(false).add(position),
			host.velocity().add(velocity)
		);
		System.out.println("POSITION: " + host.position(false).add(position));
		System.out.println("VELOCITY: " + host.velocity().add(velocity));
		add(probe);
	}

	public void update() {
		//EulerSolver.step(spaceObjects, dt);
		//RungeKuttaSolver.step(spaceObjects, dt);
		VerletSolver.step(spaceObjects, dt);
	}

	public void pause(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public void render(SpriteBatch batch) {
		for(int i=0; i<spaceObjects.size(); i++) {
			spaceObjects.get(i).render(batch);
		}

		if(!isPaused) {
			update();
			currentStep += dt;
			SpaceObject r = spaceObjects.get(4);
			Vector3d rPos = r.position(false);
			Vector3d rVel = r.velocity();
			System.out.println(currentStep + " POSITION: " + rPos);
			System.out.println(currentStep + " VELOCITY: " + rVel);
		}
	}

	public SpaceObject getClicked(double x, double y) {
		for(int i=0; i<spaceObjects.size(); i++) {
			x = Math.abs(x);
			y = Math.abs(y);

			SpaceObject obj = spaceObjects.get(i);
			Vector3d pos = obj.position(true);
			double xObj = Math.abs(pos.getX());
			double yObj = Math.abs(pos.getY());

			//System.out.println(obj.name() + ": " + x + "/" + xObj + ", " + y + "/" + yObj);

			if(Math.abs(x-xObj) < SpaceObject.SHAPE_SIZE && Math.abs(y-yObj) < obj.size())
				return obj;
		}

		return null;
	}
}
