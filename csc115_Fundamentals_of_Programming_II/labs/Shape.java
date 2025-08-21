public abstract class Shape{
	private Point position;
	
	public Shape(){
		position = new Point();
	}
	public Shape(Point y){
		position = y;
	}
		
	public Point getPos(){
		return position;
	}

    /*
     * Purpose: calculates the area of this Shape
     * Parameters: none
     * Returns: double - the area
     */
	public abstract double area() ; 
     
    /*
     * Purpose: calculates the perimeter of this Shape
     * Parameters: none
     * Returns: double - the perimeter
     */
	public abstract double perimeter();
    
    /*
     * Purpose: determines whether p is within this Shape
     * Parameters: none
     * Precondition: p is not null
     * Returns: boolean - true if p is with this Shape,
     *          false otherwise
     */
	public abstract boolean contains(Point p);
    
    /*
     * Purpose: returns a String reprensentation of this Shape
     * Parameters: none
     * Returns: String - the representation
     */
    public String toString(){
		return position.toString();
	}

}
