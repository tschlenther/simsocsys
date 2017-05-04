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
    private double vx = 0;
    private double vy = 0;
    private double length = 0.4;
    private double width = 0.2;
    private double speed = 1;
    
    private double x;
    private double y;
    private double phi = 0;//radian!!


    private int routeIndex = 0;

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


        this.vx = dx * this.speed;
        this.vy = dy * this.speed;


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

    public double getLength() {
        return length;
    }


}
