//----------------------------------------------------------------------------
// BSTNode.java               by Dale/Joyce/Weems                    Chapter 8
//
// Implements Comparable nodes for a binary search tree.
//
// ajg version: format, protected-->private
//----------------------------------------------------------------------------

package lab6.support;

// Used to hold references to BST nodes for the linked implementation
public class BSTNode<T extends Comparable<T>> {
    private T info;                        // The info in a BST node
    private BSTNode<T> left  = null;       // A link to the left child node
    private BSTNode<T> right = null;       // A link to the right child node

    public BSTNode(T info) { this.info = info; }

    public T getInfo()          { return info; }
    public void setInfo(T info) { this.info = info; }

    public BSTNode<T> getLeft()          { return left; }
    public void setLeft(BSTNode<T> link) { left = link; }

    public BSTNode<T> getRight()          { return right; }
    public void setRight(BSTNode<T> link) { right = link; }
}
// Local Variables:
// compile-command: "cd ..; javac support/BSTNode.java"
// End:
