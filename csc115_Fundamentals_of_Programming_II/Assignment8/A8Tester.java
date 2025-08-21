/*
 * A8Tester.java
 *
 * A test program for Assignment 8.
 *
 */

import java.util.Random;

public class A8Tester {
    private static int testPassCount = 0;
    private static int testCount = 0;
    public static boolean  	testHeapSolution = true;
    
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
    
    
    public static void testOne () {
        PriorityQueue 	q = createNewPriorityQueue();
        
        System.out.println("Basic testing of size, isEmpty");
        displayResults (q.size() == 0, "size on empty Q");
        
        displayResults (q.isEmpty(), "isEmpty on empty Q");
        
        q.insert(10);
        displayResults (q.size() == 1, "size on 1 element Q");
        displayResults (!q.isEmpty(), "isEmpty on 1 element Q");
        
        q.insert(9);
        displayResults (q.size() == 2, "size on 2 element Q");
        
        q.insert(7);
        displayResults (q.size() == 3, "size on 3 element Q");
        
    }
    
    
    public static void testTwo()
    {
        PriorityQueue q = createNewPriorityQueue();
        
        System.out.println("Basic testing of removeHigh");
        q.insert(10);
        q.insert(9);
        q.insert(8);
        
        int result;
        result = ((Integer)q.removeHigh()).intValue();
               System.out.println("res: " + result + ":" + q);
        displayResults(result == 10, "remove on multiple element Q");
        displayResults(q.size() == 2, "remove + size on multiple element Q");
        
        result = ((Integer)q.removeHigh()).intValue();
               System.out.println("res: " + result + ":" + q);
        displayResults(result == 9, "remove on multiple element Q");
        displayResults(q.size() == 1, "remove + size on multiple element Q");
        
        result = ((Integer)q.removeHigh()).intValue();
        displayResults(result == 8, "remove + size on 1 element Q");
        displayResults(q.isEmpty(), "remove + isEmpty on 1 element Q");
        
        q = createNewPriorityQueue();
        q.insert(1);
        q.insert(2);
        q.insert(3);
        
        result = ((Integer)q.removeHigh()).intValue();
        displayResults(result == 3, "insert + remove on multiple element Q");
        displayResults(q.size() == 2, "insert + remove + size on multiple element Q");
        
        result = ((Integer)q.removeHigh()).intValue();
        displayResults(result == 2, "insert + remove on multiple element Q");
        displayResults(q.size() == 1, "insert + remove + size on multiple element Q");
        
        result = ((Integer)q.removeHigh()).intValue();
        displayResults(result == 1, "insert + remove on 1 element Q");
        displayResults(q.isEmpty(), "insert + remove + size on 1 element Q");
    }
    public static void testExceptions()
    {
        PriorityQueue q = createNewPriorityQueue(2);
        
        System.out.println("Testing of exceptions");
        
        try {
            q.removeHigh();
            displayResults(false, "exception should have been thrown");
        } catch (HeapFullException e) {
            displayResults(false, "different exception should have been thrown");
        } catch (HeapEmptyException e) {
            displayResults(true, "HeapEmptyException should be thrown");
        }
        q.insert(10);
        q.insert(9);
        try {
            q.insert(8);
            if(testHeapSolution)
                displayResults(false, "exception should have been thrown in heap solution");
            else
                displayResults(true, "exception should not have been thrown in linked version");
        } catch (HeapEmptyException e) {
            displayResults(false, "different exception should have been thrown");
        } catch (HeapFullException e) {
            displayResults(true, "HeapFullException should be thrown");
        }
        
    }
    public static void testThree() {
        PriorityQueue q = createNewPriorityQueue();
        String result;
        
        System.out.println("Testing of removeHigh with Strings");
        q.insert("abc");
        q.insert("def");
        q.insert("ghi");
        
        result = ((String)q.removeHigh());
        displayResults(result.equals("ghi"), "insert/remove Strings");
        displayResults(q.size() == 2, "insert/remove Strings");
        
        result = ((String)q.removeHigh());
        displayResults(result.equals("def"), "insert/remove Strings");
        displayResults(q.size() == 1, "insert/remove Strings");
        
        result = ((String)q.removeHigh());
        displayResults(result.equals("abc"), "insert/remove Strings");
        displayResults(q.isEmpty(), "insert/remove Strings");
        
        q = createNewPriorityQueue();
        q.insert("ghi");
        q.insert("def");
        q.insert("abc");
        
        result = ((String)q.removeHigh());
        displayResults(result.equals("ghi"), "insert/remove Strings");
        displayResults(q.size() == 2, "insert/remove + size Strings");
        
        result = ((String)q.removeHigh());
        displayResults(result.equals("def"), "insert/remove Strings");
        displayResults(q.size() == 1, "insert/remove + size Strings");
        
        result = ((String)q.removeHigh());
        displayResults(result.equals("abc"), "insert/remove Strings");
        displayResults(q.isEmpty(), "insert/remove + isEmpty Strings");
        
    }
    
