package jpize.util.array;

import java.util.function.Function;
import java.util.*;
import java.nio.*;
import org.jetbrains.annotations.NotNull;

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
        this.addAll(buffer);
    }

    public BoolList(Iterable<Boolean> iterable) {
        this.array = new boolean[1];
        this.addAll(iterable);
        this.trim();
    }

    public BoolList(Collection<Boolean> collection) {
        this.array = new boolean[collection.size()];
        this.addAll(collection);
    }

    public <O> BoolList(Iterable<O> iterable, Function<O, Boolean> func) {
        this.array = new boolean[1];
        this.addAll(iterable, func);
        this.trim();
    }

    public <O> BoolList(Collection<O> collection, Function<O, Boolean> func) {
        this.array = new boolean[collection.size()];
        this.addAll(collection, func);
    }

    public <O> BoolList(O[] array, Function<O, Boolean> func) {
        this.array = new boolean[array.length];
        this.addAll(array, func);
    }


    public boolean[] array() {
        return array;
    }

    public boolean[] arrayTrimmed() {
        if(array.length == size)
            return array;
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
            array = new boolean[Math.max(minCapacity, DEFAULT_CAPACITY)];
        }else{
            final int newCapacity = ArraysSupport.newLength(oldCapacity, minCapacity - oldCapacity, oldCapacity >> 1);
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    private void grow() {
        this.grow(size + 1);
    }


    public BoolList add(boolean element) {
        if(size == array.length)
           this.grow();
        
        array[size] = element;
        size++;
        return this;
    }

    public BoolList add(boolean... elements) {
        if(size + elements.length >= array.length)
            this.grow(size + elements.length);
        
        System.arraycopy(elements, 0, array, size, elements.length);
        size += elements.length;
        return this;
    }

    public BoolList add(BoolList list) {
        if(list.size() == list.capacity()){
            this.add(list.array());
        }else{
            this.add(list.arrayTrimmed());
        }
        return this;
    }

    public BoolList add(int i, boolean element) {
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

    public BoolList add(int i, boolean... elements) {
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

    public BoolList addFirst(boolean element) {
        return this.add(0, element);
    }

    public BoolList addFirst(boolean... elements) {
        return this.add(0, elements);
    }


    public BoolList addAll(ByteBuffer buffer) {
        for(int i = buffer.position(); i < buffer.limit(); i++)
            this.add(buffer.get(i) == 1);
        return this;
    }

    public BoolList addAll(Iterable<Boolean> iterable) {
        for(Boolean item: iterable)
            this.add(item);
        return this;
    }

    public BoolList addAll(Collection<Boolean> collection) {
        for(Boolean item: collection)
            this.add(item);
        return this;
    }

    public <O> BoolList addAll(Iterable<O> iterable, Function<O, Boolean> func) {
        for(O object: iterable)
            this.add(func.apply(object));
        return this;
    }

    public <O> BoolList addAll(Collection<O> collection, Function<O, Boolean> func) {
        for(O object: collection)
            this.add(func.apply(object));
        return this;
    }

    public <O> BoolList addAll(O[] array, Function<O, Boolean> func) {
        for(O object: array)
            this.add(func.apply(object));
        return this;
    }


    public BoolList remove(int i, int len) {
        len = Math.min(len, size - i);
        if(len <= 0)
            return this;
        
        final int newCapacity = (array.length - len);
        final boolean[] copy = new boolean[newCapacity];
        
        System.arraycopy(array, 0, copy, 0, i);
        System.arraycopy(array, i + len, copy, i, newCapacity - i);
        array = copy;
        
        size -= len;
        return this;
    }

    public boolean remove(int i) {
        final boolean val = this.get(i);
        this.remove(i, 1);
        return val;
    }

    public boolean removeFirst() {
        return this.remove(0);
    }

    public boolean removeLast() {
        return this.remove(this.lastIndex());
    }

    public Boolean removeFirst(boolean value) {
        final int index = this.indexOf(value);
        if(index > -1)
            return this.remove(index);
        return null;
    }

    public Boolean removeLast(boolean value) {
        final int index = this.lastIndexOf(value);
        if(index > -1)
            return this.remove(index);
        return null;
    }


    public boolean contains(boolean element) {
        return (this.indexOf(element) != -1);
    }

    public int indexOf(boolean element) {
        return this.indexOfRange(element, 0, size);
    }

    public int lastIndexOf(boolean element) {
        return this.lastIndexOfRange(element, 0, size);
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
        return (size == 0);
    }

    public boolean isNotEmpty() {
        return (size != 0);
    }


    public BoolList clear() {
        Arrays.fill(array, 0, size, false);
        size = 0;
        return this;
    }

    public BoolList fill(boolean value) {
        Arrays.fill(array, 0, size, value);
        return this;
    }


    public BoolList trim() {
        if(array.length == size)
            return this;
        array = Arrays.copyOf(array, size);
        return this;
    }

    public BoolList capacity(int newCapacity) {
        if(newCapacity == 0){
            array = new boolean[0];
        }else{
            array = Arrays.copyOf(array, newCapacity);
        }
        size = Math.min(size, newCapacity);
        return this;
    }


    public boolean get(int i) {
        return array[i];
    }

    public boolean getFirst() {
        return this.get(0);
    }

    public boolean getLast() {
        return this.get(this.lastIndex());
    }

    public BoolList set(int i, boolean newValue) {
        array[i] = newValue;
        return this;
    }

    public BoolList setFirst(boolean newValue) {
        return this.set(0, newValue);
    }

    public BoolList setLast(boolean newValue) {
        return this.set(this.lastIndex(), newValue);
    }



    public boolean[] copyOf(int offset, int newLength) {
        final boolean[] slice = new boolean[newLength];
        System.arraycopy(array, offset, slice, 0, newLength);
        return slice;
    }

    public boolean[] copyOf(int newLength) {
        return this.copyOf(0, newLength);
    }

    public boolean[] copyOf() {
        return this.copyOf(size);
    }

    public boolean[] copyOfRange(int from, int to) {
        return this.copyOf(from, to - from);
    }

    public BoolList copyTo(boolean[] dst, int offset, int length) {
        System.arraycopy(array, 0, dst, offset, length);
        return this;
    }

    public BoolList copyTo(boolean[] dst, int offset) {
        return this.copyTo(dst, offset, size);
    }

    public BoolList copyTo(boolean[] dst) {
        return this.copyTo(dst, 0);
    }

    public BoolList copy() {
        return new BoolList(this);
    }


    @Override
    public String toString() {
        return Arrays.toString(this.arrayTrimmed());
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
                return (index < size);
            }
            @Override
            public Boolean next() {
                return array[index++];
            }
        };
    }

}