package ru.academits.voropaeva.list_main;

import ru.academits.voropaeva.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();
        testList.addFirst(5);
        testList.addFirst(4);
        testList.addFirst(null);
        testList.addFirst(3);
        testList.addFirst(2);
        testList.addFirst(null);

        System.out.println("Односвязный список: " + testList);
        System.out.println("Размер: " + testList.getCount());
        System.out.println("----------------");

        SinglyLinkedList<Integer> testListCopy = testList.copy();

        System.out.println("Его копия: " + testListCopy);
        System.out.println("Размер: " + testListCopy.getCount());
        System.out.println("Первый элемент: " + testListCopy.getFirst());
        System.out.println("Эл-нт по индексу 5: " + testListCopy.getByIndex(5));

        testListCopy.setByIndex(0, 100);
        testListCopy.deleteByIndex(0);
        testListCopy.addByIndex(1, 100);
        testListCopy.deleteByData(2);
        testListCopy.deleteFirst();
        testListCopy.reverse();
        testListCopy.addLast(50);
    }
}