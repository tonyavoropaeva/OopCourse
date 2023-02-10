package ru.academits.voropaeva.model.temperature;

public class Celsius implements TemperatureScaleInterface {
    private double temperature;

    @Override
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public double convert(Kelvin kelvin) {
        return temperature + 273.15;
    }

    @Override
    public double convert(Fahrenheit fahrenheit) {
        return temperature * 1.8 + 32;
    }

    @Override
    public double convert(Celsius celsius) {
        return temperature;
    }
}