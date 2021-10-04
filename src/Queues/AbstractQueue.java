package Queues;

import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractQueue implements Queue {
    private int size = 0;

    protected abstract void doEnqueue(Object element);

    protected abstract Object doDequeue();

    protected abstract void doPush(Object element);

    public void enqueue(Object element) {
        doEnqueue(element);
        size++;
    }

    public Object element() {
        assert size > 0;
        Object r = dequeue();
        push(r);
        return r;
    }

    public Object dequeue() {
        assert size > 0;
        Object r = doDequeue();
        size--;
        return r;
    }

    public int size() {
        return size;
    }

    public void clear() {
        while (size > 0) {
            dequeue();
        }
    }

    public final boolean isEmpty() {
        return size == 0;
    }

    public void push(Object element) {
        doPush(element);
        size++;
    }
}