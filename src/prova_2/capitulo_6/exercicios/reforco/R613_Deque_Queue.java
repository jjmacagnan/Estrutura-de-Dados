package prova_2.capitulo_6.exercicios.reforco;

import prova_1.capitulo_3.codigos_livro.SinglyLinkedList;
import prova_2.capitulo_6.deque.Deque;
import prova_2.capitulo_6.deque.LinkedDeque;
import prova_2.capitulo_6.queue.LinkedQueue;
import prova_2.capitulo_6.queue.Queue;

/*
 * Created by jjmacagnan on 30/05/2017.
 */

/*Suppose you have a deque D containing the numbers (1,2,3,4,5,6,7,8), in this
        order. Suppose further that you have an initially empty queue Q. Give a code
        fragment that uses only D and Q (and no other variables) and results in D storing
        the elements in the order (1,2,3,5,4,6,7,8).*/
public class R613_Deque_Queue {
    public static void main(String[] args) {
        LinkedDeque<Integer> D = new LinkedDeque<>();
        LinkedQueue<Integer> Q = new LinkedQueue<>();

        D.addFirst(1);
        D.addLast(2);
        D.addLast(3);
        D.addLast(4);
        D.addLast(5);
        D.addLast(6);
        D.addLast(7);
        D.addLast(8);

        while (D.size() > 0) {
            Q.enqueue(D.removeFirst());
        }

        System.out.println("imprimindo fila");
        Q.imprime();

        while (!Q.isEmpty()) {
            D.addLast(Q.dequeue());
        }

    }
}
