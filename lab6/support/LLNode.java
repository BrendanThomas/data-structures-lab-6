//----------------------------------------------------------------------------
// LLNode.java            by Dale/Joyce/Weems                  Chapter 3
//
// Implements <T> nodes for a Linked List.
// ajg version: format
//----------------------------------------------------------------------------

package lab6.support;
public class LLNode<T> {
  private LLNode<T> link = null;
  private T info;
  
  public LLNode(T info) { this.info = info; }
 
  public void setInfo(T info) { this.info = info; }

  public T getInfo() { return info; }
 
  public void setLink(LLNode<T> link) { this.link = link; }

  public LLNode<T> getLink() { return link; }
}
// Local Variables:
// compile-command: "cd ..; javac support/LLNode.java"
// End:
