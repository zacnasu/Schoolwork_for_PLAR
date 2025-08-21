public class IntegerArrayList implements IntegerList {
    
    private static final int INITIAL_SIZE = 2;
    
    private int[]	storage;
    private int		count;
    
    
    public IntegerArrayList() {
        storage = new int[INITIAL_SIZE];
        count = 0;
    }
    
    
    public int get (int position) {
        return storage[position];
    }
    
    
    public void remove (int position) {
        shiftLeft(position);
        count--;
        
    }
    
    public void removeValue (int val) {
        
        int index = 0;
        int numElements = count;
        for(int i=0; i<numElements; i++) {
            if (storage[index] == val) {
                shiftLeft(index);
                count--;
            } else {
                index++;
            }
        }
        
    }
    
    private void shiftLeft(int index) {
        
        for (int i=index; i<count-1; i++) {
            storage[i] = storage[i+1];
        }
    }
    
    
    public int size() {
        return count;
    }
    
    
    public void add (int i) {
        if (count == storage.length)
            growAndCopy();
        
        storage[count++] = i;
    }
    
    
    public int find (int i) {
        for (int index=0; index<count; index++)
            if (storage[index] == i)
                return index;
        
        return -1;
    }
    
    public String toString() {
        String s = "";
        
        for(int i=0; i<count; i++) {
            s += storage[i];
            if(i!=count-1)
                s += " ";
        }
        
        return s;
        
    }
    
    public String reverse() {
        String s = "";
        
        for(int i=count-1; i>=0; i--) {
            s += storage[i];
            if(i!=0)
                s += " ";
        }
        
        return s;
    }
    
    
    
    private void growAndCopy() {
        int[] a = new int[storage.length*2];
        
        for (int i = 0; i < count; i++)
            a[i] = storage[i];
        
        storage = a;
    }
}
