/*
 * A4Tester.java
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
public class A4Tester {
    
    private static int testPassCount = 0;
    private static int testCount = 0;
    public static int  stressTestSize = 20000;
    
    public static void main (String[] args) {
        
        try
        {
            playerListTestOne();
            playerListTestTwo();
            playerListResizeTest();
            playerListStressTest();
            teamTest();
        }
        catch (Exception e)
        {
            System.out.println("Your code threw an Exception.");
            System.out.println("Perhaps a stack trace will help:");
            e.printStackTrace(System.out);
        }
        
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
	
    public static void playerListTestOne() {
        
        PlayerListInterface l = new PlayerList();
        Player p = new Player("Derek Jeter");
        
        displayResults(l.size() == 0, "list constructor, size");
        l.add(p);
        displayResults(l.size() == 1, "list constructor + add 1 element");
        displayResults(l.get(0).equals(p), "list get 1 element list");
        displayResults(l.find(p) != -1, "list find of player not there, 1 element list");
    }
    
    public static void playerListTestTwo() {
        
        System.out.println("PlayerList testing: add, get, find, remove");
        
        PlayerListInterface l = new PlayerList();
        Player p1 = new Player("Derek Jeter", 350);
        Player p2 = new Player("Jose Bautista", 400);
        
        l.add(p1);
        l.add(p2);
        displayResults(l.size() == 2, "add + size, 2 element list");
        Player p3 = l.get(0);
        Player remaining;
        
        if (p3.equals(p1))
            remaining = p2;
        else
            remaining = p1;

        l.remove(0);
        displayResults(l.find(remaining) != -1, "remove + find, 2 element list");
        displayResults(l.size() == 1, "remove + size, 2 element list");
        displayResults(l.get(0).equals(remaining), "remove + get, 2 element list");
        
        l.remove(0);
        displayResults(l.size() == 0, "remove + size, 1 element list");
    }
    
    public static void playerListResizeTest() {
        
        System.out.println("PlayerList testing resizing, add, find");
        PlayerListInterface l = new PlayerList();
        
        int    num = 5500000;
        for (int i = 0; i < 100; i++)
            l.add(new Player(Integer.toString(num+i)));
        
        displayResults(l.size() == 100, "add + size, 100 elements");
        
        boolean passed = true;
        
        for (int i = 99; i >= 0; i--)
            if (l.find(new Player(Integer.toString(num+i))) == -1)
                passed = false;

        displayResults(passed, "find, 100 elements");
    }
    
    public static void playerListStressTest() {
            
        System.out.println("PlayerList stress test.");
        
        PlayerListInterface l = new PlayerList();
        
        int    num = 5500000;
        for (int i = 0; i < stressTestSize; i++)
            l.add(new Player(Integer.toString(num+i)));

        displayResults(l.size() == stressTestSize, "add + size, >10000 elements");
        
        boolean passed = true;
        
        for (int i = (stressTestSize - 1); i >= 0; i--)
            if (l.find(new Player(Integer.toString(num+i))) == -1)
                passed = false;

        displayResults(passed, "find, >10000 elements");
        
        passed = true;
        for (int i = (stressTestSize - 1); i > 0; i--) {
            Player p = l.get(0);
            l.remove(0);
            
            if (l.find(p) != -1) {
                passed = false;
                break;
            }
            
            if (l.size() != i) {
                passed = false;
                break;
            }
        }
        displayResults(passed, "remove + find, >10000 elements");
        
    }
    
    public static void teamTest() {
            
        System.out.println("Testing Team");
        Player p1 = new Player("Derek Jeter", 350);
        Player p2 = new Player("Jose Bautista", 400);
        Player p3 = new Player("Michael Saunders", 298);
        
        Team c = new Team("Toronto Blue Jays");
        displayResults(c.getName().equals("Toronto Blue Jays"), "constructor without player + getName");
        displayResults(c.getPlayerCount() == 0, "constructor without player + getPlayerCount");
        c.setName("Seattle Mariners");
        displayResults(c.getName().equals("Seattle Mariners"), "setName + getName");
        
        Team c2 = new Team("New York Yankees", p1);
        displayResults(c2.getName().equals("New York Yankees"), "constructor with player + getName");
        displayResults(c2.getPlayerCount() == 1 , "constructor with player + getPlayerCount");
        displayResults(c2.getPlayer(0).equals(p1), "constructor with player + getPlayer");
        c2.removePlayer(p1);
        displayResults(c2.getPlayerCount() == 0, "remove player + getPlayerCount");
        c2.addPlayer(p2);
        c2.addPlayer(p3);
        displayResults(c2.getPlayerCount() == 2, "add player + getPlayerCount");
        
    }

}
