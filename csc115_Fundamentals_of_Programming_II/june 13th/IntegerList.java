public interface IntegerList {

    /* Parameters: (int) i
     * Purpose:  add i to the front of the list
     * Returns:  nothing
     */
    void addFront (int i);

    /* Parameters: (int) i
     * Purpose:  add i to the back of the list
     * Returns:  nothing
     */
    void addBack (int i);

    /* Parameters: nothing
     * Purpose:  get the size of the list
     * Returns:  (int) size
     */
    int size ();

    /* Parameters: (int) position
     * Purpose:  get the int value at specified position in the list
     * Returns:  (int) the int value
     * Precondition: 0 <= position < list.size()
     */
    int get (int position);

    /* Parameters: (int) val
     * Purpose:  remove val from the list
     * Returns:  nothing
     */
    public void remove (int val);
}


