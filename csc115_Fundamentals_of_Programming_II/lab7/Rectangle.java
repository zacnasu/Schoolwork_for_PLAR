public class Rectangle implements Shape {

	private int length; //length units along x axis
	private int width;  //width units along y axis
	private Point position; // position is the (x,y) coordinates of lower left corner of the rectangle
	
	public Rectangle() {
		this.length = 0;
		this.width = 0;
		this.position = new Point();
	}

	public Rectangle(int length, int width) {
		this.length = length;
		this.width = width;
		this.position = new Point();
	}
	
	public Rectangle(int length, int width, Point position) {
		this.length = length;
		this.width = width;
		this.position = position;
	}

	public double area () {
		return length * width;
	}
	
	public double perimeter () {
		return 2 * (length + width);
	}
	
	public boolean contains(Point pt){
		int ptX = pt.getX();
		int ptY = pt.getY();
		
		int positionX = position.getX();
		int positionY = position.getY();
		
		if ((ptX >= positionX && ptX <= positionX +length) && 
			(ptY >= positionY && ptY <= positionY +width))
			return true;
			
		return false;
	}
    
    public String toString() {
        return "Rectangle of dimensions: " + length + " by " + width + " at Point: " + position;
    }
}
