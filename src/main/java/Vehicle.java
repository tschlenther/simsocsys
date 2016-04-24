/* *********************************************************************** *
 * project: org.matsim.*
 *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2014 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
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

import java.util.List;

/**
 * Created by laemmel on 18/04/16.
 */
public class Vehicle {
	private int x;
	private int y;

	private double phi = 0;//radian!!

	public Vehicle(int x, int y) {
		this.x = x;
		this.y = y;
	}



	public void update(List<Vehicle> vehs) {
		for (Vehicle v : vehs) {
			if(v == this) {
				continue;
			}

			//TODO right sensor activation

			//TODO left sensor activation



		}

		phi += 0.1;
	}

	public void move() {

		//TODO euler integration

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getPhi() {
		return phi;
	}
}
