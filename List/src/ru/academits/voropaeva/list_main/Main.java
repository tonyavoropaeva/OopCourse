package ru.academits.voropaeva.list_main;

import ru.academits.voropaeva.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> newList = new SinglyLinkedList<>();

        newList.addFirst(5);
        newList.addFirst(4);
        newList.addFirst(3);
        newList.addFirst(2);
        newList.addFirst(1);
        newList.addFirst(0);

        System.out.println(newList.getCount());

        // System.out.println(newList.getFirst());
        // System.out.println(newList.getByIndex(5));
        // System.out.println(newList.setByIndex(2, 100));
        // System.out.println(newList.deletedByIndex(5));
        // newList.addByIndex(5, 100);
        // System.out.println(newList.deleteByItem(1));
        // System.out.println(newList.deleteFirst());
        // newList.reverse();
        // SinglyLinkedList<Integer> list = newList.copy();

        System.out.println("----------------");
        System.out.println(newList);
    }
}