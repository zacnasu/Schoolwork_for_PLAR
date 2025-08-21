import java.util.Arrays;
/*
 * A3Tester.java
 *
 * A partial test program for Assignment 3.
 *
 * While this program includes many test cases,
 * it is INTENTIONALLY not a comprehensive set of tests.
 * You should add your own tests to test cases not considered.
 *
 * The auto grading of your assignment will include different and additional tests.
 *
 */
public class A3Tester {
    
    private static int testPassCount = 0;
    private static int testCount = 0;
    
    private static Date d20200412 = new Date(4,12,2020);
    private static Date d20200315 = new Date(3,15,2020);
    private static Date d20180528 = new Date(5,28,2018);
    private static Date d20210301 = new Date(3,01,2021);
    
    private static Pet pFelix   = new Pet("Cat",  "Felix",   d20200412);
    private static Pet pBowser  = new Pet("Dog",  "Bowser",  d20200315);
    private static Pet pFreddie = new Pet("Fish", "Freddie", d20180528);
    private static Pet pDora    = new Pet("Dog",  "Dora",    d20210301);

    public static void main (String[] args) {
        
        getAllPetNamesTest();
        getPetTypeTest();
        addPetTest();
        hasPetTest();
        removePetTest();
        getOldestPetTest();
        
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
	
	public static void getAllPetNamesTest() {
        // TODO: uncomment the following to test your solution
        // Remember to add tests as needed
        
        Pet[] petsEmpty = {};
        Pet[] pets3 = {pDora, pBowser, pFreddie};
        
        String[] result;
        String[] expectedEmpty = {};
        String[] expected3 = {"Dora", "Bowser", "Freddie"};

        result = PetArrayOperations.getAllPetNames(petsEmpty);
        displayResults (Arrays.equals(result, expectedEmpty), "getAllPetNamesTest - empty array");
        result = PetArrayOperations.getAllPetNames(pets3);
		displayResults (Arrays.equals(result, expected3), "getAllPetNamesTest - expected 3");
    }
    
    public static void getPetTypeTest() {
        // TODO: uncomment the following to test your solution
        // Remember to add tests as needed
        
        Pet[] petsEmpty = {};
        Pet[] pets4 = {pFelix, pBowser, pFreddie, pDora};
        
        String result;

        result = PetArrayOperations.getPetType(petsEmpty, "Freddie");
        displayResults (result.equals(""), "getPetTypeTest - empty array");
        
        result = PetArrayOperations.getPetType(pets4, "Felix");
        displayResults (result.equals("Cat"), "getPetTypeTest - non-empty array");
        
		result = PetArrayOperations.getPetType(pets4, "Bowser");
        displayResults (result.equals("Dog"), "getPetTypeTest - non-empty array");
    }
    
    public static void addPetTest() {
        // TODO: uncomment the following to test your solution
        // Remember to add tests as needed
        
        Pet[] petsEmpty = {};
        
        Pet[] expectedEmpty = {pBowser};

        Pet[] result;
		
		Pet[] pets3 = {pFelix, pBowser, pFreddie};
		
		Pet[] pets4 = {pFelix, pBowser, pFreddie, pDora};
        
        result = PetArrayOperations.addPet(petsEmpty, pBowser);
        // uncomment to print result array:
        //System.out.println("result array: " + Arrays.toString(result));
        displayResults (Arrays.equals(result, expectedEmpty), "getAllPetNamesTest - empty array");
        
        result = PetArrayOperations.addPet(pets3, pDora);
		displayResults (Arrays.equals(result, pets4), "getAllPetNamesTest - full array");
    }
    
    public static void hasPetTest() {
        // TODO: uncomment the following to test your solution
        // Remember to add tests as needed
        
        Pet[] petsEmpty = {};
        Pet[] pets4 = {pFelix, pBowser, pFreddie, pDora};
        
        Boolean result;
        
        result = PetArrayOperations.hasPet(petsEmpty, "Bowser");
        displayResults (!result, "hasPetTest - empty array -- false");
        
        result = PetArrayOperations.hasPet(pets4, "Felix");
        displayResults (result, "hasPetTest - non-empty array -- true");
        
    }
    
    public static void removePetTest() {
        // TODO: uncomment the following to test your solution
        // Remember to add tests as needed
        
        Pet[] petsEmpty = {};
        Pet[] pets4 = {pFelix, pBowser, pFreddie, pDora};
        
        Pet[] result;
        Pet[] expectedEmpty = {};
        Pet[] expected4RemoveFelix = {pBowser, pFreddie, pDora};
        
        result = PetArrayOperations.removePet(petsEmpty, "Bowser");
        displayResults (Arrays.equals(result, expectedEmpty), "removePetTest - empty array");
        
        result = PetArrayOperations.removePet(pets4, "Felix");
        displayResults (Arrays.equals(result, expected4RemoveFelix), "removePetTest - non-empty array front");
        
    }
    
    public static void getOldestPetTest() {
        // TODO: uncomment the following to test your solution
        // Remember to add tests as needed
        
        Pet[] pets1 = {pFelix};
        Pet[] petsA = {pBowser, pFelix, pDora};
        
        Pet result;
        
        result = PetArrayOperations.getOldestPet(pets1);
        displayResults (result.equals(pFelix), "getOldestTest - one element array");
        
        result = PetArrayOperations.getOldestPet(petsA);
        displayResults (result.equals(pBowser), "getOldestTest - longer array date order");
        
    }

}
