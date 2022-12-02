package com.dke.simulator;

public class EulerSolv extends  SolarSystem {


    public static void update() {

        for(int i=0; i<spaceObjects.size(); i++) {
            SpaceObject obj1 = spaceObjects.get(i);
            Vector3d acceleration = new Vector3d(0, 0, 0);

            for(int j=0; j<spaceObjects.size(); j++) {
                if(i == j)
                    continue;

                SpaceObject obj2 = spaceObjects.get(j);
                Vector3d r = obj2.position(false).sub(obj1.position(false));
                Vector3d currentA = r.mul(G*obj2.mass() / Math.pow(obj2.position(false).dist(obj1.position(false)), 3));
                acceleration = acceleration.add(currentA);
            }

            Vector3d x = obj1.velocity().mul(dt).add(obj1.position(false));
            Vector3d v = acceleration.mul(dt).add(obj1.velocity());

            obj1.setPosition(x);
            obj1.setVelocity(v);
        }
    }
}
