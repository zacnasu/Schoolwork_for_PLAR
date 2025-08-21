public class CheckedExceptionExercise {

    public static void foo(int x) throws TooSmallException{
        
        System.out.println("start foo");
        if (x<10) {
            System.out.println("foo throws ");
            throw new TooSmallException();
            // would the code ever be able to reach here?
            // uncomment this line and try to compile...
            // System.out.println("***");
        }
        //bar(x);
        
        System.out.println("end foo");
	
	}
    
    public static void bar(int x) throws TooBigException {
        System.out.println("start bar");
        if (x>50) {
            System.out.println("bar throws");
            throw new TooBigException();
        }
        System.out.println("end bar");
    }
	
    public static void main(String[] args) {
		System.out.println("start");
        
        try {
            // Q1. Trace the output of this call to foo(20) on paper.
            //  Check your answer by compiling and running.
            //  Notice the print statement says System.out.println("caught in main: " + e);
            //      where e is an object.
            //      Look at the TooSmallException class -notice it does not have a toString method
            //         But the printed output is not the referencecode that we would expect if no toString method exists?
            //      Given what you know about inheritance,
            //          what can you tell me about the Exception class that TooSmallException extends
            foo(20);
            
            // Q2. Trace the output of this call to foo(9) on paper.
            //  Check your answer by uncommenting the call, compiling and running.
            //foo(9);
            
            // Q3. In the foo method, there is a call to bar - uncomment this call
            //  Try to compile - you should get a compilation error given TooBigException is a checked exception
            //  You are going to fix the compile error in 2 different ways and trace the code
            
            //  Fix 1. wrap the call to bar in foo with a try/catch block
            //    Trace the output of this call to foo(100) on paper.
            //    Check your answer by uncommenting the call, compiling and running.
            //    foo(100);
            
            //  Fix 2. Remove the try/catch block around the call to bar
            //      and update the signature of foo to declare it throws a TooBigException as well
            //      ie. public static void foo(int x) throws TooSmallException, TooBigException {
            //   Try to compile - it still won't compile since the TooBigException has not yet been caught!
            //      Add a second catch block in main after the existing one to catch the TooBigException
            //    Trace the output of this call to foo(100) on paper.
            //    Check your answer by uncommenting the call, compiling and running.
            //    foo(100);

        } catch (TooSmallException e) {
            System.out.println("caught in main: " + e);
        }
        
		System.out.println("end");
	}

}
