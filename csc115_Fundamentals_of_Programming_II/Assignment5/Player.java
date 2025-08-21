public class Player {
    
    private String name;
    private int battingAvg;
    
    /*
     *
     * Purpose:
     * 	Initialize this instance of Player
     *  set name to parameter name
     * 	set the battingAvg to be 0
     *
     * Parameters: String - name
     *
     */
    public Player (String name) {
        battingAvg = 0;
        this.name = name;
    }
    
    /*
     *
     * Purpose:
     *	Initialize this instance of Player
     *  set name to parameter name
     *  set battingAvg to parameter battingAvg
     *
     * Parameters: String - name, int - battingAvg
     *
     */
    public Player (String name, int battingAvg) {
        this.battingAvg = battingAvg;
        this.name = name;
        
    }
    
    /*
     *
     * Purpose:	Change the name associated with this Player
     * 	to parameter value
     *
     * Parameters: String - name
     *
     * Returns: nothing
     *
     */
    public void setName (String name) {
        this.name = name;
    }
    
    /*
     *
     * Purpose: Return the name associated with this Player
     *
     * Parameters: none
     *
     * Returns: String - name
     *
     */
    public String getName() {
        return name;
    }
    
    /*
     *
     * Purpose: Change the battingAvg associated with this Player
     *	to be parameter battingAvg
     *
     * Parameters: int - battingAvg
     *
     * Returns: nothing
     *
     */
    public void setBattingAvg (int battingAvg) {
        this.battingAvg = battingAvg;
    }
    
    /*
     *
     * Purpose: Return the battingAvg associated with this Player
     *
     * Parameters: none
     *
     * Returns: int - battingAvg
     *
     */
    public int getBattingAvg() {
        return battingAvg;
    }
    
    /*
     *
     * Purpose: Compare this instance of Player to other
     *	return true if they are the same.
     *
     *	We consider two Players to be equal if
     *	their name are the same.  We do NOT consider
     *	the battingAvg.
     *
     * Parameters: Player - other
     *
     * Pre-conditions:
     *	other is not null
     *
     * Returns: boolean - true if this name is the same as
     *		other's name, false	otherwise
     *
     * Examples:
     *
     *	Player p = new Player("Derek Jeter", 265);
     *	Player q = new Player("Derek Jeter", 300);
     *	Player r = new Player("Jose Bautista", 400);
     *
     *	p.equals(q)	- returns true
     *	p.equals(r)	- returns false
     *
     */
    public boolean equals (Player other) {
        return other.getName().equals(name);
    }
    
    /*
     *
     * Purpose: Return a String representation of this Player
     *
     * Parameters: none
     *
     * Returns: String - the reprensentation
     *
     * Examples:
     * 	Player p = new Player("Derek Jeter", 265);
     *
     *	p.toString() returns	Derek Jeter:265
     *
     */
    public String toString() {
        return name + ":" + battingAvg ;
    }
}
