package ru.academits.voropaeva.shape;

public class Triangle implements Shape {
    double x1;
    double y1;
    double x2;
    double y2;
    double x3;
    double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public double getWidth() {
        double maxX = Math.max(x3, Math.max(x1, x2));
        double minX = Math.min(x3, Math.min(x1, x2));
        return maxX - minX;
    }

    @Override
    public double getHeight() {
        double maxY = Math.max(y3, Math.max(y1, y2));
        double minY = Math.min(y3, Math.min(y1, y2));
        return maxY - minY;
    }

    @Override
    public double getArea() {
        double semiPerimeter = (getSideAB() + getSideAC() + getSideBC()) / 2;

        return Math.sqrt(semiPerimeter *
                (semiPerimeter - getSideAB()) *
                (semiPerimeter - getSideAC()) *
                (semiPerimeter - getSideBC()));
    }

    @Override
    public double getPerimeter() {
        return getSideAB() + getSideAC() + getSideBC();
    }

    double getSideAB() {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    double getSideAC() {
        return Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));
    }

    double getSideBC() {
        return Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
    }
}
