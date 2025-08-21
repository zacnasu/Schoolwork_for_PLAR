public interface IntegerList {
    
    
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
    int get (int position);
    
    
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
    void remove (int position) ;
    
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
    void removeValue(int val) ;
    
    /*
     *
     * Purpose: returns the number of elements in the list
     *
     * Parameters: none
     *
     * Returns: int - the number of elements
     *
     */
    int size();
    
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
    void add (int i);
    
    
    
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
    int find (int i);
    
    
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
    String toString();
    
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
    String reverse();
    
    
}
