package Queues;

// INV: FIFO (First in - first out)
//      size >= 0
//      queue[head]..queue[tail] - queue
public class ArrayQueueADT {
    private int head = 0;
    private int size = 0;
    private Object[] elements = new Object[16];

    // PRE:  newSize >= 0
    //       queue - not null
    // POST: elements.length > newSize
    //       elements.length < 4 * newSize
    //       queue - immutable
    private static void ensureCapacity(ArrayQueueADT queue, int newSize) {
        if (newSize == queue.elements.length || (newSize > 3 && newSize == queue.elements.length / 4)) {
            newSize = (newSize == queue.elements.length ? queue.elements.length * 2 : queue.elements.length / 2);
            Object[] newElements = new Object[newSize];
            if (queue.head + size(queue) < queue.elements.length) {
                System.arraycopy(queue.elements, queue.head, newElements, 0, size(queue));
            } else {
                System.arraycopy(queue.elements, queue.head, newElements, 0, queue.elements.length - queue.head);
                System.arraycopy(queue.elements, 0, newElements, queue.elements.length - queue.head, (queue.head + size(queue)) % queue.elements.length);
            }
            queue.head = 0;
            queue.elements = newElements;
        }
    }

    // PRE:  queue - not nullable
    // POST: queue[tail] = element
    //       size' = size + 1
    //       queue[head]..queue[tail-1] - immutable
    public static void enqueue(ArrayQueueADT queue, Object element) {
        ensureCapacity(queue, size(queue) + 1);
        queue.elements[(queue.head + size(queue)) % queue.elements.length] = element;
        queue.size++;
    }

    // PRE:  size > 0
    //       queue - not nullable
    // POST: R = queue[head]
    //       queue - immutable
    public static Object element(ArrayQueueADT queue) {
        return queue.elements[queue.head];
    }

    // PRE:  size > 0
    //       queue - not nullable
    // POST: R = queue[head]
    //       queue[head] = queue[head+1]
    //       size' = size - 1
    //       queue[head+1]..queue[tail] - immutable
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue.size > 0;
        ensureCapacity(queue, size(queue) - 1);
        Object r = queue.elements[queue.head];
        queue.elements[queue.head] = null;
        queue.head = (queue.head + 1) % queue.elements.length;
        queue.size--;
        return r;
    }

    // PRE:  queue - not nullable
    // POST: R = size
    //       queue - immutable
    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }

    // PRE:  queue - not nullable
    // POST: queue - immutable
    //       R = (size == 0)
    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }

    // PRE:  queue - not nullable
    // POST: size == 0
    public static void clear(ArrayQueueADT queue) {
        while (queue.size > 0) {
            dequeue(queue);
        }
    }
}
