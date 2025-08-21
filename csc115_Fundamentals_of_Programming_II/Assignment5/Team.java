public class Team {
    private String name;
    private PlayerListInterface	players;
    
    /*
     *
     * Purpose: initialize this instance of Team
     * 	with name passed in and an empty playerlist
     *
     */
    public Team (String name) {
        //You must allocate a PlayerList here
        this.name = name;
        //players = new PlayerList(); // array list
        players = new PlayerLinkedList();  // linked list
        
    }
    
    /*
     *
     * Purpose: initialize this instance of Team
     *     with name passed in and a new playerlist
     *     with p added to it
     *
     */
    public Team (String name, Player p) {
        //You must allocate a PlayerList here
        this(name);
        players.add(p);
    }
    
    /*
     *
     * Purpose: return the name associated with this instance
     *
     * Parameters: none
     *
     * Returns: String - the name
     *
     */
    public String getName () {
        return name;
    }
    
    /*
     *
     * Purpose: change the name associated with this instance to parameter value
     *
     * Parameters: String - name
     *
     * Returns: nothing
     *
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /*
     *
     * Purpose: add Player p to this Team
     *	there is no maximum number of players that can be
     *	assigned to a Team.
     *
     * Parameters: Player - p
     *
     * Returns: nothing
     *
     */
    public void addPlayer (Player p) {
        players.add(p);
    }
    
    /*
     *
     * Purpose: remove p from the list of Players associated with this Team
     *	if p is not in the list, do nothing.
     *
     * Parameters: Player p
     *
     * Returns: nothing
     *
     */
    public void removePlayer (Player p) {
        int pos = players.find(p);
        
        if (pos != -1) {
            players.remove(pos);
        }
    }
    
    /*
     *
     * Purpose: return the count of Players associated with this Team
     *
     * Parameters: none
     *
     * Returns: int - the count
     *
     */
    public int getPlayerCount() {
        return players.size();
    }
    
    /*
     *
     * Purpose: returns the Player at index pos from this Team
     *
     * Parameters: int - pos
     *
     * Returns: Player - the player at index
     *
     * Pre-condition:
     *	pos >= 0 AND
     *	pos < this.getPlayerCount()
     *
     */
    public Player getPlayer (int pos) {
        return players.get(pos);
    }
    
    /*
     *
     * Purpose: return a String representation of this Team
     *
     * Parameters: none
     *
     * Returns: String - the representation
     *
     */
    public String toString() {
        String s = name;
        
        for (int i=0;i<players.size();i++)
        {
            s += "\n";
            s += players.get(i);
        }
        return s;
    }
}
