//----------------------------------------------------------------------------
// QueueInterface.java           by Dale/Joyce/Weems                 Chapter 5
//
// Interface for a class that implements a queue of T.
// A queue is a "first in, first out" structure.
// ajg version: format
//----------------------------------------------------------------------------

package lab6.queues;

public interface QueueInterface<T> {

  T dequeue() throws QueueUnderflowException;
  // Throws QueueUnderflowException if this queue is empty;
  // otherwise, removes front element from this queue and returns it.

  boolean isEmpty();
}




