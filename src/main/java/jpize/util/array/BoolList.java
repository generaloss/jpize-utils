package jpize.util.array;

import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;

public class BoolList implements Iterable<Boolean> {

    public static final int DEFAULT_CAPACITY = 10;

    private boolean[] array;
    private int size;

    public BoolList() {
        this(DEFAULT_CAPACITY);
    }

    public BoolList(int capacity) {
        if(capacity < 0)
            throw new IllegalArgumentException();
        this.array = new boolean[capacity];
    }

    public BoolList(boolean... items) {
        this.size = items.length;
        this.array = items;
    }

    public BoolList(BoolList list) {
        this.size = list.size;
        this.array = list.copyOf();
    }

    public BoolList(ByteBuffer buffer) {
        this.array = new boolean[buffer.limit()];
        addAll(buffer);
    }

    public BoolList(Iterable<Boolean> iterable) {
        this.array = new boolean[1];
        addAll(iterable);
        this.trim();
    }

    public BoolList(Collection<Boolean> collection) {
        this.array = new boolean[collection.size()];
        addAll(collection);
    }

    public<T> BoolList(Iterable<T> iterable, Function<T, Boolean> func) {
        this.array = new boolean[1];
        addAll(iterable, func);
        this.trim();
    }

    public<T> BoolList(Collection<T> collection, Function<T, Boolean> func) {
        this.array = new boolean[collection.size()];
        addAll(collection, func);
    }

    public<T> BoolList(T[] array, Function<T, Boolean> func) {
        this.array = new boolean[array.length];
        addAll(array, func);
    }


    public boolean[] array() {
        return array;
    }

    public boolean[] arrayTrimmed() {
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
            array = new boolean[Math.max(minCapacity, DEFAULT_CAPACITY)];
        }else{
            final int newCapacity = ArraysSupport.newLength(oldCapacity, minCapacity - oldCapacity, oldCapacity >> 1);
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    private void grow() {
        grow(size + 1);
    }


    public void add(boolean element) {
        if(size == array.length)
            grow();

        array[size] = element;
        size++;
    }

    public void add(boolean... elements) {
        if(size + elements.length >= array.length)
            grow(size + elements.length);

        System.arraycopy(elements, 0, array, size, elements.length);
        size += elements.length;
    }

    public void add(BoolList list) {
        if(list.size() == list.capacity())
            add(list.array());
        else
            add(list.arrayTrimmed());
    }


    public void add(int i, boolean element) {
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

    public void add(int i, boolean... elements) {
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


    public void addAll(ByteBuffer buffer) {
        for(int i = buffer.position(); i < buffer.limit(); i++)
            this.add(buffer.get(i) == 1);
    }

    public void addAll(Iterable<Boolean> iterable) {
        for(Boolean item: iterable)
            this.add(item);
    }

    public void addAll(Collection<Boolean> collection) {
        for(Boolean item: collection)
            this.add(item);
    }

    public <T> void addAll(Iterable<T> iterable, Function<T, Boolean> func) {
        for(T object: iterable)
            this.add(func.apply(object));
    }

    public <T> void addAll(Collection<T> collection, Function<T, Boolean> func) {
        for(T object: collection)
            this.add(func.apply(object));
    }

    public <T> void addAll(T[] array, Function<T, Boolean> func) {
        for(T object: array)
            this.add(func.apply(object));
    }


    public void remove(int i, int len) {
        len = Math.min(len, size - i);
        if(len <= 0)
            return;

        final int newCapacity = array.length - len;
        final boolean[] copy = new boolean[newCapacity];

        System.arraycopy(array, 0, copy, 0, i);
        System.arraycopy(array, i + len, copy, i, newCapacity - i);
        array = copy;

        size -= len;
    }

    public void remove(int i) {
        remove(i, 1);
    }

    public void removeFirst(boolean value) {
        final int index = indexOf(value);
        if(index > -1)
            remove(index);
    }

    public void removeLast(boolean value) {
        final int index = lastIndexOf(value);
        if(index > -1)
            remove(index);
    }


    public boolean contains(boolean element) {
        return indexOf(element) != -1;
    }

    public int indexOf(boolean element) {
        return indexOfRange(element, 0, size);
    }

    public int lastIndexOf(boolean element) {
        return lastIndexOfRange(element, 0, size);
    }

    public int indexOfRange(boolean element, int start, int end) {
        for(int i = start; i < end; i++)
            if(array[i] == element)
                return i;
        return -1;
    }

    public int lastIndexOfRange(boolean element, int start, int end) {
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
        Arrays.fill(array, 0, size, false);
        size = 0;
    }

    public void trim() {
        array = Arrays.copyOf(array, size);
    }


    public void capacity(int newCapacity) {
        if(newCapacity == 0)
            array = new boolean[0];
        else
            array = Arrays.copyOf(array, newCapacity);
        size = Math.min(size, newCapacity);
    }


    public boolean get(int i) {
        return array[i];
    }

    public void set(int i, boolean newValue) {
        array[i] = newValue;
    }


    public boolean[] copyOf(int offset, int newLength) {
        final boolean[] slice = new boolean[newLength];
        System.arraycopy(array, offset, slice, 0, newLength);
        return slice;
    }

    public boolean[] copyOf(int newLength) {
        return copyOf(0, newLength);
    }

    public boolean[] copyOf() {
        return copyOf(array.length);
    }

    public boolean[] copyOfRange(int from, int to) {
        return copyOf(from, to - from);
    }


    public void copyTo(boolean[] dst, int offset, int length) {
        System.arraycopy(array, 0, dst, offset, length);
    }

    public void copyTo(boolean[] dst, int offset) {
        copyTo(dst, offset, array.length);
    }

    public void copyTo(boolean[] dst) {
        copyTo(dst, 0);
    }


    public BoolList copy() {
        return new BoolList(this);
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
        final BoolList list = (BoolList) object;
        return (size == list.size && Objects.deepEquals(array, list.array));
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(array), size);
    }

    @Override
    @NotNull
    public Iterator<Boolean> iterator() {
        return new Iterator<>() {
            private int index;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public Boolean next() {
                return array[index++];
            }
        };
    }

}
