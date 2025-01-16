package jpize.util.array;

import java.util.function.Function;
import java.util.*;
import java.nio.*;
import org.jetbrains.annotations.NotNull;

public class ShortList implements Iterable<Short> {

    public static final int DEFAULT_CAPACITY = 10;

    private short[] array;
    private int size;

    public ShortList() {
        this(DEFAULT_CAPACITY);
    }

    public ShortList(int capacity) {
        if(capacity < 0)
           throw new IllegalArgumentException();
        this.array = new short[capacity];
    }

    public ShortList(short... items) {
        this.size = items.length;
        this.array = items;
    }

    public ShortList(ShortList list) {
        this.size = list.size;
        this.array = list.copyOf();
    }

    public ShortList(ShortBuffer buffer) {
        this.array = new short[buffer.limit()];
        this.addAll(buffer);
    }

    public ShortList(Iterable<Short> iterable) {
        this.array = new short[1];
        this.addAll(iterable);
        this.trim();
    }

    public ShortList(Collection<Short> collection) {
        this.array = new short[collection.size()];
        this.addAll(collection);
    }

    public <O> ShortList(Iterable<O> iterable, Function<O, Short> func) {
        this.array = new short[1];
        this.addAll(iterable, func);
        this.trim();
    }

    public <O> ShortList(Collection<O> collection, Function<O, Short> func) {
        this.array = new short[collection.size()];
        this.addAll(collection, func);
    }

    public <O> ShortList(O[] array, Function<O, Short> func) {
        this.array = new short[array.length];
        this.addAll(array, func);
    }


    public short[] array() {
        return array;
    }

    public short[] arrayTrimmed() {
        return Arrays.copyOf(array, size);
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return array.length;
    }

    public int lastIndex() {
        return Math.max(0, (size - 1));
    }


