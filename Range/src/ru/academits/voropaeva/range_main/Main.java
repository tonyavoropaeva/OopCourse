package ru.academits.voropaeva.range_main;

import ru.academits.voropaeva.range.Range;

public class Main {
    public static void main(String[] args) {
        Range rangeAB = new Range(-10, 7);

        System.out.printf("������� �� �������� ��������� ����� �� a = %.2f �� b = %.2f %n", rangeAB.getFrom(), rangeAB.getTo());

        rangeAB.setFrom(-16);
        rangeAB.setTo(20);

        double a = rangeAB.getFrom();
        double b = rangeAB.getTo();

        System.out.printf("����� ������� ����� a = %.2f �� b = %.2f %n", a, b);
        System.out.printf("����� ab = %.2f %n", rangeAB.getLength());

        double point1 = 10;
        System.out.printf("����� �� ����� %.2f � �� ������� ab? - �����: %s", point1, rangeAB.isInside(10));
    }
}