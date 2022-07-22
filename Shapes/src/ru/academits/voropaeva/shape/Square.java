package ru.academits.voropaeva.shape;

public class Square implements Shape {
    double sideLength;

    public Square(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double getWidth() {  //ширина
        return sideLength;
    }

    @Override
    public double getHeight() {   //высота
        return sideLength;
    }

    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return sideLength * 4;
    }
}
