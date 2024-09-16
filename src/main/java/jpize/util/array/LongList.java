package jpize.util.array;

import java.util.function.Function;
import java.util.*;
import java.nio.*;
import org.jetbrains.annotations.NotNull;

public class LongList implements Iterable<Long> {

    public static final int DEFAULT_CAPACITY = 10;

    private long[] array;
    private int size;

    public LongList() {
        this(DEFAULT_CAPACITY);
    }

    public LongList(int capacity) {
        if(capacity < 0)
           throw new IllegalArgumentException();
        this.array = new long[capacity];
    }

    public LongList(long... items) {
        this.size = items.length;
        this.array = items;
    }

    public LongList(LongList list) {
        this.size = list.size;
        this.array = list.copyOf();
    }

    public LongList(LongBuffer buffer) {
        this.array = new long[buffer.limit()];
        addAll(buffer);
    }

    public LongList(Iterable<Long> iterable) {
        this.array = new long[1];
        addAll(iterable);
        this.trim();
    }

    public LongList(Collection<Long> collection) {
        this.array = new long[collection.size()];
        addAll(collection);
    }

    public <T> LongList(Iterable<T> iterable, Function<T, Long> func) {
        this.array = new long[1];
        addAll(iterable, func);
        trim();
    }

    public <T> LongList(Collection<T> collection, Function<T, Long> func) {
        this.array = new long[collection.size()];
        addAll(collection, func);
    }

    public <T> LongList(T[] array, Function<T, Long> func) {
        this.array = new long[array.length];
        addAll(array, func);
    }


    public long[] array() {
        return array;
    }

    public long[] arrayTrimmed() {
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
            array = new long[Math.max(minCapacity, DEFAULT_CAPACITY)];
        }else{
            final int newCapacity = ArraysSupport.newLength(oldCapacity, minCapacity - oldCapacity, oldCapacity >> 1);
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    private void grow() {
        grow(size + 1);
    }


    public LongList add(long element) {
        if(size == array.length)
           grow();
        
        array[size] = element;
        size++;
        return this;
    }

    public LongList add(long... elements) {
        if(size + elements.length >= array.length)
            grow(size + elements.length);
        
        System.arraycopy(elements, 0, array, size, elements.length);
        size += elements.length;
        return this;
    }

    public LongList add(LongList list) {
        if(list.size() == list.capacity())
            add(list.array());
        else
            add(list.arrayTrimmed());
        return this;
    }

    public LongList add(int i, long element) {
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

    public LongList add(int i, long... elements) {
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


    public LongList addAll(LongBuffer buffer) {
        for(int i = buffer.position(); i < buffer.limit(); i++)
            this.add(buffer.get(i));
        return this;
    }

    public LongList addAll(Iterable<Long> iterable) {
        for(Long item: iterable)
            this.add(item);
        return this;
    }

    public LongList addAll(Collection<Long> collection) {
        for(Long item: collection)
            this.add(item);
        return this;
    }

    public <T> LongList addAll(Iterable<T> iterable, Function<T, Long> func) {
        for(T object: iterable)
            this.add(func.apply(object));
        return this;
    }

    public <T> LongList addAll(Collection<T> collection, Function<T, Long> func) {
        for(T object: collection)
            this.add(func.apply(object));
        return this;
    }

    public <T> LongList addAll(T[] array, Function<T, Long> func) {
        for(T object: array)
            this.add(func.apply(object));
        return this;
    }


    public LongList remove(int i, int len) {
        len = Math.min(len, size - i);
        if(len <= 0)
            return this;
        
        final int newCapacity = array.length - len;
        final long[] copy = new long[newCapacity];
        
        System.arraycopy(array, 0, copy, 0, i);
        System.arraycopy(array, i + len, copy, i, newCapacity - i);
        array = copy;
        
        size -= len;
        return this;
    }

    public long remove(int i) {
        final long val = get(i);
        remove(i, 1);
        return val;
    }

    public long removeLast() {
        return remove(lastIdx());
    }

    public LongList removeFirst(long value) {
        final int index = indexOf(value);
        if(index > -1)
            remove(index);
        return this;
    }

    public LongList removeLast(long value) {
        final int index = lastIndexOf(value);
        if(index > -1)
            remove(index);
        return this;
    }


    public boolean contains(long element) {
        return indexOf(element) != -1;
    }

    public int indexOf(long element) {
        return indexOfRange(element, 0, size);
    }

    public int lastIndexOf(long element) {
        return lastIndexOfRange(element, 0, size);
    }

    public int indexOfRange(long element, int start, int end) {
        for(int i = start; i < end; i++)
            if(array[i] == element )
                return i;
        return -1;
    }

    public int lastIndexOfRange(long element, int start, int end) {
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


    public LongList clear() {
        Arrays.fill(array, 0, size, 0L);
        size = 0;
        return this;
    }

    public LongList fill(long value) {
        Arrays.fill(array, 0, size, value);
        return this;
    }


    public LongList trim() {
        array = Arrays.copyOf(array, size);
        return this;
    }

    public LongList capacity(int newCapacity) {
        if(newCapacity == 0)
            array = new long[0];
        else
            array = Arrays.copyOf(array, newCapacity);
        size = Math.min(size, newCapacity);
        return this;
    }


    public long get(int i) {
        return array[i];
    }

    public long getLast() {
        return get(lastIdx());
    }

    public LongList set(int i, long newValue) {
        array[i] = newValue;
        return this;
    }

    public LongList setLast(long newValue) {
        return set(lastIdx(), newValue);
    }


    public LongList valAdd(int i, long value) {
        array[i] += value;
        return this;
    }

    public LongList valSub(int i, long value) {
        array[i] -= value;
        return this;
    }

    public LongList valMul(int i, long value) {
        array[i] *= value;
        return this;
    }

    public LongList valDiv(int i, long value) {
        array[i] /= value;
        return this;
    }


    public long[] copyOf(int offset, int newLength) {
        final long[] slice = new long[newLength];
        System.arraycopy(array, offset, slice, 0, newLength);
        return slice;
    }

    public long[] copyOf(int newLength) {
        return copyOf(0, newLength);
    }

    public long[] copyOf() {
        return copyOf(array.length);
    }

    public long[] copyOfRange(int from, int to) {
        return copyOf(from, to - from);
    }

    public LongList copyTo(long[] dst, int offset, int length) {
        System.arraycopy(array, 0, dst, offset, length);
        return this;
    }

    public LongList copyTo(long[] dst, int offset) {
        copyTo(dst, offset, array.length);
        return this;
    }

    public LongList copyTo(long[] dst) {
        copyTo(dst, 0);
        return this;
    }

    public LongList copy() {
        return new LongList(this);
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
        final LongList list = (LongList) object;
        return (size == list.size && Objects.deepEquals(array, list.array));
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(array), size);
    }

    @Override
    @NotNull
    public Iterator<Long> iterator() {
        return new Iterator<>() {
            private int index;
            @Override
            public boolean hasNext() {
                return index < size;
            }
            @Override
            public Long next() {
                return array[index++];
            }
        };
    }

}