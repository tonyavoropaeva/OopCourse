package ru.academits.voropaeva.arraylist_home;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHome {
    public static void main(String[] args) throws IOException {
        // Прочитать в список все строки из файла

        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("text.txt"))) {
            String line = reader.readLine();

            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка, файл не найден");
        }

        System.out.println("Данные из файла: " + lines);

        /* Есть список из целых чисел. Удалить из него все четные числа. В
           этой задаче новый список создавать нельзя */

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 10, 2, 5, 6, 0, 12, 7, 9, 3, 9, 10, 17, 17, 2, 3, 17, 21));

        System.out.println("Список: " + numbers);

        numbers.removeIf(number -> number % 2 == 0);

        System.out.println("Список после удаления четных чисел: " + numbers);

        /* Есть список из целых чисел, в нём некоторые числа могут
           повторяться. Надо создать новый список, в котором будут
           элементы первого списка в таком же порядке, но без
           повторений */

        ArrayList<Integer> numbersNoRepeats = new ArrayList<>(numbers.size());

        for (Integer number : numbers) {

            if (!numbersNoRepeats.contains(number)) {
                numbersNoRepeats.add(number);
            }
        }

        System.out.println("Новый список после удаления повторяющихся чисел: " + numbersNoRepeats);
    }
}