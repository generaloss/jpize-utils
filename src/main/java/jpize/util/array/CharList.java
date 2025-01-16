package jpize.util.array;

import java.util.function.Function;
import java.util.*;
import java.nio.*;
import org.jetbrains.annotations.NotNull;

public class CharList implements Iterable<Character> {

    public static final int DEFAULT_CAPACITY = 10;

    private char[] array;
    private int size;

    public CharList() {
        this(DEFAULT_CAPACITY);
    }

    public CharList(int capacity) {
        if(capacity < 0)
           throw new IllegalArgumentException();
        this.array = new char[capacity];
    }

    public CharList(char... items) {
        this.size = items.length;
        this.array = items;
    }

    public CharList(String string) {
        this(string.toCharArray());
    }

    public CharList(CharList list) {
        this.size = list.size;
        this.array = list.copyOf();
    }

    public CharList(CharBuffer buffer) {
        this.array = new char[buffer.limit()];
        this.addAll(buffer);
    }

    public CharList(Iterable<Character> iterable) {
        this.array = new char[1];
        this.addAll(iterable);
        this.trim();
    }

    public CharList(Collection<Character> collection) {
        this.array = new char[collection.size()];
        this.addAll(collection);
    }

    public <O> CharList(Iterable<O> iterable, Function<O, Character> func) {
        this.array = new char[1];
        this.addAll(iterable, func);
        this.trim();
    }

    public <O> CharList(Collection<O> collection, Function<O, Character> func) {
        this.array = new char[collection.size()];
        this.addAll(collection, func);
    }

    public <O> CharList(O[] array, Function<O, Character> func) {
        this.array = new char[array.length];
        this.addAll(array, func);
    }


    public char[] array() {
        return array;
    }