    public static void testFour()
    {
        PriorityQueue q = createNewPriorityQueue();
        int result;
        
        System.out.println("Testing duplicates.");
        q.insert(4);
        q.insert(5);
        q.insert(5);
        q.insert(1);
        q.insert(10);
                System.out.println("should be 10 5 5 4 1: " + q);
        
        result = ((Integer)q.removeHigh()).intValue();
        displayResults(result == 10, "add duplicates + remove single");
        
               System.out.println("should be 5 5 4 1: " + q);
        q.insert(4);
        q.insert(1);
        q.insert(4);
        q.insert(4);
                System.out.println("should be 5 5 4 4 4 4 1 1 : " + q);
        
        q.insert(10);
        q.insert(5);
        q.insert(10);
        q.insert(1);
                System.out.println("should be 10 10 5 5 5 4 4 4 4 1 1 1: " + q);
        
        result = ((Integer)q.removeHigh()).intValue();
        displayResults(result == 10, "add duplicates + remove duplicates");
        result = ((Integer)q.removeHigh()).intValue();
        displayResults(result == 10, "add duplicates + remove duplicates");
        
        result = ((Integer)q.removeHigh()).intValue();
        displayResults(result == 5, "add duplicates + remove duplicates");
        result = ((Integer)q.removeHigh()).intValue();
        displayResults(result == 5, "add duplicates + remove duplicates");
        result = ((Integer)q.removeHigh()).intValue();
        displayResults(result == 5, "add duplicates + remove duplicates");
        
        result = ((Integer)q.removeHigh()).intValue();
        displayResults(result == 4, "add duplicates + remove duplicates");
        result = ((Integer)q.removeHigh()).intValue();
        displayResults(result == 4, "add duplicates + remove duplicates");
        result = ((Integer)q.removeHigh()).intValue();
        displayResults(result == 4, "add duplicates + remove duplicates");
        result = ((Integer)q.removeHigh()).intValue();
        displayResults(result == 4, "add duplicates + remove duplicates");
        
        
        result = ((Integer)q.removeHigh()).intValue();
        displayResults(result == 1, "add duplicates + remove duplicates");
        result = ((Integer)q.removeHigh()).intValue();
        displayResults(result == 1, "add duplicates + remove duplicates");
        result = ((Integer)q.removeHigh()).intValue();
        displayResults(result == 1, "add duplicates + remove duplicates");
        
        displayResults(q.isEmpty(), "insert/remove + isEmpty");
    }
    
    
    public static void testFive()
    {
        PriorityQueue q = createNewPriorityQueue();
        int result;
        System.out.println("Testing insert mixed with removeHigh.");
        
        q.insert(7);
        q.insert(10);
        q.insert(5);
        q.insert(2);
                System.out.println("10 7 5 2: " + q);
        
        result = ((Integer)q.removeHigh()).intValue();
        displayResults( result == 10, "inserts + remove");
                System.out.println("7 5 2: " + q);
        q.insert(4);
               System.out.println("7 4 5 2: " + q);
        
        result = ((Integer)q.removeHigh()).intValue();
        displayResults(result == 7, "inserts + remove + insert + remove");
                System.out.println("5 4 2: " + q);
        q.insert(1);
        q.insert(2);
        q.insert(3);
                System.out.println("5 4 3 1 2 2: " + q);
        
        q.insert(11);
                System.out.println("11 4 5 1 2 2 3: " + q);
        
        result = ((Integer)q.removeHigh()).intValue();
        displayResults(result == 11, "inserts + remove");
               System.out.println("5 4 3 1 2 2: " + q);
        
        result = ((Integer)q.removeHigh()).intValue();
        displayResults(result == 5, "inserts + remove");
                System.out.println("4 2 3 1 2 : " + q);
        
        result = ((Integer)q.removeHigh()).intValue();
        displayResults(result == 4, "inserts + remove");
               System.out.println("3 2 2 1 : " + q);
        
        result = ((Integer)q.removeHigh()).intValue();
        displayResults(result == 3, "inserts + remove");
               System.out.println("2 1 2 : " + q);
        
        q.insert(6);
                System.out.println("6 2 2 1: " + q);
        
        result = ((Integer)q.removeHigh()).intValue();
        displayResults(result == 6, "inserts + remove");
        displayResults(q.size() == 3, "inserts + remove + size");
                System.out.println("2 1 2 : " + q);
    }
    
    public static boolean testRandomArray (int count)
    {
        // These tests are effectively sorting the random values
        //
        // For the heap, this is O (n log n)
        // For the linked list, this is O (n^2)
        PriorityQueue q = createNewPriorityQueue(count);
        
        System.out.println("Testing size: " + count);
        Random r = new Random();
        
        for ( int i = 0; i < count; i++ ) {
            int val = r.nextInt(1000000);
            q.insert (val);
        }
        
        int oldVal = 1000000; //biggest possible value val could be
        int i = 0;
        while (!q.isEmpty()) {
            int val = (int)((Integer)q.removeHigh()).intValue(); // or a bug
            if ( val > oldVal )
                return false;
            oldVal = val;
            i++;
        }
        return true;
        
    }
    
