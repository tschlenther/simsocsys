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

	public static final double H = 0.1;

	private final Vis vis;
	private List<Vehicle> vehs = new ArrayList<>();

	public Simulation() {
		this.vis = new Vis();
	}

	public static void main (String [] args) {
		Simulation sim = new Simulation();
		Vehicle v1 = new Vehicle(2,3, Vehicle.Wiring.Crossover);
		sim.add(v1);
		Vehicle v2 = new Vehicle(5,2, Vehicle.Wiring.Crossover);
		sim.add(v2);
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

			List<VehicleInfo> vInfos = new ArrayList<>();
			for (Vehicle v : this.vehs) {
				VehicleInfo vi = new VehicleInfo(v.getX(),v.getY(),v.getPhi(),
						v.getSensXLeft(),v.getSensXRight(),v.getSensYLeft(),
						v.getSensYRight(),v.getLength(),v.getWidth());
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
