//
// A10Tester.java
//
import java.util.*;

public class A10Tester {
    static boolean testLinked = false;
    static boolean testHash = false;
    static boolean testBST = false;
    

    private static int testPassCount = 0;
    private static int testCount = 0;


    public static void displayResults (boolean passed, String testName) {
        /* There is some magic going on here getting the line number
         * Borrowed from:
         * http://blog.taragana.com/index.php/archive/core-java-how-to-get-java-source-code-line-number-file-name-in-code/
         */
        testCount++;
        if (passed)
        {
            System.out.println ("Passed test: " + testCount);
            testPassCount++;
        }
        else
        {
            System.out.println ("Failed test: " + testName + " at line "
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    
    public static void map1_test() {
        System.out.println("**** begin test map size put get");
        Map<String, Integer> d = A10Tester.<String,Integer>createMap();

        displayResults(d.size() == 0 ,"size");
        d.put("abc", 10);

        displayResults(d.size() == 1,"put + size");
        try
        {
            int val = d.get("abc");

            displayResults(val == 10,"put + get");
        }
        catch (KeyNotFoundException e)
        {
            displayResults(false, "put + get");
        }

        d.put("abc", 15);

        displayResults(d.size() == 1,"put update + size");

        try
        {
            int val = d.get("abc");

            displayResults(val == 15,"put + get");
        }
        catch (KeyNotFoundException e)
        {
            displayResults(false, "put + get");
        }

        try
        {
            int val = d.get("def");
            displayResults(false, "put + get should not be there");
            val = val + 1;

        }
        catch (KeyNotFoundException e)
        {
            displayResults(true, "put + get should not be there");
        }
        System.out.println("****** end map size put get\n");

    }

    public static void map2_test() {
        System.out.println("**** begin map stress test");
        Map<Integer, Integer> d = A10Tester.<Integer,Integer>createMap();

        for (int i = 0; i < 10000; i++) {
            d.put(i,i);
        }
        displayResults(d.size() == 10000, "put + size");

        boolean passed = true;
        for (int i = 0; i < 10000; i++) {
            try {
                d.get(i);
            }
            catch (KeyNotFoundException e) {
                passed = false;
                break;
            }
        }
        displayResults(passed, "get");
        System.out.println("****** end map stress test\n");
    }


    public static <K extends Comparable<K>,V> Map<K,V> createMap() {
        if (testLinked) {
            return new LinkedMap<K,V>();
        } else if (testHash) {
            return new HashMap<K,V>();
        } else {
            return new BSTMap<K,V>();
        }
    }
	public static void main (String[] args)	{
		testCount = 0;
        testPassCount = 0;
        
        if (args.length != 0 && args[0].equals("linked")) {
            testLinked = true;
            
        } else if (args.length != 0 && args[0].equals("bst")) {
            testBST = true;
        }
        else {
            testHash = true;
        }
        System.out.println("Testing " +
                           (testLinked ? "LinkedList" :
                            (testHash ? "HashTable" : "BinarySearchTree"))
                           + " implementation.");

		try {
            map1_test();
            map2_test();
        }
		catch (Exception e) {
			System.out.println("Unhandled exception in tester: " + e);
            e.printStackTrace();
		}
	 }
}
