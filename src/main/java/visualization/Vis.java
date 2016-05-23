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


import network.Link;
import network.Network;
import network.Node;
import processing.core.PApplet;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by laemmel on 17/04/16.
 */
public class Vis extends PApplet {


	public static final double SCALE = 100;

	private List<VehicleInfo> vehs = new ArrayList<>();

	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;

	private int x = 0;
	private int y = 0;

	private double phi = 0;
	private Network network = new Network();

	public Vis() {
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

	@Override
	public void draw() {
		background(255); // eraser

		synchronized (this.network) {
			for (Link link : this.network.getLinks()) {

				line((float)(SCALE*link.getFrom().getX()),(float)(SCALE*link.getFrom().getY()), (float)(SCALE*link.getTo().getX()),(float)(SCALE*link.getTo().getY()));

			}

			for (Node node : this.network.getNodes()) {
				ellipseMode(CENTER);
				fill(0,128,128,128);
				ellipse((float)(SCALE*node.getX()),(float)(SCALE*node.getY()),10,10);
			}
		}

		synchronized (this.vehs) {
			for (VehicleInfo v : this.vehs) {
				v.draw(this);
			}
		}
	}

	public void update(double time, List<VehicleInfo> vehs) {
		synchronized (this.vehs) {
			this.vehs = new ArrayList<VehicleInfo>(vehs);
		}

	}

	public void setNetwork(Network net) {
		synchronized (this.network) {
			this.network = net;
		}
	}
}
