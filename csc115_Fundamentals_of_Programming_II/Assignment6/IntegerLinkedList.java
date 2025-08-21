public class IntegerLinkedList implements IntegerList {
    
    
    private IntegerNode	head;
    private IntegerNode tail;
    private int		    count;
    
    
    public IntegerLinkedList() {
        head = null;
        tail = null;
        count = 0;
    }
    
    
    public int get (int position) {
        IntegerNode tmp = head;
        int        curPos = 0;
        
        while (curPos != position) {
            curPos++;
            tmp = tmp.getNext();
        }
        return tmp.getElement();
    }
    
    
    public void remove (int position) {
        
        IntegerNode current = head;
        
        for(int i=0; i<position; i++){
            current = current.getNext();
        }
        
        remove(current);
        
    }
    
    public void removeValue (int val) {
        IntegerNode tmp = head;
        
        while(tmp!=null) {
            if (tmp.getElement() == val) {
                tmp = remove(tmp);
            } else {
                tmp = tmp.next;
            }
        }
    }
    
    
    /*
     *
     * Purpose: remove n from list, and
     *  return IntegerNode that was n's next
     *  before n was removed
     *
     * Parameters: IntegerNode - n
     *
     * Precondition: n is not null
     *
     * Postcondition: n.next and n.prev are null
     *
     * Returns: IntegerNode -  n's next
     *
     */
    private IntegerNode remove (IntegerNode n) {
        IntegerNode nxt = n.next;
        
        if (n==head) {
            head = head.next;
        } else {
            n.prev.next = n.next;
        }
        
        if (n==tail) {
            tail = tail.prev;
        }  else {
            n.next.prev = n.prev;
        }
        
        n.prev = null;
        n.next = null;
        
        count--;
        
        return nxt;
        
    }
    
    
    public int size() {
        return count;
    }
    
    
    public void add (int i) {
        IntegerNode n = new IntegerNode(i, null, tail);
        
        if (head == null)
            head = n;
        else
            n.prev.next = n;
        
        
        tail = n;
        
        count++;
        
    }
    
    
    public int find (int i) {
        IntegerNode tmp = head;
        int         curPos = 0;
        
        while (tmp!=null) {
            
            if (tmp.getElement() == i)
                return curPos;
            
            curPos++;
            tmp = tmp.getNext();
        }
        
        return -1;
    }
    
    
    public String toString() {
        String s = "";
        IntegerNode tmp = head;
        
        while (tmp!=null) {
            s += tmp.getElement();
            if(tmp.next!=null)
                s += " ";
            tmp = tmp.next;
        }
        
        return s;
        
    }
    
    public String reverse() {
        String s = "";
        IntegerNode tmp = tail;
        
        while (tmp!=null) {
            s += tmp.getElement();
            if(tmp.prev!=null)
                s += " ";
            tmp = tmp.prev;
        }
        
        return s;
        
    }
    
}
