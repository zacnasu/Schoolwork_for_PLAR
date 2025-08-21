/*
 * A6Tester.java
 *
 * A test program for implementations of IntegerList.
 *
 */
public class A6Tester {
    
    private static int testPassCount = 0;
    private static int testCount = 0;
    public static int  stressTestSize = 5000;
    
    public static String listType = "linked";
    
    public static void main (String[] args) {
        if (args.length == 1)
            listType = args[0];
        
        try {
            listTestOne();
            listTestTwo();
            testAddRemove();
            testRemoveValue();
            listResizeTest();
            listStressTest();

        } catch (Exception e) {
            
            System.out.println("Your code threw an Exception.");
            System.out.println("Perhaps a stack trace will help:");
            e.printStackTrace(System.out);
        }
        System.out.println("Passed " + testPassCount + "/" + testCount + " tests");
    }
    
    
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
    
    public static IntegerList createList() {
        
        if (listType.equals("array")){
            return new IntegerArrayList();
        } else if (listType.equals("linked")){
            return new IntegerLinkedList();
        } else {
            return new RecursiveIntegerLinkedList();
        }
    }
    
    public static void listTestOne() {
        
        IntegerList l = createList();
        
        displayResults(l.size() == 0, "list constructor, size");
        l.add(4);
        displayResults(l.size() == 1, "list constructor + add 1 element");
        displayResults(l.get(0) == 4, "list get 1 element list");
        displayResults(l.find(4) != -1, "list find of player not there, 1 element list");
    }
    
    public static void listTestTwo() {
        
        System.out.println("List testing: add, get, find, remove");
        
        IntegerList l = createList();
        int p1 = 350;
        int p2 = 400;
        
        l.add(p1);
        l.add(p2);
        displayResults(l.size() == 2, "add + size, 2 element list");
        int p3 = l.get(0);
        int remaining;
        
        if (p3 == p1)
            remaining = p2;
        else
            remaining = p1;
        
        l.remove(0);
        displayResults(l.find(remaining) != -1, "remove + find, 2 element list");
        displayResults(l.size() == 1, "remove + size, 2 element list");
        displayResults(l.get(0) == remaining, "remove + get, 2 element list");
        
        l.remove(0);
        displayResults(l.size() == 0, "remove + size, 1 element list");
    }
    
