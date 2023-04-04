package ru.academits.voropaeva.hashtable;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private final ArrayList<E>[] items;
    private int size;
    private int modCount;

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Вместимость должна быть больше 0, сейчас она равна " + capacity);
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
        ArrayList<E> list = items[getIndex(object)];

        return list != null && list.contains(object);
    }

    private class HashTableIterator implements Iterator<E> {
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

            ArrayList<E> currentList = items[currentIndexInArray];

            while (currentList == null || currentList.isEmpty()) {
                ++currentIndexInArray;
                currentList = items[currentIndexInArray];
            }

            E result = currentList.get(currentIndexInList);

            if (currentIndexInList == currentList.size() - 1) {
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
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;

        for (E item : this) {
            array[i] = item;

            ++i;
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

        if (list != null && list.remove(object)) {
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
        int hashTableInitialSize = size;

        if (collection.isEmpty()) {
            clear();
        } else {
            for (ArrayList<E> list : items) {
                if (list != null) {
                    int listInitialSize = list.size();

                    if (list.retainAll(collection)) {
                        size -= listInitialSize - list.size();
                        ++modCount;
                    }
                }
            }
        }

        return hashTableInitialSize != size;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(items, null);

        size = 0;
        ++modCount;
    }

    private int getIndex(Object object) {
        if (object == null) {
            return 0;
        }

        return Math.abs(object.hashCode() % items.length);
    }
}