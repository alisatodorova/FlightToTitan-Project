package com.dke.simulator;

public class VerletSolv extends SolarSystem {


    public static void update(){

        for(int i=0; i<spaceObjects.size(); i++) {
            SpaceObject obj1 = spaceObjects.get(i);
            //Vector3d acceleration = new Vector3d(0, 0, 0);
            Vector3d oldAcc = obj1.acceleration;

            for(int j=0; j<spaceObjects.size(); j++) {
                if(i == j)
                    continue;

                SpaceObject obj2 = spaceObjects.get(j);
                Vector3d r = obj2.position(false).sub(obj1.position(false));
                Vector3d currentA = r.mul(G*obj2.mass() / Math.pow(obj2.position(false).dist(obj1.position(false)), 3));
                obj1.acceleration = obj1.acceleration.add(currentA.mul(1/obj1.mass));
            }

            Vector3d pos = obj1.position.addVerlet(obj1.velocity.mul(dt), obj1.acceleration.mul(dt*dt/2));//obj1.velocity().mul(dt).add(obj1.position(false));
            Vector3d a = oldAcc.add(obj1.acceleration);
            a = a.mul(dt*1/2);
            Vector3d v = obj1.velocity.add(a);

            obj1.setPosition(pos);
            obj1.setAcceleration(a);
            obj1.setVelocity(v);
        }

    }
}
