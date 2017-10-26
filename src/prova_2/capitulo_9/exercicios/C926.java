package prova_2.capitulo_9.exercicios;

import prova_2.capitulo_6.queue.Queue;
import prova_2.capitulo_6.stack.Stack;
import prova_2.capitulo_9.Heap.HeapPriorityQueue;
import prova_2.capitulo_9.PriorityQueue.PriorityQueue;

/*
 * Created by jjmacagnan on 17/06/2017.
 */
/*Show how to implement the FIFO queue ADT using only a priority queue and
one additional integer instance variable.*/
public class C926<E> implements Queue<E> {
    private PriorityQueue queue = new HeapPriorityQueue();
    private int x;

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        queue.insert(x++, e);
    }

    @Override
    public E first() {
        return (E) queue.min().getValue();
    }

    @Override
    public E dequeue() {
        return (E) queue.removeMin().getValue();
    }

    public static void main(String[] args) {

        Queue queue = new C926<>();


        queue.enqueue("A");
        queue.enqueue("C");
        queue.enqueue("J");
        queue.enqueue("K");
        queue.enqueue("V");


        System.out.println(queue.first());
        System.out.println(queue.dequeue());


    }
}
