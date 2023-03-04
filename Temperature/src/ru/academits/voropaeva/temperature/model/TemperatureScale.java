package ru.academits.voropaeva.temperature.model;

public interface TemperatureScale {
    KelvinScale KELVIN_SCALE = new KelvinScale();
    FahrenheitScale FAHRENHEIT_SCALE = new FahrenheitScale();
    CelsiusScale CELSIUS_SCALE = new CelsiusScale();

    double getTemperature();

    void setTemperature(double temperature);

    TemperatureScale convertToCelsius();

    String toString();
}