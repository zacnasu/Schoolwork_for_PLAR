public class PlayerList implements PlayerListInterface {
    
    private static final int INITIAL_SIZE = 2;
    
    private Player[]	storage;
    private int		    count;
    
    /*
     *
     * Purpose:
     *	Initialize a new instance of PlayerList
     *
     */
    public PlayerList() {
        // You must allocate an array of Player objects
        // of size INITIAL_SIZE here
        // You must set count to be 0
        storage = new Player[INITIAL_SIZE];
        count = 0;
    }
    
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
    public Player get (int position) {
        return storage[position];
    }
    
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
    public void remove (int position) {
        if (position != count - 1)
            storage[position] = storage[count - 1];
        count--;
        
    }
    
    /*
     *
     * Purpose: returns the number of elements in the list
     *
     * Parameters: none
     *
     * Returns: int - the number of elements
     *
     */
    public int size() {
        return count;
    }
    
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
    public void add (Player p) {
        if (count == storage.length)
            growAndCopy();
        
        storage[count++] = p;
    }
    
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
     *     x.find(p) returns 0
     *    x.find(q) returns 2
     *    x.find(r) returns -1
     *
     */
    public int find (Player p) {
        for (int i=0;i<count;i++)
            
            if (storage[i].equals(p))
                return i;
        
        return -1;
    }
    
    /*
     *
     * Purpose: creates a new list double the size
     *  and copies all values from this list to new list
     *  list then becomes new list
     *
     * Parameters: none
     *
     * Returns: nothing
     *
     */
    private void growAndCopy() {
        Player[] a = new Player[storage.length*2];
        
        for (int i = 0; i < count; i++)
            a[i] = storage[i];
        
        storage = a;
    }
}
