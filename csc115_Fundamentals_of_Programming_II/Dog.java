public class Dog extends Mammal {

    private String houseName;
    
    public Dog() {
        houseName = "unnammed";
    }
    
    public Dog(String houseName) {
        this.houseName = houseName;
    }
    
    public Dog(String houseName, String species, String sound) {
        this.houseName = houseName;
    }

    public String toString() {
        String s = super.toString();
        s += ":" + houseName;
        return s;
    }
    
    public void feed() {
       System.out.println("feed me twice a day");
    }
}
