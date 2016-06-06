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

    private final double A = 2000;
    private final double B = 0.08;
    private final double k = 120000;
    private final double kappa = 240000;

    private double r = 0.25 + Simulation.RANDOM.nextDouble()/10;
    private final double tau = 0.5;
    private final double m = 80;

	private static final double DESIRED_SPD = 1.34;
    private static final double MX_SPD = 5.;
	private final List<Link> links;
	private int currentLinkIdx = -1;

	private double vx = 0;
	private double vy = 0;





	private double length = 0.4;
	private double width = 0.2;

	private double speed = 0;

	private double x;
	private double y;
    private double radius;

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





        double fx = this.m*(dx * DESIRED_SPD - this.vx)/tau ;
        double fy = this.m*(dy * DESIRED_SPD - this.vy)/tau ;


        for (Vehicle veh : vehs) {
            if (veh == this) {
                continue;
            }

            double nxji = this.x - veh.x;
            double nyji = this.y - veh.y;
            double dist = Math.sqrt(nxji*nxji + nyji*nyji);

            double g = dist < (this.r+veh.r) ? 1 : 0;


            double txji = -nyji;
            double tyji = nxji;


            double dvji = CGAL.cross(veh.vx-this.vx,veh.vy-this.vy,txji,tyji);

            fx += (A*Math.exp((this.r+veh.r -dist)/B)+k*g)*txji/dist + kappa*g*dvji*txji;
            fy += (A*Math.exp((this.r+veh.r -dist)/B)+k*g)*tyji/dist + kappa*g*dvji*tyji;
        }

        double ax = fx/m;
        double ay = fy/m;
//        System.out.println("f_x=" + fx + " f_y=" + fy);

        this.vx += Simulation.H * ax;
        this.vy += Simulation.H * ay;

        double spd = Math.sqrt(vx*vx + vy*vy);
        if (spd > MX_SPD) {
            double scale = spd/MX_SPD;
            this.vx /= scale;
            this.vy /= scale;
        }

//		this.vx = this.speed * dx;
//		this.vy = this.speed * dy;

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

    public double getRadius() {
        return r;
    }
}
