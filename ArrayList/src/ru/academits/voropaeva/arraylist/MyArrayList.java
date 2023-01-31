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
            throw new IllegalArgumentException("Вместимость не может быть отрицательной");
        }

        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    public void ensureCapacity(int minCapacity) {
        if (items.length < minCapacity) {
            items = Arrays.copyOf(items, minCapacity);
        }
    }

    // +
    public void trimToSize() {
        if (items.length > size) {
            items = Arrays.copyOf(items, size);
        }
    }

    // +
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

    // есть ли такой элемент +
    @Override
    public boolean contains(Object object) {
        return indexOf(object) >= 0;
    }

    // создаем итератор +
    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    // преобразование в массив
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

    // вставка в конец +
    @Override
    public boolean add(E item) {
        add(size, item);

        return true;
    }

    //увеличение массива при переполнении +
    private void increaseCapacity() {
        if (items.length == 0) {
            //noinspection unchecked
            items = (E[]) new Object[1];
        }

        items = Arrays.copyOf(items, items.length * 2);
    }

    // удаление по значению(первое вхождение) +
    @Override
    public boolean remove(Object object) {
        int index = indexOf(object);

        if (index == -1) {
            return false;
        }

        remove(index);

        return true;
    }

    // добавляет все элементы в конец из переданной коллекции +
    @Override
    public boolean addAll(Collection<? extends E> collection) {
        addAll(size, collection);

        return true;
    }

    // добавляет все элементы по индексу из переданной коллекции +
    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть не меньше 0, сейчас он равен " + index);
        }

        if (index > size) {
            throw new IndexOutOfBoundsException("Индекс должен быть не больше " + size + " сейчас он равен " + index);
        }

        System.arraycopy(items, index, items, index + collection.size(), size - index);

        for (int i = 0; i < collection.size(); ++index, ++i) {
            //noinspection unchecked
            items[index] = (E) collection.toArray()[i];
        }

        size += collection.size();

        return true;
    }

    // удаление всех элементов +
    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        modCount += size;

        Arrays.fill(items, null);

        size = 0;
    }

    // получить элемент по индексу +
    @Override
    public E get(int index) {
        checkIndex(index);

        return items[index];
    }

    // оставляет элементы из переданной коллекции, а остальные удаляет +
    @Override
    public boolean retainAll(Collection<?> collection) {
        int initialSize = size;

        if (collection.isEmpty()) {
            clear();

            return true;
        }

        for (int i = 0; i < size; i++) {
            E current = get(i);

            if (!collection.contains(current)) {
                remove(current);

                --i;
            }
        }

        return initialSize != size;
    }

    // удаляет все элементы которые были в переданной коллекции +
    @Override
    public boolean removeAll(Collection<?> collection) {
        int initialSize = size;

        for (Object item : collection) {
            boolean isDeleted = remove(item);

            while (isDeleted) {
                remove(item);

                isDeleted = remove(item);
            }
        }

        return initialSize != size;
    }

    // содержит ли все элементы из переданной коллекции ++
    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object item : collection) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    // удаление по индексу +
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

    //заменяет значение по индексу +
    @Override
    public E set(int index, E item) {
        checkIndex(index);

        E oldItem = items[index];
        items[index] = item;

        return oldItem;
    }

    //добавление элемента по индексу +
    @Override
    public void add(int index, E item) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть не меньше 0, сейчас он равен " + index);
        }

        if (index > size) {
            throw new IndexOutOfBoundsException("Индекс должен быть не больше " + size + " сейчас он равен " + index);
        }

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

    //первое вхождение +
    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; ++i) {
            Object current = items[i];

            if (object == null && current == null) {
                return i;
            }

            if (current == null) {
                continue;
            }

            if (current.equals(object)) {
                return i;
            }
        }

        return -1;
    }

    //последнее вхождение +
    @Override
    public int lastIndexOf(Object object) {
        int index = -1;

        for (int i = size - 1; i >= 0; --i) {
            if (items[i] == null) {
                continue;
            }

            if (items[i].equals(object)) {
                index = i;

                break;
            }
        }

        return index;
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