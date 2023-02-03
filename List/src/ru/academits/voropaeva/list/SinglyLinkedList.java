package ru.academits.voropaeva.list;

import java.util.NoSuchElementException;
import java.util.Objects;

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
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть не меньше 0, сейчас он равен " + index);
        }

        if (index > count) {
            throw new IndexOutOfBoundsException("Индекс должен быть не больше " + count + ", сейчас он равен " + index);
        }

        if (index == 0) {
            addFirst(newData);
            return;
        }

        ListItem<T> previousItem = getItemByIndex(index - 1);
        ListItem<T> newItem = new ListItem<>(newData, previousItem.getNext());
        previousItem.setNext(newItem);

        ++count;
    }

    public boolean deleteByData(T data) {
        if (head == null) {
            return false;
        }

        if (Objects.equals(head.getData(), data)) {
            deleteFirst();

            return true;
        }

        for (ListItem<T> currentItem = head.getNext(), previousItem = head; currentItem != null; previousItem = currentItem, currentItem = currentItem.getNext()) {
            if (Objects.equals(currentItem.getData(), data)) {
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

        if (count == 0) {
            return copy;
        }

        copy.head = new ListItem<>(head.getData());
        copy.count = count;

        for (ListItem<T> item = head.getNext(), currentItemCopy = copy.head; item != null; item = item.getNext(), currentItemCopy = currentItemCopy.getNext()) {
            ListItem<T> nextItemCopy = new ListItem<>(item.getData());

            currentItemCopy.setNext(nextItemCopy);
        }

        return copy;
    }

    public void addLast(T item) {
        addByIndex(count, item);
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть не меньше 0, сейчас он равен " + index);
        }

        if (index >= count) {
            throw new IndexOutOfBoundsException("Индекс должен быть не больше " + (count - 1) + ", сейчас он равен " + index);
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