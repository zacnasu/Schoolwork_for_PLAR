//Zachary Nasu
//V00911790

/*
 * HeapPriorityQueue.java
 *
 * An implementation of a PriorityQueue using a heap.
 * based on the implementation in "Data Structures and Algorithms
 * in Java", by Goodrich and Tamassia
 *
 * This implementation will throw a Runtime, HeapEmptyException
 *	if the heap is empty and removeHigh is called.
 *
 * This implementation will throw a Runtime, HeapFullException
 *	if the heap is full and insert is called.
 *
 */

public class HeapPriorityQueue implements PriorityQueue 
{
    protected final static int DEFAULT_SIZE = 10000;
    
    protected Comparable[] storage;
    protected int currentSize;
    
    public HeapPriorityQueue () {
        this(DEFAULT_SIZE);
        currentSize = 0;
    }
    
    public HeapPriorityQueue(int size) {
        // TODO...
        storage = new Comparable[size];
    }
    
    public int size () {
        // TODO...
        
        return currentSize ;
    }
    
    public Comparable removeHigh () throws HeapEmptyException {
        // TODO...
        if(isEmpty())
            throw new HeapEmptyException();
        swapElement(0, currentSize-1);
        Comparable temp = storage[currentSize-1];
        storage[currentSize-1] = null;
        bubbleDown(0);
        currentSize--;
        return temp;
        
    }
    
    public void insert ( Comparable k  ) throws HeapFullException {
        // TODO...
        if(isFull() == true)
            throw new HeapFullException();
        
        storage[currentSize] = k;
        bubbleUp(currentSize);
        currentSize++;
    }

    
    public boolean isEmpty ( ) {
        // TODO...
        if(currentSize <= 0)
            return true;

        return false;
    }
    
    /*
     * PURPOSE:
     *    Determines if the PriorityQueue is full or not.
     *
     * PRECONDITIONS:
     *    none
     *
     * RETURNS:
     *    true if PriorityQueue is empty, false otherwise.
     *
     */
    protected boolean isFull ( ) {
        // HELPER METHOD you may find helpful to implement and use
        if(currentSize>=storage.length)
        return true;
        

        return false;
    }
    
    
    
    /*
     * PURPOSE:
     *    Bubbles value at index up until the value at
     *      the parent index is bigger by swapping values
     *
     * PARAMETERS:
     *    int index
     *
     * RETURNS:
     *    None
     *
     */
    protected void bubbleUp (int index) {
        // HELPER METHOD you may find helpful to implement and use
        bubbleup(index);
    }
    private void bubbleup(int index){
        if(parent(index)<0)
            return;
        
        if(storage[index].compareTo(storage[parent(index)])<=0)
            return;
        
        

        swapElement(index, parent(index));
        bubbleup(parent(index));
        
            

    }
    
    /*
     * PURPOSE:
     *    Bubbles value at index down until the value at
     *      the both child indicies is smaller than that at index by swapping values
     *      When a swap occurs, it is with the larger of the two children
     *
     * PARAMETERS:
     *    int index
     *
     * RETURNS:
     *    None
     *
     */
    protected void bubbleDown(int index) {
        // HELPER METHOD you may find helpful to implement and use
        
        bubbledown(index);
    }

    private void bubbledown(int index){
        if(!(hasLeft(index))&&!(hasRight(index)))
            return;
        if(storage[index].compareTo(storage[getIndexOfMax(index)])>=0){
            return;
        }
        int temp = getIndexOfMax(index);
        swapElement(index, temp);
        bubbleDown(temp);

        
            
    }
    
