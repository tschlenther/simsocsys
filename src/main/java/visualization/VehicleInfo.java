package visualization;/* *********************************************************************** *
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


import processing.core.PApplet;
import processing.core.PConstants;

/**
 * Created by laemmel on 24/04/16.
 */
public class VehicleInfo {

	private final int x;
	private final int y;


	private final int radius;

	public VehicleInfo(double x, double y,double radius) {
		this.x = (int) (Vis.SCALE*x);
		this.y = (int) (Vis.SCALE*y);

		this.radius = (int)(Vis.SCALE*radius);
	}

	public void draw(PApplet p) {
		p.pushMatrix();

		p.translate(x,y);
		p.ellipseMode(PConstants.CENTER);
		p.fill(255,0,0);
		p.ellipse(0,0,radius,radius);
		p.popMatrix();


	}
}
