package tests;

import jpize.util.array.FloatList;

public class InDev {

    public static void main(String[] args) {
        final FloatList list = new FloatList();
        list.add(54F);
        list.elementAdd(0, 46F);
        System.out.println(list.getLast());
    }

}
