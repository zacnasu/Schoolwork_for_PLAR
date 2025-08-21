public interface PlayerListInterface {
    
    
    /*
     *
     * Purpose: returns the element at position
     *
     * Parameters: int - position
     *
     * Pre-Conditions:
     *     for a PlayerList x:
     *    position >= 0 AND
     *    position < x.size()
     *
     * Returns: Player - the player at position
     *
     */
    Player get (int position);
    /*
     *
     * Purpose:
     *    remove the element at position
     *
     * Parameters: int - position
     *
     * Pre-Conditions:
     *    for a PlayerList x:
     *        position >= 0 AND
     *        position < x.size()
     *
     * If x is {"Derek Jeter:300", "Jose Bautista:400", "Michael Saunders:250"} then
     *    after x.remove(0), x is {"Jose Bautista:400", "Michael Saunders:250"}
     *
     * Returns: nothing
     *
     */
    void remove (int position) ;
    
    /*
     *
     * Purpose: returns the number of elements in the list
     *
     * Parameters: none
     *
     * Returns: int - the number of elements
     *
     */
    int size();
    
    /*
     *
     * Purpose: add Player p to the Playerlist
     *  more space is allocated for the Playerlist if necessary
     *  to add p to the list
     *
     * Parameters: Player - p
     *
     * Returns: nothing
     *
     */
    void add (Player p);
    
    /*
     *
     * Purpose: return the position where Player p is in the list,
     *  if p is not found returns -1
     *
     * Parameters: Player - p
     *
     * Pre-Conditions: p is not null
     *
     * Returns: int - position of p, -1 if p does not exist
     *
     * Examples:
     *
     * If x is {"Derek Jeter:300", "Jose Bautista:400", "Michael Saunders:250"} then
     *
     *    Player p = new Player("Derek Jeter");
     *    Player q = new Player("Michael Saunders");
     *    Player r = new Player("Nelson Cruz");
     *
     *    x.find(p) returns 0
     *    x.find(q) returns 2
     *    x.find(r) returns -1
     *
     */
    int find (Player p);
    
    
    /*
     *
     * Purpose: return a String representing the forward traversal
     *  with a space between each element
     *
     * Parameters: none
     *
     * Returns: String - the forward list representation
     *
     */
    String toString();
    
}