    public char[] arrayTrimmed() {
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
            array = new char[Math.max(minCapacity, DEFAULT_CAPACITY)];
        }else{
            final int newCapacity = ArraysSupport.newLength(oldCapacity, minCapacity - oldCapacity, oldCapacity >> 1);
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    private void grow() {
        this.grow(size + 1);
    }


    public CharList add(char element) {
        if(size == array.length)
           this.grow();
        
        array[size] = element;
        size++;
        return this;
    }

    public CharList add(char... elements) {
        if(size + elements.length >= array.length)
            this.grow(size + elements.length);
        
        System.arraycopy(elements, 0, array, size, elements.length);
        size += elements.length;
        return this;
    }

    public CharList add(CharList list) {
        if(list.size() == list.capacity()){
            this.add(list.array());
        }else{
            this.add(list.arrayTrimmed());
        }
        return this;
    }

    public CharList add(int i, char element) {
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

    public CharList add(int i, char... elements) {
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

    public CharList addFirst(char element) {
        return this.add(0, element);
    }

    public CharList addFirst(char... elements) {
        return this.add(0, elements);
    }


    public CharList addAll(CharBuffer buffer) {
        for(int i = buffer.position(); i < buffer.limit(); i++)
            this.add(buffer.get(i));
        return this;
    }

    public CharList addAll(Iterable<Character> iterable) {
        for(Character item: iterable)
            this.add(item);
        return this;
    }

    public CharList addAll(Collection<Character> collection) {
        for(Character item: collection)
            this.add(item);
        return this;
    }

    public <O> CharList addAll(Iterable<O> iterable, Function<O, Character> func) {
        for(O object: iterable)
            this.add(func.apply(object));
        return this;
    }

    public <O> CharList addAll(Collection<O> collection, Function<O, Character> func) {
        for(O object: collection)
            this.add(func.apply(object));
        return this;
    }

    public <O> CharList addAll(O[] array, Function<O, Character> func) {
        for(O object: array)
            this.add(func.apply(object));
        return this;
    }


    public CharList remove(int i, int len) {
        len = Math.min(len, size - i);
        if(len <= 0)
            return this;
        
        final int newCapacity = (array.length - len);
        final char[] copy = new char[newCapacity];
        
        System.arraycopy(array, 0, copy, 0, i);
        System.arraycopy(array, i + len, copy, i, newCapacity - i);
        array = copy;
        
        size -= len;
        return this;
    }

    public char remove(int i) {
        final char val = this.get(i);
        this.remove(i, 1);
        return val;
    }

    public char removeFirst() {
        return this.remove(0);
    }

    public char removeLast() {
        return this.remove(this.lastIndex());
    }

    public Character removeFirst(char value) {
        final int index = this.indexOf(value);
        if(index > -1)
            return this.remove(index);
        return null;
    }

    public Character removeLast(char value) {
        final int index = this.lastIndexOf(value);
        if(index > -1)
            return this.remove(index);
        return null;
    }


    public boolean contains(char element) {
        return (this.indexOf(element) != -1);
    }

    public int indexOf(char element) {
        return this.indexOfRange(element, 0, size);
    }

    public int lastIndexOf(char element) {
        return this.lastIndexOfRange(element, 0, size);
    }

    public int indexOfRange(char element, int start, int end) {
        for(int i = start; i < end; i++)
            if(array[i] == element)
                return i;
        return -1;
    }

    public int lastIndexOfRange(char element, int start, int end) {
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


    public CharList clear() {
        Arrays.fill(array, 0, size, (char) 0);
        size = 0;
        return this;
    }

    public CharList fill(char value) {
        Arrays.fill(array, 0, size, value);
        return this;
    }


    public CharList trim() {
        array = Arrays.copyOf(array, size);
        return this;
    }

    public CharList capacity(int newCapacity) {
        if(newCapacity == 0){
            array = new char[0];
        }else{
            array = Arrays.copyOf(array, newCapacity);
        }
        size = Math.min(size, newCapacity);
        return this;
    }


    public char get(int i) {
        return array[i];
    }

    public char getFirst() {
        return this.get(0);
    }

    public char getLast() {
        return this.get(this.lastIndex());
    }

    public CharList set(int i, char newValue) {
        array[i] = newValue;
        return this;
    }

    public CharList setFirst(char newValue) {
        return this.set(0, newValue);
    }

    public CharList setLast(char newValue) {
        return this.set(this.lastIndex(), newValue);
    }


    public CharList elementAdd(int i, char value) {
        array[i] += value;
        return this;
    }

    public CharList elementSub(int i, char value) {
        array[i] -= value;
        return this;
    }

    public CharList elementMul(int i, char value) {
        array[i] *= value;
        return this;
    }

    public CharList elementDiv(int i, char value) {
        array[i] /= value;
        return this;
    }


    public char[] copyOf(int offset, int newLength) {
        final char[] slice = new char[newLength];
        System.arraycopy(array, offset, slice, 0, newLength);
        return slice;
    }

    public char[] copyOf(int newLength) {
        return this.copyOf(0, newLength);
    }

    public char[] copyOf() {
        return this.copyOf(array.length);
    }

    public char[] copyOfRange(int from, int to) {
        return this.copyOf(from, to - from);
    }

    public CharList copyTo(char[] dst, int offset, int length) {
        System.arraycopy(array, 0, dst, offset, length);
        return this;
    }

    public CharList copyTo(char[] dst, int offset) {
        return this.copyTo(dst, offset, array.length);
    }

    public CharList copyTo(char[] dst) {
        return this.copyTo(dst, 0);
    }

    public CharList copy() {
        return new CharList(this);
    }


    public String charsString() {
        return new String(array);
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
        final CharList list = (CharList) object;
        return (size == list.size && Objects.deepEquals(array, list.array));
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(array), size);
    }

    @Override
    @NotNull
    public Iterator<Character> iterator() {
        return new Iterator<>() {
            private int index;
            @Override
            public boolean hasNext() {
                return (index < size);
            }
            @Override
            public Character next() {
                return array[index++];
            }
        };
    }

}