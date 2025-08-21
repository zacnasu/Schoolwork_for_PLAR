//Zachary Nasu
//V00911790

public class PlayerLinkedList implements PlayerListInterface {
    
    private PlayerNode	head;
    private PlayerNode  tail;
    private int		count;
	
	public PlayerLinkedList(){
		head = null;
		tail = null;
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
		PlayerNode current = head;
		for( int i =0; i < position; i++){
			current = current.next;
		}
		return current.getPlayer();
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
		if(position ==0){
			if(count == 1){
				head = null;
				tail = null;
			}else{
				PlayerNode curr = head.next;
				curr.prev = null;
				head = curr;
			}
		}else if( position == count-1){
			PlayerNode cur = tail.prev;
			cur.next = null;
			tail = cur;
		}else{
			
		PlayerNode current = head;
		PlayerNode previous = null;
		for(int i = 0; i<position; i++){
			previous = current;
			current = current.next;
		}
		previous.next = current.next;
		current.next.prev = previous;
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
    public int size(){
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
    public void add (Player p){
		PlayerNode newone = new PlayerNode(p);
		if(head == null){
			head = newone;
			tail = newone;
		}else{
			tail.next = newone;
			newone.prev = tail;
			tail = newone;
		}
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
		PlayerNode current = head;
		for( int i = 0; current != null; i++){
			if(current.getPlayer().equals(p)){
				return i;
			}
			current = current.next;
		}
		return -1;
	}
    
    
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
    public String toString(){
		String y = "";
		 PlayerNode current = head;
		 
		 while(current != null){
			 y += current.getPlayer().toString();
			 if(current.next!= null){
				 y += " ";
			 }
			 current = current.next;
		 }
		 return y;
	}
    
    
    /*
     *
     * Purpose: return the a String representing the reverse traversal
     *  with a space between each element
     *
     * Parameters: none
     *
     * Returns: String - the reverse list representation
     *
     */
    // DO NOT remove or change this method - the tester depends on it.
    public String reverse() {
        String s = "";
        PlayerNode tmp = tail;
        
        while (tmp!=null) {
            s += tmp.getPlayer();
            if(tmp.prev!=null)
                s += " ";
            tmp = tmp.prev;
        }
        
        return s;
        
    }
    
}
