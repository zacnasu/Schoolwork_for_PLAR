public class Test {

    public static void main (String[] args) {
              
              // Q1. write and test a recursive function that will
              //  find and return the largest number in the list
              //  assume there is at least one element in the list
              IntegerLinkedList oneList = new IntegerLinkedList();
              oneList.addBack(10);
              int max = oneList.max();
              System.out.println("emptyList, should print 10\n" + max);
              
              IntegerLinkedList myList = new IntegerLinkedList();
              myList.addBack(10);
              myList.addBack(9);
              myList.addBack(3);
              myList.addBack(12);
              
              max = myList.max();
              System.out.println("should print 12:\n" + max);
              
              myList.addBack(3);
              max = myList.max();
              System.out.println("should print 12:\n" + max);
              
              myList.addFront(40);
              max = myList.max();
              System.out.println("should print 40:\n" + max);
              
              // Q2. write and test a recursive function that will
              //  determine whether all elements are above a given threshold
              IntegerLinkedList emptyList = new IntegerLinkedList();
              boolean above = emptyList.allAbove(10);
              System.out.println("emptyList, should print true: " + above);
              
              // myList has: 40 10 9 3 12 3
              above = myList.allAbove(3);
              System.out.println("should print false: " + above);
              
              above = myList.allAbove(0);
              System.out.println("should print true: " + above);
              
              
              // // Q3. write and test a recursive function that will
              // //  print the values at odd positions
              // System.out.println("emptyList, should print an empty line: ");
              // emptyList.printAtOddPosition();
              //
              // IntegerLinkedList myList1 = new IntegerLinkedList();
              // myList1.addBack(-10);
              //
              // System.out.println("myList1, should print an empty line: ");
              // myList1.printAtOddPosition();
              //
              // IntegerLinkedList myList2 = new IntegerLinkedList();
              // myList2.addBack(-10);
              // myList2.addBack(7);
              // myList2.addBack(-3);
              // myList2.addBack(12);
              // myList2.addBack(1);
              // myList2.addBack(14);
              //
              // System.out.println("myList2, should print: 7 12 14");
              // myList2.printAtOddPosition();
              //
              //
              // // Q4. write and test a recursive function that will
              // //  determine whether a list is strictly decreasing by 1
              // //  ie. 5 4 3 2 is strictly decreasing by 1
              // //  ie. 5 3 2 1 is NOT strictly decreasing by 1
              // boolean isDecreasing = emptyList.isStrictlyDecreasing();
              // System.out.println("emptyList, should print true: " + isDecreasing);
              //
              // IntegerLinkedList myList1a = new IntegerLinkedList();
              // myList1a.addBack(-10);
              //
              // isDecreasing = myList1a.isStrictlyDecreasing();
              // System.out.println("list with one element, should print true: " + isDecreasing);
              //
              // IntegerLinkedList myList2a = new IntegerLinkedList();
              // myList2a.addBack(3);
              // myList2a.addBack(5);
              // myList2a.addBack(6);
              // myList2a.addBack(7);
              //
              // isDecreasing = myList2a.isStrictlyDecreasing();
              // System.out.println("list with one element, should print false: " + isDecreasing);
              //
              // IntegerLinkedList myList3a = new IntegerLinkedList();
              // myList3a.addBack(4);
              // myList3a.addBack(5);
              // myList3a.addBack(6);
              // myList3a.addBack(8);
              //
              // isDecreasing = myList3a.isStrictlyDecreasing();
              // System.out.println("list with one element, should print false: " + isDecreasing);
              //
              // IntegerLinkedList myList4a = new IntegerLinkedList();
              // myList4a.addBack(4);
              // myList4a.addBack(5);
              // myList4a.addBack(6);
              // myList4a.addBack(7);
              //
              // isDecreasing = myList4a.isStrictlyDecreasing();
              // System.out.println("list with one element, should print true: " + isDecreasing);
              //
              //
              // // Q5. write and test a recursive function that will
              // //  remove a given value from the list
              //
              // emptyList.remove(10);
              // System.out.println("emptyList, should print an empty list: " + emptyList);
              //
              // IntegerLinkedList myList1b = new IntegerLinkedList();
              // myList1b.addBack(-10);
              //
              // myList1b.remove(10);
              // System.out.println("myList1b, should print -10: " + myList1b);
              //
              // myList1b.remove(-10);
              // System.out.println("myList1b, should print empty list: " + myList1b);
              //
              // IntegerLinkedList myList2b = new IntegerLinkedList();
              // myList2b.addBack(10);
              // myList2b.addBack(7);
              // myList2b.addBack(-3);
              // myList2b.addBack(10);
              // myList2b.addBack(1);
              // myList2b.addBack(10);
              //
              // myList2b.remove(1);
              // System.out.println("myList2b, should print 10 7 -3 10 10: " + myList2b);
              //
              // myList2b.remove(10);
              // System.out.println("myList2b, should print 7 -3: " + myList2b);
    }
}
