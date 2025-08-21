public class IntegerLinkedList implements IntegerList{

    private int count;
    private Node head;

    public IntegerLinkedList() {

        count = 0;
        head = null;
    }
    /* Parameters: (int) i
     * Purpose:  add i to the front of the list
     * Returns:  nothing
     */
    public void addFront (int i){
        Node nn = new Node(i);
        if (head != null)
            nn.next = head;
        head = nn;
        count++;
    }

    /* Parameters: (int) i
     * Purpose:  add i to the back of the list
     * Returns:  nothing
     */
    public void addBack (int i){
        Node nn = new Node(i);
        if (head == null) {
            head = nn;
        } else {
            Node tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = nn;
        }
        count++;

    }

    /* Parameters: nothing
     * Purpose:  get the size of the list
     * Returns:  (int) size
     */
    public int size (){
        return count;
    }

    /* Parameters: (int) position
     * Purpose:  get the int value at specified position in the list
     * Returns:  (int) the int value
     * Precondition: 0 <= position < list.size()
     */
    public int get (int position){
        Node tmp = head;

        for(int i=0; i<position; i++)
            tmp = tmp.next;

        return tmp.getValue();
    }

    /* Parameters: none
     * Purpose:  returns a string representing the list
     * Returns:  (String) the representation
     */
    public String toString(){
        String s = "";

        Node tmp = head;
        while(tmp != null) {
            s = s + tmp.getValue() + " ";
            tmp = tmp.next;
        }

        return s;
    }



    /* Parameters: (int) val
     * Purpose:  remove val from the list
     * Returns:  nothing
     */
    public void remove (int val){
        Node tmp = head;
        Node prev = null;
        while(tmp!=null) {
            if(tmp.getValue() == val) {
                removeNode(prev, tmp);
            } else {
                prev = tmp;
            }
            tmp = tmp.next;
        }
    }

    /* Parameters: Node prev, Node n
     * Purpose:  remove n from this list
     * Postcondition: n's next and prev values are unchanged
     * Returns:  nothing
     */
    private void removeNode (Node prev, Node n){
        if (prev == null)
            head = n.next;
        else {
            prev.next = n.next;
        }
        count--;
    }

    /* Parameters: none
     * Purpose:  prints all values in this list
     * Returns:  nothing
     */
    public void printValues() {
        printValues(head);
    }

    /* Parameters: Node n
     * Purpose:  prints all values in list pointed to by n
     * Returns:  nothing
     */
    private void printValues(Node n) {
        if (n==null) {
            System.out.println();
        } else {
            int firstVal = n.getValue();
            Node rest = n.next;
            System.out.print(firstVal + " ");
            printValues(rest);
        }
    }

    /* Parameters: none
     * Purpose:  prints all odd values in this list
     * Returns:  nothing
     */
    public void printOddValues() {
        printOddValues(head);
    }

    /* Parameters: Node n
     * Purpose:  prints all odd values in list pointed to by n
     * Returns:  nothing
     */
    private void printOddValues(Node n) {
        if (n==null) {
            System.out.println();
        } else {
            int firstVal = n.getValue();
            Node rest = n.next;
            if (firstVal%2 != 0)
                System.out.print(firstVal + " ");
            printOddValues(rest);
        }
    }

    /* Parameters: none
     * Purpose:  updates all values in list to their absolute value
     * Returns:  nothing
     */
    public void abs() {
        abs(head);
    }

    /* Parameters: Node n
     * Purpose:  updates all values in list pointed to by n to their absolute value
     * Returns:  nothing
     */
    private void abs(Node n) {
        if (n==null) {
            return;
        } else {
            int firstVal = Math.abs(n.getValue());
            n.setValue(firstVal);
            Node rest = n.next;
            abs(rest);
        }
    }


    /* Parameters: none
     * Purpose:  computes and returns the sum of all values in this list
     * Returns:  int - the sum
     */
    public int sum() {
        return sum(head);
    }

    /* Parameters: Node n
     * Purpose:  computes and returns the sum of all values in list pointed to by n
     * Returns:  int - the sum
     */
    private int sum(Node n) {
        if (n==null) {
            return 0;
        } else {
            int firstVal = n.getValue();
            Node rest = n.next;
            return firstVal + sum(rest);
        }
    }

    /* Parameters: none
     * Purpose:  computes and returns the sum of all odd values in this list
     * Returns:  int - the sum
     */
    public int sumOdd() {
        return sumOdd(head);
    }

    /* Parameters: Node n
     * Purpose:  computes and returns the sum of all odd values in list pointed to by n
     * Returns:  int - the sum
     */
    private int sumOdd(Node n) {
        if (n==null) {
            return 0;
        } else {
            int firstVal = n.getValue();
            Node rest = n.next;
            if (firstVal%2 != 0)
                return firstVal + sumOdd(rest);
            else
                return sumOdd(rest);
        }
    }
	public int max(){
		return max(head);
	}
	
	private int max(Node n){
		if(n == null){
			return 0;
		}
		int i = max(n.next);
		if(n.getValue() > i){
			return n.getValue();
		}
		return i;
	}
	
	public boolean allAbove(int i){
		return allAbove(head, i);
	}
	private boolean allAbove(Node n , int i){
		if(n == null){
			return true;
		}
		if(allAbove(n.next, i) == true){
			if(n.getValue() > i){
				return true;
			}
		}
		return false;
	}

}
