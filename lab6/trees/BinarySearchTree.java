//----------------------------------------------------------------------------
// BinarySearchTree.java          by Dale/Joyce/Weems                Chapter 8
//
// Defines all constructs for a reference-based BST
//
// ajg version: format, protected-->private,  no iterative size()
//              only one iteration at a time so no arg for getNext()
//----------------------------------------------------------------------------

package lab6.trees;
import lab6.support.*;
import lab6.queues.*;




public class BinarySearchTree<T extends Comparable<T>> 
    implements BSTInterface<T> {
    private BSTNode<T> root=null;      // reference to the root of this BST
    boolean found;                     // used by remove
    private LinkedUnbndQueue<T> bSTQueue; // queue of info used for traversals

    public boolean isEmpty() { return (root == null); }

    public int size() { return recSize(root); }

    private int recSize(BSTNode<T> tree) {
	if (tree == null)    
	    return 0;
	return recSize(tree.getLeft()) + recSize(tree.getRight()) + 1;
    }

    // Does the BST contains an element e such that e.compareTo(element) == 0
    public boolean contains (T element) { return recContains(element, root); }
  
    private boolean recContains(T element, BSTNode<T> tree) {
	if (tree == null)
	    return false;       // element is not found
	if (element.compareTo(tree.getInfo()) == 0)
	    return true;        // element is found
	if (element.compareTo(tree.getInfo()) < 0)
	    return recContains(element, tree.getLeft()); // Search left subtree
	return recContains(element, tree.getRight());    // Search right subtree
    }

    // Return an element e that e.compareTo(element) == 0 or null if none exists
    public T get(T element) { return recGet(element, root); }

    // Returns an element e from tree such that e.compareTo(element) == 0;
    // if no such element exists, returns null.
    private T recGet(T element, BSTNode<T> tree) {
	if (tree == null)
	    return null;             // element is not found
	if (element.compareTo(tree.getInfo()) == 0)
	    return tree.getInfo();  // element is found
	if (element.compareTo(tree.getInfo()) < 0)
	    return recGet(element, tree.getLeft());   // get from left subtree
	return recGet(element, tree.getRight());      // get from right subtree
    }

    // Adds element to this BST.
    public void add (T element) { root = recAdd(element, root); }

    private BSTNode<T> recAdd(T element, BSTNode<T> tree) {
	if (tree == null)                          // insert new element here
	    tree = new BSTNode<T>(element);
	else if (element.compareTo(tree.getInfo()) <= 0) // Add in left subtree
	    tree.setLeft(recAdd(element, tree.getLeft()));
	else                                             // Add in right subtree
	    tree.setRight(recAdd(element, tree.getRight())); 
	return tree;
    }

    // Try to remove an element e with e.compareTo(element)==0;  return outcome
    public boolean remove (T element) {
	root = recRemove(element, root); // recRemove sets found
	return found;
    }

    private BSTNode<T> recRemove(T element, BSTNode<T> tree) {
	if (tree == null)
	    found = false;
	else if (element.compareTo(tree.getInfo()) == 0) { // remove this node
	    tree = removeNode(tree);
	    found = true;
	}
	else if (element.compareTo(tree.getInfo()) < 0) // remove a left node
	    tree.setLeft(recRemove(element, tree.getLeft()));
	else                                           // remove a right node
	    tree.setRight(recRemove(element, tree.getRight()));
	return tree;
    }

    // Remove the information at tree. If tree has < two children,
    // the node is removed; otherwise, the info is replaced by that in
    // the node's predecessor and the predecessor is removed.
    private BSTNode<T> removeNode(BSTNode<T> tree) {
	if (tree.getLeft() == null)
	    return tree.getRight();
	if (tree.getRight() == null) 
	    return tree.getLeft();
	T data;
	data = getPredecessor(tree.getLeft());
	tree.setInfo(data);
	tree.setLeft(recRemove(data, tree.getLeft()));  
	return tree;
    }

    // Return the information held in precessor
    private T getPredecessor(BSTNode<T> tree) {
	tree = tree.getLeft(); // go left then keep going right
	while (tree.getRight() != null)
	    tree = tree.getRight();
	return tree.getInfo();
    }

    // Populates a queue with the tree elements in the given order
    // Returns current number of nodes in the BST.
    public int reset(int orderType) {
	bSTQueue = new LinkedUnbndQueue<T>();
	if (orderType == INORDER)
	    inOrder(root);
	else if (orderType == PREORDER)
	    preOrder(root);
	else if (orderType == POSTORDER)
	    postOrder(root);
	return size();
    }

    // Return the element at the current position
    // and advance current position based on orderType. 
    // Preconditions: The BST is not empty
    //                The BST has been reset
    //                The BST has not been modified since the most recent reset
    //                The end of the iteration has not been reached
    public T getNext () { return bSTQueue.dequeue(); };

    // Initializes bSTQueue with tree elements in inOrder order.
    private void inOrder(BSTNode<T> tree) {
	if (tree != null) {
	    inOrder(tree.getLeft());
	    bSTQueue.enqueue(tree.getInfo());
	    inOrder(tree.getRight());
		}
    }

    // Initializes bSTQueue with tree elements in preOrder order.
    private void preOrder(BSTNode<T> tree) {
	if (tree != null) {
	    bSTQueue.enqueue(tree.getInfo());
	    preOrder(tree.getLeft());
	    preOrder(tree.getRight());
		}
    }

    // Initializes bSTQueue with tree elements in postOrder order.
    private void postOrder(BSTNode<T> tree) {
	if (tree != null) {
	    postOrder(tree.getLeft());
	    postOrder(tree.getRight());
	    bSTQueue.enqueue(tree.getInfo());
		}
    }
    
    public int height()
    {
    	return height1(root);
    }
    
    private int height1(BSTNode<T> tree){
    	if(tree==null)
    		return 0;
    	else
    		return 1+ Math.max(height1(tree.getLeft()), 
				height1(tree.getRight()));
		
    }
}
	

// Local Variables:
// compile-command: "export CLASSPATH=../..:.; \
// cd ../..; javac ch08/trees/BinarySearchTree.java"
// End:
