package ru.academits.voropaeva.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Размерность вектора должена быть больше 0");
        }

        components = new double[length];
    }

    public Vector(Vector vector) {
        this.components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] vectorArray) {
        if (vectorArray == null) {
            throw new NullPointerException("Массив пуст");
        }

        if (vectorArray.length == 0) {
            throw new IllegalArgumentException("Размерность вектора должена быть больше 0");
        }

        components = Arrays.copyOf(vectorArray, vectorArray.length);
    }

    public Vector(int length, double[] vectorArray) {
        if (vectorArray == null) {
            throw new NullPointerException("Массив пуст");
        }

        if (length <= 0) {
            throw new IllegalArgumentException("Размерность вектора должена быть больше 0");
        }

        components = Arrays.copyOf(vectorArray, length);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder vector = new StringBuilder("{");

        for (double components : components) {
            vector.append(components)
                    .append(", ");
        }

        vector.delete(vector.length() - 2, vector.length() + 1);
        vector.append("}");

        return vector.toString();
    }

    public void add(Vector vector) {
        int min = Math.min(components.length, vector.components.length);

        if (vector.components.length > components.length) {
            double[] vectorArray = Arrays.copyOf(vector.components, vector.components.length);
            System.arraycopy(components, 0, vectorArray, 0, min);

            components = Arrays.copyOf(vectorArray, vectorArray.length);
        }

        for (int i = 0; i < min; ++i) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        int min = Math.min(components.length, vector.components.length);

        if (vector.components.length > components.length) {
            double[] vectorArray = new double[vector.components.length];
            System.arraycopy(components, 0, vectorArray, 0, min);

            components = Arrays.copyOf(vectorArray, vectorArray.length);
        }

        for (int i = 0; i < components.length; ++i) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyByScalar(int scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double components : components) {
            sum += components * components;
        }

        return Math.sqrt(sum);
    }

    public double getComponentByIndex(int index) {
        if (index >= components.length) {
            throw new IndexOutOfBoundsException("Индекс не должен быть больше " + (components.length - 1));
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть не меньше 0");
        }

        return components[index];
    }

    public void setElementByIndex(int index, double element) {
        if (index >= components.length) {
            throw new IndexOutOfBoundsException("Индекс не должен быть больше " + (components.length - 1));
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть не меньше 0");
        }

        components[index] = element;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Vector vector = (Vector) o;

        if (components.length != vector.components.length) {
            return false;
        }

        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(components);

        return hash;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector copy = new Vector(vector1);
        copy.add(vector2);

        return copy;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector copy = new Vector(vector1);
        copy.subtract(vector2);

        return copy;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        int minSize = Math.min(vector1.components.length, vector2.components.length);
        double result = 0;

        for (int i = 0; i < minSize; i++) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }
}