    public static void stressTest() {
        System.out.println("Stress Tests.");
        displayResults(testRandomArray(100), "inserts + removes");
        displayResults(testRandomArray(10000), "inserts + removes");
        displayResults(testRandomArray(100000), "inserts + removes");
        
        //This takes too long using the linked list.
        if (testHeapSolution)
            displayResults(testRandomArray(1000000), "inserts + removes");
    }
    
    public static PriorityQueue createNewPriorityQueue()  {
        if (testHeapSolution) {
            return new HeapPriorityQueue();
        }
        else {
            return new LinkedPriorityQueue();
        }
    }
    
    public static PriorityQueue createNewPriorityQueue(int size)  {
        if (testHeapSolution) {
            return new HeapPriorityQueue(size);
        }
        else {
            return new LinkedPriorityQueue();
        }
    }
    
    public static void testPatient ()
    {
        System.out.println("Testing Patient creation, compareTo and equals.");
        Patient p1 = new Patient(3, "Lady Gaga", 123, "torn voice box" );
        Patient p2 = new Patient(8, "Keith Richards", 456, "failing liver" );
        Patient p3 = new Patient(3, "Taylor Swift", 789, "broken heart" );
        Patient p4 = new Patient(5, "T. Swift", 789, "broken nose" );
        
        displayResults(p1.compareTo(p2) < 0, "testing Patient compareTo");
        displayResults(p2.compareTo(p1) > 0, "testing Patient compareTo");
        displayResults(p1.compareTo(p1) == 0, "testing Patient compareTo");
        displayResults(p3.equals(p4), "testing Patient equals");
        
    }
    
    public static void testTriage()
    {
        
        System.out.println("Testing adding/removing patients from Triage.");
        Patient p1 = new Patient(3, "Lady Gaga", 123, "torn voice box" );
        Patient p2 = new Patient(8, "Keith Richards", 456, "failing liver" );
        Patient p3 = new Patient(2, "Taylor Swift", 789, "broken heart" );
        Patient p4 = new Patient(7, "Kevin Bacon", 356, "too many movies" );
        Patient p5 = new Patient(6, "Tom Cruise", 321, "depression" );
        Patient p6 = new Patient(10, "Mick Jagger", 678, "heart attack" );
        
        Triage er = new Triage();
        displayResults(er.numPatientsWaiting() == 0, "testing Triage constructor + numPatientsWaiting");
        
        er.addPatient(p1);
        er.addPatient(p2);
        er.addPatient(p3);
        er.addPatient(p4);
        er.addPatient(p5);
        
        displayResults(er.numPatientsWaiting() == 5, "testing Triage addPatient + numPatientsWaiting");
        
        displayResults(p2.equals(er.nextPatient()), "testing Triage addPatient + numPatientsWaiting");
        er.addPatient(p6);
        displayResults(er.numPatientsWaiting() == 5, "testing Triage addPatient + numPatientsWaiting");
        
        displayResults(p6.equals(er.nextPatient()), "testing Triage addPatient + nextPatient");
        displayResults(p4.equals(er.nextPatient()), "testing Triage addPatient + nextPatient");
        displayResults(er.numPatientsWaiting() == 3, "testing Triage addPatient + numPatientsWaiting");
        
        displayResults(p5.equals(er.nextPatient()), "testing Triage addPatient + nextPatient");
        displayResults(p1.equals(er.nextPatient()), "testing Triage addPatient + nextPatient");
        displayResults(p3.equals(er.nextPatient()), "testing Triage addPatient + nextPatient");
        displayResults(er.numPatientsWaiting() == 0, "testing Triage addPatient + numPatientsWaiting");
        displayResults(er.nextPatient() == null, "testing Triage nextPatient - no more patients");
        
        Triage erSmall = new Triage(2);
        erSmall.addPatient(p1);
        erSmall.addPatient(p2);
        
        try {
            erSmall.addPatient(p3);
            displayResults(true, "testing Triage addPatient to full Triage - should get here without exception");
        } catch (HeapFullException e) {
            displayResults(false, "testing Triage addPatient to full Triage - should not get here");
        }
    }
    
    public static void main (String[] args) {
        if (args.length != 0 && args[0].equals("linked")) {
            testHeapSolution=false;
        }
        
        System.out.println("Testing " + (testHeapSolution ? "Heap" : "Linked" ) + " implementation.");
        testOne();
        testTwo();
        testExceptions();
        testThree();
        testFour();
        testFive();
        stressTest();
        
        System.out.println("Testing Application using Priority Queue.");
        testPatient();
        testTriage();
        
        System.out.println("Passed " + testPassCount + "/" + testCount + " tests");
    }
}
