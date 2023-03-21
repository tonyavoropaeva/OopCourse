package ru.academits.voropaeva.range_main;

import ru.academits.voropaeva.range.Range;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        /* Range rangeAB = new Range(-10, 7);

        System.out.printf("Отрезок на числовом диапазоне равен от a = %.2f до b = %.2f %n", rangeAB.getFrom(), rangeAB.getTo());

        rangeAB.setFrom(-16);
        rangeAB.setTo(20);

        double a = rangeAB.getFrom();
        double b = rangeAB.getTo();

        System.out.printf("Новый отрезок равен a = %.2f до b = %.2f %n", a, b);
        System.out.printf("Длина ab = %.2f %n", rangeAB.getLength());

        double point1 = 10;
        System.out.printf("Лежит ли точка %.2f с на отрезке ab? - Ответ: %s", point1, rangeAB.isInside(10)); */

        Range range1 = new Range(1, 20);
        Range range2 = new Range(10, 15);

        System.out.println("Пересечение: " + range1.getIntervalIntersection(range2));
        System.out.println("Объединение: " + Arrays.toString(range1.getIntervalUnion(range2)));
        System.out.println("Разность: " + Arrays.toString(range1.getIntervalDifference(range2)));
    }
}