package ru.academits.voropaeva.shapes_main;

import ru.academits.voropaeva.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {new Circle(10),
                new Rectangle(10, 5),
                new Square(25),
                new Triangle(4, 4, 4, -2, -6, -6),
                new Circle(6),
                new Square(17),
                new Rectangle(7, 6),
                new Square(3)};

        System.out.println("���������� � ������ � ������������ ��������: " + getShapeWithMaxArea(shapes));
        System.out.println("���-�� � ������ �� ������ ���������� ����������: " + getShapeWithSecondMaxPerimeter(shapes));
    }

    public static Shape getShapeWithMaxArea(Shape[] shapes) {
        Arrays.sort(shapes, new ShapeAreaComparator());

        return shapes[shapes.length - 1];
    }

    public static Shape getShapeWithSecondMaxPerimeter(Shape[] shapes) {
        Arrays.sort(shapes, new ShapePerimeterComparator());

        return shapes[shapes.length - 2];
    }
}