    /*
     * PURPOSE:
     *    determines the largest of values at index and its
     *      2 child indicies and returns the index of the largest
     *
     * PARAMETERS:
     *    int index - index of the parent
     *
     * PRECONDITION:
     *    value at index is not null
     *
     * RETURNS:
     *    int - the index of the largest
     *
     */
    protected int getIndexOfMax(int index) {
        // HELPER METHOD you may find helpful to implement and use
        if(!(hasRight(index))&&!(hasLeft(index)))
            return index;
    
        if(!(hasRight(index)))
            return leftChild(index);
        if(!(hasLeft(index)))
            return rightChild(index);

        Comparable left = storage[leftChild(index)];
        Comparable right = storage[rightChild(index)];
        if(left.compareTo(right)>0)
            return leftChild(index);
        
        return rightChild(index);
    }
    
    /*
     * PURPOSE:
     *    swaps elements at position p1 and p2 in storage
     *
     * PARAMETERS:
     *    int p1, int p2
     *
     * PRECONDITION:
     *    p1 and p2 are valid indicies in storage
     *
     * RETURNS:
     *    None
     *
     */
    protected void swapElement ( int p1, int p2 ) {
        // HELPER METHOD you may find helpful to implement and use
        Comparable temp = storage[p1];
        storage[p1] = storage[p2];
        storage[p2] = temp;

    }
    
    /*
     * PURPOSE:
     *    computes and returns the position of parent to pos
     *
     * PARAMETERS:
     *    int pos
     *
     * PRECONDITION:
     *    pos is a valid index in storage
     *
     * RETURNS:
     *    int - position of the parent
     *
     */
    protected int parent ( int pos ) {
        // HELPER METHOD you may find helpful to implement and use
        
        return (pos-1)/2;
    }
    
    /*
     * PURPOSE:
     *    computes and returns the position of the left child to pos
     *
     * PARAMETERS:
     *    int pos
     *
     * PRECONDITION:
     *    pos is a valid index in storage that has a left child
     *
     * RETURNS:
     *    int - position of the left child
     *
     */
    protected int leftChild ( int pos ) {
        // HELPER METHOD you may find helpful to implement and use
        return 2*pos+1;
    }
    
    /*
     * PURPOSE:
     *    computes and returns the position of the right child to pos
     *
     * PARAMETERS:
     *    int pos
     *
     * PRECONDITION:
     *    pos is a valid index in storage that has a right child
     *
     * RETURNS:
     *    int - position of the right child
     *
     */
    protected int rightChild ( int pos ) {
        // HELPER METHOD you may find helpful to implement and use
    
        return 2*pos +2;
    }
    
    
    /*
     * PURPOSE:
     *    determines whether the is a valid left child to pos
     *
     * PARAMETERS:
     *    int pos
     *
     * PRECONDITION:
     *    pos is a valid index in storage
     *
     * RETURNS:
     *    boolean - true if pos has a left child
     *
     */
    protected boolean hasLeft ( int pos ) {
        // HELPER METHOD you may find helpful to implement and use
        if(leftChild(pos)>=storage.length){
            return false;
        }
        if(storage[leftChild(pos)]!=null)
        return true;
        
        return false;
    }
    
    /*
     * PURPOSE:
     *    determines whether the is a valid right child to pos
     *
     * PARAMETERS:
     *    int pos
     *
     * PRECONDITION:
     *    pos is a valid index in storage
     *
     * RETURNS:
     *    boolean - true if pos has a right child
     *
     */
    protected boolean hasRight ( int pos ) {
        // HELPER METHOD you may find helpful to implement and use
        if(rightChild(pos)>=storage.length){
            return false;
        }
        if(storage[rightChild(pos) ] !=null)
        return true;

        return false;
    }
    
    
    /*
     * PURPOSE:
     *    constructs a String representation of the elements in storage separated by spaces
     *    Ordered by their position in storage not by priority
     *
     * PARAMETERS:
     *    None.
     *
     * RETURNS:
     *    String - the String representation
     *
     */
    public String toString() {
        // HELPER METHOD you may find helpful to implement and use
        // This will work with commented out debug print statements in Tester
        String result = "";
        for(int i =0; i<currentSize; i++){
            result+=storage[i] + " ";
        }
        return result;
    }
    
}
