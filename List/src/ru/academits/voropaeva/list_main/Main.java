package ru.academits.voropaeva.list_main;

import ru.academits.voropaeva.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> newList = new SinglyLinkedList<>();

        newList.addToStart(7);
        newList.addToStart(6);
        newList.addToStart(5);
        newList.addToStart(4);
        newList.addToStart(3);
        newList.addToStart(2);
        newList.addToStart(1);

        System.out.println("----------------");
        System.out.println(newList.removeValue(7));
        newList.print();
    }
}
