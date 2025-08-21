/*
 * A7Tester.java
 *
 *
 */

import java.io.*;

public class A7Tester
{
    private static int     testCount = 0;
    private static int     testPassCount = 0;
    private static boolean testArraySolution = false;
    private static int     stressTestSize = 200;
    private static String  testDir = "tests/";


    
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



    public static void performSanityCheck() {
        File f = new File(testDir);

        if (!f.exists() || !f.isDirectory()) {
            System.out.println(
                "Before testing can proceed, please create a subdirectory\n"
              + "names tests/ in which are copied the test files (those\n"
              + "beginning with 'maze...') for assignment #3."
            );
            displayResults(false, "test directory creation");
        }
    }


    public static void testBasicStack() {
        System.out.println("testBasicStack: start");

        Stack<Integer> s;
        int subtestSize;
        boolean subtestResult;

        s = new StackRefBased<Integer>();
        displayResults(s.isEmpty(), "isEmpty on empty stack");
        displayResults(s.size() == 0, "size on empty stack");

        s = new StackRefBased<Integer>();
        s.push(22);
        displayResults(!s.isEmpty(), "isEmpty - stack with one element");
        displayResults(s.size() == 1, "size - stack with one element");
       
        try { 
            s = new StackRefBased<Integer>();
            subtestSize = 10; 
            for (int i = 0; i < subtestSize; i++) {
                s.push(i);     
            }
            displayResults(!s.isEmpty(), "isEmpty - stack with multiple elements");
            displayResults(s.size() == subtestSize, "size - stack with multiple elements");
            
            subtestResult = true;
            for (int i = subtestSize - 1; i >= 0; i--) {
                Integer ii = s.pop();
                subtestResult = subtestResult && (ii == i);
            }
            displayResults(subtestResult, "pop - stack with multiple elements");
            displayResults(s.isEmpty(), "isEmpty - after pop");
            displayResults(s.size() == 0, "size - after pop");
        } 
        catch (StackEmptyException see) {
            displayResults(false, "exception thrown when it shouldn't be");
        }

        try {
            s = new StackRefBased<Integer>();
            s.push(33);
            s.peek();
            displayResults( !s.isEmpty(), "push/peek + isEmpty - stack with one elements");
            displayResults(s.size() == 1, "push/peek + size - stack with one elements");
        } 
        catch (StackEmptyException see) {
            displayResults(false, "exception thrown when it shouldn't be");
        }

        try {
            s = new StackRefBased<Integer>();
            s.pop();
            displayResults( false, "shouldn't get here - exception should be thrown");
            s.peek();
            displayResults( false, "shouldn't get here - exception should be thrown");
        } 
        catch (StackEmptyException see) {
            displayResults(true, "exception should have been thrown");
        }

        try { 
            s = new StackRefBased<Integer>();
            subtestSize = 10; 
            for (int i = 0; i < subtestSize; i++) {
                s.push(i);     
            }
            displayResults(!s.isEmpty(), "push + isEmpty");
            displayResults(s.size() == subtestSize, "push + size");
            subtestResult = true; 
            for (int i = subtestSize - 1; i >= 0; i--) {
                Integer ii = s.peek();
                subtestResult = subtestResult && (ii == (subtestSize - 1));
            }
            displayResults(subtestResult, "push + peek");
            displayResults(!s.isEmpty(), "push + peek + isEmpty");
            displayResults(s.size() == subtestSize, "push + peek + size");
        } 
        catch (StackEmptyException see) {
            displayResults(false, "exception thrown when it shouldn't be");
        }

        s = new StackRefBased<Integer>();
        subtestSize = 10; 
        for (int i = 0; i < subtestSize; i++) {
            s.push(i);     
        }
        s.makeEmpty();
        displayResults( s.isEmpty(), "makeEmpty + isEmpty - stack with multiple elements");

        s = new StackRefBased<Integer>();
        subtestSize = 10; 
        for (int i = 0; i < subtestSize; i++) {
            s.push(i);     
        }
        s.makeEmpty();
        for (int i = 0; i < subtestSize; i++) {
            s.push(i);     
        }
        displayResults(!s.isEmpty(), "makeEmpty + push + isEmpty - stack with multiple elements");
        displayResults(s.size() == subtestSize, "makeEmpty + push + size - stack with multiple elements");

        try {
            s = new StackRefBased<Integer>();
            subtestSize = 10; 
            for (int i = 0; i < subtestSize; i++) {
                s.push(i);     
            }
            s.makeEmpty();
            for (int i = 0; i < subtestSize; i++) {
                s.push(i);     
            }
            displayResults(s.peek() == (subtestSize - 1), "makeEmpty + push + peek - stack with multiple elements");
            s.pop();
            displayResults(s.peek() == (subtestSize - 2), "makeEmpty + push + pop + peek - stack with multiple elements");
        }
        catch (StackEmptyException see) {
            displayResults(false, "exception thrown when it shouldn't be");
        }

        System.out.println("testBasicStack: end");
        System.out.println();
    }

    
    public static void testBoardStack() {
        System.out.println("testBoardStack: start");

        StackRefBased<Board> s;
        Board bd1, bd2;

        s = new StackRefBased<Board>();
        displayResults(s.isEmpty(), "isEmpty on empty Board stack");

        s = new StackRefBased<Board>();
        boolean[] bd1aValues = {false, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
        bd1 = new Board(bd1aValues, 14);
        s.push(bd1);
        displayResults(!s.isEmpty(), "push + isEmpty");
        displayResults(s.size() == 1, "push + size");

        try {
            s = new StackRefBased<Board>();
            boolean[] bd2aValues = {true, true, true, false, true, true, true, true, true, true, true, true, true, true, false};
            bd1 = new Board(bd2aValues, 13);
            s.push(bd1);
            bd2 = s.pop();
            
            boolean[] bd1bValues = {false, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
            boolean[] bd2bValues = {true, true, true, false, true, true, true, true, true, true, true, true, true, true, false};
            
            displayResults(bd2.equals(new Board(bd2bValues, 13)),
                           "push + pop");
            displayResults(!bd2.equals(new Board(bd1bValues, 14)),
                           "push + pop");
        }
        catch (StackEmptyException see) {
            displayResults(false, "exception thrown when it shouldn't be");
        }

        System.out.println("testBoardStack: end");
        System.out.println();
    }


    public static void testTriangleSolitaireSolver() {
        System.out.println("testTriangleSolitaireSolver: start....");

        performSanityCheck();
        
        testSolved();
        testOneMove();
        testUnsolveable();
        testSomeBacktracking();
        testHalfSolved0();
        testHalfSolved1();
        testTopOpenBoard();
        testOpenPosition7();
        testCenterOpenBoard();
    }
    
    public static void testSolved() {
        System.out.println("testTriangleSolitaireSolver: testUnsolveable");
        
        Board board;
        Stack<Board> result = null;
        Stack<Board> expected = new StackRefBased<Board>();
        
        board = new Board(testDir + "board_solved1.txt");
        result = TriangleSolitaireSolver.solve(board);
        boolean[] b1 = {false,true,false,false,false,false,false,false,false,false,false,false,false,false,false};
        Board bd1 = new Board(b1,1);
        expected.push(bd1);
        
        //        System.out.println("result:\n" + result);
        //        System.out.println("expected:\n" + expected);
        displayResults(result.equals(expected), "solved 1 element at position 1");
        

        board = new Board(testDir + "board_solved9.txt");
        result = TriangleSolitaireSolver.solve(board);
        expected.makeEmpty();
        
        boolean[] b8 = {false,false,false,false,false,false,false,false,true,false,false,false,false,false,false};
        Board bd8 = new Board(b8,1);
        expected.push(bd8);
        
//        System.out.println("result:\n" + result);
//        System.out.println("expected:\n" + expected);
        displayResults(result.equals(expected), "solved 1 element at position 8");
        
        
    }
    
    public static void testOneMove() {
        System.out.println("testTriangleSolitaireSolver: testOneMove");
        Board board;
        Stack<Board> result = null;
        Stack<Board> expected = new StackRefBased<Board>();
        
        board = new Board(testDir + "board_noBacktracking.txt");
        result = TriangleSolitaireSolver.solve(board);
        
        boolean[] b2 = {true,true,false,false,false,false,false,false,false,false,false,false,false,false,false};
        Board bd2 = new Board(b2,2);
        expected.push(bd2);
        boolean[] b1 = {false,false,false,true,false,false,false,false,false,false,false,false,false,false,false};
        Board bd1 = new Board(b1,1);
        expected.push(bd1);
        
        //        System.out.println("result:\n" + result);
        //        System.out.println("expected:\n" + expected);
        displayResults(result.equals(expected), "testOneMove");
    }
    
    public static void testUnsolveable() {
        System.out.println("testTriangleSolitaireSolver: testUnsolveable");
        
        Board board;
        Stack<Board> result = null;
        Stack<Board> expected = new StackRefBased<Board>();
        
        board = new Board(testDir + "board_unsolveable_noMoves.txt");
        result = TriangleSolitaireSolver.solve(board);
        //        System.out.println("result:\n" + result);
        //        System.out.println("expected:\n" + expected);
        displayResults(result.equals(expected), "unsolveable no moves");
        
        board = new Board(testDir + "board_unsolveable_backtracking1.txt");
        result = TriangleSolitaireSolver.solve(board);
        //        System.out.println("result:\n" + result);
        //        System.out.println("expected:\n" + expected);
        displayResults(result.equals(expected), "unsolveable one level of backtracking");
        
        board = new Board(testDir + "board_unsolveable_backtracking2.txt");
        result = TriangleSolitaireSolver.solve(board);
        //        System.out.println("result:\n" + result);
        //        System.out.println("expected:\n" + expected);
        displayResults(result.equals(expected), "unsolveable more backtracking");
        
        board = new Board(testDir + "board_unsolveable_backtracking3.txt");
        result = TriangleSolitaireSolver.solve(board);
        //        System.out.println("result:\n" + result);
        //        System.out.println("expected:\n" + expected);
        displayResults(result.equals(expected), "unsolveable more backtracking");
        
    }
    
    public static void testSomeBacktracking() {
        System.out.println("testTriangleSolitaireSolver: testSomeBacktracking");
        Board board;
        Stack<Board> result = null;
        Stack<Board> expected = new StackRefBased<Board>();
        
        board = new Board(testDir + "board_someBacktracking.txt");
        result = TriangleSolitaireSolver.solve(board);
        
        boolean[] b4 = {false,false,false,false,false,true,false,false,true,false,true,false,false,true,false};
        Board bd4 = new Board(b4,4);
        expected.push(bd4);
        boolean[] b3 = {false,false,false,false,false,false,false,false,false,false,true,false,true,true,false};
        Board bd3 = new Board(b3,3);
        expected.push(bd3);
        boolean[] b2 = {false,false,false,false,false,false,false,false,false,false,true,true,false,false,false};
        Board bd2 = new Board(b2,2);
        expected.push(bd2);
        boolean[] b1 = {false,false,false,false,false,false,false,false,false,false,false,false,true,false,false};
        Board bd1 = new Board(b1,1);
        expected.push(bd1);
        
        //        System.out.println("result:\n" + result);
        //        System.out.println("expected:\n" + expected);
        displayResults(result.equals(expected), "some backtracking");
    }
    
    public static void testHalfSolved0() {
        System.out.println("testTriangleSolitaireSolver: testHalfSolved0");
        
        Board board;
        Stack<Board> result = null;
        Stack<Board> expected = new StackRefBased<Board>();
        
        board = new Board(testDir + "board_halfSolved0.txt");
        result = TriangleSolitaireSolver.solve(board);
        
        expected = new StackRefBased<Board>();

        boolean[] b9 = {true,true,true,true,false,true,false,false,true,false,false,true,true,true,false};
        Board bd9 = new Board(b9,9);
        expected.push(bd9);
        boolean[] b8 = {true,false,true,false,false,true,true,false,true,false,false,true,true,true,false};
        Board bd8 = new Board(b8,8);
        expected.push(bd8);
        boolean[] b7 = {true,false,false,false,false,false,true,false,true,true,false,true,true,true,false};
        Board bd7 = new Board(b7,7);
        expected.push(bd7);
        boolean[] b6 = {true,false,false,false,false,false,true,false,true,true,true,false,false,true,false};
        Board bd6 = new Board(b6,6);
        expected.push(bd6);
        boolean[] b5 = {true,false,false,true,false,false,false,false,true,true,false,false,false,true,false};
        Board bd5 = new Board(b5,5);
        expected.push(bd5);
        boolean[] b4 = {true,false,false,true,true,false,false,false,false,true,false,false,false,false,false};
        Board bd4 = new Board(b4,4);
        expected.push(bd4);
        boolean[] b3 = {true,false,false,false,false,true,false,false,false,true,false,false,false,false,false};
        Board bd3 = new Board(b3,3);
        expected.push(bd3);
        boolean[] b2 = {true,false,true,false,false,false,false,false,false,false,false,false,false,false,false};
        Board bd2 = new Board(b2,2);
        expected.push(bd2);
        boolean[] b1 = {false,false,false,false,false,true,false,false,false,false,false,false,false,false,false};
        Board bd1 = new Board(b1,1);
        expected.push(bd1);
        
        //        System.out.println("result:\n" + result);
        //        System.out.println("expected:\n" + expected);
        displayResults(result.equals(expected), "half solved 0");
        
    }
    
    public static void testHalfSolved1() {
        System.out.println("testTriangleSolitaireSolver: testHalfSolved1");
        
        Board board;
        Stack<Board> result = null;
        Stack<Board> expected = new StackRefBased<Board>();
        
        board = new Board(testDir + "board_halfSolved1.txt");
        result = TriangleSolitaireSolver.solve(board);
        
        expected = new StackRefBased<Board>();
        
        boolean[] b8 = {false,true,true,false,true,false,false,false,true,false,true,false,true,true,true};
        Board bd8 = new Board(b8,8);
        expected.push(bd8);
        boolean[] b7 = {false,true,true,false,true,true,false,false,false,false,true,false,false,true,true};
        Board bd7 = new Board(b7,7);
        expected.push(bd7);
        boolean[] b6 = {false,false,true,false,false,true,false,false,true,false,true,false,false,true,true};
        Board bd6 = new Board(b6,6);
        expected.push(bd6);
        boolean[] b5 = {false,false,false,false,false,false,false,false,true,true,true,false,false,true,true};
        Board bd5 = new Board(b5,5);
        expected.push(bd5);
        boolean[] b4 = {false,false,false,false,false,true,false,false,true,false,true,false,false,true,false};
        Board bd4 = new Board(b4,4);
        expected.push(bd4);
        boolean[] b3 = {false,false,false,false,false,false,false,false,false,false,true,false,true,true,false};
        Board bd3 = new Board(b3,3);
        expected.push(bd3);
        boolean[] b2 = {false,false,false,false,false,false,false,false,false,false,true,true,false,false,false};
        Board bd2 = new Board(b2,2);
        expected.push(bd2);
        boolean[] b1 = {false,false,false,false,false,false,false,false,false,false,false,false,true,false,false};
        Board bd1 = new Board(b1,1);
        expected.push(bd1);
        
        //        System.out.println("result:\n" + result);
        //        System.out.println("expected:\n" + expected);
        displayResults(result.equals(expected), "half solved 1");
        
    }
    public static void testOpenPosition7() {
        System.out.println("testTriangleSolitaireSolver: testOpenPosition7");
        
        Board board;
        Stack<Board> result = null;
        Stack<Board> expected = new StackRefBased<Board>();
        
        board = new Board(testDir + "board_open7.txt");
        result = TriangleSolitaireSolver.solve(board);
        
        expected = new StackRefBased<Board>();
        boolean[] b14 = {true,true,true,true,true,true,true,false,true,true,true,true,true,true,true};
        Board bd14 = new Board(b14,14);
        expected.push(bd14);
        boolean[] b13 = {true,true,false,true,false,true,true,true,true,true,true,true,true,true,true};
        Board bd13 = new Board(b13,13);
        expected.push(bd13);
        boolean[] b12 = {true,true,true,true,false,false,true,true,true,false,true,true,true,true,true};
        Board bd12 = new Board(b12,12);
        expected.push(bd12);
        boolean[] b11 = {true,true,true,true,false,true,true,true,false,false,true,true,false,true,true};
        Board bd11 = new Board(b11,11);
        expected.push(bd11);
        boolean[] b10 = {true,true,false,true,false,false,true,true,false,true,true,true,false,true,true};
        Board bd10 = new Board(b10,10);
        expected.push(bd10);
        boolean[] b9 = {true,true,false,true,false,false,false,false,true,true,true,true,false,true,true};
        Board bd9 = new Board(b9,9);
        expected.push(bd9);
        boolean[] b8 = {true,false,false,false,false,false,true,false,true,true,true,true,false,true,true};
        Board bd8 = new Board(b8,8);
        expected.push(bd8);
        boolean[] b7 = {true,false,false,true,false,false,false,false,true,true,false,true,false,true,true};
        Board bd7 = new Board(b7,7);
        expected.push(bd7);
        boolean[] b6 = {true,false,false,true,false,false,false,false,true,true,false,true,true,false,false};
        Board bd6 = new Board(b6,6);
        expected.push(bd6);
        boolean[] b5 = {true,false,false,true,false,false,false,false,true,true,false,false,false,true,false};
        Board bd5 = new Board(b5,5);
        expected.push(bd5);
        boolean[] b4 = {true,false,false,true,true,false,false,false,false,true,false,false,false,false,false};
        Board bd4 = new Board(b4,4);
        expected.push(bd4);
        boolean[] b3 = {true,false,false,false,false,true,false,false,false,true,false,false,false,false,false};
        Board bd3 = new Board(b3,3);
        expected.push(bd3);
        boolean[] b2 = {true,false,true,false,false,false,false,false,false,false,false,false,false,false,false};
        Board bd2 = new Board(b2,2);
        expected.push(bd2);
        boolean[] b1 = {false,false,false,false,false,true,false,false,false,false,false,false,false,false,false};
        Board bd1 = new Board(b1,1);
        expected.push(bd1);
        
        //        System.out.println("result:\n" + result);
        //        System.out.println("expected:\n" + expected);
        displayResults(result.equals(expected), "board_open7");
        
    }
    
    public static void testTopOpenBoard() {
        System.out.println("testTriangleSolitaireSolver: testTopOpenBoard");
            
        Board board;
        Stack<Board> result = null;
        Stack<Board> expected = new StackRefBased<Board>();
        
        board = new Board(testDir + "board_topOpen.txt");
        result = TriangleSolitaireSolver.solve(board);
        
        expected = new StackRefBased<Board>();
        boolean[] b14 = {false,true,true,true,true,true,true,true,true,true,true,true,true,true,true};
        Board bd14 = new Board(b14,14);
        expected.push(bd14);
        boolean[] b13 = {true,false,true,false,true,true,true,true,true,true,true,true,true,true,true};
        Board bd13 = new Board(b13,13);
        expected.push(bd13);
        boolean[] b12 = {true,false,true,true,false,false,true,true,true,true,true,true,true,true,true};
        Board bd12 = new Board(b12,12);
        expected.push(bd12);
        boolean[] b11 = {false,false,false,true,false,true,true,true,true,true,true,true,true,true,true};
        Board bd11 = new Board(b11,11);
        expected.push(bd11);
        boolean[] b10 = {false,true,false,false,false,true,false,true,true,true,true,true,true,true,true};
        Board bd10 = new Board(b10,10);
        expected.push(bd10);
        boolean[] b9 = {false,true,true,false,false,false,false,true,true,false,true,true,true,true,true};
        Board bd9 = new Board(b9,9);
        expected.push(bd9);
        boolean[] b8 = {false,true,true,false,true,false,false,false,true,false,true,false,true,true,true};
        Board bd8 = new Board(b8,8);
        expected.push(bd8);
        boolean[] b7 = {false,true,true,false,true,true,false,false,false,false,true,false,false,true,true};
        Board bd7 = new Board(b7,7);
        expected.push(bd7);
        boolean[] b6 = {false,false,true,false,false,true,false,false,true,false,true,false,false,true,true};
        Board bd6 = new Board(b6,6);
        expected.push(bd6);
        boolean[] b5 = {false,false,false,false,false,false,false,false,true,true,true,false,false,true,true};
        Board bd5 = new Board(b5,5);
        expected.push(bd5);
        boolean[] b4 = {false,false,false,false,false,true,false,false,true,false,true,false,false,true,false};
        Board bd4 = new Board(b4,4);
        expected.push(bd4);
        boolean[] b3 = {false,false,false,false,false,false,false,false,false,false,true,false,true,true,false};
        Board bd3 = new Board(b3,3);
        expected.push(bd3);
        boolean[] b2 = {false,false,false,false,false,false,false,false,false,false,true,true,false,false,false};
        Board bd2 = new Board(b2,2);
        expected.push(bd2);
        boolean[] b1 = {false,false,false,false,false,false,false,false,false,false,false,false,true,false,false};
        Board bd1 = new Board(b1,1);
        expected.push(bd1);
        
        //        System.out.println("result:\n" + result);
        //        System.out.println("expected:\n" + expected);
        displayResults(result.equals(expected), "board_topOpen.txt");
    
    }
    
    public static void testCenterOpenBoard() {
        System.out.println("testTriangleSolitaireSolver: testCenterOpenBoard");
        
        Board board;
        Stack<Board> result = null;
        Stack<Board> expected = new StackRefBased<Board>();
        
        board = new Board(testDir + "board_centerOpen.txt");
        result = TriangleSolitaireSolver.solve(board);
        
        expected = new StackRefBased<Board>();
        boolean[] b14 = {true,true,true,true,false,true,true,true,true,true,true,true,true,true,true};
        Board bd14 = new Board(b14,14);
        expected.push(bd14);
        boolean[] b13 = {true,true,true,true,true,true,true,false,true,true,true,false,true,true,true};
        Board bd13 = new Board(b13,13);
        expected.push(bd13);
        boolean[] b12 = {true,true,true,true,true,true,true,true,false,false,true,false,true,true,true};
        Board bd12 = new Board(b12,12);
        expected.push(bd12);
        boolean[] b11 = {true,false,true,true,false,true,true,true,true,false,true,false,true,true,true};
        Board bd11 = new Board(b11,11);
        expected.push(bd11);
        boolean[] b10 = {true,false,false,true,false,false,true,true,true,true,true,false,true,true,true};
        Board bd10 = new Board(b10,10);
        expected.push(bd10);
        boolean[] b9 = {true,true,false,false,false,false,false,true,true,true,true,false,true,true,true};
        Board bd9 = new Board(b9,9);
        expected.push(bd9);
        boolean[] b8 = {false,false,false,true,false,false,false,true,true,true,true,false,true,true,true};
        Board bd8 = new Board(b8,8);
        expected.push(bd8);
        boolean[] b7 = {false,false,false,true,false,false,false,true,true,true,true,true,false,false,true};
        Board bd7 = new Board(b7,7);
        expected.push(bd7);
        boolean[] b6 = {false,false,false,false,false,false,false,false,true,true,true,true,true,false,true};
        Board bd6 = new Board(b6,6);
        expected.push(bd6);
        boolean[] b5 = {false,false,false,false,false,false,false,false,true,true,true,false,false,true,true};
        Board bd5 = new Board(b5,5);
        expected.push(bd5);
        boolean[] b4 = {false,false,false,false,false,true,false,false,true,false,true,false,false,true,false};
        Board bd4 = new Board(b4,4);
        expected.push(bd4);
        boolean[] b3 = {false,false,false,false,false,false,false,false,false,false,true,false,true,true,false};
        Board bd3 = new Board(b3,3);
        expected.push(bd3);
        boolean[] b2 = {false,false,false,false,false,false,false,false,false,false,true,true,false,false,false};
        Board bd2 = new Board(b2,2);
        expected.push(bd2);
        boolean[] b1 = {false,false,false,false,false,false,false,false,false,false,false,false,true,false,false};
        Board bd1 = new Board(b1,1);
        expected.push(bd1);
        
        //        System.out.println("result:\n" + result);
        //        System.out.println("expected:\n" + expected);
        displayResults(result.equals(expected), "board_centerOpen.txt");
    }

	public static void main (String[] args)
	{
		try
		{
            testBasicStack();
            testBoardStack();
            testTriangleSolitaireSolver();
			System.out.println("done");
		}
		catch (Exception e)
		{
			System.out.println("Your code threw an Exception.");
			System.out.println("Perhaps a stack trace will help:");
			e.printStackTrace(System.out);
		}
        
        System.out.println ("Passed " + testPassCount + "/" + testCount + "tests");
        
	}
}
