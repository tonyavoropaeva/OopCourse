package ru.academits.voropaeva.hashtable_main;

import ru.academits.voropaeva.hashtable.HashTable;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<String> hashTable = new HashTable<>();
        hashTable.add("40");
        hashTable.add("60");
        hashTable.add("70");
        hashTable.add("40");
        hashTable.add("10");
        hashTable.add("10");
        hashTable.add(null);

        System.out.println("Размер: " + hashTable.size());
        System.out.println("Вместимость: " + hashTable.getCapacity());
        System.out.println(hashTable.contains(null));
        System.out.println(Arrays.toString(hashTable.toArray()));

        String[] array = new String[15];
        System.out.println(Arrays.toString(hashTable.toArray(array)));

        ArrayList<String> lines = new ArrayList<>(Arrays.asList("10", "10", "40", null));
        System.out.println(hashTable.containsAll(lines));

        for (String s : hashTable) {
            System.out.print(s + ", ");
        }
    }
}