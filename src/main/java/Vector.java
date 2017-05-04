
public class Vector {
	
	private double x;
	private double y;
	
	public Vector(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
	
	public double getLength(){
		return Math.sqrt(this.x*this.x+this.y*this.y);
	}
	
	public Vector multiplicationWithScalar(double multiplicator){
		Vector v = new Vector(this.x,this.y);
		v.x *= multiplicator;
		v.y *= multiplicator;
		return v;
	}
	
	public double crossProduct(Vector otherVector){
		return (this.getX()*otherVector.getY()-otherVector.getX()*this.getY());
	}
	
	public void addVector(Vector otherVector){
		this.x += otherVector.getX();
		this.y += otherVector.getY();
	}
}
