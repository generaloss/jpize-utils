package jpize.util.array;

import java.util.function.Function;
import java.util.*;
import org.jetbrains.annotations.NotNull;

public class ObjectList implements Iterable<Object> {

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

    public ObjectList(Object... items) {
        this.size = items.length;
        this.array = items;
    }

    public ObjectList(ObjectList list) {
        this.size = list.size;
        this.array = list.copyOf();
    }

    public ObjectList(Iterable<Object> iterable) {
        this.array = new Object[1];
        addAll(iterable);
        this.trim();
    }

    public ObjectList(Collection<Object> collection) {
        this.array = new Object[collection.size()];
        addAll(collection);
    }

    public <T> ObjectList(Iterable<T> iterable, Function<T, Object> func) {
        this.array = new Object[1];
        addAll(iterable, func);
        trim();
    }

    public <T> ObjectList(Collection<T> collection, Function<T, Object> func) {
        this.array = new Object[collection.size()];
        addAll(collection, func);
    }

    public <T> ObjectList(T[] array, Function<T, Object> func) {
        this.array = new Object[array.length];
        addAll(array, func);
    }


    public Object[] array() {
        return array;
    }

    public Object[] arrayTrimmed() {
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
            array = new Object[Math.max(minCapacity, DEFAULT_CAPACITY)];
        }else{
            final int newCapacity = ArraysSupport.newLength(oldCapacity, minCapacity - oldCapacity, oldCapacity >> 1);
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    private void grow() {
        grow(size + 1);
    }


    public ObjectList add(Object element) {
        if(size == array.length)
           grow();
        
        array[size] = element;
        size++;
        return this;
    }

    public ObjectList add(Object... elements) {
        if(size + elements.length >= array.length)
            grow(size + elements.length);
        
        System.arraycopy(elements, 0, array, size, elements.length);
        size += elements.length;
        return this;
    }

    public ObjectList add(ObjectList list) {
        if(list.size() == list.capacity())
            add(list.array());
        else
            add(list.arrayTrimmed());
        return this;
    }

    public ObjectList add(int i, Object element) {
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

    public ObjectList add(int i, Object... elements) {
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


    public ObjectList addAll(Iterable<Object> iterable) {
        for(Object item: iterable)
            this.add(item);
        return this;
    }

    public ObjectList addAll(Collection<Object> collection) {
        for(Object item: collection)
            this.add(item);
        return this;
    }

    public <T> ObjectList addAll(Iterable<T> iterable, Function<T, Object> func) {
        for(T object: iterable)
            this.add(func.apply(object));
        return this;
    }

    public <T> ObjectList addAll(Collection<T> collection, Function<T, Object> func) {
        for(T object: collection)
            this.add(func.apply(object));
        return this;
    }

    public <T> ObjectList addAll(T[] array, Function<T, Object> func) {
        for(T object: array)
            this.add(func.apply(object));
        return this;
    }


    public ObjectList remove(int i, int len) {
        len = Math.min(len, size - i);
        if(len <= 0)
            return this;
        
        final int newCapacity = array.length - len;
        final Object[] copy = new Object[newCapacity];
        
        System.arraycopy(array, 0, copy, 0, i);
        System.arraycopy(array, i + len, copy, i, newCapacity - i);
        array = copy;
        
        size -= len;
        return this;
    }

    public Object remove(int i) {
        final Object val = get(i);
        remove(i, 1);
        return val;
    }

    public Object removeLast() {
        return remove(lastIdx());
    }

    public ObjectList removeFirst(Object value) {
        final int index = indexOf(value);
        if(index > -1)
            remove(index);
        return this;
    }

    public ObjectList removeLast(Object value) {
        final int index = lastIndexOf(value);
        if(index > -1)
            remove(index);
        return this;
    }


    public boolean contains(Object element) {
        return indexOf(element) != -1;
    }

    public int indexOf(Object element) {
        return indexOfRange(element, 0, size);
    }

    public int lastIndexOf(Object element) {
        return lastIndexOfRange(element, 0, size);
    }

    public int indexOfRange(Object element, int start, int end) {
        for(int i = start; i < end; i++)
            if(array[i].equals(element ))
                return i;
        return -1;
    }

    public int lastIndexOfRange(Object element, int start, int end) {
        for(int i = end - 1; i >= start; i--)
            if(array[i].equals(element ))
                return i;
        return -1;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isNotEmpty() {
        return size != 0;
    }


    public ObjectList clear() {
        Arrays.fill(array, 0, size, null);
        size = 0;
        return this;
    }

    public ObjectList fill(Object value) {
        Arrays.fill(array, 0, size, value);
        return this;
    }


    public ObjectList trim() {
        array = Arrays.copyOf(array, size);
        return this;
    }

    public ObjectList capacity(int newCapacity) {
        if(newCapacity == 0)
            array = new Object[0];
        else
            array = Arrays.copyOf(array, newCapacity);
        size = Math.min(size, newCapacity);
        return this;
    }


    public Object get(int i) {
        return array[i];
    }

    public Object getLast() {
        return get(lastIdx());
    }

    public ObjectList set(int i, Object newValue) {
        array[i] = newValue;
        return this;
    }

    public ObjectList setLast(Object newValue) {
        return set(lastIdx(), newValue);
    }



    public Object[] copyOf(int offset, int newLength) {
        final Object[] slice = new Object[newLength];
        System.arraycopy(array, offset, slice, 0, newLength);
        return slice;
    }

    public Object[] copyOf(int newLength) {
        return copyOf(0, newLength);
    }

    public Object[] copyOf() {
        return copyOf(array.length);
    }

    public Object[] copyOfRange(int from, int to) {
        return copyOf(from, to - from);
    }

    public ObjectList copyTo(Object[] dst, int offset, int length) {
        System.arraycopy(array, 0, dst, offset, length);
        return this;
    }

    public ObjectList copyTo(Object[] dst, int offset) {
        copyTo(dst, offset, array.length);
        return this;
    }

    public ObjectList copyTo(Object[] dst) {
        copyTo(dst, 0);
        return this;
    }

    public ObjectList copy() {
        return new ObjectList(this);
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
        final ObjectList list = (ObjectList) object;
        return (size == list.size && Objects.deepEquals(array, list.array));
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(array), size);
    }

    @Override
    @NotNull
    public Iterator<Object> iterator() {
        return new Iterator<>() {
            private int index;
            @Override
            public boolean hasNext() {
                return index < size;
            }
            @Override
            public Object next() {
                return array[index++];
            }
        };
    }

}