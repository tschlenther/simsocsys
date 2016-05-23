package network;
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


import java.util.LinkedList;
import java.util.List;

/**
 * Created by laemmel on 02/05/16.
 */
public class Node {
	private final double x;
	private final double y;
	private final int id;

	private final List<Link> outLinks = new LinkedList<Link>();
	private final List<Link> inLinks = new LinkedList<Link>();

	public Node(double x, double y, int id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public void addOutLink(Link l) {
		this.outLinks.add(l);
	}

	public void addInLink(Link l) {
		this.inLinks.add(l);
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}
}
