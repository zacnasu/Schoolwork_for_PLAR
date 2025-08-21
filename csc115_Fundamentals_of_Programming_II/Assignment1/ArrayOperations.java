//Zachary Nasu
//V00911790

import java.lang.Math;

/*
 * ArrayOperations
 * DO NOT use builtin java Arrays mehthods
 * A class with basic array methods to
 *  - print the values in an array
 *  - calculate the product of the values in an array
 *  - calculate the minimum of the values in an array
 *  - calculate the maximum of the values in an array
 *  - determine the equality to 2 arrays
 *  -
 *
 */
public class ArrayOperations {
    /*
     * printArray
     *
     * Purpose: prints all the values in the array to the console
     *  example format:  {1,2,3,4}
     *
     * Parameters: an array of integers
     *
     * Returns: void
     *
     */
    public static void printArray ( int[] array ) {
        System.out.print("{");
		
        for(int i=0; i<array.length-1; i++)
            System.out.print(array[i] + ",");

        System.out.println(array[array.length-1] + "}");
		
    }

    /*
     * arraySum
     *
     * Purpose: computes the product of all values in the input array
     *  NOTE: product of 3 numbers n1, n2 and n3 = n1*n2*n3
     *  NOTE: product of no numbers = 1
     *
     * Parameters: an array of integers
     *
     * Returns: product of all values in the array
     *
     */
    public static int arrayProduct ( int[] array ) {
        int product = 1;
		for(int i =0; i<array.length; i++){
			product = array[i]*product;
		}
        

        return product;
    }

    /*
     * arrayMax
     *
     * Purpose: finds the maximum value in the input array
     *
     * Parameters: an array of integers
     *
     * Preconditions:
     *	array contains at least one element
     *
     * Returns: maximum value in the array
     *
     */
    // TODO...
	public static int arrayMax ( int[] array){
		int max = -2000000;
		for( int i = 0; i<array.length; i++){
			if(array[i]>max){
				max = array[i];
			}
		}
		return max;
	}
	
	
    /*
     * arrayMin
     *
     * Purpose: finds the maximum value in the input array
     *
     * Parameters: an array of integers
     *
     * Preconditions:
     *	array contains at least one element
     *
     * Returns:  minimum value in the array
     *
     */
    // TODO...
	public static int arrayMin(int[] array){
		int min = 2000000;
		for(int i =0; i<array.length; i++){
		if(array[i]<min){
			min=array[i];
			}
		}
		return min;
	}
    /*
     * arraysEqual
     *
     * Purpose: determines whether the two arrays are equal
     *      where equal means array1 and array2 are the same length
     *      and the contain the same values in the same order
     *
     * Parameters: two arrays of integers
     *
     * Returns: true if the are equal, false otherwise
     *
     */
    // TODO...
	public static boolean arraysEqual(int[] array1, int[] array2){
		if(array1.length!= array2.length){
			return false;
		}
		for(int i =0; i<array1.length; i++){
			if(array1[i] != array2[i]){
				return false;
			}
		}
		return true;
	}
    /*
     * shiftLeft
     *
     * Purpose: copies every element in the array into a new array of the same length
     *  with every element shifted by the left by the specified amount
     *
     * Parameters: an input array of integers, a result array that holds ints
     *   and the number of positions to shift left by
     *
     * Returns: int[] - the new array
     *
     */
    // TODO...
	
	public static int[] shiftLeft(int[] array, int left){
		int[] newArray = new int[array.length];
		if(array.length == 0 || left ==0){
			return array;
		}
		int place = left % array.length;
		for(int i =0; i< array.length; i++){
			newArray[i] = array[place++];
			if(place == array.length){
				place = 0;
			}
		}
	return newArray;
	}
		

}
