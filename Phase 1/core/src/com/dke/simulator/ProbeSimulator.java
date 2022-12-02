package com.dke.simulator;

import com.dke.simulator.interfaces.ProbeSimulatorInterface;
import com.dke.simulator.interfaces.Vector3dInterface;

import static java.lang.Math.round;

public class ProbeSimulator implements ProbeSimulatorInterface {

    @Override
    public Vector3dInterface[] trajectory(Vector3dInterface p0, Vector3dInterface v0, double[] ts) {

        Vector3dInterface[] trajectory = new Vector3dInterface[ts.length];

        for(int i =0; i< ts.length; i++){

            trajectory[i].setX(p0.getX()+(v0.getX()*ts[i]));
            trajectory[i].setY(p0.getY()+(v0.getY()*ts[i]));
            trajectory[i].setZ(p0.getZ()+(v0.getZ()*ts[i]));

        }
        return trajectory;
    }

    @Override
    public Vector3dInterface[] trajectory(Vector3dInterface p0, Vector3dInterface v0, double tf, double h) {

        Vector3dInterface[] trajectory = new Vector3dInterface[(int) (round(tf/h)+1)];

        double[] times = new double[trajectory.length];

        times[0] = 0;

        for (int i =1 ; i<trajectory.length -1 ; i++ ){

            times[i] = h * i;
        }

        times[times.length-1] = tf;

        for(int i =0; i< times.length; i++){

            trajectory[i].setX(p0.getX()+(v0.getX()*times[i]));
            trajectory[i].setY(p0.getY()+(v0.getY()*times[i]));
            trajectory[i].setZ(p0.getZ()+(v0.getZ()*times[i]));

        }

        return trajectory;
    }
}
>>>>>>> 914887538969c0a973224c2c75e557a1cc1be77f
