package ru.academits.voropaeva.hashtable;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private final Object[] items;
    private int size;
    private final int capacity;
    private int modCount;

    public int getCapacity() {
        return capacity;
    }

    public HashTable(int capacity) {
        items = new Object[capacity];
        this.capacity = capacity;
    }

    public HashTable() {
        items = new Object[capacity = 10];
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
        Object arrayList = items[getIndex(object)];

        if (arrayList == null) {
            return false;
        }

        //noinspection unchecked
        return ((ArrayList<E>) arrayList).contains(object);
    }

    private class MyIterator implements Iterator<E> {
        private int count;
        private int currentIndexArray = 0;
        private int currentIndexArrayList = 0;
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
                throw new ConcurrentModificationException("Размер хэш-таблицы был изменён во время обхода");
            }

            while (items[currentIndexArray] == null) {
                ++currentIndexArray;
            }

            //noinspection unchecked
            ArrayList<E> currentItem = (ArrayList<E>) items[currentIndexArray];

            E result = currentItem.get(currentIndexArrayList);

            if (currentIndexArrayList == currentItem.size() - 1) {
                ++currentIndexArray;
                currentIndexArrayList = 0;
            } else {
                ++currentIndexArrayList;
            }

            ++count;

            return result;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;

        for (int i = 0; i < capacity; i++) {
            if (items[i] != null) {
                //noinspection unchecked
                for (E item : (ArrayList<E>) items[i]) {
                    array[index] = item;

                    ++index;
                }
            }
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
            items[index] = new ArrayList<E>();
        }

        ++size;
        ++modCount;

        //noinspection unchecked
        return ((ArrayList<E>) items[index]).add(item);
    }

    @Override
    public boolean remove(Object object) {
        Object arrayList = items[getIndex(object)];

        if (arrayList == null) {
            return false;
        }

        //noinspection unchecked
        if (((ArrayList<E>) arrayList).remove(object)) {
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
            remove(object);
        }

        return initialSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        int initialSize = size;

        if (collection.isEmpty()) {
            clear();
        } else {
            for (Object object : collection) {
                if (!contains(object)) {
                    remove(object);
                }
            }
        }

        return initialSize != size;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(items, 0, capacity, null);

        size = 0;
        ++modCount;
    }

    private int getIndex(Object object) {
        return Math.abs(
                (object != null
                        ? object.hashCode()
                        : 0
                ) % items.length);
    }
}