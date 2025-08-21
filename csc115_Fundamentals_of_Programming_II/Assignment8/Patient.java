//Zachary Nasu 
//V00911790

/*
 * Patient.java
 */

public class Patient implements Comparable<Patient>{
    
    private int priority;
    private String name;
    private int carecard;
    private String initAssessment;
    
    public Patient(int priority, String name, int carecard, String initAssessment) {
        this.priority = priority;
        this.name = name;
        this.carecard = carecard;
        this.initAssessment = initAssessment;
    }
    
    
    /*
     * PURPOSE:
     *  Computes and returns a relative difference between this Patient and
     *  other Patient based on priority for order.
     *  Difference is a negative integer, zero, or a positive integer as this Patient priority
     *  is less than, equal to, or greater than the other Patient respectively.
     *
     * PARAMETERS:
     *   Patient other - Patient to compare to
     *
     * RETURNS:
     *  int - the relative difference
     */
    public int compareTo(Patient other) {
        // TODO...
        if(this.priority > other.priority){
            return 1;
        }else if(this.priority < other.priority){
            return -1;
        }else{
        return 0;
        }
    }
    
    /*
     * PURPOSE:
     *  deterimine whether this Patient is the same Patient
     *  by comparing this carecard to other Patient's carecard
     *
     * PARAMETERS:
     *   Patient other - Patient to compare to
     *
     * RETURNS:
     *  boolean - true if other is same Patient as this patient
     */
    public boolean equals(Patient other) {
        // TODO...
        return true;
    }
    
    public String toString() {
        return carecard + ": " + name;
    }
}

