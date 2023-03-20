package ru.academits.voropaeva.temperature.model;

import java.util.ArrayList;

public class Converter implements Model {
    ArrayList<Scale> scales;

    public Converter(ArrayList<Scale> scales) {
        this.scales = scales;
    }

    @Override
    public ArrayList<Scale> getScales() {
        return scales;
    }

    @Override
    public double convert(Scale scaleFrom, Scale scaleTo, double temperature) {
        return scaleTo.convertFromCelsius(scaleFrom.convertToCelsius(temperature));
    }
}