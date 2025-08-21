//Zachary Nasu
//V00911790

public class RecursiveIntegerLinkedList implements IntegerList {
    
    private IntegerNode    head;
    private IntegerNode    tail;
    private int		count;
    
    
    public RecursiveIntegerLinkedList() {
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
     *     for a IntegerList x:
     *    position >= 0 AND
     *    position < x.size()
     *
     * Returns: Integer - the int at position
     *
     */
    public int get (int position) {
        return get(head, position, 0);
       
    }
	
	private int get(IntegerNode n, int position, int i){
		if(i == position){
			return n.getElement();
		}else{
			return get(n.getNext(), position, i+1);
		}
	}
   
     /*
     *
     * Purpose:
     *    remove the element at position
     *
     * Parameters: int - position
     *
     * Pre-Conditions:
     *    for a IntegerList x:
     *        position >= 0 AND
     *        position < x.size()
     *
     *
     * Returns: nothing
     *
     */
    public void remove (int position){
		remove(head, null, position, 0);
	}
	
	private void remove(IntegerNode curr, IntegerNode prev, int position, int i){
		if( i == position){
			if(i == 0){
				curr.getNext().setPrev(null);
				head = curr.getNext();
				curr.setNext(null);
				curr = null;
			}else if(i == count){
				curr.setPrev(null);
				prev.setNext(null);
				tail = prev;
			}else{
			prev.setNext(curr.getNext());
			curr.getNext().setPrev(prev);
			}
			count--;
			return;
		}else{
			prev = curr;
			curr = curr.getNext();
			remove(curr, prev, position, i+1);
		}
		
	}
    
    /*
     *
     * Purpose: remove all elements with value from the list
     *   The number of occurances of value can be >= 0
     *
     * Parameters: int - val
     *
     * Returns: nothing
     *
     */
    public void removeValue(int val){
		removeValue(head, null, val);
	}
    private void removeValue(IntegerNode curr, IntegerNode prev, int val){
		if(curr == null){
			return;
		}else{
			if(curr.getElement() == val){
				if(curr == tail){
					curr.setPrev(null);
					prev.setNext(null);
					tail = prev;
				}else if(curr == head) {
					curr.getNext().setPrev(null);
					head = curr.getNext();
					curr.setNext(null);
					curr = null;
				}else{
				prev.setNext(curr.getNext());
				curr.getNext().setPrev(prev);
				curr = curr.getNext();
				}
				count--;
			}else{
				prev=curr;
				curr = curr.getNext();
			}
		}
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
		return size(head);
	}
	private int size(IntegerNode n){
		if(n == null){
			return 0;
		}else{
			return 1 + size(n.getNext());
		}
		
	}
    
    /*
     *
     * Purpose: add Integer ito the Integerlist
     *  more space is allocated for the Integerlist if necessary
     *  to add ito the list
     *
     * Parameters: Integer - i
     *
     * Returns: nothing
     *
     */
    public void add (int i){
		IntegerNode n = new IntegerNode(i);
		if(count == 0){
			head = n;
			tail = n;
		}else{
			tail.setNext(n);
			n.setPrev(tail);
			tail = n;
		}
		count++;
		
			
	}
	
    
    
    /*
     *
     * Purpose: return the position where Integer iis in the list,
     *  if iis not found returns -1
     *
     * Parameters: Integer - i
     *
     * Pre-Conditions: iis not null
     *
     * Returns: int - position of i, -1 if idoes not exist
     *
     */
    public int find (int i){
		return find(head, i, 0);
	}
	private int find(IntegerNode n, int i, int index){
		if(n == null){
			return -1;
		}else{
			if(n.getElement() == i){
				return index;
			}else{
				return find(n.getNext(), i, index+1);
			}
		}
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
		return toString(head);
	}
	private String toString(IntegerNode n){
		if(n == null){
			return "";
		}else{
			if(n.getNext() != null){
				return n.getElement() + " " + toString(n.getNext());
			}else{
				return n.getElement() + toString(n.getNext());
			}
		}
	}
    
    /*
     *
     * Purpose: return a String representing the reverse traversal
     *  with a space between each element
     *
     * Parameters: none
     *
     * Returns: String - the reverse list representation
     *
     */
    public String reverse(){
		return reverse(tail);
	}
	private String reverse(IntegerNode n){
		if(n == null){
			return "";
		}else{
			if(n.getPrev() != null){
				return n.getElement() + " " + reverse(n.getPrev());
			}else{
				return n.getElement() + reverse(n.getPrev());
			}
		}
	}
    
   
    
        
}
