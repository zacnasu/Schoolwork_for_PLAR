//Zachary Nasu
//V00911790


//see Stack Interface for documentation
// complete the given stubs
public class StackRefBased<T extends Object> implements Stack<T> {
    private StackNode<T> top;

    public StackRefBased() {
        this.top = null;
    }

    public int size() {
		int i = 0 ;
		StackNode<T> j = top;
		while(j!=null){
				j = j.getNext();
				i++;
		}
        
        return i;
    }


    public boolean isEmpty() {
		if(top == null)
        return true;
		
		return false;
    }


    public void push(T data) {
        StackNode<T> x = new StackNode(data);
		x.setNext(top);
		top = x;
    }


    public T pop() throws StackEmptyException {
		if(top == null){
			throw new StackEmptyException("pop");
		}
		StackNode<T> x = top;
		top = top.getNext();
		x.setNext(null);
        return x.getValue();
    }


    public T peek() throws StackEmptyException {
		if(top == null){
			throw new StackEmptyException("peek");
		}
		return top.getValue();
    }


    public void makeEmpty() {
		top = null;
    }
    
    /*
     * Purpose: creates and returns a String representation of this Stack
     *  from bottom to top
     * Parameters: none
     * Returns: String - the representation
     */
    
    public String toString() {
        String result = "";
        
        StackNode<T> tmp = top;
        while(tmp != null) {
            result = tmp.getValue() + result;
            tmp = tmp.getNext();
        }
        
        return result;
    }
    /*
     * Purpose: overrides Object's equals method
     *  determines whether this StackRefBased has the same
     *  elements as other StackRefBased, in the same order
     * Parameters: Object other - the other StackRefBased
     * Precondition: assumes other is an instance of StackRefBased<T>
     * Returns: boolean - true if the are equal, false otherwise
     */
    public boolean equals(Object other) {
        // this line will cause a warning when you compile
        // you can ignore this warning
        StackRefBased<T> otherStack = (StackRefBased<T>)other;
        
        StackNode<T> thisTmp = top;
        StackNode<T> otherTmp = otherStack.top;

        while(thisTmp!=null && otherTmp!=null){
            T thisVal = thisTmp.getValue();
            T otherVal = otherTmp.getValue();
            if (!thisVal.equals(otherVal)){
                return false;
            }
            thisTmp = thisTmp.getNext();
            otherTmp = otherTmp.getNext();
        }
            
        if (thisTmp!=null || otherTmp!=null)
            return false;
        else
            return true;
    }
}
