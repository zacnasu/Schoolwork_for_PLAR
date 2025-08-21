//Zachary Nasu
//V00911790

public class PlayerList implements PlayerListInterface {
    
    private static final int INITIAL_SIZE = 2;  // initial capacity of storage
    
    private Player[]    storage;  // array to store list elements
    private int         count;    // number of Players in the list
    
    /*
     *
     * Purpose:
     *    Initialize a new instance of PlayerList
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
    public Player get (int position){
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
		storage[position] = null;
		if(storage[position+1] != null){
			for(int i = position; i<count-1; i++){
				storage[i] = storage[i+1];
			}
		 storage[count-1] = null;
		}
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
		return this.count;
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
    public void add (Player p){
		if(count >= storage.length){
			Player[] temp = new Player[storage.length*2];
			for(int i =0; i<count; i++){
				temp[i] = storage[i];
			}
			storage = temp;
		}
		storage[count] = p; 
		count++;
		
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
     *    x.find(p) returns 0
     *    x.find(q) returns 2
     *    x.find(r) returns -1
     *
     */
    public int find (Player p){
		for(int i =0; i<count; i++){
			if(p.equals(storage[i])){
				return i;
			}
		}
		return -1;
	}
    

    
    

    
    
    
    public String toString() {
        String s = "";
        
        for(int i=0; i< count; i++) {
            s += storage[i];
            if(i != (count-1))
                s += " ";
        }
        
        return s;
        
    }
    
    
}
