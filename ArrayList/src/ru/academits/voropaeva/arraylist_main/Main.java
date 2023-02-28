package ru.academits.voropaeva.arraylist_main;

import ru.academits.voropaeva.arraylist.MyArrayList;

public class Main {
    public static void main(String[] args) {
        MyArrayList<String> numbers1 = new MyArrayList<>();
        numbers1.add("0");
        numbers1.add("1");
        numbers1.add("2");
        numbers1.add("3");
        numbers1.add(null);
        numbers1.add("5");
        numbers1.add("6");
        numbers1.add("7");
        numbers1.add(null);
        numbers1.add("9");
        numbers1.add(null);
        numbers1.add("11");

        MyArrayList<String> numbers2 = new MyArrayList<>();
        numbers2.add("1000");
        numbers2.add(null);

        System.out.println("size: " + numbers1.size());
        System.out.println(numbers1.contains("100"));
        System.out.println(numbers1.remove("300"));
        System.out.println(numbers1.addAll(10, numbers2));
        System.out.println(numbers1);
        System.out.println("size: " + numbers1.size());
    }
}