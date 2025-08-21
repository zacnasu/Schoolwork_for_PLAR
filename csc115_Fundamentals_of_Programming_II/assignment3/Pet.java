/*
 * Pet class representing a domesticated pet
 */
public class Pet {
    
	private String type;    // type of the pet
    private String name;    // name of the pet
    private Date birthdate; // date pet was born

    /*
     * Purpose: Initialize this instance of Pet with parameter values
     *  birthdate is set to default value of Date
     *
     * Parameters: String type, String name
     *
     */
    public Pet (String type, String name) {
        
        this.type = type;
        this.name = name;
        this.birthdate = new Date();
    }
    
    /*
     * Purpose: Initialize this instance of Pet with parameter values
     *
     * Parameters: String type, String name, Date birthdate
     *
     * Precondition: date is not null
     */
	public Pet (String type, String name, Date birthdate) {
        
        this.type = type;
        this.name = name;
        this.birthdate = birthdate;
	}

    /*
     * Purpose: Returns the type associated with this Pet
     *
     * Parameters: nothing
     *
     * Returns: (String) - type associated with the Pet
     */
    public String getType () {
        return type;
    }
    
    /*
     * Purpose: sets the type associated with this Pet to parameter value
     *
     * Parameters: String type
     *
     * Returns: nothing
     */
	public void setType (String type) {
		this.type = type;
	}
    
    /*
     * Purpose: Returns the name associated with this Pet
     *
     * Parameters: nothing
     *
     * Returns: (String) - name associated with this Pet
     */
    public String getName () {
        return name;
    }
    
    /*
     * Purpose: sets the name associated with this Pet to parameter value
     *
     * Parameters: String name
     *
     * Returns: nothing
     */
    public void setName (String name) {
        this.name = name;
    }
    
    /*
     * Purpose: Returns the birthdate associated with this Pet
     *
     * Parameters: nothing
     *
     * Returns: (Date) - birthdate associated with this Pet
     */
    public Date getBirthdate () {
        return birthdate;
    }
    
    /*
     * Purpose: sets the birthdate associated with this Pet to parameter value
     *
     * Parameters: String birthdate
     *
     * Returns: nothing
     */
    public void setBirthdate (Date birthdate) {
        this.birthdate = birthdate;
    }
    
    /*
     * Purpose: determines whether the birthdate of
     *  this instance of pet is before other Pet
     *  ie. this Pet is older than other pet
     *
     * Parameters: Pet other
     *
     * Precondition: other is not null
     *
     * Returns: true if this Pet is younger than other Pet, false otherwise
     */
    public boolean isOlder (Pet other) {
        return  this.birthdate.isBefore(other.getBirthdate());
    }

    /*
     * Purpose: returns a String representing this Pet formated as:
     *  type: name: birthdate
     *
     * Parameters: nothing
     *
     * Returns: String - a representation of this Pet
     *
     * Example:
     *  Pet p = new Pet("Dog", "Rover", new Date(10,25,2019));
     *  p.toString() returns "Dog: Rover: 2019-10-25"
     */
	public String toString() {
        return type + ": " + name + ": " + birthdate;
	}
}
