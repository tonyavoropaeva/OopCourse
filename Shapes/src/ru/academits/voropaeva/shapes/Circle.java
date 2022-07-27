package ru.academits.voropaeva.shapes;

public class Circle implements Shape {
    public double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double getWidth() {
        return radius * 2;
    }

    @Override
    public double getHeight() {
        return radius * 2;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * radius * Math.PI;
    }

    @Override
    public String toString() {
        return "Shape = Circle"
                + ", radius = " + getRadius()
                + ", diameter = " + getHeight()
                + ", Area = " + getArea()
                + ", Perimeter = " + getPerimeter();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Circle shape = (Circle) o;

        return radius == shape.radius;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(radius);
    }
}