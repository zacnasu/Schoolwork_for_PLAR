public class IntegerArrayList implements IntegerList {

    private static final int INIT_SZ = 4;

    private int[] data;  // elements will be stored
    private int   count;  // number elements

    public IntegerArrayList () {
        count = 0;
        data = new int[INIT_SZ];
        
    }
    /* Parameters: (int) val
     * Purpose:  add val to the front of the list
     * Returns:  nothing
     */
    public void addFront (int val) {
        if (count==data.length)
            expandAndCopy();
        
        shiftRight();
        data[INIT_SZ] = val;
        count++;
    }
    public void shiftRight() {
        
    }
    
    /* Parameters: (int) val
     * Purpose:  add val to the back of the list
     * Returns:  nothing
     */
    public void addBack (int val) {
        if (count==data.length)
            expandAndCopy();
        data[count] = val;
        count++;
    }
    
    public void expandAndCopy() {
        
    }
    
    /* Parameters: none
     * Purpose:  get the size of the list
     * Returns:  (int) the size
     */
    public int size () {
        return count;
    }
    
    /* Parameters: (int) position
     * Purpose:  get the int value at specified position in the list
     * Returns:  (int) the int value
     * Precondition: 0 <= position < list.size()
     */
    public int get (int position) {
        return data[position];
    }


    /* Parameters: nothing
     * Purpose: create a string representation of list
     * Returns: (String) the string representation
     */
    public String toString() {
        String s = "";

        for(int i=0; i<count; i++)
            s += data[i] + " ";

        return s;
    }

}
