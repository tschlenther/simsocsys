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


/**
 * Created by laemmel on 02/05/16.
 */
public class Link {

	private final Node from;
	private final Node to;
	private final int id;
	private final double weight;

	public Link(Node from, Node to, int id) {
		this.from = from;
		this.to = to;
		this.id = id;

		double dx = from.getX() - to.getX();;
		double dy = from.getY() - to.getY();;
		this.weight = Math.sqrt(dx*dx+dy*dy);
	}

	public Node getFrom() {
		return from;
	}

	public Node getTo() {
		return to;
	}

	public  double getFlX() {
		return - (this.to.getY()-this.from.getY());
	}
	public  double getFlY() {
		return (this.to.getX()-this.from.getX());
	}
}
