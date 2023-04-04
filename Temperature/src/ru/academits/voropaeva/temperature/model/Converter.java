package ru.academits.voropaeva.temperature.model;

import java.util.List;

public record Converter(List<Scale> scales) implements Model {
    @Override
    public double convert(Scale scaleFrom, Scale scaleTo, double temperature) {
        return scaleTo.convertFromCelsius(scaleFrom.convertToCelsius(temperature));
    }
}