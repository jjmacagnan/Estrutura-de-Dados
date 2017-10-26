package prova_2.capitulo_8;

import prova_2.capitulo_7.positional_list.Position;

/*
 * Created by jjmacagnan on 27/05/2017.
 */
public interface BinaryTree<E> extends Tree<E> {
    Position<E> left(Position<E> p) throws IllegalStateException;
    Position<E> right(Position<E> p) throws IllegalStateException;
    Position<E> sibling(Position<E> p) throws IllegalStateException;
}
