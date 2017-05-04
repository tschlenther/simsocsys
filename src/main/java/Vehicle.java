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


    private final List<Link> route;
    
    private Vector vector_vi;
    private Vector vector_eo;
    private Vector vector_r;
//    private double vx = 0;
//    private double vy = 0;
    private final double mass = 80;
    private double length = 0.4;
    private double width = 0.2;
    private double vO = 1.34;
    private double tau_i = 0.5;
    
    private double phi = 0;//radian!!

    private int routeIndex = 0;

	private Vector vector_ai;

	private Vector vector_fi;

    public Vehicle(double x, double y, List<Link> route) {
        this.vector_r = new Vector(x, y);
        this.route = route;
        this.vector_vi = new Vector(0,0);
        this.vector_fi = new Vector(0,0);
        this.vector_ai = new Vector(0,0);
    }

    public void update(List<Vehicle> vehs) {

        Link currentLink = this.route.get(routeIndex);

        
        double dx = currentLink.getTo().getX() - this.vector_r.getX();
        double dy = currentLink.getTo().getY() - this.vector_r.getY();

        double dist = Math.sqrt(dx*dx+dy*dy);
        dx /= dist;
        dy /= dist;
   
        vector_fi.setX(( dx*this.vO-this.vector_vi.getX() ) * (this.mass / this.tau_i) );
        vector_fi.setY(( dy*this.vO-this.vector_vi.getY() )  * (this.mass / this.tau_i));
//        this.vx = dx * this.speed;
//        this.vy = dy * this.speed;

        vector_ai = vector_fi.multiplicationWithScalar(Simulation.H/this.mass);
        vector_vi.addVector(vector_ai);
        
        this.phi = Math.atan2(this.vector_vi.getY(),this.vector_vi.getX());

    }

    public void move() {
    	Vector v = this.vector_vi.multiplicationWithScalar(Simulation.H);
    	this.vector_r.addVector(v);
    	
//    	this.x = this.x + Simulation.H * this.vx;
//        this.y = this.y + Simulation.H * this.vy;

        Link currentLink = this.route.get(routeIndex);
        if (currentLink.hasVehicleReachedEndOfLink(this)) {
        	if(routeIndex == this.route.size() -1){
        		this.vO = 0;
        	}
        	else{
        		routeIndex++;	
        	}
        }

    }

    public double getX() {
        return this.vector_r.getX();
    }

    public double getY() {
        return this.vector_r.getY();
    }

    public double getPhi() {
        return phi;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }


}
