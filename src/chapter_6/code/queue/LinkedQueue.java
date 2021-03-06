package chapter_6.code.queue;

import chapter_3.code.SinglyLinkedList;

/*
 * Created by jjmacagnan on 16/05/17.
 */
public class LinkedQueue<E> implements Queue<E> {
    private SinglyLinkedList<E> list = new SinglyLinkedList<>();
    public LinkedQueue(){}

    @Override
    public int size() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        list.addLast(e);
    }

    @Override
    public E first() {
        return list.first();
    }

    @Override
    public E dequeue() {
        return list.removeFirst();
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public void concatenate(LinkedQueue<E> Q2) {
        while (!Q2.isEmpty()) {
            E e = Q2.dequeue();
            System.out.println("Concatenado elemento " + e + " Fila Q2 na fila Q1");
            list.addLast(e);
        }
    }


    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        System.out.println(queue.isEmpty());
        System.out.println(queue.size());
        System.out.println(queue.first());
        queue.dequeue();
        System.out.println(queue.first());
        System.out.println(queue.isEmpty());
        queue.dequeue();
        System.out.println(queue.isEmpty());
        System.out.println(queue.first());
    }
}
