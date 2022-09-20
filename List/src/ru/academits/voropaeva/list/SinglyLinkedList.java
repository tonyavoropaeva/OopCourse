package ru.academits.voropaeva.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
        head = null;
    }

    public SinglyLinkedList(ListItem<T> head, int count) {
        this.head = head;
        this.count = count;
    }

    public ListItem<T> get() {
        if (head == null) {
            throw new NullPointerException("Value is empty");
        }

        return head;
    }

    public int getCount() {
        return count;
    }

    public void setHead(ListItem<T> head) {
        this.head = head;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSize() {
        if (head == null) {
            throw new NullPointerException("Список пустой");
        }

        int size = 1;
        ListItem<T> elementReference = head;

        while (elementReference.getNext() != null) {
            ++size;

            elementReference = elementReference.getNext();
        }

        return size;
    }

    public T getInitialValue() {
        if (head == null) {
            throw new NullPointerException("Список пустой");
        }

        return head.getData();
    }

    public T getValueByIndex(int index) {
        if (head == null) {
            throw new NullPointerException("Список пустой");
        }

        if (index <= 0) {
            throw new IllegalArgumentException("Индекс должен быть больше 0");
        }

        if (index > getSize()) {
            throw new IllegalArgumentException("Индекс должен быть не больше размера списка");
        }

        ListItem<T> elementReference = head;

        for (int i = 1; i != index; ++i) {
            elementReference = elementReference.getNext();
        }

        return elementReference.getData();
    }

    public T changeValueByIndex(int index, T newValue) {
        if (head == null) {
            throw new NullPointerException("Список пустой");
        }

        if (index <= 0) {
            throw new IllegalArgumentException("Индекс должен быть больше 0");
        }

        if (index > getSize()) {
            throw new IllegalArgumentException("Индекс должен быть не больше размера списка");
        }

        ListItem<T> elementReference = head;
        ListItem<T> deletedValue = new ListItem<>(head.getData());

        if (index == 1) {
            head = head.getNext();
            addToStart(newValue);

            return deletedValue.getData();
        }

        for (int i = 2; i != index; ++i) {
            elementReference = elementReference.getNext();
        }

        ListItem<T> newItem = new ListItem<>(newValue);
        newItem.setNext(elementReference.getNext());
        elementReference.setNext(newItem);

        elementReference = elementReference.getNext();
        deletedValue = new ListItem<>(elementReference.getNext().getData());
        elementReference.setNext(elementReference.getNext().getNext());

        return deletedValue.getData();
    }

    public T deletedElementByIndex(int index) {
        if (head == null) {
            throw new NullPointerException("Список пустой");
        }

        if (index <= 0) {
            throw new IllegalArgumentException("Индекс должен быть больше 0");
        }

        if (index > getSize()) {
            throw new IllegalArgumentException("Индекс должен быть не больше размера списка");
        }

        ListItem<T> elementReference = head;
        ListItem<T> deletedValue = new ListItem<>(head.getData());

        if (index == 1) {
            head = head.getNext();

            return deletedValue.getData();
        }

        for (int i = 2; i != index; ++i) {
            elementReference = elementReference.getNext();
        }

        deletedValue = new ListItem<>(elementReference.getNext().getData());
        elementReference.setNext(elementReference.getNext().getNext());

        return deletedValue.getData();
    }

    public void addToStart(T item) {
        head = new ListItem<>(item, head);
    }

    public void addByIndex(int index, T newValue) {
        if (index <= 0) {
            throw new IllegalArgumentException("Индекс должен быть больше 0");
        }

        if (index > getSize()) {
            throw new IllegalArgumentException("Индекс должен быть не больше размера списка");
        }

        ListItem<T> elementReference = head;

        if (index == 1) {
            addToStart(newValue);
            return;
        }

        for (int i = 2; i != index; ++i) {
            elementReference = elementReference.getNext();
        }

        ListItem<T> newItem = new ListItem<>(newValue);

        newItem.setNext(elementReference.getNext());
        elementReference.setNext(newItem);
    }

    public boolean removeValue(T item) {
        if (head == null) {
            throw new NullPointerException("Список пустой");
        }

        if (head.getData() == item) {
            if (getSize() == 1) {
                head = null;

                return true;
            }

            head = head.getNext();

            return true;
        }

        for (ListItem<T> current = head, prev = null; current != null; prev = current, current = current.getNext()) {
            if (current.getData() == item) {
                if (prev != null) {
                    prev.setNext(prev.getNext().getNext());
                }

                return true;
            }
        }

        return false;
    }

    public T deleteToStart() {
        if (head == null) {
            throw new NullPointerException("Список пустой");
        }

        ListItem<T> deletedValue = new ListItem<>(head.getData());

        head = head.getNext();

        return deletedValue.getData();
    }

    public void reverseList() {
        if (head == null) {
            throw new NullPointerException("Список пустой");
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

    public SinglyLinkedList<T> copyList() {
        SinglyLinkedList<T> copy = new SinglyLinkedList<>();

        reverseList();

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            copy.addToStart(p.getData());
        }

        reverseList();

        return copy;
    }

    public void print() {
        if (head == null) {
            throw new NullPointerException("Value is empty");
        }

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            System.out.println(p.getData());
        }
    }
}