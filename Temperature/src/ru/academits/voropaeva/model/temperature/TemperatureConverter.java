package ru.academits.voropaeva.model.temperature;

public class TemperatureConverter implements TemperatureConverterInterface, TemperatureScaleInterface {
    TemperatureScaleInterface scaleNameFrom;
    double temperature;

    @Override
    public Kelvin getKelvin() {
        return kelvin;
    }

    @Override
    public Fahrenheit getFahrenheit() {
        return fahrenheit;
    }

    @Override
    public Celsius getCelsius() {
        return celsius;
    }

    @Override
    public void setScaleNameFrom(TemperatureScaleInterface temperatureScaleInterface) {
        scaleNameFrom = temperatureScaleInterface;
    }

    @Override
    public void setTemperature(double temperature) {
        this.temperature = temperature;
        scaleNameFrom.setTemperature(temperature);
    }

    @Override
    public double convert(Kelvin kelvin) {
        return scaleNameFrom.convert(kelvin);
    }

    @Override
    public double convert(Fahrenheit fahrenheit) {
        return scaleNameFrom.convert(fahrenheit);
    }

    @Override
    public double convert(Celsius celsius) {
        return scaleNameFrom.convert(celsius);
    }
}