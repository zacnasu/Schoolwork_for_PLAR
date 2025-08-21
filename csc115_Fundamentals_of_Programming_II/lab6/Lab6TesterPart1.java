/*
 * Lab6TesterPart1.java
 */
public class Lab6TesterPart1 {
    
    private static int testPassCount = 0;
    private static int testCount = 0;
    
    public static void main (String[] args) {
        
        try {
            testBasicStack();
            testStackUseFunctions();
            
        } catch (Exception e) {
            
            System.out.println("Your code threw an Exception.");
            System.out.println("Perhaps a stack trace will help:");
            e.printStackTrace(System.out);
        }
        System.out.println("Passed " + testPassCount + "/" + testCount + " tests");
    }
    
    public static void testBasicStack() {
        System.out.println("testBasicStack: start");
        
        Stack s;
        int subtestSize;
        boolean subtestResult = true;
        
        // TESTS for isEmpty and size
        s = new StackArrayBased();
        displayResults(s.isEmpty(), "isEmpty on empty stack");
        displayResults(s.size() == 0, "size on empty stack");
        
        // TESTS for push, isEmpty and size
        s = new StackArrayBased();
        s.push('a');
        displayResults(!s.isEmpty(), "isEmpty - stack with one element");
        displayResults(s.size() == 1, "size - stack with one element");
        
        // TESTS for push, pop isEmpty and size
        s = new StackArrayBased();
        subtestSize = 10;
        for (int i = 0; i < subtestSize; i++) {
            s.push((char)(i+'0'));
        }
        displayResults(!s.isEmpty(), "isEmpty - stack with multiple elements");
        displayResults(s.size() == subtestSize, "size - stack with multiple elements");
        try{
        subtestResult = true;
        for (int i = subtestSize - 1; i >= 0; i--) {
            char ii = s.pop();
            subtestResult = subtestResult && (ii == (char)(i+'0'));
        }
    }catch(StackEmptyException e){
        System.out.println(e);
    }
        displayResults(subtestResult, "pop - stack with multiple elements");
        displayResults(s.isEmpty(), "isEmpty - after pop");
        displayResults(s.size() == 0, "size - after pop");
        
        // Write code to test that exception is thrown when it should be
        // For example:
        //        try {
        //           // do something that you know will throw an exception
        //            // we should not get here - test passes
        //            displayResults(false, "exception thrown when it should not be");
        //        }  catch (StackEmptyException see) {
        //            // we should get here - test passes
        //            displayResults(true, "exception thrown when it should be");
        //        }
        
        
        
        // TESTS for peek
        s = new StackArrayBased();
        subtestSize = 10;
        for (int i = 0; i < subtestSize; i++) {
            s.push((char)(i+'0'));
        }
        displayResults(!s.isEmpty(), "push + isEmpty");
        displayResults(s.size() == subtestSize, "push + size");
        subtestResult = true;
        try{
        for (int i = subtestSize - 1; i >= 0; i--) {
            char ii = s.peek();
            subtestResult = subtestResult && (ii == (char)((subtestSize - 1) + '0'));
        }
    }catch(StackEmptyException e){
        System.out.println(e);
    }
        displayResults(subtestResult, "push + peek");
        displayResults(!s.isEmpty(), "push + peek + isEmpty");
        displayResults(s.size() == subtestSize, "push + peek + size");
        
        // TESTS for makeEmpty + push
        s = new StackArrayBased();
        subtestSize = 10;
        for (int i = 0; i < subtestSize; i++) {
            s.push((char)(i+'0'));
        }
        s.makeEmpty();
        displayResults( s.isEmpty(), "makeEmpty + isEmpty - stack with multiple elements");
        
        s = new StackArrayBased();
        subtestSize = 10;
        for (int i = 0; i < subtestSize; i++) {
            s.push((char)(i+'0'));
        }
        s.makeEmpty();
        for (int i = 0; i < subtestSize; i++) {
            s.push((char)(i+'0'));
        }
        displayResults(!s.isEmpty(), "makeEmpty + push + isEmpty - stack with multiple elements");
        displayResults(s.size() == subtestSize, "makeEmpty + push + size - stack with multiple elements");
        
        // TESTS for makeEmpty + push + peek + pop
        s = new StackArrayBased();
        subtestSize = 10;
        for (int i = 0; i < subtestSize; i++) {
            s.push((char)(i+'0'));
        }
        s.makeEmpty();
        for (int i = 0; i < subtestSize; i++) {
            s.push((char)(i+'0'));
        }
        try{
        displayResults(s.peek() == (char)((subtestSize - 1) + '0'), "makeEmpty + push + peek - stack with multiple elements");
        s.pop();
        displayResults(s.peek() == (char)((subtestSize - 2) + '0'), "makeEmpty + push + pop + peek - stack with multiple elements");
        }catch(StackEmptyException e){
            System.out.print(e);
        }
        
        System.out.println("testBasicStack: end");
        System.out.println();
    }
    
    public static void testStackUseFunctions() {
        
        String resultString;
        String expected;
        
        resultString = reverseString("");
        expected = "";
        displayResults(resultString.equals(expected), "reverse empty String");
        
        resultString = reverseString("abc");
        expected = "cba";
        displayResults(resultString.equals(expected), "reverse \"abc\"");
        
        boolean matched;
        matched = doBracketsMatch("");
        displayResults(matched, "doBracketsMatch empty String");
        
        matched = doBracketsMatch("(abc)");
        displayResults(matched, "doBracketsMatch \"(abc)\"");
        
        matched = doBracketsMatch("(a(bc))");
        displayResults(matched, "doBracketsMatch \"(a(bc))\"");
        
        matched = doBracketsMatch("(lm(def)))");
        displayResults(!matched, "doBracketsMatch \"(lm(def)))\"");
        
        matched = doBracketsMatch("k(lmn)ab)");
        displayResults(!matched, "doBracketsMatch \"k(lmn)ab)\"");
    }
    

    
    /*
     * Purpose: reverses str following this algorithm
     *  - pushes each character in str onto a new StackArrayBased,
     *  - creates a new empty result String
     *  - pops each character off the Stack adding it to the result String
     *  - returns the result String
     * Parameters: String str - the String to reverse
     * Returns: String - the reversed result String
     */
    public static String reverseString(String str) {
        String result = "";

        return result;
    }
    
    /*
     * Purpose: determines whether every '(' bracket
     *  in str is matched with a following ')'
     * Parameters: String str - the String check
     * Returns: boolean - true if brackets are matched, false otherwise
     */
    public static boolean doBracketsMatch(String str) {
        // TODO: complete this function
        
        return true;
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
    
    
}
