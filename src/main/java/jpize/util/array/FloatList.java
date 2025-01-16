package jpize.util.array;

import java.util.function.Function;
import java.util.*;
import java.nio.*;
import org.jetbrains.annotations.NotNull;

public class FloatList implements Iterable<Float> {

    public static final int DEFAULT_CAPACITY = 10;

    private float[] array;
    private int size;

    public FloatList() {
        this(DEFAULT_CAPACITY);
    }

    public FloatList(int capacity) {
        if(capacity < 0)
           throw new IllegalArgumentException();
        this.array = new float[capacity];
    }

    public FloatList(float... items) {
        this.size = items.length;
        this.array = items;
    }

    public FloatList(FloatList list) {
        this.size = list.size;
        this.array = list.copyOf();
    }

    public FloatList(FloatBuffer buffer) {
        this.array = new float[buffer.limit()];
        this.addAll(buffer);
    }

    public FloatList(Iterable<Float> iterable) {
        this.array = new float[1];
        this.addAll(iterable);
        this.trim();
    }

    public FloatList(Collection<Float> collection) {
        this.array = new float[collection.size()];
        this.addAll(collection);
    }

    public <O> FloatList(Iterable<O> iterable, Function<O, Float> func) {
        this.array = new float[1];
        this.addAll(iterable, func);
        this.trim();
    }

    public <O> FloatList(Collection<O> collection, Function<O, Float> func) {
        this.array = new float[collection.size()];
        this.addAll(collection, func);
    }

    public <O> FloatList(O[] array, Function<O, Float> func) {
        this.array = new float[array.length];
        this.addAll(array, func);
    }


    public float[] array() {
        return array;
    }

