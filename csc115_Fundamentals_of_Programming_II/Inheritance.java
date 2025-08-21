public class Inheritance {
    
    public static void main(String args[]) {
        Animal a1 = new Animal();
        Animal a2 = new Animal("lion", "roar");
       a1.speak();
       a2.speak();
       System.out.println("a1: " + a1);
       System.out.println("a2: " + a2);
       
       
       Mammal m1 = new Mammal();
       Animal m2 = new Mammal();
       m1.speak();
       m2.speak();
       
       System.out.println("m1: " + m1);
       System.out.println("m2: " + m2);
       
       Mammal m3 = new Mammal(4, "pig", "oink");
       Animal m4 = new Mammal(9, "cow", "moo");
       m3.speak();
       m4.speak();
       System.out.println("m3: " + m3);
       System.out.println("m4: " + m4);
       
       Dog d1 = new Dog();
       Dog d2 = new Dog("Rover");
       Dog d3 = new Dog("Chauncy", "Chihuahua", "yap yap");
       
       d1.speak();
       d2.speak();
       d3.speak();
       
       
       System.out.println("d1: " + d1);
       System.out.println("d2: " + d2);
       System.out.println("d3: " + d3);
       
       d1.feed();
       d2.feed();
       d3.feed();
        
        
    }
}
