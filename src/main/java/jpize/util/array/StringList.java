package jpize.util.array;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;

public class StringList implements Iterable<String> {

    public static final int DEFAULT_CAPACITY = 3;

    private String[] array;
    private int size;

    public StringList() {
        this(DEFAULT_CAPACITY);
    }

    public StringList(int capacity) {
        if(capacity < 0)
            throw new IllegalArgumentException();
        this.array = new String[capacity];
    }

    public StringList(String... items) {
        this.size = items.length;
        this.array = items;
    }

    public StringList(StringList list) {
        this.size = list.size;
        this.array = list.copyOf();
    }


    public StringList(Iterable<?> iterable) {
        this.array = new String[1];
        addAll(iterable);
        this.trim();
    }

    public StringList(Collection<?> collection) {
        this.array = new String[collection.size()];
        addAll(collection);
    }

    public<T> StringList(Iterable<T> iterable, Function<T, String> func) {
        this.array = new String[1];
        addAll(iterable, func);
        this.trim();
    }

    public<T> StringList(Collection<T> collection, Function<T, String> func) {
        this.array = new String[collection.size()];
        addAll(collection, func);
    }

    public<T> StringList(T[] array, Function<T, String> func) {
        this.array = new String[array.length];
        addAll(array, func);
    }


    public String[] array() {
        return array;
    }

    public String[] arrayTrimmed() {
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
            array = new String[Math.max(minCapacity, DEFAULT_CAPACITY)];
        }else{
            final int newCapacity = ArraysSupport.newLength(oldCapacity, minCapacity - oldCapacity, oldCapacity >> 1);
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    private void grow() {
        grow(size + 1);
    }


    public void add(String element) {
        if(size == array.length)
            grow();

        array[size] = element;
        size++;
    }

    public void add(String... elements) {
        if(size + elements.length >= array.length)
            grow(size + elements.length);

        System.arraycopy(elements, 0, array, size, elements.length);
        size += elements.length;
    }

    public void add(StringList list) {
        if(list.size() == list.capacity())
            add(list.array());
        else
            add(list.arrayTrimmed());
    }


    public void add(int i, String element) {
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

    public void add(int i, String... elements) {
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


    public void addAll(Iterable<?> iterable) {
        for(Object object: iterable)
            this.add(object.toString());
    }

    public void addAll(Collection<?> collection) {
        for(Object object: collection)
            this.add(object.toString());
    }

    public <T> void addAll(Iterable<T> iterable, Function<T, String> func) {
        for(T object: iterable)
            this.add(func.apply(object));
    }

    public <T> void addAll(Collection<T> collection, Function<T, String> func) {
        for(T object: collection)
            this.add(func.apply(object));
    }

    public <T> void addAll(T[] array, Function<T, String> func) {
        for(T object: array)
            this.add(func.apply(object));
    }


    public void remove(int i, int len) {
        len = Math.min(len, size - i);
        if(len <= 0)
            return;

        final int newCapacity = array.length - len;
        final String[] copy = new String[newCapacity];

        System.arraycopy(array, 0, copy, 0, i);
        System.arraycopy(array, i + len, copy, i, newCapacity - i);
        array = copy;

        size -= len;
    }

    public void remove(int i) {
        remove(i, 1);
    }

    public void removeFirst(String value) {
        final int index = indexOf(value);
        if(index > -1)
            remove(index);
    }

    public void removeLast(String value) {
        final int index = lastIndexOf(value);
        if(index > -1)
            remove(index);
    }


    public boolean contains(String element) {
        return indexOf(element) != -1;
    }

    public int indexOf(String element) {
        return indexOfRange(element, 0, size);
    }

    public int lastIndexOf(String element) {
        return lastIndexOfRange(element, 0, size);
    }

    public int indexOfRange(String element, int start, int end) {
        for(int i = start; i < end; i++)
            if(array[i].equals(element))
                return i;
        return -1;
    }

    public int lastIndexOfRange(String element, int start, int end) {
        for(int i = end - 1; i >= start; i--)
            if(array[i].equals(element))
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
        Arrays.fill(array, 0, size, null);
        size = 0;
    }

    public void fill(String value) {
        Arrays.fill(array, 0, size, value);
    }


    public void trim() {
        array = Arrays.copyOf(array, size);
    }

    public void capacity(int newCapacity) {
        if(newCapacity == 0)
            array = new String[0];
        else
            array = Arrays.copyOf(array, newCapacity);
        size = Math.min(size, newCapacity);
    }


    public String get(int i) {
        return array[i];
    }

    public void set(int i, String newValue) {
        array[i] = newValue;
    }


    public void valAdd(int i, String value) {
        array[i] += value;
    }

    public void valTrim(int i) {
        array[i] = array[i].trim();
    }

    public void valReplace(int i, char oldChar, char newChar) {
        array[i] = array[i].replace(oldChar, newChar);
    }

    public void valReplace(int i, CharSequence target, CharSequence replacement) {
        array[i] = array[i].replace(target, replacement);
    }

    public void valReplaceAll(int i, String regex, String replacement) {
        array[i] = array[i].replaceAll(regex, replacement);
    }

    public void valReplaceFirst(int i, String regex, String replacement) {
        array[i] = array[i].replaceFirst(regex, replacement);
    }

    public void valToLowerCase(int i) {
        array[i] = array[i].toLowerCase();
    }

    public void valToUpperCase(int i) {
        array[i] = array[i].toUpperCase();
    }


    public String[] copyOf(int offset, int newLength) {
        final String[] slice = new String[newLength];
        System.arraycopy(array, offset, slice, 0, newLength);
        return slice;
    }

    public String[] copyOf(int newLength) {
        return copyOf(0, newLength);
    }

    public String[] copyOf() {
        return copyOf(array.length);
    }

    public String[] copyOfRange(int from, int to) {
        return copyOf(from, to - from);
    }


    public void copyTo(String[] dst, int offset, int length) {
        System.arraycopy(array, 0, dst, offset, length);
    }

    public void copyTo(String[] dst, int offset) {
        copyTo(dst, offset, array.length);
    }

    public void copyTo(String[] dst) {
        copyTo(dst, 0);
    }


    public StringList copy() {
        return new StringList(this);
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
        final StringList list = (StringList) object;
        return (size == list.size && Objects.deepEquals(array, list.array));
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(array), size);
    }

    @Override
    @NotNull
    public Iterator<String> iterator() {
        return new Iterator<>() {
            private int index;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public String next() {
                return array[index++];
            }
        };
    }

}
