package tests;

import jpize.util.color.Color;

public class InDev {

    public static void main(String[] args) {
        Color c = new Color();

        c.set(0x1234abff);

        System.out.println(c.getHexString());
    }

}
