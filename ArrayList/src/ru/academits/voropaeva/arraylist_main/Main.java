package ru.academits.voropaeva.arraylist_main;

import ru.academits.voropaeva.arraylist.MyArrayList;

public class Main {
    public static void main(String[] args) {
        MyArrayList<String> numbersOne = new MyArrayList<>();
        numbersOne.add("0");
        numbersOne.add("1");
        numbersOne.add("2");
        numbersOne.add("3");
        numbersOne.add(null);
        numbersOne.add("5");
        numbersOne.add("6");
        numbersOne.add("7");
        numbersOne.add(null);
        numbersOne.add("9");
        numbersOne.add(null);
        numbersOne.add("11");

        MyArrayList<String> numbersTwo = new MyArrayList<>();
        numbersTwo.add("1000");
        numbersTwo.add(null);

        System.out.println("size: " + numbersOne.size());
        System.out.println(numbersOne.contains("100"));
        System.out.println(numbersOne.remove("300"));
        System.out.println(numbersOne.addAll(10, numbersTwo));
    }
}