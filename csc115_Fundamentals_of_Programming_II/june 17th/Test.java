public class Test {
    public static void main (String[] args) {
        // Q1. replace preconditions in LinkedList with thrown checked exceptions
        
        // Q2. Make LinkedList generic
        
        LinkedList myList = new LinkedList();

        System.out.println("empty: " + myList);
        myList.addFront(10);
        System.out.println("10: " + myList);
        myList.addBack(20);
        System.out.println("10 20: " + myList);
        myList.addFront(30);
        System.out.println("30 10 20: " + myList);
        
        int result;
        result = myList.get(2);
        System.out.println("20: " + result);
        
        result = myList.get(3);
        System.out.println("access off the list");

    }
}
