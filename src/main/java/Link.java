/**
 * Created by laemmel on 27.04.17.
 */
public class Link {


    private final double length;
    private final Node from;
    private final Node to;
    private final int id;
    private final double weight;


    private double finishLineX1;
    private double finishLineY1;

    public Link(Node from, Node to, int id) {
        this.from = from;
        this.to = to;
        this.id = id;

        double dx = from.getX() - to.getX();
        ;
        double dy = from.getY() - to.getY();
        ;
        this.weight = Math.sqrt(dx * dx + dy * dy);
        this.length = Math.sqrt(dx * dx + dy * dy);

        this.finishLineX1 = -dy;
        this.finishLineY1 = dx;

    }

    public Node getTo() {
        return to;
    }

    public boolean hasVehicleReachedEndOfLink(Vehicle vehicle) {

        double vx = vehicle.getX() - this.to.getX();
        double vy = vehicle.getY() - this.to.getY();

        double cross = this.finishLineX1 * vy - this.finishLineY1 * vx;
        if (cross > 0) {
            return true;
        }

        return false;
    }

    public double getLength() {
        return length;
    }
}
