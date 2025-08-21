/*
 * StackNode.java
 *
 * A plain-jane node for a ref-based Stack, assuming that the stack
 * meant to contain Nodes storing values of type T.
 */


public class StackNode<T> {
    private T data;
    private StackNode<T> next;


    public StackNode(T data) {
        this.data = data;
        this.next = null;
    }


    public T getValue() {
        return this.data;
    }


    public StackNode<T> getNext() {
        return this.next;
    }


    public void setNext(StackNode<T> next) {
        this.next = next;
    }


    public String toString() {
        return data.toString();
    }
}
