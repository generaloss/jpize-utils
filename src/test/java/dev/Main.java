package dev;

import jpize.util.io.FastReader;
import jpize.util.res.Resource;

public class Main {

    public static void main(String[] args) {
        final FastReader reader = Resource.internal("/testfile1.txt").reader();
        while(reader.hasNext())
            System.out.println("'" + reader.nextWord() + "'");
    }

}
