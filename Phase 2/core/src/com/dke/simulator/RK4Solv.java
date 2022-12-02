package com.dke.simulator;

public class RK4Solv extends  SolarSystem {

    public static void update() {

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

            double kx0, kx1, kx2, kx3, ky0,ky1,ky2,ky3, kz0, kz1, kz2, kz3, lx0,lx1,lx2,lx3,ly0,ly1,ly2,ly3,lz0, lz1, lz2, lz3;
            SpaceObject ob1 = obj1, ob2 = obj1, ob3 = obj1;

            kx0 = dt * obj1.velocity().getX();
            ky0 = dt * obj1.velocity().getY();
            kz0 = dt * obj1.velocity().getZ();



            lx0 = dt * obj1.acceleration().getX();
            ly0 = dt * obj1.acceleration().getY();
            lz0 = dt * obj1.acceleration().getZ();

            kx1 = dt * (obj1.velocity().getX() + 0.5 * lx0);
            ky1 = dt * (obj1.velocity().getY() + 0.5 * ly0);
            kz1 = dt * (obj1.velocity().getZ() + 0.5 * lz0);

            ob1.position.add(new Vector3d((float) (0.5 * kx0),
                    (float) (0.5 * ky0),
                    (float) (0.5 * kz0)));

            ob1.velocity.add(new Vector3d(
                    (float) (0.5 * lx0),
                    (float) (0.5 * ly0),
                    (float) (0.5 * lz0)));


            lx1 = dt * obj1.acceleration().getX();
            ly1 = dt * obj1.acceleration().getY();
            lz1 = dt * obj1.acceleration().getZ();

            kx2 = dt * (obj1.velocity().getX() + 0.5 * lx1);
            ky2 = dt * (obj1.velocity().getY() + 0.5 * ly1);
            kz2 = dt * (obj1.velocity().getZ() + 0.5 * lz1);

            ob2.position.add(new Vector3d(
                    (float) (0.5 * kx1),
                    (float) (0.5 * ky1),
                    (float) (0.5 * kz1)));

            ob2.velocity.add(new Vector3d(
                    (float) (0.5 * lx1),
                    (float) (0.5 * ly1),
                    (float) (0.5 * lz1)));



            lx2 = dt * obj1.acceleration().getX();
            ly2 = dt * obj1.acceleration().getY();
            lz2 = dt * obj1.acceleration().getZ();

            kx3 = dt * (obj1.velocity().getX() + lx2);
            ky3 = dt * (obj1.velocity().getY() + ly2);
            kz3 = dt * (obj1.velocity().getZ() + lz2);

            ob3.position.add(new Vector3d(
                    (float) (kx2),
                    (float) (ky2),
                    (float) (kz2)));

            ob3.velocity.add(new Vector3d(
                    (float) (lx2),
                    (float) (ly2),
                    (float) (lz2)));


            lx3 = dt * obj1.acceleration().getX();
            ly3 = dt * obj1.acceleration().getY();
            lz3 = dt * obj1.acceleration().getZ();


            Vector3d x = new Vector3d(
                    (float) ((1.0 / 6.0) * (kx0 + 2 * kx1 + 2 * kx2 + kx3)),
                    (float) ((1.0 / 6.0) * (ky0 + 2 * ky1 + 2 * ky2 + ky3)),
                    (float) ((1.0 / 6.0) * (kz0 + 2 * kz1 + 2 * kz2 + kz3)));

            Vector3d a = oldAcc.add(obj1.acceleration);
            a = a.mul(dt);

            Vector3d v = new Vector3d(
                    (float) ((1.0 / 6.0) * (lx0 + 2 * lx1 + 2 * lx2 + lx3)),
                    (float) ((1.0 / 6.0) * (ly0 + 2 * ly1 + 2 * ly2 + ly3)),
                    (float) ((1.0 / 6.0) * (lz0 + 2 * lz1 + 2 * lz2 + lz3)));

            obj1.setPosition(x);
            obj1.setAcceleration(a);
            obj1.setVelocity(v);

        }
    }
}
