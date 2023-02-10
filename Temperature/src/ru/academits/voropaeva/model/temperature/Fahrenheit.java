package ru.academits.voropaeva.model.temperature;

public class Fahrenheit implements TemperatureScaleInterface {
    private double temperature;

    @Override
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public double convert(Kelvin kelvin) {
        return (temperature - 32) * 5 / 9 + 273.15;
    }

    @Override
    public double convert(Fahrenheit fahrenheit) {
        return temperature;
    }

    @Override
    public double convert(Celsius celsius) {
        return (temperature - 32) / 1.8;
    }
}