/*
 * PlayerNode.java
 *
 * An implementation of a node class for a doubly-linked list of integers.
 *
 * Your textbook uses the following coding conventions for setting the value of 
 * a node's next and prev values 
 *
 *	n.next = null; 
 * 	n.prev.next = p;
 *
 * Rather than:
 *
 *	n.setNext(null);
 *	n.getPrev().setNext(p);
 *
 * Therefore I have not set next and prev to be private
 * contrary to what we have said about keeping data fields private...
 * 
 * In this assignment, and on exams, you are free to use whichever 
 * method you find most comfortable.
 *
 */

public class PlayerNode
{
	PlayerNode	next;
	PlayerNode	prev;
	Player		p;

	public PlayerNode() {
		next = null;
        prev = null;
		p = null;
	}

	public PlayerNode (Player p) {
		this.p = p;
		next = null;
        prev = null;
	}

	public PlayerNode (Player p, PlayerNode next, PlayerNode prev) {
		this.p = p;
		this.next = next;
        this.prev = prev;
	}

	public PlayerNode getNext()	{
		return next;
	}
	
	public void setNext (PlayerNode next) {
		this.next = next;
	}

	public PlayerNode getPrev() {
		return prev;
	}
	
	public void setPrev (PlayerNode prev) {
		this.prev = prev;
	}

	public Player getPlayer() {
		return p;
	}
	
	public void setPlayer (Player p) {
		this.p = p;
	}	
}

