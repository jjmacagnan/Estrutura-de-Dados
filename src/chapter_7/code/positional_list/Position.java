package chapter_7.code.positional_list;

/*
 * Created by JJMacagnan on 21/05/2017.
 */
public interface Position<E> {

    E getElement() throws IllegalStateException;
}
