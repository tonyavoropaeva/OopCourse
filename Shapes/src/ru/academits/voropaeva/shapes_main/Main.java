package ru.academits.voropaeva.shapes_main;

import ru.academits.voropaeva.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(10),
                new Rectangle(10, 5),
                new Square(25),
                new Triangle(4, 4, 4, -2, -6, -6),
                new Circle(6),
                new Square(17),
                new Rectangle(7, 6),
                new Square(3)};

        System.out.println("Информация о фигуре с максимальной площадью: " + getShapeWithMaxArea(shapes));
        System.out.println("Инф-ия о фигуре со вторым наибольшим периметром: " + getShapeWithSecondMaxPerimeter(shapes));
    }

    public static Shape getShapeWithMaxArea(Shape[] shapes) {
        if (shapes.length == 0) {
            return null;
        }

        Arrays.sort(shapes, new ShapeAreaComparator());

        return shapes[shapes.length - 1];
    }

    public static Shape getShapeWithSecondMaxPerimeter(Shape[] shapes) {
        if (shapes.length == 0 || shapes.length == 1) {
            return null;
        }

        Arrays.sort(shapes, new ShapePerimeterComparator());

        return shapes[shapes.length - 2];
    }
}