    private void grow(int minCapacity) {
        final int oldCapacity = array.length;
        if(oldCapacity == 0){
            array = new short[Math.max(minCapacity, DEFAULT_CAPACITY)];
        }else{
            final int newCapacity = ArraysSupport.newLength(oldCapacity, minCapacity - oldCapacity, oldCapacity >> 1);
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    private void grow() {
        this.grow(size + 1);
    }


    public ShortList add(short element) {
        if(size == array.length)
           this.grow();
        
        array[size] = element;
        size++;
        return this;
    }

    public ShortList add(short... elements) {
        if(size + elements.length >= array.length)
            this.grow(size + elements.length);
        
        System.arraycopy(elements, 0, array, size, elements.length);
        size += elements.length;
        return this;
    }

    public ShortList add(ShortList list) {
        if(list.size() == list.capacity()){
            this.add(list.array());
        }else{
            this.add(list.arrayTrimmed());
        }
        return this;
    }

    public ShortList add(int i, short element) {
        final int minCapacity = Math.max(size, i) + 1;
        if(minCapacity >= array.length)
            this.grow(minCapacity);
        
        if(size != 0 && size >= i)
            System.arraycopy(array, i, array, i + 1, size - i);
        
        array[i] = element;
        
        final int growth = (minCapacity - size);
        if(growth > 0)
            size += growth;
        return this;
    }

    public ShortList add(int i, short... elements) {
        if(elements.length == 0)
            return this;
        
        final int minCapacity = (Math.max(size, i) + elements.length);
        if(minCapacity >= array.length)
            this.grow(minCapacity);
        
        if(size != 0 && size >= i)
            System.arraycopy(array, i, array, i + elements.length, size - i);
        System.arraycopy(elements, 0, array, i, elements.length);
        
        final int growth = (minCapacity - size);
        if(growth > 0)
            size += growth;
        return this;
    }

    public ShortList addFirst(short element) {
        return this.add(0, element);
    }

    public ShortList addFirst(short... elements) {
        return this.add(0, elements);
    }


    public ShortList addAll(ShortBuffer buffer) {
        for(int i = buffer.position(); i < buffer.limit(); i++)
            this.add(buffer.get(i));
        return this;
    }

    public ShortList addAll(Iterable<Short> iterable) {
        for(Short item: iterable)
            this.add(item);
        return this;
    }

    public ShortList addAll(Collection<Short> collection) {
        for(Short item: collection)
            this.add(item);
        return this;
    }

    public <O> ShortList addAll(Iterable<O> iterable, Function<O, Short> func) {
        for(O object: iterable)
            this.add(func.apply(object));
        return this;
    }

    public <O> ShortList addAll(Collection<O> collection, Function<O, Short> func) {
        for(O object: collection)
            this.add(func.apply(object));
        return this;
    }

    public <O> ShortList addAll(O[] array, Function<O, Short> func) {
        for(O object: array)
            this.add(func.apply(object));
        return this;
    }


    public ShortList remove(int i, int len) {
        len = Math.min(len, size - i);
        if(len <= 0)
            return this;
        
        final int newCapacity = (array.length - len);
        final short[] copy = new short[newCapacity];
        
        System.arraycopy(array, 0, copy, 0, i);
        System.arraycopy(array, i + len, copy, i, newCapacity - i);
        array = copy;
        
        size -= len;
        return this;
    }

    public short remove(int i) {
        final short val = this.get(i);
        this.remove(i, 1);
        return val;
    }

    public short removeFirst() {
        return this.remove(0);
    }

    public short removeLast() {
        return this.remove(this.lastIndex());
    }

    public short removeFirst(short value) {
        final int index = this.indexOf(value);
        if(index > -1)
            return this.remove(index);
        return null;
    }

    public short removeLast(short value) {
        final int index = this.lastIndexOf(value);
        if(index > -1)
            return this.remove(index);
        return null;
    }


    public boolean contains(short element) {
        return (this.indexOf(element) != -1);
    }

    public int indexOf(short element) {
        return this.indexOfRange(element, 0, size);
    }

    public int lastIndexOf(short element) {
        return this.lastIndexOfRange(element, 0, size);
    }

    public int indexOfRange(short element, int start, int end) {
        for(int i = start; i < end; i++)
            if(array[i] == element)
                return i;
        return -1;
    }

    public int lastIndexOfRange(short element, int start, int end) {
        for(int i = end - 1; i >= start; i--)
            if(array[i] == element)
                return i;
        return -1;
    }


    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean isNotEmpty() {
        return (size != 0);
    }


    public ShortList clear() {
        Arrays.fill(array, 0, size, (short) 0);
        size = 0;
        return this;
    }

    public ShortList fill(short value) {
        Arrays.fill(array, 0, size, value);
        return this;
    }


    public ShortList trim() {
        array = Arrays.copyOf(array, size);
        return this;
    }

    public ShortList capacity(int newCapacity) {
        if(newCapacity == 0){
            array = new short[0];
        }else{
            array = Arrays.copyOf(array, newCapacity);
        }
        size = Math.min(size, newCapacity);
        return this;
    }


    public short get(int i) {
        return array[i];
    }

    public short getFirst() {
        return this.get(0);
    }

    public short getLast() {
        return this.get(this.lastIndex());
    }

    public ShortList set(int i, short newValue) {
        array[i] = newValue;
        return this;
    }

    public ShortList setFirst(short newValue) {
        return this.set(0, newValue);
    }

    public ShortList setLast(short newValue) {
        return this.set(this.lastIndex(), newValue);
    }


    public ShortList elementAdd(int i, short value) {
        array[i] += value;
        return this;
    }

    public ShortList elementSub(int i, short value) {
        array[i] -= value;
        return this;
    }

    public ShortList elementMul(int i, short value) {
        array[i] *= value;
        return this;
    }

    public ShortList elementDiv(int i, short value) {
        array[i] /= value;
        return this;
    }


    public short[] copyOf(int offset, int newLength) {
        final short[] slice = new short[newLength];
        System.arraycopy(array, offset, slice, 0, newLength);
        return slice;
    }

    public short[] copyOf(int newLength) {
        return this.copyOf(0, newLength);
    }

    public short[] copyOf() {
        return this.copyOf(array.length);
    }

    public short[] copyOfRange(int from, int to) {
        return this.copyOf(from, to - from);
    }

    public ShortList copyTo(short[] dst, int offset, int length) {
        System.arraycopy(array, 0, dst, offset, length);
        return this;
    }

    public ShortList copyTo(short[] dst, int offset) {
        return this.copyTo(dst, offset, array.length);
    }

    public ShortList copyTo(short[] dst) {
        return this.copyTo(dst, 0);
    }

    public ShortList copy() {
        return new ShortList(this);
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
        final ShortList list = (ShortList) object;
        return (size == list.size && Objects.deepEquals(array, list.array));
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(array), size);
    }

    @Override
    @NotNull
    public Iterator<Short> iterator() {
        return new Iterator<>() {
            private int index;
            @Override
            public boolean hasNext() {
                return (index < size);
            }
            @Override
            public Short next() {
                return array[index++];
            }
        };
    }

}