package Queues;

import java.util.function.Function;
import java.util.function.Predicate;

// INV: FIFO (First in - first out)
//      size >= 0
//      queue[head]..queue[tail] - queue
public interface Queue {

    // PRE:  None
    // POST: queue[tail] = element
    //       size' = size + 1
    //       queue[head]..queue[tail-1] - immutable
    void enqueue(Object element);

    // PRE:  size > 0
    // POST: R = queue[head]
    //       queue - immutable
    Object element();

    // PRE:  size > 0
    // POST: R = queue[head]
    //       queue'[head] = queue[head+1]
    //       size' = size - 1
    //       queue[head+1]..queue[tail] - immutable
    Object dequeue();

    // PRE:  None
    // POST: R = size
    //       queue - immutable
    int size();

    // PRE:  None
    // POST: queue - immutable
    //       R = (size == 0)
    boolean isEmpty();

    // PRE:  None
    // POST: size = 0
    void clear();

    // PRE:  None
    // POST: queue[head] = element
    //       size' = size + 1
    //       queue[head + 1]..queue[tail] - immutable
    void push(Object element);
}