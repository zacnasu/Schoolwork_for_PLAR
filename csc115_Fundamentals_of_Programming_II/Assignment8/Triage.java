//Zachary Nasu 
//V00911790

/*
 * Triage.java
 */

public class Triage {
    
    private PriorityQueue patients;
    
    /*
     * PURPOSE:
     *  initialize patients to a new HeapPriorityQueue
     */
    public Triage() {
        // TODO...
        patients = new HeapPriorityQueue();
    }
    
    /*
     * PURPOSE:
     *  initialize patients to a new HeapPriorityQueue of given size
     */
    public Triage(int size) {
        // TODO...
        patients = new HeapPriorityQueue(size);
    }
    
    /*
     * PURPOSE:
     *  add given Patient p to patients and
     *  prints a notification message if the Triage is full
     *
     * PARAMETERS:
     *  Patient p - Patient to be added
     *
     * RETURNS:
     *  None.
     */
    public void addPatient(Patient p) {
        // TODO...
        try{
        patients.insert(p);
        }catch(HeapFullException e){
            System.out.println(e);
        }
    }
    
    /*
     * PURPOSE:
     *  returns that number of Patients in patients
     *
     * PARAMETERS:
     *  None.
     *
     * RETURNS:
     *  int - number of patients
     */
    public int numPatientsWaiting() {
        // TODO...
        return patients.size();
    }
    
    /*
     * PURPOSE:
     *  removes and returns the next Patient from patients
     *
     * PARAMETERS:
     *  None.
     *
     * RETURNS:
     *  Patient - the next patient, null if there is no more Patients.
     */
    public Patient nextPatient() {
        // TODO...
        try{
        Patient y = (Patient)patients.removeHigh();
        return y;
        }catch(HeapEmptyException e){
            return null;
        }
    }
}

