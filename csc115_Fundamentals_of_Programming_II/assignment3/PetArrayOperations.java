//Zachary Nasu
//V00911790
import java.lang.Math;

/*
 * PetArrayOperations
 */
public class PetArrayOperations {
    
    /*
     * getAllPetNames
     *
     * Purpose: creates a new array of the names of all of the Pets in input array
     *
     * Parameters: Pet[] - array
     *
     * Returns: String[] - new array of Pet names
     *
     */
    // TODO...
	public static String[] getAllPetNames(Pet[] pets){
		String[] names = new String[pets.length];
		for( int i =0; i < pets.length; i++){
			names[i] = pets[i].getName();
		}
		return names;
	}
    
    /*
     * getPetType
     *
     * Purpose: gets the type of the Pet in input array that has specified name
     *
     * Parameters: Pet[] - array, String - name
     *
     * Returns: String - the type of the Pet or "" if Pet name not found
     *
     */
    // TODO...
	public static String getPetType(Pet[] pets, String name){
		for(int i = 0; i<pets.length; i++){
			if(pets[i].getName().equals(name) == true){
				return pets[i].getType();
			}
		}
		return "";
}
    
    
    /*
     * addPet
     *
     * Purpose: creates a new array 1 longer than input array and copies all Pet references
     *  from input array to new array and puts p at the end of new array
     *
     * Parameters: Pet[] - array, Pet - p
     *
     * Preconditions:  p is not null
     *
     * Returns:  Pet[] - the new array
     *
     */
    // TODO...
	public static Pet[] addPet(Pet[] oldArray, Pet newPet){
		Pet[] newArray = new Pet[oldArray.length+1];
		for(int i =0; i<oldArray.length ; i++){
			newArray[i] = oldArray[i];
		}
		newArray[oldArray.length] = newPet;
		return newArray;
	}


    /*
     * hasPet
     *
     * Purpose: determines whether a Pet with specified name is in input array
     *
     * Parameters: Pet[] - array, String - name
     *
     * Returns: boolean - true if Pet with name is found, false otherwise
     *
     */
    // TODO...
	public static boolean hasPet(Pet[] array, String name){
		for(int i =0; i<array.length; i++){
			if(array[i].getName().equals(name)){
				return true;
			}
		}
		return false;
	}

    /*
     * removePet
     *
     * Purpose: if input array has Pet with given name, returns a new array
     *  with length one less that input array is created and
     *  all but that Pet to be removed is copied from input array to new array
     *  if input array does not have Pet with given name, return input array
     *
     *  if two pets with the same name are in the input array
     *  the method removes the first occurance of the Pet with that name
     *
     * Parameters: Pet[] - array, String - name
     *
     * Precondition: array contains pets with unique names
     *  (ie. no two Pets have the same name)
     *
     * Returns: Pet[] - new array if name found, otherwise array
     *
     */
    // TODO...
	public static Pet[] removePet(Pet[] array, String name){
		
		if(hasPet(array, name) == false){
			return array;
		}else{
			int pass =0;
			Pet[] newArray = new Pet[array.length-1];
			int k =0;
			for( int i = 0; i<array.length; i++){
				if(array[i].getName().equals(name) && pass == 0){
					pass = 1;
				}else{
					newArray[k] = array[i];
					k++;
				}
			}
			return newArray;
		}
	}
    
    
    /*
     * getOldestPet
     *
     * Purpose: get the Pet from array with earliest birthdate (oldest)
     *  if two pets with the same birthdate are the oldest,
     *  the first occurance of Pet with that birthdate in the array is used
     *
     * Parameters: Pet[] - array
     *
     * Precondition: array is not empty
     *
     * Returns: Pet - oldest Pet
     *
     */
    // TODO...
	public static Pet getOldestPet(Pet[] array){
		if(array.length ==1){
			return array[0];
		}else{
			Pet Oldest = array[0];
			for(int i =1; i<array.length; i++){
				if(Oldest.isOlder(array[i])== false){
					Oldest = array[i];
				}
			}
			return Oldest;
		}
	}
    
}
