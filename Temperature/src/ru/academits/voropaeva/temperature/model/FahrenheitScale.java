package ru.academits.voropaeva.temperature.model;

public class FahrenheitScale implements TemperatureScale {
    double temperature;

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
        CELSIUS_SCALE.setTemperature((temperature - 32) / 1.8);

        return CELSIUS_SCALE;
    }
}
