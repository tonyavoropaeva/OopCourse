package ru.academits.voropaeva.arraylist_home;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHome {
    public static void main(String[] args) {
        // Прочитать в список все строки из файла

        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("text.txt"))) {
            String line = reader.readLine();

            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }

            System.out.println("Данные из файла: " + lines);
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка, файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка: " + e);
        }

        /* Есть список из целых чисел. Удалить из него все четные числа. В
           этой задаче новый список создавать нельзя */

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(2, 1, 10, 2, 5, 6, 0, 12, 7, 9, 3, 9, 10, 17, 17, 2, 3, 17, 21));

        System.out.println("Список: " + numbers);

        for (int i = 0; i < numbers.size(); ++i) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
                --i;
            }
        }

        System.out.println("Список после удаления четных чисел: " + numbers);

        /* Есть список из целых чисел, в нём некоторые числа могут
           повторяться. Надо создать новый список, в котором будут
           элементы первого списка в таком же порядке, но без
           повторений */

        ArrayList<Integer> numbersWithoutRepetitions = new ArrayList<>(numbers.size());

        for (Integer number : numbers) {
            if (!numbersWithoutRepetitions.contains(number)) {
                numbersWithoutRepetitions.add(number);
            }
        }

        System.out.println("Новый список после удаления повторяющихся чисел: " + numbersWithoutRepetitions);
    }
}