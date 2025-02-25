package jpize.util.array;

import java.util.function.Function;
import java.util.*;

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
        this.addAll(iterable);
        this.trim();
    }

    public StringList(Collection<?> collection) {
        this.array = new String[collection.size()];
        this.addAll(collection);
    }

    public <O> StringList(Iterable<O> iterable, Function<O, String> func) {
        this.array = new String[1];
        this.addAll(iterable, func);
        this.trim();
    }

    public <O> StringList(Collection<O> collection, Function<O, String> func) {
        this.array = new String[collection.size()];
        this.addAll(collection, func);
    }

    public <O> StringList(O[] array, Function<O, String> func) {
        this.array = new String[array.length];
        this.addAll(array, func);
    }


    public String[] array() {
        return array;
    }

    public String[] arrayTrimmed() {
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
            array = new String[Math.max(minCapacity, DEFAULT_CAPACITY)];
        }else{
            final int newCapacity = ArraysSupport.newLength(oldCapacity, minCapacity - oldCapacity, oldCapacity >> 1);
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    private void grow() {
        this.grow(size + 1);
    }


    public StringList add(String element) {
        if(size == array.length)
           this.grow();
        
        array[size] = element;
        size++;
        return this;
    }

    public StringList add(String... elements) {
        if(size + elements.length >= array.length)
            this.grow(size + elements.length);
        
        System.arraycopy(elements, 0, array, size, elements.length);
        size += elements.length;
        return this;
    }

    public StringList add(StringList list) {
        if(list.size() == list.capacity()){
            this.add(list.array());
        }else{
            this.add(list.arrayTrimmed());
        }
        return this;
    }

    public StringList add(int i, String element) {
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

    public StringList add(int i, String... elements) {
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

    public StringList addFirst(String element) {
        return this.add(0, element);
    }

    public StringList addFirst(String... elements) {
        return this.add(0, elements);
    }


    public StringList addAll(Iterable<?> iterable) {
        for(Object object: iterable)
            this.add(object.toString());
        return this;
    }

    public StringList addAll(Collection<?> collection) {
        for(Object object: collection)
            this.add(object.toString());
        return this;
    }

    public <O> StringList addAll(Iterable<O> iterable, Function<O, String> func) {
        for(O object: iterable)
            this.add(func.apply(object));
        return this;
    }

    public <O> StringList addAll(Collection<O> collection, Function<O, String> func) {
        for(O object: collection)
            this.add(func.apply(object));
        return this;
    }

    public <O> StringList addAll(O[] array, Function<O, String> func) {
        for(O object: array)
            this.add(func.apply(object));
        return this;
    }


    public StringList remove(int i, int len) {
        len = Math.min(len, size - i);
        if(len <= 0)
            return this;
        
        final int newCapacity = (array.length - len);
        final String[] copy = new String[newCapacity];
        
        System.arraycopy(array, 0, copy, 0, i);
        System.arraycopy(array, i + len, copy, i, newCapacity - i);
        array = copy;
        
        size -= len;
        return this;
    }

    public String remove(int i) {
        final String val = this.get(i);
        this.remove(i, 1);
        return val;
    }

    public String removeFirst() {
        return this.remove(0);
    }

    public String removeLast() {
        return this.remove(this.lastIndex());
    }

    public String removeFirst(String value) {
        final int index = this.indexOf(value);
        if(index > -1)
            return this.remove(index);
        return null;
    }

    public String removeLast(String value) {
        final int index = this.lastIndexOf(value);
        if(index > -1)
            return this.remove(index);
        return null;
    }


    public boolean contains(String element) {
        return (this.indexOf(element) != -1);
    }

    public int indexOf(String element) {
        return this.indexOfRange(element, 0, size);
    }

    public int lastIndexOf(String element) {
        return this.lastIndexOfRange(element, 0, size);
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
        return (size == 0);
    }

    public boolean isNotEmpty() {
        return (size != 0);
    }


    public StringList clear() {
        Arrays.fill(array, 0, size, null);
        size = 0;
        return this;
    }

    public StringList fill(String value) {
        Arrays.fill(array, 0, size, value);
        return this;
    }


    public StringList trim() {
        if(array.length == size)
            return this;
        array = Arrays.copyOf(array, size);
        return this;
    }

    public StringList capacity(int newCapacity) {
        if(newCapacity == 0){
            array = new String[0];
        }else{
            array = Arrays.copyOf(array, newCapacity);
        }
        size = Math.min(size, newCapacity);
        return this;
    }


    public String get(int i) {
        return array[i];
    }

    public String getFirst() {
        return this.get(0);
    }

    public String getLast() {
        return this.get(this.lastIndex());
    }

    public StringList set(int i, String newValue) {
        array[i] = newValue;
        return this;
    }

    public StringList setFirst(String newValue) {
        return this.set(0, newValue);
    }

    public StringList setLast(String newValue) {
        return this.set(this.lastIndex(), newValue);
    }


    public StringList elementAdd(int i, String value) {
        array[i] += value;
        return this;
    }

    public StringList elementAdd(int i, char value) {
        array[i] += value;
        return this;
    }

    public StringList elementTrim(int i) {
        array[i] = array[i].trim();
        return this;
    }

    public StringList elementReplace(int i, char oldChar, char newChar) {
        array[i] = array[i].replace(oldChar, newChar);
        return this;
    }

    public StringList elementReplace(int i, CharSequence target, CharSequence replacement) {
        array[i] = array[i].replace(target, replacement);
        return this;
    }

    public StringList elementReplaceAll(int i, String regex, String replacement) {
        array[i] = array[i].replaceAll(regex, replacement);
        return this;
    }

    public StringList elementReplaceFirst(int i, String regex, String replacement) {
        array[i] = array[i].replaceFirst(regex, replacement);
        return this;
    }

    public StringList elementToLowerCase(int i) {
        array[i] = array[i].toLowerCase();
        return this;
    }

    public StringList elementToUpperCase(int i) {
        array[i] = array[i].toUpperCase();
        return this;
    }


    public String[] copyOf(int offset, int newLength) {
        final String[] slice = new String[newLength];
        System.arraycopy(array, offset, slice, 0, newLength);
        return slice;
    }

    public String[] copyOf(int newLength) {
        return this.copyOf(0, newLength);
    }

    public String[] copyOf() {
        return this.copyOf(size);
    }

    public String[] copyOfRange(int from, int to) {
        return this.copyOf(from, to - from);
    }

    public StringList copyTo(String[] dst, int offset, int length) {
        System.arraycopy(array, 0, dst, offset, length);
        return this;
    }

    public StringList copyTo(String[] dst, int offset) {
        return this.copyTo(dst, offset, size);
    }

    public StringList copyTo(String[] dst) {
        return this.copyTo(dst, 0);
    }

    public StringList copy() {
        return new StringList(this);
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
        final StringList list = (StringList) object;
        return (size == list.size && Objects.deepEquals(array, list.array));
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(array), size);
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<>() {
            private int index;
            @Override
            public boolean hasNext() {
                return (index < size);
            }
            @Override
            public String next() {
                return array[index++];
            }
        };
    }

}