package ru.academits.voropaeva.model.temperature;

public interface TemperatureScaleInterface {
    void setTemperature(double temperature);

    double convert(Kelvin kelvin);

    double convert(Fahrenheit fahrenheit);

    double convert(Celsius celsius);
}