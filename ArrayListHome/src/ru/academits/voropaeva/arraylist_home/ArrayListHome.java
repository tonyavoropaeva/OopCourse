package ru.academits.voropaeva.arraylist_home;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) throws FileNotFoundException {
        // Прочитать в список все строки из файла

        ArrayList<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("text.txt"))) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        }

        System.out.println(lines);

        /* Есть список из целых чисел. Удалить из него все четные числа. В
           этой задаче новый список создавать нельзя */

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 10, 2, 5, 6, 0, 12, 7, 9, 3, 9, 10, 17, 17, 2, 3, 17, 21));

        if (numbers.get(2) == 2) {
            numbers.remove(numbers.get(2));
        }

        for (int i = 0; i < numbers.size(); ++i) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(numbers.get(i));
            }
        }

        // или numbers.removeIf(number -> number % 2 == 0);

        System.out.println(numbers);

        /* Есть список из целых чисел, в нём некоторые числа могут
           повторяться. Надо создать новый список, в котором будут
           элементы первого списка в таком же порядке, но без
           повторений */

        ArrayList<Integer> numbersNoRepeats = new ArrayList<>();

        for (Integer number : numbers) {
            boolean isThere = numbersNoRepeats.contains(number);

            if (!isThere) {
                numbersNoRepeats.add(number);
            }
        }

        System.out.println(numbersNoRepeats);
    }
}