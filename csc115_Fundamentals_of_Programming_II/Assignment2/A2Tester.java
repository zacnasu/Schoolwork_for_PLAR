/*
 * A2Tester.java
 *
 * A partial test program for Assignment 2.
 *
 * While this program includes many test cases,
 * it is INTENTIONALLY not a comprehensive set of tests.
 * You should add your own tests to test cases not considered.
 *
 * The auto grading of your assignment will include different and additional tests.
 *
 */
public class A2Tester {
    
    private static int testPassCount = 0;
    private static int testCount = 0;

    public static void main (String[] args) {
        
        dateConstructorGetterTests();
        dateSetterGetterTests();
        dateEqualsTests();
        dateIsBeforeTests();
        dateIsLeapYearTests();
        dateToStringTests();
        
        petConstructorGetterTests();
        petSetterGetterTests();
        petIsOlderTests();
        petToStringTests();
        
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
	
	public static void dateConstructorGetterTests() {
        
        // TODO:
        // uncomment the following test your Constructors and Getter methods
        // Remember to add tests as needed
        
        Date noParams = new Date();
        displayResults (noParams.getMonth() == 1, "Date() Constructor Getter Test -month");
        displayResults (noParams.getDay()   == 1, "Date() Constructor Getter Test -day");
        displayResults (noParams.getYear()  == 1582, "Date() Constructor Getter Test -year");
		
        Date d20190131 = new Date(1,31,2019);
		displayResults (d20190131.getMonth() == 1, "Date(1,31,2019) Constructor Getter Test -month");
        displayResults (d20190131.getDay()   == 31, "Date(1,31,2019) Constructor Getter Test -day");
		displayResults (d20190131.getYear()  == 2019, "Date(1,31,2019) Constructor Getter Test -year");
         
        
    }

    public static void dateSetterGetterTests() {
        
        // TODO:
        // uncomment the following test your Setter methods
        // Remember to add tests as needed
        
        Date d1 = new Date(1,31,2018);
        
        d1.setYear(2019);
        d1.setMonth(02);
        d1.setDay(10);

        displayResults (d1.getMonth() == 2, "Date setMonth(2) Test");
        displayResults (d1.getDay()   == 10, "Date setDay(10) Test");
        displayResults (d1.getYear()  == 2019, "Date setYear(2019) Test");
        
    }

    public static void dateEqualsTests() {
        
        // TODO:
        // uncomment the following test your equals method
        // Remember to add tests as needed
        
        Date d         = new Date(1,31,2018);
        Date dEqual    = new Date(1,31,2018);
        Date dDefault  = new Date();
        
        displayResults (d.equals(d), "Date Equals Tests - same object");
        displayResults (d.equals(dEqual), "Date Equals Tests - different objects, equal date");
        displayResults (!dDefault.equals(d), "Date Equals Tests - different objects, all fields differ");
         
    }
    
    public static void dateIsBeforeTests() {
        
        // TODO:
        // uncomment the following test your isBefore method
        // Remember to add tests as needed
        
        Date d1a         = new Date(3,23,2018);
        Date d1Same      = new Date(3,23,2018);
        Date d1Before1a  = new Date(3,23,2017);
        Date d1After1a   = new Date(3,24,2018);

        
        displayResults (!d1Same.isBefore(d1a), "Date isBefore Tests - different objects, equal date");
        displayResults (d1Before1a.isBefore(d1a), "Date isBefore Tests - year is before");
        displayResults (!d1After1a.isBefore(d1a), "Date isBefore Tests - day is after");
        
    }
    
    public static void dateIsLeapYearTests() {
        
        // TODO:
        // uncomment the following test your isLeapYear method
        // Remember to add tests as needed
        
        Date d1LeapYear = new Date(1,1,2008);
        Date d5NotLeapYear = new Date(2,23,2011);
        
        displayResults (d1LeapYear.isLeapYear(), "Date IsLeapYear Test 2008 - true");
        displayResults (!d5NotLeapYear.isLeapYear(), "Date IsLeapYear Test 2011 - false");
        
    }
    
    public static void dateToStringTests() {
        
        // TODO:
        // uncomment the following test your toString method
        // Remember to add tests as needed
        
        Date d1 = new Date(11,1,2008);
        Date d2 = new Date(3, 23, 2000);
         
        displayResults (d1.toString().equals("2008-11-1"), "Date toString Test 2008-11-1");
        displayResults (d2.toString().equals("2000-3-23"), "Date toString Test 2009-3-23");
        
    }

    public static void petConstructorGetterTests() {
        
        // TODO:
        // uncomment the following test your Constructors and Getter methods
        // Remember to add tests as needed
        
        Pet pNoDate = new Pet("Cat", "Felix");
        displayResults (pNoDate.getType().equals("Cat"),
                        "Pet(Cat, Felix) Constructor Getter Test - type");
        displayResults (pNoDate.getName().equals("Felix"),
                        "Pet(Cat, Felix) Constructor Getter Test - name");
        displayResults (pNoDate.getBirthdate().equals(new Date()),
                        "Pet(Cat, Felix) Constructor Getter Test - birthdate");
        
        
        Date d20190131 = new Date(1,31,2019);
        Pet pWithDate = new Pet("Fish", "Louie", d20190131);
        displayResults (pWithDate.getType().equals("Fish"),
                        "Pet(Fish, Louie, d20190131) Constructor Getter Test - type");
        displayResults (pWithDate.getName().equals("Louie"),
                        "Pet(Fish, Louie, d20190131) Constructor Getter Test - name");
        displayResults (pWithDate.getBirthdate().equals(d20190131),
                        "Pet(Fish, Louie, d20190131) Constructor Getter Test - birthdate ");
         
        
        
    }
    
    public static void petSetterGetterTests() {
        
        // TODO:
        // uncomment the following test your Setter methods
        // Remember to add tests as needed
        
        Pet p1 = new Pet("Cat", "Felix");

        p1.setType("Tabby Cat");
        p1.setName("Radd");
        Date d20200315 = new Date(3,15,2020);
        p1.setBirthdate(d20200315);
        
        displayResults (p1.getType().equals("Tabby Cat"),
                        "Pet setType(Tabby Cat) Test");
        displayResults (p1.getName().equals("Radd"),
                        "Pet setName(Radd) Test");
        displayResults (p1.getBirthdate().equals(d20200315),
                        "Pet setDate(d20200315) Test");
        
         
    }
    
    public static void petIsOlderTests() {
        
        // TODO:
        // uncomment the following test your isOlder method
        // Remember to add tests as needed
        
        Date d20200315a = new Date(3,15,2020);
        Date d20180315b = new Date(3,15,2018);

        
        Pet p1 = new Pet("Cat", "Felix", d20200315a);
        Pet olderThanp1 = new Pet("Fish", "Freddie", d20180315b);
        
        displayResults (!p1.isOlder(p1), "Pet isOlder Test same pet - false");
        displayResults (olderThanp1.isOlder(p1), "Pet isOlder Test older - true");
        
    }
    
    public static void petToStringTests() {
        
        // TODO:
        // uncomment the following test your equals method
        // Remember to add tests as needed
        
        Pet p0 = new Pet("Cat", "Felix");
        Pet p1 = new Pet("Dog", "Rover", new Date(11,1,2008));

        displayResults (p0.toString().equals("Cat: Felix: 1582-1-1"), "Pet toString Test Cat: Felix: 1582-1-1");
        
        displayResults (p1.toString().equals("Dog: Rover: 2008-11-1"), "Pet toString Test Dog: Rover: 2008-11-1");
        
    }
}
