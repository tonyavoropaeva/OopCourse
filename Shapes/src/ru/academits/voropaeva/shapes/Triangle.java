package ru.academits.voropaeva.shapes;

public final class Triangle implements Shape {
    public double x1;
    public double y1;
    public double x2;
    public double y2;
    public double x3;
    public double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public double getX3() {
        return x3;
    }

    public double getY3() {
        return y3;
    }

    @Override
    public double getWidth() {
        return Math.max(x3, Math.max(x1, x2)) - Math.min(x3, Math.min(x1, x2));
    }

    @Override
    public double getHeight() {
        return Math.max(y3, Math.max(y1, y2)) - Math.min(y3, Math.min(y1, y2));
    }

    @Override
    public double getArea() {
        double semiPerimeter = getPerimeter() / 2;

        return Math.sqrt(semiPerimeter *
                (semiPerimeter - getSideLength(x2, x1, y2, y1)) *
                (semiPerimeter - getSideLength(x3, x1, y3, y1)) *
                (semiPerimeter - getSideLength(x3, x2, y3, y2)));
    }

    @Override
    public double getPerimeter() {
        return getSideLength(x2, x1, y2, y1) + getSideLength(x3, x1, y3, y1) + getSideLength(x3, x2, y3, y2);
    }

    private double getSideLength(double start1, double start2, double end1, double end2) {
        return Math.sqrt(Math.pow(start2 - start1, 2) + Math.pow(end2 - end1, 2));
    }

    @Override
    public String toString() {
        return "Shape = Triangle"
                + ", x1 = " + getX1()
                + ", y1 = " + getY1()
                + ", x2 = " + getX2()
                + ", y2 = " + getY2()
                + ", x3 = " + getX3()
                + ", y3 = " + getY3()
                + "Area = " + getArea()
                + "Perimeter = " + getPerimeter();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Triangle shape = (Triangle) o;

        return x1 == shape.x1 && y1 == shape.y1
                && x2 == shape.x2 && y2 == shape.y2
                && x3 == shape.x3 && y3 == shape.y3;
    }

    @Override
    public int hashCode() {
        final int prime = 49;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);
        return hash;
    }
}