package ru.academits.voropaeva.hashtable;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private final ArrayList<E>[] items;
    private int size;
    private int modCount;

    public HashTable(int capacity) {
        if (capacity < 0) {
            throw new IndexOutOfBoundsException("Вместимость не может быть отрицательной, сейчас она равна " + capacity);
        }

        //noinspection unchecked
        items = new ArrayList[capacity];
    }

    public HashTable() {
        //noinspection unchecked
        items = new ArrayList[10];
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
        if (size == 0) {
            return false;
        }

        return (items[getIndex(object)]).contains(object);
    }

    private class Iterator implements java.util.Iterator<E> {
        private int count;
        private int currentIndexInArray;
        private int currentIndexInList;
        private final int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Хэш-таблица закончилась");
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("Хэш-таблица была изменена во время обхода");
            }

            ArrayList<E> currentItem = items[currentIndexInArray];

            while (currentItem == null || currentItem.size() == 0) {
                ++currentIndexInArray;
                currentItem = items[currentIndexInArray];
            }

            E result = currentItem.get(currentIndexInList);

            if (currentIndexInList == currentItem.size() - 1) {
                ++currentIndexInArray;
                currentIndexInList = 0;
            } else {
                ++currentIndexInList;
            }

            ++count;

            return result;
        }
    }

    @Override
    public java.util.Iterator<E> iterator() {
        return new Iterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;

        for (E item : this) {
            array[index] = item;

            ++index;
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(toArray(), size, array.getClass());
        }

        //noinspection unchecked
        T[] itemsArray = (T[]) toArray();

        System.arraycopy(itemsArray, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(E item) {
        int index = getIndex(item);

        if (items[index] == null) {
            items[index] = new ArrayList<>();
        }

        ++size;
        ++modCount;

        return items[index].add(item);
    }

    @Override
    public boolean remove(Object object) {
        ArrayList<E> list = items[getIndex(object)];

        if (list == null) {
            return false;
        }

        if (list.remove(object)) {
            --size;
            ++modCount;

            return true;
        }

        return false;
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
    public boolean addAll(Collection<? extends E> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        for (E item : collection) {
            add(item);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection.isEmpty() || size == 0) {
            return false;
        }

        int initialSize = size;

        for (Object object : collection) {
            boolean wasDeletion = true;

            while (wasDeletion) {
                wasDeletion = remove(object);
            }
        }

        return initialSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        int initialSize = size;

        if (collection.isEmpty()) {
            clear();
        } else {
            Set<Object> set = new HashSet<>();

            for (Object object : toArray()) {
                if (!collection.contains(object)) {
                    set.add(object);
                }
            }

            removeAll(set);
        }

        return initialSize != size;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(items, 0, items.length, null);

        size = 0;
        ++modCount;
    }

    private int getIndex(Object object) {
        int hash = object != null ? object.hashCode() : 0;

        return Math.abs(hash % items.length);
    }
}