    public static void testAddRemove() {
        
        System.out.println("PlayerLinkedList add/remove");
        
        IntegerList l = createList();
        
        int    num = 10;
        for (int i = 0; i < 6; i++) {
            l.add(num+i);
        }
        
        displayResults(l.size() == 6, "list constructor + size 6 elements");
        
        int p0 = num+0;
        int p1 = num+1;
        int p2 = num+2;
        int p3 = num+3;
        int p4 = num+4;
        int p5 = num+5;

        String sFwd = l.toString();
        String sRev = l.reverse();
        String foward = p0 + " " + p1 + " " + p2 + " " + p3 + " " + p4 + " "  + p5;
        String reverse = p5 + " " + p4 + " " + p3 + " " + p2 + " "  + p1 + " " + p0;
        displayResults(sFwd.equals(foward), "list forward after add 6 elements");
        displayResults(sRev.equals(reverse), "list reverse after add 6 elements");
        
        l.remove(0);
        displayResults(l.size() == 5, "list constructor + size after remove front");
        displayResults(l.find(p0) == -1, "list constructor + find after remove front");
        
        
        sFwd = l.toString();
        sRev = l.reverse();
        foward = p1 + " " + p2 + " " + p3 + " " + p4 + " "  + p5;
        reverse = p5 + " " + p4 + " " + p3 + " " + p2 + " "  + p1;
        displayResults(sFwd.equals(foward), "list forward after remove front");
        displayResults(sRev.equals(reverse), "list reverse after remove front");
        
        
        l.remove(2);
        displayResults(l.size() == 4, "list constructor + size after remove middle");
        displayResults(l.find(p3) == -1, "list constructor + find after remove middle");
        
        sFwd = l.toString();
        sRev = l.reverse();
        foward = p1 + " " + p2 + " " + p4 + " "  + p5;
        reverse = p5 + " " + p4 + " " + p2 + " "  + p1;
        displayResults(sFwd.equals(foward), "list forward after remove middle");
        displayResults(sRev.equals(reverse), "list reverse after remove middle");
        
        l.remove(3);
        displayResults(l.size() == 3, "list constructor + size after remove end");
        displayResults(l.find(p5) == -1, "list constructor + find after remove end");
        
        sFwd = l.toString();
        sRev = l.reverse();
        foward = p1 + " " + p2 + " " + p4;
        reverse = p4 + " " + p2 + " " + p1;
        displayResults(sFwd.equals(foward), "list forward after remove end");
        displayResults(sRev.equals(reverse), "list forward after remove end");
        
    }
    
    
    public static void testRemoveValue() {
        
        System.out.println("IntegerList removeValue");
        
        IntegerList l = createList();
        l.removeValue(5);
        displayResults(l.size() == 0, "removeValue empty list");
        
        int    num = 10;
        int p0 = num+0;
        int p1 = num+1;
        int p2 = num+2;
        int p3 = num+1;
        int p4 = num+1;
        int p5 = num+4;
        
        l.add(p0);
        l.add(p1);
        l.add(p2);
        l.add(p3);
        l.add(p4);
        l.add(p5);
        
        l.removeValue(p0);
        
        displayResults(l.size() == 5, "removeValue one element at front");
        displayResults(l.find(p0) == -1, "removeValue one element at front");
        
        String sFwd = l.toString();
        String sRev = l.reverse();
        String foward = p1 + " " + p2 + " " + p3 + " " + p4 + " "  + p5;
        String reverse = p5 + " " + p4 + " " + p3 + " " + p2 + " "  + p1;
        displayResults(sFwd.equals(foward), "removeValue one element at front");
        displayResults(sRev.equals(reverse), "removeValue one element at front");
        
        
        l.removeValue(p5);
        displayResults(l.size() == 4, "removeValue one element at end");
        displayResults(l.find(p5) == -1, "removeValue one element at end");
        
        sFwd = l.toString();
        sRev = l.reverse();
        foward = p1 + " " + p2 + " " + p3 + " " + p4;
        reverse = p4 + " " + p3 + " " + p2 + " "  + p1;
        displayResults(sFwd.equals(foward), "removeValue one element at end");
        displayResults(sRev.equals(reverse), "removeValue one element at end");
        
        
        l.removeValue(9);
        
        displayResults(l.size() == 4, "removeValue one element at end");
        displayResults(l.find(p5) == -1, "removeValue one element at end");
        
        sFwd = l.toString();
        sRev = l.reverse();
        foward = p1 + " " + p2 + " " + p3 + " " + p4;
        reverse = p4 + " " + p3 + " " + p2 + " "  + p1;
        displayResults(sFwd.equals(foward), "removeValue one element at end");
        displayResults(sRev.equals(reverse), "removeValue one element at end");
        
        
        l.removeValue(p1);
        
        displayResults(l.size() == 1, "removeValue 3 elements at end");
        displayResults(l.find(p1) == -1, "removeValue 3 elements at end");
        
        sFwd = l.toString();
        sRev = l.reverse();
        foward =  p2 + "";
        reverse = p2 + "";
        displayResults(sFwd.equals(foward), "removeValue 3 elements at end");
        displayResults(sRev.equals(reverse), "removeValue 3 elements at end");
        
    }
    
    public static void listResizeTest() {
        
        System.out.println("List testing resizing, add, find");
        IntegerList l = createList();
        
        int    num = 5500000;
        for (int i = 0; i < 100; i++)
            l.add(num+i);
        
        displayResults(l.size() == 100, "add + size, 100 elements");
        
        boolean passed = true;
        
        for (int i = 99; i >= 0; i--)
            if (l.find(num+i) == -1)
                passed = false;
        
        displayResults(passed, "find, 100 elements");
    }
    
    public static void listStressTest() {
        
        System.out.println("List stress test.");
        
        IntegerList l = createList();
        
        int    num = 5500000;
        for (int i = 0; i < stressTestSize; i++)
            l.add(num+i);
        
        displayResults(l.size() == stressTestSize, "add + size, >10000 elements");
        
        boolean passed = true;
        
        for (int i = (stressTestSize - 1); i >= 0; i--)
            if (l.find(num+i) == -1)
                passed = false;
        
        displayResults(passed, "find, >10000 elements");
        
        passed = true;
        for (int i = (stressTestSize - 1); i > 0; i--) {
            int val = l.get(0);
            l.remove(0);
            
            if (l.find(val) != -1) {
                passed = false;
                break;
            }
            
            if (l.size() != i) {
                passed = false;
                break;
            }
        }
        displayResults(passed, "remove + find, >10000 elements");
        
    }
    
}
