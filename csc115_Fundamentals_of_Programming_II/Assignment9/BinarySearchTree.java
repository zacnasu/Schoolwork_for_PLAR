import java.util.*;

//Zachary Nasu
//V00911790
//
// An implementation of a binary search tree.
//
// This tree stores both keys and values associated with those keys.
//
// More information about binary search trees can be found here:
//
// http://en.wikipedia.org/wiki/Binary_search_tree
//
// Note: Wikipedia is using a different definition of
//       depth and height than we are using.  Be sure
//       to read the comments in this file for the
//	 	 height function.
//
class BinarySearchTree <K extends Comparable<K>, V>  {

	public static final int BST_PREORDER  = 1;
	public static final int BST_POSTORDER = 2;
	public static final int BST_INORDER   = 3;

	// These are package friendly for the TreeView class
	BSTNode<K,V>	root;
	int		count;


	public BinarySearchTree () {
		root = null;
		count = 0;
	}


	//
	// Purpose:
	//
	// Insert a new Key:Value Entry into the tree.  If the Key
	// already exists in the tree, update the value stored at
	// that node with the new value.
	//
	// Pre-Conditions:
	// 	the tree is a valid binary search tree
	//
	public void insert (K k, V v) {
		BSTNode<K,V> newNode = new BSTNode<K,V>(k,v);
		if(root == null ){
			root = newNode;
			count++;
			return;
		}
		BSTNode<K,V> temp = root;
		
		while(true){
			if(temp.key.compareTo(newNode.key)==0){
				temp.value = v;
				return;
			}else if(temp.key.compareTo(newNode.key)< 0){
				if(temp.right == null){
					temp.right = newNode;
					count++;
					return;
				}else{
					temp = temp.right;
				}
			}else{
				if(temp.left == null){
					temp.left = newNode;
					count++;
					return;
				}else{
					temp = temp.left;
				}
			}
		}
	}

	//
	// Purpose:
	//
	// Return the value stored at key.  Throw a KeyNotFoundException
	// if the key isn't in the tree.
	//
	// Pre-conditions:
	//	the tree is a valid binary search tree
	//
	// Returns:
	//	the value stored at key
	//
	// Throws:
	//	KeyNotFoundException if key isn't in the tree
	//
	public V find (K k) throws KeyNotFoundException {
		BSTNode<K,V> temp = root;
			while(temp!=null){
			if(temp.key.compareTo(k)==0){
				return temp.value;
				
			}else if(temp.key.compareTo(k)< 0){
				temp = temp.right;
			}else{
				temp = temp.left;
			}
		}
		throw new KeyNotFoundException();
	}

	//
	// Purpose:
	//
	// Return the number of nodes in the tree.
	//
	// Returns:
	//	the number of nodes in the tree.
	public int size() {
		return count;
	}

	//
	// Purpose:
	//	Remove all nodes from the tree.
	//
	public void clear() {
		root = null;
		count = 0;
	}

	//
	// Purpose:
	//
	// Return the height of the tree.  We define height
	// as being the number of nodes on the path from the root
	// to the deepest node.
	//
	// This means that a tree with one node has height 1.
	//
	// Examples:
	//	See the assignment PDF and the test program for
	//	examples of height.
	//
	public int height() {
		return height(root);
	}
	private int height(BSTNode<K,V> node){
		if(node == null){
			return 0;
		}
		int leftheight = height(node.left);
		int rightheight = height(node.right);
		if(rightheight>leftheight){
			return rightheight + 1;
		}else{
			return leftheight + 1;
		}
		
	}
	//
	// Purpose:
	//
	// Return a list of all the key/value Entrys stored in the tree
	// The list will be constructed by performing a level-order
	// traversal of the tree.
	public List<Entry<K,V> >	entryList() {
		List<Entry<K,V> > l = new LinkedList<Entry<K,V> >();
		LinkedList<BSTNode<K,V> > list = new LinkedList<BSTNode<K,V> > (); 
		list.add(root);
		BSTNode<K,V> temp;
		Entry<K,V> t;
		while(list.size()!=0){
			temp = list.remove();
			t = new Entry(temp.key, temp.value);
			l.add(t);
			if(temp.left != null){
				list.add(temp.left);
			}
			if(temp.right != null){
				list.add(temp.right);
			}
		}
		return l;
	}

	//
	// Purpose:
	//
	// Return a list of all the key/value Entrys stored in the tree
	// The list will be constructed by performing a traversal
	// specified by the parameter which.
	//
	// If which is:
	//	BST_PREORDER	perform a pre-order traversal
	//	BST_POSTORDER	perform a post-order traversal
	//	BST_INORDER	perform an in-order traversal
	//
	public List<Entry<K,V> >	entryList (int which) {
		List<Entry<K,V> > l = new LinkedList<Entry<K,V> >();
		if(which == BST_PREORDER)
			doPreOrder(root, l);
		if(which == BST_POSTORDER)
			doPostOrder(root, l);
		if(which == BST_INORDER)
			doInOrder(root, l);
		return l;
	}
	

	// Your instructor had the following private methods in their solution:
	private void doInOrder (BSTNode<K,V> n, List <Entry<K,V> > l){
		if(n == null)
			return;
		doInOrder(n.left,l);
		l.add(new Entry<K,V>(n.key, n.value));
		doInOrder(n.right,l);
	}
	private void doPreOrder (BSTNode<K,V> n, List <Entry<K,V> > l){
		if(n==null){
			return;
		}
		l.add(new Entry<K,V>(n.key, n.value));
		doPreOrder(n.left,l);
		doPreOrder(n.right, l);
	}
	private void doPostOrder (BSTNode<K,V> n, List <Entry<K,V> > l){
		if(n == null)
			return;
		doPostOrder(n.left, l);
		doPostOrder(n.right,l);
		l.add(new Entry<K,V>(n.key,n.value));
	}
	//private int doHeight (BSTNode<K,V> t)
}
