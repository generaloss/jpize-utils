package jpize.util.array;

import java.util.function.Function;
import java.util.*;
import java.nio.*;
import org.jetbrains.annotations.NotNull;

public class IntList implements Iterable<Integer> {

    public static final int DEFAULT_CAPACITY = 10;

    private int[] array;
    private int size;

    public IntList() {
        this(DEFAULT_CAPACITY);
    }

    public IntList(int capacity) {
        if(capacity < 0)
           throw new IllegalArgumentException();
        this.array = new int[capacity];
    }

    public IntList(int... items) {
        this.size = items.length;
        this.array = items;
    }

    public IntList(IntList list) {
        this.size = list.size;
        this.array = list.copyOf();
    }

    public IntList(IntBuffer buffer) {
        this.array = new int[buffer.limit()];
        addAll(buffer);
    }

    public IntList(Iterable<Integer> iterable) {
        this.array = new int[1];
        addAll(iterable);
        this.trim();
    }

    public IntList(Collection<Integer> collection) {
        this.array = new int[collection.size()];
        addAll(collection);
    }

    public <T> IntList(Iterable<T> iterable, Function<T, Integer> func) {
        this.array = new int[1];
        addAll(iterable, func);
        trim();
    }

    public <T> IntList(Collection<T> collection, Function<T, Integer> func) {
        this.array = new int[collection.size()];
        addAll(collection, func);
    }

    public <T> IntList(T[] array, Function<T, Integer> func) {
        this.array = new int[array.length];
        addAll(array, func);
    }


    public int[] array() {
        return array;
    }

    public int[] arrayTrimmed() {
        return Arrays.copyOf(array, size);
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return array.length;
    }

    public int lastIdx() {
        return size - 1;
    }


