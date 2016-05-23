package simulation;
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


import network.Link;
import network.Network;
import network.Node;
import visualization.VehicleInfo;
import visualization.Vis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laemmel on 24/04/16.
 */
public class Simulation {

	public static final double H = 0.1;

	private final Vis vis;
	private List<Vehicle> vehs = new ArrayList<>();

	public Simulation() {
		this.vis = new Vis();
	}

	public static void main (String [] args) {



		Network net = new Network();

		Node n0 = net.createNode(0.5,0.5,0);
		Node n1 = net.createNode(7.5,0.5,1);
		Node n2 = net.createNode(7.5,3,2);
		Node n3 = net.createNode(0.5,5.5,3);
		Node n4 = net.createNode(7.5,5.5,4);

		Link l0 = net.createLink(n0,n1,0);
		Link l1 = net.createLink(n1,n2,1);
		Link l3 = net.createLink(n2,n4,2);
		Link l4 = net.createLink(n2,n3,3);
		Link l5 = net.createLink(n3,n4,4);

		List<Link> route1 = new ArrayList<>();
		route1.add(l0);
		route1.add(l1);
		route1.add(l3);
		List<Link> route2 = new ArrayList<>();
		route2.add(l0);
		route2.add(l1);
		route2.add(l4);
		route2.add(l5);

		Simulation sim = new Simulation();
		sim.setNetwork(net);


		Vehicle v1 = new Vehicle(2,3, route1);
		sim.add(v1);
		Vehicle v2 = new Vehicle(5,2, route2);
		sim.add(v2);
		sim.run();

	}

	private void setNetwork(Network net) {
		this.vis.setNetwork(net);
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

			List<VehicleInfo> vInfos = new ArrayList<>();
			for (Vehicle v : this.vehs) {
				VehicleInfo vi = new VehicleInfo(v.getX(),v.getY(),v.getLength(),v.getWidth());
				vInfos.add(vi);
			}
			this.vis.update(time,vInfos);

			time += H;

			try {
				Thread.sleep((long) (H*1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void add(Vehicle v1) {
		this.vehs.add(v1);
	}
}
