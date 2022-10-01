package ru.academits.voropaeva.vector;

import java.util.Arrays;

public class Vector {
    private double[] vectorComponents;

    public Vector(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Размерность вектора должена быть больше 0, сейчас она = " + length);
        }

        vectorComponents = new double[length];
    }

    public Vector(Vector vectorComponents) {
        double[] array = Arrays.copyOf(vectorComponents.vectorComponents, vectorComponents.vectorComponents.length);
        this.vectorComponents = Arrays.copyOf(array, array.length);
    }

    public Vector(double[] array) {
        if (array == null) {
            throw new NullPointerException("array = null");
        }

        vectorComponents = Arrays.copyOf(array, array.length);
    }

    public Vector(int length, double[] array) {
        if (array == null) {
            throw new NullPointerException("array = null");
        }

        if (length <= 0) {
            throw new IndexOutOfBoundsException("Размерность вектора должена быть больше 0, сейчас она = " + length);
        }

        vectorComponents = Arrays.copyOf(array, length);
    }

    public int getSize() {
        return vectorComponents.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(vectorComponents);
    }

    public void add(Vector addend) {
        int min = Math.min(getSize(), addend.getSize());

        for (int i = 0; i < min; ++i) {
            vectorComponents[i] = vectorComponents[i] + addend.vectorComponents[i];
        }

        if (addend.getSize() > getSize()) {
            Vector copy = new Vector(addend.vectorComponents);
            double[] array = copy.vectorComponents;

            if (min >= 0) System.arraycopy(vectorComponents, 0, array, 0, min);

            vectorComponents = Arrays.copyOf(array, array.length);
        }
    }

    public void subtract(Vector subtrahend) {
        int min = Math.min(getSize(), subtrahend.getSize());

        for (int i = 0; i < min; ++i) {
            vectorComponents[i] = vectorComponents[i] - subtrahend.vectorComponents[i];
        }

        if (subtrahend.getSize() > getSize()) {
            Vector copy = new Vector(subtrahend.vectorComponents);
            copy.reverse();
            double[] array = copy.vectorComponents;

            if (min >= 0) System.arraycopy(vectorComponents, 0, array, 0, min);

            vectorComponents = Arrays.copyOf(array, array.length);
        }
    }

    public void multiplyByScalar(int scalar) {
        for (int i = 0; i < getSize(); i++) {
            vectorComponents[i] = vectorComponents[i] * scalar;
        }
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (int i = 0; i < getSize(); i++) {
            sum += vectorComponents[i] * vectorComponents[i];
        }

        sum = Math.sqrt(sum);

        return sum;
    }

    public double getElementByIndex(int index) {
        if (index >= getSize()) {
            throw new IndexOutOfBoundsException("Индекс не должен быть больше " + (getSize() - 1));
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть не меньше 0");
        }

        return vectorComponents[index];
    }

    public void setElementByIndex(int index, double element) {
        if (index >= getSize()) {
            throw new IndexOutOfBoundsException("Индекс не должен быть больше " + (getSize() - 1));
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс должен быть не меньше 0");
        }

        vectorComponents[index] = element;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Vector object = (Vector) o;

        if (getSize() != object.getSize()) {
            return false;
        }

        for (int i = 0; i < getSize(); i++) {
            if (vectorComponents[i] != object.vectorComponents[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(vectorComponents);

        return hash;
    }

    public static Vector getSum(Vector addend1, Vector addend2) {
        Vector copy = new Vector(addend1);
        copy.add(addend2);

        return copy;
    }

    public static Vector getDifference(Vector minuend, Vector subtrahend) {
        Vector copy = new Vector(minuend);
        copy.subtract(subtrahend);

        return copy;
    }

    public static double getScalarProduct(Vector multiplier1, Vector multiplier2) {
        int minSize = Math.min(multiplier1.getSize(), multiplier2.getSize());
        double result = 0;

        for (int i = 0; i < minSize; i++) {
            result += multiplier1.vectorComponents[i] * multiplier2.vectorComponents[i];
        }

        return result;
    }
}