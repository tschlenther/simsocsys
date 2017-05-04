/* *********************************************************************** *
 * project: simsocsys
 *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2016 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : gregor dot laemmel at gmail dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */


import java.util.ArrayList;
import java.util.List;

/**
 * Created by laemmel on 24/04/16.
 */
public class Simulation {

    public static final double SCALE = 80;

    public static final double H = 0.1;

    private final Vis vis;
    private List<Vehicle> vehs = new ArrayList<>();

    public Simulation(Network net) {
        this.vis = new Vis(net);
    }

    public static void main(String[] args) {

        Network net = new Network();
        Node n0 = net.createNode(1,1,1);
        Node n1 = net.createNode(7,1,2);
        Node n2 = net.createNode(7,3,3);
        Node n3 = net.createNode(1,3,4);
        Node n4 = net.createNode(1,5,5);
        Node n5 = net.createNode(7,5,6);
        Link l0 = net.createLink(n0,n1,1);
        Link l1 = net.createLink(n1,n2,2);
        Link l2 = net.createLink(n2,n3,3);
        Link l3 = net.createLink(n3,n4,4);
        Link l4 = net.createLink(n4,n5,4);
        List<Link> route = new ArrayList<>();
        route.add(l0);
        route.add(l1);
        route.add(l2);
        route.add(l3);
        route.add(l4);

        Simulation sim = new Simulation(net);
        Vehicle v1 = new Vehicle(1, 1, route);
        sim.add(v1);
        sim.run();

    }

    private void run() {
        double time = 0;

        double maxTime = 1000;
        while (time < maxTime) {
            for (Vehicle v : this.vehs) {
                v.update(this.vehs);
            }
            for (Vehicle v : this.vehs) {
                v.move();
            }
            //
            List<VehicleInfo> vInfos = new ArrayList<>();
            for (Vehicle v : this.vehs) {
                VehicleInfo vi = new VehicleInfo(v.getX(), v.getY(), v.getPhi(), v.getLength(), v.getWidth());
                vInfos.add(vi);
            }
            this.vis.update(time, vInfos);

            time += H;

            try {
                Thread.sleep((long) (H * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void add(Vehicle v1) {
        this.vehs.add(v1);
    }
}
