import java.util.*;

//Zachary Nasu
//V00911790

public class BSTMap<K extends Comparable<K>, V > implements  Map<K, V>  {
	private BinarySearchTree<K,V> list;

	public BSTMap () {
		list = new BinarySearchTree<K,V>();
	}

	public boolean containsKey(K key) {
		try{
			list.find(key);
			return true;
		}catch(KeyNotFoundException e){
		return false;
		}
	}

	public V get (K key) throws KeyNotFoundException {
		return list.find(key);
	}

	public List<Entry<K,V> >	entryList() {
		return list.entryList();
	}

	public void put (K key, V value) {
		list.insert(key,value);
	}

	public int size() {
		return list.size();
	}

	public void clear() {
		list.clear();
	}
	public long getGetLoopCount() {
		return list.getFindLoopCount();
		}
		public long getPutLoopCount() {
		return list.getInsertLoopCount();
		}
		public void resetGetLoops() {
		 list.resetFindLoops();
		}
		public void resetPutLoops() {
		 list.resetInsertLoops();
		}

}
