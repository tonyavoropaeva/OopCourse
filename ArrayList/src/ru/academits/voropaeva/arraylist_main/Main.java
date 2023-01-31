package ru.academits.voropaeva.arraylist_main;

import ru.academits.voropaeva.arraylist.MyArrayList;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        MyArrayList<String> numbers = new MyArrayList<>();
        numbers.add("0");
        numbers.add("1");
        numbers.add("1");
        numbers.add("1");
        numbers.add(null);
        numbers.add("3");
        numbers.add("4");
        numbers.add("5");
        numbers.add(null);
        numbers.add("7");
        numbers.add(null);
        numbers.add("9");
        numbers.add("10");

       /* MyArrayList<String> numbersInt = new MyArrayList<>();
        numbersInt.add("0");
        numbersInt.add("1");
        numbersInt.add("2");
        numbersInt.add(null);*/

        System.out.println(numbers.add(null));
        System.out.println(numbers);


        System.out.println("_______________________________________________________");

      /*  ArrayList<String> numbers1 = new ArrayList<>();
        numbers1.add("0");
        numbers1.add("1");
        numbers1.add("1");
        numbers1.add("1");
        numbers1.add(null);
        numbers1.add("3");
        numbers1.add("4");
        numbers1.add("5");
        numbers1.add(null);
        numbers1.add("7");
        numbers1.add(null);
        numbers1.add("9");
        numbers1.add("10");*/

       /* ArrayList<String> numbers2 = new ArrayList<>();
        numbers2.add("0");
        numbers2.add("1");
        numbers2.add("2");
        numbers2.add(null);

        System.out.println(numbers2.addAll(numbers2));
        System.out.println(numbers2);*/
    }
}
