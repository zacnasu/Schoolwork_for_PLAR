public class Rectangle extends Shape {

	private int length; //length units along x axis
	private int width;  //width units along y axis
	//private Point position; // position is the (x,y) coordinates of lower left corner of the rectangle
	
	public Rectangle() {
		this.length = 0;
		this.width = 0;
	}

	public Rectangle(int length, int width) {
		this.length = length;
		this.width = width;
		
	}
	
	public Rectangle(int length, int width, Point position) {
		super(position);
		this.length = length;
		this.width = width;
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
		
		int positionX = super.getPos().getX();
		int positionY = super.getPos().getY();
		
		if ((ptX >= positionX && ptX <= positionX +length) && 
			(ptY >= positionY && ptY <= positionY +width))
			return true;
			
		return false;
	}
    
    public String toString() {
		System.out.println("override");
    return "Rectangle of dimensions: " + length + " by " + width + " at Point: " ;
		
    }
}