    public float[] arrayTrimmed() {
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
            array = new float[Math.max(minCapacity, DEFAULT_CAPACITY)];
        }else{
            final int newCapacity = ArraysSupport.newLength(oldCapacity, minCapacity - oldCapacity, oldCapacity >> 1);
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    private void grow() {
        this.grow(size + 1);
    }


    public FloatList add(float element) {
        if(size == array.length)
           this.grow();
        
        array[size] = element;
        size++;
        return this;
    }

    public FloatList add(float... elements) {
        if(size + elements.length >= array.length)
            this.grow(size + elements.length);
        
        System.arraycopy(elements, 0, array, size, elements.length);
        size += elements.length;
        return this;
    }

    public FloatList add(FloatList list) {
        if(list.size() == list.capacity()){
            this.add(list.array());
        }else{
            this.add(list.arrayTrimmed());
        }
        return this;
    }

    public FloatList add(int i, float element) {
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

    public FloatList add(int i, float... elements) {
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

    public FloatList addFirst(float element) {
        return this.add(0, element);
    }

    public FloatList addFirst(float... elements) {
        return this.add(0, elements);
    }


    public FloatList addAll(FloatBuffer buffer) {
        for(int i = buffer.position(); i < buffer.limit(); i++)
            this.add(buffer.get(i));
        return this;
    }

    public FloatList addAll(Iterable<Float> iterable) {
        for(Float item: iterable)
            this.add(item);
        return this;
    }

    public FloatList addAll(Collection<Float> collection) {
        for(Float item: collection)
            this.add(item);
        return this;
    }

    public <O> FloatList addAll(Iterable<O> iterable, Function<O, Float> func) {
        for(O object: iterable)
            this.add(func.apply(object));
        return this;
    }

    public <O> FloatList addAll(Collection<O> collection, Function<O, Float> func) {
        for(O object: collection)
            this.add(func.apply(object));
        return this;
    }

    public <O> FloatList addAll(O[] array, Function<O, Float> func) {
        for(O object: array)
            this.add(func.apply(object));
        return this;
    }


    public FloatList remove(int i, int len) {
        len = Math.min(len, size - i);
        if(len <= 0)
            return this;
        
        final int newCapacity = (array.length - len);
        final float[] copy = new float[newCapacity];
        
        System.arraycopy(array, 0, copy, 0, i);
        System.arraycopy(array, i + len, copy, i, newCapacity - i);
        array = copy;
        
        size -= len;
        return this;
    }

    public float remove(int i) {
        final float val = this.get(i);
        this.remove(i, 1);
        return val;
    }

    public float removeFirst() {
        return this.remove(0);
    }

    public float removeLast() {
        return this.remove(this.lastIndex());
    }

    public Float removeFirst(float value) {
        final int index = this.indexOf(value);
        if(index > -1)
            return this.remove(index);
        return null;
    }

    public Float removeLast(float value) {
        final int index = this.lastIndexOf(value);
        if(index > -1)
            return this.remove(index);
        return null;
    }


    public boolean contains(float element) {
        return (this.indexOf(element) != -1);
    }

    public int indexOf(float element) {
        return this.indexOfRange(element, 0, size);
    }

    public int lastIndexOf(float element) {
        return this.lastIndexOfRange(element, 0, size);
    }

    public int indexOfRange(float element, int start, int end) {
        for(int i = start; i < end; i++)
            if(array[i] == element)
                return i;
        return -1;
    }

    public int lastIndexOfRange(float element, int start, int end) {
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


    public FloatList clear() {
        Arrays.fill(array, 0, size, 0F);
        size = 0;
        return this;
    }

    public FloatList fill(float value) {
        Arrays.fill(array, 0, size, value);
        return this;
    }


    public FloatList trim() {
        array = Arrays.copyOf(array, size);
        return this;
    }

    public FloatList capacity(int newCapacity) {
        if(newCapacity == 0){
            array = new float[0];
        }else{
            array = Arrays.copyOf(array, newCapacity);
        }
        size = Math.min(size, newCapacity);
        return this;
    }


    public float get(int i) {
        return array[i];
    }

    public float getFirst() {
        return this.get(0);
    }

    public float getLast() {
        return this.get(this.lastIndex());
    }

    public FloatList set(int i, float newValue) {
        array[i] = newValue;
        return this;
    }

    public FloatList setFirst(float newValue) {
        return this.set(0, newValue);
    }

    public FloatList setLast(float newValue) {
        return this.set(this.lastIndex(), newValue);
    }


    public FloatList elementAdd(int i, float value) {
        array[i] += value;
        return this;
    }

    public FloatList elementSub(int i, float value) {
        array[i] -= value;
        return this;
    }

    public FloatList elementMul(int i, float value) {
        array[i] *= value;
        return this;
    }

    public FloatList elementDiv(int i, float value) {
        array[i] /= value;
        return this;
    }


    public float[] copyOf(int offset, int newLength) {
        final float[] slice = new float[newLength];
        System.arraycopy(array, offset, slice, 0, newLength);
        return slice;
    }

    public float[] copyOf(int newLength) {
        return this.copyOf(0, newLength);
    }

    public float[] copyOf() {
        return this.copyOf(array.length);
    }

    public float[] copyOfRange(int from, int to) {
        return this.copyOf(from, to - from);
    }

    public FloatList copyTo(float[] dst, int offset, int length) {
        System.arraycopy(array, 0, dst, offset, length);
        return this;
    }

    public FloatList copyTo(float[] dst, int offset) {
        return this.copyTo(dst, offset, array.length);
    }

    public FloatList copyTo(float[] dst) {
        return this.copyTo(dst, 0);
    }

    public FloatList copy() {
        return new FloatList(this);
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
        final FloatList list = (FloatList) object;
        return (size == list.size && Objects.deepEquals(array, list.array));
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(array), size);
    }

    @Override
    @NotNull
    public Iterator<Float> iterator() {
        return new Iterator<>() {
            private int index;
            @Override
            public boolean hasNext() {
                return (index < size);
            }
            @Override
            public Float next() {
                return array[index++];
            }
        };
    }

}