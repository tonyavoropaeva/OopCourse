package ru.academits.voropaeva.temperature.model;

public interface Scale {
    double convertToCelsius(double temperature);

    double convertFromCelsius(double temperature);
}