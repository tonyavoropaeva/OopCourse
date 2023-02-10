package ru.academits.voropaeva.arraylist;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modCount;

    public MyArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[10];
    }

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Вместимость не может быть отрицательной, сейчас она равна " + capacity);
        }

        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    public void ensureCapacity(int minCapacity) {
        if (items.length < minCapacity) {
            items = Arrays.copyOf(items, minCapacity);
        }
    }

    public void trimToSize() {
        if (items.length > size) {
            items = Arrays.copyOf(items, size);
        }
    }

    private class MyIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("Размер коллекции был изменён во время обхода");
            }

            ++currentIndex;

            return items[currentIndex];
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object object) {
        return indexOf(object) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, array.getClass());
        }

        //noinspection unchecked
        T[] itemsArray = (T[]) items;

        System.arraycopy(itemsArray, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(E item) {
        add(size, item);

        return true;
    }

    private void increaseCapacity() {
        if (items.length == 0) {
            //noinspection unchecked
            items = (E[]) new Object[1];
        } else {
            items = Arrays.copyOf(items, items.length * 2);
        }
    }

    @Override
    public boolean remove(Object object) {
        int index = indexOf(object);

        if (index == -1) {
            return false;
        }

        remove(index);

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return addAll(size, collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть не меньше 0, сейчас он равен " + index);
        }

        if (index > size) {
            throw new IndexOutOfBoundsException("Индекс должен быть не больше " + size + ", сейчас он равен " + index);
        }

        if (collection.isEmpty()) {
            return false;
        }

        int collectionIndex = index;

        for (E element : collection) {
            add(collectionIndex, element);

            collectionIndex++;
        }

        ++modCount;

        return true;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        ++modCount;

        Arrays.fill(items, 0, size, null);

        size = 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        int initialSize = size;

        if (collection.isEmpty()) {
            clear();
        } else {
            for (int i = 0; i < size; i++) {
                E current = items[i];

                if (!collection.contains(current)) {
                    remove(i);

                    --i;
                }
            }
        }

        return initialSize != size;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection.isEmpty() || size == 0) {
            return false;
        }

        int initialSize = size;

        for (int i = 0; i < size; i++) {
            E current = items[i];

            if (collection.contains(current)) {
                remove(i);

                --i;
            }
        }

        return initialSize != size;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object item : collection) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E deletedItem = items[index];

        System.arraycopy(items, index + 1, items, index, size - index - 1);

        items[size - 1] = null;

        --size;
        ++modCount;

        return deletedItem;
    }

    @Override
    public E set(int index, E item) {
        checkIndex(index);

        E oldItem = items[index];
        items[index] = item;

        return oldItem;
    }

    @Override
    public void add(int index, E item) {
        if (size >= items.length) {
            increaseCapacity();
        }

        if (index != size) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }

        items[index] = item;

        ++size;
        ++modCount;
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; ++i) {
            if (Objects.equals(object, items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; --i) {
            if (Objects.equals(object, items[i])) {
                return i;
            }
        }

        return -1;
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть не меньше 0, сейчас он равен " + index);
        }

        if (index >= size) {
            throw new IndexOutOfBoundsException("Индекс должен быть не больше " + (size - 1) + " сейчас он равен " + index);
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for (int i = 0; i < size; ++i) {
            stringBuilder.append(items[i]);
            stringBuilder.append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}