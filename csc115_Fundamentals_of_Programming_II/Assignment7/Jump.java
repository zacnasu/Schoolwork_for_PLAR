/*
 * Class to represent a Jump on a Triangle Solitaire Board
 */
public class Jump {

    private int from;   //hole jumping from
    private int over;   //hole jumping over
    private int to;     //hole jumping to
    
    /*
     * Purpose: sets from, over, and to of this Jump
     *      to  given from, over and to
     * Parameters: int from, int over, int to
     * Precondition: from, over and to must be
     *    >=0 and <the size of Triangle Solitaire Board
     *  from, over and to must not be an impossible Jump
     */
    public Jump(int from, int over, int to) {
        this.from = from;
        this.over = over;
        this.to = to;
    }
    
    /*
     * Purpose: gets the from position of this Jump
     * Parameters: none
     * Returns: int - the from position
     */
    public int getFrom(){
        return from;
    }
 
    /*
     * Purpose: gets the over position of this Jump
     * Parameters: none
     * Returns: int - the over position
     */
    public int getOver(){
        return over;
    }
    
    /*
     * Purpose: gets the to position of this Jump
     * Parameters: none
     * Returns: int - the to position
     */
    public int getTo(){
        return to;
    }
    
    /*
     * Purpose: creates a triangle shaped String representation of this Jump
     * Parameters: none
     * Returns: String - string representation
     */
    public String toString() {
        return from + ":" + over + ":" + to;
    }
}
