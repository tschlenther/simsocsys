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

/**
 * Created by laemmel on 24/04/16.
 */
public class VehicleInfo {

	private final int x;
	private final int y;
	private final double phi;

	public VehicleInfo(int x, int y, double phi) {
		this.x = x;
		this.y = y;
		this.phi = phi;
	}

	public void draw(PApplet p) {
		p.pushMatrix();

		p.translate(x,y);
		p.rotate((float)(phi));
		p.rect(-100,-50,200,100);

		p.popMatrix();
	}
}
