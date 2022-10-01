package ru.academits.voropaeva.vector_main;

import ru.academits.voropaeva.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] a = {1, 2, 5};
        double[] b = {1, 2, 3, 6};

        Vector vector1 = new Vector(a);
        Vector vector2 = new Vector(b);

        System.out.print("Сумма векторов " + vector2 + " и " + vector1);
        vector2.add(vector1);
        System.out.println(" = " + vector2);

        System.out.print("Разность векторов " + vector1 + " и " + vector2);
        vector1.subtract(vector2);
        System.out.println(" = " + vector1);

        System.out.print("Умножение вектора " + vector1 + " на скаляр(2) = ");
        vector1.multiplyByScalar(2);
        System.out.println(vector1);

        System.out.print("Разворот вектора " + vector2 + " = ");
        vector2.reverse();
        System.out.println(vector2);

        System.out.println("Длина вектора " + vector1 + " = " + vector1.getLength());

        System.out.println("компонента вектора под индексом 3 " + vector1 + " = " + vector1.getElementByIndex(3));

        System.out.print("Замена компоненты вектора под индексом 1 " + vector1 + " на 2.7 = ");
        vector1.setElementByIndex(1, 2.7);
        System.out.println(vector1);

        System.out.print("Сумма векторов " + vector1 + " и " + vector2);
        Vector vectorsSum = Vector.getSum(vector1, vector2);
        System.out.println(" = " + vectorsSum);

        System.out.print("Разность векторов " + vector1 + " и " + vector2);
        Vector vectorsDifference = Vector.getDifference(vector1, vector2);
        System.out.println(" = " + vectorsDifference);

        System.out.print("Скалярное произведение векторов " + vector1 + " и " + vector2
                + " = " + Vector.getScalarProduct(vector1, vector2));
    }
}