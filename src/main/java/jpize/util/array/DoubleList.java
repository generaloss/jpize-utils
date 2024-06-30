package jpize.util.array;

import org.jetbrains.annotations.NotNull;

import java.nio.DoubleBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;

public class DoubleList implements Iterable<Double> {

    public static final int DEFAULT_CAPACITY = 10;

    private double[] array;
    private int size;

    public DoubleList() {
        this(DEFAULT_CAPACITY);
    }

    public DoubleList(int capacity) {
        if(capacity < 0)
            throw new IllegalArgumentException();
        this.array = new double[capacity];
    }

    public DoubleList(double... items) {
        this.size = items.length;
        this.array = items;
    }

    public DoubleList(DoubleList list) {
        this.size = list.size;
        this.array = list.copyOf();
    }

    public DoubleList(DoubleBuffer buffer) {
        this.array = new double[buffer.limit()];
        addAll(buffer);
    }

    public DoubleList(Iterable<Double> iterable) {
        this.array = new double[1];
        addAll(iterable);
        this.trim();
    }

    public DoubleList(Collection<Double> collection) {
        this.array = new double[collection.size()];
        addAll(collection);
    }

    public<T> DoubleList(Iterable<T> iterable, Function<T, Double> func) {
        this.array = new double[1];
        addAll(iterable, func);
        this.trim();
    }

    public<T> DoubleList(Collection<T> collection, Function<T, Double> func) {
        this.array = new double[collection.size()];
        addAll(collection, func);
    }

    public<T> DoubleList(T[] array, Function<T, Double> func) {
        this.array = new double[array.length];
        addAll(array, func);
    }


    public double[] array() {
        return array;
    }

    public double[] arrayTrimmed() {
        return Arrays.copyOf(array, size);
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return array.length;
    }

    private void grow(int minCapacity) {
        final int oldCapacity = array.length;
        if(oldCapacity == 0){
            array = new double[Math.max(minCapacity, DEFAULT_CAPACITY)];
        }else{
            final int newCapacity = ArraysSupport.newLength(oldCapacity, minCapacity - oldCapacity, oldCapacity >> 1);
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    private void grow() {
        grow(size + 1);
    }


    public void add(double element) {
        if(size == array.length)
            grow();

        array[size] = element;
        size++;
    }

    public void add(double... elements) {
        if(size + elements.length >= array.length)
            grow(size + elements.length);

        System.arraycopy(elements, 0, array, size, elements.length);
        size += elements.length;
    }

    public void add(DoubleList list) {
        if(list.size() == list.capacity())
            add(list.array());
        else
            add(list.arrayTrimmed());
    }


    public void add(int i, double element) {
        final int minCapacity = Math.max(size, i) + 1;
        if(minCapacity >= array.length)
            grow(minCapacity);

        if(size != 0 && size >= i)
            System.arraycopy(array, i, array, i + 1, size - i);

        array[i] = element;

        final int growth = minCapacity - size;
        if(growth > 0)
            size += growth;
    }

    public void add(int i, double... elements) {
        if(elements.length == 0)
            return;

        final int minCapacity = Math.max(size, i) + elements.length;
        if(minCapacity >= array.length)
            grow(minCapacity);

        if(size != 0 && size >= i)
            System.arraycopy(array, i, array, i + elements.length, size - i);
        System.arraycopy(elements, 0, array, i, elements.length);

        final int growth = minCapacity - size;
        if(growth > 0)
            size += growth;
    }


    public void addAll(DoubleBuffer buffer) {
        for(int i = buffer.position(); i < buffer.limit(); i++)
            this.add(buffer.get(i));
    }

    public void addAll(Iterable<Double> iterable) {
        for(Double item: iterable)
            this.add(item);
    }

    public void addAll(Collection<Double> collection) {
        for(Double item: collection)
            this.add(item);
    }

    public <T> void addAll(Iterable<T> iterable, Function<T, Double> func) {
        for(T object: iterable)
            this.add(func.apply(object));
    }

    public <T> void addAll(Collection<T> collection, Function<T, Double> func) {
        for(T object: collection)
            this.add(func.apply(object));
    }

    public <T> void addAll(T[] array, Function<T, Double> func) {
        for(T object: array)
            this.add(func.apply(object));
    }


    public void remove(int i, int len) {
        len = Math.min(len, size - i);
        if(len <= 0)
            return;

        final int newCapacity = array.length - len;
        final double[] copy = new double[newCapacity];

        System.arraycopy(array, 0, copy, 0, i);
        System.arraycopy(array, i + len, copy, i, newCapacity - i);
        array = copy;

        size -= len;
    }

    public void remove(int i) {
        remove(i, 1);
    }

    public void removeFirst(double value) {
        final int index = indexOf(value);
        if(index > -1)
            remove(index);
    }

    public void removeLast(double value) {
        final int index = lastIndexOf(value);
        if(index > -1)
            remove(index);
    }


    public boolean contains(double element) {
        return indexOf(element) != -1;
    }

    public int indexOf(double element) {
        return indexOfRange(element, 0, size);
    }

    public int lastIndexOf(double element) {
        return lastIndexOfRange(element, 0, size);
    }

    public int indexOfRange(double element, int start, int end) {
        for(int i = start; i < end; i++)
            if(array[i] == element)
                return i;
        return -1;
    }

    public int lastIndexOfRange(double element, int start, int end) {
        for(int i = end - 1; i >= start; i--)
            if(array[i] == element)
                return i;
        return -1;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isNotEmpty() {
        return size != 0;
    }


    public void clear() {
        Arrays.fill(array, 0, size, 0D);
        size = 0;
    }

    public void fill(double value) {
        Arrays.fill(array, 0, size, value);
    }


    public void trim() {
        array = Arrays.copyOf(array, size);
    }

    public void capacity(int newCapacity) {
        if(newCapacity == 0)
            array = new double[0];
        else
            array = Arrays.copyOf(array, newCapacity);
        size = Math.min(size, newCapacity);
    }


    public double get(int i) {
        return array[i];
    }

    public void set(int i, double newValue) {
        array[i] = newValue;
    }


    public void valAdd(int i, double value) {
        array[i] += value;
    }

    public void valSub(int i, double value) {
        array[i] -= value;
    }

    public void valMul(int i, double value) {
        array[i] *= value;
    }

    public void valDiv(int i, double value) {
        array[i] /= value;
    }


    public double[] copyOf(int offset, int newLength) {
        final double[] slice = new double[newLength];
        System.arraycopy(array, offset, slice, 0, newLength);
        return slice;
    }

    public double[] copyOf(int newLength) {
        return copyOf(0, newLength);
    }

    public double[] copyOf() {
        return copyOf(array.length);
    }

    public double[] copyOfRange(int from, int to) {
        return copyOf(from, to - from);
    }


    public void copyTo(double[] dst, int offset, int length) {
        System.arraycopy(array, 0, dst, offset, length);
    }

    public void copyTo(double[] dst, int offset) {
        copyTo(dst, offset, array.length);
    }

    public void copyTo(double[] dst) {
        copyTo(dst, 0);
    }


    public DoubleList copy() {
        return new DoubleList(this);
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
        final DoubleList list = (DoubleList) object;
        return (size == list.size && Objects.deepEquals(array, list.array));
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(array), size);
    }

    @Override
    @NotNull
    public Iterator<Double> iterator() {
        return new Iterator<>() {
            private int index;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public Double next() {
                return array[index++];
            }
        };
    }

}
