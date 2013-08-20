//---------------------------------------------------------------------------
// LinkedUnbndQueue.java         by Dale/Joyce/Weems                Chapter 5
//
// Implements UnboundedQueueInterface using a linked list
// AJG Version: format; protected->private
//---------------------------------------------------------------------------

package lab6.queues;
import lab6.support.*;




public class LinkedUnbndQueue<T> implements UnboundedQueueInterface<T> {

    private LLNode<T> front;   // reference to the front of this queue
    private LLNode<T> rear;    // reference to the rear of this queue

    public LinkedUnbndQueue() {
	front = null;
	rear = null;
    }

    // Adds element to the rear of this queue.
    public void enqueue(T element) { 
	LLNode<T> newNode = new LLNode<T>(element);
	if (rear == null)
	    front = newNode;
	else
	    rear.setLink(newNode);
	rear = newNode;
    }     

    // Throws QueueUnderflowException if this queue is empty;
    // otherwise, removes front element from this queue and returns it.
    public T dequeue() {
	if (isEmpty())
	    throw new QueueUnderflowException("Helpful msg.");
	T ans = front.getInfo();
	front = front.getLink();
	if (front == null)
	    rear = null;
	return ans;
    }

    public boolean isEmpty() { return (front == null); }
}
// Local Variables:
// compile-command: "cd ../..; javac ch05/queues/LinkedUnbndQueue.java"
// End:
