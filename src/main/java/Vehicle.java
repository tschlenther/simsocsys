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


import java.util.List;

/**
 * Created by laemmel on 18/04/16.
 */
public class Vehicle {

	private static final double MAX_SPEED = 1;
	private static final double MAX_OMEGA = Math.PI/2;
	private final Wiring wiring;
	private double vx = 0;
	private double vy = 0;

	public enum Wiring {
		Straight,
		Crossover
	}

	private final static double FRICTION = 0.1;

	private final static double FORCE = 1;


	private final double weight = 1;

	private double length = 0.4;
	private double width = 0.2;

	private double speed = 0;
	private double omega = 0;

	private double x;
	private double y;

	private double phi = 0;//radian!!
	private double sensXLeft;
	private double sensYLeft;
	private double sensXRight;
	private double sensYRight;

	public Vehicle(double x, double y, Wiring wiring) {
		this.x = x;
		this.y = y;
		this.wiring = wiring;
	}



	public void update(List<Vehicle> vehs) {

		double dx = 1 * Math.cos(phi);
		double dy = 1 * Math.sin(phi);

		double xPrime = x + dx* length/2;
		double yPrime = y + dy* length/2;

		this.sensXLeft = xPrime -dy*width/2;
		this.sensYLeft = yPrime +dx*width/2;

		this.sensXRight = xPrime +dy*width/2;
		this.sensYRight = yPrime -dx*width/2;

		double leftSensActivation = .5;
		double rightSensActivation = 1.;



		Vehicle closest = null;
		double minSqrDist = Double.POSITIVE_INFINITY;
		for (Vehicle v : vehs) {
			if(v == this) {
				continue;
			}

			double sqrDist = (this.x-v.getX())*(this.x-v.getX())+
					(this.y-v.getY())*(this.y-v.getY());

			if (sqrDist < minSqrDist) {
				minSqrDist = sqrDist;
				closest = v;
			}
		}
		//TODO calculate left and right sensor activation
		if (closest != null) {

		}

		double forceLeftEngine;
		double forceRightEngine;
		if (this.wiring == Wiring.Crossover) {
			forceRightEngine = leftSensActivation * FORCE;
			forceLeftEngine = rightSensActivation * FORCE;
		} else {
			forceRightEngine = rightSensActivation * FORCE;
			forceLeftEngine = leftSensActivation * FORCE;
		}

		double Fdri = 2 * Math.min(forceLeftEngine,forceRightEngine);
		double a = Fdri/weight - FRICTION;

		double tmpSpeed = this.speed +Simulation.H * a;
		this.speed = Math.max(0,Math.min(tmpSpeed,MAX_SPEED));

		double Frot = forceLeftEngine-forceRightEngine;

		double alpha = Frot/(weight*width/2);

		double tmpOmega = this.omega +Simulation.H * alpha;
		this.omega = Math.max(-MAX_OMEGA,Math.min(tmpOmega,MAX_OMEGA));

		this.phi = this.phi + this.omega * Simulation.H;

		this.vx = this.speed * Math.cos(phi);
		this.vy = this.speed * Math.sin(phi);

	}

	public void move() {
		this.x = this.x + Simulation.H * this.vx;
		this.y = this.y + Simulation.H * this.vy;


	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getPhi() {
		return phi;
	}

	public double getSensXLeft() {
		return sensXLeft;
	}

	public double getSensXRight() {
		return sensXRight;
	}

	public double getSensYLeft() {
		return sensYLeft;
	}

	public double getSensYRight() {
		return sensYRight;
	}

	public double getWidth() {
		return width;
	}

	public double getLength() {
		return length;
	}
}
