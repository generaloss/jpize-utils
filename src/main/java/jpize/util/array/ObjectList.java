package jpize.util.array;

import java.util.function.Function;
import java.util.*;
import org.jetbrains.annotations.NotNull;

public class ObjectList<T> implements Iterable<T> {

    public static final int DEFAULT_CAPACITY = 3;

    private Object[] array;
    private int size;

    public ObjectList() {
        this(DEFAULT_CAPACITY);
    }

    public ObjectList(int capacity) {
        if(capacity < 0)
           throw new IllegalArgumentException();
        this.array = new Object[capacity];
    }

    public ObjectList(T... items) {
        this.size = items.length;
        this.array = items;
    }

    public ObjectList(ObjectList<T> list) {
        this.size = list.size;
        this.array = list.copyOf();
    }

    public ObjectList(Iterable<T> iterable) {
        this.array = new Object[1];
        this.addAll(iterable);
        this.trim();
    }

    public ObjectList(Collection<T> collection) {
        this.array = new Object[collection.size()];
        this.addAll(collection);
    }

    public <O> ObjectList(Iterable<O> iterable, Function<O, T> func) {
        this.array = new Object[1];
        this.addAll(iterable, func);
        this.trim();
    }

    public <O> ObjectList(Collection<O> collection, Function<O, T> func) {
        this.array = new Object[collection.size()];
        this.addAll(collection, func);
    }

    public <O> ObjectList(O[] array, Function<O, T> func) {
        this.array = new Object[array.length];
        this.addAll(array, func);
    }


    public T[] array() {
        return (T[]) array;
    }

    public T[] arrayTrimmed() {
        return (T[]) Arrays.copyOf(array, size);
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
            array = new Object[Math.max(minCapacity, DEFAULT_CAPACITY)];
        }else{
            final int newCapacity = ArraysSupport.newLength(oldCapacity, minCapacity - oldCapacity, oldCapacity >> 1);
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    private void grow() {
        this.grow(size + 1);
    }


    public ObjectList<T> add(T element) {
        if(size == array.length)
           this.grow();
        
        array[size] = element;
        size++;
        return this;
    }

    public ObjectList<T> add(T... elements) {
        if(size + elements.length >= array.length)
            this.grow(size + elements.length);
        
        System.arraycopy(elements, 0, array, size, elements.length);
        size += elements.length;
        return this;
    }

    public ObjectList<T> add(ObjectList<T> list) {
        if(list.size() == list.capacity()){
            this.add(list.array());
        }else{
            this.add(list.arrayTrimmed());
        }
        return this;
    }

    public ObjectList<T> add(int i, T element) {
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

    public ObjectList<T> add(int i, T... elements) {
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

    public ObjectList<T> addFirst(T element) {
        return this.add(0, element);
    }

    public ObjectList<T> addFirst(T... elements) {
        return this.add(0, elements);
    }


    public ObjectList<T> addAll(Iterable<T> iterable) {
        for(T item: iterable)
            this.add(item);
        return this;
    }

    public ObjectList<T> addAll(Collection<T> collection) {
        for(T item: collection)
            this.add(item);
        return this;
    }

    public <O> ObjectList<T> addAll(Iterable<O> iterable, Function<O, T> func) {
        for(O object: iterable)
            this.add(func.apply(object));
        return this;
    }

    public <O> ObjectList<T> addAll(Collection<O> collection, Function<O, T> func) {
        for(O object: collection)
            this.add(func.apply(object));
        return this;
    }

    public <O> ObjectList<T> addAll(O[] array, Function<O, T> func) {
        for(O object: array)
            this.add(func.apply(object));
        return this;
    }


    public ObjectList<T> remove(int i, int len) {
        len = Math.min(len, size - i);
        if(len <= 0)
            return this;
        
        final int newCapacity = (array.length - len);
        final Object[] copy = new Object[newCapacity];
        
        System.arraycopy(array, 0, copy, 0, i);
        System.arraycopy(array, i + len, copy, i, newCapacity - i);
        array = copy;
        
        size -= len;
        return this;
    }

    public T remove(int i) {
        final T val = this.get(i);
        this.remove(i, 1);
        return val;
    }

    public T removeFirst() {
        return this.remove(0);
    }

    public T removeLast() {
        return this.remove(this.lastIndex());
    }

    public T removeFirst(Object value) {
        final int index = this.indexOf(value);
        if(index > -1)
            return this.remove(index);
        return null;
    }

    public T removeLast(Object value) {
        final int index = this.lastIndexOf(value);
        if(index > -1)
            return this.remove(index);
        return null;
    }


    public boolean contains(Object element) {
        return (this.indexOf(element) != -1);
    }

    public int indexOf(Object element) {
        return this.indexOfRange(element, 0, size);
    }

    public int lastIndexOf(Object element) {
        return this.lastIndexOfRange(element, 0, size);
    }

    public int indexOfRange(Object element, int start, int end) {
        for(int i = start; i < end; i++)
            if(array[i].equals(element))
                return i;
        return -1;
    }

    public int lastIndexOfRange(Object element, int start, int end) {
        for(int i = end - 1; i >= start; i--)
            if(array[i].equals(element))
                return i;
        return -1;
    }


    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean isNotEmpty() {
        return (size != 0);
    }


    public ObjectList<T> clear() {
        Arrays.fill(array, 0, size, null);
        size = 0;
        return this;
    }

    public ObjectList<T> fill(Object value) {
        Arrays.fill(array, 0, size, value);
        return this;
    }


    public ObjectList<T> trim() {
        array = Arrays.copyOf(array, size);
        return this;
    }

    public ObjectList<T> capacity(int newCapacity) {
        if(newCapacity == 0){
            array = new Object[0];
        }else{
            array = Arrays.copyOf(array, newCapacity);
        }
        size = Math.min(size, newCapacity);
        return this;
    }


    public T get(int i) {
        return (T) array[i];
    }

    public T getFirst() {
        return this.get(0);
    }

    public T getLast() {
        return this.get(this.lastIndex());
    }

    public ObjectList<T> set(int i, Object newValue) {
        array[i] = newValue;
        return this;
    }

    public ObjectList<T> setFirst(Object newValue) {
        return this.set(0, newValue);
    }

    public ObjectList<T> setLast(Object newValue) {
        return this.set(this.lastIndex(), newValue);
    }



    public Object[] copyOf(int offset, int newLength) {
        final Object[] slice = new Object[newLength];
        System.arraycopy(array, offset, slice, 0, newLength);
        return slice;
    }

    public Object[] copyOf(int newLength) {
        return this.copyOf(0, newLength);
    }

    public Object[] copyOf() {
        return this.copyOf(array.length);
    }

    public Object[] copyOfRange(int from, int to) {
        return this.copyOf(from, to - from);
    }

    public ObjectList<T> copyTo(Object[] dst, int offset, int length) {
        System.arraycopy(array, 0, dst, offset, length);
        return this;
    }

    public ObjectList<T> copyTo(Object[] dst, int offset) {
        return this.copyTo(dst, offset, array.length);
    }

    public ObjectList<T> copyTo(Object[] dst) {
        return this.copyTo(dst, 0);
    }

    public ObjectList<T> copy() {
        return new ObjectList<T>(this);
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
        final ObjectList<?> list = (ObjectList<?>) object;
        return (size == list.size && Objects.deepEquals(array, list.array));
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(array), size);
    }

    @Override
    @NotNull
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index;
            @Override
            public boolean hasNext() {
                return (index < size);
            }
            @Override
            public T next() {
                return (T) array[index++];
            }
        };
    }

}