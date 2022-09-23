package ru.academits.voropaeva.vector_main;

import ru.academits.voropaeva.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] a = {1, 2, 5};
        double[] b = {1, 2, 3, 6};

        Vector vector1 = new Vector(a);
        Vector vector2 = new Vector(b);

        System.out.print("Сумма векторов " + vector1 + " и " + vector2);
        vector1.addVectors(vector2);
        System.out.println(" = " + vector1);

        System.out.print("Разность векторов " + vector1 + " и " + vector2);
        vector1.differenceVector(vector2);
        System.out.println(" = " + vector1);

        System.out.print("Умножение вектора " + vector1 + " на скаляр(2) = ");
        vector1.multiplyVectorByScalar(2);
        System.out.println(vector1);

        System.out.print("Разворот вектора " + vector2 + " = ");
        vector2.reverseVector();
        System.out.println(vector2);

        System.out.println("Длина вектора " + vector1 + " = " + vector1.getVectorLength());

        System.out.println("4-ая компонента вектора " + vector1 + " = " + vector1.getElementByIndex(4));

        System.out.print("Замена 1-ой компоненты вектора " + vector1 + " на 2.7 = ");
        vector1.changeElementByIndex(2.7, 1);
        System.out.println(vector1);

        System.out.print("Сумма векторов " + vector1 + " и " + vector2);
        Vector vectorsSum = Vector.getVectorsSum(vector1, vector2);
        System.out.println(" = " + vectorsSum);

        System.out.print("Разность векторов " + vector1 + " и " + vector2);
        Vector vectorsDifference = Vector.getDifferenceVectors(vector1, vector2);
        System.out.println(" = " + vectorsDifference);

        System.out.print("Скалярное произведение векторов " + vector1 + " и " + vector2
                + " = " + Vector.getVectorsScalarMultiplication(vector1, vector2));
    }
}