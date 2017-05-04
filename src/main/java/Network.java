import processing.core.PApplet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// test
/**
 * Created by laemmel on 27.04.17.
 */
public class Network {
    private final Map<Integer,Node> nodes = new HashMap<Integer,Node>();
   	private final Map<Integer,Link> links = new HashMap<Integer,Link>();

   	private final List<LinkInfo> linkInfos = new ArrayList<>();

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
        LinkInfo li = new LinkInfo();
        li.x0 = (float)(from.getX()*Simulation.SCALE);
        li.y0 = (float)(from.getY()*Simulation.SCALE);
        li.x1 = (float)(to.getX()*Simulation.SCALE);
        li.y1 = (float)(to.getY()*Simulation.SCALE);
        linkInfos.add(li);
   		return l;
   	}


	public void draw(PApplet p) {

   	    for (LinkInfo linkInfo : this.linkInfos) {
   	        p.line(linkInfo.x0,linkInfo.y0,linkInfo.x1,linkInfo.y1);
        }

	}

	private static final class LinkInfo {
   	    float x0;
   	    float y0;
   	    float x1;
   	    float y1;
    }
}
