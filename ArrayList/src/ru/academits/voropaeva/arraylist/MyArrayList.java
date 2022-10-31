package ru.academits.voropaeva.arraylist;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private T[] items;
    private int size;
    private int modCount;

    public MyArrayList() {
        //noinspection unchecked
        items = (T[]) new Object[10];
    }

    public MyArrayList(int capacity) {
        //noinspection unchecked
        items = (T[]) new Object[capacity];
    }

    public void ensureCapacity(int minCapacity) {
        items = Arrays.copyOf(items, items.length + minCapacity);
    }

    // +
    public void trimToSize() {
        items = Arrays.copyOf(items, size);
    }

    // +
    private class MyIterator implements Iterator<T> {
        private int currentIndex = -1;
        private final int modCount1 = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (currentIndex + 1 >= size) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (modCount1 != modCount) {
                if (modCount1 < modCount) {
                    throw new ConcurrentModificationException("В коллекцию добавились элементы, (" + (modCount - modCount1) + (" шт)"));
                }

                throw new ConcurrentModificationException("Из коллекции удалились элементы, (" + (modCount1 - modCount) + (" шт)"));
            }

            ++currentIndex;

            return items[currentIndex];
        }
    }

    // размер +
    @Override
    public int size() {
        return size;
    }

    // пустой ли +
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // есть ли такой элемент +
    @Override
    public boolean contains(Object item) {
        for (int i = 0; i < size; ++i) {
            if (items[i].equals(item)) {
                return true;
            }
        }

        return false;
    }

    // создаем итератор +
    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    // возвращает массив -??
    @Override
    public T[] toArray() {
        //noinspection unchecked
        return (T[]) Arrays.copyOf(items, size, items.getClass());
    }

    // преобразование в массив -??
    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, array.getClass());
        }

        System.arraycopy(items, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    // вставка в конец +
    @Override
    public boolean add(T item) {
        if (size >= items.length) {
            increaseCapacity();
        }

        items[size] = item;
        ++size;
        ++modCount;

        return true;
    }

    //увеличение массива при переполнении +
    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    // удаление по значению(первое вхождение) +
    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; ++i) {
            if (items[i].equals(o)) {
                System.arraycopy(items, i + 1, items, i, size - i - 1);

                items[size - 1] = null;

                --size;
                --modCount;

                return true;
            }
        }

        return false;
    }

    // добавляет все элементы в конец из переданной коллекции +
    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        //noinspection unchecked
        T[] collectionArray = (T[]) collection.toArray();

        for (int i = 0; i < collection.size(); ++i) {
            add(collectionArray[i]);
        }

        return true;
    }

    // добавляет все элементы по индексу из переданной коллекции +
    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        if (index == size) {
            addAll(collection);
            return true;
        }

        checkIndex(index);

        //noinspection unchecked
        T[] collectionArray = (T[]) collection.toArray();

        for (int i = 0; i < collection.size(); ++index, ++i) {
            add(index, collectionArray[i]);
        }

        return true;
    }

    // удаление всех элементов +
    @Override
    public void clear() {
        modCount -= size;

        for (int i = 0; i < size; ++i) {
            items[i] = null;
        }

        size = 0;
    }

    // получить элемент по индексу +
    @Override
    public T get(int index) {
        checkIndex(index);

        return items[index];
    }

    // оставляет элементы из переданной коллекции, а остальные удаляет +
    @Override
    public boolean retainAll(Collection<?> collection) {
        //noinspection unchecked
        T[] collectionArray = (T[]) collection.toArray();
        MyArrayList<T> list = new MyArrayList<>();
        int sizeTo = size;

        for (T item : collectionArray) {
            if (contains(item)) {
                remove(item);
                list.add(item);
            }
        }

        if (sizeTo == size) {
            clear();
            return true;
        }

        for (int i = 0; i < list.size(); ++i) {
            items[i] = list.get(i);
        }

        return true;
    }

    // удаляет все элементы которые были в переданной коллекции + ????
    @Override
    public boolean removeAll(Collection<?> collection) {
        //noinspection unchecked
        T[] collectionArray = (T[]) collection.toArray();
        int sizeTo = size;

        for (T item : collectionArray) {
            if (contains(item)) {
                remove(item);
            }
        }

        return sizeTo != size;
    }

    // содержит ли все элементы из переданной коллекции --??
    @Override
    public boolean containsAll(Collection<?> collection) {
        //noinspection unchecked
        T[] collectionArray = (T[]) collection.toArray();
        MyArrayList<T> list = new MyArrayList<>();
        list.items = Arrays.copyOf(items, items.length);

        for (T item : collectionArray) {
            if (list.contains(item)) {
                list.remove(item);
            } else {
                return false;
            }
        }

        return true;
    }

    // удаление по индексу +
    @Override
    public T remove(int index) {
        checkIndex(index);

        T previous = items[index];

        System.arraycopy(items, index + 1, items, index, size - index - 1);

        items[size - 1] = null;

        --size;
        --modCount;

        return previous;
    }

    //заменяет значение по индексу +
    @Override
    public T set(int index, T item) {
        checkIndex(index);

        T previous = items[index];
        items[index] = item;

        return previous;
    }

    //добавление элемента по индексу +
    @Override
    public void add(int index, T item) {
        checkIndex(index);

        if (size >= items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, size - index);

        items[index] = item;

        ++size;
        ++modCount;
    }

    //первое вхождение +
    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; ++i) {
            if (items[i].equals(object)) {
                return i;
            }
        }

        return -1;
    }

    //последнее вхождение +
    @Override
    public int lastIndexOf(Object object) {
        int index = -1;

        for (int i = 0; i < size; ++i) {
            if (items[i].equals(object)) {
                index = i;
            }
        }

        return index;
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть не меньше 0");
        }

        if (index > size - 1) {
            throw new IndexOutOfBoundsException("Индекс должен быть не больше " + (size - 1));
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
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }
}