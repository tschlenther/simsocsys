package simulation;/* *********************************************************************** *
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


import cgal.CGAL;
import network.Link;
import network.Node;

import java.util.List;

/**
 * Created by laemmel on 18/04/16.
 */
public class Vehicle {

	private static final double MAX_SPEED = 1;
	private final List<Link> links;
	private int currentLinkIdx = -1;

	private double vx = 0;
	private double vy = 0;





	private double length = 0.4;
	private double width = 0.2;

	private double speed = 0;

	private double x;
	private double y;


	public Vehicle(double x, double y, List<Link> links) {
		this.x = x;
		this.y = y;
		this.links = links;
	}



	public void update(List<Vehicle> vehs) {


		Node target = null;
		if (currentLinkIdx == -1) {
			double dist = getDistTo(this.links.get(0).getFrom());
			if (dist < 0.5) {
				currentLinkIdx = 0;
			} else {
				target = this.links.get(0).getFrom();
			}
		}

		if (this.currentLinkIdx >= 0 && this.currentLinkIdx < this.links.size()) {
			target = this.links.get(currentLinkIdx).getTo();
			//check for finish line crossed;
			double flX = this.getX() - target.getX();
			double flY = this.getY() - target.getY();

			Link currentLink = this.links.get(this.currentLinkIdx);

			double cross = CGAL.cross(flX,flY,currentLink.getFlX(),currentLink.getFlY());
			if (cross > 0) {
				this.currentLinkIdx++;
			}
		}

		double dx = 0;
		double dy = 0;
		if (target != null) {
			double dist = getDistTo(target);
			dx = (target.getX()-this.x)/dist;
			dy = (target.getY()-this.y)/dist;
		}



		double tmpSpeed = 1;
		this.speed = Math.max(0,Math.min(tmpSpeed,MAX_SPEED));



		this.vx = this.speed * dx;
		this.vy = this.speed * dy;

	}

	private double getDistTo(double x, double y, Node n) {
		double dx = x - n.getX();
		double dy = y - n.getY();
		return Math.sqrt(dx*dx+dy*dy);
	}

	private double getDistTo(Node n) {
		return getDistTo(this.x,this.y,n);
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

	public double getWidth() {
		return width;
	}

	public double getLength() {
		return length;
	}
}
