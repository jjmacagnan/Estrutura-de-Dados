package prova_1.capitulo_3.exercicios.criatividade;

import prova_1.capitulo_3.codigos_livro.CircularlyLinkedList;

/*Given a circularly linked list L containing an even number of nodes, describe
how to cardShuffle L into two circularly linked lists of half the size.*/
public class C330 {

    public static void main(String[] args) {
        CircularlyLinkedList list = new CircularlyLinkedList();
        CircularlyLinkedList list1;

        list.addFirst("A");
        list.addFirst("B");
        list.addFirst("C");
        list.addFirst("D");

        System.out.println("Lista original: " + list.toString());

        CircularlyLinkedList.split(list);

    }
}
