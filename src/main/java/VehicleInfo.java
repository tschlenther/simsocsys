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


import processing.core.PApplet;
import processing.core.PConstants;

/**
 * Created by laemmel on 24/04/16.
 */
public class VehicleInfo {

	private final int x;
	private final int y;

	private final int sensXRight;
	private final int sensXLeft;
	private final int sensYRight;
	private final int sensYLeft;

	private final double phi;

	private final double scale = 100;
	private final int length;
	private final int width;

	public VehicleInfo(double x, double y, double phi, double sensXLeft,
					   double sensXRight, double sensYLeft,
					   double sensYRight,double length, double width) {
		this.x = (int) (scale*x);
		this.y = (int) (scale*y);
		this.phi = phi;
		this.sensXLeft = (int) (scale*sensXLeft);
		this.sensXRight = (int) (scale*sensXRight);
		this.sensYLeft = (int) (scale*sensYLeft);
		this.sensYRight = (int) (scale*sensYRight);
		this.length = (int)(scale*length);
		this.width = (int)(scale*width);
	}

	public void draw(PApplet p) {
		p.pushMatrix();

		p.translate(x,y);

		p.rotate((float)(phi));
		p.fill(128);
		p.rect(-length/2,-width/2,length,width);
		p.ellipseMode(PConstants.CENTER);
		p.fill(255,0,0);
		p.ellipse(length/2,-width/2,10,10);
		p.fill(0,0,255);
		p.ellipse(length/2,width/2,10,10);
		p.popMatrix();


	}
}
