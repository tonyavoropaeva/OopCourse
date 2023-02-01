package ru.academits.voropaeva.lambda_main;

import ru.academits.voropaeva.lambda.Person;

import java.util.*;
import java.util.stream.Collectors;

public class Lambda {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Илья", 10),
                new Person("Оля", 10),
                new Person("Катя", 30),
                new Person("Оля", 21),
                new Person("Влад", 12),
                new Person("Иван", 25),
                new Person("Крис", 23),
                new Person("Илья", 26)
        );

        // А) получить список уникальных имен +

        List<String> uniqueNames = persons.stream()
                .map(Person::name)
                .distinct()
                .toList();

        System.out.println("Список уникальных имен: " + uniqueNames);

        // Б) вывести список уникальных имен в формате: Имена: Иван, Сергей, Петр. +

        System.out.println(uniqueNames.stream().collect(Collectors.joining(", ", "Имена: ", ".")));

        // В) получить список людей младше 18, посчитать для них средний возраст +

        List<Person> personsTo18 = persons.stream()
                .filter(x -> x.age() < 18)
                .toList();

        System.out.println(personsTo18.stream()
                .map(person -> person.name() + " " + person.age())
                .collect(Collectors.joining(", ", "Список людей младше 18: ", ".")));

        OptionalDouble averageAge = personsTo18.stream()
                .mapToInt(Person::age)
                .average();

        System.out.println("Средний возраст людей младше 18: " + averageAge.orElse(-1));

        // Г) при помощи группировки получить Map, в котором ключи – имена, а значения – средний возраст +

        Map<String, Double> nameToAverageAgeOfPersons = persons.stream()
                .collect(Collectors.groupingBy(Person::name, Collectors.averagingInt(Person::age)));

        nameToAverageAgeOfPersons.forEach((k, v) -> System.out.println("name:" + k + " averageAge:" + v));

        // Д) получить людей, возраст которых от 20 до 45, вывести в консоль их имена в порядке убывания возраста +

        List<Person> personsFrom20To45 = persons.stream()
                .filter(person -> person.age() >= 20 && person.age() <= 45)
                .sorted((o1, o2) -> o2.age() - o1.age())
                .toList();

        personsFrom20To45.forEach(person -> System.out.println(person.name() + " " + person.age()));
    }
}
