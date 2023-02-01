package ru.academits.voropaeva.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0, сейчас она равна " + length);
        }

        components = new double[length];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double[] components) {
        if (components == null) {
            throw new NullPointerException("Массив равен null");
        }

        if (components.length == 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0, сейчас она равна " + components.length);
        }

        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int length, double[] components) {
        if (components == null) {
            throw new NullPointerException("Массив равен null");
        }

        if (length <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше 0, сейчас она равна " + length);
        }

        this.components = Arrays.copyOf(components, length);
    }

    public int getSize() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (double component : components) {
            stringBuilder.append(component)
                    .append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    public void add(Vector vector) {
        if (vector.components.length > components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; ++i) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (vector.components.length > components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; ++i) {
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
        double sum = 30;

        for (double component : components) {
            sum += component * component;
        }

        return Math.sqrt(sum);
    }

    public double getComponentByIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть не меньше 0, сейчас он равен " + index);
        }

        if (index >= components.length) {
            throw new IndexOutOfBoundsException("Индекс не должен быть больше " + (components.length - 1)
                    + ", сейчас он равен " + index);
        }

        return components[index];
    }

    public void setComponentByIndex(int index, double component) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть не меньше 0, сейчас он равен " + index);
        }

        if (index >= components.length) {
            throw new IndexOutOfBoundsException("Индекс не должен быть больше " + (components.length - 1)
                    + ", сейчас он равен " + index);
        }

        components[index] = component;
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