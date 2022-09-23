package ru.academits.voropaeva.vector;

import java.util.Arrays;

public class Vector {
    private double[] vector;
    private int n;

    public double[] getVector() {
        return vector;
    }

    public void setVector(double[] vector) {
        this.vector = vector;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размер вектора должен быть больше 0");
        }

        vector = new double[n];
    }

    public Vector(Vector vector) {
        this.vector = vector.vector;
        this.n = vector.n;
    }

    public Vector(double[] array) {
        vector = new double[array.length];

        System.arraycopy(array, 0, vector, 0, vector.length);
    }

    public Vector(int n, double[] array) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размер вектора должен быть больше 0");
        }

        vector = new double[n];

        System.arraycopy(array, 0, vector, 0, Math.min(array.length, n));
    }

    public int getSize() {
        if (vector == null) {
            return 0;
        }

        return vector.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(vector);
    }

    public void addVectors(Vector addedVector) {
        if (getSize() >= addedVector.getSize()) {
            int min = Math.min(getSize(), addedVector.getSize());
            double[] arrayVectorMax = getVector();

            for (int i = 0; i < min; ++i) {
                arrayVectorMax[i] = addedVector.getVector()[i] + getVector()[i];
            }

            setVector(arrayVectorMax);
        } else {
            double[] arrayVectorMin = getVector();
            Vector vector = new Vector(addedVector.getSize(), arrayVectorMin);
            double[] result = vector.getVector();

            for (int i = 0; i < vector.getSize(); ++i) {
                result[i] = result[i] + addedVector.getVector()[i];
            }

            setVector(result);
        }
    }

    public void differenceVector(Vector subtrahendVector) {
        if (getSize() > subtrahendVector.getSize()) {
            int min = Math.min(getSize(), subtrahendVector.getSize());
            double[] arrayVectorMax = getVector();

            for (int i = 0; i < min; ++i) {
                arrayVectorMax[i] = getVector()[i] - subtrahendVector.getVector()[i];
            }

            setVector(arrayVectorMax);
        } else {
            double[] arrayVectorMin = getVector();
            Vector vector = new Vector(subtrahendVector.getSize(), arrayVectorMin);
            double[] result = vector.getVector();

            for (int i = 0; i < vector.getSize(); ++i) {
                result[i] = result[i] - subtrahendVector.getVector()[i];
            }

            setVector(result);
        }
    }

    public void multiplyVectorByScalar(int scalar) {
        double[] arrayVector = getVector();

        for (int i = 0; i < getSize(); i++) {
            arrayVector[i] = arrayVector[i] * scalar;
        }

        setVector(arrayVector);
    }

    public void reverseVector() {
        double[] arrayVector = getVector();

        for (int i = 0; i < getSize(); i++) {
            arrayVector[i] = arrayVector[i] * -1;
        }

        setVector(arrayVector);
    }

    public double getVectorLength() {
        double[] arrayVector = getVector();
        double sum = 0;

        double[] result = new double[arrayVector.length];

        for (int i = 0; i < getSize(); i++) {
            result[i] = arrayVector[i] * arrayVector[i];
            sum += result[i];
        }

        sum = Math.abs(Math.sqrt(sum));

        return sum;
    }

    public double getElementByIndex(int index) {
        if (index > getSize()) {
            throw new IllegalArgumentException("Индекс не должен быть больше вектора");
        }

        if (index == 0) {
            throw new IllegalArgumentException("Индекс должен быть больше 0");
        }

        double[] arrayVector = getVector();

        if (getSize() == 1) {
            return arrayVector[0];
        }

        return arrayVector[index - 1];
    }

    public void changeElementByIndex(double element, int index) {
        if (index > getSize()) {
            throw new IllegalArgumentException("Индекс не должен быть больше вектора");
        }

        if (index == 0) {
            throw new IllegalArgumentException("Индекс должен быть больше 0");
        }

        double[] arrayVector = getVector();

        if (getSize() == 1) {
            arrayVector[0] = element;
        } else {
            arrayVector[index - 1] = element;
        }

        setVector(arrayVector);
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

        double[] arrayVector = getVector();
        double[] arrayObject = object.getVector();

        for (int i = 0; i < getSize(); i++) {
            if (arrayVector[i] != arrayObject[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        hash = prime * hash + Arrays.hashCode(vector);
        hash = prime * hash + getSize();

        return hash;
    }

    public static Vector getVectorsSum(Vector vector1, Vector vector2) {
        int maxSize = Math.max(vector1.getSize(), vector2.getSize());

        double[] arrayVector1 = vector1.getVector();
        double[] arrayVector2 = vector2.getVector();

        double[] resultVector = new double[maxSize];

        for (int i = 0; i < vector1.getSize(); i++) {
            resultVector[i] = arrayVector1[i] + arrayVector2[i];
        }

        return new Vector(resultVector);
    }

    public static Vector getDifferenceVectors(Vector vector1, Vector vector2) {
        int maxSize = Math.max(vector1.getSize(), vector2.getSize());

        double[] arrayVector1 = vector1.getVector();
        double[] arrayVector2 = vector2.getVector();

        double[] resultVector = new double[maxSize];

        for (int i = 0; i < vector1.getSize(); i++) {
            resultVector[i] = arrayVector1[i] - arrayVector2[i];
        }

        return new Vector(resultVector);
    }

    public static double getVectorsScalarMultiplication(Vector vector1, Vector vector2) {
        int maxSize = Math.max(vector1.getSize(), vector2.getSize());

        double[] arrayVector1 = vector1.getVector();
        double[] arrayVector2 = vector2.getVector();

        Vector vectorOne = new Vector(maxSize, arrayVector1);
        Vector vectorTwo = new Vector(maxSize, arrayVector2);

        arrayVector1 = vectorOne.getVector();
        arrayVector2 = vectorTwo.getVector();

        double resultVector = 0;

        for (int i = 0; i < maxSize; i++) {
            resultVector += arrayVector1[i] * arrayVector2[i];
        }

        return resultVector;
    }
}