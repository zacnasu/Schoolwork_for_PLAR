/*
 * IntegerNode.java
 *
 * An implementation of a node class for a doubly-linked list of integers.
 *
 */

public class IntegerNode {
	IntegerNode	next;
	IntegerNode	prev;
	int		    e;

	public IntegerNode() {
		next = null;
        prev = null;
		e = 0;
	}

	public IntegerNode (Integer e) {
		this.e = e;
		next = null;
        prev = null;
	}

	public IntegerNode (Integer e, IntegerNode next, IntegerNode prev) {
		this.e = e;
		this.next = next;
        this.prev = prev;
	}

	public IntegerNode getNext()	{
		return next;
	}
	
	public void setNext (IntegerNode next) {
		this.next = next;
	}

	public IntegerNode getPrev() {
		return prev;
	}
	
	public void setPrev (IntegerNode prev) {
		this.prev = prev;
	}

	public int getElement() {
		return e;
	}
	
	public void setElement (Integer e) {
		this.e = e;
	}	
}

