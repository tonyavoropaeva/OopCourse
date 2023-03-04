package ru.academits.voropaeva.temperature.model;

public class CelsiusScale implements TemperatureScale {
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
        return CELSIUS_SCALE;
    }

    public FahrenheitScale convertToFahrenheit() {
        FAHRENHEIT_SCALE.setTemperature(temperature * 1.8 + 32);

        return FAHRENHEIT_SCALE;
    }

    public KelvinScale convertToKelvin() {
        KELVIN_SCALE.setTemperature(temperature + 273.15);

        return KELVIN_SCALE;
    }

    @Override
    public String toString() {
        return "°C";
    }
}