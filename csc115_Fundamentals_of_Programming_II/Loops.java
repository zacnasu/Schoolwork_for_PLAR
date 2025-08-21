// Loops.java
class Loops {
    
    public static void main (String[] args) {
        
        // Q0. Write and test the method sumToN
        //     documentation below
        int result = sumToN(1);
		System.out.println("should be 1: " + result); 
			result =sumToN(4);
		System.out.println("should be 10: " + result);
        
        
        arrayReview ();
    }
    
    /*
     * sumToN
     *
     * Purpose: sums the numbers from 1 to n
     * Parameters: int - n
     * Precondition: n is >0
     * Returns: int - n
     */
    
    public static int sumToN(int n){
		int sum =0;
		for(int i = 1; i<=n ;i++){
		sum+= i;
		}
		return sum;
	}
    
    public static void arrayReview () {
        
        //        // *Q1. What is the output of the following:
        //        int[] array1 = null;
        //        System.out.println("array1: " + array1);
        //
        //        // *Q2. What is the output of the following:
        //        array1[1] = 5;
        //        System.out.println("array1[1]: " + array1[1]);
        //
        //        // *Q3. What is the output of the following:
        //        int[] array2 = {4, 5, 6};
        //        System.out.println("array2: " + array2);
        //        System.out.println("array2[1]: " + array2[1]);
        //
        //        // *Q4. What is the output of the following:
        //        double[] array3 = new double[10];
        //        System.out.println("array3[10]: " + array3[10]);
        
        
        // Q5. Write and test a method that takes an array of integers,
        //  doubles all the values in the array
        
        
        // Q6. Write and test a method that takes an array of integers, and an integer n,
       //      returns true if all numbers in the array are above n and false otherwise
        
 
        // Q7. Write and test a method that given an array of integers,
        //      returns the sum of all numbers in the array are odd
        
        
        // Q8.  Write and test a method that given a 2D array of integers,
        //  returns an array in which each index stores the sum of
        //  the odd values in the corresponding row of the 2D array
        
        
        
        
        
        
    }
    
}