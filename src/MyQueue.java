import Queues.ArrayQueue;
import Queues.ArrayQueueADT;
import Queues.ArrayQueueModule;
import Queues.LinkedQueue;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class MyQueue {

    public static void main(String[] args) {
        ArrayQueue Q1 = new ArrayQueue();
        ArrayQueueADT Q2 = new ArrayQueueADT();
        ArrayQueueModule Q3 = new ArrayQueueModule();
        ArrayQueueModule Q4 = new ArrayQueueModule();
        ArrayQueueModule Q5 = new ArrayQueueModule();
        LinkedQueue Q6 = new LinkedQueue();
        for (int i = 0; i < 10; i++){
            Q1.enqueue(i);
            Q6.enqueue(i);
            ArrayQueueADT.enqueue(Q2, i);
            Q3.enqueue(i);
        }
        int sum1 = 0, sum2 = 0, sum3 = 0;
        for (int i = 0; i < 5; i++){
            sum1 += (Integer) Q1.dequeue();
            sum2 += (Integer) Q6.dequeue();
            sum3 += (Integer) ArrayQueueADT.dequeue(Q2);
        }
        System.out.println(sum1 + " " + sum2 + " " + sum3);
        System.out.println(Q1.element() + " " + Q6.element() + " " + ArrayQueueADT.element(Q2));
        System.out.println(Q1.size() + " " + Q6.size() + " " + ArrayQueueADT.size(Q2));
        Q1.clear();
        Q6.clear();
        ArrayQueueADT.clear(Q2);
        if (Q1.isEmpty()){
            System.out.println("ArrayQueue is empty");
        }
        if (Q6.isEmpty()){
            System.out.println("LinkedQueue is empty");
        }
        if (ArrayQueueADT.isEmpty(Q2)){
            System.out.println("ArrayQueueADT is empty");
        }

        for (int i = 0; i < 3; i++){
            Q4.enqueue(i + 10);
        }

        System.out.println(Q5.size());
    }

}