    private void grow(int minCapacity) {
        final int oldCapacity = array.length;
        if(oldCapacity == 0){
            array = new int[Math.max(minCapacity, DEFAULT_CAPACITY)];
        }else{
            final int newCapacity = ArraysSupport.newLength(oldCapacity, minCapacity - oldCapacity, oldCapacity >> 1);
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    private void grow() {
        grow(size + 1);
    }


    public IntList add(int element) {
        if(size == array.length)
           grow();
        
        array[size] = element;
        size++;
        return this;
    }

    public IntList add(int... elements) {
        if(size + elements.length >= array.length)
            grow(size + elements.length);
        
        System.arraycopy(elements, 0, array, size, elements.length);
        size += elements.length;
        return this;
    }

    public IntList add(IntList list) {
        if(list.size() == list.capacity())
            add(list.array());
        else
            add(list.arrayTrimmed());
        return this;
    }

    public IntList add(int i, int element) {
        final int minCapacity = Math.max(size, i) + 1;
        if(minCapacity >= array.length)
            grow(minCapacity);
        
        if(size != 0 && size >= i)
            System.arraycopy(array, i, array, i + 1, size - i);
        
        array[i] = element;
        
        final int growth = minCapacity - size;
        if(growth > 0)
            size += growth;
        return this;
    }

    public IntList add(int i, int... elements) {
        if(elements.length == 0)
            return this;
        
        final int minCapacity = Math.max(size, i) + elements.length;
        if(minCapacity >= array.length)
            grow(minCapacity);
        
        if(size != 0 && size >= i)
            System.arraycopy(array, i, array, i + elements.length, size - i);
        System.arraycopy(elements, 0, array, i, elements.length);
        
        final int growth = minCapacity - size;
        if(growth > 0)
            size += growth;
        return this;
    }


    public IntList addAll(IntBuffer buffer) {
        for(int i = buffer.position(); i < buffer.limit(); i++)
            this.add(buffer.get(i));
        return this;
    }

    public IntList addAll(Iterable<Integer> iterable) {
        for(Integer item: iterable)
            this.add(item);
        return this;
    }

    public IntList addAll(Collection<Integer> collection) {
        for(Integer item: collection)
            this.add(item);
        return this;
    }

    public <T> IntList addAll(Iterable<T> iterable, Function<T, Integer> func) {
        for(T object: iterable)
            this.add(func.apply(object));
        return this;
    }

    public <T> IntList addAll(Collection<T> collection, Function<T, Integer> func) {
        for(T object: collection)
            this.add(func.apply(object));
        return this;
    }

    public <T> IntList addAll(T[] array, Function<T, Integer> func) {
        for(T object: array)
            this.add(func.apply(object));
        return this;
    }


    public IntList remove(int i, int len) {
        len = Math.min(len, size - i);
        if(len <= 0)
            return this;
        
        final int newCapacity = array.length - len;
        final int[] copy = new int[newCapacity];
        
        System.arraycopy(array, 0, copy, 0, i);
        System.arraycopy(array, i + len, copy, i, newCapacity - i);
        array = copy;
        
        size -= len;
        return this;
    }

    public int remove(int i) {
        final int val = get(i);
        remove(i, 1);
        return val;
    }

    public int removeLast() {
        return remove(lastIdx());
    }

    public IntList removeFirst(int value) {
        final int index = indexOf(value);
        if(index > -1)
            remove(index);
        return this;
    }

    public IntList removeLast(int value) {
        final int index = lastIndexOf(value);
        if(index > -1)
            remove(index);
        return this;
    }


    public boolean contains(int element) {
        return indexOf(element) != -1;
    }

    public int indexOf(int element) {
        return indexOfRange(element, 0, size);
    }

    public int lastIndexOf(int element) {
        return lastIndexOfRange(element, 0, size);
    }

    public int indexOfRange(int element, int start, int end) {
        for(int i = start; i < end; i++)
            if(array[i] == element )
                return i;
        return -1;
    }

    public int lastIndexOfRange(int element, int start, int end) {
        for(int i = end - 1; i >= start; i--)
            if(array[i] == element )
                return i;
        return -1;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isNotEmpty() {
        return size != 0;
    }


    public IntList clear() {
        Arrays.fill(array, 0, size, 0);
        size = 0;
        return this;
    }

    public IntList fill(int value) {
        Arrays.fill(array, 0, size, value);
        return this;
    }


    public IntList trim() {
        array = Arrays.copyOf(array, size);
        return this;
    }

    public IntList capacity(int newCapacity) {
        if(newCapacity == 0)
            array = new int[0];
        else
            array = Arrays.copyOf(array, newCapacity);
        size = Math.min(size, newCapacity);
        return this;
    }


    public int get(int i) {
        return array[i];
    }

    public int getLast() {
        return get(lastIdx());
    }

    public IntList set(int i, int newValue) {
        array[i] = newValue;
        return this;
    }

    public IntList setLast(int newValue) {
        return set(lastIdx(), newValue);
    }


    public IntList valAdd(int i, int value) {
        array[i] += value;
        return this;
    }

    public IntList valSub(int i, int value) {
        array[i] -= value;
        return this;
    }

    public IntList valMul(int i, int value) {
        array[i] *= value;
        return this;
    }

    public IntList valDiv(int i, int value) {
        array[i] /= value;
        return this;
    }


    public int[] copyOf(int offset, int newLength) {
        final int[] slice = new int[newLength];
        System.arraycopy(array, offset, slice, 0, newLength);
        return slice;
    }

    public int[] copyOf(int newLength) {
        return copyOf(0, newLength);
    }

    public int[] copyOf() {
        return copyOf(array.length);
    }

    public int[] copyOfRange(int from, int to) {
        return copyOf(from, to - from);
    }

    public IntList copyTo(int[] dst, int offset, int length) {
        System.arraycopy(array, 0, dst, offset, length);
        return this;
    }

    public IntList copyTo(int[] dst, int offset) {
        copyTo(dst, offset, array.length);
        return this;
    }

    public IntList copyTo(int[] dst) {
        copyTo(dst, 0);
        return this;
    }

    public IntList copy() {
        return new IntList(this);
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
        final IntList list = (IntList) object;
        return (size == list.size && Objects.deepEquals(array, list.array));
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(array), size);
    }

    @Override
    @NotNull
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            private int index;
            @Override
            public boolean hasNext() {
                return index < size;
            }
            @Override
            public Integer next() {
                return array[index++];
            }
        };
    }

}