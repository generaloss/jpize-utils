package unit;

import jpize.util.array.CharList;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ListTests {

    @Test
    public void char_addAtIndex() {
        final CharList list = new CharList('a', 'b', 'e');

        // insert 'c' and 'd' before 'e'
        list.add(2, 'c', 'd');

        Assert.assertEquals(5, list.size());
        Assert.assertEquals('a', list.get(0));
        Assert.assertEquals('b', list.get(1));
        Assert.assertEquals('c', list.get(2));
        Assert.assertEquals('d', list.get(3));
        Assert.assertEquals('e', list.get(4));
    }

    @Test
    public void char_addFirst() {
        final CharList list = new CharList('b', 'c');

        // add 'a' to the beginning
        list.addFirst('a');

        Assert.assertEquals(3, list.size());
        Assert.assertEquals('a', list.get(0));
    }

    @Test
    public void char_removeRange() {
        final CharList list = new CharList('a', 'b', 'c', 'd', 'e');

        list.remove(1, 2); // ('a', 'd', 'e')
        Assert.assertEquals(3, list.size());
        Assert.assertEquals('a', list.get(0));
        Assert.assertEquals('d', list.get(1));
        Assert.assertEquals('e', list.get(2));

        list.remove(0, 1); // ('d', 'e')
        Assert.assertEquals(2, list.size());
        Assert.assertEquals('d', list.get(0));

        list.remove(1, 5); // out of bounds removing (without exception)
        Assert.assertEquals(1, list.size());
        Assert.assertEquals('d', list.get(0));

        list.remove(0, 1); // empty list
        Assert.assertTrue(list.isEmpty());

        // check that condition (len <= 0) changes nothing
        list.add('x', 'y', 'z');
        list.remove(1, 0);
        Assert.assertEquals(3, list.size());
        Assert.assertEquals('x', list.get(0));
        Assert.assertEquals('y', list.get(1));
        Assert.assertEquals('z', list.get(2));
    }

    @Test
    public void char_trim() {
        final CharList list = new CharList(20);
        list.add('x', 'y', 'z');

        // shrink the capacity to fit current size
        int oldCapacity = list.capacity();
        list.trim();

        Assert.assertEquals(3, list.capacity());
        Assert.assertTrue(oldCapacity > list.capacity());
    }

    @Test
    public void char_copyOfRange() {
        final CharList list = new CharList('a', 'b', 'c', 'd', 'e');

        // copy subrange 'b', 'c', 'd'
        final char[] sub = list.copyOfRange(1, 4);

        Assert.assertArrayEquals(new char[]{'b', 'c', 'd'}, sub);
    }

    @Test
    public void char_addBeyondSize() {
        final CharList list = new CharList('a', 'b');

        // add 'x' at index 4 (beyond current size), fills with '\0'
        list.add(4, 'x');

        Assert.assertEquals(5, list.size());
        Assert.assertEquals('x', list.get(4));
        Assert.assertEquals('\0', list.get(2));
        Assert.assertEquals('\0', list.get(3));
    }

    @Test
    public void char_copy() {
        final CharList original = new CharList('1', '2', '3');

        // create a copy
        final CharList copy = original.copy();

        Assert.assertEquals(original, copy);

        // modify the copy and ensure original is unaffected
        copy.set(0, '9');
        Assert.assertNotSame(original, copy);
    }

    @Test
    public void char_iterable() {
        final CharList list = new CharList('a', 'b', 'c');

        // iterate over the list and build a string
        final StringBuilder sb = new StringBuilder();
        for(Character ch: list)
            sb.append(ch);

        Assert.assertEquals("abc", sb.toString());
    }

    @Test
    public void char_hashCodeAndEquals() {
        final CharList list1 = new CharList('a', 'b', 'c');
        final CharList list2 = new CharList('a', 'b', 'c');

        // test equality and matching hash codes
        Assert.assertEquals(list1, list2);
        Assert.assertEquals(list1.hashCode(), list2.hashCode());
    }

    @Test
    public void char_fill() {
        final CharList list = new CharList('x', 'x', 'x');

        // fill all elements with 'y'
        list.fill('y');

        Assert.assertEquals('y', list.get(0));
        Assert.assertEquals('y', list.get(1));
        Assert.assertEquals('y', list.get(2));
    }

    @Test
    public void char_addAllCollection() {
        final CharList list = new CharList();

        // add characters from a standard java list
        list.addAll(List.of('a', 'b', 'c'));

        Assert.assertEquals(3, list.size());
        Assert.assertEquals('a', list.get(0));
    }

}
