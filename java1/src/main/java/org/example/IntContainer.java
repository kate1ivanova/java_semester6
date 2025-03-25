package org.example;

public class IntContainer {
    private int[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public IntContainer() {
        this(DEFAULT_CAPACITY);
    }

    public IntContainer(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity cannot be negative");
        }
        this.elements = new int[initialCapacity];
        this.size = 0;
    }

    public void add(int element) {
        ensureCapacity(size + 1);
        elements[size++] = element;
    }

    public int get(int index) {
        checkIndex(index);
        return elements[index];
    }

    public int remove(int index) {
        checkIndex(index);
        int removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
    }

    public boolean contains(int element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element) {
                return true;
            }
        }
        return false;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = elements.length * 2;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            int[] newElements = new int[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}