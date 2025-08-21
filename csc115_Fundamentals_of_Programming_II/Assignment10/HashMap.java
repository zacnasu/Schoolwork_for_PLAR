//Zachary Nasu
//V00911790

import java.util.*;

public class HashMap<K extends Comparable<K>, V> implements Map<K,V> {

    private long			getLoops;
    private long			putLoops;
    private int       tableSize;

    private List< List<Entry<K,V>> > 	table;
    private int			count;

    public HashMap() {
        tableSize = 1000003; // prime number
        table = new ArrayList<List<Entry<K,V>>>(tableSize);
        // initializes table as a list of empty lists
        for (int i = 0; i < tableSize; i++)
            table.add(new LinkedList<Entry<K,V>>());
        count = 0;

        resetGetLoops();
        resetPutLoops();
    }

    public long getGetLoopCount() {
        return getLoops;
    }

    public long getPutLoopCount() {
        return putLoops;
    }

    public void resetGetLoops() {
        getLoops = 0;
    }
    public void resetPutLoops() {
        putLoops = 0;
    }

    public boolean containsKey(K key) {
        // gets the index in the table this key should be in
        int index = Math.abs(key.hashCode()) % tableSize;
        // TODO... complete this method
        // Tip: use Java's List and Iterator operations to go through a chain at a specific index
        List<Entry<K,V>> list = table.get(index);
        ListIterator<Entry<K,V>> li = list.listIterator();
        Entry<K,V> temp;
        while(li.hasNext()){
            temp = li.next();
            if(temp.key.compareTo(key)==0)
                return true;
        }
        return false;
    }

    public V get (K key) throws KeyNotFoundException {
        // gets the index in the table this key should be in
        int index = Math.abs(key.hashCode()) % tableSize;
        // TODO... complete this method
        // Tip: use Java's List and Iterator operations to go through a chain at a specific index
        List<Entry<K,V>> list = table.get(index);
        ListIterator<Entry<K,V>> li = list.listIterator();
        Entry<K,V> temp;
        while(li.hasNext()){
            getLoops++;
            temp = li.next();
            if(temp.key.compareTo(key)==0)
                return temp.getValue();
        }
        throw new KeyNotFoundException();

    }

    public List<Entry<K,V> >	entryList() {
        List <Entry<K,V>> l = new LinkedList<Entry<K,V>>();
        // TODO... complete this method
        // Tip: use Java's List and Iterator operations to go through every index in the table
        //      use a second Iterator to go through each element in a chain at a specific index
        //      and add each element to l
        ListIterator<Entry<K,V>> list;
        for(int i =0; i< tableSize; i++){
            list = table.get(i).listIterator();
            while(list.hasNext()){
                l.add(list.next());
            }
        }
        return l;
    }

    public void put (K key, V value){
        // gets the index in the table this key should be in
        int index = Math.abs(key.hashCode())%tableSize;
        // TODO... complete this method
        // Tip: use Java's List and Iterator operations to go through a chain at a specific index
        //  if key is found, update value.  if key is not found add a new Entry with key,value
        List<Entry<K,V>> list = table.get(index);
        ListIterator<Entry<K,V>> li = list.listIterator();
        Entry<K,V> temp;
        while(li.hasNext()){
            putLoops++;
            temp = li.next();
            if(temp.key.compareTo(key)==0){
                temp.setValue(value);
                return;
            }
        }
        count++;
        list.add(new Entry(key,value));
    }

    public int size() {
        return count;
    }

    public void clear() {
        table.clear();
        count = 0;
    }

}
