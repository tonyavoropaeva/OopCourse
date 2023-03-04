package ru.academits.voropaeva.temperature.model;

public class KelvinScale implements TemperatureScale {
    private double temperature;

    @Override
    public double getTemperature() {
        return temperature;
    }

    @Override
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public CelsiusScale convertToCelsius() {
        CELSIUS_SCALE.setTemperature(temperature - 273.15);

        return CELSIUS_SCALE;
    }

    @Override
    public String toString() {
        return "K";
    }
}