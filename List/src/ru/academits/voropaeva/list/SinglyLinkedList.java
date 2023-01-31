package ru.academits.voropaeva.list;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public int getCount() {
        return count;
    }

    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }

        return head.getData();
    }

    public T getByIndex(int index) {
        checkIndex(index);

        return getItemByIndex(index).getData();
    }

    public T setByIndex(int index, T newData) {
        checkIndex(index);

        ListItem<T> itemByIndex = getItemByIndex(index);
        T oldData = itemByIndex.getData();
        itemByIndex.setData(newData);

        return oldData;
    }

    public T deleteByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return deleteFirst();
        }

        ListItem<T> previousItem = getItemByIndex(index - 1);
        T deletedData = previousItem.getNext().getData();
        previousItem.setNext(previousItem.getNext().getNext());

        --count;

        return deletedData;
    }

    public void addFirst(T item) {
        head = new ListItem<>(item, head);
        ++count;
    }

    public void addByIndex(int index, T newData) {
        if (index == count - 1) {
            addLast(newData);
            return;
        }

        checkIndex(index);

        if (index == 0) {
            addFirst(newData);
            return;
        }

        ListItem<T> previousItem = getItemByIndex(index - 1);

        ListItem<T> newItem = new ListItem<>(newData, previousItem.getNext());

        previousItem.setNext(newItem);

        ++count;
    }

    public boolean deleteByData(T item) {
        if (head == null) {
            return false;
        }

        if (head.getData().equals(item)) {
            deleteFirst();

            return true;
        }

        for (ListItem<T> currentItem = head.getNext(), previousItem = head; currentItem != null; previousItem = currentItem, currentItem = currentItem.getNext()) {
            if (currentItem.getData() == null) {
                continue;
            }

            if (currentItem.getData().equals(item)) {
                previousItem.setNext(previousItem.getNext().getNext());

                --count;

                return true;
            }
        }

        return false;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }

        T deletedData = head.getData();
        head = head.getNext();

        --count;

        return deletedData;
    }

    public void reverse() {
        if (count <= 1) {
            return;
        }

        ListItem<T> currentItem = head.getNext();
        head.setNext(null);

        while (currentItem.getNext() != null) {
            ListItem<T> nextItem = currentItem.getNext();
            currentItem.setNext(head);
            head = currentItem;
            currentItem = nextItem;
        }

        currentItem.setNext(head);
        head = currentItem;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> copy = new SinglyLinkedList<>();

        for (ListItem<T> item = head; item != null; item = item.getNext()) {
            copy.addLast(item.getData());
        }

        return copy;
    }

    private void addLast(T item) {
        if (count == 0) {
            addFirst(item);
            return;
        }

        ListItem<T> lastItem = getItemByIndex(count - 1);
        lastItem.setNext(new ListItem<>(item));

        ++count;
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть не меньше 0");
        }

        if (index >= count) {
            throw new IndexOutOfBoundsException("Индекс должен быть не больше " + (count - 1));
        }
    }

    private ListItem<T> getItemByIndex(int index) {
        ListItem<T> item = head;

        for (int i = 0; i != index; ++i) {
            item = item.getNext();
        }

        return item;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        ListItem<T> item = head;

        while (item != null) {
            stringBuilder.append(item.getData()).append(", ");

            item = item.getNext();
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}