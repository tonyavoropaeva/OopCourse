package ru.academits.voropaeva.range_main;

import ru.academits.voropaeva.range.Range;

public class Main {
    public static void main(String[] args) {
        Range segmentAB = new Range(-10, 7);

        System.out.printf("Отрезок на числовом диапазоне равен от a = %.2f до b = %.2f %n", segmentAB.getFrom(), segmentAB.getTo());

        segmentAB.setFrom(-16);
        segmentAB.setTo(20);

        double a = segmentAB.getFrom();
        double b = segmentAB.getTo();

        System.out.printf("Новый отрезок равен a = %.2f до b = %.2f %n", a, b);
        System.out.printf("Длина ab = %.2f %n", segmentAB.getLength());

        double point1 = 10;
        System.out.printf("Лежит ли точка %.2f с на отрезке ab? - Ответ: %s", point1, segmentAB.isInside(10));
    }
}