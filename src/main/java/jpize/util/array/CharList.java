package jpize.util.array;

import org.jetbrains.annotations.NotNull;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;

public class CharList implements Iterable<Character> {

    public static final int DEFAULT_CAPACITY = 10;

    private char[] array;
    private int size;

    public CharList() {
        this(DEFAULT_CAPACITY);
    }

    public CharList(int capacity) {
        if(capacity < 0)
            throw new IllegalArgumentException();
        this.array = new char[capacity];
    }

    public CharList(char... items) {
        this.size = items.length;
        this.array = items;
    }

    public CharList(CharList list) {
        this.size = list.size;
        this.array = list.copyOf();
    }

    public CharList(CharBuffer buffer) {
        this.array = new char[buffer.limit()];
        addAll(buffer);
    }

    public CharList(Iterable<Character> iterable) {
        this.array = new char[1];
        addAll(iterable);
        this.trim();
    }

    public CharList(Collection<Character> collection) {
        this.array = new char[collection.size()];
        addAll(collection);
    }

    public<T> CharList(Iterable<T> iterable, Function<T, Character> func) {
        this.array = new char[1];
        addAll(iterable, func);
        this.trim();
    }

    public<T> CharList(Collection<T> collection, Function<T, Character> func) {
        this.array = new char[collection.size()];
        addAll(collection, func);
    }

    public<T> CharList(T[] array, Function<T, Character> func) {
        this.array = new char[array.length];
        addAll(array, func);
    }


    public char[] array() {
        return array;
    }

    public char[] arrayTrimmed() {
        return Arrays.copyOf(array, size);
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return array.length;
    }

    private void grow(int minCapacity) {
        final int oldCapacity = array.length;
        if(oldCapacity == 0){
            array = new char[Math.max(minCapacity, DEFAULT_CAPACITY)];
        }else{
            final int newCapacity = ArraysSupport.newLength(oldCapacity, minCapacity - oldCapacity, oldCapacity >> 1);
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    private void grow() {
        grow(size + 1);
    }


    public void add(char element) {
        if(size == array.length)
            grow();

        array[size] = element;
        size++;
    }

    public void add(char... elements) {
        if(size + elements.length >= array.length)
            grow(size + elements.length);

        System.arraycopy(elements, 0, array, size, elements.length);
        size += elements.length;
    }

    public void add(CharList list) {
        if(list.size() == list.capacity())
            add(list.array());
        else
            add(list.arrayTrimmed());
    }


    public void add(int i, char element) {
        final int minCapacity = Math.max(size, i) + 1;
        if(minCapacity >= array.length)
            grow(minCapacity);

        if(size != 0 && size >= i)
            System.arraycopy(array, i, array, i + 1, size - i);

        array[i] = element;

        final int growth = minCapacity - size;
        if(growth > 0)
            size += growth;
    }

    public void add(int i, char... elements) {
        if(elements.length == 0)
            return;

        final int minCapacity = Math.max(size, i) + elements.length;
        if(minCapacity >= array.length)
            grow(minCapacity);

        if(size != 0 && size >= i)
            System.arraycopy(array, i, array, i + elements.length, size - i);
        System.arraycopy(elements, 0, array, i, elements.length);

        final int growth = minCapacity - size;
        if(growth > 0)
            size += growth;
    }


    public void addAll(CharBuffer buffer) {
        for(int i = buffer.position(); i < buffer.limit(); i++)
            this.add(buffer.get(i));
    }

    public void addAll(Iterable<Character> iterable) {
        for(Character item: iterable)
            this.add(item);
    }

    public void addAll(Collection<Character> collection) {
        for(Character item: collection)
            this.add(item);
    }

    public <T> void addAll(Iterable<T> iterable, Function<T, Character> func) {
        for(T object: iterable)
            this.add(func.apply(object));
    }

    public <T> void addAll(Collection<T> collection, Function<T, Character> func) {
        for(T object: collection)
            this.add(func.apply(object));
    }

    public <T> void addAll(T[] array, Function<T, Character> func) {
        for(T object: array)
            this.add(func.apply(object));
    }


    public void remove(int i, int len) {
        len = Math.min(len, size - i);
        if(len <= 0)
            return;

        final int newCapacity = array.length - len;
        final char[] copy = new char[newCapacity];

        System.arraycopy(array, 0, copy, 0, i);
        System.arraycopy(array, i + len, copy, i, newCapacity - i);
        array = copy;

        size -= len;
    }

    public void remove(int i) {
        remove(i, 1);
    }

    public void removeFirst(char value) {
        final int index = indexOf(value);
        if(index > -1)
            remove(index);
    }

    public void removeLast(char value) {
        final int index = lastIndexOf(value);
        if(index > -1)
            remove(index);
    }


    public boolean contains(char element) {
        return indexOf(element) != -1;
    }

    public int indexOf(char element) {
        return indexOfRange(element, 0, size);
    }

    public int lastIndexOf(char element) {
        return lastIndexOfRange(element, 0, size);
    }

    public int indexOfRange(char element, int start, int end) {
        for(int i = start; i < end; i++)
            if(array[i] == element)
                return i;
        return -1;
    }

    public int lastIndexOfRange(char element, int start, int end) {
        for(int i = end - 1; i >= start; i--)
            if(array[i] == element)
                return i;
        return -1;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isNotEmpty() {
        return size != 0;
    }

    public void clear() {
        Arrays.fill(array, 0, size, (char) 0);
        size = 0;
    }

    public void trim() {
        array = Arrays.copyOf(array, size);
    }


    public void capacity(int newCapacity) {
        if(newCapacity == 0)
            array = new char[0];
        else
            array = Arrays.copyOf(array, newCapacity);
        size = Math.min(size, newCapacity);
    }


    public char get(int i) {
        return array[i];
    }

    public void set(int i, char newValue) {
        array[i] = newValue;
    }
    public void valAdd(int i, char value) {
        array[i] += value;
    }

    public void valSub(int i, char value) {
        array[i] -= value;
    }

    public void valMul(int i, char value) {
        array[i] *= value;
    }

    public void valDiv(int i, char value) {
        array[i] /= value;
    }


    public char[] copyOf(int offset, int newLength) {
        final char[] slice = new char[newLength];
        System.arraycopy(array, offset, slice, 0, newLength);
        return slice;
    }

    public char[] copyOf(int newLength) {
        return copyOf(0, newLength);
    }

    public char[] copyOf() {
        return copyOf(array.length);
    }

    public char[] copyOfRange(int from, int to) {
        return copyOf(from, to - from);
    }


    public void copyTo(char[] dst, int offset, int length) {
        System.arraycopy(array, 0, dst, offset, length);
    }

    public void copyTo(char[] dst, int offset) {
        copyTo(dst, offset, array.length);
    }

    public void copyTo(char[] dst) {
        copyTo(dst, 0);
    }


    public CharList copy() {
        return new CharList(this);
    }


    @Override
    public String toString() {
        return Arrays.toString(arrayTrimmed());
    }

    @Override
    public boolean equals(Object object) {
        if(this == object)
            return true;
        if(object == null || getClass() != object.getClass())
            return false;
        final CharList list = (CharList) object;
        return (size == list.size && Objects.deepEquals(array, list.array));
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(array), size);
    }

    @Override
    @NotNull
    public Iterator<Character> iterator() {
        return new Iterator<>() {
            private int index;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public Character next() {
                return array[index++];
            }
        };
    }

}
