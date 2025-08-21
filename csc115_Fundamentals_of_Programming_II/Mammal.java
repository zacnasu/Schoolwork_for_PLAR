public class Mammal extends Animal {

    private int gestationPeriod; // in months
    
    public Mammal() {
        gestationPeriod = 0;
        System.out.println("mammal constructor 0");
    }
    
    public Mammal(int gestationPeriod) {
        this.gestationPeriod = gestationPeriod;
        System.out.println("mammal constructor 1");
    }
    
    public Mammal(String species, String sound) {
        super(species, sound);
        this.gestationPeriod = 0;
        System.out.println("mammal constructor 2");
    }
    
    public Mammal(int gestationPeriod, String species, String sound) {
        super(species, sound);
        this.gestationPeriod = gestationPeriod;
        System.out.println("mammal constructor 3");
    }
    
    public void speak() {
        super.speak();
        System.out.println("I have a gestation period of " + gestationPeriod + " months");
    }
    
    public String toString() {
        String s = super.toString();
        s += ":" + gestationPeriod;
        return s;
    }
}
