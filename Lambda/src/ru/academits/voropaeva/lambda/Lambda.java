package ru.academits.voropaeva.lambda;

import ru.academits.voropaeva.person.Person;

import java.util.*;
import java.util.stream.Collectors;

public class Lambda {
    public static void main(String[] args) {
        ArrayList<Person> persons = new ArrayList<>(Arrays.asList(
                new Person("Илья", 10),
                new Person("Оля", 10),
                new Person("Катя", 30),
                new Person("Оля", 21),
                new Person("Влад", 12),
                new Person("Иван", 25),
                new Person("Крис", 23),
                new Person("Илья", 26)
        ));

        // А) получить список уникальных имен +

        List<String> uniqueNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .toList();

        // Б) вывести список уникальных имен в формате: Имена: Иван, Сергей, Петр.

        System.out.printf("Имена: %s.%n", uniqueNames.stream().collect(Collectors.joining(", ")));

        // В) получить список людей младше 18, посчитать для них средний возраст +

        List<Person> list = persons.stream()
                .filter(x -> x.getAge() < 18)
                .toList();

        OptionalDouble ageAverage = list.stream()
                .mapToInt(Person::getAge)
                .average();

        // Г) при помощи группировки получить Map, в котором ключи – имена, а значения – средний возраст

        Map<String, Double> personsMap = persons.stream()
                .collect(Collectors.groupingBy(Person::getName,
                        Collectors.averagingInt(Person::getAge)));

        // Д) получить людей, возраст которых от 20 до 45, вывести в консоль их имена в порядке убывания возраста +

        List<Person> peopleFrom20To45 = persons.stream()
                .filter(person -> person.getAge() >= 20 && person.getAge() <= 45)
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                .toList();

        peopleFrom20To45.forEach(person -> System.out.println(person.getName() + " " + person.getAge()));
    }
}
