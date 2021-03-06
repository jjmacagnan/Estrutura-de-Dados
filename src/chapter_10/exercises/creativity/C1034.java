package chapter_10.exercises.creativity;

import chapter_10.code.hash_tables.ChainHashMap;

public class C1034 {


    public static void main(String[] args) {

        ChainHashMap chain = new ChainHashMap(5);

        chain.putIfAbsent(1, "A");
        chain.putIfAbsent(2, "B");

        System.out.println(chain.get(1));
        System.out.println(chain.get(2));
    }

}
