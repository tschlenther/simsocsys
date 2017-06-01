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
 * Created by laemmel on 18/04/16.
 */
public class Vehicle {


    private final List<Link> route;
    private double vx = 0;
    private double vy = 0;
    private double length = 0.4;
    private double width = 0.2;
    private double speed = 1;

    public static final double E = 2.718;
    private double x;
    private double y;
    private double phi = 0;//radian!!
    
    private double v0 = 1.34;
    private double m= 80;
    private double tau= 0.5;
    private double k = 120000;
    private double kappa = 240000;
    private double a= 2000;
    private double b = 0.08;
    private double r = 0.3;
    private double distanceBetweenAgents;
    private double distanceBetweenMidPoints;
    private int g=0;
    private int count=0;
    private int routeIndex = 0;
    List <Vehicle> neighbours = new ArrayList<>();
    
    public Vehicle(double x, double y, List<Link> route) {
        this.x = x;
        this.y = y;
        this.route = route;
        
    }

    public void update(List<Vehicle> vehs) {
    	
        Link currentLink = this.route.get(routeIndex);

        double dx = currentLink.getTo().getX() - this.x;
        double dy = currentLink.getTo().getY() - this.y;

        double dist = Math.sqrt(dx*dx+dy*dy);
        dx /= dist;
        dy /= dist;

        double fx = m * (dx * v0 - this.vx) / tau ;
        double fy = m * (dy * v0 - this.vy) / tau ;
        
        for (Vehicle veh : Simulation.vehs){
        	
        	if (veh != this){
//        	count ++;
        	double nijx = this.x - veh.x;
        	double nijy = this.y - veh.y;
	        distanceBetweenMidPoints = Math.sqrt((nijx * nijx) + (nijy * nijy));
	        nijx /= distanceBetweenMidPoints;
	        nijy /= distanceBetweenMidPoints;
	        
	        distanceBetweenAgents = r * 2 - distanceBetweenMidPoints;
	        if (distanceBetweenMidPoints < r *2){
	        	g=1;
	        }
//	        +k*g*distanceBetweenAgents
	        double fijx=(a*Math.pow(Math.E, distanceBetweenAgents/b))*nijx;
	        double fijy=(a*Math.pow(Math.E, distanceBetweenAgents/b))*nijy;
	        fx += fijx;
	        fy += fijy;
	        
	       
        }
        }
        double ax = fx/m;
        double ay = fy/m;


        this.vx += Simulation.H * ax;
        this.vy += Simulation.H * ay;

        this.phi = Math.atan2(vy,vx);

    }

    public void move() {
        this.x = this.x + Simulation.H * this.vx;
        this.y = this.y + Simulation.H * this.vy;


        Link currentLink = this.route.get(routeIndex);
        if (currentLink.hasVehicleReachedEndOfLink(this)) {
            routeIndex++;//TODO check for end of route!
        }






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

    public double getWidth() {
        return width;
    }

    public void setNeighbours(List<Vehicle> newNeighbours){
    	this.neighbours = newNeighbours;
    }
    
    public List<Vehicle> getNeighbours() {
		return neighbours;
	}    
    public double getLength() {
        return length;
    }


}
