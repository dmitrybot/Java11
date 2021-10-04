package Queues;

// INV: FIFO (First in - first out)
//      size >= 0
//      queue[head]..queue[tail] - queue
public class ArrayQueueModule {
    private static int head = 0;
    private static int size = 0;
    private static Object[] elements = new Object[16];

    // PRE:  newSize >= 0
    // POST: elements.length > newSize
    //       elements.length < 4 * newSize
    //       queue - immutable
    private static void ensureCapacity(int newSize) {
        if (newSize == elements.length || (newSize > 3 && newSize == elements.length / 4)) {
            newSize = (newSize == elements.length ? elements.length * 2 : elements.length / 2);
            Object[] newElements = new Object[newSize];
            if (head + size() < elements.length) {
                System.arraycopy(elements, head, newElements, 0, size());
            } else {
                System.arraycopy(elements, head, newElements, 0, elements.length - head);
                System.arraycopy(elements, 0, newElements, elements.length - head, (head + size()) % elements.length);
            }
            head = 0;
            elements = newElements;
        }
    }

    // PRE:  None
    // POST: queue[tail] = element
    //       size' = size + 1
    //       queue[head]..queue[tail-1] - immutable
    public static void enqueue(Object element) {
        ensureCapacity(size() + 1);
        elements[(head + size()) % elements.length] = element;
        size++;
    }

    // PRE:  size > 0
    // POST: R = queue[head]
    //       queue - immutable
    public static Object element() {
        return elements[head];
    }

    // PRE:  size > 0
    // POST: R = queue[head]
    //       queue[head] = queue[head+1]
    //       size' = size - 1
    //       queue[head+1]..queue[tail] - immutable
    public static Object dequeue() {
        assert size > 0;
        ensureCapacity(size() - 1);
        Object r = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;
        return r;
    }

    // PRE:  None
    // POST: R = size
    //       queue - immutable
    public static int size() {
        return size;
    }

    // PRE:  None
    // POST: queue - immutable
    //       R = (size == 0)
    public static boolean isEmpty() {
        return size == 0;
    }

    // PRE:  None
    // POST: size == 0
    public static void clear() {
        while (size > 0) {
            dequeue();
        }
    }
}