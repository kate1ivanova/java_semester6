package org.example;

/**
 * Класс контейнера для хранения произвольного количества целых чисел.
 * Реализация основана на массиве с динамическим расширением при необходимости.
 */
public class IntContainer {
    private int[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Создание контейнера с начальной емкостью по умолчанию.
     */
    public IntContainer() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Создание контейнера с указанной начальной емкостью.
     *
     * @param initialCapacity начальная емкость контейнера
     * @throws IllegalArgumentException если начальная емкость отрицательна
     */
    public IntContainer(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity cannot be negative");
        }
        this.elements = new int[initialCapacity];
        this.size = 0;
    }

    /**
     * Добавление элемента в контейнер.
     *
     * @param element добавляемый элемент
     */
    public void add(int element) {
        ensureCapacity(size + 1);
        elements[size++] = element;
    }

    /**
     * Возврат элемента по указанному индексу.
     *
     * @param index индекс элемента
     * @return элемент по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за границы контейнера
     */
    public int get(int index) {
        checkIndex(index);
        return elements[index];
    }

    /**
     * Удаление элемент по указанному индексу.
     *
     * @param index индекс удаляемого элемента
     * @return удаленный элемент
     * @throws IndexOutOfBoundsException если индекс выходит за границы контейнера
     */
    public int remove(int index) {
        checkIndex(index);
        int removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
    }

    /**
     * Возврат количество элементов в контейнере.
     *
     * @return количество элементов
     */
    public int size() {
        return size;
    }

    /**
     * Проверка пуст ли контейнер.
     *
     * @return true, если контейнер пуст, иначе false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Очистка контейнера, удаляя все элементы.
     */
    public void clear() {
        size = 0;
    }

    /**
     * Проверка содержит ли контейнер указанный элемент.
     *
     * @param element искомый элемент
     * @return true, если элемент найден, иначе false
     */
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