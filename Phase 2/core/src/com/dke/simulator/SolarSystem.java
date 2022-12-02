package com.dke.simulator;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SolarSystem {
	public static ArrayList<SpaceObject> spaceObjects;
	public static double dt = 4000;

	public boolean isPaused = false;

	public SolarSystem() {
		spaceObjects = new ArrayList<SpaceObject>();
	}

	public void add(SpaceObject obj) {
		spaceObjects.add(obj);
	}

	public void addProbe(SpaceObject host, double mass, double radius, Vector3d position, Vector3d velocity) {
		SpaceObject probe = new SpaceObject("Probe", 0.5, "FFFFFF", mass, radius,
			host.position(false).add(position),
			host.velocity().add(velocity)
		);
		add(probe);
	}

	// WIP
	public static final double G = 6.67408e-11;
//	public void update() {
//		for(int i=0; i<spaceObjects.size(); i++) {
//			SpaceObject obj1 = spaceObjects.get(i);
//			Vector3d a = new Vector3d(0, 0, 0);
//
//			for(int j=0; j<spaceObjects.size(); j++) {
//				if(i == j)
//					continue;
//
//				SpaceObject obj2 = spaceObjects.get(j);
//				Vector3d r = obj2.position(false).sub(obj1.position(false));
//				Vector3d currentA = r.mul(G*obj2.mass() / Math.pow(obj2.position(false).dist(obj1.position(false)), 3));
//				a = a.add(currentA);
//			}
//
//			Vector3d x = obj1.velocity().mul(dt).add(obj1.position(false));
//			Vector3d v = a.mul(dt).add(obj1.velocity());
//
//			obj1.setPosition(x);
//			obj1.setVelocity(v);
//		}
//	}

	public void pause(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public void render(SpriteBatch batch) {
		for(int i=0; i<spaceObjects.size(); i++) {
			spaceObjects.get(i).render(batch);
		}

		if(!isPaused)
			EulerSolv.update();
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
