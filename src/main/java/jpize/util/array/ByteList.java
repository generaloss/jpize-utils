package jpize.util.array;

import java.util.function.Function;
import java.util.*;
import java.nio.*;
import org.jetbrains.annotations.NotNull;

public class ByteList implements Iterable<Byte> {

    public static final int DEFAULT_CAPACITY = 10;

    private byte[] array;
    private int size;

    public ByteList() {
        this(DEFAULT_CAPACITY);
    }

    public ByteList(int capacity) {
        if(capacity < 0)
           throw new IllegalArgumentException();
        this.array = new byte[capacity];
    }

    public ByteList(byte... items) {
        this.size = items.length;
        this.array = items;
    }

    public ByteList(ByteList list) {
        this.size = list.size;
        this.array = list.copyOf();
    }

    public ByteList(ByteBuffer buffer) {
        this.array = new byte[buffer.limit()];
        addAll(buffer);
    }

    public ByteList(Iterable<Byte> iterable) {
        this.array = new byte[1];
        addAll(iterable);
        this.trim();
    }

    public ByteList(Collection<Byte> collection) {
        this.array = new byte[collection.size()];
        addAll(collection);
    }

    public <T> ByteList(Iterable<T> iterable, Function<T, Byte> func) {
        this.array = new byte[1];
        addAll(iterable, func);
        trim();
    }

    public <T> ByteList(Collection<T> collection, Function<T, Byte> func) {
        this.array = new byte[collection.size()];
        addAll(collection, func);
    }

    public <T> ByteList(T[] array, Function<T, Byte> func) {
        this.array = new byte[array.length];
        addAll(array, func);
    }


    public byte[] array() {
        return array;
    }

    public byte[] arrayTrimmed() {
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
            array = new byte[Math.max(minCapacity, DEFAULT_CAPACITY)];
        }else{
            final int newCapacity = ArraysSupport.newLength(oldCapacity, minCapacity - oldCapacity, oldCapacity >> 1);
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    private void grow() {
        grow(size + 1);
    }


    public ByteList add(byte element) {
        if(size == array.length)
           grow();
        
        array[size] = element;
        size++;
        return this;
    }

    public ByteList add(byte... elements) {
        if(size + elements.length >= array.length)
            grow(size + elements.length);
        
        System.arraycopy(elements, 0, array, size, elements.length);
        size += elements.length;
        return this;
    }

    public ByteList add(ByteList list) {
        if(list.size() == list.capacity())
            add(list.array());
        else
            add(list.arrayTrimmed());
        return this;
    }

    public ByteList add(int i, byte element) {
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

    public ByteList add(int i, byte... elements) {
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


    public ByteList addAll(ByteBuffer buffer) {
        for(int i = buffer.position(); i < buffer.limit(); i++)
            this.add(buffer.get(i));
        return this;
    }

    public ByteList addAll(Iterable<Byte> iterable) {
        for(Byte item: iterable)
            this.add(item);
        return this;
    }

    public ByteList addAll(Collection<Byte> collection) {
        for(Byte item: collection)
            this.add(item);
        return this;
    }

    public <T> ByteList addAll(Iterable<T> iterable, Function<T, Byte> func) {
        for(T object: iterable)
            this.add(func.apply(object));
        return this;
    }

    public <T> ByteList addAll(Collection<T> collection, Function<T, Byte> func) {
        for(T object: collection)
            this.add(func.apply(object));
        return this;
    }

    public <T> ByteList addAll(T[] array, Function<T, Byte> func) {
        for(T object: array)
            this.add(func.apply(object));
        return this;
    }


    public ByteList remove(int i, int len) {
        len = Math.min(len, size - i);
        if(len <= 0)
            return this;
        
        final int newCapacity = array.length - len;
        final byte[] copy = new byte[newCapacity];
        
        System.arraycopy(array, 0, copy, 0, i);
        System.arraycopy(array, i + len, copy, i, newCapacity - i);
        array = copy;
        
        size -= len;
        return this;
    }

    public byte remove(int i) {
        final byte val = get(i);
        remove(i, 1);
        return val;
    }

    public byte removeLast() {
        return remove(lastIdx());
    }

    public ByteList removeFirst(byte value) {
        final int index = indexOf(value);
        if(index > -1)
            remove(index);
        return this;
    }

    public ByteList removeLast(byte value) {
        final int index = lastIndexOf(value);
        if(index > -1)
            remove(index);
        return this;
    }


    public boolean contains(byte element) {
        return indexOf(element) != -1;
    }

    public int indexOf(byte element) {
        return indexOfRange(element, 0, size);
    }

    public int lastIndexOf(byte element) {
        return lastIndexOfRange(element, 0, size);
    }

    public int indexOfRange(byte element, int start, int end) {
        for(int i = start; i < end; i++)
            if(array[i] == element )
                return i;
        return -1;
    }

    public int lastIndexOfRange(byte element, int start, int end) {
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


    public ByteList clear() {
        Arrays.fill(array, 0, size, (byte) 0);
        size = 0;
        return this;
    }

    public ByteList fill(byte value) {
        Arrays.fill(array, 0, size, value);
        return this;
    }


    public ByteList trim() {
        array = Arrays.copyOf(array, size);
        return this;
    }

    public ByteList capacity(int newCapacity) {
        if(newCapacity == 0)
            array = new byte[0];
        else
            array = Arrays.copyOf(array, newCapacity);
        size = Math.min(size, newCapacity);
        return this;
    }


    public byte get(int i) {
        return array[i];
    }

    public byte getLast() {
        return get(lastIdx());
    }

    public ByteList set(int i, byte newValue) {
        array[i] = newValue;
        return this;
    }

    public ByteList setLast(byte newValue) {
        return set(lastIdx(), newValue);
    }


    public ByteList elementAdd(int i, byte value) {
        array[i] += value;
        return this;
    }

    public ByteList elementSub(int i, byte value) {
        array[i] -= value;
        return this;
    }

    public ByteList elementMul(int i, byte value) {
        array[i] *= value;
        return this;
    }

    public ByteList elementDiv(int i, byte value) {
        array[i] /= value;
        return this;
    }


    public byte[] copyOf(int offset, int newLength) {
        final byte[] slice = new byte[newLength];
        System.arraycopy(array, offset, slice, 0, newLength);
        return slice;
    }

    public byte[] copyOf(int newLength) {
        return copyOf(0, newLength);
    }

    public byte[] copyOf() {
        return copyOf(array.length);
    }

    public byte[] copyOfRange(int from, int to) {
        return copyOf(from, to - from);
    }

    public ByteList copyTo(byte[] dst, int offset, int length) {
        System.arraycopy(array, 0, dst, offset, length);
        return this;
    }

    public ByteList copyTo(byte[] dst, int offset) {
        copyTo(dst, offset, array.length);
        return this;
    }

    public ByteList copyTo(byte[] dst) {
        copyTo(dst, 0);
        return this;
    }

    public ByteList copy() {
        return new ByteList(this);
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
        final ByteList list = (ByteList) object;
        return (size == list.size && Objects.deepEquals(array, list.array));
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(array), size);
    }

    @Override
    @NotNull
    public Iterator<Byte> iterator() {
        return new Iterator<>() {
            private int index;
            @Override
            public boolean hasNext() {
                return index < size;
            }
            @Override
            public Byte next() {
                return array[index++];
            }
        };
    }

}