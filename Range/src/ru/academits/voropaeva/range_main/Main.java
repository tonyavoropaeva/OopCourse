package ru.academits.voropaeva.range_main;

import ru.academits.voropaeva.range.Range;

public class Main {
    public static void main(String[] args) {
        Range segmentAB = new Range(-10, 7);

        System.out.printf("������� �� �������� ��������� ����� �� a = %.2f �� b = %.2f %n", segmentAB.getFrom(), segmentAB.getTo());

        segmentAB.setFrom(-16);
        segmentAB.setTo(20);

        double a = segmentAB.getFrom();
        double b = segmentAB.getTo();

        System.out.printf("����� ������� ����� a = %.2f �� b = %.2f %n", a, b);
        System.out.printf("����� ab = %.2f %n", segmentAB.getLength());

        double point1 = 10;
        System.out.printf("����� �� ����� %.2f � �� ������� ab? - �����: %s", point1, segmentAB.isInside(10));
    }
}