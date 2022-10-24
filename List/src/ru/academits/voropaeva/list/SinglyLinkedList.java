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
        T previousData = itemByIndex.getData();
        itemByIndex.setData(newData);

        return previousData;
    }

    public T deleteByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return deleteFirst();
        }

        ListItem<T> nodeByIndex = getItemByIndex(index - 1);
        T deletedData = nodeByIndex.getNext().getData();
        nodeByIndex.setNext(nodeByIndex.getNext().getNext());

        --count;

        return deletedData;
    }

    public void addFirst(T item) {
        head = new ListItem<>(item, head);
        ++count;
    }

    public void addByIndex(int index, T newData) {
        checkIndex(index);

        if (index == 0) {
            addFirst(newData);
            return;
        }

        ListItem<T> nodeByIndex = getItemByIndex(index - 1);

        ListItem<T> newItem = new ListItem<>(newData, nodeByIndex.getNext());

        nodeByIndex.setNext(newItem);

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
        if (head == null || count == 1) {
            return;
        }

        ListItem<T> currentItem = head.getNext();
        head.setNext(null);

        ListItem<T> nextItem;

        while (currentItem.getNext() != null) {
            nextItem = currentItem.getNext();
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
            copy.addFirst(item.getData());
        }

        copy.reverse();
        copy.count = count;

        return copy;
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

        StringBuilder items = new StringBuilder();
        items.append("[");

        ListItem<T> item = head;

        for (int i = 0; i < count; ++i) {
            items.append(item.getData()).append(", ");

            item = item.getNext();
        }

        items.delete(items.length() - 2, items.length());
        items.append("]");

        return items.toString();
    }
}