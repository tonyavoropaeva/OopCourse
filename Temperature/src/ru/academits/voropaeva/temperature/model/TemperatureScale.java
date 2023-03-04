package ru.academits.voropaeva.temperature.model;

public interface TemperatureScale {
    CelsiusScale CELSIUS_SCALE = new CelsiusScale();
    FahrenheitScale FAHRENHEIT_SCALE = new FahrenheitScale();
    KelvinScale KELVIN_SCALE = new KelvinScale();

    double getTemperature();

    void setTemperature(double temperature);

    CelsiusScale convertToCelsius();

    String toString();
}
