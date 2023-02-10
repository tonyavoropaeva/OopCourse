package ru.academits.voropaeva.model.temperature;

public class Kelvin implements TemperatureScaleInterface {
    private double temperature;

    @Override
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public double convert(Kelvin kelvin) {
        return temperature;
    }

    @Override
    public double convert(Fahrenheit fahrenheit) {
        return (temperature - 273.15) * 9 / 5 + 32;
    }

    @Override
    public double convert(Celsius celsius) {
        return temperature - 273.15;
    }
}