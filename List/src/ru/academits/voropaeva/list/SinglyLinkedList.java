package ru.academits.voropaeva.list;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    SinglyLinkedList(ListItem<T> head) {
        this.head = head;
    }

    ListItem<T> getHead() {
        if (head == null) {
            throw new NoSuchElementException("Список пуст");
        }

        return head;
    }

    void setHead(ListItem<T> head) {
        this.head = head;
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

        ListItem<T> item = head;

        if (index == 0) {
            return head.getData();
        }

        for (int i = 0; i != index; ++i) {
            item = item.getNext();
        }

        return item.getData();
    }

    public T setByIndex(int index, T newData) {
        checkIndex(index);

        T deletedData = head.getData();

        if (index == 0) {
            head = head.getNext();
            addFirst(newData);

            return deletedData;
        }

        deletedData = getItemByIndex(index).getData();
        getItemByIndex(index).setData(newData);

        return deletedData;
    }

    public T deleteByIndex(int index) {
        checkIndex(index);

        T deletedData = head.getData();

        if (index == 0) {
            head = head.getNext();

            return deletedData;
        }

        ListItem<T> item = getItemByIndex(index - 1);

        deletedData = item.getNext().getData();
        item.setNext(item.getNext().getNext());

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

        ListItem<T> data = getItemByIndex(index - 1);

        ListItem<T> newItem = new ListItem<>(newData);

        newItem.setNext(data.getNext());
        data.setNext(newItem);

        ++count;
    }

    public boolean deleteByItem(T item) {
        if (head == null) {
            return false;
        }

        if (head.getData().equals(item)) {
            head = head.getNext();
            --count;

            return true;
        }

        for (ListItem<T> current = head.getNext(), previous = head; current != null; previous = current, current = current.getNext()) {
            if (current.getData().equals(item)) {
                if (previous != null) {
                    previous.setNext(previous.getNext().getNext());
                }

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

        ListItem<T> deletedData = new ListItem<>(head.getData());

        head = head.getNext();
        --count;

        return deletedData.getData();
    }

    public void reverse() {
        if (head == null) {
            return;
        }

        ListItem<T> current = head.getNext();
        ListItem<T> next;
        head.setNext(null);

        while (current.getNext() != null) {
            next = current.getNext();
            current.setNext(head);
            head = current;
            current = next;
        }

        current.setNext(head);
        head = current;
    }

    public SinglyLinkedList<T> copy() {
        return new SinglyLinkedList<>(head);
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть не меньше 0");
        }

        if (index > getCount()) {
            throw new IndexOutOfBoundsException("Индекс должен быть не больше " + getCount());
        }
    }

    private ListItem<T> getItemByIndex(int index) {
        ListItem<T> item = head;

        if (index == 0) {
            return item;
        }

        for (int i = 0; i != index; ++i) {
            item = item.getNext();
        }

        return item;
    }

    public void print() {
        if (head == null) {
            System.out.println("Список пуст");

            return;
        }

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            System.out.println(p.getData());
        }
    }

    @Override
    public String toString() {
        if (head == null) {
            return "Список пуст";
        }

        String[] array = new String[count];
        ListItem<T> p = head;

        for (int i = 0; p != null; ++i) {
            array[i] = p.getData().toString();

            p = p.getNext();
        }

        return Arrays.toString(array);
    }
}