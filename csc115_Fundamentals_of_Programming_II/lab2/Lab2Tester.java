/*
 * Lab2Tester.java
 *
 * A tester for the methods in Lab2.java
 *
 */
import java.util.Arrays;

public class Lab2Tester {
    
    private static int testPassCount = 0;
    private static int testCount = 0;
    
    
    // for approximate comparison of floating point numbers
    private static final double THRESHOLD = 0.01;
    
    public static void main(String[] args) {
        
        testGetHigherGradeStudent();
        testIsGradeAbove();
        testGetClasslist();
        testCountAbove();
        testGetClassAverage();
        testRegisterStudent();
        
        System.out.println("Passed " + testPassCount + "/" + testCount + " tests");
    }
    
    public static void displayResults (boolean passed, String testName)
    {
        /* There is some magic going on here getting the line number
         * Borrowed from:
         * http://blog.taragana.com/index.php/archive/core-java-how-to-get-java-source-code-line-number-file-name-in-code/
         */
        
        testCount++;
        if (passed)
        {
            System.out.println ("Passed test: " + testName);
            testPassCount++;
        }
        else
        {
            System.out.println ("Failed test: " + testName + " at line "
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        
    }
    
    public static void testGetHigherGradeStudent() {
        // ToDo: once you have implemented getHigherGradeStudent in Lab2.java
        //  uncomment the following tests - make sure you understand what they are testing
        
        
         Student s0  = new Student("abc", 50);
         Student s1a = new Student("def", 56);
         Student s1b = new Student("xyz", 56);
         Student s2  = new Student("xyz", 99);
         
         Student result;
         
         result = Lab2.getHigherGradeStudent(s0,s1a);
         //System.out.println("should be  " + s1a + " is " + result);
         displayResults(result.equals(s1a), "testGetHigherGradeStudent");
         
         result = Lab2.getHigherGradeStudent(s1a,s0);
         //System.out.println("should be  " + s1a + " is " + result);
         displayResults(result.equals(s1a), "testGetHigherGradeStudent");
         
         result = Lab2.getHigherGradeStudent(s1b,s1a);
         //System.out.println("should be  " + s1b + " is " + result);
         displayResults(result.equals(s1b) && result == s1b, "testGetHigherGradeStudent");
         
         result = Lab2.getHigherGradeStudent(s1b,s2);
         //System.out.println("should be  " + s2 + " is " + result);
         displayResults(result.equals(s2), "testGetHigherGradeStudent");
         
        
    }
    
    public static void testIsGradeAbove() {
        // ToDo: write tests for Lab2.isGradeAbove
        

    }
    
    public static void testGetClasslist() {
        // ToDo: write tests for Lab2.getClasslist
        
        // NOTE: the Arrays library has been imported for you.
        //  you can use the Arrays.equals method to compare
        //  2 arrays of String objects as String has a equals method
        // The API for Arrays.equals:
        //  equals(Object[] a, Object[] a2)
        //  Returns true if the two specified arrays of Objects are equal to one another.
        
        // ToDo: once you have implemented getClasslist in Lab2.java
        //  uncomment the following test
        // We have gotten you started with some initial test data and one test,
        // but you should consider other cases (empty array, longer array)

        /*
        Student s0  = new Student("abc", 50);
        Student[] students1 = {s0};
        String[] expected1 = {"abc"};

        String[] result;
     
        result = Lab2.getClasslist(students1);
        displayResults(Arrays.equals(result, expected1), "testGetClasslist - 1 elements");
        */
    }
    
    
    public static void testCountAbove() {
        // ToDo: write tests for Lab2.countAbove
        
  
    }
    
 
    
    public static void testGetClassAverage() {
        // ToDo: write tests for Lab2.getClassAverage
        

        
    }
    
    public static void testRegisterStudent() {
        // ToDo: write tests for Lab2.registerStudent
        // HINT: the Student class also has a equals method so you
        //  can use Arrays.equals again to compare 2 Student arrays

        
    }
    
    
}
