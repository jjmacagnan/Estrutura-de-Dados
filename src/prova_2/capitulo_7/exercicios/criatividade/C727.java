package prova_2.capitulo_7.exercicios.criatividade;

import prova_2.capitulo_7.array_list.ArrayList;
import prova_2.capitulo_7.array_list.List;

/*
 * Created by jjmacagnan on 06/06/2017.
 */
/*C-7.27 Modifique nossa implementação ArrayList para suportar a interface Cloneable, conforme descrito na Seção 3.6.*/
public class C727 {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();

        list.add(0, "A");
        list.add(1, "E");
        list.add(2, "I");
        list.add(3, "O");
        list.add(4, "U");

        try {
            List list1 = list.clone();

            while (!list1.isEmpty()) {
                System.out.println("remove elemento clone: " + list1.remove(0));
            }


        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println("size array original: " + list.size());


    }
}
