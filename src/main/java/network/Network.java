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


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by laemmel on 02/05/16.
 */
public class Network {


	private final Map<Integer,Node> nodes = new HashMap<Integer,Node>();
	private final Map<Integer,Link> links = new HashMap<Integer,Link>();

	public Node createNode(double x, double y, int id) {
		Node n = new Node(x,y,id);
		this.nodes.put(id,n);
		return n;
	}

	public Link createLink(Node from, Node to, int id) {
		Link l = new Link(from,to,id);
		this.links.put(id,l);
		from.addOutLink(l);
		to.addInLink(l);
		return l;
	}

	public Collection<Link> getLinks() {
		return links.values();
	}

	public Collection<Node> getNodes() {
		return nodes.values();
	}
}


