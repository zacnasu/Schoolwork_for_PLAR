public class RuntimeExceptionExercise {
    // Q1. The following program will access an index outside of the array
    //  What type of exception is thrown?
    //  Would you call this a checked or unchecked exception?
    //  Identify this exception in the class heirachy diagram in the lab pdf?


    // Q2. Uncomment the try and first catch block (lines 33,36,37,38) .
    //      Notice, this will catch an exception of type RuntimeException
    //      Recompile and run the program again.
    //   What is the output?
    //   Given your answer to Q1 about what type of exception is thrown,
    //      and you knowledge of inheritance,
    //      explain to your neighbor why you see the output you see

    // Q3. Change the code to catch an ArrayIndexOutOfBoundsException
    //  Recompile and rerun.  Did the output change?

    // Q4. Change the print statement to the following:
    //                System.out.println(array1[0]/array1.length);
    //      What is the output now?

    // Q5. Change the print statement to the following:
    //                System.out.println(array1[0]/array0.length);
    //    What is the output now?
    //    Uncomment the second catch block (lines 39, 40 and 41)
    //          and fill in the Exception type left as ???
    //  Recompile and rerun.  What is the output?

    public static void main(String[] args)  {
        System.out.println("start of main");
        int[] array0 = {};
        int[] array1 = {9, 10, 11};
//        try {
            System.out.println(array1[10]/array1.length);

//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println("first catch block: " + e);
//        }
//        catch (??? e) {
//            System.out.println("second catch block: " + e);
//        }

        System.out.println("end of main");
    }

}
