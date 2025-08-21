public class Circle extends Shape {

	private int radius;     //radius
	//private Point position; // position is the (x,y) coordinates of center of the circle
	
	public Circle () {
		
		this.radius = 0;
	}
	
	public Circle (int radius) {
		
		this.radius = radius;
		
	}
	
	public Circle (int radius, Point position) {
		super(position);
		this.radius = radius;
		
	}
	
	public double area(){
		return Math.PI * radius * radius;
	}
	
	public double perimeter(){
		return Math.PI * radius * 2;
	}
	
	public boolean contains(Point pt){
        int ptX = pt.getX();
        int ptY = pt.getY();
        
        int positionX = super.getPos().getX();
        int positionY = super.getPos().getY();
        
        int xDistance = ptX-positionX;
        int yDistance = ptY-positionY;
        
        if (Math.pow(xDistance,2) + Math.pow(yDistance,2) <=
            Math.pow(radius, 2))
            return true;
        
		return false;
	}
    

    public String toString() {
        return "Circle of radius: " + radius + " " +super.toString();
    }

}
