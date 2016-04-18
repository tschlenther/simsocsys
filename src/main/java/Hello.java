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

import javax.swing.*;
import java.awt.*;

/**
 * Created by laemmel on 17/04/16.
 */
public class Hello extends PApplet {

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	private int x = 0;
	private int y = 0;

	private double phi = 0;

	public Hello() {
		JFrame fr = new JFrame();
		fr.setSize(WIDTH, HEIGHT);
		JPanel panel = new JPanel();
		panel.setLayout(new OverlayLayout(panel));

		fr.add(panel, BorderLayout.CENTER);

		panel.add(this);
		panel.setEnabled(true);
		panel.setVisible(true);

		this.init();
		frameRate(90);

		fr.setVisible(true);

		size(WIDTH, HEIGHT);
		background(255);
	}

	public static void main(String[] args) {
		new Hello();

	}

	@Override
	public void draw() {
		background(255); // eraser
		stroke(0);
		line(x, y, 200 + x, 200 + y);
		if (x < HEIGHT) {
			x += 1;
			y += 1;
		} else {
			x = 0;
			y = 0;
		}

		int x_c = 500;
		int y_c = 100;
		float dx = 50;
		float dy = 0;
		//TODO vector rotation, see https://en.wikipedia.org/wiki/Rotation_matrix


		if (phi < 2 * Math.PI) { //in radian
			phi += 2 * Math.PI / 360; //one degree rotation
		} else {
			phi = 0;
		}

	}

}
