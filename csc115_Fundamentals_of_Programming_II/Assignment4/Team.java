//Zachary Nasu
//V00911790
public class Team {
    private String name;
    private PlayerListInterface	players;
    
    /*
     * constructor
     *
     * Purpose: initialize this instance of Team
     * 	with name passed in and an empty playerlist
     *
     */
    // ToDo:
	public Team(String name){
		this.name = name;
		players = new PlayerList();
	}
    
    /*
     * constructor
     *
     * Purpose: initialize this instance of Team
     *     with name passed in and a new PlayerList
     *     with p added to it
     *
     * Parameters: String - the name, Player - p
     *
     */
    // ToDo:
	public Team(String name, Player p){
		this.name = name;
		players = new PlayerList();
		players.add(p);
    }
    /*
     * getName
     *
     * Purpose: return the name associated with this Team
     *
     * Parameters: none
     *
     * Returns: String - the name
     *
     */
    // ToDo:
	public String getName(){
		return name;
	}
    
    /*
     * setName
     *
     * Purpose: change the name associated with this instance to parameter value
     *
     * Parameters: String - name
     *
     * Returns: nothing
     *
     */
    // ToDo:
	public void setName(String name){
		this.name = name;
	}
    
    /*
     * addPlayer
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
    // ToDo:
	public void addPlayer(Player p){
		players.add(p);
	}
    
    /*
     * removePlayer
     *
     * Purpose: remove p from the list of Players associated with this Team
     *	if p is not in the list, do nothing.
     *
     * Parameters: Player p
     *
     * Returns: nothing
     *
     */
    // ToDo:
	public void removePlayer(Player p){
		int y = players.find(p);
		players.remove(y);
	
	}
    
    /*
     * getPlayerCount
     *
     * Purpose: return the count of Players associated with this Team
     *
     * Parameters: none
     *
     * Returns: int - the count
     *
     */
    // ToDo:
    public int getPlayerCount(){
		int y = players.size();
		return y;
	}
    
    /*
     * getPlayer
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
    // ToDo:
	public Player getPlayer(int position){
		return players.get(position);
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
